/*
 * Created on Mar 28, 2006
 *
 */
package mojo.km.notification;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import messaging.notification.UpdateNotificationDefEvent;
import messaging.notification.to.NotificationIdentityAddressDefBean;
import mojo.km.messaging.EventFactory;
import mojo.km.naming.NotificationConstants;
import mojo.km.utilities.MessageUtil;
import mojo.naming.NotificationControllerSerivceNames;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @author Jim Fisher
 *  
 */
public class LoadNotificationDefinitions
{
    static private final String NONE = "none";

    static private final String EMAIL = "email";

    static private final String NOTICE = "notice";

    // Notification Def Attributes
    private final String TOPIC = "topic";

    private final String DEFAULT_SEVERITY = "defaultSeverity";

    private final String DEFAULT_SUBJECT = "defaultSubject";

    private final String TRANSPORT_TYPE = "transportType";

    // Notification Def Children
    static private final String SOURCE_ADDRESS = "Source";

    static private final String DESTINATION_ADDRESS = "Destination";

    private final String TEMPLATE = "Template";

    // Identity Address Attributes
    private final String TRANSPORTTYPE = "transportType";

    private final String BEAN = "bean";

    private final String PROPERTY = "property";

    private final String VALUE = "value";

    private final String CONTEXT = "context";

    private final String NOTIF_DEF_CONFIG_FILE = "notificationDefinition.xml";

    private Map notifDefMap;

    private Set senderSet;

    public LoadNotificationDefinitions()
    {
        this.senderSet = new HashSet();
        this.notifDefMap = new HashMap();
    }

    private void parseNotificationDef(Element element)
    {
        Attribute topicAttr = element.getAttribute(TOPIC);
        String topic = topicAttr.getValue();

        String defaultSeverity = this.getAttributeData(topic, element, DEFAULT_SEVERITY);
        String defaultSubject = this.getAttributeData(topic, element, DEFAULT_SUBJECT);

        UpdateNotificationDefEvent updateEvent = (UpdateNotificationDefEvent) EventFactory
                .getInstance(NotificationControllerSerivceNames.UPDATENOTIFICATIONDEF);
        updateEvent.setNotificationTopic(topic);
        updateEvent.setDefaultServerity(defaultSeverity);
        updateEvent.setDefaultSubject(defaultSubject);

        List sourceChildren = element.getChildren(SOURCE_ADDRESS);
        List destinationChildren = element.getChildren(DESTINATION_ADDRESS);

        if (sourceChildren == null || sourceChildren.size() == 0)
        {
            String msg = "Notification definition(" + topic + ") has no source identity defined.";
            throw new NoSuchElementException(msg);
        }
        else if (sourceChildren.size() > 1)
        {
            String msg = "Notification definition(" + topic + ") has than one source defined.";
            throw new IllegalArgumentException(msg);
        }

        Element sourceAddressDefElement = (Element) sourceChildren.get(0);
        NotificationIdentityAddressDefBean identityAddressDef = this.parseNotificationIdentityAddressDef(topic,
                sourceAddressDefElement, SOURCE_ADDRESS);
        updateEvent.addIdentityAddressDefs(identityAddressDef);

        Iterator i = destinationChildren.iterator();
        while (i.hasNext())
        {
            Element addressDefElement = (Element) i.next();
            identityAddressDef = this
                    .parseNotificationIdentityAddressDef(topic, addressDefElement, DESTINATION_ADDRESS);
            updateEvent.addIdentityAddressDefs(identityAddressDef);
        }

        Element templateElement = element.getChild(TEMPLATE);
        if (templateElement == null)
        {
            String msg = "Notification definition(" + topic + ") has no template defined.";
            throw new NoSuchElementException(msg);
        }

        String template = this.parseReportTemplate(topic, templateElement);
        updateEvent.setTemplate(template);

        MessageUtil.postRequest(updateEvent);
    }

    /**
     * @param templateElement
     */
    private String parseReportTemplate(String topic, Element templateElement)
    {
        String messageTemplate = templateElement.getText();
        if (messageTemplate.trim().equals(""))
        {
            String msg = "Notification definition(" + topic + ") has no template defined.";
            throw new NoSuchElementException(msg);
        }
        System.out.println("Report Template: " + messageTemplate);
        return messageTemplate;
    }

    /**
     * @param addressDefElement
     */
    private NotificationIdentityAddressDefBean parseNotificationIdentityAddressDef(String topic, Element element,
            String direction)
    {
        if (LoadNotificationDefinitions.SOURCE_ADDRESS.equalsIgnoreCase(direction))
        {
            boolean hasSender = this.senderSet.add(topic);
            if (hasSender == false)
            {
                String msg = "Notification definition: " + topic + " has more than one sender";
                throw new IllegalArgumentException(msg);
            }
            direction = NotificationConstants.SEND;
        }
        else if (LoadNotificationDefinitions.DESTINATION_ADDRESS.equalsIgnoreCase(direction))
        {
            direction = NotificationConstants.RECEIVE;
        }
        else
        {
            String msg = "Invalid direction value (" + direction + ") in notification definition: " + topic;
            throw new IllegalArgumentException(msg);
        }

        String transportType = null;
        if (NotificationConstants.RECEIVE.equals(direction))
        {
            // *** transport attribute (only required for receivers)
            transportType = this.getAttributeData(topic, element, TRANSPORTTYPE);

            if (LoadNotificationDefinitions.EMAIL.equalsIgnoreCase(transportType))
            {
                transportType = NotificationConstants.EMAIL_TRANSPORT;
            }
            else if (LoadNotificationDefinitions.NOTICE.equalsIgnoreCase(transportType))
            {
                transportType = NotificationConstants.NOTICE_TRANSPORT;
            }
            else
            {
                String msg = "Invalid transportType value (" + transportType + ") in notification definition: " + topic;
                throw new IllegalArgumentException(msg);
            }
        }

        String value = this.getAttributeData(topic, element, VALUE);
        boolean hasValue = !(value == null || "".equals(value) || NONE.equalsIgnoreCase(value));

        String context = this.getAttributeData(topic, element, CONTEXT);
        String bean = this.getAttributeData(topic, element, BEAN);
        String property = this.getAttributeData(topic, element, PROPERTY);

        if (hasValue == false)
        {
            this.checkRequiredAttribute(topic, CONTEXT, context);
            this.checkRequiredAttribute(topic, BEAN, bean);
            this.checkRequiredAttribute(topic, PROPERTY, property);
        }

        return this.createNotificationIdentityAddressDef(transportType, direction, bean, property, context, value);
    }

    private String getErrorParsingIdentityAddress(String topic, String attribute)
    {
        return "Error loading " + NOTIF_DEF_CONFIG_FILE + " for topic: " + topic + " attribute " + attribute
                + " is required if value is not provided";
    }

    private String getAttributeData(String topic, Element element, String attributeName)
    {
        Attribute attr = element.getAttribute(attributeName);
        String attrVal = null;
        if (attr != null)
        {
            attrVal = attr.getValue();
            attrVal = attrVal.trim();
            if (NONE.equalsIgnoreCase(attrVal) || "".equals(attrVal))
            {
                attrVal = null;
            }
        }

        return attrVal;
    }

    private void checkRequiredAttribute(String topic, String attributeName, String value)
    {
        if (value != null)
        {
            value = value.trim();
        }
        else
        {
            throw new NoSuchElementException(this.getErrorParsingIdentityAddress(topic, attributeName));
        }
    }

    /**
     * @param addressDefElement
     */
    private NotificationIdentityAddressDefBean createNotificationIdentityAddressDef(String transportType,
            String direction, String bean, String property, String context, String value)
    {
        NotificationIdentityAddressDefBean identityDef = new NotificationIdentityAddressDefBean();
        System.out.println("Create notif addr def: " + value);
        identityDef.setTransportType(transportType);
        identityDef.setDirection(direction);
        identityDef.setBeanName(bean);
        identityDef.setProperty(property);
        identityDef.setContext(context);
        identityDef.setValue(value);
        return identityDef;
    }
    
    private void init() throws IOException, JDOMException
    {
        this.init(NOTIF_DEF_CONFIG_FILE);
    }

    public void init(String aConfigFile) throws IOException, JDOMException
    {
        String config = System.getProperty("mojo.config");
        if(config == null || config.equals(""))
        {
            throw new RuntimeException("mojo.config has not been set: pass as an argument (i.e. -Dmojo.config=multisource.xml)");
        }
        mojo.km.context.ContextManager.currentContext();
        
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource(aConfigFile);

        if (url != null)
        {
            System.out.println("Loading " + aConfigFile + " from: " + url.getPath());
        }
        else
        {
            throw new FileNotFoundException(aConfigFile);
        }

        InputStream is = url.openStream();

        SAXBuilder saxReader = new SAXBuilder();
        Document document = saxReader.build(is);
        Element root = document.getRootElement();

        // iterate through child elements of root
        List children = root.getChildren();
        if (children != null)
        {
            Iterator i = children.iterator();
            while (i.hasNext())
            {
                Element element = (Element) i.next();
                this.parseNotificationDef(element);
            }
        }
    }

    public static void main(String[] args)
    {
        LoadNotificationDefinitions loader = new LoadNotificationDefinitions();
        try
        {
            if (args.length > 0)
            {
                loader.init(args[0]);
            }
            else
            {
                loader.init();
            }
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JDOMException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

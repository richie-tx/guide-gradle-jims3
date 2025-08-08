/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.notification;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.notification.domintf.ICreateNotification;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportRequestEvent;
import mojo.km.naming.NotificationConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import mojo.km.reporting.IReport;
import mojo.km.reporting.text.TextReporting;

/**
 * @author Jim Fisher
 *  
 */
public class NotificationDef extends PersistentObject
{

    static public NotificationDef find(String notificationId)
    {
        IHome home = new Home();
        NotificationDef notification = (NotificationDef) home.find(notificationId, NotificationDef.class);
        return notification;
    }

    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, NotificationDef.class);
    }

    static public NotificationDef findByTopic(String topic)
    {
        IHome home = new Home();
        Iterator i = home.findAll("topic", topic, NotificationDef.class);
        NotificationDef notificationDef = null;
        if (i.hasNext())
        {
            notificationDef = (NotificationDef) i.next();
        }
        return notificationDef;
    }

    private String defaultSubject;

    /**
     * Properties for destinationIdentityDefs
     * 
     * @referencedType mojo.km.notification.NotificationIdentityAddressDef
     * @detailerDoNotGenerate true
     */
    private Collection identityDefs;

    private String severityLevel;

    private String sourceIdentityDefId;

    private String topic;

    private String transportType;

    /**
     * Clears all pd.codetable.Code from class relationship collection.
     */
    public void clearIdentityDefs()
    {
        this.initIdentityDefs();
        this.identityDefs.clear();
    }

    /**
     * @param beans
     * @return
     * @throws Exception
     */
    public Notification createNotification(ICreateNotification createNotif) throws Exception
    {
        Notification notification = new Notification();
        notification.setNotificationDefId(this.getOID().toString());
        notification.setSeverityLevelCode(this.getSeverityLevel());
        notification.setStatusCode(NotificationConstants.PENDING);

        String subject = createNotif.getSubject();

        if (subject == null)
        {
            notification.setSubject(this.getDefaultSubject());
        }
        else
        {
            notification.setSubject(subject);
        }

        notification.setSentDate(new Date());

        // resolve fields
        IReport reporting = new TextReporting();
        ReportRequestEvent requestEvent = new ReportRequestEvent();
        requestEvent.setReportName(createNotif.getNotificationTopic());

        List beans = createNotif.getContentBeans();
        int len = beans.size();
        for (int i = 0; i < len; i++)
        {
            requestEvent.addDataObject(beans.get(i));
        }

        byte[] bytes = reporting.getByteOutput(requestEvent);

        String message = new String(bytes);

        notification.setMessage(message);
                
        notification.update(createNotif.getAttachment());        

        return notification;
    }

    /**
     * @return
     */
    public String getDefaultSubject()
    {
        fetch();
        return defaultSubject;
    }

    /**
     * @return
     */
    public Collection getIdentityDefs()
    {
        fetch();
        this.initIdentityDefs();
        return this.identityDefs;
    }

    /**
     * @return
     */
    public String getSeverityLevel()
    {
        fetch();
        return severityLevel;
    }

    /**
     * @return
     */
    public String getTopic()
    {
        fetch();
        return topic;
    }

    /**
     * @return Returns the transportType.
     */
    public String getTransportType()
    {
        fetch();
        return transportType;
    }

    /**
     *  
     */
    private void initIdentityDefs()
    {
        if (this.identityDefs == null)
        {
            if (this.getOID() == null)
            {
                new Home().bind(this);
            }
            this.identityDefs = new mojo.km.persistence.ArrayList(NotificationIdentityAddressDef.class, "notificationDefId",
                    (String) getOID());
        }
    }

    /**
     * insert a mojo.km.notification.NotificationIdentityAddressDef into class relationship
     * collection.
     * 
     * @param anObject
     */
    public void insertIdentityDefs(NotificationIdentityAddressDef anObject)
    {
        this.initIdentityDefs();
        this.identityDefs.add(anObject);
    }

    /**
     * @param string
     */
    public void setDefaultSubject(String aDefaultSubject)
    {
        if (this.defaultSubject == null || !this.defaultSubject.equals(aDefaultSubject))
        {
            markModified();
        }
        this.defaultSubject = aDefaultSubject;
    }

    /**
     * @param string
     */
    public void setSeverityLevel(String aSeverityLevel)
    {
        if (this.severityLevel == null || !this.severityLevel.equals(aSeverityLevel))
        {
            markModified();
        }
        this.severityLevel = aSeverityLevel;
    }

    /**
     * @param string
     */
    public void setTopic(String aTopic)
    {
        if (this.topic == null || !this.topic.equals(aTopic))
        {
            markModified();
        }
        this.topic = aTopic;
    }

    /**
     * @param transportType
     *            The transportType to set.
     */
    public void setTransportType(String aTransportType)
    {
        if (this.transportType == null || !this.transportType.equals(aTransportType))
        {
            markModified();
        }
        this.transportType = aTransportType;
    }
}

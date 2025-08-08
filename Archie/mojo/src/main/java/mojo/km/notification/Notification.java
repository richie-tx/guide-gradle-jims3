/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.notification;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import messaging.notification.domintf.IAttachment;
import messaging.notification.domintf.INotification;
import messaging.notification.domintf.INotificationIdentityAddressDef;
import messaging.notification.to.EmailAttachmentBean;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;
import mojo.km.naming.NotificationConstants;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *  
 */
public class Notification extends PersistentObject
{

    static public Notification find(String notificationId)
    {
        IHome home = new Home();
        Notification notification = (Notification) home.find(notificationId, Notification.class);
        return notification;
    }

    /**
     * @return
     */
    public static Collection findAllUnsent()
    {
        Collection notifications = new mojo.km.persistence.ArrayList(Notification.class, "statusCode",
                NotificationConstants.PENDING);
        return notifications;
    }

    public static Iterator findUserNotices(IEvent event)
    {
        IHome home = new Home();
        return home.findAll(event, PendingNotification.class);
    }

    private byte[] attachment;

    private String attachmentContentType;

    private String attachmentName;

    /**
     * Properties for identities
     * 
     * @referencedType mojo.km.identityaddress.IdentityAddress
     * @detailerDoNotGenerate true
     */
    private Collection identities;

    private String message;

    /**
     * Properties for notificationDef
     * 
     * @referencedType mojo.km.notification.NotificationDef
     * @detailerDoNotGenerate true
     */
    private NotificationDef notificationDef;

    private String notificationDefId;

    private Date sentDate;

    private String severityLevelCode;

    private String statusCode;

    private String subject;

    public Notification()
    {
    }

    public void fill(INotification notification)
    {
        if (notification != null)
        {
            if (this.getOID() != null)
            {
                notification.setNotificationId(this.getOID().toString());
            }
            notification.setSentDate(this.getSentDate());
            notification.setSeverityLevelCode(this.getSeverityLevelCode());
            notification.setStatusCode(this.getStatusCode());
            notification.setSubject(this.getSubject());
            if (this.getMessage() != null)
            {
                String messageString = String.valueOf(this.getMessage());
                notification.setMessage(messageString);
            }
            else
            {
                notification.setMessage(null);
            }
            
            byte[] content = this.getAttachment();
            if (content != null)
            {
                IAttachment attachment = new EmailAttachmentBean();
                attachment.setContent(content);
                attachment.setContentType(this.getAttachmentContentType());
                attachment.setName(this.getAttachmentName());
                notification.setAttachment(attachment);
            }
        }
    }    

    public void findAll()
    {
        fetch();
    }

    public byte[] getAttachment()
    {
        fetch();
        return attachment;
    }

    /**
     * @return Returns the attachmentContentType.
     */
    public String getAttachmentContentType()
    {
        fetch();
        return attachmentContentType;
    }

    /**
     * @return Returns the attachmentName.
     */
    public String getAttachmentName()
    {
        fetch();
        return attachmentName;
    }

    /**
     * @return
     */
    public Collection getIdentities()
    {
        this.initIdentities();
        return this.identities;
    }

    /**
     * @return
     */
    public String getMessage()
    {
        fetch();
        return message;
    }

    /**
     * Gets referenced type mojo.km.notification.NotificationDef
     * 
     * @return NotificationDef notificationDef
     */
    public NotificationDef getNotificationDef()
    {
        fetch();
        this.initNotificationDef();
        return this.notificationDef;
    }

    /**
     * @return
     */
    public String getNotificationDefId()
    {
        fetch();
        return notificationDefId;
    }

    /**
     * @return
     */
    public Date getSentDate()
    {
        fetch();
        return sentDate;
    }

    /**
     * @return
     */
    public String getSeverityLevelCode()
    {
        fetch();
        return severityLevelCode;
    }

    /**
     * @return
     */
    public String getStatusCode()
    {
        fetch();
        return statusCode;
    }

    /**
     * @return
     */
    public String getSubject()
    {
        fetch();
        return subject;
    }

    /**
     *  
     */
    private void initIdentities()
    {
        if (this.identities == null)
        {
            if (this.getOID() == null)
            {
                new Home().bind(this);
            }
            this.identities = new mojo.km.persistence.ArrayList(NotificationIdentityAddress.class, "notificationId", ""
                    + getOID());
        }
    }

    /**
     *  
     */
    private void initNotificationDef()
    {
        if (notificationDef == null)
        {
            notificationDef = (NotificationDef) new mojo.km.persistence.Reference(notificationDefId, NotificationDef.class)
                    .getObject();
        }
    }

    public void insertIdentities(IdentityAddress anObject, INotificationIdentityAddressDef identityDef)
    {
        this.initIdentities();
        NotificationIdentityAddress actual = new NotificationIdentityAddress();
        if (this.getOID() == null)
        {
            new Home().bind(this);
        }
        if (anObject.getOID() == null)
        {
            new Home().bind(anObject);
        }
        actual.setChild(anObject);
        actual.setParent(this);

        // TODO Set other data properties on this association table
        actual.setContext(identityDef.getContext());
        actual.setTransportDirection(identityDef.getDirection());
        actual.setTransportType(identityDef.getTransportType());
        actual.setStatus(NotificationConstants.PENDING);

        this.identities.add(actual);
    }

    public void insertIdentityContext(INotificationIdentityAddressDef identityDef)
    {
        // In this case the identity address is not known, therefore the child object is not set

        this.initIdentities();
        NotificationIdentityAddress actual = new NotificationIdentityAddress();
        if (this.getOID() == null)
        {
            new Home().bind(this);
        }

        actual.setParent(this);

        // TODO Set other data properties on this association table
        actual.setContext(identityDef.getContext());
        actual.setTransportDirection(identityDef.getDirection());
        actual.setTransportType(identityDef.getTransportType());
        actual.setStatus(NotificationConstants.PENDING);

        this.identities.add(actual);
    }

    public void setAttachment(byte[] anAttachment)
    {
        if (this.attachment == null || !this.attachment.equals(anAttachment))
        {
            markModified();
        }
        this.attachment = anAttachment;
    }

    /**
     * @param anAttachmentContentType
     *            The attachmentContentType to set.
     */
    public void setAttachmentContentType(String anAttachmentContentType)
    {
        if (this.attachmentContentType == null || !this.attachmentContentType.equals(anAttachmentContentType))
        {
            markModified();
        }
        this.attachmentContentType = anAttachmentContentType;
    }

    /**
     * @param attachmentName
     *            The anAttachmentName to set.
     */
    public void setAttachmentName(String anAttachmentName)
    {
        if (this.attachmentName == null || !this.attachmentName.equals(anAttachmentName))
        {
            markModified();
        }
        this.attachmentName = anAttachmentName;
    }

    /**
     * @param string
     */
    public void setMessage(String aMessage)
    {
        markModified();
        this.message = aMessage;
    }

    /**
     * @param string
     */
    public void setNotificationDefId(String string)
    {
        if (this.notificationDefId == null || !this.notificationDefId.equals(string))
        {
            markModified();
        }
        this.notificationDefId = string;
    }

    /**
     * @param date
     */
    public void setSentDate(Date aDate)
    {
        if (this.sentDate == null || !this.sentDate.equals(aDate))
        {
            markModified();
        }
        this.sentDate = aDate;
    }

    /**
     * @param string
     */
    public void setSeverityLevelCode(String string)
    {
        if (this.severityLevelCode == null || !this.sentDate.equals(string))
        {
            markModified();
        }
        this.severityLevelCode = string;
    }

    /**
     * @param string
     */
    public void setStatusCode(String string)
    {
        if (this.statusCode == null || !this.statusCode.equals(string))
        {
            markModified();
        }
        this.statusCode = string;
    }

    /**
     * @param string
     */
    public void setSubject(String string)
    {
        if (this.subject == null || !this.subject.equals(string))
        {
            markModified();
        }
        this.subject = string;
    }

    public void update(INotification notification)
    {
        if (notification != null)
        {
            this.setSentDate(notification.getSentDate());
            this.setSeverityLevelCode(notification.getSeverityLevelCode());
            this.setStatusCode(notification.getStatusCode());
        }
    }

    /**
     * @param attachment2
     */
    public void update(IAttachment anAttachment)
    {
        if(anAttachment != null)
        {
            this.setAttachmentName(anAttachment.getName());
            this.setAttachment(anAttachment.getContent());
            this.setAttachmentContentType(anAttachment.getContentType());
        }
    }
}
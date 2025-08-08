/*
 * Created on Apr 3, 2006
 *
 */
package mojo.km.notification;

import java.util.Date;
import java.util.Iterator;

import messaging.notification.domintf.INotification;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *  
 */
public class PendingNotification extends PersistentObject
{

    private byte[] attachment;

    private String destinationIdentityAddrId;

    private String destinationIdentityValue;

    private String destinationStatus;

    private String message;

    private String notificationId;

    private String notificationStatus;

    private Date sentDate;

    private String severityLevel;

    private String sourceIdentityAddrId;

    private String sourceIdentityValue;

    private String subject;

    private String transportType;

    public void fill(INotification notification)
    {
        if (notification != null)
        {
            notification.setNotificationId(this.getNotificationId());
            notification.setSentDate(this.getSentDate());
            notification.setSeverityLevelCode(this.getSeverityLevel());
            notification.setStatusCode(this.getDestinationStatus());
            notification.setSubject(this.getSubject());
            notification.setMessage(this.getMessage());
        }
    }
    
    /**
     * @return
     */
    public static Iterator findAll()
    {
        IHome home = new Home();
        return home.findAll(PendingNotification.class);
    }

    /**
     * @return Returns the attachment.
     */
    public byte[] getAttachment()
    {
        fetch();
        return attachment;
    }

    /**
     * @return
     */
    public String getDestinationIdentityAddrId()
    {
        fetch();
        return destinationIdentityAddrId;
    }

    /**
     * @return
     */
    public String getDestinationIdentityValue()
    {
        fetch();
        return destinationIdentityValue;
    }

    /**
     * @return
     */
    public String getDestinationStatus()
    {
        fetch();
        return destinationStatus;
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
     * @return
     */
    public String getNotificationId()
    {
        fetch();
        return notificationId;
    }

    /**
     * @return
     */
    public String getNotificationStatus()
    {
        fetch();
        return notificationStatus;
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
    public String getSeverityLevel()
    {
        fetch();
        return severityLevel;
    }

    /**
     * @return
     */
    public String getSourceIdentityAddrId()
    {
        fetch();
        return sourceIdentityAddrId;
    }

    /**
     * @return
     */
    public String getSourceIdentityValue()
    {
        fetch();
        return sourceIdentityValue;
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
     * @return
     */
    public String getTransportType()
    {
        fetch();
        return transportType;
    }

    /**
     * @param attachment
     *            The attachment to set.
     */
    public void setAttachment(byte[] anAttachment)
    {
        if (this.attachment == null || !this.attachment.equals(anAttachment))
        {
            markModified();
        }
        this.attachment = anAttachment;
    }

    /**
     * @param string
     */
    public void setDestinationIdentityAddrId(String aDestinationIdentityAddrId)
    {
        if (this.destinationIdentityAddrId == null || !this.destinationIdentityAddrId.equals(aDestinationIdentityAddrId))
        {
            markModified();
        }
        this.destinationIdentityAddrId = aDestinationIdentityAddrId;
    }

    /**
     * @param string
     */
    public void setDestinationIdentityValue(String aDestinationIdentityValue)
    {
        if (this.destinationIdentityValue == null || !this.destinationIdentityValue.equals(aDestinationIdentityValue))
        {
            markModified();
        }
        this.destinationIdentityValue = aDestinationIdentityValue;
    }

    /**
     * @param string
     */
    public void setDestinationStatus(String aDestinationStatus)
    {
        if (this.destinationStatus == null || !this.destinationStatus.equals(aDestinationStatus))
        {
            markModified();
        }
        this.destinationStatus = aDestinationStatus;
    }

    /**
     * @param string
     */
    public void setMessage(String aMessage)
    {
        if (this.message == null || !this.message.equals(aMessage))
        {
            markModified();
        }
        this.message = aMessage;
    }

    /**
     * @param string
     */
    public void setNotificationId(String aNotificationId)
    {
        if (this.notificationId == null || !this.notificationId.equals(aNotificationId))
        {
            markModified();
        }
        this.notificationId = aNotificationId;
    }

    /**
     * @param string
     */
    public void setNotificationStatus(String aNotificationStatus)
    {
        if (this.notificationStatus == null || !this.notificationStatus.equals(aNotificationStatus))
        {
            markModified();
        }
        this.notificationStatus = aNotificationStatus;
    }

    /**
     * @param date
     */
    public void setSentDate(Date aSentDate)
    {
        if (this.sentDate == null || !this.sentDate.equals(aSentDate))
        {
            markModified();
        }
        this.sentDate = aSentDate;
    }

    /**
     * @param string
     */
    public void setSeverityLevel(String aSeverityLevel)
    {
        if (this.severityLevel == null || !this.sentDate.equals(aSeverityLevel))
        {
            markModified();
        }
        this.severityLevel = aSeverityLevel;
    }

    /**
     * @param string
     */
    public void setSourceIdentityAddrId(String aSourceIdentityAddrId)
    {
        if (this.sourceIdentityAddrId == null || !this.sourceIdentityAddrId.equals(aSourceIdentityAddrId))
        {
            markModified();
        }
        this.sourceIdentityAddrId = aSourceIdentityAddrId;
    }

    /**
     * @param string
     */
    public void setSourceIdentityValue(String aSourceIdentityValue)
    {
        if (this.sourceIdentityValue == null || !this.sourceIdentityValue.equals(aSourceIdentityValue))
        {
            markModified();
        }
        this.sourceIdentityValue = aSourceIdentityValue;
    }

    /**
     * @param string
     */
    public void setSubject(String aSubject)
    {
        if (this.subject == null || !this.subject.equals(aSubject))
        {
            markModified();
        }
        this.subject = aSubject;
    }

    /**
     * @param string
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

/*
 * Created on Mar 9, 2006
 *
 */
package messaging.notification.to;

import java.util.Date;

import messaging.notification.domintf.IAttachment;
import messaging.notification.domintf.INotification;

/**
 * @author Jim Fisher
 *  
 */
public class NotificationBean implements INotification
{
    private String subject;

    private String statusCode;

    private String severityLevelCode;

    private Date sentDate;

    private String message;

    private String notificationId;

    private String topic;

    private IAttachment attachment;

    public String getMessage()
    {
        return this.message;
    }

    public String getNotificationId()
    {
        return this.notificationId;
    }

    public Date getSentDate()
    {
        return this.sentDate;
    }

    public String getSeverityLevelCode()
    {
        return this.severityLevelCode;
    }

    public String getStatusCode()
    {
        return this.statusCode;
    }

    public String getSubject()
    {
        return this.subject;
    }

    /**
     * @param string
     */
    public void setMessage(String string)
    {
        message = string;
    }

    /**
     * @param i
     */
    public void setNotificationId(String string)
    {
        notificationId = string;
    }

    /**
     * @param date
     */
    public void setSentDate(Date date)
    {
        sentDate = date;
    }

    /**
     * @param string
     */
    public void setSeverityLevelCode(String string)
    {
        severityLevelCode = string;
    }

    /**
     * @param string
     */
    public void setStatusCode(String string)
    {
        statusCode = string;
    }

    /**
     * @param string
     */
    public void setSubject(String string)
    {
        subject = string;
    }

    /**
     * @return
     */
    public String getTopic()
    {
        return topic;
    }

    /**
     * @param string
     */
    public void setTopic(String string)
    {
        topic = string;
    }

    public IAttachment getAttachment()
    {
        return this.attachment;
    }

    public void setAttachment(IAttachment anAttachment)
    {
        this.attachment = anAttachment;
    }
}

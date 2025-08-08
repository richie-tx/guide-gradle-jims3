/*
 * Created on Mar 9, 2006
 *
 */
package messaging.notification.domintf;

import java.util.Date;

/**
 * @author Jim Fisher
 *
 */
public interface INotification
{	
	String getMessage();
	String getNotificationId();
	Date getSentDate();
	String getSeverityLevelCode();
	String getStatusCode();
	String getSubject();
	String getTopic();
	IAttachment getAttachment();
	void setMessage(String string);
	void setNotificationId(String string);	
	void setSentDate(Date date);
	void setSeverityLevelCode(String string);
	void setStatusCode(String string);
	void setSubject(String string);
	void setTopic(String string);
	void setAttachment(IAttachment anAttachment);
}

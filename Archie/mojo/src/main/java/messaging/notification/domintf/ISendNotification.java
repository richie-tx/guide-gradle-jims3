/*
 * Created on Mar 10, 2006
 *
 */
package messaging.notification.domintf;

import java.util.List;

/**
 * @author Jim Fisher
 *
 */
public interface ISendNotification
{	
	List getDestinationIdentities();
	INotification getNotification();
	String getSourceIdentity();
	void addDestinationIdentity(String identity);
	void setNotification(INotification notification);
	void setSourceIdentity(String identity);
}

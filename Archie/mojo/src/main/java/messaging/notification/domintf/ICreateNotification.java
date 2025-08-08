/*
 * Created on Mar 29, 2006
 *
 */
package messaging.notification.domintf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import messaging.identityaddress.domintf.IAddressable;

/**
 * @author Jim Fisher
 *
 */
public interface ICreateNotification
{
	void addContentBean(Serializable bean);
	void addIdentity(String context, IAddressable addressable);
	IAttachment getAttachment();
	List getContentBeans();
	Map getIdentities();
	String getNotificationTopic();
	String getSubject();
	void setAttachment(IAttachment anAttachment);
	void setNotificationTopic(String string);
	void setSubject(String aSubject);
}

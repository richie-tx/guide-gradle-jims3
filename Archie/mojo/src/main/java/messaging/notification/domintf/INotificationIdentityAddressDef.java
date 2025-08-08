/*
 * Created on Mar 30, 2006
 *
 */
package messaging.notification.domintf;

import java.io.Serializable;

/**
 * @author Jim Fisher
 *
 */
public interface INotificationIdentityAddressDef extends Serializable
{
	/**
	 * @return
	 */
	public String getBeanName();

	/**
	 * @return
	 */
	public String getDirection();

	/**
	 * @return
	 */
	public String getProperty();

	/**
	 * @return
	 */
	public String getTransportType();

	/**
	 * @return
	 */
	public String getValue();
	/**
	 * @param string
	 */
	public void setBeanName(String string);
	/**
	 * @param string
	 */
	public void setDirection(String string);
	/**
	 * @param string
	 */
	public void setProperty(String string);
	
	public void setTransportType(String string);

	/**
	 * @param string
	 */
	public void setValue(String string);
	
	public String getContext();
	
	public void setContext(String string);
}

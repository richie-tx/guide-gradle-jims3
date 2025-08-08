/*
 * Created on Mar 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.identity;

import mojo.km.messaging.IEvent;

/**
 * @author jfisher
 *
 */
public class GetIdentityAddressEvent implements IEvent
{
	private String name;
	private String topic;
	private String value;

	public String getServer()
	{
		return this.name;
	}

	public void setServer(String name)
	{
		this.name = name;
	}

	public String getTopic()
	{
		return this.topic;
	}

	public void setTopic(String aService)
	{
		this.topic = aService;
	}

	public String hashKey()
	{
		return GetIdentityAddressEvent.class.getName();
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		value = string;
	}

}

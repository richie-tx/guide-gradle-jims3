/*
 * Created on Mar 31, 2006
 *
 */
package messaging.identityaddress;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetIdentityGroupEvent extends RequestEvent
{
	private String groupName;
	/**
	 * @return
	 */
	public String getGroupName()
	{
		return groupName;
	}

	/**
	 * @param string
	 */
	public void setGroupName(String string)
	{
		groupName = string;
	}

}

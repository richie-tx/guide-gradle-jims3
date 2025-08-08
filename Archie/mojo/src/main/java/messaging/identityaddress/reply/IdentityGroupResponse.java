/*
 * Created on Mar 31, 2006
 *
 */
package messaging.identityaddress.reply;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author Jim Fisher
 *
 */
public class IdentityGroupResponse extends ResponseEvent
{
	private String groupName;
	private Collection members;
	
	public IdentityGroupResponse()
	{
		this.members = new ArrayList();
	}
	
	/**
	 * @return
	 */
	public Collection getMembers()
	{
		return members;
	}

	/**
	 * @param address
	 */
	public void addMember(String member)
	{
		this.members.add(member);
	}

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

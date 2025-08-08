/*
 * Created on Mar 28, 2006
 *
 */
package messaging.identityaddress;

import java.util.HashSet;
import java.util.Set;

import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *
 */
public class UpdateIdentityGroupEvent extends RequestEvent
{
	private String groupName;
	private Set members;
	
	public UpdateIdentityGroupEvent()
	{
		this.members = new HashSet();
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
	
	public void addMember(String string)
	{
		members.add(string);
	}
	
	public Set getMembers()
	{		
		return this.members;
	}
}

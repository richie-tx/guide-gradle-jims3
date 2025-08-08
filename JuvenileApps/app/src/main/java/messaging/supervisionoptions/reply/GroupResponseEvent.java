/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import messaging.transferobjects.OrganizationTO;
import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class GroupResponseEvent extends ResponseEvent implements Comparable
{
	private String groupId;
	private String agencyId;
	private String statusCd;	
	private String name;
	private String parentGroupId;
	private Collection subgroups = new ArrayList();

	/**
	 * @return
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * 
	 */
	public void setGroupId( String anID )
	{
		groupId = anID;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * 
	 */
	public void setAgencyId( String anID )
	{
		agencyId = anID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * 
	 * @param statusCd
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 
	 */
	public void setName( String aName )
	{
		name = aName;
	}

	/**
	 * @return
	 */
	public Collection getSubgroups()
	{
		Collections.sort((ArrayList)subgroups);
				return (Collection)subgroups;
	}

	/**
	 * @param collection
	 */
	public void setSubgroups(Collection collection)
	{
		subgroups = collection;
		
	}

	/**
	 * 
	 */
	public void addSubgroup( GroupResponseEvent evt )
	{
		subgroups.add( evt );
	}
	
	/**
	 * @return name
	 */
	public int compareTo(Object obj) throws ClassCastException
	{
		GroupResponseEvent evt = (GroupResponseEvent)obj;
		return name.compareTo(evt.getName());
	}
			
	public String getParentGroupId() {
		return parentGroupId;
	}
	public void setParentGroupId(String parentGroupId) {
		this.parentGroupId = parentGroupId;
	}
	
	public static Comparator groupNameComparator = new Comparator( )
	{
		public int compare( Object group, Object other_group )
		{
			String group_name = ((GroupResponseEvent)group).getName();
			String other_group_name = ((GroupResponseEvent)other_group).getName();
			return group_name.compareTo( other_group_name ) ;
		}
	}; 
}


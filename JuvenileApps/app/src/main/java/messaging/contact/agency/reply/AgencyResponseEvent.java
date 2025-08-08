/*
 * Created on Mar 17, 2004
 */
package messaging.contact.agency.reply;

import java.util.Collection;

import naming.PDConstants;
import naming.PDContactConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class AgencyResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyType; 
	private String agencyTypeId; 
	private String agencyId;
	private String agencyName;
	private String projectAnalystInd;
	
	private Collection departments;
	
	public String toString()
	{
		return agencyName;
	}

	public int compareTo(Object obj) throws ClassCastException
	{
		AgencyResponseEvent evt = (AgencyResponseEvent) obj;
		return agencyName.compareTo(evt.getAgencyName());
	}
	
	/**
	 * Returns the Topic that is associated to any Contact children of this Agency Event.  Used by the
	 * ui to get the DivisionEvents associated with this Agency Event.  PD uses it to set 
	 * the appropriate topic in the Command.
	 * @return
	 */
	public String getAgencyContactTopic()
	{
		return getAgencyTopic()
			+ "."
			+ this.getAgencyId()
			+ "."
			+ PDContactConstants.CONTACT_EVENT_TOPIC
			+ "."
			+ PDConstants.LIST_ITEM;
	}

	/**
	 * Returns the Topic that is associated to any Division children of this Agency Event.  Used by the
	 * ui to get the DivisionEvents associated with this Agency Event.  PD uses it to set 
	 * the appropriate topic in the Command.
	 * @return
	 */
	public String getAgencyDivisionTopic()
	{
		return PDContactConstants.AGENCY_EVENT_TOPIC
			+ "."
			+ this.getAgencyId()
			+ "."
			+ PDContactConstants.DIVISION_EVENT_TOPIC
			+ "."
			+ PDConstants.LIST_ITEM;
	}

/**
	 * Returns the Agency Topic value.  Used for both the PD to set the Topic and the UI 
	 * to get the topic of the event.
	 * @return
	 */
	public String getAgencyTopic()
	{
		return PDContactConstants.AGENCY_EVENT_TOPIC + "." + PDConstants.LIST_ITEM;
	}



	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getAgencyType()
	{
		return agencyType;
	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}

	/**
	 * @return
	 */
	public String getProjectAnalystInd()
	{
		return projectAnalystInd;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyType(String string)
	{
		agencyType = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypeId(String string)
	{
		agencyTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setProjectAnalystInd(String string)
	{
		projectAnalystInd = string;
	}

	/**
	 * @return
	 */
	public Collection getDepartments()
	{
		return departments;
	}

	/**
	 * @param collection
	 */
	public void setDepartments(Collection collection)
	{
		departments = collection;
	}

}
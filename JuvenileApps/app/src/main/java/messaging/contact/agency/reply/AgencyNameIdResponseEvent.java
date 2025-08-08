package messaging.contact.agency.reply;

import naming.PDConstants;
import naming.PDContactConstants;
import messaging.codetable.reply.ICode;
import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author dapte
 *
 * This event will hold the name and id of the agency. Can be used in all places
 * where we need to display the agency names in a dropdown on the UI. In this case
 * we need not pass back all the agency information.
 */

public class AgencyNameIdResponseEvent extends ResponseEvent implements Comparable, ICode
{

	private String agencyId;
	private String agencyName;

	public AgencyNameIdResponseEvent()
	{

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
	 * Returns the Agency Topic value.  Used for both the PD to set the Topic and the UI 
	 * to get the topic of the event.
	 * @return
	 */
	public String getAgencyTopic()
	{
		return PDContactConstants.AGENCY_EVENT_TOPIC + "." + PDConstants.LIST_ITEM;
	}

	/**
	 * Returns the Topic that is associated to any Division children of this Agency Event.  Used by the
	 * ui to get the DivisionEvents associated with this Agency Event.  PD uses it to set 
	 * the appropriate topic in the Command.
	 * @return
	 */
	public String getAgencyDivisionTopic()
	{
		return PDContactConstants.DIVISION_EVENT_TOPIC + "." + PDConstants.LIST_ITEM;
	}

	public String toString()
	{
		return agencyName;
	}

	public int compareTo(Object obj)
	{
		AgencyNameIdResponseEvent evt = (AgencyNameIdResponseEvent) obj;
		return agencyName.compareTo(evt.getAgencyName());
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getCode()
	 */
	public String getCode()
	{
		// TODO Auto-generated method stub
		return this.getAgencyId();
	}

	/* (non-Javadoc)
	 * @see messaging.codetable.reply.ICode#getDescription()
	 */
	public String getDescription()
	{
		// TODO Auto-generated method stub
		return this.getAgencyName();
	}
}

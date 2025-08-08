/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author bschwartz
 *
 */
public class EventTypeResponseEvent extends ResponseEvent
{
	private String eventTypeId;
	private String agencyId;
	private String name;
	private String description;

	/**
	 * @return
	 */
	public String getEventTypeId()
	{
		return eventTypeId;
	}

	/**
	 * 
	 */
	public void setEventTypeId( String anID )
	{
		eventTypeId = anID;
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
	public String getDescription()
	{
		return description;
	}

	/**
	 * 
	 */
	public void setDescription( String aDescription )
	{
		description = aDescription;
	}
}

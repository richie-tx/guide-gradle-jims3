//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetCalendarServiceEventConflictsEvent.java

package messaging.calendar;

import messaging.calendar.GetCalendarEventsEvent;

public class GetCalendarServiceEventConflictsEvent extends GetCalendarEventsEvent
{			

	public int serviceProviderId;
	public int instructorId;
	public int serviceLocationId;
	public boolean isInHouse; 

	/**
	 * Constructor is used to set the default retriever for this event.  
	 * It can be changed later if required. 
	 *
	 */
	public GetCalendarServiceEventConflictsEvent()
	{
		setRetriever("pd.supervision.calendar.OuterServiceEventRetriever");
	}
	/**
	 * @return
	 */
	public boolean isInHouse()
	{
		return isInHouse;
	}


	/**
	 * @param b
	 */
	public void setInHouse(boolean b)
	{
		isInHouse = b;
	}

	/**
	 * @return
	 */
	public int getInstructorId()
	{
		return instructorId;
	}

	/**
	 * @return
	 */
	public int getServiceLocationId()
	{
		return serviceLocationId;
	}

	/**
	 * @return
	 */
	public int getServiceProviderId()
	{
		return serviceProviderId;
	}

	/**
	 * @param i
	 */
	public void setInstructorId(int instructorId)
	{
		this.instructorId = instructorId;
	}

	/**
	 * @param i
	 */
	public void setServiceLocationId(int serviceLocationId)
	{
		this.serviceLocationId = serviceLocationId;
	}

	/**
	 * @param i
	 */
	public void setServiceProviderId(int serviceProviderId)
	{
		this.serviceProviderId = serviceProviderId;
	}

}
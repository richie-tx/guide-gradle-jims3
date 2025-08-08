/*
 * Created on Nov 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar.reply;

/**
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarEventAttendenceResponse extends CalendarEventResponse
{	
	private Integer maximumAttendees;
	private Integer currentAttendees;

	/**
	 * @return
	 */
	public Integer getCurrentAttendees()
	{
		return currentAttendees;
	}

	/**
	 * @return
	 */
	public Integer getMaximumAttendees()
	{
		return maximumAttendees;
	}

	/**
	 * @param integer
	 */
	public void setCurrentAttendees(Integer integer)
	{
		currentAttendees = integer;
	}

	/**
	 * @param integer
	 */
	public void setMaximumAttendees(Integer integer)
	{
		maximumAttendees = integer;
	}

}

//Source file: C:\\views\\dev\\app\\src\\messaging\\officer\\UpdateOfficerProfileEvent.java

package messaging.officer;

import mojo.km.messaging.RequestEvent;

public class WorkDayRequestEvent extends RequestEvent
{
	private String dayId;
	private String dayOff;
	private String endTimeId;
	private String officerProfileId;
	private String startTimeId;
	private String workScheduleId;
	/**
	 * @roseuid 42E67A0000A9
	 */
	public WorkDayRequestEvent()
	{

	}

	/**
	 * @return
	 */
	public String getDayId()
	{
		return dayId;
	}

	/**
	 * @return
	 */
	public String getDayOff()
	{
		return dayOff;
	}

	/**
	 * @return
	 */
	public String getEndTimeId()
	{
		return endTimeId;
	}

	/**
	 * @return
	 */
	public String getOfficerProfileId()
	{
		return officerProfileId;
	}

	/**
	 * @return
	 */
	public String getStartTimeId()
	{
		return startTimeId;
	}

	/**
	 * @return
	 */
	public String getWorkScheduleId()
	{
		return workScheduleId;
	}

	/**
	 * @param dayId
	 */
	public void setDayId(String dayId)
	{
		this.dayId = dayId;
	}

	/**
	 * @param dayOff
	 */
	public void setDayOff(String dayOff)
	{
		this.dayOff = dayOff;
	}

	/**
	 * @param endTimeId
	 */
	public void setEndTimeId(String endTimeId)
	{
		this.endTimeId = endTimeId;
	}

	/**
	 * @param officerProfileId
	 */
	public void setOfficerProfileId(String officerProfileId)
	{
		this.officerProfileId = officerProfileId;
	}

	/**
	 * @param startTimeId
	 */
	public void setStartTimeId(String startTimeId)
	{
		this.startTimeId = startTimeId;
	}

	/**
	 * @param workScheduleId
	 */
	public void setWorkScheduleId(String workScheduleId)
	{
		this.workScheduleId = workScheduleId;
	}
}
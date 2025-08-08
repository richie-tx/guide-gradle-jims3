/*
 * Created on August 04, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.officer.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class WorkDayResponseEvent extends ResponseEvent
{
	private String day;
	private String dayId;
	private String endTime;
	private String endTimeId;
	private String offDay;
	private String officerProfileId;
	private String startTime;
	private String startTimeId;
	private String workScheduleId;

	/**
	 * @return
	 */
	public String getDay()
	{
		return day;
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
	public String getEndTime()
	{
		return endTime;
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
	public String getOffDay()
	{
		return offDay;
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
	public String getStartTime()
	{
		return startTime;
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
	 * @param string
	 */
	public void setDay(String string)
	{
		day = string;
	}

	/**
	 * @param string
	 */
	public void setDayId(String string)
	{
		dayId = string;
	}

	/**
	 * @param string
	 */
	public void setEndTime(String string)
	{
		endTime = string;
	}

	/**
	 * @param string
	 */
	public void setEndTimeId(String string)
	{
		endTimeId = string;
	}

	/**
	 * @param string
	 */
	public void setOffDay(String string)
	{
		offDay = string;
	}

	/**
	 * @param string
	 */
	public void setOfficerProfileId(String string)
	{
		officerProfileId = string;
	}

	/**
	 * @param string
	 */
	public void setStartTime(String string)
	{
		startTime = string;
	}

	/**
	 * @param string
	 */
	public void setStartTimeId(String string)
	{
		startTimeId = string;
	}

	/**
	 * @param string
	 */
	public void setWorkScheduleId(String string)
	{
		workScheduleId = string;
	}

}

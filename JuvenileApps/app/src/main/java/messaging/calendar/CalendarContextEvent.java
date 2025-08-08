/*
 * Created on Dec 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import java.io.Serializable;

import messaging.calendar.ICalendarContext;

/**
 * This is a bean.
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CalendarContextEvent implements Serializable, ICalendarContext
{
	private Integer calendarEventContextId;
	private String probationOfficerId;
	private String caseFileId;
	private String juvenileId;
	private Integer calendarEventId;
	
	private String CLMProbationOfficerIds[];
	
	public CalendarContextEvent() {
		
	}
	
	/**
	 * @return calendarEventContextId
	 */
	public Integer getCalendarEventContextId()
	{
		return calendarEventContextId;
	}
	
	/**
	 * @param integer
	 */
	public void setCalendarEventContextId(Integer integer)
	{
		calendarEventContextId = integer;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getProbationOfficerId()
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getCaseFileId()
	 */
	public String getCaseFileId() {
		return caseFileId;
	}
	/**
	 * @param caseFileId
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
	/* (non-Javadoc)
	 * @see messaging.calendar.ICalendarContext#getJuvenileId()
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
	/**
	 * @return calendarEventId
	 */
	public Integer getCalendarEventId()
	{
		return calendarEventId;
	}
	/**
	 * @param integer
	 */
	public void setCalendarEventId(Integer integer)
	{
		calendarEventId = integer;
	}	
	
	/**
	 * @return probationOfficerIds
	 */
	public String[] getCLMProbationOfficerIds() {
		return CLMProbationOfficerIds;
	}

	/**
	 * @param probationOfficerIds
	 */
	public void setCLMProbationOfficerIds(String[] probationOfficerIds) {
		CLMProbationOfficerIds = probationOfficerIds;
	}
	
}

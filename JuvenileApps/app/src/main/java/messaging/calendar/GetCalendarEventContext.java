/*
 * Created on Nov 18, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCalendarEventContext extends RequestEvent
{
	private String probationOfficerId;
	private String caseFileId;
	private String juvenileId;
	private Integer calendarEventId;
	
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
	 * @return probationOfficerId
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
	/**
	 * @return caseFileId
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
	/**
	 * @return juvenileId
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
	

}

/*
 * Created on Dec 21, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

/**
 * This is a bean.
 *
 */
public class GetProductionSupportCalendarEventContextEvent extends RequestEvent
{
	private String casefileId;
	private Integer calendarEventId;
	private String juvenileId;
	
	public GetProductionSupportCalendarEventContextEvent() {
		
	}

	/**
	 * @return the casefileId
	 */
	public String getCasefileId() {
		return casefileId;
	}

	/**
	 * @param casefileId the casefileId to set
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
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
	public void setCalendarEventId(Integer calendarEventId)
	{
		this.calendarEventId = calendarEventId;
	}

	/**
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}	
	
}

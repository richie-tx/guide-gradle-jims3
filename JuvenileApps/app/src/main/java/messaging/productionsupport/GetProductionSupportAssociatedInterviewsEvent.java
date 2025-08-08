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
public class GetProductionSupportAssociatedInterviewsEvent extends RequestEvent
{
	private String calendarEventId;
	private String casefileId;
	
	public GetProductionSupportAssociatedInterviewsEvent() {
		
	}
	
	/**
	 * @return calendarEventId
	 */
	public String getCalendarEventId()
	{
		return calendarEventId;
	}
	
	/**
	 * @param integer
	 */
	public void setCalendarEventId(String calendarEventId)
	{
		this.calendarEventId = calendarEventId;
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
	
	
}

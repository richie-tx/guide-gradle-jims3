//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\UpdateNonCompliantEventEvent.java

package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetViolationReportCalEventsEvent extends RequestEvent 
{
	private String defendantId;
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}
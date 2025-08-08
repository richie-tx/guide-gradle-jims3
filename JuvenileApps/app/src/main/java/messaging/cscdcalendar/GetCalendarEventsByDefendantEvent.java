package messaging.cscdcalendar;

import mojo.km.messaging.RequestEvent;


/**
 * 
 * @author cc_bjangay
 *
 */
public class GetCalendarEventsByDefendantEvent  extends RequestEvent
{
	private String defendantId;

	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}

	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	
	
	
}

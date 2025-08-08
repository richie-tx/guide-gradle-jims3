/*
 * Created on Nov 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.calendar;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCalendarByDefendantIdsEvent extends RequestEvent
{
	private String defendantIds;

	public String getDefendantIds() {
		return defendantIds;
	}

	public void setDefendantIds(String defendantIds) {
		this.defendantIds = defendantIds;
	}
}

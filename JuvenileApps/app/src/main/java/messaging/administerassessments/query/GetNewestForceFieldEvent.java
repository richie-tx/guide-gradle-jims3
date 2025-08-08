/*
 * Created on Jul 13, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_bjangay
 *
 */
public class GetNewestForceFieldEvent extends RequestEvent
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

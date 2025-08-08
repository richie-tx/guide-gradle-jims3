/*
 * Created on Jun 21, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.caseplan;

import mojo.km.messaging.RequestEvent;



/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJPOReviewReportEvent extends RequestEvent {
	private String reviewRepId;

	/**
	 * @return Returns the reviewRepId.
	 */
	public String getReviewRepId() {
		return reviewRepId;
	}
	/**
	 * @param reviewRepId The reviewRepId to set.
	 */
	public void setReviewRepId(String reviewRepId) {
		this.reviewRepId = reviewRepId;
	}
}

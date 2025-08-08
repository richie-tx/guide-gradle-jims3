/*
 * Created on Jun 16, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetVRPreviousCourtCommentsEvent extends RequestEvent{
	private String commentType;
	private String requestType;
	private String ncResponseId;
	
	public String getNcResponseId() {
		return ncResponseId;
	}
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
}

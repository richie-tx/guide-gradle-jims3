/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NCCommentResponseEvent extends ResponseEvent{
	private String comment;
	private String commentType;
	private String reportType;
	private String ncResponseId;
	
	public String getNcResponseId() {
		return ncResponseId;
	}
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	public String getComment() {
		return comment;
	}
	public String getCommentType() {
		return commentType;
	}
	public String getReportType() {
		return reportType;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
}

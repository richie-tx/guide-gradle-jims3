package messaging.report;

import java.util.Vector;

import mojo.km.messaging.reporting.ReportRequestEvent;

public class SupervisionPlanPrintTemplateRequestEvent extends ReportRequestEvent{
	private String replyEvent;
	private String replyTopic;
	private Vector reportData = new Vector();
	
	public String getReplyEvent() {
		return replyEvent;
	}
	public void setReplyEvent(String replyEvent) {
		this.replyEvent = replyEvent;
	}
	public String getReplyTopic() {
		return replyTopic;
	}
	public void setReplyTopic(String replyTopic) {
		this.replyTopic = replyTopic;
	}
	public Vector getReportData() {
		return reportData;
	}
	public void setReportData(Vector reportData) {
		this.reportData = reportData;
	}
	
}

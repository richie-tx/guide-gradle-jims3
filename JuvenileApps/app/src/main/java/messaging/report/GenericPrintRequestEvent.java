//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\report\\SupervisionOrderPrintTemplateRequestEvent.java

package messaging.report;

import java.util.Vector;

import mojo.km.messaging.reporting.ReportRequestEvent;

public class GenericPrintRequestEvent extends ReportRequestEvent {
	private String replyEvent;
	private String replyTopic;
	private Vector reportData = new Vector();

	/**
	 * @roseuid 42FBA487006D
	 */
	public GenericPrintRequestEvent() {

	}

	/**
	 * @return
	 */
	public String getReplyEvent() {
		return replyEvent;
	}

	/**
	 * @return
	 */
	public String getReplyTopic() {
		return replyTopic;
	}

	/**
	 * @return
	 */
	public Vector getReportData() {
		return reportData;
	}

	/**
	 * @param string
	 */
	public void setReplyEvent(String replyEvent) {
		this.replyEvent = replyEvent;
	}

	/**
	 * @param string
	 */
	public void setReplyTopic(String replyTopic) {
		this.replyTopic = replyTopic;
	}

	/**
	 * @param vector
	 */
	public void setReportData(Vector reportData) {
		this.reportData = reportData;
	}

}

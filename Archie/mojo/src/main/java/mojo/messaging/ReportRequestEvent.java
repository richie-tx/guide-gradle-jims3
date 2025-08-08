package mojo.messaging;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.IEvent;
import java.util.Vector;

/**
 * Responsible for housing data that will be sent to control command ReportRequestCommand
 *@author Design detail addin
 *@version 1.0
 */
public class ReportRequestEvent extends RequestEvent {
    public ReportRequestEvent() { }

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

    public void addReportData(IEvent event) {
        this.reportData.add(event);
    }

    private String replyEvent;
    private String replyTopic;
    private Vector reportData = new Vector();
}

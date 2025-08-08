package mojo.messaging.reportrequestevents;

import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.IEvent;
import java.util.Vector;
import java.util.Collection;

/**
 * Responsible for housing data that will be sent to control command ReportRequestCommand
 *@author Design detail addin
 *@version 1.0
 * @modelguid {E870BAA4-F09B-48BC-ACA0-2098D54AF351}
 */
public class ReportRequestEvent extends RequestEvent {
	/** @modelguid {1D29C233-5FEB-45FA-9277-00C21362B888} */
    public ReportRequestEvent() { }

	/** @modelguid {ACBBAEA1-3D3F-4331-ABE3-B9368FABBD2C} */
    public String getReplyEvent() {
        return replyEvent;
    }

	/** @modelguid {37D0CA96-B4FE-47EF-BD32-E8F8A8D133F4} */
    public void setReplyEvent(String replyEvent) {
        this.replyEvent = replyEvent;
    }

	/** @modelguid {38648E91-297E-48CD-82F7-694C69B7EF76} */
    public String getReplyTopic() {
        return replyTopic;
    }

	/** @modelguid {F82AC739-605B-4C95-9D51-AACD0519589E} */
    public void setReplyTopic(String replyTopic) {
        this.replyTopic = replyTopic;
    }

	/** @modelguid {B55DD0EA-2F1C-41C2-ADAB-B7A59B0497DF} */
    public Vector getReportData() {
        return reportData;
    }

	/** @modelguid {8849D98C-528A-4189-A204-BC979459D032} */
    public void addReportData(IEvent event) {
        this.reportData.add(event);
    }

	/** @modelguid {5310B1EA-1E9C-42DC-9E1F-03B574FD1162} */
    public void setReportData(Vector reportData){
            this.reportData = reportData;
        }

	/** @modelguid {F785BAE5-4A14-4963-8DF4-BBBA0532F884} */
    private String replyEvent;
	/** @modelguid {FEEA0948-C317-4F58-9376-9FA7680D0BF1} */
    private String replyTopic;
	/** @modelguid {5AB30332-0FDB-4AE2-818D-885C7B7F194C} */
    private Vector reportData = new Vector();
}

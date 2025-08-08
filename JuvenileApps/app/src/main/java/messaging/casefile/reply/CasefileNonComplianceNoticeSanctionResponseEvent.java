package messaging.casefile.reply;

import mojo.km.messaging.ResponseEvent;

public class CasefileNonComplianceNoticeSanctionResponseEvent extends ResponseEvent{
	private String comments;
	private String otherText;
	private String casefileNonComplianceNoticeId;
	private String juvenileVOPSanctionCodesId;
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOtherText() {
		return otherText;
	}
	public void setOtherText(String otherText) {
		this.otherText = otherText;
	}
	public String getCasefileNonComplianceNoticeId() {
		return casefileNonComplianceNoticeId;
	}
	public void setCasefileNonComplianceNoticeId(
			String casefileNonComplianceNoticeId) {
		this.casefileNonComplianceNoticeId = casefileNonComplianceNoticeId;
	}
	public String getJuvenileVOPSanctionCodesId() {
		return juvenileVOPSanctionCodesId;
	}
	public void setJuvenileVOPSanctionCodesId(String juvenileVOPSanctionCodesId) {
		this.juvenileVOPSanctionCodesId = juvenileVOPSanctionCodesId;
	}
}

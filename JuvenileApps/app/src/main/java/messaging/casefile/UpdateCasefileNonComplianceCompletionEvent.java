package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateCasefileNonComplianceCompletionEvent extends RequestEvent
{
	private String casefileNonComplianceId;
	private Date completionDate;
	private String completionComments;
	private String actionTakenComments;
	private String actionTakenOtherText;
	private String completionStatusId;
	private String actionTakenId;
	
	public String getCasefileNonComplianceId() {
		return casefileNonComplianceId;
	}
	public void setCasefileNonComplianceId(String casefileNonComplianceId) {
		this.casefileNonComplianceId = casefileNonComplianceId;
	}
	public Date getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}
	public String getCompletionComments() {
		return completionComments;
	}
	public void setCompletionComments(String completionComments) {
		this.completionComments = completionComments;
	}
	public String getActionTakenComments() {
		return actionTakenComments;
	}
	public void setActionTakenComments(String actionTakenComments) {
		this.actionTakenComments = actionTakenComments;
	}
	public String getCompletionStatusId() {
		return completionStatusId;
	}
	public void setCompletionStatusId(String completionStatusId) {
		this.completionStatusId = completionStatusId;
	}
	public String getActionTakenId() {
		return actionTakenId;
	}
	public void setActionTakenId(String actionTakenId) {
		this.actionTakenId = actionTakenId;
	}
	public String getActionTakenOtherText() {
		return actionTakenOtherText;
	}
	public void setActionTakenOtherText(String actionTakenOtherText) {
		this.actionTakenOtherText = actionTakenOtherText;
	}
}

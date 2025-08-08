package messaging.casefile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateCasefileNonComplianceSignatureEvent extends RequestEvent
{	
	private String casefileNonComplianceId;
	private Date signedDate;
	private String signatureStatusId;
	private boolean parentInformed;
	
	public String getCasefileNonComplianceId() {
		return casefileNonComplianceId;
	}
	public void setCasefileNonComplianceId(String casefileNonComplianceId) {
		this.casefileNonComplianceId = casefileNonComplianceId;
	}
	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	public String getSignatureStatusId() {
		return signatureStatusId;
	}
	public void setSignatureStatusId(String signatureStatusId) {
		this.signatureStatusId = signatureStatusId;
	}
	public boolean isParentInformed() {
		return parentInformed;
	}
	public void setParentInformed(boolean parentInformed) {
		this.parentInformed = parentInformed;
	}
}

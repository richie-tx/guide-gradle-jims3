package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJPOAssignmentHistoryEvent extends RequestEvent {
	private String casefileId;
	private String officerProfileId;
	
	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public String getOfficerProfileId() {
		return officerProfileId;
	}
	public void setOfficerProfileId(String officerProfileId) {
		this.officerProfileId = officerProfileId;
	}
}

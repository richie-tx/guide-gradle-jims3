package messaging.casefile;

import mojo.km.messaging.RequestEvent;

public class GetActivitiesByCasefileAndActivityIdEvent extends RequestEvent {
	private String casefileId;
	private String activityCodeId;

	public String getCasefileId() {
		return casefileId;
	}
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}
	public String getActivityCodeId() {
		return activityCodeId;
	}
	public void setActivityCodeId(String activityCodeId) {
		this.activityCodeId = activityCodeId;
	}

}

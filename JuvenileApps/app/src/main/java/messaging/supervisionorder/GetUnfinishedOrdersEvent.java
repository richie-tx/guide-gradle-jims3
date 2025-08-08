package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetUnfinishedOrdersEvent extends RequestEvent {
	private String agencyId;
	private String courtId;
	private String criminalCaseId;

	public String getAgencyId() {
		return agencyId;
	}
	public String getCourtId() {
		return courtId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

}

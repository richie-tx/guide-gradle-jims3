package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetMigratedSupervisionOrderEvent extends RequestEvent {
	private String agencyId;
	private String criminalCaseId;
	private int orderChainNum;
	private String versionTypeId;
	
	public String getAgencyId() {
		return agencyId;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	public int getOrderChainNum() {
		return orderChainNum;
	}
	public String getVersionTypeId() {
		return versionTypeId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}
	public void setVersionTypeId(String versionTypeId) {
		this.versionTypeId = versionTypeId;
	}
}

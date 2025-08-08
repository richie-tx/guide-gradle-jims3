package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetAllReferralsByCasefileIdEvent extends RequestEvent {
	private String juvenileNum;
	private String caseFileId;
	
	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return the caseFileId
	 */
	public String getCaseFileId() {
		return caseFileId;
	}
	/**
	 * @param caseFileId the caseFileId to set
	 */
	public void setCaseFileId(String caseFileId) {
		this.caseFileId = caseFileId;
	}
}

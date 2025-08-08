package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetFacilityCurrentFacilityOfficerCdEvent extends RequestEvent{

	
	private String juvenileNum;
	private String referralNum;
	private String probationOfficerId;
	
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
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
	/**
	 * @return the probationOfficerId
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	/**
	 * @param probationOfficerId the probationOfficerId to set
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	
}

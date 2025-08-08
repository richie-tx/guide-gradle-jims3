package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetCurrentFacilityCalCourtEvent extends RequestEvent{

	
	private String juvenileNum;
	private String referralNum;
	private String petitionNum;
	
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
	 * @return the petitionNum
	 */
	public String getPetitionNum() {
		return petitionNum;
	}
	/**
	 * @param petitionNum the petitionNum to set
	 */
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	
}

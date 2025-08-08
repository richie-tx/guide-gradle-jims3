/*
 * Created on March 29th 2018
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetCasefileWithReferralEvent extends RequestEvent {
	
	private String juvenileNum;
	private String referralNum;
	private String petitionNum;
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the referralNum.
	 */
	public String getReferralNum() {
		return referralNum;
	}
	/**
	 * @param referralNum The referralNum to set.
	 */
	public void setReferralNum(String referralNum) {
		this.referralNum = referralNum;
	}
	
	public String getPetitionNum() {
		return petitionNum;
	}
	
	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}
	
}

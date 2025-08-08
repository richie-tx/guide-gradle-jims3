package messaging.administerprogramreferrals.reply;

import mojo.km.messaging.ResponseEvent;

public class ReferralFormResponseEvent extends ResponseEvent
{
	private String referralFormId;
	private String referralFormTitle;
	/**
	 * @return the referralFormId
	 */
	public String getReferralFormId() {
		return referralFormId;
	}
	/**
	 * @param referralFormId the referralFormId to set
	 */
	public void setReferralFormId(String referralFormId) {
		this.referralFormId = referralFormId;
	}
	/**
	 * @return the referralFormTitle
	 */
	public String getReferralFormTitle() {
		return referralFormTitle;
	}
	/**
	 * @param referralFormTitle the referralFormTitle to set
	 */
	public void setReferralFormTitle(String referralFormTitle) {
		this.referralFormTitle = referralFormTitle;
	}
	
}

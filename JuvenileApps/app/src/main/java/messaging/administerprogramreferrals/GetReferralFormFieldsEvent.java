package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetReferralFormFieldsEvent extends RequestEvent
{
	private String programReferralId;
	private String referralFormId;

	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

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
}

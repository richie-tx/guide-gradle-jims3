package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetReferralFormDataEvent extends RequestEvent
{
	private String progRefId;
	private String referalFormId;
	
	
	/**
	 * @return the progRefId
	 */
	public String getProgRefId() {
		return progRefId;
	}
	/**
	 * @param progRefId the progRefId to set
	 */
	public void setProgRefId(String progRefId) {
		this.progRefId = progRefId;
	}
	/**
	 * @return the referalFormId
	 */
	public String getReferalFormId() {
		return referalFormId;
	}
	/**
	 * @param referalFormId the referalFormId to set
	 */
	public void setReferalFormId(String referalFormId) {
		this.referalFormId = referalFormId;
	}
}

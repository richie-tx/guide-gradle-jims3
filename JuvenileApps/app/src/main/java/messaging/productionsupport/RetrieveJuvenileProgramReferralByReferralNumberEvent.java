package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class RetrieveJuvenileProgramReferralByReferralNumberEvent extends RequestEvent {
	public String referralNum;

	/**
	 * @roseuid 463BA4D003A2
	 */
	public RetrieveJuvenileProgramReferralByReferralNumberEvent() {

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


}

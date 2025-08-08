package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;

public class GetReferralFormsEvent extends RequestEvent
{
	private String referralTypeCd;

	/**
	 * @return the referralTypeCd
	 */
	public String getReferralTypeCd() {
		return referralTypeCd;
	}

	/**
	 * @param referralTypeCd the referralTypeCd to set
	 */
	public void setReferralTypeCd(String referralTypeCd) {
		this.referralTypeCd = referralTypeCd;
	}

}

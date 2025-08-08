//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilePetitionEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDetentionFacilitiesEvent extends RequestEvent
{
	public String referralNum;
	public String juvenileNum;

	/**
	 * @roseuid 42A9A16D0190
	 */
	public GetJuvenileDetentionFacilitiesEvent()
	{
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
}

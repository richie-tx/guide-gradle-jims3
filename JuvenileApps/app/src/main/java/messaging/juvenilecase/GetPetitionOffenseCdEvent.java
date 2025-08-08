
package messaging.juvenilecase;
import mojo.km.messaging.RequestEvent;

public class GetPetitionOffenseCdEvent extends RequestEvent{

	private String juvenileNum;
	private String referralNum;
	private String facilityId;
	/**
	 * @return the facilityId
	 */
	public String getFacilityId() {
		return facilityId;
	}
	/**
	 * @param facilityId the facilityId to set
	 */
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
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
}

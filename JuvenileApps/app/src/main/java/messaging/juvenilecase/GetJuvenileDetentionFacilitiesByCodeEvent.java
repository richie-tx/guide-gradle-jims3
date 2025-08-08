//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilecase\\GetJuvenileCasefilePetitionEvent.java

package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileDetentionFacilitiesByCodeEvent extends RequestEvent
{
	private String facilityCode;
	private String juvenileNum;
	private String facilitySeqNum;
	private String referralNumber;
	private String searchType;
	private String facilityStatus;

	/**
	 * @roseuid 42A9A16D0190
	 */
	public GetJuvenileDetentionFacilitiesByCodeEvent()
	{
	}

	/**
	 * @return Returns the facilityCode.
	 */
	public String getFacilityCode() {
		return facilityCode;
	}
	/**
	 * @param facilityCode The facilityCode to set.
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
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
	 * @return Returns the facilitySeqNum.
	 */
	public String getFacilitySeqNum() {
		return facilitySeqNum;
	}
	/**
	 * @param facilitySeqNum The facilitySeqNum to set.
	 */
	public void setFacilitySeqNum(String facilitySeqNum) {
		this.facilitySeqNum = facilitySeqNum;
	}
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return facilitySeqNum;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return Returns the searchType.
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType The searchType to set.
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	/**
	 * @return Returns the facilityStatus.
	 */
	public String getFacilityStatus() {
		return facilityStatus;
	}
	/**
	 * @param facilityStatus The facilityStatus to set.
	 */
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
}

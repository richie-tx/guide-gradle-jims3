package messaging.juvenilecase;

import mojo.km.messaging.RequestEvent;

public class GetFacilityCurrentObservationEvent extends RequestEvent {
	
	public GetFacilityCurrentObservationEvent()
	{
	}
	
	public String facilityCode;
	private String juvenileNum;
	private String facilitySeqNum;
	private String referralNum;
	
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
	 * @return the facilitySeqNum
	 */
	public String getFacilitySeqNum() {
		return facilitySeqNum;
	}
	/**
	 * @param facilitySeqNum the facilitySeqNum to set
	 */
	public void setFacilitySeqNum(String facilitySeqNum) {
		this.facilitySeqNum = facilitySeqNum;
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

}

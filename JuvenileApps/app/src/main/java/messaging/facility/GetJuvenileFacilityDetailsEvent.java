package messaging.facility;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileFacilityDetailsEvent extends RequestEvent{
	
	
	public GetJuvenileFacilityDetailsEvent(){
		
	}
	
	
	private String juvenileNum;
	private String facilitySequenceNum;
	private String ReferralNum;
	private String DetentionRecordId;

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}

	public void setFacilitySequenceNum(String facilitySequenceNum) {
		this.facilitySequenceNum = facilitySequenceNum;
	}

	/**
	 * @return the referralNum
	 */
	public String getReferralNum() {
		return ReferralNum;
	}

	/**
	 * @param referralNum the referralNum to set
	 */
	public void setReferralNum(String referralNum) {
		ReferralNum = referralNum;
	}

	/**
	 * @return the detentionRecordId
	 */
	public String getDetentionRecordId() {
		return DetentionRecordId;
	}

	/**
	 * @param detentionRecordId the detentionRecordId to set
	 */
	public void setDetentionRecordId(String detentionRecordId) {
		DetentionRecordId = detentionRecordId;
	}
}

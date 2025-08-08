package messaging.juvenile;


import mojo.km.messaging.RequestEvent;

public class GetFacilityAdmitReasonPopEvent extends RequestEvent {
	
	
	private String facilityId;
	private String securedFacility;
	private String admitReasonId;
	private String juvenileNum;
	private String facilitySeqNum;
    private String referralNum;
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
	 * @return Returns the facilityId.
	 */
	public String getFacilityId() {
		return facilityId;
	}
	/**
	 * @param facilityId The facilityId to set.
	 */
	public void setFacilityId(String facilityId) {
		this.facilityId = facilityId;
	}
	
	/**
	 * @return Returns the securedFacility.
	 */
	public String getSecuredFacility() {
		return securedFacility;
	}
	/**
	 * @param securedFacility The securedFacility to set.
	 */
	public void setSecuredFacility(String securedFacility) {
		this.securedFacility = securedFacility;
	}
	
	/**
	 * @return Returns the admitReasonId.
	 */
	public String getAdmitReasonId() {
		return admitReasonId;
	}
	/**
	 * @param admitReasonId The admitReasonId to set.
	 */
	public void setAdmitReasonId(String admitReasonId) {
		this.admitReasonId = admitReasonId;
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

package messaging.facility;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileFacilityReturnEscapeEvent extends RequestEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String juvenileNum;
	private String headerFacility;

	
	private Date returnDate;
	private String returnStatus;
	private String returnTime;
	private String returnReason;
	
	private String detainedFacility;
	private String facilitySequenceNum;
	private String facilityAdmissionComments;
	
	//special attention fields
	private String specialAttentionCd;
	private String specialAttentionDesc;
	private String saReason;
	private String saReasonStr;
	private String saReasonOtherComments;
	
	private boolean isObservationStatusChanged;
	
	
	public Date getReturnDate() {
		return returnDate;
	}
	
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	public String getReturnStatus() {
		return returnStatus;
	}
	
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	
	public String getReturnTime() {
		return returnTime;
	}
	
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	public String getReturnReason() {
		return returnReason;
	}
	
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	public String getHeaderFacility() {
		return headerFacility;
	}
	
	public void setHeaderFacility(String headerFacility) {
		this.headerFacility = headerFacility;
	}

	public String getDetainedFacility() {
		return detainedFacility;
	}

	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}

	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}

	public void setFacilitySequenceNum(String facilitySequenceNum) {
		this.facilitySequenceNum = facilitySequenceNum;
	}

	/**
	 * @return the specialAttentionCd
	 */
	public String getSpecialAttentionCd() {
		return specialAttentionCd;
	}

	/**
	 * @param specialAttentionCd the specialAttentionCd to set
	 */
	public void setSpecialAttentionCd(String specialAttentionCd) {
		this.specialAttentionCd = specialAttentionCd;
	}

	/**
	 * @return the specialAttentionDesc
	 */
	public String getSpecialAttentionDesc() {
		return specialAttentionDesc;
	}

	/**
	 * @param specialAttentionDesc the specialAttentionDesc to set
	 */
	public void setSpecialAttentionDesc(String specialAttentionDesc) {
		this.specialAttentionDesc = specialAttentionDesc;
	}

	/**
	 * @return the saReason
	 */
	public String getSaReason() {
		return saReason;
	}

	/**
	 * @param saReason the saReason to set
	 */
	public void setSaReason(String saReason) {
		this.saReason = saReason;
	}

	/**
	 * @return the saReasonStr
	 */
	public String getSaReasonStr() {
		return saReasonStr;
	}

	/**
	 * @param saReasonStr the saReasonStr to set
	 */
	public void setSaReasonStr(String saReasonStr) {
		this.saReasonStr = saReasonStr;
	}

	/**
	 * @return the saReasonOtherComments
	 */
	public String getSaReasonOtherComments() {
		return saReasonOtherComments;
	}

	/**
	 * @param saReasonOtherComments the saReasonOtherComments to set
	 */
	public void setSaReasonOtherComments(String saReasonOtherComments) {
		this.saReasonOtherComments = saReasonOtherComments;
	}

	/**
	 * @return the facilityAdmissionComments
	 */
	public String getFacilityAdmissionComments() {
		return facilityAdmissionComments;
	}

	/**
	 * @param facilityAdmissionComments the facilityAdmissionComments to set
	 */
	public void setFacilityAdmissionComments(String facilityAdmissionComments) {
		this.facilityAdmissionComments = facilityAdmissionComments;
	}

	/**
	 * @return the isObservationStatusChanged
	 */
	public boolean isObservationStatusChanged() {
		return isObservationStatusChanged;
	}

	/**
	 * @param isObservationStatusChanged the isObservationStatusChanged to set
	 */
	public void setObservationStatusChanged(boolean isObservationStatusChanged) {
		this.isObservationStatusChanged = isObservationStatusChanged;
	}
}

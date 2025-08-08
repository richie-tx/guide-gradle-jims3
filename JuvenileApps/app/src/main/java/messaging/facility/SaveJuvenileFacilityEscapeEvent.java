package messaging.facility;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileFacilityEscapeEvent extends RequestEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String juvenileNum;
	private String outcome;
	private String escapeCode;
	private String escapeDate;
	private String escapeFromDesc;
	private String currentSupervisionNum;
	private String nextHearingDate;
	private String comments;
	private String relocationTime;
	private Date relocationDate;
	private Date lcDate;
	private Date lcTime;
	private String headerFacility;
	private String detentionFacilityDesc;
	private String facilitySequenceNum;
	private String facilityAdmissionComments;
	private String releaseReason;
	
	//special attention fields
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;
	
	private boolean isObservationStatusChanged;
	
	
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getEscapeCode() {
		return escapeCode;
	}
	public void setEscapeCode(String escapeCode) {
		this.escapeCode = escapeCode;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getRelocationTime() {
		return relocationTime;
	}
	public void setRelocationTime(String relocationTime) {
		this.relocationTime = relocationTime;
	}
	public Date getRelocationDate() {
		return relocationDate;
	}
	public void setRelocationDate(Date relocationDate) {
		this.relocationDate = relocationDate;
	}
	public Date getLcDate() {
		return lcDate;
	}
	public void setLcDate(Date lcDate) {
		this.lcDate = lcDate;
	}
	public Date getLcTime() {
		return lcTime;
	}
	public void setLcTime(Date lcTime) {
		this.lcTime = lcTime;
	}
	public String getHeaderFacility() {
		return headerFacility;
	}
	public void setHeaderFacility(String headerFacility) {
		this.headerFacility = headerFacility;
	}
	public String getJuvenileNum() {
		return juvenileNum;
	}
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}
	public void setFacilitySequenceNum(String facilitySequenceNum) {
		this.facilitySequenceNum = facilitySequenceNum;
	}
	public String getNextHearingDate() {
		return nextHearingDate;
	}
	public void setNextHearingDate(String nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}
	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}
	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}
	public String getEscapeFromDesc() {
		return escapeFromDesc;
	}
	public void setEscapeFromDesc(String escapeFromDesc) {
		this.escapeFromDesc = escapeFromDesc;
	}
	public String getEscapeDate() {
		return escapeDate;
	}
	public void setEscapeDate(String escapeDate) {
		this.escapeDate = escapeDate;
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
	 * @return the releaseReason
	 */
	public String getReleaseReason() {
		return releaseReason;
	}
	/**
	 * @param releaseReason the releaseReason to set
	 */
	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}
	/**
	 * @return the detentionFacilityDesc
	 */
	public String getDetentionFacilityDesc() {
		return detentionFacilityDesc;
	}
	/**
	 * @param detentionFacilityDesc the detentionFacilityDesc to set
	 */
	public void setDetentionFacilityDesc(String detentionFacilityDesc) {
		this.detentionFacilityDesc = detentionFacilityDesc;
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
	


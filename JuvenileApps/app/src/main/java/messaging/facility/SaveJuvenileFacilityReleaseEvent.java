package messaging.facility;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileFacilityReleaseEvent extends RequestEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String releaseAuthorizedBy;
	private Date releaseDate;
	private String releaseTime;
	private String releasedBy;
	private String releaseTo;
	private String outcome;
	private String releaseReason;
	private String facilitySequenceNum;
	private String juvenileNumber;
	private String currentSupervisionNum;
	private String detainedFacility;
	private String juvenileName;

	private Date detainedDate;
	private String bookingReferralNumber;
	private String facilityStatus;
	private String facilityAdmissionComments;
	// special attention fields
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;
	private String custodylastName;
	private String custodyfirstName;

	

	private boolean isObservationStatusChanged;

	public String getReleaseAuthorizedBy() {
		return releaseAuthorizedBy;
	}

	public void setReleaseAuthorizedBy(String releaseAuthorizedBy) {
		this.releaseAuthorizedBy = releaseAuthorizedBy;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleasedBy() {
		return releasedBy;
	}

	public void setReleasedBy(String releasedBy) {
		this.releasedBy = releasedBy;
	}

	public String getReleaseTo() {
		return releaseTo;
	}

	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	public void setJuvenileNum(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}

	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}

	public void setFacilitySequenceNum(String facilitySequenceNum) {
		this.facilitySequenceNum = facilitySequenceNum;
	}

	public Date getDetainedDate() {
		return detainedDate;
	}

	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public String getBookingReferralNumber() {
		return bookingReferralNumber;
	}

	public void setBookingReferralNumber(String bookingReferralNumber) {
		this.bookingReferralNumber = bookingReferralNumber;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}

	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}

	public String getDetainedFacility() {
		return detainedFacility;
	}

	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}

	public String getJuvenileName() {
		return juvenileName;
	}

	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	/**
	 * @return the specialAttentionCd
	 */
	public String getSpecialAttentionCd() {
		return specialAttentionCd;
	}

	/**
	 * @param specialAttentionCd
	 *            the specialAttentionCd to set
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
	 * @param saReason
	 *            the saReason to set
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
	 * @param saReasonOtherComments
	 *            the saReasonOtherComments to set
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
	public String getCustodylastName()
	{
	    return custodylastName;
	}

	public void setCustodylastName(String custodylastName)
	{
	    this.custodylastName = custodylastName;
	}
	
	public String getCustodyfirstName()
	{
	    return custodyfirstName;
	}
	public void setCustodyfirstName(String custodyfirstName)
	{
	    this.custodyfirstName = custodyfirstName;
	}
}

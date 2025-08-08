/**
 * 
 */
package messaging.facility;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author sthyagarajan
 * 
 */
public class SaveJuvenileFacilityInTransferEvent extends RequestEvent {

	private static final long serialVersionUID = 1L;
	private String releaseReason;
	private String releaseAuthorizedBy;
	private Date releaseDate;
	private String releaseTime;
	private String releasedBy;
	private String releaseTo;
	private String outcome;
	private String transferToFacility;
	private String transferToFacilityDesc;
	private String facilitySequenceNum;
	private String juvenileNumber;
	private String currentSupervisionNum;
	private String detainedFacility;
	private String juvenileName;
	private String facilityAdmissionComments;

	private Date detainedDate;
	private String bookingReferralNumber;
	private String facilityStatus;

	// special attention fields
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;
	private boolean isObservationStatusChanged;

	/**
	 * @return the releaseAuthorizedBy
	 */
	public String getReleaseAuthorizedBy() {
		return releaseAuthorizedBy;
	}

	/**
	 * @param releaseAuthorizedBy
	 *            the releaseAuthorizedBy to set
	 */
	public void setReleaseAuthorizedBy(String releaseAuthorizedBy) {
		this.releaseAuthorizedBy = releaseAuthorizedBy;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate
	 *            the releaseDate to set
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * @return the releaseTime
	 */
	public String getReleaseTime() {
		return releaseTime;
	}

	/**
	 * @param releaseTime
	 *            the releaseTime to set
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @return the releasedBy
	 */
	public String getReleasedBy() {
		return releasedBy;
	}

	/**
	 * @param releasedBy
	 *            the releasedBy to set
	 */
	public void setReleasedBy(String releasedBy) {
		this.releasedBy = releasedBy;
	}

	/**
	 * @return the releaseTo
	 */
	public String getReleaseTo() {
		return releaseTo;
	}

	/**
	 * @param releaseTo
	 *            the releaseTo to set
	 */
	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome
	 *            the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the releaseReason
	 */
	public String getReleaseReason() {
		return releaseReason;
	}

	/**
	 * @param releaseReason
	 *            the releaseReason to set
	 */
	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	/**
	 * @return the facilitySequenceNum
	 */
	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}

	/**
	 * @param facilitySequenceNum
	 *            the facilitySequenceNum to set
	 */
	public void setFacilitySequenceNum(String facilitySequenceNum) {
		this.facilitySequenceNum = facilitySequenceNum;
	}

	/**
	 * @return the juvenileNumber
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}

	/**
	 * @param juvenileNumber
	 *            the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}

	/**
	 * @return the currentSupervisionNum
	 */
	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}

	/**
	 * @param currentSupervisionNum
	 *            the currentSupervisionNum to set
	 */
	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}

	/**
	 * @return the detainedFacility
	 */
	public String getDetainedFacility() {
		return detainedFacility;
	}

	/**
	 * @param detainedFacility
	 *            the detainedFacility to set
	 */
	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}

	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}

	/**
	 * @param juvenileName
	 *            the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}

	/**
	 * @return the detainedDate
	 */
	public Date getDetainedDate() {
		return detainedDate;
	}

	/**
	 * @param detainedDate
	 *            the detainedDate to set
	 */
	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}

	/**
	 * @return the bookingReferralNumber
	 */
	public String getBookingReferralNumber() {
		return bookingReferralNumber;
	}

	/**
	 * @param bookingReferralNumber
	 *            the bookingReferralNumber to set
	 */
	public void setBookingReferralNumber(String bookingReferralNumber) {
		this.bookingReferralNumber = bookingReferralNumber;
	}

	/**
	 * @return the facilityStatus
	 */
	public String getFacilityStatus() {
		return facilityStatus;
	}

	/**
	 * @param facilityStatus
	 *            the facilityStatus to set
	 */
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the transferToFacility
	 */
	public String getTransferToFacility() {
		return transferToFacility;
	}

	/**
	 * @param transferToFacility the transferToFacility to set
	 */
	public void setTransferToFacility(String transferToFacility) {
		this.transferToFacility = transferToFacility;
	}

	/**
	 * @return the facilityComments
	 */
	public String getFacilityAdmissionComments() {
		return facilityAdmissionComments;
	}

	/**
	 * @param facilityComments the facilityComments to set
	 */
	public void setFacilityAdmissionComments(String facilityAdmissionComments) {
		this.facilityAdmissionComments = facilityAdmissionComments;
	}

	public String getTransferToFacilityDesc() {
		return transferToFacilityDesc;
	}

	public void setTransferToFacilityDesc(String transferToFacilityDesc) {
		this.transferToFacilityDesc = transferToFacilityDesc;
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

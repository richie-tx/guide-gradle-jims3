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
public class SaveJuvenileFacilityReturnTempReleaseEvent extends RequestEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SaveJuvenileFacilityReturnTempReleaseEvent() {
		// TODO Auto-generated constructor stub
	}

	private String juvenileNum;
	private String headerFacility;

	
	private Date returnDate;
	private String returnStatus;
	private String returnTime;
	private String returnReason;
	
	private String detainedFacility;
	private String facilitySequenceNum;
	private String bookingRefNum;
	private String facilityAdmissionComments;
	
	//special attention fields
	private String specialAttentionCd;
	private String specialAttentionDesc;
	private String saReason;
	private String saReasonStr;
	private String saReasonOtherComments;
	
	private boolean isObservationStatusChanged;
	
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
	 * @return the headerFacility
	 */
	public String getHeaderFacility() {
		return headerFacility;
	}
	/**
	 * @param headerFacility the headerFacility to set
	 */
	public void setHeaderFacility(String headerFacility) {
		this.headerFacility = headerFacility;
	}
	/**
	 * @return the returnDate
	 */
	public Date getReturnDate() {
		return returnDate;
	}
	/**
	 * @param returnDate the returnDate to set
	 */
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	/**
	 * @return the returnStatus
	 */
	public String getReturnStatus() {
		return returnStatus;
	}
	/**
	 * @param returnStatus the returnStatus to set
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}
	/**
	 * @return the returnTime
	 */
	public String getReturnTime() {
		return returnTime;
	}
	/**
	 * @param returnTime the returnTime to set
	 */
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	/**
	 * @return the returnReason
	 */
	public String getReturnReason() {
		return returnReason;
	}
	/**
	 * @param returnReason the returnReason to set
	 */
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	/**
	 * @return the detainedFacility
	 */
	public String getDetainedFacility() {
		return detainedFacility;
	}
	/**
	 * @param detainedFacility the detainedFacility to set
	 */
	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}
	/**
	 * @return the facilitySequenceNum
	 */
	public String getFacilitySequenceNum() {
		return facilitySequenceNum;
	}
	/**
	 * @param facilitySequenceNum the facilitySequenceNum to set
	 */
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
	 * @return the bookingRefNum
	 */
	public String getBookingRefNum() {
		return bookingRefNum;
	}
	/**
	 * @param bookingRefNum the bookingRefNum to set
	 */
	public void setBookingRefNum(String bookingRefNum) {
		this.bookingRefNum = bookingRefNum;
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

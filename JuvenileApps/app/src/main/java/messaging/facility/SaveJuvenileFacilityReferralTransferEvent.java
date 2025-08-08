package messaging.facility;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileFacilityReferralTransferEvent extends RequestEvent{
	
	/**
	 *Serialization 
	 */
	private static final long serialVersionUID = -3507999596240013351L;
	
	
	private String juvenileNum;
	private String petition;
	
	private String transferToReferral;
	private String transferFromReferral;
	
	private String bookingOffense;
	private String bookingReferral;
	private String bookingSupervision;
	private String bookingOffenseLevel;
	
	private String currentReferral;
	private String currentOffense;
	private String currentOffenseCode;
	private String currentSupervision;
	private String currentOffenseLevel;
	
	private String facilitySequenceNum;
	

	/**
	 * @return the petition
	 */
	public String getPetition() {
		return petition;
	}
	/**
	 * @param petition the petition to set
	 */
	public void setPetition(String petition) {
		this.petition = petition;
	}
	/**
	 * @return the bookingOffense
	 */
	public String getBookingOffense() {
		return bookingOffense;
	}
	/**
	 * @param bookingOffense the bookingOffense to set
	 */
	public void setBookingOffense(String bookingOffense) {
		this.bookingOffense = bookingOffense;
	}
	/**
	 * @return the currentOffense
	 */
	public String getCurrentOffense() {
		return currentOffense;
	}
	/**
	 * @param currentOffense the currentOffense to set
	 */
	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}
	public String getCurrentOffenseCode() {
		return currentOffenseCode;
	}
	/**
	 * @param currentOffenseCode the currentOffenseCode to set
	 */
	public void setCurrentOffenseCode(String currentOffenseCode) {
		this.currentOffenseCode = currentOffenseCode;
	}
	/**
	 * @return the offenseLevel
	 */
	public String getBookingOffenseLevel() {
		return bookingOffenseLevel;
	}
	/**
	 * @param offenseLevel the offenseLevel to set
	 */
	public void setBookingOffenseLevel(String bookingOffenseLevel) {
		this.bookingOffenseLevel = bookingOffenseLevel;
	}
	/**
	 * @return the bookingSupervision
	 */
	public String getBookingSupervision() {
		return bookingSupervision;
	}
	/**
	 * @param bookingSupervision the bookingSupervision to set
	 */
	public void setBookingSupervision(String bookingSupervision) {
		this.bookingSupervision = bookingSupervision;
	}
	/**
	 * @return the currentSupervision
	 */
	public String getCurrentSupervision() {
		return currentSupervision;
	}
	/**
	 * @param currentSupervision the currentSupervision to set
	 */
	public void setCurrentSupervision(String currentSupervision) {
		this.currentSupervision = currentSupervision;
	}
	/**
	 * @return the currentReferral
	 */
	public String getCurrentReferral() {
		return currentReferral;
	}
	/**
	 * @param currentReferral the currentReferral to set
	 */
	public void setCurrentReferral(String currentReferral) {
		this.currentReferral = currentReferral;
	}
	/**
	 * @return the bookingReferral
	 */
	public String getBookingReferral() {
		return bookingReferral;
	}
	/**
	 * @param bookingReferral the bookingReferral to set
	 */
	public void setBookingReferral(String bookingReferral) {
		this.bookingReferral = bookingReferral;
	}
	/**
	 * @return the currentOffenseLevel
	 */
	public String getCurrentOffenseLevel() {
		return currentOffenseLevel;
	}
	/**
	 * @param currentOffenseLevel the currentOffenseLevel to set
	 */
	public void setCurrentOffenseLevel(String currentOffenseLevel) {
		this.currentOffenseLevel = currentOffenseLevel;
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
	 * @return the transferFromReferral
	 */
	public String getTransferFromReferral() {
		return transferFromReferral;
	}
	/**
	 * @param transferFromReferral the transferFromReferral to set
	 */
	public void setTransferFromReferral(String transferFromReferral) {
		this.transferFromReferral = transferFromReferral;
	}
	/**
	 * @return the transferToReferral
	 */
	public String getTransferToReferral() {
		return transferToReferral;
	}
	/**
	 * @param transferToReferral the transferToReferral to set
	 */
	public void setTransferToReferral(String transferToReferral) {
		this.transferToReferral = transferToReferral;
	}
}

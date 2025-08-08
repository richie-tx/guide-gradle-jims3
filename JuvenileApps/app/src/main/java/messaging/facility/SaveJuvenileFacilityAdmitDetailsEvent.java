package messaging.facility;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileFacilityAdmitDetailsEvent extends RequestEvent{
	
	private String juvenileNum;
	private Date admitDate;
	private String admitTime;
	private String admitAuthority;
	private String admitBy;
	private String detainedFacility;
	private String secureStatus;
	private String bookingReferral;
	private String bookingOffense;	
	private String bookingSupervisionNum;	
	private String bookingPetitionNum;
	private String referralSource;	
	private String referralOfficers;	
	private String valuablesReceipt;
	private String locker;	
	private String reasonCode;
	private String admitReasonDetentionType;
	private Date originalAdmitDate;	
	private String originalAdmitTime;
	private Date detainedDate;	
	private String facilitySeqNum;
	private String currentReferral;
	private String currentOffense;
	private String currentSupervisionNum;
	private String currentPetitionNum;
	private String admissionComments;
	private String floorLocation;
	private String unitLocation;
	private String roomLocation;
	private boolean locationChangeFlag;
	private boolean secureStatusChangeFlag;
	private boolean admitReasonChangeFlag;
	private String admissionChangeExpanation;
	private String detentionType;
	private String genDetHearingChain;
	private String daysToProbableCause;
	private String facRefereeCourt;	
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;
	private String originalAdmitOID; //added for User-story #44549
	private String detentionId;
	
	private String multipleOccupyUnit;
	
	private boolean newAdmit;
	
	private boolean admitAfterHrdrelease; 		//added for User-story #44549
	
	//Bug #69605
	private String detainedByInd;
	
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	private String vendorLocation;
	private String avgCostPerDay;
	private String originaladmitSEQNUM;
	private String postAdmitOID;
	
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
	/**
	 * @param admitDate the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}
	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitTime the admitTime to set
	 */
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}
	/**
	 * @return the admitTime
	 */
	public String getAdmitTime() {
		return admitTime;
	}
	/**
	 * @param admitAuthority the admitAuthority to set
	 */
	public void setAdmitAuthority(String admitAuthority) {
		this.admitAuthority = admitAuthority;
	}
	/**
	 * @return the admitAuthority
	 */
	public String getAdmitAuthority() {
		return admitAuthority;
	}
	/**
	 * @param admitBy the admitBy to set
	 */
	public void setAdmitBy(String admitBy) {
		this.admitBy = admitBy;
	}
	/**
	 * @return the admitBy
	 */
	public String getAdmitBy() {
		return admitBy;
	}
	/**
	 * @param detainedFacility the detainedFacility to set
	 */
	public void setDetainedFacility(String detainedFacility) {
		this.detainedFacility = detainedFacility;
	}
	/**
	 * @return the detainedFacility
	 */
	public String getDetainedFacility() {
		return detainedFacility;
	}
	/**
	 * @param secureStatus the secureStatus to set
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}
	/**
	 * @return the secureStatus
	 */
	public String getSecureStatus() {
		return secureStatus;
	}
	/**
	 * @param bookingReferral the bookingReferral to set
	 */
	public void setBookingReferral(String bookingReferral) {
		this.bookingReferral = bookingReferral;
	}
	/**
	 * @return the bookingReferral
	 */
	public String getBookingReferral() {
		return bookingReferral;
	}
	/**
	 * @param bookingOffense the bookingOffense to set
	 */
	public void setBookingOffense(String bookingOffense) {
		this.bookingOffense = bookingOffense;
	}
	/**
	 * @return the bookingOffense
	 */
	public String getBookingOffense() {
		return bookingOffense;
	}
	/**
	 * @param bookingSupervisionNum the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}
	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}
	/**
	 * @param bookingPetitionNum the bookingPetitionNum to set
	 */
	public void setBookingPetitionNum(String bookingPetitionNum) {
		this.bookingPetitionNum = bookingPetitionNum;
	}
	/**
	 * @return the bookingPetitionNum
	 */
	public String getBookingPetitionNum() {
		return bookingPetitionNum;
	}
	/**
	 * @param referralSource the referralSource to set
	 */
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}
	/**
	 * @return the referralSource
	 */
	public String getReferralSource() {
		return referralSource;
	}
	/**
	 * @param referralOfficers the referralOfficers to set
	 */
	public void setReferralOfficers(String referralOfficers) {
		this.referralOfficers = referralOfficers;
	}
	/**
	 * @return the referralOfficers
	 */
	public String getReferralOfficers() {
		return referralOfficers;
	}
	/**
	 * @param valuablesReceipt the valuablesReceipt to set
	 */
	public void setValuablesReceipt(String valuablesReceipt) {
		this.valuablesReceipt = valuablesReceipt;
	}
	/**
	 * @return the valuablesReceipt
	 */
	public String getValuablesReceipt() {
		return valuablesReceipt;
	}
	/**
	 * @param locker the locker to set
	 */
	public void setLocker(String locker) {
		this.locker = locker;
	}
	/**
	 * @return the locker
	 */
	public String getLocker() {
		return locker;
	}
	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}
	/**
	 * @param originalAdmitDate the originalAdmitDate to set
	 */
	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}
	/**
	 * @return the originalAdmitDate
	 */
	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}
	/**
	 * @param detainedDate the detainedDate to set
	 */
	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}
	/**
	 * @return the detainedDate
	 */
	public Date getDetainedDate() {
		return detainedDate;
	}
	/**
	 * @param facilitySeqNum the facilitySeqNum to set
	 */
	public void setFacilitySeqNum(String facilitySeqNum) {
		this.facilitySeqNum = facilitySeqNum;
	}
	/**
	 * @return the facilitySeqNum
	 */
	public String getFacilitySeqNum() {
		return facilitySeqNum;
	}
	/**
	 * @param currentReferral the currentReferral to set
	 */
	public void setCurrentReferral(String currentReferral) {
		this.currentReferral = currentReferral;
	}
	/**
	 * @return the currentReferral
	 */
	public String getCurrentReferral() {
		return currentReferral;
	}
	/**
	 * @param currentOffense the currentOffense to set
	 */
	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}
	/**
	 * @return the currentOffense
	 */
	public String getCurrentOffense() {
		return currentOffense;
	}
	/**
	 * @param currentSupervisionNum the currentSupervisionNum to set
	 */
	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}
	/**
	 * @return the currentSupervisionNum
	 */
	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}
	/**
	 * @param currentPetitionNum the currentPetitionNum to set
	 */
	public void setCurrentPetitionNum(String currentPetitionNum) {
		this.currentPetitionNum = currentPetitionNum;
	}
	/**
	 * @return the currentPetitionNum
	 */
	public String getCurrentPetitionNum() {
		return currentPetitionNum;
	}
	/**
	 * @param admissionComments the admissionComments to set
	 */
	public void setAdmissionComments(String admissionComments) {
		this.admissionComments = admissionComments;
	}
	/**
	 * @return the admissionComments
	 */
	public String getAdmissionComments() {
		return admissionComments;
	}
	/**
	 * @param floorLocation the floorLocation to set
	 */
	public void setFloorLocation(String floorLocation) {
		this.floorLocation = floorLocation;
	}
	/**
	 * @return the floorLocation
	 */
	public String getFloorLocation() {
		return floorLocation;
	}
	/**
	 * @param unitLocation the unitLocation to set
	 */
	public void setUnitLocation(String unitLocation) {
		this.unitLocation = unitLocation;
	}
	/**
	 * @return the unitLocation
	 */
	public String getUnitLocation() {
		return unitLocation;
	}
	/**
	 * @param roomLocation the roomLocation to set
	 */
	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}
	/**
	 * @return the roomLocation
	 */
	public String getRoomLocation() {
		return roomLocation;
	}
	/**
	 * @param locationChangeFlag the locationChangeFlag to set
	 */
	public void setLocationChangeFlag(boolean locationChangeFlag) {
		this.locationChangeFlag = locationChangeFlag;
	}
	/**
	 * @return the locationChangeFlag
	 */
	public boolean isLocationChangeFlag() {
		return locationChangeFlag;
	}
	/**
	 * @param secureStatusChangeFlag the secureStatusChangeFlag to set
	 */
	public void setSecureStatusChangeFlag(boolean secureStatusChangeFlag) {
		this.secureStatusChangeFlag = secureStatusChangeFlag;
	}
	/**
	 * @return the secureStatusChangeFlag
	 */
	public boolean isSecureStatusChangeFlag() {
		return secureStatusChangeFlag;
	}
	/**
	 * @param admitReasonChangeFlag the admitReasonChangeFlag to set
	 */
	public void setAdmitReasonChangeFlag(boolean admitReasonChangeFlag) {
		this.admitReasonChangeFlag = admitReasonChangeFlag;
	}
	/**
	 * @return the admitReasonChangeFlag
	 */
	public boolean isAdmitReasonChangeFlag() {
		return admitReasonChangeFlag;
	}
	
	/**
	 * @param admissionChangeExpanation the admissionChangeExpanation to set
	 */
	public void setAdmissionChangeExpanation(String admissionChangeExpanation) {
		this.admissionChangeExpanation = admissionChangeExpanation;
	}
	/**
	 * @return the admissionChangeExpanation
	 */
	public String getAdmissionChangeExpanation() {
		return admissionChangeExpanation;
	}
	/**
	 * @param specialAttentionCd the specialAttentionCd to set
	 */
	public void setSpecialAttentionCd(String specialAttentionCd) {
		this.specialAttentionCd = specialAttentionCd;
	}
	/**
	 * @return the specialAttentionCd
	 */
	public String getSpecialAttentionCd() {
		return specialAttentionCd;
	}
	/**
	 * @param saReason the saReason to set
	 */
	public void setSaReason(String saReason) {
		this.saReason = saReason;
	}
	/**
	 * @return the saReason
	 */
	public String getSaReason() {
		return saReason;
	}
	/**
	 * @param saReasonOtherComments the saReasonOtherComments to set
	 */
	public void setSaReasonOtherComments(String saReasonOtherComments) {
		this.saReasonOtherComments = saReasonOtherComments;
	}
	/**
	 * @return the saReasonOtherComments
	 */
	public String getSaReasonOtherComments() {
		return saReasonOtherComments;
	}
	/**
	 * @param admitReasonDetentionType the admitReasonDetentionType to set
	 */
	public void setAdmitReasonDetentionType(String admitReasonDetentionType) {
		this.admitReasonDetentionType = admitReasonDetentionType;
	}
	/**
	 * @return the admitReasonDetentionType
	 */
	public String getAdmitReasonDetentionType() {
		return admitReasonDetentionType;
	}
	/**
	 * @param detentionType the detentionType to set
	 */
	public void setDetentionType(String detentionType) {
		this.detentionType = detentionType;
	}
	/**
	 * @return the detentionType
	 */
	public String getDetentionType() {
		return detentionType;
	}
	public void setGenDetHearingChain(String genDetHearingChain) {
		this.genDetHearingChain = genDetHearingChain;
	}
	public String getGenDetHearingChain() {
		return genDetHearingChain;
	}
	public void setDaysToProbableCause(String daysToProbableCause) {
		this.daysToProbableCause = daysToProbableCause;
	}
	public String getDaysToProbableCause() {
		return daysToProbableCause;
	}
	public void setFacRefereeCourt(String facRefereeCourt) {
		this.facRefereeCourt = facRefereeCourt;
	}
	public String getFacRefereeCourt() {
		return facRefereeCourt;
	}
	public void setOriginalAdmitTime(String originalAdmitTime) {
		this.originalAdmitTime = originalAdmitTime;
	}
	public String getOriginalAdmitTime() {
		return originalAdmitTime;
	}
	public boolean isNewAdmit() {
		return newAdmit;
	}
	public void setNewAdmit(boolean newAdmit) {
		this.newAdmit = newAdmit;
	}
	public String getMultipleOccupyUnit() {
		return multipleOccupyUnit;
	}
	public void setMultipleOccupyUnit(String multipleOccupyUnit) {
		this.multipleOccupyUnit = multipleOccupyUnit;
	}
	/**
	 * @return the originalAdmitOID
	 */
	public String getOriginalAdmitOID() {
		return originalAdmitOID;
	}
	/**
	 * @param originalAdmitOID the originalAdmitOID to set
	 */
	public void setOriginalAdmitOID(String originalAdmitOID) {
		this.originalAdmitOID = originalAdmitOID;
	}
	/**
	 * @return the admitAfterHrdrelease
	 */
	public boolean isAdmitAfterHrdrelease() {
		return admitAfterHrdrelease;
	}
	/**
	 * @param admitAfterHrdrelease the admitAfterHrdrelease to set
	 */
	public void setAdmitAfterHrdrelease(boolean admitAfterHrdrelease) {
		this.admitAfterHrdrelease = admitAfterHrdrelease;
	}
	/**
	 * @return the detainedByInd
	 */
	public String getDetainedByInd() {
		return detainedByInd;
	}
	/**
	 * @param detainedByInd the detainedByInd to set
	 */
	public void setDetainedByInd(String detainedByInd) {
		this.detainedByInd = detainedByInd;
	}
	public String getDetentionId()
	{
	    return detentionId;
	}
	public void setDetentionId(String detentionId)
	{
	    this.detentionId = detentionId;
	}
	public int getTjjdFacilityId()
	{
	    return tjjdFacilityId;
	}
	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	    this.tjjdFacilityId = tjjdFacilityId;
	}
	public String getTjjdFundingSrc()
	{
	    return tjjdFundingSrc;
	}
	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	    this.tjjdFundingSrc = tjjdFundingSrc;
	}
	

	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
	}
	
	public String getAvgCostPerDay()
	{
	    return avgCostPerDay;
	}
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
	}
	public String getOriginaladmitSEQNUM()
	{
	    return originaladmitSEQNUM;
	}
	public void setOriginaladmitSEQNUM(String originaladmitSEQNUM)
	{
	    this.originaladmitSEQNUM = originaladmitSEQNUM;
	}
	public String getPostAdmitOID()
	{
	    return postAdmitOID;
	}
	public void setPostAdmitOID(String postAdmitOID)
	{
	    this.postAdmitOID = postAdmitOID;
	}
	
	
	

}

package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProdSupportJJSDetentionEvent extends RequestEvent 
{
   
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
	private String admissionChangeExpanation;
	private String detentionType;	
	private String specialAttentionCd;
	private String saReason;
	private String saReasonOtherComments;
	private String multipleOccupyUnit;
	private String detainedByInd;
	private String detentionId;
	private String transferToFacility;
	private Date releaseDate;
	private String releaseTime;
	private Date returnDate;
	private String returnTime;
	private String outcome;
	private String releasedTo;
	private String releaseReason;
	private String returnReason;
	private String releaseAuthority;
	private String releasedBy;
	private String tempReleaseReason;	
	private String lcuser;
	private Date lcDate;
	private String facilityStatus;	
	private String returnStatus;
	private String riskAnalysisId;
	private String originalAdmitOID;
	private String avgCostPerDay;	
	private String tjjdFundingSrc;
	private String  originaladmitSEQNUM;
	private String postAdmitOID;
	private int tjjdFacilityId;
	private String custodyfirstName;
	private String custodylastName;
	
	
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
	
	public int getTjjdFacilityId()
	{
	    return tjjdFacilityId;
	}

	public void setTjjdFacilityId(int tjjdFacilityId)
	{
	     this.tjjdFacilityId = tjjdFacilityId;
	}
	
	public String getPostAdmitOID()
	{
	    return postAdmitOID;
	}

	public void setPostAdmitOID(String postAdmitOID)
	{
	    this.postAdmitOID = postAdmitOID;
	}
	
	public String getOriginaladmitSEQNUM()
	{
	    return originaladmitSEQNUM;
	}

	public void setOriginaladmitSEQNUM(String originaladmitSEQNUM)
	{
	    this.originaladmitSEQNUM = originaladmitSEQNUM;
	}
	
	public String getTjjdFundingSrc()
	{
	    return tjjdFundingSrc;
	}

	public void setTjjdFundingSrc(String tjjdFundingSrc)
	{
	      this.tjjdFundingSrc = tjjdFundingSrc;
	}
	
	
	
	public String getAvgCostPerDay()
	{	   
	    return avgCostPerDay;
	}
	
	public void setAvgCostPerDay(String avgCostPerDay)
	{
	    this.avgCostPerDay = avgCostPerDay;
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
	
	public void setOriginalAdmitTime(String originalAdmitTime) {
		this.originalAdmitTime = originalAdmitTime;
	}
	public String getOriginalAdmitTime() {
		return originalAdmitTime;
	}
	
	public String getMultipleOccupyUnit() {
		return multipleOccupyUnit;
	}
	public void setMultipleOccupyUnit(String multipleOccupyUnit) {
		this.multipleOccupyUnit = multipleOccupyUnit;
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
	public String getTransferToFacility()
	{
	    return transferToFacility;
	}
	public void setTransferToFacility(String transferToFacility)
	{
	    this.transferToFacility = transferToFacility;
	}
	public Date getReleaseDate()
	{
	    return releaseDate;
	}
	public void setReleaseDate(Date releaseDate)
	{
	    this.releaseDate = releaseDate;
	}
	public String getReleaseTime()
	{
	    return releaseTime;
	}
	public void setReleaseTime(String releaseTime)
	{
	    this.releaseTime = releaseTime;
	}
	public Date getReturnDate()
	{
	    return returnDate;
	}
	public void setReturnDate(Date returnDate)
	{
	    this.returnDate = returnDate;
	}
	public String getReturnTime()
	{
	    return returnTime;
	}
	public void setReturnTime(String returnTime)
	{
	    this.returnTime = returnTime;
	}
	public String getOutcome()
	{
	    return outcome;
	}
	public void setOutcome(String outcome)
	{
	    this.outcome = outcome;
	}
	public String getReleasedTo()
	{
	    return releasedTo;
	}
	public void setReleasedTo(String releasedTo)
	{
	    this.releasedTo = releasedTo;
	}
	public String getReleaseReason()
	{
	    return releaseReason;
	}
	public void setReleaseReason(String releaseReason)
	{
	    this.releaseReason = releaseReason;
	}
	public String getReturnReason()
	{
	    return returnReason;
	}
	public void setReturnReason(String returnReason)
	{
	    this.returnReason = returnReason;
	}
	public String getReleaseAuthority()
	{
	    return releaseAuthority;
	}
	public void setReleaseAuthority(String releaseAuthority)
	{
	    this.releaseAuthority = releaseAuthority;
	}
	public String getReleasedBy()
	{
	    return releasedBy;
	}
	public void setReleasedBy(String releasedBy)
	{
	    this.releasedBy = releasedBy;
	}
	public String getTempReleaseReason()
	{
	    return tempReleaseReason;
	}
	public void setTempReleaseReason(String tempReleaseReason)
	{
	    this.tempReleaseReason = tempReleaseReason;
	}
	public String getLcuser()
	{
	    return lcuser;
	}
	public void setLcuser(String lcuser)
	{
	    this.lcuser = lcuser;
	}
	public Date getLcDate()
	{
	    return lcDate;
	}
	public void setLcDate(Date lcDate)
	{
	    this.lcDate = lcDate;
	}
	public String getFacilityStatus()
	{
	    return facilityStatus;
	}
	public void setFacilityStatus(String facilityStatus)
	{
	    this.facilityStatus = facilityStatus;
	}
	public String getReturnStatus()
	{
	    return returnStatus;
	}
	public void setReturnStatus(String returnStatus)
	{
	    this.returnStatus = returnStatus;
	}
	
	public String getRiskAnalysisId()
	{
	    return riskAnalysisId;
	}
	public void setRiskAnalysisId(String riskAnalysisId)
	{
	    this.riskAnalysisId = riskAnalysisId;
	}
}

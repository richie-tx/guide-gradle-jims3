/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.productionsupport.reply;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.referral.JJSReferral;

import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.messaging.ResponseEvent;


/**
 * @author rcarter
 *
 */
public class ProductionSupportFacilityDetentionResponseEvent extends ResponseEvent
{
	private String detentionId;
	private String juvenileId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String suffixName;
	private String bookingReferralNum;
	private String currentReferralNum;
	private String bookingSupervisionNum;
	private String currentSupervisionNum;
	private String sequenceNum;
	private String tjpcSequenceNum;
	private String currentOffense;
	private String facilityCode;
	private String facilityName;
	private String facilityStatusCode;
	private String facilityStatusCodeDesc;
	private String admittanceReasonCode;
	private String admittanceReasonCodeDesc;
	private String secureStatus;
	private String locationUnit;
	private String transferFacilityCode;
	private String transferFacilityCodeDesc;
	private String locationFloorNumber;
	private String roomNumber;
	private String multipleOccupanyUnit;
	private String lockerNumber;	
	private String valuablesReceiptNumber;
	private Date detainedDate;
	private String admittedByAuthority;
	private Date admittedDate;
	private String admittedTime;
	private String admittedAuthorizeJPO;
	private Date originalAdmittedDate;
	private String originalAdmittedTime;	
	private String changeExplanation;
	private String comments;
	private String escapeAttempts;
	private String escapeCode;
	private String escapeCodeDesc;
	private String escapeAttemptComments;
	private String riskAnalysisId;
	
	private String outcome;
	private Date   releaseDate;
	private String releaseTime;
	private String releaseBy;
	private String releaseTo;
	private String releaseAuthorizedBy;
	private String releaseReason;
	private Date   returnDate;
	private String returnTime;
	private String returnReason;
	private String returnStatus;
	private String specialAttention;
	private String specialAttentionReason;
	private String specialAttentionOtherComments;
	private String tempReleaseReasonCode;
	private String tempReleaseReasonCodeDesc;
	private String tempReleaseOtherComments;
	
	
	//added for Production Support Audit Data
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	private String originalAdmitOID;
	private String avgCostPerDay;
	private String detainedByInd;
	private String tjjdFundingSrc;
	private String  originaladmitSEQNUM;
	private String postAdmitOID;
	private int tjjdFacilityId;
	private String custodyfirstName;
	private String custodylastName;
	
	//US 88627
	private ArrayList<JJSReferral> referralNumbers;
	private ArrayList<JuvenileCasefileSearchResponseEvent> currentCasefiles;
	
	public ProductionSupportFacilityDetentionResponseEvent()
	{
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
	 * @return the juvenileId
	 */
	public String getJuvenileId() {
		return juvenileId;
	}

	/**
	 * @param juvenileId the juvenileId to set
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the suffixName
	 */
	public String getSuffixName() {
		return suffixName;
	}

	/**
	 * @param suffixName the suffixName to set
	 */
	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	/**
	 * @return the bookingReferralNum
	 */
	public String getBookingReferralNum() {
		return bookingReferralNum;
	}

	/**
	 * @param bookingReferralNum the bookingReferralNum to set
	 */
	public void setBookingReferralNum(String bookingReferralNum) {
		this.bookingReferralNum = bookingReferralNum;
	}

	/**
	 * @return the currentReferralNum
	 */
	public String getCurrentReferralNum() {
		return currentReferralNum;
	}



	/**
	 * @param currentReferralNum the currentReferralNum to set
	 */
	public void setCurrentReferralNum(String currentReferralNum) {
		this.currentReferralNum = currentReferralNum;
	}



	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}

	/**
	 * @param bookingSupervisionNum the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}

	/**
	 * @return the currentSupervisionNum
	 */
	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}



	/**
	 * @param currentSupervisionNum the currentSupervisionNum to set
	 */
	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}



	/**
	 * @return the SequenceNum
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param sequenceNum the sequenceNum to set
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return the facilityCode
	 */
	public String getFacilityCode() {
		return facilityCode;
	}

	/**
	 * @param facilityCode the facilityCode to set
	 */
	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	/**
	 * @return the facilityName
	 */
	public String getFacilityName() {
		return facilityName;
	}

	/**
	 * @param facilityName the facilityName to set
	 */
	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	/**
	 * @return the facilityStatusCode
	 */
	public String getFacilityStatusCode() {
		return facilityStatusCode;
	}

	/**
	 * @param facilityStatusCode the facilityStatusCode to set
	 */
	public void setFacilityStatusCode(String facilityStatusCode) {
		this.facilityStatusCode = facilityStatusCode;
	}

	/**
	 * @return the facilityStatusCodeDesc
	 */
	public String getFacilityStatusCodeDesc() {
		return facilityStatusCodeDesc;
	}

	/**
	 * @param facilityStatusCodeDesc the facilityStatusCodeDesc to set
	 */
	public void setFacilityStatusCodeDesc(String facilityStatusCodeDesc) {
		this.facilityStatusCodeDesc = facilityStatusCodeDesc;
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}

	/**
	 * @return the detentionId
	 */
	public String getDetentionId() {
		return detentionId;
	}

	/**
	 * @param detentionId the detentionId to set
	 */
	public void setDetentionId(String detentionId) {
		this.detentionId = detentionId;
	}
	
	/**
	 * @return the tjpcSequenceNum
	 */
	public String getTjpcSequenceNum() {
		return tjpcSequenceNum;
	}

	/**
	 * @param tjpcSequenceNum the tjpcSequenceNum to set
	 */
	public void setTjpcSequenceNum(String tjpcSequenceNum) {
		this.tjpcSequenceNum = tjpcSequenceNum;
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

	/**
	 * @return the admittanceReasonCode
	 */
	public String getAdmittanceReasonCode() {
		return admittanceReasonCode;
	}

	/**
	 * @param admittanceReasonCode the admittanceReasonCode to set
	 */
	public void setAdmittanceReasonCode(String admittanceReasonCode) {
		this.admittanceReasonCode = admittanceReasonCode;
	}

	/**
	 * @return the secureStatus
	 */
	public String getSecureStatus() {
		return secureStatus;
	}

	/**
	 * @param secureStatus the secureStatus to set
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}

	/**
	 * @return the transferFacilityCode
	 */
	public String getTransferFacilityCode() {
		return transferFacilityCode;
	}

	/**
	 * @param transferFacilityCode the transferFacilityCode to set
	 */
	public void setTransferFacilityCode(String transferFacilityCode) {
		this.transferFacilityCode = transferFacilityCode;
	}

	/**
	 * @return the locationUnit
	 */
	public String getLocationUnit() {
		return locationUnit;
	}

	/**
	 * @param locationUnit the locationUnit to set
	 */
	public void setLocationUnit(String locationUnit) {
		this.locationUnit = locationUnit;
	}

	/**
	 * @return the locationFloorNumber
	 */
	public String getLocationFloorNumber() {
		return locationFloorNumber;
	}

	/**
	 * @param locationFloorNumber the locationFloorNumber to set
	 */
	public void setLocationFloorNumber(String locationFloorNumber) {
		this.locationFloorNumber = locationFloorNumber;
	}

	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the multipleOccupanyUnit
	 */
	public String getMultipleOccupanyUnit() {
		return multipleOccupanyUnit;
	}

	/**
	 * @param multipleOccupanyUnit the multipleOccupanyUnit to set
	 */
	public void setMultipleOccupanyUnit(String multipleOccupanyUnit) {
		this.multipleOccupanyUnit = multipleOccupanyUnit;
	}

	/**
	 * @return the lockerNumber
	 */
	public String getLockerNumber() {
		return lockerNumber;
	}

	/**
	 * @param lockerNumber the lockerNumber to set
	 */
	public void setLockerNumber(String lockerNumber) {
		this.lockerNumber = lockerNumber;
	}

	/**
	 * @return the valuablesReceiptNumber
	 */
	public String getValuablesReceiptNumber() {
		return valuablesReceiptNumber;
	}

	/**
	 * @param valuablesReceiptNumber the valuablesReceiptNumber to set
	 */
	public void setValuablesReceiptNumber(String valuablesReceiptNumber) {
		this.valuablesReceiptNumber = valuablesReceiptNumber;
	}

	/**
	 * @return the admittedByAuthority
	 */
	public String getAdmittedByAuthority() {
		return admittedByAuthority;
	}

	/**
	 * @param admittedByAuthority the admittedByAuthority to set
	 */
	public void setAdmittedByAuthority(String admittedByAuthority) {
		this.admittedByAuthority = admittedByAuthority;
	}

	/**
	 * @return the admittedTime
	 */
	public String getAdmittedTime() {
		return admittedTime;
	}

	/**
	 * @param admittedTime the admittedTime to set
	 */
	public void setAdmittedTime(String admittedTime) {
		this.admittedTime = admittedTime;
	}

	/**
	 * @return the admittedAuthorizeJPO
	 */
	public String getAdmittedAuthorizeJPO() {
		return admittedAuthorizeJPO;
	}

	/**
	 * @param admittedAuthorizeJPO the admittedAuthorizeJPO to set
	 */
	public void setAdmittedAuthorizeJPO(String admittedAuthorizeJPO) {
		this.admittedAuthorizeJPO = admittedAuthorizeJPO;
	}

	/**
	 * @return the originalAdmittedTime
	 */
	public String getOriginalAdmittedTime() {
		return originalAdmittedTime;
	}

	/**
	 * @param originalAdmittedTime the originalAdmittedTime to set
	 */
	public void setOriginalAdmittedTime(String originalAdmittedTime) {
		this.originalAdmittedTime = originalAdmittedTime;
	}

	/**
	 * @return the detainedDate
	 */
	public Date getDetainedDate() {
		return detainedDate;
	}

	/**
	 * @param detainedDate the detainedDate to set
	 */
	public void setDetainedDate(Date detainedDate) {
		this.detainedDate = detainedDate;
	}

	/**
	 * @return the admittedDate
	 */
	public Date getAdmittedDate() {
		return admittedDate;
	}

	/**
	 * @param admittedDate the admittedDate to set
	 */
	public void setAdmittedDate(Date admittedDate) {
		this.admittedDate = admittedDate;
	}

	/**
	 * @return the originalAdmittedDate
	 */
	public Date getOriginalAdmittedDate() {
		return originalAdmittedDate;
	}

	/**
	 * @param originalAdmittedDate the originalAdmittedDate to set
	 */
	public void setOriginalAdmittedDate(Date originalAdmittedDate) {
		this.originalAdmittedDate = originalAdmittedDate;
	}

	/**
	 * @return the changeExplanation
	 */
	public String getChangeExplanation() {
		return changeExplanation;
	}

	/**
	 * @param changeExplanation the changeExplanation to set
	 */
	public void setChangeExplanation(String changeExplanation) {
		this.changeExplanation = changeExplanation;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the escapeAttempts
	 */
	public String getEscapeAttempts() {
		return escapeAttempts;
	}

	/**
	 * @param escapeAttempts the escapeAttempts to set
	 */
	public void setEscapeAttempts(String escapeAttempts) {
		this.escapeAttempts = escapeAttempts;
	}

	/**
	 * @return the escapeCode
	 */
	public String getEscapeCode() {
		return escapeCode;
	}

	/**
	 * @param escapeCode the escapeCode to set
	 */
	public void setEscapeCode(String escapeCode) {
		this.escapeCode = escapeCode;
	}

	/**
	 * @return the escapeAttemptComments
	 */
	public String getEscapeAttemptComments() {
		return escapeAttemptComments;
	}

	/**
	 * @param escapeAttemptComments the escapeAttemptComments to set
	 */
	public void setEscapeAttemptComments(String escapeAttemptComments) {
		this.escapeAttemptComments = escapeAttemptComments;
	}
	
	/**
	 * @return the riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}

	/**
	 * @param the riskAnalysisId to set
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	/**
	 * @return the outcome
	 */
	public String getOutcome() {
		return outcome;
	}

	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	/**
	 * @return the releaseDate
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * @param releaseDate the releaseDate to set
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
	 * @param releaseTime the releaseTime to set
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	/**
	 * @return the releaseBy
	 */
	public String getReleaseBy() {
		return releaseBy;
	}

	/**
	 * @param releaseBy the releaseBy to set
	 */
	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}

	/**
	 * @return the releaseTo
	 */
	public String getReleaseTo() {
		return releaseTo;
	}

	/**
	 * @param releaseTo the releaseTo to set
	 */
	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	/**
	 * @return the releaseAuthorizedBy
	 */
	public String getReleaseAuthorizedBy() {
		return releaseAuthorizedBy;
	}

	/**
	 * @param releaseAuthorizedBy the releaseAuthorizedBy to set
	 */
	public void setReleaseAuthorizedBy(String releaseAuthorizedBy) {
		this.releaseAuthorizedBy = releaseAuthorizedBy;
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
	 * @return the specialAttention
	 */
	public String getSpecialAttention() {
		return specialAttention;
	}

	/**
	 * @param specialAttention the specialAttention to set
	 */
	public void setSpecialAttention(String specialAttention) {
		this.specialAttention = specialAttention;
	}

	/**
	 * @return the specialAttentionReason
	 */
	public String getSpecialAttentionReason() {
		return specialAttentionReason;
	}

	/**
	 * @param specialAttentionReason the specialAttentionReason to set
	 */
	public void setSpecialAttentionReason(String specialAttentionReason) {
		this.specialAttentionReason = specialAttentionReason;
	}

	/**
	 * @return the specialAttentionOtherComments
	 */
	public String getSpecialAttentionOtherComments() {
		return specialAttentionOtherComments;
	}

	/**
	 * @param specialAttentionOtherComments the specialAttentionOtherComments to set
	 */
	public void setSpecialAttentionOtherComments(
			String specialAttentionOtherComments) {
		this.specialAttentionOtherComments = specialAttentionOtherComments;
	}

	/**
	 * @return the tempReleaseReasonCode
	 */
	public String getTempReleaseReasonCode() {
		return tempReleaseReasonCode;
	}

	/**
	 * @param tempReleaseReasonCode the tempReleaseReasonCode to set
	 */
	public void setTempReleaseReasonCode(String tempReleaseReasonCode) {
		this.tempReleaseReasonCode = tempReleaseReasonCode;
	}

	/**
	 * @return the tempReleaseOtherComments
	 */
	public String getTempReleaseOtherComments() {
		return tempReleaseOtherComments;
	}

	/**
	 * @param tempReleaseOtherComments the tempReleaseOtherComments to set
	 */
	public void setTempReleaseOtherComments(String tempReleaseOtherComments) {
		this.tempReleaseOtherComments = tempReleaseOtherComments;
	}
	

	/**
	 * @return the admittanceReasonCodeDesc
	 */
	public String getAdmittanceReasonCodeDesc() {
		return admittanceReasonCodeDesc;
	}

	/**
	 * @param admittanceReasonCodeDesc the admittanceReasonCodeDesc to set
	 */
	public void setAdmittanceReasonCodeDesc(String admittanceReasonCodeDesc) {
		this.admittanceReasonCodeDesc = admittanceReasonCodeDesc;
	}

	/**
	 * @return the transferFacilityCodeDesc
	 */
	public String getTransferFacilityCodeDesc() {
		return transferFacilityCodeDesc;
	}

	/**
	 * @param transferFacilityCodeDesc the transferFacilityCodeDesc to set
	 */
	public void setTransferFacilityCodeDesc(String transferFacilityCodeDesc) {
		this.transferFacilityCodeDesc = transferFacilityCodeDesc;
	}

	/**
	 * @return the escapeCodeDesc
	 */
	public String getEscapeCodeDesc() {
		return escapeCodeDesc;
	}

	/**
	 * @param escapeCodeDesc the escapeCodeDesc to set
	 */
	public void setEscapeCodeDesc(String escapeCodeDesc) {
		this.escapeCodeDesc = escapeCodeDesc;
	}

	/**
	 * @return the tempReleaseReasonCodeDesc
	 */
	public String getTempReleaseReasonCodeDesc() {
		return tempReleaseReasonCodeDesc;
	}

	/**
	 * @param tempReleaseReasonCodeDesc the tempReleaseReasonCodeDesc to set
	 */
	public void setTempReleaseReasonCodeDesc(String tempReleaseReasonCodeDesc) {
		this.tempReleaseReasonCodeDesc = tempReleaseReasonCodeDesc;
	}

	/**
	 * @return the detentionSequenceNumberComparator
	 */
	public static Comparator getDetentionSequenceNumberComparator() {
		return detentionSequenceNumberComparator;
	}

	/**
	 * @param detentionSequenceNumberComparator the detentionSequenceNumberComparator to set
	 */
	public static void setDetentionSequenceNumberComparator(
			Comparator detentionSequenceNumberComparator) {
		ProductionSupportFacilityDetentionResponseEvent.detentionSequenceNumberComparator = detentionSequenceNumberComparator;
	}



	public ArrayList<JJSReferral> getReferralNumbers()
	{
	    return referralNumbers;
	}

	public void setReferralNumbers(ArrayList<JJSReferral> referralNumbers)
	{
	    this.referralNumbers = referralNumbers;
	}



	public ArrayList<JuvenileCasefileSearchResponseEvent> getCurrentCasefiles()
	{
	    return currentCasefiles;
	}

	public void setCurrentCasefiles(ArrayList<JuvenileCasefileSearchResponseEvent> currentCasefiles)
	{
	    this.currentCasefiles = currentCasefiles;
	}



	/**
	 * Comparator to sort based on sequence number
	 */
	public static Comparator detentionSequenceNumberComparator = new Comparator() 
	{
		public int compare(Object detentionDetailResponse1, Object detentionDetailResponse2) 
		{	
		
		 String sequenceStringNum1 = ((ProductionSupportFacilityDetentionResponseEvent)detentionDetailResponse1).getSequenceNum();
		 int sequenceIntNum1 = 0;
		 String sequenceStringNum2 = ((ProductionSupportFacilityDetentionResponseEvent)detentionDetailResponse2).getSequenceNum();
		 int sequenceIntNum2 = 0;
		 if(sequenceStringNum1 != null && sequenceStringNum2 != null){
			  sequenceIntNum1 = Integer.valueOf(sequenceStringNum1);
			  sequenceIntNum2 = Integer.valueOf(sequenceStringNum2);
			  if (sequenceIntNum1 > sequenceIntNum2){
				  return -1;
			  }else if (sequenceIntNum1 < sequenceIntNum2){
				  return 1;
			  }else{
				  return 0;
			  }
		 }else if(sequenceStringNum1 == null){
			 return 1;
		 }else{
			 return -1;
		 }
		}
	};
}

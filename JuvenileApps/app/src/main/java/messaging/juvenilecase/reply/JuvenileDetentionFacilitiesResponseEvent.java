/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import messaging.casefile.reply.ActivityResponseEvent;
import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

/**
 * A fully populated JuvenileDetentionFacilitiesResponseEvent value object
 * 
 * @author ugopinath
 */
public class JuvenileDetentionFacilitiesResponseEvent extends ResponseEvent implements Comparable<JuvenileDetentionFacilitiesResponseEvent>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String admitReason;
	private String admitReasonDesc;
	private Date admitDate;
	private String admitTime;
	private Date originalAdmitDate;
	private String originalAdmitOID; //added for user-story #44549
	private String facilityName;
	private String referralNumber;
	
	private Date releaseDate;
	private String releaseTime;
	private String releaseTo;
	private String relToDesc;
	private String releaseBy;
	private String relByDesc;
	private String releaseReason;
	private String releaseReasonDesc;
	
	private String facilitySequenceNumber;
	private String secureStatus;
	private String lockerNumber;
	private String receiptNumber;
	private String juvTJPCPlacementType; //added for JIMS200077404
	
	
	//referral details
	private String referralSource;
	private String referralOfficer;
	private String referralSourceDesc;
	
	private String petitionNum;
	
	//return details
	private Date returnDate;
	private String returnTime;
	private String returnStatus;
	private String returnReason;
	
	//Added for JJS Facility Reports
	
	private String floorNum;
	private String unit;
	private String roomNum;
	private String juvNum;
	private Name juvName;
	private String juvSex;
	private String juvRace;
	private int age;
	private String reasonDescription;
	
	//Added for Resident Status report
	private String facilityStatus;
	private String facilityStatusDesc;
	private boolean locationChangeFlag;
	private boolean reasonChangeFlag;
	private boolean secureIndicatorChangeFlag;
	private boolean otherChangeFlag;
	private Date lastChangeDate;	
	private Date modifydate;
	private String changeExplanation;
	private String relocationTime;
	private Date relocationDate;
	private String releaseAuthorizedBy;
	private String relAuthByDesc;
	
	//facility Changes
	private String specialAttention;
	private String specialAttentionDesc;
	private String saReason;
	private String saReasonDesc;
	private String saReasonOther;
	private String saReasonOtherComments;
	
	private String admitAuthority;
	private String admitAuthorityDesc;
	private String admittedByJPO;
	private String admittedByJPODesc;
	
	private Date detainedDate;
	private String detainedByInd;
	private String detainedFacility;
	private String detainedFacilityDesc;
	
	private String bookingSupervisionNum;
	private String detentionStatus;
	private String currentOffense;
	private String currentOffenseDesc;
	
	private String currentReferral;
	private String currentSupervisionNum;
	
	
	private String comments;
	private String escapeCode;
	private String escapeCodeDesc;
	private String escapeAttempts;
	private String escapeAttemptComments;
	private String tempReleaseReasonCd;
	private String tempReleaseReasonCdDesc;
	private String tempReleaseOtherComments;
	private String outcome;
	private String outcomeDesc;
	
	private String transferToFacility;
	private String transferToFacilityDesc;
	private String multipleOccupyUnit;
	private String detentionId; //task #34820
	
	private String admitDateStr;
	private String releaseDateStr;
	private String lcTime;
	//added for 51734 user-story
	private String bookingOffenseCd;
	private String bookingOffenseDesc;
	//added for 51734 user-story
	
	//added for court Action
	private String updateFlag;
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	private String riskAnalysisId;
	private String custodylastName;
	private String custodyfirstName;
	
	private Date nexthearingDate;
	
	private String originaladmitSEQNUM;
	

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
	
	public String getRiskAnalysisId()
	{
	    return riskAnalysisId;
	}
	
	public void setRiskAnalysisId(String riskAnalysisId)
	{
	    this.riskAnalysisId = riskAnalysisId;
	}

	private Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistories = new ArrayList<JuvenileDetentionFacilitiesResponseEvent>(); //added for U.S 14780;
	
	private Collection<ActivityResponseEvent> activitiesByDetention = new ArrayList<ActivityResponseEvent>(); //added for U.S 103958;
	//private Collection activitiesByDetention;
	
	
	public int compareTo(JuvenileDetentionFacilitiesResponseEvent obj)
	{
		if(obj==null)
			return -1;
		JuvenileDetentionFacilitiesResponseEvent evt = (JuvenileDetentionFacilitiesResponseEvent) obj;
		return referralNumber.compareToIgnoreCase(evt.getReferralNumber());		
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
	 * @return Returns the secureStatus.
	 */
	public String getSecureStatus() {
		return secureStatus;
	}
	/**
	 * @param secureStatus The secureStatus to set.
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}

	/**
	 * @return the juvTJPCPlacementType
	 */
	public String getJuvTJPCPlacementType() {
		return juvTJPCPlacementType;
	}
	/**
	 * @param juvTJPCPlacementType the juvTJPCPlacementType to set
	 */
	public void setJuvTJPCPlacementType(String juvTJPCPlacementType) {
		this.juvTJPCPlacementType = juvTJPCPlacementType;
	}
	public Date getReleaseDateTime() {
		Date returnDate = null;
		if(releaseDate != null){
			Calendar cal = Calendar.getInstance();		
			cal.setTime(releaseDate);
			cal.set(Calendar.HOUR, new Integer(releaseTime.substring(0, releaseTime.length()-2)).intValue());
			cal.set(Calendar.MINUTE, new Integer(releaseTime.substring(releaseTime.length()-2, releaseTime.length())).intValue());
			returnDate = cal.getTime();
		}
		return returnDate;
	}

	public Date getAdmitDateTime() {
		Date returnDate = null;
		if(admitDate != null){		
			Calendar cal = Calendar.getInstance();		
			cal.setTime(admitDate);
			cal.set(Calendar.HOUR, new Integer(admitTime.substring(0, admitTime.length()-2)).intValue());
			cal.set(Calendar.MINUTE, new Integer(admitTime.substring(admitTime.length()-2, admitTime.length())).intValue());
			returnDate = cal.getTime();
		}
		return returnDate;
	}

	/**
	 * @return Returns the facilitySequenceNumber.
	 */
	public String getFacilitySequenceNumber() {
		return facilitySequenceNumber;
	}
	/**
	 * @param facilitySequenceNumber The facilitySequenceNumber to set.
	 */
	public void setFacilitySequenceNumber(String facilitySequenceNumber) {
		this.facilitySequenceNumber = facilitySequenceNumber;
	}
	
	/**
	 * @return Returns the admitDate.
	 */
	public Date getAdmitDate() {
		return admitDate;
	}
	/**
	 * @param admitDate The admitDate to set.
	 */
	public void setAdmitDate(Date admitDate) {
		
		this.admitDate = admitDate;
	}
	/**
	 * @return Returns the admitReason.
	 */
	public String getAdmitReason() {
		return admitReason;
	}
	/**
	 * @param admitReason The admitReason to set.
	 */
	public void setAdmitReason(String admitReason) {
		this.admitReason = admitReason;
	}
	/**
	 * @return Returns the admitTime.
	 */
	public String getAdmitTime() {
		return admitTime;
	}
	/**
	 * @param admitTime The admitTime to set.
	 */
	public void setAdmitTime(String admitTime) {
		this.admitTime=admitTime;
	}
	/**
	 * @return Returns the originalAdmitDate.
	 */
	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}
	/**
	 * @param originalAdmitDate The originalAdmitDate to set.
	 */
	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
	/**
	 * @return Returns the releaseBy.
	 */
	public String getReleaseBy() {
		return releaseBy;
	}
	/**
	 * @param releaseBy The releaseBy to set.
	 */
	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}
	/**
	 * @return Returns the releaseDate.
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}
	/**
	 * @param releaseDate The releaseDate to set.
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	/**
	 * @return Returns the releaseTime.
	 */
	public String getReleaseTime() {
		return releaseTime;
	}
	/**
	 * @param releaseTime The releaseTime to set.
	 */
	public void setReleaseTime(String releaseTime) {
		this.releaseTime=releaseTime;
		
	}
	/**
	 * @return Returns the releaseTo.
	 */
	public String getReleaseTo() {
		return releaseTo;
	}
	/**
	 * @param releaseTo The releaseTo to set.
	 */
	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
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
	 * @return the receiptNumber
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}
	/**
	 * @param receiptNumber the receiptNumber to set
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	/**
	 * @return the floorNum
	 */
	public String getFloorNum() {	
		return floorNum;
	}

	/**
	 * @param floorNum the floorNum to set
	 */
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	
	/**
	 * @return the unit
	 */
	public String getUnit() {	
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	/**
	 * @return the roomNum
	 */
	public String getRoomNum() {	
		return roomNum;
	}

	/**
	 * @param roomNum the roomNum to set
	 */
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	/**
	 * @return the juvNum
	 */
	public String getJuvNum() {	
		return juvNum;
	}

	/**
	 * @param juvNum the juvNum to set
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}
	/**
	 * @param facilityStatus The facilityStatus to set.
	 */
	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}
	/**
	 * @return Returns the facilityStatus.
	 */
	public String getFacilityStatus() {
		return facilityStatus;
	}
	
	/**
	 * @param lastChangeDate The lastChangeDate to set.
	 */
	public void setLastChangeDate(Date lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}
	/**
	 * @return Returns the lastChangeDate.
	 */
	public Date getLastChangeDate() {
		return lastChangeDate;
	}
	/**
	 * @param modifydate The modifydate to set.
	 */
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/**
	 * @return Returns the modifydate.
	 */
	public Date getModifydate() {
		return modifydate;
	}
	/**
	 * @param changeExplanation The changeExplanation to set.
	 */
	public void setChangeExplanation(String changeExplanation) {
		this.changeExplanation = changeExplanation;
	}
	/**
	 * @return Returns the changeExplanation.
	 */
	public String getChangeExplanation() {
		return changeExplanation;
	}
	/**
	 * @return Returns the juvName.
	 */
	public Name getJuvName() {
		return juvName;
	}
	/**
	 * @param juvName The juvName to set.
	 */
	public void setJuvName(String firstName, String middleName, String lastName) {
		this.juvName = new Name(firstName, middleName, lastName) ;
	}
	/**
	 * @return Returns the juvSex.
	 */
	public String getjuvSex() {
		return juvSex;
	}
	/**
	 * @param juvSex The juvSex to set.
	 */
	public void setJuvSex(String juvSex) {
		this.juvSex = juvSex;
	}
	/**
	 * @return Returns the juvRace.
	 */
	public String getJuvRace() {
		return juvRace;
	}
	/**
	 * @param juvRace The juvRace to set.
	 */
	public void setJuvRace(String juvRace) {
		this.juvRace = juvRace;
	}
	
	/**
	 * @return Returns the age.
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age The age to set.
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * @return Returns the relocationDate.
	 */
	public Date getRelocationDate() {
		return relocationDate;
	}
	/**
	 * @param relocationDate The relocationDate to set.
	 */
	public void setRelocationDate(Date relocationDate) {
		
		this.relocationDate = relocationDate;
	}
	/**
	 * @return Returns the relocationTime.
	 */
	public String getRelocationTime() {
		return relocationTime;
	}
	/**
	 * @param relocationTime The relocationTime to set.
	 */
	public void setRelocationTime(String relocationTime) {
		this.relocationTime = relocationTime;
	}

	/**
	 * 
	 * @return Returns the releaseAuthorizedBy.
	 */
	public String getReleaseAuthorizedBy()
	{		
		return releaseAuthorizedBy;
	}

	/**
	 * 
	 * @param releaseAuthorizedBy The releaseAuthorizedBy to set.
	 */
	public void setReleaseAuthorizedBy(String releaseAuthorizedBy)
	{
		this.releaseAuthorizedBy = releaseAuthorizedBy;
	}
	
	/**
	 * 
	 * @return Returns the reasonDescription.
	 */
	public String getReasonDescription()
	{		
		return reasonDescription;
	}

	/**
	 * 
	 * @param reasonDescription The reasonDescription to set.
	 */
	public void setReasonDescription(String reasonDescription)
	{
		this.reasonDescription = reasonDescription;
	}
	
	/**
	 * 
	 * @return Returns the relAuthByDesc.
	 */
	public String getRelAuthByDesc()
	{		
		return relAuthByDesc;
	}

	/**
	 * 
	 * @param relAuthByDesc The relAuthByDesc to set.
	 */
	public void setRelAuthByDesc(String relAuthByDesc)
	{
		this.relAuthByDesc = relAuthByDesc;
	}
	/**
	 * 
	 * @return Returns the relToDesc.
	 */
	public String getRelToDesc()
	{		
		return relToDesc;
	}

	/**
	 * 
	 * @param relToDesc The relToDesc to set.
	 */
	public void setRelToDesc(String relToDesc)
	{
		this.relToDesc = relToDesc;
	}
	/**
	 * 
	 * @return Returns the relByDesc.
	 */
	public String getRelByDesc()
	{		
		return relByDesc;
	}

	/**
	 * 
	 * @param relByDesc The relByDesc to set.
	 */
	public void setRelByDesc(String relByDesc)
	{
		this.relByDesc = relByDesc;
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
	 * @return the saReasonOther
	 */
	public String getSaReasonOther() {
		return saReasonOther;
	}
	/**
	 * @param saReasonOther the saReasonOther to set
	 */
	public void setSaReasonOther(String saReasonOther) {
		this.saReasonOther = saReasonOther;
	}
	/**
	 * @return the admitAuthority
	 */
	public String getAdmitAuthority() {
		return admitAuthority;
	}
	/**
	 * @param admitAuthority the admitAuthority to set
	 */
	public void setAdmitAuthority(String admitAuthority) {
		this.admitAuthority = admitAuthority;
	}
	/**
	 * @return the juvSex
	 */
	public String getJuvSex() {
		return juvSex;
	}
	/**
	 * @param juvName the juvName to set
	 */
	public void setJuvName(Name juvName) {
		this.juvName = juvName;
	}
	/**
	 * @param specialAttention the specialAttention to set
	 */
	public void setSpecialAttention(String specialAttention) {
		this.specialAttention = specialAttention;
	}
	/**
	 * @return the specialAttention
	 */
	public String getSpecialAttention() {
		return specialAttention;
	}
	/**
	 * @param detentionStatus the detentionStatus to set
	 */
	public void setDetentionStatus(String detentionStatus) {
		this.detentionStatus = detentionStatus;
	}
	/**
	 * @return the detentionStatus
	 */
	public String getDetentionStatus() {
		return detentionStatus;
	}
	/**
	 * @param admittedByJPO the admittedByJPO to set
	 */
	public void setAdmittedByJPO(String admittedByJPO) {
		this.admittedByJPO = admittedByJPO;
	}
	/**
	 * @return the admittedByJPO
	 */
	public String getAdmittedByJPO() {
		return admittedByJPO;
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
	 * @param escapeAttempts the escapeAttempts to set
	 */
	public void setEscapeAttempts(String escapeAttempts) {
		this.escapeAttempts = escapeAttempts;
	}
	/**
	 * @return the escapeAttempts
	 */
	public String getEscapeAttempts() {
		return escapeAttempts;
	}
	/**
	 * @param escapeAttemptComments the escapeAttemptComments to set
	 */
	public void setEscapeAttemptComments(String escapeAttemptComments) {
		this.escapeAttemptComments = escapeAttemptComments;
	}
	/**
	 * @return the escapeAttemptComments
	 */
	public String getEscapeAttemptComments() {
		return escapeAttemptComments;
	}
	/**
	 * @return the tempReleaseReasonCd
	 */
	public String getTempReleaseReasonCd() {
		return tempReleaseReasonCd;
	}
	/**
	 * @param tempReleaseReasonCd the tempReleaseReasonCd to set
	 */
	public void setTempReleaseReasonCd(String tempReleaseReasonCd) {
		this.tempReleaseReasonCd = tempReleaseReasonCd;
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
	 * @param admitReasonDesc the admitReasonDesc to set
	 */
	public void setAdmitReasonDesc(String admitReasonDesc) {
		this.admitReasonDesc = admitReasonDesc;
	}
	/**
	 * @return the admitReasonDesc
	 */
	public String getAdmitReasonDesc() {
		
		return admitReasonDesc;
	}
	/**
	 * @param tempReleaseReasonCdDesc the tempReleaseReasonCdDesc to set
	 */
	public void setTempReleaseReasonCdDesc(String tempReleaseReasonCdDesc) {
		this.tempReleaseReasonCdDesc = tempReleaseReasonCdDesc;
	}
	/**
	 * @return the tempReleaseReasonCdDesc
	 */
	public String getTempReleaseReasonCdDesc() {
		return tempReleaseReasonCdDesc;
	}
	/**
	 * @param admittedByJPODesc the admittedByJPODesc to set
	 */
	public void setAdmittedByJPODesc(String admittedByJPODesc) {
		this.admittedByJPODesc = admittedByJPODesc;
	}
	/**
	 * @return the admittedByJPODesc
	 */
	public String getAdmittedByJPODesc() {
		return admittedByJPODesc;
	}
	/**
	 * @param transferToFacility the transferToFacility to set
	 */
	public void setTransferToFacility(String transferToFacility) {
		this.transferToFacility = transferToFacility;
	}
	/**
	 * @return the transferToFacility
	 */
	public String getTransferToFacility() {
		return transferToFacility;
	}
	/**
	 * @param detainedFacilityDesc the detainedFacilityDesc to set
	 */
	public void setDetainedFacilityDesc(String detainedFacilityDesc) {
		this.detainedFacilityDesc = detainedFacilityDesc;
	}
	/**
	 * @return the detainedFacilityDesc
	 */
	public String getDetainedFacilityDesc() {
		return detainedFacilityDesc;
	}

	/**
	 * @param escapeCodeDesc the escapeCodeDesc to set
	 */
	public void setEscapeCodeDesc(String escapeCodeDesc) {
		this.escapeCodeDesc = escapeCodeDesc;
	}

	/**
	 * @return the escapeCodeDesc
	 */
	public String getEscapeCodeDesc() {
		return escapeCodeDesc;
	}

	public String getTransferToFacilityDesc() {
		return transferToFacilityDesc;
	}

	public void setTransferToFacilityDesc(String transferToFacilityDesc) {
		this.transferToFacilityDesc = transferToFacilityDesc;
	}

	public String getOutcomeDesc() {
		return outcomeDesc;
	}

	public void setOutcomeDesc(String outcomeDesc) {
		this.outcomeDesc = outcomeDesc;
	}

	public String getSaReasonDesc() {
		return saReasonDesc;
	}

	public void setSaReasonDesc(String saReasonDesc) {
		this.saReasonDesc = saReasonDesc;
	}

	public String getCurrentOffenseDesc() {
		return currentOffenseDesc;
	}

	public void setCurrentOffenseDesc(String currentOffenseDesc) {
		this.currentOffenseDesc = currentOffenseDesc;
	}

	public String getMultipleOccupyUnit() {
		return multipleOccupyUnit;
	}

	public void setMultipleOccupyUnit(String multipleOccupyUnit) {
		this.multipleOccupyUnit = multipleOccupyUnit;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public boolean isOtherChangeFlag() {
		return otherChangeFlag;
	}

	public void setOtherChangeFlag(boolean otherChangeFlag) {
		this.otherChangeFlag = otherChangeFlag;
	}

	public boolean isLocationChangeFlag() {
		return locationChangeFlag;
	}

	public void setLocationChangeFlag(boolean locationChangeFlag) {
		this.locationChangeFlag = locationChangeFlag;
	}

	public boolean isReasonChangeFlag() {
		return reasonChangeFlag;
	}

	public void setReasonChangeFlag(boolean reasonChangeFlag) {
		this.reasonChangeFlag = reasonChangeFlag;
	}

	public boolean isSecureIndicatorChangeFlag() {
		return secureIndicatorChangeFlag;
	}

	public void setSecureIndicatorChangeFlag(boolean secureIndicatorChangeFlag) {
		this.secureIndicatorChangeFlag = secureIndicatorChangeFlag;
	}
	
	public String getReferralSource() {
		return referralSource;
	}

	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	public String getReferralOfficer() {
		return referralOfficer;
	}

	public void setReferralOfficer(String referralOfficer) {
		this.referralOfficer = referralOfficer;
	}

	public String getReferralSourceDesc() {
		return referralSourceDesc;
	}

	public void setReferralSourceDesc(String referralSourceDesc) {
		this.referralSourceDesc = referralSourceDesc;
	}

	public String getPetitionNum() {
		return petitionNum;
	}

	public void setPetitionNum(String petitionNum) {
		this.petitionNum = petitionNum;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public String getReleaseReasonDesc() {
		return releaseReasonDesc;
	}

	public void setReleaseReasonDesc(String releaseReasonDesc) {
		this.releaseReasonDesc = releaseReasonDesc;
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
	 * @return the facilityStatusDesc
	 */
	public String getFacilityStatusDesc() {
		return facilityStatusDesc;
	}

	/**
	 * @param facilityStatusDesc the facilityStatusDesc to set
	 */
	public void setFacilityStatusDesc(String facilityStatusDesc) {
		this.facilityStatusDesc = facilityStatusDesc;
	}

	/**
	 * @return the admitAuthorityDesc
	 */
	public String getAdmitAuthorityDesc() {
		return admitAuthorityDesc;
	}

	/**
	 * @param admitAuthorityDesc the admitAuthorityDesc to set
	 */
	public void setAdmitAuthorityDesc(String admitAuthorityDesc) {
		this.admitAuthorityDesc = admitAuthorityDesc;
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

	public String getAdmitDateStr() {
	    return admitDateStr;
	}

	public void setAdmitDateStr(String admitDateStr) {
	    this.admitDateStr = admitDateStr;
	}

	public String getReleaseDateStr() {
	    return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
	    this.releaseDateStr = releaseDateStr;
	}

	/**
	 * @return the facilityHistories
	 */
	public Collection<JuvenileDetentionFacilitiesResponseEvent> getFacilityHistories() {
		return facilityHistories;
	}

	/**
	 * @param facilityHistories the facilityHistories to set
	 */
	public void setFacilityHistories(Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistories) {
		this.facilityHistories = facilityHistories;
	}

	public String getLcTime() {
		return lcTime;
	}

	public void setLcTime(String lcTime) {
		this.lcTime = lcTime;
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
	 * @return the bookingOffenseCd
	 */
	public String getBookingOffenseCd() {
		return bookingOffenseCd;
	}

	/**
	 * @param bookingOffenseCd the bookingOffenseCd to set
	 */
	public void setBookingOffenseCd(String bookingOffenseCd) {
		this.bookingOffenseCd = bookingOffenseCd;
	}

	/**
	 * @return the bookingOffenseDesc
	 */
	public String getBookingOffenseDesc() {
		return bookingOffenseDesc;
	}

	/**
	 * @param bookingOffenseDesc the bookingOffenseDesc to set
	 */
	public void setBookingOffenseDesc(String bookingOffenseDesc) {
		this.bookingOffenseDesc = bookingOffenseDesc;
	}

	/**
	 * @return the updateFlag
	 */
	public String getUpdateFlag()
	{
	    return updateFlag;
	}

	/**
	 * @param updateFlag the updateFlag to set
	 */
	public void setUpdateFlag(String updateFlag)
	{
	    this.updateFlag = updateFlag;
	}
	/*public Collection<ActivityResponseEvent> getActivitiesByDetention()
	{
	    return activitiesByDetention;
	}

	public void setActivitiesByDetention(Collection<ActivityResponseEvent> activitiesByDetention)
	{
	    this.activitiesByDetention = activitiesByDetention;
	}*/
	public Collection getActivitiesByDetention()
	{
	    return activitiesByDetention;
	}

	public void setActivitiesByDetention(Collection activitiesByDetention)
	{
	    this.activitiesByDetention = activitiesByDetention;
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
	public Date getNexthearingDate()
	{
	    return nexthearingDate;
	}

	public void setNexthearingDate(Date nexthearingDate)
	{
	    this.nexthearingDate = nexthearingDate;
	}
	
	
	public String getOriginaladmitSEQNUM()
	{
	    return originaladmitSEQNUM;
	}

	public void setOriginaladmitSEQNUM(String originaladmitSEQNUM)
	{
	    this.originaladmitSEQNUM = originaladmitSEQNUM;
	}

	public static Comparator<JuvenileDetentionFacilitiesResponseEvent> DetentionRespEventComparator = new Comparator<JuvenileDetentionFacilitiesResponseEvent>() {
		public int compare(JuvenileDetentionFacilitiesResponseEvent evt1, JuvenileDetentionFacilitiesResponseEvent evt2) {
		  return Integer.valueOf(evt1.getFacilitySequenceNumber()).compareTo(Integer.valueOf(evt2.getFacilitySequenceNumber()));
		}	
	};
}
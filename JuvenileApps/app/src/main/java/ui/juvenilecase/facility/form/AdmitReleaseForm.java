package ui.juvenilecase.facility.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.ServiceDeliveryWithoutFeeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.facility.reply.JuvenileFacilityAdmissionCommentsResponseEvent;
import messaging.facility.reply.JuvenileFacilitySplAttnReasonResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.CodeHelper;
import ui.juvenilecase.form.TraitsForm;

public class AdmitReleaseForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String juvenileNum;
	private String facilityStatus;
	private String facilityStatusDesc;
	private String ethnicity;
	private String age;
	private String primaryLanguageDesc;

	// admit details
	private Date admitDate;
	private String admitDateStr;
	private String admitTime;
	private String admitAuthority;
	private String admitAuthorityDesc;
	private String admitBy;
	private String admitByDesc;
	
	private String detainedFacility;
	private String detainedFacilityStr;
	
	private String placementType;
	private String secureStatus;
	private String secureStatusDesc;
	
	private String bookingReferral;
	private String bookingOffense;
	private String bookingOffenseCd;
	private String bookingOffenseLevel;
	private String bookingSupervisionNum = "";
	private String bookingPetitionNum;
	
	private String referralSource;
	private String referralOfficers;
	
	private String valuablesReceipt;
	private String locker;
	private String reasonCode;
	
	private String admitReasonStr;
	private String admitReasonDetentionType;
	
	private Date originalAdmitDate;
	private String originalAdmitTime;
	
	private String detainedDate;
	//Bug #69605
	private String detainedByInd;
	private String facilitySeqNum;
	private int tjjdFacilityId;
	private String tjjdFundingSrc;
	
	private String currentReferral;
	private String currentOffense;
	private String currentOffenseCd;
	private String currentOffenseLevel;
	private String currentSupervisionNum = "";
	private String currentPetitionNum;
	private String severitySubType;
	

	private String admissionComments;
	private String floorLocation;
	private String unitLocation;
	private String roomLocation;
	private String lockerLocation;
	private boolean hideFacilityTraitsLink;

	private boolean locationChangeFlag;
	private boolean secureStatusChangeFlag;
	private boolean admitReasonChangeFlag;
	private boolean otherChangeFlag;
	
	private String reasonForChange;
	private String bookingRefPIAStatus;

	//Detention Hearing Information
	private String courtId;
	private String courtDesc;
	private String nextHearingDate;

	//labels for location within facility
	private String locationOneLabel;
	private String locationTwoLabel;
	private String locationThreeLabel;
	


	private String specialAttentionCd;
	private String specialAttentionDesc;
	private String saReason;
	private String saReasonStr;
	private String saReasonOtherComments;

	//Escape details
	private String escapeDate;
	private String escapeTime;
	private String escapeFrom;
	private String escapeFromDesc;
	private String escapeStatus;
	private String escapeEnteredBy;
	private String escapeComments;
	private String numOfEscapeAttempts;


	//temp release details
	private String tempReleaseReason;
	private String tempReleaseReasonDesc;
	private String tempReleaseReasonOther;
	private String tempReleaseReasonOtherComments;
	
	//release Information
	private String releaseDate;
	private String releaseAuthority;
	private String releaseAuthorityDesc;
	private String releasedBy;
	private String releasedTo;
	private String releasedToDesc;
	private String releaseReason;
	private String releaseReasonDesc;
	private String releaseTime;
	
	// modify admit flow
	private String petition;
	private String changeLabelCd;
	private String changeLabel;
	
	// return details
	private String returnDate;
	private String returnTime;
	private String returnStatus;
	private String returnComments;
	private String returnReason;
	private String returnReasonDesc;
	
	// referral Transfer
	private String refTransferMesg;
	
	//added for escape details:
	private String admitReasonCd;
	private String escapeAction;
	private String returnAction;
	private String releaseAction;
	private String tempReleaseAction;
	private String retTempReleaseAction;
	private String transferAction;
	private String referralTransferAction;
	
	// transfer details
	private String transferToFacility;
	private String transferToFacilityDesc;
	private String outcome;
	private String outcomeDesc;
	private String multipleOccupancyUnit;
	private String transferFromReferral;
	private String transferToReferral;
	
	//detention Id -OID added for special attention reason.//task #34820
	private String detentionId;

	private Collection facilities;
	private Collection guardians;
	private Collection admitReasons;
	private Collection specialAttentions;
	private Collection specialAttentionReasons;
	private Collection releaseAuthorities;
	private Collection releaseTo;
	private Collection casefiles;
	
	private Collection<CodeResponseEvent> outcomes;
	private Collection<ServiceDeliveryWithoutFeeResponseEvent> escapeCodes;
	private Collection<CodeResponseEvent> returnReasons;
	private Collection<CodeResponseEvent> releaseReasons;
	private Collection<CodeResponseEvent> tempReleaseReasons;
	private Collection<CodeResponseEvent> transferToFacilities;
	private Collection<JuvenileProfileReferralListResponseEvent> referrals;
	private Collection<JuvenileFacilitySplAttnReasonResponseEvent> splAttnReasonComments;
	private Collection<JuvenileFacilityAdmissionCommentsResponseEvent> facilityAdmissionComments; //added for u.s #51737
	
	private List<String> selectedSAReasonList = new ArrayList<String>();
	
	private boolean PIAStatusError;
	private String mostRecentCasefileId;

	// traits
	private Collection detentionTraitDescriptions;
	private TraitsForm detentionTrait = new TraitsForm();

	private String action;
	private String selectedValue;
	private boolean newAdmit;
	private String[] selectedSAReasons;
	private String selectedSA;
	private String selectedAdmitReason;
	
	private JuvenileProfileDetailResponseEvent profileDetail;
	private JuvenileDetentionFacilitiesResponseEvent detResp;
	private JuvenileFacilityHeaderResponseEvent headerInfo;
	private JuvenileAdmitReasonsResponseEvent admitReasonResp;
	
	private String bookingSeveritySubType;
	private String currentSeveritySubType;
	
	//added for User Story 31029
	private boolean restrictedAccessTrait;
	
	private String originalAdmitOID;//added for User-story #44549
    private boolean isResidentialCasefile; //added for #51734
    private Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList;//added for #51734
    
    	private String temporaryReleaseAllowed;
    	private String tempReleaseDecisionComments;
    	private String authorizingOfficer;
    	private String authorizingOfficerName;
    	private String tempReleaseDecisionAction;
    	private boolean tempRelDecisionEnabled;
	private boolean tempRelDecisionDistEnabled;
	
	private String vendorLocation;
	
	private String  custodylastName;
	private String  custodyfirstName;
	
	/**
	 * resets the list and flag attributes.
	 */
	public void clear() {
		this.action="";
		this.escapeAction ="";
		this.returnAction ="";
		this.releaseAction="";
		this.transferAction="";
		this.tempReleaseAction="";
		this.retTempReleaseAction="";
		this.referralTransferAction="";
		this.severitySubType="";
		this.facilityHistoryList= new ArrayList<JuvenileDetentionFacilitiesResponseEvent>();
		this.isResidentialCasefile=false;
		this.admissionComments=""; //added for 53113
		this.releaseAuthority="";
		this.releasedBy="";
		this.tempReleaseDecisionComments="";
		this.vendorLocation = null;
		this.custodyfirstName="";
		this.custodylastName="";
		
	}
	
	
	public String getSeveritySubType() {
		return severitySubType;
	}

	public void setSeveritySubType(String severitySubType) {
		this.severitySubType = severitySubType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// Data from codetable
	public Collection getMultipleOccupancyUnits() {
		return CodeHelper.getCodes(
				PDCodeTableConstants.MULTIPLE_OCCUPANCY_UNIT, true);
	}

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the bookingReferral
	 */
	public String getBookingReferral() {
		return bookingReferral;
	}

	/**
	 * @param bookingReferral
	 *            the bookingReferral to set
	 */
	public void setBookingReferral(String bookingReferral) {
		this.bookingReferral = bookingReferral;
	}

	/**
	 * @return the admitDate
	 */
	public Date getAdmitDate() {
		return admitDate;
	}

	/**
	 * @param admitDate
	 *            the admitDate to set
	 */
	public void setAdmitDate(Date admitDate) {
		this.admitDate = admitDate;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode
	 *            the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the secureStatus
	 */
	public String getSecureStatus() {
		return secureStatus;
	}

	/**
	 * @param secureStatus
	 *            the secureStatus to set
	 */
	public void setSecureStatus(String secureStatus) {
		this.secureStatus = secureStatus;
	}

	/**
	 * @return the admitTime
	 */
	public String getAdmitTime() {
		return admitTime;
	}

	/**
	 * @param admitTime
	 *            the admitTime to set
	 */
	public void setAdmitTime(String admitTime) {
		this.admitTime = admitTime;
	}

	/**
	 * @return the admitAuthority
	 */
	public String getAdmitAuthority() {
		return admitAuthority;
	}

	/**
	 * @param admitAuthority
	 *            the admitAuthority to set
	 */
	public void setAdmitAuthority(String admitAuthority) {
		this.admitAuthority = admitAuthority;
	}

	/**
	 * @return the admitBy
	 */
	public String getAdmitBy() {
		return admitBy;
	}

	/**
	 * @param admitBy
	 *            the admitBy to set
	 */
	public void setAdmitBy(String admitBy) {
		this.admitBy = admitBy;
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
	 * @return the bookingOffense
	 */
	public String getBookingOffense() {
		return bookingOffense;
	}

	/**
	 * @param bookingOffense
	 *            the bookingOffense to set
	 */
	public void setBookingOffense(String bookingOffense) {
		this.bookingOffense = bookingOffense;
	}

	/**
	 * @return the bookingSupervisionNum
	 */
	public String getBookingSupervisionNum() {
		return bookingSupervisionNum;
	}

	/**
	 * @param bookingSupervisionNum
	 *            the bookingSupervisionNum to set
	 */
	public void setBookingSupervisionNum(String bookingSupervisionNum) {
		this.bookingSupervisionNum = bookingSupervisionNum;
	}

	/**
	 * @return the referralSource
	 */
	public String getReferralSource() {
		return referralSource;
	}

	/**
	 * @param referralSource
	 *            the referralSource to set
	 */
	public void setReferralSource(String referralSource) {
		this.referralSource = referralSource;
	}

	/**
	 * @return the referralOfficers
	 */
	public String getReferralOfficers() {
		return referralOfficers;
	}

	/**
	 * @param referralOfficers
	 *            the referralOfficers to set
	 */
	public void setReferralOfficers(String referralOfficers) {
		this.referralOfficers = referralOfficers;
	}

	/**
	 * @return the valuablesReceipt
	 */
	public String getValuablesReceipt() {
		return valuablesReceipt;
	}

	/**
	 * @param valuablesReceipt
	 *            the valuablesReceipt to set
	 */
	public void setValuablesReceipt(String valuablesReceipt) {
		this.valuablesReceipt = valuablesReceipt;
	}

	public void setLocker(String locker) {
		this.locker = locker;
	}

	public String getLocker() {
		return locker;
	}

	public void setOriginalAdmitDate(Date originalAdmitDate) {
		this.originalAdmitDate = originalAdmitDate;
	}

	public Date getOriginalAdmitDate() {
		return originalAdmitDate;
	}

	public void setDetainedDate(String detainedDate) {
		this.detainedDate = detainedDate;
	}

	public String getDetainedDate() {
		return detainedDate;
	}

	public void setFacilitySeqNum(String facilitySeqNum) {
		this.facilitySeqNum = facilitySeqNum;
	}

	public String getFacilitySeqNum() {
		return facilitySeqNum;
	}

	public void setCurrentReferral(String currentReferral) {
		this.currentReferral = currentReferral;
	}

	public String getCurrentReferral() {
		return currentReferral;
	}

	public void setCurrentOffense(String currentOffense) {
		this.currentOffense = currentOffense;
	}

	public String getCurrentOffense() {
		return currentOffense;
	}

	public void setCurrentSupervisionNum(String currentSupervisionNum) {
		this.currentSupervisionNum = currentSupervisionNum;
	}

	public String getCurrentSupervisionNum() {
		return currentSupervisionNum;
	}

	public void setAdmissionComments(String admissionComments) {
		this.admissionComments = admissionComments;
	}

	public String getAdmissionComments() {
		return admissionComments;
	}

	public void setFloorLocation(String floorLocation) {
		this.floorLocation = floorLocation;
	}

	public String getFloorLocation() {
		return floorLocation;
	}

	public void setUnitLocation(String unitLocation) {
		this.unitLocation = unitLocation;
	}

	public String getUnitLocation() {
		return unitLocation;
	}

	public void setRoomLocation(String roomLocation) {
		this.roomLocation = roomLocation;
	}

	public String getRoomLocation() {
		return roomLocation;
	}

	public void setLocationChangeFlag(boolean locationChangeFlag) {
		this.locationChangeFlag = locationChangeFlag;
	}

	public boolean isLocationChangeFlag() {
		return locationChangeFlag;
	}

	public void setSecureStatusChangeFlag(boolean secureStatusChangeFlag) {
		this.secureStatusChangeFlag = secureStatusChangeFlag;
	}

	public boolean isSecureStatusChangeFlag() {
		return secureStatusChangeFlag;
	}

	public void setAdmitReasonChangeFlag(boolean admitReasonChangeFlag) {
		this.admitReasonChangeFlag = admitReasonChangeFlag;
	}

	public boolean isAdmitReasonChangeFlag() {
		return admitReasonChangeFlag;
	}

	public void setEscapeDate(String escapeDate) {
		this.escapeDate = escapeDate;
	}

	public String getEscapeDate() {
		return escapeDate;
	}

	public void setEscapeFrom(String escapeFrom) {
		this.escapeFrom = escapeFrom;
	}

	public String getEscapeFrom() {
		return escapeFrom;
	}

	public void setEscapeStatus(String escapeStatus) {
		this.escapeStatus = escapeStatus;
	}

	public String getEscapeStatus() {
		return escapeStatus;
	}

	public void setEscapeEnteredBy(String escapeEnteredBy) {
		this.escapeEnteredBy = escapeEnteredBy;
	}

	public String getEscapeEnteredBy() {
		return escapeEnteredBy;
	}

	public void setEscapeComments(String escapeComments) {
		this.escapeComments = escapeComments;
	}

	public String getEscapeComments() {
		return escapeComments;
	}

	public void setNumOfEscapeAttempts(String numOfEscapeAttempts) {
		this.numOfEscapeAttempts = numOfEscapeAttempts;
	}

	public String getNumOfEscapeAttempts() {
		return numOfEscapeAttempts;
	}

	public void setTempReleaseReason(String tempReleaseReason) {
		this.tempReleaseReason = tempReleaseReason;
	}

	public String getTempReleaseReason() {
		return tempReleaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setTempReleaseReasonOther(String tempReleaseReasonOther) {
		this.tempReleaseReasonOther = tempReleaseReasonOther;
	}

	public String getTempReleaseReasonOther() {
		return tempReleaseReasonOther;
	}

	public void setTempReleaseReasonOtherComments(
			String tempReleaseReasonOtherComments) {
		this.tempReleaseReasonOtherComments = tempReleaseReasonOtherComments;
	}

	public String getTempReleaseReasonOtherComments() {
		return tempReleaseReasonOtherComments;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseAuthority(String releaseAuthority) {
		this.releaseAuthority = releaseAuthority;
	}

	public String getReleaseAuthority() {
		return releaseAuthority;
	}

	public void setReleasedBy(String releasedBy) {
		this.releasedBy = releasedBy;
	}

	public String getReleasedBy() {
		return releasedBy;
	}

	public void setReleasedTo(String releasedTo) {
		this.releasedTo = releasedTo;
	}

	public String getReleasedTo() {
		return releasedTo;
	}

	public void setTransferToFacility(String transferToFacility) {
		this.transferToFacility = transferToFacility;
	}

	public String getTransferToFacility() {
		return transferToFacility;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setFacilities(Collection facilities) {
		this.facilities = facilities;
	}

	public Collection getFacilities() {
		return facilities;
	}

	public void setBookingPetitionNum(String bookingPetitionNum) {
		this.bookingPetitionNum = bookingPetitionNum;
	}

	public String getBookingPetitionNum() {
		return bookingPetitionNum;
	}

	public void setCurrentPetitionNum(String currentPetitionNum) {
		this.currentPetitionNum = currentPetitionNum;
	}

	public String getCurrentPetitionNum() {
		return currentPetitionNum;
	}

	public void setProfileDetail(
			JuvenileProfileDetailResponseEvent profileDetail) {
		this.profileDetail = profileDetail;
	}

	public JuvenileProfileDetailResponseEvent getProfileDetail() {
		return profileDetail;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setGuardians(Collection guardians) {
		this.guardians = guardians;
	}

	public Collection getGuardians() {
		return guardians;
	}

	public void setSpecialAttentionCd(String specialAttentionCd) {
		this.specialAttentionCd = specialAttentionCd;
	}

	public String getSpecialAttentionCd() {
		return specialAttentionCd;
	}

	public void setSaReason(String saReason) {
		this.saReason = saReason;
	}

	public String getSaReason() {
		return saReason;
	}

	public void setSaReasonOtherComments(String saReasonOtherComments) {
		this.saReasonOtherComments = saReasonOtherComments;
	}

	public String getSaReasonOtherComments() {
		return saReasonOtherComments;
	}

	public void setAdmitReasons(Collection admitReasons) {
		this.admitReasons = admitReasons;
	}

	public Collection getAdmitReasons() {
		return admitReasons;
	}

	public void setAdmitDateStr(String admitDateStr) {
		this.admitDateStr = admitDateStr;
	}

	public String getAdmitDateStr() {
		return admitDateStr;
	}

	public void setDetainedFacilityStr(String detainedFacilityStr) {
		this.detainedFacilityStr = detainedFacilityStr;
	}

	public String getDetainedFacilityStr() {
		return detainedFacilityStr;
	}

	public void setAdmitReasonStr(String admitReasonStr) {
		this.admitReasonStr = admitReasonStr;
	}

	public String getAdmitReasonStr() {
		return admitReasonStr;
	}

	public void setDetentionTrait(TraitsForm detentionTrait) {
		this.detentionTrait = detentionTrait;
	}

	public TraitsForm getDetentionTrait() {
		return detentionTrait;
	}

	public void setSpecialAttentionReasons(Collection specialAttentionReasons) {
		this.specialAttentionReasons = specialAttentionReasons;
	}

	public Collection getSpecialAttentionReasons() {
		return specialAttentionReasons;
	}

	public void setDetentionTraitDescriptions(
			Collection detentionTraitDescriptions) {
		this.detentionTraitDescriptions = detentionTraitDescriptions;
	}

	public Collection getDetentionTraitDescriptions() {
		return detentionTraitDescriptions;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setBookingRefPIAStatus(String bookingRefPIAStatus) {
		this.bookingRefPIAStatus = bookingRefPIAStatus;
	}

	public String getBookingRefPIAStatus() {
		return bookingRefPIAStatus;
	}

	/**
	 * @param admitReasonDetentionType
	 *            the admitReasonDetentionType to set
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
	 * @param bookingOffenseCd
	 *            the bookingOffenseCd to set
	 */
	public void setBookingOffenseCd(String bookingOffenseCd) {
		this.bookingOffenseCd = bookingOffenseCd;
	}

	/**
	 * @return the bookingOffenseCd
	 */
	public String getBookingOffenseCd() {
		return bookingOffenseCd;
	}

	/**
	 * @param currentOffenseCd
	 *            the currentOffenseCd to set
	 */
	public void setCurrentOffenseCd(String currentOffenseCd) {
		this.currentOffenseCd = currentOffenseCd;
	}

	/**
	 * @return the currentOffenseCd
	 */
	public String getCurrentOffenseCd() {
		return currentOffenseCd;
	}

	/**
	 * @param saReasonStr
	 *            the saReasonStr to set
	 */
	public void setSaReasonStr(String saReasonStr) {
		this.saReasonStr = saReasonStr;
	}

	/**
	 * @param saReasonStr
	 *            the saReasonStr to set
	 */
	public void setSaReasonStr(List selectedSAReasonCodes) {
		// this.saReasonStr = saReasonStr;
		StringBuffer buf = new StringBuffer();
		if (selectedSAReasonCodes != null) {
			for (int i = 0; i < selectedSAReasonCodes.size(); i++) {
				buf.append(selectedSAReasonCodes.get(i));
				if (i != selectedSAReasonCodes.size() - 1) {
					buf.append(",");
				}
			}
			this.saReasonStr = buf.toString();
		} else
			this.saReasonStr = "";
	}

	/**
	 * @return the saReasonStr
	 */
	public String getSaReasonStr() {
		return saReasonStr;
	}

	public void setOriginalAdmitTime(String originalAdmitTime) {
		this.originalAdmitTime = originalAdmitTime;
	}

	public String getOriginalAdmitTime() {
		return originalAdmitTime;
	}

	public void setLocationOneLabel(String locationOneLabel) {
		this.locationOneLabel = locationOneLabel;
	}

	public String getLocationOneLabel() {
		return locationOneLabel;
	}

	public void setLocationTwoLabel(String locationTwoLabel) {
		this.locationTwoLabel = locationTwoLabel;
	}

	public String getLocationTwoLabel() {
		return locationTwoLabel;
	}

	public void setLocationThreeLabel(String locationThreeLabel) {
		this.locationThreeLabel = locationThreeLabel;
	}

	public String getLocationThreeLabel() {
		return locationThreeLabel;
	}

	public void setReferrals(Collection<JuvenileProfileReferralListResponseEvent> referrals) {
		this.referrals = referrals;
	}

	public Collection<JuvenileProfileReferralListResponseEvent> getReferrals() {
		return referrals;
	}

	public boolean isNewAdmit() {
		return newAdmit;
	}

	public void setNewAdmit(boolean newAdmit) {
		this.newAdmit = newAdmit;
	}

	public String getMultipleOccupancyUnit() {
		return multipleOccupancyUnit;
	}

	public void setMultipleOccupancyUnit(String multipleOccupancyUnit) {
		this.multipleOccupancyUnit = multipleOccupancyUnit;
	}

	public String getPlacementType() {
		return placementType;
	}

	public void setPlacementType(String placementType) {
		this.placementType = placementType;
	}

	public String getPrimaryLanguageDesc() {
		return primaryLanguageDesc;
	}

	public void setPrimaryLanguageDesc(String primaryLanguageDesc) {
		this.primaryLanguageDesc = primaryLanguageDesc;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getCourtDesc() {
		return courtDesc;
	}

	public void setCourtDesc(String courtDesc) {
		this.courtDesc = courtDesc;
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public String getNextHearingDate() {
		return nextHearingDate;
	}

	public void setNextHearingDate(String nextHearingDate) {
		this.nextHearingDate = nextHearingDate;
	}

	public String getPetition() {
		return petition;
	}

	public void setPetition(String petition) {
		this.petition = petition;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
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

	public String getReturnComments() {
		return returnComments;
	}

	public void setReturnComments(String returnComments) {
		this.returnComments = returnComments;
	}

	public String[] getSelectedSAReasons() {
		return selectedSAReasons;
	}

	public void setSelectedSAReasons(String[] selectedSAReasons) {
		this.selectedSAReasons = selectedSAReasons;
	}

	public Collection getReleaseAuthorities() {
		return releaseAuthorities;
	}

	public void setReleaseAuthorities(Collection releaseAuthorities) {
		this.releaseAuthorities = releaseAuthorities;
	}

	public Collection getReleaseTo() {
		return releaseTo;
	}

	public void setReleaseTo(Collection releaseTo) {
		this.releaseTo = releaseTo;
	}

	public boolean isOtherChangeFlag() {
		return otherChangeFlag;
	}

	public void setOtherChangeFlag(boolean otherChangeFlag) {
		this.otherChangeFlag = otherChangeFlag;
	}

	public String getChangeLabelCd() {
		return changeLabelCd;
	}

	public void setChangeLabelCd(String changeLabelCd) {
		this.changeLabelCd = changeLabelCd;
	}

	public String getChangeLabel() {
		return changeLabel;
	}

	public void setChangeLabel(String changeLabel) {
		this.changeLabel = changeLabel;
	}

	public String getEscapeTime() {
		return escapeTime;
	}

	public void setEscapeTime(String escapeTime) {
		this.escapeTime = escapeTime;
	}

	public String getReasonForChange() {
		return reasonForChange;
	}

	public void setReasonForChange(String reasonForChange) {
		this.reasonForChange = reasonForChange;
	}

	public List<String> getSelectedSAReasonList() {
		return selectedSAReasonList;
	}

	public void setSelectedSAReasonList(List<String> selectedSAReasonList) {
		this.selectedSAReasonList = selectedSAReasonList;
	}

	public String getReleaseAuthorityDesc() {
		return releaseAuthorityDesc;
	}

	public void setReleaseAuthorityDesc(String releaseAuthorityDesc) {
		this.releaseAuthorityDesc = releaseAuthorityDesc;
	}

	public String getReleasedToDesc() {
		return releasedToDesc;
	}

	public void setReleasedToDesc(String releasedToDesc) {
		this.releasedToDesc = releasedToDesc;
	}

	public String getOutcomeDesc() {
		return outcomeDesc;
	}

	public void setOutcomeDesc(String outcomeDesc) {
		this.outcomeDesc = outcomeDesc;
	}

	public String getSecureStatusDesc() {
		return secureStatusDesc;
	}

	public void setSecureStatusDesc(String secureStatusDesc) {
		this.secureStatusDesc = secureStatusDesc;
	}

	public boolean isHideFacilityTraitsLink() {
		return hideFacilityTraitsLink;
	}

	public void setHideFacilityTraitsLink(boolean hideFacilityTraitsLink) {
		this.hideFacilityTraitsLink = hideFacilityTraitsLink;
	}

	public boolean isPIAStatusError() {
		return PIAStatusError;
	}

	public void setPIAStatusError(boolean pIAStatusError) {
		PIAStatusError = pIAStatusError;
	}

	public String getMostRecentCasefileId() {
		return mostRecentCasefileId;
	}

	public void setMostRecentCasefileId(String mostRecentCasefileId) {
		this.mostRecentCasefileId = mostRecentCasefileId;
	}

	public Collection getCasefiles() {
		return casefiles;
	}

	public void setCasefiles(Collection casefiles) {
		this.casefiles = casefiles;
	}

	public JuvenileDetentionFacilitiesResponseEvent getDetResp() {
		return detResp;
	}

	public void setDetResp(JuvenileDetentionFacilitiesResponseEvent detResp) {
		this.detResp = detResp;
	}

	public String getSpecialAttentionDesc() {
		return specialAttentionDesc;
	}

	public void setSpecialAttentionDesc(String specialAttentionDesc) {
		this.specialAttentionDesc = specialAttentionDesc;
	}

	public JuvenileAdmitReasonsResponseEvent getAdmitReasonResp() {
		return admitReasonResp;
	}

	public void setAdmitReasonResp(JuvenileAdmitReasonsResponseEvent admitReasonResp) {
		this.admitReasonResp = admitReasonResp;
	}

	public String getEscapeFromDesc() {
		return escapeFromDesc;
	}

	public void setEscapeFromDesc(String escapeFromDesc) {
		this.escapeFromDesc = escapeFromDesc;
	}

	public Collection<ServiceDeliveryWithoutFeeResponseEvent> getEscapeCodes() {
		return escapeCodes;
	}

	public void setEscapeCodes(Collection<ServiceDeliveryWithoutFeeResponseEvent> escapeCodes) {
		this.escapeCodes = escapeCodes;
	}

	public JuvenileFacilityHeaderResponseEvent getHeaderInfo() {
		return headerInfo;
	}

	public void setHeaderInfo(JuvenileFacilityHeaderResponseEvent headerInfo) {
		this.headerInfo = headerInfo;
	}

	public String getAdmitReasonCd() {
		return admitReasonCd;
	}

	public void setAdmitReasonCd(String admitReasonCd) {
		this.admitReasonCd = admitReasonCd;
	}

	public String getEscapeAction() {
		return escapeAction;
	}

	public void setEscapeAction(String escapeAction) {
		this.escapeAction = escapeAction;
	}
	public String getReturnReasonDesc() {
		return returnReasonDesc;
	}

	public void setReturnReasonDesc(String returnReasonDesc) {
		this.returnReasonDesc = returnReasonDesc;
	}

	public String getReturnAction() {
		return returnAction;
	}

	public void setReturnAction(String returnAction) {
		this.returnAction = returnAction;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public Collection<CodeResponseEvent> getReturnReasons() {
		return returnReasons;
	}

	public void setReturnReasons(Collection<CodeResponseEvent> returnReasons) {
		this.returnReasons = returnReasons;
	}

	public Collection<CodeResponseEvent> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(Collection<CodeResponseEvent> outcomes) {
		this.outcomes = outcomes;
	}

	public String getReleaseAction() {
		return releaseAction;
	}

	public void setReleaseAction(String releaseAction) {
		this.releaseAction = releaseAction;
	}

	public Collection<CodeResponseEvent> getReleaseReasons() {
		return releaseReasons;
	}

	public void setReleaseReasons(Collection<CodeResponseEvent> releaseReasons) {
		this.releaseReasons = releaseReasons;
	}

	public String getReleaseReasonDesc() {
		return releaseReasonDesc;
	}

	public void setReleaseReasonDesc(String releaseReasonDesc) {
		this.releaseReasonDesc = releaseReasonDesc;
	}
	public Collection<CodeResponseEvent> getTempReleaseReasons() {
		return tempReleaseReasons;
	}

	public void setTempReleaseReasons(
			Collection<CodeResponseEvent> tempReleaseReasons) {
		this.tempReleaseReasons = tempReleaseReasons;
	}

	public String getTempReleaseReasonDesc() {
		return tempReleaseReasonDesc;
	}

	public void setTempReleaseReasonDesc(String tempReleaseReasonDesc) {
		this.tempReleaseReasonDesc = tempReleaseReasonDesc;
	}

	/**
	 * @return the tempReleaseAction
	 */
	public String getTempReleaseAction() {
		return tempReleaseAction;
	}

	/**
	 * @param tempReleaseAction the tempReleaseAction to set
	 */
	public void setTempReleaseAction(String tempReleaseAction) {
		this.tempReleaseAction = tempReleaseAction;
	}

	/**
	 * @return the retTempReleaseAction
	 */
	public String getRetTempReleaseAction() {
		return retTempReleaseAction;
	}

	/**
	 * @param retTempReleaseAction the retTempReleaseAction to set
	 */
	public void setRetTempReleaseAction(String retTempReleaseAction) {
		this.retTempReleaseAction = retTempReleaseAction;
	}

	/**
	 * @return the transferToFacilities
	 */
	public Collection<CodeResponseEvent> getTransferToFacilities() {
		return transferToFacilities;
	}

	/**
	 * @param transferToFacilities the transferToFacilities to set
	 */
	public void setTransferToFacilities(Collection<CodeResponseEvent> transferToFacilities) {
		this.transferToFacilities = transferToFacilities;
	}

	/**
	 * @return the transferToFacilityDesc
	 */
	public String getTransferToFacilityDesc() {
		return transferToFacilityDesc;
	}

	/**
	 * @param transferToFacilityDesc the transferToFacilityDesc to set
	 */
	public void setTransferToFacilityDesc(String transferToFacilityDesc) {
		this.transferToFacilityDesc = transferToFacilityDesc;
	}

	/**
	 * @return the transferAction
	 */
	public String getTransferAction() {
		return transferAction;
	}

	/**
	 * @param transferAction the transferAction to set
	 */
	public void setTransferAction(String transferAction) {
		this.transferAction = transferAction;
	}

	/**
	 * @return the refTransferMesg
	 */
	public String getRefTransferMesg() {
		return refTransferMesg;
	}

	/**
	 * @param refTransferMesg the refTransferMesg to set
	 */
	public void setRefTransferMesg(String refTransferMesg) {
		this.refTransferMesg = refTransferMesg;
	}

	/**
	 * @return the bookingOffenseLevel
	 */
	public String getBookingOffenseLevel() {
		return bookingOffenseLevel;
	}

	/**
	 * @param bookingOffenseLevel the bookingOffenseLevel to set
	 */
	public void setBookingOffenseLevel(String bookingOffenseLevel) {
		this.bookingOffenseLevel = bookingOffenseLevel;
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
	 * @return the referralTransferAction
	 */
	public String getReferralTransferAction() {
		return referralTransferAction;
	}

	/**
	 * @param referralTransferAction the referralTransferAction to set
	 */
	public void setReferralTransferAction(String referralTransferAction) {
		this.referralTransferAction = referralTransferAction;
	}


	public boolean isRestrictedAccessTrait() {
		return restrictedAccessTrait;
	}


	public void setRestrictedAccessTrait(boolean restrictedAccessTrait) {
		this.restrictedAccessTrait = restrictedAccessTrait;
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
	 * @return the splAttnReasonComments
	 */
	public Collection<JuvenileFacilitySplAttnReasonResponseEvent> getSplAttnReasonComments() {
		return splAttnReasonComments;
	}


	/**
	 * @param splAttnReasonComments the splAttnReasonComments to set
	 */
	public void setSplAttnReasonComments(Collection<JuvenileFacilitySplAttnReasonResponseEvent> splAttnReasonComments) {
		this.splAttnReasonComments = splAttnReasonComments;
	}


	public String getBookingSeveritySubType() {
		return bookingSeveritySubType;
	}


	public void setBookingSeveritySubType(String bookingSeveritySubType) {
		this.bookingSeveritySubType = bookingSeveritySubType;
	}


	public String getCurrentSeveritySubType() {
		return currentSeveritySubType;
	}


	public void setCurrentSeveritySubType(String currentSeveritySubType) {
		this.currentSeveritySubType = currentSeveritySubType;
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
	 * @return the specialAttentions
	 */
	public Collection getSpecialAttentions() {
		return specialAttentions;
	}


	/**
	 * @param specialAttentions the specialAttentions to set
	 */
	public void setSpecialAttentions(Collection specialAttentions) {
		this.specialAttentions = specialAttentions;
	}


	/**
	 * @return the selectedSA
	 */
	public String getSelectedSA() {
		return selectedSA;
	}


	/**
	 * @param selectedSA the selectedSA to set
	 */
	public void setSelectedSA(String selectedSA) {
		this.selectedSA = selectedSA;
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
	 * @return the selectedAdmitReason
	 */
	public String getSelectedAdmitReason() {
		return selectedAdmitReason;
	}


	/**
	 * @param selectedAdmitReason the selectedAdmitReason to set
	 */
	public void setSelectedAdmitReason(String selectedAdmitReason) {
		this.selectedAdmitReason = selectedAdmitReason;
	}


	/**
	 * @return the facilityHistoryList
	 */
	public Collection<JuvenileDetentionFacilitiesResponseEvent> getFacilityHistoryList() {
		return facilityHistoryList;
	}


	/**
	 * @param facilityHistoryList the facilityHistoryList to set
	 */
	public void setFacilityHistoryList(Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList) {
		this.facilityHistoryList = facilityHistoryList;
	}

	public String getIsResidentialCasefile() {
		return selectedAdmitReason;
	}

	/**
	 * @return the isResidentialCasefile
	 */
	public boolean isResidentialCasefile() {
		return isResidentialCasefile;
	}


	/**
	 * @param isResidentialCasefile the isResidentialCasefile to set
	 */
	public void setResidentialCasefile(boolean isResidentialCasefile) {
		this.isResidentialCasefile = isResidentialCasefile;
	}


	/**
	 * @return the facilityAdmissionComments
	 */
	public Collection<JuvenileFacilityAdmissionCommentsResponseEvent> getFacilityAdmissionComments() {
		return facilityAdmissionComments;
	}


	/**
	 * @param facilityAdmissionComments the facilityAdmissionComments to set
	 */
	public void setFacilityAdmissionComments(
			Collection<JuvenileFacilityAdmissionCommentsResponseEvent> facilityAdmissionComments) {
		this.facilityAdmissionComments = facilityAdmissionComments;
	}


	/**
	 * @return the admitByDesc
	 */
	public String getAdmitByDesc() {
		return admitByDesc;
	}


	/**
	 * @param admitByDesc the admitByDesc to set
	 */
	public void setAdmitByDesc(String admitByDesc) {
		this.admitByDesc = admitByDesc;
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


	public String getTemporaryReleaseAllowed()
	{
	    return temporaryReleaseAllowed;
	}


	public void setTemporaryReleaseAllowed(String temporaryReleaseAllowed)
	{
	    this.temporaryReleaseAllowed = temporaryReleaseAllowed;
	}


	public String getTempReleaseDecisionComments()
	{
	    return tempReleaseDecisionComments;
	}


	public void setTempReleaseDecisionComments(String tempReleaseDecisionComments)
	{
	    this.tempReleaseDecisionComments = tempReleaseDecisionComments;
	}


	public String getAuthorizingOfficer()
	{
	    return authorizingOfficer;
	}


	public void setAuthorizingOfficer(String authorizingOfficer)
	{
	    this.authorizingOfficer = authorizingOfficer;
	}


	public String getTempReleaseDecisionAction()
	{
	    return tempReleaseDecisionAction;
	}


	public void setTempReleaseDecisionAction(String tempReleaseDecisionAction)
	{
	    this.tempReleaseDecisionAction = tempReleaseDecisionAction;
	}


	public String getAuthorizingOfficerName()
	{
	      return authorizingOfficerName;
	}


	public void setAuthorizingOfficerName(String authorizingOfficerName)
	{
	    this.authorizingOfficerName = authorizingOfficerName;
	}
	//task 104028
	
	
	public String getLockerLocation()
	{
	    return lockerLocation;
	}


	public void setLockerLocation(String lockerLocation)
	{
	    this.lockerLocation = lockerLocation;
	}
	//


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
	public boolean isTempRelDecisionDistEnabled()
	{
	    return tempRelDecisionDistEnabled;
	}

	public void setTempRelDecisionDistEnabled(boolean tempRelDecisionDistEnabled)
	{
	    this.tempRelDecisionDistEnabled = tempRelDecisionDistEnabled;
	}
	public boolean isTempRelDecisionEnabled()
	{
	    return tempRelDecisionEnabled;
	}

	public void setTempRelDecisionEnabled(boolean tempRelDecisionEnabled)
	{
	    this.tempRelDecisionEnabled = tempRelDecisionEnabled;
	}
	
	public String getVendorLocation() {
		return vendorLocation;
	}

	public void setVendorLocation(String vendorLocation) {
		this.vendorLocation = vendorLocation;
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

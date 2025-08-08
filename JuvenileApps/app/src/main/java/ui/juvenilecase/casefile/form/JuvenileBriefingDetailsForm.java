package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import messaging.juvenile.reply.JuvenileDetentionVisitResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.referral.reply.JuvenileBehaviorHistoryResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.Address;
import ui.common.CodeHelper;
import ui.juvenilecase.form.JuvenileMemberForm;

public class JuvenileBriefingDetailsForm extends ActionForm
{
	// used to set a Collection to the empty set.
	private static Collection emptyColl = new ArrayList( ) ;

	private JuvenileProfileDetailResponseEvent profileDetail ;

	private String hispanic ;
	private String isUsCitizen;
	private String multiracial ;
	private String ethnicity ;
	private String memberNum;

	private String naturalEyeColor ;
	private String naturalHairColor ;

	private String age ;

	private String juvenileNum ;
	private String supervisionNum ;

	private String fromProfile ;
	private JuvenileCasefileResponseEvent currentCasefile ;

	private JuvenilePhysicalAttributesResponseEvent physicalAttribute ;

	private JuvenileSchoolHistoryResponseEvent school ;

	private Address memberAddress ;
	private JuvenileMemberForm.MemberContact memberContact ;

	private Collection guardians ;
	private FamilyMemberDetailResponseEvent memberDetail ;
	
	private Collection familyMembers ;

	private Collection casefiles ;
	
	private JuvenileTraitResponseEvent currentTrait ;
	private Collection traits ;

	private JuvenileBehaviorHistoryResponseEvent behaviorHistory ;

	private JuvenileWarrantResponseEvent warrant ;

	private HashSet referralNums ;

	private String action = "" ;

	private boolean inHomeStatus = false ;
	
	private boolean inTrackerProgram = false;
	
	private boolean inSpecialtyCourt = false;
	
	private boolean inMentalHealthServices = false;
	
	private boolean hasOnly1010Referral = false;
	
	private String dualStatus = "";
	
	private String medicalHold = "";

	private String officerAlert;
	
	private String holdTrait = "";
	
	private String holdTraitDescription="";
	private String specialtyCourtDescription="";
	
	//added for US 31029 - if all casefiles of a Juv are closed, display Resticted Access
	
	private boolean restrictedAccessTrait;
	
	private String juvProgramCode = null;
	private String juvProgramDescription = null;
	
	/**---------------------------ADDED FOR FACILITY BRIEFING PAGE------------------------------------------**/
	private boolean hasPendingCasefiles;
	private boolean hasActiveOrClosingPendingCasefiles; //added for Referrals US 71171
	private boolean hasActiveCasefiles;
	private boolean hasCasefiles;
	private boolean hasPostAdjCatCasefile;
	private boolean hasOpenReferrals=true;
	// added for US 101939
	private String tempReleaseRequestDecision="i";
	private String tempDistReleaseRequestDecision="i";
	//
	private String isCourtSettingToday;
	private String isDistCourtSettingToday;
	private boolean tempRelDecisionEnabled;
	private boolean tempRelDecisionDistEnabled;
	private boolean hideFacilityTraitsLink;
	private Collection referrals;
	private String bookingReferral;
	private String bookingOffenseCode; 
	private String bookingOffenseCodeDesc;
	private String bookingSupervisionNum;
	private String bookingOffenseLevel;
	private String currentReferral;
	private String currentOffenseCode;	
	private String currentSupervisionNum;
	private String facilityStatus;
	private String releaseTo;
	private String primaryLanguageDesc;
	private boolean escapeRisk;
	private String refTransferMesg;
	
	//Bug #69605
	private Date detainedDate;
	private String detainedByInd;
	
	private JuvenileDetentionFacilitiesResponseEvent admissionInfo;
	private Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListDetails;
	private Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacList;
	private JuvenileFacilityHeaderResponseEvent headerInfo;
	
	//Added for US #37931 - JIS Information
	
	private JuvenileJISResponseEvent jisInfo = new JuvenileJISResponseEvent();
	
	//pact attributes task 41028 .
    private String pactAuthKey;
    private String userId;
    //# task 44099 . U.S #41378
    private String pactServerName; 
    //added for task #43956
    private String riskLvl;
    private String needsLvl;
    private String lastPactDate;
    private String pactApplicationName; //bug #46110
    
    //User Story 43116
    private Collection<JuvenileDetentionVisitResponseEvent> detVisits;
    private boolean detVisitBanned;
    
    //User Story 70421 (Referrals Conversion)
    private String juvAddress;
    private String juvCounty;
    private String juvSchoolDist;
    private String juvSchoolName;
    private Address guardianAddress;
    //only for migrated Records
    private String fathersName;
    private String fathersSSN;
    private String fathersAddress;
    private String fathersPhone;
    private String mothersName;
    private String mothersSSN;
    private String mothersAddress;
    private String mothersPhone;
    private String otherRelName;
    private String otherRelSSN;
    private String otherRelAddress;
    private String otherRelPhone;
    private String purgeDate;
    private String purgeSerNum;
    private String purgeBoxNum;
    private String checkedOutTo;
    
    private String deathDate;
    private int deathAge;
    private Collection causesOfDeath;
    private Collection deathVerficationCodes;
    private String youthDeathReason;
    private String youthDeathReasonDesc;
    private String youthDeathVerification;
    private String youthDeathVerificationDesc;    
    
    private String nextCourtDate;
    private String courtID;
    private String juvPhoneNum;
    private String parentEmail;
    private String primaryIndEmail;
    private String lastAttorney;  
    private String galName;
    
    

    private String accessToken;
    private String attorneyBarNumber;
    private String attorneyEmail;
    private String galEmail;
    
    

    //US 85384
    private boolean hasActiveReferrals;
    private boolean hasAnyReferrals;
    private String masterStatus; //US 101337
    private String traitTypeId;
    private String traitTypeDescription;
	
	public void clearAll( )
	{
		this.profileDetail = new JuvenileProfileDetailResponseEvent() ;
		this.hispanic = "" ;
		this.multiracial = "" ;
		this.ethnicity = "" ;
		this.memberNum = "";
		this.naturalEyeColor = "" ;
		this.naturalHairColor = "" ;
		this.age = "" ;
		this.juvenileNum = "" ;
		this.refTransferMesg="";
		this.supervisionNum = "" ;
		this.currentCasefile = new JuvenileCasefileResponseEvent() ;
		this.physicalAttribute = new JuvenilePhysicalAttributesResponseEvent() ;
		this.school = new JuvenileSchoolHistoryResponseEvent() ;
		this.memberAddress = null ;
		this.memberContact = new JuvenileMemberForm.MemberContact() ;
		this.guardians = emptyColl ;
		this.memberDetail = new FamilyMemberDetailResponseEvent() ;
		this.setFamilyMembers(emptyColl) ;
		this.casefiles = emptyColl ;

		this.traits = emptyColl ;
		this.currentTrait = new JuvenileTraitResponseEvent() ;
		this.behaviorHistory = new JuvenileBehaviorHistoryResponseEvent() ;
		this.warrant = null ;
		this.referralNums = null ;
		this.action = "" ;
		this.inHomeStatus = false ;
		this.officerAlert = "false";
		
		//this.admissionInfo= new JuvenileDetentionFacilitiesResponseEvent();
		this.juvProfRefListDetails=new ArrayList<JuvenileProfileReferralListResponseEvent>();
		this.juvFacList=new ArrayList<JuvenileDetentionFacilitiesResponseEvent>();
		this.headerInfo=new JuvenileFacilityHeaderResponseEvent();
		this.admissionInfo=new JuvenileDetentionFacilitiesResponseEvent();
		
		this.referrals = emptyColl;
		this.hasPendingCasefiles=false;
		this.hasActiveCasefiles=false;
		this.hasPostAdjCatCasefile=false;
		this.hasActiveOrClosingPendingCasefiles=false;
		this.bookingReferral="";
		this.currentReferral="";
		this.facilityStatus="";
		this.releaseTo="";
		this.bookingOffenseCode="";
		this.bookingOffenseCodeDesc="";
		this.escapeRisk=false;
		
		this.pactServerName="";
		this.pactApplicationName="";
		this.riskLvl="";
		this.needsLvl="";
		this.lastPactDate=null;
		this.detVisits= new ArrayList<JuvenileDetentionVisitResponseEvent>();
		this.detVisitBanned=false;
		this.restrictedAccessTrait=false;
		this.juvAddress="";
		this.juvCounty="";
		this.juvSchoolDist="";
		this.juvSchoolName="";
		this.fathersAddress="";
		this.fathersName="";
		this.fathersPhone="";
		this.fathersSSN="";
		this.mothersName="";
		this.mothersAddress="";
		this.mothersPhone="";
		this.mothersSSN="";
		this.otherRelName="";
		this.otherRelAddress="";
		this.otherRelSSN="";
		this.otherRelPhone="";
		this.dualStatus="";
		this.holdTrait="";
		this.holdTraitDescription="";
		this.setMedicalHold("");
		this.nextCourtDate="";
		this.courtID="";
		this.juvPhoneNum="";
		this.parentEmail="";
		this.primaryIndEmail = "";
		this.lastAttorney="";
		this.galName="";
		this.galEmail="";
		
		this.deathDate = null;
		this.deathAge = 0;
		this.youthDeathReason = "";
		this.youthDeathVerification = "";
		this.youthDeathReasonDesc = "";
		this.youthDeathVerificationDesc = "";
		this.masterStatus = "";
		this.traitTypeId = null;
		this.traitTypeDescription = null;
	}
	
	public JuvenileBriefingDetailsForm( )
	{
	}

	public String getMemberNum()
	{
		return memberNum;
	}

	public void setMemberNum(String memberNum)
	{
		this.memberNum = memberNum;
	}

	public JuvenileBehaviorHistoryResponseEvent getBehaviorHistory( )
	{
		return behaviorHistory ;
	}

	public void setBehaviorHistory( JuvenileBehaviorHistoryResponseEvent behaviorHistory )
	{
		this.behaviorHistory = behaviorHistory ;
	}

	public Collection getCasefiles( )
	{
		return casefiles ;
	}

	public void setCasefiles( Collection casefiles )
	{
		this.casefiles = casefiles ;
	}

	public Address getMemberAddress( )
	{
		return memberAddress ;
	}

	public void setMemberAddress( Address memberAddress )
	{
		this.memberAddress = memberAddress ;
	}

	public JuvenileMemberForm.MemberContact getMemberContact( )
	{
		return memberContact ;
	}

	public void setMemberContact( JuvenileMemberForm.MemberContact memberContact )
	{
		this.memberContact = memberContact ;
	}

	public FamilyMemberDetailResponseEvent getMemberDetail( )
	{
		return memberDetail ;
	}

	public void setMemberDetail( FamilyMemberDetailResponseEvent memberDetail )
	{
		this.memberDetail = memberDetail ;
	}

	public JuvenilePhysicalAttributesResponseEvent getPhysicalAttribute( )
	{
		return physicalAttribute ;
	}

	public void setPhysicalAttribute( JuvenilePhysicalAttributesResponseEvent physicalAttribute )
	{
		this.physicalAttribute = physicalAttribute ;
	}

	public JuvenileProfileDetailResponseEvent getProfileDetail( )
	{
		return profileDetail ;
	}

	public void setProfileDetail( JuvenileProfileDetailResponseEvent profileDetail )
	{
		this.profileDetail = profileDetail ;
	}

	public JuvenileSchoolHistoryResponseEvent getSchool( )
	{
		return school ;
	}

	public void setSchool( JuvenileSchoolHistoryResponseEvent school )
	{
		this.school = school ;
	}

	public JuvenileWarrantResponseEvent getWarrant( )
	{
		return warrant ;
	}

	public void setWarrant( JuvenileWarrantResponseEvent warrant )
	{
		this.warrant = warrant ;
	}

	public String getAction( )
	{
		return action ;
	}

	/**
	 * @param action
	 *          The action to set.
	 */
	public void setAction( String action )
	{
		this.action = action ;
	}

	public Collection getGuardians( )
	{
		return guardians ;
	}

	public void setGuardians( Collection guardians )
	{
		this.guardians = guardians ;
	}

	/**
	 * @return the familyMembers
	 */
	public Collection getFamilyMembers()
	{
	    return familyMembers;
	}

	/**
	 * @param familyMembers the familyMembers to set
	 */
	public void setFamilyMembers(Collection familyMembers)
	{
	    this.familyMembers = familyMembers;
	}

	public HashSet getReferralNums( )
	{
		return referralNums ;
	}

	public void setReferralNums( HashSet referralNums )
	{
		this.referralNums = referralNums ;
	}

	public String getHispanic( )
	{
		return hispanic ;
	}

	public void setHispanic( String hispanic )
	{
		this.hispanic = hispanic ;
	}

	public String getMultiracial( )
	{
		return multiracial ;
	}

	public void setMultiracial( String multiracial )
	{
		this.multiracial = multiracial ;
	}

	public String getEthnicity( )
	{
		return ethnicity ;
	}

	public void setEthnicity( String ethnicity )
	{
		this.ethnicity = ethnicity ;
	}

	public String getNaturalEyeColor( )
	{
		return naturalEyeColor ;
	}

	public void setNaturalEyeColor( String naturalEyeColor )
	{
		this.naturalEyeColor = naturalEyeColor ;
	}

	public String getNaturalHairColor( )
	{
		return naturalHairColor ;
	}

	public void setNaturalHairColor( String naturalHairColor )
	{
		this.naturalHairColor = naturalHairColor ;
	}

	public void setProfileDescriptions( )
	{
		if( this.getProfileDetail( ) != null )
		{
			JuvenileProfileDetailResponseEvent aRespEvnt = this.getProfileDetail( ) ;

			this.setMultiracial( (aRespEvnt.isMultiracial()) ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT ) ;
			
			//U.S 88526
                	if (aRespEvnt.getHispanic() != null)
                	{
                	    if (aRespEvnt.getHispanic().equalsIgnoreCase("Y"))
                	    {
                		this.setHispanic(UIConstants.YES_FULL_TEXT);
                	    }
                	    else
                	    {
                		this.setHispanic(UIConstants.NO_FULL_TEXT);
                	    }
                	}
                	else
                	{
                	    this.setHispanic(UIConstants.EMPTY_STRING);
                	}
			
			if( stringNotEmpty(aRespEvnt.getEthnicity()) )
			{
				this.setEthnicity( CodeHelper.getFastCodeDescription( PDCodeTableConstants.JUVENILE_ETHNICITY, aRespEvnt.getEthnicity( ) ) ) ;
			}
			
			if( stringNotEmpty( aRespEvnt.getNatualEyeColor()) )
			{
				this.setNaturalEyeColor( CodeHelper.getFastCodeDescription( PDCodeTableConstants.EYE_COLOR, aRespEvnt.getNatualEyeColor( ) ) ) ;
			}
			
			if( stringNotEmpty( aRespEvnt.getNaturalHairColor()) )
			{
				this.setNaturalHairColor( CodeHelper.getFastCodeDescription( PDCodeTableConstants.HAIR_COLOR, aRespEvnt.getNaturalHairColor( ) ) ) ;
			}
			if( stringNotEmpty( aRespEvnt.getMasterStatusId()) )
			{
				this.setMasterStatus(aRespEvnt.getMasterStatusId()) ;
			}
		}
	}
	
	private boolean stringNotEmpty( String str )
	{
		return( str != null && str.length( ) > 0 ) ;		
	}

	public String getJuvenileNum( )
	{
		return juvenileNum ;
	}

	public void setJuvenileNum( String juvenileNum )
	{
		this.juvenileNum = juvenileNum ;
	}

	public String getSupervisionNum( )
	{
		return supervisionNum ;
	}

	public void setSupervisionNum( String supervisionNum )
	{
		this.supervisionNum = supervisionNum ;
	}

	public String getAge( )
	{
		return age ;
	}

	public void setAge( String age )
	{
		this.age = age ;
	}

	public JuvenileCasefileResponseEvent getCurrentCasefile( )
	{
		return currentCasefile ;
	}

	public void setCurrentCasefile( JuvenileCasefileResponseEvent currentCasefile )
	{
		this.currentCasefile = currentCasefile ;
	}

	public String getFromProfile( )
	{
		return fromProfile ;
	}

	public void setFromProfile( String fromProfile )
	{
		this.fromProfile = fromProfile ;
	}

	public boolean isInHomeStatus( )
	{
		return inHomeStatus ;
	}

	public void setInHomeStatus( boolean inHomeStatus )
	{
		this.inHomeStatus = inHomeStatus ;
	}

	public Collection getTraits( )
	{
		return traits ;
	}

	public void setTraits( Collection traits )
	{
		this.traits = traits ;
	}

	public JuvenileTraitResponseEvent getCurrentTrait( )
	{
		return currentTrait ;
	}

	public void setCurrentTrait( JuvenileTraitResponseEvent currentTrait )
	{
		this.currentTrait = currentTrait ;
	}

	public boolean isInTrackerProgram() {
		return inTrackerProgram;
	}

	public void setInTrackerProgram(boolean inTrackerProgram) {
		this.inTrackerProgram = inTrackerProgram;
	}

	public boolean isInSpecialtyCourt()
	{
	    return inSpecialtyCourt;
	}

	public void setInSpecialtyCourt(boolean inSpecialtyCourt)
	{
	    this.inSpecialtyCourt = inSpecialtyCourt;
	}

	public boolean isInMentalHealthServices() {
		return inMentalHealthServices;
	}

	public void setInMentalHealthServices(boolean inMentalHealthServices) {
		this.inMentalHealthServices = inMentalHealthServices;
	}

	/**
	 * @return the officerAlert
	 */
	public String getOfficerAlert() {
		return officerAlert;
	}

	/**
	 * @param officerAlert the officerAlert to set
	 */
	public void setOfficerAlert(String officerAlert) {
		this.officerAlert = officerAlert;
	}
	
	public boolean isHasPendingCasefiles() {
		return hasPendingCasefiles;
	}

	public void setHasPendingCasefiles(boolean hasPendingCasefiles) {
		this.hasPendingCasefiles = hasPendingCasefiles;
	}

	public Collection getReferrals() {
		return referrals;
	}

	public void setReferrals(Collection referrals) {
		this.referrals = referrals;
	}

	public void setBookingReferral(String bookingReferral) {
		this.bookingReferral = bookingReferral;
	}

	public String getBookingReferral() {
		return bookingReferral;
	}

	public void setCurrentReferral(String currentReferral) {
		this.currentReferral = currentReferral;
	}

	public String getCurrentReferral() {
		return currentReferral;
	}

	public void setFacilityStatus(String facilityStatus) {
		this.facilityStatus = facilityStatus;
	}

	public String getFacilityStatus() {
		return facilityStatus;
	}

	public void setHasActiveCasefiles(boolean hasActiveCasefiles) {
		this.hasActiveCasefiles = hasActiveCasefiles;
	}

	public boolean isHasActiveCasefiles() {
		return hasActiveCasefiles;
	}

	public void setHasPostAdjCatCasefile(boolean hasPostAdjCatCasefile) {
		this.hasPostAdjCatCasefile = hasPostAdjCatCasefile;
	}

	public boolean isHasPostAdjCatCasefile() {
		return hasPostAdjCatCasefile;
	}

	/**
	 * @param headerInfo the headerInfo to set
	 */
	public void setHeaderInfo(JuvenileFacilityHeaderResponseEvent headerInfo) {
		this.headerInfo = headerInfo;
	}

	/**
	 * @return the headerInfo
	 */
	public JuvenileFacilityHeaderResponseEvent getHeaderInfo() {
		return headerInfo;
	}

	/**
	 * @param releaseTo the releaseTo to set
	 */
	public void setReleaseTo(String releaseTo) {
		this.releaseTo = releaseTo;
	}

	/**
	 * @return the releaseTo
	 */
	public String getReleaseTo() {
		return releaseTo;
	}

	/**
	 * @param juvFacList the juvFacList to set
	 */
	public void setJuvFacList(Collection<JuvenileDetentionFacilitiesResponseEvent> juvFacList) {
		this.juvFacList = juvFacList;
	}

	/**
	 * @return the juvFacList
	 */
	public Collection<JuvenileDetentionFacilitiesResponseEvent> getJuvFacList() {
		return juvFacList;
	}

	/**
	 * @param admissionInfo the admissionInfo to set
	 */
	public void setAdmissionInfo(JuvenileDetentionFacilitiesResponseEvent admissionInfo) {
		this.admissionInfo = admissionInfo;
	}

	/**
	 * @return the admissionInfo
	 */
	public JuvenileDetentionFacilitiesResponseEvent getAdmissionInfo() {
		return admissionInfo;
	}



	public Collection<JuvenileProfileReferralListResponseEvent> getJuvProfRefListDetails() {
		return juvProfRefListDetails;
	}

	public void setJuvProfRefListDetails(
			Collection<JuvenileProfileReferralListResponseEvent> juvProfRefListDetails) {
		this.juvProfRefListDetails = juvProfRefListDetails;
	}

	/**
	 * @param bookingOffenseCode the bookingOffenseCode to set
	 */
	public void setBookingOffenseCode(String bookingOffenseCode) {
		this.bookingOffenseCode = bookingOffenseCode;
	}

	/**
	 * @return the bookingOffenseCode
	 */
	public String getBookingOffenseCode() {
		return bookingOffenseCode;
	}

	/**
	 * @param bookingOffenseCodeDesc the bookingOffenseCodeDesc to set
	 */
	public void setBookingOffenseCodeDesc(String bookingOffenseCodeDesc) {
		this.bookingOffenseCodeDesc = bookingOffenseCodeDesc;
	}

	/**
	 * @return the bookingOffenseCodeDesc
	 */
	public String getBookingOffenseCodeDesc() {
		return bookingOffenseCodeDesc;
	}

	/**
	 * @param hasCasefiles the hasCasefiles to set
	 */
	public void setHasCasefiles(boolean hasCasefiles) {
		this.hasCasefiles = hasCasefiles;
	}

	/**
	 * @return the hasCasefiles
	 */
	public boolean isHasCasefiles() {
		return hasCasefiles;
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
	 * @param currentOffenseCode the currentOffenseCode to set
	 */
	public void setCurrentOffenseCode(String currentOffenseCode) {
		this.currentOffenseCode = currentOffenseCode;
	}

	/**
	 * @return the currentOffenseCode
	 */
	public String getCurrentOffenseCode() {
		return currentOffenseCode;
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
	
	public boolean isHideFacilityTraitsLink() {
		return hideFacilityTraitsLink;
	}

	public void setHideFacilityTraitsLink(boolean hideFacilityTraitsLink) {
		this.hideFacilityTraitsLink = hideFacilityTraitsLink;
	}

	public String getPrimaryLanguageDesc() {
		return primaryLanguageDesc;
	}

	public void setPrimaryLanguageDesc(String primaryLanguageDesc) {
		this.primaryLanguageDesc = primaryLanguageDesc;
	}

	public boolean isEscapeRisk() {
		return escapeRisk;
	}

	public void setEscapeRisk(boolean escapeRisk) {
		this.escapeRisk = escapeRisk;
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
	 * @return the restrictedAccessTrait
	 */
	public boolean isRestrictedAccessTrait() {
		return restrictedAccessTrait;
	}

	/**
	 * @param restrictedAccessTrait the restrictedAccessTrait to set
	 */
	public void setRestrictedAccessTrait(boolean restrictedAccessTrait) {
		this.restrictedAccessTrait = restrictedAccessTrait;
	}

	public boolean isHasOpenReferrals() {
		return hasOpenReferrals;
	}

	public void setHasOpenReferrals(boolean hasOpenReferrals) {
		this.hasOpenReferrals = hasOpenReferrals;
	}

	public JuvenileJISResponseEvent getJisInfo() {
		return jisInfo;
	}

	public void setJisInfo(JuvenileJISResponseEvent jisInfo) {
		this.jisInfo = jisInfo;
	}

	/**
	 * @return the pactAuthKey
	 */
	public String getPactAuthKey() {
		return pactAuthKey;
	}

	/**
	 * @param pactAuthKey the pactAuthKey to set
	 */
	public void setPactAuthKey(String pactAuthKey) {
		this.pactAuthKey = pactAuthKey;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the pactServerName
	 */
	public String getPactServerName() {
		return pactServerName;
	}

	/**
	 * @param pactServerName the pactServerName to set
	 */
	public void setPactServerName(String pactServerName) {
		this.pactServerName = pactServerName;
	}

	/**
	 * @return the riskLvl
	 */
	public String getRiskLvl() {
		return riskLvl;
	}

	/**
	 * @param riskLvl the riskLvl to set
	 */
	public void setRiskLvl(String riskLvl) {
		this.riskLvl = riskLvl;
	}

	/**
	 * @return the needsLvl
	 */
	public String getNeedsLvl() {
		return needsLvl;
	}

	/**
	 * @param needsLvl the needsLvl to set
	 */
	public void setNeedsLvl(String needsLvl) {
		this.needsLvl = needsLvl;
	}

	/**
	 * @return the lastPactDate
	 */
	public String getLastPactDate() {
		return lastPactDate;
	}

	/**
	 * @param lastPactDate the lastPactDate to set
	 */
	public void setLastPactDate(String lastPactDate) {
		this.lastPactDate = lastPactDate;
	}

	/**
	 * @return the pactApplicationName
	 */
	public String getPactApplicationName() {
		return pactApplicationName;
	}

	/**
	 * @param pactApplicationName the pactApplicationName to set
	 */
	public void setPactApplicationName(String pactApplicationName) {
		this.pactApplicationName = pactApplicationName;
	}

	public Collection<JuvenileDetentionVisitResponseEvent> getDetVisits() {
		return detVisits;
	}

	public void setDetVisits(Collection<JuvenileDetentionVisitResponseEvent> detVisits) {
		this.detVisits = detVisits;
	}

	/**
	 * @return the detVisitBanned
	 */
	public boolean isDetVisitBanned() {
		return detVisitBanned;
	}

	/**
	 * @param detVisitBanned the detVisitBanned to set
	 */
	public void setDetVisitBanned(boolean detVisitBanned) {
		this.detVisitBanned = detVisitBanned;
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

	public String getJuvAddress()
	{
	    return juvAddress;
	}

	public void setJuvAddress(String juvAddress)
	{
	    this.juvAddress = juvAddress;
	}

	public String getJuvCounty()
	{
	    return juvCounty;
	}

	public void setJuvCounty(String juvCounty)
	{
	    this.juvCounty = juvCounty;
	}

	public String getJuvSchoolDist()
	{
	    return juvSchoolDist;
	}

	public void setJuvSchoolDist(String juvSchoolDist)
	{
	    this.juvSchoolDist = juvSchoolDist;
	}

	public String getJuvSchoolName()
	{
	    return juvSchoolName;
	}

	public void setJuvSchoolName(String juvSchoolName)
	{
	    this.juvSchoolName = juvSchoolName;
	}

	public Address getGuardianAddress()
	{
	    return guardianAddress;
	}

	public void setGuardianAddress(Address guardianAddress)
	{
	    this.guardianAddress = guardianAddress;
	}

	public String getFathersName()
	{
	    return fathersName;
	}

	public void setFathersName(String fathersName)
	{
	    this.fathersName = fathersName;
	}

	public String getFathersSSN()
	{
	    return fathersSSN;
	}

	public void setFathersSSN(String fathersSSN)
	{
	    this.fathersSSN = fathersSSN;
	}

	public String getFathersAddress()
	{
	    return fathersAddress;
	}

	public void setFathersAddress(String fathersAddress)
	{
	    this.fathersAddress = fathersAddress;
	}

	public String getFathersPhone()
	{
	    return fathersPhone;
	}

	public void setFathersPhone(String fathersPhone)
	{
	    this.fathersPhone = fathersPhone;
	}

	public String getMothersName()
	{
	    return mothersName;
	}

	public void setMothersName(String mothersName)
	{
	    this.mothersName = mothersName;
	}

	public String getMothersSSN()
	{
	    return mothersSSN;
	}

	public void setMothersSSN(String mothersSSN)
	{
	    this.mothersSSN = mothersSSN;
	}

	public String getMothersAddress()
	{
	    return mothersAddress;
	}

	public void setMothersAddress(String mothersAddress)
	{
	    this.mothersAddress = mothersAddress;
	}

	public String getMothersPhone()
	{
	    return mothersPhone;
	}

	public void setMothersPhone(String mothersPhone)
	{
	    this.mothersPhone = mothersPhone;
	}

	public String getOtherRelName()
	{
	    return otherRelName;
	}

	public void setOtherRelName(String otherRelName)
	{
	    this.otherRelName = otherRelName;
	}

	public String getOtherRelSSN()
	{
	    return otherRelSSN;
	}

	public void setOtherRelSSN(String otherRelSSN)
	{
	    this.otherRelSSN = otherRelSSN;
	}

	public String getOtherRelAddress()
	{
	    return otherRelAddress;
	}

	public void setOtherRelAddress(String otherRelAddress)
	{
	    this.otherRelAddress = otherRelAddress;
	}

	public String getOtherRelPhone()
	{
	    return otherRelPhone;
	}

	public void setOtherRelPhone(String otherRelPhone)
	{
	    this.otherRelPhone = otherRelPhone;
	}

	public boolean isHasActiveOrClosingPendingCasefiles()
	{
	    return hasActiveOrClosingPendingCasefiles;
	}

	public void setHasActiveOrClosingPendingCasefiles(boolean hasActiveOrClosingPendingCasefiles)
	{
	    this.hasActiveOrClosingPendingCasefiles = hasActiveOrClosingPendingCasefiles;
	}

	public boolean isHasActiveReferrals()
	{
	    return hasActiveReferrals;
	}

	public void setHasActiveReferrals(boolean hasActiveReferrals)
	{
	    this.hasActiveReferrals = hasActiveReferrals;
	}
	
	
	public boolean isHasAnyReferrals()
	{
	    return hasAnyReferrals;
	}

	public void setHasAnyReferrals(boolean hasAnyReferrals)
	{
	    this.hasAnyReferrals = hasAnyReferrals;
	}

	public String getDualStatus()
	{
	    return dualStatus;
	}

	public void setDualStatus(String dualStatus)
	{
	    this.dualStatus = dualStatus;
	}

	public String getMedicalHold()
	{
	    return medicalHold;
	}

	public void setMedicalHold(String medicalHold)
	{
	    this.medicalHold = medicalHold;
	}

	//added for US 101939
	public String getTempReleaseRequestDecision()
	{
	    return tempReleaseRequestDecision;
	}

	public void setTempReleaseRequestDecision(String tempReleaseRequestDecision)
	{
	    this.tempReleaseRequestDecision = tempReleaseRequestDecision;
	}

	//
	
	public String getIsCourtSettingToday()
	{
	    return isCourtSettingToday;
	}

	public void setIsCourtSettingToday(String isCourtSettingToday)
	{
	    this.isCourtSettingToday = isCourtSettingToday;
	}

	public boolean isTempRelDecisionEnabled()
	{
	    return tempRelDecisionEnabled;
	}

	public void setTempRelDecisionEnabled(boolean tempRelDecisionEnabled)
	{
	    this.tempRelDecisionEnabled = tempRelDecisionEnabled;
	}

	public String getPurgeSerNum()
	{
	    return purgeSerNum;
	}

	public void setPurgeSerNum(String purgeSerNum)
	{
	    this.purgeSerNum = purgeSerNum;
	}

	public String getPurgeBoxNum()
	{
	    return purgeBoxNum;
	}

	public void setPurgeBoxNum(String purgeBoxNum)
	{
	    this.purgeBoxNum = purgeBoxNum;
	}

	public String getCheckedOutTo()
	{
	    return checkedOutTo;
	}

	public void setCheckedOutTo(String checkedOutTo)
	{
	    this.checkedOutTo = checkedOutTo;
	}

	public String getPurgeDate()
	{
	    return purgeDate;
	}

	public void setPurgeDate(String purgeDate)
	{
	    this.purgeDate = purgeDate;
	}

	public boolean isHasOnly1010Referral()
	{
	    return hasOnly1010Referral;
	}

	public void setHasOnly1010Referral(boolean hasOnly1010Referral)
	{
	    this.hasOnly1010Referral = hasOnly1010Referral;
	}

	public String getHoldTrait()
	{
	    return holdTrait;
	}

	public void setHoldTrait(String holdTrait)
	{
	    this.holdTrait = holdTrait;
	}

	public String getHoldTraitDescription()
	{
	    return holdTraitDescription;
	}

	public void setHoldTraitDescription(String holdTraitDescription)
	{
	    this.holdTraitDescription = holdTraitDescription;
	}

	public String getSpecialtyCourtDescription()
	{
	    return specialtyCourtDescription;
	}

	public void setSpecialtyCourtDescription(String specialtyCourtDescription)
	{
	    this.specialtyCourtDescription = specialtyCourtDescription;
	}

	public String getNextCourtDate()
	{
	    return nextCourtDate;
	}

	public void setNextCourtDate(String nextCourtDate)
	{
	    this.nextCourtDate = nextCourtDate;
	}
	public boolean isTempRelDecisionDistEnabled()
	{
	    return tempRelDecisionDistEnabled;
	}

	public void setTempRelDecisionDistEnabled(boolean tempRelDecisionDistEnabled)
	{
	    this.tempRelDecisionDistEnabled = tempRelDecisionDistEnabled;
	}
	public String getTempDistReleaseRequestDecision()
	{
	    return tempDistReleaseRequestDecision;
	}

	public void setTempDistReleaseRequestDecision(String tempDistReleaseRequestDecision)
	{
	    this.tempDistReleaseRequestDecision = tempDistReleaseRequestDecision;
	}
	public String getIsDistCourtSettingToday()
	{
	    return isDistCourtSettingToday;
	}

	public void setIsDistCourtSettingToday(String isDistCourtSettingToday)
	{
	    this.isDistCourtSettingToday = isDistCourtSettingToday;
	}
	
	public String getDeathDate()
	{
	    return deathDate;
	}

	public void setDeathDate(String deathDate)
	{
	    this.deathDate = deathDate;
	}

	public int getDeathAge()
	{
	    return deathAge;
	}

	public void setDeathAge(int deathAge)
	{
	    this.deathAge = deathAge;
	}

	public Collection getCausesOfDeath()
	{
	    return CodeHelper.getCauseOfDeathCodes();
	}

	public void setCausesOfDeath(Collection causesOfDeath)
	{
	    this.causesOfDeath = causesOfDeath;
	}

	public String getYouthDeathReason()
	{
	    return youthDeathReason != null ? youthDeathReason.trim() : youthDeathReason;
	}

	public void setYouthDeathReason(String youthDeathReason)
	{
	    this.youthDeathReason = youthDeathReason;
	}
	
	public String getYouthDeathReasonDesc()
	{
	    return youthDeathReasonDesc != null ? youthDeathReasonDesc.trim() : youthDeathReasonDesc;
	}
	
	public void setYouthDeathReasonDesc(String youthDeathReasonDesc)
	{
	    this.youthDeathReasonDesc = youthDeathReasonDesc;
	}

	public String getYouthDeathVerification()
	{
	    return youthDeathVerification;
	}

	public void setYouthDeathVerification(String youthDeathVerification)
	{
	    this.youthDeathVerification = youthDeathVerification;
	}
	
	public String getYouthDeathVerificationDesc()
	{
	    return youthDeathVerificationDesc;
	}

	public void setYouthDeathVerificationDesc(String youthDeathVerificationDesc)
	{
	    this.youthDeathVerificationDesc = youthDeathVerificationDesc;
	}

	public Collection getDeathVerficationCodes()
	{
	    return CodeHelper.getDeathVerificationCodes();
	}

	public void setDeathVerficationCodes(Collection deathVerficationCodes)
	{
	    this.deathVerficationCodes = deathVerficationCodes;
	}

	public String getMasterStatus()
	{
	    return masterStatus;
	}

	public void setMasterStatus(String masterStatus)
	{
	    this.masterStatus = masterStatus;
	}
	
	public String getTraitTypeId()
	{
	    return this.traitTypeId;
	}

	public void setTraitTypeId(String traittypeId)
	{
	        this.traitTypeId = traittypeId;
	}
	
	public String getTraitTypeDescription()
	{
	    return this.traitTypeDescription;
	}

	public void setTraitTypeDescription(String traitTypeDesc)
	{
	        this.traitTypeDescription = traitTypeDesc;
	}
	public String getCourtID()
	    {
	        return courtID;
	    }

	    public void setCourtID(String courtID)
	    {
	        this.courtID = courtID;
	    }

    public String getJuvPhoneNum()
    {
	return juvPhoneNum;
    }

    public void setJuvPhoneNum(String juvPhoneNum)
    {
	this.juvPhoneNum = juvPhoneNum;
    }
    
    public String getParentEmail()
    {
	return parentEmail;
    }

    public void setParentEmail(String parentEmail)
    {
	this.parentEmail = parentEmail;
    }
    
    public String getPrimaryIndEmail()
    {
	return this.primaryIndEmail;
    }

    public void setPrimaryIndEmail(String primaryIndEmail)
    {
	this.primaryIndEmail = primaryIndEmail;
    }
    
    public String getLastAttorney()
    {
        return lastAttorney;
    }

    public void setLastAttorney(String lastAttorney)
    {
        this.lastAttorney = lastAttorney;
    }

    public String getIsUsCitizen()
    {
        return isUsCitizen;
    }

    public void setIsUsCitizen(String isUsCitizen)
    {
        this.isUsCitizen = isUsCitizen;
    }

    public String getJuvProgramCode()
    {
        return this.juvProgramCode;
    }

    public void setJuvProgramCode(String programCode)
    {
        this.juvProgramCode = programCode;
    }
    
    public String getJuvProgramDescription()
    {
        return this.juvProgramDescription;
    }

    public void setJuvProgramDescription(String programDescription)
    {
        this.juvProgramDescription = programDescription;
    }
    
    public String getAccessToken()
    {
        return this.accessToken;
    }

    public void setAccessToken(String token)
    {
        this.accessToken = token;
    }
    
    public String getAttorneyBarNumber()
    {
        return this.attorneyBarNumber;
    }

    public void setAttorneyBarNumber(String barNumber)
    {
        this.attorneyBarNumber = barNumber;
    }
    
    public String getAttorneyEmail()
    {
        return this.attorneyEmail;
    }

    public void setAttorneyEmail(String email)
    {
        this.attorneyEmail = email;
    }
    public String getGalName()
    {
        return galName;
    }

    public void setGalName(String galName)
    {
        this.galName = galName;
    }
    public String getGalEmail()
    {
        return galEmail;
    }

    public void setGalEmail(String galEmail)
    {
        this.galEmail = galEmail;
    }
}

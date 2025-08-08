/*
 * Created on December 06, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.juvenilecase.reply.OverrideOptionsResponseEvent;

import org.apache.struts.action.ActionForm;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileLoadCodeTables;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CasefileClosingForm extends ActionForm {
	// Default field
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private boolean skipSubOutCome = false;
	private String selectedValue = "";
	private boolean closingInfoFound;
		
	//	Casefile Closing Related fields
	private String supervisionEndDate = "";
	private String supervisionOutcomeId = "";
	private String supervisionOutcome = "";
	private String supervsionOutcomeDescriptionId;
	private String supervsionOutcomeDescription;
	private Collection referrals = null;
	private Collection profileReferrals = null;
	private String closingEvalution = "";
	private String closingComments = "";
	private Collection casefileExceptions = null;
	private String controllingReferral = "";
	private String shortDescription = "";
	private String selectedDispostion = "";
	private String rejectReason = "";
	private String closingInfoId = "";
	private Collection permanencyPlanList = null;
	private Collection facilityReleaseReasonList = null;
	private Collection levelOfCareList = null;
	private String supervisionNumber = "";
	private Collection facilityList = null;
	private boolean isClosingPktGenerated;
	private boolean isClosingLetterGenerated;
	private String completedDescId;
	private String failureDescId;
	
	private String exceptionOverrideComments = "" ;
	
	//for CLM review page
	private Collection interviewList = null;
	private Collection referralList = null;
	private Collection activereferralList = null;
	
	private Collection programReferrals = null;
	
	// used by pd to prevent database lookup please do not modify as this is a key piece for pd
	private String reportFileLocOnServer = "";
	private boolean closingInfoAvail =false;
	
	private boolean isCaseLoadManager = false;
	private boolean hasActiveProgramReferral = false;
	private boolean hasNonComplianceIncompleteStatus = false;
	private boolean hasGuardianException = false;
	private boolean hasPendingTJJDException = true;
	private boolean hasGoalsException = true;
	private boolean hasRulesException = true;
	private boolean casePlanNotAcknowledgedException = true;
	private boolean hasJuvenileInFacility = false;
	
	 
	//	Drop down lists
	protected static Collection supervisionOutcomesList = emptyColl;
	protected static Collection activeSupervisionOutcomesList = emptyColl;
	
	private Collection optionalSupervisionOutcomeDescList = emptyColl;
	private Collection requiredSupervisionOutcomeDescList = emptyColl;
	private Collection<OverrideOptionsResponseEvent> overrideOptionCodes= emptyColl;
	
	private String juvenileNum;
	
	private String activationDateStr;
	
	//closing date compare ER JIMS200076597
	private String earliestAssignAddDate;
	private String earliestDateCasefile;
	
	//This variable is to be used in Client Satisfaction scenario
	private boolean spanishText = false;
	
	private boolean hasTransferredOffense = false;  // User-story 11079
	
	private String transferCasefileId;
	
	private boolean isCasefileAssociated;
	private String probationStartDate;
	private String probationEndDate;
	private String refCloseDate;
	private String refNo;
	//private String selectedReferrals="";	

	private Collection newDates=null;	
	private String cartRefnums;
	private Collection causesOfDeath;
	private Collection deathVerficationCodes;
	private String youthDeathReason;
	private String youthDeathReasonDesc;
	private String youthDeathVerification;
	private String youthDeathVerificationDesc;
	private String deathDate;
	private int deathAge;
	private String overrideSelection;
	

	

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	
	public CasefileClosingForm()
	{
		emptyColl = new ArrayList();
		listsSet = false;
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
		reportFileLocOnServer = "";  //Common App ER  11046 changes
		UIJuvenileLoadCodeTables.getInstance().setJuvenileCasefileClosingForm(this);
		newDates=null;
		probationStartDate=null;
		probationEndDate=null;
		refCloseDate=null;
		
		//selectedReferrals="";
		// Form specific initialization
	}
	
	public void clearActions()
	{
		action = "";
		secondaryAction = "";
	}
	
	public void clearAll()
	{
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
		supervisionEndDate = "";
		supervisionOutcomeId = "";
		supervisionOutcome = "";
		supervsionOutcomeDescriptionId = "";
		supervsionOutcomeDescription = "";
		referrals = null;
		closingEvalution = "";
		closingComments = "";
		casefileExceptions = null;
		controllingReferral = "";
		rejectReason = "";
		reportFileLocOnServer = "";
		closingInfoId = "";
		exceptionOverrideComments = "" ;
		closingInfoAvail=false;
		newDates=null;
		probationStartDate=null;
		probationEndDate=null;
		refCloseDate=null;
		cartRefnums=null;
		youthDeathReason = "";
		youthDeathVerification = "";
		youthDeathReasonDesc = "";
		youthDeathVerificationDesc = "";
		deathDate = null;
		deathAge = 0;
		overrideSelection ="";
		
		//this.cartRefnums=new ArrayList<String>();
		//selectedReferrals="";
	}
	
	public static class Refferal implements Comparable 
	{
		private String referralNumber = "";
		private String referralSeverity = "";
		private String finalDisposition = "";
		
		/**
		 * @return
		 */
		public String getReferralNumber()
		{
			return referralNumber;
		}

		/**
		 * @param string
		 */
		public void setReferralNumber(String string)
		{
			referralNumber = string;
		}
		
		/**
		 * @return the shortDescription
		 */
		public String getFinalDisposition() {
			return finalDisposition;
		}

		/**
		 * @param shortDescription the shortDescription to set
		 */
		public void setFinalDisposition(String string) {
			this.finalDisposition = string;
		}

		/**
		 * @return the referralNumberWithDesc
		 */
		public String getReferralSeverity() {
			return referralSeverity;
		}

		/**
		 * @param referralNumberWithDesc the referralNumberWithDesc to set
		 */
		public void setReferralSeverity(String string) {
			referralSeverity = string;
		}

		public int compareTo(Object obj) {
			Refferal evt = (Refferal) obj;
			
			String ref1 = referralNumber.trim();
			String ref2 = evt.getReferralNumber().trim();		
			return ref1.compareToIgnoreCase(ref2);
		}
	}// end public static class Refferal

	public static class CasefileException
	{ 
		private String exceptionType = null;
		private String exceptionId = null;
		private String exceptionMessage = null;

		/**
		 * @return
		 */
		public String getExceptionId()
		{
			return exceptionId;
		}

		/**
		 * @return
		 */
		public String getExceptionMessage()
		{
			return exceptionMessage;
		}

		/**
		 * @return
		 */
		public String getExceptionType()
		{
			return exceptionType;
		}

		/**
		 * @param string
		 */
		public void setExceptionId(String string)
		{
			exceptionId = string;
		}

		/**
		 * @param string
		 */
		public void setExceptionMessage(String string)
		{
			exceptionMessage = string;
		}

		/**
		 * @param string
		 */
		public void setExceptionType(String string)
		{
			exceptionType = string;
		}

	}// END Casefile Exception class
	
	/**
	 * @return
	 */
	public Collection getSupervisionOutcomesList()
	{
		return supervisionOutcomesList;
	}

	/**
	 * @return
	 */
	public Collection getActiveSupervisionOutcomesList()
	{
		return activeSupervisionOutcomesList;
	}

	/**
	 * @return
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @return
	 */
	public Collection getCasefileExceptions()
	{
		return casefileExceptions;
	}

	/**
	 * @return
	 */
	public String getClosingComments()
	{
		return closingComments;
	}

	/**
	 * @return
	 */
	public String getClosingEvalution()
	{
		return closingEvalution;
	}

	/**
	 * @return
	 */
	public String getControllingReferral()
	{
		return controllingReferral;
	}	
	
	
	public boolean getSkipSubOutCome()
	{
		return skipSubOutCome;
	}
	
	public void setSkipSubOutCome(boolean skipSubOutCome)
	{
	    this.skipSubOutCome =  skipSubOutCome;
	}
	
	
	/**
	 * @return
	 */
	public boolean isDelete()
	{
		return delete;
	}

	/**
	 * @return
	 */
	public boolean isListsSet()
	{
		return listsSet;
	}

	/**
	 * @return
	 */
	public Collection getReferrals()
	{
		return referrals;
	}

	public Collection getProfileReferrals() 
	{
		return profileReferrals;
	}
	
	public void setProfileReferrals(Collection profileReferrals) 
	{
		this.profileReferrals = profileReferrals;
	}
	
	/**
	 * @return
	 */
	public String getRejectReason()
	{
		return rejectReason;
	}

	/**
	 * @return
	 */
	public String getSecondaryAction()
	{
		return secondaryAction;
	}

	/**
	 * @return
	 */
	public String getSelectedValue()
	{
		return selectedValue;
	}

	/**
	 * @return
	 */
	public String getSupervisionEndDate()
	{
		return supervisionEndDate;
	}

	/**
	 * @return the earliestAssignAddDate
	 */
	public String getEarliestAssignAddDate() {
		return earliestAssignAddDate;
	}

	/**
	 * @param earliestAssignAddDate the earliestAssignAddDate to set
	 */
	public void setEarliestAssignAddDate(String earliestAssignAddDate) {
		this.earliestAssignAddDate = earliestAssignAddDate;
	}

	/**
	 * @return the earliestDateCasefile
	 */
	public String getEarliestDateCasefile() {
		return earliestDateCasefile;
	}

	/**
	 * @param earliestDateCasefile the earliestDateCasefile to set
	 */
	public void setEarliestDateCasefile(String earliestDateCasefile) {
		this.earliestDateCasefile = earliestDateCasefile;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcome()
	{
		return supervisionOutcome;
	}

	/**
	 * @return
	 */
	public String getSupervisionOutcomeId()
	{
		return supervisionOutcomeId;
	}

	/**
	 * @return
	 */
	public boolean isUpdate()
	{
		return update;
	}

	/**
	 * @param collection
	 */
	public void setSupervisionOutcomesList(Collection collection)
	{
		supervisionOutcomesList = collection;
	}

	/**
	 * @param collection
	 */
	public void setActiveSupervisionOutcomesList(Collection collection)
	{
		activeSupervisionOutcomesList = collection;
	}

	
	/**
	 * @param string
	 */
	public void setAction(String string)
	{
		action = string;
	}

	/**
	 * @param collection
	 */
	public void setCasefileExceptions(Collection collection)
	{
		casefileExceptions = collection;
	}

	/**
	 * @param string
	 */
	public void setClosingComments(String string)
	{
		closingComments = string;
	}

	/**
	 * @param string
	 */
	public void setClosingEvalution(String string)
	{
		closingEvalution = string;
	}

	/**
	 * @param string
	 */
	public void setControllingReferral(String string)
	{
		controllingReferral = string;
	}

	/**
	 * @param b
	 */
	public void setDelete(boolean b)
	{
		delete = b;
	}

	/**
	 * @param b
	 */
	public void setListsSet(boolean b)
	{
		listsSet = b;
	}

	/**
	 * @param collection
	 */
	public void setReferrals(Collection collection)
	{
		referrals = collection;
	}

	/**
	 * @param string
	 */
	public void setRejectReason(String string)
	{
		rejectReason = string;
	}

	/**
	 * @param string
	 */
	public void setSecondaryAction(String string)
	{
		secondaryAction = string;
	}

	/**
	 * @param string
	 */
	public void setSelectedValue(String string)
	{
		selectedValue = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionEndDate(String string)
	{
		supervisionEndDate = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcome(String string)
	{
		supervisionOutcome = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisionOutcomeId(String string)
	{
		supervisionOutcomeId = string;
		if(supervisionOutcomeId == null || supervisionOutcomeId.equals(""))
			return;

		if(CasefileClosingForm.supervisionOutcomesList != null &&  CasefileClosingForm.supervisionOutcomesList.size()>0)
		{
			supervisionOutcome = CodeHelper.getCodeDescriptionByCode(CasefileClosingForm.supervisionOutcomesList,supervisionOutcomeId);
		}
	}

	/**
	 * @param b
	 */
	public void setUpdate(boolean b)
	{
		update = b;
	}

	/**
	 * @return
	 */
	public String getClosingInfoId()
	{
		return closingInfoId;
	}

	/**
	 * @param string
	 */
	public void setClosingInfoId(String string)
	{
		closingInfoId = string;
	}

	/**
	 * @return
	 */
	public static Collection getEmptyColl()
	{
		return emptyColl;
	}

	/**
	 * @return
	 */
	public Collection getFacilityReleaseReasonList()
	{
		return facilityReleaseReasonList;
	}

	/**
	 * @return
	 */
	public Collection getLevelOfCareList()
	{
		return levelOfCareList;
	}

	/**
	 * @return
	 */
	public Collection getPermanencyPlanList()
	{
		return permanencyPlanList;
	}

	/**
	 * @param collection
	 */
	public static void setEmptyColl(Collection collection)
	{
		emptyColl = collection;
	}

	/**
	 * @param collection
	 */
	public void setFacilityReleaseReasonList(Collection collection)
	{
		facilityReleaseReasonList = collection;
	}

	/**
	 * @param collection
	 */
	public void setLevelOfCareList(Collection collection)
	{
		levelOfCareList = collection;
	}

	/**
	 * @param collection
	 */
	public void setPermanencyPlanList(Collection collection)
	{
		permanencyPlanList = collection;
	}

	/**
	 * @return
	 */
	public Collection getFacilityList()
	{
		return facilityList;
	}

	/**
	 * @param collection
	 */
	public void setFacilityList(Collection collection)
	{
		facilityList = collection;
	}

	/**
	 * @return
	 */
	public String getSupervisionNumber()
	{
		return supervisionNumber;
	}

	/**
	 * @param string
	 */
	public void setSupervisionNumber(String string)
	{
		supervisionNumber = string;
	}

	/**
	 * @return
	 */
	public boolean isCaseLoadManager()
	{
		return isCaseLoadManager;
	}

	/**
	 * @param b
	 */
	public void setCaseLoadManager(boolean b)
	{
		isCaseLoadManager = b;
	}

	/**
	 * @return
	 */
	public String getReportFileLocOnServer()
	{
		return reportFileLocOnServer;
	}

	/**
	 * @param string
	 */
	public void setReportFileLocOnServer(String string)
	{
	// commented for Common App ER  11046 changes
//		if((this.reportFileLocOnServer != null || 
//				!(this.reportFileLocOnServer.trim().equals(""))) && 
//				(string == null || string.trim().equals("")))
//		{
//			return;
//		}
//Common App ER  11046 ends
		reportFileLocOnServer = string;
	}

	/**
	 * @return
	 */
	public String getSelectedDispostion()
	{
		return selectedDispostion;
	}

	/**
	 * @param string
	 */
	public void setSelectedDispostion(String string)
	{
		if(string == null)
			string = "";
		selectedDispostion = string;
	}

	/**
	 * @return Returns the interviewList.
	 */
	public Collection getInterviewList() 
	{
		return interviewList;
	}
	
	/**
	 * @param interviewList The interviewList to set.
	 */
	public void setInterviewList(Collection interviewList) 
	{
		this.interviewList = interviewList;
	}
	
	/**
	 * @return Returns the referralList.
	 */
	public Collection getReferralList() 
	{
		return referralList;
	}
	
	/**
	 * @param referralList The referralList to set.
	 */
	public void setReferralList(Collection referralList) {
		this.referralList = referralList;
	}
	
	/**
	 * @return Returns the programReferrals.
	 */
	public Collection getProgramReferrals() {
		return programReferrals;
	}
	
	/**
	 * @param programReferrals The programReferrals to set.
	 */
	public void setProgramReferrals(Collection programReferrals) {
		this.programReferrals = programReferrals;
	}

	/**
	 * @return Returns the closingInfoFound.
	 */
	public boolean isClosingInfoFound() {
		return closingInfoFound;
	}

	/**
	 * @param closingInfoFound The closingInfoFound to set.
	 */
	public void setClosingInfoFound(boolean closingInfoFound) {
		this.closingInfoFound = closingInfoFound;
	}

	/**
	 * @return Returns the isClosingLetterGenerated.
	 */
	public boolean isClosingLetterGenerated() {
		return isClosingLetterGenerated;
	}
	
	/**
	 * @param isClosingLetterGenerated The isClosingLetterGenerated to set.
	 */
	public void setClosingLetterGenerated(boolean isClosingLetterGenerated) {
		this.isClosingLetterGenerated = isClosingLetterGenerated;
	}

	/**
	 * @return Returns the isClosingPktGenerated.
	 */
	public boolean isClosingPktGenerated() {
		return isClosingPktGenerated;
	}

	/**
	 * @param isClosingPktGenerated The isClosingPktGenerated to set.
	 */
	public void setClosingPktGenerated(boolean isClosingPktGenerated) {
		this.isClosingPktGenerated = isClosingPktGenerated;
	}

	public String getExceptionOverrideComments() {
		return exceptionOverrideComments;
	}

	public void setExceptionOverrideComments(String exceptionOverrideComments) {
		this.exceptionOverrideComments = exceptionOverrideComments;
	}

	/**
	 * @return the spanishText
	 */
	public boolean isSpanishText() {
		return spanishText;
	}

	/**
	 * @param spanishText the spanishText to set
	 */
	public void setSpanishText(boolean spanishText) {
		this.spanishText = spanishText;
	}

	public boolean isHasActiveProgramReferral() {
		return hasActiveProgramReferral;
	}

	public void setHasActiveProgramReferral(boolean hasActiveProgramReferral) {
		this.hasActiveProgramReferral = hasActiveProgramReferral;
	}

	/**
	 * @return the activationDateStr
	 */
	public String getActivationDateStr() {
		return activationDateStr;
	}

	/**
	 * @param activationDateStr the activationDateStr to set
	 */
	public void setActivationDateStr(String activationDateStr) {
		this.activationDateStr = activationDateStr;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the supervsionOutcomeDescriptionId
	 */
	public String getSupervsionOutcomeDescriptionId() {
		return supervsionOutcomeDescriptionId;
	}

	/**
	 * @param supervsionOutcomeDescriptionId the supervsionOutcomeDescriptionId to set
	 */
	public void setSupervsionOutcomeDescriptionId(
			String supervsionOutcomeDescriptionId) {
		this.supervsionOutcomeDescriptionId = supervsionOutcomeDescriptionId;
	}

	/**
	 * @return the supervsionOutcomeDescription
	 */
	public String getSupervsionOutcomeDescription() {
		return supervsionOutcomeDescription;
	}

	/**
	 * @param supervsionOutcomeDescription the supervsionOutcomeDescription to set
	 */
	public void setSupervsionOutcomeDescription(String supervsionOutcomeDescription) {
		this.supervsionOutcomeDescription = supervsionOutcomeDescription;
	}

	/**
	 * @return the optionalSupervisionOutcomeDescList
	 */
	public Collection getOptionalSupervisionOutcomeDescList() {
		return optionalSupervisionOutcomeDescList;
	}

	/**
	 * @param optionalSupervisionOutcomeDescList the optionalSupervisionOutcomeDescList to set
	 */
	public void setOptionalSupervisionOutcomeDescList(
			Collection optionalSupervisionOutcomeDescList) {
		this.optionalSupervisionOutcomeDescList = optionalSupervisionOutcomeDescList;
	}

	/**
	 * @return the requiredSupervisionOutcomeDescList
	 */
	public Collection getRequiredSupervisionOutcomeDescList() {
		return requiredSupervisionOutcomeDescList;
	}

	/**
	 * @param requiredSupervisionOutcomeDescList the requiredSupervisionOutcomeDescList to set
	 */
	public void setRequiredSupervisionOutcomeDescList(
			Collection requiredSupervisionOutcomeDescList) {
		this.requiredSupervisionOutcomeDescList = requiredSupervisionOutcomeDescList;
	}

	/**
	 * @return the completedDescId
	 */
	public String getCompletedDescId() {
		return completedDescId;
	}

	/**
	 * @param completedDescId the completedDescId to set
	 */
	public void setCompletedDescId(String completedDescId) {
		this.completedDescId = completedDescId;
	}

	/**
	 * @return the failureDescId
	 */
	public String getFailureDescId() {
		return failureDescId;
	}

	/**
	 * @param failureDescId the failureDescId to set
	 */
	public void setFailureDescId(String failureDescId) {
		this.failureDescId = failureDescId;
	}

	/**
	 * @return the hasNonComplianceIncompleteStatus
	 */
	public boolean isHasNonComplianceIncompleteStatus() {
		return hasNonComplianceIncompleteStatus;
	}

	/**
	 * @param hasNonComplianceIncompleteStatus the hasNonComplianceIncompleteStatus to set
	 */
	public void setHasNonComplianceIncompleteStatus(boolean hasNonComplianceIncompleteStatus) {
		this.hasNonComplianceIncompleteStatus = hasNonComplianceIncompleteStatus;
	}

	/**
	 * @return the hasGuardianException
	 */
	public boolean isHasGuardianException() {
		return hasGuardianException;
	}

	/**
	 * @return the hasPendingTJJDException
	 */
	public boolean isHasPendingTJJDException() {
		return hasPendingTJJDException;
	}

	/**
	 * @param hasPendingTJJDException the hasPendingTJJDException to set
	 */
	public void setHasPendingTJJDException(boolean hasPendingTJJDException) {
		this.hasPendingTJJDException = hasPendingTJJDException;
	}

	/**
	 * @return the hasGoalsException
	 */
	public boolean isHasGoalsException() {
		return hasGoalsException;
	}

	/**
	 * @param hasGoalsException the hasGoalsException to set
	 */
	public void setHasGoalsException(boolean hasGoalsException) {
		this.hasGoalsException = hasGoalsException;
	}

	/**
	 * @return the hasRulesException
	 */
	public boolean isHasRulesException() {
		return hasRulesException;
	}

	/**
	 * @param hasRulesException the hasRulesException to set
	 */
	public void setHasRulesException(boolean hasRulesException) {
		this.hasRulesException = hasRulesException;
	}
	/**
	 * @return the casePlanNotAcknowledgedException
	 */
	public boolean isCasePlanNotAcknowledgedException() {
		return casePlanNotAcknowledgedException;
	}

	/**
	 * @param casePlanNotAcknowledgedException the casePlanNotAcknowledgedException to set
	 */
	public void setCasePlanNotAcknowledgedException(boolean casePlanNotAcknowledgedException) {
		this.casePlanNotAcknowledgedException = casePlanNotAcknowledgedException;
	}
	
	/**
	 * @param hasGuardianException the hasGuardianException to set
	 */
	public void setHasGuardianException(boolean hasGuardianException) {
		this.hasGuardianException = hasGuardianException;
	}

	/**
	 * @return the hasTransferredOffense
	 */
	public boolean isHasTransferredOffense() {
		return hasTransferredOffense;
	}

	/**
	 * @param hasTransferredOffense the hasTransferredOffense to set
	 */
	public void setHasTransferredOffense(boolean hasTransferredOffense) {
		this.hasTransferredOffense = hasTransferredOffense;
	}

	/**
	 * @return the hasJuvenileInFacility
	 */
	public boolean isHasJuvenileInFacility() {
		return hasJuvenileInFacility;
	}

	/**
	 * @param hasJuvenileInFacility the hasJuvenileInFacility to set
	 */
	public void setHasJuvenileInFacility(boolean hasJuvenileInFacility) {
		this.hasJuvenileInFacility = hasJuvenileInFacility;
	}

	/**
	 * @return the closingInfoAvail
	 */
	public boolean isClosingInfoAvail() {
		return closingInfoAvail;
	}

	/**
	 * @param closingInfoAvail the closingInfoAvail to set
	 */
	public void setClosingInfoAvail(boolean closingInfoAvail) {
		this.closingInfoAvail = closingInfoAvail;
	}

	/**
	 * @return the transferCasefileId
	 */
	public String getTransferCasefileId() {
		return transferCasefileId;
	}

	/**
	 * @param transferCasefileId the transferCasefileId to set
	 */
	public void setTransferCasefileId(String transferCasefileId) {
		this.transferCasefileId = transferCasefileId;
	}
	
	/**
	 * @return the isCasefileAssociated
	 */
	public boolean isCasefileAssociated() {
		return isCasefileAssociated;
	}

	/**
	 * @return the isTransferred
	 */
	public boolean getIsCasefileAssociated() {
		return isCasefileAssociated;
	}
	/**
	 * @param isCasefileAssociated the isCasefileAssociated to set
	 */
	public void setCasefileAssociated(boolean isCasefileAssociated) {
		this.isCasefileAssociated = isCasefileAssociated;
	}
	public Collection getActivereferralList()
	{
	    return activereferralList;
	}

	public void setActivereferralList(Collection activereferralList)
	{
	    this.activereferralList = activereferralList;
	}
	public String getProbationStartDate()
	{
	    return probationStartDate;
	}

	public void setProbationStartDate(String probationStartDate)
	{
	    this.probationStartDate = probationStartDate;
	}
	
	public String getProbationEndDate()
	{
	    return probationEndDate;
	}

	public void setProbationEndDate(String probationEndDate)
	{
	    this.probationEndDate = probationEndDate;
	}
	public String getRefCloseDate()
	{
	    return refCloseDate;
	}

	public void setRefCloseDate(String refCloseDate)
	{
	    this.refCloseDate = refCloseDate;
	}
	public Collection getNewDates()
	{
	    if( this.newDates == null )
		{
			this.newDates = new ArrayList( ) ;
		}	    
	    return newDates;
	}

	public void setNewDates(Collection newDates)
	{
	    this.newDates = newDates;
	}
	public String getRefNo()
	{
	    return refNo;
	}

	public void setRefNo(String refNo)
	{
	    this.refNo = refNo;
	}	
	public String getCartRefnums()
	{
	    return cartRefnums;
	}

	public void setCartRefnums(String cartRefnums)
	{
	    this.cartRefnums = cartRefnums;
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
	    return youthDeathReason;
	}

	public void setYouthDeathReason(String youthDeathReason)
	{
	    this.youthDeathReason = youthDeathReason;
	}

	public String getYouthDeathVerification()
	{
	    return youthDeathVerification;
	}

	public void setYouthDeathVerification(String youthDeathVerification)
	{
	    this.youthDeathVerification = youthDeathVerification;
	}

	public Collection getDeathVerficationCodes()
	{
	    return CodeHelper.getDeathVerificationCodes();
	}

	public void setDeathVerficationCodes(Collection deathVerficationCodes)
	{
	    this.deathVerficationCodes = deathVerficationCodes;
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

	public String getYouthDeathReasonDesc()
	{
	    return youthDeathReasonDesc;
	}

	public void setYouthDeathReasonDesc(String youthDeathReasonDesc)
	{
	    this.youthDeathReasonDesc = youthDeathReasonDesc;
	}

	public String getYouthDeathVerificationDesc()
	{
	    return youthDeathVerificationDesc;
	}

	public void setYouthDeathVerificationDesc(String youthDeathVerificationDesc)
	{
	    this.youthDeathVerificationDesc = youthDeathVerificationDesc;
	}

	public String getOverrideSelection()
	{
	    return overrideSelection;
	}

	public void setOverrideSelection(String overrideSelection)
	{
	    this.overrideSelection = overrideSelection;
	}

	public Collection<OverrideOptionsResponseEvent> getOverrideOptionCodes()
	{
	    return overrideOptionCodes;
	}

	public void setOverrideOptionCodes(Collection<OverrideOptionsResponseEvent> overrideOptionCodes)
	{
	    this.overrideOptionCodes = overrideOptionCodes;
	}
	
	
	
	
	

}// END CLASS

package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletRequest;

import messaging.casefile.reply.AssessmentReferralResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.common.Name;

public class AssessmentReferralForm extends ActionForm {
	
	private String juvenileNum;
	private String personMakingRef;
	private Name juvenileName;
	private String dateOfBirth;
	private String gender;
	private String language;
	private String raceOrEthinicity;
	private String referralDate;
	
	private String assessmentType;
	private String assessmentIDNumber;
	
	private String placementFacility=null;
	private String placementFacilityId;
	
	private String gangName=null;
	private String gangNameId;
	
	private String cliqueSet=null;
	private String cliqueSetId;
	
	private String reasonForReferral=null;
	private String reasonForReferralId;
	
	private String[] selectedReasonForReferrals;
	
	private String lvlOfGangInvolvement=null;
	private String lvlOfGangInvolvementId;
	private String parentNotified;
	private String parentNotifiedGangAssReq;

	//update assessment
	private String recommendations=null;
	private String recommendationsId;
	private String acceptedStatus; 
	
	//text area;
	private String comments;
	private String conclusion;
	private String otherReasonForReferral;
	private String otherReasonForReferralTxt;
	private String otherGangName;
	private String descHybrid;
	private String otherCliqueSet;
	private String rejectionReason;
	
	private String updateAssessment;
	private String status;
	
	private String action;
	private String secondaryAction;
	
	
	private String errorMsg;
	private String reportGenerated;
	private String createUserId;
	
	private Collection<AssessmentReferralResponseEvent> gangAssessmentRefList = new ArrayList<AssessmentReferralResponseEvent>();
	private List<String> selectedReasonForReferralsList=new ArrayList<String>();
	
	//US 175505 
	private String startDateAsStr;
	private String endDateAsStr;
	private String selectedStatusId;
	private int listTotal;
	private Collection<AssessmentReferralResponseEvent> gangAssessmentRefSearchList = new ArrayList<AssessmentReferralResponseEvent>();//to hold the list from left nav search
	//US 175505 ENDS

	/**
	 * @see ActionForm#reset(ActionMapping,
	 *      ServletRequest)
	 */
	public void reset(ActionMapping aMapping, ServletRequest aRequest)
	{
		super.reset(aMapping, aRequest);
		clear();
	}
	
	/**
	 * resets the list and flag attributes.
	 */
	public void clear()
	{
		this.gangAssessmentRefList= new ArrayList<AssessmentReferralResponseEvent>();
		this.placementFacilityId="";
		this.gangNameId="";
		this.cliqueSetId="";
		this.reasonForReferralId="";
		this.lvlOfGangInvolvementId="";
		this.selectedReasonForReferrals=new String[0];
		this.selectedReasonForReferralsList=new ArrayList<String>();
		this.otherReasonForReferral="";
		this.reasonForReferralId="";
		this.recommendationsId="";
		this.conclusion="";
		this.rejectionReason="";
		this.status="";
		this.otherReasonForReferralTxt="";
		this.parentNotified="";
		this.parentNotifiedGangAssReq = "";
	}
	
	public void clearAll()
	{
		this.gangAssessmentRefList= new ArrayList<AssessmentReferralResponseEvent>();
		this.placementFacilityId="";
		this.gangNameId="";
		this.cliqueSetId="";
		this.reasonForReferralId="";
		this.lvlOfGangInvolvementId="";
		this.selectedReasonForReferrals=new String[0];
		this.selectedReasonForReferralsList=new ArrayList<String>();
		this.otherReasonForReferral="";
		this.reasonForReferralId="";
		this.recommendationsId="";
		this.conclusion="";
		this.rejectionReason="";
		this.status="";
		this.otherReasonForReferralTxt="";
		this.parentNotified="";
		this.parentNotifiedGangAssReq = "";
		this.startDateAsStr = "";
		this.endDateAsStr = "";
		this.listTotal = 0;
		this.selectedStatusId = "";
	}
	
	
	/**
	 * resets the gang info form values.
	 */
	public void clearAssessmentReferralForm()
	{
		this.placementFacility="";
		this.gangName="";
		this.cliqueSet="";
		this.reasonForReferral="";
		this.lvlOfGangInvolvement="";
		this.recommendations="";
		this.comments="";
		this.conclusion="";
		this.otherReasonForReferral="";
		this.otherReasonForReferralTxt="";
		this.otherGangName="";
		this.descHybrid="";
		this.otherCliqueSet="";
		this.rejectionReason="";
		this.status="";
		this.acceptedStatus="";
		this.updateAssessment="";
		this.assessmentIDNumber="";
		this.parentNotified="";
		this.parentNotifiedGangAssReq = "";
	}

	
	public Collection getGangNames()
	{
		return CodeHelper.getGangNameCodes();
	}
	
	public Collection getCliqueSets()
	{
		return CodeHelper.getGangCliqueCodes();
	}
	
	public Collection getPlacementFacilities()
	{
		//return CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY,true);
	    	//task 173549 
		Collection facilityList;		
		Collection<JuvenileFacilityResponseEvent> activefacilityLst = new ArrayList<JuvenileFacilityResponseEvent>();
		facilityList = CodeHelper.getJuvenileFacilities(true);
		//filter
		Iterator iter = facilityList.iterator();
		while(iter.hasNext())
		{
		    JuvenileFacilityResponseEvent resp = (JuvenileFacilityResponseEvent) iter.next();
		    if(resp!=null && resp.getInactiveInd()==null )
		    {
			activefacilityLst.add(resp);
		    }
		}  
		return activefacilityLst;
	}
	
	public Collection getReasonForReferralList()
	{
		return CodeHelper.getGangAssessmentReasonForReferralCodes();
	}
	
	public Collection getLevelOfGangInvolvementList()
	{
		//unsorted the codes.
		return CodeHelper.getGangAssessmentLevelOfGangInvolvementCodes();
	}
	
	public Collection getRecommendationsList()
	{
		return CodeHelper.getRecommendationsCodes();
	}
	
	public Collection getAssessmentReferralTypeList()
	{
		return CodeHelper.getAssessmentReferralTypeCodes();
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
	 * @return the referralDate
	 */
	public String getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate the referralDate to set
	 */
	public void setReferralDate(String referralDate) {
		this.referralDate = referralDate;
	}
	
	/**
	 * @return the personMakingRef
	 */
	public String getPersonMakingRef() {
		return personMakingRef;
	}
	/**
	 * @param personMakingRef the personMakingRef to set
	 */
	public void setPersonMakingRef(String personMakingRef) {
		this.personMakingRef = personMakingRef;
	}
	/**
	 * @return the placementFacility
	 */
	public String getPlacementFacility() {
		return placementFacility;
	}
	/**
	 * @param placementFacility the placementFacility to set
	 */
	public void setPlacementFacility(String placementFacility) {
		this.placementFacility = placementFacility;
	}
	/**
	 * @return the placementFacilityId
	 */
	public String getPlacementFacilityId() {
		return placementFacilityId;
	}
	/**
	 * @param placementFacilityId the placementFacilityId to set
	 */
	public void setPlacementFacilityId(String placementFacilityId) {
		this.placementFacilityId = placementFacilityId;
	}
	/**
	 * @return the juvenileName
	 */
	public Name getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(Name juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the raceOrEthinicity
	 */
	public String getRaceOrEthinicity() {
		return raceOrEthinicity;
	}
	/**
	 * @param raceOrEthinicity the raceOrEthinicity to set
	 */
	public void setRaceOrEthinicity(String raceOrEthinicity) {
		this.raceOrEthinicity = raceOrEthinicity;
	}
	/**
	 * @return the gangName
	 */
	public String getGangName() {
		return gangName;
	}
	/**
	 * @param gangName the gangName to set
	 */
	public void setGangName(String gangName) {
		this.gangName = gangName;
	}
	/**
	 * @return the gangNameId
	 */
	public String getGangNameId() {
		return gangNameId;
	}
	/**
	 * @param gangNameId the gangNameId to set
	 */
	public void setGangNameId(String gangNameId) {
		this.gangNameId = gangNameId;
	}
	
	/**
	 * @return the lvlOfGangInvolvement
	 */
	public String getLvlOfGangInvolvement() {
		return lvlOfGangInvolvement;
	}
	/**
	 * @param lvlOfGangInvolvement the lvlOfGangInvolvement to set
	 */
	public void setLvlOfGangInvolvement(String lvlOfGangInvolvement) {
		this.lvlOfGangInvolvement = lvlOfGangInvolvement;
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
	 * @param assessmentType the assessmentType to set
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}

	/**
	 * @return the assessmentType
	 */
	public String getAssessmentType() {
		return assessmentType;
	}

	/**
	 * @param gangAssessmentRefList the gangAssessmentRefList to set
	 */
	public void setGangAssessmentRefList(Collection<AssessmentReferralResponseEvent> gangAssessmentRefList) {
		this.gangAssessmentRefList = gangAssessmentRefList;
	}

	/**
	 * @return the gangAssessmentRefList
	 */
	public Collection<AssessmentReferralResponseEvent> getGangAssessmentRefList() {
		return gangAssessmentRefList;
	}

	/**
	 * @param reasonForReferral the reasonForReferral to set
	 */
	public void setReasonForReferral(String reasonForReferral) {
		this.reasonForReferral = reasonForReferral;
	}

	/**
	 * @return the reasonForReferral
	 */
	public String isReasonForReferral() {
		return reasonForReferral;
	}

	/**
	 * @param cliqueSet the cliqueSet to set
	 */
	public void setCliqueSet(String cliqueSet) {
		this.cliqueSet = cliqueSet;
	}

	/**
	 * @return the cliqueSet
	 */
	public String getCliqueSet() {
		return cliqueSet;
	}

	/**
	 * @param cliqueSetId the cliqueSetId to set
	 */
	public void setCliqueSetId(String cliqueSetId) {
		this.cliqueSetId = cliqueSetId;
	}

	/**
	 * @return the cliqueSetId
	 */
	public String getCliqueSetId() {
		return cliqueSetId;
	}

	/**
	 * @param otherGangName the otherGangName to set
	 */
	public void setOtherGangName(String otherGangName) {
		this.otherGangName = otherGangName;
	}

	/**
	 * @return the otherGangName
	 */
	public String getOtherGangName() {
		return otherGangName;
	}

	/**
	 * @param descHybrid the descHybrid to set
	 */
	public void setDescHybrid(String descHybrid) {
		this.descHybrid = descHybrid;
	}

	/**
	 * @return the descHybrid
	 */
	public String getDescHybrid() {
		return descHybrid;
	}

	/**
	 * @param otherCliqueSet the otherCliqueSet to set
	 */
	public void setOtherCliqueSet(String otherCliqueSet) {
		this.otherCliqueSet = otherCliqueSet;
	}

	/**
	 * @return the otherCliqueSet
	 */
	public String getOtherCliqueSet() {
		return otherCliqueSet;
	}

	
	/**
	 * @return the otherReasonForReferral
	 */
	public String getOtherReasonForReferral() {
		return otherReasonForReferral;
	}

	/**
	 * @param otherReasonForReferral the otherReasonForReferral to set
	 */
	public void setOtherReasonForReferral(String otherReasonForReferral) {
		this.otherReasonForReferral = otherReasonForReferral;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param recommendations the recommendations to set
	 */
	public void setRecommendations(String recommendations) {
		this.recommendations = recommendations;
	}

	/**
	 * @return the recommendations
	 */
	public String getRecommendations() {
		return recommendations;
	}

	/**
	 * @param recommendationsId the recommendationsId to set
	 */
	public void setRecommendationsId(String recommendationsId) {
		this.recommendationsId = recommendationsId;
	}

	/**
	 * @return the recommendationsId
	 */
	public String getRecommendationsId() {
		return recommendationsId;
	}


	/**
	 * @return the rejectionReason
	 */
	public String getRejectionReason() {
		return rejectionReason;
	}

	/**
	 * @param rejectionReason the rejectionReason to set
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	/**
	 * @return the reasonForReferral
	 */
	public String getReasonForReferral() {
		return reasonForReferral;
	}

	/**
	 * @param reasonForReferralId the reasonForReferralId to set
	 */
	public void setReasonForReferralId(String reasonForReferralId) {
		this.reasonForReferralId = reasonForReferralId;
	}

	/**
	 * @return the reasonForReferralId
	 */
	public String getReasonForReferralId() {
		return reasonForReferralId;
	}

	/**
	 * @param lvlOfGangInvolvementId the lvlOfGangInvolvementId to set
	 */
	public void setLvlOfGangInvolvementId(String lvlOfGangInvolvementId) {
		this.lvlOfGangInvolvementId = lvlOfGangInvolvementId;
	}

	/**
	 * @return the lvlOfGangInvolvementId
	 */
	public String getLvlOfGangInvolvementId() {
		return lvlOfGangInvolvementId;
	}

	/**
	 * @param assessmentIDNumber the assessmentIDNumber to set
	 */
	public void setAssessmentIDNumber(String assessmentIDNumber) {
		this.assessmentIDNumber = assessmentIDNumber;
	}

	/**
	 * @return the assessmentIDNumber
	 */
	public String getAssessmentIDNumber() {
		return assessmentIDNumber;
	}

	/**
	 * @param conclusion the conclusion to set
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	/**
	 * @return the conclusion
	 */
	public String getConclusion() {
		return conclusion;
	}

	/**
	 * @param selectedReasonForReferrals the selectedReasonForReferrals to set
	 */
	public void setSelectedReasonForReferrals(
			String[] selectedReasonForReferrals) {
		this.selectedReasonForReferrals = selectedReasonForReferrals;
	}

	/**
	 * @return the selectedReasonForReferrals
	 */
	public String[] getSelectedReasonForReferrals() {
		return selectedReasonForReferrals;
	}

	/**
	 * @param selectedReasonForReferralsList the selectedReasonForReferralsList to set
	 */
	public void setSelectedReasonForReferralsList(
			List<String> selectedReasonForReferralsList) {
		this.selectedReasonForReferralsList = selectedReasonForReferralsList;
	}

	/**
	 * @return the selectedReasonForReferralsList
	 */
	public List<String> getSelectedReasonForReferralsList() {
		return selectedReasonForReferralsList;
	}

	/**
	 * @param otherReasonForReferralTxt the otherReasonForReferralTxt to set
	 */
	public void setOtherReasonForReferralTxt(String otherReasonForReferralTxt) {
		this.otherReasonForReferralTxt = otherReasonForReferralTxt;
	}

	/**
	 * @return the otherReasonForReferralTxt
	 */
	public String getOtherReasonForReferralTxt() {
		return otherReasonForReferralTxt;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param updateAssessment the updateAssessment to set
	 */
	public void setUpdateAssessment(String updateAssessment) {
		this.updateAssessment = updateAssessment;
	}

	/**
	 * @return the updateAssessment
	 */
	public String getUpdateAssessment() {
		return updateAssessment;
	}

	/**
	 * @param acceptedStatus the acceptedStatus to set
	 */
	public void setAcceptedStatus(String acceptedStatus) {
		this.acceptedStatus = acceptedStatus;
	}

	/**
	 * @return the acceptedStatus
	 */
	public String getAcceptedStatus() {
		return acceptedStatus;
	}

	/**
	 * @param errorMesg the errorMesg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the errorMesg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param reportGenerated the reportGenerated to set
	 */
	public void setReportGenerated(String reportGenerated) {
		this.reportGenerated = reportGenerated;
	}

	/**
	 * @return the reportGenerated
	 */
	public String getReportGenerated() {
		return reportGenerated;
	}

	/**
	 * @param createUserId the createUserId to set
	 */
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the createUserId
	 */
	public String getCreateUserId() {
		return createUserId;
	}
	
	public String getParentNotified()
	{
	    return parentNotified;
	}

	public void setParentNotified(String parentNotified)
	{
	    this.parentNotified = parentNotified;
	}
	
	public String getParentNotifiedGangAssReq()
	{
	    return this.parentNotifiedGangAssReq;
	}

	public void setParentNotifiedGangAssReq(String parentNotifiedGangAssReq)
	{
	    this.parentNotifiedGangAssReq = parentNotifiedGangAssReq;
	}

	public String getStartDateAsStr()
	{
	    return startDateAsStr;
	}

	public void setStartDateAsStr(String startDateAsStr)
	{
	    this.startDateAsStr = startDateAsStr;
	}

	public String getEndDateAsStr()
	{
	    return endDateAsStr;
	}

	public void setEndDateAsStr(String endDateAsStr)
	{
	    this.endDateAsStr = endDateAsStr;
	}

	public String getSelectedStatusId()
	{
	    return selectedStatusId;
	}

	public void setSelectedStatusId(String selectedStatusId)
	{
	    this.selectedStatusId = selectedStatusId;
	}

	public int getListTotal()
	{
	    return listTotal;
	}

	public void setListTotal(int listTotal)
	{
	    this.listTotal = listTotal;
	}

	public Collection<AssessmentReferralResponseEvent> getGangAssessmentRefSearchList()
	{
	    return gangAssessmentRefSearchList;
	}

	public void setGangAssessmentRefSearchList(Collection<AssessmentReferralResponseEvent> gangAssessmentRefSearchList)
	{
	    this.gangAssessmentRefSearchList = gangAssessmentRefSearchList;
	}
}


package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import messaging.casefile.reply.AssessmentReferralResponseEvent;


public class GangAssessmentReferralPrintReportBean {
	
	private String juvenileNumber;
	private String personMakingReferral;
	private String juvenileName;
	private String dateOfBirth;
	private String gender;
	private String language;
	private String raceOrEthinicity;
	private String placementFacility;
	private String referralDate;
	private String lvlOfGangInvolvement=null;
	private String gangName=null;
	private String cliqueSet=null;
	private String comments;
	private String conclusion;
	

	//update assessment
	private String recommendations=null;
	private String recommendationsId;
	private String acceptedStatus; 
	
	private String otherReasonForReferral;
	private String otherGangName;
	private String descHybrid;
	private String otherCliqueSet;
	private String rejectionReason;
	private String status;
	
	private String assessmentIDNumber;
	private String[] selectedReasonForReferrals;
	private List<String> selectedReasonForReferralsList=new ArrayList<String>();
	private String parentNotified;	
	private String parentNotifiedGangAssReq;
	//US 180013
	private Collection<AssessmentReferralResponseEvent> gangAssessmentRefList = new ArrayList<AssessmentReferralResponseEvent>();
	private int total;
	private String todaysDate;
	private String fromDate;
	private String toDate;
	
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
	 * @return the juvenileNumber
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber the juvenileNumber to set
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	/**
	 * @return the personMakingReferral
	 */
	public String getPersonMakingReferral() {
		return personMakingReferral;
	}
	/**
	 * @param personMakingReferral the personMakingReferral to set
	 */
	public void setPersonMakingReferral(String personMakingReferral) {
		this.personMakingReferral = personMakingReferral;
	}
	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
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
	 * @param referralDate the referralDate to set
	 */
	public void setReferralDate(String referralDate) {
		this.referralDate = referralDate;
	}
	/**
	 * @return the referralDate
	 */
	public String getReferralDate() {
		return referralDate;
	}
	/**
	 * @param gangName the gangName to set
	 */
	public void setGangName(String gangName) {
		this.gangName = gangName;
	}
	/**
	 * @return the gangName
	 */
	public String getGangName() {
		return gangName;
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
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
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
	 * @param otherReasonForReferral the otherReasonForReferral to set
	 */
	public void setOtherReasonForReferral(String otherReasonForReferral) {
		this.otherReasonForReferral = otherReasonForReferral;
	}
	/**
	 * @return the otherReasonForReferral
	 */
	public String getOtherReasonForReferral() {
		return otherReasonForReferral;
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
	 * @param rejectionReason the rejectionReason to set
	 */
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	/**
	 * @return the rejectionReason
	 */
	public String getRejectionReason() {
		return rejectionReason;
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
	public Collection<AssessmentReferralResponseEvent> getGangAssessmentRefList()
	{
	    return gangAssessmentRefList;
	}
	public void setGangAssessmentRefList(Collection<AssessmentReferralResponseEvent> gangAssessmentRefList)
	{
	    this.gangAssessmentRefList = gangAssessmentRefList;
	}
	public int getTotal()
	{
	    return total;
	}
	public void setTotal(int total)
	{
	    this.total = total;
	}
	public String getTodaysDate()
	{
	    return todaysDate;
	}
	public void setTodaysDate(String todaysDate)
	{
	    this.todaysDate = todaysDate;
	}
	public String getFromDate()
	{
	    return fromDate;
	}
	public void setFromDate(String fromDate)
	{
	    this.fromDate = fromDate;
	}
	public String getToDate()
	{
	    return toDate;
	}
	public void setToDate(String toDate)
	{
	    this.toDate = toDate;
	}
	
}

/*
 * Created on Sep 16, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase.form.riskanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.juvenilecase.reply.CommunityAssessmentEvent;
import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.InterviewAssessmentEvent;
import messaging.juvenilecase.reply.ProgressAssessmentEvent;
import messaging.juvenilecase.reply.ReferralAssessmentEvent;
import messaging.juvenilecase.reply.ResidentialAssessmentEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.TestingAssessmentEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author dwilliamson
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RiskAnalysisForm extends ActionForm
{
    	private String isPCSPrintable="false";
	
	public String getIsPCSPrintable()
	{
	    return isPCSPrintable;
	}


	public void setIsPCSPrintable(String isPCSPrintable)
	{
	    this.isPCSPrintable = isPCSPrintable;
	}

	private String action;
	private String juvenileNum;
	private String casefileID;
	private String recommendationScore;
	private boolean allowUpdates;
	
	private Date riskAssessmentDate;
	private String riskAssessmentType;
	private String riskAssessmentTypeDesc;
	private String finalScore; 

	private Collection assessmentList;
	private List questions;
	//added for list of pre petition and pre adjudication active casefiles in the dropdown for TJJD casefile transfer
	private List riskActiveCaseFiles = new ArrayList();

	// These are used for displaying the details of the risk Assessment done when
	// the user clicks on the Hyperlink
	// in the Previous Risk Analysis Assessments done list.
	private Collection assessmentDetailsResponseList;
	private ReferralAssessmentEvent referralAssessEvent;
	private InterviewAssessmentEvent interviewAssessEvent;
	private ResidentialAssessmentEvent residentialAssessEvent;
	private CommunityAssessmentEvent communityAssessEvent;
	private ProgressAssessmentEvent progressAssessEvent;
	private TestingAssessmentEvent testingAssessEvent; 
	private CourtReferralAssessmentEvent courtReferralAssessmentEvent; 
	
	//private String recommendation;
	private List releaseOverrideReasons;
	private List detentionOverrideReasons;
	private List overrideOtherReasons;
		
	// this relates to the radio button, "Override Recommendation"?
	private boolean recommendationOverridden = false ;

	// this relates to one of the radio button choices for override;
	// there are currently six radio button choices
	private String overRiddenReasonCd = "" ;
	private String overRiddenReasonDesc = "" ;
	
	// if the user selected OVERRIDE_TYPE_OTHER, then the user is allowed 
	// to enter free-form text for the reason - this is where it's stored.
	private String overRideType = "";
	
	private String overRiddenReasonOther = "" ; //Release
	private String overRiddenReasonDetentionOther = "" ; //Detention
	
	private String assessmentId;	
	
	private String overNinetyDays;
	private String mode;
	private String modReason;
	private String filteredModReason;
	private String riskFormulaId;
    
	private List recommendations;
	private List questionAnswers = new ArrayList();
	private List processedQuestionAnswers;
	private List processedViewQuestionAnswers;
	private String secondaryAction;
 	
	private String effectiveDate;
	String filteredModReason1;
	
	public void clear()
	{
		recommendationOverridden = false ;
		overRiddenReasonCd = UIConstants.EMPTY_STRING ;
		overRiddenReasonDesc = UIConstants.EMPTY_STRING ;
		overRideType = UIConstants.EMPTY_STRING ;
		overRiddenReasonOther = UIConstants.EMPTY_STRING ; //Release
		overRiddenReasonDetentionOther = UIConstants.EMPTY_STRING; //Detention
	}
	
	
	/**
	 * @return
	 */
	public Collection getAssessmentList()
	{
		if( assessmentList == null )
		{
			assessmentList = new ArrayList();
		}
		return assessmentList;
	}

	/**
	 * @return
	 */
	public String getRecommendationScore()
	{
		return recommendationScore;
	}

	/**
	 * @return
	 */
	public Date getRiskAssessmentDate()
	{
		if (getMode() != null && getMode().equalsIgnoreCase("update")) {
			return riskAssessmentDate;
		} else if (riskAssessmentDate != null){
			return riskAssessmentDate;
		} else {
			return new Date();
		}
	}

	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}


	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}


	/**
	 * @return
	 */
	public String getRiskAssessmentType()
	{
		return riskAssessmentType;
	}
	

	/**
	 * @return finalscore
	 */
	public String getFinalScore() {
		return finalScore;
	}
	
	/**
	 * @param finalscore
	 */
	public void setFinalScore(String finalScore) {
		this.finalScore = finalScore;
	}

	/**
	 * @param collection
	 */
	public void setAssessmentList(Collection collection)
	{
		assessmentList = collection;
	}

	/**
	 * @param string
	 */
	public void setRecommendationScore(String string)
	{
		recommendationScore = string;
	}

	/**
	 * @param aDate
	 */
	public void setRiskAssessmentDate(Date aDate)
	{
		riskAssessmentDate = aDate;
	}

	/**
	 * @param string
	 */
	public void setRiskAssessmentType(String string)
	{
		riskAssessmentType = string;
	}

	/**
	 * @return
	 */
	public String getCasefileID()
	{
		return casefileID;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(String string)
	{
		casefileID = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @return
	 */
	public Collection getAssessmentDetailsResponseList()
	{
		if( assessmentDetailsResponseList == null )
		{
			assessmentDetailsResponseList = new ArrayList();
		}
		return assessmentDetailsResponseList;
	}

	/**
	 * @param riskAssessmentDetails
	 */
	public void setAssessmentDetailsResponseList(Collection riskAssessmentDetails)
	{
		assessmentDetailsResponseList = riskAssessmentDetails;

	}

	/**
	 * @return
	 */
	public InterviewAssessmentEvent getInterviewAssessEvent()
	{
		return interviewAssessEvent;
	}

	/**
	 * @return
	 */
	public ReferralAssessmentEvent getReferralAssessEvent()
	{
		return referralAssessEvent;
	}

	/**
	 * @param event
	 */
	public void setInterviewAssessEvent(InterviewAssessmentEvent event)
	{
		interviewAssessEvent = event;
	}

	/**
	 * @param event
	 */
	public void setReferralAssessEvent(ReferralAssessmentEvent event)
	{
		referralAssessEvent = event;
	}

	/**
	 * @return
	 */
	public ResidentialAssessmentEvent getResidentialAssessEvent()
	{
		return residentialAssessEvent;
	}

	/**
	 * @param event
	 */
	public void setResidentialAssessEvent(ResidentialAssessmentEvent event)
	{
		residentialAssessEvent = event;
	}

	/**
	 * @return
	 */
	public CommunityAssessmentEvent getCommunityAssessEvent()
	{
		return communityAssessEvent;
	}

	/**
	 * @param event
	 */
	public void setCommunityAssessEvent(CommunityAssessmentEvent event)
	{
		communityAssessEvent = event;
	}

	/**
	 * @return
	 */
	public ProgressAssessmentEvent getProgressAssessEvent()
	{
		return progressAssessEvent;
	}

	/**
	 * @param event
	 */
	public void setProgressAssessEvent(ProgressAssessmentEvent event)
	{
		progressAssessEvent = event;
	}

	/**
	 * @return
	 */
	public TestingAssessmentEvent getTestingAssessEvent()
	{
		return testingAssessEvent;
	}

	/**
	 * @param event
	 */
	public void setTestingAssessEvent(TestingAssessmentEvent event)
	{
		testingAssessEvent = event;
	}
	
	public void setCourtReferralAssessmentEvent(
			CourtReferralAssessmentEvent courtReferralAssessmentEvent) {
		this.courtReferralAssessmentEvent = courtReferralAssessmentEvent;
	}


	public CourtReferralAssessmentEvent getCourtReferralAssessmentEvent() {
		return courtReferralAssessmentEvent;
	}
			
	/**
	 * @return recommendationOverridden
	 */
	public boolean isRecommendationOverridden() {
		return recommendationOverridden;
	}

	/**
	 * @param recommendationOverridden
	 */
	public void setRecommendationOverridden(boolean recommendationOverridden) {
		this.recommendationOverridden = recommendationOverridden;
	}

	/**
	 * @return overRiddenReasonCd
	 */
	public String getOverRiddenReasonCd() {
		return overRiddenReasonCd;
	}

	/**
	 * @param overRiddenReasonCd
	 */
	public void setOverRiddenReasonCd(String overRiddenReasonCd) {
		this.overRiddenReasonCd = overRiddenReasonCd;
	}

	/**
	 * @return overRiddenReasonOther
	 */
	public String getOverRiddenReasonOther() {
		return overRiddenReasonOther;
	}

	/**
	 * @param overRiddenReasonOther
	 */
	public void setOverRiddenReasonOther(String overRiddenReasonOther) {
		this.overRiddenReasonOther = overRiddenReasonOther;
	}

	/**
	 * @return releaseOverrideReasons
	 */
	public List getReleaseOverrideReasons() {
		return releaseOverrideReasons;
	}

	/**
	 * @param releaseOverrideReasons
	 */
	public void setReleaseOverrideReasons(List releaseOverrideReasons) {
		this.releaseOverrideReasons = releaseOverrideReasons;
	}

	/**
	 * @return detentionOverrideReasons
	 */
	public List getDetentionOverrideReasons() {
		return detentionOverrideReasons;
	}

	/**
	 * @param detentionOverrideReasons
	 */
	public void setDetentionOverrideReasons(List detentionOverrideReasons) {
		this.detentionOverrideReasons = detentionOverrideReasons;
	}

	/**
	 * @return overrideOtherReasons
	 */
	public List getOverrideOtherReasons() {
		return overrideOtherReasons;
	}

	/**
	 * @param overrideOtherReasons
	 */
	public void setOverrideOtherReasons(List overrideOtherReasons) {
		this.overrideOtherReasons = overrideOtherReasons;
	}

	/**
	 * @return assessmentId
	 */
	public String getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessmentId
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @return the action
	 */
	public String getAction()
	{
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action)
	{
		this.action = action;
	}
	/**
	 * @return overRiddenReasonDesc
	 */
	public String getOverRiddenReasonDesc() {
		return overRiddenReasonDesc;
	}
	/**
	 * @param overRiddenReasonDesc
	 */
	public void setOverRiddenReasonDesc(String overRiddenReasonDesc) {
		this.overRiddenReasonDesc = overRiddenReasonDesc;
	}

	/**
	 * @return overRideType
	 */
	public String getOverRideType() {
		return overRideType;
	}

	/**
	 * @param overRideType
	 */
	public void setOverRideType(String overRideType) {
		this.overRideType = overRideType;
	}


	/**
	 * @param overNinetyDays
	 */
	public void setOverNinetyDays(String overNinetyDays) {
		this.overNinetyDays = overNinetyDays;
	}


	/**
	 * @return
	 */
	public String getOverNinetyDays() {
		return overNinetyDays;
	}


	public List getQuestions() {
		return questions;
	}


	public void setQuestions(List questions) {
		this.questions = questions;
	}
	
	public List getRiskActiveCaseFiles() {
		return riskActiveCaseFiles;
	}


	public void setRiskActiveCaseFiles(List riskActiveCaseFiles) {
		this.riskActiveCaseFiles = riskActiveCaseFiles;
	}


	public void setOverRiddenReasonDetentionOther(
			String overRiddenReasonDetentionOther) {
		this.overRiddenReasonDetentionOther = overRiddenReasonDetentionOther;
	}


	public String getOverRiddenReasonDetentionOther() {
		return overRiddenReasonDetentionOther;
	}

	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @param modreason
	 */
	public void setModReason(String modReason) {
		this.modReason = modReason;
	}

	/**
	 * @return
	 */
	public String getModReason() {
		return modReason;
	}


	public void setRiskFormulaId(String riskFormulaId) {
		this.riskFormulaId = riskFormulaId;
	}


	public String getRiskFormulaId() {
		return riskFormulaId;
	}
	public void setRecommendations(List recommendations) {
		this.recommendations = recommendations;
	}

	public List getRecommendations() {
		return recommendations;
	}


	public void setQuestionAnswers(List questionAnswers) {
		this.questionAnswers = questionAnswers;
	}


	public List getQuestionAnswers() {
		return questionAnswers;
	}


	public void setProcessedQuestionAnswers(List processedQuestionAnswers) {
		this.processedQuestionAnswers = processedQuestionAnswers;
	}


	public List getProcessedQuestionAnswers() {
		return processedQuestionAnswers;
	}


	public void setProcessedViewQuestionAnswers(
			List processedViewQuestionAnswers) {
		this.processedViewQuestionAnswers = processedViewQuestionAnswers;
	}


	public List getProcessedViewQuestionAnswers() {
		return processedViewQuestionAnswers;
	}


	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}


	public String getSecondaryAction() {
		return secondaryAction;
	}


	public void setRiskAssessmentTypeDesc(String riskAssessmentTypeDesc) {
		this.riskAssessmentTypeDesc = riskAssessmentTypeDesc;
	}


	public String getRiskAssessmentTypeDesc() {
		return riskAssessmentTypeDesc;
	}
	
	public void reset( ActionMapping aMapping, HttpServletRequest aRequest )
	{
		   String clearThem = aRequest.getParameter("clearRiskCheckBoxes");
		   if (clearThem != null){
			   
		        List qAns = this.getQuestionAnswers();
		        RiskQuestionResponseEvent rqre = null;
		        
		        for (int i = 0; i < qAns.size(); i++) {
					rqre = (RiskQuestionResponseEvent) qAns.get(i);
					rqre.setSelectedAnswerIDs(new String[0]);
					rqre.setSelectedChronicIDs(new String[0]);
		        }
		        rqre = null;
		   }
	}


	public String getEffectiveDate()
	{
	    return effectiveDate;
	}


	public void setEffectiveDate(String effectiveDate)
	{
	    this.effectiveDate = effectiveDate;
	}


	public String getFilteredModReason()
	{
	    return filteredModReason;
	}


	public void setFilteredModReason(String filteredModReason)
	{
	    this.filteredModReason = filteredModReason;
	}
	
	/*
	 * PK 3/17/23 - added new property to remove tag from report for PREA
	 */
	public String getFilteredModReason1(){
	    if(this.modReason!=null)	    
		return this.modReason.toString().replaceAll("\\s*\\[.*?\\]\\s*", " ");
	    else
		return "";
	}

}

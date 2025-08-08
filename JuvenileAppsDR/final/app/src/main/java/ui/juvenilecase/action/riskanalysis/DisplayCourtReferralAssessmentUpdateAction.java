package ui.juvenilecase.action.riskanalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenilecase.reply.CourtReferralAssessmentEvent;
import messaging.juvenilecase.reply.CourtReferralPrefillResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskSuggestedCasePlanDomainResponseEvent;
import messaging.juvenilecase.reply.RiskWeightedResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import messaging.riskanalysis.GetRiskQuestionAnswersEvent;
import messaging.riskanalysis.RefreshReferralPrefillDataEvent;
import messaging.riskanalysis.reply.RiskAssessmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class DisplayCourtReferralAssessmentUpdateAction extends Action
{
	private static final String BRIEFING_FORM_STR = "juvenileBriefingDetailsForm" ; //added by srtitwisha for TJJD Risk transfer

    /**
     * @roseuid 433D89CB021D
     */
    public DisplayCourtReferralAssessmentUpdateAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get both RiskAnalysis and RiskReferral Forms
//        RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
//        RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aForm;
        
        //Get Casefile and Juvenile Number From Risk Form
//        String casefileID = riskForm.getCasefileID();
//        String juvenileNum = riskForm.getJuvenileNum();
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
    	RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aRequest.getSession().getAttribute("riskCourtReferralForm");
    	if (refForm == null){
    		refForm = new RiskAssessmentCourtReferralForm();
    		aRequest.getSession().setAttribute("riskCourtReferralForm", refForm);
    	}
        //Set Casefile and Juvenile Number on Referral Form
//        refForm.setCasefileID(riskForm.getCasefileID());
//        refForm.setJuvenileNum(riskForm.getJuvenileNum());
        
        String theMode = aRequest.getParameter("mode");
        riskForm.setMode(theMode);
        
        //Get Assessment ID and Type
		String assessmentId = aRequest.getParameter("assessmentId");
		String assessmentType = aRequest.getParameter(RiskAnalysisConstants.RISK_ASSESSMENT_TYPE);
		if( assessmentId == null ) {
			assessmentId = riskForm.getAssessmentId();
		}
		if( assessmentType == null ) {
			assessmentType = riskForm.getRiskAssessmentType();
		}
                
        //Get Questions and Answers
        GetRiskQuestionAnswersEvent riskQuestionAnswersEvent = (GetRiskQuestionAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKQUESTIONANSWERS);
        riskQuestionAnswersEvent.setAssessmentType(assessmentType); 
        riskQuestionAnswersEvent.setFormulaId(riskForm.getRiskFormulaId());
        
        CompositeResponse questionsAndAnswers = 
        	MessageUtil.postRequest(riskQuestionAnswersEvent); //Builds Questions and Answers in HTML Elements
        
        //Get Assessment Details
		GetRiskAssessmentDetailsEvent event = (GetRiskAssessmentDetailsEvent)EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETRISKASSESSMENTDETAILS);
		event.setAssessmentID(assessmentId);
		event.setAssessmentType(assessmentType);
		
		CompositeResponse composite = MessageUtil.postRequest(event);
		
		//Get User Answers to questions
		List answersToQuestions = 
			MessageUtil.compositeToList(composite, RiskAnswerResponseEvent.class); //Stored user Answers to Questions
		RiskAnswerResponseEvent[] riskAnswerResponseEvents = 
    		(RiskAnswerResponseEvent[]) answersToQuestions.toArray(new RiskAnswerResponseEvent[answersToQuestions.size()]);
		
		//Get Referral Specific Details
		CourtReferralAssessmentEvent refAssessEvent = (CourtReferralAssessmentEvent)MessageUtil.filterComposite(composite, CourtReferralAssessmentEvent.class);
		refForm.setCollateralVisits(refAssessEvent.getCollateralVisits());
		refForm.setCourtDispositionTJPC(refAssessEvent.getCourtDispositionTJPC());
		refForm.setFace2FaceVisits(refAssessEvent.getFace2FaceVisits());
		refForm.setJjsCourtDecision(refAssessEvent.getJjsCourtDecision());
		refForm.setJjsCourtDisposition(refAssessEvent.getJjsCourtDisposition());
	
		RiskSuggestedCasePlanDomainResponseEvent cpdre = (RiskSuggestedCasePlanDomainResponseEvent) MessageUtil.filterComposite(composite, RiskSuggestedCasePlanDomainResponseEvent.class);
		if (cpdre != null){
			refForm.setSuggestedCasePlanDomains(cpdre.getSuggestedCasePlanDomainNames());
		}
		
		RiskAssessmentResponseEvent riskAssessEvent = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);

		RefreshReferralPrefillDataEvent prefillEvt = (RefreshReferralPrefillDataEvent) EventFactory
        .getInstance(JuvenileRiskAnalysisControllerServiceNames.REFRESHREFERRALPREFILLDATA);
		prefillEvt.setAssessmentId(assessmentId);
		
		//Refresh Referral Data on update
		CourtReferralPrefillResponseEvent prefillResp = (CourtReferralPrefillResponseEvent) MessageUtil.postRequest(prefillEvt, CourtReferralPrefillResponseEvent.class);

		//Groups Raw Questions & Answners for Display
        List questionAnswerGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers(questionsAndAnswers);
        
        RiskQuestionResponseEvent[] riskQuestionResponseEvents = 
    		(RiskQuestionResponseEvent[]) questionAnswerGroup.toArray(new RiskQuestionResponseEvent[questionAnswerGroup.size()]);        
        
        //Create Final List of Refferal Information Question & Answers with User Reponses
        List <RiskQuestionResponseEvent> finalList = UIRiskAnalysisHelper.mergeUserReponsesWithQuestions(riskQuestionResponseEvents,
        		riskAnswerResponseEvents);

    	for (int i = 0; i < finalList.size(); i++) {
    		RiskQuestionResponseEvent qre = finalList.get(i);
		
    		if (qre.getControlCode() != null && qre.getControlCode().length() > 0) {
    			if (qre.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_VIOLENT_FELONY)) {
       			String violentFelonyAnswer = "";
       			if (prefillResp.isVoilentFelony()) {
        				violentFelonyAnswer = RiskAnalysisConstants.YES;
        			} else  {
        				violentFelonyAnswer = RiskAnalysisConstants.NO;
        			} 
       				preSetOnSelectedAnswerId(violentFelonyAnswer, qre);
    			} else if (qre.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_REFERRALS_INFORMATION)) {
        			qre.setSelectedAnswerID(prefillResp.getReferralsInformation());
       				//qre.setUseAnswerText(false);  //If there is already an answer to this question,useAnswerText was set to true.
         		} else if (qre.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.COURT_REFERRAL_TOTAL_REFFERRALS)) {
         			String refAnswer = UIConstants.EMPTY_STRING;
        			if (prefillResp.getTotalReferralsToJuvenile() >= 0 
        					&& prefillResp.getTotalReferralsToJuvenile() <= 20){
        				refAnswer = String.valueOf(prefillResp.getTotalReferralsToJuvenile());
        			} 
        			preSetOnSelectedAnswerId(refAnswer, qre);
        			//qre.setSelectedAnswerID(String.valueOf(prefillResp.getTotalReferralsToJuvenile()));
       				//qre.setUseAnswerText(true);  //If there is already an answer to this question,useAnswerText was set to true.
         		}
       		}        				
   		}
    	//}
        //Set Final List of Refferal Information Question & Answers with User Reponses **/
        riskForm.setQuestionAnswers(finalList);	
		
        //Set Mod Reason
        riskForm.setModReason(riskAssessEvent.getModReason());
        riskForm.setRiskAssessmentDate(riskAssessEvent.getEnteredDate());
			
   
		//getting casefile details from session form
        HttpSession session= aRequest.getSession();
        JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm)
		session.getAttribute(BRIEFING_FORM_STR);

		if(form != null)
		{
			Collection casefiles = form.getCasefiles();
			List riskCaseFilesList = new ArrayList(); 
			if(casefiles != null)
			{
				Iterator iter = casefiles.iterator();
				while (iter.hasNext()){
					JuvenileCasefileSearchResponseEvent respEvt = (JuvenileCasefileSearchResponseEvent) iter.next();
					//get only Active case file with PRE-ADJUDICATION and PRE-PETITION supervision category 
					String supCategory= respEvt.getSupervisionCategory();
					String supervisionTypeDesc = respEvt.getSupervisionType(); 
					if(supCategory == null || supCategory.length() < 1){
						//to get the supervision type id from supervision type using codetable												
						Collection codes = CodeHelper.getCodes(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE);
						Iterator codeIter = codes.iterator();
                        String supTypeId = "";
                        while(codeIter.hasNext()){
                        	CodeResponseEvent respEvent = (CodeResponseEvent)codeIter.next();
                                String currentCodeDesc = respEvent.getDescription();
                                if(currentCodeDesc.equalsIgnoreCase(supervisionTypeDesc)){
                                	supTypeId = respEvent.getCode();
                                	break;
                                }
                        }
                        //Getting supervision category from supervision type Id
                        supCategory = UIJuvenileCaseworkHelper.getSupCatFromType(supTypeId);						 
					}
					//checking only active PRE-ADJUDICATION and PRE-PETITION supervision category
					if(supCategory != null && (supCategory.equals("AD") || supCategory.equals("PP")) && respEvt.getCaseStatus().equals("ACTIVE")){						
						riskCaseFilesList.add(respEvt);
					}
					
				}
			}
			if(riskCaseFilesList != null){				
				riskForm.setRiskActiveCaseFiles(riskCaseFilesList);
			}
		}
		
		return aMapping.findForward("success");
	
    }
	private void preSetOnSelectedAnswerId(
			String stringToCompare,
			RiskQuestionResponseEvent question) {
		Iterator<RiskWeightedResponseEvent> iter = question.getAnswers().iterator();
		if( iter != null && iter.hasNext() ) {
			while(iter.hasNext()) {
				RiskWeightedResponseEvent riskWeightedResponseEvent = iter.next();
				
				if (stringToCompare.equalsIgnoreCase(riskWeightedResponseEvent.getAnswerText())) {
					question.setSelectedAnswerID(String.valueOf(riskWeightedResponseEvent.getWeightedResponseID()));
					break;
				}
				
			}
		}
	}

}

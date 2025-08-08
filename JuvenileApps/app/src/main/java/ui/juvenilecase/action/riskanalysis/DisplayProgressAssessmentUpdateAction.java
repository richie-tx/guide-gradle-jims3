package ui.juvenilecase.action.riskanalysis;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.ProgressPrefillResponseEvent;
import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.GetProgressPrefillInfoEvent;
import messaging.riskanalysis.GetRiskAssessmentDetailsEvent;
import messaging.riskanalysis.GetRiskQuestionAnswersEvent;
import messaging.riskanalysis.reply.RiskAssessmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class DisplayProgressAssessmentUpdateAction extends Action {
	   /**
     * @roseuid 433D89CB021D
     */
    public DisplayProgressAssessmentUpdateAction()
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
        RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
       
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

		RiskAssessmentResponseEvent refAssessEvent = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);

		GetProgressPrefillInfoEvent getPrefillInfoEvent = (GetProgressPrefillInfoEvent)
			EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETPROGRESSPREFILLINFO);

		getPrefillInfoEvent.setCaseFileId(riskForm.getCasefileID());
		getPrefillInfoEvent.setJuvenileNumber(riskForm.getJuvenileNum());
		getPrefillInfoEvent.setAssessmentDate(refAssessEvent.getEnteredDate());
		getPrefillInfoEvent.setUpdateRisk(true);
		getPrefillInfoEvent.setRiskAnalysisId(assessmentId);
		
		CompositeResponse response = MessageUtil.postRequest(getPrefillInfoEvent);

		ProgressPrefillResponseEvent prefillResponse = (ProgressPrefillResponseEvent)
				MessageUtil.filterComposite(response, ProgressPrefillResponseEvent.class);
		
		// Groups Raw Questions & Answers for Display
        List questionAnswerGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers(questionsAndAnswers);
        
        RiskQuestionResponseEvent[] riskQuestionResponseEvents = 
    		(RiskQuestionResponseEvent[]) questionAnswerGroup.toArray(new RiskQuestionResponseEvent[questionAnswerGroup.size()]);        
        
        //Create Final List of Question & Answers with User Reponses
        List <RiskQuestionResponseEvent> finalList = UIRiskAnalysisHelper.mergeUserReponsesWithQuestions(riskQuestionResponseEvents,
        		riskAnswerResponseEvents);

        if (prefillResponse != null){
        	for (int i = 0; i < finalList.size(); i++) {
        		RiskQuestionResponseEvent qre = finalList.get(i);
			
        		if (qre.getControlCode() != null && qre.getControlCode().length() > 0) {
        			if (qre.getControlCode().equalsIgnoreCase(RiskAnalysisConstants.PROGRESS_SUPERVISION_MONTHS)) {
        				String months = new Integer(prefillResponse.getSupervisionMonths()).toString();
        				qre.setSelectedAnswerID(months);
        				qre.setUseAnswerText(false);  //If there is already an answer to this question,useAnswerText was set to true.
        				break;
        			}         				
        		}
        	}
         }
        //Set Final List of Question & Answers with User Reponses **/
        riskForm.setQuestionAnswers(finalList);	
		
        riskForm.setModReason(refAssessEvent.getModReason());
        riskForm.setRiskAssessmentDate(refAssessEvent.getEnteredDate());
 
		return aMapping.findForward("success");

    }
}

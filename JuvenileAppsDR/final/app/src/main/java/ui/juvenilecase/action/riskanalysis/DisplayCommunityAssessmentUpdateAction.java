package ui.juvenilecase.action.riskanalysis;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskAnswerResponseEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
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

public class DisplayCommunityAssessmentUpdateAction extends Action {
	   /**
     * @roseuid 433D89CB021D
     */
    public DisplayCommunityAssessmentUpdateAction()
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
    	//Get both RiskAnalysis and RiskCommunity Forms
//        RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
//        RiskAssessmentCommunityForm refForm = (RiskAssessmentCommunityForm) aForm;
        
        //Get Casefile and Juvenile Number From Risk Form
//        String casefileID = riskForm.getCasefileID();
//        String juvenileNum = riskForm.getJuvenileNum();
        
        //Set Casefile and Juvenile Number on Interview Form
//        refForm.setCasefileID(casefileID);
//        refForm.setJuvenileNum(juvenileNum);
  
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
		
		//Get Community Specific Details
		//CommunityAssessmentEvent refAssessEvent = (CommunityAssessmentEvent)MessageUtil.filterComposite(composite, CommunityAssessmentEvent.class);
		RiskAssessmentResponseEvent riskAssessResponseEvent = (RiskAssessmentResponseEvent)MessageUtil.filterComposite(composite, RiskAssessmentResponseEvent.class);

		// Groups Raw Questions & Answners for Display
        List questionAnswerGroup = UIRiskAnalysisHelper.groupQuestionsAndAnswers(questionsAndAnswers);
        RiskQuestionResponseEvent[] riskQuestionResponseEvents = 
    		(RiskQuestionResponseEvent[]) questionAnswerGroup.toArray(new RiskQuestionResponseEvent[questionAnswerGroup.size()]);        
        
        //Create Final List of Question & Answers with User Reponses
        List finalList = UIRiskAnalysisHelper.mergeUserReponsesWithQuestions(riskQuestionResponseEvents,
        		riskAnswerResponseEvents);

        //Set Final List Question & Answers with User Reponses **/
        riskForm.setQuestionAnswers(finalList);	
		
		//Set Interview Specific Information

        riskForm.setModReason(riskAssessResponseEvent.getModReason());
        riskForm.setRiskAssessmentDate(riskAssessResponseEvent.getEnteredDate());
 
		return aMapping.findForward("success");

    }

}

package ui.juvenilecase.riskanalysis.action;

import messaging.codetable.GetRiskAnalysisControlCodesEvent;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.RiskAnswerDeleteForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskAnswerDeleteSummaryAction extends JIMSBaseAction
{
	public DisplayRiskAnswerDeleteSummaryAction()
	{
		
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward setFormBeansAndSendToDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	//Get Form(s) 
        //RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskAnswerDeleteForm riskAnswerDeleteForm = (RiskAnswerDeleteForm) aForm;
    	
    	//Clear form for new incoming question detail
    	riskAnswerDeleteForm.clearForm();
    	
    	//Get Question ID from Request
    	String riskQuestionId = (String)aRequest.getSession().getAttribute("riskQuestionId");
    	
    	//Set Question ID inForm
    	riskAnswerDeleteForm.getQuestion().setRiskQuestionId(riskQuestionId);
    	
    	//Get Answer ID from Request
    	String riskAnswerId = (String)aRequest.getSession().getAttribute("selectedRiskAnswerId");
    	
    	//Set Answer ID in Form
    	riskAnswerDeleteForm.getCurrentAnswer().setRiskAnswerId(riskAnswerId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
    	riskAnswerDeleteForm.setControlCodes(controlCodes);
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getSingleQuestionEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getSingleQuestionEvent.setReturnSingleQuestionBasedOnId(true);
    	getSingleQuestionEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionReponse = 
        	MessageUtil.postRequest(getSingleQuestionEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List singleQuestion
			= MessageUtil.compositeToList( singleQuestionReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = new GetQuestionResponseEvent();
    	for (Object o : singleQuestion) 
    		getQuestionResponseEvent = (GetQuestionResponseEvent)o; //Only one question is expected
    	
    	//Set Question Detail on form
    	setRetrievedQuestionInForm( riskAnswerDeleteForm, getQuestionResponseEvent);
        
    	//Create Get Answers Event
    	GetAnswersEvent getSingleQuestionAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSingleQuestionAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getSingleQuestionAnswersEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionAnswersReponse = 
        	MessageUtil.postRequest(getSingleQuestionAnswersEvent); 
       
    	//Extract GetAnswerResponseEvent Events 
    	List singleQuestionAnswers = MessageUtil.compositeToList( singleQuestionAnswersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskAnswerDeleteForm, singleQuestionAnswers, false);
    	
    	// Create Get Answers Event for Selected Risk Answer ID
    	GetAnswersEvent getSelectedAnswerEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSelectedAnswerEvent.setReturnAnswerBasedOnAnswerId(true);    
    	getSelectedAnswerEvent.setAnswerId(riskAnswerId);
    	
    	//Run Get Answers Event
    	CompositeResponse selectedAnswerReponse = 
        	MessageUtil.postRequest(getSelectedAnswerEvent); 
       
    	//Extract GetAnswerResponseEvent and place in Current Answer
    	List selectedAnswer = MessageUtil.compositeToList( selectedAnswerReponse, GetAnswerResponseEvent.class );
    	//Set Question Detail on form
    	setRetrievedAnswersInForm( riskAnswerDeleteForm, selectedAnswer, true);
    	
    	return aMapping.findForward("success");
		
    }
    
    private void setRetrievedQuestionInForm(
    		RiskAnswerDeleteForm riskAnswerDeleteForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	riskAnswerDeleteForm.setQuestion(question);    	
    	riskAnswerDeleteForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskAnswerDeleteForm.getControlCodes(), question.getControlCode()));
	}
    
    private void setRetrievedAnswersInForm(
    		RiskAnswerDeleteForm riskAnswerDeleteForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerDeleteForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerDeleteForm.setAnswerList(answers);
    	}
    	
		
	}
    
    protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}

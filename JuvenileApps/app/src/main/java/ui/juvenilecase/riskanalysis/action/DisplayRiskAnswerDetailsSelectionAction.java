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
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.HandleRiskAnswerDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskAnswerDetailsSelectionAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward setFormBeansAndSendToDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	//Get Form(s) 
    	HandleRiskAnswerDetailsSelectionForm handleRiskAnswerDetailsSelectionForm 
    		= (HandleRiskAnswerDetailsSelectionForm) aForm;
    	
    	//Get Question and Answer ID from form
		String selectedRiskQuestionId = handleRiskAnswerDetailsSelectionForm.getQuestion().getRiskQuestionId();
		String selectedRiskAnswerId = handleRiskAnswerDetailsSelectionForm.getCurrentAnswer().getRiskAnswerId();
		
		//Clear form for new incoming question detail
    	handleRiskAnswerDetailsSelectionForm.clearForm();
    	
    	//Place questionId on form // Use the form?
    	//String selectedRiskQuestionId = (String)aRequest.getAttribute("selectedRiskQuestionId");
    	//handleRiskAnswerDetailsSelectionForm.getQuestion().setRiskQuestionId(selectedRiskQuestionId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
		handleRiskAnswerDetailsSelectionForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(false);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnId(true);
    	getQuestionsEvent.setQuestionId(selectedRiskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = new GetQuestionResponseEvent();
    	for (Object o : questions) 
    		getQuestionResponseEvent = (GetQuestionResponseEvent)o; //Only one question is expected
    	
    	//Set Question Detail on form
    	setRetrievedQuestionInForm( handleRiskAnswerDetailsSelectionForm, getQuestionResponseEvent);
        
    	// Create Get Answers Event for Selected Risk Answer ID
    	GetAnswersEvent getSelectedAnswerEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSelectedAnswerEvent.setReturnAnswerBasedOnAnswerId(true);    
    	getSelectedAnswerEvent.setAnswerId(selectedRiskAnswerId);
    	
    	//Run Get Questions Event
    	CompositeResponse selectedAnswerReponse = 
        	MessageUtil.postRequest(getSelectedAnswerEvent); 
       
    	//Extract GetQuestionsResponseEvent and place in Current Answer
    	List selectedAnswer = MessageUtil.compositeToList( selectedAnswerReponse, GetAnswerResponseEvent.class );
    	//Set Question Detail on form
    	setRetrievedAnswersInForm( handleRiskAnswerDetailsSelectionForm, selectedAnswer, true);
	
    	return aMapping.findForward("success");
    }
    
    private void setRetrievedAnswersInForm(
    		HandleRiskAnswerDetailsSelectionForm handleRiskAnswerDetailsSelectionForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		handleRiskAnswerDetailsSelectionForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		handleRiskAnswerDetailsSelectionForm.setAnswerList(answers);
    	}
    	
		
	}
    
    private void setRetrievedQuestionInForm(
    		HandleRiskAnswerDetailsSelectionForm handleRiskAnswerDetailsSelectionForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	handleRiskAnswerDetailsSelectionForm.setQuestion(question);    	
    	handleRiskAnswerDetailsSelectionForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(handleRiskAnswerDetailsSelectionForm.getControlCodes(), question.getControlCode()));
	}
	
    protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}

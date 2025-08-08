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
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskQuestionDetailsSelectionAction extends JIMSBaseAction
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
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	//Clear form for new incoming question detail
    	handleRiskQuestionDetailsSelectionForm.clearForm();
    	
    	//Place questionId on form
    	String selectedRiskQuestionId = (String)aRequest.getAttribute("selectedRiskQuestionId");
    	handleRiskQuestionDetailsSelectionForm.getQuestion().setRiskQuestionId(selectedRiskQuestionId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
		handleRiskQuestionDetailsSelectionForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
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
    	setRetrievedQuestionInForm( handleRiskQuestionDetailsSelectionForm, getQuestionResponseEvent);
        
    	 //Create Get Answers Event
    	GetAnswersEvent getAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(false);    
    	getAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getAnswersEvent.setQuestionId(selectedRiskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(getAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List answers = MessageUtil.compositeToList( answersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(handleRiskQuestionDetailsSelectionForm, answers, false);
	
    	return aMapping.findForward("success");
    }
    
    private void setRetrievedQuestionInForm(
    		HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	handleRiskQuestionDetailsSelectionForm.setQuestion(question);    	
    	handleRiskQuestionDetailsSelectionForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(handleRiskQuestionDetailsSelectionForm.getControlCodes(), question.getControlCode()));

	}

	
    
    private void setRetrievedAnswersInForm(
    		HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	handleRiskQuestionDetailsSelectionForm.setAnswerList(answers);

	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}

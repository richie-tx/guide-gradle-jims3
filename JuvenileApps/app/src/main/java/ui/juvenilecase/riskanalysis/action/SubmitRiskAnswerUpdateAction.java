package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.SaveAnswerEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.SaveAnswerResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

public class SubmitRiskAnswerUpdateAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward createAnswers(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskAnswerUpdateForm riskAnswerUpdateForm = (RiskAnswerUpdateForm) aForm;
    	
    	//Create Save Answer Event (One SaveAnswerEvent houses many saveAnswerEvents in it's requests attribute)
    	SaveAnswerEvent saveAnswerEvent = (SaveAnswerEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEANSWER);
    	createSaveAnswerEvent(riskAnswerUpdateForm, saveAnswerEvent);
    	
    	//Run Save Answer Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(saveAnswerEvent); 
    		
    	//Clear currentAnswer
    	riskAnswerUpdateForm.clearCurrentAnswer();
    	
    	//Extract Newly Saved Answer from Response
        List<SaveAnswerResponseEvent> saveAnswerResponseEvents
        	= MessageUtil.compositeToList( answersReponse, SaveAnswerResponseEvent.class );
        //Set Newly Saved Answers in Form
    	setRetrievedAnswersInForm(riskAnswerUpdateForm, saveAnswerResponseEvents, true);
        
        //Clear Answer List
        riskAnswerUpdateForm.getAnswerList().clear();
        
        //Update Answer List from database
    	GetAnswersEvent getSingleQuestionAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSingleQuestionAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getSingleQuestionAnswersEvent.setQuestionId(riskAnswerUpdateForm.getQuestion().getRiskQuestionId());
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionAnswersReponse = 
        	MessageUtil.postRequest(getSingleQuestionAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List singleQuestionAnswers = MessageUtil.compositeToList( singleQuestionAnswersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskAnswerUpdateForm, singleQuestionAnswers, false);
    	
    	return aMapping.findForward("success");
    }
    
    private void setRetrievedAnswersInForm(
    		RiskAnswerUpdateForm riskAnswerUpdateForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerUpdateForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerUpdateForm.setAnswerList(answers);
    	}
    	
		
	}
    
    private void createSaveAnswerEvent(
    		RiskAnswerUpdateForm riskAnswerUpdateForm,
			SaveAnswerEvent saveAnswerEvent) {
    	
    	//Will update only a single SaveAnswerEvent event
    	saveAnswerEvent.setACreate(false);
    	saveAnswerEvent.setRiskQuestionId(riskAnswerUpdateForm.getQuestion().getRiskQuestionId()); 
    	
    	//Call Adapter to set Answer Event
    	List answerList = new ArrayList();
    	answerList.add(riskAnswerUpdateForm.getCurrentAnswer());
    	RiskAnalysisObjectsAdapter.createAnswerEventObject(saveAnswerEvent, answerList);
        
	}
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward goToQuestionDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
    	RiskAnswerUpdateForm riskAnswerUpdateForm = (RiskAnswerUpdateForm) aForm;
    	
    	//QuestionId to be used for the Question Details Selection page
    	String selectedRiskQuestionId = riskAnswerUpdateForm.getQuestion().getRiskQuestionId();
    	aRequest.setAttribute("selectedRiskQuestionId", selectedRiskQuestionId);
    	
    	return aMapping.findForward("goToQuestionDetails");
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.questionDetails", "goToQuestionDetails");
		keyMap.put("button.finish", "createAnswers");
		keyMap.put("button.back", "back");
	}
}

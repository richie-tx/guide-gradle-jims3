package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveAnswerEvent;
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
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.RiskAnswerCreateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

public class SubmitRiskAnswerCreateAction extends JIMSBaseAction
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
    	RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
    	
    	//Create Save Answer Event (One SaveAnswerEvent houses many saveAnswerEvents in it's requests attribute)
    	SaveAnswerEvent saveAnswerEvents = (SaveAnswerEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEANSWER);
    	//saveAnswerEvents.setRiskQuestionId(riskAnswerCreateForm.getQuestion().getRiskQuestionId()); //now done in createSaveAnswersEvents
    	createSaveAnswersEvents(riskAnswerCreateForm, saveAnswerEvents);
    	
    	//Run Save Answer Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(saveAnswerEvents); 
    	
    	//Clear newAnswerList (Keep answerList intact)
    	riskAnswerCreateForm.getNewAnswerList().clear();
    	
    	//Extract Newly Saved Answers from Response
        List<SaveAnswerResponseEvent> saveAnswerResponseEvents = MessageUtil.compositeToList( answersReponse, SaveAnswerResponseEvent.class );
        //Set Newly Saved Answers in Form
        setSavedAnswersInForm(riskAnswerCreateForm, saveAnswerResponseEvents, false);
    	
    	return aMapping.findForward("success");
    }
    
    private void setSavedAnswersInForm(
    		RiskAnswerCreateForm riskAnswerCreateForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerCreateForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerCreateForm.getAnswerList().addAll(answers);
    	}
	}
    
    private void createSaveAnswersEvents(
    		RiskAnswerCreateForm riskAnswerCreateForm,
			SaveAnswerEvent saveAnswerEvents) {
		
    	//Will allow for answers to be cycled through and created
    	saveAnswerEvents.setACreate(true);
    	saveAnswerEvents.setRiskQuestionId(riskAnswerCreateForm.getQuestion().getRiskQuestionId()); //Will be the same for all

    	//Call Adapter to set Answer Event
    	RiskAnalysisObjectsAdapter.createAnswerEventObject(saveAnswerEvents, riskAnswerCreateForm.getNewAnswerList());
        
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
    	RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
    	
    	//QuestionId to be used for the Question Details Selection page
    	String selectedRiskQuestionId = riskAnswerCreateForm.getQuestion().getRiskQuestionId();
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

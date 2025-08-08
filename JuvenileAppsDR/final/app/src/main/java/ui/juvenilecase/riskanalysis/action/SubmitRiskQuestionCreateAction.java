package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.SaveAnswerResponseEvent;
import messaging.riskanalysis.reply.SaveQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;
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
import ui.juvenilecase.riskanalysis.form.RiskQuestionCreateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class SubmitRiskQuestionCreateAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward createQuestion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskQuestionCreateForm riskQuestionCreateForm = (RiskQuestionCreateForm) aForm;
    	
    	//Create Save Questions & Answers Event
    	SaveQuestionEvent saveQuestionsEvent = (SaveQuestionEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEQUESTION);
    	createSaveQuestionAndAnswersEvents(riskQuestionCreateForm,
				saveQuestionsEvent);
    	
    	//Run Save Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(saveQuestionsEvent); 
    	
    	//Extract Newly Saved Question from Response
    	SaveQuestionResponseEvent saveQuestionResponseEvent = (SaveQuestionResponseEvent)
			MessageUtil.filterComposite( questionsReponse, SaveQuestionResponseEvent.class );
    	
    	//Clear Form 
    	riskQuestionCreateForm.clearForm();
    	
    	//Set Newly Saved Question in Form
    	setSavedQuestionInForm(riskQuestionCreateForm, saveQuestionResponseEvent);
    	
    	//Extract Newly Saved Answers from Response
        List<SaveAnswerResponseEvent> saveAnswerResponseEvents = MessageUtil.compositeToList( questionsReponse, SaveAnswerResponseEvent.class );
        //Set Newly Saved Answers in Form
        setRetrievedAnswersInForm(riskQuestionCreateForm, saveAnswerResponseEvents, false);
    	
    	return aMapping.findForward("success");
    	
    }

    private void setRetrievedAnswersInForm(
    		RiskQuestionCreateForm riskQuestionCreateForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskQuestionCreateForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskQuestionCreateForm.setNewAnswerList(answers);
    	}
    	
		
	}

	private void setSavedQuestionInForm(
			RiskQuestionCreateForm riskQuestionCreateForm,
			SaveQuestionResponseEvent saveQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(saveQuestionResponseEvent);
    	riskQuestionCreateForm.setQuestion(question);    
    	riskQuestionCreateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionCreateForm.getControlCodes(), question.getControlCode()));
	}

	private void createSaveQuestionAndAnswersEvents(
			RiskQuestionCreateForm riskQuestionCreateForm,
			SaveQuestionEvent saveQuestionsEvent) {
		
		saveQuestionsEvent.setACreate(true);
		RiskAnalysisObjectsAdapter.createQuestionEventObject(saveQuestionsEvent, riskQuestionCreateForm.getQuestion(), riskQuestionCreateForm.getNewAnswerList());
	}
    
    /**
	 * Default Back method implementation
	 * @param aMapping  - the struts mapping
	 * @param aForm -- the struts form
	 * @param aRequest -- the request object
	 * @param aResponse -- the response object
	 * @return -- an action forward for "BACK"
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.BACK);
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "createQuestion");
		keyMap.put("button.back", "back");
	}
}

package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveQuestionEvent;
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
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class SubmitRiskQuestionUpdateAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward updateQuestion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskQuestionUpdateForm riskQuestionUpdateForm = (RiskQuestionUpdateForm) aForm;
    	
    	//Create Save Questions & Answers Event
    	SaveQuestionEvent saveQuestionsEvent = (SaveQuestionEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEQUESTION);
    	createSaveQuestionEvent(riskQuestionUpdateForm,
				saveQuestionsEvent);
    	
    	//Run Save Questions Event
    	CompositeResponse questionsReponse = MessageUtil.postRequest(saveQuestionsEvent); 
    	
    	//Extract Newly Saved Question from Response
    	SaveQuestionResponseEvent saveQuestionResponseEvent = (SaveQuestionResponseEvent)
			MessageUtil.filterComposite( questionsReponse, SaveQuestionResponseEvent.class );
    	
    	//Clear Form 
    	riskQuestionUpdateForm.clearForm();
    	
    	//Set Newly Saved Question in Form
    	setSavedQuestionInForm(riskQuestionUpdateForm, saveQuestionResponseEvent);
    	
    	return aMapping.findForward("success");
    	
    }

	private void setSavedQuestionInForm(
			RiskQuestionUpdateForm riskQuestionUpdateForm,
			SaveQuestionResponseEvent saveQuestionResponseEvent) {
		
		Question question = RiskAnalysisObjectsAdapter.createQuestionObject(saveQuestionResponseEvent);
		riskQuestionUpdateForm.setQuestion(question);  
		riskQuestionUpdateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionUpdateForm.getControlCodes(), question.getControlCode()));
	}

	private void createSaveQuestionEvent(
			RiskQuestionUpdateForm riskQuestionUpdateForm,
			SaveQuestionEvent saveQuestionsEvent) {
		
		saveQuestionsEvent.setACreate(false);
		RiskAnalysisObjectsAdapter.createQuestionEventObject(saveQuestionsEvent, riskQuestionUpdateForm.getQuestion(), null);
    	
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
    	RiskQuestionUpdateForm riskQuestionUpdateForm = (RiskQuestionUpdateForm) aForm;
    	
    	//QuestionId to be used for the Question Details Selection page
    	String selectedRiskQuestionId = riskQuestionUpdateForm.getQuestion().getRiskQuestionId();
    	aRequest.setAttribute("selectedRiskQuestionId", selectedRiskQuestionId);
    	
    	return aMapping.findForward("goToQuestionDetails");
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.questionDetails", "goToQuestionDetails");
		keyMap.put("button.finish", "updateQuestion");
		keyMap.put("button.back", "back");
	}
}

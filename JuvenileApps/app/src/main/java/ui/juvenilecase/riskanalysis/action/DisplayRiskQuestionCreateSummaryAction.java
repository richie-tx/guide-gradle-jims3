package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionCreateForm;

public class DisplayRiskQuestionCreateSummaryAction extends JIMSBaseAction
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
    	RiskQuestionCreateForm riskQuestionCreateForm = (RiskQuestionCreateForm) aForm;
    	
    	//Validate Data
    	boolean validationFailed = validateData(aMapping, aRequest, riskQuestionCreateForm);
    	if (validationFailed)
    		return aMapping.findForward("validationFailed");
    	
    	riskQuestionCreateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionCreateForm.getControlCodes(), riskQuestionCreateForm.getQuestion().getControlCode()));
        	
    	return aMapping.findForward("success");
		
    }

	private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskQuestionCreateForm riskQuestionCreateForm) {
		
		//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnQuestionName(true);
    	getQuestionsEvent.setQuestionName(riskQuestionCreateForm.getQuestion().getQuestionName());
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List<GetQuestionResponseEvent> questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	String inputQname = riskQuestionCreateForm.getQuestion().getQuestionName();
    	for (GetQuestionResponseEvent question : questions) {
//Question name matches another, do not allow create
    		if (inputQname.equalsIgnoreCase(question.getQuestionName())) {
				ActionErrors errors = new ActionErrors();
				ActionMessage newErr = null;
				newErr = new ActionMessage( "error.questionNameExist", riskQuestionCreateForm.getQuestion().getQuestionName() );
				errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
				saveErrors( aRequest, errors );
				inputQname = UIConstants.EMPTY_STRING;
				return true;
    		}	
		}
		inputQname = UIConstants.EMPTY_STRING;
    	//Create Get Questions Event
/** 04/23/2012 question text exist validation no longer valid per defect 73239   	
    	getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnQuestionText(true);
    	getQuestionsEvent.setQuestionText(riskQuestionCreateForm.getQuestion().getQuestionText());
    	
    	//Run Get Questions Event
    	questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	getQuestionResponseEvent = null;
    	for (GetQuestionResponseEvent question : questions) 
    		getQuestionResponseEvent = question; //Only one question is expected
    	
    	
    	if (getQuestionResponseEvent != null) 
		{
			//Question text matches another, do not allow create
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			newErr = new ActionMessage( "error.questionTextExist", riskQuestionCreateForm.getQuestion().getQuestionText() );
			errors.add( ActionErrors.GLOBAL_ERROR, newErr );
			saveErrors( aRequest, errors );
			
			return true;
		}  */
    	
    	return false;
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}

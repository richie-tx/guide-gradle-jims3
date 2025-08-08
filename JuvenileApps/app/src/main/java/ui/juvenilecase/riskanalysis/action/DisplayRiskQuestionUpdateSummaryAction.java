package ui.juvenilecase.riskanalysis.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;

public class DisplayRiskQuestionUpdateSummaryAction extends JIMSBaseAction
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
    	RiskQuestionUpdateForm riskQuestionUpdateForm = (RiskQuestionUpdateForm) aForm;
    	
    	//Validate Data - No longer necessary per defect #73238  04/23/2012 
/**    	boolean validationFailed = validateData(aMapping, aRequest, riskQuestionUpdateForm);
    	if (validationFailed)
    		return aMapping.findForward("validationFailed"); */
    	
    	riskQuestionUpdateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionUpdateForm.getControlCodes(), riskQuestionUpdateForm.getQuestion().getControlCode()));
        	
    	return aMapping.findForward("success");
		
    }
/**  No longer necessary per defect #73238  04/23/2012   
    private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskQuestionUpdateForm riskQuestionUpdateForm) {
		
		//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnQuestionName(true);
    	getQuestionsEvent.setExcludeSingleQuestionBasedOnId(true);
    	getQuestionsEvent.setQuestionName(riskQuestionUpdateForm.getQuestion().getQuestionName());
    	getQuestionsEvent.setQuestionId(riskQuestionUpdateForm.getQuestion().getRiskQuestionId());
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List<GetQuestionResponseEvent> questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = null;
    	for (GetQuestionResponseEvent question : questions) 
    		getQuestionResponseEvent = question; //Only one question is expected
    	
    	
    	if (getQuestionResponseEvent != null) 
		{
			//Question is a subordinate question, do not allow a delete
			ActionErrors errors = new ActionErrors();
			ActionError newErr = null;
			newErr = new ActionError( "error.questionNameExist", riskQuestionUpdateForm.getQuestion().getQuestionName() );
			errors.add( ActionErrors.GLOBAL_ERROR, newErr );
			saveErrors( aRequest, errors );
			
			return true;
		}
    	
    	//Create Get Questions Event
    	getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnQuestionText(true);
    	getQuestionsEvent.setExcludeSingleQuestionBasedOnId(true);
    	getQuestionsEvent.setQuestionText(riskQuestionUpdateForm.getQuestion().getQuestionText());
    	getQuestionsEvent.setQuestionId(riskQuestionUpdateForm.getQuestion().getRiskQuestionId());
    	
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
			//Question is a subordinate question, do not allow a delete
			ActionErrors errors = new ActionErrors();
			ActionError newErr = null;
			newErr = new ActionError( "error.questionTextExist", riskQuestionUpdateForm.getQuestion().getQuestionText() );
			errors.add( ActionErrors.GLOBAL_ERROR, newErr );
			saveErrors( aRequest, errors );
			
			return true;
		} 
    	
    	return false;
	}  */
    
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}

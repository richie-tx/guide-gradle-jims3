package ui.juvenilecase.riskanalysis.action;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.HandleRiskAnswerDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;

public class HandleRiskAnswerDetailsSelectionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.update", "updateAnswerRequest");
		keyMap.put("button.remove", "removeAnswerRequest");
	}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updateAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	HandleRiskAnswerDetailsSelectionForm handleRiskAnswerDetailsSelectionForm 
    		= (HandleRiskAnswerDetailsSelectionForm) aForm;
    	return aMapping.findForward("updateAnswerSuccess");
    } 

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
    {   
    	HandleRiskAnswerDetailsSelectionForm handleRiskAnswerDetailsSelectionForm 
    		= (HandleRiskAnswerDetailsSelectionForm) aForm;
    	RiskAnswerDeleteForm raDeleteForm = (RiskAnswerDeleteForm)getSessionForm(aMapping, aRequest, "riskAnswerDeleteForm", true);
    	raDeleteForm.setPageType("summary");   	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskAnswerDetailsSelectionForm.getQuestion().getRiskQuestionId());	
    	aRequest.getSession().setAttribute("selectedRiskAnswerId", handleRiskAnswerDetailsSelectionForm.getSelectedRiskAnswerId());
    	
    	return aMapping.findForward("removeAnswerSuccess");
    }	
}
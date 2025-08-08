package ui.juvenilecase.riskanalysis.action;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;

public class HandleRiskQuestionDetailsSelectionAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updateQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	//Get Form(s) 
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskQuestionDetailsSelectionForm.getQuestion().getRiskQuestionId());
    	
    	return aMapping.findForward("updateQuestionRequest");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward deleteQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	//Get Form(s) 
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskQuestionDetailsSelectionForm.getQuestion().getRiskQuestionId());
    	
    	return aMapping.findForward("deleteQuestionRequest");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	//Get Form(s) 
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskQuestionDetailsSelectionForm.getQuestion().getRiskQuestionId());
    	
    	return aMapping.findForward("addAnswerRequest");
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
    	//Get Form(s) 
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskQuestionDetailsSelectionForm.getQuestion().getRiskQuestionId());	
    	aRequest.getSession().setAttribute("selectedRiskAnswerId", handleRiskQuestionDetailsSelectionForm.getSelectedRiskAnswerId());
    	
    	return aMapping.findForward("updateAnswerRequest");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward deleteAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	//Get Form(s) 
    	HandleRiskQuestionDetailsSelectionForm handleRiskQuestionDetailsSelectionForm 
    		= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	
    	aRequest.getSession().setAttribute("riskQuestionId", handleRiskQuestionDetailsSelectionForm.getQuestion().getRiskQuestionId());	
    	aRequest.getSession().setAttribute("selectedRiskAnswerId", handleRiskQuestionDetailsSelectionForm.getSelectedRiskAnswerId());
    	
    	return aMapping.findForward("deleteAnswerRequest");
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.addAnswer", "addAnswerRequest");
		keyMap.put("button.updateAnswer", "updateAnswerRequest");
		keyMap.put("button.deleteAnswer", "deleteAnswerRequest");
		keyMap.put("button.updateQuestion", "updateQuestionRequest");
		keyMap.put("button.deleteQuestion", "deleteQuestionRequest");
	}
}

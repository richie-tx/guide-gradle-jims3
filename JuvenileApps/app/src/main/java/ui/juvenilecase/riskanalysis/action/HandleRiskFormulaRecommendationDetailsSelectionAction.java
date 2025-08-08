package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

import org.apache.struts.action.ActionForward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class HandleRiskFormulaRecommendationDetailsSelectionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.update", "updateRecommendation");
		keyMap.put("button.remove", "removeRecommendation");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward updateRecommendation(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	return aMapping.findForward("updateRecommendationSuccess") ;
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward removeRecommendation(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) getSessionForm(aMapping, aRequest, "riskFormulaDetailsForm", true);
    	rfRecommendationForm.setCurrentList(rfDetailsForm.getRecommendationsList());
    	List wrkList = new ArrayList();
    	int len = rfRecommendationForm.getCurrentList().size();
    	for (int x=0; x<len; x++)
    	{
    		FormulaRecommendationResponseEvent frEvent = (FormulaRecommendationResponseEvent) rfRecommendationForm.getCurrentList().get(x);
    		if (frEvent.getRecommendationId().equalsIgnoreCase(rfRecommendationForm.getRecommendationId()))
    		{
    			wrkList.add(frEvent);
    			break;
    		}
    	}
    	rfRecommendationForm.setRemoveList(wrkList);
    	wrkList = null;
    	rfRecommendationForm.setPageType("summary");
    	return aMapping.findForward("removeRecommendationSuccess"); 
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	rfRecommendationForm.clear();
    	aRequest.setAttribute("selectedRecommendationId", null);
    	return aMapping.findForward("back"); 
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	rfRecommendationForm.clear();
    	aRequest.setAttribute("selectedRecommendationId", null);
    	return aMapping.findForward("cancel"); 
	}
	
}
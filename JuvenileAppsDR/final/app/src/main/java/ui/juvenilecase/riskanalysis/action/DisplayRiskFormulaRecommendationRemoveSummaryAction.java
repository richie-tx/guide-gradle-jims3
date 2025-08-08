package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

public class DisplayRiskFormulaRecommendationRemoveSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
	
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	String recommendationId = aRequest.getAttribute("selectedRecommendationId").toString();
    	rfRecommendationForm.setRecommendationId(recommendationId);
    	aRequest.setAttribute("selectedRecommendationId", null);
    	List rList = new ArrayList();
    	Object obj = aRequest.getAttribute("recommendationsList");
    	if (obj != null) {
    		rList = (List) obj;
    	}
    	rfRecommendationForm.setCurrentList(rList); 
    	aRequest.setAttribute("recommendationsList", null);
// load remove list with selected recommendation  
    	List wrkList = new ArrayList();
    	int len = rfRecommendationForm.getCurrentList().size();
    	for (int x=0; x<len; x++)
    	{
    		FormulaRecommendationResponseEvent frEvent = (FormulaRecommendationResponseEvent) rfRecommendationForm.getCurrentList().get(x);
    		if (frEvent.getRecommendationId().equalsIgnoreCase(recommendationId))
    		{
    			wrkList.add(frEvent);
    			break;
    		}
    	}
    	rfRecommendationForm.setRemoveList(wrkList);
    	rfRecommendationForm.setPageType("summary");
    	return aMapping.findForward("success") ;
	}	
}

package ui.juvenilecase.riskanalysis.action;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskFormulaRecommendationAddSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextPage");
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
     */
    public ActionForward nextPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) 
    {
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	for (int x=0; x<rfRecommendationForm.getRiskResultGroupList().size(); x++)
    	{
    		RiskResultGroupCodesResponseEvent crEvent = (RiskResultGroupCodesResponseEvent) rfRecommendationForm.getRiskResultGroupList().get(x);
    		if (crEvent.getCode().equalsIgnoreCase(rfRecommendationForm.getRiskResultGroupId()))
    		{	
    			rfRecommendationForm.setRiskResultGroupDesc(crEvent.getDescription());
    			break;
    		}	
    	}
    	rfRecommendationForm.setPageType("summary");
    	return aMapping.findForward("nextSuccess");
    }
   
}

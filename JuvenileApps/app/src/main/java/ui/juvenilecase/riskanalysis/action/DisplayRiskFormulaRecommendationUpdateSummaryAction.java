package ui.juvenilecase.riskanalysis.action;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskFormulaRecommendationUpdateSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextPage");
		keyMap.put("button.back", "back");
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
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
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	String forwardStr = "back";
       	rfRecommendationForm.setPageType("summary");
    	if ("RecommendationDetails".equalsIgnoreCase(rfRecommendationForm.getFromPage() ) )
    	{
    		forwardStr = "backToRecommendationDetail";
    		rfRecommendationForm.setFromPage(UIConstants.EMPTY_STRING);
    		rfRecommendationForm.setPageType(UIConstants.EMPTY_STRING);
    		// reload original display values in case user changed something
        	FormulaRecommendationResponseEvent freEvent = (FormulaRecommendationResponseEvent) rfRecommendationForm.getCurrentList().get(0);
        	rfRecommendationForm.setRecommendationName(freEvent.getRecommendationName());
        	rfRecommendationForm.setRecommendationText(freEvent.getRecommendationDesc());
        	rfRecommendationForm.setMinScore(Integer.toString(freEvent.getMinScore() ) );
        	rfRecommendationForm.setMaxScore(Integer.toString(freEvent.getMaxScore() ) );
        	rfRecommendationForm.setInCustodyId(new Boolean (freEvent.isCustody() ).toString());
        	rfRecommendationForm.setRiskResultGroupId(freEvent.getResultGroup());
        	rfRecommendationForm.setRiskResultGroupDesc(freEvent.getResultGroupDisplayDesc());
        	freEvent = null;
    	}
    	return aMapping.findForward(forwardStr);
    }
}

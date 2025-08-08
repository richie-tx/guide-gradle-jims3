package ui.juvenilecase.riskanalysis.action;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskAnswerCreateSummaryAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward setFormBeansAndSendToDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
    	//RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
	
    	return aMapping.findForward("success");
		
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
	
}

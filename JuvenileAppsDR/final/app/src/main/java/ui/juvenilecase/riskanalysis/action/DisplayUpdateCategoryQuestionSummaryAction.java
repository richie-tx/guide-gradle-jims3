package ui.juvenilecase.riskanalysis.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;

public class DisplayUpdateCategoryQuestionSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
	}
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskQuestionUpdateForm rqUpdateForm = (RiskQuestionUpdateForm) aForm;
    	String ccName = UIConstants.EMPTY_STRING;
    	ccName = RiskAnalysisHelper.getControlCodeName(rqUpdateForm.getControlCodes(), rqUpdateForm.getQuestion().getControlCode());
    	if (ccName == null)
    	{
    		ccName = UIConstants.EMPTY_STRING;
    	}
    	rqUpdateForm.getQuestion().setControlCodeName(ccName);
    	rqUpdateForm.setPageType("summary");    	
    	return aMapping.findForward("success");
    }  
}

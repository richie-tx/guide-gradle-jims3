package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.DeleteFormulaEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitRiskFormulaDeleteAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.confirmDelete", "confirm");
		keyMap.put("button.formulaSearch", "backToSearch");
	}
	
    public ActionForward confirm(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	DeleteFormulaEvent deleteFormulaEvent = 
    		(DeleteFormulaEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.DELETEFORMULA);
    	deleteFormulaEvent.setRiskFormulaId(rfDetailsForm.getFormulaId() );
    	MessageUtil.postRequest(deleteFormulaEvent);
    	
    	rfDetailsForm.setConfirmMessage("Formula successfully deleted.");
    	rfDetailsForm.setPageType("confirm");
    	return aMapping.findForward("confirmSuccess") ;
	}
    
    public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
       	rfDetailsForm.setPageType(UIConstants.EMPTY_STRING);
    	return aMapping.findForward("backToSearch") ;
	}
}

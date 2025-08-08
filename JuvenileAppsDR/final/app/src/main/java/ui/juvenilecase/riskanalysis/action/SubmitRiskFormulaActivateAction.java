package ui.juvenilecase.riskanalysis.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.ActivateRiskFormulaEvent;
import messaging.riskanalysis.GetActiveAndPendingFormulasEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

public class SubmitRiskFormulaActivateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.confirmActivation", "confirm");
		keyMap.put("button.formulaSearchResults", "backToResults");
	}
	
    public ActionForward confirm(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
 
    	ActivateRiskFormulaEvent actFormulaEvent = 
    		(ActivateRiskFormulaEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.ACTIVATERISKFORMULA);
    	actFormulaEvent.setRiskFormulaId(rfDetailsForm.getFormulaId() );
    	CompositeResponse compResp = MessageUtil.postRequest(actFormulaEvent);
    	
    	FormulaResponseEvent frEvent = (FormulaResponseEvent) MessageUtil.filterComposite(compResp, FormulaResponseEvent.class);
    	rfDetailsForm.setConfirmMessage("Formula successfully activated");
    	rfDetailsForm.setStatusDesc(frEvent.getStatusDesc());  
    	rfDetailsForm.setActivationDateStr(DateUtil.dateToString(frEvent.getActivateDate(), DateUtil.DATE_FMT_1));
    	rfDetailsForm.setPageType("confirm");
    	return aMapping.findForward(UIConstants.CONFIRM_SUCCESS);
	}
    
    public ActionForward backToResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
       	rfDetailsForm.setPageType(UIConstants.EMPTY_STRING);
		RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);
    	GetActiveAndPendingFormulasEvent reqEvent =
			(GetActiveAndPendingFormulasEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETACTIVEANDPENDINGFORMULAS);		
    	List formulas = MessageUtil.postRequestListFilter(reqEvent, FormulaResponseEvent.class);
    	rfSearchForm.setFormulasList(formulas);
    	return aMapping.findForward("backToResults") ;
	}
}

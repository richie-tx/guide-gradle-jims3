package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.RemoveFormulaCategoryEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

import org.apache.struts.action.ActionForward;

import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitRiskFormulaCategoryRemoveAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeCategory");
		keyMap.put("button.formulaDetails", "backToDetails");
	}
	
    public ActionForward removeCategory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
		RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);

    	RemoveFormulaCategoryEvent rfcEvent = (RemoveFormulaCategoryEvent)
    		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVEFORMULACATEGORY);
    	rfcEvent.setCategoryId(rfDetailsForm.getSelectedCategoryId());
    	
    	CompositeResponse cr = MessageUtil.postRequest(rfcEvent);
    	FormulaResponseEvent fre = (FormulaResponseEvent) MessageUtil.filterComposite(cr, FormulaResponseEvent.class);
    	
    	if (fre != null & !fre.getFormulaId().equals(rfDetailsForm.getFormulaId())){
    		rfDetailsForm.setFormulaId(fre.getFormulaId());
    		rfDetailsForm.setVersion(fre.getVersion());
    		rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    		rfDetailsForm.setStatusId(fre.getStatus());
    		rfSearchForm.setFormulaId(fre.getFormulaId());
    	}
    	
    	List riskCategories = MessageUtil.compositeToList(cr, CategoryResponseEvent.class);
    	if (riskCategories.size() > 1){
    		rfDetailsForm.setCurrentCategoriesList(RiskAnalysisUIHelper.sortCategories(riskCategories));
    	} else {
    		rfDetailsForm.setCurrentCategoriesList(riskCategories);
    	}

    	rfDetailsForm.setConfirmMessage("Category successfully removed from Formula.");
    	rfDetailsForm.setPageType("confirm");

    	return aMapping.findForward("finishSuccess") ;
	}
    
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
       	rfDetailsForm.setPageType(UIConstants.EMPTY_STRING);
       	rfDetailsForm.setRemovedCategoriesList(null);
    	return aMapping.findForward("backToDetails") ;
	}
}

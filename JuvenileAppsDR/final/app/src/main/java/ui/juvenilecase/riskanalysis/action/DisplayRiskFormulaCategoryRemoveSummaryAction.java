package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.reply.CategoryResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

public class DisplayRiskFormulaCategoryRemoveSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	List wrkList = new ArrayList();
    	int curLen = rfDetailsForm.getCurrentCategoriesList().size();
    	for (int x=0; x<curLen; x++)
    	{
    		CategoryResponseEvent crEvent =  (CategoryResponseEvent) rfDetailsForm.getCurrentCategoriesList().get(x);
    		if (crEvent.getCategoryId().equalsIgnoreCase(rfDetailsForm.getSelectedCategoryId()))
    		{
    			wrkList.add(crEvent);
    			break;
    		}
    	}
    	rfDetailsForm.setRemovedCategoriesList(wrkList);
    	wrkList = null; 
    	rfDetailsForm.setPageType("summary");
    	return aMapping.findForward("success") ;
	}
}

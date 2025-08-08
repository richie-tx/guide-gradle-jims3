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
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaCreateForm;

public class DisplayRiskFormulaCreateSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.addSelected", "addSelected");
		keyMap.put("button.remove", "removeSelected");
		keyMap.put("button.next", "nextPage");
	}
	
    public ActionForward nextPage(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
    	
    	rfCreateForm.setPageType("summary");
    	return aMapping.findForward("createSuccess") ;
	}
    
    public ActionForward addSelected(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
       	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List wrkList3 = new ArrayList();
    	wrkList1 = rfCreateForm.getSelectableCategoriesList();
    	wrkList2 = rfCreateForm.getSelectedCategoriesList();
    	String[] selectedId = rfCreateForm.getSelectedValues();
    	int len = rfCreateForm.getSelectedValues().length;
    	int len2 = wrkList1.size();
    	for (int x=0; x<len; x++)
    	{
    		for (int y=0; y<len2; y++)
    		{
    			CategoryResponseEvent cre = (CategoryResponseEvent) wrkList1.get(y);
        		if (selectedId[x].equalsIgnoreCase(cre.getCategoryId() ) )
        		{
        			wrkList2.add(cre);
        			break;
        		} 
    		}
    	}
    	boolean matchFound = false;
    	len = wrkList1.size();
     	for (int x=0; x<len; x++)
    	{
     		CategoryResponseEvent cre1 = (CategoryResponseEvent) wrkList1.get(x);
    		matchFound = false;
    		for (int y=0; y<wrkList2.size(); y++)
    		{
    			CategoryResponseEvent cre2 = (CategoryResponseEvent) wrkList2.get(y);
    			if (cre2.getCategoryId().equalsIgnoreCase(cre1.getCategoryId()))
    			{
    				matchFound = true;
    				break;
    			}
    		}
    		if (matchFound == false)
    		{
    			wrkList3.add(cre1);
    		}	
     	}
    	rfCreateForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList3));
    	rfCreateForm.setSelectedCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	wrkList1 = null;
    	wrkList2 = null;
    	wrkList3 = null;
    	
    	return aMapping.findForward("addSuccess") ;
	}
    
    public ActionForward removeSelected(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
    	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List selectedList = rfCreateForm.getSelectedCategoriesList();
    	int len = selectedList.size();
    	String selectedId = aRequest.getParameter("categoryID");
    	for (int x=0; x<len; x++)
    	{
       		CategoryResponseEvent cre = (CategoryResponseEvent) selectedList.get(x);
       		if (selectedId.equalsIgnoreCase(cre.getCategoryId() ) )
       		{
       			wrkList1.add(cre);
       		} else {
       			wrkList2.add(cre);       			
       		}
    	}
    	rfCreateForm.setSelectedCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	wrkList2 = rfCreateForm.getSelectableCategoriesList();
    	wrkList2.add(wrkList1.get(0));
    	rfCreateForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	wrkList1 = null;
    	wrkList2 = null;
    	selectedList = null;
    	return aMapping.findForward("removeSuccess") ;
	}
}

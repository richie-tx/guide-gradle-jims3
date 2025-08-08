package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

public class HandleRiskFormulaSearchAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.submit", "searchFormulas");
		keyMap.put("button.createFormula", "createFormula");
	}
	
    public ActionForward createFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
    	aRequest.setAttribute("availAssessmentTypes", rfSearchForm.getAvailableAssessmentTypesList());
    	return aMapping.findForward("createSuccess") ;
	}
	
    public ActionForward searchFormulas(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
    	rfSearchForm.setSearchResultsList(new ArrayList());
    	List resultGroupsCodes = ComplexCodeTableHelper.getRiskResultGroups();
    	List formulas = rfSearchForm.getFormulasList();
    	List wrkList = new ArrayList();
    	for (int x=0; x<formulas.size(); x++) {
    		FormulaResponseEvent fre = (FormulaResponseEvent) formulas.get(x);
    		if (rfSearchForm.getAssessmentId().equalsIgnoreCase(fre.getAssessmentTypeCd()) ) {
    			loadRiskGroupDescriptions((List)fre.getCategories(), resultGroupsCodes);
    			fre.setCategories(RiskAnalysisUIHelper.sortCategories(fre.getCategories() ) );
    			wrkList.add(fre);
    		}
    	}

    	rfSearchForm.setSearchResultsList(wrkList);
    	String msg = wrkList.size() + " formulas found in search results.";
    	rfSearchForm.setResultsMessage(msg);
    	rfSearchForm.setSelectedValue(UIConstants.EMPTY_STRING);
    	msg = null;
    	wrkList = null;
    	return aMapping.findForward("searchSuccess") ;
	}

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward("refreshSuccess") ;
	}

    private void loadRiskGroupDescriptions(List categoriesList, List codeTable)
    {
    	for (int x=0; x<categoriesList.size(); x++) {
			CategoryResponseEvent crEvent = (CategoryResponseEvent) categoriesList.get(x);
			for (int y=0; y<codeTable.size(); y++) {
				RiskResultGroupCodesResponseEvent cdre = (RiskResultGroupCodesResponseEvent) codeTable.get(y);
				if (cdre.getCode().equals(crEvent.getRiskResultGroupId() ) ) {
					crEvent.setRiskResultGroupDesc(cdre.getDescription());
					break;
				}
			}
    	}
    }	
}
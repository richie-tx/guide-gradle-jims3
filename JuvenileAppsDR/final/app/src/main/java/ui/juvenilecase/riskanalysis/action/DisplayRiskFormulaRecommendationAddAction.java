package ui.juvenilecase.riskanalysis.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

public class DisplayRiskFormulaRecommendationAddAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
    	String formulaName = aRequest.getAttribute("riskFormulaName").toString();
    	String formulaId =  aRequest.getAttribute("riskFormulaId").toString();
    	aRequest.setAttribute("riskFormulaName", null);
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
   		rfRecommendationForm.setRiskResultGroupList(ComplexCodeTableHelper.getRiskResultGroups());
    	rfRecommendationForm.setEntryDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));	
    	rfRecommendationForm.setFormulaName(formulaName);
    	rfRecommendationForm.setFormulaId(formulaId);
    	rfRecommendationForm.setRecommendationId(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setRecommendationName(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setRecommendationText(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setMinScore(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setMaxScore(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setInCustodyId(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setRiskResultGroupId(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setRiskResultGroupDesc(UIConstants.EMPTY_STRING);
    	formulaName = null;
    	formulaId = null;
    	return aMapping.findForward("success") ;
	}	
}

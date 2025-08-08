package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

public class DisplayRiskFormulaRecommendationDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	rfRecommendationForm.setRiskResultGroupList(ComplexCodeTableHelper.getRiskResultGroups());
		List recList = new ArrayList();
    	Object obj = aRequest.getAttribute("selectedRecommendationInfo");
    	if (obj != null) {
    		recList = (List) obj;
    	}	
    	rfRecommendationForm.setCurrentList(recList); // hold original recommendation values 
    	FormulaRecommendationResponseEvent freEvent = (FormulaRecommendationResponseEvent) recList.get(0);
    	aRequest.setAttribute("selectedRecommendationInfo", null);
    	String updatable = aRequest.getAttribute("recommendationUpdatable").toString();
    	aRequest.setAttribute("recommendationUpdatable", null);
    	rfRecommendationForm.setRecommendationUpdatable(false);
    	if ("true".equalsIgnoreCase(updatable)) {
    		rfRecommendationForm.setRecommendationUpdatable(true);
    	}
    	updatable = null;
    	rfRecommendationForm.setFormulaId(freEvent.getFormulaId());
    	rfRecommendationForm.setRecommendationId(freEvent.getRecommendationId());
    	rfRecommendationForm.setFormulaName(freEvent.getAssessmentTypeName());
    	rfRecommendationForm.setRecommendationName(freEvent.getRecommendationName());
    	rfRecommendationForm.setEntryDate(DateUtil.dateToString(freEvent.getEntryDate(), DateUtil.DATE_FMT_1) );
    	rfRecommendationForm.setRecommendationText(freEvent.getRecommendationDesc());
    	rfRecommendationForm.setMinScore(Integer.toString(freEvent.getMinScore() ) );
    	rfRecommendationForm.setMaxScore(Integer.toString(freEvent.getMaxScore() ) );
    	rfRecommendationForm.setInCustodyId(new Boolean (freEvent.isCustody() ).toString());
    	rfRecommendationForm.setRiskResultGroupId(freEvent.getResultGroup());
    	rfRecommendationForm.setRiskResultGroupDesc(freEvent.getResultGroupDisplayDesc());
    	recList = null;
    	return aMapping.findForward("success");
	}	
}

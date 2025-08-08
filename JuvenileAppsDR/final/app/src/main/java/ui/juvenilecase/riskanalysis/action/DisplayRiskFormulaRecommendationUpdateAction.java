package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;

import org.apache.struts.action.ActionForward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskFormulaRecommendationUpdateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
		
	public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	rfRecommendationForm.setRiskResultGroupList(ComplexCodeTableHelper.getRiskResultGroups());
// load recommendation form with selected recommendation event info  
		List recList = new ArrayList();
    	Object obj = aRequest.getAttribute("selectedRecommendationInfo");
    	if (obj != null) {
    		recList = (List) obj;
    	}	
    	FormulaRecommendationResponseEvent freEvent = (FormulaRecommendationResponseEvent) recList.get(0);
    	aRequest.setAttribute("selectedRecommendationInfo", null);
    	rfRecommendationForm.setRiskResultGroupList(ComplexCodeTableHelper.getRiskResultGroups());
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
    	return aMapping.findForward("success") ;
	}	

}

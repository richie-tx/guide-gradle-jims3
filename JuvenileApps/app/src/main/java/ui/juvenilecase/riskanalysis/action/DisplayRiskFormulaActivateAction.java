package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

public class DisplayRiskFormulaActivateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}
	
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	List wrkList = new ArrayList();
    	Object obj = aRequest.getAttribute("selectedRiskFormulaInfo");
    	if (obj != null) {
    		wrkList = (List)obj;
    	}	
    	aRequest.setAttribute("selectedRiskFormulaInfo", null);
    	rfDetailsForm.setCurrentFormulaList(wrkList);
    	FormulaResponseEvent fre = (FormulaResponseEvent) wrkList.get(0);
    	rfDetailsForm.setFormulaId(fre.getFormulaId());
    	rfDetailsForm.setFormulaName(fre.getAssessmentTypeCd());
    	rfDetailsForm.setVersion(fre.getVersion());
    	rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    	rfDetailsForm.setEntryDate(DateUtil.dateToString(fre.getEntryDate(), DateUtil.DATE_FMT_1));
    	rfDetailsForm.setActivationDateStr(UIConstants.EMPTY_STRING);

    	rfDetailsForm.setPageType("summary");
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
    	return aMapping.findForward(UIConstants.ACTIVATE_SUCCESS);
	}
}

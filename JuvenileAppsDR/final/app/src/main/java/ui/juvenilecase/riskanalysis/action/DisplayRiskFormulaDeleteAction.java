package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

import org.apache.struts.action.ActionForward;

import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskFormulaDeleteAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	Object obj = aRequest.getAttribute("selectedRiskFormulaInfo");
    	List wrkList = (List)obj;
    	aRequest.setAttribute("selectedRiskFormulaInfo", null);
    	rfDetailsForm.setCurrentFormulaList(wrkList);
    	FormulaResponseEvent fre = (FormulaResponseEvent) wrkList.get(0);
    	rfDetailsForm.setFormulaId(fre.getFormulaId());
    	rfDetailsForm.setFormulaName(fre.getAssessmentTypeCd());
//    	rfDetailsForm.setRiskType("RISK");
    	rfDetailsForm.setVersion(fre.getVersion());
    	rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    	rfDetailsForm.setEntryDate(DateUtil.dateToString(fre.getEntryDate(), DateUtil.DATE_FMT_1));
    	rfDetailsForm.setActivationDateStr(DateUtil.dateToString(fre.getActivateDate(), DateUtil.DATE_FMT_1));
 
    	rfDetailsForm.setPageType("summary");
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
    	return aMapping.findForward("deleteSuccess") ;
	}
}

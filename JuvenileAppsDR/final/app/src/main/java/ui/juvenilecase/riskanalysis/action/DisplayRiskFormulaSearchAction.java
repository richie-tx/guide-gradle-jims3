package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.riskanalysis.GetActiveAndPendingFormulasEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

public class DisplayRiskFormulaSearchAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
    	rfSearchForm.clear();

    	//Get Formula/Assessment Type info
    	GetActiveAndPendingFormulasEvent reqEvent =
			(GetActiveAndPendingFormulasEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETACTIVEANDPENDINGFORMULAS);		
    	List formulas = MessageUtil.postRequestListFilter(reqEvent, FormulaResponseEvent.class);
    	rfSearchForm.setFormulasList(formulas);
    	Iterator iter = formulas.iterator();
		SortedMap map = new TreeMap();
		CodeResponseEvent cre = new CodeResponseEvent();
		while(iter.hasNext()){
			cre = new CodeResponseEvent();
			FormulaResponseEvent frEvent = (FormulaResponseEvent) iter.next();
			cre.setCode(frEvent.getAssessmentTypeCd());
			cre.setDescription(frEvent.getAssessmentTypeCd());
			map.put(cre.getCode(), cre);
		}
		List wrkList1 = new ArrayList(map.values());
		rfSearchForm.setAssessmentTypesList(wrkList1);
    	wrkList1 = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.JUV_RISK_ASSESSMENT_TYPE);
    	List wrkList2 = new ArrayList();
    	boolean matchFound = false;
    	for (int x=0; x<wrkList1.size(); x++) {
    		CodeResponseEvent cre1 = (CodeResponseEvent) wrkList1.get(x);
    		matchFound = false;
        	for (int y=0; y<rfSearchForm.getAssessmentTypesList().size(); y++) 	{
        		CodeResponseEvent cre2 = (CodeResponseEvent) rfSearchForm.getAssessmentTypesList().get(y);
        		if (cre1.getCode().equalsIgnoreCase(cre2.getCode() ) )
        		{
        			matchFound = true;
        			break;
        		}
        	}
        	if (matchFound == false) {
        		wrkList2.add(cre1);
        	}
    	}
    	Collections.sort(wrkList2);
    	rfSearchForm.setAvailableAssessmentTypesList(wrkList2);
    	wrkList1 = null;
    	wrkList2 = null;
    	return aMapping.findForward("success");
    }
}
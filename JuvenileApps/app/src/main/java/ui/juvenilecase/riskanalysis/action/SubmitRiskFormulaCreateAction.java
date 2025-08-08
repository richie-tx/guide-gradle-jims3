package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.error.reply.ErrorResponseEvent;
import messaging.riskanalysis.SaveFormulaEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaCreateForm;

public class SubmitRiskFormulaCreateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "addFormula");
		keyMap.put("button.formulaSearch", "backToSearch");
	}
	
    public ActionForward addFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
    	SaveFormulaEvent saveEvent = new SaveFormulaEvent();
    	saveEvent.setAssessmentType(rfCreateForm.getAssessmentTypeId());
    	// categories in saveEvent are just a list of categoryIds
    	int catSize = rfCreateForm.getSelectedCategoriesList().size();
    	List wrkList = new ArrayList();
    	String[] catIds = new String[catSize];
    	List sels = rfCreateForm.getSelectedCategoriesList();
    	for (int x=0; x<catSize; x++) {
    		CategoryResponseEvent cre = (CategoryResponseEvent) sels.get(x);
    		catIds[x] = cre.getCategoryId();
    	}
    	Collections.addAll(wrkList, catIds);
    	saveEvent.setCategories(wrkList);
    	saveEvent.setRecommendations(new ArrayList()); // needed to prevent null pointer error
//    	saveEvent.setCategories(rfCreateForm.getSelectedCategoriesList());
    	String forwardStr = UIConstants.CREATE_FAILURE;
    	CompositeResponse compResp = MessageUtil.postRequest(saveEvent);
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite( compResp, ErrorResponseEvent.class );
		if (error == null) {
			FormulaResponseEvent freEvent = (FormulaResponseEvent) MessageUtil.filterComposite(compResp, FormulaResponseEvent.class);
	    	rfCreateForm.setFormulaName(freEvent.getAssessmentTypeCd());
	    	rfCreateForm.setVersion(freEvent.getVersion());
	    	rfCreateForm.setStatusDesc(freEvent.getStatusDesc());
	    	rfCreateForm.setEntryDate(freEvent.getEntryDate());
	    	rfCreateForm.setActivationDate(UIConstants.EMPTY_STRING);
	    	rfCreateForm.setPageType("confirm");
	    	forwardStr = UIConstants.CREATE_SUCCESS;
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( error.getMessage() ) );
			saveErrors( aRequest, errors );
		}
    	return aMapping.findForward(forwardStr) ;
	}
    
    public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
    	rfCreateForm.clear();
    	rfCreateForm.setPageType(UIConstants.EMPTY_STRING);
    	return aMapping.findForward("backToSearch") ;
	}
}

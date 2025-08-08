package ui.juvenilecase.riskanalysis.action;

import messaging.error.reply.ErrorResponseEvent;
import messaging.riskanalysis.GetFormulaDetailsforActivationEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

import org.apache.struts.action.ActionForward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class HandleRiskFormulaSelectionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.preview", "previewFormula");
		keyMap.put("button.activate", "activateFormula");
		keyMap.put("button.delete", "deleteFormula");
		keyMap.put("button.view", "viewFormula");
	}
	
    public ActionForward previewFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
       	clearActionErrors(aRequest);
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
    	aRequest.setAttribute("selectedAssessmentId", rfSearchForm.getSelectedValue());
    	aRequest.setAttribute("selectedAssessmentType", rfSearchForm.getAssessmentId());
    	return aMapping.findForward("previewSuccess") ;
	}
    
    public ActionForward activateFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
  		clearActionErrors(aRequest);
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
    	String forwardStr = UIConstants.ACTIVATE_SUCCESS;
//verify formula meets requirements to be activated
    	GetFormulaDetailsforActivationEvent getDetailsEvt = 
       		(GetFormulaDetailsforActivationEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETFORMULADETAILSFORACTIVATION);
      	getDetailsEvt.setFormulaToBeActivatedId(rfSearchForm.getSelectedValue());
      	
      	List <ErrorResponseEvent> fndErrors = MessageUtil.postRequestListFilter(getDetailsEvt, ErrorResponseEvent.class);
      	if (fndErrors.size() > 0){
			String msg = "Activation not possible - ";
      		for (int i = 0; i < fndErrors.size(); i++) {
      			ErrorResponseEvent ere = fndErrors.get(i);
      			msg += ere.getMessage();
      			if (i < fndErrors.size() - 1){
      				msg += ", ";
      			}
			}
      		ActionErrors errors = new ActionErrors();
			errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg ) );			
			saveErrors( aRequest, errors );
      		forwardStr = UIConstants.ACTIVATE_FAILURE;
      	} else {
	       	List list1 = new ArrayList();
	      	for (int x=0; x<rfSearchForm.getSearchResultsList().size(); x++)
	       	{	
	       		FormulaResponseEvent frEvent = (FormulaResponseEvent) rfSearchForm.getSearchResultsList().get(x);
	       		if (rfSearchForm.getSelectedValue().equals(frEvent.getFormulaId() ) ) {
	       			list1.add(frEvent);
	       			aRequest.setAttribute("selectedRiskFormulaInfo", list1);
	       			break;
	       		}
	       	}
	      	list1 = null;
      	}	
    	return aMapping.findForward(forwardStr);
	}
    
    public ActionForward deleteFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
       	clearActionErrors(aRequest);
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm) aForm;
       	List list1 = new ArrayList();
      	for (int x=0; x<rfSearchForm.getSearchResultsList().size(); x++)
       	{	
       		FormulaResponseEvent frEvent = (FormulaResponseEvent) rfSearchForm.getSearchResultsList().get(x);
       		if (rfSearchForm.getSelectedValue().equals(frEvent.getFormulaId() ) ) {
       			list1.add(frEvent);
       			aRequest.setAttribute("selectedRiskFormulaInfo", list1);
       			break;
       		}
       	}
      	list1 = null;
    	return aMapping.findForward("deleteSuccess") ;
	}
    
    public ActionForward viewFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	clearActionErrors(aRequest);
    	String formulaId = aRequest.getParameter("formulaId");
       	aRequest.setAttribute("selectedRiskFormulaId", formulaId);
       	formulaId = null;
    	return aMapping.findForward("detailsSuccess") ;
	}
    
  	private void clearActionErrors(HttpServletRequest aRequest)
	{	
  		ActionErrors errors = new ActionErrors();
  		errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", UIConstants.EMPTY_STRING ) );			
  		saveErrors( aRequest, errors );
	}
}
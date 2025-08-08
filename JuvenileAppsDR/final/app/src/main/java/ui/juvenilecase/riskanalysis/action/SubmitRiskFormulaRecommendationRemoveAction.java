package ui.juvenilecase.riskanalysis.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.RemoveFormulaRecommendationEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

public class SubmitRiskFormulaRecommendationRemoveAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeFormula");
		keyMap.put("button.formulaDetails", "backToDetails");
		keyMap.put("button.back", "back");
	}
	
    public ActionForward removeFormula(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) getSessionForm(aMapping, aRequest, "riskFormulaDetailsForm", true);
		RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);
     	
    	RemoveFormulaRecommendationEvent removeEvent = 
    		(RemoveFormulaRecommendationEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVEFORMULARECOMMENDATION);
    	removeEvent.setRecommendationId(rfRecommendationForm.getRecommendationId());
    	
    	CompositeResponse cr = MessageUtil.postRequest(removeEvent);
    	
    	FormulaResponseEvent fre = 
    		(FormulaResponseEvent) MessageUtil.filterComposite(cr, FormulaResponseEvent.class);
    	
    	if (fre != null && !fre.getFormulaId().equals(rfDetailsForm.getFormulaId())){
    		rfDetailsForm.setFormulaId(fre.getFormulaId());
    		rfDetailsForm.setFormulaUpdatable(fre.isUpdatable());
    		rfDetailsForm.setVersion(fre.getVersion());
    		rfDetailsForm.setStatusId(fre.getStatus());
    		rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    		rfRecommendationForm.setFormulaId(fre.getFormulaId());
    		rfSearchForm.setFormulaId(fre.getFormulaId());
    	}
    	
    	List <FormulaRecommendationResponseEvent> recommendations = CollectionUtil.iteratorToList(MessageUtil.compositeToCollection(cr, FormulaRecommendationResponseEvent.class).iterator());
    	if (recommendations.size() > 0){
    		Collections.sort(recommendations);
    	}

		rfDetailsForm.setRecommendationsList(recommendations);
    	rfRecommendationForm.setCurrentList(recommendations);
     	
    	rfRecommendationForm.setPageType("confirm");
    	rfRecommendationForm.setConfirmMessage("Recommendation successfully removed.");
    	return aMapping.findForward("finishSuccess") ;
	}
    
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) getSessionForm(aMapping, aRequest, "riskFormulaDetailsForm", true);
    	rfDetailsForm.setRecommendationsList(rfRecommendationForm.getCurrentList());
    	rfRecommendationForm.clear();
    	return aMapping.findForward("backToDetails") ;
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm;
    	String forwardStr = "back";
    	rfRecommendationForm.setPageType("summary");
    	if ("RecommendationDetails".equalsIgnoreCase(rfRecommendationForm.getFromPage() ) )
    	{
    		forwardStr = "backToRecommendationDetail";
    		rfRecommendationForm.setFromPage(UIConstants.EMPTY_STRING);
    		rfRecommendationForm.setPageType(UIConstants.EMPTY_STRING);
    	}
    	return aMapping.findForward(forwardStr);
    }
}

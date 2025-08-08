package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveFormulaRecommendationEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaRecommendationForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

import org.apache.struts.action.ActionForward;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;


public class SubmitRiskFormulaRecommendationAddAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "addRecommendation");
		keyMap.put("button.formulaDetails", "backToDetails");
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward addRecommendation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm; 
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) getSessionForm(aMapping, aRequest, "riskFormulaDetailsForm", true);
		RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);

    	SaveFormulaRecommendationEvent saveEvent = 
    		(SaveFormulaRecommendationEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEFORMULARECOMMENDATION);
    	
    	saveEvent.setFormulaId(rfRecommendationForm.getFormulaId());
    	saveEvent.setRecommendationId(UIConstants.EMPTY_STRING);
    	saveEvent.setAssessmentTypeName(rfRecommendationForm.getFormulaName());
    	saveEvent.setRecommendationName(rfRecommendationForm.getRecommendationName());
    	saveEvent.setRecommendationText(rfRecommendationForm.getRecommendationText());
    	int score = 0;
    	if (rfRecommendationForm.getMinScore() != null && !UIConstants.EMPTY_STRING.equalsIgnoreCase(rfRecommendationForm.getMinScore() ) ) {
    		score = Integer.parseInt(rfRecommendationForm.getMinScore());
    	}
    	saveEvent.setMinScore(score);
    	score = 0;
    	if (rfRecommendationForm.getMaxScore() != null && !UIConstants.EMPTY_STRING.equalsIgnoreCase(rfRecommendationForm.getMaxScore() ) ) {
    		score = Integer.parseInt(rfRecommendationForm.getMaxScore());
    	}
    	saveEvent.setMaxScore(score);
    	boolean custody = false;
    	if ("true".equalsIgnoreCase(rfRecommendationForm.getInCustodyId() ) ) {
    		custody = true;
    	}
    	saveEvent.setCustody(custody);
    	saveEvent.setResultGroupId(rfRecommendationForm.getRiskResultGroupId());
    	CompositeResponse cr = MessageUtil.postRequest(saveEvent);
    	
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
    	rfRecommendationForm.setConfirmMessage("Recommendation successfully Added.");
    	return aMapping.findForward("finishSuccess");
    }
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskFormulaRecommendationForm rfRecommendationForm = (RiskFormulaRecommendationForm) aForm; 
    	rfRecommendationForm.setPageType(UIConstants.EMPTY_STRING);
    	rfRecommendationForm.setConfirmMessage(UIConstants.EMPTY_STRING);
// add code to retrieve latest recommendations for current formula     	
    	return aMapping.findForward("backToDetails");
    }
}

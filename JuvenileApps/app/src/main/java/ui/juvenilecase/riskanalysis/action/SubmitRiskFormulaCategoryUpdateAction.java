package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveFormulaCategoryEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

import org.apache.struts.action.ActionForward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitRiskFormulaCategoryUpdateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "updateCategory");
		keyMap.put("button.formulaDetails", "backToDetails");
	}
	
    public ActionForward updateCategory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
		RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);

    	SaveFormulaCategoryEvent saveEvent = (SaveFormulaCategoryEvent)
    		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEFORMULACATEGORY);
    	
    	saveEvent.setRiskFormulaId(rfDetailsForm.getFormulaId());
    	saveEvent.setCategories(new ArrayList());
    	
    	for (int i = 0; rfDetailsForm.getSelectedCategoriesList() != null && i < rfDetailsForm.getSelectedCategoriesList().size(); i++) {
			CategoryResponseEvent cre = (CategoryResponseEvent) rfDetailsForm.getSelectedCategoriesList().get(i);
			saveEvent.getCategories().add(cre.getCategoryId());
			cre = null;
		}
    	
    	CompositeResponse cr = MessageUtil.postRequest(saveEvent);
    	
    	FormulaResponseEvent fre = (FormulaResponseEvent) MessageUtil.filterComposite(cr, FormulaResponseEvent.class);
		List categoryList = MessageUtil.compositeToList(cr, CategoryResponseEvent.class);
		List recommendationList = MessageUtil.compositeToList(cr, FormulaRecommendationResponseEvent.class);
    	
    	if (!fre.getFormulaId().equals(rfDetailsForm.getFormulaId())){
    		//new version was created.
    		rfDetailsForm.setFormulaId(fre.getFormulaId());
    		rfDetailsForm.setFormulaUpdatable(fre.isUpdatable());
    		rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    		rfDetailsForm.setStatusId(fre.getStatus());
    		rfDetailsForm.setVersion(fre.getVersion());
    		rfSearchForm.setFormulaId(fre.getFormulaId());
    	} 
    	
     	if (recommendationList.size() > 1){
    		Collections.sort(recommendationList);
    	}
		rfDetailsForm.setRecommendationsList(recommendationList);
		
		if (categoryList.size() > 1){
			rfDetailsForm.setCurrentCategoriesList(RiskAnalysisUIHelper.sortCategories(categoryList) );
		} else {
			rfDetailsForm.setCurrentCategoriesList(categoryList);
		}
    	rfDetailsForm.setSelectedCategoriesList(new ArrayList());
    	rfDetailsForm.setConfirmMessage("Formula categories successfully updated.");
    	rfDetailsForm.setPageType("confirm");
    	
    	categoryList = null;
    	recommendationList = null;
    	saveEvent = null;
    	fre = null;

    	return aMapping.findForward("finishSuccess") ;
	}
    
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
       	rfDetailsForm.setPageType(UIConstants.EMPTY_STRING);
    	return aMapping.findForward("backToDetails") ;
	}
}

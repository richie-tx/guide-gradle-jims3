package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetCategoriesEvent;
import messaging.riskanalysis.GetFormulaDetailsEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskFormulaSearchForm;

public class DisplayRiskFormulaDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
	}

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setConfirmMessage(UIConstants.EMPTY_STRING);
    	rfDetailsForm.setRiskResultGroups(ComplexCodeTableHelper.getRiskResultGroups());
		
    	RiskFormulaSearchForm rfSearchForm = (RiskFormulaSearchForm)getSessionForm(aMapping, aRequest, "riskFormulaSearchForm", true);
    	
		String formulaId = aRequest.getParameter("selectedRiskFormulaId");
    	if (formulaId == null || UIConstants.EMPTY_STRING.equalsIgnoreCase(formulaId))
    	{
    		formulaId = rfSearchForm.getFormulaId();
    	}
    	rfDetailsForm.setFormulaId(formulaId);

    	GetFormulaDetailsEvent gfdEvent = 
			(GetFormulaDetailsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETFORMULADETAILS);		
		gfdEvent.setRiskFormulaId(formulaId);	
		
		CompositeResponse cr = MessageUtil.postRequest(gfdEvent); 
		
		FormulaResponseEvent fre =(FormulaResponseEvent) MessageUtil.filterComposite(cr, FormulaResponseEvent.class);
		List riskCategories =MessageUtil.compositeToList(cr, CategoryResponseEvent.class);
		
    	rfDetailsForm.setFormulaName(fre.getAssessmentTypeCd());
    	rfDetailsForm.setRiskTypeDesc(UIConstants.EMPTY_STRING);
    	rfDetailsForm.setVersion(fre.getVersion());
    	rfDetailsForm.setStatusDesc(fre.getStatusDesc());
    	rfDetailsForm.setEntryDate(DateUtil.dateToString(fre.getEntryDate(), DateUtil.DATE_FMT_1) );
    	rfDetailsForm.setActivationDateStr(DateUtil.dateToString(fre.getActivateDate(), DateUtil.DATE_FMT_1) );
    	fre.setCategories(RiskAnalysisUIHelper.sortCategories(fre.getCategories() ) );

    	this.loadRiskGroupDescriptions(riskCategories, rfDetailsForm.getRiskResultGroups());
    	
    	if (riskCategories.size() > 1){
    		rfDetailsForm.setCurrentCategoriesList(RiskAnalysisUIHelper.sortCategories(riskCategories));
    	} else {
    		rfDetailsForm.setCurrentCategoriesList(riskCategories);
    	}
    	rfDetailsForm.setRecommendationsList(new ArrayList());
    	rfDetailsForm.setSelectableCategoriesList(new ArrayList());
    	rfDetailsForm.setSelectedCategoriesList(new ArrayList());
    	rfDetailsForm.setFormulaUpdatable(fre.isUpdatable());

    	List rcmdList = MessageUtil.compositeToList( cr, FormulaRecommendationResponseEvent.class );
    	
    	if (rcmdList.size() > 0){
    		Collections.sort(rcmdList);
    	}
    	
    	rfDetailsForm.setRecommendationsList(rcmdList);
    	rcmdList = null;
	
    	GetCategoriesEvent getCategoryEvent = 
    		(GetCategoriesEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORIES);
    	
    	CompositeResponse categoryResponse = MessageUtil.postRequest(getCategoryEvent); 
       
    	riskCategories = MessageUtil.compositeToList( categoryResponse, CategoryResponseEvent.class );
    	if (riskCategories != null) {
    		this.loadRiskGroupDescriptions(riskCategories, rfDetailsForm.getRiskResultGroups());
    	}	
    	if (riskCategories.size() > 0){
    		rfDetailsForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(riskCategories) );
    	} else {
    		rfDetailsForm.setSelectableCategoriesList(new ArrayList());
    	}
    	riskCategories = null;
    	
    	return aMapping.findForward("viewSuccess") ;
	}
    
    private void loadRiskGroupDescriptions(List categoriesList, List codeTable)
    {
    	for (int x=0; x<categoriesList.size(); x++) {
			CategoryResponseEvent crEvent = (CategoryResponseEvent) categoriesList.get(x);
			for (int y=0; y<codeTable.size(); y++) {
				RiskResultGroupCodesResponseEvent cdre = (RiskResultGroupCodesResponseEvent) codeTable.get(y);
				if (cdre.getCode().equals(crEvent.getRiskResultGroupId() ) ) {
					crEvent.setRiskResultGroupDesc(cdre.getDescription());
					break;
				}
			}
    	}
    }
}
package ui.juvenilecase.riskanalysis.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetCategoriesEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
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
import ui.juvenilecase.riskanalysis.form.RiskFormulaCreateForm;

public class DisplayRiskFormulaCreateAction extends JIMSBaseAction
{
    
	protected void addButtonMapping(Map keyMap)
	{
	}

    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
    	RiskFormulaCreateForm rfCreateForm = (RiskFormulaCreateForm) aForm;
    	rfCreateForm.clear();
    	Object obj = aRequest.getAttribute("availAssessmentTypes");
    	List wrkList = (List) obj;
    	rfCreateForm.setAssessmentTypes(wrkList);
    	wrkList = null;
    	aRequest.setAttribute("availAssessmentTypes", null);
    	rfCreateForm.setAssessmentTypeId(UIConstants.EMPTY_STRING);
    	//Get Categories not associated to Formula
    	GetCategoriesEvent getCategoriesEvent = 
    		(GetCategoriesEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORIES);
//    	getCategoriesEvent.setReturnCategoriesNotAttachedToFormula(true);
     	CompositeResponse categoryResponse = MessageUtil.postRequest(getCategoriesEvent); 
     	List riskCategories = MessageUtil.compositeToList( categoryResponse, CategoryResponseEvent.class );
    	rfCreateForm.setRiskResultGroups(ComplexCodeTableHelper.getRiskResultGroups());
    	int catLen = riskCategories.size();
    	int grpLen = rfCreateForm.getRiskResultGroups().size();
    	for (int x=0; x<catLen; x++) {
       		CategoryResponseEvent catRe = (CategoryResponseEvent) riskCategories.get(x);
       		for (int y=0; y<grpLen; y++) {
       			RiskResultGroupCodesResponseEvent grpRe = (RiskResultGroupCodesResponseEvent) rfCreateForm.getRiskResultGroups().get(y);
       			if (catRe.getRiskResultGroupId().equalsIgnoreCase(grpRe.getCode())) {
       				catRe.setRiskResultGroupDesc(grpRe.getDescription());
       				break;
       			}
       		}
    	}
     	rfCreateForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(riskCategories) );

    	return aMapping.findForward("createSuccess") ;
	}
    
}
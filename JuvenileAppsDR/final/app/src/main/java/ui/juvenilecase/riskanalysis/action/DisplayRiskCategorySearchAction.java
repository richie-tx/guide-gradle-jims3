package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.GetRiskAnalysisControlCodesEvent;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.GetActiveAndPendingFormulasEvent;
import messaging.riskanalysis.GetCategoriesEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaCategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.ComplexCodeTableHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;

public class DisplayRiskCategorySearchAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
        //RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskCategorySearchForm riskCategorySearchForm = (RiskCategorySearchForm) aForm;
    	riskCategorySearchForm.setRiskCategoryId(UIConstants.EMPTY_STRING);
    	
    	 //Create Get Categories Event
    	GetCategoriesEvent getCategoryEvent = 
    		(GetCategoriesEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORIES);
//    	getCategoriesEvent.setReturnCategoriesNotAttachedToFormula(true);
    	
    	//Run Get Questions Event
    	CompositeResponse categoryResponse = MessageUtil.postRequest(getCategoryEvent); 
       
    	//Extract GetCategoryResponseEvents and place in List
    	List riskCategories = MessageUtil.compositeToList( categoryResponse, CategoryResponseEvent.class );
//		Collections.sort( riskCategories );
    	
    	//Set List in Form
    	riskCategorySearchForm.setRiskCategories(riskCategories);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Sort and Set Control Code List in Form
		riskCategorySearchForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes));
    	
    	//Get Formula info
    	GetActiveAndPendingFormulasEvent reqEvent =
			(GetActiveAndPendingFormulasEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETACTIVEANDPENDINGFORMULAS);		
    	List formulas = MessageUtil.postRequestListFilter(reqEvent, FormulaResponseEvent.class);
    	List wrkList1 = new ArrayList();
		List wrkList2 = new ArrayList();
		List categories = new ArrayList();
    	if (formulas != null && !formulas.isEmpty())
    	{
    		int len1 = formulas.size();
    		int len2 = 0;
    		FormulaCategoryResponseEvent fcre1 = new FormulaCategoryResponseEvent();
    		FormulaCategoryResponseEvent fcre2 = new FormulaCategoryResponseEvent();
    		String lit = UIConstants.EMPTY_STRING;
    		for (int y=0; y<len1; y++)
    		{
    			fcre1 = new FormulaCategoryResponseEvent();
    			FormulaResponseEvent frEvent = (FormulaResponseEvent) formulas.get(y);
    			lit = frEvent.getAssessmentTypeCd() + " Ver. " + frEvent.getVersion() + " - " + frEvent.getStatusDesc();
    			fcre1.setGroupId(frEvent.getFormulaId());
    			fcre1.setName(lit);
    			fcre2 = new FormulaCategoryResponseEvent();
    			len2 = frEvent.getCategories().size();
    			wrkList2 = new ArrayList();
    			categories = (List)frEvent.getCategories();
    			for (int x=0; x<len2; x++)
    			{
    				CategoryResponseEvent cre = (CategoryResponseEvent) categories.get(x);
    				fcre2 = new FormulaCategoryResponseEvent();
    				fcre2.setGroupId(cre.getCategoryId());
        			fcre2.setName(cre.getCategoryName());
        			fcre2.setSubgroups(new ArrayList());
        			wrkList2.add(fcre2);
    			}
    			Collections.sort(wrkList2);
    			fcre1.setSubgroups(wrkList2);
    			wrkList1.add(fcre1);
    		}
    		lit = null;
    		Collections.sort(wrkList1);
    	}

		//Set Formulas List in Form
		riskCategorySearchForm.setGroups(wrkList1);
		riskCategorySearchForm.setGroup2(new ArrayList());
		formulas = null;
		wrkList1 = null;
		wrkList2 = null;
		categories = null;
		
		//Load Risk Result Groups drop down selection List
    	riskCategorySearchForm.setRiskResultGroups(ComplexCodeTableHelper.getRiskResultGroups());

    	//return aMapping.getInputForward(); //uses input attribute in config mapping
    	return aMapping.findForward("success");
    }

	protected void addButtonMapping(Map keyMap)
	{
	}
}

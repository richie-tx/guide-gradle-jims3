package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.FormulaRecommendationResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

public class HandleRiskFormulaDetailsSelectionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.add", "addRecommendation");
		keyMap.put("button.update", "updateRecommendation");
		keyMap.put("button.remove", "removeRequest");
		keyMap.put("button.link", "viewRecommendation");
		keyMap.put("button.cancelSelection", "cancelSelections");
		keyMap.put("button.addSelected", "addSelectedCategory");
		keyMap.put("button.removeSelected", "removeSelectedCategory");
		keyMap.put("button.next", "nextPage");
		keyMap.put("button.back", "backToResults");
	}
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward addRecommendation(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	aRequest.setAttribute("riskFormulaName", rfDetailsForm.getFormulaName());
    	aRequest.setAttribute("riskFormulaId", rfDetailsForm.getFormulaId());
    	return aMapping.findForward("addRecommendationSuccess") ;
	}

	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward updateRecommendation(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	List wrkList = new ArrayList(); 
    	for (int x=0; x<rfDetailsForm.getRecommendationsList().size(); x++) {
    		FormulaRecommendationResponseEvent freEvent = (FormulaRecommendationResponseEvent) rfDetailsForm.getRecommendationsList().get(x);
    		if (freEvent.getRecommendationId().equals(rfDetailsForm.getSelectedRecommendationId() ) ) {
    			wrkList.add(freEvent);
    			break;
    		}
    	}
    	aRequest.setAttribute("selectedRecommendationInfo", wrkList);
    	wrkList = null;
    	
    	return aMapping.findForward("updateRecommendationSuccess") ;
	}

	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward removeRequest(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	String forwardStr = "removeAttachedCategorySuccess"; 
    	// RemoveType equals R for recommendation
    	if ("R".equals(rfDetailsForm.getRemoveType()))
    	{	
        	aRequest.setAttribute("recommendationsList", rfDetailsForm.getRecommendationsList() );
        	aRequest.setAttribute("selectedRecommendationId", rfDetailsForm.getSelectedRecommendationId() );
    		forwardStr = "removeRecommendationSuccess"; 
    	} 
   		return aMapping.findForward(forwardStr);
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward viewRecommendation(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	String recommendationId = aRequest.getParameter("recommendationId");
    	List rList = loadRecommnedationEvent(recommendationId, rfDetailsForm.getRecommendationsList());;
    	aRequest.setAttribute("selectedRecommendationInfo", rList);
    	aRequest.setAttribute("recommendationsList", rfDetailsForm.getRecommendationsList());
    	rList = null;
    	aRequest.setAttribute("recommendationUpdatable", rfDetailsForm.isFormulaUpdatable());
    	return aMapping.findForward("viewRecommendationSuccess") ;
	}
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward addSelectedCategory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
       	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List wrkList3 = new ArrayList();
	    wrkList1 = rfDetailsForm.getSelectableCategoriesList();;
    	wrkList2 = rfDetailsForm.getSelectedCategoriesList();
    	String[] selectedCategoryIds = rfDetailsForm.getSelectedValues();
    	int len = rfDetailsForm.getSelectedValues().length;
    	int len2 = wrkList1.size();
		
		for (int x=0; x<len; x++)
    	{
			for (int y=0; y<len2; y++)
    		{
    			CategoryResponseEvent cre = (CategoryResponseEvent) wrkList1.get(y);
        		if (selectedCategoryIds[x].equalsIgnoreCase(cre.getCategoryId() ) )
        		{
        			wrkList2.add(cre);
        		} 
    		}
    	}
		boolean matchFound = false;
		len = wrkList1.size();
    	for (int x=0; x<len; x++)
    	{
     		CategoryResponseEvent cre1 = (CategoryResponseEvent) wrkList1.get(x);
    		matchFound = false;
    		for (int y=0; y<wrkList2.size(); y++)
    		{
    			CategoryResponseEvent cre2 = (CategoryResponseEvent) wrkList2.get(y);
    			if (cre2.getCategoryId().equalsIgnoreCase(cre1.getCategoryId()))
    			{
    				matchFound = true;
    				break;
    			}
    		}
    		if (matchFound == false)
    		{
    			wrkList3.add(cre1);
    		}
     	}
    	rfDetailsForm.setSelectedCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	rfDetailsForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList3));
    	wrkList1 = null;
    	wrkList2 = null;
    	wrkList3 = null;
    	return aMapping.findForward("shoppingCartSuccess") ;
	}

	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward removeSelectedCategory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List selectedList = rfDetailsForm.getSelectedCategoriesList();
    	int len = selectedList.size();
    	String selectedId = aRequest.getParameter("categoryId");
    	for (int x=0; x<len; x++)
    	{
       		CategoryResponseEvent cre = (CategoryResponseEvent) selectedList.get(x);
       		if (selectedId.equalsIgnoreCase(cre.getCategoryId() ) )
       		{
       			wrkList1.add(cre);
       		} else {
       			wrkList2.add(cre);       			
       		}
    	}
    	rfDetailsForm.setSelectedCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	wrkList2 = rfDetailsForm.getSelectableCategoriesList();
    	wrkList2.add(wrkList1.get(0));
    	rfDetailsForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList2));
    	wrkList1 = null;
    	wrkList2 = null;
    	selectedList = null;
   	   	return aMapping.findForward("shoppingCartSuccess"); 
	}

	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward cancelSelections(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	List wrkList = new ArrayList();
    	wrkList = rfDetailsForm.getSelectableCategoriesList();
    	int len = rfDetailsForm.getSelectedCategoriesList().size();
    	for (int x=0; x<len; x++)
    	{
    		CategoryResponseEvent cre = (CategoryResponseEvent)rfDetailsForm.getSelectedCategoriesList().get(x);
    		wrkList.add(cre);
    	}
    	rfDetailsForm.setSelectableCategoriesList(RiskAnalysisUIHelper.sortCategories(wrkList));
    	rfDetailsForm.setSelectedCategoriesList(new ArrayList());
    	wrkList = null;
    	return aMapping.findForward("shoppingCartSuccess") ;
	}
    
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward nextPage(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	rfDetailsForm.setPageType("summary");
    	return aMapping.findForward("nextSuccess") ;
	}

	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward backToResults(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
    	return aMapping.findForward("back") ;
	} 
    
	/**
     * @param String
     * @param List
     * @return List
     */    
    private List loadRecommnedationEvent(String recommendationId, List recommendationsList)
    {
		List rList = new ArrayList();
		for (int x=0; x<recommendationsList.size(); x++) {
			FormulaRecommendationResponseEvent freEvent = (FormulaRecommendationResponseEvent) recommendationsList.get(x);
			if (freEvent.getRecommendationId().equals(recommendationId) ) {
				rList.add(freEvent);
				break;
			}
		}
		return rList;
    }

}
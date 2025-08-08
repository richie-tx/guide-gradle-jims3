package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.RiskCategoryCreateForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDetailsPopupForm;

public class DisplayRiskCategoryCreateSummaryAction extends JIMSBaseAction
{
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	   {
		    RiskCategoryCreateForm categoryCreateForm = (RiskCategoryCreateForm) aForm;
		    categoryCreateForm.setActionType("summary");
		    List rrGroups = (List) categoryCreateForm.getRiskResultGroups();
		    String rrGroupsId = categoryCreateForm.getCategory().getRiskResultGroupId();
		    for (int x=0; x<rrGroups.size(); x++)
		    {
		    	RiskResultGroupCodesResponseEvent cre = (RiskResultGroupCodesResponseEvent) rrGroups.get(x);
		    	if (rrGroupsId.equalsIgnoreCase(cre.getCode() ) ) {
		    		categoryCreateForm.getCategory().setRiskResultGroup(cre.getDescription());
		    		break;
		    	}
		    }
		   	return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	   }
	
	/**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
	    */
	   public ActionForward addSelected(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	   {    
		    
		    RiskCategoryCreateForm catCreateForm = (RiskCategoryCreateForm)aForm;
		    List wrkList1 = catCreateForm.getQuestionList();;
	    	List wrkList2 = new ArrayList();
	    	List selectableList = catCreateForm.getNewQuestionList();
	    	int len = catCreateForm.getSelectedValue().length;
			String[] selectedQuestionIds = catCreateForm.getSelectedValue();
			
			for (int x=0; x<len; x++)
	    	{
	    		for (int y=0; y<catCreateForm.getNewQuestionList().size(); y++)
	    		{
	        		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) selectableList.get(y);
	        		if (selectedQuestionIds[x].equalsIgnoreCase(gre.getQuestionId()))
	        		{
	        			wrkList1.add(gre);
	        		} 
	    		}
	    	}
			String matchFound = "N";
	    	len = catCreateForm.getNewQuestionList().size();
	    	for (int x=0; x<len; x++)
	    	{
	    		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) selectableList.get(x);
	    		matchFound = "N";
	    		for (int y=0; y<wrkList1.size(); y++)
	    		{
	    			GetQuestionResponseEvent gre2 = (GetQuestionResponseEvent) wrkList1.get(y);
	    			if (gre.getQuestionId().equalsIgnoreCase(gre2.getQuestionId()))
	    			{
	    				matchFound = "Y";
	    				break;
	    			}
	    		}
	    		if ("N".equals(matchFound))
	    		{
	    			wrkList2.add(gre);
	    		}	
	     	}
	    	Collections.sort(wrkList2);
	    	Collections.sort(wrkList1);
	    	catCreateForm.setNewQuestionList(wrkList2);
	    	catCreateForm.setQuestionList(wrkList1);
	    	wrkList1 = null;
	    	wrkList2 = null;
	    	selectableList = null;
	    	matchFound = null;
	
			return aMapping.findForward(UIConstants.ADD_SELECTED_SUCCESS);
	   }
	   
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
	    */
	   public ActionForward removeSelected(
	   		ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	   {    
		    
		    RiskCategoryCreateForm catCreateForm = (RiskCategoryCreateForm)aForm;
	    	List wrkList1 = new ArrayList();
	    	List wrkList2 = new ArrayList();
	    	List selectedList = catCreateForm.getQuestionList();
	    	int len = selectedList.size();
	    	String selectedId = aRequest.getParameter("questionID");
	    	for (int x=0; x<len; x++)
	    	{
	       		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) selectedList.get(x);
	       		if (selectedId.equalsIgnoreCase(gre.getQuestionId()))
	       		{
	       			wrkList1.add(gre);
	       		} else {
	       			wrkList2.add(gre);       			
	       		}
	    	}
	    	Collections.sort(wrkList2);
	    	catCreateForm.setQuestionList(wrkList2);
	    	wrkList2 = catCreateForm.getNewQuestionList();
	    	wrkList2.add(wrkList1.get(0));
	    	Collections.sort(wrkList2);
	    	catCreateForm.setNewQuestionList(wrkList2);
	    	wrkList1 = null;
	    	wrkList2 = null;
	    	selectedList = null;

		return aMapping.findForward(UIConstants.REMOVE_SUCCESS);
   }
	   
	   /**
	     * @param aMapping
	     * @param aForm
	     * @param aRequest
	     * @param aResponse
	     * @return ActionForward
	     */
    public ActionForward questionDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	    {   
	    	RiskQuestionDetailsPopupForm rqDetailsPopupForm = (RiskQuestionDetailsPopupForm)getSessionForm(aMapping, aRequest, "riskQuestionDetailsPopupForm", true); 
	    	String selectedQuestionId = aRequest.getParameter("questionID");
	    	String showCategory = aRequest.getParameter("showCategory");
	    	if (showCategory == null) {
	    		showCategory = "Y";
	    	}
	    	rqDetailsPopupForm.setShowCategory(showCategory);
			GetQuestionsEvent questEvent =
				(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
			questEvent.setReturnSingleQuestionBasedOnId(true);
			questEvent.setQuestionId(selectedQuestionId);
	    	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
	    	
	    	List wrkList1 = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
	    	if (wrkList1 != null && wrkList1.size() > 0)
	    	{	
	    		RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm)getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);	    
	    		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList1.get(0);
	    		rqDetailsPopupForm.getQuestion().setRiskQuestionId(gre.getQuestionId());
	    		rqDetailsPopupForm.getQuestion().setQuestionName(gre.getQuestionName());
	    		rqDetailsPopupForm.getQuestion().setQuestonEntryDate(gre.getQuestonEntryDate());
	    		rqDetailsPopupForm.getQuestion().setQuestionText(gre.getQuestionText());
	    		rqDetailsPopupForm.getQuestion().setUiControlType(gre.getUiControlType());
	    		rqDetailsPopupForm.getQuestion().setCollapsibleHeader(new Boolean (gre.isCollapsibleHeader()).toString());
	    		rqDetailsPopupForm.getQuestion().setUiDisplayOrder(gre.getUiDisplayOrder());
	    		rqDetailsPopupForm.getQuestion().setAllowFutureDates(new Boolean(gre.isAllowsFutureDates()).toString());
	    		rqDetailsPopupForm.getQuestion().setNumericOnly(new Boolean(gre.isNumeric()).toString());
	    		rqDetailsPopupForm.getQuestion().setHardcoded(new Boolean(gre.isHardcoded()).toString());
	    		rqDetailsPopupForm.getQuestion().setQuestionInitialAction(gre.getInitialAction());
	    		rqDetailsPopupForm.getQuestion().setRequired(new Boolean(gre.isRequired()).toString());
	    		rqDetailsPopupForm.getQuestion().setRiskCategoryId(String.valueOf(gre.getRiskCategoryId()));
	    		rqDetailsPopupForm.getQuestion().setAllowPrint(new Boolean(gre.isAllowPrint()).toString());
	 			
	   			String controlCodeLit = UIConstants.EMPTY_STRING;
	 			if (gre.getControlCode() != null && !UIConstants.EMPTY_STRING.equals(gre.getControlCode()))
	   			{
					controlCodeLit = RiskAnalysisHelper.getControlCodeName(rcSearchForm.getControlCodes(), gre.getControlCode());
	   			}
	 			rqDetailsPopupForm.getQuestion().setControlCode(controlCodeLit);
	 			rqDetailsPopupForm.setAnswerList(new ArrayList());
	 			GetAnswersEvent ansEvent =
	 				(GetAnswersEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
	 			ansEvent.setQuestionId(selectedQuestionId);
	 			ansEvent.setReturnAnswersBasedOnQuestionId(true);
	 	    	CompositeResponse ansResponse = MessageUtil.postRequest(ansEvent);
	 	    	
	 	    	List wrkList2 = MessageUtil.compositeToList( ansResponse, GetAnswerResponseEvent.class );
	 	    	if (wrkList2 != null)
	 	    	{
	 	    		Collections.sort(wrkList2);
	 	    		rqDetailsPopupForm.setAnswerList(wrkList2);
	 	    	}
	 	    	wrkList1 = null;
	 	    	wrkList2 = null;
	    	}
	    	return aMapping.findForward("viewSuccess");
	    }    
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			RiskCategoryCreateForm rcCreateForm = (RiskCategoryCreateForm)aForm;
			rcCreateForm.setNewQuestionList(new ArrayList());
			rcCreateForm.setQuestionList(new ArrayList());
			rcCreateForm.clearCategory();
			return aMapping.findForward(UIConstants.CANCEL);
		}

	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			RiskCategoryCreateForm rcCreateForm = (RiskCategoryCreateForm)aForm;
			rcCreateForm.setNewQuestionList(new ArrayList());
			rcCreateForm.setQuestionList(new ArrayList());
			rcCreateForm.clearCategory();
			return aMapping.findForward(UIConstants.BACK);	
		}
	   
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.link", "questionDetails");
		keyMap.put("button.addSelected","addSelected");
		keyMap.put("button.remove", "removeSelected");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
}

package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryUpdateForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;

public class HandleRiskCategoryDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.update", "updateRequest");
		keyMap.put("button.delete", "deleteRequest");
		keyMap.put("button.remove", "removeRequest");
		keyMap.put("button.view", "viewQuestionRequest");
	}
	private static final String UPDATE_QUESTION = "Q";
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updateRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm) aForm;
		RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm)getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	String forwardStr = "updateCategoryRequest";
    	if (UPDATE_QUESTION.equals(rcDetailsForm.getUpdateType() ) )
		{
// add code to load question data based on form selected value as question id?  
    		RiskQuestionUpdateForm rqUpdateForm = (RiskQuestionUpdateForm)getSessionForm(aMapping, aRequest, "riskQuestionUpdateForm", true);
    		String[] questId = rcDetailsForm.getSelectedValues();
    		GetQuestionsEvent questEvent =
    			(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
//    		questEvent.setReturnQuestionsNotAttachedToCategory(false);
    		questEvent.setReturnSingleQuestionBasedOnId(true);
    		questEvent.setQuestionId(questId[0]);
        	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
        	wrkList1 = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
        	if (wrkList1 != null && wrkList1.size() > 0)
        	{	
	        	GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList1.get(0);
	   			rqUpdateForm.setQuestions(wrkList2);
	   			rqUpdateForm.getQuestion().setRiskQuestionId(gre.getQuestionId());
	   			rqUpdateForm.getQuestion().setQuestionName(gre.getQuestionName());
	   			rqUpdateForm.getQuestion().setQuestonEntryDate(gre.getQuestonEntryDate());
	   			rqUpdateForm.getQuestion().setQuestionText(gre.getQuestionText());
	   			rqUpdateForm.getQuestion().setUiControlType(gre.getUiControlType());
	   			rqUpdateForm.getQuestion().setUiDisplayOrder(gre.getUiDisplayOrder());
	 			rqUpdateForm.getQuestion().setAllowFutureDates(new Boolean(gre.isAllowsFutureDates()).toString());
	 			rqUpdateForm.getQuestion().setNumericOnly(new Boolean(gre.isNumeric()).toString());
	 			rqUpdateForm.getQuestion().setHardcoded(new Boolean(gre.isHardcoded()).toString());
	   			rqUpdateForm.getQuestion().setQuestionInitialAction(gre.getInitialAction());
	 			rqUpdateForm.getQuestion().setRequired(new Boolean(gre.isRequired()).toString());
	   			rqUpdateForm.getQuestion().setControlCode(gre.getControlCode());
	   			rqUpdateForm.getQuestion().setRiskCategoryId(String.valueOf(gre.getRiskCategoryId()));
	   			rqUpdateForm.getQuestion().setAllowPrint(new Boolean(gre.isAllowPrint()).toString());
        	}
        	rqUpdateForm.setControlCodes(rcSearchForm.getControlCodes());
        	rqUpdateForm.setUiControlTypes(SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.JUV_RISK_UI_CONTROL_TYPE));
        	questEvent = null;
    		forwardStr = "updateQuestionRequest";
		} else {
    		RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm)getSessionForm(aMapping, aRequest, "riskCategoryUpdateForm", true);
    		rcUpdateForm.setRiskResultGroups(rcSearchForm.getRiskResultGroups());
    		rcUpdateForm.getCategory().setCategoryName(rcDetailsForm.getCategory().getCategoryName());
    		rcUpdateForm.getCategory().setEntryDate(rcDetailsForm.getCategory().getEntryDate());
    		rcUpdateForm.getCategory().setRiskResultGroupId(rcDetailsForm.getCategory().getRiskResultGroupId());
       		rcUpdateForm.getCategory().setVersion(rcDetailsForm.getCategory().getVersion());
       		rcUpdateForm.getCategory().setCategoryDescription(rcDetailsForm.getCategory().getCategoryDescription());
    		rcUpdateForm.getCategory().setCategoryId(rcDetailsForm.getCategory().getCategoryId());
    		rcUpdateForm.getCategory().setUpdatable(rcDetailsForm.getCategory().isUpdatable());
    		rcUpdateForm.setQuestionList(rcDetailsForm.getQuestionsList());
        	GetQuestionsEvent questEvent = 
        		(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
        	
        	questEvent.setReturnQuestionsNotAttachedToCategory(true);
        	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
        	
        	List selectableList = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
        	
        	wrkList2 = rcUpdateForm.getQuestionList();
        	int len = selectableList.size(); 
        	int len2 = rcUpdateForm.getQuestionList().size();
        	boolean matchFound = false;
        	GetQuestionResponseEvent gre = null;
        	GetQuestionResponseEvent gre2 = null;
// remove existing category questions from all questions list
        	if (len2 > 0)
        	{	
	        	for (int x=0; x<len; x++)
	        	{
	        		gre = (GetQuestionResponseEvent) selectableList.get(x);
        			matchFound = false;
	        		for (int y=0; y<len2; y++)
	        		{
	        			gre2 = (GetQuestionResponseEvent) wrkList2.get(y);
	        			if (gre.getQuestionId().equals(gre2.getQuestionId() ) )
	        			{
	        				matchFound = true;
	        				break;
	        			}
	        		}	
	        		if (matchFound == false)
	        		{
	        			wrkList1.add(gre);
	        		}	
	        	}
        	} else {
        		wrkList1 = selectableList;
        	}
        	if (!wrkList1.isEmpty()) {
        		Collections.sort(wrkList1);
			}
        	wrkList1 = UIRiskAnalysisHelper.sortQuestions(wrkList1);
        	rcUpdateForm.setNewQuestionList(wrkList1);
        	gre = null;
        	gre2 = null;
        	selectableList = null;
        	questEvent = null;
        	questResponse = null;
		}
    	wrkList1 = null;
    	wrkList2 = null;
    	return aMapping.findForward(forwardStr);
    }
 
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updateQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
//    	RiskCategoryDetailsForm rcdForm = (RiskCategoryDetailsForm) aForm;
    	return aMapping.findForward("updateQuestionRequest");
    } 
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward deleteRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm) aForm;
    	RiskCategoryDeleteForm rcDeleteForm = (RiskCategoryDeleteForm)getSessionForm(aMapping, aRequest, "riskCategoryDeleteForm", true);
    	rcDeleteForm.clear();
    	rcDeleteForm.setPageType("summary");

    	rcDeleteForm.getCategory().setCategoryId(rcDetailsForm.getCategory().getCategoryId());
    	rcDeleteForm.getCategory().setCategoryName(rcDetailsForm.getCategory().getCategoryName());
    	rcDeleteForm.getCategory().setEntryDate(rcDetailsForm.getCategory().getEntryDate());
    	rcDeleteForm.getCategory().setModificatoinDate(rcDetailsForm.getCategory().getModificatoinDate());
    	rcDeleteForm.getCategory().setRiskResultGroupId(rcDetailsForm.getCategory().getRiskResultGroupId());
    	rcDeleteForm.getCategory().setVersion(rcDetailsForm.getCategory().getVersion());

    	rcDeleteForm.setQuestionsList(rcDetailsForm.getQuestionsList());
    	return aMapping.findForward("deleteCategoryRequest");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
    {   
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm) aForm;
    	RiskQuestionDeleteForm rqDeleteForm = (RiskQuestionDeleteForm)getSessionForm(aMapping, aRequest, "riskQuestionDeleteForm", true);
    	rqDeleteForm.clearForm();
    	rqDeleteForm.setPageType("summary");
    	rqDeleteForm.setCurrentQuestions(rcDetailsForm.getQuestionsList());
    	List removeList = new ArrayList();
    	String[] questIds = rcDetailsForm.getSelectedValues();
    	int len = rcDetailsForm.getQuestionsList().size();
    	int len2 = questIds.length;
    	for (int x=0; x<len; x++)
    	{
    		GetQuestionResponseEvent qrEvent = (GetQuestionResponseEvent) rcDetailsForm.getQuestionsList().get(x);
    		for (int y=0; y<len2; y++)
    		{	
	    		if (qrEvent.getQuestionId().equalsIgnoreCase(questIds[y] ) )
	    		{
	    			removeList.add(qrEvent);
	    		}
    		}	
    	}
    	rqDeleteForm.setCategoryId(rcDetailsForm.getCategory().getCategoryId());
    	rqDeleteForm.setRemoveQuestions(removeList);
    	removeList = null;
    	return aMapping.findForward("removeQuestionRequest");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward viewQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	HandleRiskQuestionDetailsSelectionForm hrqDetailsForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm)getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	hrqDetailsForm.getCategory().setCategoryId(rcDetailsForm.getCategory().getCategoryId());
    	hrqDetailsForm.getCategory().setCategoryName(rcDetailsForm.getCategory().getCategoryName());
    	hrqDetailsForm.getCategory().setFormulaStatusCd(rcDetailsForm.getCategory().getFormulaStatusCd());
    	hrqDetailsForm.getCategory().setUpdatable(rcDetailsForm.getCategory().isUpdatable());
    	
    	hrqDetailsForm.clearForm();
    	String questionId = aRequest.getParameter("questionId");
		GetQuestionsEvent questEvent =
			(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
//		questEvent.setReturnQuestionsNotAttachedToCategory(false);
		questEvent.setReturnSingleQuestionBasedOnId(true);
		questEvent.setQuestionId(questionId);
    	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
    	
    	List wrkList1 = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
    	if (wrkList1 != null && wrkList1.size() > 0)
    	{	
        	GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList1.get(0);
   			hrqDetailsForm.getQuestion().setRiskQuestionId(gre.getQuestionId());
   			hrqDetailsForm.getQuestion().setQuestionName(gre.getQuestionName());
   			hrqDetailsForm.getQuestion().setQuestonEntryDate(gre.getQuestonEntryDate());
   			hrqDetailsForm.getQuestion().setQuestionText(gre.getQuestionText());
   			hrqDetailsForm.getQuestion().setUiControlType(gre.getUiControlType());
   			hrqDetailsForm.getQuestion().setCollapsibleHeader(new Boolean (gre.isCollapsibleHeader()).toString());
   			hrqDetailsForm.getQuestion().setUiDisplayOrder(gre.getUiDisplayOrder());
 			hrqDetailsForm.getQuestion().setAllowFutureDates(new Boolean(gre.isAllowsFutureDates()).toString());
 			hrqDetailsForm.getQuestion().setNumericOnly(new Boolean(gre.isNumeric()).toString());
 			hrqDetailsForm.getQuestion().setHardcoded(new Boolean(gre.isHardcoded()).toString());
   			hrqDetailsForm.getQuestion().setQuestionInitialAction(gre.getInitialAction());
 			hrqDetailsForm.getQuestion().setRequired(new Boolean(gre.isRequired()).toString());
			hrqDetailsForm.getQuestion().setRiskCategoryId(String.valueOf(gre.getRiskCategoryId()));
 			hrqDetailsForm.getQuestion().setControlCode(gre.getControlCode());
 			hrqDetailsForm.getQuestion().setAllowPrint(new Boolean(gre.isAllowPrint()).toString());
 			
   			String controlCodeLit = UIConstants.EMPTY_STRING;
 			if (gre.getControlCode() != null && !UIConstants.EMPTY_STRING.equals(gre.getControlCode()))
   			{
				controlCodeLit = RiskAnalysisHelper.getControlCodeName(rcSearchForm.getControlCodes(), gre.getControlCode());
   			}
 			hrqDetailsForm.getQuestion().setControlCodeName(controlCodeLit);
 			hrqDetailsForm.setAnswerList(new ArrayList());
 			GetAnswersEvent ansEvent =
 				(GetAnswersEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
 			ansEvent.setQuestionId(questionId);
 			ansEvent.setReturnAnswersBasedOnQuestionId(true);
 	    	CompositeResponse ansResponse = MessageUtil.postRequest(ansEvent);
 	    	
 	    	List wrkList2 = MessageUtil.compositeToList( ansResponse, GetAnswerResponseEvent.class );
 	    	if (wrkList2 != null)
 	    	{
 	    		Collections.sort(wrkList2);
 	    		hrqDetailsForm.setAnswerList(wrkList2);
 	    	}
 	    	wrkList1 = null;
 	    	wrkList2 = null;
    	}
    	questEvent = null;
    	aRequest.setAttribute("riskQuestionId", questionId);    	
    	return aMapping.findForward("viewQuestionSuccess");
    } 
}

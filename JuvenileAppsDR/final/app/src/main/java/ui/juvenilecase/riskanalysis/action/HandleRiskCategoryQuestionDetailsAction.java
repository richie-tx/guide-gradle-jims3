package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerCreateForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;

import org.apache.struts.action.ActionForward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

import ui.juvenilecase.riskanalysis.form.objects.Answer;
public class HandleRiskCategoryQuestionDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.update", "updateRequest");
		keyMap.put("button.remove", "removeQuestionRequest");
		keyMap.put("button.add", "addAnswerRequest");
		keyMap.put("button.delete", "deleteAnswerRequest");
		keyMap.put("button.back", "backToCategoryDetails");
	}
	
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
    	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm 	= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	String forwardStr = "updateQuestionSuccess";
    	if ("Q".equalsIgnoreCase(hrQDetailsSelectionForm.getUpdateType() ) )
    	{		
    		aRequest.getSession().setAttribute("riskQuestionId", hrQDetailsSelectionForm.getQuestion().getRiskQuestionId());
    		RiskQuestionUpdateForm rqUpdateForm = (RiskQuestionUpdateForm) getSessionForm(aMapping, aRequest, "riskQuestionUpdateForm", true);
    		rqUpdateForm.setQuestion(hrQDetailsSelectionForm.getQuestion());
    		GetQuestionsEvent questEvent =
    			(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
//    		questEvent.setReturnQuestionsNotAttachedToCategory(false);
    		questEvent.setReturnSingleQuestionBasedOnId(true);
    		questEvent.setQuestionId(hrQDetailsSelectionForm.getQuestion().getRiskQuestionId());
        	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
        	List wrkList1 = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
        	if (wrkList1 != null && wrkList1.size() > 0)
        	{	
	        	GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList1.get(0);
	// 			rqUpdateForm.setQuestions(wrkList2);
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
	   			rqUpdateForm.getQuestion().setControlCodeName(gre.getControlCodeDesc());
	   			rqUpdateForm.getQuestion().setRiskCategoryId(String.valueOf(gre.getRiskCategoryId())); 
	   			rqUpdateForm.getQuestion().setAllowPrint(new Boolean(gre.isAllowPrint()).toString());
        	}	
    		if (rqUpdateForm.getControlCodes() == null || rqUpdateForm.getControlCodes().isEmpty())
    		{	
    			RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    			rqUpdateForm.setControlCodes(rcSearchForm.getControlCodes());
    		}
    	} else {
        	RiskAnswerUpdateForm raUpdateForm = (RiskAnswerUpdateForm) getSessionForm(aMapping, aRequest, "riskAnswerUpdateForm", true);
        	raUpdateForm.clearCurrentAnswer();
        	String selAnsValue = hrQDetailsSelectionForm.getSelectedRiskAnswerIds()[0];
        	raUpdateForm.setSelectedValue(hrQDetailsSelectionForm.getQuestion().getRiskQuestionId());
        	for (int x=0; x<hrQDetailsSelectionForm.getAnswerList().size(); x++)
        	{
        		GetAnswerResponseEvent garEvent = (GetAnswerResponseEvent)hrQDetailsSelectionForm.getAnswerList().get(x);
        		if (garEvent.getRiskAnswerId().equalsIgnoreCase(selAnsValue) )
        		{
        			raUpdateForm.getCurrentAnswer().setRiskAnswerId(garEvent.getRiskAnswerId());
        			raUpdateForm.getCurrentAnswer().setAnswerEntryDate(garEvent.getAnswerEntryDate());
        			raUpdateForm.getCurrentAnswer().setAnswerText(garEvent.getResponse());
        			raUpdateForm.getCurrentAnswer().setWeight(Integer.toString(garEvent.getWeight() ) );
        			raUpdateForm.getCurrentAnswer().setSubordinateQuestionId(garEvent.getSubordinateQuestionId());
        			raUpdateForm.getCurrentAnswer().setSortOrder(Integer.toString(garEvent.getSortOrder() ) );
        			raUpdateForm.getCurrentAnswer().setAction(garEvent.getAction());
        			break;
        		}
        	}
        	selAnsValue = null;

        	GetQuestionsEvent getQuestionsEvent = 
        		(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
        	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(true);
        	CompositeResponse questionsReponse = MessageUtil.postRequest(getQuestionsEvent); 
        	List<GetQuestionResponseEvent> questions = MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );

        	if (raUpdateForm.getCurrentAnswer().getSubordinateQuestionId() != null
        			&& !raUpdateForm.getCurrentAnswer().getSubordinateQuestionId().equals(UIConstants.EMPTY_STRING)){
        		getQuestionsEvent = (GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
        		getQuestionsEvent.setQuestionId(raUpdateForm.getCurrentAnswer().getSubordinateQuestionId());
            	getQuestionsEvent.setReturnSingleQuestionBasedOnId(true);
        		questionsReponse = MessageUtil.postRequest(getQuestionsEvent); 
            	GetQuestionResponseEvent qre = (GetQuestionResponseEvent) MessageUtil.filterComposite(questionsReponse, GetQuestionResponseEvent.class);
        		if (qre != null){
        			questions.add(qre);
        		}
        	}
        	raUpdateForm.setQuestions(questions); 
     
        	forwardStr = "updateAnswerSuccess";
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
    public ActionForward removeQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
     	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm) aForm;
     	String questionId = hrQDetailsSelectionForm.getQuestion().getRiskQuestionId();
     	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	RiskQuestionDeleteForm rqDeleteForm = (RiskQuestionDeleteForm)getSessionForm(aMapping, aRequest, "riskQuestionDeleteForm", true);
    	rqDeleteForm.setCurrentQuestions(rcDetailsForm.getQuestionsList());
    	int cqLen = rqDeleteForm.getCurrentQuestions().size();
    	List removeList = new ArrayList();
    	for (int x=0; x<cqLen; x++)
    	{
    		GetQuestionResponseEvent qrEvent = (GetQuestionResponseEvent) rqDeleteForm.getCurrentQuestions().get(x);
    		if (questionId.equalsIgnoreCase(qrEvent.getQuestionId() ) ) {
    			removeList.add(qrEvent);
    			break;
    		}
    	}
    	rqDeleteForm.setRemoveQuestions(removeList);
    	removeList = null;
    	rqDeleteForm.setPageType("summary");
    	return aMapping.findForward("removeQuestionSuccess");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
    {   
    	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm) aForm;
    	RiskAnswerCreateForm raCreateForm = (RiskAnswerCreateForm) getSessionForm(aMapping, aRequest, "riskAnswerCreateForm", true);
    	raCreateForm.setCurrentAnswer(new Answer());
    	raCreateForm.getCurrentAnswer().setAnswerEntryDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
    	raCreateForm.setNewAnswerList(new ArrayList());
    	aRequest.getSession().setAttribute("riskQuestionId", hrQDetailsSelectionForm.getQuestion().getRiskQuestionId());

    	GetQuestionsEvent getQuestionsEvent = 
    		(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(true);
    	CompositeResponse questionsReponse = MessageUtil.postRequest(getQuestionsEvent); 
    	List<GetQuestionResponseEvent> questions = MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );

    	GetAnswersEvent getAnswersEvent =
    		(GetAnswersEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(true);    	
    	CompositeResponse answersReponse = MessageUtil.postRequest(getAnswersEvent); 
    	List<GetAnswerResponseEvent> answers = MessageUtil.compositeToList( answersReponse, GetAnswerResponseEvent.class );
//Remove questions from list that are already subordinate questions
    	List questionsFiltered = RiskAnalysisHelper.removeSubordinateQuestions(questions, answers);
    	raCreateForm.setQuestions(questionsFiltered);
   	
    	return aMapping.findForward("addAnswerSuccess");
    }
 
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward deleteAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
    {   
     	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm 	= (HandleRiskQuestionDetailsSelectionForm) aForm;
    	RiskAnswerDeleteForm raDeleteForm = (RiskAnswerDeleteForm)getSessionForm(aMapping, aRequest, "riskAnswerDeleteForm", true);
    	
    	raDeleteForm.getQuestion().setRiskQuestionId(hrQDetailsSelectionForm.getQuestion().getRiskQuestionId());
    	raDeleteForm.getQuestion().setRiskCategoryId(hrQDetailsSelectionForm.getQuestion().getRiskCategoryId());
    	raDeleteForm.setRemoveAnswersList(new ArrayList());
    	
    	List wrkList = new ArrayList();
    	String[] sels = hrQDetailsSelectionForm.getSelectedRiskAnswerIds();
    	for (int x=0; x<hrQDetailsSelectionForm.getAnswerList().size(); x++)
    	{
    		GetAnswerResponseEvent garEvent = (GetAnswerResponseEvent)hrQDetailsSelectionForm.getAnswerList().get(x);
    		for (int y=0; y<sels.length; y++)
    		{	
    			if (garEvent.getRiskAnswerId().equalsIgnoreCase(sels[y]) )
    			{
    				wrkList.add(garEvent);
    				break;
    			}
    		}	
    	}
    	Collections.sort(wrkList);
    	raDeleteForm.setRemoveAnswersList(wrkList);
    	wrkList = null;
    	sels = null;
    	raDeleteForm.setPageType("summary");   	
    	return aMapping.findForward("deleteAnswerSuccess");
    } 
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward backToCategoryDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm)getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	rcSearchForm.setSelectedValue(rcDetailsForm.getCategory().getCategoryId());
    	return aMapping.findForward(UIConstants.BACK);
    } 
}

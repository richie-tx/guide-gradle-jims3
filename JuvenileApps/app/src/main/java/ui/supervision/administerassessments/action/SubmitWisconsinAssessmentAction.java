/*
 * Created on Feb 20, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.DeleteAssessmentEvent;
import messaging.administerassessments.UpdateWisconsinAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.CSTaskControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitWisconsinAssessmentAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.saveAsDraft","saveAsDraft");	
		keyMap.put("button.finish","finish");	
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::SubmitWisconsinAssessmentAction::saveAsDraft()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm) aForm;
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		if((wisconsinForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (wisconsinForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			ArrayList riskList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(wisconsinForm.getRiskAssessmentQuestionsList());
			ArrayList needsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(wisconsinForm.getNeedsAssessmentQuestionsList());
			ArrayList questionAnswerList = new ArrayList();
			questionAnswerList.addAll(riskList);
			questionAnswerList.addAll(needsList);
			wisconsinForm.setQuestionAnswerList(questionAnswerList);
			wisconsinForm.setAssessmentIncomplete(true);
			
			UpdateWisconsinAssessmentEvent wisconsinRequestEvent = (UpdateWisconsinAssessmentEvent)AdminAssessmentsHelper.getUpdateWisconsinAssessmentEvent(wisconsinForm);
			
	        CompositeResponse response = this.postRequestEvent(wisconsinRequestEvent);   
			MessageUtil.processReturnException(response);
			
			AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
            wisconsinForm.setAssessmentId(null);
            
            if(assessRespObj != null)
            {
            	wisconsinForm.setAssessmentId(assessRespObj.getAssessmentId());	
            }
		}
		 
    	if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
	    {       	
    		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
    		{
    			aRequest.setAttribute("confirmMessage","Wisconsin Assessment saved as draft.");
    		}
    		else
    		{
    			aRequest.setAttribute("confirmMessage","Wisconsin Reassessment saved as draft.");
    		}
    		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
	    } 
        wisconsinForm.setSecondaryAction(UIConstants.CONFIRM);
        
        return aMapping.findForward(forward);
	}//end of saveAsDraft()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::SubmitWisconsinAssessmentAction::finish()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm) aForm;
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteAssessmentEvent deleteRequestEvent = (DeleteAssessmentEvent)AdminAssessmentsHelper.getDeleteAssessmentEvent(wisconsinForm);
			
	        CompositeResponse response = this.postRequestEvent(deleteRequestEvent);   
			MessageUtil.processReturnException(response);
		}
		else 
		if((wisconsinForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (wisconsinForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			ArrayList riskList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(wisconsinForm.getRiskAssessmentQuestionsList());
			ArrayList needsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(wisconsinForm.getNeedsAssessmentQuestionsList());
			
//	  		validate if all the questions are answered
			ArrayList riskAssessQuestionList = (ArrayList)wisconsinForm.getRiskAssessmentQuestionsList();
			boolean isAllQuestionsAnswered = AdminAssessmentsHelper.validateWisconsinFields(riskAssessQuestionList);
			if(!isAllQuestionsAnswered)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "All the Risk Assessment fields are required");
				return aMapping.findForward(UIConstants.FAILURE);	
			}
			
			 ArrayList needsAssessQuestionList =(ArrayList)wisconsinForm.getNeedsAssessmentQuestionsList(); 
			 isAllQuestionsAnswered = AdminAssessmentsHelper.validateWisconsinFields(needsAssessQuestionList);
			  if(!isAllQuestionsAnswered) 
			  { 
			  	sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "All the Needs Assessment fields are required");
				return aMapping.findForward(UIConstants.FAILURE);	
			  }
			
			ArrayList questionAnswerList = new ArrayList();
			questionAnswerList.addAll(riskList);
			questionAnswerList.addAll(needsList);
			wisconsinForm.setQuestionAnswerList(questionAnswerList);
			
			wisconsinForm.setAssessmentIncomplete(false);
			
			UpdateWisconsinAssessmentEvent wisconsinRequestEvent = (UpdateWisconsinAssessmentEvent)AdminAssessmentsHelper.getUpdateWisconsinAssessmentEvent(wisconsinForm);
			
	        CompositeResponse response = this.postRequestEvent(wisconsinRequestEvent);   
			MessageUtil.processReturnException(response);
			
			AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
            wisconsinForm.setAssessmentId(null);
            
            if(assessRespObj != null)
            {
            	wisconsinForm.setAssessmentId(assessRespObj.getAssessmentId());
            }
		}  
		 
    	if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
	    {       	
	    	if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
	    	{
	    		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Assessment successfully created.");
	    		}
	    		else
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Reassessment successfully created.");
	    		}
	    		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
	    	}
	    	
	    	else if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
	    	{
	    		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Assessment successfully updated.");
	    		}
	    		else
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Reassessment successfully updated.");
	    		}
	    		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
	    	}
	    	
	    	else if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
	    	{
	    		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Assessment successfully deleted.");
	    		}
	    		else
	    		{
	    			aRequest.setAttribute("confirmMessage","Wisconsin Reassessment successfully deleted.");
	    		}  
	    		forward=UIConstants.DELETE_CONFIRM_SUCCESS;
	    	}
	    	
	    } 
        wisconsinForm.setSecondaryAction(UIConstants.CONFIRM);
        
        if ( StringUtils.isNotEmpty( wisconsinForm.getTaskId() ) ){
			String taskId = wisconsinForm.getTaskId();			
			UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
		}
        
        return aMapping.findForward(forward);
	}//end of finish()
	
}// end of Class

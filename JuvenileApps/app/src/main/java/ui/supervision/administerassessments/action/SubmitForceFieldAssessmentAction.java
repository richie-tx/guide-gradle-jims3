/*
 * Created on Mar 3, 2008
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
import messaging.administerassessments.UpdateForceFieldAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
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
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitForceFieldAssessmentAction extends JIMSBaseAction
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
		System.out.println("AdministerAssessments::SubmitForceFieldAssessmentAction::saveAsDraft()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm) aForm;
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		ArrayList questionAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(forceFieldForm.getForceFieldQuestionsList());
		forceFieldForm.setQuestionAnswerList(questionAnsList);
		
		forceFieldForm.setAssessmentIncomplete(true);
		
		UpdateForceFieldAssessmentEvent forceFieldRequestEvent = (UpdateForceFieldAssessmentEvent)AdminAssessmentsHelper.getUpdateForceFieldAssessmentEvent(forceFieldForm);
        CompositeResponse response = this.postRequestEvent(forceFieldRequestEvent);   
		MessageUtil.processReturnException(response);
		 
        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
        forceFieldForm.setAssessmentId(null);
        
        if(assessRespObj != null)
        {
        	forceFieldForm.setAssessmentId(assessRespObj.getAssessmentId());
        	forceFieldForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
        }
		
    	if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
    	{       	
    		aRequest.setAttribute("confirmMessage","Force Field Analysis saved as draft.");
    		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
        } 
    	forceFieldForm.setSecondaryAction(UIConstants.CONFIRM);
    	
		return aMapping.findForward(forward);
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
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::SubmitForceFieldAssessmentAction::finish()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm) aForm;
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		if(forceFieldForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteAssessmentEvent deleteRequestEvent = (DeleteAssessmentEvent)AdminAssessmentsHelper.getDeleteAssessmentEvent(forceFieldForm);
			
	        CompositeResponse response = this.postRequestEvent(deleteRequestEvent);   
			MessageUtil.processReturnException(response);
		}
		else 
		if((forceFieldForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (forceFieldForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			ArrayList questionAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(forceFieldForm.getForceFieldQuestionsList());
			forceFieldForm.setQuestionAnswerList(questionAnsList);
			
			forceFieldForm.setAssessmentIncomplete(false);
			
			UpdateForceFieldAssessmentEvent forceFieldRequestEvent = (UpdateForceFieldAssessmentEvent)AdminAssessmentsHelper.getUpdateForceFieldAssessmentEvent(forceFieldForm);
	        CompositeResponse response = this.postRequestEvent(forceFieldRequestEvent);   
			MessageUtil.processReturnException(response);
			 
	        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
            forceFieldForm.setAssessmentId(null);
            
            if(assessRespObj != null)
            {
            	forceFieldForm.setAssessmentId(assessRespObj.getAssessmentId());
            }
		} 
		
    	if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
    	{       	
        	if(forceFieldForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
        	{
        		aRequest.setAttribute("confirmMessage","Force Field Analysis successfully created.");
        		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
        	}
	    	else if(forceFieldForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
	    	{
	    		aRequest.setAttribute("confirmMessage","Force Field Analysis successfully updated.");
	    		forward=UIConstants.CREATE_UPDATE_CONFIRM_SUCCESS;
	    	}
	    	else if(forceFieldForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
	    	{
	    		aRequest.setAttribute("confirmMessage","Force Field Analysis successfully deleted.");
	    		forward=UIConstants.DELETE_CONFIRM_SUCCESS;
	    	}
        } 
    	forceFieldForm.setSecondaryAction(UIConstants.CONFIRM);
    	
    	if ( StringUtils.isNotEmpty( forceFieldForm.getTaskId() ) ){
			String taskId = forceFieldForm.getTaskId();			
			UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
		}
        
        return aMapping.findForward(forward);
	}//end of finish()

}//end of Class

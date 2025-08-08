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
import messaging.administerassessments.UpdateSCSAssessmentEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.managetask.UpdateCSTaskEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
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
import ui.supervision.administerassessments.form.SCSAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitSCSAssessmentAction extends JIMSBaseAction
{
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next","next");	
		keyMap.put("button.saveContinue","saveNContinue");
		keyMap.put("button.saveAsDraft","saveAsDraft");	
		keyMap.put("button.finish","finish");	
		keyMap.put("button.finishNNewForceFieldAnalysis","finishNCreateForceField");
		keyMap.put("button.finishNUpdateForceFieldAnalysis","finishNUpdateForceField");
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
	public ActionForward saveNContinue(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::saveNContinue()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;
		
		String forward = UIConstants.SUMMARY_SUCCESS;
		
		ArrayList questionAnswerList = new ArrayList();
		
		ArrayList tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenOneQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenTwoQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenThreeQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenFourQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		
		scsForm.setQuestionAnswerList(questionAnswerList);
		
		scsForm.setAssessmentIncomplete(true);
		
		UpdateSCSAssessmentEvent scsRequestEvent = null;
		try
		{
			scsRequestEvent = (UpdateSCSAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSAssessmentEvent(scsForm);
		}
		catch(Exception ex)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Invalid SCS Scores");
			return aMapping.findForward(UIConstants.FAILURE);
		}
        CompositeResponse response = this.postRequestEvent(scsRequestEvent);   
		MessageUtil.processReturnException(response);
		
        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
        scsForm.setCreateUpdateSCSId("");
        scsForm.setCreateUpdateSCSMasterAssessId("");
        scsForm.setMasterAssessmentId("");
        
        if(assessRespObj != null)
        {
        	scsForm.setCreateUpdateSCSId(assessRespObj.getAssessmentId());
        	scsForm.setCreateUpdateSCSMasterAssessId(assessRespObj.getMasterAssessmentId());
        	scsForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
        }
        
        scsForm.setSecondaryAction(UIConstants.SUMMARY);
		
		return aMapping.findForward(forward);
	}// end of saveNContinue()
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::next()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;
		scsForm.setSecondaryAction(UIConstants.SUMMARY);
		
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}//end of next()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::saveAsDraft()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		
		String forward = UIConstants.CONFIRM_SUCCESS;
		
		if((scsForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (scsForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			scsForm.setForceFieldContinued(false);
			
			ArrayList questionAnswerList = new ArrayList();
			
			ArrayList tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenOneQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenTwoQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenThreeQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenFourQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			
			scsForm.setQuestionAnswerList(questionAnswerList);
			
			scsForm.setAssessmentIncomplete(true);
			
			UpdateSCSAssessmentEvent scsRequestEvent = null;
			try
			{
				scsRequestEvent = (UpdateSCSAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSAssessmentEvent(scsForm);
			}
			catch(Exception ex)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Invalid SCS scores");
				 return aMapping.findForward(UIConstants.FAILURE);
			}
			
	        CompositeResponse response = this.postRequestEvent(scsRequestEvent);   
			MessageUtil.processReturnException(response);
			
	        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
	        scsForm.setCreateUpdateSCSId("");
	        scsForm.setCreateUpdateSCSMasterAssessId("");
	        scsForm.setMasterAssessmentId("");
	        
	        if(assessRespObj != null)
	        {
	        	scsForm.setCreateUpdateSCSId(assessRespObj.getAssessmentId());
	        	scsForm.setCreateUpdateSCSMasterAssessId(assessRespObj.getMasterAssessmentId());
	        	scsForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
	        	
	        	
	        	
	        }
		}
		
		aRequest.setAttribute("confirmMessage","New SCS successfully saved as draft.");
		
        scsForm.setSecondaryAction(UIConstants.CONFIRM);        
        return aMapping.findForward(forward);
	}//end of saveAsDraft()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::finish()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		
		String forward = UIConstants.CONFIRM_SUCCESS;
		
		if(scsForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteAssessmentEvent deleteRequestEvent = (DeleteAssessmentEvent)AdminAssessmentsHelper.getDeleteAssessmentEvent(scsForm);
	        CompositeResponse response = this.postRequestEvent(deleteRequestEvent);   
			MessageUtil.processReturnException(response);
		}
		else 
		if((scsForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (scsForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			scsForm.setForceFieldContinued(false);
			scsForm.setAssessmentIncomplete(false);
			forward = scsFinishRequestPost(scsForm, aRequest);	
		}
	
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			if(scsForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
	    	{
	    		aRequest.setAttribute("confirmMessage","New SCS successfully created.");
	    	}
	    	else if(scsForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
	    	{
	    		aRequest.setAttribute("confirmMessage","SCS successfully updated.");
	    	}
	    	else if(scsForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
	    	{
	    		aRequest.setAttribute("confirmMessage","SCS successfully deleted.");
	    	}
			scsForm.setSecondaryAction(UIConstants.CONFIRM);  
		}
        return aMapping.findForward(forward);
	}//end of finish()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward finishNCreateForceField(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::finishNCreateForceField()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		scsForm.setAssessmentIncomplete(false);
		
		String forward = scsFinishRequestPost(scsForm, aRequest);
		
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			scsForm.setForceFieldContinued(true);
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
			forceFieldForm.setAssessmentDate(scsForm.getAssessmentDate());
			
			if(scsForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
			{
				scsForm.setConfirmationMsg("New SCS successfully created.");
				aRequest.setAttribute("confirmMessage","New SCS successfully created.");
			}
			else if(scsForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
			{
				scsForm.setConfirmationMsg("SCS successfully updated.");
				aRequest.setAttribute("confirmMessage","SCS successfully updated.");
			}
        	forward = UIConstants.CREATE_SUCCESS;
		}
        return aMapping.findForward(forward);
	}//end of finishNCreateForceField()
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward finishNUpdateForceField(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::finishNUpdateForceField()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		scsForm.setAssessmentIncomplete(false);
		
		String forward = scsFinishRequestPost(scsForm, aRequest);
		
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			scsForm.setForceFieldContinued(true);
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
			forceFieldForm.setAssessmentDate(scsForm.getAssessmentDate());
			scsForm.setConfirmationMsg("SCS successfully updated.");
			aRequest.setAttribute("confirmMessage","SCS successfully updated.");
        	forward = UIConstants.UPDATE_SUCCESS;
		}
		
        return aMapping.findForward(forward);
	}//end of finishNUpdateForceField()

	
	
	
	/**
	 * 
	 * @param scsForm
	 * @param aRequest
	 * @return
	 */
	private String scsFinishRequestPost(SCSAssessmentForm scsForm, HttpServletRequest aRequest) throws ReturnException
	{
		System.out.println("AdministerAssessments::SubmitSCSAssessmentAction::scsFinishRequestPost()::Method Begin");
		
		ArrayList questionAnswerList = new ArrayList();
		
		ArrayList tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenOneQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenTwoQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenThreeQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsForm.getScsScreenFourQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		
		scsForm.setQuestionAnswerList(questionAnswerList);
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		UpdateSCSAssessmentEvent scsRequestEvent = null;
		try
		{
			scsRequestEvent = (UpdateSCSAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSAssessmentEvent(scsForm);
		}
		catch(Exception ex)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Invalid SCS scores");
			 return UIConstants.FAILURE;
		}
        CompositeResponse response = this.postRequestEvent(scsRequestEvent);   
		MessageUtil.processReturnException(response);
		
        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
        scsForm.setCreateUpdateSCSId("");
        
        if(assessRespObj != null)
        {
        	scsForm.setCreateUpdateSCSId(assessRespObj.getAssessmentId());
        }
        if ( StringUtils.isNotEmpty( scsForm.getTaskId() ) ){
			String taskId = scsForm.getTaskId();			
			UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
		}
        return forward;
	}//end of scsFinishRequestPost()
	
}//end of Class

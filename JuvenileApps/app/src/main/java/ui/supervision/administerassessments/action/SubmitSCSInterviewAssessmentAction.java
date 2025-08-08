/*
 * Created on Jun 23, 2009
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
import messaging.administerassessments.UpdateSCSInterviewAssessmentEvent;
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
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;

/**
 * @author cc_bjangay
 *
 */
public class SubmitSCSInterviewAssessmentAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	@Override
	protected void addButtonMapping(Map keyMap) 
	{
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
	 */
	public ActionForward saveAsDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::SubmitSCSInterviewAssessmentAction::saveAsDraft()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		
		String forward = UIConstants.CONFIRM_SUCCESS;
		
		if((scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			scsInterviewForm.setForceFieldContinued(false);
			
			ArrayList questionAnswerList = new ArrayList();
			
			ArrayList tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenOneQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenTwoQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenThreeQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenFourQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenFiveQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenSixQuestionsList());
			questionAnswerList.addAll(tempQuesAnsList);
			
			scsInterviewForm.setQuestionAnswerList(questionAnswerList);
			
			scsInterviewForm.setAssessmentIncomplete(true);
			
			UpdateSCSInterviewAssessmentEvent scsInterviewRequestEvent = (UpdateSCSInterviewAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSInterviewAssessmentEvent(scsInterviewForm);
			
	        CompositeResponse response = this.postRequestEvent(scsInterviewRequestEvent);   
			MessageUtil.processReturnException(response);
			
	        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
	        scsInterviewForm.setCreateUpdateSCSInterviewId("");
	        scsInterviewForm.setCreateUpdateSCSIntervwMasterAssessId("");
	        scsInterviewForm.setMasterAssessmentId("");
	        
	        if(assessRespObj != null)
	        {
	        	scsInterviewForm.setCreateUpdateSCSInterviewId(assessRespObj.getAssessmentId());
	        	scsInterviewForm.setCreateUpdateSCSIntervwMasterAssessId(assessRespObj.getMasterAssessmentId());
	        	scsInterviewForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
	        }
		}
		
		aRequest.setAttribute("confirmMessage","New SCS interview successfully saved as draft.");
		
        scsInterviewForm.setSecondaryAction(UIConstants.CONFIRM);        
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
		System.out.println("AdministerAssessments::SubmitSCSInterviewAssessmentAction::finish()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		
		String forward = UIConstants.CONFIRM_SUCCESS;
		
		if(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
		{
			DeleteAssessmentEvent deleteRequestEvent = (DeleteAssessmentEvent)AdminAssessmentsHelper.getDeleteAssessmentEvent(scsInterviewForm);
	        CompositeResponse response = this.postRequestEvent(deleteRequestEvent);   
			MessageUtil.processReturnException(response);
		}
		else 
		if((scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.CREATE)) || (scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.UPDATE)))
		{
			scsInterviewForm.setForceFieldContinued(false);
			scsInterviewForm.setAssessmentIncomplete(false);
			forward = scsFinishRequestPost(scsInterviewForm, aRequest);	
		}  
	
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			if(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
	    	{
	    		aRequest.setAttribute("confirmMessage","New SCS Interview successfully created.");
	    	}
	    	else if(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
	    	{
	    		aRequest.setAttribute("confirmMessage","SCS Interview successfully updated.");
	    	}
	    	else if(scsInterviewForm.getAction().equalsIgnoreCase(UIConstants.DELETE))
	    	{
	    		aRequest.setAttribute("confirmMessage","SCS Interview successfully deleted.");
	    	}
			scsInterviewForm.setSecondaryAction(UIConstants.CONFIRM);  
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
		System.out.println("AdministerAssessments::SubmitSCSInterviewAssessmentAction::finishNCreateForceField()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		scsInterviewForm.setAssessmentIncomplete(false);
		
		String forward = scsFinishRequestPost(scsInterviewForm, aRequest);
		
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			scsInterviewForm.setForceFieldContinued(true);
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
			forceFieldForm.setAssessmentDate(scsInterviewForm.getAssessmentDate());
			scsInterviewForm.setConfirmationMsg("SCS Interview successfully created.");
			aRequest.setAttribute("confirmMessage","SCS Interview successfully created.");
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
		System.out.println("AdministerAssessments::SubmitSCSInterviewAssessmentAction::finishNUpdateForceField()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		scsInterviewForm.setAssessmentIncomplete(false);
		
		String forward = scsFinishRequestPost(scsInterviewForm, aRequest);
		
		if(forward.equalsIgnoreCase(UIConstants.CONFIRM_SUCCESS))
		{
			scsInterviewForm.setForceFieldContinued(true);
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
			forceFieldForm.setAssessmentDate(scsInterviewForm.getAssessmentDate());
			scsInterviewForm.setConfirmationMsg("SCS Interview successfully updated.");
			aRequest.setAttribute("confirmMessage","SCS Interview successfully updated.");
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
	private String scsFinishRequestPost(SCSInterviewAssessmentForm scsInterviewForm, HttpServletRequest aRequest) throws ReturnException
	{
		System.out.println("AdministerAssessments::SubmitSCSInterviewAssessmentAction::scsFinishRequestPost()::Method Begin");
		
		ArrayList questionAnswerList = new ArrayList();
		
		ArrayList tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenOneQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenTwoQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenThreeQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenFourQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenFiveQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		tempQuesAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(scsInterviewForm.getScsIntrvScreenSixQuestionsList());
		questionAnswerList.addAll(tempQuesAnsList);
		
		scsInterviewForm.setQuestionAnswerList(questionAnswerList);
		
		String forward=UIConstants.CONFIRM_SUCCESS;
		
		UpdateSCSInterviewAssessmentEvent scsIntrvwRequestEvent = (UpdateSCSInterviewAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSInterviewAssessmentEvent(scsInterviewForm);
        CompositeResponse response = this.postRequestEvent(scsIntrvwRequestEvent);   
		MessageUtil.processReturnException(response);
		
        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
        scsInterviewForm.setCreateUpdateSCSInterviewId("");
        
        if(assessRespObj != null)
        {
        	scsInterviewForm.setCreateUpdateSCSInterviewId(assessRespObj.getAssessmentId());
        }
        if ( StringUtils.isNotEmpty( scsInterviewForm.getTaskId() ) ){
			String taskId = scsInterviewForm.getTaskId();			
			UpdateCSTaskEvent updateTaskEvent = (UpdateCSTaskEvent) EventFactory.getInstance(CSTaskControllerServiceNames.UPDATECSTASK);
			updateTaskEvent.setCsTaskId( taskId );
			updateTaskEvent.setStatusId( UIConstants.CLOSED_STATUS_ID );
			updateTaskEvent.setCloseTask(true);
			MessageUtil.postRequest(updateTaskEvent);
		}
        return forward;
	}//end of scsFinishRequestPost()
	
}

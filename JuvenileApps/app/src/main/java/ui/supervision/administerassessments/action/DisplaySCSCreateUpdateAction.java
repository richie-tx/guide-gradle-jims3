/*
 * Created on Feb 21, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.CalculateSCSTotalsEvent;
import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.UpdateSCSAssessmentEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.administerassessments.reply.SCSTotalsResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.SendSCSInventoryTaskEvent;
import messaging.scheduling.RegisterTaskEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.naming.CalendarConstants;
import naming.CSCAssessmentConstants;
import naming.CSTaskControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CSCQuestionGroup;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplaySCSCreateUpdateAction extends JIMSBaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.newSCS", "create");
		keyMap.put("button.next", "next");
		keyMap.put("button.submit", "next");
		keyMap.put("button.update", "update");
		keyMap.put("button.updateLink", "updateAssessmentLink");
		keyMap.put("button.saveContinue","saveNContinue");
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
	public ActionForward create(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::create()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
				true);

		String forward = UIConstants.CREATE_SUCCESS;

		String screenType = scsForm.getScsScreenType();
		if ((screenType != null)
				&& ((screenType
						.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE))
						|| (screenType
								.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO))
						|| (screenType
								.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE)) || (screenType
						.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_FOUR)))) {
			screenType = "";
			return aMapping.findForward(forward);
		}

		scsForm.clearAll();

		scsForm.setAction(UIConstants.CREATE);
		scsForm.setSecondaryAction("");
		scsForm.setDefendantId(assessmentForm.getDefendantId());
		scsForm.setSupervisionBeginDate(assessmentForm
				.getSupervisionBeginDate());
		scsForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
		scsForm
				.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		scsForm
				.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		scsForm.setAssessmentDate(null);
		scsForm.setMasterAssessmentId(null);
		scsForm.setAssessmentId(null);
		scsForm.setAssessmentIncomplete(true);
		
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) AdminAssessmentsHelper
				.getAssessmentDetailsEvent(scsForm);
		CompositeResponse compResponse = this
				.postRequestEvent(assessmentDetailsEvent);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(compResponse, ErrorResponseEvent.class);
		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Inventory Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent) MessageUtil
				.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
		if (assessmentDetailsRespEvt == null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Inventory Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt
				.getQuestionAnswerList();
		// convert the questionGroupResponseEvents to UIQuestionGroup
		Collection uiQuestionGroupsList = UIUtil
				.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		// separate the questions into different QuestionGroups and assign then
		// to SCSForm
		if ((uiQuestionGroupsList != null)
				&& (uiQuestionGroupsList.size() == 4)) {
			Iterator questionGrpIterator = uiQuestionGroupsList.iterator();
			while (questionGrpIterator.hasNext()) {
				CSCQuestionGroup questionGrp = (CSCQuestionGroup) questionGrpIterator
						.next();
				ArrayList questionGrpList = new ArrayList();
				questionGrpList.add(questionGrp);

				if (questionGrp
						.getGroupId()
						.equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE_QUEST_GRP_ID)) {
					scsForm.setScsScreenOneQuestionsList(questionGrpList);
				} else if (questionGrp
						.getGroupId()
						.equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO_QUEST_GRP_ID)) {
					scsForm.setScsScreenTwoQuestionsList(questionGrpList);
				} else if (questionGrp
						.getGroupId()
						.equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE_QUEST_GRP_ID)) {
					scsForm.setScsScreenThreeQuestionsList(questionGrpList);
				} else {
					scsForm.setScsScreenFourQuestionsList(questionGrpList);
				}
			}
		}
		return aMapping.findForward(forward);
	}// end of method create()

	
	
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
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::saveNContinue()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
				true);
		
		String forward = null;
		boolean isNewSCS = false;
		if( !StringUtils.isNotEmpty(scsForm.getCreateUpdateSCSId()) && !StringUtils.isNotEmpty(scsForm.getAssessmentId()) ) {
			isNewSCS = true;
		}
		
		if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE)) {
			// validate the assessment date not greater than the present date
			Date presentDate = new Date();
			int result = DateUtil.compare(scsForm.getAssessmentDate(),
					presentDate, DateUtil.DATE_FMT_1);
			if (result > 0) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should not be future dated");
				return aMapping.findForward("screenOneFailure");
			}

			// validate the assessment date if entered within the Supervision
			// Period
			boolean isAssessmentDateInRange = AdminAssessmentsHelper
					.isAssessmentDateInSupervisionRange(scsForm
							.getAssessmentDate(), scsForm);

			if (!isAssessmentDateInRange) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should be within the Supervision Period");
				return aMapping.findForward("screenOneFailure");
			}

			// validate for Assessment Date should not be lesser than that of
			// Initial Assessment Date
			ArrayList initialAssessmentsList = (ArrayList) assessmentForm
					.getInitialAssessmentsList();
			if ((initialAssessmentsList != null)
					&& (initialAssessmentsList.size() == 1)) {
				AssessmentLightBean assessObj = (AssessmentLightBean) initialAssessmentsList
						.get(0);
				Date initialAssessmentDate = assessObj.getAssessmentDate();

				result = DateUtil.compare(scsForm.getAssessmentDate(),
						initialAssessmentDate, DateUtil.DATE_FMT_1);
				// Reassessment Date cannot be lesser than Initial Assessment
				// Date
				if (result < 0) {
					sendToErrorPage(aRequest,
							JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
							"Assessment Date cannot be before the Initial Assessment Date");
					return aMapping.findForward("screenOneFailure");
				}
			}
			forward = "screenOneNext";
		} 
		else if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO))
		{
			forward = "screenTwoNext";
		} 
		else if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE))
		{
			forward = "screenThreeNext";
		} 
		else 
		{
			forward = screenFourNext(scsForm, aRequest);
		}
		
		if (!scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_FOUR))
		{
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
			
			CalculateSCSTotalsEvent scsTotalRequestEvt = (CalculateSCSTotalsEvent) AdminAssessmentsHelper.getCalculateSCSTotalsEvent(scsForm);
			SCSTotalsResponseEvent scsTotalsResponseObj = (SCSTotalsResponseEvent)this.postRequestEvent(scsTotalRequestEvt, SCSTotalsResponseEvent.class);
			if(scsTotalsResponseObj!= null)
			{
				String failureReason = "";
				try
				{
					failureReason = AdminAssessmentsHelper.setSCSTotalsResponseEvent(scsForm, scsTotalsResponseObj);
				}
				catch(Exception ex)
				{
					if(failureReason!=null)
					{
						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,	failureReason);
						
						if (scsForm.getScsScreenType().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE))
						{
							return aMapping.findForward("screenOneFailure");
						}
						else if (scsForm.getScsScreenType().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO))
						{
							return aMapping.findForward("screenTwoFailure");
						} 
						else if (scsForm.getScsScreenType().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE))
						{
							return aMapping.findForward("screenThreeFailure");
						} 
						else if (scsForm.getScsScreenType().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_FOUR))
						{
							return aMapping.findForward("screenFourFailure");
						} 

					}
				}
			}

			scsForm.setAssessmentIncomplete(true);
			
			UpdateSCSAssessmentEvent scsRequestEvent= null;
			try
			{
				scsRequestEvent = (UpdateSCSAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSAssessmentEvent(scsForm);
			}
			catch(Exception ex)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Invalid SCS scores");
				
				if (scsForm.getScsScreenType().equalsIgnoreCase(
						CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE))
				{
					return aMapping.findForward("screenOneFailure");
				}
				else if (scsForm.getScsScreenType().equalsIgnoreCase(
						CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO))
				{
					return aMapping.findForward("screenTwoFailure");
				} 
				else if (scsForm.getScsScreenType().equalsIgnoreCase(
						CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE))
				{
					return aMapping.findForward("screenThreeFailure");
				} 
				else if (scsForm.getScsScreenType().equalsIgnoreCase(
						CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_FOUR))
				{
					return aMapping.findForward("screenFourFailure");
				}
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
	        	if(isNewSCS) {
	        		SendSCSInventoryTaskEvent SCSInventoryTaskEvent = (SendSCSInventoryTaskEvent) EventFactory
					.getInstance(CSTaskControllerServiceNames.SENDSCSINVENTORYTASK);
					Date date = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					//cal.add(Calendar.HOUR, 168);
					Date dueDate = cal.getTime();
					SCSInventoryTaskEvent.setDueDate(dueDate);
					
					// Get tasks for userId and Position
			        String userPosition = "";
					GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
					gEvent.setLogonId(SecurityUIHelper.getLogonId());
					CompositeResponse resp = MessageUtil.postRequest(gEvent);
					
					LightCSCDStaffResponseEvent staffResponse = (LightCSCDStaffResponseEvent) MessageUtil.filterComposite(resp, LightCSCDStaffResponseEvent.class);
			
			        if (staffResponse != null)
			        {
			            userPosition = staffResponse.getStaffPositionId();
			        }
			        
			        SCSInventoryTaskEvent.setAssessmentId(assessRespObj.getMasterAssessmentId());
			        SCSInventoryTaskEvent.setDefendantId(scsForm.getDefendantId());			
			        SCSInventoryTaskEvent.setStaffPositionId( userPosition );
			        SCSInventoryTaskEvent.setTaskSubject("SCS Inventory is in an incomplete status");
			        SCSInventoryTaskEvent.setTaskTopic("INCOMPLETE SCS INVENTORY NOTIFICATION");
			        try {
						SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
						SCSInventoryTaskEvent.setSuperviseeName( myHeaderForm.getSuperviseeNameDesc() );
					} catch (GeneralFeedbackMessageException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Create the task name
					StringBuffer taskName = new StringBuffer();
					taskName.append( SCSInventoryTaskEvent.getClass().getName() );
					taskName.append( "-" );
					taskName.append( Math.random() );
					//Registering the task with the scheduler
					RegisterTaskEvent rtEvent = new RegisterTaskEvent();
					rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
					rtEvent.setFirstNotificationDate(dueDate);
					rtEvent.setNextNotificationDate(dueDate); 
					rtEvent.setTaskName(taskName.toString());
					rtEvent.setNotificationEvent(SCSInventoryTaskEvent);
					EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
	        	}
	        }
		}
		return aMapping.findForward(forward);
	}// end of saveNContinue()
	
	
	
	private UpdateSCSAssessmentEvent getUpdateEvent(SCSAssessmentForm scsForm, HttpServletRequest aRequest) {
		UpdateSCSAssessmentEvent scsRequestEvent= null;

		try
		{
			//An exception generated here will identify any problems with the answers.
			scsRequestEvent = (UpdateSCSAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSAssessmentEvent(scsForm);
		}
		catch(Exception ex)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Invalid SCS scores");
			scsRequestEvent = null;
		}
		return scsRequestEvent;
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
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::next()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
				true);

		if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_ONE)) {
			// validate the assessment date not greater than the present date
			Date presentDate = new Date();
			int result = DateUtil.compare(scsForm.getAssessmentDate(),
					presentDate, DateUtil.DATE_FMT_1);
			if (result > 0) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should not be future dated");
				return aMapping.findForward("screenOneFailure");
			}

			// validate the assessment date if entered within the Supervision
			// Period
			boolean isAssessmentDateInRange = AdminAssessmentsHelper
					.isAssessmentDateInSupervisionRange(scsForm
							.getAssessmentDate(), scsForm);

			if (!isAssessmentDateInRange) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should be within the Supervision Period");
				return aMapping.findForward("screenOneFailure");
			}

			// validate for Assessment Date should not be lesser than that of
			// Initial Assessment Date
			ArrayList initialAssessmentsList = (ArrayList) assessmentForm
					.getInitialAssessmentsList();
			if ((initialAssessmentsList != null)
					&& (initialAssessmentsList.size() == 1)) {
				AssessmentLightBean assessObj = (AssessmentLightBean) initialAssessmentsList
						.get(0);
				Date initialAssessmentDate = assessObj.getAssessmentDate();

				result = DateUtil.compare(scsForm.getAssessmentDate(),
						initialAssessmentDate, DateUtil.DATE_FMT_1);
				// Reassessment Date cannot be lesser than Initial Assessment
				// Date
				if (result < 0) {
					sendToErrorPage(aRequest,
							JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
							"Assessment Date cannot be before the Initial Assessment Date");
					return aMapping.findForward("screenOneFailure");
				}
			}

			return aMapping.findForward("screenOneNext");
		} else if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_TWO)) {
			return aMapping.findForward("screenTwoNext");
		} else if (scsForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_SCREEN_THREE)) {
			return aMapping.findForward("screenThreeNext");
		} else {
			return aMapping.findForward(screenFourNext(scsForm, aRequest));
		}
	}// end of next()
	
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward updateAssessmentLink(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::updateAssessmentLink()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
				true);

		String selectedAssessBeanId = scsForm.getSelectedAssessmentBeanId();
		String taskId = "";
		if (StringUtils.isNotEmpty(scsForm.getTaskId())){
			taskId = scsForm.getTaskId();
		}
		AssessmentLightBean selectedAssessBeanObj = new AssessmentLightBean();
		AssessmentLightBean ffAssessmentBeanObj = null;
	    
		scsForm.clearAll();

		scsForm.setAction(UIConstants.UPDATE);
		scsForm.setSecondaryAction("");
		scsForm.setDefendantId(assessmentForm.getDefendantId());
		scsForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
		scsForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
		scsForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		scsForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		scsForm.setTaskId(taskId);

		selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(
				assessmentForm, selectedAssessBeanId, CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
		ArrayList scsAssessmentList = (ArrayList)assessmentForm.getScsAssessmentsList();
	    if((scsAssessmentList != null) && (scsAssessmentList.size() > 0))
		{
			Iterator iterator = scsAssessmentList.iterator();
			while(iterator.hasNext())
			{
				AssessmentLightBean assessmentLightBean = (AssessmentLightBean) iterator.next();
				if(assessmentLightBean.getAssessmentTypeId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENTTYPE_FORCEFIELD))
				{
					ffAssessmentBeanObj = assessmentLightBean;
					break;
				}
			}
		}
		
		scsForm.setMasterAssessmentId(selectedAssessBeanObj.getMasterAssessmentId());
		scsForm.setAssessmentId(selectedAssessBeanObj.getAssessmentId());
		
		if(!selectedAssessBeanObj.isStatusComplete())
		{
			scsForm.setAssessmentIncomplete(true);
		}
		
		scsForm.setForceFieldAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
		scsForm.setForceFieldAssessmentStatus(selectedAssessBeanObj.getForceFieldAssessmentStatus());
		if(ffAssessmentBeanObj != null)
		{
			if(!ffAssessmentBeanObj.isForceFieldStatusComplete())
	    	{
	    		scsForm.setForceFieldAssessmentIncomplete(true);
	    	}
			
//	    	set force field variables
	    	if(scsForm.getForceFieldAssessmentStatus().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_EXIST))
	    	{
	    		scsForm.setForceFieldMasterAssessId(ffAssessmentBeanObj.getMasterAssessmentId());
	    		scsForm.setForceFieldAssessmentId(ffAssessmentBeanObj.getAssessmentId());
	    	}
	    	else
	    	{
	    		scsForm.setForceFieldMasterAssessId(null);
	    		scsForm.setForceFieldAssessmentId(null);
	    	}
		}
		else
		{
			scsForm.setForceFieldMasterAssessId(null);
			scsForm.setForceFieldAssessmentId(null);
		}
		
		scsForm.setAssessmentDate(selectedAssessBeanObj.getAssessmentDate());

		String forward = UIConstants.CREATE_SUCCESS;

		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) AdminAssessmentsHelper
				.getAssessmentDetailsEvent(scsForm);
		CompositeResponse compResponse = this
				.postRequestEvent(assessmentDetailsEvent);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(compResponse, ErrorResponseEvent.class);
		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent) MessageUtil
				.filterComposite(compResponse,
						AssessmentDetailsResponseEvent.class);
		if (assessmentDetailsRespEvt == null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCS(
				scsForm, assessmentDetailsRespEvt);

		return aMapping.findForward(forward);
	}// end of updateAssessmentLink()

	/**
	 * Assessment updated from the Assessment Details Page
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 *
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::update()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;

		scsForm.setAction(UIConstants.UPDATE);
		scsForm.setSecondaryAction("");

		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}// end of update()

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 *
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::cancel()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm) aForm;
		scsForm.clearAll();
		return aMapping.findForward(UIConstants.CANCEL);
	}// end of cancel()

	/**
	 * 
	 * @param scsForm
	 * @param aRequest
	 * @return
	 */
	private String screenFourNext(SCSAssessmentForm scsForm,
			HttpServletRequest aRequest) 
	{
		//Defect 63213 - DAG added save logic on screen4.
		System.out.println("AdministerAssessments::DisplaySCSCreateUpdateAction::screenFourNext()::Method Begin");
		
		String forward = UIConstants.SUMMARY_SUCCESS;

		// set the summary question list
		ArrayList summaryQuestionsList = new ArrayList();

		CSCQuestionGroup screenOneQuesGrp = (CSCQuestionGroup) ((ArrayList) scsForm
				.getScsScreenOneQuestionsList()).get(0);
		CSCQuestionGroup questGrp = new CSCQuestionGroup();
		questGrp.setGroupId(screenOneQuesGrp.getGroupId());
		questGrp.setGroupSequence(screenOneQuesGrp.getGroupSequence());
		questGrp.setGroupText(screenOneQuesGrp.getGroupText());
		ArrayList questionList = new ArrayList();
		questGrp.setQuestions(questionList);

		ArrayList screenOneQuesLst = (ArrayList) screenOneQuesGrp
				.getQuestions();
		questionList.addAll(screenOneQuesLst);

		ArrayList screenTwoQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsForm
				.getScsScreenTwoQuestionsList()).get(0)).getQuestions();
		questionList.addAll(screenTwoQuesLst);

		ArrayList screenThreeQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsForm
				.getScsScreenThreeQuestionsList()).get(0)).getQuestions();
		questionList.addAll(screenThreeQuesLst);

		ArrayList screenFourQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsForm
				.getScsScreenFourQuestionsList()).get(0)).getQuestions();
		questionList.addAll(screenFourQuesLst);

		summaryQuestionsList.add(questGrp);
		scsForm.setScsSummaryQuestionsList(summaryQuestionsList);

		// Convert the questions in the form of Question and selected answer
		ArrayList screenOnequestionAnsList = (ArrayList) UIUtil
				.getQuestionAnswerListFromUICSCQuestionGroups(scsForm
						.getScsScreenOneQuestionsList());
		ArrayList screenTwoquestionAnsList = (ArrayList) UIUtil
				.getQuestionAnswerListFromUICSCQuestionGroups(scsForm
						.getScsScreenTwoQuestionsList());
		ArrayList screenThreequestionAnsList = (ArrayList) UIUtil
				.getQuestionAnswerListFromUICSCQuestionGroups(scsForm
						.getScsScreenThreeQuestionsList());
		ArrayList screenFourquestionAnsList = (ArrayList) UIUtil
				.getQuestionAnswerListFromUICSCQuestionGroups(scsForm
						.getScsScreenFourQuestionsList());

		ArrayList questionAnswersList = new ArrayList();
		questionAnswersList.addAll(screenOnequestionAnsList);
		questionAnswersList.addAll(screenTwoquestionAnsList);
		questionAnswersList.addAll(screenThreequestionAnsList);
		questionAnswersList.addAll(screenFourquestionAnsList);

		scsForm.setQuestionAnswerList(questionAnswersList);

		CalculateSCSTotalsEvent scsTotalRequestEvt = (CalculateSCSTotalsEvent) AdminAssessmentsHelper
				.getCalculateSCSTotalsEvent(scsForm);

		CompositeResponse response = this.postRequestEvent(scsTotalRequestEvt);
		MessageUtil.processReturnException(response);

		SCSTotalsResponseEvent scsTotalsResponseObj = (SCSTotalsResponseEvent) MessageUtil
				.filterComposite(response, SCSTotalsResponseEvent.class);
		if (scsTotalsResponseObj == null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to calculate SCS scores");
			return "screenFourFailure";
		}
		
		String failureReason = "";
		try
		{
			failureReason = AdminAssessmentsHelper.setSCSTotalsResponseEvent(scsForm,
				scsTotalsResponseObj);
		}
		catch(Exception ex)
		{
			if(failureReason!=null)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,	failureReason);
				return "screenFourFailure";
			}
		}
		UpdateSCSAssessmentEvent scsRequestEvent= this.getUpdateEvent(scsForm, aRequest);
		if (scsRequestEvent == null){
			return "screenFourFailure";
		}
		
		// check if there is a tie in the highest total
		boolean isTotalTie = false;

		HashMap classificationTypeKeyMap = (HashMap) scsForm.getClassificationTypeTotalsMap();

		classificationTypeKeyMap.remove(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI);

		Set keySet = classificationTypeKeyMap.keySet();
		Iterator it = keySet.iterator();
		HashMap totalKeyMap = new HashMap(3);
		ArrayList totalList = new ArrayList(3);

		while (it.hasNext()) {
			Object classificationType = it.next();
			Integer total = (Integer) classificationTypeKeyMap.get(classificationType);		
			totalList.add(total);
			totalKeyMap.put(total, classificationType);

		}
		Collections.sort(totalList);

		int highestTotal = Integer.parseInt(((Integer) totalList.get(2)).toString());
		for (int i = 0; i < 2; i++) {
			// if((Integer.parseInt(((Integer)totalList.get(i)).toString()))== highestTotal)
			if (highestTotal - Integer.parseInt(((Integer) totalList.get(i)).toString()) <= 3) {
				isTotalTie = true;
				break;
			}
		}

		if (isTotalTie) {
			Iterator i = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION).iterator();
			Collection codesToDisplay = new FastArrayList();

			if (Integer.parseInt(scsForm.getTotalSI()) - highestTotal >= 4) {
				while (i.hasNext()) {
					CodeResponseEvent ResponseEvent = (CodeResponseEvent) i.next();
					if (ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI)|| ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SS)) {
						codesToDisplay.add(ResponseEvent);
					}
				}
				scsForm.setSecondaryAction(UIConstants.NO_CLASSIFICATION_TIE);
				scsForm.setAssessmentClassificationCodes(codesToDisplay);
				scsForm.setHighestSI(UIConstants.YES);
				scsRequestEvent= this.getUpdateEvent(scsForm, aRequest);
				if (scsRequestEvent != null){
			       response = this.postRequestEvent(scsRequestEvent);   
			       MessageUtil.processReturnException(response);
			       this.processResponse(response, scsForm);
				}
				return forward;
			}
			
			String code = "";		
			if(Integer.parseInt(((Integer) totalList.get(2)).toString())- Integer.parseInt(((Integer) totalList.get(0)).toString()) > 3){
				code = (String) totalKeyMap.get(totalList.get(0));		
			}
			

			while (i.hasNext()) {
				CodeResponseEvent ResponseEvent = (CodeResponseEvent) i.next();
				if (!ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI) && !ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SS) && !ResponseEvent.getCode().equalsIgnoreCase(code) ) {
					codesToDisplay.add(ResponseEvent);						
				}
			}
			scsForm.setSecondaryAction(UIConstants.CLASSIFICATION_TIE);
			scsForm.setAssessmentClassificationCodes(codesToDisplay);
			forward = UIConstants.CLASSIFICATION_TIE_SUCCESS;
		}

		else {

			Iterator i = SimpleCodeTableHelper.getCodesSortedByDesc(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION).iterator();
			Collection codesToDisplay = new FastArrayList();

			if (Integer.parseInt(scsForm.getTotalSI()) - highestTotal >= 4) {
				while (i.hasNext()) {
	                CodeResponseEvent ResponseEvent = (CodeResponseEvent) i.next();
					if (ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SI) || ResponseEvent.getCode().equals(PDCodeTableConstants.CSC_ASSESSMENTS_SCS_CLASSIFICATION_ID_SS)) {
						codesToDisplay.add(ResponseEvent);
					}
				}
				scsForm.setSecondaryAction(UIConstants.NO_CLASSIFICATION_TIE);
				scsForm.setAssessmentClassificationCodes(codesToDisplay);
				scsForm.setHighestSI(UIConstants.YES);
				scsForm.setSecondaryClassificationTypeId("");
				scsForm.setSecondaryClassificationTypeDesc("");
				scsRequestEvent= this.getUpdateEvent(scsForm, aRequest);
				if (scsRequestEvent != null){
			       response = this.postRequestEvent(scsRequestEvent);   
			       MessageUtil.processReturnException(response);
			       this.processResponse(response, scsForm);
				}
				return forward;
			}

			scsForm.setPrimaryClassificationTypeId((String) totalKeyMap.get(totalList.get(2)));
			String classification = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CSC_ASSESSMENTS_CODETABLE_ASSESSMENT_CLASSIFICATION,scsForm.getPrimaryClassificationTypeId());
			scsForm.setPrimaryClassificationTypeDesc(classification);
			scsForm.setHighestSI(UIConstants.NO);
			scsForm.setSecondaryAction(UIConstants.NO_CLASSIFICATION_TIE);
			scsForm.setSecondaryClassificationTypeId("");
			scsForm.setSecondaryClassificationTypeDesc("");
			scsRequestEvent= this.getUpdateEvent(scsForm, aRequest);
			if (scsRequestEvent != null){
		       response = this.postRequestEvent(scsRequestEvent);   
		       MessageUtil.processReturnException(response);
		       this.processResponse(response, scsForm);
			}
			return forward;
		}
		scsRequestEvent= this.getUpdateEvent(scsForm, aRequest);
		if (scsRequestEvent != null){
	       response = this.postRequestEvent(scsRequestEvent);   
	       MessageUtil.processReturnException(response);
	       this.processResponse(response, scsForm);
		}

		return forward;
	}// end of screenFourNext()

	private void processResponse(CompositeResponse response, SCSAssessmentForm scsForm) {
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

}// end of Class

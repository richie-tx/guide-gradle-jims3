/*
 * Created on Jun 23, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.UpdateSCSInterviewAssessmentEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.SendSCSInterviewTaskEvent;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CSCQuestionGroup;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 */
public class DisplaySCSInterviewCreateUpdateAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	@Override
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.newSCSInterview", "create");
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
			throws GeneralFeedbackMessageException 
	{
		
		System.out.println("AdministerAssessments::DisplaySCSInterviewCreateUpdateAction::create()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,true);

		String forward = UIConstants.CREATE_SUCCESS;

		String screenType = scsInterviewForm.getScsScreenType();
		if ((screenType != null)
				&& ((screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_ONE))
						|| (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_TWO))
						|| (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_THREE)) 
						|| (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FOUR))
						|| (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FIVE))
						|| (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_SIX)))) 
		{
			screenType = "";
			return aMapping.findForward(forward);
		}

		scsInterviewForm.clearAll();

		scsInterviewForm.setAction(UIConstants.CREATE);
		scsInterviewForm.setSecondaryAction("");
		scsInterviewForm.setDefendantId(assessmentForm.getDefendantId());
		scsInterviewForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
		scsInterviewForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
		scsInterviewForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		scsInterviewForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW);
		scsInterviewForm.setAssessmentDate(null);
		scsInterviewForm.setMasterAssessmentId(null);
		scsInterviewForm.setAssessmentId(null);
		scsInterviewForm.setAssessmentIncomplete(true);
		
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) AdminAssessmentsHelper
				.getAssessmentDetailsEvent(scsInterviewForm);
		CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compResponse, ErrorResponseEvent.class);
		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Interview Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent) MessageUtil
				.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
		if (assessmentDetailsRespEvt == null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Interview Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt
				.getQuestionAnswerList();
		// convert the questionGroupResponseEvents to UIQuestionGroup
		Collection uiQuestionGroupsList = UIUtil
				.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		// separate the questions into different QuestionGroups and assign then
		// to scsInterviewForm
		if ((uiQuestionGroupsList != null)
				&& (uiQuestionGroupsList.size() == 6)) {
			Iterator questionGrpIterator = uiQuestionGroupsList.iterator();
			while (questionGrpIterator.hasNext()) {
				CSCQuestionGroup questionGrp = (CSCQuestionGroup) questionGrpIterator
						.next();
				ArrayList questionGrpList = new ArrayList();
				questionGrpList.add(questionGrp);

				if (questionGrp.getGroupId().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_ONE_QUEST_GRP_ID)) {
					scsInterviewForm.setScsIntrvScreenOneQuestionsList(questionGrpList);
				} else if (questionGrp.getGroupId().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_TWO_QUEST_GRP_ID)) {
					scsInterviewForm.setScsIntrvScreenTwoQuestionsList(questionGrpList);
				} else if (questionGrp.getGroupId().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_THREE_QUEST_GRP_ID)) {
					scsInterviewForm.setScsIntrvScreenThreeQuestionsList(questionGrpList);
				} else if (questionGrp.getGroupId().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_FOUR_QUEST_GRP_ID)) {
					scsInterviewForm.setScsIntrvScreenFourQuestionsList(questionGrpList);
				} else if (questionGrp.getGroupId().equalsIgnoreCase(
								CSCAssessmentConstants.ASSESSMENT_SCS_INTRW_SCREEN_FIVE_QUEST_GRP_ID)) {
					scsInterviewForm.setScsIntrvScreenFiveQuestionsList(questionGrpList);
				} else {
					scsInterviewForm.setScsIntrvScreenSixQuestionsList(questionGrpList);
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewCreateUpdateAction::saveNContinue()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		boolean isNewInterview = false;
		if(!StringUtils.isNotEmpty(scsInterviewForm.getAssessmentId()) && !StringUtils.isNotEmpty(scsInterviewForm.getCreateUpdateSCSInterviewId())) {
			isNewInterview = true;
		}
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
				true);
		
		String forward = null;
		
		if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_ONE)) {
			// validate the assessment date not greater than the present date
			Date presentDate = new Date();
			int result = DateUtil.compare(scsInterviewForm.getAssessmentDate(),
					presentDate, DateUtil.DATE_FMT_1);
			if (result > 0) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should not be future dated");
				return aMapping.findForward("screenOneFailure");
			}

			// validate the assessment date if entered within the Supervision
			// Period
			boolean isAssessmentDateInRange = AdminAssessmentsHelper
					.isAssessmentDateInSupervisionRange(scsInterviewForm
							.getAssessmentDate(), scsInterviewForm);

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

				result = DateUtil.compare(scsInterviewForm.getAssessmentDate(),
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
		else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_TWO))
		{
			forward = "screenTwoNext";
		} 
		else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_THREE))
		{
			forward = "screenThreeNext";
		} 
		else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FOUR))
		{
			forward = "screenFourNext";
		} 
		else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FIVE))
		{
			forward = "screenFiveNext";
		} 
		else 
		{
			// set the summary question list
			ArrayList summaryQuestionsList = new ArrayList();

			CSCQuestionGroup screenOneQuesGrp = (CSCQuestionGroup) ((ArrayList) scsInterviewForm.getScsIntrvScreenOneQuestionsList()).get(0);
			CSCQuestionGroup questGrp = new CSCQuestionGroup();
			questGrp.setGroupId(screenOneQuesGrp.getGroupId());
			questGrp.setGroupSequence(screenOneQuesGrp.getGroupSequence());
			questGrp.setGroupText(screenOneQuesGrp.getGroupText());
			ArrayList questionList = new ArrayList();
			questGrp.setQuestions(questionList);

			ArrayList screenOneQuesLst = (ArrayList) screenOneQuesGrp.getQuestions();
			questionList.addAll(screenOneQuesLst);

			ArrayList screenTwoQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsInterviewForm.getScsIntrvScreenTwoQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenTwoQuesLst);

			ArrayList screenThreeQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList)scsInterviewForm.getScsIntrvScreenThreeQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenThreeQuesLst);

			ArrayList screenFourQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsInterviewForm.getScsIntrvScreenFourQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenFourQuesLst);
			
			ArrayList screenFiveQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsInterviewForm.getScsIntrvScreenFiveQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenFiveQuesLst);
			
			ArrayList screenSixQuesLst = (ArrayList) ((CSCQuestionGroup) ((ArrayList) scsInterviewForm.getScsIntrvScreenSixQuestionsList()).get(0)).getQuestions();
			questionList.addAll(screenSixQuesLst);

			summaryQuestionsList.add(questGrp);
			scsInterviewForm.setScsSummaryQuestionsList(summaryQuestionsList);
			
			forward = UIConstants.SUMMARY_SUCCESS;
		}
		
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
		
		UpdateSCSInterviewAssessmentEvent scsInterviewRequestEvent= null;
		scsInterviewRequestEvent = (UpdateSCSInterviewAssessmentEvent)AdminAssessmentsHelper.getUpdateSCSInterviewAssessmentEvent(scsInterviewForm);
		
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
        	
        	if (isNewInterview){
        		SendSCSInterviewTaskEvent scsInterviewTaskEvent = (SendSCSInterviewTaskEvent) EventFactory
				.getInstance(CSTaskControllerServiceNames.SENDSCSINTERVIEWTASK);
				Date date = new Date();
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);
				//cal.add(Calendar.HOUR, 168);
				Date dueDate = cal.getTime();
				scsInterviewTaskEvent.setDueDate(dueDate);
				
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
		        
		        scsInterviewTaskEvent.setStaffPositionId( userPosition );
		        scsInterviewTaskEvent.setAssessmentId(assessRespObj.getAssessmentId());			
				
		        scsInterviewTaskEvent.setTaskTopic("INCOMPLETE SCS INTERVIEW NOTIFICATION");
		        scsInterviewTaskEvent.setTaskSubject("SCS Interview is in an incomplete status");
		        scsInterviewTaskEvent.setDefendantId(scsInterviewForm.getDefendantId());
		        try {
					SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
					scsInterviewTaskEvent.setSuperviseeName( myHeaderForm.getSuperviseeNameDesc() );
				} catch (GeneralFeedbackMessageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Create the task name
				StringBuffer taskName = new StringBuffer();
				taskName.append( scsInterviewTaskEvent.getClass().getName() );
				taskName.append( "-" );
				taskName.append( Math.random() );
				//Registering the task with the scheduler
				RegisterTaskEvent rtEvent = new RegisterTaskEvent();
				rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
				rtEvent.setFirstNotificationDate(dueDate);
				rtEvent.setNextNotificationDate(dueDate); 
				rtEvent.setTaskName(taskName.toString());
				rtEvent.setNotificationEvent(scsInterviewTaskEvent);
				EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
        	}
        }

        return aMapping.findForward(forward);
	}// end of saveNContinue()
	
	
	
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewCreateUpdateAction::next()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);

		if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_ONE)) {
			// validate the assessment date not greater than the present date
			Date presentDate = new Date();
			int result = DateUtil.compare(scsInterviewForm.getAssessmentDate(),
					presentDate, DateUtil.DATE_FMT_1);
			if (result > 0) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Assessment Date should not be future dated");
				return aMapping.findForward("screenOneFailure");
			}

			// validate the assessment date if entered within the Supervision
			// Period
			boolean isAssessmentDateInRange = AdminAssessmentsHelper
					.isAssessmentDateInSupervisionRange(scsInterviewForm
							.getAssessmentDate(), scsInterviewForm);

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

				result = DateUtil.compare(scsInterviewForm.getAssessmentDate(),
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
		} 
		else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_TWO)) {
			return aMapping.findForward("screenTwoNext");
		} else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_THREE)) {
			return aMapping.findForward("screenThreeNext");
		} else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FOUR)) {
			return aMapping.findForward("screenFourNext");
		} else if (scsInterviewForm.getScsScreenType().equalsIgnoreCase(
				CSCAssessmentConstants.ASSESSMENT_SCS_INTERVIEW_SCREEN_FIVE)) {
			return aMapping.findForward("screenFiveNext");
		} else {
			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
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
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplaySCSInterviewCreateUpdateAction::updateAssessmentLink()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
				aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);

		String selectedAssessBeanId = scsInterviewForm.getSelectedAssessmentBeanId();
		String taskId = scsInterviewForm.getTaskId();
		AssessmentLightBean selectedAssessBeanObj = new AssessmentLightBean();
		AssessmentLightBean ffAssessmentBeanObj = null;
		
		scsInterviewForm.clearAll();

		scsInterviewForm.setTaskId(taskId);
		scsInterviewForm.setAction(UIConstants.UPDATE);
		scsInterviewForm.setSecondaryAction("");
		scsInterviewForm.setDefendantId(assessmentForm.getDefendantId());
		scsInterviewForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
		scsInterviewForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
		scsInterviewForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		scsInterviewForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW);

		selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(
				assessmentForm, selectedAssessBeanId,
				CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
		
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
		
		scsInterviewForm.setMasterAssessmentId(selectedAssessBeanObj
				.getMasterAssessmentId());
		scsInterviewForm.setAssessmentId(selectedAssessBeanObj.getAssessmentId());
		
		if(!selectedAssessBeanObj.isStatusComplete())
		{
			scsInterviewForm.setAssessmentIncomplete(true);
		}
		
		scsInterviewForm.setForceFieldAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
		scsInterviewForm.setForceFieldAssessmentStatus(selectedAssessBeanObj.getForceFieldAssessmentStatus());
		if(ffAssessmentBeanObj != null)
		{
			if(!ffAssessmentBeanObj.isForceFieldStatusComplete())
	    	{
				scsInterviewForm.setForceFieldAssessmentIncomplete(true);
	    	}
			
//			set the force field variables
			if(scsInterviewForm.getForceFieldAssessmentStatus().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_EXIST))
	    	{
				scsInterviewForm.setForceFieldMasterAssessId(ffAssessmentBeanObj.getMasterAssessmentId());
				scsInterviewForm.setForceFieldAssessmentId(ffAssessmentBeanObj.getAssessmentId());
	    	}
	    	else
	    	{
	    		scsInterviewForm.setForceFieldMasterAssessId(null);
				scsInterviewForm.setForceFieldAssessmentId(null);
	    	}
		}
		else
		{
			scsInterviewForm.setForceFieldMasterAssessId(null);
			scsInterviewForm.setForceFieldAssessmentId(null);
		}
		
		scsInterviewForm.setAssessmentDate(selectedAssessBeanObj.getAssessmentDate());

		String forward = UIConstants.CREATE_SUCCESS;

		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) AdminAssessmentsHelper
				.getAssessmentDetailsEvent(scsInterviewForm);
		CompositeResponse compResponse = this
				.postRequestEvent(assessmentDetailsEvent);
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(compResponse, ErrorResponseEvent.class);
		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Interview Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}

		// get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent) MessageUtil
				.filterComposite(compResponse,
						AssessmentDetailsResponseEvent.class);
		if (assessmentDetailsRespEvt == null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
					"Failed to retrieve SCS Interview Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCSInterview(
				scsInterviewForm, assessmentDetailsRespEvt);

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
		System.out.println("AdministerAssessments::DisplaySCSInterviewCreateUpdateAction::update()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm) aForm;

		scsInterviewForm.setAction(UIConstants.UPDATE);
		scsInterviewForm.setSecondaryAction("");

		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}// end of update()
	
	
	
}

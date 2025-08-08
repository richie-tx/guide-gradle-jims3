/*
 * Created on Feb 18, 2008
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.UpdateForceFieldAssessmentEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.SendForceFieldTaskEvent;
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
import ui.common.CSCQuestion;
import ui.common.CSCQuestionGroup;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayForceFieldCreateUpdateAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.create","create");	
		keyMap.put("button.submit","submit");
		keyMap.put("button.update","update");
		keyMap.put("button.updateLink","updateAssessmentLink");
		keyMap.put("button.updateAfterSCS","updateAfterSCS");
		keyMap.put("button.updateAfterSCSInterview","updateAfterSCSInterview");
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
	public ActionForward saveNContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::saveNContinue()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
		boolean isNewForceField = false;
		if(!StringUtils.isNotEmpty(forceFieldForm.getAssessmentId())) {
			isNewForceField = true;
		}
		List rankList = new ArrayList();
		Collection questionGroupList = forceFieldForm.getForceFieldQuestionsList();
		if(questionGroupList!=null && questionGroupList.size()>0)
		{
			Iterator qGroupIter = questionGroupList.iterator();
			while(qGroupIter.hasNext())
			{
				CSCQuestionGroup qGroup = (CSCQuestionGroup)qGroupIter.next();
				Collection quesList = qGroup.getQuestions();
				if(quesList!=null && quesList.size()>0)
				{
					Iterator quesIter = quesList.iterator();
					while(quesIter.hasNext())
					{
						CSCQuestion question = (CSCQuestion)quesIter.next();
						if((question.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXT)) &&
								(question.getResponseDataType().equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_NUMERIC)))
						{
							String rankString = question.getResponseText();
							if(rankString!=null && !(rankString.trim().equalsIgnoreCase("")))
							{
								Integer rank = new Integer(rankString);
								if(!rankList.contains(rank))
								{
									rankList.add(rank);
								}
								else
								{
									sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Duplicate ranking is not allowed. Each problem must have a unique rank");
									return aMapping.findForward(UIConstants.CREATE_FAILURE);
								}
							}
						}
					}
				}
			}
			
			Collections.sort(rankList);
			
			if((!rankList.contains(1)) || (!rankList.contains(2)) || (!rankList.contains(3)))
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "There must be at least 3 problems that are ranked in order from 1-3");
				return aMapping.findForward(UIConstants.CREATE_FAILURE);
			}
			
			int highestRank = (Integer)rankList.get(rankList.size()-1);
			if(highestRank!=rankList.size())
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "For the problems that are ranked they must be sequential without skipping numbers");
				return aMapping.findForward(UIConstants.CREATE_FAILURE);
			}
		}
		
//		save the Force Field Assessment
		ArrayList questionAnsList = (ArrayList)UIUtil.getQuestionAnswerListFromUICSCQuestionGroups(forceFieldForm.getForceFieldQuestionsList());
		forceFieldForm.setQuestionAnswerList(questionAnsList);
		
		forceFieldForm.setAssessmentIncomplete(true);
		
		UpdateForceFieldAssessmentEvent forceFieldRequestEvent = (UpdateForceFieldAssessmentEvent)AdminAssessmentsHelper.getUpdateForceFieldAssessmentEvent(forceFieldForm);
        CompositeResponse response = this.postRequestEvent(forceFieldRequestEvent);   
		MessageUtil.processReturnException(response);
		 
        AssessmentResponseEvent assessRespObj = (AssessmentResponseEvent)MessageUtil.filterComposite(response, AssessmentResponseEvent.class);
        forceFieldForm.setAssessmentId(null);
        forceFieldForm.setMasterAssessmentId(null);
        
        if(assessRespObj != null)
        {
        	forceFieldForm.setAssessmentId(assessRespObj.getAssessmentId());
        	forceFieldForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
        	if (isNewForceField){
        		SendForceFieldTaskEvent forceFieldTaskEvent = (SendForceFieldTaskEvent) EventFactory
    			.getInstance(CSTaskControllerServiceNames.SENDFORCEFIELDTASK);
    			Date date = new Date();
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(date);
    			//cal.add(Calendar.HOUR, 1);
    			Date dueDate = cal.getTime();
    			forceFieldTaskEvent.setDueDate(dueDate);
    			
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
    	        
    	        forceFieldTaskEvent.setAssessmentId(assessRespObj.getMasterAssessmentId());
    	        forceFieldTaskEvent.setDefendantId(forceFieldForm.getDefendantId());			
    	        forceFieldTaskEvent.setStaffPositionId( userPosition );
    	        forceFieldTaskEvent.setTaskSubject("Force Field Assesment is in an incomplete status");
    	        forceFieldTaskEvent.setTaskTopic("COMPLETE FORCE FIELD ASSESSMENT NOTIFICATION");
    	        try {
    				SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
    				forceFieldTaskEvent.setSuperviseeName( myHeaderForm.getSuperviseeNameDesc() );
    			} catch (GeneralFeedbackMessageException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			//Create the task name
    			StringBuffer taskName = new StringBuffer();
    			taskName.append( forceFieldTaskEvent.getClass().getName() );
    			taskName.append( "-" );
    			taskName.append( Math.random() );
    			//Registering the task with the scheduler
    			RegisterTaskEvent rtEvent = new RegisterTaskEvent();
    			rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
    			rtEvent.setFirstNotificationDate(dueDate);
    			rtEvent.setNextNotificationDate(dueDate); 
    			rtEvent.setTaskName(taskName.toString());
    			rtEvent.setNotificationEvent(forceFieldTaskEvent);
    			EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);
        	}
        }
		
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	
	
	
	
	
	/**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
		 * @throws GeneralFeedbackMessageException
	    * @roseuid 479101BB01AE
	    */
	   public ActionForward create(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	   {
		   System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::create()::Method Begin");
			
	    ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	    SCSAssessmentForm scsForm = (SCSAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_ASSESSMENT_FORM, true);
	    SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM, true);
	    
	    String forward=UIConstants.CREATE_SUCCESS;
	    
	    String screenType = forceFieldForm.getForceFieldScreenType();
	    if((screenType!= null) && (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_FORCE_FIELD_SCREEN))) 
	    {
	    	screenType = "";
	    	return aMapping.findForward(forward);
	    }
	    
	    String assessmentDateStr = forceFieldForm.getAssessmentDateStr();
	    
	    forceFieldForm.clearAll();
	    
	    if((scsForm != null) && (true == scsForm.isForceFieldContinued()))
	    {
	    	forceFieldForm.setAfterSCSOperation(true);
			forceFieldForm.setCreateUpdateSCSId(scsForm.getCreateUpdateSCSId());
			scsForm.setForceFieldContinued(false);
	    	scsForm.setCreateUpdateSCSId(null);
	    }
	    else
	    {
	    	forceFieldForm.setAfterSCSOperation(false);
	    	forceFieldForm.setCreateUpdateSCSId("");
	    }
	    
	    if((scsInterviewForm != null) && (true == scsInterviewForm.isForceFieldContinued()))
	    {
	    	forceFieldForm.setAfterSCSIntrvwOperation(true);
			forceFieldForm.setCreateUpdateSCSIntrvwId(scsInterviewForm.getCreateUpdateSCSInterviewId());
			scsInterviewForm.setForceFieldContinued(false);
			scsInterviewForm.setCreateUpdateSCSInterviewId(null);
	    }
	    else
	    {
	    	forceFieldForm.setAfterSCSIntrvwOperation(false);
	    	forceFieldForm.setCreateUpdateSCSIntrvwId("");
	    }
	    
	    forceFieldForm.setAction(UIConstants.CREATE);  
	    forceFieldForm.setSecondaryAction("");
	    forceFieldForm.setDefendantId(assessmentForm.getDefendantId());    
	    forceFieldForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);    
	    forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD); 
	    forceFieldForm.setAssessmentDateStr(assessmentDateStr);
	    forceFieldForm.setAssessmentDate(DateUtil.stringToDate(assessmentDateStr, DateUtil.DATE_FMT_1));
	    forceFieldForm.setScsMasterAssessmentId("");    
	    forceFieldForm.setScsIntrvwMasterAssessmentId("");
	    forceFieldForm.setMasterAssessmentId(null);
	    forceFieldForm.setAssessmentId(null);
	    forceFieldForm.setVersionId(null);
	    forceFieldForm.setAssessmentIncomplete(true);
	    
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			String errMsg = null;
			if(forceFieldForm.isAfterSCSOperation())
			{
				errMsg = scsForm.getConfirmationMsg() + " " + error.getMessage() ;
			}
			else
			if(forceFieldForm.isAfterSCSIntrvwOperation())
			{
				errMsg = scsInterviewForm.getConfirmationMsg() + " " + error.getMessage() ;
			}	
			else
			{
				errMsg = error.getMessage() ;
			}
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
			forceFieldForm.setAfterSCSOperation(false);
			return aMapping.findForward(UIConstants.FAILURE);
		}
	    
//		get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			String errMsg = null;
			if(forceFieldForm.isAfterSCSOperation())
			{
				errMsg = scsForm.getConfirmationMsg() + " Failed to retrieve ForceField Assessment" ;
			}
			else
			if(forceFieldForm.isAfterSCSIntrvwOperation())
			{
				errMsg = scsInterviewForm.getConfirmationMsg() + " Failed to retrieve ForceField Assessment" ;
			}	
			else
			{
				errMsg = "Failed to retrieve ForceField Assessment" ;
			}
			forceFieldForm.setAfterSCSOperation(false);
			forceFieldForm.setAfterSCSIntrvwOperation(false);
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		if((uiQuestionGroupsList != null) && (uiQuestionGroupsList.size() > 0))
		{		
			forceFieldForm.setForceFieldQuestionsList(uiQuestionGroupsList);
		}		
		
	   	return aMapping.findForward(forward);
	   }//end of method create()
	   
	   
	   
	   /**
		 * 
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return
		 * @throws GeneralFeedbackMessageException
		 */
		public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
				HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::submit()::Method Begin");
			
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
			
			List rankList = new ArrayList();
			Collection questionGroupList = forceFieldForm.getForceFieldQuestionsList();
			if(questionGroupList!=null && questionGroupList.size()>0)
			{
				Iterator qGroupIter = questionGroupList.iterator();
				while(qGroupIter.hasNext())
				{
					CSCQuestionGroup qGroup = (CSCQuestionGroup)qGroupIter.next();
					Collection quesList = qGroup.getQuestions();
					if(quesList!=null && quesList.size()>0)
					{
						Iterator quesIter = quesList.iterator();
						while(quesIter.hasNext())
						{
							CSCQuestion question = (CSCQuestion)quesIter.next();
							if((question.getUiControlType().equalsIgnoreCase(CSCQuestion.UI_CNTRL_TYPE_TEXT)) &&
									(question.getResponseDataType().equalsIgnoreCase(CSCQuestion.RESPONSE_VAR_TYPE_NUMERIC)))
							{
								String rankString = question.getResponseText();
								if(rankString!=null && !(rankString.trim().equalsIgnoreCase("")))
								{
									Integer rank = new Integer(rankString);
									if(!rankList.contains(rank))
									{
										rankList.add(rank);
									}
									else
									{
										sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Duplicate ranking is not allowed. Each problem must have a unique rank");
										return aMapping.findForward(UIConstants.CREATE_FAILURE);
									}
								}
							}
						}
					}
				}
				
				Collections.sort(rankList);
				
				if((!rankList.contains(1)) || (!rankList.contains(2)) || (!rankList.contains(3)))
				{
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "There must be at least 3 problems that are ranked in order from 1-3");
					return aMapping.findForward(UIConstants.CREATE_FAILURE);
				}
				
				int highestRank = (Integer)rankList.get(rankList.size()-1);
				if(highestRank!=rankList.size())
				{
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "For the problems that are ranked they must be sequential without skipping numbers");
					return aMapping.findForward(UIConstants.CREATE_FAILURE);
				}
			}
			
			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
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
		public ActionForward updateAssessmentLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
				HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
		{
			System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::updateAssessmentLink()::Method Begin");
			
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
			AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(
					aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM,
					true);

			String selectedAssessBeanId = forceFieldForm.getSelectedAssessmentBeanId();
			AssessmentLightBean selectedAssessBeanObj = null;
			String taskId = "";
			if(StringUtils.isNotEmpty(forceFieldForm.getTaskId())){
				taskId = forceFieldForm.getTaskId();
			}

			forceFieldForm.clearAll();

			forceFieldForm.setAction(UIConstants.UPDATE);
			forceFieldForm.setSecondaryAction("");
			forceFieldForm.setDefendantId(assessmentForm.getDefendantId());
			forceFieldForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
			forceFieldForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
			forceFieldForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
			forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);

			forceFieldForm.setAfterSCSOperation(false);
	    	forceFieldForm.setCreateUpdateSCSId("");
	    	
	    	forceFieldForm.setAfterSCSIntrvwOperation(false);
	    	forceFieldForm.setCreateUpdateSCSIntrvwId("");
	    	
			selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, selectedAssessBeanId,
					CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
			
			ArrayList scsAssessmentList = (ArrayList)assessmentForm.getScsAssessmentsList();
		    if((scsAssessmentList != null) && (scsAssessmentList.size() > 0))
			{
				Iterator iterator = scsAssessmentList.iterator();
				while(iterator.hasNext())
				{
					AssessmentLightBean assessmentLightBean = (AssessmentLightBean) iterator.next();
					if(assessmentLightBean.getAssessmentTypeId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENTTYPE_SCS))
					{
						forceFieldForm.setScsMasterAssessmentId(assessmentLightBean.getMasterAssessmentId());
					}
					else
					if(assessmentLightBean.getAssessmentTypeId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENTTYPE_SCS_INTERVIEW))
					{
						forceFieldForm.setScsIntrvwMasterAssessmentId(assessmentLightBean.getMasterAssessmentId());
					}	
				}
			}
			
			forceFieldForm.setMasterAssessmentId(selectedAssessBeanObj.getMasterAssessmentId());
			forceFieldForm.setAssessmentId(selectedAssessBeanObj.getAssessmentId());
			forceFieldForm.setTaskId(taskId);
			
			if(!selectedAssessBeanObj.isForceFieldStatusComplete())
			{
				forceFieldForm.setAssessmentIncomplete(true);
			}
			
			forceFieldForm.setAssessmentDate(selectedAssessBeanObj.getAssessmentDate());

			String forward = UIConstants.CREATE_SUCCESS;

			GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) AdminAssessmentsHelper
					.getAssessmentDetailsEvent(forceFieldForm);
			CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);
			ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
					.filterComposite(compResponse, ErrorResponseEvent.class);
			if (error != null) 
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Failed to retrieve Force Field Assessment");
				return aMapping.findForward(UIConstants.FAILURE);
			}

			// get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent) MessageUtil
					.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
			if (assessmentDetailsRespEvt == null) 
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Failed to retrieve Force Field Assessment");
				return aMapping.findForward(UIConstants.FAILURE);
			}
			AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(
					forceFieldForm, assessmentDetailsRespEvt);

			return aMapping.findForward(forward);
		}// end of updateAssessmentLink()
		
		
		
		
	   
	   /**
		 * Assessment updated after SCS Assessment updation
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return	 * 
		 * @throws GeneralFeedbackMessageException
		 */
		public ActionForward updateAfterSCS(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
		{	
			System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::updateAfterSCS()::Method Begin");
			
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
			SCSAssessmentForm scsForm = (SCSAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_ASSESSMENT_FORM, true);
			
			String assessmentDateStr = forceFieldForm.getAssessmentDateStr();
			
			forceFieldForm.clearAll();
			
		    if((scsForm != null) && (true == scsForm.isForceFieldContinued()))
		    {
		    	forceFieldForm.setAfterSCSOperation(true);
				forceFieldForm.setCreateUpdateSCSId(scsForm.getCreateUpdateSCSId());
				scsForm.setForceFieldContinued(false);
		    	scsForm.setCreateUpdateSCSId(null);
		    }
		    else
		    {
		    	forceFieldForm.setAfterSCSOperation(false);
		    	forceFieldForm.setCreateUpdateSCSId("");
		    	forceFieldForm.setAfterSCSIntrvwOperation(false);
		    	forceFieldForm.setCreateUpdateSCSIntrvwId("");
		    }
			    
			forceFieldForm.setAction(UIConstants.UPDATE); 
			forceFieldForm.setSecondaryAction("");
			forceFieldForm.setDefendantId(scsForm.getDefendantId());
			forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
			forceFieldForm.setAssessmentDateStr(assessmentDateStr);
			forceFieldForm.setAssessmentDate(DateUtil.stringToDate(assessmentDateStr, DateUtil.DATE_FMT_1));
			forceFieldForm.setMasterAssessmentId(scsForm.getForceFieldMasterAssessId());
	    	forceFieldForm.setAssessmentId(scsForm.getForceFieldAssessmentId());
	    	forceFieldForm.setScsMasterAssessmentId(scsForm.getMasterAssessmentId());
	    	
	    	String forward=UIConstants.CREATE_SUCCESS;
	    	
	    	GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
		    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
//			check for any error messages 
			ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
			if(error != null)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
				forceFieldForm.setAfterSCSOperation(false); 
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
//			get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
			if(assessmentDetailsRespEvt==null)
			{
				String errMsg = null;
				if(forceFieldForm.isAfterSCSOperation())
				{
					errMsg = scsForm.getConfirmationMsg() + " " + "Failed to retrieve Force Field Assessment details." ;
				}
				else
				{
					errMsg = "Failed to retrieve Force Field Assessment details." ;
				}
				forceFieldForm.setAfterSCSOperation(false);
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
				return aMapping.findForward(UIConstants.FAILURE);	
			}
			
		    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
			return aMapping.findForward(forward);
		}//end of updateAfterSCS()
		
		
		
		
		
		/**
		 * Assessment updated after SCS Interview Assessment updation
		 * @param aMapping
		 * @param aForm
		 * @param aRequest
		 * @param aResponse
		 * @return	 * 
		 * @throws GeneralFeedbackMessageException
		 */
		public ActionForward updateAfterSCSInterview(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
		{	
			System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::updateAfterSCSInterview()::Method Begin");
			
			ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
			SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM, true);
			
			String assessmentDateStr = forceFieldForm.getAssessmentDateStr();
			
			forceFieldForm.clearAll();
			
		    if((scsInterviewForm != null) && (true == scsInterviewForm.isForceFieldContinued()))
		    {
		    	forceFieldForm.setAfterSCSIntrvwOperation(true);
				forceFieldForm.setCreateUpdateSCSIntrvwId(scsInterviewForm.getCreateUpdateSCSInterviewId());
				scsInterviewForm.setForceFieldContinued(false);
				scsInterviewForm.setCreateUpdateSCSInterviewId(null);
		    }
		    else
		    {
		    	forceFieldForm.setAfterSCSOperation(false);
		    	forceFieldForm.setCreateUpdateSCSId("");
		    	forceFieldForm.setAfterSCSIntrvwOperation(false);
		    	forceFieldForm.setCreateUpdateSCSIntrvwId("");
		    }
			    
			forceFieldForm.setAction(UIConstants.UPDATE); 
			forceFieldForm.setSecondaryAction("");
			forceFieldForm.setDefendantId(scsInterviewForm.getDefendantId());
			forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
			forceFieldForm.setAssessmentDateStr(assessmentDateStr);
			forceFieldForm.setAssessmentDate(DateUtil.stringToDate(assessmentDateStr, DateUtil.DATE_FMT_1));
			forceFieldForm.setMasterAssessmentId(scsInterviewForm.getForceFieldMasterAssessId());
	    	forceFieldForm.setAssessmentId(scsInterviewForm.getForceFieldAssessmentId());
	    	forceFieldForm.setScsMasterAssessmentId(scsInterviewForm.getMasterAssessmentId());
	    	
	    	String forward=UIConstants.CREATE_SUCCESS;
	    	
	    	GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
		    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
//			check for any error messages 
			ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
			if(error != null)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
				forceFieldForm.setAfterSCSIntrvwOperation(false); 
				return aMapping.findForward(UIConstants.FAILURE);
			}
			
//			get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
			if(assessmentDetailsRespEvt==null)
			{
				String errMsg = null;
				if(forceFieldForm.isAfterSCSIntrvwOperation())
				{
					errMsg = scsInterviewForm.getConfirmationMsg() + " " + "Failed to retrieve Force Field Assessment details." ;
				}
				else
				{
					errMsg = "Failed to retrieve Force Field Assessment details." ;
				}
				forceFieldForm.setAfterSCSIntrvwOperation(false);
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
				return aMapping.findForward(UIConstants.FAILURE);	
			}
			
		    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
			return aMapping.findForward(forward);
		}//end of updateAfterSCSInterview()
		
		
		
	
		
	/**
	 * Assessment updated from the Assessment Details Page
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 * 
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{	
		System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::update()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
	    
		forceFieldForm.setAction(UIConstants.UPDATE); 
		forceFieldForm.setSecondaryAction("");
		
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}//end of update()
		
			
		
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{	
		System.out.println("AdministerAssessments::DisplayForceFieldCreateUpdateAction::cancel()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
		forceFieldForm.clearAll();
		return aMapping.findForward(UIConstants.CANCEL);
	}//end of cancel()

}//end of Class

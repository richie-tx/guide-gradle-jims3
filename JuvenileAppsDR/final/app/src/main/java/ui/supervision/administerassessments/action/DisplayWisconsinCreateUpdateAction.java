/*
 * Created on Feb 14, 2008
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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.UpdateWisconsinAssessmentEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.AssessmentResponseEvent;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.managetask.SendWisconsinIncompleteTaskEvent;
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
import ui.common.CSCQuestionResponse;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;



/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayWisconsinCreateUpdateAction extends JIMSBaseAction
{
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.newWisconsin","createInitialAssessment");	
		keyMap.put("button.submit","submit");	
		keyMap.put("button.next","next");
		keyMap.put("button.update","update");
		keyMap.put("button.updateLink","updateAssessmentLink");
		keyMap.put("button.process","createReassessment");
		keyMap.put("button.saveContinue","saveNContinue");
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
   public ActionForward saveNContinue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
	   System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::saveNContinue()::Method Begin");
		
	   String forward = null;
	   
	   	WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		boolean isNewWisconsin = false;
		if(!StringUtils.isNotEmpty(wisconsinForm.getAssessmentId())) {
			isNewWisconsin = true;
		}
		if(wisconsinForm.getWisconsinScreenType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_RISK))
		{
			Date assessmentDate = wisconsinForm.getAssessmentDate();
			String assessmentId = wisconsinForm.getAssessmentId();
			
	//		validate the assessment date is not future dated
			Date presentDate = new Date();
			int result = DateUtil.compare(assessmentDate,presentDate,DateUtil.DATE_FMT_1);
			if(result > 0)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should not be future dated");
				return aMapping.findForward("riskFailure");	
			}
				
	//	  	validate the assessment date if entered within the Supervision Period	
			boolean isAssessmentDateInRange = AdminAssessmentsHelper.isAssessmentDateInSupervisionRange(assessmentDate,wisconsinForm);
			if(!isAssessmentDateInRange)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should be within the Supervision Period");
				return aMapping.findForward("riskFailure");	
			}
			
			if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
			{
	//		   	validate for Initial Assessment Date should not be greater than Reassessment Date
				ArrayList reassessList = (ArrayList)assessmentForm.getReassessmentsList();
				if((reassessList != null) && (reassessList.size()>0))
				{
					ArrayList filteredReasessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessList);
					if(filteredReasessList.size()>0)
					{	
						Collections.sort(filteredReasessList);
						
						Date firstReassessmentDate = ((AssessmentLightBean)filteredReasessList.get(0)).getAssessmentDate();
						result = DateUtil.compare(assessmentDate, firstReassessmentDate, DateUtil.DATE_FMT_1);
						
						if(result >= 0)
						{
							sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Initial assessment Date should be before the Reassessment Date");
				   			return aMapping.findForward("riskFailure");
						}
					}	
				}
			}
	
			if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT))
			{
	//		  	validate for Reassessment Date should be greater than that of Initial Assessment Date	
				ArrayList initialAssessmentsList = (ArrayList)assessmentForm.getInitialAssessmentsList();
				if((initialAssessmentsList != null) && (initialAssessmentsList.size()==1))
				{
					AssessmentLightBean assessObj = (AssessmentLightBean)initialAssessmentsList.get(0);
					Date initialAssessmentDate = assessObj.getAssessmentDate();
	
					result = DateUtil.compare(assessmentDate,initialAssessmentDate,DateUtil.DATE_FMT_1);
					if(result<=0)
					{
						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the Initial Assessment Date");
						return aMapping.findForward("riskFailure");	
					}
				}
				
	//			validate if the Reassessment Date is after the assessment date of the previous reassessment	
				ArrayList reassessmentList = (ArrayList)assessmentForm.getReassessmentsList();
				if((reassessmentList != null) && (reassessmentList.size()>0))
				{
					ArrayList filteredReasessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessmentList);
					if(filteredReasessList.size() > 0)
					{
		   				Collections.sort(filteredReasessList);
		   				
	//	   			in case of Create Reassessment, check that assessment date should be greater than the previous reassessment
		   				if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
		   				{
		   					AssessmentLightBean previousReassess = (AssessmentLightBean)filteredReasessList.get(filteredReasessList.size()-1);
		   					result = DateUtil.compare(assessmentDate, previousReassess.getAssessmentDate(), DateUtil.DATE_FMT_1);
		   					if(result<=0)
		   					{
		   						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
		   	  					return aMapping.findForward("riskFailure");	
		   					}
		   				}
		   				
	//	   			in case of Update Reassessment
		   				if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
		   				{
		   					for(int index=0; index < filteredReasessList.size(); index++)
		   					{
		   						AssessmentLightBean assessBeanObj = (AssessmentLightBean)filteredReasessList.get(index);
		   						
		   						if(assessBeanObj.getAssessmentId().equalsIgnoreCase(assessmentId))
		   						{
	//	   						if there is only one reassessment, then skip the validations
		   							if((index == 0) && (filteredReasessList.size()==1))
		   							{
		   								break;
		   							}
		   							
	//	   						if first reassessment is updated
		   							if(index == 0)
		   							{
			   							AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
			   							
			   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
			   							if(result>=0)
			   							{
			   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
			   			  					return aMapping.findForward("riskFailure");
			   							}
		   							}
		   							else 
	//	   							if last reassessment is updated	
									if((index == filteredReasessList.size()-1))
		   							{
			   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
			   							
			   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
			   							if(result<=0)
			   							{
			   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
			   			  					return aMapping.findForward("riskFailure");
			   							}
		   							}
									else
									{
										AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
			   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
			   							if(result>=0)
			   							{
			   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
			   			  					return aMapping.findForward("riskFailure");
			   							}
			   							
			   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
			   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
			   							if(result<=0)
			   							{
			   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
			   			  					return aMapping.findForward("riskFailure");
			   							}
									}
		   						}
		   					}
//		   			  	validate if all questions are answered
		   					ArrayList riskAssessQuestionList = (ArrayList)wisconsinForm.getRiskAssessmentQuestionsList();
		   					boolean isAllQuestionsAnswered = AdminAssessmentsHelper.validateWisconsinFields(riskAssessQuestionList);
		   					if(!isAllQuestionsAnswered)
		   					{
		   						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "All fields are required");
		   						return aMapping.findForward("riskFailure");	
		   					}
		   				}
					}
				}
			}
		   forward = UIConstants.SUBMIT;
	   }
	   else
	   {
		   forward = UIConstants.SUMMARY_SUCCESS;
		   
		   populateWisconsinScores(wisconsinForm);
	   }
	   
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
    	   wisconsinForm.setMasterAssessmentId(assessRespObj.getMasterAssessmentId());
    	   wisconsinForm.setAssessmentId(assessRespObj.getAssessmentId());
    	   
    	   if (isNewWisconsin){
	    	   SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
	    	   SendWisconsinIncompleteTaskEvent wisconsinIncompleteTaskEvent = (SendWisconsinIncompleteTaskEvent) EventFactory
	    	   		.getInstance(CSTaskControllerServiceNames.SENDWISCONSININCOMPLETETASK);
	    	   Date date = new Date();
	    	   Calendar cal = Calendar.getInstance();
	    	   cal.setTime(date);
	    	   cal.add(Calendar.HOUR, 1);
	    	   Date dueDate = cal.getTime();
	    	   wisconsinIncompleteTaskEvent.setDueDate(dueDate);
			
	    	   // Get userId and Position
	    	   String userPosition = "";
	    	   GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
	    	   gEvent.setLogonId(SecurityUIHelper.getLogonId());
	    	   CompositeResponse resp = MessageUtil.postRequest(gEvent);
			
	    	   LightCSCDStaffResponseEvent staffResponse = (LightCSCDStaffResponseEvent) MessageUtil.filterComposite(resp, LightCSCDStaffResponseEvent.class);
		
	    	   if (staffResponse != null)
	    	   {
	    		   userPosition = staffResponse.getStaffPositionId();
	    	   }
	    	   wisconsinIncompleteTaskEvent.setStaffPositionId( userPosition );
	    	   wisconsinIncompleteTaskEvent.setAssessmentId(assessRespObj.getAssessmentId());
	    	   wisconsinIncompleteTaskEvent.setTaskTopic("INCOMPLETE WISCONSIN ASSESSMENT NOTIFICATION");
	    	   wisconsinIncompleteTaskEvent.setTaskSubject("Wisconsin is in an incomplete status");
	    	   wisconsinIncompleteTaskEvent.setDefendantId(wisconsinForm.getDefendantId());
	    	   wisconsinIncompleteTaskEvent.setSuperviseeName( myHeaderForm.getSuperviseeNameDesc() );
	    	   //Create the task name
	    	   StringBuffer taskName = new StringBuffer();
	    	   taskName.append( wisconsinIncompleteTaskEvent.getClass().getName() );
	    	   taskName.append( "-" );
	    	   taskName.append( Math.random() );
	    	   //Registering the task with the scheduler
	    	   RegisterTaskEvent rtEvent = new RegisterTaskEvent();
	    	   rtEvent.setScheduleClassName(CalendarConstants.ONCE_SCHEDULE_CLASS);
	    	   rtEvent.setFirstNotificationDate(dueDate);
	    	   rtEvent.setNextNotificationDate(dueDate); 
	    	   rtEvent.setTaskName(taskName.toString());
	    	   rtEvent.setNotificationEvent(wisconsinIncompleteTaskEvent);
	    	   EventManager.getSharedInstance(EventManager.REQUEST).postEvent(rtEvent);    		   
    	   }	   
       }
	   
       return aMapping.findForward(forward);
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
   public ActionForward createInitialAssessment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
	   	System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::createInitialAssessment()::Method Begin");
		
	    WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	    
	    String forward=UIConstants.CREATE_SUCCESS;
	    
	    String screenType = wisconsinForm.getWisconsinScreenType();
	    if((screenType!= null) && ((screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_RISK)) ||
	    	(screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_NEEDS))))
	    {
	    	screenType = "";
	    	return aMapping.findForward(forward);
	    }
	    
	    wisconsinForm.clearAll();
	   
	    wisconsinForm.setAction(UIConstants.CREATE); 
	    wisconsinForm.setSecondaryAction("");
	    wisconsinForm.setDefendantId(assessmentForm.getDefendantId());
	    wisconsinForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    wisconsinForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    wisconsinForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
	    wisconsinForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
	    wisconsinForm.setWisconsinAssessmentType(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT);
	    wisconsinForm.setAssessmentDate(null);
	    wisconsinForm.setMasterAssessmentId(null);
	    wisconsinForm.setAssessmentId(null);
	    wisconsinForm.setAssessmentIncomplete(true);
	    
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(wisconsinForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
	//	get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve Wisconsin Initial Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
	//	convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		populateWisconsinQuestions(wisconsinForm, (ArrayList)uiQuestionGroupsList);
	    
	   	return aMapping.findForward(forward);
   }//end of method createInitialAssessment()
   
   
   /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @throws GeneralFeedbackMessageException
	 * @roseuid 479101BB01AE
	 */
  public ActionForward createReassessment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
  {
	    System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::createReassessment()::Method Begin");
		
	   	WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	   
	    String forward=UIConstants.CREATE_SUCCESS;
	    
	    String screenType = wisconsinForm.getWisconsinScreenType();
	    if((screenType!= null) && ((screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_RISK)) ||
	    	(screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_NEEDS))))
	    {
	    	if (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_SCREEN_RISK)){
	    		wisconsinForm.setAssessmentDateStr("");
	    	}
	    	screenType = "";
	    	return aMapping.findForward(forward);
	    }
	    
	    wisconsinForm.clearAll();
	   
	    wisconsinForm.setAction(UIConstants.CREATE); 
	    wisconsinForm.setSecondaryAction("");
	    wisconsinForm.setDefendantId(assessmentForm.getDefendantId());
	    wisconsinForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    wisconsinForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    wisconsinForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
	    wisconsinForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
	    wisconsinForm.setWisconsinAssessmentType(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT);
//	    wisconsinForm.setAssessmentDate(new Date());
	    wisconsinForm.setMasterAssessmentId(null);
	    wisconsinForm.setAssessmentId(null);
	    //wisconsinForm.setAssessmentIncomplete(true);
	    
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(wisconsinForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
	//	get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve Wisconsin Reassessment questions");
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
	//	convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		populateWisconsinQuestions(wisconsinForm, (ArrayList)uiQuestionGroupsList);
	    
	   	return aMapping.findForward(forward);
  }//end of method createReassessment()

  
  /**
   * 
   * @param aMapping
   * @param aForm
   * @param aRequest
   * @param aResponse
   * @return
   * @throws GeneralFeedbackMessageException
   */
  public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
  {
	  System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::submit()::Method Begin");
		  
	WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
	AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	
	Date assessmentDate = wisconsinForm.getAssessmentDate();
	String assessmentId = wisconsinForm.getAssessmentId();
	
//	validate the assessment date is not future dated
	Date presentDate = new Date();
	int result = DateUtil.compare(assessmentDate,presentDate,DateUtil.DATE_FMT_1);
	if(result > 0)
	{
		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should not be future dated");
		return aMapping.findForward("riskFailure");	
	}
		
//  	validate the assessment date if entered within the Supervision Period	
	boolean isAssessmentDateInRange = AdminAssessmentsHelper.isAssessmentDateInSupervisionRange(assessmentDate,wisconsinForm);
	if(!isAssessmentDateInRange)
	{
		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should be within the Supervision Period");
		return aMapping.findForward("riskFailure");	
	}
	
	
	if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
	{
//	   	validate for Initial Assessment Date should not be greater than Reassessment Date
		ArrayList reassessList = (ArrayList)assessmentForm.getReassessmentsList();
		if((reassessList != null) && (reassessList.size()>0))
		{
			ArrayList filteredReasessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessList);
			if(filteredReasessList.size()>0)
			{	
				Collections.sort(filteredReasessList);
				
				Date firstReassessmentDate = ((AssessmentLightBean)filteredReasessList.get(0)).getAssessmentDate();
				result = DateUtil.compare(assessmentDate, firstReassessmentDate, DateUtil.DATE_FMT_1);
				
				if(result >= 0)
				{
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Initial assessment Date should be before the Reassessment Date");
		   			return aMapping.findForward("riskFailure");
				}
			}	
		}
	}

	if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT))
	{
//	  	validate for Reassessment Date should be greater than that of Initial Assessment Date	
		ArrayList initialAssessmentsList = (ArrayList)assessmentForm.getInitialAssessmentsList();
		if((initialAssessmentsList != null) && (initialAssessmentsList.size()==1))
		{
			AssessmentLightBean assessObj = (AssessmentLightBean)initialAssessmentsList.get(0);
			Date initialAssessmentDate = assessObj.getAssessmentDate();

			result = DateUtil.compare(assessmentDate,initialAssessmentDate,DateUtil.DATE_FMT_1);
			if(result<=0)
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the Initial Assessment Date");
				return aMapping.findForward("riskFailure");	
			}
		}
		
//		validate if the Reassessment Date is after the assessment date of the previous reassessment	
		ArrayList reassessmentList = (ArrayList)assessmentForm.getReassessmentsList();
		if((reassessmentList != null) && (reassessmentList.size()>0))
		{
			ArrayList filteredReasessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessmentList);
			if(filteredReasessList.size() > 0)
			{
   				Collections.sort(filteredReasessList);
   				
//   			in case of Create Reassessment, check that assessment date should be greater than the previous reassessment
   				if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
   				{
   					AssessmentLightBean previousReassess = (AssessmentLightBean)filteredReasessList.get(filteredReasessList.size()-1);
   					result = DateUtil.compare(assessmentDate, previousReassess.getAssessmentDate(), DateUtil.DATE_FMT_1);
   					if(result<=0)
   					{
   						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
   	  					return aMapping.findForward("riskFailure");	
   					}
   				}
   				
//   			in case of Update Reassessment
   				if(wisconsinForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
   				{
   					for(int index=0; index < filteredReasessList.size(); index++)
   					{
   						AssessmentLightBean assessBeanObj = (AssessmentLightBean)filteredReasessList.get(index);
   						
   						if(assessBeanObj.getAssessmentId().equalsIgnoreCase(assessmentId))
   						{
//   						if there is only one reassessment, then skip the validations
   							if((index == 0) && (filteredReasessList.size()==1))
   							{
   								break;
   							}
   							
//   						if first reassessment is updated
   							if(index == 0)
   							{
	   							AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
	   							
	   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
	   							if(result>=0)
	   							{
	   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
	   			  					return aMapping.findForward("riskFailure");
	   							}
   							}
   							else 
//   							if last reassessment is updated	
							if((index == filteredReasessList.size()-1))
   							{
	   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
	   							
	   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
	   							if(result<=0)
	   							{
	   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
	   			  					return aMapping.findForward("riskFailure");
	   							}
   							}
							else
							{
								AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
	   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
	   							if(result>=0)
	   							{
	   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
	   			  					return aMapping.findForward("riskFailure");
	   							}
	   							
	   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
	   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
	   							if(result<=0)
	   							{
	   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
	   			  					return aMapping.findForward("riskFailure");
	   							}
							}
   						}
   					}
   				}
			}
		}
	}
	
//  	validate if all questions are answered
	ArrayList riskAssessQuestionList = (ArrayList)wisconsinForm.getRiskAssessmentQuestionsList();
	boolean isAllQuestionsAnswered = AdminAssessmentsHelper.validateWisconsinFields(riskAssessQuestionList);
	if(!isAllQuestionsAnswered)
	{
		sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "All fields are required");
		return aMapping.findForward("riskFailure");	
	}
	
	return aMapping.findForward(UIConstants.SUBMIT);	   		
  }//end of submit()
	
  
  
  	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::next()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm) aForm;
 
		  ArrayList needsAssessQuestionList =(ArrayList)wisconsinForm.getNeedsAssessmentQuestionsList(); 
		  boolean isAllQuestionsAnswered = AdminAssessmentsHelper.validateWisconsinFields(needsAssessQuestionList);
		  if(!isAllQuestionsAnswered) 
		  { 
		  	sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "All fields are required");
			return aMapping.findForward("needsFailure");	
		  }
		populateWisconsinScores(wisconsinForm);
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}//end of next()

	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward updateAssessmentLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::updateAssessmentLink()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		   
		String selectedAssessBeanId = wisconsinForm.getSelectedAssessmentBeanId();
		String selectedWisAssessType = wisconsinForm.getSelectedAssessmentType();
		String taskId = wisconsinForm.getTaskId();
		AssessmentLightBean selectedAssessBeanObj = null;
		
		wisconsinForm.clearAll();
	    wisconsinForm.setAction(UIConstants.UPDATE); 
	    wisconsinForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
	    wisconsinForm.setDefendantId(assessmentForm.getDefendantId());
	    wisconsinForm.setSecondaryAction("");
	    wisconsinForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    wisconsinForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    wisconsinForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		wisconsinForm.setTaskId(taskId);
	    if(selectedWisAssessType.equalsIgnoreCase("true"))
	    {
	    	wisconsinForm.setWisconsinAssessmentType(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT);
	    	selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, selectedAssessBeanId, CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT_LIST);
	    }
	    else
	    {
	    	wisconsinForm.setWisconsinAssessmentType(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT);
	    	selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, selectedAssessBeanId, CSCAssessmentConstants.ASSESSMENT_REASSESSMENT_LIST);
	    }
	    
	    wisconsinForm.setMasterAssessmentId(selectedAssessBeanObj.getMasterAssessmentId());
	    wisconsinForm.setAssessmentId(selectedAssessBeanObj.getAssessmentId());
	    wisconsinForm.setAssessmentDate(selectedAssessBeanObj.getAssessmentDate());
	    if(!selectedAssessBeanObj.isStatusComplete())
    	{
    		wisconsinForm.setAssessmentIncomplete(true);
    	}
	    
	    String forward=UIConstants.CREATE_SUCCESS;
	    
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(wisconsinForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve Wisconsin Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve Wisconsin Assessment");
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
		wisconsinForm.setTotalRiskScore(assessmentDetailsRespEvt.getRiskScore());
		wisconsinForm.setRiskLevel(assessmentDetailsRespEvt.getRiskLevel());
		wisconsinForm.setTotalNeedsScore(assessmentDetailsRespEvt.getNeedsScore());
		wisconsinForm.setNeedsLevel(assessmentDetailsRespEvt.getNeedsLevel());
		wisconsinForm.setLevelOfSupervision(assessmentDetailsRespEvt.getLevelOfSupervision());
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
	
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		populateWisconsinQuestions(wisconsinForm, (ArrayList)uiQuestionGroupsList);
	    
	   	return aMapping.findForward(forward);
	}//end of updateAssessmentLink()
	
	
	/**
	 * Assessment updated from the Assessment Details Page
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 * 
	 */
	public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{	
		System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::update()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		
		wisconsinForm.setAction(UIConstants.UPDATE); 
		wisconsinForm.setSecondaryAction("");
		
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
		System.out.println("AdministerAssessments::DisplayWisconsinCreateUpdateAction::cancel()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		wisconsinForm.clearAll();
		return aMapping.findForward(UIConstants.CANCEL);
	}//end of cancel()
	
	
	/**
	 * 
	 * @param questionGrpList
	 * @return
	 */
	private int getQuestionGroupscore(ArrayList questionGrpList)
	{
		int resultScore = 0;
		
		if((questionGrpList != null) && (questionGrpList.size() > 0))
		{
			Iterator grpItr = questionGrpList.iterator();
			
			while(grpItr.hasNext())
			{
				CSCQuestionGroup riskGrp = (CSCQuestionGroup) grpItr.next();
				ArrayList questionsList = (ArrayList)riskGrp.getQuestions();
				
				if((questionsList != null) && (questionsList.size() > 0))
				{
					Iterator questItr = questionsList.iterator();
					
					while(questItr.hasNext())
					{
						CSCQuestion question = (CSCQuestion) questItr.next();
					
						String selectedRespId = question.getResponseId();
						
						if((selectedRespId != null) && (!selectedRespId.equalsIgnoreCase("")))
						{
							ArrayList responsesList = (ArrayList) question.getResponseChoices();
							
							if((responsesList != null) && (responsesList.size() > 0))
							{
								Iterator responseIt = responsesList.iterator();
								
								while(responseIt.hasNext())
								{
									CSCQuestionResponse response = (CSCQuestionResponse)responseIt.next();
									if((response.getResponseId().equalsIgnoreCase(selectedRespId)) && (!response.getResponseValue().equalsIgnoreCase("")))
									{
										int tempRespScore = 0;
										try
										{
											tempRespScore = Integer.parseInt(response.getResponseValue());
										}
										catch(Exception ex)
										{
										}
										resultScore = resultScore + tempRespScore;
									}
								}
							}
						}
					}
				}
			}
		}
		return resultScore;
	}//end of getQuestionGroupscore()
	
	
	
	
	/**
	 * 
	 * @param wisconsinForm
	 */
	private void populateWisconsinScores(WisconsinAssessmentForm wisconsinForm)
	{
//		set Risk Score
		int riskScore = getQuestionGroupscore((ArrayList)wisconsinForm.getRiskAssessmentQuestionsList());
		int riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		
		if((riskScore >= 0) && (riskScore <= 7 ))
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM_CD;
		}
		else if((riskScore>=8) && (riskScore <= 14))
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		}
		else if(riskScore >= 15)
		{
			riskLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM_CD;
		}
		wisconsinForm.setTotalRiskScore(Integer.toString(riskScore));
		wisconsinForm.setRiskLevel(Integer.toString(riskLevel));
		
//		set Needs Score
		int needsScore = getQuestionGroupscore((ArrayList)wisconsinForm.getNeedsAssessmentQuestionsList());
		int needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		if(needsScore <= 14)
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MINIMUM_CD;
		}
		else if((needsScore>=15) && (needsScore <= 29))
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MEDIUM_CD;
		}
		else
		{
			needsLevel = CSCAssessmentConstants.ASSESSMENT_LOS_MAXIMUM_CD;
		}
		wisconsinForm.setTotalNeedsScore(Integer.toString(needsScore));
		wisconsinForm.setNeedsLevel(Integer.toString(needsLevel));
		
		int levelOfSupervisionCd = riskLevel;
		if(needsLevel < levelOfSupervisionCd)
		{
			levelOfSupervisionCd = needsLevel;
		}
		
		wisconsinForm.setLevelOfSupervisionCd(Integer.toString(levelOfSupervisionCd));
		wisconsinForm.setLevelOfSupervision(AdminAssessmentsHelper.getLevelOfSupervisionDesc(levelOfSupervisionCd));
	}//end of populateWisconsinScores()
	
	
	/**
	 * 
	 * @param wisconsinForm
	 * @param uiQuestionGroupsList
	 */
	private void populateWisconsinQuestions(WisconsinAssessmentForm wisconsinForm, ArrayList uiQuestionGroupsList)
	{
//		separate the questions into Risk and Needs QuestionList and assign then to WisconsinForm
		if(uiQuestionGroupsList.size() > 0)
		{
			Iterator iterator = uiQuestionGroupsList.iterator();
			
			while(iterator.hasNext())
			{
				CSCQuestionGroup quesGroup = (CSCQuestionGroup)iterator.next();
				
				if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
				{
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITAIL_RISK_QUEST_GRP_ID))
					{
						ArrayList riskList = new ArrayList();
						riskList.add(quesGroup);
						wisconsinForm.setRiskAssessmentQuestionsList(riskList);
					}
					else
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_INITIAL_NEEDS_QUEST_GRP_ID))
					{
						ArrayList needsList = new ArrayList();
						needsList.add(quesGroup);
						wisconsinForm.setNeedsAssessmentQuestionsList(needsList);
					}
				}
				else
				{
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_REASSESS_RISK_QUEST_GRP_ID))
					{
						ArrayList riskList = new ArrayList();
						riskList.add(quesGroup);
						wisconsinForm.setRiskAssessmentQuestionsList(riskList);
					}
					else
					if(quesGroup.getGroupId().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_WISCONSIN_REASSESS_NEEDS_QUEST_GRP_ID))
					{
						ArrayList needsList = new ArrayList();
						needsList.add(quesGroup);
						wisconsinForm.setNeedsAssessmentQuestionsList(needsList);
					}
				}
			}
		}
	}//end of populateWisconsinQuestions()
   
}//end of Class
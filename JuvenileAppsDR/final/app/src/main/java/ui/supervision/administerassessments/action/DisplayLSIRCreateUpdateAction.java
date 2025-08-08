/*
 * Created on Feb 19, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.LSIRAssessmentForm;


/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayLSIRCreateUpdateAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.enterLSIRScore","createAssessment");	
		keyMap.put("button.submit","submit");	
		keyMap.put("button.update","update");
		keyMap.put("button.updateLink","updateAssessmentLink");
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
	public ActionForward createAssessment(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayLSIRCreateUpdateAction::createAssessment()::Method Begin");
		
	    LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aForm;
	    String lsirAssessmentType = lsirForm.getLsirAssessmentType();
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	   
	    String forward=UIConstants.CREATE_SUCCESS;
	    
	    String screenType = lsirForm.getLsirScreenType();
	    if((screenType!= null) && (screenType.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_LSIR_SCREEN))) 
	    {
	    	screenType = "";
	    	return aMapping.findForward(forward);
	    }
	    
	    lsirForm.clearAll();
	   
	    lsirForm.setAction(UIConstants.CREATE);    
	    lsirForm.setSecondaryAction("");
	    lsirForm.setDefendantId(assessmentForm.getDefendantId());
	    lsirForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    lsirForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    lsirForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
	    lsirForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
	    lsirForm.setLsirAssessmentType(lsirAssessmentType);
	    lsirForm.setAssessmentDate(null);
	    lsirForm.setMasterAssessmentId(null);
	    lsirForm.setAssessmentId(null);
	   
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(lsirForm);
 
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve LSIR Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
//		get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve LSIR Assessment questions");
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);
		if((uiQuestionGroupsList != null) && (uiQuestionGroupsList.size() == 1))
		{
			lsirForm.setLsirAssessmentQuestionsList(uiQuestionGroupsList);			
		}
	    
	   	return aMapping.findForward(forward);
	   }//end of method createAssessment()

	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return	 * 
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayLSIRCreateUpdateAction::submit()::Method Begin");
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aForm;
		AssessmentForm assessmentForm = (AssessmentForm) this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		
		String forward = UIConstants.SUMMARY_SUCCESS;
		
		Date assessmentDate = lsirForm.getAssessmentDate();
		String assessmentId = lsirForm.getAssessmentId();
		
//   	validate the assessment date is not future dated
		Date presentDate = new Date();
		int result = DateUtil.compare(assessmentDate,presentDate,DateUtil.DATE_FMT_1);
   		if(result > 0)
   		{
   			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should not be future dated");
   			return aMapping.findForward("createFailure");
   		}
		 
//   	validate the assessment date if entered within the Supervision Period	
   		boolean isAssessmentDateInRange = AdminAssessmentsHelper.isAssessmentDateInSupervisionRange(assessmentDate,lsirForm);
   		if(!isAssessmentDateInRange)
   		{
   			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Assessment Date should be within the Supervision Period");
   			return aMapping.findForward("createFailure");
   		}
   		

   		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
   		{
//   	   	validate for Initial Assessment Date should not be greater than Reassessment Date
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
	   		   			return aMapping.findForward("createFailure");
	   				}
   				}	
   			}
   		}
   		

   		if(lsirForm.getLsirAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT))
   		{
//   	  	validate for Reassessment Date should be greater than that of Initial Assessment Date	
   			ArrayList initialAssessmentsList = (ArrayList)assessmentForm.getInitialAssessmentsList();
   			if((initialAssessmentsList != null) && (initialAssessmentsList.size()==1))
   			{
  				AssessmentLightBean assessObj = (AssessmentLightBean)initialAssessmentsList.get(0);
  				Date initialAssessmentDate = assessObj.getAssessmentDate();

  				result = DateUtil.compare(assessmentDate,initialAssessmentDate,DateUtil.DATE_FMT_1);
  				if(result<=0)
  				{
  					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the Initial Assessment Date");
  					return aMapping.findForward("createFailure");	
  				}
   			}
   			
//   		validate if the Reassessment Date is after the assessment date of the previous reassessment	
   			ArrayList reassessmentList = (ArrayList)assessmentForm.getReassessmentsList();
   			if((reassessmentList != null) && (reassessmentList.size()>0))
   			{
   				ArrayList filteredReasessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessmentList);
   				if(filteredReasessList.size() > 0)
   				{
	   				Collections.sort(filteredReasessList);
	   				
//	   				in case of Create Reassessment, check that assessment date should be greater than the previous reassessment
	   				if(lsirForm.getAction().equalsIgnoreCase(UIConstants.CREATE))
	   				{
	   					AssessmentLightBean previousReassess = (AssessmentLightBean)filteredReasessList.get(filteredReasessList.size()-1);
	   					result = DateUtil.compare(assessmentDate, previousReassess.getAssessmentDate(), DateUtil.DATE_FMT_1);
	   					if(result<=0)
	   					{
	   						sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
	   	  					return aMapping.findForward("createFailure");	
	   					}
	   				}
	   				
//	   				in case of Update Reassessment
	   				if(lsirForm.getAction().equalsIgnoreCase(UIConstants.UPDATE))
	   				{
	   					for(int index=0; index < filteredReasessList.size(); index++)
	   					{
	   						AssessmentLightBean assessBeanObj = (AssessmentLightBean)filteredReasessList.get(index);
	   						
	   						if(assessBeanObj.getAssessmentId().equalsIgnoreCase(assessmentId))
	   						{
//	   							if there is only one reassessment, then skip the validations
	   							if((index == 0) && (filteredReasessList.size()==1))
	   							{
	   								break;
	   							}
	   							
//	   							if first reassessment is updated
	   							if(index == 0)
	   							{
		   							AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
		   							
		   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
		   							if(result>=0)
		   							{
		   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
		   			  					return aMapping.findForward("createFailure");
		   							}
	   							}
	   							else 
//	   							if last subsequent reassessment is updated	
								if((index == filteredReasessList.size()-1))
	   							{
		   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
		   							
		   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
		   							if(result<=0)
		   							{
		   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
		   			  					return aMapping.findForward("createFailure");
		   							}
	   							}
								else
								{
									AssessmentLightBean successiveReassessBean = (AssessmentLightBean)filteredReasessList.get(index+1);
		   							result = DateUtil.compare(assessmentDate, successiveReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
		   							if(result>=0)
		   							{
		   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be before the assessment date of the subsequent reassessment");
		   			  					return aMapping.findForward("createFailure");
		   							}
		   							
		   							AssessmentLightBean previousReassessBean = (AssessmentLightBean)filteredReasessList.get(index-1);
		   							result = DateUtil.compare(assessmentDate, previousReassessBean.getAssessmentDate(), DateUtil.DATE_FMT_1); 
		   							if(result<=0)
		   							{
		   								sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Reasssessment Date should be after the assessment date of the previous reassessment");
		   			  					return aMapping.findForward("createFailure");
		   							}
								}
	   						}
	   					}
	   				}
   				}
   			}
   		}
   		
		return aMapping.findForward(forward);
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
	public ActionForward updateAssessmentLink(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayLSIRCreateUpdateAction::updateAssessmentLink()::Method Begin");
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aForm;
		
		String selectedLsirAssessmentType = lsirForm.getSelectedAssessmentType();
		String selectedAssessBeanId = lsirForm.getSelectedAssessmentBeanId();
		AssessmentLightBean selectedAssessBeanObj = null;
		
		lsirForm.clearAll();
		
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);

		lsirForm.setAction(UIConstants.UPDATE); 
		lsirForm.setSecondaryAction("");
		lsirForm.setDefendantId(assessmentForm.getDefendantId());
		lsirForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    lsirForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
		lsirForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		lsirForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
		
		if(selectedLsirAssessmentType.equalsIgnoreCase("true"))
	    {
			lsirForm.setLsirAssessmentType(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT);
	    	selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, selectedAssessBeanId, CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT_LIST);
	    }
	    else
	    {
	    	lsirForm.setLsirAssessmentType(CSCAssessmentConstants.ASSESSMENT_REASSESSMENT);
	    	selectedAssessBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, selectedAssessBeanId, CSCAssessmentConstants.ASSESSMENT_REASSESSMENT_LIST);
	    }
		    
		lsirForm.setMasterAssessmentId(selectedAssessBeanObj.getMasterAssessmentId());
		lsirForm.setAssessmentId(selectedAssessBeanObj.getAssessmentId());
		lsirForm.setAssessmentDate(selectedAssessBeanObj.getAssessmentDate());
		
		String forward=UIConstants.CREATE_SUCCESS;
		
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(lsirForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve LSIR Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
//		get the collection of QuestionGroupResponseEvents
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse,AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt == null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve LSIR Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		Collection questionGroupResponseEvtList = assessmentDetailsRespEvt.getQuestionAnswerList();
//		convert the questionGroupResponseEvents to UIQuestionGroup 
		Collection uiQuestionGroupsList = UIUtil.mapCSCQuestionGroupRespEvtsToUIQuestionGroup(questionGroupResponseEvtList);

		if((uiQuestionGroupsList != null) && (uiQuestionGroupsList.size() == 1))
		{
			lsirForm.setLsirAssessmentQuestionsList(uiQuestionGroupsList);			
		}

		lsirForm.setComments(assessmentDetailsRespEvt.getComments());
		
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
		System.out.println("AdministerAssessments::DisplayLSIRCreateUpdateAction::update()::Method Begin");
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aForm;
		
		lsirForm.setAction(UIConstants.UPDATE); 
		lsirForm.setSecondaryAction("");
		
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
	
	
	
	
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
		System.out.println("AdministerAssessments::DisplayLSIRCreateUpdateAction::cancel()::Method Begin");
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)aForm;
		lsirForm.clearAll();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
}//end of Class

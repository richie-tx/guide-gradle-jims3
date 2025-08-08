/*
 * Created on Feb 27, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.PriorAssessmentVersionResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.AssessmentVersion;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayWisconsinDetailsAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.viewDetails","viewDetails");
		keyMap.put("button.viewVersion","viewVersion");
		keyMap.put("button.correctInitial","update");
		keyMap.put("button.delete","delete");
		keyMap.put("button.correct","update");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewDetails(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::DisplayWisconsinDetailsAction::viewDetails()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	  
	    wisconsinForm.clear();
	    wisconsinForm.setAction(UIConstants.VIEW_DETAIL);
	    wisconsinForm.setSecondaryAction("");
	    wisconsinForm.setDefendantId(assessmentForm.getDefendantId());
	    wisconsinForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    wisconsinForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    wisconsinForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN);
	    wisconsinForm.setSupervisionPeriod(wisconsinForm.getSelectedAssessSupervisionPrd());
	    wisconsinForm.setWisconsinAssessmentType(wisconsinForm.getSelectedAssessmentType());	    
	    String assessmentBeanId = wisconsinForm.getSelectedAssessmentBeanId();
	    wisconsinForm.clearSelected();
	    
	    AssessmentLightBean assessmentBeanObj;
	    if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
	    {
	    	assessmentBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, assessmentBeanId, CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT_LIST);
	    	wisconsinForm.setActualAssessmentDate(assessmentBeanObj.getActualAssessmentDate());
	    	wisconsinForm.setMigrated(assessmentBeanObj.getMigrated());
	    }
	    else
	    {
	    	assessmentBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, assessmentBeanId, CSCAssessmentConstants.ASSESSMENT_REASSESSMENT_LIST);
	    }
	    if(assessmentBeanObj==null)
	    {
	    	sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to display the selected Wisconsin Assessment details");
			return aMapping.findForward(UIConstants.FAILURE);
	    }
	  	
    	wisconsinForm.setMasterAssessmentId(assessmentBeanObj.getMasterAssessmentId());
    	wisconsinForm.setAssessmentId(assessmentBeanObj.getAssessmentId());
	    
    	if(!assessmentBeanObj.isStatusComplete())
    	{
    		wisconsinForm.setAssessmentIncomplete(true);
    	}
    	
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(wisconsinForm);
	    
	    String forward=UIConstants.VIEW_SUCCESS;
	   	
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
	    ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);	
		}
		
//		get the Assessment Details for the latest version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
		Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
		
		if(assessmentDetailsRespEvt==null || priorVersionList==null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected Wisconsin Assessment details could not be retrieved");
			return aMapping.findForward(UIConstants.FAILURE);
		}
			
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForWisconsin(wisconsinForm, assessmentDetailsRespEvt);		
		AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(wisconsinForm, priorVersionList);
		
	   	return aMapping.findForward(forward);
	}//end of viewDetails()

	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewVersion(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplayWisconsinDetailsAction::viewVersion()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		
		wisconsinForm.setAssessmentDate(null);
		wisconsinForm.setAssessmentDateStr("");
		wisconsinForm.setAssessmentId("");
		wisconsinForm.setVersionId("");
		wisconsinForm.setIslatestVersionShown(false);
		ArrayList emptyList = new ArrayList();
		wisconsinForm.setRiskAssessmentQuestionsList(emptyList);
		wisconsinForm.setNeedsAssessmentQuestionsList(emptyList);
		
		wisconsinForm.setAction(UIConstants.ASSESSMENT_VERSION_DETAILS);
		
		ArrayList versionDetailsList = (ArrayList)wisconsinForm.getVersionDetailsList();
		if(versionDetailsList.size() > 0)
		{
			Iterator it = versionDetailsList.iterator();
			while(it.hasNext())
			{
				AssessmentVersion assessmentVerObj = (AssessmentVersion)it.next();
				if(assessmentVerObj.getVersionNumber().equalsIgnoreCase(wisconsinForm.getSelectedVersionNumber()))
				{
					wisconsinForm.setAssessmentDate(assessmentVerObj.getAssessmentDate());
					wisconsinForm.setAssessmentDateStr(assessmentVerObj.getAssessmentDateStr());
					wisconsinForm.setAssessmentId(assessmentVerObj.getAssessmentId());
					wisconsinForm.setVersionId(assessmentVerObj.getVersionNumber());
					break;
				}
			}
			String latestVer = ((AssessmentVersion)versionDetailsList.get(0)).getVersionNumber();
			if(wisconsinForm.getVersionId().equalsIgnoreCase(latestVer))
			{
				wisconsinForm.setIslatestVersionShown(true);
			}
		}
		
		String forward=UIConstants.VIEW_SUCCESS;
		
		GetAssessmentDetailsEvent assessmentDetailsReqEvt = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(wisconsinForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsReqEvt);    
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward("versionfailure");	
		}
		
//		get the Assessment Details for the latest version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt==null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected Wisconsin Assessment version details could not be retrieved");
			return aMapping.findForward("versionfailure");
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForWisconsin(wisconsinForm, assessmentDetailsRespEvt);		

	   	return aMapping.findForward(forward);
	}//end of viewVersion()

	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward update(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplayWisconsinDetailsAction::update()::Method Begin");
		
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}//end of update()
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward delete(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayWisconsinDetailsAction::delete()::Method Begin");
		
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)aForm;
		
		AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
		ArrayList reassessList = (ArrayList)assessmentForm.getReassessmentsList();
		//String scsAssessExist = assessmentForm.getScsAssessmentExist();
		
//		Initial Assessment cannot be deleted if SCSAssessment or Reassessment exists
		if(wisconsinForm.getWisconsinAssessmentType().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT))
		{
			//Removed per defect 65856.  There are no requirements stating that a Wisconsin Initial cannot be deleted if there is an SCS.
			/* if(scsAssessExist.equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_EXIST))
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Initial Assessment cannot be deleted if a Reassessment or SCS Assessment exists");
				return aMapping.findForward("versionfailure");	
			}
			else*/
			if(reassessList != null && reassessList.size()> 0)
			{
				ArrayList filteredReassessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessList);
				
				if(filteredReassessList.size()>0)
				{
					//sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Initial Assessment cannot be deleted if a Reassessment or SCS Assessment exists");
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Initial Assessment cannot be deleted if a Reassessment exists");
					return aMapping.findForward("versionfailure");
				}
				else
				{	
					wisconsinForm.setAction(UIConstants.DELETE); 
					wisconsinForm.setSecondaryAction(UIConstants.SUMMARY);
					return aMapping.findForward(UIConstants.DELETE_SUCCESS);
				}
			}
		}
//		only the most current reassessment can be deleted
		else
		{
			ArrayList filteredReassessList = AdminAssessmentsHelper.getReassessListWithoutPendingWisc(reassessList);
			
			Collections.sort(filteredReassessList);
			AssessmentLightBean currentReasses = (AssessmentLightBean)filteredReassessList.get(filteredReassessList.size()-1);
			
			if(wisconsinForm.getAssessmentId().equalsIgnoreCase(currentReasses.getAssessmentId()))
			{
				wisconsinForm.setAction(UIConstants.DELETE); 
				wisconsinForm.setSecondaryAction(UIConstants.SUMMARY);
				
				return aMapping.findForward(UIConstants.DELETE_SUCCESS);
			}
			else
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "The most recent Reassessment can only be deleted");
				return aMapping.findForward("versionfailure");
			}
		}
		
		wisconsinForm.setAction(UIConstants.DELETE); 
		wisconsinForm.setSecondaryAction(UIConstants.SUMMARY);
		
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}//end of delete()
}
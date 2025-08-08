/*
 * Created on Mar 4, 2008
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
import mojo.km.utilities.DateUtil;
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
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;



/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayForceFieldDetailsAction extends JIMSBaseAction
{
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.viewDetails","viewDetails");
		keyMap.put("button.viewVersion","viewVersion");
		keyMap.put("button.viewDetailsAfterSCS","viewDetailsAfterSCS");
		keyMap.put("button.viewDetailsAfterSCSInterview","viewDetailsAfterSCSInterview");
		keyMap.put("button.correctInitial","update");
		keyMap.put("button.delete","delete");
		keyMap.put("button.print","print");
	}
	
	
	public ActionForward viewDetails(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::viewDetails()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	  
	    forceFieldForm.clear();
	    forceFieldForm.setAction(UIConstants.VIEW_DETAIL);
	    forceFieldForm.setSecondaryAction("");
	    forceFieldForm.setAfterSCSOperation(false);
	    forceFieldForm.setAfterSCSIntrvwOperation(false);
	    forceFieldForm.setDefendantId(assessmentForm.getDefendantId());
	    forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
	    forceFieldForm.setSupervisionPeriod(forceFieldForm.getSelectedAssessSupervisionPrd());	    
	    String assessmentBeanId = forceFieldForm.getSelectedAssessmentBeanId();
	    forceFieldForm.clearSelected();
	    
	    AssessmentLightBean assessmentBeanObj;
	    assessmentBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, assessmentBeanId, CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
	    if(assessmentBeanObj==null)
	    {
	    	sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve selected Force Field Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
	    }
	  	
	    forceFieldForm.setActualAssessmentDate(assessmentBeanObj.getActualAssessmentDate());
	    forceFieldForm.setMigrated(assessmentBeanObj.getMigrated());
    	forceFieldForm.setMasterAssessmentId(assessmentBeanObj.getForceFieldMasterAssessId());
    	forceFieldForm.setAssessmentId(assessmentBeanObj.getForceFieldAssessmentId());
    	
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
    	
    	if(!assessmentBeanObj.isForceFieldStatusComplete())
    	{
    		forceFieldForm.setAssessmentIncomplete(true);
    	}
    	
    	String forward=UIConstants.VIEW_SUCCESS;
    	
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
	    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
//		get the Assessment Details for the latest version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
		Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
		
		if(assessmentDetailsRespEvt==null || priorVersionList==null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, Force Field Assessment details could not be retrieved.");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
		AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(forceFieldForm, priorVersionList);
		
	   	return aMapping.findForward(forward);
	}//end of viewDetails()
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewDetailsAfterSCS(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::viewDetailsAfterSCS()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
	    SCSAssessmentForm scsForm = (SCSAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_ASSESSMENT_FORM, true);
	  
	    forceFieldForm.clearAll();
	    forceFieldForm.setAction(UIConstants.VIEW_DETAIL);
	    forceFieldForm.setSecondaryAction("");
	    forceFieldForm.setAfterSCSOperation(true);
	    forceFieldForm.setDefendantId(scsForm.getDefendantId());
	    forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
	    forceFieldForm.setSupervisionPeriod(scsForm.getSupervisionPeriod());	
//	    populate the forceField ID corresponding to the selected SCS verion
	    forceFieldForm.setAssessmentDate(scsForm.getAssessmentDate());
    	forceFieldForm.setMasterAssessmentId(scsForm.getForceFieldMasterAssessId());
    	forceFieldForm.setAssessmentId(scsForm.getForceFieldAssessmentId());
    	forceFieldForm.setScsMasterAssessmentId(scsForm.getMasterAssessmentId());
    	forceFieldForm.setAssessmentIncomplete(scsForm.isForceFieldAssessmentIncomplete());
    	
    	String forward=UIConstants.VIEW_SUCCESS;
    	
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			forceFieldForm.setAfterSCSOperation(false);
			return aMapping.findForward("afterSCSFailure");
		}
		
//		get the Assessment Details for the selected version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
		Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
		
		if(assessmentDetailsRespEvt==null || priorVersionList==null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, Force Field Assessment details could not be retrieved");
			forceFieldForm.setAfterSCSOperation(false);
			return aMapping.findForward("afterSCSFailure");
		}
		
//		populate the assessment details of the selected version of forceField
		AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
		 
//		If latest version of SCS is selected, then latest version of forceField will be associated with it
		if(scsForm.isIslatestVersionShown())
		{
			AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(forceFieldForm, priorVersionList);
		}
//		if previous version of SCS selected, then latest or previous version of forcefield will be associated with it
		else
		{
			ArrayList versionList = new ArrayList();
			
			if(priorVersionList.size() > 0)
			{
				Iterator it = priorVersionList.iterator();
				while(it.hasNext())
				{
					PriorAssessmentVersionResponseEvent versionRespEvt =  (PriorAssessmentVersionResponseEvent)it.next();
					
					AssessmentVersion assessmentVersion = new AssessmentVersion();
					
					assessmentVersion.setAssessmentDate(versionRespEvt.getAssessmentDate());
					String dateString = DateUtil.dateToString(versionRespEvt.getAssessmentDate(), DateUtil.DATE_FMT_1);
					assessmentVersion.setAssessmentDateStr(dateString);
					
					assessmentVersion.setTransactionDate(versionRespEvt.getTransactionDate());
					dateString = DateUtil.dateToString(versionRespEvt.getTransactionDate(), DateUtil.DATE_FMT_1);
					assessmentVersion.setTransactionDateStr(dateString);
					
					assessmentVersion.setAssessmentId(versionRespEvt.getAssessmentId());
					assessmentVersion.setVersionNumber(versionRespEvt.getVersionNumber());
					
					versionList.add(assessmentVersion);
				}
				Collections.sort(versionList);
				forceFieldForm.setVersionDetailsList(versionList);
			}
//			set the versionId of selected forceField
			Iterator versionListIt = forceFieldForm.getVersionDetailsList().iterator();
			while(versionListIt.hasNext())
			{
				AssessmentVersion assessVersionObj = (AssessmentVersion)versionListIt.next();
				if(assessVersionObj.getAssessmentId().equalsIgnoreCase(forceFieldForm.getAssessmentId()))
				{
					forceFieldForm.setVersionId(assessVersionObj.getVersionNumber());
					AssessmentVersion latestAssessment = (AssessmentVersion)versionList.get(versionList.size()-1);
					if(latestAssessment.getAssessmentId().equalsIgnoreCase(forceFieldForm.getAssessmentId()))
					{
						forceFieldForm.setIslatestVersionShown(true);
					}
					else
					{
						forceFieldForm.setIslatestVersionShown(false);
					}
				}
			}
		}
		
	   	return aMapping.findForward(forward);
	}//end of viewDetailsAfterSCS()
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewDetailsAfterSCSInterview(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::viewDetailsAfterSCSInterview()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
	    SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM, true);
	  
	    forceFieldForm.clearAll();
	    forceFieldForm.setAction(UIConstants.VIEW_DETAIL);
	    forceFieldForm.setSecondaryAction("");
	    forceFieldForm.setAfterSCSIntrvwOperation(true);
	    forceFieldForm.setDefendantId(scsInterviewForm.getDefendantId());
	    forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
	    forceFieldForm.setSupervisionPeriod(scsInterviewForm.getSupervisionPeriod());	
//	    populate the forceField ID corresponding to the selected SCS Interview version
	    forceFieldForm.setAssessmentDate(scsInterviewForm.getAssessmentDate());
    	forceFieldForm.setMasterAssessmentId(scsInterviewForm.getForceFieldMasterAssessId());
    	forceFieldForm.setAssessmentId(scsInterviewForm.getForceFieldAssessmentId());
    	forceFieldForm.setScsIntrvwMasterAssessmentId(scsInterviewForm.getMasterAssessmentId());
    	forceFieldForm.setAssessmentIncomplete(scsInterviewForm.isForceFieldAssessmentIncomplete());
    	
    	String forward=UIConstants.VIEW_SUCCESS;
    	
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			forceFieldForm.setAfterSCSOperation(false);
			return aMapping.findForward("afterSCSFailure");
		}
		
//		get the Assessment Details for the selected version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
		Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
		
		if(assessmentDetailsRespEvt==null || priorVersionList==null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, Force Field Assessment details could not be retrieved");
			forceFieldForm.setAfterSCSIntrvwOperation(false);
			return aMapping.findForward("afterSCSFailure");
		}
		
//		populate the assessment details of the selected version of forceField
		AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
		 
//		If latest version of SCS is selected, then latest version of forceField will be associated with it
		if(scsInterviewForm.isIslatestVersionShown())
		{
			AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(forceFieldForm, priorVersionList);
		}
//		if previous version of SCS selected, then latest or previous version of forcefield will be associated with it
		else
		{
			ArrayList versionList = new ArrayList();
			
			if(priorVersionList.size() > 0)
			{
				Iterator it = priorVersionList.iterator();
				while(it.hasNext())
				{
					PriorAssessmentVersionResponseEvent versionRespEvt =  (PriorAssessmentVersionResponseEvent)it.next();
					
					AssessmentVersion assessmentVersion = new AssessmentVersion();
					
					assessmentVersion.setAssessmentDate(versionRespEvt.getAssessmentDate());
					String dateString = DateUtil.dateToString(versionRespEvt.getAssessmentDate(), DateUtil.DATE_FMT_1);
					assessmentVersion.setAssessmentDateStr(dateString);
					
					assessmentVersion.setTransactionDate(versionRespEvt.getTransactionDate());
					dateString = DateUtil.dateToString(versionRespEvt.getTransactionDate(), DateUtil.DATE_FMT_1);
					assessmentVersion.setTransactionDateStr(dateString);
					
					assessmentVersion.setAssessmentId(versionRespEvt.getAssessmentId());
					assessmentVersion.setVersionNumber(versionRespEvt.getVersionNumber());
					
					versionList.add(assessmentVersion);
				}
				Collections.sort(versionList);
				forceFieldForm.setVersionDetailsList(versionList);
			}
//			set the versionId of selected forceField
			Iterator versionListIt = forceFieldForm.getVersionDetailsList().iterator();
			while(versionListIt.hasNext())
			{
				AssessmentVersion assessVersionObj = (AssessmentVersion)versionListIt.next();
				if(assessVersionObj.getAssessmentId().equalsIgnoreCase(forceFieldForm.getAssessmentId()))
				{
					forceFieldForm.setVersionId(assessVersionObj.getVersionNumber());
					AssessmentVersion latestAssessment = (AssessmentVersion)versionList.get(versionList.size()-1);
					if(latestAssessment.getAssessmentId().equalsIgnoreCase(forceFieldForm.getAssessmentId()))
					{
						forceFieldForm.setIslatestVersionShown(true);
					}
					else
					{
						forceFieldForm.setIslatestVersionShown(false);
					}
				}
			}
		}
		
	   	return aMapping.findForward(forward);
	}//end of viewDetailsAfterSCSInterview()
	

	
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
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::viewVersion()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
		
		forceFieldForm.setAssessmentDate(null);
		forceFieldForm.setAssessmentDateStr("");
		forceFieldForm.setAssessmentId("");
		forceFieldForm.setVersionId("");
		forceFieldForm.setIslatestVersionShown(false);
		ArrayList emptyList = new ArrayList();
		forceFieldForm.setForceFieldQuestionsList(emptyList);
		
		forceFieldForm.setAction(UIConstants.ASSESSMENT_VERSION_DETAILS);
		forceFieldForm.setSecondaryAction("");
		
		ArrayList versionDetailsList = (ArrayList)forceFieldForm.getVersionDetailsList();
		if(versionDetailsList.size() > 0)
		{
			Iterator it = versionDetailsList.iterator();
			while(it.hasNext())
			{
				AssessmentVersion assessmentVerObj = (AssessmentVersion)it.next();
				if(assessmentVerObj.getVersionNumber().equalsIgnoreCase(forceFieldForm.getSelectedVersionNumber()))
				{
					forceFieldForm.setAssessmentDate(assessmentVerObj.getAssessmentDate());
					forceFieldForm.setAssessmentDateStr(assessmentVerObj.getAssessmentDateStr());
					forceFieldForm.setAssessmentId(assessmentVerObj.getAssessmentId());
					forceFieldForm.setVersionId(assessmentVerObj.getVersionNumber());
					break;
				}
			}
			
			String latestVer = ((AssessmentVersion)versionDetailsList.get(0)).getVersionNumber();
			if(forceFieldForm.getVersionId().equalsIgnoreCase(latestVer))
			{
				forceFieldForm.setIslatestVersionShown(true);
			}
		}
		
		String forward=UIConstants.VIEW_SUCCESS;
		
		GetAssessmentDetailsEvent assessmentDetailsReqEvt = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsReqEvt);    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
			if(forceFieldForm.isAfterSCSOperation())
			{
				forceFieldForm.setAfterSCSOperation(false);
				return aMapping.findForward("afterSCSFailure");
			}
			else
			if(forceFieldForm.isAfterSCSIntrvwOperation())
			{
				forceFieldForm.setAfterSCSIntrvwOperation(false);
				return aMapping.findForward("afterSCSFailure");
			}	
			return aMapping.findForward("versionfailure");
		}
		
//		get the Assessment Details for the latest version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
		if(assessmentDetailsRespEvt==null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, FOrce Field Assessment details could not be retrieved");
			if(forceFieldForm.isAfterSCSOperation())
			{
				forceFieldForm.setAfterSCSOperation(false);
				return aMapping.findForward("afterSCSFailure");
			}
			else
			if(forceFieldForm.isAfterSCSIntrvwOperation())
			{
				forceFieldForm.setAfterSCSIntrvwOperation(false);
				return aMapping.findForward("afterSCSFailure");
			}	
			return aMapping.findForward("versionfailure");
		}
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		

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
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::update()::Method Begin");
		
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward delete(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplayForceFieldDetailsAction::delete()::Method Begin");
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)aForm;
		
		forceFieldForm.setAction(UIConstants.DELETE); 
		forceFieldForm.setSecondaryAction(UIConstants.SUMMARY);
		
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}

}//end of Class

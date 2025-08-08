/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Collection;
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

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.AssessmentVersion;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplaySCSDetailsAction extends JIMSBaseAction{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.viewDetails","viewDetails");
		keyMap.put("button.viewVersion","viewVersion");
		keyMap.put("button.viewForceFieldAnalysis","viewForceField" );
		keyMap.put("button.correctInitial","update");
		keyMap.put("button.delete","delete");
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
	public ActionForward viewDetails(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		System.out.println("AdministerAssessments::DisplaySCSDetailsAction::viewDetails()::Method Begin");
		String taskId = "";
		SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;
		if (StringUtils.isNotEmpty(scsForm.getTaskId())){
			taskId = scsForm.getTaskId();
		}
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	  
	    scsForm.clear();
	    scsForm.setAction(UIConstants.VIEW_DETAIL);
	    scsForm.setSecondaryAction("");
	    scsForm.setDefendantId(assessmentForm.getDefendantId());
	    scsForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
		scsForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    scsForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
	    
	    scsForm.setSupervisionPeriod(scsForm.getSelectedAssessSupervisionPrd());
	    scsForm.setTaskId(taskId);
	    String assessmentBeanId = scsForm.getSelectedAssessmentBeanId();
	    scsForm.clearSelected();
	    
	    AssessmentLightBean assessmentBeanObj = new AssessmentLightBean();
		AssessmentLightBean ffAssessmentBeanObj = null;
		
	   	assessmentBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, assessmentBeanId, CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
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
	    
    	scsForm.setMasterAssessmentId(assessmentBeanObj.getMasterAssessmentId());
    	scsForm.setAssessmentId(assessmentBeanObj.getAssessmentId());
    	scsForm.setActualAssessmentDate(assessmentBeanObj.getActualAssessmentDate());
    	scsForm.setMigrated(assessmentBeanObj.getMigrated());
    	
    	if(!assessmentBeanObj.isStatusComplete())
    	{
    		scsForm.setAssessmentIncomplete(true);
    	}
    	
    	scsForm.setForceFieldAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
    	scsForm.setForceFieldAssessmentStatus(assessmentBeanObj.getForceFieldAssessmentStatus());
    	if(ffAssessmentBeanObj != null)
    	{
	    	if(!ffAssessmentBeanObj.isForceFieldStatusComplete())
	    	{
	    		scsForm.setForceFieldAssessmentIncomplete(true);
	    	}
	    	
	//    	set force field variables
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
    	
    	String forward=UIConstants.VIEW_SUCCESS;
    	
//    	get the GetAssessmentDetailsEvent Request Event
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(scsForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
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
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected SCS Assessment details could not be retrieved");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCS(scsForm, assessmentDetailsRespEvt);		
		AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(scsForm, priorVersionList);
		
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
		System.out.println("AdministerAssessments::DisplaySCSDetailsAction::viewVersion()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;
		
		scsForm.setAssessmentDate(null);
		scsForm.setAssessmentDateStr("");
		scsForm.setAssessmentId("");
		scsForm.setVersionId("");
		scsForm.setIslatestVersionShown(false);
		ArrayList emptyList = new ArrayList();
		scsForm.setScsScreenOneQuestionsList(emptyList);
		scsForm.setScsScreenTwoQuestionsList(emptyList);
		scsForm.setScsScreenThreeQuestionsList(emptyList);
		scsForm.setScsScreenFourQuestionsList(emptyList);
		scsForm.setScsSummaryQuestionsList(emptyList);
		
		scsForm.setAction(UIConstants.ASSESSMENT_VERSION_DETAILS);
		scsForm.setSecondaryAction("");
		
		ArrayList versionDetailsList = (ArrayList)scsForm.getVersionDetailsList();
		if(versionDetailsList.size() > 0)
		{
			Iterator it = versionDetailsList.iterator();
			while(it.hasNext())
			{
				AssessmentVersion assessmentVerObj = (AssessmentVersion)it.next();
				if(assessmentVerObj.getVersionNumber().equalsIgnoreCase(scsForm.getSelectedVersionNumber()))
				{
					scsForm.setAssessmentDate(assessmentVerObj.getAssessmentDate());
					scsForm.setAssessmentDateStr(assessmentVerObj.getAssessmentDateStr());
					scsForm.setAssessmentId(assessmentVerObj.getAssessmentId());
					scsForm.setVersionId(assessmentVerObj.getVersionNumber());
					break;
				}
			}
			String latestVer = ((AssessmentVersion)versionDetailsList.get(0)).getVersionNumber();
			if(scsForm.getVersionId().equalsIgnoreCase(latestVer))
			{
				scsForm.setIslatestVersionShown(true);
			}
		}
		
		String forward=UIConstants.VIEW_SUCCESS;
		
		GetAssessmentDetailsEvent assessmentDetailsReqEvt = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(scsForm);
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
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected SCS Assessment version details could not be retrieved");
			return aMapping.findForward("versionfailure");	
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCS(scsForm, assessmentDetailsRespEvt);		

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
		System.out.println("AdministerAssessments::DisplaySCSDetailsAction::update()::Method Begin");
		
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
		System.out.println("AdministerAssessments::DisplaySCSDetailsAction::delete()::Method Begin");
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;		
		scsForm.setAction(UIConstants.DELETE); 
		scsForm.setSecondaryAction(UIConstants.SUMMARY);		
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewForceField(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) 
	{
		System.out.println("AdministerAssessments::DisplaySCSDetailsAction::viewForceField()::Method Begin");
		
		return aMapping.findForward("viewForceFieldSuccess");
	}
	
}//end of Class

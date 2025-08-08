/*
 * Created on Jun 23, 2009
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
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;

/**
 * @author cc_bjangay
 *
 */
public class DisplaySCSInterviewDetailsAction extends JIMSBaseAction
{

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	@Override
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewDetailsAction::viewDetails()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)aForm;
	    AssessmentForm assessmentForm = (AssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_ASSESSMENT_FORM, true);
	  
	    scsInterviewForm.clear();
	    scsInterviewForm.setAction(UIConstants.VIEW_DETAIL);
	    scsInterviewForm.setSecondaryAction("");
	    scsInterviewForm.setDefendantId(assessmentForm.getDefendantId());
	    scsInterviewForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    scsInterviewForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    scsInterviewForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW);
	    
	    scsInterviewForm.setSupervisionPeriod(scsInterviewForm.getSelectedAssessSupervisionPrd());	    
	    String assessmentBeanId = scsInterviewForm.getSelectedAssessmentBeanId();
	    scsInterviewForm.clearSelected();
	    
	    AssessmentLightBean assessmentBeanObj = new AssessmentLightBean();
	    AssessmentLightBean ffAssessmentBeanObj = null;
	    
	   	assessmentBeanObj = AdminAssessmentsHelper.getAssessmentBean(assessmentForm, assessmentBeanId, CSCAssessmentConstants.ASSESSMENT_SCS_ASSESSMENT_LIST);
	    if(assessmentBeanObj==null)
	    {
	    	sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Unable to display the details of selected SCS Interview Assessment");
			return aMapping.findForward(UIConstants.FAILURE);
	    }
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
	    
	    scsInterviewForm.setMasterAssessmentId(assessmentBeanObj.getMasterAssessmentId());
	    scsInterviewForm.setAssessmentId(assessmentBeanObj.getAssessmentId());
	    scsInterviewForm.setActualAssessmentDate(assessmentBeanObj.getActualAssessmentDate());
	    scsInterviewForm.setMigrated(assessmentBeanObj.getMigrated());
    	
    	if(!assessmentBeanObj.isStatusComplete())
    	{
    		scsInterviewForm.setAssessmentIncomplete(true);
    	}
    	
    	scsInterviewForm.setForceFieldAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
    	scsInterviewForm.setForceFieldAssessmentStatus(assessmentBeanObj.getForceFieldAssessmentStatus());
    	if(ffAssessmentBeanObj != null)
    	{
	    	if(!ffAssessmentBeanObj.isForceFieldStatusComplete())
	    	{
	    		scsInterviewForm.setForceFieldAssessmentIncomplete(true);
	    	}
	    	
	//    	set force field variables
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
    	
    	String forward=UIConstants.VIEW_SUCCESS;
    	
//    	get the GetAssessmentDetailsEvent Request Event
	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(scsInterviewForm);
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
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected SCS Interview Assessment details could not be retrieved");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCSInterview(scsInterviewForm, assessmentDetailsRespEvt);		
		AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(scsInterviewForm, priorVersionList);
		
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewDetailsAction::viewVersion()::Method Begin");
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)aForm;
		
		scsInterviewForm.setAssessmentDate(null);
		scsInterviewForm.setAssessmentDateStr("");
		scsInterviewForm.setAssessmentId("");
		scsInterviewForm.setVersionId("");
		scsInterviewForm.setIslatestVersionShown(false);
		ArrayList emptyList = new ArrayList();
		scsInterviewForm.setScsIntrvScreenOneQuestionsList(emptyList);
		scsInterviewForm.setScsIntrvScreenTwoQuestionsList(emptyList);
		scsInterviewForm.setScsIntrvScreenThreeQuestionsList(emptyList);
		scsInterviewForm.setScsIntrvScreenFourQuestionsList(emptyList);
		scsInterviewForm.setScsIntrvScreenFiveQuestionsList(emptyList);
		scsInterviewForm.setScsIntrvScreenSixQuestionsList(emptyList);
		scsInterviewForm.setScsSummaryQuestionsList(emptyList);
		
		scsInterviewForm.setAction(UIConstants.ASSESSMENT_VERSION_DETAILS);
		scsInterviewForm.setSecondaryAction("");
		
		ArrayList versionDetailsList = (ArrayList)scsInterviewForm.getVersionDetailsList();
		if(versionDetailsList.size() > 0)
		{
			Iterator it = versionDetailsList.iterator();
			while(it.hasNext())
			{
				AssessmentVersion assessmentVerObj = (AssessmentVersion)it.next();
				if(assessmentVerObj.getVersionNumber().equalsIgnoreCase(scsInterviewForm.getSelectedVersionNumber()))
				{
					scsInterviewForm.setAssessmentDate(assessmentVerObj.getAssessmentDate());
					scsInterviewForm.setAssessmentDateStr(assessmentVerObj.getAssessmentDateStr());
					scsInterviewForm.setAssessmentId(assessmentVerObj.getAssessmentId());
					scsInterviewForm.setVersionId(assessmentVerObj.getVersionNumber());
					break;
				}
			}
			String latestVer = ((AssessmentVersion)versionDetailsList.get(0)).getVersionNumber();
			if(scsInterviewForm.getVersionId().equalsIgnoreCase(latestVer))
			{
				scsInterviewForm.setIslatestVersionShown(true);
			}
		}
		
		String forward=UIConstants.VIEW_SUCCESS;
		
		GetAssessmentDetailsEvent assessmentDetailsReqEvt = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(scsInterviewForm);
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
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected SCS Interview Assessment version details could not be retrieved");
			return aMapping.findForward("versionfailure");	
		}
		
	    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCSInterview(scsInterviewForm, assessmentDetailsRespEvt);		

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
		System.out.println("AdministerAssessments::DisplaySCSInterviewDetailsAction::update()::Method Begin");
		
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewDetailsAction::delete()::Method Begin");
		
		if (aForm instanceof SCSInterviewAssessmentForm){
			SCSInterviewAssessmentForm scsIForm = (SCSInterviewAssessmentForm)aForm;
			scsIForm.setAction(UIConstants.DELETE); 
			scsIForm.setSecondaryAction(UIConstants.SUMMARY);		
		
		}
		if (aForm instanceof SCSAssessmentForm){
			SCSAssessmentForm scsForm = (SCSAssessmentForm)aForm;
			scsForm.setAction(UIConstants.DELETE); 
			scsForm.setSecondaryAction(UIConstants.SUMMARY);		
		
		}
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
		System.out.println("AdministerAssessments::DisplaySCSInterviewDetailsAction::viewForceField()::Method Begin");
		
		return aMapping.findForward("viewForceFieldSuccess");
	}
	
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\commonfunctionality\\spnsplit\\action\\HandleSpnSplitTopicSelectionDetailsAction.java

package ui.commonfunctionality.spnsplit.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.GetAssessmentDetailsEvent;
import messaging.administerassessments.reply.AssessmentDetailsResponseEvent;
import messaging.administerassessments.reply.PriorAssessmentVersionResponseEvent;
import messaging.administersupervisionplan.GetSupervisionPlanDetailsEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AssessmentControllerServiceNames;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administerassessments.form.LSIRAssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;
import ui.supervision.administersupervisionplan.AdminSupervisionPlanHelper;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;
import ui.supervision.manageassociate.action.HandleAssociateDisplayOptionsAction;
import ui.supervision.manageassociate.form.AssociateForm;


public class HandleSpnSplitTopicSelectionDetailsAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 4561E28602A4
    */
   public HandleSpnSplitTopicSelectionDetailsAction() 
   {
   }

   /**
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    * @return Map
    */
	protected void addButtonMapping(Map keyMap) {
	       keyMap.put("button.view", "view");
	}
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
			throws GeneralFeedbackMessageException {
		String forwardStr = UIConstants.FAILURE;
		// common parameter and required
		String detailTypeStr = (String)aRequest.getParameter("Type");
		if (detailTypeStr == null || detailTypeStr.equals("")) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to find supervisee information");
			return aMapping.findForward(forwardStr);
		}
		// associate parameter		
		String spnStr = (String)aRequest.getParameter("SPN");
//		spn, defendantId and superviseeId are all same value
		if (detailTypeStr.equalsIgnoreCase(UIConstants.ASSOCIATE) || detailTypeStr.equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)) {
			if (spnStr != null && !spnStr.equals("")) {
				while (spnStr.length() < 8) {
					spnStr = "0" + spnStr;
				}
			} else {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to find supervisee information");
				return aMapping.findForward(forwardStr);
			}	
		}

		if (detailTypeStr.equalsIgnoreCase(UIConstants.ASSESSMENT)){
			String assessmentType = (String)aRequest.getParameter("assessmentType");
			String assessmentId = (String)aRequest.getParameter("assessmentId"); 
			String masterAssessmentId = (String)aRequest.getParameter("masterAssessmentId");
			String assessmentSupervisionPeriod = (String)aRequest.getParameter("assessSupervisionPrd");			
			if (assessmentType == null || assessmentType.equals("") ||
			    assessmentId == null || assessmentId.equals("") || 
			    masterAssessmentId == null || masterAssessmentId.equals("") ||
			    assessmentSupervisionPeriod == null || assessmentSupervisionPeriod.equals("")) { 
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to find supervisee information");
					return aMapping.findForward(forwardStr);
			}
			if (assessmentType.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_WISCONSIN)){
				WisconsinAssessmentForm wForm = (WisconsinAssessmentForm)getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_WISC_ASSESSMENT_FORM,true);
				wForm.clearAll();
				wForm.setWisconsinAssessmentType(CSCAssessmentConstants.ASSESSMENT_INITIAL_ASSESSMENT);
				this.findWisconsinAssessmentInfo(wForm, spnStr, assessmentType, assessmentId, assessmentSupervisionPeriod, masterAssessmentId, aRequest);
				forwardStr = UIConstants.WISCONSIN_SUCCESS;
			}
			if (assessmentType.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD)){
				ForceFieldAssessmentForm ffForm = (ForceFieldAssessmentForm)getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
				ffForm.clearAll();
				this.findForceFieldAssessmentInfo(ffForm, spnStr, assessmentType, assessmentId, assessmentSupervisionPeriod, masterAssessmentId, aRequest);
				forwardStr = UIConstants.FORCEFIELD_SUCCESS;
			}
			if (assessmentType.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS)){
				SCSAssessmentForm scsForm = (SCSAssessmentForm)getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_SCS_ASSESSMENT_FORM,true);
				scsForm.clearAll();
				scsForm.setAction(UIConstants.VIEW_DETAIL);
				this.findSCSAssessmentInfo(scsForm, spnStr, assessmentType, assessmentId, assessmentSupervisionPeriod, masterAssessmentId, aRequest);
				forwardStr = UIConstants.SCS_SUCCESS;
			}
			if (assessmentType.equalsIgnoreCase(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR)){
				LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_LSIR_ASSESSMENT_FORM,true);
				lsirForm.clearAll();
				this.findLSIRAssessmentInfo(lsirForm, spnStr, assessmentType, assessmentId, assessmentSupervisionPeriod, masterAssessmentId, aRequest);
				forwardStr = UIConstants.LSIR_SUCCESS;
			}
		}else if (detailTypeStr.equalsIgnoreCase(UIConstants.SUPERVISION_PLAN)){
			// supervision plan parameters
				String supervisionPlanId = (String)aRequest.getParameter("selectedSupervisionPlanId");
				if (supervisionPlanId == null || supervisionPlanId.equals("") ){
					sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to find supervisee information");
					return aMapping.findForward(forwardStr);
				}	
				SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)getSessionForm(aMapping, aRequest, UIConstants.CSC_SUPERVISION_PLAN_FORM, true);
				supervisionPlanForm.clearAll();
				this.findSupervisionPlanInfo(supervisionPlanForm, supervisionPlanId, spnStr, aRequest);
				forwardStr = UIConstants.SUPERVISION_SUCCESS;
			}else if (detailTypeStr.equalsIgnoreCase(UIConstants.ASSOCIATE)){
					AssociateForm associateForm = (AssociateForm)getSessionForm(aMapping,aRequest,"associateForm",true);
					associateForm.clear();
					HandleAssociateDisplayOptionsAction handleADOA = new HandleAssociateDisplayOptionsAction();
					handleADOA.populateAssociateForm(associateForm, spnStr);
					forwardStr = UIConstants.ASSOCIATE_SUCCESS;
			} else {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to find supervisee information");
			}
		return aMapping.findForward(forwardStr);
	}   
	
	public void findWisconsinAssessmentInfo(WisconsinAssessmentForm wForm, String defendantId, String assessmentTypeId, String assessmentId, String assmntSupervisionPeriod, String masterAssessmentId ,HttpServletRequest aRequest)
	{
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTDETAILS);
		
		assessmentDetailsEvent.setDefendantId(defendantId);
		assessmentDetailsEvent.setAssessmentTypeId(assessmentTypeId);
		assessmentDetailsEvent.setMasterAssessmentId(masterAssessmentId);
		assessmentDetailsEvent.setAssessmentId(assessmentId);
		// set retrieval constants
		assessmentDetailsEvent.setAssessmentCreate(false);
		assessmentDetailsEvent.setAssessmentUpdate(false);
		assessmentDetailsEvent.setAssessmentDetails(true);
		assessmentDetailsEvent.setRetrievePriorVersion(true);
		assessmentDetailsEvent.setInitial(true);
		assessmentDetailsEvent.setUserID(SecurityUIHelper.getLogonId());
		
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
	    ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
		} else {
// get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
// get the collection of Assessment Prior Versions
			Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
// set values to form
		    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForWisconsin(wForm, assessmentDetailsRespEvt);		
			AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(wForm, priorVersionList);
		}
	}

	public void findForceFieldAssessmentInfo(ForceFieldAssessmentForm forceFieldForm, String defendantId, String assessmentTypeId, String assessmentId, String assmntSupervisionPeriod, String masterAssessmentId ,HttpServletRequest aRequest)
	{
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTDETAILS);
		assessmentDetailsEvent.setDefendantId(defendantId);
		assessmentDetailsEvent.setMasterAssessmentId(masterAssessmentId);
		assessmentDetailsEvent.setAssessmentId(assessmentId);
		assessmentDetailsEvent.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
		assessmentDetailsEvent.setAssessmentCreate(false);
		assessmentDetailsEvent.setAssessmentUpdate(false);
		assessmentDetailsEvent.setAssessmentDetails(true);
		assessmentDetailsEvent.setRetrievePriorVersion(true);
		assessmentDetailsEvent.setInitial(true);
		assessmentDetailsEvent.setUserID(SecurityUIHelper.getLogonId());
		
	    forceFieldForm.setAction(UIConstants.VIEW_DETAIL);
	    forceFieldForm.setSecondaryAction("");
	    forceFieldForm.setAfterSCSOperation(false);
	    forceFieldForm.setDefendantId(defendantId);
	    forceFieldForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
	    forceFieldForm.setSupervisionPeriod(forceFieldForm.getSelectedAssessSupervisionPrd());	    
	  	
    	forceFieldForm.setMasterAssessmentId(masterAssessmentId);
    	forceFieldForm.setAssessmentId(assessmentId);
    	forceFieldForm.setScsMasterAssessmentId(masterAssessmentId);
    	
//	    GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent)AdminAssessmentsHelper.getAssessmentDetailsEvent(forceFieldForm);
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
	    
//		check for any error messages 
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
		} else {
//		get the Assessment Details for the latest version
		AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
		Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
		
		if(assessmentDetailsRespEvt==null || priorVersionList==null){
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, Force Field Assessment details could not be retrieved.");
		} else {
			AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForForceField(forceFieldForm, assessmentDetailsRespEvt);		
			AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(forceFieldForm, priorVersionList);
			}
		}
	}	

	public void findSCSAssessmentInfo(SCSAssessmentForm scsForm, String defendantId, String assessmentTypeId, String assessmentId, String assmntSupervisionPeriod, String masterAssessmentId ,HttpServletRequest aRequest)
	{
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTDETAILS);
		assessmentDetailsEvent.setDefendantId(defendantId);
		assessmentDetailsEvent.setMasterAssessmentId(masterAssessmentId);
		assessmentDetailsEvent.setAssessmentId(assessmentId);
		assessmentDetailsEvent.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS);
		assessmentDetailsEvent.setAssessmentCreate(false);
		assessmentDetailsEvent.setAssessmentUpdate(false);
		assessmentDetailsEvent.setAssessmentDetails(true);
		assessmentDetailsEvent.setRetrievePriorVersion(true);
		assessmentDetailsEvent.setInitial(true);
		assessmentDetailsEvent.setUserID(SecurityUIHelper.getLogonId());
		
    	scsForm.setForceFieldAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_FORCEFIELD);
// need to pass this value, using constant for testing 
    	scsForm.setForceFieldAssessmentStatus(CSCAssessmentConstants.ASSESSMENT_EXIST);
    	if(scsForm.getForceFieldAssessmentStatus().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_EXIST)) {
    		scsForm.setForceFieldMasterAssessId(masterAssessmentId);
    		scsForm.setForceFieldAssessmentId(assessmentId);
    	} else {
    		scsForm.setForceFieldMasterAssessId(null);
    		scsForm.setForceFieldAssessmentId(null);
    	}
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);    
	    ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
		} else {
// get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
// get the collection of Assessment Prior Versions
			Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
// set values to form
		    AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForSCS(scsForm, assessmentDetailsRespEvt);		
			AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(scsForm, priorVersionList);
		}	
	}
	
	public void findLSIRAssessmentInfo(LSIRAssessmentForm lsirForm, String defendantId, String assessmentTypeId, String assessmentId, String assmntSupervisionPeriod, String masterAssessmentId ,HttpServletRequest aRequest)
	{
		GetAssessmentDetailsEvent assessmentDetailsEvent = (GetAssessmentDetailsEvent) EventFactory.getInstance(AssessmentControllerServiceNames.GETASSESSMENTDETAILS);
		assessmentDetailsEvent.setDefendantId(defendantId);
		assessmentDetailsEvent.setMasterAssessmentId(masterAssessmentId);
		assessmentDetailsEvent.setAssessmentId(assessmentId);
		assessmentDetailsEvent.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
		assessmentDetailsEvent.setAssessmentCreate(false);
		assessmentDetailsEvent.setAssessmentUpdate(false);
		assessmentDetailsEvent.setAssessmentDetails(true);
		assessmentDetailsEvent.setRetrievePriorVersion(true);
		assessmentDetailsEvent.setInitial(true);
		assessmentDetailsEvent.setUserID(SecurityUIHelper.getLogonId());
		
	    lsirForm.setAction(UIConstants.VIEW_DETAIL);
	    lsirForm.setSecondaryAction("");
	    lsirForm.setDefendantId(defendantId);
	    lsirForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_LSIR);
	    lsirForm.setSupervisionPeriod(lsirForm.getSelectedAssessSupervisionPrd());
	    lsirForm.setMasterAssessmentId(masterAssessmentId);
	    lsirForm.setAssessmentId(assessmentId);
	    
	    CompositeResponse compResponse = this.postRequestEvent(assessmentDetailsEvent);   
		ErrorResponseEvent error = (ErrorResponseEvent)MessageUtil.filterComposite(compResponse,ErrorResponseEvent.class);
		if(error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, error.getMessage());
		} else {
		
//		get the Assessment Details for the latest version
			AssessmentDetailsResponseEvent assessmentDetailsRespEvt = (AssessmentDetailsResponseEvent)MessageUtil.filterComposite(compResponse, AssessmentDetailsResponseEvent.class);
//		get the collection of Assessment Prior Versions
			Collection priorVersionList =  MessageUtil.compositeToCollection(compResponse, PriorAssessmentVersionResponseEvent.class);
			if(assessmentDetailsRespEvt==null || priorVersionList==null) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "An unknown error was encountered, selected LSI-R Assessment details could not be retrieved");
			} else {
				AdminAssessmentsHelper.populateAssessmentDetailsResponseEventForLSIR(lsirForm, assessmentDetailsRespEvt);		
				AdminAssessmentsHelper.populatePriorAssessmentVersionResponseEvent(lsirForm, priorVersionList);
			}
		}
	}
	
	public void findSupervisionPlanInfo(SupervisionPlanForm  spForm, String spId, String defendantId, HttpServletRequest aRequest)
	{
		spForm.setAction(UIConstants.VIEW);
		spForm.setSupervisionPlanId(spId);
		spForm.setDefendantId(defendantId);
		spForm.setSecondaryAction("spnSplit");
		GetSupervisionPlanDetailsEvent supPlanDetailsEvent = (GetSupervisionPlanDetailsEvent)AdminSupervisionPlanHelper.getSupervisionPlanDetailsEvent(spForm);
		
		CompositeResponse compositeResponse = this.postRequestEvent(supPlanDetailsEvent);
		SupervisionPlanDetailsResponseEvent supervisionPlanDetailsRespEvent = (SupervisionPlanDetailsResponseEvent)MessageUtil.filterComposite(compositeResponse, SupervisionPlanDetailsResponseEvent.class);
		if(supervisionPlanDetailsRespEvent == null) {
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Failed to retrieve selected Supervision Plan Details");
		} else {
			AdminSupervisionPlanHelper.populateSupervisionPlanDetailsResponseEvent(spForm, supervisionPlanDetailsRespEvent);
		}
	}
}
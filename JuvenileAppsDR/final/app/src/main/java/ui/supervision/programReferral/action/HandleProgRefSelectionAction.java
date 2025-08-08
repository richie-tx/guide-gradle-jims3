/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetProgramLocationsEvent;
import messaging.administerprogramreferrals.GetProgramReferralEvent;
import messaging.administerprogramreferrals.GetReferralFormsEvent;
import messaging.administerprogramreferrals.GetSuperviseeActiveCasesEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.administerprogramreferrals.reply.ReferralFormResponseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeCaseResponseEvent;
import messaging.csserviceprovider.reply.CSProgramLocationResponseEvent;
import naming.CSAdministerProgramReferralsConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.programReferral.CSCCaseInfoBean;
import ui.supervision.programReferral.CSCProgRefSearchBean;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.programReferral.form.CSCSearchProgRefForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleProgRefSelectionAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.initiateNewReferral","initiateNewReferral");
		keyMap.put("button.update","updateReferral");
		keyMap.put("button.view","viewDetails");
		keyMap.put("button.submitReferral","submitReferral");
		keyMap.put("button.delete","deleteReferral");
		keyMap.put("button.exitReferral","exitReferral");
		keyMap.put("button.removeEntry","removeEntry");
		keyMap.put("button.removeExit","removeExit");
		keyMap.put("button.reReferral","reReferral");
		keyMap.put("button.generateForm","generateForm");
		keyMap.put("button.createCasenote","createCasenote");
		keyMap.put("button.printPacket","printPacket");
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
	public ActionForward createCasenote(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_CREATE_CASENOTE);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
// needed for casenote spellcheck		
		progReferralForm.setUserAgency(SecurityUIHelper.getUserAgencyId());  
		return aMapping.findForward(CSAdministerProgramReferralsConstants.CREATE_CASENOTE_SUCCESS);
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
	public ActionForward generateForm(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the referral forms for the referral Type Code
		GetReferralFormsEvent referralFormsEvt = new GetReferralFormsEvent();
		referralFormsEvt.setReferralTypeCd(progReferralForm.getReferralTypeCode());
		List  referralFormsList = postRequestListFilter(referralFormsEvt, ReferralFormResponseEvent.class);
		CSCProgRefUIHelper.convertResponseEvtToReferralFormBean(progReferralForm, referralFormsList);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.GENERATE_FORM_SUCCESS);
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
	public ActionForward reReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		GetProgramLocationsEvent prog_loc_event = CSCProgRefUIHelper.getProgramLocationsEventForReferral(progReferralForm);
		List sp_prog_loc_response = this.postRequestListFilter(prog_loc_event,CSProgramLocationResponseEvent.class);
		CSCProgRefUIHelper.convertProgLocRespEvtToBeanForRereferal(progReferralForm, sp_prog_loc_response);
		
		progReferralForm.setUserAgency(SecurityUIHelper.getUserAgencyId());
		if (progReferralForm.getUserAgency() == null){
			progReferralForm.setUserAgency("");
		}
		return aMapping.findForward(CSAdministerProgramReferralsConstants.RE_REFERRAL_SUCCESS);
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
	public ActionForward removeExit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.REMOVE_EXIT_SUCCESS);
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
	public ActionForward removeEntry(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.REMOVE_ENTRY_SUCCESS);
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
	public ActionForward exitReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_EXITREFERRAL);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);

		progReferralForm.setUserAgency(SecurityUIHelper.getUserAgencyId());
		if (progReferralForm.getUserAgency() == null){
			progReferralForm.setUserAgency("");
		}		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.EXIT_REFERRAL_SUCCESS);
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
	public ActionForward deleteReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_DELETE);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
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
	public ActionForward submitReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);

		progReferralForm.setUserAgency(SecurityUIHelper.getUserAgencyId());
		if (progReferralForm.getUserAgency() == null){
			progReferralForm.setUserAgency("");
		}
		return aMapping.findForward(CSAdministerProgramReferralsConstants.SUBMIT_REFERRAL_SUCCESS);
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
	public ActionForward viewDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW);
		progReferralForm.setProgRefId(selectedProgRefId);
		
		//retrieve selected program referral
		GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
		get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
		get_referral_event.setViewDetails(true);
		CSProgramReferralResponseEvent prog_ref_response = 
			(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
		CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		
//		retrieve the order conditions
		CSCProgRefUIHelper.populateOrderConditions(progReferralForm);
		
//		set the supervisee header info
		CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, progReferralForm, superviseeHeaderForm);
		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
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
	public ActionForward initiateNewReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		
		progReferralForm.clearAll();
		
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_CREATE);
		
		progReferralForm.setCasesAvailableList(new ArrayList());
		progReferralForm.setSelectedValue("");
		
		GetSuperviseeActiveCasesEvent myEvent=new GetSuperviseeActiveCasesEvent();
		myEvent.setDefendantId(progReferralForm.getSpn());
		
		List myRespList=postRequestListFilter(myEvent,SuperviseeCaseResponseEvent.class);
		
		if(myRespList!=null && myRespList.size()>0)
		{
			for(int loopX=0;loopX<myRespList.size(); loopX++)
			{
				SuperviseeCaseResponseEvent myTempEvent=(SuperviseeCaseResponseEvent)myRespList.get(loopX);
				CSCCaseInfoBean myBean= CSCProgRefUIHelper.convertSuperviseeCaseResponseTOCSCCaseInfoBean(myTempEvent);
				progReferralForm.getCasesAvailableList().add(myBean);
			}
		}
		
//		if no active cases available for the defendant
		if(progReferralForm.getCasesAvailableList().size()==0)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "No active cases exist for the defendant");
			return aMapping.findForward(UIConstants.CREATE_SUCCESS);
		}
		
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
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
	public ActionForward updateReferral(ActionMapping aMapping,	ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			CSCProgRefForm prg_ref_form = (CSCProgRefForm)aForm;
			
			String forward = "";
			
			String selectedProgRefId = prg_ref_form.getSelectedValue();
			prg_ref_form.clearAll();
			
			prg_ref_form.setProgRefId(selectedProgRefId);
			
			//initialize event for retrieving specified program referral
			GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
			get_referral_event.setProgramReferralId(prg_ref_form.getProgRefId());
			get_referral_event.setViewDetails(true);
			
				//retrieve selected program referral
			CSProgramReferralResponseEvent prog_ref_response = 
				(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
			CSCProgRefUIHelper.populateProgReferralForm(prg_ref_form, prog_ref_response);
			
			if(prg_ref_form.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS))
			{
				prg_ref_form.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT);
				
				//retrieve active cases for given supervisee
				GetSuperviseeActiveCasesEvent myEvent = new GetSuperviseeActiveCasesEvent();
				myEvent.setDefendantId(prg_ref_form.getSpn());
				
				List myRespList=postRequestListFilter(myEvent,SuperviseeCaseResponseEvent.class);
				if(myRespList!=null && myRespList.size()>0)
				{
					for(int loopX=0;loopX<myRespList.size(); loopX++)
					{
						SuperviseeCaseResponseEvent myTempEvent=(SuperviseeCaseResponseEvent)myRespList.get(loopX);
						CSCCaseInfoBean myBean = CSCProgRefUIHelper.convertSuperviseeCaseResponseTOCSCCaseInfoBean(myTempEvent);
						prg_ref_form.getCasesAvailableList().add(myBean);
					}
				}
				
				forward = UIConstants.CREATE_SUCCESS;
			}
//			if the referral is open or exited
			else 
			{
				CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
				SuperviseeHeaderForm superviseeHeaderForm = (SuperviseeHeaderForm)this.getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
				
//			    retrieve the order conditions
				CSCProgRefUIHelper.populateOrderConditions(prg_ref_form);
				
//				set the supervisee header info
				CSCProgRefUIHelper.setSuperviseeHeaderInfo(caseAssignForm, prg_ref_form, superviseeHeaderForm);
				
				if(prg_ref_form.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS))
				{
					prg_ref_form.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT);
					forward = CSAdministerProgramReferralsConstants.SUBMIT_REFERRAL_SUCCESS;
				}
				else
				{
					prg_ref_form.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT);
					forward = CSAdministerProgramReferralsConstants.EXIT_REFERRAL_SUCCESS;
				}
			}
			prg_ref_form.setUserAgency(SecurityUIHelper.getUserAgencyId());
			if (prg_ref_form.getUserAgency() == null){
				prg_ref_form.setUserAgency("");
			}
			return aMapping.findForward(forward);
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
	public ActionForward printPacket(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		CSCSearchProgRefForm progReferralSearchForm = (CSCSearchProgRefForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_PROGRAM_REFERRAL_SEARCH_FORM, true);
		for (int x=0; x<progReferralSearchForm.getFilteredProgReferralsList().size(); x++){
			CSCProgRefSearchBean prSearchBean = (CSCProgRefSearchBean) progReferralSearchForm.getFilteredProgReferralsList().get(x);
			if (prSearchBean.getReferralId().equals(progReferralForm.getSelectedValue())){
				progReferralForm.setCriminalCaseId(prSearchBean.getCriminalCaseId());
				break;
			}
		}
		progReferralForm.setPrintPacketFlow(true);
		progReferralForm.setAction(UIConstants.CREATE);
		return aMapping.findForward(UIConstants.PRINT_SUCCESS);
	}	
}

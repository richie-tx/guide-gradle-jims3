/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.Collection;
import java.util.Iterator;
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
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.programReferral.CSCCaseInfoBean;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.CSProgRefCaseloadBean;
import ui.supervision.programReferral.form.CSCProgRefCaseloadForm;
import ui.supervision.programReferral.form.CSCProgRefForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;


/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class HandleProgRefDetailsAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view","viewDetails");
		keyMap.put("button.update","updateReferral");
		keyMap.put("button.submitReferral","submitReferral");
		keyMap.put("button.reReferral","reReferral");
		keyMap.put("button.delete","deleteReferral");
		keyMap.put("button.exitReferral","exitReferral");
		keyMap.put("button.removeEntry","removeEntry");
		keyMap.put("button.removeExit","removeExit");
		keyMap.put("button.reReferral","reReferral");
		keyMap.put("button.generateForm","generateForm");
		keyMap.put("button.createCasenote","createCasenote");
		keyMap.put("button.finish","finish");
		keyMap.put("button.viewPgmReferral","viewProgramRefDetailsLink");
		
	}
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 *//*
	public ActionForward viewProgramRefDetailsLink(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW);
		
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
	}*/
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewProgramRefDetailsLink(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.clearDefendantId();
		progReferralForm.clearAll();
		
		CaseAssignmentForm caseAssignForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		
		CSCProgRefCaseloadForm progRefCaseloadForm = (CSCProgRefCaseloadForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_PROGRAM_REFERRAL_CASELOAD_FORM, true);
		String selectedProgRefId = progRefCaseloadForm.getSelectedId();
		progRefCaseloadForm.setSelectedId("");
		Collection progRefList = progRefCaseloadForm.getReferralsList();
		String superviseeId = null;
		Iterator iter = progRefList.iterator();
		while(iter.hasNext())
		{
			CSProgRefCaseloadBean referralBean = (CSProgRefCaseloadBean)iter.next();
			if(referralBean.getProgramReferralId().equalsIgnoreCase(selectedProgRefId))
			{
				superviseeId = referralBean.getDefendantId();
				break;
			}
		}
		
		SuperviseeHeaderForm superviseeHeaderForm =(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		superviseeHeaderForm.setSuperviseeId(superviseeId);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(superviseeHeaderForm);
		
		progReferralForm.setSpn(superviseeId);
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
	public ActionForward createCasenote(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_CREATE_CASENOTE);

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
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_GENERATE_FORM);
		
//		retrieve the referral forms for the referral Type Code
		GetReferralFormsEvent referralFormsEvt = new GetReferralFormsEvent();
		referralFormsEvt.setReferralTypeCd(progReferralForm.getReferralTypeCode());
		List  referralFormsList = postRequestListFilter(referralFormsEvt, ReferralFormResponseEvent.class);
		CSCProgRefUIHelper.convertResponseEvtToReferralFormBean(progReferralForm, referralFormsList);
		
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
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL);
		
		GetProgramLocationsEvent prog_loc_event = CSCProgRefUIHelper.getProgramLocationsEventForReferral(progReferralForm);
		List sp_prog_loc_response = this.postRequestListFilter(prog_loc_event,CSProgramLocationResponseEvent.class);
		CSCProgRefUIHelper.convertProgLocRespEvtToBeanForRereferal(progReferralForm, sp_prog_loc_response);

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
	public ActionForward updateReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		String forward = "";
		
		if(progReferralForm.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.INITIATED_REFERRAL_STATUS))
		{
			progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_INIT);
			
			//retrieve active cases for given supervisee
			GetSuperviseeActiveCasesEvent myEvent = new GetSuperviseeActiveCasesEvent();
			myEvent.setDefendantId(progReferralForm.getSpn());
			
			List myRespList=postRequestListFilter(myEvent,SuperviseeCaseResponseEvent.class);
			if(myRespList!=null && myRespList.size()>0)
			{
				for(int loopX=0;loopX<myRespList.size(); loopX++)
				{
					SuperviseeCaseResponseEvent myTempEvent=(SuperviseeCaseResponseEvent)myRespList.get(loopX);
					CSCCaseInfoBean myBean = CSCProgRefUIHelper.convertSuperviseeCaseResponseTOCSCCaseInfoBean(myTempEvent);
					progReferralForm.getCasesAvailableList().add(myBean);
				}
			}
			
			forward = CSAdministerProgramReferralsConstants.UPDATE_INIT_SUCCESS;
		}
//		if the referral is open or exited
		else 
		{
			if(progReferralForm.getReferralStatusCd().equalsIgnoreCase(CSAdministerProgramReferralsConstants.OPEN_REFERRAL_STATUS))
			{
				progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_SUBMIT);
				forward = CSAdministerProgramReferralsConstants.UPDATE_SUBMIT_SUCCESS;
			}
			else
			{
				progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_UPDATE_EXIT);
				forward = CSAdministerProgramReferralsConstants.UPDATE_EXIT_SUCCESS;
			}
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
	public ActionForward removeExit(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REMOVEEXIT);

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
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_REMOVEENTRY);
		
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
		
		String selectedProgRefId = progReferralForm.getProgRefId();
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
	public ActionForward submitReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_SUBMITREFERRAL);
		
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
	public ActionForward deleteReferral(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_DELETE);
		
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
	public ActionForward viewDetails(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		progReferralForm.setAction(CSAdministerProgramReferralsConstants.ACTION_VIEW);
		
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
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		return aMapping.findForward(UIConstants.FINISH);
	}
}

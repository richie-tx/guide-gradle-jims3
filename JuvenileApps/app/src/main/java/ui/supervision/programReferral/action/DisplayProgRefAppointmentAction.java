/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerprogramreferrals.GetAppointmentDetailsEvent;
import messaging.administerprogramreferrals.GetProgramReferralEvent;
import messaging.administerprogramreferrals.GetSuperviseeNOfficerDetailsEvent;
import messaging.administerprogramreferrals.reply.AppointmentDetailsResponseEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.administerprogramreferrals.reply.SuperviseeNOfficerDetailsResponseEvent;
import mojo.km.messaging.EventFactory;
import naming.CSAdministerProgramReferralsConstants;
import naming.CSProgramReferralControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.programReferral.AppointmentInfoBean;
import ui.supervision.programReferral.CSCProgRefUIHelper;
import ui.supervision.programReferral.form.CSCProgRefForm;

/**
 * 
 * @author cc_bjangay
 *
 */
public class DisplayProgRefAppointmentAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "printAppointment");
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
	public ActionForward printAppointment(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCProgRefForm progReferralForm =(CSCProgRefForm)aForm;
		
		String selectedProgRefId = progReferralForm.getSelectedValue();
		
//		clear the required fields
		progReferralForm.setSelectedValue("");
		progReferralForm.setProgRefId("");
		AppointmentInfoBean appointmentInfoBean = new AppointmentInfoBean();
		progReferralForm.setAppointmentInfoBean(appointmentInfoBean);
		
		progReferralForm.setProgRefId(selectedProgRefId);
		
		if(!progReferralForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL))
		{
			//retrieve selected program referral
			GetProgramReferralEvent get_referral_event = new GetProgramReferralEvent();
			get_referral_event.setProgramReferralId(progReferralForm.getProgRefId());
			get_referral_event.setViewDetails(true);
			CSProgramReferralResponseEvent prog_ref_response = 
				(CSProgramReferralResponseEvent)postRequestEvent(get_referral_event, CSProgramReferralResponseEvent.class);
			CSCProgRefUIHelper.populateProgReferralForm(progReferralForm, prog_ref_response);
		}
		
//		populate the Appointment header
		GetSuperviseeNOfficerDetailsEvent reqEvent = (GetSuperviseeNOfficerDetailsEvent)EventFactory.getInstance(CSProgramReferralControllerServiceNames.GETSUPERVISEENOFFICERDETAILS);
		reqEvent.setDefendantId(progReferralForm.getSpn());
		reqEvent.setCriminalCaseId(progReferralForm.getCriminalCaseId());
		SuperviseeNOfficerDetailsResponseEvent headerResponseEvent = (SuperviseeNOfficerDetailsResponseEvent)this.postRequestEvent(reqEvent, SuperviseeNOfficerDetailsResponseEvent.class);
		CSCProgRefUIHelper.populateSuperviseeNOfficerDetails(appointmentInfoBean, headerResponseEvent);
		
		//retrieve the appointment details
		GetAppointmentDetailsEvent appDetailsEvt = new GetAppointmentDetailsEvent();
		appDetailsEvt.setProgramReferralId(progReferralForm.getProgRefId());
		AppointmentDetailsResponseEvent apptResponseEvent = (AppointmentDetailsResponseEvent)this.postRequestEvent(appDetailsEvt, AppointmentDetailsResponseEvent.class);
		
		if(progReferralForm.getAction().equalsIgnoreCase(CSAdministerProgramReferralsConstants.ACTION_REREFERRAL))
		{
			CSCProgRefUIHelper.populateAppointmentDetailsOnReferral(progReferralForm, appointmentInfoBean, apptResponseEvent);
		}
		else
		{
			CSCProgRefUIHelper.populateAppointmentDetails(appointmentInfoBean, apptResponseEvent);
		}
		
		return aMapping.findForward(CSAdministerProgramReferralsConstants.PRINT_APPTMNT_SUCCESS);
	}
	
	

}

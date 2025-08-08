/*
 * Created on Dec 17, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import messaging.contact.user.reply.UserResponseEvent;
import messaging.csserviceprovider.SaveSPContactEvent;
import messaging.csserviceprovider.reply.CSProgramResponseEvent;
import messaging.csserviceprovider.reply.CSServiceProviderResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.PhoneNumber;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderContactForm;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderProgramForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSCContactUpdateAction extends JIMSBaseAction {

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.view","view");
		keyMap.put("button.create","create");
		keyMap.put("button.update","update");
		keyMap.put("button.inactivate","inactivate");
		keyMap.put("button.next","next");
		keyMap.put("button.saveAndContinue","saveNContinue");
		keyMap.put("button.find","find");

	}
	
	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.VIEW);
		myForm.setSecondaryAction("");
		if(loadServiceProviderContactDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedContact(myForm.getContactId());
			setLastAdminContact(myForm,(List)mySPForm.getContacts());
			return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
		}
		else
			return aMapping.findForward("failureDashboard");
	}
	
	private boolean loadServiceProviderContactDetails(ServiceProviderContactForm myForm, String aServiceProvId, HttpServletRequest aRequest){
		String mySelVal=myForm.getSelectedValue();
		myForm.clear();
		myForm.setContactId(mySelVal);
		String msg=AdminServiceProviderHelper.updateContactForm(myForm);
		if(msg!=null && !msg.trim().equals("")){
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,msg);
			return false;
		}
		myForm.setServiceProviderId(aServiceProvId);
		return true;
	}
	
	public ActionForward create(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.clear();
		myForm.setServiceProviderId(mySPForm.getServiceProviderId());
		myForm.setLastAdminContact(false);
		myForm.setAction(UIConstants.CREATE);
		myForm.setSecondaryAction("");
		myForm.setContactStatusId(PDCodeTableConstants.ASP_CS_CONTACT_ACTIVE);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.UPDATE);
		myForm.setSecondaryAction("");
		if(loadServiceProviderContactDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedContact(myForm.getContactId());
			setLastAdminContact(myForm,(List)mySPForm.getContacts());
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		else
			return aMapping.findForward("failureDashboard");
	}
	
	public ActionForward inactivate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)  throws GeneralFeedbackMessageException
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		myForm.setAction(UIConstants.INACTIVATE);
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		if(loadServiceProviderContactDetails(myForm, mySPForm.getServiceProviderId(),aRequest)){
			mySPForm.setSelectedContact(myForm.getContactId());
			setLastAdminContact(myForm,(List)mySPForm.getContacts());
			if(myForm.isLastAdminContact()){
				
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"The last administrative contact cannot be inactivated");
				return aMapping.findForward("failureDashboard");
			}
			else{
				return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
			}
		}
		else
			return aMapping.findForward("failureDashboard");
	}
	
	private void setLastAdminContact(ServiceProviderContactForm myForm ,List contacts){
		String lastAdminContactId=AdminServiceProviderHelper.getLastAdminContact(contacts);
		if(lastAdminContactId!=null && myForm.getContactId()!=null && myForm.getContactId().equals(lastAdminContactId)){
			myForm.setLastAdminContact(true);
		}
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		
		return aMapping.findForward("cancelDashboard");
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUMMARY_SUCCESS);
	}
	
	public ActionForward saveNContinue(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		String forward="successDashboard";
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		ServiceProviderCSCDForm mySPForm=(ServiceProviderCSCDForm)this.getSessionForm(aMapping,aRequest,UIConstants.ASPCSC_SP_FORM,true);
		RequestEvent myEvent=AdminServiceProviderHelper.getContactSaveEvent(myForm);
		if(UIConstants.INACTIVATE.equals(myForm.getAction())){
			((SaveSPContactEvent)myEvent).setStatusCode(PDCodeTableConstants.ASP_CS_CONTACT_INACTIVE);
			((SaveSPContactEvent)myEvent).setStatusChangeDate(new Date());
		}
        CompositeResponse myResp=this.postRequestEvent(myEvent);
        Object obj=MessageUtil.filterComposite(myResp,CSServiceProviderResponseEvent.class);
        if(obj==null){
        	sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error was encountered, Contact may not have been saved");
        	forward=UIConstants.FAILURE;
        }
        else{
        	CSServiceProviderResponseEvent myRespEvt=(CSServiceProviderResponseEvent)obj;
        	if(myRespEvt.isOperationSuccessful()){
        		AdminServiceProviderHelper.updateServiceProviderForm(mySPForm);
        		if(UIConstants.CREATE.equals(myForm.getAction()))
        			aRequest.setAttribute("confirmMsg","Contact successfully created.");
        		else if(UIConstants.UPDATE.equals(myForm.getAction()))
        			aRequest.setAttribute("confirmMsg","Contact successfully updated.");
        		else if(UIConstants.INACTIVATE.equals(myForm.getAction()))
        			aRequest.setAttribute("confirmMsg","Contact successfully inactivated.");
        		myForm.setSecondaryAction(UIConstants.CONFIRM);
        	}
        	else{
        		sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"An unknown error was encountered, Contact may not have been saved");
        		forward=UIConstants.FAILURE;
        	}

        }
        return aMapping.findForward(forward);
	}
	
	public ActionForward find(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ServiceProviderContactForm myForm=(ServiceProviderContactForm)aForm;
		if(myForm.getUserId()!=null && !myForm.getUserId().trim().equals("")){			
			String agencyId=SecurityUIHelper.getUserAgencyId();
			UserResponseEvent myUserRespEvt=SecurityUIHelper.getUser(myForm.getUserId());
			if(myUserRespEvt!=null){
				if(myUserRespEvt.getAgencyId()!=null && myUserRespEvt.getAgencyId().equals(agencyId)){
					myForm.getContactName().setFirstName(myUserRespEvt.getFirstName());
					myForm.getContactName().setMiddleName(myUserRespEvt.getMiddleName());
					myForm.getContactName().setLastName(myUserRespEvt.getLastName());	
					PhoneNumber ph = new PhoneNumber(myUserRespEvt.getPhoneNum());
					ph.setExtension(myUserRespEvt.getPhoneExt());
					myForm.setOfficePhone(ph);
				}
				else{
					sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"UserId is not within the same agency as the service provider");
				}
			}
			else{
				sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Invalid UserId. User could not be found");
			}
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}// END CLASS

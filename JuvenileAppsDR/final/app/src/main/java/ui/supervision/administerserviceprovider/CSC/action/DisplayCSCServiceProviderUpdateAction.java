/*
 * Created on Dec 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administerserviceprovider.CSC.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.contact.domintf.IAddress;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.Address;
import ui.common.UIUtil;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.CSC.AdminServiceProviderHelper;
import ui.supervision.administerserviceprovider.CSC.form.ServiceProviderCSCDForm;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSCServiceProviderUpdateAction extends JIMSBaseAction {
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link","link");
		keyMap.put("button.next","next");	
		keyMap.put("button.submit","submit");	
		keyMap.put("button.updateSP", "updateSP");
		keyMap.put("button.update", "update");
		keyMap.put("button.create", "create");
		keyMap.put("button.view", "view");
		keyMap.put("button.viewDetails", "viewDetails");
		keyMap.put("button.inactivate", "inactivate");
		keyMap.put("button.reset", "reset");
		keyMap.put("button.go", "goBack");
	}	
	
	public ActionForward inactivate(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		myForm.setAction(UIConstants.INACTIVATE);
		myForm.setSecondaryAction("");
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS_SUMMARY);
	}
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{					
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		String forward="";		
		if (!myForm.getCancelPath().equals("")){				
			forward = myForm.getCancelPath();
			myForm.setCancelPath("");
		}else {
			forward = "cancelSearch";
		}
		return aMapping.findForward(forward);
	}	
	
	public ActionForward view(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		String selectedServProvId=myForm.getSelectedValue();
		myForm.clear();
		myForm.setServiceProviderId(selectedServProvId);
		String errMsg=AdminServiceProviderHelper.updateServiceProviderForm(myForm);
		if(errMsg!=null){
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		myForm.setAction(UIConstants.VIEW);
		myForm.setSecondaryAction("");
		return aMapping.findForward("successDashboard");
	}
	
	/**
	 * This method is invoked to view Service Provider details popup window from Program Referrals
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward viewDetails(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		
		String selectedServProvId=myForm.getSelectedValue();
		
		myForm.clear();
		myForm.setServiceProviderId(selectedServProvId);
		
		myForm.setAction(UIConstants.VIEW);
		myForm.setSecondaryAction("");
		
		String errMsg=AdminServiceProviderHelper.updateServiceProviderForm(myForm);
		if(errMsg!=null)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
		}
		
		return aMapping.findForward(UIConstants.DETAIL_SUCCESS);
	}
	
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		if(!myForm.isInHouse()){
			if(myForm.isSameAsMailingAddress()){
				IAddress myBillingAddress=myForm.getBillingAddress();
				IAddress myMailingAddress=myForm.getMailingAddress();
				myBillingAddress.setStreetNum(myMailingAddress.getStreetNum());
				myBillingAddress.setStreetName(myMailingAddress.getStreetName());
				myBillingAddress.setStreetTypeCode(myMailingAddress.getStreetTypeCode());
				myBillingAddress.setAptNum(myMailingAddress.getAptNum());
				myBillingAddress.setCity(myMailingAddress.getCity());
				myBillingAddress.setStateCode(myMailingAddress.getStateCode());
				myBillingAddress.setZipCode(myMailingAddress.getZipCode());
				myBillingAddress.setAdditionalZipCode(myMailingAddress.getAdditionalZipCode());
				myBillingAddress.setCountyCode(myMailingAddress.getCountyCode());
			}
		}
		UIUtil.setAddressDescComponents((Address)myForm.getMailingAddress());
		UIUtil.setAddressDescComponents((Address)myForm.getBillingAddress());
		RequestEvent myEvent=AdminServiceProviderHelper.getServProvAddrValidationEvent(myForm);
        //CompositeResponse myResp=this.postRequestEvent(myEvent);
		// Determine if validation is passed
		// if not send error message about Name not being unique
		// return user to create/update page
		//return aMapping.findForward("failureAddress");
		myForm.setSecondaryAction("");
		
		myForm.setSecondaryAction(UIConstants.SUMMARY);
		return aMapping.findForward(UIConstants.SUCCESS_SUMMARY);
	}
	public ActionForward submit(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		//RequestEvent myEvent=AdminServiceProviderHelper.getServProvNameValidationEvent(myForm);
		myForm.getMailingAddress().setAddressTypeCode(PDCodeTableConstants.ADDRESS_TYPE_MAILING);
		myForm.getBillingAddress().setAddressTypeCode(PDCodeTableConstants.ADDRESS_TYPE_BILLING);
		if(myForm.getAction().equals(UIConstants.CREATE)){
			myForm.getMailingAddress().setStateCode("TX");
			myForm.getBillingAddress().setStateCode("TX");
		}
		UIUtil.setAddressDescComponents((Address)myForm.getMailingAddress());
		UIUtil.setAddressDescComponents((Address)myForm.getBillingAddress());
		String userAgency=SecurityUIHelper.getUserAgencyId();
//		RequestEvent myEvent=AdminServiceProviderHelper.getServProvNameValidationEvent(myForm);
		// Add user agency to request event
        //CompositeResponse myResp=this.postRequestEvent(myEvent);
		// Determine if validation is passed
		// if not send error message about Name not being unique
		// return user to create/update page
		//return aMapping.findForward(UIConstants.FAILURE);
		if(myForm.getAction().equals(UIConstants.INACTIVATE)){
			myForm.setAction(UIConstants.UPDATE);
		}
		myForm.setSecondaryAction("");
		aRequest.getSession().setAttribute("cscServiceProviderForm", myForm);
		return aMapping.findForward("successAddress");
	}
	
	public ActionForward create(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		myForm.clear();
		myForm.setCancelPath(UIConstants.CANCEL);
		myForm.setStartDate(new Date());
		myForm.setStatusId(PDCodeTableConstants.ASP_CS_SERVPROV_PENDING);
		myForm.setServiceProviderAgencyId(SecurityUIHelper.getUserAgencyId());
		myForm.setAction(UIConstants.CREATE);
		myForm.setSecondaryAction("");
		myForm.setSelectedValue("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward update(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{					
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		String selectedServProvId=myForm.getSelectedValue();
		myForm.clear();
		myForm.setServiceProviderId(selectedServProvId);
		String errMsg=AdminServiceProviderHelper.updateServiceProviderForm(myForm);
		if(errMsg!=null){
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, errMsg);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		myForm.setAction(UIConstants.UPDATE);
		myForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward updateSP(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{		
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		myForm.setCancelPath("cancelDashboard");
		myForm.setAction(UIConstants.UPDATE);
		myForm.setSecondaryAction("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
	

// Defects 55688,55690,55724 and 55723 Start here 
	public ActionForward reset(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		
		return aMapping.findForward(UIConstants.RESET);
	}	
// Defects 55688,55690,55724 and 55723 End here	
	public ActionForward goBack(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		ServiceProviderCSCDForm myForm=(ServiceProviderCSCDForm)aForm;
		myForm.partialClear();
		myForm.setStartDate(new Date());
		myForm.setStatusId(PDCodeTableConstants.ASP_CS_SERVPROV_PENDING);
		myForm.setServiceProviderAgencyId(SecurityUIHelper.getUserAgencyId());
		myForm.setAction(UIConstants.CREATE);
		myForm.setSecondaryAction("");
		myForm.setSelectedValue("");
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}// END CLASS

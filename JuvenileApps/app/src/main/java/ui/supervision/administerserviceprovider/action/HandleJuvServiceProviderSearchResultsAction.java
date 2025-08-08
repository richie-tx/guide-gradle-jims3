//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\HandleJuvServiceProviderSearchResultsAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.address.reply.AddressResponseEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCodeTableChildCodesResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.common.ComplexCodeTableHelper;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;


public class HandleJuvServiceProviderSearchResultsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 450ACCBD01A0
    */
   public HandleJuvServiceProviderSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 450AA16702D1
    */
   public ActionForward update(ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
   {
   		ServiceProviderForm sp = (ServiceProviderForm)aForm;   		 		
   		JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(sp.getSelectedValue());
		if(resp != null)
		{
			UIServiceProviderHelper.fillServiceProviderDetails(sp, resp);	
			AddressResponseEvent addressResp1 = UIServiceProviderHelper.getAddress(resp.getBillingAddressId());
			sp.setBillingAddress(new ServiceProviderForm.ProviderAddress());
			UIServiceProviderHelper.fillAddressDetails(sp.getBillingAddress(), addressResp1);
			sp.setBillingAddrStatus(addressResp1.getValidated());
			AddressResponseEvent addressResp2 = UIServiceProviderHelper.getAddress(resp.getMailingAddressId());
			sp.setMailingAddress(new ServiceProviderForm.ProviderAddress());
			UIServiceProviderHelper.fillAddressDetails(sp.getMailingAddress(), addressResp2);
			sp.setMailingAddrStatus(addressResp2.getValidated());
			Collection coll = CodeHelper.getCodes(PDCodeTableConstants.STREET_TYPE);
			sp.setStreetTypeList(UIUtil.sortCodesByCodeId(coll));
			coll = CodeHelper.getCodes(PDCodeTableConstants.STATE_ABBR,true);
			sp.setStateList(UIUtil.sortCodesByCodeId(coll));
			//sp.setGenericLogonIds(UIServiceProviderHelper.getAvailableLogons(sp.getDepartment()));//87191
			if(sp.getStatusId().equalsIgnoreCase("P"))
			{
				sp.setAdminUserIDsString(sp.getAdminContactId());
				sp.setContactUserIDsString(sp.getContactUserId());
			}
		}
		UIServiceProviderHelper.getAddressCodes(sp);
		sp.setActionType("updateProvider");
		
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward(UIConstants.BACK);
	}
	public ActionForward backToSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
	    return aMapping.findForward("backToSearch");
	}
	
	  public ActionForward view(
		 ActionMapping aMapping,
		 ActionForm aForm,
		 HttpServletRequest aRequest,
		 HttpServletResponse aResponse)
	{      
	       	ServiceProviderForm sp = (ServiceProviderForm)aForm;	       	
	       	
	       	String programReferralServiceProviderId = aRequest.getParameter("serviceProviderId");
	       	String source = aRequest.getParameter("source"); //source = programReferralTab
	       	String serviceProviderId = "";
	       	
	       	if(source != null && !"".equals(source) && programReferralServiceProviderId != null && !"".equals(programReferralServiceProviderId))
	       	{
	       	    serviceProviderId = programReferralServiceProviderId;
	       	} 
	       	else
	       	{
	       	    serviceProviderId = sp.getSelectedValue();
	       	}
	       	
	  	sp.setProviderId(serviceProviderId);
	  	JuvenileServiceProviderResponseEvent resp = UIServiceProviderHelper.getProvider(serviceProviderId);
	  	Collection results = new ArrayList();
	  	if(resp != null)
		{
	  		UIServiceProviderHelper.fillServiceProviderDetails(sp, resp);
	  		sp.clearProgram();
	  		sp.setFrmEndDate("");
	  		sp.setToEndDate("");
			Collection coll = UIServiceProviderHelper.getPrograms(sp, serviceProviderId);
			sp.setPrograms(UIServiceProviderHelper.sortResults(coll, "P"));
			//88615
			//Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContacts(resp.getServiceProviderId()); //86318	
			Collection<ServiceProviderContactResponseEvent> contacts =UIServiceProviderHelper.getContactsFromSecurityManager(resp.getAdminUserProfileId()); //86318
			UIServiceProviderHelper.updateContactInJims2FromSM(contacts,sp);
			//format the phone number
			Iterator<ServiceProviderContactResponseEvent> iter = contacts.iterator();			
			while(iter.hasNext())
			{
				ServiceProviderContactResponseEvent conResp = (ServiceProviderContactResponseEvent)iter.next();
				PhoneNumber p = new PhoneNumber(conResp.getWorkPhone());
				conResp.setWorkPhone(p+"");
				if(conResp.isInactivated())
				    conResp.setStatusCd("INACTIVE");
				else
				    conResp.setStatusCd("ACTIVE");
			}
			sp.setContacts(UIServiceProviderHelper.sortResults(contacts, "C")); //task 170594 
			//sp.setContacts(contacts);
			
		}
		return aMapping.findForward(UIConstants.VIEW_SUCCESS);	  
	}
	
	
	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.update","update");	
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.view", "view");
		keyMap.put("button.backToSearch","backToSearch");
		return keyMap;
	}
}

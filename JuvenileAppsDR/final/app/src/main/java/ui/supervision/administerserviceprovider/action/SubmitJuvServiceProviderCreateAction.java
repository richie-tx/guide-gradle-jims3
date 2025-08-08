//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateServiceProviderAction.java

package ui.supervision.administerserviceprovider.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import messaging.administerserviceprovider.CreateServiceProviderEvent;
import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class SubmitJuvServiceProviderCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D50126
	 */
	public SubmitJuvServiceProviderCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 446A2E440317
	 */
	public ActionForward save(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		sp.getMailingAddress().setAddressTypeId(UIConstants.SERVICEPROVIDER_MAILINGADDRESS_TYPE);
		sp.getMailingAddress().setValidated(sp.getMailingAddrStatus());
		sp.getBillingAddress().setAddressTypeId(UIConstants.SERVICEPROVIDER_BILLINGADDRESS_TYPE);
		sp.getBillingAddress().setValidated(sp.getBillingAddrStatus());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		ISecurityManager securityManager = (ISecurityManager)ContextManager.getSession().get("ISecurityManager");		
		IUserInfo userInfo = securityManager.getIUserInfo();		
		String strDeptId = userInfo.getDepartmentId();
		
		CreateServiceProviderEvent createEvent = UIServiceProviderHelper.getCreateServiceProviderEvent(sp);
		dispatch.postEvent(createEvent);

		// Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);		
		ServiceProviderErrorResponseEvent nameError = (ServiceProviderErrorResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceProviderErrorResponseEvent.class);
		if(nameError != null)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.exist.DepartmentId","Duplicate Department"));		
			saveErrors(aRequest, errors);
			return aMapping.findForward("failure");
		}
		
		ServiceProviderResponseEvent provider = (ServiceProviderResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceProviderResponseEvent.class);
		if(provider != null)
			sp.setProviderId(provider.getJuvServProviderId());
		if(sp.getActionType().equalsIgnoreCase("createSummary")){
			sp.setConfirmMessage("Service provider successfully created.");
			Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(sp.getAdminContactId());//88553			
			//sp.setContacts(UIServiceProviderHelper.sortResults(contacts, "C")); //86318
			sp.setContacts(contacts);
			UIServiceProviderHelper.updateContactInJims2FromSM(contacts,sp); //88615
			// persist in the sp_profile table
		}
		else if(sp.getActionType().equalsIgnoreCase("updateSummary"))
		{
			sp.setConfirmMessage("Service provider successfully updated.");
			//get programs
			sp.setCurrentProgram(new ServiceProviderForm.Program());
			Collection coll = UIServiceProviderHelper.getPrograms(sp, sp.getProviderId());
			sp.setPrograms(UIServiceProviderHelper.sortResults(coll, "P"));
			Iterator iter = coll.iterator();
			//set the targetIntervention string
			while(iter.hasNext())
			{
				ProviderProgramResponseEvent progResp = (ProviderProgramResponseEvent)iter.next();
				String targetInterventionString = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, progResp.getTargetInterventionId());
				progResp.setTargetInterventionId(targetInterventionString);
				//results.add(resp);
			}
			
			Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(sp.getAdminContactId());//86318			
			//sp.setContacts(UIServiceProviderHelper.sortResults(contacts, "C")); //86318
			sp.setContacts(contacts);
			UIServiceProviderHelper.updateContactInJims2FromSM(contacts,sp); //88615
		}
		else if(sp.getActionType().equalsIgnoreCase("saveReactivatedSP"))
		{
			sp.setConfirmMessage("Service provider successfully updated.");
			//get programs
			sp.setCurrentProgram(new ServiceProviderForm.Program());
			Collection coll = UIServiceProviderHelper.getPrograms(sp, sp.getProviderId());
			sp.setPrograms(UIServiceProviderHelper.sortResults(coll, "P"));
			Iterator iter = coll.iterator();
			//set the targetIntervention string
			while(iter.hasNext())
			{
				ProviderProgramResponseEvent progResp = (ProviderProgramResponseEvent)iter.next();
				String targetInterventionString = CodeHelper.getCodeDescription( PDCodeTableConstants.JUV_PROGRAM_GROUP, progResp.getTargetInterventionId());
				progResp.setTargetInterventionId(targetInterventionString);
				//results.add(resp);
			}
			
			Collection<ServiceProviderContactResponseEvent> contacts = UIServiceProviderHelper.getContactsFromSecurityManager(sp.getAdminContactId());//86318			
			//sp.setContacts(UIServiceProviderHelper.sortResults(contacts, "C")); //86318
			sp.setContacts(contacts);
			UIServiceProviderHelper.updateContactInJims2FromSM(contacts,sp); //88615
		}
		
		//sp.setActionType("createProvider");
		UIServiceProviderHelper.setProgramCodes(sp);
		UIServiceProviderHelper.setContactCodes(sp);
		UIServiceProviderHelper.setServiceCodes(sp);
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
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
				
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		CreateServiceProviderEvent createEvent = UIServiceProviderHelper.getCreateServiceProviderEvent(sp);
		dispatch.postEvent(createEvent);
		sp.setActionType("inactivateConfirm");
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		sp.setConfirmMessage("Service Provider Successfully inactivated");
		return aMapping.findForward(UIConstants.INACTIVATE_SUCCESS);
	}
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		if(sp.getActionType().equalsIgnoreCase("updateSummary"))
			sp.setActionType("updateProvider");
		if(sp.getActionType().equalsIgnoreCase("createSummary"))
			sp.setActionType("createProvider");
		if(sp.getActionType().equalsIgnoreCase("inactivateSummary"))
			sp.setActionType("updateProvider");
		return aMapping.findForward(UIConstants.BACK);
	}


	/* (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();	
		keyMap.put("button.saveAndContinue","save");	
		keyMap.put("button.cancel","cancel");
		keyMap.put("button.back","back");
		keyMap.put("button.finish","finish");
		keyMap.put("button.returnToServiceProviderSearch","cancel");
		return keyMap;
	}
}

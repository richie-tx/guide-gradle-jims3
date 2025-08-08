//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administerserviceprovider\\action\\SubmitJuvCreateUpdateServiceAction.java

package ui.supervision.administerserviceprovider.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.administercontract.GetServiceContractsEvent;
import messaging.administerlocation.reply.LocationNotificationResponseEvent;
import messaging.administerserviceprovider.CreateServiceEvent;
import messaging.administerserviceprovider.CreateServiceLocationRequestEvent;
import messaging.administerserviceprovider.GetServiceByServiceIdEvent;
import messaging.administerserviceprovider.administercontract.reply.ContractResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.SaveServiceEventCancellationEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ServiceEventControllerServiceNames;
import naming.ServiceProviderControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import ui.common.Name;
import ui.security.SecurityUIHelper;
import ui.supervision.administercontract.LoadSupervisionCodeTables;
import ui.supervision.administercontract.form.ContractForm;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.supervision.administerserviceprovider.form.ServiceProviderForm;

public class SubmitJuvServiceCreateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 447351D7000D
	 */
	public SubmitJuvServiceCreateAction()
	{

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FE40172
	 */
	public ActionForward save(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm sp = (ServiceProviderForm)aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		CreateServiceEvent createEvent = UIServiceProviderHelper.getCreateServiceEvent(sp);
		//get the list of locations to be removed
		
		dispatch.postEvent(createEvent);
		//Getting PD Response Event		
		 CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		 Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		 MessageUtil.processReturnException(dataMap);
		ServiceResponseEvent serviceResp = (ServiceResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceResponseEvent.class);
		if(serviceResp != null)
			sp.getCurrentProgram().getProgramService().setServiceId(serviceResp.getServiceId());
			
		//sp.setConfirmMessage("Service successfully created.");		
		Collection coll = UIServiceProviderHelper.sortResults(UIServiceProviderHelper.getServicesByProgram(sp.getCurrentProgram().getProgramId()), "S");
		ServiceProviderForm.Program.Service someService = new ServiceProviderForm.Program.Service();
		Iterator iter = coll.iterator();
		Collection temp = new ArrayList(); 
		while(iter.hasNext())
		{
			ServiceResponseEvent servResp = (ServiceResponseEvent)iter.next();
			someService.setServiceName(servResp.getServiceName());
			someService.setServiceId(servResp.getServiceId());
			someService.setCode(servResp.getServiceCode());
			someService.setType(UIServiceProviderHelper.getServiceType(sp, servResp.getServiceTypeId()));
			someService.setPreviousLocations(UIServiceProviderHelper.getLocations(servResp));
			someService.setPrevLocationsSize(someService.getPreviousLocations().size());
			someService.setStatusId(servResp.getStatusId());
			someService.setMaxEnrollment(servResp.getMaxEnrollment());
			temp.add(someService);
			someService = new ServiceProviderForm.Program.Service();
			/*resp.setServiceType(UIServiceProviderHelper.getServiceType(sp, resp.getServiceTypeId()));
			resp.setLocationName(UIServiceProviderHelper.getLocationString(resp));*/
		}
		sp.getCurrentProgram().setServices(temp);
		if(sp.getActionType().equalsIgnoreCase("updateService"))
		{	//send notice for the locations that were removed
			removedLocations(sp, createEvent);
			 cancelFutureServiceEvents(sp);
			sp.setConfirmMessage("Service successfully updated.");			
		}
		else if(sp.getActionType().equalsIgnoreCase("inactivateService"))
		{			
			 sp.setConfirmMessage("Service successfully inactivated.");
			 //cancel future service events
			 cancelFutureServiceEvents(sp);
		}	
		else
			sp.setConfirmMessage("Service successfully created.");	
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	private void removedLocations(ServiceProviderForm sp, CreateServiceEvent createEvent)
	{
		Collection serviceLocationRequests = MessageUtil.compositeToCollection(createEvent, CreateServiceLocationRequestEvent.class);
		Iterator iter = serviceLocationRequests.iterator();		
		while(iter.hasNext())
		{
			CreateServiceLocationRequestEvent reqEvent = (CreateServiceLocationRequestEvent)iter.next();
			if(reqEvent.isDeletable())
			{
				notifyLocationRemoval(sp,"SERVICE LOCATION REMOVAL", reqEvent);
			}
		}
		
	}
	private void cancelFutureServiceEvents(ServiceProviderForm spf)
	{
		Collection coll = spf.getServiceEvents();
		Iterator iter = coll.iterator();
		while(iter.hasNext())
		{
			CalendarServiceEventResponseEvent evt=(CalendarServiceEventResponseEvent)iter.next();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			SaveServiceEventCancellationEvent saveCancellationEvent = (SaveServiceEventCancellationEvent)EventFactory.getInstance(ServiceEventControllerServiceNames.SAVESERVICEEVENTCANCELLATION);
	     	saveCancellationEvent.setServiceEventId(evt.getEventId());
	    	dispatch.postEvent(saveCancellationEvent);						
	    	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	  		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	  		MessageUtil.processReturnException(dataMap);
	  		notifyServiceInactivation(spf, evt, "SERVICE INACTIVATION");
		}
	}
	private void notifyServiceInactivation(ServiceProviderForm spf, CalendarServiceEventResponseEvent evt, String subject)
	{
		String providerId = spf.getProviderId();		
	
		//send the notification
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
		nevt.setSubject(subject);
		nevt.setServiceProviderName(spf.getProviderName());
		nevt.setServiceProviderFax(spf.getFax().toString());
		nevt.setServiceName(spf.getCurrentProgram().getProgramService().getServiceName());
		nevt.setLocationAddress(UIServiceProviderHelper.getLocationAddress(evt.getLocationId()));
		SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
		nevt.setSessionDate(dfmt.format(evt.getEventDate()));
		dfmt.applyPattern("h:mm a");
		nevt.setSessionTime(dfmt.format(evt.getEventDate()));	
		nevt.setIdentity(spf.getAdminContactId());
		Iterator associatedContextsIter = evt.getAssociatedContexts().iterator();
		StringBuffer sb = new StringBuffer();
		sb.append("\n");	
		while (associatedContextsIter.hasNext()){
			JuvenileCasefileResponseEvent juvResp = (JuvenileCasefileResponseEvent)associatedContextsIter.next();
				
				Name name = new Name(juvResp.getJuvenileFirstName(),juvResp.getJuvenileLastName(),juvResp.getJuvenileMiddleName());						
				sb.append(name.getFormattedName());					
				sb.append(", " + juvResp.getJuvenileNum() + ", was scheduled to attend a session(s) at this location on ");
				sb.append("["+nevt.getSessionDate()+ "and" + nevt.getSessionTime()+"] will need to be rescheduled.");						
				sb.append("\n");	
		}
		nevt.setNotificationMessage(sb.toString());
		UIServiceProviderHelper.sendNotification(nevt,UIConstants.SERVICE_INACTIVATION_NOTIFICATION);
		
	}
	
	private void notifyLocationRemoval(ServiceProviderForm spf, String subject, CreateServiceLocationRequestEvent reqEvent)
	{
		String providerId = spf.getProviderId();		
	
		//send the notification
		LocationNotificationResponseEvent nevt = new LocationNotificationResponseEvent();
		nevt.setSubject(subject);
		nevt.setServiceProviderName(spf.getProviderName());
		nevt.setServiceProviderFax(spf.getFax().toString());
		nevt.setServiceName(spf.getCurrentProgram().getProgramService().getServiceName());
		nevt.setIdentity(spf.getAdminContactId());	
		nevt.setLocationAddress(reqEvent.getJuvLocUnitName());
		Collection coll = spf.getServiceEvents();		
		Iterator iter = coll.iterator();
		StringBuffer sb = new StringBuffer();
		while(iter.hasNext())
		{
			CalendarServiceEventResponseEvent evt=(CalendarServiceEventResponseEvent)iter.next();
			if(evt.getServiceLocationId().equalsIgnoreCase(reqEvent.getJuvLocUnitId()))
			{
				SimpleDateFormat dfmt = new SimpleDateFormat("MM/dd/yyyy");				
				nevt.setSessionDate(dfmt.format(evt.getEventDate()));
				dfmt.applyPattern("h:mm a");
				nevt.setSessionTime(dfmt.format(evt.getEventDate()));	
				//nevt.setLocationAddress(UIServiceProviderHelper.getLocationAddress(evt.getLocationId()));
				Iterator associatedContextsIter = evt.getAssociatedContexts().iterator();
				
				sb.append("\n");	
				while (associatedContextsIter.hasNext()){
				JuvenileCasefileResponseEvent juvResp = (JuvenileCasefileResponseEvent)associatedContextsIter.next();
					
					Name name = new Name(juvResp.getJuvenileFirstName(),juvResp.getJuvenileLastName(),juvResp.getJuvenileMiddleName());						
					sb.append(name.getFormattedName());					
					sb.append(", " + juvResp.getJuvenileNum() + ", was scheduled to attend a session(s) at this location on ");
					sb.append(nevt.getSessionDate()+ "and" + nevt.getSessionTime()+" will need to be rescheduled.");						
					sb.append("\n");
				}
			}
		}
		nevt.setNotificationMessage(sb.toString());
		UIServiceProviderHelper.sendNotification(nevt,UIConstants.SERVICE_LOCATION_REMOVAL_NOTIFICATION );
		
	}
	
	private void setService(ServiceProviderForm.Program.Service currentService, ServiceProviderForm.Program.Service serv)
	{
		//ServiceProviderForm.Program.Service serv = new ServiceProviderForm.Program.Service();
		 serv.setCode(currentService.getCode());
		 serv.setCost(currentService.getCost());
		 serv.setCostBasisId(currentService.getCostBasisId());
		 serv.setDisplayCost(currentService.getDisplayCost());
		 //serv.setLocationDescription(sp.getCurrentProgram().getProgramService().getLocationDescription());
		 serv.setMaxEnrollment(currentService.getMaxEnrollment());
		 serv.setServiceName(currentService.getServiceName());
		 serv.setServiceId(currentService.getServiceId());
		 serv.setType(currentService.getType());
		 serv.setTypeId(currentService.getTypeId());
		serv.setLocationString(currentService.getLocationDescription());
		return;
	}
	
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		ServiceProviderForm providerForm = (ServiceProviderForm)aForm;
		if(providerForm.getActionType().equalsIgnoreCase("view"))
			providerForm.getCurrentProgram().setProgramService(new ServiceProviderForm.Program.Service());
		return aMapping.findForward(UIConstants.BACK);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44734FE40172
	 */
	public ActionForward manageContract(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			ServiceProviderForm providerForm = (ServiceProviderForm)aForm;
			String actionType = providerForm.getActionType();
			ServiceResponseEvent servResp = null;
			String selectedVal = null;
		   	   
			if(actionType != null && (actionType.equals(UIConstants.UPDATE_SERVICE) || actionType.equals(UIConstants.CREATE_SERVICE))){
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				// Sending PD Request Event
				CreateServiceEvent createEvent = UIServiceProviderHelper.getCreateServiceEvent(providerForm);
				
				dispatch.postEvent(createEvent);
				//Getting PD Response Event		
				 CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				 Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				 MessageUtil.processReturnException(dataMap);
				 
				 if(actionType.equals(UIConstants.UPDATE_SERVICE)){
				 	 selectedVal = providerForm.getCurrentProgram().getProgramService().getServiceId();
				 }else{
					 ServiceResponseEvent serviceResp = (ServiceResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceResponseEvent.class);
					 if(serviceResp != null){
					 	selectedVal = serviceResp.getServiceId();
					 }
				 }
			}else{
				selectedVal = providerForm.getSelectedValue();		
			}

			GetServiceByServiceIdEvent reqEvent = (GetServiceByServiceIdEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSERVICEBYSERVICEID);
			reqEvent.setServiceId(selectedVal);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(reqEvent);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(compositeResponse);
			ServiceResponseEvent serv = (ServiceResponseEvent)MessageUtil.filterComposite(compositeResponse, ServiceResponseEvent.class);
			if(serv != null)
			{
				ContractForm contractForm = (ContractForm) aRequest.getSession().getAttribute("contractForm");
				if(contractForm == null){
					contractForm = new ContractForm();
					aRequest.getSession().setAttribute("contractForm",contractForm);
				}
				contractForm.clear();
				contractForm.setDepartmentId(SecurityUIHelper.getUserDepartmentId());

		   	    LoadSupervisionCodeTables load = LoadSupervisionCodeTables.getInstance();
			    load.setContractForm(contractForm);
			    
			    contractForm.setServiceName(serv.getServiceName());
			    contractForm.setServiceId(serv.getServiceId());
			    contractForm.setServiceCode(serv.getServiceCode());
			    contractForm.setServiceType(serv.getServiceType());
			    contractForm.setServiceMaxEnrollment(serv.getMaxEnrollment());
			    contractForm.setServiceCost("" + serv.getCost());
			    contractForm.setServiceLocation(serv.getLocationName());
			    
			    contractForm.setServiceProviderName(providerForm.getProviderName());
				contractForm.setServiceProviderStartDate(providerForm.getStartDate());
				contractForm.setInHouse(providerForm.getIsInHouse());
				contractForm.setProgramName(providerForm.getCurrentProgram().getProgramName());
				contractForm.setTargetIntervention(providerForm.getCurrentProgram().getTargetIntervention());
				contractForm.setShowServiceProviderInfo("Y");
				
				// Get the current contracts if there is any
				GetServiceContractsEvent sEvent = new GetServiceContractsEvent();
				sEvent.setServiceId(contractForm.getServiceId());
				dispatch.postEvent(sEvent);
				
				compositeResponse = (CompositeResponse) dispatch.getReply();
				dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);
				
				Collection currentContracts = MessageUtil.compositeToCollection(compositeResponse, ContractResponseEvent.class);
	            if(currentContracts != null && !currentContracts.isEmpty()){
	            	contractForm.setCurrentContracts(currentContracts);
	            }
			}
			return aMapping.findForward(UIConstants.MANAGE_CONTRACT_SUCCESS);
		}
	/**
	 * @param sp
	 */
	private void saveService(ServiceProviderForm sp) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		// Sending PD Request Event
		CreateServiceEvent createEvent = UIServiceProviderHelper.getCreateServiceEvent(sp);
		
		dispatch.postEvent(createEvent);
		//Getting PD Response Event		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
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
		keyMap.put("button.manageContract","manageContract");
		return keyMap;
	}
}

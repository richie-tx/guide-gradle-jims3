//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\CreateServiceCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import pd.contact.user.UserProfile;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.supervision.administerserviceprovider.SP_Profile;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.Service;
import pd.supervision.administerserviceprovider.ServiceLocation;
import pd.supervision.administerserviceprovider.ServiceProvider;
import messaging.administerserviceprovider.CreateServiceEvent;
import messaging.administerserviceprovider.CreateServiceLocationRequestEvent;
import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import messaging.administerserviceprovider.GetContactsEvent;
import messaging.administerserviceprovider.GetServiceLocationEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.CreateNotificationEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.PDAdministerServiceProviderConstants;
import naming.ServiceProviderControllerServiceNames;

public class CreateServiceCommand implements ICommand
{

	/**
	 * @roseuid 4473538A0376
	 */
	public CreateServiceCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE401B0
	 */
	public void execute(IEvent event)
	{
		CreateServiceEvent createEvent = (CreateServiceEvent) event;
		Service service = null;
		if(createEvent.isCreate()) {
			service = new Service();
			if (checkForServiceStatus(createEvent)) {
				createEvent.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
			}
			else {
				createEvent.setStatusId(PDAdministerServiceProviderConstants.PENDING);
			}
			service.setService(createEvent);
			service.setCreateUserID(createEvent.getUserID());
			service.createOID();
			String serviceId = service.getOID().toString();
			this.updateServiceLocation(createEvent, serviceId);
			this.updateServiceProviderStatus(createEvent);
			this.sendServiceResponseEvent(serviceId);
		}
		else if (createEvent.isInactivate()) {
			inactivateServiceStatus(createEvent);
		}
		else {
			if(createEvent.getServiceId() != null && !(createEvent.getServiceId().equals(""))) {
				service = Service.find(createEvent.getServiceId());
			}			
			service.setService(createEvent);
			this.updateServiceLocation(createEvent, createEvent.getServiceId());	
		}
	}
	
	private void updateServiceProviderStatus(CreateServiceEvent createEvent) {
		UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 
		JuvenileServiceProvider juvServProv = null;
		boolean existsAdminContact = true; //88553 defaulted to admin contact.
		boolean existsService =  false;
		boolean updated = false;
	
		ArrayList serviceIds = new ArrayList();
		
		/*Iterator iter = SP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
		if (iter.hasNext()) {
			SP_Profile profile = (SP_Profile) iter.next(); 
			if(profile.getIsAdminContact())
				existsAdminContact = true;
		}*/		//88553
		
		Iterator iterSP = JuvenileServiceProvider.findAll(updateSpEvent);
		while (iterSP.hasNext()) {
			juvServProv = (JuvenileServiceProvider) iterSP.next();
			serviceIds.add(new Integer(juvServProv.getServiceId()).toString());
		}
		if(!serviceIds.isEmpty()){   
			existsService = true;
		}
		if(existsAdminContact) {
			if(existsService) {
				juvServProv = JuvenileServiceProvider.find((new Integer(updateSpEvent.getServiceProviderId())).intValue());
				juvServProv.updateJuvServProvStatus(updateSpEvent, false);
				updated = true;
			}
		}
		if(updated) {
			Iterator iterServiceIds = serviceIds.iterator(); 
			while (iterServiceIds.hasNext()) {
				String strServiceId = iterServiceIds.next().toString();
				if (strServiceId != null && !strServiceId.equals("")) {
					Service service = Service.find(strServiceId);
					if (service != null) {
						if (service.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
							service.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
							service.setStatusChangeDate(DateUtil.getCurrentDate());
						}
					}
				}
			}
			// DPA: Commented for fixing defect# 40646
			// this can be taken out when completely validated.
			/*Iterator iterPrograms = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
			while (iterPrograms.hasNext()) {
				ProviderProgram program  = (ProviderProgram) iterPrograms.next(); 
				if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
					program.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
					program.setStatusChangeDate(DateUtil.getCurrentDate());
				}
			}*/				
		}
		// when the service is created, flip the status of the program to active 
		// if it is pending.. DPA: Defect #40644
		ProviderProgram program  = ProviderProgram.find(createEvent.getProviderProgramId());
			if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
				program.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
				program.setStatusChangeDate(DateUtil.getCurrentDate());
			}
	}
	
/*
	private void updateServiceProviderStatus(CreateServiceEvent createEvent) {
		UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 
		
		JuvenileServiceProvider juvServProv = null;
		boolean existsInhouseSP = false;

		Iterator iterInhouse = InhouseSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
		if (iterInhouse.hasNext()) {
			InhouseSP_Profile profile = (InhouseSP_Profile) iterInhouse.next(); 
			if(profile.getIsAdminContact())
				existsInhouseSP = true;
		}
		if(existsInhouseSP) {
			juvServProv = JuvenileServiceProvider.find((new Integer(updateSpEvent.getServiceProviderId())).intValue());
			juvServProv.updateJuvServProvStatus(updateSpEvent, false);
		}
		else {
			Iterator iterOutSource = OutSourcedSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
			boolean existsOutSourceSP =  false; 
			if (iterOutSource.hasNext()) {
				OutSourcedSP_Profile profile = (OutSourcedSP_Profile) iterOutSource.next(); 
				if(profile.getIsAdminContact())
					existsInhouseSP = true;
			}
			if(existsOutSourceSP) {
				juvServProv = JuvenileServiceProvider.find((new Integer(updateSpEvent.getServiceProviderId())).intValue());
				juvServProv.updateJuvServProvStatus(updateSpEvent, false);
			}
		}
	}
*/	
	private void updateServiceLocation(CreateServiceEvent createEvent, String serviceId)
	{
		Collection serviceLocationRequests = MessageUtil.compositeToCollection(createEvent, CreateServiceLocationRequestEvent.class);
		Iterator iter = serviceLocationRequests.iterator();
		ServiceLocation serviceLocation = null;
		while(iter.hasNext()){
			CreateServiceLocationRequestEvent serviceLocationRequestEvent = (CreateServiceLocationRequestEvent) iter.next();
			boolean isDelete = serviceLocationRequestEvent.isDeletable();

			if(isDelete){
				GetServiceLocationEvent slocation = new GetServiceLocationEvent();
				slocation.setServiceId(new Integer(serviceLocationRequestEvent.getServiceId()).intValue());			
				slocation.setJuvLocUnitId(new Integer(serviceLocationRequestEvent.getJuvLocUnitId()).intValue());
				Iterator spIter = ServiceLocation.findAll(slocation);
				while(spIter.hasNext()){
					ServiceLocation sl = (ServiceLocation) spIter.next();
					if(sl != null){
						sl.delete();
					}
				}				
			}
			else {   	
				serviceLocation = new ServiceLocation();
				serviceLocationRequestEvent.setServiceId(serviceId);
				serviceLocation.setServiceLocation(serviceLocationRequestEvent);
				serviceLocation.setCreateUserID(createEvent.getUserID());  
				serviceLocation.createOID();
			}
		}
	}
	
	/**
	 * @param event
	 * @roseuid 44734FE401BF
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FE401C1
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FE401C3
	 */
	public void update(Object anObject)
	{

	}
	private void sendServiceResponseEvent(String serviceId)
	{
		ServiceResponseEvent event = new ServiceResponseEvent();		
		event.setServiceId(serviceId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}
	private void inactivateServiceStatus(CreateServiceEvent createEvent) {
		Service service = Service.find(createEvent.getServiceId());
		String providerProgramId = "";
		if(service != null) {
			providerProgramId = service.getProviderProgramId();
			service.updateServiceStatus(createEvent);
		}
		this.updateProviderProgramStatus(providerProgramId);	
		//createNotification(createEvent); -- notification no longer required per Carla Glover after discussion with RY
	}
	
	public void updateProviderProgramStatus(String providerProgramId) {
		if(providerProgramId != null && !providerProgramId.equals("")) {
			Iterator iter = Service.findAll(PDAdministerServiceProviderConstants.PROVIDERPROGRAM_ID, providerProgramId);
			int count = 0;
			while(iter.hasNext()){
				Service service = (Service) iter.next();
				if(service.getStatusId().equals(PDAdministerServiceProviderConstants.ACTIVE) || 
							service.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
					count++;
				}
			}
			if((count != 0) && (count == 1)) {
				ProviderProgram program = ProviderProgram.find(providerProgramId);
				program.setStatusId(PDAdministerServiceProviderConstants.PENDING);	
			}
		}
	}
	private boolean checkForServiceStatus(CreateServiceEvent createEvent) {
		UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 
		
		boolean existsAdminContact = true; //88553

		//check not needed any more as it is always going to be a admin contact. 
		/*Iterator iter = SP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
		while (iter.hasNext()) {
			SP_Profile profile = (SP_Profile) iter.next(); 
			if(profile.getIsAdminContact())
				existsAdminContact = true;
		}*/
		return existsAdminContact;
	}	
	private void createNotification(CreateServiceEvent createEvent)
	{
		CreateNotificationEvent notificationEvent =
			(CreateNotificationEvent) EventFactory.getInstance("CreateNotification");

		notificationEvent.setSubject("Service Inactivation");

		notificationEvent.setNotificationTopic("SP.SERV.INACTIVATED");

		ServiceProvider juv = JuvenileServiceProvider.find(createEvent.getServiceProviderId());
		
		
		//juv.setServiceName(createEvent.getServiceName());
		if(juv.getFax()== null)
			juv.setFax("NA");
		
		//get the admin contacts for the SP 
		GetContactsEvent contactEvent = new GetContactsEvent();
		contactEvent.setServiceProviderId(createEvent.getServiceProviderId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(contactEvent);
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(compositeResponse);
		Collection contacts = MessageUtil.compositeToCollection(compositeResponse, ServiceProviderContactResponseEvent.class);	
		if(contacts != null || contacts.size()!= 0)
		{
			Iterator iter = contacts.iterator();
			while(iter.hasNext())
			{
				ServiceProviderContactResponseEvent resp = (ServiceProviderContactResponseEvent)iter.next();
				if(resp.isAdminContact())
				{
					//get the JIMS2 account
					Iterator jims2Iter = JIMS2AccountType.findAll("userAccountOID", resp.getEmployeeId());
					while (jims2Iter.hasNext()) {
						JIMS2AccountType jims2AccountType = (JIMS2AccountType) jims2Iter.next();
						if(jims2AccountType.getUserAccountTypeId().equals(PDAdministerServiceProviderConstants.USERACCOUNT_TYPE)) {
							JIMS2Account jims2Account = JIMS2Account.find(jims2AccountType.getJIMS2AccountId());
							if(jims2Account != null && jims2Account.getStatus() != null && !jims2Account.getStatus().equalsIgnoreCase("I"))
							{
								notificationEvent.addIdentity("originator", (IAddressable) jims2Account);
								notificationEvent.addContentBean(juv);
								IDispatch dispatch1= EventManager.getSharedInstance(EventManager.REPLY);
								dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
								dispatch1.postEvent(notificationEvent);
							}
								
						}
					}
				}
			}
		}
		
		
		/*UserProfile originator = UserProfile.find(juv.getAdminUserProfileId());
		notificationEvent.addIdentity("originator", (IAddressable) originator);*/
	/*	notificationEvent.addContentBean(juv);
		IDispatch dispatch1= EventManager.getSharedInstance(EventManager.REPLY);
		dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(notificationEvent);*/
	}
}


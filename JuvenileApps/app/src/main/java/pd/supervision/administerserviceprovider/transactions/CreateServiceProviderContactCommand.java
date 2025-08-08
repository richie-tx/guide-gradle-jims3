//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\CreateServiceProviderContactCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import naming.PDAdministerServiceProviderConstants;

import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

import pd.supervision.administerserviceprovider.InhouseSP_Profile;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.OutSourcedSP_Profile;
import pd.supervision.administerserviceprovider.ProviderProgram;
import pd.supervision.administerserviceprovider.Service;
import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.reply.ServiceProviderContactResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
//TODO
public class CreateServiceProviderContactCommand implements ICommand
{

	/**
	 * @roseuid 4473538C00C6
	 */
	public CreateServiceProviderContactCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FED01BF
	 */
	public void execute(IEvent event)
	{
		CreateServiceProviderContactEvent updateEvent = (CreateServiceProviderContactEvent) event;
		if(updateEvent.isInHouse()) 
		{
			InhouseSP_Profile spProfile = null;
			if(updateEvent.isCreate()){
				spProfile = new InhouseSP_Profile();
				spProfile.setInhouseSP_Profile(updateEvent, false);
				spProfile.setCreateUserID(updateEvent.getUserID());
				spProfile.createOID();
				String juvServProviderProfileId = spProfile.getOID().toString();
				if(juvServProviderProfileId != null && !(juvServProviderProfileId.equals(""))) 
				{
					spProfile = (InhouseSP_Profile) InhouseSP_Profile.find(juvServProviderProfileId);
				}	
				spProfile.setEmployeeId(updateEvent.getEmployeeId());		//88615	
				this.updateServiceProviderStatus(updateEvent);
				this.sendServiceProviderContactResponseEvent(juvServProviderProfileId); 
			}
			//88615
			/*else if(updateEvent.isInactivate()) 
			{
				inactivateContactStatus(updateEvent, updateEvent.isInHouse());
			}
			else 
			{
				String jspID = updateEvent.getJuvServProvProfId() ;
				if( jspID != null && !("".equals(jspID)) ) 
				{
					spProfile = (InhouseSP_Profile)InhouseSP_Profile.find(jspID);
				}				
				spProfile.setInhouseSP_Profile(updateEvent, true);
			}*/
		}
		else 
		{
			OutSourcedSP_Profile outSourcedSPProfile = null;
			if(updateEvent.isCreate()) 
			{
				outSourcedSPProfile = new OutSourcedSP_Profile();
				outSourcedSPProfile.setOutSourcedSP_Profile(updateEvent, false);
				outSourcedSPProfile.setCreateUserID(updateEvent.getUserID());
				outSourcedSPProfile.createOID();
				String juvServProviderProfileId = outSourcedSPProfile.getOID().toString();
				
				if(juvServProviderProfileId != null && !(juvServProviderProfileId.equals(""))) 
				{
					outSourcedSPProfile = (OutSourcedSP_Profile) OutSourcedSP_Profile.find(juvServProviderProfileId);
				}	
				outSourcedSPProfile.setEmployeeId(updateEvent.getEmployeeId()); //88615				
				
				this.updateServiceProviderStatus(updateEvent);
				this.sendServiceProviderContactResponseEvent(juvServProviderProfileId); 
			}
			//88615
			/*else if(updateEvent.isInactivate()) 
			{
				inactivateContactStatus(updateEvent, updateEvent.isInHouse());
			}			
			else 
			{
				if(updateEvent.getJuvServProvProfId() != null && !(updateEvent.getJuvServProvProfId().equals(""))) 
				{
					outSourcedSPProfile = (OutSourcedSP_Profile) OutSourcedSP_Profile.find(updateEvent.getJuvServProvProfId());
				}				
				outSourcedSPProfile.setOutSourcedSP_Profile(updateEvent, true);
			}	*/			
		}
	}

	private void inactivateContactStatus(CreateServiceProviderContactEvent createEvent, boolean inHouse) {

		if(inHouse) {
			InhouseSP_Profile profile = (InhouseSP_Profile) InhouseSP_Profile.find(createEvent.getJuvServProvProfId());
			String serviceProviderId = profile.getServiceProviderId();
			String employeeId = profile.getOID().toString();
			boolean inactivated = false;
			
			if(!createEvent.isAdminContact) {
				profile.updateInHouseProfileStatus(createEvent);
				inactivated = true;
			}
			else {
				Iterator iter = InhouseSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, serviceProviderId);
				int count = 0;
				while(iter.hasNext()){
					InhouseSP_Profile spProfile = (InhouseSP_Profile)iter.next();
					if(spProfile.getIsAdminContact() && !spProfile.getStatusId().equalsIgnoreCase("I")) {
						count++;
					}
				}
				if(count !=0 && count > 1) {
					profile.updateInHouseProfileStatus(createEvent);
					inactivated = true;
				}
			}
			
			if (inactivated) {
				inactivateJIMS2Account(employeeId);
			}
			this.sendResponseForInactivate(inactivated);
		}
		else {
			OutSourcedSP_Profile profile = (OutSourcedSP_Profile) OutSourcedSP_Profile.find(createEvent.getJuvServProvProfId());
			String serviceProviderId = profile.getServiceProviderId();
			String employeeId = profile.getOID().toString();
			boolean inactivated = false;
			Iterator iter = OutSourcedSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, serviceProviderId);
			int count = 0;
			if(!createEvent.isAdminContact) {
				profile.updateOutSourcedProfileStatus(createEvent);
				inactivated = true;
			}
			else {
				while(iter.hasNext()){
					OutSourcedSP_Profile spProfile = (OutSourcedSP_Profile)iter.next();
					if(spProfile.getIsAdminContact() && !spProfile.getStatusId().equalsIgnoreCase("I")) {
						count++;
					}
				}
				if(count !=0 && count > 1) {
					profile.updateOutSourcedProfileStatus(createEvent);
					inactivated = true;
				}
			}
			if (inactivated) {
				inactivateJIMS2Account(employeeId);
			}			
			this.sendResponseForInactivate(inactivated);
		}
	}
	private void inactivateJIMS2Account (String employeeId) {
		Iterator iter = JIMS2AccountType.findAll("userAccountOID", employeeId);
		if (iter.hasNext()) {
			JIMS2AccountType jims2AccountType = (JIMS2AccountType) iter.next();
			if(jims2AccountType.getUserAccountTypeId().equals(PDAdministerServiceProviderConstants.USERACCOUNT_TYPE)) {
				JIMS2Account jims2Account = JIMS2Account.find(jims2AccountType.getJIMS2AccountId());
				jims2Account.setStatus(PDAdministerServiceProviderConstants.INACTIVE);
			}
		}
	}
	private void updateServiceProviderStatus(CreateServiceProviderContactEvent createEvent) {
		if (createEvent.isInHouse()) {
			UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 
			JuvenileServiceProvider juvServProv = null;
			boolean existsInhouseSP = false;
			boolean existsService =  false;
			boolean updated = false;
		
			ArrayList serviceIds = new ArrayList();
			
			Iterator iterInhouse = InhouseSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
			while (iterInhouse.hasNext()) {
				InhouseSP_Profile profile = (InhouseSP_Profile) iterInhouse.next(); 
				if(profile.getIsAdminContact())
					existsInhouseSP = true;
			}		
			
			Iterator iterSP = JuvenileServiceProvider.findAll(updateSpEvent);
			while (iterSP.hasNext()) {
				juvServProv = (JuvenileServiceProvider) iterSP.next();
				serviceIds.add(new Integer(juvServProv.getServiceId()).toString());
			}
			if(!serviceIds.isEmpty()){   
				existsService = true;
			}
			if(existsInhouseSP) {
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
				Iterator iterPrograms = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
				while (iterPrograms.hasNext()) {
					ProviderProgram program  = (ProviderProgram) iterPrograms.next(); 
					if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
						program.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
						program.setStatusChangeDate(DateUtil.getCurrentDate());
					}
				}				
			}
		}
		else {
			UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 
			JuvenileServiceProvider juvServProv = null;
			boolean existsInhouseSP = false;
			boolean existsService =  false;
			boolean updated = false;
			ArrayList serviceIds = new ArrayList();
			
			Iterator iterInhouse = OutSourcedSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
			while (iterInhouse.hasNext()) {
				OutSourcedSP_Profile profile = (OutSourcedSP_Profile) iterInhouse.next(); 
				if(profile.getIsAdminContact())
					existsInhouseSP = true;
			}		
			
			Iterator iterSP = JuvenileServiceProvider.findAll(updateSpEvent);
			while (iterSP.hasNext()) {
				juvServProv = (JuvenileServiceProvider) iterSP.next();
				serviceIds.add(new Integer(juvServProv.getServiceId()).toString());
			}
			if(!serviceIds.isEmpty()){   
				existsService = true;
			}
			if(existsInhouseSP) {
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
				Iterator iterPrograms = ProviderProgram.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
				while (iterPrograms.hasNext()) {
					ProviderProgram program  = (ProviderProgram) iterPrograms.next(); 
					if(program.getStatusId().equals(PDAdministerServiceProviderConstants.PENDING)) {
						program.setStatusId(PDAdministerServiceProviderConstants.ACTIVE);
						program.setStatusChangeDate(DateUtil.getCurrentDate());
					}
				}
			}			
		}
	}
/*
	private void updateServiceProviderStatus(CreateServiceProviderContactEvent createEvent) {
		UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 

		JuvenileServiceProvider juvServProv = null;
		boolean existsInhouseSP = false;
		boolean existsService =  false;
		int serviceId = 0;

		Iterator iterInhouse = InhouseSP_Profile.findAll(PDAdministerServiceProviderConstants.SERVICE_PROVIDER_ID, updateSpEvent.getServiceProviderId());
		if (iterInhouse.hasNext()) {
			InhouseSP_Profile profile = (InhouseSP_Profile) iterInhouse.next(); 
			if(profile.getIsAdminContact())
				existsInhouseSP = true;
		}		
		
		Iterator iterSP = JuvenileServiceProvider.findAll(updateSpEvent);
		while (iterSP.hasNext()) {
			juvServProv = (JuvenileServiceProvider) iterSP.next();
			serviceId = juvServProv.getServiceId();
			if(serviceId != 0){   
				existsService = true;
				break;
			}
		}
		if(existsInhouseSP) {
			if(existsService) {
				juvServProv = JuvenileServiceProvider.find((new Integer(updateSpEvent.getServiceProviderId())).intValue());
				juvServProv.updateJuvServProvStatus(updateSpEvent);
			}
		}
		
	}
 */	
	private void sendServiceProviderContactResponseEvent(String juvServProviderProfileId)
	{
		ServiceProviderContactResponseEvent event = new ServiceProviderContactResponseEvent();		
		event.setJuvServProviderProfileId(juvServProviderProfileId);
		event.setEmployeeId(juvServProviderProfileId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}	
	
	private void sendResponseForInactivate(boolean inactivated)
	{
		ServiceProviderContactResponseEvent event = new ServiceProviderContactResponseEvent();		
		event.setInactivated(inactivated);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}
	/**
	 * @param event
	 * @roseuid 44734FED01C1
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 44734FED01C3
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 44734FED01CD
	 */
	public void update(Object anObject)
	{

	}
}
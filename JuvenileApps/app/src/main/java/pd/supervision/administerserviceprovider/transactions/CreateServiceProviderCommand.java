//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\CreateServiceProviderCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import messaging.administerserviceprovider.CreateServiceProviderEvent;
import messaging.administerserviceprovider.UpdateServiceProviderStatusRequestEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import messaging.address.AddressRequestEvent;
import pd.address.Address;
import pd.address.PDAddressHelper;
import naming.PDAdministerServiceProviderConstants;

public class CreateServiceProviderCommand implements ICommand
{

	/**
	 * @roseuid 4473538B024D
	 */
	public CreateServiceProviderCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E440362
	 */
	public void execute(IEvent event)
	{
		CreateServiceProviderEvent createEvent = (CreateServiceProviderEvent) event;
		JuvenileServiceProvider juvServProvider = null;
		if(createEvent.isCreate()) {
		// check agencyId
			if(createEvent.getDepartmentId() != null && !(createEvent.getDepartmentId().equals(""))){
					juvServProvider = new JuvenileServiceProvider();
					juvServProvider.setJuvenileServiceProvider(createEvent, true);
					juvServProvider.setCreateUserID(createEvent.getUserID());
					juvServProvider.createOID();					
					String juvServProviderId = juvServProvider.getOID().toString(); 
					this.updateAddress(createEvent,juvServProvider);
					this.sendServiceProviderResponseEvent(juvServProviderId);				
			}
		}
		else if (createEvent.isInactivate()) {
			inactivateServiceProviderStatus(createEvent);
		}
		else {
			if(createEvent.getServiceProviderId() != null && !(createEvent.getServiceProviderId().equals(""))) {
				juvServProvider = JuvenileServiceProvider.find((new Integer(createEvent.getServiceProviderId())).intValue());
				
			}
			if(!juvServProvider.getStatusId().equals(createEvent.getStatusId())) {
				juvServProvider.setJuvenileServiceProvider(createEvent, true);
				this.updateAddress(createEvent,juvServProvider);
			}
			else {
				juvServProvider.setJuvenileServiceProvider(createEvent, false);
				this.updateAddress(createEvent,juvServProvider);
			}
		}
	}
	
	private void sendServiceProviderErrorResponseEvent(String errorKey)
	{
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}	
	
	private void sendServiceProviderResponseEvent(String juvServProviderId)
	{
		ServiceProviderResponseEvent event = new ServiceProviderResponseEvent();		
		event.setJuvServProviderId(juvServProviderId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(event);
	}	

	private void updateAddress(CreateServiceProviderEvent createEvent, JuvenileServiceProvider juvServProvider)
	{
		Collection addressRequests = MessageUtil.compositeToCollection(createEvent, AddressRequestEvent.class);
		Iterator iter = addressRequests.iterator();
		while(iter.hasNext()){
			AddressRequestEvent addressRequestEvent = (AddressRequestEvent) iter.next();
			String addressTypeId = addressRequestEvent.getAddressTypeId();
			if(addressTypeId != null && addressTypeId.equalsIgnoreCase(PDAdministerServiceProviderConstants.SERVICEPROVIDER_BILLINGADDRESS_TYPE)){
				Address billingAddress = juvServProvider.getBillingAddress();
				
				if(billingAddress == null){
					billingAddress = new Address();   	
				}
				billingAddress = PDAddressHelper.getAddress(addressRequestEvent,billingAddress); 
				new Home().bind(billingAddress);
				juvServProvider.setBillingAddressId(billingAddress.getAddressId());
			}else if(addressTypeId != null && addressTypeId.equalsIgnoreCase(PDAdministerServiceProviderConstants.SERVICEPROVIDER_MAILINGADDRESS_TYPE)){   	
				Address mailingAddress = juvServProvider.getMailingAddress();
				
				if(mailingAddress == null){
					mailingAddress = new Address();   	
				}
				mailingAddress = PDAddressHelper.getAddress(addressRequestEvent,mailingAddress);  
				new Home().bind(mailingAddress);
				juvServProvider.setMailingAddressId(mailingAddress.getAddressId());
			}
		}
	}

	private void inactivateServiceProviderStatus(CreateServiceProviderEvent createEvent) {
		UpdateServiceProviderStatusRequestEvent updateSpEvent = (UpdateServiceProviderStatusRequestEvent) MessageUtil.filterComposite(createEvent, UpdateServiceProviderStatusRequestEvent.class); 

		JuvenileServiceProvider juvServProv = JuvenileServiceProvider.find((new Integer(updateSpEvent.getServiceProviderId())).intValue());
		juvServProv.updateJuvServProvStatus(updateSpEvent, true);				
	}
	/**
	 * @param event
	 * @roseuid 446A2E440364
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 446A2E440370
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 446A2E440372
	 */
	public void update(Object anObject)
	{

	}
}
/*
 * Created on Oct 3, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.supervision.administerserviceprovider.transactions;

import java.util.ArrayList;
import java.util.Iterator;

import messaging.administerserviceprovider.ValidateServiceNameEvent;
import messaging.administerserviceprovider.reply.ServiceProviderErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.SearchServiceProvider;
import naming.PDAdministerServiceProviderConstants;

/**
 * @roseuid 450ACDA502DE
 */
public class ValidateServiceNameCommand implements ICommand{

	public ValidateServiceNameCommand()
	{

	}
	
	   /**
	    * @param event
	    * @roseuid 450AA17901AA
	    */
	public void execute(IEvent event) {
		ValidateServiceNameEvent validateEvent = (ValidateServiceNameEvent) event;
		
		// check serviceProviderId
		if(validateEvent.getServiceProviderId() != null && !(validateEvent.getServiceProviderId().equals(""))){
			if(this.checkServiceName(validateEvent)){
				return;
			}
		}
		
	}
	private boolean checkServiceName(ValidateServiceNameEvent validateEvent) {
		Iterator iter = SearchServiceProvider.findAll(PDAdministerServiceProviderConstants.JUV_SERVICE_PROVIDER_ID, validateEvent.getServiceProviderId());
		ArrayList listServiceNames = new ArrayList();
		while(iter.hasNext()){
			SearchServiceProvider searchServiceProvider = (SearchServiceProvider)iter.next();
			if(!(searchServiceProvider.getServiceStatusId()).equalsIgnoreCase(PDAdministerServiceProviderConstants.INACTIVE)) {
				listServiceNames.add(searchServiceProvider.getServiceName());
			}
		}
		for(int j=0; j<listServiceNames.size(); j++){
			if(listServiceNames.get(j).toString().equalsIgnoreCase(validateEvent.getServiceName())) {
				this.sendServiceProviderErrorResponseEvent("error.duplicate.serviceName");
				return true;
			}					
		}
		return false;
	}
		/**
	 * @param errorKey
	 */
	private void sendServiceProviderErrorResponseEvent(String errorKey)
	{
		ServiceProviderErrorResponseEvent errorEvent = new ServiceProviderErrorResponseEvent();		
		errorEvent.setMessage(errorKey);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(errorEvent);
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub
		
	}

}

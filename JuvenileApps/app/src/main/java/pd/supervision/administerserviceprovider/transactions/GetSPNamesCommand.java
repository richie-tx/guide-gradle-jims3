/**
 * 
 */
package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetSPNamesEvent;
import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.ServiceProviderwithPrograms;

/**
 * @author cc_vnarsingoju
 *
 */
public class GetSPNamesCommand implements ICommand {

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception {
		GetSPNamesEvent spEvent = (GetSPNamesEvent)event;
		//Following was added instead of ServiceProvider entity for task 96471  variables are getting stripped from CSJUVSERVPROV table
		Iterator iter = ServiceProviderwithPrograms.findAll(spEvent);
		//Iterator iter = JuvenileServiceProvider.findAll(spEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()) {
		    ServiceProviderwithPrograms serviceProvider = (ServiceProviderwithPrograms) iter.next();		
		    ServiceProviderResponseEvent serviceProviderResponseEvent = new ServiceProviderResponseEvent();
		    serviceProviderResponseEvent.setJuvServProviderName(serviceProvider.getServiceProviderName());
		    String providerOID = serviceProvider.getOID();
		    serviceProviderResponseEvent.setJuvServProviderId(providerOID);
		    serviceProviderResponseEvent.setInHouse(serviceProvider.getInHouse());
		    //Following was added for Bug #93970 - in Production variables are getting stripped from CSJUVSERVPROV table
		    //logs show that it is happening after this command is called
		    //somehow the application thinks there is an update under certain circumstances
		    //retrieving the object so that all variables are there even if there is an update
		    //serviceProvider = JuvenileServiceProvider.find(Integer.parseInt(providerOID));commented this too as we have a new entity now after confirming with Uma
		    dispatch.postEvent(serviceProviderResponseEvent);	 
		}  

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

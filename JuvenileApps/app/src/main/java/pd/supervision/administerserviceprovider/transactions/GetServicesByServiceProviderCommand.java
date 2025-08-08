//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServiceLocationsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetServicesByServiceProviderEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;

public class GetServicesByServiceProviderCommand implements ICommand
{

	/**
	 * @roseuid 44805C7E0077
	 */
	public GetServicesByServiceProviderCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 447F4996016E
	 */
	public void execute(IEvent event)
	{
		GetServicesByServiceProviderEvent spEvent = 
			  (GetServicesByServiceProviderEvent)event;

		Iterator iter = JuvenileServiceProvider.findAll(spEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			JuvenileServiceProvider juvenileServiceProvider = (JuvenileServiceProvider) iter.next();		
		    ServiceResponseEvent serviceResponseEvent = 
							new ServiceResponseEvent();		    
		    serviceResponseEvent.setServiceId(juvenileServiceProvider.getServiceId()+"");		    
			dispatch.postEvent(serviceResponseEvent);	 
		}
	}

	/**
	 * @param event
	 * @roseuid 447F49960170
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 447F4996017D
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 447F4996017F
	 */
	public void update(Object anObject)
	{

	}
}
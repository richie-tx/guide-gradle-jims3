//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administerserviceprovider\\transactions\\GetServiceProviderServiceLocationsCommand.java

package pd.supervision.administerserviceprovider.transactions;

import java.util.Iterator;

import messaging.administerserviceprovider.GetServiceProviderEvent;
import messaging.administerserviceprovider.reply.JuvenileServiceProviderResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.administerserviceprovider.PDAdminsterServiceProviderHelper;

public class GetServiceProviderCommand implements ICommand
{

	/**
	 * @roseuid 44805C7E0077
	 */
	public GetServiceProviderCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 447F4996016E
	 */
	public void execute(IEvent event)
	{
		GetServiceProviderEvent serviceProviderEvent = 
			  (GetServiceProviderEvent)event;

		Iterator iter = JuvenileServiceProvider.findAll(serviceProviderEvent);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext()){
			JuvenileServiceProvider juvenileServiceProvider = (JuvenileServiceProvider) iter.next();		
		    JuvenileServiceProviderResponseEvent serviceProviderResponseEvent = 
							PDAdminsterServiceProviderHelper.getJuvenileServiceProviderResponseEvent(juvenileServiceProvider);				
						dispatch.postEvent(serviceProviderResponseEvent);	 
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
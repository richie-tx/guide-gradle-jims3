/*
 * Created on Feb 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant.transactions;

import java.util.Iterator;

import pd.juvenilewarrant.JuvenileWarrantService;
import pd.juvenilewarrant.PDJuvenileWarrantHelper;

import messaging.juvenilewarrant.GetJuvenileWarrantServiceInfoEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantServiceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 * 
 *	This command will reurn the successful service attempt for a warrant number
 */
public class GetJuvenileWarrantServiceInfoCommand  implements ICommand
{

	/**
	 * @roseuid 41FFDAC30261
	 */
	public GetJuvenileWarrantServiceInfoCommand() 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 41FFC64E006E
	 */
	public void execute(IEvent event) 
	{
		GetJuvenileWarrantServiceInfoEvent requestEvent = (GetJuvenileWarrantServiceInfoEvent)event;
		Iterator services = JuvenileWarrantService.findAll(requestEvent);

		//	Get the IDispatch to post the event
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		// There will be only one successful Service
		if (services.hasNext())
		{
			JuvenileWarrantService service = (JuvenileWarrantService) services.next();

			JuvenileWarrantServiceResponseEvent warrantServiceEvent = PDJuvenileWarrantHelper.getJuvenileWarrantServiceResponseEvent(service);
			if (warrantServiceEvent != null)
			{
				dispatch.postEvent(warrantServiceEvent);
			}
		}
	}
   
	/**
	 * @param event
	 * @roseuid 41FFC64E0070
	 */
	public void onRegister(IEvent event) 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 41FFC64E0072
	 */
	public void onUnregister(IEvent event) 
	{
    
	}
   
	/**
	 * @param updateObject
	 * @roseuid 41FFDAC30271
	 */
	public void update(Object updateObject) 
	{
    
	}

}

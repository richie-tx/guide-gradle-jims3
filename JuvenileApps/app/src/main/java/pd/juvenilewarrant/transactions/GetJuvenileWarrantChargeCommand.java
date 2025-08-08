/*
 * Created on Feb 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilewarrant.transactions;

import messaging.juvenilewarrant.GetJuvenileWarrantChargeEvent;
import messaging.juvenilewarrant.reply.ChargeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilewarrant.Charge;

/**
 * @author asrvastava
 * 
 *	This command will reurn the successful service attempt for a warrant number
 */
public class GetJuvenileWarrantChargeCommand  implements ICommand
{

	/**
	 * @roseuid 41FFDAC30261
	 */
	public GetJuvenileWarrantChargeCommand() 
	{
    
	}
   
	/**
	 * @param event
	 * @roseuid 41FFC64E006E
	 */
	public void execute(IEvent event) 
	{
		GetJuvenileWarrantChargeEvent requestEvent = (GetJuvenileWarrantChargeEvent)event;
		Charge charge = Charge.find("warrantNum",requestEvent.getWarrantNum());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if ( charge != null )
		{

		    ChargeResponseEvent response = charge.valueObject( true );
		    dispatch.postEvent(response);
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

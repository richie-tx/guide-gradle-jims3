package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileCoreLightEvent;
import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileCore;

public class GetJuvenileCoreLightCommand implements ICommand
{

	// ------------------------------------------------------------------------
	// --- constructor ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42A74EFF030D
	 */
	public GetJuvenileCoreLightCommand()
	{

	} 

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000BD
	 */
	public void execute(IEvent event)
	{
	    GetJuvenileInfoLightEvent juvEvent = (GetJuvenileInfoLightEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		JuvenileCore juvCore = JuvenileCore.findCore( juvEvent.getJuvenileNum() );
		
		if( juvCore != null ){

		    JuvenileCoreLightResponseEvent response =  juvCore.valueObject();
		    dispatch.postEvent(response);
		}

	}



	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000CB
	 */
	public void onRegister(IEvent event)
	{

	} // end of pd.juvenile.transactions.GetJuvenileProfileMainCommand.onRegister

	/**
	 * 
	 * @param event
	 * @roseuid 42A5DD9000CD
	 */
	public void onUnregister(IEvent event)
	{

	} // end of pd.juvenile.transactions.GetJuvenileProfileMainCommand.onUnregister



} // end GetJuvenileProfileMainCommand

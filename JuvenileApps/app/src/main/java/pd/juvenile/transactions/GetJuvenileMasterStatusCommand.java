package pd.juvenile.transactions;

import pd.juvenile.JuvenileMasterStatus;
import messaging.juvenile.GetJuvenileMasterStatusEvent;
import messaging.juvenile.reply.JuvenileMasterStatusResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author ryoung
 *
 */

public class GetJuvenileMasterStatusCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC4001F
	 */
	public GetJuvenileMasterStatusCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18307035E
	 */
	public void execute(IEvent event)
	{

		GetJuvenileMasterStatusEvent requestEvent = (GetJuvenileMasterStatusEvent) event;
		
		 //	Get the IDispatch to post to
	        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	        
	        JuvenileMasterStatusResponseEvent response = new JuvenileMasterStatusResponseEvent();
	        
		String statusId = "";
		JuvenileMasterStatus status = JuvenileMasterStatus.find( requestEvent.getJuvenileNum() );
		if (status != null)
		{
		    response.setStatusId( status.getStatusId() );
		    response.setJuvenileId( status.getOID());
		}
		dispatch.postEvent(response);

	}

	

	/**
	    * @param event
	    * @roseuid 42B183070360
	    */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18307036C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42B18307036E
	 */
	public void update(Object anObject)
	{

	}

}

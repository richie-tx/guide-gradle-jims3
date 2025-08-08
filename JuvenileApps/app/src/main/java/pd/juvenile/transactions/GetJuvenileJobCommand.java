package pd.juvenile.transactions;

import messaging.juvenile.GetJuvenileJobEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHelper;
import pd.juvenile.JuvenileJob;

/**
 * 
 * Name of the ResponseEvent: messaging.juvenile.reply.JuvenileJobResponseEvent
 */
public class GetJuvenileJobCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC4001F
	 */
	public GetJuvenileJobCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18307035E
	 */
	public void execute(IEvent event)
	{
		GetJuvenileJobEvent requestEvent = (GetJuvenileJobEvent) event;
		
		JuvenileJob job = JuvenileJob.find( requestEvent.getJobId() );
		JuvenileJobResponseEvent jobRespEvent = JuvenileHelper.getJuvenileJobResponseEvent(job);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(jobRespEvent);
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

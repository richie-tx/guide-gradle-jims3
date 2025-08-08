package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileHelper;
import pd.juvenile.JuvenileJob;

/**
 * 
 * @author sprakash
 *
 * This command retrieves all the Juvenile Jobs for a juvenile.
 * 
 * It returns one response event per Juvenile Job that matches.
 * The response event only has those attributes that are being displayed
 * on the search results page. For all the other attributes being 
 * displayed on the detail screen, the UI will invoke GetJuvenileJobsCommand.
 * 
 * Name of the ResponseEvent: messaging.juvenile.reply.JuvenileJobResponseEvent
 * 
 */

public class GetJuvenileJobsCommand implements ICommand
{

	/**
	 * @roseuid 42B18DC4001F
	 */
	public GetJuvenileJobsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42B18307035E
	 */
	public void execute(IEvent event)
	{

		GetJuvenileJobsEvent requestEvent = (GetJuvenileJobsEvent) event;
		Iterator jobs = JuvenileJob.findAll("juvenileId", requestEvent.getJuvenileNum());

		//	Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		//	Iterate through the warrants and post the JuvenileWarrantServiceResponseEvent for each
		while (jobs.hasNext())
		{
			JuvenileJob job = (JuvenileJob) jobs.next();
			JuvenileJobResponseEvent jobRespEvent = JuvenileHelper.getJuvenileJobResponseEvent(job);
			if (jobRespEvent != null)
			{
				dispatch.postEvent(jobRespEvent);
			}
		}

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

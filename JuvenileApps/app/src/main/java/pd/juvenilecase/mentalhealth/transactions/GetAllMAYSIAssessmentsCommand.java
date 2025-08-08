//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetAllMAYSIAssessmentsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetAllMAYSIAssessmentsEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.mentalhealth.JuvenileSubMAYSI;
import pd.supervision.administerserviceprovider.administerlocation.Location;

/**
 * 
 * @author dapte
 *
 * This command retrieves all the MAYSI assessments for a juvenile.
 * 
 * It returns one response event per MAYSI assignment that matches.
 * The response event only has those attributes that are being displayed
 * on the search results page. For all the other attributes being 
 * displayed on the detail screen, the Ui will invoke GetMAYSIDetailCommand.
 * 
 * Name of the ResponseEvent: messaging.juvenilecase.reply.MAYSISearchResultResponseEvent
 * 
 */
public class GetAllMAYSIAssessmentsCommand implements ICommand
{

	/**
	 * @roseuid 42791F8D00BB
	 */
	public GetAllMAYSIAssessmentsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5603BA
	 */
	public void execute(IEvent event)
	{
		GetAllMAYSIAssessmentsEvent mEvent = (GetAllMAYSIAssessmentsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator juvMAYSIItertor = JuvenileMAYSIComplete.findAll(mEvent);
		while (juvMAYSIItertor.hasNext())
		{
			JuvenileMAYSIComplete juvMAYSI = (JuvenileMAYSIComplete) juvMAYSIItertor.next();
			MAYSISearchResultResponseEvent maysiEvent = juvMAYSI.getSearchResponseEvent();
			dispatch.postEvent(maysiEvent);
		}

	}

	

	/**
	 * @param event
	 * @roseuid 42791D5603BC
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5603BE
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42791D5603C0
	 */
	public void update(Object anObject)
	{

	}

}

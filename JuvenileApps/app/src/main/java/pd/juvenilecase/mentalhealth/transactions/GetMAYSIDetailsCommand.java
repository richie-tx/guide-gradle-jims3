//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetMAYSIDetailsCommand.java

package pd.juvenilecase.mentalhealth.transactions;

import java.util.Iterator;

import messaging.mentalhealth.GetMAYSIDetailsEvent;
import messaging.mentalhealth.reply.MAYSIDetailsResponseEvent;
import messaging.mentalhealth.reply.MAYSISearchResultResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.juvenilecase.mentalhealth.JuvenileMAYSI;
import pd.juvenilecase.mentalhealth.JuvenileMAYSIComplete;
import pd.juvenilecase.mentalhealth.JuvenileSubMAYSI;
import pd.supervision.administerserviceprovider.administerlocation.Location;

/**
 * 
 * @author dapte
 *
 * This command retrieves the details for a particular MAYSI assignment.
 * It uses the sequence number to locate the MAYSI record and pull
 * its details. It also retrieves the Subsequent Assessment metadata related 
 * to the MAYSI assessment.
 * 
 * The response event has all the attributes that will be 
 * displayed on the detail screen.
 * 
 * Name of the ResponseEvent: messaging.juvenilecase.reply.MAYSIDetailsResponseEvent 
 * 
 */
public class GetMAYSIDetailsCommand implements ICommand
{

	/**
	 * @roseuid 42791F8E032C
	 */
	public GetMAYSIDetailsCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5701A7
	 */
	public void execute(IEvent event)
	{
		GetMAYSIDetailsEvent mEvent = (GetMAYSIDetailsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator juvMAYSIItertor = JuvenileMAYSIComplete.findAll(mEvent);
		if (juvMAYSIItertor.hasNext())
		{
			JuvenileMAYSIComplete juvMAYSI = (JuvenileMAYSIComplete) juvMAYSIItertor.next();
			MAYSIDetailsResponseEvent maysiEvent = juvMAYSI.getResponseEvent();
			dispatch.postEvent(maysiEvent);
		}

	}

	

	/**
	 * @param event
	 * @roseuid 42791D5701A9
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42791D5701B5
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42791D5701B7
	 */
	public void update(Object anObject)
	{

	}

}

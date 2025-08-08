//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefilesCommand.java

package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import messaging.juvenilecase.GetJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefilesCommand implements ICommand
{

	/**
	 * @roseuid 42B85504004C
	 */
	public GetJuvenileCasefilesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2E001F
	 */
	public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetJuvenileCasefilesEvent getEvent = (GetJuvenileCasefilesEvent) event;
		Iterator i = JuvenileCasefile.findAll(getEvent);

		if (i.hasNext())
		{
			while (i.hasNext())
			{
				JuvenileCasefile casefile = (JuvenileCasefile) i.next();
				JuvenileCasefileResponseEvent casefileResponse =
					JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
				dispatch.postEvent(casefileResponse);
			}
		}
		else
		{
			NoJuvenileCasefilesResponseEvent none = new NoJuvenileCasefilesResponseEvent();
			none.setMessage("No casefiles found for Juvenile Number: " + getEvent.getJuvenileNum());
			dispatch.postEvent(none);
		}
	}

	/**
	 * @param event
	 * @roseuid 42A70E2E0021
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 42A70E2E0023
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param updateObject
	 * @roseuid 42B85504005B
	 */
	public void update(Object updateObject)
	{

	}
}

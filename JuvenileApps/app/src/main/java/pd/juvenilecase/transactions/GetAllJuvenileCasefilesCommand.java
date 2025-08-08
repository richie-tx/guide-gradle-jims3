package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import messaging.juvenilecase.GetAllJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllJuvenileCasefilesCommand implements ICommand
{
    public GetAllJuvenileCasefilesCommand(){}
    
    public void execute(IEvent event)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetAllJuvenileCasefilesEvent getEvent = (GetAllJuvenileCasefilesEvent) event;
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

}

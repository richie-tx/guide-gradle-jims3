package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetActiveCasefilesByJuvenileIdEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;

public class GetActiveCasefilesByJuvenileIdCommand implements ICommand{
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetActiveCasefilesByJuvenileIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event)
	{
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    
	    GetActiveCasefilesByJuvenileIdEvent officerEvent = (GetActiveCasefilesByJuvenileIdEvent) event;
	    Iterator casefiles = JuvenileCasefile.findAll( officerEvent );
	    
	    while( casefiles.hasNext() ){
		
		JuvenileCasefile casefile = (JuvenileCasefile) casefiles.next();
		
		JuvenileCasefileResponseEvent casefileResponse =
			JuvenileCaseHelper.getJuvenileCasefileResponse(casefile);
		
		//post response
		dispatch.postEvent(casefileResponse);
	    }
	    
	}
	

	public void onRegister(IEvent event)
	{

	}

	public void onUnregister(IEvent event)
	{

	}

	public void update(Object anObject)
	{

	}
}

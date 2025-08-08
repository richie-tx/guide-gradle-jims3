package pd.juvenile.transactions;

import java.util.Iterator;

import messaging.juvenile.GetJuvenileGangsEvent;
import messaging.juvenile.reply.JuvenileGangsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenile.JuvenileGangs;
import pd.juvenile.JuvenileHelper;
/**
 * 
 * @author sthyagarajan
 * GetJuvenileGangsCommand
 */
public class GetJuvenileGangsCommand implements ICommand { 

	
	 /**
	 * Constructor.
	 */
	public GetJuvenileGangsCommand() 
	 {
	    
	 }
	
	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	@Override
	public void execute(IEvent event) throws Exception {
		GetJuvenileGangsEvent requestEvent = (GetJuvenileGangsEvent)event;
		//call the service to get from JCGANGS TABLE for the corresponding juvenileId.
		Iterator gangs = JuvenileGangs.findAll("juvenileId", requestEvent.getJuvenileNum());
		
		//Get the IDispatch to post to
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		//For each gang, set the response.
		while (gangs.hasNext())
		{
			JuvenileGangs gang = (JuvenileGangs) gangs.next();
			JuvenileGangsResponseEvent gangRespEvent = JuvenileHelper.getJuvenileGangsResponseEvent(gang);
			if (gangRespEvent != null)
			{
				dispatch.postEvent(gangRespEvent);
			}
		}    
	}
}

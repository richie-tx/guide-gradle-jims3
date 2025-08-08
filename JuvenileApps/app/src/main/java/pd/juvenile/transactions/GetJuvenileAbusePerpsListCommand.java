package pd.juvenile.transactions;
import java.util.Collection;
import java.util.Iterator;

import pd.juvenile.JuvenileAbusePerpatrator;
import messaging.juvenile.GetJuvenileAbusePerpsListEvent;
import messaging.juvenile.reply.JuvenileAbusePerpatratorResponseEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileAbusePerpsListCommand implements ICommand{

	@Override
	public void execute(IEvent event) throws Exception {
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator abusePerps = JuvenileAbusePerpatrator.findJuvenileAbusePerps((GetJuvenileAbusePerpsListEvent) event);
		while (abusePerps.hasNext())
		{
			JuvenileAbusePerpatrator abusePerp = (JuvenileAbusePerpatrator) abusePerps.next();
			Collection<JuvenileAbusePerpatrator> abusePrps = null;
			abusePrps.add(abusePerp);
			
			JuvenileAbusePerpatratorResponseEvent abusePerpResponse = abusePerp.getValueObject();
			dispatch.postEvent(abusePerpResponse);
		}*/
		
	}
	
}

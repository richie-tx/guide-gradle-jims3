package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import pd.supervision.cscdcalendar.FieldVisitEvent;
import messaging.cscdcalendar.GetCSFieldVisitIdEvent;
import messaging.cscdcalendar.reply.CSFieldVisitIdResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

/**
 * 
 * @author cc_bjangay
 *
 */
public class GetCSFieldVisitIdCommand implements ICommand
{
	
	/**
	 * 
	 */
	public void execute(IEvent anEvent)
	{
		GetCSFieldVisitIdEvent csFieldVisitIdEvent = (GetCSFieldVisitIdEvent)anEvent;
		
		String fieldVisitId = null;
		
		Iterator it = FieldVisitEvent.findAll("csEventId", csFieldVisitIdEvent.getCsEventId());
		while(it != null && it.hasNext())
		{
			FieldVisitEvent fieldVisit = (FieldVisitEvent)it.next();
			fieldVisitId = fieldVisit.getOID();
		}
		
		CSFieldVisitIdResponseEvent responseEvent = new CSFieldVisitIdResponseEvent();
		responseEvent.setCsFieldVisitId(fieldVisitId);
		
		MessageUtil.postReply(responseEvent);
		
	}//end of execute()
		

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}

}

package pd.supervision.cscdcalendar.transactions;

import java.util.Iterator;

import messaging.cscdcalendar.GetCSEventCasenotesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteHelper;

public class GetCSEventCasenotesCommand implements ICommand{

	
		@Override
	public void execute(IEvent event) throws Exception {
		
			GetCSEventCasenotesEvent casenoteEvent = (GetCSEventCasenotesEvent)event;
			CasenoteHelper helper = CasenoteHelper.getInstance();
			
			String eventId = casenoteEvent.getCsEventId();
			
			if( eventId != null)
			{
				Iterator casenotesIter = Casenote.findAll("csEventId", eventId);
				helper.postCasenoteResponses(casenotesIter);
			}
		}
		
}

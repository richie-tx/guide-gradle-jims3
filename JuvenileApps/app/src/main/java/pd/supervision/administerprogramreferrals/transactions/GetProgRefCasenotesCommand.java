package pd.supervision.administerprogramreferrals.transactions;

import java.util.Iterator;

import pd.supervision.administercasenotes.Casenote;
import pd.supervision.administercasenotes.CasenoteHelper;
import messaging.administerprogramreferrals.GetProgRefCasenotesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class GetProgRefCasenotesCommand implements ICommand
{

	public void execute(IEvent event) throws Exception 
	{
		GetProgRefCasenotesEvent prCasenoteEvent = (GetProgRefCasenotesEvent)event;
		CasenoteHelper helper = CasenoteHelper.getInstance();
		
		String progRefId = prCasenoteEvent.getProgramReferralId();
		
		if(progRefId != null)
		{
			Iterator casenotesIter = Casenote.findAll("programReferralId", progRefId);
			helper.postCasenoteResponses(casenotesIter);
		}
	}

}

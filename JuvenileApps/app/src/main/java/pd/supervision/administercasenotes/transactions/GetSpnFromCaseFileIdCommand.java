package pd.supervision.administercasenotes.transactions;

import messaging.administercasenotes.GetSpnFromCaseFileIdEvent;
import messaging.administercasenotes.reply.SpnFromCaseFileIdResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;
import pd.criminalcase.CriminalCase;

public class GetSpnFromCaseFileIdCommand implements ICommand {

	public void execute(IEvent event) {
		GetSpnFromCaseFileIdEvent myEvent = (GetSpnFromCaseFileIdEvent) event;
		CriminalCase criminalCase = CriminalCase.find(myEvent.getCaseFileId());
		SpnFromCaseFileIdResponseEvent response = new SpnFromCaseFileIdResponseEvent();
		if(criminalCase != null)
			response.setSpn(criminalCase.getDefendantId());
		EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);
	}

}

package pd.supervision.programreferral.transactions;

import java.util.Iterator;
import messaging.programreferral.GetJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

public class GetJuvenileProgramReferralAssignmentHistoryCommand implements ICommand  {


	public void execute(IEvent event) throws Exception {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetJuvenileProgramReferralAssignmentHistoryEvent prHistoryEvent = (GetJuvenileProgramReferralAssignmentHistoryEvent) event;
		Iterator iter = JuvenileProgramReferralAssignmentHistory.findAll("programReferralId", prHistoryEvent.getProgramReferralId());
		JuvenileProgramReferralAssignmentHistory prAssignmentHistory = null;

		while (iter.hasNext()) {
			prAssignmentHistory = (JuvenileProgramReferralAssignmentHistory) iter.next();

			if (prAssignmentHistory != null) {
				ProgramReferralAssignmentHistoryResponseEvent resp = prAssignmentHistory.getValueObject(true);
				dispatch.postEvent(resp);
			}
		}	
	}
}

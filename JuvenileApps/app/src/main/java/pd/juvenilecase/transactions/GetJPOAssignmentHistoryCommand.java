package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetJPOAssignmentHistoryEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JPOAssignmentHistory;
import pd.juvenilecase.JuvenileCaseHelper;

public class GetJPOAssignmentHistoryCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetJPOAssignmentHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetJPOAssignmentHistoryEvent jpoAssignmentHistoryEvent = (GetJPOAssignmentHistoryEvent) event;
		Iterator iter = JPOAssignmentHistory.findAll(jpoAssignmentHistoryEvent);
		JPOAssignmentHistory jpoAssignmentHistory = null;

		while (iter.hasNext()) {
			jpoAssignmentHistory = (JPOAssignmentHistory) iter.next();

			if (jpoAssignmentHistory != null) {
				JPOAssignmentHistoryResponseEvent jpoAssignmentHistoryResponse = JuvenileCaseHelper
						.getJPOAssignmentHistoryResponseEvent(jpoAssignmentHistory);
				dispatch.postEvent(jpoAssignmentHistoryResponse);
			}
		}
	}

	/**
	 * @param event
	 * @roseuid 4278C7B8034F
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80359
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B80364
	 */
	public void update(Object anObject) {

	}
}

package pd.juvenilecase.transactions;

import java.util.Iterator;
import messaging.juvenilecase.GetCasefileAssignmentHistoryEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryViewResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JPOAssignmentHistoryView;
import pd.juvenilecase.JuvenileCaseHelper;

public class GetCasefileAssignmentHistoryCommand implements ICommand{
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetCasefileAssignmentHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetCasefileAssignmentHistoryEvent casefileAssignmentHistoryEvent = (GetCasefileAssignmentHistoryEvent) event;
		Iterator iter = JPOAssignmentHistoryView.findAll(casefileAssignmentHistoryEvent);
		JPOAssignmentHistoryView jpoAssignmentHistory = null;

		while (iter.hasNext()) {
			jpoAssignmentHistory = (JPOAssignmentHistoryView) iter.next();

			if (jpoAssignmentHistory != null) {
				JPOAssignmentHistoryViewResponseEvent jpoAssignmentHistoryResponse = JuvenileCaseHelper
						.getJPOAssignmentHistoryViewResponseEvent(jpoAssignmentHistory);
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

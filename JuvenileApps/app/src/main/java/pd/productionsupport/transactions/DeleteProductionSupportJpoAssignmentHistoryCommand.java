package pd.productionsupport.transactions;

import messaging.productionsupport.DeleteProductionSupportJpoAssignmentHistoryEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JPOAssignmentHistory;

public class DeleteProductionSupportJpoAssignmentHistoryCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public DeleteProductionSupportJpoAssignmentHistoryCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {

		DeleteProductionSupportJpoAssignmentHistoryEvent deleteJpoAssignmentHistoryEvent = (DeleteProductionSupportJpoAssignmentHistoryEvent)event;
		JPOAssignmentHistory assignmentHistory = JPOAssignmentHistory.find(deleteJpoAssignmentHistoryEvent.getAssignmentHistoryId());
		
		if(assignmentHistory != null){
			assignmentHistory.delete();
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

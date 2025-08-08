/*
 * Created on Mar 17, 2006
 *
 */
package pd.task.transactions;

import messaging.task.UpdateTaskStatusEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.task.Task;

/**
 * @author Jim Fisher
 *  
 */
public class UpdateTaskStatusCommand implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception {
		UpdateTaskStatusEvent event = (UpdateTaskStatusEvent) anEvent;
		Task task = Task.find(event.getTaskId());
		if (task != null) {
			task.setStatusId(event.getStatusCode());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject) {
		// TODO Auto-generated method stub

	}

}

// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\GetCSTaskListAdvancedSearchCommand.java

package pd.supervision.managetask.transactions;

import java.util.Iterator;
import messaging.managetask.GetCSTasksEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import pd.supervision.managetask.CSTask;

public class GetCSTasksCommand implements ICommand {

	/**
	 * @roseuid 463F300000E9
	 */
	public GetCSTasksCommand() {
	}

	/**
	 * @param event
	 * @roseuid 463F171F0176
	 */
	public void execute(IEvent event) {
		
		GetCSTasksEvent evt = (GetCSTasksEvent) event;

		CSTask task = null;
		CSTaskResponseEvent respEvt = null;

		Iterator i = new Home().findAll( evt, CSTask.class );
		while ( i.hasNext() ) {
			task = (CSTask) i.next();
			respEvt = task.getResponseEvent( );
			MessageUtil.postReply(respEvt);
		}
		
		// Clean up objects
		task = null;
		evt = null;
		i = null;
		respEvt = null;
	}


	/**
	 * @param event
	 * @roseuid 463F171F0178
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 463F171F017A
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param updateObject
	 * @roseuid 463F300000F8
	 */
	public void update(Object updateObject) {

	}
}

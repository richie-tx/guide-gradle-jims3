// Source file:
// C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\GetCSTaskListAdvancedSearchCommand.java

package pd.supervision.managetask.transactions;

import messaging.managetask.GetCSTaskByOIDEvent;
import messaging.managetask.reply.CSTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.utilities.MessageUtil;
import pd.supervision.managetask.CSTask;

public class GetCSTaskByOIDCommand implements ICommand {

	/**
	 * @roseuid 463F300000E9
	 */
	public GetCSTaskByOIDCommand() {
	}

	/**
	 * @param event
	 * @roseuid 463F171F0176
	 */
	public void execute(IEvent event) {
		
		GetCSTaskByOIDEvent evt = ( GetCSTaskByOIDEvent ) event;
		CSTaskResponseEvent respEvt = null;
		
		CSTask task = (CSTask) new Home().find( evt.getTaskId(),CSTask.class);
		if ( task != null ) {
			
			respEvt = task.getResponseEvent( );
			MessageUtil.postReply( respEvt );
		}
		evt = null;
		respEvt = null;
		task = null;
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

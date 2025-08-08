package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportNTTasksEvent;
import messaging.productionsupport.reply.ProductionSupportNTTasksResponseEvent;
import messaging.task.reply.TaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.task.Task;

public class GetProductionSupportNTTasksCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportNTTasksCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportNTTasksEvent getNTTasksEvent = (GetProductionSupportNTTasksEvent) event;
		Iterator ntTasksIter = Task.findAll(getNTTasksEvent);
		
		while(ntTasksIter != null && ntTasksIter.hasNext()){
			Task ntTask = (Task)ntTasksIter.next();	
			ProductionSupportNTTasksResponseEvent ntTaskResponseEvent = new ProductionSupportNTTasksResponseEvent();
			
			ntTaskResponseEvent.setTaskId(ntTask.getOID());
			ntTaskResponseEvent.setSeverityLevel(ntTask.getSeverityLevel());
			ntTaskResponseEvent.setSourceId(ntTask.getSourceIdentityId());
			ntTaskResponseEvent.setOwnerId(ntTask.getOwnerIdentityId());
			ntTaskResponseEvent.setStatusId(ntTask.getStatusId());
			ntTaskResponseEvent.setTaskSubject(ntTask.getTaskSubject());
			ntTaskResponseEvent.setTopic(ntTask.getTopic());
			ntTaskResponseEvent.setAcceptedDate(ntTask.getAcceptedDate());
			ntTaskResponseEvent.setClosedDate(ntTask.getClosedDate());
			ntTaskResponseEvent.setDueDate(ntTask.getDueDate());
			ntTaskResponseEvent.setSubmittedDate(ntTask.getSubmittedDate());
			
			//production support 
			if(ntTask.getCreateUserID() != null){
				ntTaskResponseEvent.setCreateUserID(ntTask.getCreateUserID());
			}
			if(ntTask.getCreateTimestamp() != null){
				ntTaskResponseEvent.setCreateDate(new Date(ntTask.getCreateTimestamp().getTime()));
			}
			if(ntTask.getUpdateUserID() != null){
				ntTaskResponseEvent.setUpdateUser(ntTask.getUpdateUserID());
			}
			if(ntTask.getUpdateTimestamp() != null){
				ntTaskResponseEvent.setUpdateDate(new Date(ntTask.getUpdateTimestamp().getTime()));
			}
			if(ntTask.getCreateJIMS2UserID() != null){
				ntTaskResponseEvent.setCreateJIMS2UserID(ntTask.getCreateJIMS2UserID());
			}
			if(ntTask.getUpdateJIMS2UserID() != null){
				ntTaskResponseEvent.setUpdateJIMS2UserID(ntTask.getUpdateJIMS2UserID());
			}
			dispatch.postEvent(ntTaskResponseEvent);
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

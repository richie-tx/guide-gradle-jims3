package pd.productionsupport.transactions;

import java.util.Date;
import java.util.Iterator;

import messaging.productionsupport.GetProductionSupportEventTasksEvent;
import messaging.productionsupport.reply.ProductionSupportEventTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.productionsupport.EventTask;

public class GetProductionSupportEventTasksCommand implements ICommand {
	/**
	 * @roseuid 4278CAAA00AA
	 */
	public GetProductionSupportEventTasksCommand() {

	}

	/**
	 * @param event
	 * @roseuid 4278C7B80346
	 */
	public void execute(IEvent event) {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		GetProductionSupportEventTasksEvent getEventTasksEvent = (GetProductionSupportEventTasksEvent) event;
		Iterator eventTasksIter = EventTask.findAll(getEventTasksEvent);
		
		while(eventTasksIter != null && eventTasksIter.hasNext()){
			EventTask eventTask = (EventTask)eventTasksIter.next();	
			ProductionSupportEventTaskResponseEvent eventTaskResponseEvent = new ProductionSupportEventTaskResponseEvent();
			
			eventTaskResponseEvent.setEventTaskId(eventTask.getOID());
			eventTaskResponseEvent.setExecuted(eventTask.getExecuted());
			eventTaskResponseEvent.setNextNoticeDate(eventTask.getNextNotificationDate());
			eventTaskResponseEvent.setFirstNoticeDate(eventTask.getFirstNotificationDate());
			if(eventTask.getNotificationEvent() != null){
				eventTaskResponseEvent.setTopic(eventTask.getNotificationEvent().getTopic());
			}
			eventTaskResponseEvent.setScheduleName(eventTask.getScheduleClassName());
			eventTaskResponseEvent.setTaskName(eventTask.getTaskName());
			eventTaskResponseEvent.setEventName(eventTask.getEventName());		
			eventTaskResponseEvent.setTaskStatus(eventTask.getTaskStatus());
			
			//production support 
			if(eventTask.getCreateUserID() != null){
				eventTaskResponseEvent.setCreateUserID(eventTask.getCreateUserID());
			}
			if(eventTask.getCreateTimestamp() != null){
				eventTaskResponseEvent.setCreateDate(new Date(eventTask.getCreateTimestamp().getTime()));
			}
			if(eventTask.getUpdateUserID() != null){
				eventTaskResponseEvent.setUpdateUser(eventTask.getUpdateUserID());
			}
			if(eventTask.getUpdateTimestamp() != null){
				eventTaskResponseEvent.setUpdateDate(new Date(eventTask.getUpdateTimestamp().getTime()));
			}
			if(eventTask.getCreateJIMS2UserID() != null){
				eventTaskResponseEvent.setCreateJIMS2UserID(eventTask.getCreateJIMS2UserID());
			}
			if(eventTask.getUpdateJIMS2UserID() != null){
				eventTaskResponseEvent.setUpdateJIMS2UserID(eventTask.getUpdateJIMS2UserID());
			}
			
			dispatch.postEvent(eventTaskResponseEvent);
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

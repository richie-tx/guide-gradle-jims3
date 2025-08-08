/*
 * Created on Feb 15, 2006
 *
 */
package ui.juvenilecase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import messaging.juvenilecase.reply.TaskResponseEvent;
import messaging.taskbrowser.GetAllActiveTasksEvent;
import messaging.taskbrowser.RemoveTaskEvent;
import messaging.taskbrowser.TaskIDEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.TaskBrowserControllerServiceNames;
import ui.common.UIUtil;

/**
 * @author dapte
 *
 */
public class UITaskBrowserHelper
{

	public static Collection fetchAllActiveTasks()
	{
		
		 GetAllActiveTasksEvent event = (GetAllActiveTasksEvent)EventFactory.getInstance(TaskBrowserControllerServiceNames.GETALLACTIVETASKS);
		 event.setTaskUserID(UIUtil.getCurrentUserID()); 
		 IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		dispatch.postEvent(event);

		CompositeResponse composite = (CompositeResponse) dispatch.getReply();;
		MessageUtil.processReturnException(composite);

		Collection tmpTasks = MessageUtil.compositeToCollection(composite, TaskResponseEvent.class);
		
		List tasks = new ArrayList();
		tasks.addAll( tmpTasks );
		
		Collections.sort( tasks, new Comparator() {
			public int compare( Object o1, Object o2 )
			{
				TaskResponseEvent t1 = (TaskResponseEvent)o1;
				TaskResponseEvent t2 = (TaskResponseEvent)o2;
				return t2.getCreationDate().compareTo(t1.getCreationDate());		
			}
		} );
		
		return tasks;
	}	
	

	public static void removeTasks(String[] selectedTasks) {
		RemoveTaskEvent composite = new RemoveTaskEvent();
		TaskIDEvent evt;
		for(int i=0; i<selectedTasks.length; i++) {
			evt = new TaskIDEvent();
			evt.setTaskID(selectedTasks[i]);
			composite.addRequest(evt);
		}

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(composite);
		CompositeResponse reply = (CompositeResponse) dispatch.getReply();;
		MessageUtil.processReturnException(reply);
					
	}
	
	
} 
//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\taskbrowser\\transactions\\GetAllActiveTasksCommand.java

package pd.juvenilecase.taskbrowser.transactions;

import java.util.Iterator;

import pd.juvenilecase.taskbrowser.PDTaskBrowserHelper;
import pd.notification.Task;

import messaging.juvenilecase.reply.TaskResponseEvent;
import messaging.taskbrowser.GetAllActiveTasksEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetAllActiveTasksCommand implements ICommand 
{
   
   /**
    * @roseuid 43E1125801B5
    */
   public GetAllActiveTasksCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43DE8D130027
    */
   public void execute(IEvent event) 
   {
		GetAllActiveTasksEvent mEvent = (GetAllActiveTasksEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator tasksIterator = Task.findAll(mEvent);
		while (tasksIterator.hasNext())
		{
			Task aTask = (Task) tasksIterator.next();
			PDTaskBrowserHelper.sendTaskResponseEvent(aTask);
		}
		// temporary code for testing the helper methods...
		// can be taken out ..
//    	PDTaskBrowserHelper.createTaskForActivateCasefile("158", "J2JNJ");
//    	PDTaskBrowserHelper.createTaskForReviewBenefits("7", "J2JNJ", "38");
		//PDTaskBrowserHelper.createTaskForReviewBenefits("7", "J2JNJ", "41");
    	
   }
   
   /**
    * @param event
    * @roseuid 43DE8D13002F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43DE8D130038
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43DE8D13003A
    */
   public void update(Object anObject) 
   {
    
   }
   

}

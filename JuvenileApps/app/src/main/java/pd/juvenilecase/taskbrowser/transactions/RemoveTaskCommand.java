//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\taskbrowser\\transactions\\RemoveTaskCommand.java

package pd.juvenilecase.taskbrowser.transactions;

import java.util.Date;
import java.util.Enumeration;

import pd.notification.Task;

import messaging.taskbrowser.RemoveTaskEvent;
import messaging.taskbrowser.TaskIDEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class RemoveTaskCommand implements ICommand 
{
   
   /**
    * @roseuid 43E11267024C
    */
   public RemoveTaskCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43DE8D1602BF
    */
   public void execute(IEvent event) 
   {
   		RemoveTaskEvent composite = (RemoveTaskEvent) event;
   		Enumeration requests = composite.getRequests();
   		TaskIDEvent evt;
   		Task task;
   		while(requests.hasMoreElements()) {
   			evt = (TaskIDEvent)requests.nextElement();
   			task = Task.find(evt.getTaskID());
   			if(task!=null) {
   				task.setTaskStatusId("C");
   				task.setCompletionDate(new Date());
   			}
   		}
    
   }
   
   /**
    * @param event
    * @roseuid 43DE8D1602D1
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43DE8D1602D3
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43DE8D1602DC
    */
   public void update(Object anObject) 
   {
    
   }
   

}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageworkgroup\\transactions\\ValidateWorkGroupCommand.java

package pd.supervision.manageworkgroup.transactions;

import java.util.Iterator;

import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.supervisionoptions.Condition;
import messaging.manageworkgroup.ValidateWorkGroupEvent;
import messaging.supervisionoptions.ValidateSupervisionConditionEvent;
import messaging.supervisionoptions.reply.DuplicationNameErrorEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class ValidateWorkGroupCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB612A022D
    */
   public ValidateWorkGroupCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B2403DF
    */
   public void execute(IEvent event) 
   {
        ValidateWorkGroupEvent validateEvent = (ValidateWorkGroupEvent)event;
        
		if(WorkGroup.isDuplicate(validateEvent))
		{
			// post duplication error event
			DuplicationNameErrorEvent errorEvent = new DuplicationNameErrorEvent();
			errorEvent.setName(validateEvent.getName());
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(errorEvent);
		}
       
   }
   
   /**
    * @param event
    * @roseuid 45DB5B2403E1
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250003
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB5B250005
    */
   public void update(Object anObject) 
   {
    
   }
   
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionoptions\\transactions\\ArchiveSupervisionConditionCommand.java

package pd.supervision.supervisionoptions.transactions;

import pd.supervision.supervisionoptions.Condition;
import messaging.supervisionoptions.ArchiveSupervisionConditionEvent;
import messaging.supervisionoptions.reply.ConditionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class ArchiveSupervisionConditionCommand implements ICommand 
{
   
   /**
    * @roseuid 4421BD6C007D
    */
   public ArchiveSupervisionConditionCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4421BCEB01E5
    */
   public void execute(IEvent event) 
   {
		ArchiveSupervisionConditionEvent reqEvent = (ArchiveSupervisionConditionEvent)event;
		Condition condition = Condition.find(reqEvent.getConditionId());
		condition.setIsArchived(true);
		
		ConditionResponseEvent reply = new ConditionResponseEvent();
		reply.setConditionId(condition.getOID().toString());
		reply.setArchived(condition.getIsArchived());

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(reply);

   }
   
   /**
    * @param event
    * @roseuid 4421BCEB01E7
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4421BCEB01E9
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4421BCEB01F4
    */
   public void update(Object anObject) 
   {
    
   }
   
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageworkgroup\\transactions\\GetWorkGroupCommand.java

package pd.supervision.manageworkgroup.transactions;

import pd.supervision.manageworkgroup.WorkGroup;
import pd.supervision.manageworkgroup.WorkGroupHelper;
import messaging.manageworkgroup.GetWorkGroupEvent;
import messaging.manageworkgroup.reply.WorkGroupResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.messaging.IEvent;

public class GetWorkGroupCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB6128027B
    */
   public GetWorkGroupCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B2502B8
    */
   public void execute(IEvent event) 
   {
       GetWorkGroupEvent getWorkGroupEvent = (GetWorkGroupEvent)event;
       WorkGroup workGroup = (WorkGroup)WorkGroup.find(getWorkGroupEvent.getWorkGroupId());
	   WorkGroupResponseEvent reply = WorkGroupHelper.getWorkGroupResponseEvent(workGroup);
	   EventManager.getSharedInstance(EventManager.REPLY).postEvent(reply);
   }
   
   /**
    * @param event
    * @roseuid 45DB5B2502C5
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B2502C7
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB5B2502D3
    */
   public void update(Object anObject) 
   {
    
   }
   
}

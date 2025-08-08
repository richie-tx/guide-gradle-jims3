//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageworkgroup\\transactions\\DeleteWorkGroupCommand.java

package pd.supervision.manageworkgroup.transactions;

import pd.supervision.manageworkgroup.WorkGroup;
import messaging.manageworkgroup.DeleteWorkGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteWorkGroupCommand implements ICommand 
{
   
   /**
    * @roseuid 45DB6127021E
    */
   public DeleteWorkGroupCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250303
    */
   public void execute(IEvent event) 
   {
       DeleteWorkGroupEvent getWorkGroupEvent = (DeleteWorkGroupEvent)event;
       WorkGroup workGroup = (WorkGroup)WorkGroup.find(getWorkGroupEvent.getWorkGroupId());
       workGroup.delete();
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250305
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45DB5B250307
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45DB5B250313
    */
   public void update(Object anObject) 
   {
    
   }
   
}

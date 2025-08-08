//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\manageworkgroup\\transactions\\SaveWorkGroupCommand.java

package pd.supervision.manageworkgroup.transactions;

import pd.supervision.manageworkgroup.WorkGroup;
import messaging.manageworkgroup.SaveWorkGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveWorkGroupCommand implements ICommand 
{
   
   /**
    * @roseuid 45EF32640086
    */
   public SaveWorkGroupCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45EF31F20180
    */
   public void execute(IEvent event) 
   {
       //create Workgroup
       SaveWorkGroupEvent reqEvent = (SaveWorkGroupEvent)event;
       WorkGroup workGroup = WorkGroup.save(reqEvent);
    
       // post response event
//       WorkGroupDetailResponseEvent reply = new WorkGroupDetailResponseEvent();
//       reply.setName(workGroup.getName());
//       IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
//       dispatch.postEvent(reply);
       
   }
   
   /**
    * @param event
    * @roseuid 45EF31F2018D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 45EF31F2018F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 45EF31F20191
    */
   public void update(Object anObject) 
   {
    
   }
   

}

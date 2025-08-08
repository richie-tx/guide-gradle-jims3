//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\managetask\\transactions\\CreateCSTaskCommand.java

package pd.supervision.managetask.transactions;

import java.io.IOException;

import pd.supervision.managetask.PDTaskHelper;
import messaging.managetask.CreateCSTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class CreateCSTaskCommand implements ICommand 
{
   
   /**
    * @roseuid 463F2FFD02FC
    */
   public CreateCSTaskCommand() 
   {
    
   }
   
   /**
    * @param event
 * @throws IOException
    * @roseuid 463F170F03A9
    */
   public void execute(IEvent anEvent) throws IOException
   {
   	
   	CreateCSTaskEvent event = (CreateCSTaskEvent) anEvent;
 	
  //DAG removing command chaining.
    PDTaskHelper.createCSTask(event);
    
   }
   
   /**
    * @param event
    * @roseuid 463F170F03AB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 463F170F03AD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   
   /**
    * @param updateObject
    * @roseuid 463F2FFD031B
    */
   public void update(Object updateObject) 
   {
    
   }
}

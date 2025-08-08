//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\CreateActivityCommand.java

package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.casefile.ActivityHelper;
import messaging.casefile.CreateActivityEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class CreateActivityCommand implements ICommand 
{
   
   /**
    * @roseuid 452131F10136
    */
   public CreateActivityCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4521267803A5
    */
   public void execute(IEvent event) 
   {
		CreateActivityEvent request = (CreateActivityEvent)event;
		ActivityHelper helper = new ActivityHelper();
   		helper.createActivity(request);
   		helper = null;

    
   }
   
   /**
    * @param event
    * @roseuid 4521267803AD
    */
   public void onRegister(IEvent event) 
   {
  		
   }
   
   /**
    * @param event
    * @roseuid 4521267803AF
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
 
   /**
    * @param updateObject
    * @roseuid 452131F10168
    */
   public void update(Object updateObject) 
   {
    
   }
}

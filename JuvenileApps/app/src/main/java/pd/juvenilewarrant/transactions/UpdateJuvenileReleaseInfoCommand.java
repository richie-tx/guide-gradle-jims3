//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\UpdateJuvenileReleaseInfoCommand.java

package pd.juvenilewarrant.transactions;

import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseInfoEvent;

/**
 * @author asrvastava
 * 
 * This class is used to save the Release decision. 
 */
public class UpdateJuvenileReleaseInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 41FFDAC90000
    */
   public UpdateJuvenileReleaseInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 41F95F4502B1
    */
   public void execute(IEvent event) 
   {
		UpdateJuvenileReleaseInfoEvent requestEvent = (UpdateJuvenileReleaseInfoEvent)event;
		PDJuvenileWarrantHelper.updateJuvenileReleaseDecision(requestEvent);
   }
   
   /**
    * @param event
    * @roseuid 41F95F4502B3
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 41F95F4502B5
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 41FFDAC9000F
    */
   public void update(Object updateObject) 
   {
    
   }
}

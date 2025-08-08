//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilewarrant\\transactions\\UpdateJuvenileReleaseToJPInfoCommand.java

package pd.juvenilewarrant.transactions;

import pd.juvenilewarrant.PDJuvenileWarrantHelper;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import messaging.juvenilewarrant.UpdateJuvenileReleaseToJPInfoEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateJuvenileReleaseToJPInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 41FFDACA0251
    */
   public UpdateJuvenileReleaseToJPInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 41F95F460282
    */
   public void execute(IEvent event) 
   {
		UpdateJuvenileReleaseToJPInfoEvent requestEvent = (UpdateJuvenileReleaseToJPInfoEvent)event;
		PDJuvenileWarrantHelper.updateJuvenileReleaseToJPInfo(requestEvent);
   }
   
   /**
    * @param event
    * @roseuid 41F95F460284
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 41F95F460286
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 41FFDACA0261
    */
   public void update(Object updateObject) 
   {
    
   }
}

package pd.juvenile.transactions;

import pd.juvenile.JuvenilePhysicalAttributes;
import messaging.juvenile.SaveJuvenilePhysicalAttributesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenilePhysicalAttributesCommand implements ICommand 
{
   
   /**
    * @roseuid 42B18DCA02FD
    */
   public SaveJuvenilePhysicalAttributesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830901D6
    */
   public void execute(IEvent event) 
   {
    	JuvenilePhysicalAttributes.create((SaveJuvenilePhysicalAttributesEvent)event);
   }

   
   /**
    * @param event
    * @roseuid 42B1830901D8
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B1830901E4
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42B1830901E6
    */
   public void update(Object anObject) 
   {
    
   }
   
}

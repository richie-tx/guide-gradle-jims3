//Source file: C:\\views\\dev\\app\\src\\pd\\juvenile\\transactions\\SaveJuvenileDrugsCommand.java

package pd.juvenile.transactions;

import java.util.Enumeration;

import pd.juvenile.JuvenileDrugs;

import messaging.juvenile.JuvenileDrugRequestEvent;
import messaging.juvenile.SaveJuvenileDrugsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class SaveJuvenileDrugsCommand implements ICommand 
{
   
   /**
    * @roseuid 42B18DC80290
    */
   public SaveJuvenileDrugsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B18308036B
    */
   public void execute(IEvent event) 
   {
		SaveJuvenileDrugsEvent reqEvent = (SaveJuvenileDrugsEvent)event;
		// this is a composite event
		Enumeration enumeration = reqEvent.getRequests();
		if(enumeration != null){
			while(enumeration.hasMoreElements()){
				JuvenileDrugRequestEvent drugEvent = (JuvenileDrugRequestEvent)enumeration.nextElement();
				JuvenileDrugs.create(drugEvent); 
			}
		}
    
   }
   
   /**
    * @param event
    * @roseuid 42B18308036D
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42B18308036F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42B183080371
    */
   public void update(Object anObject) 
   {
    
   }
   
}

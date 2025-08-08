//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\casefile\\transactions\\UpdateResponseCommand.java

package pd.juvenilecase.casefile.transactions;

import pd.juvenilecase.casefile.Response;
import messaging.casefile.UpdateResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author mchowdhury
*/

public class UpdateResponseCommand implements ICommand 
{
   
   /**
    * @roseuid 439602E602D9
    */
   public UpdateResponseCommand() 
   {
   
   }

   /**
    * @param event
    * @roseuid 4395C23900C9
    */
   public void execute(IEvent event) 
   {
	   UpdateResponseEvent updateEvent = (UpdateResponseEvent) event;
	   Response response = null;
	   if(updateEvent.getResponseId() != null && !updateEvent.getResponseId().equals("")){
		   response = Response.find(updateEvent.getResponseId());	   
	   }
	   
	   if(response == null){
	   	   response = new Response();
	   }
	   response.setResponse(updateEvent);
   }
   
   /**
    * @param event
    * @roseuid 4395C23900DD
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 4395C23900F2
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 4395C2390123
    */
   public void update(Object anObject) 
   {
    
   }
   
 
}

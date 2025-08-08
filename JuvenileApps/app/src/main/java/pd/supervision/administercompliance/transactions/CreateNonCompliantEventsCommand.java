//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\UpdateNonCompliantEaventCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.Enumeration;

import pd.supervision.administercompliance.NonComplianceEvent;
import pd.supervision.administercompliance.NonComplianceEventType;

import messaging.administercompliance.CreateNonCompliantEventEvent;
import messaging.administercompliance.CreateNonCompliantEventsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class CreateNonCompliantEventsCommand implements ICommand 
{
   
   /**
    * @roseuid 473B887F03AF
    */
   public CreateNonCompliantEventsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560388
    */
   public void execute(IEvent event) 
   {
   	   CreateNonCompliantEventsEvent uEvent = (CreateNonCompliantEventsEvent) event;
   	   Enumeration requests = uEvent.getRequests();
	   while (requests.hasMoreElements()){
	   	   CreateNonCompliantEventEvent unceEvent = (CreateNonCompliantEventEvent) requests.nextElement();
	   	   if(unceEvent != null){
	   	   	   NonComplianceEvent pObj = new NonComplianceEvent();
	   	       String key = pObj.setNonComplianceEvent(unceEvent);
	   	       for(int i=0;i<unceEvent.getEventTypeCodeIds().length; i++){
	   	       	   String eventTypeCodeId = unceEvent.getEventTypeCodeIds()[i];
	   	       	   NonComplianceEventType nType = new NonComplianceEventType(Integer.parseInt(key), eventTypeCodeId);
	   	       }
	   	   }
   	   }
   }
   
   /**
    * @param event
    * @roseuid 473B7556038A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B7556038C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560398
    */
   public void update(Object anObject) 
   {
    
   }
}

//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\agency\\transactions\\ValidateAgencyUpdateRequirementsCommand.java

package pd.contact.agency.transactions;

import java.util.Iterator;

import messaging.agency.ValidateAgencyUpdateRequirementsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.PDContactHelper;
import pd.contact.agency.Agency;

/**
 * @author mchowdhury
 * @description validate an agency for update -- duplicate check  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ValidateAgencyUpdateRequirementsCommand implements ICommand 
{
   
   /**
    * @roseuid 42C594150263
    */
   public ValidateAgencyUpdateRequirementsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F38001A
    */
   public void execute(IEvent event) 
   {
	/*  ValidateAgencyUpdateRequirementsEvent validateEvent = (ValidateAgencyUpdateRequirementsEvent) event;
	  Iterator iter = Agency.findAll(validateEvent);
	  while(iter.hasNext()){
	  	 iter.next();
		 PDContactHelper.sendDuplicateRecordErrorResponseEvent("Agency Name " + validateEvent.getAgencyName());
	  } */ //87191
   }
   
   /**
    * @param event
    * @roseuid 42C58F38001C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F38001E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
 
   /**
    * @param updateObject
    * @roseuid 42C594150282
    */
   public void update(Object updateObject) 
   {
    
   }
}

//Source file: C:\\views\\archproduction\\app\\src\\pd\\contact\\agency\\transactions\\ValidateAgencyCreateRequirementsCommand.java

package pd.contact.agency.transactions;

import java.util.Iterator;

import messaging.agency.ValidateAgencyCreateRequirementsEvent;
import messaging.agency.ValidateAgencyUpdateRequirementsEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.PDConstants;
import pd.contact.PDContactHelper;
import pd.contact.agency.Agency;
/**
 * @author mchowdhury
 * @description validate an agency for create -- duplicate check  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class ValidateAgencyCreateRequirementsCommand implements ICommand 
{
   
   /**
    * @roseuid 42C594130234
    */
   public ValidateAgencyCreateRequirementsCommand() {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F370137
    */
   public void execute(IEvent event){
	/*  ValidateAgencyCreateRequirementsEvent validateEvent = (ValidateAgencyCreateRequirementsEvent) event;
      Iterator iter = Agency.findAll(PDConstants.AGENCY_ID,validateEvent.getAgencyId());
	  while(iter.hasNext()){
		 iter.next();
		 PDContactHelper.sendDuplicateRecordErrorResponseEvent("Agency Code " + validateEvent.getAgencyId());
		 return;
	  }
 
	  ValidateAgencyUpdateRequirementsEvent validateUpdateEvent = new ValidateAgencyUpdateRequirementsEvent();
	  validateUpdateEvent.setAgencyName(validateEvent.getAgencyName());
	  Iterator it = Agency.findAll(validateUpdateEvent);
	  while(it.hasNext()){
	     it.next();
		 PDContactHelper.sendDuplicateRecordErrorResponseEvent("Agency Name " + validateEvent.getAgencyName());
	  } */ //87191
   }
   
   /**
    * @param event
    * @roseuid 42C58F370139
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F370143
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   
   /**
    * @param updateObject
    * @roseuid 42C594130253
    */
   public void update(Object updateObject) 
   {
    
   }
}

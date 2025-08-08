//Source file: C:\\views\\archproduction\\app\\src\\pd\\contact\\agency\\transactions\\UpdateAgencyCommand.java

package pd.contact.agency.transactions;

import messaging.agency.UpdateAgencyEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.agency.Agency;

/**
 * 
 * 
 * @author mchowdhury
 * @description create or update an agency  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class UpdateAgencyCommand implements ICommand 
{
   
   /**
    * @roseuid 42C594120040
    */
   public UpdateAgencyCommand() {
   }
   
   /**
    * @param event
    * @roseuid 42C58F3702FB
    */
   public void execute(IEvent event) {
	  UpdateAgencyEvent agencyEvent = (UpdateAgencyEvent) event;
	  Agency agency = Agency.find(agencyEvent.getAgencyId());
	  if (agency != null){
	     this.setAgencyParameters(agencyEvent, agency);
	  }else{
	  	 agency = new Agency();
		 this.setAgencyParameters(agencyEvent, agency);
		 agency.setAgencyId(agencyEvent.getAgencyId());
	  }
   }
   
   /**
    * @param agencyEvent
    * @param agency
    */
    private void setAgencyParameters(UpdateAgencyEvent agencyEvent, Agency agency){
	  /* agency.setAgencyName(agencyEvent.getAgencyName());
	   agency.setAgencyTypeId(agencyEvent.getAgencyTypeId());
	   agency.setProjectAnalystInd(agencyEvent.getJmcRep());*/ //87191
    }

/**
    * @param event
    * @roseuid 42C58F3702FD
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F370307
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 42C59412005F
    */
   public void update(Object updateObject) 
   {
    
   }
}

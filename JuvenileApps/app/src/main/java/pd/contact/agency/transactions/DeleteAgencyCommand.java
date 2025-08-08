//Source file: C:\\views\\archproduction\\app\\src\\pd\\contact\\agency\\transactions\\DeleteAgencyCommand.java

package pd.contact.agency.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.agency.DeleteAgencyEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.contact.agency.Agency;
import pd.contact.agency.Department;

/**
 * 
 * 
 * @author mchowdhury
 * @description delete an agency  
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class DeleteAgencyCommand implements ICommand 
{
   
   /**
    * @roseuid 42C593F3034D
    */
   public DeleteAgencyCommand() 
   {
       
   }
   
   /**
    * @param event
    * @roseuid 42C58F38024D
    */
   public void execute(IEvent event) 
   {
	  DeleteAgencyEvent agencyEvent = (DeleteAgencyEvent) event;
	  Agency agency = Agency.find(agencyEvent.getAgencyId());
	  if (agency != null){
	     // delete departments under the agency
	     this.deleteDepartsments(agency);
	     // delete the agency
	     agency.delete();
	  }
   }
   
   /**
   * @param agency
   */
   private void deleteDepartsments(Agency agency){
	  Collection departments = agency.getDepartments();
	  Iterator iter = departments.iterator();
	  while(iter.hasNext()){
		Department department = (Department) iter.next();
		if(department != null){
			department.delete();
		}
	  }
   } 

/**
    * @param event
    * @roseuid 42C58F38024F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42C58F380251
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   

   /**
    * @param updateObject
    * @roseuid 42C593F3036C
    */
   public void update(Object updateObject) 
   {
    
   }
}

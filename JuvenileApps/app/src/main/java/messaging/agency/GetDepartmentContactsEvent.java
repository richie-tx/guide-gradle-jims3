//Source file: C:\\views\\dev\\app\\src\\messaging\\agency\\GetDepartmentEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 * description get the depart contacts
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

public class GetDepartmentContactsEvent extends RequestEvent 
{
   private String departmentId;
   
  		   
   /**
    * @roseuid 42E67E4001F7
    */
   public GetDepartmentContactsEvent() 
   {
    
   }
   
   /**
    * Access method for the departmentId property.
    * 
    * @return   the current value of the departmentId property
    */
   public String getDepartmentId()
   {
      return departmentId;
   }
   
   /**
    * Sets the value of the departmentId property.
    * 
    * @param aDepartmentId the new value of the departmentId property
    */
   public void setDepartmentId(String aDepartmentId)
   {
      departmentId = aDepartmentId;
   }
   
}
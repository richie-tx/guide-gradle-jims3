//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\agency\\GetDepartmentsAndAgencyEvent.java

package messaging.agency;

import mojo.km.messaging.RequestEvent;

public class GetDepartmentsAndAgencyEvent extends RequestEvent 
{
   public String departmentId;
   public String departmentName;
   
   /**
    * @roseuid 442B042A00BB
    */
   public GetDepartmentsAndAgencyEvent() 
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
   
   /**
    * Access method for the departmentName property.
    * 
    * @return   the current value of the departmentName property
    */
   public String getDepartmentName()
   {
      return departmentName;
   }
   
   /**
    * Sets the value of the departmentName property.
    * 
    * @param aDepartmentName the new value of the departmentName property
    */
   public void setDepartmentName(String aDepartmentName)
   {
      departmentName = aDepartmentName;
   }
  
}
//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetVendorContactEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetVendorContactEvent extends RequestEvent 
{
   public String serviceProviderId;
   public String employeeId;
   
   /**
    * @roseuid 447458710020
    */
   public GetVendorContactEvent() 
   {
    
   }
   
   /**
    * Access method for the serviceProviderId property.
    * 
    * @return   the current value of the serviceProviderId property
    */
   public String getServiceProviderId()
   {
      return serviceProviderId;
   }
   
   /**
    * Sets the value of the serviceProviderId property.
    * 
    * @param aServiceProviderId the new value of the serviceProviderId property
    */
   public void setServiceProviderId(String aServiceProviderId)
   {
      serviceProviderId = aServiceProviderId;
   }
   
   /**
    * Access method for the employeeId property.
    * 
    * @return   the current value of the employeeId property
    */
   public String getEmployeeId()
   {
      return employeeId;
   }
   
   /**
    * Sets the value of the employeeId property.
    * 
    * @param aEmployeeId the new value of the employeeId property
    */
   public void setEmployeeId(String aEmployeeId)
   {
      employeeId = aEmployeeId;
   }
   

}

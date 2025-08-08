//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\ValidateServiceCodeEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class ValidateServiceCodeEvent extends RequestEvent 
{
   public String serviceCode;
   
   /**
    * @roseuid 447458820291
    */
   public ValidateServiceCodeEvent() 
   {
    
   }
   
   /**
    * Access method for the serviceCode property.
    * 
    * @return   the current value of the serviceCode property
    */
   public String getServiceCode()
   {
      return serviceCode;
   }
   
   /**
    * Sets the value of the serviceCode property.
    * 
    * @param aServiceCode the new value of the serviceCode property
    */
   public void setServiceCode(String aServiceCode)
   {
      serviceCode = aServiceCode;
   }
   
 
}

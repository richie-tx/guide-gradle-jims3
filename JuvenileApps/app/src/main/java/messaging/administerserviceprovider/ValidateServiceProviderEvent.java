//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\ValidateServiceProviderEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class ValidateServiceProviderEvent extends RequestEvent 
{
   public String agencyId;
   public String serviceProviderName;
   
   /**
    * @roseuid 4474588C0224
    */
   public ValidateServiceProviderEvent() 
   {
    
   }
   
   /**
    * Access method for the agencyId property.
    * 
    * @return   the current value of the agencyId property
    */
   public String getAgencyId()
   {
      return agencyId;
   }
   
   /**
    * Sets the value of the agencyId property.
    * 
    * @param aAgencyId the new value of the agencyId property
    */
   public void setAgencyId(String aAgencyId)
   {
      agencyId = aAgencyId;
   }
   
   /**
    * Access method for the serviceProviderName property.
    * 
    * @return   the current value of the serviceProviderName property
    */
   public String getServiceProviderName()
   {
      return serviceProviderName;
   }
   
   /**
    * Sets the value of the serviceProviderName property.
    * 
    * @param aServiceProviderName the new value of the serviceProviderName property
    */
   public void setServiceProviderName(String aServiceProviderName)
   {
      serviceProviderName = aServiceProviderName;
   }
   

}

//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetServiceLocationServiceProvidersEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;

public class GetServiceLocationServiceProvidersEvent extends RequestEvent 
{
   public int locationId;
   
   /**
    * @roseuid 451155DC0009
    */
   public GetServiceLocationServiceProvidersEvent() 
   {
    
   }
   
   /**
    * @param locationId
    * @roseuid 45104B4A021B
    */
   public void setLocationId(int locationId) 
   {
    this.locationId = locationId;
   }
   
   /**
    * @return String
    * @roseuid 45104B4A021D
    */
   public int getLocationId() 
   {
    return locationId;
   }
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerlocation\\ReactivateLocationEvent.java

package messaging.administerlocation;

import mojo.km.messaging.RequestEvent;

public class ReactivateLocationEvent extends RequestEvent 
{
   public String locationId;
   
   /**
    * @roseuid 451158750040
    */
   public ReactivateLocationEvent() 
   {
    
   }
   
   /**
    * Access method for the locationId property.
    * 
    * @return   the current value of the locationId property
    */
   public String getLocationId()
   {
      return locationId;
   }
   
   /**
    * Sets the value of the locationId property.
    * 
    * @param aLocationId the new value of the locationId property
    */
   public void setLocationId(String aLocationId)
   {
      locationId = aLocationId;
   }
   
}

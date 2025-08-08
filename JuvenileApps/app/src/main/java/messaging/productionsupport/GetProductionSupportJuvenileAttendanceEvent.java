//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetJuvenileAttendanceEvent.java

package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileAttendanceEvent extends RequestEvent 
{
   public String serviceEventId;
   
   /**
    * @roseuid 456F33E603CA
    */
   public GetProductionSupportJuvenileAttendanceEvent() 
   {
    
   }
   
   /**
    * Access method for the serviceEventId property.
    * 
    * @return   the current value of the serviceEventId property
    */
   public String getServiceEventId()
   {
      return serviceEventId;
   } 

   /**
    * @param serviceEventId
    * @roseuid 456F2D8502A1
    */
   public void setServiceEventId(String serviceEventId) 
   {
    this.serviceEventId = serviceEventId;
   }
 
}

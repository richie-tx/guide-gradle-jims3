//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetJuvenileAttendanceEvent.java

package messaging.calendar;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAttendanceEvent extends RequestEvent 
{
   public String serviceEventId;
   public String juvenileId;
   
   /**
    * @roseuid 456F33E603CA
    */
   public GetJuvenileAttendanceEvent() 
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
   

	/**
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		return juvenileId;
	}
	/**
	 * @param juvenileId The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}
}

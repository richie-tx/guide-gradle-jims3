//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\calendar\\GetServiceLocationServiceEventsEvent.java

package messaging.calendar;

import messaging.calendar.GetCalendarEventsEvent;

public class GetServiceLocationServiceEventsEvent extends GetCalendarEventsEvent
{			

   public String locationId;
   
   /**
    * @roseuid 451156030161
    */
	/**
	 * Constructor is used to set the default retriever for this event.  
	 * It can be changed later if required. 
	 *
	 */
	public GetServiceLocationServiceEventsEvent()
	{
		setRetriever("pd.supervision.calendar.LocationServiceEventRetriever");
	}

   
   /**
    * @param locationId
    * @roseuid 45104B4B00F2
    */
   public void setLocationId(String locationId) 
   {
    
   }
   
   /**
    * @return String
    * @roseuid 45104B4B0102
    */
   public String getLocationId() 
   {
    return null;
   }
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class GetConditionNonCompliantEventTypesEvent extends RequestEvent 
{
   private String conditionId;
   /**
    * @roseuid 473B8997017F
    */
   public GetConditionNonCompliantEventTypesEvent() 
   {
    
   }	
	/**
	 * @return Returns the conditionId.
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
}

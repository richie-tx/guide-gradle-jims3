//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class GetNonCompliantEventsEvent extends RequestEvent 
{
   private String sprOrderConditionId;
   private String conditionId;
   
   /**
    * @roseuid 473B8997017F
    */
   public GetNonCompliantEventsEvent() 
   {
    
   }
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public String getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(String sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
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

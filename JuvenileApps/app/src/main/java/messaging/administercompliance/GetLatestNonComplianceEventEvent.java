//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

public class GetLatestNonComplianceEventEvent extends RequestEvent 
{
   private int sprOrderConditionId;
   /**
    * @roseuid 473B8997017F
    */
   public GetLatestNonComplianceEventEvent() 
   {
    
   }
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(int sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
}

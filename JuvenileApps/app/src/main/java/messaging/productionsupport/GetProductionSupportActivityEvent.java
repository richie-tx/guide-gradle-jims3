package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportActivityEvent extends RequestEvent 
{
   
	private String activityId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportActivityEvent() 
   {
    
   }

	/**
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}
	
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
   
}

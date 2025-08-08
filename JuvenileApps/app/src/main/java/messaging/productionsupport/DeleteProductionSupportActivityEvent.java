package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class DeleteProductionSupportActivityEvent extends RequestEvent 
{
   
	private String activityId;	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public DeleteProductionSupportActivityEvent() 
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

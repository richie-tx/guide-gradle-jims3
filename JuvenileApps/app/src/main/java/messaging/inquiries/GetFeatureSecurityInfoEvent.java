//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetFeaturesEvent.java

package messaging.inquiries;

import mojo.km.messaging.RequestEvent;

public class GetFeatureSecurityInfoEvent extends RequestEvent 
{
   private String featureId;
   
   /**
	* @roseuid 4256F0C50196
	*/
   public GetFeatureSecurityInfoEvent() 
   {
    
   }
  
	/**
	 * @return Returns the featureId.
	 */
	public String getFeatureId() {
		return featureId;
	}
	/**
	 * @param featureId The featureId to set.
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
}

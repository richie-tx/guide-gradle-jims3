//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetFeaturesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetFeatureByIdAndCategoryEvent extends RequestEvent 
{
   private String featureCategory;
   private String featureId = null;
   
   /**
	* @roseuid 4256F0C50196
	*/
   public GetFeatureByIdAndCategoryEvent() 
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
		if(featureId != null && !(featureId.equals(""))){
			this.featureId = featureId;
		}
	}
	/**
	 * @return Returns the featureCategory.
	 */
	public String getFeatureCategory() {
		return featureCategory;
	}
	/**
	 * @param featureCategory The featureCategory to set.
	 */
	public void setFeatureCategory(String featureCategory) {
		this.featureCategory = featureCategory;
	}
}

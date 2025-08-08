//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetFeaturesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetFeaturesEvent extends RequestEvent 
{
   private String featureName;
   private String featureCategory;
   private String featureCategoryId;   
   private String featureId;
   
   /**
	* @roseuid 4256F0C50196
	*/
   public GetFeaturesEvent() 
   {
    
   }
   
   /**
	* @param featureName
	* @roseuid 4256EB880043
	*/
   public void setFeatureName(String featureName) 
   {
	  this.featureName = featureName;
   }
   
   /**
	* @return String
	* @roseuid 4256EB880045
	*/
   public String getFeatureName() 
   {
	  return this.featureName;
   }
   

   /**
    * @return
    */
    public String getFeatureCategory()
    {
	   return this.featureCategory;
    }

    /**
     * @param string
     */
     public void setFeatureCategory(String string)
     {
	    this.featureCategory = string;
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
	
	/**
	 * @return Returns the featureCategoryId.
	 */
	public String getFeatureCategoryId() {
		return featureCategoryId;
	}
	/**
	 * @param featureCategoryId The featureCategoryId to set.
	 */
	public void setFeatureCategoryId(String featureCategoryId) {
		this.featureCategoryId = featureCategoryId;
	}
}

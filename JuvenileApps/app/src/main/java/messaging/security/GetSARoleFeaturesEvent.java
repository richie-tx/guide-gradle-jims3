//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetFeaturesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetSARoleFeaturesEvent extends RequestEvent 
{
   private String featureName;
   private String featureCategory;
   private String agencyId;
   
   /**
	* @roseuid 4256F0C50196
	*/
   public GetSARoleFeaturesEvent() 
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
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

}

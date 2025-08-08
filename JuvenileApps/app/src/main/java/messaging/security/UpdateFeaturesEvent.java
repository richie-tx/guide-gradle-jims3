//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetFeaturesEvent.java

package messaging.security;

import java.util.Collection;
import mojo.km.messaging.Composite.CompositeRequest;

public class UpdateFeaturesEvent extends CompositeRequest 
{
   public Collection GetFeaturesEvent = null;
   
   /**
    * @roseuid 4256F0C50196
    */
   public UpdateFeaturesEvent() 
   {
    
   }
   
 
   /**
    * @return
    */
    public Collection getGetFeaturesEvent()
    {
	   return GetFeaturesEvent;
    }

    /**
     * @param collection
     */
     public void setGetFeaturesEvent(Collection collection)
     {
	    GetFeaturesEvent = collection;
     }
}

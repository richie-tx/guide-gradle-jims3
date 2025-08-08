//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\DeleteSuggestedOrderEvent.java

package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

public class DeleteSuggestedOrderEvent extends RequestEvent 
{
   private String suggestedOrderId;
   
   /**
    * @roseuid 433AF25D0017
    */
   public DeleteSuggestedOrderEvent() 
   {
    
   }
   
   /**
    * Access method for the suggestedOrderId property.
    * 
    * @return   the current value of the suggestedOrderId property
    */
   public String getSuggestedOrderId()
   {
      return suggestedOrderId;
   }
   
   /**
    * @param aSuggestedOrderId
    * @roseuid 433AF05200E5
    */
   public void setSuggestedOrderId(String aSuggestedOrderId) 
   {
		this.suggestedOrderId = aSuggestedOrderId;    
   }
}

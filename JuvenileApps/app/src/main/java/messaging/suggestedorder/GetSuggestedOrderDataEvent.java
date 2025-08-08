//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\suggestedorder\\GetSuggestedOrderDataEvent.java

package messaging.suggestedorder;

import mojo.km.messaging.RequestEvent;

public class GetSuggestedOrderDataEvent extends RequestEvent 
{
   private String suggestedOrderId;
   
   /**
    * @roseuid 433AF29C00C3
    */
   public GetSuggestedOrderDataEvent() 
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
    * @param theSuggestedOrderId
    * @roseuid 433AF0520037
    */
   public void setSuggestedOrderId(String theSuggestedOrderId) 
   {
    	this.suggestedOrderId = theSuggestedOrderId;
   }
}

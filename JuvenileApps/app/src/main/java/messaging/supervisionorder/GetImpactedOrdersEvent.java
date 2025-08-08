//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\GetImpactedOrdersEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetImpactedOrdersEvent extends RequestEvent 
{
   private String orderId;
   
   /**
    * @roseuid 43B2E41E01E4
    */
   public GetImpactedOrdersEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getOrderId()
	{
		return orderId;
	}
	
	/**
	 * @param string
	 */
	public void setOrderId(String string)
	{
		orderId = string;
	}

}

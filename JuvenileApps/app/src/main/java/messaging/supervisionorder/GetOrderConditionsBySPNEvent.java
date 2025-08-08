//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSpecialConditionEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetOrderConditionsBySPNEvent extends RequestEvent 
{
   private String spnId;
   
   /**
    * @roseuid 43B2E40D01C5
    */
   public GetOrderConditionsBySPNEvent() 
   {
    
   }
	
   /**
	 * @return Returns the spnId.
	 */
	public String getSpnId() {
		return spnId;
	}
	/**
	 * @param spnId The spnId to set.
	 */
	public void setSpnId(String spnId) {
		this.spnId = spnId;
	}
}

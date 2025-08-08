//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetAssociatedConditionsForCourtPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedConditionsForCourtPolicyEvent extends RequestEvent 
{
   private String policyId;
   
   /**
    * @roseuid 42F7C4FF01D4
    */
   public GetAssociatedConditionsForCourtPolicyEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getPolicyId()
	{
		return policyId;
	}
	
	/**
	 * @param string
	 */
	public void setPolicyId(String string)
	{
		policyId = string;
	}

}

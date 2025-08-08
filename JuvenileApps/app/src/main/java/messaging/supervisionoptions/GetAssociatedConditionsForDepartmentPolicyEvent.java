//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetAssociatedConditionsForDepartmentPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedConditionsForDepartmentPolicyEvent extends RequestEvent 
{
   private String policyId;
   
   /**
    * @roseuid 42F7C5000138
    */
   public GetAssociatedConditionsForDepartmentPolicyEvent() 
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

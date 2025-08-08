//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetAssociatedPoliciesForConditionEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedCourtPoliciesForConditionEvent extends RequestEvent 
{
   private String conditionId;
   
   /**
    * @roseuid 42F7C50202CE
    */
   public GetAssociatedCourtPoliciesForConditionEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getConditionId()
	{
		return conditionId;
	}
	
	/**
	 * @param string
	 */
	public void setConditionId(String string)
	{
		conditionId = string;
	}

}

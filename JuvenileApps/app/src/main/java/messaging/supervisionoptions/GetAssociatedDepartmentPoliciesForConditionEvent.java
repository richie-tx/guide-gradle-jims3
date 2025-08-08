//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetAssociatedDepartmentPoliciesForConditionEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAssociatedDepartmentPoliciesForConditionEvent extends RequestEvent 
{
   private String conditionId;
   
   /**
    * @roseuid 42F7C50103A9
    */
   public GetAssociatedDepartmentPoliciesForConditionEvent() 
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

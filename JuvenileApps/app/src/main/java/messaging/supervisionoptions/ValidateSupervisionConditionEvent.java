//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\ValidateSupervisionConditionEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class ValidateSupervisionConditionEvent extends RequestEvent 
{
   private String name;
   private String agencyId;
   private String conditionId;
   
   /**
    * @roseuid 42F7C52000CB
    */
   public ValidateSupervisionConditionEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}
	
	/**
	 * @param string
	 */
	public void setName(String string)
	{
		name = string;
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

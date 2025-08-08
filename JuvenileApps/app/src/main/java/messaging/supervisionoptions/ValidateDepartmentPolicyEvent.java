//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\ValidateDepartentPolicyEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class ValidateDepartmentPolicyEvent extends RequestEvent 
{
   private String name;
   private String agencyId;
   private String policyId;
   
   /**
    * @roseuid 42F7C51F0167
    */
   public ValidateDepartmentPolicyEvent() 
   {
    
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
	public void setName(String string)
	{
		name = string;
	}

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}
	
	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
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

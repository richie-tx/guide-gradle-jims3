//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveDepartmentPolicyAssociateToConditionsEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveDepartmentPolicyAssociateToConditionsEvent extends RequestEvent 
{
   private String policyId;
   private Collection conditionIds = new ArrayList();
   
   /**
    * @roseuid 42F7C513004E
    */
   public SaveDepartmentPolicyAssociateToConditionsEvent() 
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

	public void addConditionId( String id )
	{
		conditionIds.add( id );
	}
   
	public Collection getConditionIds()
	{
		return conditionIds; 
	}

}

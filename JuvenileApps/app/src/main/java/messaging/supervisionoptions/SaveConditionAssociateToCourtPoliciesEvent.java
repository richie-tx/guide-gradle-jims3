//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveConditionAssociateToCourtPoliciesEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveConditionAssociateToCourtPoliciesEvent extends RequestEvent 
{
   private String conditionId;
   private Collection policyIds = new ArrayList(); 
   
   /**
    * @roseuid 42F7C50F033C
    */
   public SaveConditionAssociateToCourtPoliciesEvent() 
   {
    
   }
   
   	/**
    * @param policyId
    * @roseuid 42F79A760050
    */
   	public void setConditionId( String aConditionId ) 
   	{
   		conditionId = aConditionId;
   	}
   
   	/**
    * @roseuid 42F79A760052
   	*/
   	public String getConditionId() 
   	{
		return conditionId;    
   	}

	public void addPolicyId( String id )
	{
		policyIds.add( id );
	}
   
	public Collection getPolicyIds()
	{
   		return policyIds; 
	}
   
}

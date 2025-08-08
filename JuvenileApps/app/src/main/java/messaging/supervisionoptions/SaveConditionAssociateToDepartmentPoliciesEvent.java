//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveConditionAssociateToDepartmentPoliciesEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveConditionAssociateToDepartmentPoliciesEvent extends RequestEvent 
{
   private String conditionId;
   private Collection policyIds = new ArrayList(); 
   
   /**
    * @roseuid 42F7C5100232
    */
   public SaveConditionAssociateToDepartmentPoliciesEvent() 
   {
    
   }
   
   /**
    * @param conditionId
    * @roseuid 42F79A7502CE
    */
   public void setConditionId(String aConditionId) 
   {
    	conditionId = aConditionId;
   }
   
   /**
    * @roseuid 42F79A7502D0
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

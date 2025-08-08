//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\SaveCourtPolicyAssociateToConditionsEvent.java

package messaging.supervisionoptions;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class SaveCourtPolicyAssociateToConditionsEvent extends RequestEvent 
{
   private String policyId;
   private Collection conditionIds = new ArrayList();
   
   /**
    * @roseuid 42F7C5110128
    */
   public SaveCourtPolicyAssociateToConditionsEvent() 
   {
    
   }
   
   /**
    * @param policyId
    * @roseuid 42F79A750080
    */
   public void setPolicyId(String aPolicyId) 
   {
    	policyId = aPolicyId;
   }
   
   /**
    * @roseuid 42F79A75008D
    */
   public String getPolicyId() 
   {
    	return policyId;
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

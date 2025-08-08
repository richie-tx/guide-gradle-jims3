//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetCourtPoliciesInUseEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetCourtPoliciesInUseEvent extends RequestEvent 
{
   private String policyId;
   
   /**
    * @roseuid 42F7C50400DA
    */
   public GetCourtPoliciesInUseEvent() 
   {
    
   }
   
   /**
    * @param policyId
    * @roseuid 42F7997D029F
    */
   public void setPolicyId(String aPolicyId) 
   {
    policyId=aPolicyId;
   }
   
   /**
    * @roseuid 42F7997D02A1
    */
   public String getPolicyId() 
   {
    return policyId;
   }
}

/*
 * Created on Nov 1, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetDeptPoliciesInUseEvent  extends RequestEvent 
{
   private String policyId;
   
   /**
    * @roseuid 42F7C50400DA
    */
   public GetDeptPoliciesInUseEvent() 
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

//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetCourtPolicyDetailsEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetCourtPolicyDetailsEvent extends RequestEvent 
{
	private String courtPolicyId;
   
    /**
     * @return Returns the courtPolicyId.
     */
    public String getCourtPolicyId() {
        return courtPolicyId;
    }
    /**
     * @param courtPolicyId The courtPolicyId to set.
     */
    public void setCourtPolicyId(String courtPolicyId) {
        this.courtPolicyId = courtPolicyId;
    }
   /**
    * @roseuid 42F7C50403B9
    */
   public GetCourtPolicyDetailsEvent() 
   {
    
   }
}

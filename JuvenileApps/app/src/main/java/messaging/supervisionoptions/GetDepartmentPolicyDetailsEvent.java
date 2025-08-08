//Source file: C:\\views\\dev\\app\\src\\messaging\\supervisionoptions\\GetDepartmentPolicyDetailsEvent.java

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetDepartmentPolicyDetailsEvent extends RequestEvent 
{
	  private String departmentPolicyId;
	   
    /**
     * @return Returns the departmentPolicyId.
     */
    public String getDepartmentPolicyId() {
        return departmentPolicyId;
    }
    /**
     * @param departmentPolicyId The departmentPolicyId to set.
     */
    public void setDepartmentPolicyId(String departmentPolicyId) {
        this.departmentPolicyId = departmentPolicyId;
    }
	  /**
	   * @roseuid 42F7C5060196
	   */
	  public GetDepartmentPolicyDetailsEvent() 
	  {
	    
	  }
   
}

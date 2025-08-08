//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\supervisionorder\\CreateSpecialConditionEvent.java

package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetActiveSupervisionOrderByCaseAndGroupEvent extends RequestEvent 
{
   private String caseId;
   private String groupId;
   
   /**
    * @roseuid 43B2E40D01C5
    */
   public GetActiveSupervisionOrderByCaseAndGroupEvent() 
   {
    
   }
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
}

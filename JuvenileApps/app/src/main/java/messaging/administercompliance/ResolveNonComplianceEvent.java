//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetNonCompliantEventsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.Composite.CompositeRequest;
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResolveNonComplianceEvent extends CompositeRequest 
{
    private String defendantId;
    private String conditionName;
    private String groupId;
        
	/**
	 * @return Returns the conditionName.
	 */
	public String getConditionName() {
		return conditionName;
	}
	/**
	 * @param conditionName The conditionName to set.
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
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
	/**
    * @roseuid 473B8997017F
    */
   public ResolveNonComplianceEvent() 
   {
    
   }
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
}

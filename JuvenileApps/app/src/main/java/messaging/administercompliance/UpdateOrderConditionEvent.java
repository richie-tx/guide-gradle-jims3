//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercompliance\\GetComplianceConditionsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class UpdateOrderConditionEvent extends RequestEvent 
{
    private int sprOrderConditionId;
	private String complianceReasonCodeId;	
	private String caseNumber;
	private int orderChainNum;
	private int conditionId;

	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the conditionId.
	 */
	public int getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(int conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the orderChainNum.
	 */
	public int getOrderChainNum() {
		return orderChainNum;
	}
	/**
	 * @param orderChainNum The orderChainNum to set.
	 */
	public void setOrderChainNum(int orderChainNum) {
		this.orderChainNum = orderChainNum;
	}
	/**
	 * @return Returns the complianceReasonCodeId.
	 */
	public String getComplianceReasonCodeId() {
		return complianceReasonCodeId;
	}
	/**
	 * @param complianceReasonCodeId The complianceReasonCodeId to set.
	 */
	public void setComplianceReasonCodeId(String complianceReasonCodeId) {
		this.complianceReasonCodeId = complianceReasonCodeId;
	}
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public int getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(int sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
}

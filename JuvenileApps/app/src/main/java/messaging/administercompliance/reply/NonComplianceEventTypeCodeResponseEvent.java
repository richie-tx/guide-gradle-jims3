/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NonComplianceEventTypeCodeResponseEvent extends ResponseEvent 
{
	/**
	 * @return Returns the conditionId.
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the nonComplianceEventTypeCodeDesc.
	 */
	public String getNonComplianceEventTypeCodeDesc() {
		return nonComplianceEventTypeCodeDesc;
	}
	/**
	 * @param nonComplianceEventTypeCodeDesc The nonComplianceEventTypeCodeDesc to set.
	 */
	public void setNonComplianceEventTypeCodeDesc(String nonComplianceEventTypeCodeDesc) {
		this.nonComplianceEventTypeCodeDesc = nonComplianceEventTypeCodeDesc;
	}
	/**
	 * @return Returns the nonComplianceEventTypeCodeId.
	 */
	public String getNonComplianceEventTypeCodeId() {
		return nonComplianceEventTypeCodeId;
	}
	/**
	 * @param nonComplianceEventTypeCodeId The nonComplianceEventTypeCodeId to set.
	 */
	public void setNonComplianceEventTypeCodeId(String nonComplianceEventTypeCodeId) {
		this.nonComplianceEventTypeCodeId = nonComplianceEventTypeCodeId;
	}
    private String conditionId;
	private String nonComplianceEventTypeCodeId;
	private String nonComplianceEventTypeCodeDesc;	
}

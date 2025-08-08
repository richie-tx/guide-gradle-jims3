/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NonComplianceEventTypeResponseEvent extends ResponseEvent 
{
    private String nonComplianceEventTypeId;
	private String nonComplianceEventId;
	private String nonComplianceEventCodeId;
	private String nonComplianceEventCodeDesc;
    

	/**
	 * @return Returns the nonComplianceEventCodeId.
	 */
	public String getNonComplianceEventCodeId() {
		return nonComplianceEventCodeId;
	}
	/**
	 * @param nonComplianceEventCodeId The nonComplianceEventCodeId to set.
	 */
	public void setNonComplianceEventCodeId(String nonComplianceEventCodeId) {
		this.nonComplianceEventCodeId = nonComplianceEventCodeId;
	}
	/**
	 * @return Returns the nonComplianceEventId.
	 */
	public String getNonComplianceEventId() {
		return nonComplianceEventId;
	}
	/**
	 * @param nonComplianceEventId The nonComplianceEventId to set.
	 */
	public void setNonComplianceEventId(String nonComplianceEventId) {
		this.nonComplianceEventId = nonComplianceEventId;
	}
	/**
	 * @return Returns the nonComplianceEventTypeId.
	 */
	public String getNonComplianceEventTypeId() {
		return nonComplianceEventTypeId;
	}
	/**
	 * @param nonComplianceEventTypeId The nonComplianceEventTypeId to set.
	 */
	public void setNonComplianceEventTypeId(String nonComplianceEventTypeId) {
		this.nonComplianceEventTypeId = nonComplianceEventTypeId;
	}
	/**
	 * @return Returns the nonComplianceEventCodeDesc.
	 */
	public String getNonComplianceEventCodeDesc() {
		return nonComplianceEventCodeDesc;
	}
	/**
	 * @param nonComplianceEventCodeDesc The nonComplianceEventCodeDesc to set.
	 */
	public void setNonComplianceEventCodeDesc(String nonComplianceEventCodeDesc) {
		this.nonComplianceEventCodeDesc = nonComplianceEventCodeDesc;
	}
}

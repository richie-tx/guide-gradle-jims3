/*
 * Created on Oct 10, 2005
 *
 */
package messaging.supervisionoptions.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 */
public class OrderConditionCourtPolicyResponseEvent extends ResponseEvent
{
	private int orderConditionId;
	private int conditionId;
	private int courtPolicyId;
	private String courtPolicyName;
	private String courtPolicyDescription;
	private String orderConditionCourtPolicyId;

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
	 * @return Returns the courtPolicyDescription.
	 */
	public String getCourtPolicyDescription() {
		return courtPolicyDescription;
	}
	/**
	 * @param courtPolicyDescription The courtPolicyDescription to set.
	 */
	public void setCourtPolicyDescription(String courtPolicyDescription) {
		this.courtPolicyDescription = courtPolicyDescription;
	}
	/**
	 * @return Returns the courtPolicyId.
	 */
	public int getCourtPolicyId() {
		return courtPolicyId;
	}
	/**
	 * @param courtPolicyId The courtPolicyId to set.
	 */
	public void setCourtPolicyId(int courtPolicyId) {
		this.courtPolicyId = courtPolicyId;
	}
	/**
	 * @return Returns the courtPolicyName.
	 */
	public String getCourtPolicyName() {
		return courtPolicyName;
	}
	/**
	 * @param courtPolicyName The courtPolicyName to set.
	 */
	public void setCourtPolicyName(String courtPolicyName) {
		this.courtPolicyName = courtPolicyName;
	}
	/**
	 * @return Returns the orderConditionId.
	 */
	public int getOrderConditionId() {
		return orderConditionId;
	}
	/**
	 * @param orderConditionId The orderConditionId to set.
	 */
	public void setOrderConditionId(int orderConditionId) {
		this.orderConditionId = orderConditionId;
	}
	/**
	 * @return Returns the orderConditionCourtPolicyId.
	 */
	public String getOrderConditionCourtPolicyId() {
		return orderConditionCourtPolicyId;
	}
	/**
	 * @param orderConditionCourtPolicyId The orderConditionCourtPolicyId to set.
	 */
	public void setOrderConditionCourtPolicyId(String orderConditionCourtPolicyId) {
		this.orderConditionCourtPolicyId = orderConditionCourtPolicyId;
	}
}

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
public class OrderConditionAgencyPolicyResponseEvent extends ResponseEvent
{
	private int orderConditionId;
	private int conditionId;
	private int agencyPolicyId;
	private String agencyPolicyName;
	private String agencyPolicyDescription;
	private String orderConditionAgencyPolicyId;


	/**
	 * @return Returns the agencyPolicyDescription.
	 */
	public String getAgencyPolicyDescription() {
		return agencyPolicyDescription;
	}
	/**
	 * @param agencyPolicyDescription The agencyPolicyDescription to set.
	 */
	public void setAgencyPolicyDescription(String agencyPolicyDescription) {
		this.agencyPolicyDescription = agencyPolicyDescription;
	}
	/**
	 * @return Returns the agencyPolicyId.
	 */
	public int getAgencyPolicyId() {
		return agencyPolicyId;
	}
	/**
	 * @param agencyPolicyId The agencyPolicyId to set.
	 */
	public void setAgencyPolicyId(int agencyPolicyId) {
		this.agencyPolicyId = agencyPolicyId;
	}
	/**
	 * @return Returns the agencyPolicyName.
	 */
	public String getAgencyPolicyName() {
		return agencyPolicyName;
	}
	/**
	 * @param agencyPolicyName The agencyPolicyName to set.
	 */
	public void setAgencyPolicyName(String agencyPolicyName) {
		this.agencyPolicyName = agencyPolicyName;
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
	 * @return Returns the orderConditionAgencyPolicyId.
	 */
	public String getOrderConditionAgencyPolicyId() {
		return orderConditionAgencyPolicyId;
	}
	/**
	 * @param orderConditionAgencyPolicyId The orderConditionAgencyPolicyId to set.
	 */
	public void setOrderConditionAgencyPolicyId(String orderConditionAgencyPolicyId) {
		this.orderConditionAgencyPolicyId = orderConditionAgencyPolicyId;
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
}

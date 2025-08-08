package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetAllVariableElementDataValueEvent extends RequestEvent
{
	private String supervsionOrderId;
	private String condtionId;
	/**
	 * @return the supervsionOrderId
	 */
	public String getSupervsionOrderId() {
		return supervsionOrderId;
	}
	/**
	 * @param supervsionOrderId the supervsionOrderId to set
	 */
	public void setSupervsionOrderId(String supervsionOrderId) {
		this.supervsionOrderId = supervsionOrderId;
	}
	/**
	 * @return the condtionId
	 */
	public String getCondtionId() {
		return condtionId;
	}
	/**
	 * @param condtionId the condtionId to set
	 */
	public void setCondtionId(String condtionId) {
		this.condtionId = condtionId;
	}
}

package messaging.supervisionoptions;

import mojo.km.messaging.RequestEvent;

public class GetVariableElementDataValueEvent extends RequestEvent
{
	private String supervsionOrderId;
	private String condtionId;
	private String variableElemTypeId;
	
	
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
	/**
	 * @return the variableElemTypeId
	 */
	public String getVariableElemTypeId() {
		return variableElemTypeId;
	}
	/**
	 * @param variableElemTypeId the variableElemTypeId to set
	 */
	public void setVariableElemTypeId(String variableElemTypeId) {
		this.variableElemTypeId = variableElemTypeId;
	}
}

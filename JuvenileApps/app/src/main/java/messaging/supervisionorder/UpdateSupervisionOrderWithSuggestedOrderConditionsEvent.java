package messaging.supervisionorder;

import java.util.Map;

public class UpdateSupervisionOrderWithSuggestedOrderConditionsEvent extends
		UpdateSupervisionOrderEvent {
	public String getOrderCourtId() {
		return orderCourtId;
	}

	public void setOrderCourtId(String orderCourtId) {
		this.orderCourtId = orderCourtId;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	private String suggestedOrderId;
	private String orderCourtId;
	private String agencyId; 
	private Map variableElementReferenceMap;

	public String getSuggestedOrderId() {
		return suggestedOrderId;
	}

	public Map getVariableElementReferenceMap() {
		return variableElementReferenceMap;
	}

	public void setSuggestedOrderId(String suggestedOrderId) {
		this.suggestedOrderId = suggestedOrderId;
	}
	public void setVariableElementReferenceMap(Map variableElementReferenceMap) {
		this.variableElementReferenceMap = variableElementReferenceMap;
	}
}

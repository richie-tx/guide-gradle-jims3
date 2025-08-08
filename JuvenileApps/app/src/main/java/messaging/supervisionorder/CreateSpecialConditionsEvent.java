package messaging.supervisionorder;

import mojo.km.messaging.Composite.CompositeRequest;

public class CreateSpecialConditionsEvent extends CompositeRequest {
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCourtId() {
		return courtId;
	}
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	private String orderId;
	private String courtId;
	private String agencyId;
	private String cdi;

}

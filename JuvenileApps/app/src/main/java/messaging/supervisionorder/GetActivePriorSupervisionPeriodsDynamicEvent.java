/*
 * Created on Nov 3, 2006
 *
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetActivePriorSupervisionPeriodsDynamicEvent extends RequestEvent {
    private String agencyId;
    private String defendantIds;
    private boolean active;
    
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAgencyId() {
		return agencyId;
	}
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	public String getDefendantIds() {
		return defendantIds;
	}
	public void setDefendantIds(String defendantIds) {
		this.defendantIds = defendantIds;
	}
}

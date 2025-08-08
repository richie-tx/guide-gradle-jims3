package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSupervisionPeriodForComplianceEvent extends RequestEvent {

	private String defendantId;	
	private String agencyId;
	
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	/**
	 * @return Returns the defendantId.
	 */
	public String getAgencyId() {
		return "CSC";
	}
}

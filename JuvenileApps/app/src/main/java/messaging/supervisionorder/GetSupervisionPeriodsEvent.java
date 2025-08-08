package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 */
public class GetSupervisionPeriodsEvent extends RequestEvent {

	private String agencyId;
	private String spn;
	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn The spn to set.
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
}

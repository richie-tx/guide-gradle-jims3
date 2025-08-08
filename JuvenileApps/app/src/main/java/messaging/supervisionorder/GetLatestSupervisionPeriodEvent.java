/*
 * Created on Aug 2, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetLatestSupervisionPeriodEvent extends RequestEvent {

	private String agencyId;
	private String defendantId;
	
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
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String spn) {
		this.defendantId = spn;
	}
}

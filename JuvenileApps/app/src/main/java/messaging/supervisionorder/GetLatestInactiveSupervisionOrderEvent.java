package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

public class GetLatestInactiveSupervisionOrderEvent extends RequestEvent
{
	private String agencyId;
	private String spn;
	private String supervisionPeriodId;
	
	
	/**
	 * @return the agencyId
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId the agencyId to set
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @return the supervisionPeriodId
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	/**
	 * @param supervisionPeriodId the supervisionPeriodId to set
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	
	
	
	
	
}

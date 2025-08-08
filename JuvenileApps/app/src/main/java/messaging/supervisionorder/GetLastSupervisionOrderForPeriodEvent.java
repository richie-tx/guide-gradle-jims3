package messaging.supervisionorder;

import mojo.km.messaging.RequestEvent;

/**
 * @author jmcnabb
 *
 */
public class GetLastSupervisionOrderForPeriodEvent extends RequestEvent {

	private String agencyId;
	private String spn;
	private String supervisionPeriodId;
	
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	
	/**
	 * @return Returns the spn.
	 */
	public String getSpn() {
		return spn;
	}
	
	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	
	/**
	 * @param anAgencyId The agencyId to set.
	 */
	public void setAgencyId(String anAgencyId) {
		this.agencyId = anAgencyId;
	}
	
	/**
	 * @param aSpn The spn to set.
	 */
	public void setSpn(String aSpn) {
		this.spn = aSpn;
	}

	/**
	 * @param aSupervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String aSupervisionPeriodId) {
		this.supervisionPeriodId = aSupervisionPeriodId;
	}

}

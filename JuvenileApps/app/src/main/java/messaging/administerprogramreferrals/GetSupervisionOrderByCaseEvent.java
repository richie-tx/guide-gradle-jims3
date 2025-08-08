package messaging.administerprogramreferrals;

import mojo.km.messaging.RequestEvent;


/**
 * 
 * @author cc_bjangay
 *
 */
public class GetSupervisionOrderByCaseEvent extends RequestEvent
{
	private String criminalCaseId;

	
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
}

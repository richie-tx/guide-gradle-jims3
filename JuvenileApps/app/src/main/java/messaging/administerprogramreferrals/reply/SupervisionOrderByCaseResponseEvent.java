package messaging.administerprogramreferrals.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * 
 * @author cc_bjangay
 *
 */
public class SupervisionOrderByCaseResponseEvent extends ResponseEvent 
{
	private String supervisionOrderId;
	

	/**
	 * @return the supervisionOrderId
	 */
	public String getSupervisionOrderId() {
		return supervisionOrderId;
	}

	/**
	 * @param supervisionOrderId the supervisionOrderId to set
	 */
	public void setSupervisionOrderId(String supervisionOrderId) {
		this.supervisionOrderId = supervisionOrderId;
	}
}

/*
 * Created on May 29, 2008
 *
 */
package messaging.posttrial;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetActiveSupervisionPeriodForSuperviseeEvent extends RequestEvent
{
	private String spn;

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
}

/*
 */
package messaging.officer;

import mojo.km.messaging.RequestEvent;


public class GetOfficerProfilesByManagerEvent extends RequestEvent
{
	private String managerId;


	/**
	 * @return Returns the managerId.
	 */
	public String getManagerId() {
		return managerId;
	}
	/**
	 * @param managerId The managerId to set.
	 */
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
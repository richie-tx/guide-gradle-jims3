//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCCommunityServiceEvent extends RequestEvent 
{
	private String caseId;
	private String hoursOrdered;
	private String hoursCompleted;
	private String communityServiceId;
	
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the communityServiceId.
	 */
	public String getCommunityServiceId() {
		return communityServiceId;
	}
	/**
	 * @param communityServiceId The communityServiceId to set.
	 */
	public void setCommunityServiceId(String communityServiceId) {
		this.communityServiceId = communityServiceId;
	}
	/**
	 * @return Returns the hoursCompleted.
	 */
	public String getHoursCompleted() {
		return hoursCompleted;
	}
	/**
	 * @param hoursCompleted The hoursCompleted to set.
	 */
	public void setHoursCompleted(String hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}
	/**
	 * @return Returns the hoursOrdered.
	 */
	public String getHoursOrdered() {
		return hoursOrdered;
	}
	/**
	 * @param hoursOrdered The hoursOrdered to set.
	 */
	public void setHoursOrdered(String hoursOrdered) {
		this.hoursOrdered = hoursOrdered;
	}
}

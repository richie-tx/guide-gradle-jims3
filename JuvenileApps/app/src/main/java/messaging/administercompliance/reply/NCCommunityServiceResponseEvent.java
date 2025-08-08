/*
 * Created on April 11, 2008
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NCCommunityServiceResponseEvent extends ResponseEvent 
{
    private String caseId;
    private String hoursOrdered;
    private String hoursCompleted;
    private String defendantId;
    private String ncResponseId;
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
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
 }

package ui.juvenilecase.detentionCourtHearings;

import java.util.Collection;

import messaging.calendar.reply.DocketEventResponseEvent;

/**
 * U.S 11645
 * @author sthyagarajan
 *
 */
public class DetentionHearingDocketBean {

	private String refereeCourt;
	private String hearingDate;
	private String assignedJudge;
	private String hearingTime;
	private String assignedDAName;
	private Collection<DocketEventResponseEvent> detentionSearchResults;
	
	/**
	 * @return the refereeCourt
	 */
	public String getRefereeCourt() {
		return refereeCourt;
	}
	/**
	 * @param refereeCourt the refereeCourt to set
	 */
	public void setRefereeCourt(String refereeCourt) {
		this.refereeCourt = refereeCourt;
	}
	/**
	 * @return the hearingDate
	 */
	public String getHearingDate() {
		return hearingDate;
	}
	/**
	 * @param hearingDate the hearingDate to set
	 */
	public void setHearingDate(String hearingDate) {
		this.hearingDate = hearingDate;
	}
	/**
	 * @return the assignedJudge
	 */
	public String getAssignedJudge() {
		return assignedJudge;
	}
	/**
	 * @param assignedJudge the assignedJudge to set
	 */
	public void setAssignedJudge(String assignedJudge) {
		this.assignedJudge = assignedJudge;
	}
	/**
	 * @return the hearingTime
	 */
	public String getHearingTime() {
		return hearingTime;
	}
	/**
	 * @param hearingTime the hearingTime to set
	 */
	public void setHearingTime(String hearingTime) {
		this.hearingTime = hearingTime;
	}
	/**
	 * @return the assignedDAName
	 */
	public String getAssignedDAName() {
		return assignedDAName;
	}
	/**
	 * @param assignedDAName the assignedDAName to set
	 */
	public void setAssignedDAName(String assignedDAName) {
		this.assignedDAName = assignedDAName;
	}

	/**
	 * @return the detentionSearchResults
	 */
	public Collection<DocketEventResponseEvent> getDetentionSearchResults() {
		return detentionSearchResults;
	}
	/**
	 * @param detentionSearchResults the detentionSearchResults to set
	 */
	public void setDetentionSearchResults(Collection<DocketEventResponseEvent> detentionSearchResults) {
		this.detentionSearchResults = detentionSearchResults;
	}
}

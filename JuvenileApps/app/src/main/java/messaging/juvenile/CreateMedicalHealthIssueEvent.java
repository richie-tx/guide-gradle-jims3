// Source file:
// C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenile\\CreateMedicalHealthIssueEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CreateMedicalHealthIssueEvent extends RequestEvent {
	public String juvenileNum;

	public String conditionLevelId;

	public String conditionSeverityId;

	public String issueId;

	public String issueStatusId;

	public Date entryDate;
	
	public String healthIssuesListId; 
	
	public String healthStatusId; 
	
	public String modificationReason;

	/**
	 * @roseuid 462CE3A80246
	 */
	public CreateMedicalHealthIssueEvent() {

	}

	/**
	 * @param juvenileNum
	 * @roseuid 462CBCA70010
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return String
	 * @roseuid 462CBCA70012
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param conditionLevel
	 * @roseuid 462CBCA70020
	 */
	public void setConditionLevelId(String conditionLevelId) {
		this.conditionLevelId = conditionLevelId;
	}

	/**
	 * @return String
	 * @roseuid 462CBCA70022
	 */
	public String getConditionLevelId() {
		return conditionLevelId;
	}

	/**
	 * @param conditionSeverityId
	 * @roseuid 462CBCA7003F
	 */
	public void setConditionSeverityId(String conditionSeverityId) {
		this.conditionSeverityId = conditionSeverityId;
	}

	/**
	 * @return String
	 * @roseuid 462CBCA70041
	 */
	public String getConditionSeverityId() {
		return conditionSeverityId;
	}

	/**
	 * @param issueId
	 * @roseuid 462CBCA7004F
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}

	/**
	 * @return String
	 * @roseuid 462CBCA70051
	 */
	public String getIssueId() {
		return issueId;
	}

	/**
	 * @param issueStatusId
	 * @roseuid 462CBCA70053
	 */
	public void setIssueStatusId(String issueStatusId) {
		this.issueStatusId = issueStatusId;
	}

	/**
	 * @return the healthStatusId
	 */
	public String getHealthStatusId() {
		return healthStatusId;
	}

	/**
	 * @param healthStatusId the healthStatusId to set
	 */
	public void setHealthStatusId(String healthStatusId) {
		this.healthStatusId = healthStatusId;
	}

	/**
	 * @return the modificationReason
	 */
	public String getModificationReason() {
		return modificationReason;
	}

	/**
	 * @param modificationReason the modificationReason to set
	 */
	public void setModificationReason(String modificationReason) {
		this.modificationReason = modificationReason;
	}

	/**
	 * @return the healthIssuesListId
	 */
	public String getHealthIssuesListId() {
		return healthIssuesListId;
	}

	/**
	 * @param healthIssuesListId the healthIssuesListId to set
	 */
	public void setHealthIssuesListId(String healthIssuesListId) {
		this.healthIssuesListId = healthIssuesListId;
	}

	/**
	 * @return String
	 * @roseuid 462CBCA70060
	 */
	public String getIssueStatusId() {
		return issueStatusId;
	}

	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
}

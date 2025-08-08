/*
 * Created on Apr 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileHealthIssueResponseEvent extends ResponseEvent implements Comparable{

	private String issueId;
	private String issueStatusId;
	private String conditionSeverityId;
	private String conditionLevelId;
	private Date entryDate;
	private String healthIssueId;
	private String healthStatusId;
	private String healthStatusFull;
	private String modificationReason;
	private String issueDesc;
	private String conditionLevelDesc;
	private String conditionSeverityDesc;	
	
	/**
	 * @param _service
	 *//*
	public JuvenileHealthIssueResponseEvent(String _service) {
		super(_service);
		// TODO Auto-generated constructor stub
	}
*/
	
	/**
	 * 
	 */
	public JuvenileHealthIssueResponseEvent() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return Returns the conditionLevelId.
	 */
	public String getConditionLevelId() {
		return conditionLevelId;
	}
	/**
	 * @param conditionLevelId The conditionLevelId to set.
	 */
	public void setConditionLevelId(String conditionLevelId) {
		this.conditionLevelId = conditionLevelId;
	}
	/**
	 * @return Returns the conditionSeverityId.
	 */
	public String getConditionSeverityId() {
		return conditionSeverityId;
	}
	/**
	 * @param conditionSeverityId The conditionSeverityId to set.
	 */
	public void setConditionSeverityId(String conditionSeverityId) {
		this.conditionSeverityId = conditionSeverityId;
	}
	/**
	 * @return Returns the entryDate.
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate The entryDate to set.
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return Returns the healthIssueId.
	 */
	public String getHealthIssueId() {
		return healthIssueId;
	}
	/**
	 * @param healthIssueId The healthIssueId to set.
	 */
	public void setHealthIssueId(String healthIssueId) {
		this.healthIssueId = healthIssueId;
	}
	/**
	 * @return Returns the issueId.
	 */
	public String getIssueId() {
		return issueId;
	}
	/**
	 * @param issueId The issueId to set.
	 */
	public void setIssueId(String issueId) {
		this.issueId = issueId;
	}
	/**
	 * @return Returns the issueStatusId.
	 */
	public String getIssueStatusId() {
		return issueStatusId;
	}
	/**
	 * @param issueStatusId The issueStatusId to set.
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
	 * @return the healthStatusFull
	 */
	public String getHealthStatusFull() {
		return healthStatusFull;
	}


	/**
	 * @param healthStatusFull the healthStatusFull to set
	 */
	public void setHealthStatusFull(String healthStatusFull) {
		this.healthStatusFull = healthStatusFull;
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


	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		// TODO Auto-generated method stub
		JuvenileHealthIssueResponseEvent evt = (JuvenileHealthIssueResponseEvent)obj;
		return evt.getEntryDate().compareTo(entryDate);
	}

	/**
	 * @return Returns the conditionLevelDesc.
	 */
	public String getConditionLevelDesc() {
		return conditionLevelDesc;
	}
	/**
	 * @param conditionLevelDesc The conditionLevelDesc to set.
	 */
	public void setConditionLevelDesc(String conditionLevelDesc) {
		this.conditionLevelDesc = conditionLevelDesc;
	}
	/**
	 * @return Returns the conditionSeverityDesc.
	 */
	public String getConditionSeverityDesc() {
		return conditionSeverityDesc;
	}
	/**
	 * @param conditionSeverityDesc The conditionSeverityDesc to set.
	 */
	public void setConditionSeverityDesc(String conditionSeverityDesc) {
		this.conditionSeverityDesc = conditionSeverityDesc;
	}
	/**
	 * @return Returns the issueDesc.
	 */
	public String getIssueDesc() {
		return issueDesc;
	}
	/**
	 * @param issueDesc The issueDesc to set.
	 */
	public void setIssueDesc(String issueDesc) {
		this.issueDesc = issueDesc;
	}
}

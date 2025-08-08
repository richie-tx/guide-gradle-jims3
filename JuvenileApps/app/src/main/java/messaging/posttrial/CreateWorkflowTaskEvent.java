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
public class CreateWorkflowTaskEvent extends RequestEvent
{
	private String positionId;
	private String workgroupId;
	private String subject;
	private String text;
	private String caseNumber;
	private String severitylevelId;
	private String dueDate;
	private String cdi;
	private String nextActionId;
	private String superviseeName;
	private String officerName;
	private String offense;
	private String spn;
	private String los;
	private String programUnit;
	private String courtNumber;
	private String reportType;
	private String nextActionParent;
	
	/**
	 * @return the nextActionParent
	 */
	public String getNextActionParent() {
		return nextActionParent;
	}
	/**
	 * @param nextActionParent the nextActionParent to set
	 */
	public void setNextActionParent(String nextActionParent) {
		this.nextActionParent = nextActionParent;
	}
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	/**
	 * @return the courtNumber
	 */
	public String getCourtNumber() {
		return courtNumber;
	}
	/**
	 * @return the los
	 */
	public String getLos() {
		return los;
	}
	/**
	 * @return the offense
	 */
	public String getOffense() {
		return offense;
	}
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @return the programUnit
	 */
	public String getProgramUnit() {
		return programUnit;
	}
	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @param courtNumber the courtNumber to set
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}
	/**
	 * @param los the los to set
	 */
	public void setLos(String los) {
		this.los = los;
	}
	/**
	 * @param offense the offense to set
	 */
	public void setOffense(String offense) {
		this.offense = offense;
	}
	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @param programUnit the programUnit to set
	 */
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return the nextActionId
	 */
	public String getNextActionId() {
		return nextActionId;
	}
	/**
	 * @param nextActionId the nextActionId to set
	 */
	public void setNextActionId(String nextActionId) {
		this.nextActionId = nextActionId;
	}
	/**
	 * @return the cdi
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the severitylevelId
	 */
	public String getSeveritylevelId() {
		return severitylevelId;
	}
	/**
	 * @param severitylevelId the severitylevelId to set
	 */
	public void setSeveritylevelId(String severitylevelId) {
		this.severitylevelId = severitylevelId;
	}
	/**
	 * @return the caseNumber
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber the caseNumber to set
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return the positionId
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @return the workgroupId
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}
	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @param workgroupId the workgroupId to set
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}
}

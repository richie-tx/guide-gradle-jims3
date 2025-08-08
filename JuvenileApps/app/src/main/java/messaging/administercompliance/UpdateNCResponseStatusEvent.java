//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseStatusEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class UpdateNCResponseStatusEvent extends RequestEvent 
{
   private String assignedOfficerId;
   private String allocSupervisorId;
   private String ncResponseId;
   private String statusId;
   private String ntTaskId;
   private String subject;
   private String text;
   private String topic;
   private String superviseeName;
   private String spn;
   private String courtNumber;
   private String caseNumber;
   
   // Header info
   private String officerName;
   private String offense;
   private String cdi;
   private String los;
   private String programUnit;
   private boolean selfApprove;
   private String logonId;
   private String psCreatorId;
   private String reportType;
   private String staffPositionId;
   
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
	 * @return the logonId
	 */
	public String getLogonId() {
		return logonId;
	}
	
	/**
	 * @param logonId the logonId to set
	 */
	public void setLogonId(String logonId) {
		this.logonId = logonId;
	}
	/**
	 * @return the isSelfApprove
	 */
	public boolean isSelfApprove() {
		return selfApprove;
	}
	
	/**
	 * @param isSelfApprove the isSelfApprove to set
	 */
	public void setSelfApprove(boolean selfApprove) {
		this.selfApprove = selfApprove;
	}

	/**
	 * @return the cdi
	 */
	public String getCdi() {
		return cdi;
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
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
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
	 * @return the courtNumber
	 */
	public String getCourtNumber() {
		return courtNumber;
	}
	
	/**
	 * @param courtNumber the courtNumber to set
	 */
	public void setCourtNumber(String courtNumber) {
		this.courtNumber = courtNumber;
	}
	
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
	
	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}
	
	/**
	 * @param topic the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the ntTaskId
	 */
	public String getNtTaskId() {
		return ntTaskId;
	}
	
	/**
	 * @param ntTaskId the ntTaskId to set
	 */
	public void setNtTaskId(String ntTaskId) {
		this.ntTaskId = ntTaskId;
	}

/**
    * @roseuid 47D9BBFD0151
    */
   public UpdateNCResponseStatusEvent() 
   {
    
   }

	/**
	 * @return the ncResponseId
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	
	/**
	 * @return the statusId
	 */
	public String getStatusId() {
		return statusId;
	}
	
	/**
	 * @param ncResponseId the ncResponseId to set
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
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
	 * @return the psCreatorId
	 */
	public String getPsCreatorId() {
		return psCreatorId;
	}

	/**
	 * @param psCreatorId the psCreatorId to set
	 */
	public void setPsCreatorId(String psCreatorId) {
		this.psCreatorId = psCreatorId;
	}

	public String getAssignedOfficerId() {
		return assignedOfficerId;
	}

	public void setAssignedOfficerId(String assignedOfficerId) {
		this.assignedOfficerId = assignedOfficerId;
	}

	public String getAllocSupervisorId() {
		return allocSupervisorId;
	}

	public void setAllocSupervisorId(String allocSupervisorId) {
		this.allocSupervisorId = allocSupervisorId;
	}

	public String getStaffPositionId() {
		return staffPositionId;
	}

	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managetask\\CreateCSTaskEvent.java

package messaging.managetask;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CreateCSTaskEvent extends RequestEvent 
{
   private String caseAssignId;	
   private String courtId;
   private String courtId2;
   private Date lastTransferDate;
   private String ncResponseId;
   private String ntTaskId;
   private String defendantId;
   private String subject2;
   private String conditionId;
   private String staffPositionId;
   private String workGroupId;
   private Date dueDate;
   private String criminalCaseId;
   private String statusId;
   private String scenario;
   private String superviseeName;
   private String supervisionOrderId;
   private String taskSubject;
   private String tastText;
   private String topic;
   private String supervisionPlanId;
   
   
/**
 * @return Returns the conditionCd.
 */
public String getConditionId() {
	return conditionId;
}
/**
 * @param conditionCd The conditionCd to set.
 */
public void setConditionId(String conditionCd) {
	this.conditionId = conditionCd;
}
/**
 * @return Returns the subject2.
 */
public String getSubject2() {
	return subject2;
}
/**
 * @param subject2 The subject2 to set.
 */
public void setSubject2(String subject2) {
	this.subject2 = subject2;
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
    * @roseuid 463F30130185
    */
   public CreateCSTaskEvent() 
   {
    
   }
   
   /**
    * @param courtId
    * @roseuid 463F170F03D7
    */
   public void setCourtId(String courtId) 
   {
    this.courtId = courtId;
   }
   
   /**
    * @return String
    * @roseuid 463F170F03D9
    */
   public String getCourtId() 
   {
    return this.courtId;
   }
   
   /**
    * @param courtId2
    * @roseuid 463F170F03E6
    */
   public void setCourtId2(String courtId2) 
   {
    this.courtId2 = courtId2;
   }
   
   /**
    * @return String
    * @roseuid 463F170F03E8
    */
   public String getCourtId2() 
   {
    return this.courtId2;
   }
   
   /**
    * @param lastTransferDate
    * @roseuid 463F1710003D
    */
   public void setLastTransferDate(Date lastTransferDate) 
   {
    this.lastTransferDate = lastTransferDate;
   }
   
   /**
    * @return java.util.date
    * @roseuid 463F1710003F
    */
   public Date getLastTransferDate() 
   {
    return this.lastTransferDate;
   }
   
   /**
    * @param taskId
    * @roseuid 463F1710006C
    */
   public void setNtTaskId(String taskId) 
   {
    this.ntTaskId = taskId;
   }
   
   /**
    * @return String
    * @roseuid 463F1710006E
    */
   public String getNtTaskId() 
   {
    return this.ntTaskId;
   }
public String getStaffPositionId() {
	return staffPositionId;
}
public void setStaffPositionId(String staffPositionId) {
	this.staffPositionId = staffPositionId;
}
public String getWorkGroupId() {
	return workGroupId;
}
public void setWorkGroupId(String workGroupId) {
	this.workGroupId = workGroupId;
}
public Date getDueDate() {
	return dueDate;
}
public void setDueDate(Date dueDate) {
	this.dueDate = dueDate;
}
public String getCriminalCaseId() {
	return criminalCaseId;
}
public void setCriminalCaseId(String criminalCaseId) {
	this.criminalCaseId = criminalCaseId;
}
public String getStatusId() {
	return statusId;
}
public void setStatusId(String statusId) {
	this.statusId = statusId;
}
public String getScenario() {
	return scenario;
}
public void setScenario(String scenario) {
	this.scenario = scenario;
}
public String getTaskSubject() {
	return taskSubject;
}
public void setTaskSubject(String taskSubject) {
	this.taskSubject = taskSubject;
}
public String getTastText() {
	return tastText;
}
public void setTastText(String tastText) {
	this.tastText = tastText;
}
public String getSuperviseeName() {
	return superviseeName;
}
public void setSuperviseeName(String superviseeName) {
	this.superviseeName = superviseeName;
}
public String getCaseAssignId() {
	return caseAssignId;
}
public void setCaseAssignId(String caseAssignId) {
	this.caseAssignId = caseAssignId;
}
public String getNcResponseId() {
	return ncResponseId;
}
public void setNcResponseId(String ncResponseId) {
	this.ncResponseId = ncResponseId;
}
public String getSupervisionOrderId() {
	return supervisionOrderId;
}
public void setSupervisionOrderId(String supervisionOrderId) {
	this.supervisionOrderId = supervisionOrderId;
}
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}
public String getSupervisionPlanId() {
	return supervisionPlanId;
}
public void setSupervisionPlanId(String supervisionPlanId) {
	this.supervisionPlanId = supervisionPlanId;
}
   
   
}

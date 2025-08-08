//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\UpdateSupervisionPlanEvent.java

package messaging.administersupervisionplan;

import java.util.Date;

import mojo.km.messaging.RequestEvent;


public class UpdateSupervisionPlanEvent extends RequestEvent 
{
   public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
private String defendantId;
//   null for create; OID for update
   private String supervisionPlanId;
   private Date supervisionPlanDate;
   private String statusCd;  
   private String behaviorObjective;
   private String csoActionPlan;
   private String offenderActionPlan;
   private String problem;
   private String taskId;
   
   
   
/**
 * @return Returns the behaviorObjective.
 */
public String getBehaviorObjective() {
	return behaviorObjective;
}
/**
 * @param behaviorObjective The behaviorObjective to set.
 */
public void setBehaviorObjective(String behaviorObjective) {
	this.behaviorObjective = behaviorObjective;
}
/**
 * @return Returns the csoActionPlan.
 */
public String getCsoActionPlan() {
	return csoActionPlan;
}
/**
 * @param csoActionPlan The csoActionPlan to set.
 */
public void setCsoActionPlan(String csoActionPlan) {
	this.csoActionPlan = csoActionPlan;
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
 * @return Returns the offenderActionPlan.
 */
public String getOffenderActionPlan() {
	return offenderActionPlan;
}
/**
 * @param offenderActionPlan The offenderActionPlan to set.
 */
public void setOffenderActionPlan(String offenderActionPlan) {
	this.offenderActionPlan = offenderActionPlan;
}
/**
 * @return Returns the problem.
 */
public String getProblem() {
	return problem;
}
/**
 * @param problem The problem to set.
 */
public void setProblem(String problem) {
	this.problem = problem;
}
/**
 * @return Returns the statusCd.
 */
public String getStatusCd() {
	return statusCd;
}
/**
 * @param statusCd The statusCd to set.
 */
public void setStatusCd(String statusCd) {
	this.statusCd = statusCd;
}
/**
 * @return Returns the supervisionPlanDate.
 */
public Date getSupervisionPlanDate() {
	return supervisionPlanDate;
}
/**
 * @param supervisionPlanDate The supervisionPlanDate to set.
 */
public void setSupervisionPlanDate(Date supervisionPlanDate) {
	this.supervisionPlanDate = supervisionPlanDate;
}
/**
 * @return Returns the supervisionPlanId.
 */
public String getSupervisionPlanId() {
	return supervisionPlanId;
}
/**
 * @param supervisionPlanId The supervisionPlanId to set.
 */
public void setSupervisionPlanId(String supervisionPlanId) {
	this.supervisionPlanId = supervisionPlanId;
}
}

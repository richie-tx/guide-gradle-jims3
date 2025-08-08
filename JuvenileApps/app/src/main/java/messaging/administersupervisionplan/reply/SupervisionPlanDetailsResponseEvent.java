//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\reply\\SupervisionPlanDetailsResponseEvent.java

package messaging.administersupervisionplan.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


public class SupervisionPlanDetailsResponseEvent extends ResponseEvent
{
   private Date supervisionPlanDate;
   private String statusCd;
   private Date lastChangeDate;
   private String lastChangeUserId;
   private String behaviorObjective;
   private String csoActionPlan;
   private String offenderActionPlan;
   private String problem;
   private String supervisionPlanId;
   private String statusLit;
   
	public String getSupervisionPlanId() {
		return supervisionPlanId;
	}
	public void setSupervisionPlanId(String supervisionPlanId) {
		this.supervisionPlanId = supervisionPlanId;
	}
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
 * @return Returns the lastChangeDate.
 */
public Date getLastChangeDate() {
	return lastChangeDate;
}
/**
 * @param lastChangeDate The lastChangeDate to set.
 */
public void setLastChangeDate(Date lastChangeDate) {
	this.lastChangeDate = lastChangeDate;
}
/**
 * @return Returns the lastChangeUserId.
 */
public String getLastChangeUserId() {
	return lastChangeUserId;
}
/**
 * @param lastChangeUserId The lastChangeUserId to set.
 */
public void setLastChangeUserId(String lastChangeUserId) {
	this.lastChangeUserId = lastChangeUserId;
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
public String getStatusLit() {
	return statusLit;
}
public void setStatusLit(String statusLit) {
	this.statusLit = statusLit;
}
}

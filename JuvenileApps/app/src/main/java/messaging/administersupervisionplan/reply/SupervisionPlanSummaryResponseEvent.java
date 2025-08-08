//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administersupervisionplan\\reply\\SupervisionPlanSummaryResponseEvent.java

package messaging.administersupervisionplan.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


public class SupervisionPlanSummaryResponseEvent extends ResponseEvent
{
//   OID
   private String supervisionPlanId;
   private Date supervisionPlanDate;
   private Date lastChangeDate;   
   private String lastChangeUserId;
   private String statusCd;
   

   
   
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

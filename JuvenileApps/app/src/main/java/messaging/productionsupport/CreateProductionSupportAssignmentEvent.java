package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CreateProductionSupportAssignmentEvent extends RequestEvent 
{
   public String casefileId;
   public String serviceUnitId;
   public String assignmentLevelId;
   public Date assignmentAddDate;
   public String referralNumber;
   public String assignByuserId;
   public String assignmentType;
   public String refSeqNum;
     


/**
    * @roseuid 456F33E603CA
    */
   public CreateProductionSupportAssignmentEvent() {}

/**
 * @return the casefileId
 */
public String getCasefileId() {
	return casefileId;
}

/**
 * @param casefileId the casefileId to set
 */
public void setCasefileId(String casefileId) {
	this.casefileId = casefileId;
}

/**
 * @return the serviceUnitId
 */
public String getServiceUnitId() {
	return serviceUnitId;
}

/**
 * @param serviceUnitId the serviceUnitId to set
 */
public void setServiceUnitId(String serviceUnitId) {
	this.serviceUnitId = serviceUnitId;
}

/**
 * @return the assignmentLevelId
 */
public String getAssignmentLevelId() {
	return assignmentLevelId;
}

/**
 * @param assignmentLevelId the assignmentLevelId to set
 */
public void setAssignmentLevelId(String assignmentLevelId) {
	this.assignmentLevelId = assignmentLevelId;
}

/**
 * @return the assignmentAddDate
 */
public Date getAssignmentAddDate() {
	return assignmentAddDate;
}

/**
 * @param assignmentAddDate the assignmentAddDate to set
 */
public void setAssignmentAddDate(Date assignmentAddDate) {
	this.assignmentAddDate = assignmentAddDate;
}

/**
 * @return the referralNumber
 */
public String getReferralNumber() {
	return referralNumber;
}

/**
 * @param referralNumber the referralNumber to set
 */
public void setReferralNumber(String referralNumber) {
	this.referralNumber = referralNumber;
}

/**
 * @return the assignByuserId
 */
public String getAssignByuserId() {
	return assignByuserId;
}

/**
 * @param assignByuserId the assignByuserId to set
 */
public void setAssignByuserId(String assignByuserId) {
	this.assignByuserId = assignByuserId;
}

public String getAssignmentType()
{
    return assignmentType;
}

public void setAssignmentType(String assignmentType)
{
    this.assignmentType = assignmentType;
}

public String getRefSeqNum()
{
    return refSeqNum;
}

public void setRefSeqNum(String refSeqNum)
{
    this.refSeqNum = refSeqNum;
}
   
   
	   
}

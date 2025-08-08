package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportAssignmentEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId;
	private String referralNumber;
	
	private String assignmentId;
	private Date assignmentDate;
	private String referralAssmentType;
	private String referralSeqNum;
    
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportAssignmentEvent() 
   {
    
   }

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
	 * @return the mergeToCasefileId
	 */
	public String getMergeToCasefileId() {
		return mergeToCasefileId;
	}
	
	/**
	 * @param mergeToCasefileId the mergeToCasefileId to set
	 */
	public void setMergeToCasefileId(String mergeToCasefileId) {
		this.mergeToCasefileId = mergeToCasefileId;
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
	 * @return the assignmentId
	 */
	public String getAssignmentId() {
		return assignmentId;
	}

	/**
	 * @param assignmentId the assignmentId to set
	 */
	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	/**
	 * @return the assignmentDate
	 */
	public Date getAssignmentDate() {
		return assignmentDate;
	}

	/**
	 * @param assignmentDate the assignmentDate to set
	 */
	public void setAssignmentDate(Date assignmentDate) {
		this.assignmentDate = assignmentDate;
	}

	public String getReferralAssmentType()
	{
	    return referralAssmentType;
	}

	public void setReferralAssmentType(String referralAssmentType)
	{
	    this.referralAssmentType = referralAssmentType;
	}

	public String getReferralSeqNum()
	{
	    return referralSeqNum;
	}

	public void setReferralSeqNum(String referralSeqNum)
	{
	    this.referralSeqNum = referralSeqNum;
	}
	
}

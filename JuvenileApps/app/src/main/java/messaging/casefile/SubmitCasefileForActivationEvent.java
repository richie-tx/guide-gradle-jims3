package messaging.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * 
 */
public class SubmitCasefileForActivationEvent extends RequestEvent 
{
	private String supervisionNum;
	private Date supervisionEndDate;
	private Date courtOrderedProbationStartDate;
	private String casefileControllingReferralId;
	private Collection referrals = new ArrayList<>();
   
	/**
	* 
	*/
	public SubmitCasefileForActivationEvent() 
	{
	}
   
	/**
	* @param supervisionNum
	*/
	public void setSupervisionNumber(String aSupervisionNum) 
	{
		this.supervisionNum = aSupervisionNum;
	}
   
	/**
	* @return String
	*/
	public String getSupervisionNum() 
	{
		return this.supervisionNum;
	}

	/**
	 * @return Returns the supervisionEndDate.
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}
	/**
	 * @param supervisionEndDate The supervisionEndDate to set.
	 */
	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}
	/**
	 * @param supervisionNum The supervisionNum to set.
	 */
	public void setSupervisionNum(String supervisionNum) {
		this.supervisionNum = supervisionNum;
	}

	public String getCasefileControllingReferralId() {
		return casefileControllingReferralId;
	}

	public void setCasefileControllingReferralId(
			String casefileControllingReferralId) {
		this.casefileControllingReferralId = casefileControllingReferralId;
	}

	/**
	 * @return the courtOrderedProbationStartDate
	 */
	public Date getCourtOrderedProbationStartDate() {
		return courtOrderedProbationStartDate;
	}

	/**
	 * @param courtOrderedProbationStartDate the courtOrderedProbationStartDate to set
	 */
	public void setCourtOrderedProbationStartDate(Date courtOrderedProbationStartDate) {
		this.courtOrderedProbationStartDate = courtOrderedProbationStartDate;
	}

	public Collection getReferrals()
	{
	    return referrals;
	}

	public void setReferrals(Collection referrals)
	{
	    this.referrals = referrals;
	}
	
	
}

package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent extends RequestEvent 
{
   
	private String casefileId;
	private String programReferralNumber;
   
   /**
    * @roseuid 45702FFC0393
    */
   public GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent() 
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
	 * @return the programReferralNumber
	 */
	public String getProgramReferralNumber() {
		return programReferralNumber;
	}

	/**
	 * @param programReferralNumber the programReferralNumber to set
	 */
	public void setProgramReferralNumber(String programReferralNumber) {
		this.programReferralNumber = programReferralNumber;
	} 
	
}

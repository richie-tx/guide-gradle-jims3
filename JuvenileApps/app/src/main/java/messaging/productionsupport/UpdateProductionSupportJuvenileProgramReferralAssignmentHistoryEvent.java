package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId;
	private boolean createNewRecord;
	private String juvProgRefId;
   
	

/**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent() 
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

	public boolean isCreateNewRecord()
	{
	    return createNewRecord;
	}

	public void setCreateNewRecord(boolean createNewRecord)
	{
	    this.createNewRecord = createNewRecord;
	}

	public String getJuvProgRefId()
	{
	    return juvProgRefId;
	}

	public void setJuvProgRefId(String juvProgRefId)
	{
	    this.juvProgRefId = juvProgRefId;
	} 
	
	
   
}

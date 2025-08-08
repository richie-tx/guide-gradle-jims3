package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportCasefileClosingEvent extends RequestEvent 
{
  
	private String casefileId;
	private String mergeToCasefileId;
	private String supervisionOutcomeId;
	private String supervisionOutcomeDescriptionId;
	private String controllingReferralId;
	private Date supervisionEndDate = null;
	private String casefileClosingInfoId;
	private String juvLocUnitId;
	private String recordCLM; 
	 
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportCasefileClosingEvent() 
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
	 * @return the supervisionOutcomeId
	 */
	public String getSupervisionOutcomeId() {
		return supervisionOutcomeId;
	}
	
	
	/**
	 * @param supervisionOutcomeId the supervisionOutcomeId to set
	 */
	public void setSupervisionOutcomeId(String supervisionOutcomeId) {
		this.supervisionOutcomeId = supervisionOutcomeId;
	}
	
	
	/**
	 * @return the supervisionOutcomeDescriptionId
	 */
	public String getSupervisionOutcomeDescriptionId() {
		return supervisionOutcomeDescriptionId;
	}
	
	
	/**
	 * @param supervisionOutcomeDescriptionId the supervisionOutcomeDescriptionId to set
	 */
	public void setSupervisionOutcomeDescriptionId(
			String supervisionOutcomeDescriptionId) {
		this.supervisionOutcomeDescriptionId = supervisionOutcomeDescriptionId;
	}
	
	
	/**
	 * @return the controllingReferralId
	 */
	public String getControllingReferralId() {
		return controllingReferralId;
	}
	
	
	/**
	 * @param controllingReferralId the controllingReferralId to set
	 */
	public void setControllingReferralId(String controllingReferralId) {
		this.controllingReferralId = controllingReferralId;
	}
	
	
	/**
	 * @return the supervisionEndDate
	 */
	public Date getSupervisionEndDate() {
		return supervisionEndDate;
	}
	
	
	/**
	 * @param supervisionEndDate the supervisionEndDate to set
	 */
	public void setSupervisionEndDate(Date supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}


	/**
	 * @return the casefileClosingInfoId
	 */
	public String getCasefileClosingInfoId() {
		return casefileClosingInfoId;
	}


	/**
	 * @param casefileClosingInfoId the casefileClosingInfoId to set
	 */
	public void setCasefileClosingInfoId(String casefileClosingInfoId) {
		this.casefileClosingInfoId = casefileClosingInfoId;
	}

	public String getJuvLocUnitId()
	{
	    return juvLocUnitId;
	}

	public void setJuvLocUnitId(String juvLocUnitId)
	{
	    this.juvLocUnitId = juvLocUnitId;
	}

	public String getRecordCLM()
	{
	    return recordCLM;
	}

	public void setRecordCLM(String recordCLM)
	{
	    this.recordCLM = recordCLM;
	}	
	   
}

package messaging.productionsupport;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportRiskAnalysisEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId;
	private String riskAnalysisId;
	private Date dateEntered;
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportRiskAnalysisEvent() 
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
	 * @return the riskAnalysisId
	 */
	public String getRiskAnalysisId() {
		return riskAnalysisId;
	}

	/**
	 * @param riskAnalysisId the riskAnalysisId to set
	 */
	public void setRiskAnalysisId(String riskAnalysisId) {
		this.riskAnalysisId = riskAnalysisId;
	}

	/**
	 * @return the dateEntered
	 */
	public Date getDateEntered() {
		return dateEntered;
	}

	/**
	 * @param dateEntered the dateEntered to set
	 */
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	} 
	
	
   
}

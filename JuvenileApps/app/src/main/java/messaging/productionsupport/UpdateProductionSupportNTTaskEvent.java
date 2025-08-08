package messaging.productionsupport;

import mojo.km.messaging.RequestEvent;

public class UpdateProductionSupportNTTaskEvent extends RequestEvent 
{
   
	private String casefileId;
	private String mergeToCasefileId; 
	private String statusCd; 
   
   /**
    * @roseuid 45702FFC0393
    */
   public UpdateProductionSupportNTTaskEvent() 
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
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}


	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

}

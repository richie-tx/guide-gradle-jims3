//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCTreatmentIssuesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCTreatmentIssuesEvent extends RequestEvent 
{
   private String caseId;
   private String cdi;
   
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	
	/**
	 * @return Returns the caseId.
	 */
	public String getCriminalCaseId() {
		if(this.cdi != null && this.caseId != null){
			return new StringBuffer(cdi).append(this.caseId).toString();
		}
		return "";
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the cdi.
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @param cdi The cdi to set.
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
   /**
    * @roseuid 47D9BBF50307
    */
   public GetNCTreatmentIssuesEvent() 
   {
    
   }
}

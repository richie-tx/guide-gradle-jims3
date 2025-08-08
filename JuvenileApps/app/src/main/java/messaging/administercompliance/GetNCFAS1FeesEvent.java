//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCFeesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCFAS1FeesEvent extends RequestEvent 
{
   private String spn;
   private String caseId;
   private String cdi;
   
   
	public String getSpn() {
		return spn;
	}
	public void setSpn(String defendantId) {
		this.spn = defendantId;
	}
	/**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
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
    * @roseuid 47D9BBF002D8
    */
   public GetNCFAS1FeesEvent() 
   {
    
   }
}

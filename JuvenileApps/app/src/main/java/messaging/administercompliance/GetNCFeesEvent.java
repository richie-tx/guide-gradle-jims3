//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCFeesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCFeesEvent extends RequestEvent 
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
   public GetNCFeesEvent() 
   {
    
   }
}

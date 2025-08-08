//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCCommunityServicesEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;


public class GetNCRecommendationsEvent extends RequestEvent 
{
   private String caseId;
   private String cdi;
   
	
   /**
    * @roseuid 47D9BBEE0029
    */
   public GetNCRecommendationsEvent() 
   {
    
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
}

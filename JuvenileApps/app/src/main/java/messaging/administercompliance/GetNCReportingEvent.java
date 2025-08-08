//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCReportingEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetNCReportingEvent extends RequestEvent 
{
   private String caseId;
   private String defendantId;
   private String cdi;
   

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
    * @roseuid 47D9BBF3025B
    */
   public GetNCReportingEvent() 
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
    * @return Returns the defendantId.
    */
   public String getDefendantId() {
   	return defendantId;
   }
   /**
    * @param defendantId The defendantId to set.
    */
   public void setDefendantId(String defendantId) {
   	this.defendantId = defendantId;
   }
}

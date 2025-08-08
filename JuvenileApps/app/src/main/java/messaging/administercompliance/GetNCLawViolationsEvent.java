//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\GetNCLawViolationsEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetNCLawViolationsEvent extends RequestEvent 
{
   private String defendantId;
   private String activationDate;
   private String caseNum;
   
	/**
	 * @return the activationDate
	 */
	public String getActivationDate() {
		return activationDate;
	}
	
	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

/**
    * @roseuid 47D9BBF20077
    */
   public GetNCLawViolationsEvent() 
   {
    
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

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
}

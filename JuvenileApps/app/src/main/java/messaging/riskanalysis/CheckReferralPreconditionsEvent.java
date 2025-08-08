//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckReferralPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckReferralPreconditionsEvent extends RequestEvent 
{
   
	private String casefileID;
	private String juvenileNum;
	private boolean newReferral;

/**
    * @roseuid 4342C3BB028B
    */
   public CheckReferralPreconditionsEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getCasefileID()
	{
		return casefileID;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @param string
	 */
	public void setCasefileID(final String string)
	{
		casefileID = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(final String string)
	{
		juvenileNum = string;
	}
	
	/**
	 * @return newReferral
	 */
	public boolean isNewReferral() {
		return newReferral;
	}
	
	/**
	 * @param newReferral
	 */
	public void setNewReferral(boolean newReferral) {
		this.newReferral = newReferral;
	}

}

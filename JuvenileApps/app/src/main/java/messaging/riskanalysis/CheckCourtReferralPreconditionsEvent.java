/*
 * Created on Nov 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

/**
 * @author kmurthy
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class CheckCourtReferralPreconditionsEvent  extends RequestEvent
{
	private String caseFileId;
	private String juvenileNumber;
	
   
   /**
	* @roseuid 4357DCB40125
	*/
   public CheckCourtReferralPreconditionsEvent() 
   {
    
   }

	/**
	 * @return
	 */
	public String getCaseFileId()
	{
		return caseFileId;
	}

	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setCaseFileId(final String string)
	{
		caseFileId = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(final String string)
	{
		juvenileNumber = string;
	}

}

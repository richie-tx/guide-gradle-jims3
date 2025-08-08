//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckResidentialPreConditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckResidentialPreConditionsEvent extends RequestEvent 
{
	private String caseFileId;
	private String juvenileNumber;
	
   
   /**
    * @roseuid 4357DCB40125
    */
   public CheckResidentialPreConditionsEvent() 
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

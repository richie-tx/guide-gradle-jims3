//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetAllRiskAssessmentsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetAllRiskAssessmentsEvent extends RequestEvent 
{
   private String juvenileNumber;
   private String caseFileID;
   
   /**
    * @roseuid 433D3C6700F2
    */
   public GetAllRiskAssessmentsEvent() 
   {
    
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
	public void setJuvenileNumber(final String string)
	{
		juvenileNumber = string;
	}

	/**
	 * @return
	 */
	public String getCaseFileID()
	{
		return caseFileID;
	}
	
	/**
	 * @param string
	 */
	public void setCaseFileID(final String string)
	{
		caseFileID = string;
	}

}

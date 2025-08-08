//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetRiskAssessmentsByCasefileEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetRiskAssessmentsByCasefileEvent extends RequestEvent 
{
   private String casefileId;
   
   /**
    * @roseuid 433D3C6700F2
    */
   public GetRiskAssessmentsByCasefileEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getCasefileId()
	{
		return casefileId;
	}
	
	/**
	 * @param string
	 */
	public void setCasefileId(final String string)
	{
		casefileId = string;
	}

}

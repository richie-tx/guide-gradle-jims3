//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckReferralPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetLatestRiskAnalysisEvent extends RequestEvent 
{
   
	private String casefileID;
	private String assessmentType;
	
	

   /**
    * @roseuid 4342C3BB028B
    */
   public GetLatestRiskAnalysisEvent() 
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
	 * @param string
	 */
	public void setCasefileID(final String string)
	{
		casefileID = string;
	}

	/**
	 * @return
	 */
	public String getAssessmentType()
	{
		return assessmentType;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(String string)
	{
		assessmentType = string;
	}

}

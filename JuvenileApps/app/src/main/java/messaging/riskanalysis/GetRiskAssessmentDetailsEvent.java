//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\GetRiskAssessmentDetailsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class GetRiskAssessmentDetailsEvent extends RequestEvent 
{
	
	private String assessmentID;
	private String assessmentType;

   
   /**
    * @roseuid 433D83B10203
    */
   public GetRiskAssessmentDetailsEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getAssessmentID()
	{
		return assessmentID;
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
	public void setAssessmentID(final String string)
	{
		assessmentID = string;
	}

	/**
	 * @param string
	 */
	public void setAssessmentType(final String string)
	{
		assessmentType = string;
	}

}

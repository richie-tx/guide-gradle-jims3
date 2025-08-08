//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\riskanalysis\\CheckRiskAnalysisOneHourEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckRiskAnalysisOneHourEvent extends RequestEvent 
{
   
	private String casefileID;
	private String assessmentType;

	/**
    * @roseuid 4342C3BB028B
    */
   public CheckRiskAnalysisOneHourEvent() 
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
	 * @param string
	 */
	public String getAssessmentType() {
		return assessmentType;
	}
	
	/**
	 * @param assessmentType
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
}
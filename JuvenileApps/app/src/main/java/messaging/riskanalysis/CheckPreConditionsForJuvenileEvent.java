//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\juvenilecase\\CheckInterviewPreconditionsEvent.java

package messaging.riskanalysis;

import mojo.km.messaging.RequestEvent;

public class CheckPreConditionsForJuvenileEvent extends RequestEvent 
{
   private String juvenileNumber;
   private String assessmentType;
   
   /**
    * @roseuid 4342C3B102E1
    */
   public CheckPreConditionsForJuvenileEvent() 
   {
    
   }
	/**
	 * @return Returns the assessmentType.
	 */
	public String getAssessmentType() {
		return assessmentType;
	}
	/**
	 * @param assessmentType The assessmentType to set.
	 */
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
}

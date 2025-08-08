package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetRequestedBenefitsAssessmentDetailEvent extends RequestEvent 
{
   public String assessmentId;
   public String guardianId;
   
   /**
    * @roseuid 4370F7360204
    */
   public GetRequestedBenefitsAssessmentDetailEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getAssessmentId()
	{
		return assessmentId;
	}
	
	/**
	 * @return
	 */
	public String getGuardianId()
	{
		return guardianId;
	}
	
	/**
	 * @param string
	 */
	public void setAssessmentId(String string)
	{
		assessmentId = string;
	}
	
	/**
	 * @param string
	 */
	public void setGuardianId(String string)
	{
		guardianId = string;
	}

}

package messaging.family;

import mojo.km.messaging.RequestEvent;

public class GetBenefitsAssessmentDetailEvent extends RequestEvent 
{
   public String assessmentId;
   
   /**
    * @roseuid 4370F7360204
    */
   public GetBenefitsAssessmentDetailEvent() 
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
	 * @param string
	 */
	public void setAssessmentId(String string)
	{
		assessmentId = string;
	}

}

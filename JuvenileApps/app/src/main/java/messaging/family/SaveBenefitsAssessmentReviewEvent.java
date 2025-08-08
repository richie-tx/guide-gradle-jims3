package messaging.family;

import mojo.km.messaging.RequestEvent;

public class SaveBenefitsAssessmentReviewEvent extends RequestEvent 
{
   public String assessmentId;
   public String comments;
   
   /**
    * @roseuid 4370F7400027
    */
   public SaveBenefitsAssessmentReviewEvent() 
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
	public String getComments()
	{
		return comments;
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
	public void setComments(String string)
	{
		comments = string;
	}

}

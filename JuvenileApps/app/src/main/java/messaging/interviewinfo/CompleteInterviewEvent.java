package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class CompleteInterviewEvent extends RequestEvent 
{
	private String interviewId;
   
   /**
    * @roseuid 448ECBB503DC
    */
   public CompleteInterviewEvent() 
   {
    
   }

	/**
	 * @return Returns the interviewId.
	 */
	public String getInterviewId() 
	{
		return interviewId;
	}
	
	/**
	 * @param interviewId The interviewId to set.
	 */
	public void setInterviewId(String interviewId) 
	{
		this.interviewId = interviewId;
	}
	
}

package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class GetInterviewTaskListEvent extends RequestEvent 
{
   public String interviewId;
   
   /**
    * @roseuid 448ECBC30012
    */
   public GetInterviewTaskListEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getInterviewId()
	{
		return interviewId;
	}
	
	/**
	 * @param string
	 */
	public void setInterviewId(String string)
	{
		interviewId = string;
	}

}

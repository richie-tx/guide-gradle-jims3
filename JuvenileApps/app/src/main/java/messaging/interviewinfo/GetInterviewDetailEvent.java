package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;


public class GetInterviewDetailEvent extends RequestEvent 
{
	private String interviewId;
	
	/**
	* 
	*/
	public GetInterviewDetailEvent() 
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

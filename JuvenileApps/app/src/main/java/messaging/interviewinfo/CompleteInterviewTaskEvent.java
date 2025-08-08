package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class CompleteInterviewTaskEvent extends RequestEvent 
{
	private String interviewId;
	private List taskIds = new ArrayList();
   
   /**
    * @roseuid 448ECBB503DC
    */
   public CompleteInterviewTaskEvent() 
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
   
	/**
	 * @return
	 */
	public List getTaskIds()
	{
		return taskIds;
	}

}

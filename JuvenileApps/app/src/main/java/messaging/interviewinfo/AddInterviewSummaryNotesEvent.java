package messaging.interviewinfo;

import mojo.km.messaging.RequestEvent;

public class AddInterviewSummaryNotesEvent extends RequestEvent 
{
	private String interviewId;
	private String summaryNotes;
   
   
   /**
    * @roseuid 448ECBC002C0
    */
   public AddInterviewSummaryNotesEvent() 
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
	 * @return
	 */
	public String getSummaryNotes()
	{
		return summaryNotes;
	}

	/**
	 * @param string
	 */
	public void setInterviewId(String string)
	{
		interviewId = string;
	}

	/**
	 * @param string
	 */
	public void setSummaryNotes(String string)
	{
		summaryNotes = string;
	}

}

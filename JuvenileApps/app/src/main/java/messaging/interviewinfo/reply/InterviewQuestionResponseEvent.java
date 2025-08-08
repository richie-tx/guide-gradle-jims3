package messaging.interviewinfo.reply;

import mojo.km.messaging.ResponseEvent;

public class InterviewQuestionResponseEvent extends ResponseEvent 
{
	private String questionId;
	private String questionText;
	
   /**
    * @roseuid 448ECBC103D0
    */
   public InterviewQuestionResponseEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public String getQuestionId()
	{
		return questionId;
	}

	/**
	 * @return
	 */
	public String getQuestionText()
	{
		return questionText;
	}

	/**
	 * @param string
	 */
	public void setQuestionId(String string)
	{
		questionId = string;
	}

	/**
	 * @param string
	 */
	public void setQuestionText(String string)
	{
		questionText = string;
	}

}

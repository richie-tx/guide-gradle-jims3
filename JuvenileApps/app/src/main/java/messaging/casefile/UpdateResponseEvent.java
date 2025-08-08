//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\casefile\\UpdateResponseEvent.java

package messaging.casefile;

import mojo.km.messaging.RequestEvent;

public class UpdateResponseEvent extends RequestEvent 
{
   private String answerText;
   private String questionId;
   private String answerId;
   private String referenceId;
   private String responseId;
   private String responseType;
   
   /**
    * @roseuid 439601E100C9
    */
   public UpdateResponseEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getAnswerText()
	{
		return answerText;
	}
	
	/**
	 * @return
	 */
	public String getQuestionId()
	{
		return questionId;
	}
	
	/**
	 * @param string
	 */
	public void setAnswerText(String string)
	{
		answerText = string;
	}
	
	/**
	 * @param string
	 */
	public void setQuestionId(String string)
	{
		questionId = string;
	}
	
	/**
	 * @return
	 */
	public String getAnswerId()
	{
		return answerId;
	}
	
	/**
	 * @param string
	 */
	public void setAnswerId(String string)
	{
		answerId = string;
	}

	/**
	 * @return
	 */
	public String getResponseId()
	{
		return responseId;
	}
	
	/**
	 * @param string
	 */
	public void setResponseId(String string)
	{
		responseId = string;
	}

	/**
	 * @return
	 */
	public String getResponseType()
	{
		return responseType;
	}
	
	/**
	 * @param string
	 */
	public void setResponseType(String string)
	{
		responseType = string;
	}

	/**
	 * @return
	 */
	public String getReferenceId()
	{
		return referenceId;
	}
	
	/**
	 * @param string
	 */
	public void setReferenceId(String string)
	{
		referenceId = string;
	}

}

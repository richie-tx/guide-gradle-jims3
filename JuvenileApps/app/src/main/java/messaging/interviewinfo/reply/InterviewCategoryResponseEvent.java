package messaging.interviewinfo.reply;

import mojo.km.messaging.ResponseEvent;

public class InterviewCategoryResponseEvent extends ResponseEvent 
{
	private String categoryId;
	private String categoryName;
	
	
   /**
    * @roseuid 448ECBC103D0
    */
   public InterviewCategoryResponseEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getCategoryId()
	{
		return categoryId;
	}

	/**
	 * @return
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * @param string
	 */
	public void setCategoryId(String string)
	{
		categoryId = string;
	}

	/**
	 * @param string
	 */
	public void setCategoryName(String string)
	{
		categoryName = string;
	}

}

package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class GetInterviewQuestionsEvent extends RequestEvent 
{
	private List categoryIds = new ArrayList();
	
   /**
    * @roseuid 448ECBC103D0
    */
   public GetInterviewQuestionsEvent() 
   {
    
   }
	/**
	 * @return
	 */
	public List getCategoryIds()
	{
		return categoryIds;
	}


}

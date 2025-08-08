package messaging.interviewinfo;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class CreateInterviewTaskListEvent extends RequestEvent 
{
   private String interviewId;
   private List questionIds = new ArrayList();
   
   /**
    * @roseuid 448ECBC30012
    */
   public CreateInterviewTaskListEvent() 
   {
    
   }
   
   /**
    * @param supervisionNumber
    */
   public void setInterviewId(String anInterviewId) 
   {
    	interviewId = anInterviewId;
   }
   
   /**
    * @return String
    */
   public String getInterviewId() 
   {
   		return interviewId;
   }
   
	/**
	 * @return
	 */
	public List getQuestionIds()
	{
		return questionIds;
	}

}

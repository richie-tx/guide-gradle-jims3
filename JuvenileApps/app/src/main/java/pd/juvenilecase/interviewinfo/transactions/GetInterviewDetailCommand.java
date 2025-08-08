package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.GetInterviewDetailEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;

public class GetInterviewDetailCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
	public GetInterviewDetailCommand() 
	{
	}
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		GetInterviewDetailEvent request = (GetInterviewDetailEvent)event;
		String interviewId = request.getInterviewId();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			
		Interview interview = Interview.find( interviewId );
	
		InterviewDetailResponseEvent response = new InterviewDetailResponseEvent();
		interview.fillDetail( response );
		dispatch.postEvent(response);
	}
   
   /**
    * @param event
    * @roseuid 448D7EEE03B4
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE03BD
    */
   public void update(Object anObject) 
   {
    
   }
   
}

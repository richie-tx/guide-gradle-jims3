package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.CreateInterviewEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;

public class CreateInterviewCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC53B0336
    */
   public CreateInterviewCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EED037D
    */
	public void execute(IEvent event) 
	{
		CreateInterviewEvent evt = (CreateInterviewEvent)event;

		Interview interview = Interview.createDetail( evt );
		
		InterviewDetailResponseEvent response = new InterviewDetailResponseEvent();
		interview.fillDetail( response ); 
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(response);
	}
   
   /**
    * @param event
    * @roseuid 448D7EED037F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EED0381
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EED0383
    */
   public void update(Object anObject) 
   {
    
   }
   
}

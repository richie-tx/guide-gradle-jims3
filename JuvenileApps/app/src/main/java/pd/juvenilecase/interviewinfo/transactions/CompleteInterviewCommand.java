package pd.juvenilecase.interviewinfo.transactions;

import naming.InterviewConstants;
import messaging.interviewinfo.CompleteInterviewEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;

public class CompleteInterviewCommand implements ICommand 
{
   /**
    * 
    */
   public CompleteInterviewCommand() 
   {    
   }
   
   /**
    * @param event
    * 
    */
	public void execute(IEvent event) 
	{
		CompleteInterviewEvent evt = (CompleteInterviewEvent)event;

		Interview interview = Interview.find( evt.getInterviewId() );
		if( interview != null )
		{
			interview.setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_COMPLETE);

			InterviewDetailResponseEvent response = new InterviewDetailResponseEvent();
			interview.fillDetail( response );
			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(response);
		}
	}
   
   /**
    * @param event
    * 
    */
   public void onRegister(IEvent event) 
   {   
   }
   
   /**
    * @param event
    * 
    */
   public void onUnregister(IEvent event) 
   {
   }
   
   /**
    * @param anObject
    * 
    */
   public void update(Object anObject) 
   {
   }
}

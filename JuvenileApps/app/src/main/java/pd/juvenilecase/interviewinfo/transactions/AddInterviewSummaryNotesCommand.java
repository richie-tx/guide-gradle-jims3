package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.AddInterviewSummaryNotesEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;

public class AddInterviewSummaryNotesCommand implements ICommand 
{
   
   /**
    * 
    */
   public AddInterviewSummaryNotesCommand() 
   {
    
   }
   
   /**
    * @param event
    * 
    */
	public void execute(IEvent event) 
	{
		AddInterviewSummaryNotesEvent evt = (AddInterviewSummaryNotesEvent)event;
		
		Interview interview = Interview.find( evt.getInterviewId() );
		interview.setSummaryNotes(evt.getSummaryNotes());

		InterviewDetailResponseEvent response = new InterviewDetailResponseEvent();
		interview.fillDetail( response );
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(response);
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

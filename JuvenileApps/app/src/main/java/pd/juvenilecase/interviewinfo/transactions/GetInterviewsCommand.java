package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import messaging.interviewinfo.GetInterviewsEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.InterviewEventView;

public class GetInterviewsCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewsCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		GetInterviewsEvent request = (GetInterviewsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator interviews = null;
		
		if ( request.getJuvenileId() != null )
		{
			//interviews = Interview.findAllForJuvenile( request.getJuvenileId() );
			interviews = InterviewEventView.findAllForJuvenile( request.getJuvenileId() );
		}
		else
		{
			//interviews = Interview.findAllForCasefile( request.getCasefileId() );
			interviews = InterviewEventView.findAllForCasefile( request.getCasefileId() );
		}
		
		
		while ( interviews.hasNext() )
		{
			InterviewEventView interview = (InterviewEventView)interviews.next();

			InterviewResponseEvent response = new InterviewResponseEvent();
			response.setAttendanceStatusCd(interview.getAttendanceStatusCd());
			interview.fillSummary( response );
			
			//TaskCount is an attribute specific to InterviewEventView
			response.setTaskCount(interview.getTaskCount());
			
			dispatch.postEvent(response);
		}
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

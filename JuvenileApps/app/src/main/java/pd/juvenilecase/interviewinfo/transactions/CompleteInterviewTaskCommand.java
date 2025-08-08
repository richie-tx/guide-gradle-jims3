package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;
import java.util.List;

import naming.InterviewConstants;

import messaging.interviewinfo.CompleteInterviewTaskEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewTask;

public class CompleteInterviewTaskCommand implements ICommand 
{
   /**
    * 
    */
   public CompleteInterviewTaskCommand() 
   { 
   }
   
   /**
    * @param event
    * 
    */
	public void execute(IEvent event) 
	{
		CompleteInterviewTaskEvent evt = (CompleteInterviewTaskEvent)event;
		Interview interview = Interview.find( evt.getInterviewId() );
		
		if( interview != null )
		{
			List selectedList = evt.getTaskIds();
			Iterator<InterviewTask> taskIter = interview.getTasks().iterator();
			boolean interviewCompleted = true;
			while( taskIter.hasNext() )
			{
				InterviewTask task = taskIter.next();
				if( selectedList.contains(task.getOID().toString()) )
				{
					task.complete();				
				}
				else if( ! task.completed() )
				{
					interviewCompleted = false;
				}
			}

			if( interviewCompleted )
			{
				interview.setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_COMPLETE);
			}
			
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

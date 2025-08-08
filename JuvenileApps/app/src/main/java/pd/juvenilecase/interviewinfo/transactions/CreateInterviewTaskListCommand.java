package pd.juvenilecase.interviewinfo.transactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.CreateInterviewTaskListEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewQuestion;
import pd.juvenilecase.interviewinfo.InterviewTask;

public class CreateInterviewTaskListCommand implements ICommand 
{
   
   /**
    * 
    */
   public CreateInterviewTaskListCommand() 
   {
    
   }
   
   /**
    * @param event
    * 
    */
	public void execute(IEvent event) 
	{
		
		
		
		CreateInterviewTaskListEvent evt = (CreateInterviewTaskListEvent)event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		ArrayList questions = new ArrayList();
		Iterator ids = evt.getQuestionIds().iterator();
		while ( ids.hasNext() )
		{
			String id = (String)ids.next();
			questions.add( InterviewQuestion.find(id) );
		}
		
		Interview interview = Interview.find( evt.getInterviewId() );
		
		if ( interview != null)
		{
			Collection tasks = interview.createTasks( questions );
		
			Iterator taskIter = tasks.iterator();
			while ( taskIter.hasNext() )
			{
				InterviewTask task = (InterviewTask)taskIter.next();
				InterviewTaskResponseEvent response = new InterviewTaskResponseEvent();
				
				response.setTaskId( task.getOID().toString() );
				response.setTaskName( task.getSubject() );
				response.setExecTaskId( task.getExecTaskId() );
				response.setCompleted( task.completed() );
				dispatch.postEvent(response);
			}
		}
		else
		{
			ErrorResponseEvent error = new ErrorResponseEvent();
			error.setMessage( "error.failedToCreateTasks.dueToMissingInterviewInfo" );
			dispatch.postEvent(error);
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

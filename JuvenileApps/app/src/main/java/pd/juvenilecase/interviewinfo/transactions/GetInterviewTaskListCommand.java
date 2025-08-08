package pd.juvenilecase.interviewinfo.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.interviewinfo.GetInterviewTaskListEvent;
import messaging.interviewinfo.reply.InterviewTaskResponseEvent;
import messaging.task.domintf.ITaskState;
import messaging.task.to.TaskBean;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewTask;
import pd.task.Task;

public class GetInterviewTaskListCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC5510279
    */
   public GetInterviewTaskListCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE00FE
    */
	public void execute(IEvent event) 
	{
		GetInterviewTaskListEvent evt = (GetInterviewTaskListEvent)event;
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Interview interview = Interview.find( evt.getInterviewId() );
		
		
		
		Collection tasks = interview.getTasks();
		if (tasks!=null){
			Iterator taskIter = tasks.iterator();
			while ( taskIter.hasNext() )
			{
				InterviewTask itask = (InterviewTask)taskIter.next();
				Task task = itask.getTask();
				
				TaskBean taskBean = new TaskBean();
				task.fill( taskBean, true );
				
				InterviewTaskResponseEvent response = new InterviewTaskResponseEvent();
				
				response.setTaskId( itask.getOID().toString() );
				response.setTaskName( itask.getSubject() );
				response.setCompleted( itask.completed() );
				response.setExecTaskId( itask.getExecTaskId() );
				
				ITaskState state = taskBean.getTaskState();
				Iterator params = state.getKeys().iterator();
				while ( params.hasNext() )
				{
					String key = (String)params.next();
					response.setTaskParam( key, state.get(key) );
				}
				
				dispatch.postEvent(response);
			}
		}
	}
   
   /**
    * @param event
    * @roseuid 448D7EEE0100
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE0102
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 448D7EEE0104
    */
   public void update(Object anObject) 
   {
    
   }
   
}

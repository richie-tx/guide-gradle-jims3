package pd.juvenilecase.interviewinfo.transactions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.interviewinfo.GetInterviewQuestionsEvent;
import messaging.interviewinfo.reply.InterviewQuestionResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.InterviewQuestion;



public class GetInterviewQuestionsCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewQuestionsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03B2
    */
	public void execute(IEvent event) 
	{
		GetInterviewQuestionsEvent evt = (GetInterviewQuestionsEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		// track the questions sent to avoid duplicates.
		List sentQuestionIds = new ArrayList();
		
		Iterator categoryIds = evt.getCategoryIds().iterator();
		while ( categoryIds.hasNext() )
		{
			String categoryId = (String)categoryIds.next();
			
			Iterator questionIter = InterviewQuestion.findAllByCategoryId(categoryId);
			while ( questionIter.hasNext() )
			{
				InterviewQuestion question = (InterviewQuestion)questionIter.next();
				
				if ( ! sentQuestionIds.contains(question.getOID().toString()) )
				{
					sentQuestionIds.add( question.getOID().toString() );
					
					InterviewQuestionResponseEvent response = new InterviewQuestionResponseEvent();
					
					response.setQuestionId( question.getOID().toString() );
					response.setQuestionText( question.getText() );
					
					dispatch.postEvent(response);
				}
			}
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

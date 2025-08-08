package pd.juvenilecase.interviewinfo.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.interviewinfo.reply.InterviewCategoryResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;

public class GetInterviewCategoriesCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewCategoriesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 448D7EEE03B2
    */
	public void execute(IEvent event) 
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		Collection codes = Code.findAll("INTERVIEW_QUESTION_CATEGORY");
		
		Iterator iter = codes.iterator();
		while ( iter.hasNext() )
		{
			Code code = (Code)iter.next();
			InterviewCategoryResponseEvent response = new InterviewCategoryResponseEvent();
			
			response.setCategoryId( code.getCode() );
			response.setCategoryName( code.getDescription() );
			
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

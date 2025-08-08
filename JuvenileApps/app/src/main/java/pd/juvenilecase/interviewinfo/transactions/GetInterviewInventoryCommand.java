package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import messaging.interviewinfo.GetInterviewInventoryEvent;
import messaging.interviewinfo.reply.InterviewDetailResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.Interview;
import pd.juvenilecase.interviewinfo.InterviewInventoryRecord;

public class GetInterviewInventoryCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewInventoryCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		GetInterviewInventoryEvent request = (GetInterviewInventoryEvent)event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		Iterator interviews = null;
		if(request.getCasefileId()!=null && !request.getCasefileId().equals("")){
			interviews = Interview.findAllForCasefile( request.getCasefileId() );
		}
		else{
			if ( request.getJuvenileId() != null )
			{
				interviews = Interview.findAllForJuvenile( request.getJuvenileId() );
			}
		}
		if(interviews!=null){
			while ( interviews.hasNext() )
			{
				InterviewDetailResponseEvent response = new InterviewDetailResponseEvent();
				Interview interview = (Interview)interviews.next();								
				Iterator records = interview.getInventoryRecords().iterator();
				response.setInterviewId(interview.getOID()+"");
				response.setOtherInventoryRecords(interview.getOtherInventoryRecords());
				while ( records.hasNext() )
				{
					InterviewInventoryRecord rec = (InterviewInventoryRecord)records.next();
					if (rec.getRecordsInventoryId()!=null && !"".equals(rec.getRecordsInventoryId())){
						response.getInventoryRecordsIds().add(rec.getRecordsInventoryId());
					}	
				}												
				dispatch.postEvent(response);
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

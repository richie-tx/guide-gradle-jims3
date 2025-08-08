package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.GetInterviewReportEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.interviewinfo.InterviewDocument;

public class GetInterviewReportCommand implements ICommand 
{
   /**
    * @roseuid 448EC54501B8
    */
   public GetInterviewReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event) 
	{
		GetInterviewReportEvent request = (GetInterviewReportEvent)event;
		String casefileId = request.getReportId();

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		
		InterviewDocument doc = InterviewDocument.find( request.getReportId() );
		
		InterviewReportResponseEvent response = new InterviewReportResponseEvent();
			
		response.setReportId( doc.getOID().toString() );
		response.setCasefileId( casefileId );
		response.setCreationDate( doc.getCreationDate() );
		response.setReportType( doc.getDocumentTypeCode().getDescription() );
		response.setContent( (byte[])doc.getDocument() );
		
		//since spaces is prohibited for file names, substitute spaces for underline
		String fileName = doc.getDocumentTypeCode().getDescription().replace(' ', '_');
		
		response.setFileName( fileName );
			
		dispatch.postEvent(response);
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

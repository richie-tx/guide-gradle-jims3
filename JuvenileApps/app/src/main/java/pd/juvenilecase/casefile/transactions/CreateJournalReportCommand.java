package pd.juvenilecase.casefile.transactions;

import messaging.casefile.CreateJournalReportEvent;
import messaging.journal.to.JournalTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.report.transactions.PDFReporting;

public class CreateJournalReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public CreateJournalReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event)
		throws Exception
	{
		CreateJournalReportEvent request = (CreateJournalReportEvent)event;
		
		String casefileId = request.getCasefileId();
		JournalTO reportTO = request.getJournalDataTO();
		
				
		// Build report.
		request.setReportName("REPORTING::CHRONOLOGICAL_DICTATION_JOURNAL");
		request.addDataObject(reportTO);
		
        IReport report  = PDFReporting.getInstance();
		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
	
		try
		{
			respEvent.setContent(report.getByteOutput(request));
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw(e);
		}
		
		InterviewDocument document = new InterviewDocument();
		document.setDocument( respEvent.getContent() );
		document.setCasefileId( casefileId );
		document.setDocumentTypeCodeId( "SHR" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
		
		dispatch.postEvent(respEvent);
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

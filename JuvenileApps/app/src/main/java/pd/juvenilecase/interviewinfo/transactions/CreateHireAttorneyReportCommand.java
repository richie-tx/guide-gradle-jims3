package pd.juvenilecase.interviewinfo.transactions;

import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.CreateHireAttorneyReportEvent;
import messaging.interviewinfo.to.HireAttorneyReportDataTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

public class CreateHireAttorneyReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public CreateHireAttorneyReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event)
		throws Exception
	{
		CreateHireAttorneyReportEvent request = (CreateHireAttorneyReportEvent)event;

		HireAttorneyReportDataTO to = InterviewHelper.buildHireAttorneyReportData( request );
		if( (to.getJjsOffenses() == null || to.getJjsOffenses().size() < 1) && 
			(to.getJotRecord() == null || to.getJotRecord().size() < 1) ){
			ErrorResponseEvent errorEvent = new ErrorResponseEvent();
            errorEvent.setMessage("errors.noReferralWithFutureCourtDate");
            IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
            dispatch.postEvent(errorEvent);
            return;
		}
		
		request.addDataObject(to);
		
		// AKA: Rights of Parents Worksheet.
		request.setReportName("REPORTING::HIRE_ATTORNEY_REPORT");
		
        IReport report  = PDFReporting.getInstance();
		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
		respEvent.setContent(report.getByteOutput(request));
		respEvent.setContentType(report.getContentType());
		respEvent.setFileName(report.getTemplate());
		
		InterviewDocument document = new InterviewDocument();
		document.setDocument( respEvent.getContent() );
		document.setCasefileId( request.getCasefileId() );
		document.setDocumentTypeCodeId( "RPW" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
		
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

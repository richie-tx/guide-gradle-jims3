package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.CreateParentalStatementReportEvent;
import messaging.interviewinfo.to.ParentalStatementReportDataTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

public class CreateParentalStatementReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public CreateParentalStatementReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event)
		throws Exception
	{
		CreateParentalStatementReportEvent request = (CreateParentalStatementReportEvent)event;
		
		String casefileId = request.getCasefileId();
		if( casefileId != null && casefileId.trim().length() > 0 )
		{
			JuvenileCasefile casefile = JuvenileCasefile.find( casefileId );
			// Profile stripping fix task 97536
			//Juvenile juvenile = casefile.getJuvenile();
			JuvenileCore juvenile = casefile.getJuvenile();
			//
			
			ParentalStatementReportDataTO reportTO = new ParentalStatementReportDataTO();
			InterviewHelper.buildParentalStatementReportData( reportTO, juvenile, casefile );
			request.addDataObject(reportTO);
	
			if ( request.isSpanishText() )
			{
				request.setReportName("REPORTING::PARENTAL_STATEMENT_REPORT_ES");
			}
			else
			{
				request.setReportName("REPORTING::PARENTAL_STATEMENT_REPORT_EN");
			}
			
      IReport report = PDFReporting.getInstance();
			ReportResponseEvent respEvent = new ReportResponseEvent();
			respEvent.setContent(report.getByteOutput(request));
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());
			
			InterviewDocument document = new InterviewDocument();
			document.setDocument( respEvent.getContent() );
			document.setCasefileId( casefileId );
			document.setDocumentTypeCodeId( "PWS" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
			
			IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
			dispatch.postEvent(respEvent);
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

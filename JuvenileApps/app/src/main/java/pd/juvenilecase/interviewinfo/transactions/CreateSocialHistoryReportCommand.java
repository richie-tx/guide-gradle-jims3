package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.CreateSocialHistoryReportEvent;
import messaging.interviewinfo.to.SocialHistoryReportDataTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

public class CreateSocialHistoryReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public CreateSocialHistoryReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event)
		throws Exception
	{
		CreateSocialHistoryReportEvent request = (CreateSocialHistoryReportEvent)event;
		
		String casefileId = request.getCasefileId();
		SocialHistoryReportDataTO reportTO = request.getSocialHistoryReportDataTO();
		
		// Prepare report fields. 
		InterviewHelper.prepareSocialHistoryReportData( reportTO, casefileId, request.isReportToReferee() );
		
		String docTypeCodeId = "SHR";  //default value 
		// Build report.
		if( request.isGeneric() )
		{
			request.setReportName("REPORTING::SOCIAL_HISTORY_REPORT_GENERIC");
			docTypeCodeId = "CRS"; // CRIS report
		} else if (request.isReportToReferee()) {
			request.setReportName("REPORTING::SOCIAL_HISTORY_REFEREE_TO_INITIATION");
			docTypeCodeId = "RTR";  // Report to Referee
			} else {
				request.setReportName("REPORTING::SOCIAL_HISTORY_REPORT");
		}
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
		document.setDocumentTypeCodeId(docTypeCodeId );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
		
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

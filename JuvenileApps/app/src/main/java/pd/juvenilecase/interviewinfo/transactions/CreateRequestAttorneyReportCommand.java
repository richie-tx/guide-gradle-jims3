package pd.juvenilecase.interviewinfo.transactions;

import messaging.interviewinfo.CreateRequestAttorneyReportEvent;
import messaging.interviewinfo.to.RequestAppointedAttorneyReportDataTO;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.report.transactions.PDFReporting;

public class CreateRequestAttorneyReportCommand implements ICommand 
{
   
   /**
    * @roseuid 448EC54501B8
    */
   public CreateRequestAttorneyReportCommand() 
   {
   }
   
	/**
	 * @param event
	 * @roseuid 448D7EEE03B2
	 */
	public void execute(IEvent event)
		throws Exception
	{
		CreateRequestAttorneyReportEvent request = (CreateRequestAttorneyReportEvent)event;
		String casefileId = request.getCasefileId();
		
		JuvenileCasefile casefile = JuvenileCasefile.find( casefileId );
		// Profile stripping fix task 97536
		//Juvenile juvenile = casefile.getJuvenile();
		JuvenileCore juvenile = casefile.getJuvenile();
		//
		String juvenileId = casefile.getJuvenileNum();
		
		RequestAppointedAttorneyReportDataTO to = InterviewHelper.buildRequestAppointedAttorneyReportData( request );
		request.addDataObject(to);
		
		request.setReportName("REPORTING::REQUEST_ATTORNEY_REPORT");

        IReport report      = PDFReporting.getInstance();
		IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
		respEvent.setContent(report.getByteOutput(request));
		respEvent.setContentType(report.getContentType());
		respEvent.setFileName(report.getTemplate());
		/*
		InterviewDocument document = new InterviewDocument();
		document.setDocument( respEvent.getContent() );
		document.setCasefileId( casefileId );
		document.setDocumentTypeCodeId( "RAA" );	// CODE_TABLE_NAME = INTERVIEW_DOCTYPE 
		*/
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

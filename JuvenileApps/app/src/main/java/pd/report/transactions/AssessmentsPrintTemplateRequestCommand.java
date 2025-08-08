package pd.report.transactions;

import messaging.report.AssessmentsPrintTemplateRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;

public class AssessmentsPrintTemplateRequestCommand implements ICommand{

	   /**
	* @param event
	* @roseuid 42FBA15403DA
	*/
   public void execute(IEvent event) throws Exception
   {
	   		AssessmentsPrintTemplateRequestEvent waReqEvent = (AssessmentsPrintTemplateRequestEvent)event;			

	   		IReport report  = PDFReporting.getInstance();
			IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
			ReportResponseEvent respEvent = new ReportResponseEvent();
			respEvent.setContent(report.getByteOutput(waReqEvent));
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());
			dispatch.postEvent(respEvent);
   }
   
   /**
	* @param event
	* @roseuid 42FBA15403DC
	*/
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
	* @param event
	* @roseuid 42FBA15403DE
	*/
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
	* @param anObject
	* @roseuid 42FBA1550000
	*/
   public void update(Object anObject) 
   {
    
   }
}

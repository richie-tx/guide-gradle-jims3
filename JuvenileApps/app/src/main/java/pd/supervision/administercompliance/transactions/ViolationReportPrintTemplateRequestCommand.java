package pd.supervision.administercompliance.transactions;

import pd.report.transactions.PDFReporting;
import messaging.administercompliance.ViolationReportPrintTemplateRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;

public class ViolationReportPrintTemplateRequestCommand implements ICommand {
	
	   public void execute(IEvent event) throws Exception
	   {
		   ViolationReportPrintTemplateRequestEvent waReqEvent = (ViolationReportPrintTemplateRequestEvent)event;			

            IReport report = PDFReporting.getInstance();
			IDispatch dispatch 	= EventManager.getSharedInstance(EventManager.REPLY);
			ReportResponseEvent respEvent = new ReportResponseEvent();
			respEvent.setContent(report.getByteOutput(waReqEvent));
			respEvent.setContentType(report.getContentType());
			respEvent.setFileName(report.getTemplate());
			dispatch.postEvent(respEvent);
	   }
}

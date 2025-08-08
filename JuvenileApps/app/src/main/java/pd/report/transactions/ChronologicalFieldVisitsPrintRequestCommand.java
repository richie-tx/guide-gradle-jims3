//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\report\\transactions\\SupervisionOrderPrintTemplateRequestCommand.java

package pd.report.transactions;

import messaging.report.ChronologicalFieldVisitsPrintRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;

//import mojo.km.reporting.ReportManager;

public class ChronologicalFieldVisitsPrintRequestCommand implements ICommand {

	/**
	 * @roseuid 42FBA49F00EA
	 */
	public ChronologicalFieldVisitsPrintRequestCommand() {

	}

	/**
	 * @param event
	 * @roseuid 42FBA15403DA
	 */
	public void execute(IEvent event) throws Exception {
		ChronologicalFieldVisitsPrintRequestEvent fvReqEvent = (ChronologicalFieldVisitsPrintRequestEvent) event;

		IReport report = PDFReporting.getInstance();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		ReportResponseEvent respEvent = new ReportResponseEvent();
		respEvent.setContent(report.getByteOutput(fvReqEvent));
		respEvent.setContentType(report.getContentType());
		respEvent.setFileName(report.getTemplate());
		dispatch.postEvent(respEvent);
	}

	/**
	 * @param event
	 * @roseuid 42FBA15403DC
	 */
	public void onRegister(IEvent event) {

	}

	/**
	 * @param event
	 * @roseuid 42FBA15403DE
	 */
	public void onUnregister(IEvent event) {

	}

	/**
	 * @param anObject
	 * @roseuid 42FBA1550000
	 */
	public void update(Object anObject) {

	}

}

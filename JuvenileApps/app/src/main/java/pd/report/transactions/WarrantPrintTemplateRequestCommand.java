//Source file: C:\\views\\dev\\app\\src\\pd\\report\\transactions\\WarrantPrintTemplateRequestCommand.java

package pd.report.transactions;

import messaging.report.WarrantPrintTemplateRequestEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import mojo.km.reporting.ReportManager;

public class WarrantPrintTemplateRequestCommand implements ICommand 
{
   
   /**
    * @roseuid 42FBA49F00EA
    */
   public WarrantPrintTemplateRequestCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42FBA15403DA
    */
   public void execute(IEvent event) throws Exception
   {
			WarrantPrintTemplateRequestEvent waReqEvent = (WarrantPrintTemplateRequestEvent)event;
			IReport report 		= ReportManager.getInstance();
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

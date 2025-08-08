//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\GetNCResponsesCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.Iterator;

import messaging.administercompliance.GetNCResponsesEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercompliance.ViolationReport;
/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetNCResponsesCommand implements ICommand 
{
   
   /**
    * @roseuid 47DA96940206
    */
   public GetNCResponsesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF7C03AB
    */
   public void execute(IEvent event) 
   {
   	   GetNCResponsesEvent gEvent = (GetNCResponsesEvent) event;
	   
	   Iterator iterator = ViolationReport.findAll(gEvent);
	   while(iterator.hasNext()){
	       ViolationReport vr = (ViolationReport) iterator.next();
	       if(vr != null){
	       	   NCResponseResponseEvent resp = vr.getResponseEvent();
	       	   MessageUtil.postReply(resp);
	       }
	   }
   }
   
   /**
    * @param event
    * @roseuid 47D5AF7C03B9
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D5AF7C03BB
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D5AF7C03C8
    */
   public void update(Object anObject) 
   {
    
   }
   
   
}

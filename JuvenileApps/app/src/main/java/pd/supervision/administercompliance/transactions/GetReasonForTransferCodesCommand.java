//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\transactions\\GetNCFilingInfoCommand.java

package pd.supervision.administercompliance.transactions;

import java.util.Iterator;

import messaging.administercompliance.GetReasonForTransferCodesEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import pd.supervision.administercompliance.ReasonForTransferCode;
/*
 * @mchowdhury
 */
public class GetReasonForTransferCodesCommand implements ICommand 
{
   
   /**
    * @roseuid 47DA968C035D
    */
   public GetReasonForTransferCodesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D9A5E4024F
    */
   public void execute(IEvent event) 
   {
	    GetReasonForTransferCodesEvent gEvent = (GetReasonForTransferCodesEvent) event;
	    Iterator codeIter = ReasonForTransferCode.findAll(naming.ViolationReportConstants.REPORTTYPE, gEvent.getRequestType());
		while(codeIter.hasNext()){
			ReasonForTransferCode rCode = (ReasonForTransferCode) codeIter.next();
			MessageUtil.postReply(rCode.getResponse());
		}
   }
   
   /**
    * @param event
    * @roseuid 47D9A5E4025E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 47D9A5E40260
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 47D9A5E4026D
    */
   public void update(Object anObject) 
   {
    
   }  
 
}

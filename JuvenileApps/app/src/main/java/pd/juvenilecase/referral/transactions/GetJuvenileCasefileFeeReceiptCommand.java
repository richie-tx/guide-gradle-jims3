//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetJuvenileCasefileFeeReceiptCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import pd.juvenilecase.referral.JUVFeeReceipt;
import messaging.referral.GetJuvenileCasefileFeeReceiptEvent;
import messaging.referral.reply.JuvenileFeeReceiptResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileFeeReceiptCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB246012B
    */
   public GetJuvenileCasefileFeeReceiptCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34E003C
    */
   public void execute(IEvent event) 
   {
   	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   	GetJuvenileCasefileFeeReceiptEvent evt = (GetJuvenileCasefileFeeReceiptEvent)event;
    Iterator receiptIter = JUVFeeReceipt.findAll(evt);
    while(receiptIter.hasNext())
    {
    	JUVFeeReceipt receipt = (JUVFeeReceipt)receiptIter.next();
    	JuvenileFeeReceiptResponseEvent resp = new JuvenileFeeReceiptResponseEvent();
    	resp.setReceiptNum(receipt.getReceiptNum());
    	resp.setPaidDate(receipt.getPaymentDate());
    	dispatch.postEvent(resp);    	
    }
    
  }
   
   /**
    * @param event
    * @roseuid 467AD34E008B
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34E00AA
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467AD34E00E8
    */
   public void update(Object anObject) 
   {
    
   } 
 
}

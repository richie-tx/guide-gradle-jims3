//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetJuvenileCasefileFeeListCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import pd.juvenilecase.referral.JUVFeePayment;
import messaging.referral.GetJuvenileCasefileFeeListEvent;
import messaging.referral.reply.JuvenileFeeResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileFeeListCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB22A0206
    */
   public GetJuvenileCasefileFeeListCommand() 
   {   		
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34A03C7
    */
   public void execute(IEvent event) 
   {
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		GetJuvenileCasefileFeeListEvent evt = (GetJuvenileCasefileFeeListEvent)event;
		Iterator feePaymentIter = JUVFeePayment.findAll(evt);
		while(feePaymentIter.hasNext())
		{
			JUVFeePayment feePayment = (JUVFeePayment)feePaymentIter.next();
			JuvenileFeeResponseEvent resp = getFeeResponse(feePayment);			
			dispatch.postEvent(resp);
			
		}
   }
    private JuvenileFeeResponseEvent getFeeResponse(JUVFeePayment feePayment)
    {
    	JuvenileFeeResponseEvent resp = new JuvenileFeeResponseEvent();
    	if (!"".equals(feePayment.getFeeCodeId()) && feePayment.getFeeCodeId() != null) {    		
    			resp.setFeeType(feePayment.getFeeCodeId()+ " (" +feePayment.getFeeCode().getDescription() +") "+ feePayment.getRevisionCode());
    	}
    	//resp.setFeeType(feePayment.getFeeCodeId());
		resp.setCaseNum(feePayment.getCaseNum());
		resp.setDueDate(feePayment.getDueDate());
		if(feePayment.getFeeStatusId()!=null && feePayment.getFeeStatusId().equalsIgnoreCase("P"))
			resp.setStatus(PDJuvenileCaseConstants.JUVENILE_FEE_STATUS_P);
		else if(feePayment.getFeeStatusId()!=null && feePayment.getFeeStatusId().equalsIgnoreCase("D"))
			resp.setStatus(PDJuvenileCaseConstants.JUVENILE_FEE_STATUS_D);
		else if(feePayment.getFeeStatusId()!=null && feePayment.getFeeStatusId().equalsIgnoreCase("S"))
			resp.setStatus(PDJuvenileCaseConstants.JUVENILE_FEE_STATUS_S);
		else if(feePayment.getFeeStatusId()!=null && feePayment.getFeeStatusId().equalsIgnoreCase("W"))
			resp.setStatus(PDJuvenileCaseConstants.JUVENILE_FEE_STATUS_W);		
		if(feePayment.getAmountDue().equalsIgnoreCase("W"))
		{
			resp.setCurrentBalance(new Double(0.00));
			resp.setTotalDue(new Double(0.00));
		}
		else
		{
			Double amtDue = new Double(0.0);
			Double amtPaid = new Double(0.0);
			if(!"".equals(feePayment.getAmountDue()) && feePayment.getAmountDue() != null) {
				amtDue = new Double(feePayment.getAmountDue());
				resp.setTotalDue(new Double(amtDue.doubleValue()/100));
			}
			if(!"".equals(feePayment.getAmountPaid()) && feePayment.getAmountPaid() != null) {
				amtPaid = new Double(feePayment.getAmountPaid());
				resp.setTotalPaid(new Double(amtPaid.doubleValue()/100));
			}
			else
				resp.setTotalPaid(amtPaid);
//			if (feePayment.getAmountPaid() != null && feePayment.getAmountDue() != null)
				resp.setCurrentBalance(new Double((amtDue.doubleValue()-amtPaid.doubleValue())/100));				
			
		}
		resp.setReceivedDate(feePayment.getReceivedDate());
		if(feePayment.getPayorTypeId().equalsIgnoreCase("A"))
			resp.setPayorType(PDJuvenileCaseConstants.JUVENILE_FEE_PAYOR_TYPE_A);
		else if(feePayment.getPayorTypeId().equalsIgnoreCase("O"))
			resp.setPayorType(PDJuvenileCaseConstants.JUVENILE_FEE_PAYOR_TYPE_O);
		resp.setTransactionNum(feePayment.getTransactionNum());
		resp.setPayorId(feePayment.getPayorId());
		resp.setPaidDate(feePayment.getPaymentDate());
		resp.setCodeId(feePayment.getFeeCodeId());
		return resp;
    }
   /**
    * @param event
    * @roseuid 467AD34A03C9
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34B003C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467AD34B005C
    */
   public void update(Object anObject) 
   {
    
   }  
 
}

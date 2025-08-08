//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetJuvenileCasefileFeePayorDetailsCommand.java

package pd.juvenilecase.referral.transactions;

import naming.PDJuvenileCaseConstants;
import pd.juvenilecase.referral.JUVBillingAddress;
import pd.juvenilecase.referral.PIDAddress;
import messaging.contact.to.PhoneNumberBean;
import messaging.referral.GetJuvenileCasefileFeePayorDetailsEvent;
import messaging.referral.reply.JuvenileFeePayorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileFeePayorDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 467FB23F009F
    */
   public GetJuvenileCasefileFeePayorDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34C0280
    */
   public void execute(IEvent event) 
   {
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
   		GetJuvenileCasefileFeePayorDetailsEvent evt = (GetJuvenileCasefileFeePayorDetailsEvent)event;
   		JuvenileFeePayorResponseEvent resp = new JuvenileFeePayorResponseEvent();
   		if(evt.getPayorType().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_FEE_PAYOR_TYPE_A))
   	    {
   	    		PIDAddress addr = PIDAddress.find(evt.getPayorId());
   	    		resp.setPayorAddress(addr.getAddress());
   	    		if(addr.getPhoneNum()!=null && !addr.getPhoneNum().equals(""))
   	    			resp.setPhone(new PhoneNumberBean(addr.getPhoneNum()));  
   	    		//resp.setPhone(new PhoneNumberBean("")); 
   	    }
   	    else if(evt.getPayorType().equalsIgnoreCase(PDJuvenileCaseConstants.JUVENILE_FEE_PAYOR_TYPE_O))
   	    {
   	    	JUVBillingAddress addr = JUVBillingAddress.find(evt.getPayorId());
   	    	
   	    	resp.setPayor(addr.getPayorName());
   	    	StringBuffer buff = new StringBuffer();
   	    	buff.append(addr.getStreetNum()+" ");
   	    	buff.append(addr.getStreetName());
   	    	if( addr.getAptNum()!=null &&  !addr.getAptNum().equals(""))
   	    		buff.append(" #" + addr.getAptNum());
			buff.append("; "+addr.getCity());
   	    	buff.append(", "+addr.getState().getDescription()+"; "+addr.getZipCode());
   	    	resp.setPayorAddress(buff.toString());
   	    	if(addr.getPhoneNum()!=null && !addr.getPhoneNum().equals(""))
   	    		resp.setPhone(new PhoneNumberBean(addr.getPhoneNum()));
   	    }
   	    dispatch.postEvent(resp);
   }
   
   /**
    * @param event
    * @roseuid 467AD34C028F
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 467AD34C02BD
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 467AD34C02BF
    */
   public void update(Object anObject) 
   {
    
   }
 
}

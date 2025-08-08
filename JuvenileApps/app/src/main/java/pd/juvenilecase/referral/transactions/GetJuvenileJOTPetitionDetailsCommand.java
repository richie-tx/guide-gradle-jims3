//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import naming.PDJuvenileCaseConstants;

import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JuvenileOffenderTrackingCharge;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.referral.GetJJSOffenseByInvestnoEvent;
import messaging.referral.GetJuvenileJOTPetitionDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author anpillai
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileJOTPetitionDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetJuvenileJOTPetitionDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
   {
       GetJuvenileJOTPetitionDetailsEvent request = (GetJuvenileJOTPetitionDetailsEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    	
		Iterator<JuvenileOffenderTrackingCharge> chargeIter = JuvenileOffenderTrackingCharge.findAll( request );	
    	while (chargeIter.hasNext())
    	{
    	    	JuvenileOffenderTrackingCharge c = (JuvenileOffenderTrackingCharge) chargeIter.next();
    	    	JuvenileOffenderTrackingChargeResponseEvent resp = new JuvenileOffenderTrackingChargeResponseEvent();
    		resp.setPetitionNum(c.getPetitionNum());
    		resp.setCJISNum(c.getCJISNum());
    		resp.setRejIndicator(c.getRejIndicator());
    		/*resp.setOffenseDate(o.getOffenseDate());
    		resp.setOffenseDescription(o.getOffenseDescription());
    		resp.setReferralNum(o.getReferralNum());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setCatagory(o.getCatagory());
    		resp.setCitationCode(o.getCitationCode());
    		resp.setCitationSource(o.getCitationSource());
    		resp.setSequenceNum(o.getSequenceNum());
    		resp.setInvestigationNum(o.getInvestigationNumber());
    		resp.setOffenseReportGroup(o.getOffenseReportGroup());
    		resp.setOffenseCodeId(o.getOffenseCodeId());*/
    		   		
    		dispatch.postEvent(resp);
    	}
   }
   
   /**
    * @param event
    * @roseuid 42A99B980162
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B98016B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42A99B98016D
    */
   public void update(Object anObject) 
   {
    
   }
}

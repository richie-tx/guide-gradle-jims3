//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\referral\\transactions\\GetTransferredOffenseReferralsCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.referral.GetVOPDetailsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.referral.JCVOP;

/**
 * 
 * @author nmathew
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetVOPDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetVOPDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
    {
	GetVOPDetailsEvent vopEvt = (GetVOPDetailsEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator<JCVOP> vopReferralsItr  = JCVOP.findAll(vopEvt);
	SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
	while (vopReferralsItr.hasNext())
	{
	   JCVOP vopRef = (JCVOP) vopReferralsItr.next();
	   JuvenileReferralVOPResponseEvent vopRespEvent = new JuvenileReferralVOPResponseEvent();
	   vopRespEvent.setReferralNum(vopRef.getReferralNumber());
	   vopRespEvent.setJuvenileNum(vopRef.getJuvenileNumber());
	   vopRespEvent.setAdultIndicatorStr(vopRef.getAdultIndicatorStr());
	   vopRespEvent.setLocationIndicator(vopRef.getLocationIndicator());
	   vopRespEvent.setVopOffenseCode(vopRef.getVOPOffenseCode());
	   vopRespEvent.setInCCountyOrigPetitionedRefNum(vopRef.getInCCountyOrigPetitionedRefNum());
	   vopRespEvent.setOffenseCharge(vopRef.getOffenseCharge());
	   vopRespEvent.setOffenseChargeDate(vopRef.getOffenseChargeDate());
	   vopRespEvent.setReferralDate(vopRef.getReferralDate());
	   vopRespEvent.setOID(vopRef.getOID());
	   dispatch.postEvent(vopRespEvent);
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

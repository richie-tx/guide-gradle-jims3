//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetVOPDetailsJuvNumEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JCVOP;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetVOPDetailsJuvNumCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetVOPDetailsJuvNumCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
    {
	GetVOPDetailsJuvNumEvent juvVOP = (GetVOPDetailsJuvNumEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	Iterator vopReferrals = JCVOP.findAll("juvenileNumber", juvVOP.getJuvenileNum());

	GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
	while (vopReferrals.hasNext())
	{
	    JCVOP vopRef = (JCVOP) vopReferrals.next();
	    JuvenileReferralVOPResponseEvent vopRespEvent = new JuvenileReferralVOPResponseEvent();
	    vopRespEvent.setReferralNum(vopRef.getReferralNumber());
	    vopRespEvent.setReferralDate(vopRef.getReferralDate());
	    vopRespEvent.setVopOffenseCode(vopRef.getVOPOffenseCode());
	    JuvenileOffenseCode vopOffenseCode = JuvenileOffenseCode.find("offenseCode", vopRef.getVOPOffenseCode());
	    vopRespEvent.setVopOffenseCodeDesc(vopOffenseCode.getShortDescription());
	    vopRespEvent.setReferralDateStr(sdf.format(vopRef.getReferralDate()));
	    vopRespEvent.setAdultIndicatorStr(vopRef.getAdultIndicatorStr());
	    vopRespEvent.setInCCountyOrigPetitionedRefNum(vopRef.getInCCountyOrigPetitionedRefNum());
	    vopRespEvent.setOffenseCharge(vopRef.getOffenseCharge());
	    vopRespEvent.setOffenseChargeDate(vopRef.getOffenseChargeDate());
	    vopRespEvent.setLocationIndicator(vopRef.getLocationIndicator());
	   /* JuvenileOffenseCode offenseCharge = JuvenileOffenseCode.find("offenseCode", vopRef.getOffenseCharge());
	    vopRespEvent.setOffenseChargeDesc(offenseCharge.getShortDescription());*/
	    if (vopRef.getOffenseChargeDate() != null)
	    {
		vopRespEvent.setOffenseChargeDateStr(sdf.format(vopRef.getOffenseChargeDate()));
	    }
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

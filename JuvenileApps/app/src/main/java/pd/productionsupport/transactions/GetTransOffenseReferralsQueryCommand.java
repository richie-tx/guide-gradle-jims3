package pd.productionsupport.transactions;
 
import java.util.Iterator;

import pd.juvenilecase.referral.JJSTransferredOffenseReferral;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;

public class GetTransOffenseReferralsQueryCommand implements ICommand
{
    @Override
    public void execute(IEvent event) throws Exception
    {
	GetTransOffenseReferralsQueryEvent getEvent = (GetTransOffenseReferralsQueryEvent)event;
	String juvenileNumber = getEvent.getJuvenileNumber();
	String referralNumber = getEvent.getReferralNumber();
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if ( juvenileNumber != null 
		&& juvenileNumber.length() > 0
		&& referralNumber != null
		&& referralNumber.length() > 0 ) {
	    
	    Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll(getEvent);
	    
	    while(transOffenseReferralsIter.hasNext()) {
		JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
		JuvenileCasefileTransferredOffenseResponseEvent jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
		jcEvent.setJuvenileNum(transOffenseReferral.getJuvenileNumber());
		jcEvent.setReferralNum(transOffenseReferral.getReferralNumber());
		jcEvent.setTransOffenseReferralId(transOffenseReferral.getOID());
		jcEvent.setOffenseCode(transOffenseReferral.getOffenseCode());
		jcEvent.setCountyId(transOffenseReferral.getFromCountyCode());
		dispatch.postEvent(jcEvent);
	    }
	}
	else if(juvenileNumber != null 
		&& juvenileNumber.length() > 0)
	{
	    Iterator<JJSTransferredOffenseReferral> transOffenseReferralsIter = JJSTransferredOffenseReferral.findAll("juvenileNumber", juvenileNumber);
	    
	    while(transOffenseReferralsIter.hasNext()) {
		JJSTransferredOffenseReferral transOffenseReferral = (JJSTransferredOffenseReferral) transOffenseReferralsIter.next();
		JuvenileCasefileTransferredOffenseResponseEvent jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
		jcEvent.setJuvenileNum(transOffenseReferral.getJuvenileNumber());
		jcEvent.setReferralNum(transOffenseReferral.getReferralNumber());
		jcEvent.setTransOffenseReferralId(transOffenseReferral.getOID());
		jcEvent.setOffenseCode(transOffenseReferral.getOffenseCode());
		jcEvent.setCountyId(transOffenseReferral.getFromCountyCode());
		jcEvent.setPersonId(transOffenseReferral.getPersonId());
		jcEvent.setAdjudicationDate(transOffenseReferral.getAdjudicationDate());
		jcEvent.setDpsCode(transOffenseReferral.getDpsCode());
		jcEvent.setCategory(transOffenseReferral.getCategoryCode());
		jcEvent.setOffenseDate(transOffenseReferral.getOffenseDate());
		dispatch.postEvent(jcEvent);
	    }
	}
      }
    
}

//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetTransferredOffenseReferralsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSTransferredOffenseReferral;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetTransferredOffenseReferralsCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetTransferredOffenseReferralsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
   {
	   GetTransferredOffenseReferralsEvent tor = (GetTransferredOffenseReferralsEvent) event;
	   IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	   Iterator referrals = JJSTransferredOffenseReferral.findAll("juvenileNumber", tor.getJuvenileNum());
	   GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	   SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
	   while (referrals.hasNext())
	   {
			JJSTransferredOffenseReferral ref = (JJSTransferredOffenseReferral) referrals.next();
			JuvenileCasefileTransferredOffenseResponseEvent jcEvent = new JuvenileCasefileTransferredOffenseResponseEvent();
			refEvent.setJuvenileNum(ref.getJuvenileNumber());
			refEvent.setReferralNum(ref.getReferralNumber());
			Iterator<JJSReferral> i = JJSReferral.findAll(refEvent);
			while(i.hasNext())
			{
			    JJSReferral ref1 = (JJSReferral) i.next();
    			    jcEvent.setReferralNum(ref.getReferralNumber());
    			    jcEvent.setCountyId(ref.getFromCountyCode());
    			    jcEvent.setPersonId(ref.getPersonId());//US 11081
    			    jcEvent.setOffenseCode(ref.getOffenseCode());
    			    // add code to load short desc
    			    JuvenileOffenseCode offenseCode = JuvenileOffenseCode.find("offenseCode",ref.getOffenseCode());
    			    jcEvent.setOffenseShortDesc(offenseCode.getShortDescription());
    			    jcEvent.setCategory(ref.getCategoryCode());
    			    jcEvent.setDpsCode(ref.getDpsCode());
    			    jcEvent.setOffenseDate(ref.getOffenseDate());
    			    jcEvent.setAdjudicationDate(ref.getAdjudicationDate());	
    			    if(ref.getAdjudicationDate() != null){ //BUG 87254 //might not be needed, be keeping it to be in the safe side
    				jcEvent.setAdjudicationDateStr(sdf.format(ref.getAdjudicationDate())); //added for Task 11181 to show the required format of the adjudication date on report
    			    }
    			    jcEvent.setSeveritySubType(offenseCode.getSeveritySubtype());// added for user-story #32226
    			    dispatch.postEvent(jcEvent);

			}

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

//Source file: C:\\views\\dev\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileOffensesCommand.java

package pd.juvenilecase.referral.transactions;

import java.util.Iterator;

import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;

import pd.juvenilecase.Assignment;
import pd.juvenilecase.referral.JJSOffense;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.referral.GetCasefileOffensesEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetCasefileOffensesCommand implements ICommand 
{
   
   /**
    * @roseuid 42A9A3020387
    */
   public GetCasefileOffensesCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42A99B980159
    */
   public void execute(IEvent event) 
   {
    	GetCasefileOffensesEvent off = (GetCasefileOffensesEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
    	
	    Iterator referrals = Assignment.findAll("caseFileId", off.getCasefileId());
   		
		while (referrals.hasNext())
		{
			Assignment ref = (Assignment) referrals.next();
		
			GetJuvenileCasefileOffensesEvent casefileOffensesEvent =
						(GetJuvenileCasefileOffensesEvent) EventFactory.getInstance(
					JuvenileReferralControllerServiceNames.GETJUVENILECASEFILEOFFENSES);
			casefileOffensesEvent.setJuvenileNum(off.getJuvenileNum());
			casefileOffensesEvent.setReferralNum(ref.getReferralNumber());
			
			Iterator i = JJSOffense.findAll(casefileOffensesEvent);	
			while (i.hasNext())
			{
				JJSOffense o = (JJSOffense) i.next();
				JJSOffenseResponseEvent resp = new JJSOffenseResponseEvent();
				resp.setTopic(PDJuvenileCaseConstants.JUVENILE_OFFENSES_TOPIC);
				resp.setJuvenileNum(o.getJuvenileNum());
				resp.setOffenseDate(o.getOffenseDate());
				resp.setOffenseDescription(o.getOffenseDescription());
				resp.setReferralNum(o.getReferralNum());
				resp.setSequenceNum(o.getSequenceNum());
				resp.setSeveritySubtype(o.getOffenseCode().getSeveritySubtype());  // User-story 11079
				dispatch.postEvent(resp);
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

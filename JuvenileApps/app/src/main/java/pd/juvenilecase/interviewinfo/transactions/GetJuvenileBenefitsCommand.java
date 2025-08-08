//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\interviewinfo\\transactions\\GetJuvenileBenefitsCommand.java

package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import pd.juvenile.JuvenileBenefit;
import pd.juvenile.JuvenileHelper;

import messaging.interviewinfo.GetJuvenileBenefitsEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;


/**
 * 
 * Eligibility Types:  (for reference) 
		VABN	VA BENEFITS
		T4EC	TITLE 4E CERTIFIED 
		T4EN	TITLE 4E CANDIDATE
		SOCS	SOCIAL SECURITY 
		MCAD	MEDICAID
		MEDI	MEDICARE 
		CHIP	CHIPS
		CHMP	CHAMPUS
		AFDC 	AFDC    
   
 */
public class GetJuvenileBenefitsCommand implements ICommand 
{
   
   /**
    * @roseuid 43F37ACD00EC
    */
   public GetJuvenileBenefitsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371BF0367
    */
   public void execute(IEvent event) 
   {
		GetJuvenileBenefitsEvent requestEvent = (GetJuvenileBenefitsEvent)event;
			 
		Iterator iterator = JuvenileBenefit.findAll("juvenileNum", requestEvent.getJuvenileNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			JuvenileBenefit benefit = (JuvenileBenefit) iterator.next();
			if(benefit != null)
			{
				if ( requestEvent.isTitle4eAndMedicaidOnly() && ! (
						benefit.getEligibilityTypeId().equals("MCAD") ||
						benefit.getEligibilityTypeId().equals("T4EN") ) )
				{
					continue;
				}
				JuvenileBenefitResponseEvent reply = JuvenileHelper.getJuvenileBenefitResponseEvent(benefit);
				dispatch.postEvent(reply);
			}
		}
   }
   
   
   /**
    * @param event
    * @roseuid 43F371BF0369
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371BF036B
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43F371BF036D
    */
   public void update(Object anObject) 
   {
    
   }
   
}

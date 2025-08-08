//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\interviewinfo\\transactions\\UpdateJuvenileBenefitsCommand.java

package pd.juvenilecase.interviewinfo.transactions;

import java.util.Iterator;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileBenefit;
import messaging.interviewinfo.UpdateJuvenileBenefitsEvent;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class UpdateJuvenileBenefitsCommand implements ICommand 
{
   
   /**
    * @roseuid 43F37ACE0292
    */
   public UpdateJuvenileBenefitsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371C30182
    */
   public void execute(IEvent event) 
   {
		UpdateJuvenileBenefitsEvent saveBenefitEvent = (UpdateJuvenileBenefitsEvent)event;
		
		Iterator iter = saveBenefitEvent.getUpdateEvents().iterator();
		while ( iter.hasNext() )
		{
			JuvenileBenefitResponseEvent evt= (JuvenileBenefitResponseEvent)iter.next();
			
			if(saveBenefitEvent.getAction()!=null && !saveBenefitEvent.getAction().equalsIgnoreCase("update"))
			{					
				JuvenileBenefit benefit = new JuvenileBenefit();
				benefit.setEligibilityTypeId(evt.getEligibilityTypeId());
				benefit.setReceivingBenefits(evt.isReceivingBenefits());
				benefit.setEligibleForBenefits(evt.isEligibleForBenefits());
				benefit.setIdNumber(evt.getIdNumber());
				benefit.setReceivedAmt(evt.getReceivedAmt());
				benefit.setReceivedByFirstName(evt.getReceivedBy().getFirstName());
				benefit.setReceivedByMiddleName(evt.getReceivedBy().getMiddleName());
				benefit.setReceivedByLastName(evt.getReceivedBy().getLastName());
				benefit.setBenefitStatus(evt.getBenefitStatus());	
				
				// Profile stripping fix task 97541
				//Juvenile juv = Juvenile.find( saveBenefitEvent.getJuvenileNum() );
				Juvenile juv = Juvenile.findJCJuvenile( saveBenefitEvent.getJuvenileNum() );
				//
				juv.insertBenefits(benefit);
			}
			else if(saveBenefitEvent.getAction()!=null && saveBenefitEvent.getAction().equalsIgnoreCase("update"))
			{
				JuvenileBenefit benefit = JuvenileBenefit.find(evt.getBenefitId());
				
				benefit.setIdNumber(evt.getIdNumber());
				benefit.setReceivedAmt(evt.getReceivedAmt());				
				benefit.setBenefitStatus(evt.getBenefitStatus());				
			}
		}
   }
   
   /**
    * @param event
    * @roseuid 43F371C3018E
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43F371C30190
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 43F371C30192
    */
   public void update(Object anObject) 
   {
    
   }
   
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\transactions\\GetSupervisionPlanDetailsCommand.java

package pd.supervision.administersupervisionplan.transactions;

import pd.supervision.administersupervisionplan.SupervisionPlan;
import pd.supervision.administersupervisionplan.SupervisionPlanHelper;
import messaging.administersupervisionplan.GetSupervisionPlanDetailsEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanDetailsResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;

public class GetSupervisionPlanDetailsCommand implements ICommand 
{
   
   /**
    * @roseuid 4817675D0302
    */
   public GetSupervisionPlanDetailsCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E2165033A
    */
   public void execute(IEvent event) 
   {
	   GetSupervisionPlanDetailsEvent reqEvent = (GetSupervisionPlanDetailsEvent)event;
	   SupervisionPlan supervisionPlan = SupervisionPlan.find(reqEvent.getSupervisionPlanId());
	   
	   if(supervisionPlan != null)
	   {
		   SupervisionPlanDetailsResponseEvent responseEvt = new SupervisionPlanDetailsResponseEvent();
		   SupervisionPlanHelper.populateSupervisionPlanDetails(responseEvt, supervisionPlan);
		   
		   MessageUtil.postReply(responseEvt);
	   }
   }
   
   /**
    * @param event
    * @roseuid 480E2165033C
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E2165033E
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 480E2165034B
    */
   public void update(Object anObject) 
   {
    
   }
   
}

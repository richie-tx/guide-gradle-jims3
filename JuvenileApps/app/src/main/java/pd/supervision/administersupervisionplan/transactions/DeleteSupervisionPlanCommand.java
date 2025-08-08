//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administersupervisionplan\\transactions\\DeleteSupervisionPlanCommand.java

package pd.supervision.administersupervisionplan.transactions;

import pd.supervision.administersupervisionplan.SupervisionPlan;
import messaging.administersupervisionplan.DeleteSupervisionPlanEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteSupervisionPlanCommand implements ICommand 
{
   
   /**
    * @roseuid 4817675D0247
    */
   public DeleteSupervisionPlanCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E2167011A
    */
   public void execute(IEvent event) 
   {
	   DeleteSupervisionPlanEvent reqEvent = (DeleteSupervisionPlanEvent)event;
	   String supervisionPlanId = reqEvent.getSupervisionPlanId();
	   
	   SupervisionPlan supervisionPlan = SupervisionPlan.find(supervisionPlanId);
	   if(supervisionPlan != null)
	   {
		   supervisionPlan.delete();
	   }
	   
   }
   
   /**
    * @param event
    * @roseuid 480E21670127
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 480E21670129
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 480E2167012B
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}

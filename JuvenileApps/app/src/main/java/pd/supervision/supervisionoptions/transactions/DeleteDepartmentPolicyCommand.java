//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\DeleteDepartmentPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Date;

import pd.supervision.supervisionoptions.AgencyPolicy;
import messaging.supervisionoptions.DeleteDepartmentPolicyEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class DeleteDepartmentPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C434003E
    */
   public DeleteDepartmentPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A080158
    */
   public void execute(IEvent event) 
   {
		DeleteDepartmentPolicyEvent evt = (DeleteDepartmentPolicyEvent)event;
		
		AgencyPolicy agencyPolicy = AgencyPolicy.find( evt.getDepartmentPolicyId() );
		
		if ( agencyPolicy != null && !evt.isInUse())
		{
			agencyPolicy.delete();
		}
		else if(agencyPolicy != null && evt.isInUse()){
			agencyPolicy.setInactiveDate(new Date());
		}
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A08015A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F79A08015C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F79A08015E
    */
   public void update(Object anObject) 
   {
    
   }
   
}

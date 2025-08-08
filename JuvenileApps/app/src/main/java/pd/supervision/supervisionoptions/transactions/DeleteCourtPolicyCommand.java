//Source file: C:\\views\\dev\\app\\src\\pd\\supervision\\SupervisionOptions\\transactions\\DeleteCourtPolicyCommand.java

package pd.supervision.supervisionoptions.transactions;

import java.util.Date;

import pd.supervision.supervisionoptions.CourtPolicy;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import messaging.supervisionoptions.DeleteCourtPolicyEvent;

public class DeleteCourtPolicyCommand implements ICommand 
{
   
   /**
    * @roseuid 42F7C432035B
    */
   public DeleteCourtPolicyCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B0292
    */
   public void execute(IEvent event) 
   {
		DeleteCourtPolicyEvent evt = (DeleteCourtPolicyEvent)event;
		
		CourtPolicy courtPolicy = CourtPolicy.find( evt.getPolicyId() );
		
		if ( courtPolicy != null && !evt.isInUse())
		{
			courtPolicy.delete();
		}
		else if(courtPolicy != null && evt.isInUse()){
			courtPolicy.setInactiveDate(new Date());
		}
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B0294
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 42F7997B029F
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 42F7997B02A1
    */
   public void update(Object anObject) 
   {
    
   }
   
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\juvenilecase\\transactions\\GetJuvenileCasefileCourtOrdersCommand.java

package pd.juvenilecase.referral.transactions;

import messaging.referral.GetJuvenileCasefileCourtOrdersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

public class GetJuvenileCasefileCourtOrdersCommand implements ICommand 
{
   
   /**
    * @roseuid 46AF864702E9
    */
   public GetJuvenileCasefileCourtOrdersCommand() 
   {
   	
   }
   
   /**
    * @param event
    * @roseuid 46AF804801C9
    */
   public void execute(IEvent event) 
   {
   	GetJuvenileCasefileCourtOrdersEvent evt = (GetJuvenileCasefileCourtOrdersEvent)event;
   }
   
   /**
    * @param event
    * @roseuid 46AF804801CB
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 46AF804801D8
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 46AF804801DA
    */
   public void update(Object anObject) 
   {
    
   }
   
  
}

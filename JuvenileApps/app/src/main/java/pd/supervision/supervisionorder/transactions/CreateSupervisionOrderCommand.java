//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\supervisionorder\\transactions\\CreateSupervisionOrderCommand.java

package pd.supervision.supervisionorder.transactions;

import pd.supervision.supervisionorder.SupervisionOrderHelper;
import messaging.supervisionorder.CreateSupervisionOrderEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author asrvastava
 *
 *	This command is used to create the order. Order is created in "Incomplete" state
 *  in this command.
 */
public class CreateSupervisionOrderCommand implements ICommand 
{
   
   /**
    * @roseuid 43BECFD80131
    */
   public CreateSupervisionOrderCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43BECD3500D5
    */
   public void execute(IEvent event) 
   {
		//long timeStart = System.currentTimeMillis();
		CreateSupervisionOrderEvent reqEvent = (CreateSupervisionOrderEvent)event;

		SupervisionOrderHelper.createSupervisionOrder(reqEvent);

		//long timeEnd = System.currentTimeMillis();
		//System.out.println("***********PD Time(milli seconds) to display Condition detail page : " + (timeEnd - timeStart));
			 	    
   }
   
   /**
    * @param event
    * @roseuid 43BECD3500D7
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 43BECD3500D9
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param updateObject
    * @roseuid 43BECFD80150
    */
   public void update(Object updateObject) 
   {
    
   }
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\UpdateNonCompliantEaventCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.RemoveNonCompliantEventEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.SupervisionOrderConditionConstants;
import pd.common.CommandUtil;
import pd.common.DAOHandler;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class RemoveNonCompliantEventCommand  extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 473B887F03AF
    */
   public RemoveNonCompliantEventCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560388
    */
   public void execute(IEvent event) 
   {
   	   RemoveNonCompliantEventEvent sEvent = (RemoveNonCompliantEventEvent) event;
   	   DAOHandler handler = getHandler(SupervisionOrderConditionConstants.REMOVE_NONCOMPLIANT_EVENT_DAO_LOCATOR);
	   handler.execute(sEvent);	   
    }
   
   /**
    * @param event
    * @roseuid 473B7556038A
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B7556038C
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560398
    */
   public void update(Object anObject) 
   {
    
   }
}

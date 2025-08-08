//Source file: C:\\views\\CommonSupervision\\app\\src\\pd\\supervision\\administercompliance\\administerconditioncompliance\\transactions\\GetNonCompliantEventsCommand.java

package pd.supervision.administercompliance.transactions;

import messaging.administercompliance.SetToNonCompliantEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import naming.SupervisionOrderConditionConstants;
import pd.common.CommandUtil;
import pd.common.DAOContextFactory;
import pd.common.DAOHandler;
import pd.exception.ReflectionException;


/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SetToNonCompliantCommand extends CommandUtil implements ICommand 
{
   
   /**
    * @roseuid 473B887E0371
    */
   public SetToNonCompliantCommand() 
   {

   }
   
   /**
    * @param event
    * @roseuid 473B75560233
    */
   public void execute(IEvent event) 
   { 
   	   SetToNonCompliantEvent sEvent = (SetToNonCompliantEvent) event;
       DAOHandler handler = getHandler(SupervisionOrderConditionConstants.SET_TO_COMPLIANT_DAO_LOCATOR);
	   handler.execute(sEvent);
   }
  
   /**
    * @param event
    * @roseuid 473B75560240
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 473B75560242
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 473B75560244
    */
   public void update(Object anObject) 
   {
    
   }
}

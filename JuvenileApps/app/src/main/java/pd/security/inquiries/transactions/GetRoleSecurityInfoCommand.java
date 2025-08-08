//Source file: C:\\views\\Security\\app\\src\\pd\\security\\transactions\\GetRoleSecurityInfoCommand.java

package pd.security.inquiries.transactions;

import naming.ResponseLocatorConstants;
import messaging.inquiries.GetRoleSecurityInfoEvent;
import messaging.security.inquiries.reply.RoleSecurityInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.exception.ReflectionException;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

public class GetRoleSecurityInfoCommand implements ICommand 
{
   
   /**
    * @roseuid 44E9D2BF009A
    */
   public GetRoleSecurityInfoCommand() 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9B4B403DC
    */
   public void execute(IEvent event) 
   {/*
   	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	    GetRoleSecurityInfoEvent getRoleSecurityInfoEvent = (GetRoleSecurityInfoEvent) event;
   		Role role = Role.find(getRoleSecurityInfoEvent.getRoleId());
   		
   		if(role != null){
   			ResponseContextFactory respFac = new ResponseContextFactory();
	   		ResponseCreator srCreator =  null;
	   		try {
				srCreator = (ResponseCreator) respFac.lookup(ResponseLocatorConstants.SECURITY_ROLE_RESPONSE_LOCATOR);
			} catch (ReflectionException e) {
				e.printStackTrace();
			}
			RoleSecurityInfoResponseEvent resp = (RoleSecurityInfoResponseEvent) srCreator.create(role);
			dispatch.postEvent(resp);
   		}*/ //87191
   }
   
   /**
    * @param event
    * @roseuid 44E9B4B50021
    */
   public void onRegister(IEvent event) 
   {
    
   }
   
   /**
    * @param event
    * @roseuid 44E9B4B50030
    */
   public void onUnregister(IEvent event) 
   {
    
   }
   
   /**
    * @param anObject
    * @roseuid 44E9B4B50032
    */
   public void update(Object anObject) 
   {
    
   }
}

//Source file: C:\\views\\archproduction\\app\\src\\pd\\security\\transactions\\ValidateUserGroupDetailsCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.security.ValidateRoleEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
import messaging.security.reply.DuplicateRoleErrorResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Role;
import pd.security.PDSecurityHelper;

public class ValidateRoleCommand implements ICommand
{

	/**
	 * @roseuid 4297213601E9
	 */
	public ValidateRoleCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC02FF
	 */
	public void execute(IEvent event)
	{
		/*IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		ValidateRoleEvent validateEvent = (ValidateRoleEvent) event;
		
		boolean doesRoleExist = false;
		Collection agencyIds = validateEvent.getAgencyIdList();
		Iterator iter = agencyIds.iterator();
		while(iter.hasNext()){
		   String agencyId = (String) iter.next();
		   doesRoleExist = Role.isRoleExistsWithinAgency("name",validateEvent.getRoleName().toUpperCase(),agencyId);
		   if(doesRoleExist){ 
			   DuplicateRoleErrorResponseEvent errorEvent = new DuplicateRoleErrorResponseEvent();  
			   errorEvent.setRoleName(validateEvent.getRoleName()); 
			   errorEvent.setAgencyId(agencyId);
			   dispatch.postEvent(errorEvent);
   	           break;  
		   }
		 }*/ //87191
 	}

	/**
	 * @param event
	 * @roseuid 428B82BC0301
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 428B82BC0303
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 428B82BC0305
	 */
	public void update(Object anObject)
	{

	}
}
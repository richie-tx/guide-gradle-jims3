//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRolesCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;
import pd.contact.agency.Agency;
import messaging.security.GetSARoleAgencyInfoEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;

/**
 * @author mchowdhury
 * This command returns agency Info of the Role.
 */
public class GetSARoleAgencyInfoCommand implements ICommand
{

	/**
	 * @roseuid 42569402000F
	 */
	public GetSARoleAgencyInfoCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800CC
	 */
	public void execute(IEvent event)
	{
		/*RoleResponseEvent responseEvent = new RoleResponseEvent();
		GetSARoleAgencyInfoEvent aEvent = (GetSARoleAgencyInfoEvent) event;
		Role role = Role.find(aEvent.getRoleId());
		Collection agencies = role.getConstraintsByConstrainerType(Agency.class);
		Iterator iter = agencies.iterator();
		while(iter.hasNext()){
			Constraint agEvent = (Constraint) iter.next();
			Agency agency = (Agency) agEvent.getConstrainerObject();
			if(agency != null){
				responseEvent .setAgencyId(agency.getAgencyId());
			    responseEvent.setAgencyName(agency.getAgencyName());
			}
			// Since this is for SA only, we are not expecting more than one here
			break;
		}
		responseEvent.setRoleId(role.getOID().toString());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(responseEvent);*/ //87191
		
	}

	/**
	 * @param event
	 * @roseuid 425551F800CE
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800D0
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 425551F800D2
	 */
	public void update(Object anObject)
	{

	}

}

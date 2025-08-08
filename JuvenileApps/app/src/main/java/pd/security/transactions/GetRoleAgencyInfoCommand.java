//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRolesCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.security.GetRoleAgencyInfoEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;
import pd.contact.agency.Agency;
import pd.security.PDSecurityHelper;

/**
 * @author mchowdhury
 * This command returns agency name as comma seperated + info of the Role.
 */
public class GetRoleAgencyInfoCommand implements ICommand
{

	/**
	 * @roseuid 42569402000F
	 */
	public GetRoleAgencyInfoCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 425551F800CC
	 */
	public void execute(IEvent event)
	{
	    //87191
	/*	GetRoleAgencyInfoEvent aEvent = (GetRoleAgencyInfoEvent) event;
		Role role = Role.find(aEvent.getRoleId());
		Collection agencies = role.getConstraintsByConstrainerType(Agency.class);
		int size = agencies.size();
		Iterator iterAgencies = agencies.iterator();
		StringBuffer buffer = new StringBuffer();
		int counter = 0;
		while(iterAgencies.hasNext()){
			Constraint cons = (Constraint) iterAgencies.next();
			Agency agency = (Agency) cons.getConstrainerObject();
		    AgencyResponseEvent agencyEvent = PDSecurityHelper.getAgencyResponseEvent(agency);
		    buffer.append(agencyEvent.getAgencyName());
		    counter++;
		    if(counter != size)
		    {
		       buffer.append(",");
		    }
		}
		RoleResponseEvent responseEvent = PDSecurityHelper.getRoleResponseEvent(role);
		responseEvent.setAgencyName(buffer.toString());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		dispatch.postEvent(responseEvent);*/
		
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

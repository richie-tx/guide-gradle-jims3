//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRoleContrainsInfoCommand.java

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;

import naming.PDSecurityConstants;
import messaging.security.GetSARoleByAgencyIdEvent;
import messaging.security.reply.ConstraintResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.Role;

/**
 * @author mchowdhury
 * This command returns roles given a set of constraints
 */
public class GetSARoleByAgencyIdCommand implements ICommand
{

	/**
	 * @roseuid 425AB0E600BB
	 */
	public GetSARoleByAgencyIdCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4256E9710082
	 */
	public void execute(IEvent event)
	{/*
		GetSARoleByAgencyIdEvent cEvent = (GetSARoleByAgencyIdEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Collection coll = Constraint.findByConstrainerId(cEvent.getAgencyId(),PDSecurityConstants.AGENCY,PDSecurityConstants.ROLE);
		Iterator iter = coll.iterator();
		if(iter.hasNext())
		{
			Constraint constraint = (Constraint) iter.next();
			if(constraint != null){
				Role role = Role.find(constraint.getConstrainsId());
				if(role != null && role.getRoleType() != null && role.getRoleType().equalsIgnoreCase(PDSecurityConstants.USER_TYPE_SA)){
					RoleResponseEvent resp = new RoleResponseEvent();
					resp.setRoleId(role.getRoleId());
					dispatch.postEvent(resp);
				}
			}
		}*/ //87191
	}

	/**
	 * @param event
	 * @roseuid 4256E9710084
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4256E971008C
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4256E971008E
	 */
	public void update(Object anObject)
	{

	}
}

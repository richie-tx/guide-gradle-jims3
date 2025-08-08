/*
 * Project: JIMS2
 * Class:   pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand
 * Version: 1.0.0
 *
 * Date:    2005-05-03
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.security.transactions;

import java.util.Collection;
import java.util.Iterator;
import pd.contact.agency.Department;
import messaging.security.GetDepartmentConstraintsForUserAdministrationEvent;
import messaging.security.reply.DepartmentConstraintsForUserAdministrationResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;
import mojo.km.security.User;

/**
 * Class GetDepartmentContraintsForUserAdminstrationCommand.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetDepartmentConstraintsForUserAdministrationCommand
	implements mojo.km.context.ICommand
{
	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 42712F1C00DA
	 */
	public GetDepartmentConstraintsForUserAdministrationCommand()
	{

	} //end of pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand.GetDivisionContraintsForUserAdminstrationCommand

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event @roseuid 42711B0D01E9
	 */
	public void execute(IEvent event)
	{
		GetDepartmentConstraintsForUserAdministrationEvent dEvent =
			(GetDepartmentConstraintsForUserAdministrationEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		/*User user = User.find(dEvent.getLogonId()); //87191
		if (user != null)
		{
			Collection constraints =
				user.getConstraintsByConstrainerType(Department.class);
			Iterator constraintsIterator = constraints.iterator();
			while (constraintsIterator.hasNext())
			{
				Constraint constraint = (Constraint) constraintsIterator.next();
				DepartmentConstraintsForUserAdministrationResponseEvent rEvent =
					new DepartmentConstraintsForUserAdministrationResponseEvent();
				rEvent.setDepartmentId(constraint.getConstrainerId());
				Department department =  Department.find(constraint.getConstrainerId());
				if(department != null){
					rEvent.setDepartmentName(department.getDepartmentName());
				}
				dispatch.postEvent(rEvent);
			}
		}*/ //87191
	} //end of pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand.execute

	/**
	 *  
	 * @param event @roseuid 42711B0D01EB
	 */
	public void onRegister(IEvent event)
	{

	} //end of pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand.onRegister

	/**
	 *  
	 * @param event @roseuid 42711B0D01ED
	 */
	public void onUnregister(IEvent event)
	{

	} //end of pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand.onUnregister

	/**
	 *  
	 * @param anObject @roseuid 42711B0D01F5
	 */
	public void update(Object anObject)
	{

	} //end of pd.security.transactions.GetDepartmentConstraintsForUserAdministrationCommand.update

} // end GetDepartmentConstraintsForUserAdministrationCommand

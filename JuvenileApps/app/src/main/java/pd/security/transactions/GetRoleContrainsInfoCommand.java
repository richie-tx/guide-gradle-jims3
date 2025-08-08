//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\pd\\security\\transactions\\GetRoleContrainsInfoCommand.java

package pd.security.transactions;

import java.util.Iterator;

import messaging.security.GetRoleContrainsInfoEvent;
import messaging.security.reply.ConstraintResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.security.Constraint;

/**
 * @author mchowdhury
 * This command returns roles given a set of constraints
 */
public class GetRoleContrainsInfoCommand implements ICommand
{

	/**
	 * @roseuid 425AB0E600BB
	 */
	public GetRoleContrainsInfoCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4256E9710082
	 */
	public void execute(IEvent event)
	{

		/*GetRoleContrainsInfoEvent cEvent = (GetRoleContrainsInfoEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator constraints = Constraint.findAll(cEvent);
		while (constraints.hasNext())
		{
			Constraint contraint = (Constraint) constraints.next();
			ConstraintResponseEvent responseEvent =
				new ConstraintResponseEvent();
			responseEvent.setConstraintId(contraint.getOID().toString());
			responseEvent.setConstrainsId(contraint.getConstrainsId());
			responseEvent.setConstrainerId(contraint.getConstrainerId());
			responseEvent.setConstrainerType(contraint.getConstrainerType());
			responseEvent.setConstrainsType(contraint.getConstrainsType());
			dispatch.postEvent(responseEvent);

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

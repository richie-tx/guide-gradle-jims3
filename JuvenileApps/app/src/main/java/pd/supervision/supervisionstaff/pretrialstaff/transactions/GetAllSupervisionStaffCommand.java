/*
 * Created on Feb 20, 2006
 *
 */
package pd.supervision.supervisionstaff.pretrialstaff.transactions;

import java.util.Iterator;

import pd.supervision.supervisionstaff.pretrialstaff.SupervisionStaff;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author dgibler
 *
 */
public class GetAllSupervisionStaffCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event)
	{
		Iterator iter = SupervisionStaff.findAll(event);
		SupervisionStaff supervisionStaff = null;
		SupervisionStaffResponseEvent re = null;
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (iter != null)
		{
			while (iter.hasNext())
			{
				supervisionStaff = (SupervisionStaff) iter.next();
				re = supervisionStaff.getResponseEvent();
				dispatch.postEvent(re);
			}
		}
	}



	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}

}

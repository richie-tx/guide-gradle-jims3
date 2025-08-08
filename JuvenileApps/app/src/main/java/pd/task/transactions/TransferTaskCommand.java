/*
 * Created on Mar 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.task.transactions;

import pd.task.Task;
import messaging.task.TransferTaskEvent;
import mojo.km.context.ICommand;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;
import mojo.naming.IdentityAddressConstants;

/**
 * @author Jim Fisher
 *
 */
public class TransferTaskCommand implements ICommand
{

	public void execute(IEvent anEvent) throws Exception
	{
		TransferTaskEvent event = (TransferTaskEvent) anEvent;
		Task task = Task.find(event.getTaskId());

		IdentityAddress address =
			IdentityAddress.identityFactory(event.getOwnerId(), IdentityAddressConstants.IDENTITY_INDIVIDUAL);
		task.setOwnerIdentity(address);
		//set task status back to Open
		//task.setStatusId(event.getStatusId());
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
		// TODO Auto-generated method stub

	}

}

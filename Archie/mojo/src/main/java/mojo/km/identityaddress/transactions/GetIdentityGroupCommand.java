/*
 * Created on Mar 31, 2006
 *
 */
package mojo.km.identityaddress.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.identityaddress.GetIdentityGroupEvent;
import messaging.identityaddress.reply.IdentityGroupResponse;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;

/**
 * @author Jim Fisher
 *
 */
public class GetIdentityGroupCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		GetIdentityGroupEvent event = (GetIdentityGroupEvent) anEvent;

		IdentityAddress groupAddress = IdentityAddress.findByValue(event.getGroupName());

		if (groupAddress != null)
		{
			Collection members = groupAddress.getMembers();

			Iterator i = members.iterator();

			IdentityGroupResponse response = new IdentityGroupResponse();

			response.setGroupName(groupAddress.getValue());

			while (i.hasNext())
			{
				IdentityAddress member = (IdentityAddress) i.next();
				response.addMember(member.getValue());
			}
			
			EventManager.getSharedInstance(EventManager.REPLY).postEvent(response);
		}

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

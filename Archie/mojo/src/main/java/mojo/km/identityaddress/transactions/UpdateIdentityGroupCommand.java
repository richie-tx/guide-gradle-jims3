/*
 * Created on Mar 27, 2006
 *
 */
package mojo.km.identityaddress.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.identityaddress.UpdateIdentityGroupEvent;
import mojo.km.context.ICommand;
import mojo.km.identityaddress.IdentityAddress;
import mojo.km.messaging.IEvent;
import mojo.naming.IdentityAddressConstants;

/**
 * @author Jim Fisher 
 *
 * This command updates an IdentityGroup instance.
 * Group members will be associated if they do not exist in the group.
 * Group members will be disassociated if they are not passed in on the event.
 * Identities (groups and members) will always be created (identityFactory) if
 * they do not already exist as distinct IdentityAddress instances.
 */
public class UpdateIdentityGroupCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent anEvent) throws Exception
	{
		UpdateIdentityGroupEvent event = (UpdateIdentityGroupEvent) anEvent;

		IdentityAddress groupAddress =
			IdentityAddress.identityFactory(event.getGroupName(), IdentityAddressConstants.IDENTITY_GROUP);

		Map existingMembers = this.createMemberMap(groupAddress.getMembers());

		// Adds new members sent on UpdateIdentityGroupEvent.members
		this.addMembers(event.getMembers(), existingMembers, groupAddress);

		// Deletes members not in the existingMembers collection
		this.deleteMembers(event.getMembers(), existingMembers);
	}

	/**
	 * Adds new members sent on UpdateIdentityGroupEvent.members
	 * @param updateMembers
	 * @param existingMembers
	 * @param groupAddress
	 */
	private void addMembers(Collection updateMembers, Map existingMembers, IdentityAddress groupAddress)
	{
		Iterator i = updateMembers.iterator();
		while (i.hasNext())
		{
			String memberValue = (String) i.next();
			if (existingMembers.keySet().contains(memberValue) == false)
			{
				IdentityAddress identity =
					IdentityAddress.identityFactory(memberValue, IdentityAddressConstants.IDENTITY_INDIVIDUAL);
				groupAddress.insertMember(identity);
			}
		}
	}

	/**
	 * Deletes members not in the existingMembers collection
	 * @param updateMembers
	 * @param existingMembers
	 */
	private void deleteMembers(Collection updateMembers, Map existingMembers)
	{
		Iterator i = existingMembers.values().iterator();
		while (i.hasNext())
		{
			IdentityAddress identity = (IdentityAddress) i.next();
			if (updateMembers.contains(identity.getValue()) == false)
			{
				identity.delete();
			}
		}
	}

	private Map createMemberMap(Collection members)
	{
		Iterator i = members.iterator();
		Map memberMap = new HashMap();
		while (i.hasNext())
		{
			IdentityAddress identity = (IdentityAddress) i.next();
			memberMap.put(identity.getValue(), identity);
		}
		return memberMap;
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

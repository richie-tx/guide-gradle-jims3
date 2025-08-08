/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.GetJuvenileFamilyMembersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import mojo.km.util.CollectionUtil;

/**
 * @author athorat
 *
 */
public class GetJuvenileFamilyMembersCommand implements ICommand, ReadOnlyTransactional
{

	/**
	 * 
	 */
	public GetJuvenileFamilyMembersCommand()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetJuvenileFamilyMembersEvent request = (GetJuvenileFamilyMembersEvent)event; 
		Iterator iterator = FamilyMember.findAll(request);
		JuvenileFamilyHelper.processFamilyMemberReplies(CollectionUtil.iteratorToList(iterator));
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

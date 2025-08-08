/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.List;
import messaging.family.GetFamilyMembersEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import mojo.km.util.CollectionUtil;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 */
public class GetFamilyMembersCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) 
	{
		GetFamilyMembersEvent request = (GetFamilyMembersEvent)event;
		
		List <FamilyMember> aList = CollectionUtil.iteratorToList(FamilyMember.findAll(request));
		
		if (!request.isLoadAssocatiedJuveniles() ) {
			JuvenileFamilyHelper.processFamilyMemberReplies(aList);
		} else {
			JuvenileFamilyHelper.processFamilyMemberWithAssocJuvenilesReplies(aList);
		}
		
		aList = null;
		request = null;
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

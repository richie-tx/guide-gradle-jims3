/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyMemberEmployment;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberEmploymentInfoCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberEmploymentInfoEvent requestEvent = (GetFamilyMemberEmploymentInfoEvent)event;
		 
		Iterator iterator = FamilyMemberEmployment.findAll("familyMemberId", requestEvent.getMemberNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			FamilyMemberEmployment employment = (FamilyMemberEmployment) iterator.next();
			if(employment != null)
			{
				FamilyMemberEmploymentInfoResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberEmploymentInfoResponseEvent(employment);
				dispatch.postEvent(reply);
			}
				
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

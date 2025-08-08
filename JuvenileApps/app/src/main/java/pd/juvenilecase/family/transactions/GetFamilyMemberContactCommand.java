/*
 * Created on Sep 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyMemberContactEvent;
import messaging.juvenilecase.reply.FamilyMemberEmailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberContactCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberContactEvent requestEvent = (GetFamilyMemberContactEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
//		if (requestEvent.getMemberContactId() != null)
//		{
//			FamilyMemberPhone phone = FamilyMemberPhone.find(requestEvent.getMemberContactId());
//			FamilyMemberPhoneResponseEvent phoneReply = JuvenileFamilyHelper.getFamilyPhoneResponseEvent(phone);
//			dispatch.postEvent(phoneReply);
//		}
//		else
		if (requestEvent.getMemberId() != null)
		{
			Iterator iter = FamilyMemberPhone.findAll("familyMemberId",requestEvent.getMemberId());
			while(iter.hasNext())
			{
				FamilyMemberPhone phone = (FamilyMemberPhone)iter.next();
				FamilyMemberPhoneResponseEvent phoneReply = JuvenileFamilyHelper.getFamilyPhoneResponseEvent(phone);
				dispatch.postEvent(phoneReply);	
			}
			iter = FamilyMemberEmail.findAll("familyMemberId",requestEvent.getMemberId());
			while(iter.hasNext())
			{
				FamilyMemberEmail email = (FamilyMemberEmail)iter.next();
				FamilyMemberEmailResponseEvent emailReply = JuvenileFamilyHelper.getFamilyEmailResponseEvent(email);
				dispatch.postEvent(emailReply);	
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

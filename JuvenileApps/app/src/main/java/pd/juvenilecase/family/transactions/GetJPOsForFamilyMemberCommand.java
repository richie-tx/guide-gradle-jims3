/*
 * Created on May 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;

import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.family.MemberNotifyJPO;
import messaging.family.GetJPOsForFamilyMemberEvent;
import messaging.juvenilecase.reply.JPOsForFamilyMemberResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJPOsForFamilyMemberCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		if(event==null || !(event instanceof GetJPOsForFamilyMemberEvent))
			return;
		GetJPOsForFamilyMemberEvent jpoFamilyMemberEvt = (GetJPOsForFamilyMemberEvent) event;
		Collection memberNotifyList=null;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(jpoFamilyMemberEvt.getFamilyMemberId()==null || jpoFamilyMemberEvt.getFamilyMemberId().equalsIgnoreCase(""))
		{	
			return;
		}
		Iterator memberNotifyIter=MemberNotifyJPO.findAll("memberId",jpoFamilyMemberEvt.getFamilyMemberId());
		if(memberNotifyIter!=null){
			while (memberNotifyIter.hasNext())
			{
				MemberNotifyJPO memberNotifyJPOobj = (MemberNotifyJPO) memberNotifyIter.next();
				if(memberNotifyJPOobj!=null){
					JPOsForFamilyMemberResponseEvent respReply =
						JuvenileFamilyHelper.getJPOsForFamilyMemberResponseEvent(memberNotifyJPOobj);
					if(respReply!=null)
						dispatch.postEvent(respReply);
				}
				
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

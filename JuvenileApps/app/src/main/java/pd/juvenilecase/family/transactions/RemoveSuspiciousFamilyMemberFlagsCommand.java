package pd.juvenilecase.family.transactions;

import java.util.List;

import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.RemoveSuspiciousFamilyMemberFlagsEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.context.ICommand;

public class RemoveSuspiciousFamilyMemberFlagsCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		RemoveSuspiciousFamilyMemberFlagsEvent compRequestEvent = (RemoveSuspiciousFamilyMemberFlagsEvent) anEvent;
		
		List <RemoveSuspiciousFamilyMemberFlagsEvent> memberList = CollectionUtil.enumerationToList(compRequestEvent.getRequests());

		RemoveSuspiciousFamilyMemberFlagsEvent reqEvent = null;
		
		for (int i = 0; i < memberList.size(); i++) {
			reqEvent = memberList.get(i);
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, reqEvent.getFamilyMemberId());
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, reqEvent.getFamilyMemberId());
		}
		
		compRequestEvent = null;
		memberList = null;
		reqEvent = null;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}

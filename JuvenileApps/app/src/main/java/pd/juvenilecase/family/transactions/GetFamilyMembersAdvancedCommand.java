package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenilecase.family.FamilyMember;
import messaging.family.GetFamilyMembersAdvancedEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetFamilyMembersAdvancedCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetFamilyMembersAdvancedEvent request =(GetFamilyMembersAdvancedEvent) event;
	
	
	Iterator memberIter = FamilyMember.findAll( request );
	
	while(memberIter.hasNext()){
	    
	    FamilyMember member = (FamilyMember) memberIter.next();
	    
	    FamilyMemberListResponseEvent response = member.valueObject();
	    
	    dispatch.postEvent(response);
	}
	
	
    }

}

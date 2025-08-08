//Source file: C:\\views\\dev\\app\\src\\pd\\juvenileFamily\\transactions\\GetFamilyMemberTraitCommand.java

package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyMemberTraitEvent;
import messaging.juvenilecase.reply.FamilyMemberTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyMemberTrait;
import pd.juvenilecase.family.JuvenileFamilyHelper;

public class GetFamilyMemberTraitCommand implements ICommand, ReadOnlyTransactional
{

	/**
	 * @roseuid 4338584501F4
	 */
	public GetFamilyMemberTraitCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4331717E006F
	 */
	public void execute(IEvent event)
	{
		GetFamilyMemberTraitEvent requestEvent = (GetFamilyMemberTraitEvent)event;
		 
		Iterator iterator = FamilyMemberTrait.findAll("familyMemberId", requestEvent.getFamilyMemberNum());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			FamilyMemberTrait trait = (FamilyMemberTrait) iterator.next();
			if(trait != null)
			{
				FamilyMemberTraitResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberTraitResponseEvent(trait);
				dispatch.postEvent(reply);
			}
				
		}		

	}


	/**
	 * @param event
	 * @roseuid 4331717E0071
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4331717E007D
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4331717E007F
	 */
	public void update(Object anObject)
	{

	}

}

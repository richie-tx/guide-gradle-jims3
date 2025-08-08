/*
 * Created on Oct 10, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.family.AddFamilyMemberEvent;
import messaging.family.SaveFamilyMemberEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.MessageUtil;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddFamilyMemberCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		AddFamilyMemberEvent addMember = (AddFamilyMemberEvent) event;
		FamilyConstellation family = FamilyConstellation.find(addMember.getFamilyId());
		if (family != null)
		{
			Collection members = MessageUtil.compositeToCollection(addMember, SaveFamilyMemberEvent.class);
			if (members != null)
			{
				Iterator i = members.iterator();
				while (i.hasNext())
				{
					SaveFamilyMemberEvent saveRequest = (SaveFamilyMemberEvent) i.next();
					FamilyMember member = JuvenileFamilyHelper.updateFamilyMember(saveRequest);
					IHome home = new Home();
					if(member.getOID() == null)
					{
						home.bind(member);
					}
					FamilyConstellationMember contellationMember =
						JuvenileFamilyHelper.createCostellationMember(member);
					
					contellationMember.setConfirmedDate(addMember.getConfirmDate());
					contellationMember.setGuardian(addMember.isGuardian());
					contellationMember.setInHomeStatus(addMember.isInHomeStauts());
					contellationMember.setPrimaryCareGiver(addMember.isPrimaryCareGiver());//Er 11063 changes.
					contellationMember.setInvolvementLevelId(addMember.getInvolvmentLevelId());
					contellationMember.setRelationshipToJuvenileId(addMember.getRelationToJuvenileId());
					contellationMember.setParentalRightsTerminated(addMember.isParentalRightsTerminated());
					
					family.insertFamilyConstellationMembers(contellationMember);

					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
//					FamilyMemberDetailResponseEvent reply =
//						JuvenileFamilyHelper.getFamilyMemberDetailResponseEvent(contellationMember, member);
//					dispatch.postEvent(reply);

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

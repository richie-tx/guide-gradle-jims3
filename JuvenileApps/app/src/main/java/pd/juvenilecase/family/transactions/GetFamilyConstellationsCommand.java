/*
 * Project: JIMS2
 * Class:   pd.juvenileFamily.transactions.GetFamilyConstellationsCommand
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import messaging.family.GetFamilyConstellationsEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDJuvenileFamilyConstants;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * Class GetFamilyConstellationsCommand.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetFamilyConstellationsCommand
	implements mojo.km.context.ICommand, mojo.km.transaction.ReadOnlyTransactional
{

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 432998C800AB
	 */
	public GetFamilyConstellationsCommand()
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.GetFamilyConstellationsCommand

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event @roseuid 432997A90244
	 */
	public void execute(IEvent event)
	{
		GetFamilyConstellationsEvent reqEvent = (GetFamilyConstellationsEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator famIter = FamilyConstellation.findAll(reqEvent);
		while (famIter.hasNext())
		{
			FamilyConstellation family = (FamilyConstellation) famIter.next();
			// create family repsonse
			FamilyConstellationListResponseEvent famReply = getFamilyConstellationListResponse(family);
			// get constellationMembers
			Collection members = family.getFamilyConstellationMembers();

		
			StringBuffer guardiansNames = new StringBuffer();
			if (members != null && members.isEmpty() == false)
			{
				// if member may be part of earlier constellation add it to  memberMap so
				//we don't need to fetch him ahain
				Map memberMap = new HashMap();
				Iterator memIter = members.iterator();
				while (memIter.hasNext())
				{
					FamilyConstellationMember constellationMember = (FamilyConstellationMember) memIter.next();
					FamilyMember famMember = null;
					Object obj = memberMap.get(constellationMember.getTheFamilyMemberId());
					if (obj != null)
					{
						famMember = (FamilyMember) obj;
					}
					else
					{
						famMember = constellationMember.getTheFamilyMember();
						// add member to memberMap for optimization
						memberMap.put((String) famMember.getFamilyMemberId(), famMember);
					}
					// construts guardiansNames
					if (constellationMember.isGuardian())
					{
						String guardiansName =
							getFormattedGaurdianName(famMember.getFirstName(), famMember.getLastName());
						if (guardiansName.length() > 0)
						{
							if (guardiansNames.length() > 0)
								guardiansNames.append(" / ");
							guardiansNames.append(guardiansName);
						}
					}

					// send the list of active
					if (family.isActive())
					{

						FamilyConstellationMemberListResponseEvent memReply =
							JuvenileFamilyHelper.getFamilyMemberListResponseEvent(constellationMember, famMember);
						dispatch.postEvent(memReply);
					}
				}
			}
			//set guardiansNames to famReply
			famReply.setGuardiansNames(guardiansNames.toString());
			// send away
			dispatch.postEvent(famReply);

		}

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.execute

	/**
	 *  
	 * @param event @roseuid 432997A90246
	 */
	public void onRegister(IEvent event)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.onRegister

	/**
	 *  
	 * @param event @roseuid 432997A90252
	 */
	public void onUnregister(IEvent event)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.onUnregister

	/**
	 *  
	 * @param anObject @roseuid 432997A90254
	 */
	public void update(Object anObject)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.update

	/**
	 *  
	 * @param firstName The first name.
	 * @param lastName The last name.
	 * @return  The formatted gaurdian name.
	 */
	private String getFormattedGaurdianName(String firstName, String lastName)
	{
		String name = new String();
		
		if (lastName != null && lastName.length() > 0)
			name = lastName;
		if (firstName != null && firstName.length() > 0)
					name = name + ", "+ firstName;
		return name;
	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.getFormattedGaurdianName


	/**
	 *  
	 * @param family The family.
	 * @return  The family constellation list response.
	 */
	private FamilyConstellationListResponseEvent getFamilyConstellationListResponse(FamilyConstellation family)
	{
		FamilyConstellationListResponseEvent reply = new FamilyConstellationListResponseEvent();
		reply.setTopic(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
		reply.setActive(family.isActive());
		reply.setEntryDate(family.getEntryDate());
		reply.setFamilyNum(family.getFamilyConstellationId());
		return reply;
	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationsCommand.getFamilyConstellationListResponse

} // end GetFamilyConstellationsCommand

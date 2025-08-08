/*
 * Project: JIMS2
 * Class:   pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand
 * Version: 1.0.0
 *
 * Date:    2005-09-19
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.juvenilecase.reply.FamilyConstellationTraitsResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyTrait;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * Class GetFamilyConstellationDetailsCommand.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetFamilyConstellationDetailsCommand implements mojo.km.context.ICommand, ReadOnlyTransactional
{

	// ------------------------------------------------------------------------
	// --- constructor                                                      ---
	// ------------------------------------------------------------------------

	/**
	 * @roseuid 432999D701A5
	 */
	public GetFamilyConstellationDetailsCommand()
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand.GetFamilyConstellationDetailsCommand

	// ------------------------------------------------------------------------
	// --- methods                                                          ---
	// ------------------------------------------------------------------------

	/**
	 *  
	 * @param event @roseuid 432997A90292
	 */
	public void execute(IEvent event)
	{
		GetFamilyConstellationDetailsEvent requestEvent = (GetFamilyConstellationDetailsEvent) event;

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if (requestEvent.getConstellationNum() != null)
		{
			JuvenileFamilyHelper.processConstellationDetailResponse(requestEvent.getConstellationNum(),dispatch);
		}

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand.execute

	/**
	 *  
	 * @param event @roseuid 432997A90294
	 */
	public void onRegister(IEvent event)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand.onRegister

	/**
	 *  
	 * @param event @roseuid 432997A90296
	 */
	public void onUnregister(IEvent event)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand.onUnregister

	/**
	 *  
	 * @param anObject @roseuid 432997A902A0
	 */
	public void update(Object anObject)
	{

	} //end of pd.juvenileFamily.transactions.GetFamilyConstellationDetailsCommand.update

} // end GetFamilyConstellationDetailsCommand

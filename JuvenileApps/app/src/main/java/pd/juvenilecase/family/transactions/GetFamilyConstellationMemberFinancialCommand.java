/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.family.GetFamilyConstellationMemberFinancialEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMemberFinancial;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyConstellationMemberFinancialCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyConstellationMemberFinancialEvent request = (GetFamilyConstellationMemberFinancialEvent)event;
		int constellationMemberId = request.getConstellationMemberId();
		int memberId =  request.getMemberId();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		if(constellationMemberId > 0)
		{
			Iterator financialsIter = FamilyMemberFinancial.findAll("familyConstellationMemberId",String.valueOf(constellationMemberId));
			while(financialsIter.hasNext())
			{
				FamilyMemberFinancial financial = (FamilyMemberFinancial)financialsIter.next();
				FamilyConstellationMemberFinancialResponseEvent reply = JuvenileFamilyHelper.getFamilyConstellationMemberFinancialResponseEvent(financial);
				dispatch.postEvent(reply);
			}
		}
		else if(memberId > 0)
		{
			Iterator memberIter =FamilyConstellationMember.findAll("theFamilyMemberId",String.valueOf(memberId));
			while(memberIter.hasNext())
			{
				FamilyConstellationMember constellationMember = (FamilyConstellationMember)memberIter.next();
				//constellationMember.getf
				Collection financials = constellationMember.getConstellationMemberFinancials();
				if(financials != null && financials.size() > 0)
				{
					Iterator financialsIter = financials.iterator();
					while(financialsIter.hasNext())
					{
						FamilyMemberFinancial financial = (FamilyMemberFinancial)financialsIter.next();
						FamilyConstellationMemberFinancialResponseEvent reply = JuvenileFamilyHelper.getFamilyConstellationMemberFinancialResponseEvent(financial);
						dispatch.postEvent(reply);

					}
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

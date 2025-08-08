/*
 * Created on Sep 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyMemberBenefitsEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyMemberBenefit;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberBenefitsCommand implements ICommand, ReadOnlyTransactional
{


	/**
	 * 
	 */
	public GetFamilyMemberBenefitsCommand()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberBenefitsEvent requestEvent = (GetFamilyMemberBenefitsEvent)event;
		 
		Iterator iterator = FamilyMemberBenefit.findAll("familyMemberId", requestEvent.getMemberId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			FamilyMemberBenefit benefit = (FamilyMemberBenefit) iterator.next();
			if(benefit != null)
			{
				FamilyMemberBenefitResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberBenefitResponseEvent(benefit);
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

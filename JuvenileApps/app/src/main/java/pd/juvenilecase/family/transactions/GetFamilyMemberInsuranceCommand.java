/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.family.GetFamilyMemberInsuranceEvent;
import messaging.juvenilecase.reply.FamilyMemberInsuranceResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import pd.juvenilecase.family.FamilyMemberInsurance;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberInsuranceCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberInsuranceEvent request = (GetFamilyMemberInsuranceEvent)event;
		Iterator iterator = FamilyMemberInsurance.findAll("familyMemberId", request.getFamilyMemberId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while (iterator.hasNext())
		{
			FamilyMemberInsurance insurance = (FamilyMemberInsurance) iterator.next();
			if(insurance != null)
			{
				FamilyMemberInsuranceResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberInsuranceResponseEvent(insurance);
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

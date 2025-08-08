/*
 * Created on Oct 19, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.SaveFamilyMemberFinancialEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberFinancialCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SaveFamilyMemberFinancialEvent saveEvent = (SaveFamilyMemberFinancialEvent)event;
		int constellationMemberId = saveEvent.getConstellationMemberId();
		if(constellationMemberId > 0)
		{
			FamilyConstellationMember constellationMember = FamilyConstellationMember.find(String.valueOf(constellationMemberId));
			if(constellationMember!= null)
			{
				JuvenileFamilyHelper.saveConstellationMemberFinancial(saveEvent,constellationMember); 
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

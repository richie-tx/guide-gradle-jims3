/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import messaging.family.SaveFamilyMemberContactEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberContactCommand implements ICommand
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SaveFamilyMemberContactEvent saveEvent = (SaveFamilyMemberContactEvent)event;
		String memberNum = "";//saveEvent.getMemberNum();
		if(saveEvent.isPhone()){
			//JuvenileFamilyHelper.createMemberPhone(saveEvent.getPhoneNum(),saveEvent.getExtentionNum(), saveEvent.getPhoneTypeId(), memberNum);
			JuvenileFamilyHelper.createMemberPhone(saveEvent, memberNum);
		}
		else
		{
			JuvenileFamilyHelper.createMemberEmail(saveEvent.getEmailAddress(),saveEvent,saveEvent.getEmailTypeId(), memberNum);
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

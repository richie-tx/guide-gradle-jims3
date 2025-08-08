/*
 * Created on Oct 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import pd.juvenilecase.family.FamilyMemberAddressView;
import messaging.family.SearchMemberAddressEvent;
import messaging.juvenilecase.reply.*;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchMemberAddressCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		SearchMemberAddressEvent requestEvent = (SearchMemberAddressEvent)event;
		String constelltionId = requestEvent.getConstelltionId();
		Iterator iter = FamilyMemberAddressView.findAll("constellationId",constelltionId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		while(iter.hasNext())
		{
			FamilyMemberAddressView memAddressView =(FamilyMemberAddressView)iter.next();
			FamilyMemberAddressViewResponseEvent reply = getMemAddressViewResponseEvent(memAddressView);
			dispatch.postEvent(reply);
		}

	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
		// TODO Auto-generated method stub

	}

	private FamilyMemberAddressViewResponseEvent getMemAddressViewResponseEvent(FamilyMemberAddressView memAddressView)
	{
		FamilyMemberAddressViewResponseEvent reply = new FamilyMemberAddressViewResponseEvent();
		
		reply.setMemberFirstName(memAddressView.getMemberFirstName());
		reply.setMemberMiddleName("");
		reply.setMemberLastName(memAddressView.getMemberLastName());
		reply.setMemberId(memAddressView.getMemberId());
		reply.setStreetNum(memAddressView.getStreetNum());
		reply.setStreetName(memAddressView.getStreetName());
		reply.setStreetTypeId(memAddressView.getStreetTypeId());
		reply.setAptNum(memAddressView.getAptNum());
		reply.setCity(memAddressView.getCity());
		reply.setCountyId(memAddressView.getCountyId());
		reply.setStateId(memAddressView.getStateId());
		reply.setCountryId(memAddressView.getCountryId());
		reply.setAddressTypeId(memAddressView.getAddressTypeId());
		reply.setZipCode(memAddressView.getZipCode());
		reply.setAdditionalZipCode(memAddressView.getAdditionalZipCode());
		reply.setAddressId(memAddressView.getAddressId());
		reply.setValidated(memAddressView.getValidated());
		return reply;

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

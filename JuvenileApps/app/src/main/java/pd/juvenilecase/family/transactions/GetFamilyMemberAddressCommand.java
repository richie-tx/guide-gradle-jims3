/*
 * Created on Sep 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import naming.PDJuvenileFamilyConstants;
import pd.address.Address;
import pd.address.PDAddressHelper;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberAddressesAddress;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberAddressCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberAddressEvent requestEvent = (GetFamilyMemberAddressEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

		if (requestEvent.getMemberNum() != null && requestEvent.getMemberNum().length() > 0)
		{
			Iterator iterator = FamilyMemberAddressesAddress.findAll("parentId", requestEvent.getMemberNum());
			while (iterator.hasNext())
			{
				FamilyMemberAddressesAddress address = (FamilyMemberAddressesAddress) iterator.next();
				Address theAddress = address.getChild();
				if (theAddress != null)
				{
					AddressResponseEvent addressReply = PDAddressHelper.getAddressResponseEvent(theAddress);
					addressReply.setAddressId(theAddress.getAddressId());
					addressReply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
					dispatch.postEvent(addressReply);
				}

			}

		}
		// this is Inefficient way to get all members and address. however the requirement is not clear and may
		// change .... this is temporary solution. 
		else
		if (requestEvent.getConstellationId() != null && requestEvent.getConstellationId().length() > 0)
			{
				Iterator iterator =
					FamilyConstellationMember.findAll("familyConstellationId", requestEvent.getConstellationId());
				while (iterator.hasNext())
				{
					FamilyConstellationMember constelltionMember = (FamilyConstellationMember) iterator.next();
					FamilyMember famMember = constelltionMember.getTheFamilyMember();

					FamilyMemberListResponseEvent reply = new FamilyMemberListResponseEvent();
					String memberOID = famMember.getOID().toString();
					reply.setMemberNum(memberOID);
					reply.setFirstName(famMember.getFirstName());
					reply.setLastName(famMember.getLastName());
					reply.setMiddleName(famMember.getMiddleName());
					reply.setDeceased(famMember.isDeceased());
					reply.setSSN(famMember.getSSN());
					reply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);
					dispatch.postEvent(reply);
					processMemberAddress(famMember, dispatch);

				}

			}

	}

	/**
	 * @param memberOID
	 * @param dispatch
	 */
	private void processMemberAddress(FamilyMember famMember, IDispatch dispatch)
	{
		Collection addresses = famMember.getAddresses();
		if (addresses != null)
		{
			addresses.iterator();
			Iterator iterator = addresses.iterator();
			while (iterator.hasNext())
			{
				Address address = (Address)iterator.next();
				AddressResponseEvent addressReply = PDAddressHelper.getAddressResponseEvent(address);
				addressReply.setAddressId(address.getAddressId());
				addressReply.setTopic(famMember.getOID().toString() + "_" +PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
				dispatch.postEvent(addressReply);
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

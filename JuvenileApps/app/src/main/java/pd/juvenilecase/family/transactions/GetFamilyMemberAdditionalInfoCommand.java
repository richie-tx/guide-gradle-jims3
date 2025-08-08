/*
 * Created on Oct 24, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package pd.juvenilecase.family.transactions;

import java.util.Iterator;

import messaging.address.reply.AddressResponseEvent;
import messaging.family.GetFamilyMemberAdditionalInfoEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberInsuranceResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberTraitResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.transaction.ReadOnlyTransactional;
import naming.PDJuvenileFamilyConstants;
import pd.address.Address;
import pd.juvenilecase.family.FamilyMemberAddressesAddress;
import pd.juvenilecase.family.FamilyMemberBenefit;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.FamilyMemberEmployment;
import pd.juvenilecase.family.FamilyMemberInsurance;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.FamilyMemberTrait;
import pd.juvenilecase.family.JuvenileFamilyHelper;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetFamilyMemberAdditionalInfoCommand implements ICommand, ReadOnlyTransactional
{

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{
		GetFamilyMemberAdditionalInfoEvent request = (GetFamilyMemberAdditionalInfoEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		String memberId = request.getMemberId();
		if (memberId != null && memberId.length() > 0)
		{
			// address
			Iterator iterator = FamilyMemberAddressesAddress.findAll("parentId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberAddressesAddress address = (FamilyMemberAddressesAddress) iterator.next();
				Address theAddress = address.getChild();
				if (theAddress != null)
				{
					AddressResponseEvent reply = getAddressResponseEvent(theAddress);
					dispatch.postEvent(reply);
				}
			}
			// phone
			iterator = FamilyMemberPhone.findAll("familyMemberId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberPhone phone = (FamilyMemberPhone) iterator.next();
				FamilyMemberPhoneResponseEvent phoneReply = JuvenileFamilyHelper.getFamilyPhoneResponseEvent(phone);
				dispatch.postEvent(phoneReply);
			}
			//email
			iterator = FamilyMemberEmail.findAll("familyMemberId",memberId);
			while(iterator.hasNext())
			{
				FamilyMemberEmail email = (FamilyMemberEmail)iterator.next();
				FamilyMemberEmailResponseEvent emailReply = JuvenileFamilyHelper.getFamilyEmailResponseEvent(email);
				dispatch.postEvent(emailReply);	
			}

			// employments
			iterator = FamilyMemberEmployment.findAll("familyMemberId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberEmployment employment = (FamilyMemberEmployment) iterator.next();
				if (employment != null)
				{
					FamilyMemberEmploymentInfoResponseEvent reply =
						JuvenileFamilyHelper.getFamilyMemberEmploymentInfoResponseEvent(employment);
					dispatch.postEvent(reply);
				}

			}
			// benefits
			iterator = FamilyMemberBenefit.findAll("familyMemberId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberBenefit benefit = (FamilyMemberBenefit) iterator.next();
				if (benefit != null)
				{
					FamilyMemberBenefitResponseEvent reply =
						JuvenileFamilyHelper.getFamilyMemberBenefitResponseEvent(benefit);
					dispatch.postEvent(reply);
				}

			}
			// triats
			iterator = FamilyMemberTrait.findAll("familyMemberId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberTrait trait = (FamilyMemberTrait) iterator.next();
				if (trait != null)
				{
					FamilyMemberTraitResponseEvent reply =
						JuvenileFamilyHelper.getFamilyMemberTraitResponseEvent(trait);
					dispatch.postEvent(reply);
				}

			}
			// insurance
			iterator = FamilyMemberInsurance.findAll("familyMemberId", memberId);
			while (iterator.hasNext())
			{
				FamilyMemberInsurance insurance = (FamilyMemberInsurance) iterator.next();
				if (insurance != null)
				{
					FamilyMemberInsuranceResponseEvent reply =
						JuvenileFamilyHelper.getFamilyMemberInsuranceResponseEvent(insurance);
					dispatch.postEvent(reply);
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
	/**
	 * @param address
	 */
	private AddressResponseEvent getAddressResponseEvent(Address theAddress)
	{
		AddressResponseEvent reply = new AddressResponseEvent();

		reply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
		reply.setStreetNum(theAddress.getStreetNum());
		reply.setStreetName(theAddress.getStreetName());
		reply.setStreetTypeId(theAddress.getStreetTypeId());
		reply.setAptNum(theAddress.getAptNum());
		reply.setCity(theAddress.getCity());
		reply.setCountyId(theAddress.getCountyId());
		reply.setStateId(theAddress.getStateId());
		reply.setCountryId(theAddress.getCountryId());
		reply.setAddressTypeId(theAddress.getAddressTypeId());
		reply.setZipCode(theAddress.getZipCode());
		reply.setAddressId(theAddress.getAddressId());
		reply.setValidated(theAddress.getValidated());
		return reply;

	}
}

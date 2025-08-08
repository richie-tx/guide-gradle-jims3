/*
 * Created on Oct 22, 2018 by NM
 *
 */
package pd.juvenilecase.family.transactions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import messaging.family.UpdateReferralFamilyMemberEvent;
import mojo.km.context.ICommand;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.utilities.DateUtil;
import pd.address.Address;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import ui.common.PhoneNumber;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.security.SecurityUIHelper;

/**
 * @author 
 *
 */
public class UpdateReferralFamilyMemberCommand implements ICommand
{

	/**
	 * 
	 */
	public UpdateReferralFamilyMemberCommand()
	{
		super();
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#execute(mojo.km.messaging.IEvent)
	 */
	public void execute(IEvent event) throws Exception
	{ 
	    UpdateReferralFamilyMemberEvent saveRequest = (UpdateReferralFamilyMemberEvent) event;
	    JuvenileReferralMemberDetailsBean memberBean = saveRequest.getSelectedMemberBean();
	    List<JuvenileReferralMemberDetailsBean> memberDetailsBeanList = saveRequest.getMemberDetailsBeanList();
	    FamilyMember member = FamilyMember.find(memberBean.getMemberNum());
	    //<FamilyMember> familyMembers = FamilyMember.findAll(ssnEvent);
	    ArrayList<String> suspMemberIds = new ArrayList<String>();
	    Iterator<JuvenileReferralMemberDetailsBean> familyMemberList = memberDetailsBeanList.iterator();
	    
	    // check if member exists
	    if ((memberBean.getFirstName() != null && memberBean.getFirstName().trim().length() > 0) || (memberBean.getMiddleName() != null && memberBean.getMiddleName().trim().length() > 0) || (memberBean.getLastName() != null && memberBean.getLastName().trim().length() > 0))
	    {
		if (memberBean.getSSN() != null && !memberBean.getSSN().getSSN().equals("") && !memberBean.getSSN().getSSN().equals("666666666") && !memberBean.getSSN().getSSN().equals("777777777") && !memberBean.getSSN().equals("888888888") && !memberBean.getSSN().getSSN().equals("999999999"))
		{
		    //check if any other family member has the same ssn
		    Iterator<FamilyMember> familyMembersSSN = JuvenileCaseHelper.checkFamilyMemberSSN(memberBean.getSSN().getSSN());
		    while (familyMemberList.hasNext())
		    {
			FamilyMember memberSSN = (FamilyMember) familyMembersSSN.next();
			//check if they have the same name
			if (memberSSN!=null && memberSSN.getFirstName()!=null &&!memberSSN.getFirstName().equalsIgnoreCase(memberBean.getFirstName()) &&memberSSN.getLastName()!=null && !memberSSN.getLastName().equalsIgnoreCase(memberBean.getLastName()))
			{
			    suspMemberIds.add(memberSSN.getFamilyMemberId());
			}
		    }
		}
	    }
	    Address address = null;
	    PhoneNumber phoneNumber = null;

	    if (memberBean.getIncarceratedOrDeceased()==null || !memberBean.getIncarceratedOrDeceased().equalsIgnoreCase("Y"))
	    {
		address = new Address();
		address.setStreetNum(memberBean.getMemberAddress().getStreetNum());
		address.setStreetNumSuffixId(memberBean.getMemberAddress().getStreetNumSuffixId());
		address.setStreetName(memberBean.getMemberAddress().getStreetName());
		address.setStreetTypeId(memberBean.getMemberAddress().getStreetTypeId());
		address.setAptNum(memberBean.getMemberAddress().getAptNum());
		address.setCity(memberBean.getMemberAddress().getCity());
		address.setStateId(memberBean.getMemberAddress().getStateId());
		address.setZipCode(memberBean.getMemberAddress().getZipCode());
		address.setAdditionalZipCode(memberBean.getMemberAddress().getAdditionalZipCode());
		address.setAddressTypeId(memberBean.getMemberAddress().getAddressTypeId());
		address.setCountyId(memberBean.getMemberAddress().getCountyId());

		address.setCreateUserID(SecurityUIHelper.getLogonId());
		address.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		address.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
		address.setValidated("N");
		phoneNumber = memberBean.getContactPhoneNumber();
	    }
	    member = JuvenileFamilyHelper.getFamilyMember(memberBean, address, phoneNumber);
//while reaching here there is an inssert into JCFAMMEMBER
	    //suspicious
	    if (suspMemberIds != null && !suspMemberIds.isEmpty())
	    {
		Iterator<String> iter = suspMemberIds.iterator();
		while (iter.hasNext())
		{
		    String memberId = (String) iter.next();
		    JuvenileCaseHelper.markMembersSuspicious(memberId, member.getFamilyMemberId());
		}
	    }
		
		IHome home = new Home();
		home.bind(member);
		saveRequest = null;
		home = null;
		member = null;
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onRegister(mojo.km.messaging.IEvent)
	 */
	public void onRegister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#onUnregister(mojo.km.messaging.IEvent)
	 */
	public void onUnregister(IEvent event)
	{
	}

	/* (non-Javadoc)
	 * @see mojo.km.context.ICommand#update(java.lang.Object)
	 */
	public void update(Object updateObject)
	{
	}

}

package pd.juvenilecase.family.transactions;

import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberAddressesAddress;
import pd.juvenilecase.family.FamilyMemberBenefit;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.FamilyMemberEmployment;
import pd.juvenilecase.family.FamilyMemberInsurance;
import pd.juvenilecase.family.FamilyMemberMaritalStatus;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.FamilyMemberTrait;
import pd.juvenilecase.family.IndividualIncomeDetermination;
import pd.juvenilecase.family.JuvenileAgeInfo;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.MergeSuspiciousFamilyMembersEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class MergeSuspiciousFamilyMembersCommand implements ICommand
{
	public void onRegister(IEvent anEvent)
	{
	}

	public void execute(IEvent anEvent)
	{
		MergeSuspiciousFamilyMembersEvent reqEvent = (MergeSuspiciousFamilyMembersEvent) anEvent;
		
		IHome home = new Home();
		FamilyMember mergeFromFamilyMember = FamilyMember.find(reqEvent.getFromFamilyMemberId());
		FamilyMember mergeToFamilyMember = FamilyMember.find(reqEvent.getToFamilyMemberId());
		
		/* ADDRESS */
		List aList = CollectionUtil.iteratorToList(FamilyMemberAddressesAddress.findAll("parentId", mergeFromFamilyMember.getOID()));
		FamilyMemberAddressesAddress address = null;
		
		for (int i = 0; i < aList.size(); i++) {
			address = (FamilyMemberAddressesAddress) aList.get(i);
			address.setParentId(mergeToFamilyMember.getOID());
			home.bind(address);
		}
		
		address = null;
		
		/* BENEFITS */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getBenefits().iterator());
		FamilyMemberBenefit benefit = null;
		
		for (int i = 0; i < aList.size(); i++) {
			benefit = (FamilyMemberBenefit) aList.get(i);
			benefit.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(benefit);
		}
		
		benefit = null;
		
		/* E-MAIL */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getEmailAddresses().iterator());
		FamilyMemberEmail email = null;
		
		for (int i = 0; i < aList.size(); i++) {
			email = (FamilyMemberEmail) aList.get(i);
			email.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(email);
		}
		email = null;
		
		/* EMPLOYMENT */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getEmployments().iterator());
		FamilyMemberEmployment employment = null;
		
		for (int i = 0; i < aList.size(); i++) {
			employment = (FamilyMemberEmployment) aList.get(i);
			employment.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(employment);
		}
		employment = null;
		
		/* INSURANCE */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getInsurances().iterator());
		FamilyMemberInsurance insurance = null;
		
		for (int i = 0; i < aList.size(); i++) {
			insurance = (FamilyMemberInsurance) aList.get(i);
			insurance.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(insurance);
		}
		insurance = null;
		
		/* PHONE NUMBERS */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getPhoneNumbers().iterator());
		FamilyMemberPhone phone = null;
		
		for (int i = 0; i < aList.size(); i++) {
			phone = (FamilyMemberPhone) aList.get(i);
			phone.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(phone);
		}
		phone = null;
		
		/* TRAITS */
		aList = CollectionUtil.iteratorToList(mergeFromFamilyMember.getTraits().iterator());
		FamilyMemberTrait trait = null;

		for (int i = 0; i < aList.size(); i++) {
			trait = (FamilyMemberTrait) aList.get(i);
			trait.setFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(trait);
		}
		trait = null;
		
		/* MARITAL STATUS */
		Iterator iter = home.findAll("theFamilyMemberId",reqEvent.getFromFamilyMemberId(),FamilyMemberMaritalStatus.class);
		aList = CollectionUtil.iteratorToList(iter);
		FamilyMemberMaritalStatus maritalStatus = null;
		for (int i = 0; i < aList.size(); i++) {
			maritalStatus = (FamilyMemberMaritalStatus) aList.get(i);
			maritalStatus.setTheFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(maritalStatus);
		}
		maritalStatus = null;
		
		/*** Turns out that GuardianFinancialInfo and GuardianInfo are entities representing views ***/
		/* GUARDIAN FINANCIAL INFO */

		/* GUARDIAN INFO */

		/* INCOME DETERMINATION */
		iter = home.findAll("memberId", reqEvent.getFromFamilyMemberId(), IndividualIncomeDetermination.class);
		aList = CollectionUtil.iteratorToList(iter);
		IndividualIncomeDetermination income = null;
		for (int i = 0; i < aList.size(); i++) {
			income = (IndividualIncomeDetermination) aList.get(i);
			income.setMemberId(mergeToFamilyMember.getOID());
			home.bind(income);
		}
		income = null;
		
		/* JUVENILE AGE INFO */
		/* Only one JuvenileAgeInfo record is allowed per family member.  If the member being merged into already
		 * has a JuvenileAgeInfo record do not merge. Instead delete the record of the member being merged from.
		*/
		iter = home.findAll("familyMemberId", reqEvent.getToFamilyMemberId(), JuvenileAgeInfo.class);
		aList = CollectionUtil.iteratorToList(iter);
		JuvenileAgeInfo ageInfo = null;
		
		if (aList.size() > 0){
			iter = home.findAll("familyMemberId", reqEvent.getFromFamilyMemberId(), JuvenileAgeInfo.class);
			aList = CollectionUtil.iteratorToList(iter);
			
			for (int i = 0; i < aList.size(); i++) {
				ageInfo = (JuvenileAgeInfo) aList.get(i);
				ageInfo.delete();
				home.bind(ageInfo);
			}
		} else {
			iter = home.findAll("familyMemberId", reqEvent.getFromFamilyMemberId(), JuvenileAgeInfo.class);
			aList = CollectionUtil.iteratorToList(iter);
			
			for (int i = 0; i < aList.size(); i++) {
				ageInfo = (JuvenileAgeInfo) aList.get(i);
				ageInfo.setFamilyMemberId(mergeToFamilyMember.getOID());
				home.bind(ageInfo);
			}
		}
		ageInfo = null;
		
		/* FAMILY CONSTELLATION */
  		iter = home.findAll("theFamilyMemberId", reqEvent.getFromFamilyMemberId(), FamilyConstellationMember.class);
		aList = CollectionUtil.iteratorToList(iter);
		FamilyConstellationMember famConstellationMember = null;	
		
		for (int i = 0; i < aList.size(); i++) {
			famConstellationMember = (FamilyConstellationMember)aList.get(i);
			famConstellationMember.setTheFamilyMemberId(mergeToFamilyMember.getOID());
			home.bind(famConstellationMember);
		}
		famConstellationMember = null;
		
		/* DELETE SUSPICIOUS MEMBER */
		JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, mergeFromFamilyMember.getOID());
		JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, mergeFromFamilyMember.getOID());
		
		boolean isStillSuspicious = this.isSuspicious(mergeFromFamilyMember, mergeToFamilyMember);

		if (!isStillSuspicious){
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, mergeToFamilyMember.getOID());
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, mergeToFamilyMember.getOID());
		}
		
		/* DELETE "FROM" MEMBER */
		mergeFromFamilyMember.delete();
		
		/* OBJECT CLEAN-UP */
		mergeFromFamilyMember = null;
		iter = null;
		aList = null;
		reqEvent = null;
		home = null;
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
	
	/**
	 * @param mergeFromFm
	 * @param mergeToFm
	 * @return
	 */
	private boolean isSuspicious(FamilyMember mergeFromFm, FamilyMember mergeToFm){
		
		boolean isSuspicious = false;
		
		if (mergeToFm.getSSN()!= null && !mergeToFm.getSSN().equals(PDConstants.BLANK)){
			GetFamilyMembersEvent validateSSNEvent = new GetFamilyMembersEvent();
			validateSSNEvent.setMemberSsn(mergeToFm.getSSN());
			FamilyMember familyMember = null;
			
			List aList = CollectionUtil.iteratorToList(FamilyMember.findAll(validateSSNEvent));

			for (int i = 0; i < aList.size(); i++) {
				familyMember = (FamilyMember) aList.get(i);
				if (familyMember.getOID().equals(mergeToFm.getOID())
						|| familyMember.getOID().equals(mergeFromFm.getOID())){
					continue;
				} else {
					isSuspicious = true;
					break;
				}
			}
			validateSSNEvent = null;
			familyMember = null;
			aList = null;
			familyMember = null;
		}
		
		return isSuspicious;
	}
}

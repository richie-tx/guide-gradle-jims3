package pd.juvenilecase.family.transactions;

import java.util.List;

import naming.PDConstants;

import pd.juvenilecase.family.BenefitsAssessment;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberAddressesAddress;
import pd.juvenilecase.family.FamilyMemberBenefit;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.FamilyMemberEmployment;
import pd.juvenilecase.family.FamilyMemberFinancial;
import pd.juvenilecase.family.FamilyMemberInsurance;
import pd.juvenilecase.family.FamilyMemberMaritalStatus;
import pd.juvenilecase.family.FamilyMemberPhone;
import pd.juvenilecase.family.FamilyMemberTrait;
import pd.juvenilecase.family.IndividualIncomeDetermination;
import pd.juvenilecase.family.JuvenileAgeInfo;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.supervision.calendar.ServiceEvent;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.ReplaceSuspiciousFamilyMemberEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class ReplaceSuspiciousFamilyMemberCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		ReplaceSuspiciousFamilyMemberEvent reqEvent = (ReplaceSuspiciousFamilyMemberEvent) anEvent;
		
		IHome home = new Home();

		FamilyMember familyMemberToBeReplaced = FamilyMember.find(reqEvent.getFamilyMemberIdToBeReplaced());

		/* FAMILY CONSTELLATION */
		List constellationsTiedToMemberToBeReplaced = CollectionUtil.iteratorToList(home.findAll("theFamilyMemberId", reqEvent.getFamilyMemberIdToBeReplaced(), FamilyConstellationMember.class));

		FamilyConstellationMember consMemToBeReplaced = null;
		FamilyConstellationMember targetConsMem = null;
		
		List targetConstellations = CollectionUtil.iteratorToList(home.findAll("theFamilyMemberId", reqEvent.getTargetFamilyMemberId(), FamilyConstellationMember.class));
		
		for (int i = 0; i < constellationsTiedToMemberToBeReplaced.size(); i++) {
			boolean alreadyExists = false;
			consMemToBeReplaced = (FamilyConstellationMember)constellationsTiedToMemberToBeReplaced.get(i);
			for (int j = 0; j < targetConstellations.size(); j++) {
				targetConsMem = (FamilyConstellationMember)targetConstellations.get(j);
				if (consMemToBeReplaced.getFamilyConstellationId().equals(targetConsMem.getFamilyConstellationId())){
					alreadyExists = true;
					break;
				}
			}
			if (!alreadyExists){
				consMemToBeReplaced.setTheFamilyMemberId(reqEvent.getTargetFamilyMemberId());
				//Force the bind on the new constellation before deleting family member to be replaced and associated records.
				home.bind(consMemToBeReplaced);
			}
			List <FamilyMemberFinancial> financials = CollectionUtil.iteratorToList(FamilyMemberFinancial.findAll("familyConstellationMemberId", consMemToBeReplaced.getOID()));
			FamilyMemberFinancial financial = null;
			for (int j = 0; j < financials.size(); j++) {
				financial = financials.get(j);
				financial.delete();
				home.bind(financial);
			}
			BenefitsAssessment benefitsAsmt = null;
			List <BenefitsAssessment> benefitsAsmts = CollectionUtil.iteratorToList(BenefitsAssessment.findAll("guardianId", consMemToBeReplaced.getOID()));
				for (int j = 0; j < benefitsAsmts.size(); j++) {
					benefitsAsmt = benefitsAsmts.get(j);
					benefitsAsmt.delete();
					home.bind(benefitsAsmt);
				}
			financial = null;
			financials = null;
			benefitsAsmt = null;
			benefitsAsmts = null;
		}
		
		targetConstellations = null;
		constellationsTiedToMemberToBeReplaced = null;
		consMemToBeReplaced = null;
		targetConsMem = null;

		/* ADDRESS */
		List aList = CollectionUtil.iteratorToList(FamilyMemberAddressesAddress.findAll("parentId", familyMemberToBeReplaced.getOID()));
		FamilyMemberAddressesAddress address = null;
		
		for (int i = 0; i < aList.size(); i++) {
			address = (FamilyMemberAddressesAddress) aList.get(i);
			List <ServiceEvent> serviceEvents = CollectionUtil.iteratorToList(ServiceEvent.findAll("memberAddressId", address.getOID()));
			ServiceEvent serviceEvent = null;
			for (int j = 0; j < serviceEvents.size(); j++) {
				serviceEvent = serviceEvents.get(j);
				serviceEvent.delete();
				home.bind(serviceEvent);
			}
			serviceEvent = null;
			address.delete();
			home.bind(address);
		}
		
		address = null;

		/* BENEFITS */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getBenefits().iterator());
		FamilyMemberBenefit benefit = null;
		
		for (int i = 0; i < aList.size(); i++) {
			benefit = (FamilyMemberBenefit) aList.get(i);
			benefit.delete();
			home.bind(benefit);
		}
		
		benefit = null;
		
		/* E-MAIL */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getEmailAddresses().iterator());
		FamilyMemberEmail email = null;
		
		for (int i = 0; i < aList.size(); i++) {
			email = (FamilyMemberEmail) aList.get(i);
			email.delete();
			home.bind(email);
		}
		email = null;
		
		/* EMPLOYMENT */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getEmployments().iterator());
		FamilyMemberEmployment employment = null;
		
		for (int i = 0; i < aList.size(); i++) {
			employment = (FamilyMemberEmployment) aList.get(i);
			employment.delete();
			home.bind(employment);
		}
		employment = null;
		
		/* INSURANCE */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getInsurances().iterator());
		FamilyMemberInsurance insurance = null;
		
		for (int i = 0; i < aList.size(); i++) {
			insurance = (FamilyMemberInsurance) aList.get(i);
			insurance.delete();
			home.bind(insurance);
		}
		insurance = null;
		
		/* PHONE NUMBERS */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getPhoneNumbers().iterator());
		FamilyMemberPhone phone = null;
		
		for (int i = 0; i < aList.size(); i++) {
			phone = (FamilyMemberPhone) aList.get(i);
			phone.delete();
			home.bind(phone);
		}
		phone = null;
		
		/* TRAITS */
		aList = CollectionUtil.iteratorToList(familyMemberToBeReplaced.getTraits().iterator());
		FamilyMemberTrait trait = null;

		for (int i = 0; i < aList.size(); i++) {
			trait = (FamilyMemberTrait) aList.get(i);
			trait.delete();
			home.bind(trait);
		}
		trait = null;
		
		/* MARITAL STATUS */
		aList = CollectionUtil.iteratorToList(home.findAll("theFamilyMemberId",reqEvent.getFamilyMemberIdToBeReplaced(),FamilyMemberMaritalStatus.class));
		FamilyMemberMaritalStatus maritalStatus = null;
		for (int i = 0; i < aList.size(); i++) {
			maritalStatus = (FamilyMemberMaritalStatus) aList.get(i);
			maritalStatus.delete();
			home.bind(maritalStatus);
		}
		maritalStatus = null;
		
		/* INCOME DETERMINATION */
		aList = CollectionUtil.iteratorToList(home.findAll("memberId", reqEvent.getFamilyMemberIdToBeReplaced(), IndividualIncomeDetermination.class));
		IndividualIncomeDetermination income = null;
		for (int i = 0; i < aList.size(); i++) {
			income = (IndividualIncomeDetermination) aList.get(i);
			income.delete();
			home.bind(income);
		}
		income = null;
		
		/* JUVENILE AGE INFO */
		aList = CollectionUtil.iteratorToList(home.findAll("familyMemberId", reqEvent.getFamilyMemberIdToBeReplaced(), JuvenileAgeInfo.class));
		JuvenileAgeInfo ageInfo = null;
		for (int i = 0; i < aList.size(); i++) {
			ageInfo = (JuvenileAgeInfo) aList.get(i);
			ageInfo.delete();
			home.bind(ageInfo);
		}
		ageInfo = null;
		
		/* DELETE SUSPICIOUS MEMBER */
		JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, reqEvent.getFamilyMemberIdToBeReplaced());
		JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, reqEvent.getFamilyMemberIdToBeReplaced());
		
		FamilyMember targetFamilyMember = FamilyMember.find(reqEvent.getTargetFamilyMemberId());
		
		boolean isStillSuspicious = this.isSuspicious(targetFamilyMember, familyMemberToBeReplaced);
		
		if (!isStillSuspicious){
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, reqEvent.getTargetFamilyMemberId());
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, reqEvent.getTargetFamilyMemberId());
		}
		
		/* DELETE MEMBER BEING REPLACED */

		familyMemberToBeReplaced.delete();
		
		/* OBJECT CLEAN-UP */
		familyMemberToBeReplaced = null;
		aList = null;
		reqEvent = null;
		home = null;		
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
	/**
	 * @param targetFamilyMember
	 * @param familyMemberToBeReplaced
	 * @return
	 */
	private boolean isSuspicious(FamilyMember targetFamilyMember, FamilyMember familyMemberToBeReplaced){
		
		boolean isSuspicious = false;
		
		if (targetFamilyMember.getSSN()!= null && !targetFamilyMember.getSSN().equals(PDConstants.BLANK)){
			GetFamilyMembersEvent validateSSNEvent = new GetFamilyMembersEvent();
			
			validateSSNEvent.setMemberSsn(targetFamilyMember.getSSN());
			FamilyMember familyMember = null;
			
			List aList = CollectionUtil.iteratorToList(FamilyMember.findAll(validateSSNEvent));
			
			for (int i = 0; i < aList.size(); i++) {
				familyMember = (FamilyMember) aList.get(i);
				if (familyMember.getOID().equals(targetFamilyMember.getOID())
						|| familyMember.getOID().equals(familyMemberToBeReplaced.getOID())){
					continue;
				} else {
					isSuspicious = true;
					break;
				}
			}
			validateSSNEvent = null;
			familyMember = null;
			aList = null;
		}
		
		return isSuspicious;
	}
}

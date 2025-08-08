package pd.juvenilecase.family.transactions;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import naming.PDConstants;

import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyConstellationMember;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.FamilyMemberMaritalStatus;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.IFamilyMember;
import messaging.family.SaveFamilyMemberMaritalStatusEvent;
import messaging.family.SaveSuspiciousFamilyMemberEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class SaveSuspiciousFamilyMemberCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		SaveSuspiciousFamilyMemberEvent saveRequest = (SaveSuspiciousFamilyMemberEvent) anEvent;
		
		FamilyMember member = this.updateFamilyMember(saveRequest);
		IHome home = new Home();
		home.bind(member);
		
		Collection coll = 	MessageUtil.compositeToCollection(saveRequest, SaveFamilyMemberMaritalStatusEvent.class);

		if (coll != null && coll.size() > 0)
		{
			List <SaveFamilyMemberMaritalStatusEvent> maritalStatusList = CollectionUtil.iteratorToList(coll.iterator());
			SaveFamilyMemberMaritalStatusEvent maritalStatus = null;
			FamilyMemberMaritalStatus theMemberMaritalStatus = null;

			for (int i = 0; i < maritalStatusList.size(); i++) 
			{
				maritalStatus = maritalStatusList.get(i);
				theMemberMaritalStatus = new FamilyMemberMaritalStatus();
				theMemberMaritalStatus.setTheFamilyMemberId(member.getOID());
				theMemberMaritalStatus.setEntryDate(new java.util.Date());
				theMemberMaritalStatus.setMaritalStatusId(maritalStatus.getMaritalStatusId());
				theMemberMaritalStatus.setMarriageDate(maritalStatus.getMarriageDate());
				theMemberMaritalStatus.setDivorceDate(maritalStatus.getDivorceDate());
				theMemberMaritalStatus.setNoOfChildren(maritalStatus.getNoOfChildren());
				if(maritalStatus!=null && maritalStatus.equals(PDConstants.BLANK)){
					theMemberMaritalStatus.setTheRelatedFamMemId(null);
				}
				else{
					theMemberMaritalStatus.setTheRelatedFamMemId(maritalStatus.getRelatedFamMemId());
				}
				home.bind(theMemberMaritalStatus);
			}
			maritalStatusList = null;
			maritalStatus = null;
			theMemberMaritalStatus = null;
			home = null;
		}
		
		FamilyMemberDetailResponseEvent reply = JuvenileFamilyHelper.getFamilyMemberDetailResponseEvent(member);
		
		JuvenileFamilyHelper.sendMaritalStatusList(member.getOID());
		
		MessageUtil.postReply(reply);
		
		saveRequest = null;
		member = null;
		coll = null;
		reply = null;

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
	
	private FamilyMember updateFamilyMember(IFamilyMember familyMember)
	{
		FamilyMember member = FamilyMember.find(familyMember.getMemberId());;

		member.setFirstName(familyMember.getFirstName());
		member.setLastName(familyMember.getLastName());
		member.setMiddleName(familyMember.getMiddleName());
		member.setSsn(familyMember.getSsn());
		member.setDateOfBirth(familyMember.getDateOfBirth());
		member.setSexId(familyMember.getSexId());
		member.setAlienNum(familyMember.getAlienRegistrationNum());
		member.setIsUSCitizenId(familyMember.getIsUSCitizenId());
		member.setEthnicityId(familyMember.getEthnicityId());
		member.setNationalityId(familyMember.getNationalityId());
		member.setSidNum(familyMember.getSidNum());

		member.setPrimaryLanguageId(familyMember.getPrimarylanguageId());
		member.setSecondaryLanguageId(familyMember.getSecondaryLanguageId());
		boolean isDeceased = familyMember.isDeceasedInd();
		member.setDeceased(isDeceased);
		member.setCauseofDeathId(familyMember.getCauseOfDeathId());
		
		boolean isIncarcerated = familyMember.isIncarcerated();
		member.setIncarcerated(isIncarcerated);
		
		member.setComments(familyMember.getComments());

		member.setDriverLicenseNum(familyMember.getDriverLicenceNumber());
		member.setDriverLicenseStateId(familyMember.getDriverLicenceStateId());
		member.setDriverLicenseExpirationDate(familyMember.getDriverLicenceExpiryDate());
		member.setDriverLicenseClass(familyMember.getDriverLicenceClassId());
		member.setIdCardNum(familyMember.getIdCardNum());
		member.setIdCardStateId(familyMember.getIdCardStateId());
		
		if (member.isDeceased())
		{
			Iterator constellationMemIter =
				FamilyConstellationMember.findAll("theFamilyMemberId", familyMember.getMemberId());
			List <FamilyConstellationMember> constMemList = CollectionUtil.iteratorToList(constellationMemIter);
			FamilyConstellation constellation = null;
			FamilyConstellationMember constellationMember = null;
			
			for (int i = 0; i < constMemList.size(); i++) 
			{
				constellationMember = constMemList.get(i);
				if (constellationMember != null && constellationMember.isGuardian())
				{
					constellation = constellationMember.getFamilyConstellation();
					if(constellation.isActive()){
						boolean hasAnActiveGuardian=false;
						Collection constCurrentMembers=constellation.getFamilyConstellationMembers();
						List <FamilyConstellationMember> currentConstMembers = CollectionUtil.iteratorToList(constCurrentMembers.iterator());
						FamilyConstellationMember myMember = null;
						for (int j = 0; j < currentConstMembers.size(); j++) {
							myMember = currentConstMembers.get(j);
							if(myMember!=null && myMember.isGuardian()){
								if(!(myMember.getTheFamilyMemberId().equalsIgnoreCase(constellationMember.getTheFamilyMemberId()))){
									FamilyMember tempMember = FamilyMember.find(myMember.getTheFamilyMemberId());
									if(tempMember!=null && !(tempMember.isDeceased())){
										constellationMember.setGuardian(false);
										hasAnActiveGuardian=true;
										tempMember = null;
										break;
									} // end if the member is not null, not deceased, and is a guardian;
								}  // end if member is not same as current member
							} // END if member is not null
						} // ENd While loop through members
								
						constellation.setActive(hasAnActiveGuardian);
						
						constCurrentMembers = null;
						currentConstMembers = null;
						myMember = null;
					}// END IF constellation active
				}// End if member is guardian of constellation
			}// ENd while 
			
			constellationMemIter = null;
			constMemList = null;
			constellation = null;
		}
		
		boolean isStillSuspicious = this.isSuspicious(familyMember.getMemberId(), familyMember.getSsn());
		
		if (!isStillSuspicious){
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERA, familyMember.getMemberId());
			JuvenileFamilyHelper.removeSuspicousMemberFlags(JuvenileFamilyHelper.MEMBERB, familyMember.getMemberId());
		} 
		
		return member;
	}
	
	/**
	 * @param famMemberId
	 * @param ssn
	 * @return
	 */
	private boolean isSuspicious(String famMemberId, String ssn){
		
		boolean isSuspicious = false;
		
		if (ssn != null 
				&& !ssn.equals(PDConstants.BLANK)
				&& !ssn.equals(PDConstants.SSN_666666666)
				&& !ssn.equals(PDConstants.SSN_777777777)
				&& !ssn.equals(PDConstants.SSN_888888888)
				&& !ssn.equals(PDConstants.SSN_999999999)){
			GetFamilyMembersEvent validateSSNEvent = new GetFamilyMembersEvent();
			validateSSNEvent.setMemberSsn(ssn);
		
			List aList = CollectionUtil.iteratorToList(FamilyMember.findAll(validateSSNEvent));
			
			FamilyMember fm = null;
		
			for (int i = 0; i < aList.size(); i++) {
				fm = (FamilyMember) aList.get(i);
				if (fm.getOID().equals(famMemberId)){
					continue;
				} else {
					isSuspicious = true;
					break;
				}
			}
			validateSSNEvent = null;
			fm = null;
			aList = null;
		}
		
		return isSuspicious;
	}

}

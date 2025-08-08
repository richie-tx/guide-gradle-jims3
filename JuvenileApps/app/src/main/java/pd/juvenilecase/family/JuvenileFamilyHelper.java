/*
 *  Created on Sep 26, 2005
 *
  */
package pd.juvenilecase.family;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.addressValidation.AddressValidationEvent;
import messaging.family.GetFamilyConstellationGuardianFinancialEvent;
import messaging.family.GetFamilyConstellationMemberLatestFinancialEvent;
import messaging.family.IFamilyMember;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.family.SaveFamilyMemberAdditionalInfoEvent;
import messaging.family.SaveFamilyMemberAddressEvent;
import messaging.family.SaveFamilyMemberBenefitsEvent;
import messaging.family.SaveFamilyMemberContactEvent;
import messaging.family.SaveFamilyMemberEmploymentInfoEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.family.SaveFamilyMemberInsuranceEvent;
import messaging.family.SaveFamilyMemberTraitEvent;
import messaging.family.SaveFamilyTraitEvent;
import messaging.interviewinfo.to.EmploymentHistoryTO;
import messaging.interviewinfo.to.FamilyInformationTO;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyConstealltionResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationTraitsResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberBenefitResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberEmploymentInfoResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberInsuranceResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberTraitResponseEvent;
import messaging.juvenilecase.reply.JPOsForFamilyMemberResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeRequest;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.ISecurityManager;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.Features;
import naming.PDConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.ujac.util.BeanComparator;

import pd.address.Address;
import pd.codetable.PDCodeHelper;
import pd.contact.Phone;
import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileCore;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.km.util.Name;
import pd.security.PDSecurityHelper;
import ui.common.PhoneNumber;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.security.SecurityUIHelper;

/**
 * @author athorat
 *
 */
public class JuvenileFamilyHelper
{

	/**
	 * 
	 */
	public JuvenileFamilyHelper()
	{
		super();
	}
	
	
	public static JPOsForFamilyMemberResponseEvent getJPOsForFamilyMemberResponseEvent(
			MemberNotifyJPO aEntity)
		{
			if(aEntity==null)
				return null;
			
			//Bug fix 14996 starts
			String purgeFlag="";
			//Juvenile juvenile = aEntity.getJuvenile();
			JuvenileCore juvenile = aEntity.getJuvenile();
			if(juvenile!=null){
				JJSJuvenile jjsJuv =juvenile.getJjsJuvInfo();
				if(jjsJuv!=null){
					purgeFlag = jjsJuv.getPurgeFlag();
				}
			}
			if("=".equals(purgeFlag))
			return null;
			//Bug fix 14996 ends
			
			JPOsForFamilyMemberResponseEvent respEvent= new JPOsForFamilyMemberResponseEvent();
			respEvent.setConstellationId(aEntity.getConstellationId());
			//respEvent.setJuvenileFirstName(aEntity.getJuvenileFirstName());
			if (aEntity.getJuvenileId()!=null && !UIConstants.EMPTY_STRING.equals(aEntity.getJuvenileId())){
				respEvent.setJuvenileId(aEntity.getJuvenileId());
				// Profile stripping fix task 97538
				//Juvenile juv = aEntity.getJuvenile();
				JuvenileCore juv = aEntity.getJuvenile();
				//
				if (juv != null){
					respEvent.setJuvenileFirstName(juv.getFirstName());
					respEvent.setJuvenileMiddleName(juv.getMiddleName());
					respEvent.setJuvenileLastName(juv.getLastName());
				} else {
					respEvent.setJuvenileFirstName(UIConstants.EMPTY_STRING);
					respEvent.setJuvenileMiddleName(UIConstants.EMPTY_STRING);
					respEvent.setJuvenileLastName(UIConstants.EMPTY_STRING);
				}
			}
			
			//respEvent.setJuvenileLastName(aEntity.getJuvenileLastName());
			//respEvent.setJuvenileMiddleName(aEntity.getJuvenileMiddleName());
			respEvent.setMemberId(aEntity.getMemberId());
			respEvent.setOfficerEmail(aEntity.getOfficerEmail());
			respEvent.setOfficerFirstName(aEntity.getOfficerFirstName());
			respEvent.setOfficerId(aEntity.getOfficerId());
			respEvent.setOfficerLastName(aEntity.getOfficerLastName());
			respEvent.setOfficerMiddleName(aEntity.getOfficerMiddleName());
			respEvent.setOfficerUserId(aEntity.getOfficerUserId());
			respEvent.setMemberFirstName(aEntity.getMemberFirstName());
			respEvent.setMemberMiddleName(aEntity.getMemberMiddleName());
			respEvent.setMemberLastName(aEntity.getMemberLastName());
			respEvent.setMemberSSN(aEntity.getMemberSSN());
			respEvent.setRelationshipId(aEntity.getRelationshipToJuv());
			respEvent.setRelationship("");
			if(respEvent.getRelationshipId()!=null && !(respEvent.getRelationshipId().equals(""))){
				String relationship=PDCodeHelper.getCodeDescriptionByCode(PDCodeHelper.getRelationshipsToJuvenileCodes(),respEvent.getRelationshipId());
				if(relationship!=null)
					respEvent.setRelationship(relationship);
			}
			respEvent.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_NOTIFY_JPO_TOPIC);
			return respEvent;
		}

	/**
	 *  
	 * @param constellationMember The constellation member.
	 * @param famMember The fam member.
	 * @return  The family member list response event.
	 */
	public static FamilyConstellationMemberListResponseEvent getFamilyMemberListResponseEvent(
		FamilyConstellationMember constellationMember,
		FamilyMember famMember)
	{
		FamilyConstellationMemberListResponseEvent reply = new FamilyConstellationMemberListResponseEvent();
		reply.setMemberNum("" + famMember.getOID());
		reply.setFamConstellationMemberNum(constellationMember.getOID().toString());

		reply.setFirstName(famMember.getFirstName());
		reply.setLastName(famMember.getLastName());
		reply.setMiddleName(famMember.getMiddleName());
		reply.setDeceased(famMember.isDeceased());
		reply.setIncarcerated(famMember.isIncarcerated());
		reply.setGuardian(constellationMember.isGuardian());
		//User Story 39892
		if(famMember.getSSN()!=null && !famMember.getSSN().equals(""))
		{
			//check if the ssn is a repeat of a single character
			String ssn = famMember.getSSN();
			String firstChar = ssn.substring(0, 1);
			String matchingChars[] = ssn.split(firstChar+ "+");
			boolean repeatChars = (matchingChars.length == 0);
			  if (!repeatChars)//Individual has never had a social security number.
			  {
				  reply.setSSN("XXX-XX-"+famMember.getSSN().substring(5)); //added the dashes as part of Referral US 70421
			  }
			  else 
				  reply.setSSN(famMember.getSSN());
		}
		//reply.setSSN(famMember.getSSN());
		reply.setDateOfBirth(famMember.getDateOfBirth()); 
		reply.setDriverLicenseNum(famMember.getDriverLicenseNum()); 
		if(famMember.getDriverLicenseStateId() != null ){
		reply.setDriverLicenseStateId(famMember.getDriverLicenseStateId());} 
		//reply.setDriverLicenseState(famMember.getDriverLicenseState().getDescription()); 
		reply.setStateIssuedIdNum(famMember.getIdCardNum()); 
		if(famMember.getIdCardStateId() != null){
		reply.setStateIssuedIdStateId(famMember.getIdCardStateId());} 
		reply.setRelationToJuvenileId(constellationMember.getRelationshipToJuvenileId());
		reply.setRelationToJuvenile(constellationMember.getRelationshipToJuvenile().getDescription());
		reply.setGuardian(constellationMember.isGuardian());
		reply.setInvolvmentLevelId(constellationMember.getInvolvementLevelId());
		reply.setParentalRightsTerminated(constellationMember.isParentalRightsTerminated());
		reply.setDetentionHearing(constellationMember.isDetentionHearing()); 
		reply.setDetentionVisitation(constellationMember.isDetentionVisitation()); 
		reply.setPrimaryContact(constellationMember.isPrimaryContact());
		reply.setPrimaryCareGiver(constellationMember.isPrimaryCareGiver()); //ER changes 11063
		reply.setInHomeStatus(constellationMember.isInHomeStatus());
		reply.setSuspiciousMember(famMember.getSuspiciousMember());
		reply.setTopic(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
		reply.setConfirmedDate(new Date(constellationMember.getCreateTimestamp().getTime()));
		reply.setOver21(famMember.isOver21());    //added for User Story 43892
		
		reply.setPassportNum(famMember.getPsportNum()); //User Story 43116
	   	
		return reply;
	} //end of pd.juvenilecase.family.JuvenileFamilyHelper.getFamilyMemberListResponseEvent

	/**
	 * @param trait
	 * @return
	 */
	public static FamilyConstellationTraitsResponseEvent getFamilyConstellationTraitsResponseEvent(FamilyTrait trait)
	{
		FamilyConstellationTraitsResponseEvent reply = new FamilyConstellationTraitsResponseEvent();
		reply.setDynamicTypeId(trait.getDynamicId());
		reply.setId(trait.getOID().toString());
		reply.setEntryDate(trait.getEntryDate());
		reply.setStatusId(trait.getStatusId());
		reply.setLevelId(trait.getLevelId());
		reply.setComments(trait.getNotes());
		return reply;
	}

	/**
	 * @param createEvent
	 */
	public static void saveFamilyConstellation(SaveFamilyConstellationEvent saveEvent)
	{
		String juvNum = saveEvent.getJuvNum();
		boolean newConstellation = false;

		if (juvNum != null)
		{
			FamilyConstellation constellation = null;
			// create constellation if ConstellationNum null
			if (saveEvent.getConstellationNum() == null || saveEvent.getConstellationNum().trim().length() == 0)
			{
				constellation = new FamilyConstellation();
				constellation.setJuvenileId(juvNum);
				constellation.setActive(true);
				constellation.setEntryDate(new Date());
				IHome home = new Home();
				home.bind(constellation);
				newConstellation = true;
			}
			else
			{
				constellation = FamilyConstellation.find(saveEvent.getConstellationNum());
			}

			if (constellation != null)
			{
				saveFamilyMembersToConstellation(constellation, saveEvent);
				saveFamilyTraits(constellation, saveEvent);
				if (newConstellation)
				{
					FamilyConstealltionResponseEvent reply = new FamilyConstealltionResponseEvent();
					reply.setConstelltionId(constellation.getOID().toString());
					reply.setTopic(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_TOPIC);
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
					dispatch.postEvent(reply);
				}
			}
		}

	}

	/**
	 * @param constellation
	 * @param saveEvent
	 */
	private static void saveFamilyTraits(FamilyConstellation constellation, SaveFamilyConstellationEvent saveEvent)
	{
		Collection traits = MessageUtil.compositeToCollection(saveEvent, SaveFamilyTraitEvent.class);
		Iterator iter = traits.iterator();
		while (iter.hasNext())
		{
			SaveFamilyTraitEvent addTraitEvent = (SaveFamilyTraitEvent) iter.next();
			FamilyTrait trait = new FamilyTrait();
			trait.setDynamicId(addTraitEvent.getDynamicTraitId());
			trait.setLevelId(addTraitEvent.getLevelId());
			trait.setNotes(addTraitEvent.getComments());
			trait.setStatusId(addTraitEvent.getStatusId());
			constellation.insertTraits(trait);
		}

	}

	/**
	 * @param constellation
	 * @param createEvent
	 */
	public static void saveFamilyMembersToConstellation(
		FamilyConstellation constellation,
		CompositeRequest createEvent)
	{

		Collection costellationMembers =
			MessageUtil.compositeToCollection(createEvent, SaveFamilyConstellationMemberInfoEvent.class);
		Iterator iter = costellationMembers.iterator();
		while (iter.hasNext())
		{
			SaveFamilyConstellationMemberInfoEvent saveEvent = (SaveFamilyConstellationMemberInfoEvent) iter.next();
			FamilyConstellationMember constellationMember = null;

			String constellationMemberNum = saveEvent.getConstellationMemberNum();

			// check if member exists 
			if (constellationMemberNum == null || constellationMemberNum.length() == 0)
			{
				constellationMember = new FamilyConstellationMember();
				constellationMember.setTheFamilyMemberId(saveEvent.getMemberNum());
				constellationMember.setConfirmedDate(new Date());
				constellationMember.setInvolvementLevelId(saveEvent.getInvolvmentLevelId());
				constellationMember.setRelationshipToJuvenileId(saveEvent.getRelationToJuvenileId());
				constellationMember.setPrimaryCareGiver(saveEvent.isPrimaryCareGiver());
				constellationMember.setParentalRightsTerminated(saveEvent.isParentalRightsTerminated());
				constellationMember.setDetentionHearing(saveEvent.isDetentionHearing()); 
				constellationMember.setDetentionVisitation(saveEvent.isDetentionVisitation()); 
				constellationMember.setPrimaryContact(saveEvent.isPrimaryContact());
				constellation.insertFamilyConstellationMembers(constellationMember);
			}
			else
			{
				constellationMember = FamilyConstellationMember.find(constellationMemberNum);
			}

			// 
			if (saveEvent.isRemoveMemberFromConstellation())
			{
				constellationMember.clearConstellationMemberFinancials();
				constellationMember.delete();
			}

			else
				if (constellationMember != null)
				{
					constellationMember.setInHomeStatus(saveEvent.isInHomeStatus());
					constellationMember.setInvolvementLevelId(saveEvent.getInvolvmentLevelId());
					constellationMember.setGuardian(saveEvent.isGuardian());
					constellationMember.setDetentionHearing(saveEvent.isDetentionHearing()); 
					constellationMember.setDetentionVisitation(saveEvent.isDetentionVisitation());
					constellationMember.setRelationshipToJuvenileId(saveEvent.getRelationToJuvenileId());
					constellationMember.setPrimaryCareGiver(saveEvent.isPrimaryCareGiver());
					constellationMember.setParentalRightsTerminated(saveEvent.isParentalRightsTerminated());
					constellationMember.setPrimaryContact(saveEvent.isPrimaryContact());
					Collection finacials =
						MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberFinancialEvent.class);
					Iterator finacialIterator = finacials.iterator();
					while (finacialIterator.hasNext())
					{
						SaveFamilyMemberFinancialEvent saveFinancial =
							(SaveFamilyMemberFinancialEvent) finacialIterator.next();
						saveConstellationMemberFinancial(saveFinancial, constellationMember);
					}
				}
		}
	}

	/**
	 * @param saveEvent
	 * @param constellationMember
	 */
	public static void saveConstellationMemberFinancial(
		SaveFamilyMemberFinancialEvent saveEvent,
		FamilyConstellationMember constellationMember)
	{
		FamilyMemberFinancial financial = new FamilyMemberFinancial();
//		financial.setJobTitle(saveEvent.getJobTitle());
//		financial.setPlaceOfEmpoyment(saveEvent.getPlaceOfEmpoyment());
//		financial.setAnnualNetIncome(saveEvent.getAnnualNetIncome());
		financial.setChildSupportPaid(saveEvent.getChildSupportPaid());
		financial.setChildSupportReceived(saveEvent.getChildSupportReceived());
		financial.setOtherAdultIncome(saveEvent.getOtherAdultIncome());
		financial.setNotes(saveEvent.getNotes());
		financial.setSsi(saveEvent.getSsi());
		financial.setTanfAfdc(saveEvent.getTanfAfdc());
		financial.setFoodStamps(saveEvent.getFoodStamps());
		financial.setRentExpenses(saveEvent.getRentExpenses());
		financial.setUtilitiesExpenses(saveEvent.getUtilitiesExpenses());
		financial.setGroceryExpenses(saveEvent.getGroceryExpenses());
		financial.setLifeInsurancePremium(saveEvent.getLifeInsurancePremium());
		financial.setMedicalExpenses(saveEvent.getMedicalExpenses());
//		financial.setTotalExpenses(saveEvent.getTotalExpenses());
		financial.setPropertyValue(saveEvent.getPropertyValue());
		financial.setIntangibleValue(saveEvent.getIntangibleValue());
		financial.setSavings(saveEvent.getSavings());
		financial.setOtherIncome(saveEvent.getOtherIncome());
		financial.setNumberLivingInHome(saveEvent.getNumberLivingInHome());
		financial.setNumberOfDependents(saveEvent.getNumberOfDependents());
		financial.setChildSupportPayorFirstName(saveEvent.getChildSupportPayorFirstName());
		financial.setChildSupportPayorLastName(saveEvent.getChildSupportPayorLastName());
		financial.setChildSupportPayorMiddleName(saveEvent.getChildSupportPayorMiddleName());
		financial.setSchoolExpenses(saveEvent.getSchoolExpenses());
		financial.setFamilyNum(saveEvent.getNumberInFamily());
		String logonId = PDSecurityHelper.getLogonId();
		//logonId = (logonId == null || logonId.equals(""))?PDJuvenileCaseConstants.CASEFILE_CREATOR:logonId; //89637
		financial.setCreateUserID(logonId);
		constellationMember.insertConstellationMemberFinancials(financial);

	}

	/**
	 * @param phone
	 * @return
	 */
	public static FamilyMemberPhoneResponseEvent getFamilyPhoneResponseEvent(FamilyMemberPhone phone)
	{
		FamilyMemberPhoneResponseEvent phoneReply = new FamilyMemberPhoneResponseEvent();
		phoneReply.setFamilyPhoneId("" + phone.getOID());
		phoneReply.setPhoneTypeId(phone.getPhoneTypeId());
		Date aDate = new Date(phone.getCreateTimestamp().getTime());
		phoneReply.setEntryDate(aDate);
		Phone phoneMaster = phone.getPhoneMaster();
		if(phoneMaster != null ){
		    phoneReply.setPhoneNum(phoneMaster.getPhoneNumber());
		    phoneReply.setExtentionNum(phoneMaster.getPhoneExt());		   
		    phoneReply.setPrimaryInd(phoneMaster.isPrimaryInd());		    
		}		
		return phoneReply;
	}
	
	public static FamilyMemberEmailResponseEvent getFamilyEmailResponseEvent(FamilyMemberEmail email)
	{
		FamilyMemberEmailResponseEvent reply = new FamilyMemberEmailResponseEvent();
		reply.setFamilyEmailId("" + email.getOID());
		reply.setEmailTypeId(email.getEmailTypeId());
		reply.setEmailAddress(email.getEmailAddress());
		Date aDate = new Date(email.getCreateTimestamp().getTime());
		reply.setEntryDate(aDate);
		reply.setPrimaryInd(email.getPrimaryInd());
		return reply;
	}
	
	public static MemberContact getLatestFamilyMemberEmail(MemberContact memberContact, String memberNumber)
	{
	    FamilyMemberEmail latestEmail = null;
	    Date latestDate = null;
	    List<FamilyMemberEmail> emailList = getFamilyMemberEmailSortedList(memberNumber);
	    String primaryIndicator = null;
	    boolean primaryIndicatorFound = false;
	    
	    //Iterator<FamilyMemberEmail> iter = FamilyMemberEmail.findAll("familyMemberId", memberNumber);
	    //while(iter != null && iter.hasNext()){
		
	    //	FamilyMemberEmail email = (FamilyMemberEmail)iter.next();
	    //	if(email != null && email.getCreateTimestamp() != null){
	    //	Date date = (Date)email.getCreateTimestamp();
	    //	if(date != null){
	    //	if(latestDate == null || date.after(latestDate)){
	    //	latestDate = date;
	    //	latestEmail = email; 
	    //	}
	    //	}
	    //	}
	    //}
	    
	    if(emailList != null && emailList.size() == 1)
	    {
		latestEmail = emailList.get(0);
		primaryIndicator = latestEmail.getPrimaryInd() ? "Primary" : UIConstants.EMPTY_STRING;
		memberContact.setPrimaryIndEmail(primaryIndicator);
		if(latestEmail != null && latestEmail.getEmailAddress() != null){
		    memberContact.setEmailAddress(latestEmail.getEmailAddress());
		}
		return memberContact;
	    }
	    
	    if(emailList != null && emailList.size() > 1)
	    {
		   for(int i = 0; i < emailList.size(); i++)
		   {
			FamilyMemberEmail email = emailList.get(i); 
			
			if(email != null && email.getPrimaryInd())
			{
			    primaryIndicatorFound = true;
			    memberContact.setPrimaryIndEmail("Primary");
			    if(email.getEmailAddress() != null){
				memberContact.setEmailAddress(email.getEmailAddress());
			    }
			    
			    return memberContact;
			}
		   }
		    
		 if(!primaryIndicatorFound){
		     
		     for(int i = 0; i < emailList.size(); i++)
		     {			 
			 FamilyMemberEmail email = emailList.get(i); 
				
			 if(email != null && !email.getPrimaryInd())
			 {
			     memberContact.setPrimaryIndEmail("");
			     if(email.getEmailAddress() != null){
				 memberContact.setEmailAddress(email.getEmailAddress());
			     }
			     
			     return memberContact;
			 }					
		     }
		     
		 }
		
	    }
	    	    
	    return memberContact;
	}

	private static List<FamilyMemberEmail> getFamilyMemberEmailSortedList(String memberNumber)
	{	    
	    List<FamilyMemberEmail> emailList = new ArrayList<FamilyMemberEmail>();
	    Iterator<FamilyMemberEmail> iter = FamilyMemberEmail.findAll("familyMemberId", memberNumber);
	    
        	    while(iter != null && iter.hasNext()){		
        		FamilyMemberEmail email = (FamilyMemberEmail)iter.next();
        		emailList.add(email);
        	    }
        	    
        	    if(emailList.size() == 0 || emailList.size() == 1){
        		return emailList;
        	    }
        	    
        	    //sort descending
        	    Collections.sort(emailList, new Comparator<FamilyMemberEmail>() {
        		@Override
        	        public int compare(FamilyMemberEmail e1, FamilyMemberEmail e2) {
        		    return e2.getCreateTimestamp().compareTo(e1.getCreateTimestamp());
        	        }
        	   });
	    
	   return emailList;   	    
	}
	
	/**
	 * @param motherPhone
	 * @return
	 */
    private static Phone getPhone(String phoneNumber, String extention)
    {
	// TODO Auto-generated method stub
	Phone phone = null;
	if (phoneNumber != null)
	{

	    //TODO modify this logic later include ext in search  
	    Iterator<Phone> iter = Phone.findAll("phoneNumber", phoneNumber);
	    if (iter.hasNext())
	    {
		phone = (Phone) iter.next();
	    }
	    else
	    {
		phone = new Phone();
		phone.setPhoneNumber(phoneNumber);
		phone.setPhoneExt(extention);

		IHome home = new Home();
		home.bind(phone);

	    }
	}
	return phone;
    }

	/**
	 * @param motherPhone
	 * @return
	 */
	private static Phone getPhone(SaveFamilyMemberContactEvent saveEvent)
	{
		// TODO Auto-generated method stub
		Phone phone = null;
		if (saveEvent.getPhoneNum() != null)
		{

			//TODO modify this logic later include ext in search  
			Iterator iter = Phone.findAll(saveEvent);
			if (iter.hasNext())
			{
				phone = (Phone) iter.next();
				phone.setPrimaryInd(saveEvent.isPrimaryInd());
			}
			else
			{
				phone = new Phone();
				phone.setPhoneNumber(saveEvent.getPhoneNum());
				phone.setPhoneExt(saveEvent.getExtentionNum());
				phone.setPrimaryInd(saveEvent.isPrimaryInd());
				
				IHome home = new Home();
				home.bind(phone);

			}
		}
		return phone;
	}
	/**
	 * @param memberPhoneNum
	 * @param extention
	 * @param type
	 * @return
	 */
	public static FamilyMemberPhone createMemberPhone(
		String memberPhoneNum,
		String extention,
		String type,
		String memberId)
	{
		FamilyMemberPhone famPhone = null;
		Phone phone = getPhone(memberPhoneNum, extention);
		if (phone != null)
		{
			famPhone = new FamilyMemberPhone();
			famPhone.setPhoneMasterId((String) phone.getOID());
			famPhone.setFamilyMemberId(memberId);
			famPhone.setPhoneTypeId(type);
			famPhone.setCreateUserID(SecurityUIHelper.getLogonId());
			famPhone.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
		}
		return famPhone;
	}
	
	public static FamilyMemberPhone createMemberPhone(
			SaveFamilyMemberContactEvent saveEvent,
			String memberId)
		{
			FamilyMemberPhone famPhone = null;
			Phone phone = getPhone(saveEvent);
			//Defect fix:JIMS200077386 added additional condition.
			if (phone != null && (phone.isPrimaryInd()&&saveEvent.getConstellationMemberPhoneId().equalsIgnoreCase("")) || ((!phone.isPrimaryInd())&&saveEvent.getConstellationMemberPhoneId().equalsIgnoreCase("")))
			{
				famPhone = new FamilyMemberPhone();
				famPhone.setPhoneMasterId((String) phone.getOID());
				famPhone.setFamilyMemberId(memberId);
				famPhone.setPhoneTypeId(saveEvent.getPhoneTypeId());
				famPhone.setCreateUserID(SecurityUIHelper.getJIMSLogonId());
				famPhone.setCreateJIMS2UserID(SecurityUIHelper.getJIMS2LogonId());
				famPhone.setCreateTimestamp(new Timestamp(DateUtil.getCurrentDate().getTime()));
			}
			return famPhone;
		}
	
	public static FamilyMemberEmail createMemberEmail(
			String memberEmailAddress,SaveFamilyMemberContactEvent saveEvent,
			String type,
			String memberId)
		{
			FamilyMemberEmail famEmail = null;
			 if(saveEvent.getConstellationMemberEmailId().equalsIgnoreCase("")){ //bug fix 35677
				famEmail = new FamilyMemberEmail();
				famEmail.setEmailAddress(memberEmailAddress);
				famEmail.setFamilyMemberId(memberId);
				//TODO 
				famEmail.setEmailTypeId(type);
				famEmail.setPrimaryInd(saveEvent.getPrimaryIndEmail());
			 }
			return famEmail;
		}

	/**
	 * @param string
	 */
	public static void processFamilyMemberListResponse(
		String constellationId,
		IDispatch dispatch,
		boolean financialInfoReqd)
	{

		Iterator iter = FamilyConstellationMember.findAll("familyConstellationId", constellationId);
		while (iter.hasNext())
		{
			FamilyConstellationMember familyConstellationMember = (FamilyConstellationMember) iter.next();
			FamilyMember familyMember = familyConstellationMember.getTheFamilyMember();

			FamilyConstellationMemberListResponseEvent memReply =
				JuvenileFamilyHelper.getFamilyMemberListResponseEvent(familyConstellationMember, familyMember);
			dispatch.postEvent(memReply);
			// if financial is requested 
			if (financialInfoReqd && familyConstellationMember.isGuardian())
			{
				GetFamilyConstellationMemberLatestFinancialEvent getConstelltionMemberFinacialEvent =
					new GetFamilyConstellationMemberLatestFinancialEvent();
				getConstelltionMemberFinacialEvent.setConstelltionMemberId(
					familyConstellationMember.getOID().toString());
				processFamilyConstellationMemberLatestFinancial(getConstelltionMemberFinacialEvent, dispatch);

			}

		}

	}

	public static void processFamilyConstellationMemberLatestFinancial(
		GetFamilyConstellationMemberLatestFinancialEvent getConstelltionMemberFinacialEvent,
		IDispatch dispatch)
	{
		Iterator financialIter = FamilyMemberFinancial.findAll(getConstelltionMemberFinacialEvent);
		// only first record  
		FamilyMemberFinancial financial=null;
		if (financialIter.hasNext())
		{
			financial = (FamilyMemberFinancial) financialIter.next();
			FamilyConstellationMemberFinancialResponseEvent latestFinancial =
				getFamilyConstellationMemberFinancialResponseEvent(financial);
			latestFinancial.setTopic(
				getConstelltionMemberFinacialEvent.getConstelltionMemberId()
					+ "_"
					+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC);
			dispatch.postEvent(latestFinancial);

		}
		else{
			FamilyConstellationMemberFinancialResponseEvent myRespEvt=new FamilyConstellationMemberFinancialResponseEvent();
			myRespEvt.setEntryDate(new Date());
			myRespEvt.setTopic(
					getConstelltionMemberFinacialEvent.getConstelltionMemberId()
					+ "_"
					+ PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_FINANCIAL_TOPIC);
			dispatch.postEvent(myRespEvt);
		}
	}

	/**
	 * @param string
	 * @param dispatch
	 */
	public static void processFamilyTraitsResponse(String constellationId, IDispatch dispatch)
	{
		Iterator iter = FamilyTrait.findAll("familyConstellationId", constellationId);
		while (iter.hasNext())
		{
			FamilyTrait trait = (FamilyTrait) iter.next();

			FamilyConstellationTraitsResponseEvent traitReply =
				JuvenileFamilyHelper.getFamilyConstellationTraitsResponseEvent(trait);
			dispatch.postEvent(traitReply);

		}

	}

	private static final String UNRESOLVED = "UNRESOLVED";
	public static final String MEMBERA = "memberA";
	public static final String MEMBERB = "memberB";
	/**
	 * 
	 */
	public static FamilyMember updateFamilyMember(IFamilyMember familyMember)
	{
		String memberId = familyMember.getMemberId();
		FamilyMember member = null;
		boolean isUpdate = false;
		if (memberId != null)
		{
			member = FamilyMember.find(memberId);
			isUpdate = true;
		}
		else
		{
			member = new FamilyMember();
		}

		member.setFirstName(familyMember.getFirstName());
		member.setLastName(familyMember.getLastName());
		member.setMiddleName(familyMember.getMiddleName());
		//User Story 39892
		/*if(familyMember.getSsn()!=null && !familyMember.getSsn().equals(""))
		{
			//check if the ssn is a repeat of a single character
			String ssn = familyMember.getSsn();
			String firstChar = ssn.substring(0, 1);
			String matchingChars[] = ssn.split(firstChar+ "+");
			boolean repeatChars = (matchingChars.length == 0);
			  if (!repeatChars)//Individual has never had a social security number.
			  {
				  member.setSsn("XXXXX"+familyMember.getSsn().substring(5));
			  }
			  else 
				  member.setSsn(familyMember.getSsn());
		}*/
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
		member.setPsportNum(familyMember.getPsportNum()); //added for passport detail
		member.setPsportExpiryDate(familyMember.getPsportExpiryDate()); //added for passport details
		member.setPsportIssueCountryId(familyMember.getPsportIssueCountryId());//added for passport details
		
		//added for User Story 43892
		member.setOver21(familyMember.isOver21());
		
		
		if (member.isDeceased())
		{
			if(memberId != null){
				Iterator constellationMemIter =
					FamilyConstellationMember.findAll("theFamilyMemberId", member.getOID().toString());
				while (constellationMemIter.hasNext())
				{
					FamilyConstellationMember constellationMember = (FamilyConstellationMember) constellationMemIter.next();
					if (constellationMember != null && constellationMember.isGuardian())
					{
						FamilyConstellation constelltion = constellationMember.getFamilyConstellation();
						if(constelltion.isActive()){
							boolean hasAnActiveGuardian=false;
							Collection constCurrentMembers=constelltion.getFamilyConstellationMembers();
							if(constCurrentMembers!=null && constCurrentMembers.size()>0){
								Iterator loopConstMembers=constCurrentMembers.iterator();
								while(loopConstMembers.hasNext()){
									FamilyConstellationMember myMember=(FamilyConstellationMember)loopConstMembers.next();
									if(myMember!=null && myMember.isGuardian()){
										if(!(myMember.getTheFamilyMemberId().equalsIgnoreCase(constellationMember.getTheFamilyMemberId()))){
											FamilyMember tempMember = FamilyMember.find(myMember.getTheFamilyMemberId());
											if(tempMember!=null && !(tempMember.isDeceased())){
												constellationMember.setGuardian(false);
												hasAnActiveGuardian=true;
												break;
											} // end if the member is not null, not deceased, and is a guardian;
										}  // end if member is not same as current member
									} // END if member is not null
								} // ENd While loop through members
							}// END IF const has members
							constelltion.setActive(hasAnActiveGuardian);
						}// END IF constellation active
					}// End if member is guardian of constellation
				}// ENd while 
			}
		}
		
		//Need OID for FamilyMemberMatch records.
		IHome home = new Home();
		home.bind(member);

		Map existingMatchingMemberMap = new HashMap();
		Map newSuspiciousMemberMap = new HashMap();
		FamilyMemberMatch familyMemberMatch = null;
		String matchingMemberId = null;
		
		Iterator iter = FamilyMemberMatch.findAll(MEMBERA, member.getOID());
		List <FamilyMemberMatch> existingMatches = CollectionUtil.iteratorToList(iter);
		
		for (int i = 0; i < existingMatches.size(); i++) {
			familyMemberMatch = existingMatches.get(i);
			existingMatchingMemberMap.put(familyMemberMatch.getMemberB(), familyMemberMatch);
		}

		if (familyMember.getSuspiciousMatches() != null && familyMember.getSuspiciousMatches().size() > 0){
			StringBuffer msg = new StringBuffer("SYSTEM GENERATED NOTES: Match found when member number ");
			//msg.append(familyMember.getMemberId());//commented to add the memberNum in the Notes column in JCFMEMMATCHVAL tables
			msg.append(member.getOID());
			if (isUpdate){
				msg.append(" was being updated.");
			} else {
				msg.append(" was being created.");
			}
			String finalMsg = msg.toString();

			for (int i = 0; i < familyMember.getSuspiciousMatches().size(); i++) {

				matchingMemberId = (String) familyMember.getSuspiciousMatches().get(i);
				newSuspiciousMemberMap.put(matchingMemberId, matchingMemberId);
				if (existingMatchingMemberMap.get(matchingMemberId) == null){
				    FamilyMember matchedFamMem = FamilyMember.find(matchingMemberId);//added for US  185870
				    String matchType = "";
					familyMemberMatch = new FamilyMemberMatch();
					familyMemberMatch.setMemberA(member.getOID());
					familyMemberMatch.setMemberB(matchingMemberId);
					familyMemberMatch.setStatus(UNRESOLVED);
					familyMemberMatch.setNotes(finalMsg);
					//added for  185870 BEGINS
					if(matchedFamMem.getSSN()!= null && !matchedFamMem.getSSN().isEmpty()){
					    if(matchedFamMem.getSSN().equalsIgnoreCase(member.getSSN())){
						matchType = "SSN";
					    }else if(matchedFamMem.getDriverLicenseNum() != null && !matchedFamMem.getDriverLicenseNum().isEmpty())
					    {
						if(matchedFamMem.getDriverLicenseNum().equalsIgnoreCase(member.getDriverLicenseNum()))
						{
						    matchType = "DL";
						}
					    }
					}//added for US  185870 ENDS
					familyMemberMatch.setMatchType(matchType); //added for US 181437, 185870
					familyMemberMatch = new FamilyMemberMatch();
					familyMemberMatch.setMemberA(matchingMemberId);
					familyMemberMatch.setMemberB(member.getOID());
					familyMemberMatch.setStatus(UNRESOLVED);
					familyMemberMatch.setNotes(finalMsg);
					familyMemberMatch.setMatchType(matchType); //added for US 181437, 185870  
					
					//added for US 183152 STARTS
					//To get the Family ID, use the Fammember_ID and join it with the JCCONSRELATION table.
					//Find the highest (most recent) CONSTELLATION_ID for the family.

					List<FamilyConstellation> constellations = new ArrayList<FamilyConstellation>();
					    
					Iterator iterConstellation = FamilyConstellationMember.findAll("theFamilyMemberId", matchingMemberId);         	    
					while(iterConstellation.hasNext())
					{
				     		FamilyConstellationMember constMem = (FamilyConstellationMember)iterConstellation.next();
				     	
				     		if(constMem != null && constMem.getFamilyConstellationId() != null && !"".equals(constMem.getFamilyConstellationId()))
				     		{   	    
				     		    constellations.add(constMem.getFamilyConstellation());
				     		}
					}
					ArrayList sortedConstList = new ArrayList();
					sortedConstList.add(new ReverseComparator(new BeanComparator("familyConstellationId")));
					ComparatorChain chain = new ComparatorChain(sortedConstList);
					Collections.sort(constellations, chain);
					String familyID = "";
					String juvenileId = null;
					if(constellations!= null &&  constellations.size()>0){
					FamilyConstellation constellation = (FamilyConstellation)constellations.get(0);
					familyID = constellation.getFamilyConstellationId();
					juvenileId = constellation.getJuvenileId();
					/*if(familyConstellationId != null){
						  //get constellation info from JCConstellation
						    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(familyID);
						    
						    if(familyConstellation != null && familyConstellation.getJuvenileId() != null)
						    {
							juvenileId = familyConstellation.getJuvenileId();
						    }
						    
						}*/
					}
					
					List<FamilyConstellation> constellationsMem = new ArrayList<FamilyConstellation>();
					    
					Iterator iterConstellationMem = FamilyConstellationMember.findAll("theFamilyMemberId", member.getOID());         	    
					while(iterConstellationMem.hasNext())
					{
				     		FamilyConstellationMember constMem2 = (FamilyConstellationMember)iterConstellationMem.next();
				     	
				     		if(constMem2 != null && constMem2.getFamilyConstellationId() != null && !"".equals(constMem2.getFamilyConstellationId()))
				     		{   	    
				     		constellationsMem.add(constMem2.getFamilyConstellation());
				     		}
					}
					ArrayList sortedConstList2 = new ArrayList();
					sortedConstList2.add(new ReverseComparator(new BeanComparator("familyConstellationId")));
					ComparatorChain chain2 = new ComparatorChain(sortedConstList2);
					Collections.sort(constellationsMem, chain2);
					
					String familyIDMem2 = null;
					String juvenileIdMem2 = null;
					if(constellationsMem!= null &&  constellationsMem.size()>0){
					FamilyConstellation constellationMem = (FamilyConstellation)constellationsMem.get(0);
					familyIDMem2 = constellationMem.getFamilyConstellationId();
					juvenileIdMem2 = constellationMem.getJuvenileId();
					/*if(familyConstellationIdMem2 != null){
						  //get constellation info from JCConstellation
						    FamilyConstellation familyConstellation = (FamilyConstellation)FamilyConstellation.find(familyIDMem2);
						    
						    if(familyConstellation != null && familyConstellation.getJuvenileId() != null)
						    {
							juvenileIdMem2 = familyConstellation.getJuvenileId();
						    }
						    
						}*/
					}
					//added for US 183152 ENDS
					
					//send email task 161785
        				SendEmailEvent sendEmailEvent = new SendEmailEvent();
        				//String emailDataCorrections = "Eso.Ajewole@harriscountytx.gov"; //"nekha.mathew@harriscountytx.gov";
        		        	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");        		        	
        		        	sendEmailEvent.addToAddress("Data.Corrections@hcjpd.hctx.net");
        		        	//sendEmailEvent.addToAddress(emailDataCorrections);
        		        	//UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, emailDataCorrections);         		        	 
        		        	sendEmailEvent.setSubject("Suspicious Family Member Created "+member.getOID());
        		        	StringBuffer emailMsg = new StringBuffer("Family member id: ");
        		        	emailMsg.append(familyMemberMatch.getMemberA());
        		        	emailMsg.append(" was located as a duplicate to ");
        		        	emailMsg.append(familyMemberMatch.getMemberB());
        		        	emailMsg.append(".  Please update the Suspicious Family Member.\n");
        		        	emailMsg.append("Member " + familyMemberMatch.getMemberA() + " " + matchedFamMem.getLastName() + ",  " + matchedFamMem.getFirstName()+ ";  ");
        		        	emailMsg.append("Family " + familyID + "; Juvenile " + juvenileId);
        		        	emailMsg.append("    \n");
        		        	emailMsg.append("Member " + member.getOID() + " " + member.getLastName() + ",  " +member.getFirstName()+ ";  ");
        		        	emailMsg.append("Family " + familyIDMem2 + "; Juvenile " + juvenileIdMem2 + "; \n" );
        		        	//sendEmailEvent.setMessage("Family member id: "+familyMemberMatch.getMemberA() +" was located as a duplicate to "+familyMemberMatch.getMemberB()+".  Please update the Suspicious Family Member.");
        		        	sendEmailEvent.setMessage(emailMsg.toString());
        		        	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
        		        	MessageUtil.processReturnException( res ) ;
        		        	//
				}
			}
			
			msg = null;
			finalMsg = null;
		}
		
		List <FamilyMemberMatch> existingSuspiciousMemberList = CollectionUtil.iteratorToList(existingMatchingMemberMap.values().iterator());
		for (int i = 0; i < existingSuspiciousMemberList.size(); i++) {
			familyMemberMatch = existingSuspiciousMemberList.get(i);
			if (newSuspiciousMemberMap.get(familyMemberMatch.getMemberB()) == null){
				//Member is no longer suspicious 
				familyMemberMatch.delete();
				removeSuspicousMemberFlags(MEMBERB, familyMemberMatch.getMemberA());
			}
		}
		
		familyMemberMatch = null;
		iter = null;
		existingMatches = null;
		existingMatchingMemberMap = null;
		newSuspiciousMemberMap = null;
    	home = null;	
		matchingMemberId = null;
		
		return member;
	}

	/**
	 * @param familyMemberId
	 */
	public static void removeSuspicousMemberFlags(String attributeName, String familyMemberId){
		
		Iterator iter = FamilyMemberMatch.findAll(attributeName, familyMemberId);
		List <FamilyMemberMatch> aList = CollectionUtil.iteratorToList(iter);
		
		FamilyMemberMatch familyMemberMatch = null;
		IHome home = new Home();
		
		for (int i = 0; i < aList.size(); i++) {
			familyMemberMatch = aList.get(i);
			familyMemberMatch.delete();
			home.bind(familyMemberMatch);
		}
		
		home = null;
		iter = null;
		aList = null;
		familyMemberMatch = null;
	}
	
	/**
	 * @param populateFamilyMember
	 */
	public static FamilyMemberDetailResponseEvent populateFamilyMember(
		FamilyMemberDetailResponseEvent member,
		FamilyMember familyMember)
	{
	    
	    	ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		member.setMemberId(UIConstants.EMPTY_STRING + familyMember.getOID());
		member.setFirstName(familyMember.getFirstName());
		member.setLastName(familyMember.getLastName());
		member.setMiddleName(familyMember.getMiddleName());
		//User Story 39892
		if(familyMember.getSSN()!=null && !familyMember.getSSN().equals(""))
		{
			//check if the ssn is a repeat of a single character
			String ssn = familyMember.getSSN();
			String firstChar = ssn.substring(0, 1);
			String matchingChars[] = ssn.split(firstChar+ "+");
			boolean repeatChars = (matchingChars.length == 0);
			  if (!repeatChars)//Individual has never had a social security number.
			  {
			      if ( mgr.isAllowed( Features.JRP_JUVSSN_U) ){
				  member.setSsn(familyMember.getSSN());
			      } else {
				  member.setSsn("XXXXX"+familyMember.getSSN().substring(5));
			      }
				 
			  } else {
				  member.setSsn(familyMember.getSSN());
			  }
		}
		member.setCompleteSSN(familyMember.getSSN());
		member.setDateOfBirth(familyMember.getDateOfBirth());
		member.setSexId(familyMember.getSexId());
		member.setAlienRegistrationNum(familyMember.getAlienNum());
		member.setIsUSCitizenId(familyMember.getIsUSCitizenId());
		member.setEthnicityId(familyMember.getEthnicityId());
		member.setNationalityId(familyMember.getNationalityId());
		member.setSidNum(familyMember.getSidNum());
		member.setSuspiciousMember(familyMember.getSuspiciousMember());

		member.setPrimarylanguageId(familyMember.getPrimaryLanguageId());
		member.setSecondaryLanguageId(familyMember.getSecondaryLanguageId());
		member.setDeceasedInd(familyMember.isDeceased());
		member.setIncarcerated(familyMember.isIncarcerated());
		member.setCauseOfDeathId(familyMember.getCauseofDeathId());
		member.setComments(familyMember.getComments());

		member.setDriverLicenceNumber(familyMember.getDriverLicenseNum());
		member.setDriverLicenceStateId(familyMember.getDriverLicenseStateId());
		member.setDriverLicenceExpiryDate(familyMember.getDriverLicenseExpirationDate());
		member.setDriverLicenceClassId(familyMember.getDriverLicenseClass());
		member.setIdCardNum(familyMember.getIdCardNum());
		member.setIdCardStateId(familyMember.getIdCardStateId());
		member.setPsportNum(familyMember.getPsportNum()); //added for passport details
		member.setPsportIssueCountryId(familyMember.getPsportIssueCountryId()); //added for passport details
		member.setPsportExpirationDate(familyMember.getPsportExpiryDate()); //added for passport details                                                                                                                                                                                                                                                                                                                                                    
		
		member.setOver21(familyMember.isOver21()); //added for User Story 43892
		
		return member;
	}

	/**
	 * @param iterator
	 */
	public static void processFamilyMemberReplies(List <FamilyMember> memberList)
	{
		FamilyMemberListResponseEvent reply = null;
		FamilyMember famMember = null;
		List respList = new ArrayList();
		Map memberMap = new HashMap();
		
		for (int i = 0; i < memberList.size(); i++) {
			famMember = memberList.get(i);
			if (memberMap.get(famMember.getFamilyMemberId()) == null){
				reply = JuvenileFamilyHelper.getFamilyMemberListResponseEvent(famMember);
				respList.add(reply);
				memberMap.put(famMember.getFamilyMemberId(), famMember.getFamilyMemberId());
			}
		}
		
		MessageUtil.postReplies(respList);
		
		reply = null;
		famMember = null;
		respList = null;
		memberMap = null;
		memberList = null;
	}
	
	/**
	 * @param familyMember
	 * @return
	 */
	public static void processFamilyMemberWithAssocJuvenilesReplies(List <FamilyMember> memberList)
	{
		FamilyMemberListResponseEvent reply = null;
		FamilyMember famMember = null;
		List respList = new ArrayList();
		Map memberMap = new HashMap();
		
		for (int i = 0; i < memberList.size(); i++) {
			famMember = memberList.get(i);
			if (memberMap.get(famMember.getFamilyMemberId()) == null){
				reply = JuvenileFamilyHelper.getFamilyMemberListResponseEvent(famMember);

	  			Iterator iter = AssociatedJuvMemberView.find(famMember.getFamilyMemberId());
				List <AssociatedJuvMemberView> jList = CollectionUtil.iteratorToList(iter);
				List juvReplies = new ArrayList();
				AssociatedJuvMemberView myData = null;
				AssociatedJuvenilesResponseEvent myRespEvt = null;
		
				for (int j = 0; j < jList.size(); j++) 
				{
					myData = jList.get(j);
					if(myData.isConstActive()){
						myRespEvt=myData.getResponseEvent();
						juvReplies.add(myRespEvt);
					}
				}
				if (!juvReplies.isEmpty()){
					reply.setAssociatedJuveniles(juvReplies);
				}			

				respList.add(reply);
				memberMap.put(famMember.getFamilyMemberId(), famMember.getFamilyMemberId());
			}
		}
		
		MessageUtil.postReplies(respList);
		
		reply = null;
		famMember = null;
		respList = null;
		memberMap = null;
		memberList = null;
	}
	
	/**
	 * @param familyMember
	 * @return
	 */
	public static FamilyMemberListResponseEvent getFamilyMemberListResponseEvent(FamilyMember familyMember){
		
		FamilyMemberListResponseEvent reply = new FamilyMemberListResponseEvent();
		reply.setMemberNum(UIConstants.EMPTY_STRING + familyMember.getOID());
		if (familyMember.getFirstName() != null){
			reply.setFirstName(familyMember.getFirstName());
		} else {
			reply.setFirstName(PDConstants.BLANK);
		}
		if (familyMember.getLastName() != null){
			reply.setLastName(familyMember.getLastName());
		} else{
			reply.setLastName(PDConstants.BLANK);
		}
		if (familyMember.getMiddleName() != null){
			reply.setMiddleName(familyMember.getMiddleName());
		} else {
			reply.setMiddleName(PDConstants.BLANK);
		}
		reply.setDeceased(familyMember.isDeceased());
		reply.setIncarcerated(familyMember.isIncarcerated());
		//User Story 39892
		if(familyMember.getSSN()!=null && !familyMember.getSSN().equals(""))
		{
			//check if the ssn is a repeat of a single character
			String ssn = familyMember.getSSN();
			String firstChar = ssn.substring(0, 1);
			String matchingChars[] = ssn.split(firstChar+ "+");
			boolean repeatChars = (matchingChars.length == 0);
			  if (!repeatChars)//Individual has never had a social security number.
			  {	 
				  reply.setSSN("XXXXX"+ ssn.substring(5));
			  }
			  else 
				  reply.setSSN(ssn);
		    reply.setOriginalSSN(ssn);
		}
		reply.setDateOfBirth(familyMember.getDateOfBirth()); 
		reply.setSuspiciousMember(familyMember.getSuspiciousMember());
		if (!UIConstants.EMPTY_STRING.equals(familyMember.getSexId()) && familyMember.getSexId() != null) {
			reply.setSex(familyMember.getSex().getDescription());
		}
		reply.setSexCd(familyMember.getSexId());
		reply.setDateOfBirth(familyMember.getDateOfBirth());
		reply.setNationalityCd(familyMember.getNationalityId());
		reply.setEthnicityCd(familyMember.getEthnicityId());
		reply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_LIST_TOPIC);
		reply.setOver21(familyMember.isOver21());
		return reply;
	}

	/**
	 * @param memberList
	 */
	public static void processFamilyMemberMatchReplies(List <String> memberIdList)
	{
		String famMemberId = null;
		FamilyMember familyMember = null;
		List replies = new ArrayList();
		FamilyMemberListResponseEvent re = null;
		
		for (int i = 0; i < memberIdList.size(); i++) {
			famMemberId = memberIdList.get(i);
			familyMember = FamilyMember.find(famMemberId);
			re= JuvenileFamilyHelper.getFamilyMemberListResponseEvent(familyMember);
			replies.add(re);
		}
		
		MessageUtil.postReplies(replies);
		
		replies = null;
		famMemberId = null;
		familyMember = null;
		re = null;
	}
 
	/**
	 * @param memberList
	 */
	public static void processFamilyMemberMatchWithAssocJuvenilesReplies(List <String> memberIdList)
	{
		String famMemberId = null;
		FamilyMember familyMember = null;
		List replies = new ArrayList();
		FamilyMemberListResponseEvent reply = null;
		
		for (int i = 0; i < memberIdList.size(); i++) {
			famMemberId = memberIdList.get(i);
			familyMember = FamilyMember.find(famMemberId);
			reply= JuvenileFamilyHelper.getFamilyMemberListResponseEvent(familyMember);
  			Iterator iter = AssociatedJuvMemberView.find(familyMember.getFamilyMemberId());
			List <AssociatedJuvMemberView> jList = CollectionUtil.iteratorToList(iter);
			List juvReplies = new ArrayList();
			AssociatedJuvMemberView myData = null;
			AssociatedJuvenilesResponseEvent myRespEvt = null;
	
			for (int j = 0; j < jList.size(); j++) 
			{
				myData = jList.get(j);
				if(myData.isConstActive()){
					myRespEvt=myData.getResponseEvent();
					juvReplies.add(myRespEvt);
				}
			}
			if (!juvReplies.isEmpty()){
				reply.setAssociatedJuveniles(juvReplies);
			}			

			replies.add(reply);
		}
		
		MessageUtil.postReplies(replies);
		
		replies = null;
		famMemberId = null;
		familyMember = null;
		reply = null;
	}
	
	/**
	 * @param mother
	 * @param relationshipId
	 * @return FamilyConstellationMember
	 */
	public static FamilyConstellationMember createCostellationMember(FamilyMember member)
	{
		FamilyConstellationMember familyConstellationMember = null;
		if (member != null)
		{
			familyConstellationMember = new FamilyConstellationMember();
			familyConstellationMember.setTheFamilyMemberId((String) member.getOID());
		}
		return familyConstellationMember;
	}

	/**
	 *  
	 * @param constellationMember The constellation member.
	 * @param famMember The fam member.
	 * @return  The family member list response event.
	 */
	public static FamilyMemberDetailResponseEvent getFamilyMemberDetailResponseEvent(FamilyMember famMember)
	{
		FamilyMemberDetailResponseEvent reply = new FamilyMemberDetailResponseEvent();

		reply = (FamilyMemberDetailResponseEvent) JuvenileFamilyHelper.populateFamilyMember(reply, famMember);
		return reply;
	}
	
	
	public static void sendAssociatedJuvList(String familyMemberId)
	{

		Iterator iter = AssociatedJuvMemberView.find(familyMemberId);
		List <AssociatedJuvMemberView> aList = CollectionUtil.iteratorToList(iter);
		List replies = new ArrayList();
		AssociatedJuvMemberView myData = null;
		AssociatedJuvenilesResponseEvent myRespEvt = null;
		
		for (int i = 0; i < aList.size(); i++) 
		{
		    myData = aList.get(i);
			
			//if(myData.isConstActive()){
			myRespEvt=myData.getResponseEvent();
			
			if(myRespEvt.isActiveConstellation())
			{
			  replies.add(myRespEvt);
			}
	    }

		MessageUtil.postReplies(replies);
		
		iter = null;
		aList = null;
		replies = null;
		myData = null;
		myRespEvt = null;
	}

	/**
	 * @param familyMember
	 * @param dispatch
	 */
	public static void sendMaritalStatusList(String familyMemberId)
	{
		//FamilyMemberMartialStatusResponseEvent
		Iterator iter = FamilyMemberMaritalStatus.findAll("theFamilyMemberId", familyMemberId);
		List <FamilyMemberMaritalStatus> marStatusList = CollectionUtil.iteratorToList(iter);
		
		FamilyMemberMaritalStatus status = null;
		FamilyMemberMartialStatusResponseEvent reply = null;
		FamilyMember relatedMember = null;
		List replies = new ArrayList();
		
		for (int i = 0; i < marStatusList.size(); i++) 
		{
			status = marStatusList.get(i);
			reply = new FamilyMemberMartialStatusResponseEvent();
			reply.setMartialId(status.getOID());
			reply.setEntryDate(status.getEntryDate());
			reply.setMartialStatusId(status.getMaritalStatusId());
			reply.setMarriageDate(status.getMarriageDate());
			reply.setDivorceDate(status.getDivorceDate());
			reply.setNumberOfChildren(status.getNoOfChildren());
			reply.setRelatedFamMemId(status.getTheRelatedFamMemId());
			relatedMember=status.getTheRelatedFamMem();
			
			if(relatedMember!=null){
				reply.setRelatedFamMemFirstName(relatedMember.getFirstName());
				reply.setRelatedFamMemMiddleName(relatedMember.getMiddleName());
				reply.setRelatedFamMemLastName(relatedMember.getLastName());
			}
			
			replies.add(reply);
		}

		MessageUtil.postReplies(replies);
		
		iter = null;
		marStatusList = null;
		status = null;
		reply = null;
		relatedMember = null;
		replies = null;

	}

	/**
	 * @param saveEvent
	 */
	public static void saveFamilyMemberAdditionalInfo(SaveFamilyMemberAdditionalInfoEvent saveEvent)
	{
		String memberId = saveEvent.getMemberId();
		FamilyMember member = FamilyMember.find(memberId);
		// address
		Collection familyMemberAddresses =
			MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberAddressEvent.class);
		if (familyMemberAddresses != null && familyMemberAddresses.size() > 0)
		{
			Iterator iter = familyMemberAddresses.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberAddressEvent saveAddressEvent = (SaveFamilyMemberAddressEvent) iter.next();
				Address address = createNewAddress(saveAddressEvent);
				member.insertAddresses(address);
			}
		}
		// contact info
		Collection familyMemberContacts =
			MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberContactEvent.class);
		if (familyMemberContacts != null && familyMemberContacts.size() > 0)
		{
			Iterator iter = familyMemberContacts.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberContactEvent saveContactEvent = (SaveFamilyMemberContactEvent) iter.next();
				if(saveContactEvent.isPhone()){
				FamilyMemberPhone memberPhone =
					//createMemberPhone(
					//	saveContactEvent.getPhoneNum(),
					//	saveContactEvent.getExtentionNum(),
					//	saveContactEvent.getPhoneTypeId(),
					//	memberId);
					createMemberPhone(saveContactEvent, memberId);
					//Defect fix: JIMS200077386 Check the null condition
					if(memberPhone!=null)
					{
						member.insertPhoneNumbers(memberPhone);
					}
				}
				else{
					FamilyMemberEmail memberEmail =
						createMemberEmail(
							saveContactEvent.getEmailAddress(),saveContactEvent, //bug fix #35677
							saveContactEvent.getEmailTypeId(),
							memberId);
					member.insertEmailAddresses(memberEmail);
				}
			}

		}
		// traits
		Collection familyMemberTraits = MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberTraitEvent.class);
		if (familyMemberTraits != null && familyMemberTraits.size() > 0)
		{
			Iterator iter = familyMemberTraits.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberTraitEvent saveTraitEvent = (SaveFamilyMemberTraitEvent) iter.next();
				FamilyMemberTrait trait = createFamilyMemberTrait(saveTraitEvent);
				member.insertTraits(trait);
			}

		}
		// employments
		Collection familyMemberEmployments =
			MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberEmploymentInfoEvent.class);
		if (familyMemberEmployments != null && familyMemberEmployments.size() > 0)
		{
			Iterator iter = familyMemberEmployments.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberEmploymentInfoEvent saveEmploymentEvent =
					(SaveFamilyMemberEmploymentInfoEvent) iter.next();
				FamilyMemberEmployment employment = createFamilyMemberEmployment(saveEmploymentEvent);
				member.insertEmployments(employment);
			}

		}
		// benefits
		Collection familyMemberBenefits =
			MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberBenefitsEvent.class);
		if (familyMemberBenefits != null && familyMemberBenefits.size() > 0)
		{
			Iterator iter = familyMemberBenefits.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberBenefitsEvent saveBenefitEvent = (SaveFamilyMemberBenefitsEvent) iter.next();
				FamilyMemberBenefit benefit = createFamilyMemberBenefit(saveBenefitEvent);
				member.insertBenefits(benefit);
			}

		}

		// insurance
		Collection familyMemberInsurances =
			MessageUtil.compositeToCollection(saveEvent, SaveFamilyMemberInsuranceEvent.class);
		if (familyMemberInsurances != null && familyMemberInsurances.size() > 0)
		{
			Iterator iter = familyMemberInsurances.iterator();
			while (iter.hasNext())
			{
				SaveFamilyMemberInsuranceEvent saveInsuranceEvent = (SaveFamilyMemberInsuranceEvent) iter.next();
				FamilyMemberInsurance insurance = createFamilyMemberInsurance(saveInsuranceEvent);
				member.insertInsurances(insurance);

			}

		}

	}

	/**
	 * @param saveInsuranceEvent
	 * @param memberId
	 */
	private static FamilyMemberInsurance createFamilyMemberInsurance(SaveFamilyMemberInsuranceEvent saveInsuranceEvent)
	{

		FamilyMemberInsurance insurance = new FamilyMemberInsurance();
		insurance.setCarrier(saveInsuranceEvent.getCarrier());
		insurance.setInsuranceTypeId(saveInsuranceEvent.getTypeId());
		insurance.setPolicyNum(saveInsuranceEvent.getPolicyNum());
		return insurance;

	}

	/**
	 * @param saveBenefitEvent
	 * @param memberId
	 */
	private static FamilyMemberBenefit createFamilyMemberBenefit(SaveFamilyMemberBenefitsEvent saveBenefitEvent)
	{
		FamilyMemberBenefit benefit = new FamilyMemberBenefit();
		benefit.setEligibilityTypeId(saveBenefitEvent.getEligibilityTypeId());
		benefit.setReceivingBenefits(saveBenefitEvent.isReceivingBenefits());
		benefit.setEligibleForBenefits(saveBenefitEvent.isElgibleForBenefits());
		benefit.setIdNumber(saveBenefitEvent.getIdNumber());
		benefit.setReceivedAmt(saveBenefitEvent.getReceivedAmt());
		benefit.setReceivedByFirstName(saveBenefitEvent.getReceivedBy().getFirstName());
		benefit.setReceivedByMiddleName(saveBenefitEvent.getReceivedBy().getMiddleName());
		benefit.setReceivedByLastName(saveBenefitEvent.getReceivedBy().getLastName());
		return benefit;

	}

	/**
	 * @param saveEmploymentEvent
	 * @param memberId
	 */
	private static FamilyMemberEmployment createFamilyMemberEmployment(SaveFamilyMemberEmploymentInfoEvent saveEmploymentEvent)
	{
		FamilyMemberEmployment employment = new FamilyMemberEmployment();
		//employment.setFamilyMemberId(memberId);

		employment.setEmploymentStatusId(saveEmploymentEvent.getStatusId());
		employment.setCurrentEmployer(saveEmploymentEvent.getCurrentEmployer());
		employment.setSalary(saveEmploymentEvent.getSalary());
		employment.setSalaryRateId(saveEmploymentEvent.getSalaryRateId());
		employment.setWorkHours(saveEmploymentEvent.getWorkHours());
		employment.setLenghtOfEmployment(saveEmploymentEvent.getLengthOfEmployment());
		employment.setJobTitle(saveEmploymentEvent.getJobTitle());
		employment.setAnnualNetIncome(saveEmploymentEvent.getAnnualNetIncome());
		return employment;

	}

	/**
	 * @param saveTraitEvent
	 * @param memberId
	 */
	private static FamilyMemberTrait createFamilyMemberTrait(SaveFamilyMemberTraitEvent saveTraitEvent)
	{
		FamilyMemberTrait trait = new FamilyMemberTrait();
		//trait.setFamilyMemberId(memberId);
		trait.setTypeId(saveTraitEvent.getTraitTypeId());
		trait.setLevelId(saveTraitEvent.getLevelId());
		trait.setComments(saveTraitEvent.getComments());
		trait.setStatusId(saveTraitEvent.getStatusId());
		return trait;
	}

	private static Address createNewAddress(SaveFamilyMemberAddressEvent memberAddress)
	{
		Address address = new Address();
		address.setStreetName(memberAddress.getStreetName());
		address.setStreetNum(memberAddress.getStreetNumber());
		address.setStreetNumSuffixId( memberAddress.getStreetNumSuffixId() );
		address.setStreetTypeId(memberAddress.getStreetTypeId());
		address.setAptNum(memberAddress.getAptNum());
		address.setAddressTypeId(memberAddress.getAddressTypeId());
		address.setStateId(memberAddress.getStateId());
		address.setCity(memberAddress.getCity());
		address.setZipCode(memberAddress.getZip());
		address.setCountyId(memberAddress.getCountyId());
		address.setAdditionalZipCode(memberAddress.getAdditionalZip());
		address.setValidated(memberAddress.getValidated()); 
		if (memberAddress.getValidated().equals("")) {
			address.setValidated("U");
		}
		return address;

	}

	/**
	 * @param benefit
	 * @return
	 */
	public static FamilyMemberBenefitResponseEvent getFamilyMemberBenefitResponseEvent(FamilyMemberBenefit benefit)
	{
		FamilyMemberBenefitResponseEvent benefitResponse = new FamilyMemberBenefitResponseEvent();
		benefitResponse.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_BENEFIT_TOPIC);
		benefitResponse.setEligibilityTypeId(benefit.getEligibilityTypeId());
		benefitResponse.setReceivingBenefits(benefit.isReceivingBenefits());
		benefitResponse.setElgibleForBenefits(benefit.isEligibleForBenefits());
		benefitResponse.setEntryDate(benefit.getCreateTimestamp());
		benefitResponse.setBenefitId(benefit.getOID().toString());
		benefitResponse.setReceivedAmt(benefit.getReceivedAmt());
		benefitResponse.setIdNumber(benefit.getIdNumber());
		benefitResponse.setReceivedBy(new Name(benefit.getReceivedByFirstName(), benefit.getReceivedByMiddleName(), benefit.getReceivedByLastName()));
		return benefitResponse;

	}

	/**
	 * @param benefit
	 * @return
	 */
	public static FamilyMemberTraitResponseEvent getFamilyMemberTraitResponseEvent(FamilyMemberTrait trait)
	{
		FamilyMemberTraitResponseEvent traitReply = new FamilyMemberTraitResponseEvent();
		traitReply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_TRAIT_TOPIC);
		traitReply.setTraitid(trait.getOID().toString());
		traitReply.setTraitTypeId(trait.getTypeId());
		traitReply.setLevelId(trait.getLevelId());
		traitReply.setComments(trait.getComments());
		traitReply.setStatusId(trait.getStatusId());
		traitReply.setEntryDate(trait.getCreateTimestamp());
		traitReply.setMemberId(trait.getFamilyMemberId());  //bug fix:32652 
		return traitReply;
	}

	/**
	 * @param employment
	 * @return
	 */
	public static FamilyMemberEmploymentInfoResponseEvent getFamilyMemberEmploymentInfoResponseEvent(FamilyMemberEmployment employment)
	{
		FamilyMemberEmploymentInfoResponseEvent employmentReply = new FamilyMemberEmploymentInfoResponseEvent();
		employmentReply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_EMPLOYMENT_TOPIC);
		employmentReply.setEmploymentId(employment.getOID().toString());
		employmentReply.setStatusId(employment.getEmploymentStatusId());
		employmentReply.setCurrentEmployer(employment.getCurrentEmployer());
		employmentReply.setSalary(employment.getSalary());
		employmentReply.setSalaryRateId(employment.getSalaryRateId());
		employmentReply.setWorkHours(employment.getWorkHours());
		employmentReply.setLengthOfEmployment(employment.getLenghtOfEmployment());
		employmentReply.setJobTitle(employment.getJobTitle());
		employmentReply.setEntryDate(employment.getCreateTimestamp());
		employmentReply.setAnnualNetIncome(employment.getAnnualNetIncome());
		return employmentReply;
	}

	/**
	 * @param insurance
	 * @return
	 */
	public static FamilyMemberInsuranceResponseEvent getFamilyMemberInsuranceResponseEvent(FamilyMemberInsurance insurance)
	{
		FamilyMemberInsuranceResponseEvent insuranceReply = new FamilyMemberInsuranceResponseEvent();
		insuranceReply.setTopic(PDJuvenileFamilyConstants.FAMILY_MEMBER_INSURANCE_TOPIC);
		insuranceReply.setInsuranceId(insurance.getOID().toString());
		insuranceReply.setCarrier(insurance.getCarrier());
		insuranceReply.setTypeId(insurance.getInsuranceTypeId());
		insuranceReply.setPolicyNum(insurance.getPolicyNum());
		insuranceReply.setEntryDate(insurance.getCreateTimestamp());
		return insuranceReply;
	}

	/**
	 * @param financial
	 * @return
	 */
	public static FamilyConstellationMemberFinancialResponseEvent getFamilyConstellationMemberFinancialResponseEvent(FamilyMemberFinancial financial)
	{
		FamilyConstellationMemberFinancialResponseEvent reply = new FamilyConstellationMemberFinancialResponseEvent();
		reply.setEntryDate(financial.getCreateTimestamp());
		reply.setSsi(financial.getSsi());
//		reply.setJobTitle(financial.getJobTitle());
//		reply.setPlaceOfEmpoyment(financial.getPlaceOfEmpoyment());
//		reply.setAnnualNetIncome(financial.getAnnualNetIncome());
		reply.setChildSupportPaid(financial.getChildSupportPaid());
		reply.setChildSupportReceived(financial.getChildSupportReceived());
		reply.setOtherAdultIncome(financial.getOtherAdultIncome());
		reply.setNotes(financial.getNotes());
		reply.setTanfAfdc(financial.getTanfAfdc());
		reply.setFoodStamps(financial.getFoodStamps());
		reply.setRentExpenses(financial.getRentExpenses());
		reply.setUtilitiesExpenses(financial.getUtilitiesExpenses());
		reply.setGroceryExpenses(financial.getGroceryExpenses());
		reply.setLifeInsurancePremium(financial.getLifeInsurancePremium());
		reply.setMedicalExpenses(financial.getMedicalExpenses());
		reply.setTotalExpenses(financial.getTotalExpenses());
		reply.setPropertyValue(financial.getPropertyValue());
		reply.setIntangibleValue(financial.getIntangibleValue());
		reply.setSavings(financial.getSavings());
		reply.setOtherIncome(financial.getOtherIncome());
		reply.setNumberLivingInHome(financial.getNumberLivingInHome());
		reply.setNumberOfDependents(financial.getNumberOfDependents());
		reply.setChildSupportPayorFirstName(financial.getChildSupportPayorFirstName());
		reply.setChildSupportPayorLastName(financial.getChildSupportPayorLastName());
		reply.setChildSupportPayorMiddleName(financial.getChildSupportPayorMiddleName());
		//reply.setConstellationId(financial.getc)
		reply.setConstellationMemberId(Integer.parseInt(financial.getFamilyConstellationMemberId()));
		reply.setConstellationMemberFinancialId(Integer.parseInt(financial.getOID().toString()));
		reply.setEntryDate(financial.getEntryDate());
		reply.setEntryDateTimestamp(financial.getEntryDateTimestamp());
		reply.setConstellationId(financial.getConsRelationId());
		reply.setNumberInFamily(financial.getFamilyNum());
		reply.setSchoolExpenses(financial.getSchoolExpenses());
		return reply;
	}

	/**
	 * @param string
	 * @param dispatch
	 */
	public static void processConstellationDetailResponse(String constellationNum, IDispatch dispatch)
	{
		if (constellationNum != null && constellationNum.trim().length() > 0)
		{
			processFamilyMemberListResponse(constellationNum, dispatch, true);
			processFamilyTraitsResponse(constellationNum, dispatch);

		}
	}

	/**
	 * @param fc
	 */
	public static void sendFamilyConstellationGuardianEvent(FamilyConstellation fc)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		FamilyConstellationGuardianResponseEvent resp = new FamilyConstellationGuardianResponseEvent();
		resp.setActive(fc.isActive());
		resp.setEntryDate(fc.getEntryDateTimestamp());
		resp.setFamilyNum(fc.getFamilyNum());
		resp.setFirstName(fc.getFirstName());
		resp.setGuardian(fc.isGuardian());
		resp.setJuvenileId(fc.getJuvenileId());
		resp.setJuvRelation(fc.getJuvRelation());
		resp.setLastName(fc.getLastName());
		resp.setMemberNum(fc.getMemberNum());
		resp.setMiddleName(fc.getMiddleName());
		resp.setFinancialId(fc.getFinancialId());
		resp.setTopic(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_TOPIC);
		dispatch.postEvent(resp);
	}
	
	/**
	 * @param getFamilyConstellationGuardianFinancialEvent
	 */
	public static void processFamilyConstellationGuardianFinancial(
				GetFamilyConstellationGuardianFinancialEvent getFamilyConstellationGuardianFinancialEvent)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
		Iterator financialIter = FamilyMemberFinancial.findAll(getFamilyConstellationGuardianFinancialEvent);
	
		if (financialIter.hasNext())
		{
			FamilyMemberFinancial financial = (FamilyMemberFinancial) financialIter.next();
			FamilyConstellationMemberFinancialResponseEvent financialEvent =
				getFamilyConstellationMemberFinancialResponseEvent(financial);
			financialEvent.setTopic(
		   		PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_GUARDIAN_FINANCIAL_TOPIC);
			dispatch.postEvent(financialEvent);
		}
	}
	
	/**
	 * Social History Report - Financial History
	 */
	public static List buildFinancialHistory( Juvenile juvenile )
	{
		List list = new ArrayList();
		
		FamilyConstellation constellation = juvenile.getCurrentFamilyConstellation();
		if(constellation != null)
		{
			Collection coll = constellation.getFamilyConstellationMembers();
			if(coll != null)
			{
				Iterator members = coll.iterator();
				
				//only show employment information record of 1 guardian 
				//if both guardians are living in-home with the juvenile
				boolean prevGuardiansInHome = false;
				
				while ( members.hasNext() )
				{
					FamilyConstellationMember constMember = (FamilyConstellationMember)members.next();
					FamilyMember famMember = constMember.getTheFamilyMember();
		
					if ( constMember.isGuardian() )
					{
						FamilyInformationTO to = new FamilyInformationTO();
						list.add( to );
						
						// Guardian
						to.setGuardian( (new Name( famMember.getFirstName(), famMember.getMiddleName(), famMember.getLastName()) ).getFormattedName() );
							
						// Relationship
						to.setRelationship( constMember.getRelationshipToJuvenile().getDescription() );
						
						// Address - get latest Residential address
						Address latestAddress = null;
						Iterator addresses = famMember.getAddresses().iterator();
						while ( addresses.hasNext() )
						{
							Address address = (Address)addresses.next();
							if ( "RES".equals(address.getAddressTypeId()))
							{
								if(address.getCreateDate() != null ||
										(latestAddress == null &&  
										latestAddress.getCreateDate().compareTo(address.getCreateDate()) < 0 ) )
								{
									latestAddress = address;
								}
							}
						}
						if ( latestAddress != null )
						{
							to.setAddress( InterviewHelper.formatAddress(latestAddress) );
						}
						
						// Phone - get latest Home phone
						FamilyMemberPhone latestPhone = null;
						Iterator phones = famMember.getPhoneNumbers().iterator();
						while ( phones.hasNext() )
						{
							FamilyMemberPhone fmPhone = (FamilyMemberPhone)phones.next();
							if ( "HM".equals(fmPhone.getPhoneTypeId()))
							{
								if(fmPhone.getCreateTimestamp() != null || 
										(latestPhone != null && latestPhone.getCreateTimestamp() != null && 
										latestPhone.getCreateTimestamp().compareTo(fmPhone.getCreateTimestamp()) < 0 ))
								{
									latestPhone = fmPhone;
								}
							}
							
						}
						if ( latestPhone != null )
						{
							Phone phone = latestPhone.getPhoneMaster();
							to.setPhone( phone.getPhoneNumber() );
						}
		
						// In Home
						to.setInHome( constMember.isInHomeStatus() );
						
						// Financial - financials are bases on latest record.
						FamilyMemberFinancial latestFin = null;
						Iterator finIter = constMember.getConstellationMemberFinancials().iterator();
						while ( finIter.hasNext() )
						{
							FamilyMemberFinancial fin = (FamilyMemberFinancial)finIter.next();
							if ( latestFin == null || latestFin.getEntryDate().compareTo(fin.getEntryDate()) < 0 )
							{
								latestFin = fin;
							}
						}
						
						if ( latestFin != null )
						{
							to.setTANFAssistance( latestFin.getTanfAfdc() );
							to.setOtherIncome( latestFin.getOtherIncome() );
							
							to.setRentExpenses( latestFin.getRentExpenses() );
							to.setUtilitiesExpenses( latestFin.getUtilitiesExpenses() );
							to.setGroceryExpenses( latestFin.getGroceryExpenses() );
							to.setSchoolExpenses( latestFin.getSchoolExpenses() );
							to.setChildSupportPaid( latestFin.getChildSupportPaid() );
							to.setMedicalExpenses( latestFin.getMedicalExpenses() );
							to.setLifeInsurancePremium( latestFin.getLifeInsurancePremium() );
							to.setPropertyValue( latestFin.getPropertyValue() );
							to.setIntangibleValue( latestFin.getIntangibleValue() );
							to.setSavings( latestFin.getSavings() );
							to.setChildSupportReceived( latestFin.getChildSupportReceived() );
							
							//TODO Fix food stamps when type is changed to double. 
							to.setFoodStamps( latestFin.getFoodStamps() );
							
							to.setNumberLivingInHome( latestFin.getNumberLivingInHome() );
						}
						
						//When both guardians are in home, only show the first guardian's
						//employment info & income info
						if(!(prevGuardiansInHome && constMember.isInHomeStatus()))
						{
							// Employment
							FamilyMemberEmployment latestEmp = null;
							Iterator empIter = famMember.getEmployments().iterator();
							while ( empIter.hasNext() )
							{
								FamilyMemberEmployment emp = (FamilyMemberEmployment)empIter.next();
								
								// Save the latest employment record for income calculations 
								if ( latestEmp == null || latestEmp.getCreateTimestamp().compareTo(emp.getCreateTimestamp()) < 0 )
								{
									latestEmp = emp;
								}
								
								EmploymentHistoryTO empTO = new EmploymentHistoryTO();
								to.getEmploymentHistory().add( empTO );
								
								empTO.setExcluded( true );
								empTO.setOID( emp.getOID().toString() );
								empTO.setPlaceEmployed( emp.getCurrentEmployer() );
								empTO.setWorkHours( Double.toString(emp.getWorkHours()) );
								
								if ( emp.getEmploymentStatus() != null )
									empTO.setEmploymentStatus( emp.getEmploymentStatus().getDescription() );
								
								empTO.setSupervisorName( "" );
								empTO.setJobDescription( emp.getJobTitle() );
								empTO.setEntryDate( emp.getCreateTimestamp() );
								double annualGrossIncome = emp.getAnnualNetIncome();
								if(annualGrossIncome == 0) {
									annualGrossIncome = calculateAnnualIncome( emp.getSalary(), emp.getSalaryRateId(), emp.getWorkHours() );
								}
								empTO.setAnnualGrossIncome( annualGrossIncome );
							}
							if(latestEmp!=null)
								to.setAnnualNetIncome( latestEmp.getAnnualNetIncome() );
							// Sort employment records newest to oldest 
							Collections.sort( to.getEmploymentHistory() );						
						}
						// income calculations
						
						///double grossIncome = calculateAnnualIncome( latestEmp.getSalary(), latestEmp.getSalaryRateId(), latestEmp.getWorkHours() );
						double grossIncome = 0;
						grossIncome += to.getTANFAssistance() * 12; 
						grossIncome += to.getOtherIncome();	
						to.setTotalGross( grossIncome );		
						prevGuardiansInHome = constMember.isInHomeStatus();
		
					}
				}
			}
		}
			
		return list;
	}
	
	/**
	 * @param salaryStr
	 * @param salaryRateId
	 * @param workHoursStr
	 * @return
	 */
	public static double calculateAnnualIncome( double salary, String salaryRateId, double workHours )
	{
		try
		{
			if ( "HR".equals(salaryRateId) )
			{
				return salary * workHours * 52;
			}
			else if ( "WK".equals(salaryRateId) )
			{
				return salary * 52;
			}
			else if ( "BW".equals(salaryRateId) )
			{
				return salary * 26;
			}
			else if ( "MO".equals(salaryRateId) )
			{
				return salary * 12;
			}
			else if ( "YR".equals(salaryRateId) )
			{
				return salary;
			}
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		return 0;
	}

	public static JuvenileFamilyForm.MemberList getPrimaryGuardian(Collection memberList)
	{
		
		//check if there is a guardian with primary indicator
		Iterator iter =  memberList.iterator();
		List unsorted = new ArrayList();
		while(iter.hasNext()) {
			JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
			if(myMember.isGuardian() && myMember.isPrimaryContact())
				return myMember;
			if(myMember.isGuardian())
				unsorted.add(myMember);
		}
		
		//there is no primary guardian so get the most recent guardian - sort the collection by createdate
		
		ArrayList sortedList = new ArrayList();
		sortedList.add(new ReverseComparator(new BeanComparator("confirmedDate")));
		ComparatorChain chain = new ComparatorChain(sortedList);
		Collections.sort(unsorted, chain);
		
		
		return (JuvenileFamilyForm.MemberList)unsorted.get(0);
	}
	
	 /** copied this method from SaveJuvenileProfileMainCommand //made changes 10/11/2018
	     * @param firstName
	     * @param middleName
	     * @param lastName
	     * @param memberAddress
	     * @param memberPhoneNum
	     * @param ssn
	     * @return
	     */
	    public static FamilyMember getFamilyMember(JuvenileReferralMemberDetailsBean memberBean, Address memberAddress, PhoneNumber contactPhoneNumber) throws RuntimeException, Exception
	    {
		if (memberBean != null)
		{
		    String firstName = memberBean.getFirstName();
		    String middleName = memberBean.getMiddleName();
		    String lastName = memberBean.getLastName();
		    String ssn = memberBean.getSSN().getSSN();

		    FamilyMember member = null;
		    if ((firstName != null && firstName.trim().length() > 0) || (middleName != null && middleName.trim().length() > 0) || (lastName != null && lastName.trim().length() > 0))
		    {
			//TODO need to check if member exists
			member = new FamilyMember();
			member.setFirstName(firstName);
			member.setLastName(lastName);
			member.setMiddleName(middleName);
			if (ssn == null || ssn.trim().equals(""))
			{
			    ssn = "666666666";
			}
			member.setSsn(ssn);
			//member.setCreateUserID(PDJuvenileCaseConstants.CASEFILE_CREATOR);
			member.setCreateUserID(PDSecurityHelper.getLogonId()); //89637
			// create address
			if (memberAddress != null && !memberAddress.getStreetName().equals(""))
			{
			    Address address = null;
			    // check if address exists 
			    AddressValidationEvent requestEvent = new AddressValidationEvent();
				requestEvent.setStreetNumStr(memberAddress.getStreetNum());
				requestEvent.setStreetName(memberAddress.getStreetName());
				if (memberAddress.getZipCode() != null && memberAddress.getZipCode().equals("") == false) {
					requestEvent.setZipCodeStr(memberAddress.getZipCode());
				}

			    Iterator iter = Address.findAll(requestEvent);//goes to GLADDRESS checks for ZipCode+STREETNAME+STREETNUM match
			    //Iterator<Address> iter = Address.findAll("streetName", memberAddress.getStreetName());
			    if (iter.hasNext())
			    {
				address = (Address) iter.next();
			    }
			    else
			    {
				address = memberAddress;
			    }
			    member.insertAddresses(address);//there is an insert into jcfammember + looks into JCMEMADDRESS with FAMMEMBER_ID match ~NM (
			}

			FamilyMemberPhone memberPhone = null;
			if (contactPhoneNumber != null && contactPhoneNumber.getPhoneNumber() != null && contactPhoneNumber.getPhoneNumber().trim().length() > 0)
			{
			    memberPhone = JuvenileFamilyHelper.createMemberPhone(contactPhoneNumber.getPhoneNumber(), contactPhoneNumber.getExt(), memberBean.getPhoneType(), (String) member.getOID());
			}

			//add phonenum to member
			if (memberPhone != null)
			{
			    member.insertPhoneNumbers(memberPhone);
			}

			if (member.getOID() == null)
			{
			    member.setIncarcerated(memberBean.isIncarcerated());
			    member.setDeceased(memberBean.isDeceased());
			   // IHome home = new Home(); //NM
			    //home.bind(member); //NMS
			}
		    }
		    return member;
		}
		return null;
	    }

}

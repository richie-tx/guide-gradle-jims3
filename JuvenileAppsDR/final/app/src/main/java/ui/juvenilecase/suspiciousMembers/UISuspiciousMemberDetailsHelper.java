package ui.juvenilecase.suspiciousMembers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.contact.to.NameBean;
import messaging.contact.to.SocialSecurityBean;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberMartialStatusResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.suspiciousMembers.form.SuspiciousMemberForm;

public class UISuspiciousMemberDetailsHelper {

	/**
	 */
	public static void loadMemberDetails(String memberNum, SuspiciousMemberForm smForm)
	{
        GetFamilyMemberDetailsEvent event = 
        	(GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
        event.setMemberNum(memberNum);
        CompositeResponse response = MessageUtil.postRequest(event);

        FamilyMemberDetailResponseEvent resp =
        	(FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(response, FamilyMemberDetailResponseEvent.class);

        List maritalStatusList = MessageUtil.compositeToList(response, FamilyMemberMartialStatusResponseEvent.class);
        List assocJuvsList = MessageUtil.compositeToList(response, AssociatedJuvenilesResponseEvent.class);
// load family member display info
		NameBean nameBean = new NameBean(resp.getFirstName(), resp.getMiddleName(), resp.getLastName());
		smForm.setMemberName(nameBean.getFormattedName());
		smForm.setMemberFirstName(resp.getFirstName());
		smForm.setMemberLastName(resp.getLastName());
		smForm.setMemberNumber(memberNum);
		if (resp.getSsn() != null && resp.getSsn().indexOf("-") == -1)
		{
			SocialSecurityBean ssn = new SocialSecurityBean( resp.getSsn());
			smForm.setMemberSSN( ssn.getFormattedSsn() );
		} else {
			smForm.setMemberSSN( resp.getSsn() );
		}
		smForm.setMemberDOB( DateUtil.dateToString(resp.getDateOfBirth(), DateUtil.DATE_FMT_1) );
		smForm.setSexLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.SEX, resp.getSexId()));
		smForm.setSidNum( resp.getSidNum() );
		smForm.setAlienRegNumber(resp.getAlienRegistrationNum());
		smForm.setUsCitizenId(UIConstants.NO_FULL_TEXT);
		if ("Y".equalsIgnoreCase(resp.getIsUSCitizenId()))
		{
			smForm.setUsCitizenId(UIConstants.YES_FULL_TEXT);
		}
		smForm.setNationalityLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PLACE_OF_BIRTH, resp.getNationalityId() ) );
		smForm.setEthnicityLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, resp.getEthnicityId() ) );
		smForm.setPrimaryLanguageLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE, resp.getPrimarylanguageId() ) );
		smForm.setSecondaryLanguageLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.LANGUAGE, resp.getSecondaryLanguageId() ) );

		smForm.setDeceasedLit(UIConstants.NO_FULL_TEXT);
		if (resp.isDeceasedInd() == true)
		{
			smForm.setDeceasedLit(UIConstants.YES_FULL_TEXT);
		}
		smForm.setCauseOfDeathLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.CAUSE_OF_DEATH, resp.getCauseOfDeathId() ) );
//		smForm.setJuvenileAgeAtDeath( resp.getJuvenileAgeAtDeath() );
		smForm.setIncarcetatedLit(UIConstants.NO_FULL_TEXT);
		if (resp.isIncarcerated() == true)
		{
			smForm.setIncarcetatedLit(UIConstants.YES_FULL_TEXT);
		} 
		smForm.setComments( resp.getComments() );
		
// load driver license/Id card display info
		smForm.setDlNumber( resp.getDriverLicenceNumber() );
		smForm.setDlStateLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, resp.getDriverLicenceStateId() ) );
		smForm.setDlExpDateStr( DateUtil.dateToString(resp.getDriverLicenceExpiryDate(),DateUtil.DATE_FMT_1 ) );
		smForm.setDlClassId(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.DRIVERS_LICENSE_CLASS, resp.getDriverLicenceClassId() ) );
		smForm.setStateIssuedCardNumber( resp.getIdCardNum() );
		smForm.setStateIssuedCardStateLit(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR,resp.getIdCardStateId() ));
		
// load Associated list
		smForm.setAssociatedJuvenilesList(assocJuvsList);
// load Marital status info	
		ArrayList workList = new ArrayList();
		if( maritalStatusList != null )
		{
			for (int x=0; x<maritalStatusList.size(); x++)	
			{
				FamilyMemberMartialStatusResponseEvent event2 = (FamilyMemberMartialStatusResponseEvent) maritalStatusList.get(x);
				SuspiciousMemberForm.MaritalList maritalObj = new SuspiciousMemberForm.MaritalList();
				maritalObj.setMaritalId( event2.getMartialId() );
				maritalObj.setMaritalStatusId( event2.getMartialStatusId() );
				maritalObj.setMaritalStatus(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.MARITAL_STATUS, event2.getMartialStatusId() ) );
				maritalObj.setMarriageDate(DateUtil.dateToString(event2.getMarriageDate(), DateUtil.DATE_FMT_1 ) );
				maritalObj.setDivorceDate(DateUtil.dateToString(event2.getDivorceDate(), DateUtil.DATE_FMT_1 ) );
				maritalObj.setNumOfChildren( event2.getNumberOfChildren() );
				maritalObj.setEntryDate(DateUtil.dateToString(event2.getEntryDate(), DateUtil.DATE_FMT_1) );
					
				Name myRelName = new Name();
				maritalObj.setRelatedFamMemName( myRelName );
				if (event2.getRelatedFamMemId() != null && event2.getRelatedFamMemId().trim().length() > 0) 
				{
					maritalObj.setRelatedFamMemId( event2.getRelatedFamMemId() );
					myRelName.setFirstName( event2.getRelatedFamMemFirstName() );
					myRelName.setMiddleName( event2.getRelatedFamMemMiddleName() );
					myRelName.setLastName( event2.getRelatedFamMemLastName() );
				}
				else
				{
					maritalObj.setRelatedFamMemId( UIConstants.EMPTY_STRING );
				}
				workList.add( maritalObj );
			} // for
		}	
		smForm.setMaritalStatusDetailsList( sortMemberMarriageList( workList ) );
		
		smForm.setAddress(  UIJuvenileHelper.getLatestFamilyMemberAddress(memberNum).getFormattedAddress() );
		smForm.setPhone( UIJuvenileHelper.getFamilyMemberPhone(memberNum).getContactPhoneNumber().getFormattedPhoneNumber());
		smForm.setEmail( UIJuvenileHelper.getFamilyMemberEmail(memberNum).getEmailAddress());
		    
		
	}
		
	/*
	 * @param aNeedToSortList
	 * @return
	 */
		public static List sortMemberMarriageList( List aNeedToSortList )
				{
					if( aNeedToSortList == null || aNeedToSortList.size() < 2 )
					{
						return( aNeedToSortList );
					}
					Collections.sort( aNeedToSortList );
		
					return( aNeedToSortList );
		}	
		
		public static List filterJuvenilesByMemberNumber(String member1, String member2, List <AssociatedJuvenilesResponseEvent> associatedJuveniles){
			List filteredList = new ArrayList();
			AssociatedJuvenilesResponseEvent ajre = null;
			if (member2 == null){
				member2 = UIConstants.EMPTY_STRING;
			}
			
			for (int i = 0; i < associatedJuveniles.size(); i++) {
				ajre = associatedJuveniles.get(i);
				if (ajre.getFamMemberId().equals(member1)
						|| ajre.getFamMemberId().equals(member2)){
					filteredList.add(ajre);
				}
			}
			ajre = null;
			
			return filteredList;
		}
		
		public static List eliminateDuplicateJuveniles(List <AssociatedJuvenilesResponseEvent> associatedJuveniles){
			
			List filteredList = new ArrayList();
			AssociatedJuvenilesResponseEvent ajre = null;
			Map juvMap = new HashMap();
			
			for (int i = 0; i < associatedJuveniles.size(); i++) {
				ajre = associatedJuveniles.get(i);
				if (juvMap.get(ajre.getJuvId()) == null){
					filteredList.add(ajre);
					juvMap.put(ajre.getJuvId(), ajre.getJuvId());
				}
			}
			ajre = null;
			juvMap = null;
			
			return filteredList;
			
		}

		public static List eliminateDuplicateFamilyMembers(List <FamilyMemberListResponseEvent> familyMembers){
			
			List filteredList = new ArrayList();
			FamilyMemberListResponseEvent fmlre = null;
			Map famMap = new HashMap();
			
			for (int i = 0; i < familyMembers.size(); i++) {
				fmlre = familyMembers.get(i);
			     if(isValidSsn(fmlre.getSSN()))
			     {
				 if (famMap.get(fmlre.getMemberNum()) == null){
					filteredList.add(fmlre);
					famMap.put(fmlre.getMemberNum(), fmlre.getMemberNum());
				} 
			     }
				
			}
			fmlre = null;
			famMap = null;
			
			return filteredList;
			
		}
		
		public static boolean isValidSsn(String ssn){
		    return !(("666666666").equals(ssn) || ("777777777").equals(ssn) || ("888888888").equals(ssn) || ("999999999").equals(ssn));
			    
		}
	}
	

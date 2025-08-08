package ui.juvenilecase.districtCourtHearings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAllJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileFacilityByCodeEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileHearingTypeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.districtCourtHearings.UpdateCTSServiceRecordEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenile.GetJuvenileInfoLightEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import messaging.juvenile.UpdateJuvenileServiceCTSInfoEvent;
import messaging.juvenile.UpdateJuvenileServiceEvent;
import messaging.juvenile.UpdateTrackingControlNumberEvent;
import messaging.juvenile.reply.JuvenileCoreLightResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenile.reply.TrackingControlNumberResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.JPOofRecSupervisionTypeCategoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import pd.codetable.Code;
import pd.codetable.SimpleCodeTableHelper;
import pd.codetable.criminal.JuvenileDates;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.juvenilecase.Assignment;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefile;
import pd.juvenilecase.SupervisionTypeMap;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.referral.JJSReferral;
import pd.security.PDSecurityHelper;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.util.NumberSuffixUtil;

public class JuvenileDistrictCourtHelper {
    
	
	/**
	 * buildDocketResponseEvent
	 * @param court
	 * @return DocketEventResponseEvent
	 */
	public static DocketEventResponseEvent buildDocketResponseEvent(JJSCourt court){
		DocketEventResponseEvent resp = new DocketEventResponseEvent();
		resp.setDocketEventId(court.getOID());
		resp.setEventDate(court.getCourtDate());
		resp.setStartDatetime(court.getCourtDate());
		resp.setEndDatetime(court.getCourtDate());
		resp.setCourtTime(court.getCourtTime());
		resp.setAttorneyName(court.getAttorneyName());
		resp.setPetitionNumber(court.getPetitionNumber());
		resp.setAllegation(court.getPetitionAllegation());
		resp.setAllegationDesc(court.getPetitionAllegationDesc());
		resp.setPetitionStatus(court.getPetitionStatus());
		resp.setPetitionAmendment(court.getPetitionAmendment());
		resp.setCourt(court.getCourtId());
		resp.setCourtResult(court.getHearingResult());
		resp.setOldcourtResult(court.getHearingResult());
		resp.setCourtResultDesc(court.getHearingResultDesc());
		resp.setDisposition(court.getHearingDisposition());
		resp.setDispositionDesc(court.getHearingDispositionDesc());
		resp.setHearingType(court.getHearingType());
		resp.setHearingTypeDesc(court.getHearingTypeDesc());
		resp.setDocketType("Court");
		resp.setLastCourtDate(DateUtil.dateToString(court.getCourtDate(),DateUtil.DATE_FMT_1));
		if(court.getJuvenile()!=null){
			Name name = new Name(court.getJuvenile().getFirstName(), court.getJuvenile().getMiddleName(), court.getJuvenile().getLastName());
			resp.setJuvenileName(name.getFormattedName());
		}
		resp.setAge(String.valueOf(court.getJuvenile().getAgeInYears(court.getJuvenile().getDateOfBirth())));
		resp.setRace(court.getJuvenile().getRaceCodeDescription());
		resp.setGender(court.getJuvenile().getSexCodeDescription());
		resp.setJuvenileNumber(court.getJuvenileNumber());
		resp.setIssueFlag(court.getIssueFlag());

		return resp;
	}
	
	public static boolean isReadyForCourtAction(CourtHearingForm courtHearingForm){
		
		boolean isCourtActionReady = false;
		
		if(courtHearingForm != null){
		    
		    if(courtHearingForm.getPetitionStatus() != null && !"".equals(courtHearingForm.getPetitionStatus())  
			    && courtHearingForm.getPetitionAllegation() != null && !"".equals(courtHearingForm.getPetitionAllegation()) 
				    && courtHearingForm.getPetitionNumber() != null && !"".equals(courtHearingForm.getPetitionNumber())
				    && courtHearingForm.getCourtDate() != null && !"".equals(courtHearingForm.getCourtDate())
				    && courtHearingForm.getHearingType() != null && !"".equals(courtHearingForm.getHearingType())
				    && courtHearingForm.getCourtId() != null && !"".equals(courtHearingForm.getCourtId())){
			
			isCourtActionReady = true;
		    }
		    
		}
		
		return isCourtActionReady;
	}
	
	/**
	 * setJuvenileFamilyDetails
	 * @param juvenileNum
	 * @param form
	 * @return Collection<FamilyConstellationListResponseEvent>
	 */
    public static Collection<FamilyConstellationListResponseEvent> setJuvenileFamilyDetails(String juvenileNum,CourtHearingForm form)
    {
	Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(juvenileNum);
	
	if (!constellationList.isEmpty())
	{
	    /*
	     * Only 1 active constellation at a time, therefore it's safe to get
	     * the first in the collection
	     */
	    FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();

	    GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
	    getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
	    CompositeResponse replyEvent = MessageUtil.postRequest(getConstellationDetails);
	    Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
	    boolean primaryContactFound=false;
	    
	   boolean isFatherGuardian=false;
	    boolean isMotherGuardian=false;
	    boolean isOtherGuardian=false;
	    // create a list of guardian(s) for display and residential
	    // information - address and phone number
	    if (familyMembers.size() > 0)
	    {
		for (FamilyConstellationMemberListResponseEvent member : familyMembers)
		{
		    GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);

		    getMemberDetails.setMemberNum(member.getMemberNum());
		    replyEvent = MessageUtil.postRequest(getMemberDetails);
		    FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);
		    
		    
		    if (form.getFather()==null && member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER)){
			    form.setFather(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName())) ;
			    Address fatherAddress = UIJuvenileHelper.getFamilyMemberAddress(memberDetail.getMemberId());
			    MemberContact memberContact= UIJuvenileHelper.getFamilyMemberPhone(memberDetail.getMemberId());
			    if (member.isGuardian() && !primaryContactFound){
				isFatherGuardian=true;
				if( member.isPrimaryContact()){
				    form.setMemberAddress(fatherAddress);
				    form.setMemberContact(memberContact);
				    primaryContactFound= true;
				}else if(member.isInHomeStatus()){
				    form.setMemberAddress(fatherAddress);
				    form.setMemberContact(memberContact);
				}
			    }
			    form.setFatherAddress(fatherAddress);
			    form.setFatherContact(UIJuvenileHelper.getFamilyMemberPhone(memberDetail.getMemberId()));
			//US 67132 changes starts
			    if (member.isIncarcerated())
			    {
				form.setIsFatherIncarcerated("Y");
			    }
			    else
			    {
				form.setIsFatherIncarcerated("N");
			    }
			    if (member.isDeceased())
			    {
				form.setIsFatherDeceased("Y");
			    }
			    else
			    {
				form.setIsFatherDeceased("N");
			    }//US 67132 changes ends
			}
		    //}
		    if (form.getMother()==null && member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)){
			    form.setMother(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
			    Address motherAddress = UIJuvenileHelper.getFamilyMemberAddress(memberDetail.getMemberId());
			    MemberContact memberContact= UIJuvenileHelper.getFamilyMemberPhone(memberDetail.getMemberId());
			    if (member.isGuardian() && !primaryContactFound){
				isMotherGuardian=true;
				if( member.isPrimaryContact()){
				    form.setMemberAddress(motherAddress);
				    form.setMemberContact(memberContact);
				    primaryContactFound= true;
				}else if(member.isInHomeStatus()){
				    form.setMemberAddress(motherAddress);
				    form.setMemberContact(memberContact);
				}			    }
			    form.setMotherAddress(motherAddress);
			    form.setMotherContact(memberContact);
			  //US 67132 changes starts
			/*if (member.isIncarcerated() && member.isDeceased())
			{
			    form.setIsMotherIncarcerated("B");
			}
			else
			{*/
			    if (member.isIncarcerated())
			    {
				form.setIsMotherIncarcerated("Y");
			    }
			    else
			    {
				form.setIsMotherIncarcerated("N");
			    }
			    if (member.isDeceased())
			    {
				form.setIsMotherDeceased("Y");
			    }
			    else
			    {
				form.setIsMotherDeceased("N");
			    }//US 67132 changes ends
			}
		  //  }
		     //member is a guardian.
		    if (member.isGuardian() && !member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)&&!member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER))
		    {
			isOtherGuardian=true;
			String rel = member.getRelationToJuvenileId();
			if (notNullNotEmptyString(rel))
			{
			    form.setGuardian(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName())) ;
			    form.setRelationship(member.getRelationToJuvenile());
			    
			    Address familyAddress = UIJuvenileHelper.getFamilyMemberAddress(member.getMemberNum());
			    form.setGuardianAddress(familyAddress);
			    
			    MemberContact familyPhone = UIJuvenileHelper.getFamilyMemberPhone(member.getMemberNum());
			    form.setGuardianContact(familyPhone);
			    if(!primaryContactFound){
        			    if(member.isPrimaryContact()){
        				form.setMemberAddress(familyAddress);
        				form.setMemberContact(familyPhone);
        				 primaryContactFound= true;
        			    }else if(member.isInHomeStatus()){
        				form.setMemberAddress(familyAddress);
        				form.setMemberContact(familyPhone);
        			    }
			    }
			}
		    } // if guardian
		    else{
			if (member.getRelationToJuvenileId()!=null && !member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)&&!member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER)){
			    if(member.isPrimaryCareGiver() && !member.isGuardian()){
			         form.setCareGiver(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName())) ;
			         form.setRelationship(member.getRelationToJuvenile());
			         
			         Address careGiverAddr = UIJuvenileHelper.getFamilyMemberAddress(member.getMemberNum());
			         MemberContact  familyPhone = UIJuvenileHelper.getFamilyMemberPhone(member.getMemberNum());
			         if(!primaryContactFound){
        			         if(member.isPrimaryContact()){
        			             primaryContactFound= true;
        			             form.setMemberAddress(careGiverAddr);
        			             form.setMemberContact(familyPhone);
        			         }else if(member.isInHomeStatus()){
        			             form.setMemberAddress(careGiverAddr);
        			             form.setMemberContact(familyPhone);
        			         }
			         }
			         form.setCareGiverAddress(careGiverAddr);
				 form.setCareGiverContact(familyPhone);
			      }
			}
		    }
		} // for - familyMembers
		  // if no in-home status or primary guardian and one or more guardian.
		if (form.getMemberAddress() ==null)
		{
		    if (isFatherGuardian && isMotherGuardian)
		    { //father && mother
			//go by create date
			if (form.getFatherAddress() != null && form.getMotherAddress() != null && form.getFatherAddress().getCreateDate() != null && form.getMotherAddress().getCreateDate() != null)
			{
			    if (DateUtil.compare(form.getFatherAddress().getCreateDate(), form.getMotherAddress().getCreateDate(), DateUtil.DATE_FMT_1) > 0)
			    {
				form.setMemberAddress(form.getFatherAddress());
				form.setMemberContact(form.getFatherContact());
			    } else
			    {
				form.setMemberAddress(form.getMotherAddress());
				form.setMemberContact(form.getMotherContact());
			    }
			}

		    } else if (isMotherGuardian && isOtherGuardian)
		    { //mother and other
			//go by create date
			if (form.getGuardianAddress() != null && form.getMotherAddress() != null && form.getGuardianAddress().getCreateDate() != null && form.getMotherAddress().getCreateDate() != null)
			{
			    if (DateUtil.compare(form.getGuardianAddress().getCreateDate(), form.getMotherAddress().getCreateDate(), DateUtil.DATE_FMT_1) > 0)
			    {
				form.setMemberAddress(form.getGuardianAddress());
				form.setMemberContact(form.getGuardianContact());
			    } else
			    {
				form.setMemberAddress(form.getMotherAddress());
				form.setMemberContact(form.getMotherContact());
			    }
			}
		    } else if (isFatherGuardian && isOtherGuardian)
		    { //father &&other
			//go by create date
			if (form.getGuardianAddress() != null && form.getFatherAddress() != null && form.getGuardianAddress().getCreateDate() != null && form.getFatherAddress().getCreateDate() != null)
			{
			    if (DateUtil.compare(form.getGuardianAddress().getCreateDate(), form.getFatherAddress().getCreateDate(), DateUtil.DATE_FMT_1) > 0)
			    {
				form.setMemberAddress(form.getGuardianAddress());
				form.setMemberContact(form.getGuardianContact());
			    } else
			    {
				form.setMemberAddress(form.getFatherAddress());
				form.setMemberContact(form.getFatherContact());
			    }
			}
		    } else if (isMotherGuardian)
		    { //no inhome status
			//moms address
			form.setMemberAddress(form.getMotherAddress());
			form.setMemberContact(form.getMotherContact());
		    } else if (isFatherGuardian)
		    { //no infome status
			//father address
			form.setMemberAddress(form.getFatherAddress());
			form.setMemberContact(form.getFatherContact());
		    } else if (isOtherGuardian)
		    {
			//other address
			form.setMemberAddress(form.getGuardianAddress());
			form.setMemberContact(form.getGuardianContact());
		    }
		} // no memaddress populated check.
	    }
	}
	return constellationList;
    }
    
    /**
     * juvenileHolidays
     * @return
     */
    public static List<JSONObject> juvenileHolidays(){
	List<JSONObject> juvenileDatesFormattedList = new ArrayList<JSONObject>();
	Iterator<JuvenileDates> juvenileDatesItr = JuvenileDates.findAll();
	while (juvenileDatesItr.hasNext())
	{
	    JSONObject json = new JSONObject();
	    JuvenileDates date = juvenileDatesItr.next();
	    if ( !"Y".equalsIgnoreCase( date.getInactiveInd()) )
	    {
		json.put("description", date.getDescription());
		json.put("resetDate", date.getResetDate());
		json.put("code", date.getCode());
		juvenileDatesFormattedList.add(json);
	    }
	}
	return juvenileDatesFormattedList;
    }
    
    /**
     * courtDecisions for detention records.
     * 
     * @return  List<JSONObject>
     */
    public static List<JSONObject> courtDecisionResponses(List<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsRespEventList)
    {
	List<JSONObject> courtDecisionsJsonList = new ArrayList<JSONObject>();
	Iterator<JuvenileCourtDecisionCodeResponseEvent> courtDecisionsItr = crtDecisionsRespEventList.iterator();
	while (courtDecisionsItr.hasNext())
	{
	    JSONObject json = new JSONObject();
	    JuvenileCourtDecisionCodeResponseEvent courtDecisionResp = courtDecisionsItr.next();
	    if (courtDecisionResp != null)
	    {
		json.put("code", courtDecisionResp.getCode());
		json.put("description", courtDecisionResp.getDescription());
		json.put("decision", courtDecisionResp.getDecision());
		json.put("action", courtDecisionResp.getAction());
		json.put("resetAllowed", courtDecisionResp.getResetAllowed());
		courtDecisionsJsonList.add(json);
	    }
	}
	return courtDecisionsJsonList;
    }
    //
    public static List<JSONObject> districtcourtDecisionResponses(List<JuvenileDispositionCodeResponseEvent> crtDecisionsRespEventList)
    {
	List<JSONObject> courtDecisionsJsonList = new ArrayList<JSONObject>();
	Iterator<JuvenileDispositionCodeResponseEvent> courtDecisionsItr = crtDecisionsRespEventList.iterator();
	while (courtDecisionsItr.hasNext())
	{
	    JSONObject json = new JSONObject();
	    JuvenileDispositionCodeResponseEvent courtDecisionResp = courtDecisionsItr.next();
	    if (courtDecisionResp != null)
	    {
		json.put("code", courtDecisionResp.getCodeAlpha());
		json.put("description", courtDecisionResp.getLongDesc());
		//json.put("decision", courtDecisionResp.getDecision());
		json.put("subgroupInd", courtDecisionResp.getSubGroupInd());
		json.put("optionCode", courtDecisionResp.getOptionCode());
		json.put("codeNumber", courtDecisionResp.getCodeNum());
		courtDecisionsJsonList.add(json);
	    }
	}
	return courtDecisionsJsonList;
    }
    //
    
    
    
    /**
     * activeHearingTypesWithCatIndAB
     * @return
     */
    public static List<JSONObject> activeHearingTypesWithCatIndAB(){
    	
    	List<JuvenileHearingTypeResponseEvent> activeHearingTypesAB = (List<JuvenileHearingTypeResponseEvent>) PDCriminalCodeTableHelper.loadHearingTypesWithCatIndABonlyActive(); //edit for US 157620
    	List<JSONObject> hearingTypesCatAB = new ArrayList<JSONObject>();
    	Iterator<JuvenileHearingTypeResponseEvent> hearingTypesCatABItr = activeHearingTypesAB.iterator();
    	while(hearingTypesCatABItr.hasNext()){
    	    JSONObject json = new JSONObject();
    	    JuvenileHearingTypeResponseEvent hearingType = hearingTypesCatABItr.next();
    	    if(hearingType!=null){
    		    json.put("description",hearingType.getDescription());
    		    json.put("issueFlag",hearingType.getIssueInd());
    		    json.put("code",hearingType.getCode());
    		    json.put("descriptionWithCode", hearingType.getDescriptionWithCode());
    		    hearingTypesCatAB.add(json);
    	    }
    	}
    	return hearingTypesCatAB;
        }
    
    /**
     * validateOffenseCd
     * 
     * @param offenseCd
     * @return JuvenileOffenseCodeResponseEvent
     */
    public static JuvenileOffenseCodeResponseEvent validateOffenseCd(String offenseCd)
    {
	JuvenileOffenseCodeResponseEvent jpEvent = null;
	GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
	jocEvent.setAlphaCode(offenseCd);
	List<JuvenileOffenseCodeResponseEvent> events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
	if (events != null & !events.isEmpty())
	{
	    for (int x = 0; x < events.size(); x++)
	    {
		JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
		// Added DPS code blank for bug 37346
		if (offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
		{ //!"Y".equals(respEvent.getInactiveInd()) && 
		    // U.S 58355
		    if( !"Y".equals( respEvent.getDiscontCode() )
			    && !"Y".equals( respEvent.getInactiveInd() ) ){
        		jpEvent = new JuvenileOffenseCodeResponseEvent();
        		jpEvent.setOffenseCode(respEvent.getOffenseCode());   //US 71174
        		jpEvent.setCategory(respEvent.getCategory());
        		jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
        		jpEvent.setShortDescription(respEvent.getShortDescription());
        		jpEvent.setSeverity(respEvent.getSeverity());
        		//severity subtype
        		jpEvent.setSeveritySubtype(respEvent.getSeveritySubtype());
        		jpEvent.setSeverityType(respEvent.getSeverityType());
        		break;
		    }
		}
	    }
	}
	return jpEvent;
    }
    public static JuvenileOffenseCodeResponseEvent validateWithAllOffenseCd(String offenseCd)
    {
	JuvenileOffenseCodeResponseEvent jpEvent = null;
	GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
	jocEvent.setAlphaCode(offenseCd);
	List<JuvenileOffenseCodeResponseEvent> events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
	if (events != null & !events.isEmpty())
	{
	    for (int x = 0; x < events.size(); x++)
	    {
		JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
		
		if (offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()) && !(respEvent.getDpsOffenseCode().isEmpty()))
		{		    
        		jpEvent = new JuvenileOffenseCodeResponseEvent();
        		jpEvent.setOffenseCode(respEvent.getOffenseCode());   //US 71174
        		jpEvent.setCategory(respEvent.getCategory());
        		jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
        		jpEvent.setShortDescription(respEvent.getShortDescription());
        		jpEvent.setSeverity(respEvent.getSeverity());
        		break;
		   
		}
	    }
	}
	return jpEvent;
    }
    
    //added for US 171712 STARTS, the following checks are not done in this method
    /*�	DPS code is not blank
    �	DISCONTINUECODE != Y
    �	INACTIVEIND != �Y�*/

    public static JuvenileOffenseCodeResponseEvent validateWithAllOffenses(String offenseCd)
    {
	JuvenileOffenseCodeResponseEvent jpEvent = null;
	GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
	jocEvent.setAlphaCode(offenseCd);
	List<JuvenileOffenseCodeResponseEvent> events = MessageUtil.postRequestListFilter(jocEvent, JuvenileOffenseCodeResponseEvent.class);
	if (events != null & !events.isEmpty())
	{
	    for (int x = 0; x < events.size(); x++)
	    {
		JuvenileOffenseCodeResponseEvent respEvent = (JuvenileOffenseCodeResponseEvent) events.get(x);
		
		if (offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()))
		{		    
        		jpEvent = new JuvenileOffenseCodeResponseEvent();
        		jpEvent.setOffenseCode(respEvent.getOffenseCode());   
        		jpEvent.setCategory(respEvent.getCategory());
        		jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
        		jpEvent.setShortDescription(respEvent.getShortDescription());
        		jpEvent.setSeverity(respEvent.getSeverity());
        		break;
		   
		}
	    }
	}
	return jpEvent;
    }
    
    //added for US 171712 ENDS
    /**
     * populateSubpoenaReportBean
     * @param rptBean
     * @param form
     * @return 
     * @throws Exception 
     */
    public static void populateSubpoenaReportBean(SubpoenaReportBean rptBean,CourtHearingForm form) 
    {
	rptBean.setCourtIdWithSuffix(NumberSuffixUtil.getSuffixForString(form.getCourtId()).toUpperCase());
	//set CourtLocation
	String courtLocation="";
	String reopenStatus ="";
	if(form.getCourtId().equalsIgnoreCase("315")){
	    courtLocation="7TH FLOOR - JUVENILE JUSTICE CENTER";
	 }else{
	     courtLocation="5TH FLOOR - JUVENILE JUSTICE CENTER";   
	 }
	rptBean.setCourtLocation(courtLocation);
	rptBean.setTrackingNum(form.getTrackingNum());
	rptBean.setPetitionNum(form.getPetitionNumber());
	rptBean.setReopenPetStatus(form.getPetitionStatus());
	rptBean.setPetitionAmendment(form.getPetitionAmendment());
	rptBean.setFilingDate(NumberSuffixUtil.getDateSuffixForDateString(DateUtil.dateToString(DateUtil.stringToDate(form.getFilingDate(),"MM/dd/yyyy"),"MMMM dd, yyyy")).toUpperCase());
	rptBean.setCourtDate(NumberSuffixUtil.getDateSuffixForDateString(DateUtil.dateToString(DateUtil.stringToDate(form.getCourtDate(),"MM/dd/yyyy"),"MMMM dd, yyyy")).toUpperCase());
	rptBean.setCourtId(form.getCourtId());
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	Date formattedTime = null;
	try
	{
	    formattedTime = timeFormat.parse(JuvenileFacilityHelper.getDateWithColon(form.getCourtTime()));
	} catch (ParseException e)
	{
	    e.printStackTrace();
	}
	timeFormat = new SimpleDateFormat("hh:mm a.");
	String formattedCourtTime = timeFormat.format(formattedTime);
	
	if(formattedCourtTime!=null && !formattedCourtTime.equals(""))
	rptBean.setCourtTime(formattedCourtTime.replace("AM", "A.M").replace("PM","P.M"));
	
	rptBean.setConstablePct1(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_COUNTY_OFFICIALS, "CD1"));
	rptBean.setDistrictClerkName(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_COUNTY_OFFICIALS, "DCL"));
	rptBean.setPlaintiffName(form.getPlaintiff().toUpperCase());
	rptBean.setJuvenileNumber(form.getJuvenileNumber());
	rptBean.setJuvenileName(form.getJuvenileName());
	// juv address
	rptBean.setJuvenileAddress(form.getMemberAddress());
	rptBean.setJuvenileContact(form.getMemberContact()); // primary guardians phone no.
	rptBean.setPreparationDate(NumberSuffixUtil.getYearSuffixForDateString(DateUtil.dateToString(DateUtil.stringToDate(form.getPreparationDate(),"MM/dd/yyyy"),"MMMM dd, yyyy")).toUpperCase());
	//bug 83602
	if(form.getPetitionStatus()!=null && form.getPetitionStatus().equals("R")){
	    if(form.getPetitionAmendment()!=null){
		rptBean.setAmendmentDate(NumberSuffixUtil.getDateSuffixForDateString(DateUtil.dateToString(DateUtil.stringToDate(form.getAmendmentDate(),"MM/dd/yyyy"),"MMMM dd, yyyy")).toUpperCase());
        	    if(form.getPetitionAmendment().equals("1")){
        		reopenStatus="REOPEN AMENDED";
        	    }else if(form.getPetitionAmendment().equals("2")){
        		reopenStatus="REOPEN SECOND AMENDED";
        	    }else if(form.getPetitionAmendment().equals("3")){
        		reopenStatus="REOPEN THIRD AMENDED";
        	    }else if(form.getPetitionAmendment().equals("4")){
        		reopenStatus="REOPEN FOURTH AMENDED";
        	    }else if(form.getPetitionAmendment().equals("5")){
        		reopenStatus="REOPEN FIFTH AMENDED";
        	    }else if(form.getPetitionAmendment().equals("6")){
        		reopenStatus="REOPEN SIXTH AMENDED";
        	    }else if(form.getPetitionAmendment().equals("7")){
        		reopenStatus="REOPEN SEVENTH AMENDED";
        	    }else if(form.getPetitionAmendment().equals("8")){
        		reopenStatus="REOPEN EIGHTH AMENDED";
        	    }else if(form.getPetitionAmendment().equals("9")){
        		reopenStatus="REOPEN NINTH AMENDED";
        	    }
	    }
	    
	    if(form.getPetitionAmendment()==null || form.getPetitionAmendment()!=null && form.getPetitionAmendment().isEmpty()){
		reopenStatus="REOPEN";
	    }
	}else{
	    if(form.getPetitionAmendment()!=null)
	    {
		rptBean.setAmendmentDate(NumberSuffixUtil.getDateSuffixForDateString(DateUtil.dateToString(DateUtil.stringToDate(form.getAmendmentDate(),"MM/dd/yyyy"),"MMMM dd, yyyy")).toUpperCase());
		if (form.getPetitionAmendment().equals("1"))
		{
		    reopenStatus = "AMENDED";
		}
		else if (form.getPetitionAmendment().equals("2"))
		{
		    reopenStatus = "SECOND AMENDED";
		}
		else if (form.getPetitionAmendment().equals("3"))
		{
		    reopenStatus = "THIRD AMENDED";
		}
		else if (form.getPetitionAmendment().equals("4"))
		{
		    reopenStatus = "FOURTH AMENDED";
		}
		else if (form.getPetitionAmendment().equals("5"))
		{
		    reopenStatus = "FIFTH AMENDED";
		}
		else if (form.getPetitionAmendment().equals("6"))
		{
		    reopenStatus = "SIXTH AMENDED";
		}
		else if (form.getPetitionAmendment().equals("7"))
		{
		    reopenStatus = "SEVENTH AMENDED";
		}
		else if (form.getPetitionAmendment().equals("8"))
		{
		    reopenStatus = "EIGHTH AMENDED";
		}
		else if (form.getPetitionAmendment().equals("9"))
		{
		    reopenStatus = "NINTH AMENDED";
		}
	    }
	}
	rptBean.setReopenPetStatus(reopenStatus);
	if(Integer.valueOf(form.getJuvAge())>17){
	    //for adult_guardian certification
	    rptBean.setCertCode("54.02(J)"); 	
	}else{
	    //for adult_guardian certification
	    rptBean.setCertCode("54.02"); 
	}
	
	if (form.getQueryString().equalsIgnoreCase("F")) //FATHER
	{
	    rptBean.setMemberName(form.getFather());
	    rptBean.setMemberAddress(form.getFatherAddress());
	    rptBean.setMemberContact(form.getFatherContact());
	}
	if (form.getQueryString().equalsIgnoreCase("M"))  //MOTHER
	{
	    rptBean.setMemberName(form.getMother());
	    rptBean.setMemberAddress(form.getMotherAddress());
	    rptBean.setMemberContact(form.getMotherContact());
	}
	if (form.getQueryString().equalsIgnoreCase("O")) //OTHER
	{ 
	    rptBean.setMemberName(form.getCareGiver());
	    rptBean.setMemberAddress(form.getCareGiverAddress());
	    rptBean.setMemberContact(form.getCareGiverContact());
	    if (form.getCareGiver() == null || (form.getCareGiver() != null && form.getCareGiver().getFormattedName()!=null && form.getCareGiver().getFormattedName().isEmpty()))
	    {
		rptBean.setMemberName(form.getGuardian());
		rptBean.setMemberAddress(form.getGuardianAddress());
		rptBean.setMemberContact(form.getGuardianContact());
	    }
	}
	
	//update tracking control number. Increment each time when subpoena is printed.
	if(rptBean.getMemberName()!=null && !rptBean.getMemberName().getFormattedName().isEmpty()){
	    updateTrackingControlNumber(rptBean,form);
	    updateJuvenileService(rptBean,form); // sv.service
	    updateJuvenileServiceCTSInfo(rptBean,form); //sv.service.cts.info
	}else{
	    if(form.getQueryString().equalsIgnoreCase("J")){//JUVENILE
		updateTrackingControlNumber(rptBean,form);
		updateJuvenileService(rptBean,form); // sv.service
		updateJuvenileServiceCTSInfo(rptBean,form);//sv.service.cts.info
	    }
	}
	
	JuvenileFacilityHelper helper = new JuvenileFacilityHelper();
	//set facility information  //83328 bug fixed
	JuvenileFacilityHeaderResponseEvent header = helper.getJJSHeaderResp( form.getJuvenileNumber() );
	if( header!= null && header.getFacilityStatus()!= null ){
	    
	    //pull detention facility from jjs detention.Bug fix #57130 
	    JuvenileDetentionFacilitiesResponseEvent facility = helper.getCurrentJuvenileFacilityRecord( form.getJuvenileNumber() );
	    
	    GetJuvenileFacilityByCodeEvent facilityEvent = (GetJuvenileFacilityByCodeEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEFACILITYBYCODE);
	    facilityEvent.setCode(facility.getDetainedFacility());
	    CompositeResponse response = MessageUtil.postRequest(facilityEvent);
	    JuvenileFacilityResponseEvent juvFacRespEvnt =  (JuvenileFacilityResponseEvent) MessageUtil.filterComposite(response, JuvenileFacilityResponseEvent.class);
	    rptBean.setFacilityName(facility.getDetainedFacility());
	    
	    if (juvFacRespEvnt != null) //83328 bug fixed
	    {
		Address address = new Address();
		address.setStreetName(juvFacRespEvnt.getAddress() + " - WARD");
		address.setCity(juvFacRespEvnt.getCity());
		address.setStateId("TX");
		address.setZipCode(juvFacRespEvnt.getZip());

		rptBean.setJuvenileAddress(address);
		if(juvFacRespEvnt.getFacilityPhone()!=null){
		    MemberContact memContact = new MemberContact();
		    PhoneNumber phone = new PhoneNumber(juvFacRespEvnt.getFacilityPhone().replace(".", ""));
		    memContact.setContactPhoneNumber(phone);
		    rptBean.setJuvenileContact(memContact); // facility phone no.
		}
	    }
	}
	helper = null;
    }

    /**
     * @param juvenileNum
     * @param form
     */
    public static void setSchoolHistory(String juvenileNum,CourtHearingForm form)
    {
	Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(juvenileNum);

	if (schoolHistories.size() > 0)
	{
	    Collections.sort((List) schoolHistories, new SchoolHistoryComparator());
	    JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp = (JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolHistories).get(0);
	    form.setSchool(mySchoolHistoryResp);
	}
    }
    /**
     * update Tracking number
     * 
     * @param rptBean
     * @param form
     * @throws Exception
     */
    public static  void updateTrackingControlNumber(SubpoenaReportBean rptBean, CourtHearingForm form)
    {
	UpdateTrackingControlNumberEvent event = (UpdateTrackingControlNumberEvent) EventFactory.getInstance(JuvenileControllerServiceNames.UPDATETRACKINGCONTROLNUMBER);
	//event.setNextTrackingNum(incrementTrackingNumber(form)); //84071
	CompositeResponse compositeResponse = MessageUtil.postRequest(event);
	TrackingControlNumberResponseEvent trackingRespEvt = (TrackingControlNumberResponseEvent) MessageUtil.filterComposite(compositeResponse, TrackingControlNumberResponseEvent.class);
	if (trackingRespEvt != null)
	{
	    rptBean.setTrackingNum(trackingRespEvt.getNextTrackingNumberControl());
	    form.setTrackingNum(trackingRespEvt.getNextTrackingNumberControl());
	}
	Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (errorResponse != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	    try
	    {
		throw new GeneralFeedbackMessageException(myEvt.getMessage());
	    }
	    catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
    }
    
    /**
     *update Juvenile Service
     * 
     * @param rptBean
     * @param form
     * @throws Exception
     */
    public static void updateJuvenileService(SubpoenaReportBean rptBean, CourtHearingForm form)
    {
	UpdateJuvenileServiceEvent event = (UpdateJuvenileServiceEvent) EventFactory.getInstance(JuvenileControllerServiceNames.UPDATEJUVENILESERVICE);
	event.setCaseNum(rptBean.getPetitionNum());
	event.setAppearanceDate(stringDateToIntConv(form.getCourtDate()));
	event.setRequestDate(stringDateToIntConv(form.getFilingDate()));
	event.setServiceType("JSB");
	event.setTrackingNum(rptBean.getTrackingNum());
	event.setServiceStatus("A");
	event.setLcUser( PDSecurityHelper.getLogonId());
	event.setIssueClerk(PDSecurityHelper.getLogonId());
	event.setIssueDate(stringDateToIntConv(DateUtil.dateToString(DateUtil.getCurrentDate(),DateUtil.DATE_FMT_1)));
	CompositeResponse compositeResponse = MessageUtil.postRequest(event);
	Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (errorResponse != null)
	    {
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		try
		{
		    throw new GeneralFeedbackMessageException(myEvt.getMessage());
		} catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }
    }

    
    /**
     * update Juvenile Service CTS Info
     * 
     * @param rptBean
     * @param form
     * @throws Exception
     */
    public static  void updateJuvenileServiceCTSInfo(SubpoenaReportBean rptBean, CourtHearingForm form)
    {
	UpdateJuvenileServiceCTSInfoEvent event = (UpdateJuvenileServiceCTSInfoEvent) EventFactory.getInstance(JuvenileControllerServiceNames.UPDATEJUVENILESERVICECTSINFO);
	event.setTrackingNum(rptBean.getTrackingNum());
	event.setJuvenileNum(rptBean.getJuvenileNumber());
	event.setCaseNum(rptBean.getPetitionNum());
	event.setCourtDate(stringDateToIntConv(form.getCourtDate()));
	event.setPaperExpirationDate(stringDateToIntConv(form.getCourtDate()));
	event.setCourtNum(rptBean.getCourtId());
	event.setNameType("D");
	event.setSubpoenaForInd(form.getQueryString());
	if(form.getMemberContact()!= null && form.getMemberContact().getContactPhoneNumber()!=null){
		event.setPhone(form.getMemberContact().getContactPhoneNumber().getFormattedPhoneNumber());
	}
	event.setPltName("STATE OF TEXAS");////83496
	event.setState("TX");
	event.setDefendant(rptBean.getJuvenileName());
	if(rptBean.getMemberAddress()!=null){
	    event.setServeName(rptBean.getMemberName().getFormattedName());
	    event.setStreetNum(rptBean.getMemberAddress().getStreetNum());
	    event.setStreetName(rptBean.getMemberAddress().getStreetName());
	    event.setZip(rptBean.getMemberAddress().getZipCode());
	}
	event.setCrossRegionUpdate("Y");
	if(event.getSubpoenaForInd()!=null && event.getSubpoenaForInd().equalsIgnoreCase("J")){
	    event.setServeName(rptBean.getJuvenileName()+ ", RESPONDENT"); //83496
	}
	event.setLcUser( PDSecurityHelper.getLogonId() );
	
	CompositeResponse compositeResponse = MessageUtil.postRequest(event);
	
	UpdateCTSServiceRecordEvent updateRecordEvent = 
		  	(UpdateCTSServiceRecordEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATECTSSERVICERECORD);
	updateRecordEvent.setCTSServiceRecordEvent(rptBean, form);
	MessageUtil.postRequest(updateRecordEvent);
	
	Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (errorResponse != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
	    try
	    {
		throw new GeneralFeedbackMessageException(myEvt.getMessage());
	    }
	    catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
    }


    /**
     * getCourtDocket
     * @param juvenileNumber
     * @param referralNumber
     * @return List<DocketEventResponseEvent>
     */
    public static List<DocketEventResponseEvent> getCourtDocket(String juvenileNumber, String referralNumber)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	// get Court Information
	GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
	courtEvt.setJuvenileNumber(juvenileNumber);
	courtEvt.setReferralNumber(referralNumber);
	dispatch.postEvent(courtEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	// Court Response.
	List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(compositeResponse, DocketEventResponseEvent.class);
	// sort by highest chain number
	Collections.sort((List<DocketEventResponseEvent>) crtdktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1,
		    DocketEventResponseEvent evt2)
	    {
		if (evt1.getChainNumber() != null && evt2.getChainNumber() != null)
		    return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
		else
		    return -1;
	    }
	}));
	Collections.sort((List<DocketEventResponseEvent>) crtdktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1,
		    DocketEventResponseEvent evt2)
	    {
		if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
		    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
		else
		    return -1;
	    }
	}));

	return crtdktRespEvts;
    }
    
    
        
    /**
     * Get Court Decisions Codes
     * 
     * @return list of codes
     */
    public static List<JuvenileDispositionCode> getCourtDecisions()
    {
	List<JuvenileDispositionCode> juvDispList = new ArrayList<JuvenileDispositionCode>();

	Iterator<JuvenileDispositionCode> juvDispItr = JuvenileDispositionCode.findAll();
	while (juvDispItr != null && juvDispItr.hasNext())
	{
	    juvDispList.add(juvDispItr.next());
	}
	return juvDispList;
    }
    
    
    /**
     * Get Court Decisions Codes
     * 
     * @return list of codes
     */
    public static List<JuvenileDispositionCodeResponseEvent> getSortedCourtDecisions()
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileDispositionCodeEvent courtDispEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
	dispatch.postEvent(courtDispEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);

	Collections.sort((List<JuvenileDispositionCodeResponseEvent>) courtDispResps, new Comparator<JuvenileDispositionCodeResponseEvent>() {
	    @Override
	    public int compare(JuvenileDispositionCodeResponseEvent evt1,JuvenileDispositionCodeResponseEvent evt2)
	    {
		if (evt1.getCodeAlpha() != null && evt2.getCodeAlpha() != null)
		    return evt1.getCodeAlpha().compareTo(evt2.getCodeAlpha());
		else
		    return -1;
	    }
	});
	
	return courtDispResps;
    }
    
    /** US 167675
     * Get Court Disposition Codes with CodeType = B or D
     * 
     * @return list of codes
     */
    public static List<JuvenileDispositionCodeResponseEvent> getCourtDispositionCodeTypeDorB()
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileDispositionCodeEvent courtDispEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
	dispatch.postEvent(courtDispEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);
	List<JuvenileDispositionCodeResponseEvent> courtDispRespsDB = new ArrayList<JuvenileDispositionCodeResponseEvent>();
	Iterator juvDisCodesItr = courtDispResps.iterator();
	while (juvDisCodesItr.hasNext()){
	    JuvenileDispositionCodeResponseEvent juvDisCodeRespEvt = (JuvenileDispositionCodeResponseEvent)juvDisCodesItr.next();
	    if(juvDisCodeRespEvt.getCodeType() != null)
	    {
	    if(juvDisCodeRespEvt.getCodeType().equalsIgnoreCase("D") || juvDisCodeRespEvt.getCodeType().equalsIgnoreCase("B")){
		courtDispRespsDB.add(juvDisCodeRespEvt);
	    }
	}
	}
	Collections.sort((List<JuvenileDispositionCodeResponseEvent>) courtDispRespsDB, new Comparator<JuvenileDispositionCodeResponseEvent>() {
	    @Override
	    public int compare(JuvenileDispositionCodeResponseEvent evt1,JuvenileDispositionCodeResponseEvent evt2)
	    {
		if (evt1.getCodeAlpha() != null && evt2.getCodeAlpha() != null)
		    return evt1.getCodeAlpha().compareTo(evt2.getCodeAlpha());
		else
		    return -1;
	    }
	});
	
	return courtDispRespsDB;
    }
   
    
    /** US 167675
     * Get Court Disposition Codes with CodeType = B or R
     * 
     * @return list of codes
     */
    public static List<JuvenileDispositionCodeResponseEvent> getCourtResultCodeTypeRorB()
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileDispositionCodeEvent courtDispEvt = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
	dispatch.postEvent(courtDispEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);
	List<JuvenileDispositionCodeResponseEvent> courtDispRespsDB = new ArrayList<JuvenileDispositionCodeResponseEvent>();
	Iterator juvDisCodesItr = courtDispResps.iterator();
	while (juvDisCodesItr.hasNext()){
	    JuvenileDispositionCodeResponseEvent juvDisCodeRespEvt = (JuvenileDispositionCodeResponseEvent)juvDisCodesItr.next();
	    if(juvDisCodeRespEvt.getCodeType() != null)
	    {
	    if(juvDisCodeRespEvt.getCodeType().equalsIgnoreCase("R") || juvDisCodeRespEvt.getCodeType().equalsIgnoreCase("B")){
		courtDispRespsDB.add(juvDisCodeRespEvt);
	    }
	}
	}
	Collections.sort((List<JuvenileDispositionCodeResponseEvent>) courtDispRespsDB, new Comparator<JuvenileDispositionCodeResponseEvent>() {
	    @Override
	    public int compare(JuvenileDispositionCodeResponseEvent evt1,JuvenileDispositionCodeResponseEvent evt2)
	    {
		if (evt1.getCodeAlpha() != null && evt2.getCodeAlpha() != null)
		    return evt1.getCodeAlpha().compareTo(evt2.getCodeAlpha());
		else
		    return -1;
	    }
	});
	
	return courtDispRespsDB;
    }

    
    public static List<JuvenileDispositionCodeResponseEvent> getSortedAllCourtDecisions()
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAllJuvenileDispositionCodeEvent courtDispEvt = (GetAllJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEDISPOSITIONCODE);
	dispatch.postEvent(courtDispEvt);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	List<JuvenileDispositionCodeResponseEvent> courtDispResps = (List<JuvenileDispositionCodeResponseEvent>) MessageUtil.compositeToCollection(compositeResponse, JuvenileDispositionCodeResponseEvent.class);

	Collections.sort((List<JuvenileDispositionCodeResponseEvent>) courtDispResps, new Comparator<JuvenileDispositionCodeResponseEvent>() {
	    @Override
	    public int compare(JuvenileDispositionCodeResponseEvent evt1,JuvenileDispositionCodeResponseEvent evt2)
	    {
		if (evt1.getCodeAlpha() != null && evt2.getCodeAlpha() != null)
		    return evt1.getCodeAlpha().compareTo(evt2.getCodeAlpha());
		else
		    return -1;
	    }
	});
	
	return courtDispResps;
    }
    
    /**
     * loadActiveHearingTypes
     * 
     * @param
     * @param newAdmit
     * @return Collection<JuvenileHearingTypeResponseEvent>
     */
    public static Collection<JuvenileHearingTypeResponseEvent> loadActiveHearingTypes(String docketType)
    {
	GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();
	juvHearingTypesEvent.setDocketType(docketType);
	CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);
	Collections.sort((List<JuvenileHearingTypeResponseEvent>) hearingTypes, new Comparator<JuvenileHearingTypeResponseEvent>() {
	    @Override
	    public int compare(JuvenileHearingTypeResponseEvent evt1,
		    JuvenileHearingTypeResponseEvent evt2)
	    {
		if (evt1.getCode() != null && evt2.getCode() != null)
		    return evt1.getCode().compareTo(evt2.getCode());
		else
		    return -1;
	    }
	});
	return hearingTypes;
    }
    
    /**
     * loadActiveHearingTypes
     * 
     * @param
     * @param newAdmit
     * @return Collection<JuvenileHearingTypeResponseEvent>
     */
    public static Collection<JuvenileHearingTypeResponseEvent> loadActiveAncillaryHearingTypes()
    {
	
	GetJuvenileHearingTypesEvent juvHearingTypesEvent = new GetJuvenileHearingTypesEvent();
	CompositeResponse response = MessageUtil.postRequest(juvHearingTypesEvent);
	List<JuvenileHearingTypeResponseEvent> hearingTypes = MessageUtil.compositeToList(response, JuvenileHearingTypeResponseEvent.class);
	Collections.sort((List<JuvenileHearingTypeResponseEvent>) hearingTypes, new Comparator<JuvenileHearingTypeResponseEvent>() {
	    @Override
	    public int compare(JuvenileHearingTypeResponseEvent evt1,
		    JuvenileHearingTypeResponseEvent evt2)
	    {
		if (evt1.getCode() != null && evt2.getCode() != null)
		    return evt1.getCode().compareTo(evt2.getCode());
		else
		    return -1;
	    }
	});
	return hearingTypes;
    }

    /**
     * getPetitions
     * @param petitionNumber
     * @return List<PetitionResponseEvent> 
     */
    public static List<PetitionResponseEvent> getPetitions(String petitionNumber){
	GetJJSPetitionsEvent petitionEvent = 
		(GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
	petitionEvent.setPetitionNum(petitionNumber);
	
	CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
	List<PetitionResponseEvent> petitionResps = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
	
	Collections.sort((List<PetitionResponseEvent>) petitionResps, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
	    @Override
	    public int compare(PetitionResponseEvent evt1,
		    PetitionResponseEvent evt2)
	    {
		if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
		    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		else
		    return -1;
	    }
	}));
	return petitionResps;
    }
    
    

    /**
     * getPetitions
     * @param petitionNumber
     * @return List<PetitionResponseEvent> 
     */
    public static List<PetitionResponseEvent> getPetitionsByJuvAndRefNum(String juvenileNumber,String referralNumber){
	GetJJSPetitionsEvent petitionEvent = 
		(GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
	  petitionEvent.setJuvenileNum(juvenileNumber);
	  petitionEvent.setReferralNum(referralNumber);
	
	CompositeResponse compositeResp = MessageUtil.postRequest(petitionEvent);
	List<PetitionResponseEvent> petitionResps = MessageUtil.compositeToList(compositeResp, PetitionResponseEvent.class);
	
	Collections.sort((List<PetitionResponseEvent>) petitionResps, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
	    @Override
	    public int compare(PetitionResponseEvent evt1,
		    PetitionResponseEvent evt2)
	    {
		if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
		    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		else
		    return -1;
	    }
	}));
	return petitionResps;
    }
    
    
    
    /**
     * setGuardians
     * 
     * @param juvenileNum
     * @param form
     */
    public static void setGuardians(String juvenileNum, CourtHearingForm form)
    {

	Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(juvenileNum);

	if (!constellationList.isEmpty())
	{
	    /*
	     * Only 1 active constellation at a time, therefore it's safe to get
	     * the first in the collection
	     */
	    FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();

	    GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();

	    getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
	    CompositeResponse replyEvent = MessageUtil.postRequest(getConstellationDetails);
	    Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);

	    // create a list of guardian(s) for display and residential information - address and phone number
	    if (familyMembers.size() > 0)
	    {
		List<GuardianBean> guardians = new ArrayList<GuardianBean>();
		//List<JuvenileDetentionVisitResponseEvent> detVisits = new ArrayList<JuvenileDetentionVisitResponseEvent>();

		for (FamilyConstellationMemberListResponseEvent member : familyMembers)
		{
		    GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);

		    getMemberDetails.setMemberNum(member.getMemberNum());
		    replyEvent = MessageUtil.postRequest(getMemberDetails);
		    FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);

		    if (member.isGuardian())
		    {
			String rel = member.getRelationToJuvenileId();
			if (notNullNotEmptyString(rel))
			{
			    GuardianBean pbean = new GuardianBean();

			    pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
			    pbean.setRelationshipType(member.getRelationToJuvenile());
			    pbean.setMemberNum(member.getMemberNum());
			    pbean.setLanguage(CodeHelper.getFastCodeDescription(PDCodeTableConstants.LANGUAGE, memberDetail.getPrimarylanguageId()));
			    pbean.setInHomeStatus(String.valueOf(member.isInHomeStatus()));
			    pbean.setPrimaryContact(String.valueOf(member.isPrimaryContact()));
			    //For facility Starts
			    pbean.setDob(DateUtil.dateToString(member.getDateOfBirth(), DateUtil.DATE_FMT_1));
			    pbean.setDriverLicenceNumber(member.getDriverLicenseNum());
			    pbean.setDriverLicenceStateId(member.getDriverLicenseState());
			    pbean.setDetentionHearing(member.isDetentionHearing());
			    pbean.setVisit(member.isDetentionVisitation());
			    pbean.setDriverLicenceStateId(member.getDriverLicenseStateId());
			    pbean.setStateIssuedIdNum(member.getStateIssuedIdNum());
			    pbean.setStateIssuedIdState(member.getStateIssuedIdState());
			    pbean.setGuardianAddress(UIJuvenileHelper.getFamilyMemberAddress(member.getMemberNum()));
			    guardians.add(pbean);

			    // <vkoyyada> ER : JIMS200056844 && Task :
			    // JIMS200056842
			    // Residential address should be the address of first In-Home
			    // guardian's recent address
			    /**
			     * ER 68391 - activity 74116 negates this code
			     * because of Primary contact superceded In-Home.
			     * replacement code added below if
			     * (member.isInHomeStatus()) { if (familyAddress ==
			     * null) { familyAddress =
			     * getFamilyMemberAddress(member.getMemberNum());
			     * form.setMemberAddress(familyAddress); }
			     * familyPhone =
			     * getFamilyMemberPhone(member.getMemberNum());
			     * form.setMemberContact(familyPhone); }
			     */
			}
		    } // if guardian

		    //(commenting as court doesn't need detVisits - )check if the family member allowed detention visit
	/*	    if (member.isDetentionVisitation())
		    {
			JuvenileDetentionVisitResponseEvent detVisit = new JuvenileDetentionVisitResponseEvent();
			detVisit.setJuvenileNum(juvenileNum);
			detVisit.setMemOrContactName(member.getFullName());
			String rel = member.getRelationToJuvenileId();
			if (notNullNotEmptyString(rel))
			    detVisit.setRelationship(member.getRelationToJuvenile());
			String dlID = member.getDriverLicenseNum();
			String stateID = member.getStateIssuedIdNum();
			String ppID = member.getPassportNum();
			String idAndType = "";
			if (dlID != null && !dlID.equals(""))
			    idAndType = dlID + "/DL Number";
			else if (stateID != null && !stateID.equals(""))
			    idAndType = stateID + "/State ID";
			else if (ppID != null && !ppID.equals(""))
			    idAndType = ppID + "/Passport Number";

			detVisit.setIdType(idAndType);
			detVisits.add(detVisit);
		    }
*/
		    form.setGuardians(guardians);

		} // for - familyMembers

		//form.setDetVisits(detVisits); //code commented as court doesn't need DET Visits
		// check for guardian (in any) flagged as Primary contact and load
		// most recent address and phone data 
		if (guardians != null)
		{
		    Address familyAddress = null;
		    MemberContact familyPhone = null;
		    String memberNum = "";
		    boolean priContactFound = false;
		    if (guardians.size() > 1)
		    {
			for (int x = 0; x < guardians.size(); x++)
			{
			    GuardianBean gbean = guardians.get(x);
			    if ("true".equalsIgnoreCase(gbean.getPrimaryContact()))
			    {
				priContactFound = true;
				// use member# to find address and phone number
				familyAddress = UIJuvenileHelper.getFamilyMemberAddress(gbean.getMemberNum());
				form.setMemberAddress(familyAddress);
				//form.setGuardianAddress(familyAddress);
				familyPhone = UIJuvenileHelper.getFamilyMemberPhone(gbean.getMemberNum());
				form.setMemberContact(familyPhone);
			    }
			}
			// if no primary contact found, use most current data based on create date 
			// and in-home status -- possible as older data may not have primary contact
			if (priContactFound == false)
			{
			    GuardianBean gbean0 = guardians.get(0);
			    familyAddress = UIJuvenileHelper.getFamilyMemberAddress(gbean0.getMemberNum());
			    GuardianBean gbean1 = guardians.get(1);
			    Address familyAddress1 = UIJuvenileHelper.getFamilyMemberAddress(gbean1.getMemberNum());
			    if ("true".equalsIgnoreCase(gbean0.getInHomeStatus()))
			    {
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    // create date can be null if no address found
				    if (familyAddress1.getCreateDate() == null)
				    {
					form.setMemberAddress(familyAddress);
					form.setGuardianAddress(familyAddress);
					memberNum = gbean0.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() == null)
				    {
					form.setMemberAddress(familyAddress1);
					form.setGuardianAddress(familyAddress1);
					memberNum = gbean1.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() != null && familyAddress1.getCreateDate() != null)
				    {
					if (DateUtil.compare(familyAddress.getCreateDate(), familyAddress1.getCreateDate(), DateUtil.DATE_FMT_1) > 0)
					{
					    form.setMemberAddress(familyAddress);
					    form.setGuardianAddress(familyAddress);
					    memberNum = gbean0.getMemberNum();
					} else
					{
					    form.setMemberAddress(familyAddress1);
					    form.setGuardianAddress(familyAddress1);
					    memberNum = gbean1.getMemberNum();
					}
				    }
				} else
				{
				    form.setMemberAddress(familyAddress);
				    form.setGuardianAddress(familyAddress);
				    memberNum = gbean0.getMemberNum();
				}
			    } else if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
			    {
				form.setMemberAddress(familyAddress1);
				form.setGuardianAddress(familyAddress1);
				memberNum = gbean1.getMemberNum();
			    }
			    gbean0 = null;
			    gbean1 = null;
			    familyAddress1 = null;
			}

		    } // end of more than 1 guardian 
		    if (guardians.size() == 1)
		    {
			GuardianBean gbean = guardians.get(0);
			if ("true".equalsIgnoreCase(gbean.getInHomeStatus()))
			{
			    familyAddress = UIJuvenileHelper.getFamilyMemberAddress(gbean.getMemberNum());
			    form.setMemberAddress(familyAddress);
			    form.setGuardianAddress(familyAddress);
			    memberNum = gbean.getMemberNum();
			}
			gbean = null;
		    }
		    if (memberNum != null && !"".equals(memberNum))
		    {
			if (familyPhone == null)
			{ // true when no primary contact found
			    familyPhone = UIJuvenileHelper.getFamilyMemberPhone(memberNum);
			    form.setMemberContact(familyPhone);
			}
		    }
		    memberNum = null;
		    familyAddress = null;
		    familyPhone = null;
		} // end guardian check
	    }
	}
    }
    
    /**
     * setCourtBriefingFlags
     * 
     * @param form
     */
    // method commented as Detention Visit is not required as per current requirements.  
    /*	public static void setCourtBriefingFlags(CourtHearingForm form)
    	{
    		GetJuvenileTraitsEvent event =
    				(GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
    			event.setJuvenileNum(form.getJuvenileNumber());
    			event.setUIFacility(false);
    			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
    			dispatch.postEvent(event);
    			IEvent replyEvent = dispatch.getReply();
    			CompositeResponse responses = (CompositeResponse) replyEvent;

    			Collection juvenileTraits =
    				MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
    			//iterating the trait details to get trait desc Officer safety /security alert and show the alert in JSP ER JIMS200076601
    			Iterator traitItr = juvenileTraits.iterator();
    			while(traitItr.hasNext()){

    				JuvenileTraitResponseEvent traitRespEvent = (JuvenileTraitResponseEvent)traitItr.next();
    				if(traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && 
    						traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_BANNED_VISITATION)){
    					form.setDetVisitBanned(true);
    				}
    			}
    	}*/
    
    /**
     * setProfileDetail
     * 
     * @param respEvent
     * @param form
     */
    public static void setProfileDetail(JuvenileProfileDetailResponseEvent respEvent, CourtHearingForm form)
    {
	form.setJuvenileFirstName(respEvent.getFirstName());
	form.setJuvenileLastName(respEvent.getLastName());
	form.setJuvenileName(respEvent.getFormattedName());
	form.setProfileDetail(respEvent);
	form.setJuvenileNumber(respEvent.getJuvenileNum());
	form.setDateOfBirth(DateUtil.dateToString(respEvent.getDateOfBirth(), DateUtil.DATE_FMT_1));
	form.setRectype(respEvent.getRecType());
	if (respEvent.getDateOfBirth() != null)
	{ // Get age based on year
	    int age = UIUtil.getAgeInYears(respEvent.getDateOfBirth());
	    if (age > 0)
	    {
		form.setJuvAge(String.valueOf(age));
	    } else
	    {
		form.setJuvAge(UIConstants.EMPTY_STRING);
	    }
	}
	form.setSexId(respEvent.getSex());
	form.setRaceId(respEvent.getRace());
	form.setSsn(respEvent.getFormattedSSN());
	form.setMultiracial((respEvent.isMultiracial()) ? UIConstants.YES_FULL_TEXT : UIConstants.NO_FULL_TEXT);
	//U.S 88526
	if (respEvent.getHispanic() != null && respEvent.getHispanic().equalsIgnoreCase("Y"))
	{
	    form.setHispanic(UIConstants.YES_FULL_TEXT);
	}
	else
	{
	    form.setHispanic(UIConstants.NO_FULL_TEXT);
	}
	
	if (notNullNotEmptyString(respEvent.getEthnicity()))
	{
	    form.setEthnicity(CodeHelper.getFastCodeDescription(PDCodeTableConstants.JUVENILE_ETHNICITY, respEvent.getEthnicity()));
	}
	//Collection<FamilyConstellationListResponseEvent>  constellation = setJuvenileFamilyDetails(respEvent.getJuvenileNum(), form);
	form.setSchool(new JuvenileSchoolHistoryResponseEvent());
	form.setGuardians(null);
	form.setGuardianAddress(null);
	form.setMemberContact(new MemberContact());
	form.setJpoOfRecID(null);
	form.setMasterStatusId(respEvent.getMasterStatusId());
	form.setMasterStatus(respEvent.getMasterStatus() );
	// task 144405
	form.setSID(respEvent.getSID());
	//
	setSchoolHistory(respEvent.getJuvenileNum(), form);
	setGuardians(respEvent.getJuvenileNum(), form);
	setSpecialtyCourt( respEvent.getJuvenileNum(), form );
	//setJuvenileFamilyDetails(respEvent.getJuvenileNum(), form);
	//setCourtBriefingFlags(form); //detention visit not required for Court briefing page
	jpoOfRecord(respEvent.getJuvenileNum(), form);
    }
    
    /**
     * jpoOfRecord
     * @param juvenileNum
     * @param form -court
     */
    public static void jpoOfRecord(String juvenileNum, CourtHearingForm form)
    {
	SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	searchForCasefiles.setSearchType("JNUM");
	searchForCasefiles.setJuvenileNum(juvenileNum);
	CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);
	/*
	 * Handle error thrown as ErrorResponseEvent from the command (if any)
	 * Expected error: Number of juveniles matching criteria > 2000.
	 */
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error == null)
	{
	    List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
	    if (casefiles.size() > 0)
	    {
		Collections.sort(casefiles);
		JPOofRecSupervisionTypeCategoryResponseEvent codeResp = setJPOOfRecord(casefiles);
		SaveJuvJPOOfRecEvent saveJPO = (SaveJuvJPOOfRecEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVJPOOFREC);
		saveJPO.setJuvenileNum(juvenileNum);
		saveJPO.setJpoId(codeResp.getJpoId());
		IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch1.postEvent(saveJPO);
		form.setJpoOfRecID(codeResp.getJpoId());
		form.setJpoOfRecord(codeResp.getJpo());
		form.setSupervisionCategoryId(codeResp.getSupervisionCategoryId());
		form.setSupervisionType(codeResp.getSupervisionTypeId());
		form.setSupervisionTypeId(codeResp.getSupervisionType());
	    }
	} else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    //saveErrors(aRequest, errors);
	}
    }
		
		
    /**
     * Get JPO of record 		
     * @param juvenileNum
     * @return JPOofRecSupervisionTypeCategoryResponseEvent
     */
    public static JPOofRecSupervisionTypeCategoryResponseEvent getJPOOfRecord(String juvenileNum)
    {
	/*SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	searchForCasefiles.setSearchType("JNUM");
	searchForCasefiles.setJuvenileNum(juvenileNum);
	CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);
	
	 * Handle error thrown as ErrorResponseEvent from the command (if any)
	 * Expected error: Number of juveniles matching criteria > 2000.
	 
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	JPOofRecSupervisionTypeCategoryResponseEvent codeResp =null;
	if (error == null)
	{
	    List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
	    if (casefiles.size() > 0)
	    {
		Collections.sort(casefiles);
		codeResp= setJPOOfRecord(casefiles);
	    }
	} 
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_ERROR, new ActionMessage(error.getMessage()));
	}
	return codeResp;*/ //commented for performance bug 84372
	
	Iterator casefilesItr = JuvenileCasefile.findAll("juvenileId", juvenileNum);
	JPOofRecSupervisionTypeCategoryResponseEvent codeResp =null;
	List<JuvenileCasefileSearchResponseEvent> casefilesRespEvtList = new ArrayList<JuvenileCasefileSearchResponseEvent>(); 
	while(casefilesItr.hasNext())
	    {
	    //find assignements for the casefile and get the most recent
	    JuvenileCasefile juvenileCasefile = (JuvenileCasefile) casefilesItr.next(); 
	    //JuvenileProfileCasefileListResponseEvent respEvt = getResponseEvent(juvenileCasefile);
	    JuvenileCasefileSearchResponseEvent respEvt = getResponseEvent(juvenileCasefile);
    	    	Iterator assignmentIter = Assignment.findAll("caseFileId", juvenileCasefile.getCasefileId());
                Date latestAssignmentDate = null;               
                while (assignmentIter.hasNext())
                {
                    Assignment assignment = (Assignment) assignmentIter.next();
                    if (latestAssignmentDate == null)
                    {
                    	latestAssignmentDate = assignment.getAssignmentAddDate();
                    }
                    else if (assignment.getAssignmentAddDate().after(latestAssignmentDate))
                    {
                    	latestAssignmentDate = assignment.getAssignmentAddDate();
                    }
                }
                respEvt.setAssignmentDate(latestAssignmentDate);
          
                casefilesRespEvtList.add(respEvt);
	    }
	if (casefilesRespEvtList != null && casefilesRespEvtList.size() > 0)
	    codeResp = setJPOOfRecord(casefilesRespEvtList);
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("No casefiles found"));
	}
	return codeResp;
    }
		
		
		
    /**
     * Get supervision category for given sup type. Returns "" if not found;
     * 
     * @param aSupTypeId
     * @returns the supTypeCatId
     * @return category
     */
    public static String getSupCatFromType(String aSupTypeId)throws RuntimeException, Exception
    {
	SupervisionTypeMap sMap = null;
	String supervisionCategory = null;
	Iterator<SupervisionTypeMap> supervisioncategoryIter = null;
	try
	{
	    supervisioncategoryIter = SupervisionTypeMap.findAll("supervisionTypeId", aSupTypeId);
	    while (supervisioncategoryIter.hasNext())
	    {
		sMap =  supervisioncategoryIter.next();
	    }
	    
	    if(sMap!=null){
		supervisionCategory = sMap.getSupervisionCatId();
	    }

	} 
	catch (Exception e)
	{
	    e.printStackTrace();
	    throw new Exception("Error occured while getting the supervisionCategory");
	}
	return supervisionCategory;
    }
/*	Bug #84071
    *//**
     * incrementTrackingNumber
     * @param form
     * @return
     *//*
    public static String incrementTrackingNumber(CourtHearingForm form)
    {
	if(form.getTrackingNum()!=null && !form.getTrackingNum().isEmpty()){
	  String trackingControlDigits = form.getTrackingNum().substring(2, form.getTrackingNum().length());
	    int trackingCtrlNum = Integer.parseInt(trackingControlDigits)+1;
	  return("V0"+String.valueOf(trackingCtrlNum));
	}
	return "";
    }
    */
    
    /**
     * getOfficerUserId
     * 
     * @param officerId
     * @return OfficerProfileResponseEvent
     */
    public static OfficerProfileResponseEvent getOfficerUserId(String officerId)
    {
	GetOfficerProfileEvent event = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
	event.setOfficerProfileId(officerId);
	CompositeResponse response = MessageUtil.postRequest(event);
	OfficerProfileResponseEvent officerProfileResponse = null;
	officerProfileResponse = (OfficerProfileResponseEvent) MessageUtil.filterComposite(response, OfficerProfileResponseEvent.class);
	if (officerProfileResponse != null)
	{
	    return officerProfileResponse;
	}

	return officerProfileResponse;
    }
    
    /**
     * setJPOOfRecord
     * @param casefiles
     * @return null
     */
    public static JPOofRecSupervisionTypeCategoryResponseEvent setJPOOfRecord(Collection<JuvenileCasefileSearchResponseEvent> casefiles)
    {
	Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
	HashMap<String, JuvenileCasefileSearchResponseEvent> casefileMap = new HashMap<String, JuvenileCasefileSearchResponseEvent>();
	String jpoOfRecId = "";
	String jpoOfRec = "";
	String supervisionType = "";
	String supervisionTypeId = "";
	String supervisionCategoryId = "";
	while (iter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) iter.next();

	    if (resp.getCaseStatus()!= null && resp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
	    {
		//US 40492 check if there are any open referrals - if yes, then figure out JPO of record
		//get the referrals for the casefile 
		Collection<JuvenileCasefileReferralsResponseEvent> casefileRefs = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(resp.getSupervisionNum(), resp.getJuvenileNum());
		//check if any of them is open
		Iterator<JuvenileCasefileReferralsResponseEvent> refsIter = casefileRefs.iterator();
		JuvenileCasefileReferralsResponseEvent refresp = null;
		boolean hasOpenRefs = false;

		while (refsIter.hasNext())
		{
		    refresp = (JuvenileCasefileReferralsResponseEvent) refsIter.next();
		    if (refresp.getRefCloseDate() == null)
		    {
			hasOpenRefs = true;
			break;
		    }
		}
		//if has open refs then check for JPO of record	
		if (hasOpenRefs)
		{
		    if (!casefileMap.containsKey(resp.getSupervisionCategory()))
		    {
			casefileMap.put(resp.getSupervisionCategory(), resp);
		    } else
		    {
			//replace with one that has been recently created
			JuvenileCasefileSearchResponseEvent respFromMap = (JuvenileCasefileSearchResponseEvent) casefileMap.get(resp.getSupervisionCategory());
			if (resp.getAssignmentDate() != null && respFromMap.getAssignmentDate() != null && (resp.getAssignmentDate().after(respFromMap.getAssignmentDate())))
			    casefileMap.put(resp.getSupervisionCategory(), resp);
		    }
		}//end hasOpenRefs				    	

	    }//end if active
	}//end while

	//Now go through the HashMap and find the JPO according to rules
	if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AR") != null)
	{
	    JuvenileCasefileSearchResponseEvent casefileAR = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AR");
	    //check if there is another rec with AC
	    if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AC") != null)
	    {
		JuvenileCasefileSearchResponseEvent casefileAC = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AC");
		if (casefileAC.getAssignmentDate().after(casefileAR.getAssignmentDate()))
		{
		    //jpo will be the one on casefileAC
		    jpoOfRecId = casefileAC.getOfficerLoginId();
		    jpoOfRec = casefileAC.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAC.getSupervisionCategory();
		    supervisionType = casefileAC.getSupervisionType();
		    supervisionTypeId = casefileAC.getSupervisionTypeId();
		}//end if assessmentDate
		else
		{
		    //jpo will be the one on casefileAR
		    jpoOfRecId = casefileAR.getOfficerLoginId();
		    jpoOfRec = casefileAR.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAR.getSupervisionCategory();
		    supervisionType = casefileAR.getSupervisionType();
		    supervisionTypeId = casefileAR.getSupervisionTypeId();
		}

	    }//end if AC
	    else
	    {
		//jpo of rec will be the one with AC
		jpoOfRecId = casefileAR.getOfficerLoginId();
		jpoOfRec = casefileAR.getProbationOfficerFullName();
		supervisionCategoryId = casefileAR.getSupervisionCategory();
		supervisionType = casefileAR.getSupervisionType();
		supervisionTypeId = casefileAR.getSupervisionTypeId();
	    }//end else AC
	}//end else if AR
	else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AC") != null)
	{
	    JuvenileCasefileSearchResponseEvent casefileAC = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AC");
	    //check if there is another rec with AR
	    if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AR") != null)
	    {

		JuvenileCasefileSearchResponseEvent casefileAR = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AR");
		if (casefileAC.getAssignmentDate().after(casefileAR.getAssignmentDate()))
		{
		    //jpo will be the one on casefileAC
		    jpoOfRecId = casefileAC.getOfficerLoginId();
		    jpoOfRec = casefileAC.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAC.getSupervisionCategory();
		    supervisionType = casefileAC.getSupervisionType();
		    supervisionTypeId = casefileAC.getSupervisionTypeId();
		}//end if assessmentDate
		else
		{
		    //jpo will be the one on casefileAR
		    jpoOfRecId = casefileAR.getOfficerLoginId();
		    jpoOfRec = casefileAR.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAR.getSupervisionCategory();
		    supervisionType = casefileAR.getSupervisionType();
		    supervisionTypeId = casefileAR.getSupervisionTypeId();
		}
	    }//end if AR
	    else
	    {
		//jpo will be one on AC rec
		jpoOfRecId = casefileAC.getOfficerLoginId();
		jpoOfRec = casefileAC.getProbationOfficerFullName();
		supervisionCategoryId = casefileAC.getSupervisionCategory();
		supervisionType = casefileAC.getSupervisionType();
		supervisionTypeId = casefileAC.getSupervisionTypeId();
	    }
	}//end else if AC
	// task 162557
	else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("ID") != null) //US 87430 
	{
	    JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent) casefileMap.get("ID");
	    jpoOfRecId = casefileAD.getOfficerLoginId();
	    jpoOfRec = casefileAD.getProbationOfficerFullName();
	    supervisionCategoryId = casefileAD.getSupervisionCategory();
	    supervisionType = casefileAD.getSupervisionType();
	    supervisionTypeId = casefileAD.getSupervisionTypeId();
	}//
	else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("DA") != null)
	{
	    JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) casefileMap.get("DA");
	    //get briefing form from session and populate
	    jpoOfRecId = casefileResp.getOfficerLoginId();
	    jpoOfRec = casefileResp.getProbationOfficerFullName();
	    supervisionType = casefileResp.getSupervisionType();
	    supervisionCategoryId = casefileResp.getSupervisionCategory();
	    supervisionTypeId = casefileResp.getSupervisionTypeId();

	}//end if DA  //moved the code down for BUG 87437
	else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AD") != null)
	{
	    JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AD");
	    jpoOfRecId = casefileAD.getOfficerLoginId();
	    jpoOfRec = casefileAD.getProbationOfficerFullName();
	    supervisionCategoryId = casefileAD.getSupervisionCategory();
	    supervisionType = casefileAD.getSupervisionType();
	    supervisionTypeId = casefileAD.getSupervisionTypeId();

	}//end else if AD
	else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("PP") != null)
	{
	    JuvenileCasefileSearchResponseEvent casefilePP = (JuvenileCasefileSearchResponseEvent) casefileMap.get("PP");
	    jpoOfRecId = casefilePP.getOfficerLoginId();
	    jpoOfRec = casefilePP.getProbationOfficerFullName();
	    supervisionCategoryId = casefilePP.getSupervisionCategory();
	    supervisionType = casefilePP.getSupervisionType();
	    supervisionTypeId = casefilePP.getSupervisionTypeId();

	}//end else if PP	
	JPOofRecSupervisionTypeCategoryResponseEvent codeResp = new JPOofRecSupervisionTypeCategoryResponseEvent();
	codeResp.setJpoId(jpoOfRecId);
	codeResp.setJpo(jpoOfRec);
	//codeResp.setSupervisionCategory(supervisionCategory);
	codeResp.setSupervisionCategoryId(supervisionCategoryId);
	codeResp.setSupervisionType(supervisionType);
	codeResp.setSupervisionTypeId(supervisionTypeId);
	return codeResp;
    }

       
	/**
	 * setJuvenileFamilyDetailsForDockets
	 * @param juvenileNum
	 * @param docket
	 * @return 
	 */
    public static void setJuvenileFamilyDetailsForDockets(String juvenileNum,DocketEventResponseEvent docket)
    {
	Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(juvenileNum);
	
	if (!constellationList.isEmpty())
	{
	    /*
	     * Only 1 active constellation at a time, therefore it's safe to get
	     * the first in the collection
	     */
	    FamilyConstellationListResponseEvent activeConstellation = constellationList.iterator().next();

	    GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
	    getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
	    CompositeResponse replyEvent = MessageUtil.postRequest(getConstellationDetails);
	    Collection<FamilyConstellationMemberListResponseEvent> familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
	    boolean primaryContactFound=false; 
	    // create a list of guardian(s) for display
	    if (familyMembers.size() > 0)
	    {
		for (FamilyConstellationMemberListResponseEvent member : familyMembers)
		{
		    /* GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);
		    getMemberDetails.setMemberNum(member.getMemberNum());
		    replyEvent = MessageUtil.postRequest(getMemberDetails);
		    FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);
		    */ //code tuning for performance
		    FamilyMember familyMember = FamilyMember.find(member.getMemberNum());
			FamilyMemberDetailResponseEvent memberDetail = JuvenileFamilyHelper.getFamilyMemberDetailResponseEvent(familyMember);
		    
		    if (member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER)){
			    docket.setFather(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName())) ;
			    //JuvenileMemberForm.MemberContact memberContact= UIJuvenileHelper.getFamilyMemberPhone(memberDetail.getMemberId());
			}
		    if (member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)){
			    docket.setMother(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));
			}
		    if (!member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_MOTHER)&&!member.getRelationToJuvenileId().equalsIgnoreCase(UIConstants.BIRTH_FATHER)){
		    	if (member.isPrimaryContact()){
        		docket.setOther(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName())) ;
				docket.setRelationship(member.getRelationToJuvenile());
		    	}	
		    }
		}
	    }
	}else {
		docket.setNoJuvFamily("** " + juvenileNum + " -- NO FAMILY FOUND **");
	}
	}
  
    public static DocketEventResponseEvent populateDocketInfo(DocketEventResponseEvent docketOriginal , DocketEventResponseEvent docketEdited){
    	
    	docketEdited.setAdmitDate(docketOriginal.getAdmitDate());
    	docketEdited.setAge(docketOriginal.getAge());
    	docketEdited.setAllegation(docketOriginal.getAllegation());
    	docketEdited.setAllegationDesc(docketOriginal.getAllegationDesc());
    	docketEdited.setAlternateSettingInd(docketOriginal.getAlternateSettingInd());
    	docketEdited.setAmendment(docketOriginal.getAmendment());
    	docketEdited.setAssignedJudge(docketOriginal.getAssignedJudge());
    	docketEdited.setAttorneyConnection(docketOriginal.getAttorneyConnection());
    	docketEdited.setAttorneyConnectionDesc(docketOriginal.getAttorneyConnectionDesc());
    	docketEdited.setAttorneyName(docketOriginal.getAttorneyName());
    	docketEdited.setBarNum(docketOriginal.getBarNum());
    	docketEdited.setBodyText(docketOriginal.getBodyText());
    	docketEdited.setCalendarEventId(docketOriginal.getCalendarEventId());
    	docketEdited.setCalendarEventType(docketOriginal.getCalendarEventType());
    	docketEdited.setChainNumber(docketOriginal.getChainNumber());
    	docketEdited.setComments(docketOriginal.getComments());
    	docketEdited.setCourt(docketOriginal.getCourt());
    	docketEdited.setCourtDate(docketOriginal.getCourtDate());
    	docketEdited.setCourtResult(docketOriginal.getCourtResult());
    	docketEdited.setOldcourtResult(docketOriginal.getCourtResult());
    	docketEdited.setCourtResultDesc(docketOriginal.getCourtResultDesc());
    	docketEdited.setCourtTime(docketOriginal.getCourtTime());
    	docketEdited.setDisposition(docketOriginal.getDisposition());
    	docketEdited.setDispositionDesc(docketOriginal.getDispositionDesc());
    	docketEdited.setDob(docketOriginal.getDob());
    	docketEdited.setDocketEventId(docketOriginal.getDocketEventId());
    	docketEdited.setDocketType(docketOriginal.getDocketType());
    	docketEdited.setEndDatetime(docketOriginal.getEndDatetime());
    	docketEdited.setEventDate(docketOriginal.getEventDate());
    	docketEdited.setEventDateWithTime(docketOriginal.getEventDateWithTime());
    	docketEdited.setFacilityName(docketOriginal.getFacilityName());
    	docketEdited.setFather(docketOriginal.getFather());
    	docketEdited.setFilingDate(docketOriginal.getFilingDate());
    	docketEdited.setFormattedCourtTime(docketOriginal.getFormattedCourtTime());
    	docketEdited.setGender(docketOriginal.getGender());
    	docketEdited.setHearingType(docketOriginal.getHearingType());
    	docketEdited.setHearingTypeDesc(docketOriginal.getHearingTypeDesc());
    	docketEdited.setIssueFlag(docketOriginal.getIssueFlag());
    	docketEdited.setJpoOfRecord(docketOriginal.getJpoOfRecord());
    	docketEdited.setJuryFlag(docketOriginal.getJuryFlag());
    	docketEdited.setJuryFlagDesc(docketOriginal.getJuryFlagDesc());
    	docketEdited.setJuvenileName(docketOriginal.getJuvenileName());
    	docketEdited.setJuvenileNumber(docketOriginal.getJuvenileNumber());
    	docketEdited.setLastCourtDate(docketOriginal.getLastCourtDate());
    	docketEdited.setLocation(docketOriginal.getLocation());
    	docketEdited.setMother(docketOriginal.getMother());
    	docketEdited.setNoJuvFamily(docketOriginal.getNoJuvFamily());
    	docketEdited.setOffenseCode(docketOriginal.getOffenseCode());
    	docketEdited.setOffenseCategory(docketOriginal.getOffenseCategory());
    	docketEdited.setOther(docketOriginal.getOther());
    	docketEdited.setPetitionAllegation(docketOriginal.getPetitionAllegation());
    	docketEdited.setPetitionAllegationDesc(docketOriginal.getPetitionAllegationDesc());
    	docketEdited.setPetitionAmendment(docketOriginal.getPetitionAmendment());
    	docketEdited.setPetitionAmendmentDate(docketOriginal.getPetitionAmendment());
    	docketEdited.setPetitionAmendmentDate(docketOriginal.getPetitionAmendmentDate());
    	docketEdited.setPetitionNumber(docketOriginal.getPetitionNumber());
    	docketEdited.setPetitionNumUI(docketOriginal.getPetitionNumUI());
    	docketEdited.setPetitionStatus(docketOriginal.getPetitionStatus());
    	docketEdited.setPetitionStatusCd(docketOriginal.getPetitionStatusCd());
    	docketEdited.setPetitionType(docketOriginal.getPetitionType());
    	docketEdited.setPrevHearingDate(docketOriginal.getPrevHearingDate());
    	docketEdited.setPrevNotes(docketOriginal.getPrevNotes());
    	docketEdited.setProbableCauseDate(docketOriginal.getProbableCauseDate());
    	docketEdited.setProbationOfficer(docketOriginal.getProbationOfficer());
    	docketEdited.setProbationOfficerCd(docketOriginal.getProbationOfficerCd());
    	docketEdited.setRace(docketOriginal.getRace());
    	docketEdited.setRaceId(docketOriginal.getRaceId());
    	docketEdited.setRecType(docketOriginal.getRecType());
    	docketEdited.setReferralNum(docketOriginal.getReferralNum());
    	docketEdited.setRelationship(docketOriginal.getRelationship());
    	docketEdited.setResetHearingType(docketOriginal.getResetHearingType());
    	docketEdited.setOldresetHearingType(docketOriginal.getResetHearingType());
    	docketEdited.setResetReason(docketOriginal.getResetReason());
    	docketEdited.setResetTo(docketOriginal.getResetTo());
    	docketEdited.setRespondantName(docketOriginal.getRespondantName());
    	docketEdited.setSeqNum(docketOriginal.getSeqNum());
    	docketEdited.setServer(docketOriginal.getServer());
    	docketEdited.setSetNote(docketOriginal.getSetNote());
    	docketEdited.setSettingReason(docketOriginal.getSettingReason());
    	docketEdited.setSexId(docketOriginal.getSexId());
    	docketEdited.setSortOrder(docketOriginal.getSortOrder());
    	docketEdited.setStartDatetime(docketOriginal.getStartDatetime());
    	docketEdited.setStartTime(docketOriginal.getStartDatetime());
    	docketEdited.setStatus(docketOriginal.getStatus());
    	docketEdited.setSubject(docketOriginal.getSubject());
    	docketEdited.setTjpcSeqNum(docketOriginal.getTjpcSeqNum());
    	docketEdited.setTopic(docketOriginal.getTopic());
    	docketEdited.setTransferTo(docketOriginal.getTransferTo());
    	docketEdited.setOldtransferTo(docketOriginal.getTransferTo());
    	docketEdited.setTypeCase(docketOriginal.getTypeCase());
    	docketEdited.setUpdateFlag(docketOriginal.getUpdateFlag());
    	docketEdited.setGalName(docketOriginal.getGalName());
    	docketEdited.setAtyConfirmation(docketOriginal.getAtyConfirmation());
    	docketEdited.setAttorney2Name(docketOriginal.getAttorney2Name());
    	docketEdited.setAppAttorney(docketOriginal.getAppAttorney());
    	//task 168663
    	docketEdited.setInterpreter(docketOriginal.getInterpreter());
    	return docketEdited;
    }
      /** 
     * getDateWithoutTime
     * @param date
     * @return Date
     */
    public static Date getDateWithoutTime(Date date){
	    Date dateWithoutTime = date;
	    Calendar calendar = Calendar.getInstance();

	    calendar.setTime( date );
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);

	    dateWithoutTime = calendar.getTime();

	    return dateWithoutTime;

    }
    
    /**
     * returns true if string isn't null and contains one or more chars
     */
    private static boolean notNullNotEmptyString(String str) {
    	return (str != null && str.trim().length() > 0);
    }
    
    /**
     * IsReferral Exists.
     */
    public static boolean doesReferralExists(String referralNumber, String juvenileNumber)
    {
	GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	refEvent.setJuvenileNum(juvenileNumber);
	refEvent.setReferralNum(referralNumber);

	Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);
	if(referralList.hasNext()){
	    return true;
	}
	return false;
    }
    
    
   /**
    * 
    * @param juvenileNumber
    * @return
    */
    public static boolean isJuvenileFound(String juvenileNumber)
    {
	 GetJuvenileInfoLightEvent req = (GetJuvenileInfoLightEvent) 
			EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEINFOLIGHT);
	 req.setJuvenileNum( juvenileNumber );
	 JuvenileCoreLightResponseEvent juvenile = (JuvenileCoreLightResponseEvent) MessageUtil.postRequest(req, JuvenileCoreLightResponseEvent.class);

	if (juvenile!=null)
	{
	    return true;
	}
	return false;
    }
    
    /**
     * 
     * @return
     */
    public static List<JSONObject> detentionCourtDecisions(){
	
	List<JuvenileCourtDecisionCodeResponseEvent> codes = JuvenileCaseHelper.getCourtDecisionsNew();
   	List<JSONObject> courtDecisionObj = new ArrayList<JSONObject>();
   	Iterator<JuvenileCourtDecisionCodeResponseEvent> codesItr = codes.iterator();
   	while( codesItr.hasNext() ){
   	    JSONObject json = new JSONObject();
   	    JuvenileCourtDecisionCodeResponseEvent code = codesItr.next();
   	    if( code!=null ){
   		    json.put("code",code.getCode());
   		    json.put("description",code.getDescription());
   		    json.put("decision",code.getDecision());
   		    json.put("action",code.getAction());
   		    json.put("allowReset",code.getResetAllowed());
   		    
   		    courtDecisionObj.add(json);
   	    }
   	}
   	return courtDecisionObj;
       }
    
    /**
     *  //83426
     * CTS Assign JPO ID
     * @return
     */
    public static JJSReferral getRefInfo(String juvenileNumber,String referralNumber){
	GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
	refEvent.setJuvenileNum(juvenileNumber);
	refEvent.setReferralNum(referralNumber);
	Iterator<JJSReferral> referralList = JJSReferral.findAll(refEvent);
	if (referralList.hasNext())
	{
	    JJSReferral ref = (JJSReferral) referralList.next();
	    if (ref != null)
	    {
			return ref;
	    }

	}
	return null;
    }
    public static JJSLastAttorney getLastAttorneyInfo(String juvenileNumber){
        
        Iterator it = JJSLastAttorney.findAll("juvenileNum",juvenileNumber);
      		
        if(it.hasNext())
        {  
            if (it != null)
	    {
        	JJSLastAttorney rec = (JJSLastAttorney) it.next();
		return rec;
	    }
        }
        return null;
      	
    }
    
    /** created by NeMathew
     * getResponseEvent
     * @param juvenileCasefile
     * @return
     */
    private static JuvenileCasefileSearchResponseEvent getResponseEvent(JuvenileCasefile juvenileCasefile)
    {

	JuvenileCasefileSearchResponseEvent event = new JuvenileCasefileSearchResponseEvent();
        event.setSupervisionNum(juvenileCasefile.getOID());
        event.setSequenceNum(juvenileCasefile.getSequenceNumber());
        event.setProbationOfficerFullName(juvenileCasefile.getProbationOfficerFullName());
        event.setOfficerLoginId(juvenileCasefile.getOfficerLogonIdData());
        Code supervisionType = juvenileCasefile.getSupervisionType();
        event.setSupervisionType(supervisionType.getDescription());
        event.setSupervisionCategory(juvenileCasefile.getSupervisionCategoryId());
        event.setSupervisionTypeId(juvenileCasefile.getSupervisionTypeId()); 
        Code status = juvenileCasefile.getCaseStatus();
        
        if (status != null)
        {
            event.setCaseStatus(status.getDescription());
        }
        return event;
    }
    
    /**
     * getFormatedDate(Date) created by NeMathew
     * @param date
     * @return
     */
    private static String getFormatedDate(Date date)
    {
        String strDate = "";
        if (date != null)
        {
            strDate = DateUtil.dateToString(date, "MM/dd/yyyy");
        }
        return strDate;
    } 
    
    /**
     * 
     * @param juvenileNum
     * @return
     */
    public String getJpoOfRecordNew( String juvenileNum ){
	
	String jpoOfRec = "";
	List<JuvenileCasefileSearchResponseEvent> activeList = new ArrayList<JuvenileCasefileSearchResponseEvent>();
	SearchJuvenileCasefilesEvent requestEvent = (SearchJuvenileCasefilesEvent) 
				     EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	requestEvent.setSearchType("JNUM");
	requestEvent.setJuvenileNum(juvenileNum);
	
	List casefileList = MessageUtil.postRequestListFilter(requestEvent, JuvenileCasefileSearchResponseEvent.class);
	
	Iterator listIter = casefileList.iterator();
	while(listIter.hasNext()){
	    
	    JuvenileCasefileSearchResponseEvent response = (JuvenileCasefileSearchResponseEvent) listIter.next();
	    if( "ACTIVE".equalsIgnoreCase(response.getCaseStatus() )){
		
		activeList.add(response);
		
	    }
	}
	
	Collections.sort((List<JuvenileCasefileSearchResponseEvent>) activeList, new Comparator<JuvenileCasefileSearchResponseEvent>() {
		@Override
		public int compare(JuvenileCasefileSearchResponseEvent evt1, JuvenileCasefileSearchResponseEvent evt2) {
			return new CompareToBuilder().append(evt1.getAssignmentDate(), evt2.getAssignmentDate())
					.append(evt1.getCaseStatus(), evt2.getCaseStatus()).toComparison();
		}
	});
	
	for( int y=0;y<activeList.size();y++ ){
	   
	    JuvenileCasefileSearchResponseEvent respEvt = (JuvenileCasefileSearchResponseEvent) activeList.get(y);
	    
	   if("AD".equalsIgnoreCase( respEvt.getSupervisionCategory() ) || "AR".equalsIgnoreCase( respEvt.getSupervisionCategory())){
	       
	       /*OfficerProfileResponseEvent officer = getOfficerUserId(respEvt.getJpoId());
	       if( officer!= null ){
		
		   jpoOfRec = respEvt.getProbationOfficerFullName();
	       }*/
	       jpoOfRec = respEvt.getProbationOfficerFullName();
	       break;
	       
	   }
	}	
	
	return jpoOfRec;
    }
    
    /*
     * 
     */
    private static int stringDateToIntConv(String date){
	
	int retDate = 0;
	if( date.contains("/") ){
	    
	    String[] args = date.split("/");
	    String month = args[0];
	    String day   = args[1];
	    String year = args[2];
	    StringBuffer  sb = new StringBuffer();
	    sb.append(year).append(month).append(day);
	    retDate = Integer.parseInt(sb.toString());
	}	
	
	return retDate;
    }
    
    /**
     * 
     * @param juvenileNum
     * @param form
     */
    private static void setSpecialtyCourt(String juvenileNum,  CourtHearingForm form){
	
	//'GCP','DCV','GCT','C36'
	//2728,1159,1156,1161
	 form.setSpecialtyCourtDescription("");
	 form.setInSpecialtyCourt(false); 
	 List activeReferrals = UIProgramReferralHelper.getOnlyActiveJuvenileProgramReferrals(juvenileNum);
	 for(int x=0; x<activeReferrals.size();x++){
	     
	     ProgramReferralResponseEvent prre = (ProgramReferralResponseEvent) activeReferrals.get(x);
	     if (prre.getReferralStatusCd().equals("AC"))
	     {
    		   if ( prre.getProvProgramId().equals("2728")){
    		       
    		       form.setSpecialtyCourtDescription("COURT 360");
    		       form.setInSpecialtyCourt(true);//SC   		    
    		       break;
    		        
    		   }else if ( prre.getProvProgramId().equals("1159")){
    		       
   		       form.setSpecialtyCourtDescription("DRUG COURT SUPERVISION - SPECIALTY COURT");
    		       form.setInSpecialtyCourt(true);//SC   		    
    		       break;
    		        
    		   }else if( prre.getProvProgramId().equals("1156")){
   		       
    		       form.setSpecialtyCourtDescription("GANG COURT SUPERVISION (SPECIALTY COURT)");
    		       form.setInSpecialtyCourt(true);//SC   		    
    		       break;       
    		        
    		   }else if(prre.getProvProgramId().equals("1161")){
    		       
   		       form.setSpecialtyCourtDescription("CARE COURT SUPERVISION - SPECIALTY COURT");
    		       form.setInSpecialtyCourt(true);//SC   		    
    		       break;
    		       
    		   }
       		    
    	       }
	   }
	 }
    
    
   
  
}

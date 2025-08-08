/*
 *  Created on Jun 16, 2005
 *
 */
package ui.juvenilecase;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import messaging.address.GetAddressByIdEvent;
import messaging.address.reply.AddressResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.codetable.GetAllAliasNameTypeCodesEvent;
import messaging.codetable.GetAllMAYSIReasonCodesEvent;
import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.districtCourtHearings.UpdateJJSCLCourtSettingEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyConstellationsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.family.GetFamilyMemberEmploymentInfoEvent;
import messaging.family.GetFamilyMembersEvent;
import messaging.family.GetJPOsForFamilyMemberEvent;
import messaging.family.GetJuvReferralFamilyInfoEvent;
import messaging.family.GetJuvenileFamilyMembersEvent;
import messaging.family.SaveFamilyConstellationEvent;
import messaging.family.SaveFamilyConstellationMemberInfoEvent;
import messaging.family.SaveFamilyMemberAddressEvent;
import messaging.family.SaveFamilyMemberBenefitsEvent;
import messaging.family.SaveFamilyMemberContactEvent;
import messaging.family.SaveFamilyMemberEmploymentInfoEvent;
import messaging.family.SaveFamilyMemberEvent;
import messaging.family.SaveFamilyMemberFinancialEvent;
import messaging.family.SaveFamilyMemberInsuranceEvent;
import messaging.family.SaveFamilyMemberMaritalStatusEvent;
import messaging.family.SaveFamilyMemberTraitEvent;
import messaging.family.SaveFamilyTraitEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.interviewinfo.reply.JuvenileBenefitResponseEvent;
import messaging.interviewinfo.to.SchoolHistoryTO;
import messaging.juvenile.GetJuvenileAbuseListEvent;
import messaging.juvenile.GetJuvenileAbusePerpsListEvent;
import messaging.juvenile.GetJuvenileAbuserRelationshipListEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.GetJuvenileDSservicesListEvent;
import messaging.juvenile.GetJuvenileDualStatusListEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetJuvenileSchoolEvent;
import messaging.juvenile.GetSchoolDistrictsEvent;
import messaging.juvenile.JuvenileDrugRequestEvent;
import messaging.juvenile.JuvenileGangRequestEvent;
import messaging.juvenile.SaveJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileAbuseResponseEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileDualStatusResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenilePhotoResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.GetTraitParentByCategoryEvent;
import messaging.juvenilecase.reply.AssociatedJuvenilesResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationGuardianResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberFinancialResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationTraitsResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberAddressViewResponseEvent;
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
import messaging.juvenilecase.reply.JuvReferralFamilyResponseEvent;
import messaging.juvenilecase.reply.JuvenileAliasResponseEvent;
import messaging.juvenilecase.reply.JuvenileFamilyMemberViewResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.TraitTypeResponseEvent;
import messaging.notification.CreateNotificationEvent;
import messaging.referral.GetBehavioralHistoryEvent;
import messaging.referral.SaveJJSReferralEvent;
import messaging.referral.UpdateJuvenileReferralEvent;
import messaging.referral.reply.JuvenileBehaviorHistoryResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.ISecurityManager;
import mojo.km.security.Token;
import mojo.km.security.UserEntityBean;
import mojo.km.security.helper.SecurityManagerWebServiceHelper;
import mojo.km.util.CollectionUtil;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;

import org.apache.commons.collections.FastArrayList;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.ujac.util.BeanComparator;

import pd.attorney.Attorney;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileReferralDispositionCode;
import pd.codetable.person.AliasNameTypeCode;
import pd.codetable.person.ReasonNotDone;
import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenile.JuvenileAbusePerpatrator;
import pd.juvenile.JuvenileAbuserRelationship;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileDualStatusCPSServices;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.family.FamilyConstellation;
import pd.juvenilecase.family.FamilyMember;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.security.JIMS2Account;
import pd.security.JIMS2AccountType;
import pd.security.PDSecurityHelper;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.Photo;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.juvenilecase.action.DisplayJuvenileMasterInformationAction;
import ui.juvenilecase.casefile.form.CasefileClosingForm;
import ui.juvenilecase.casefile.form.CommonAppForm;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.casefile.form.ResidentialExitPlanForm;
import ui.juvenilecase.form.JuvenileAbuseForm;
import ui.juvenilecase.form.JuvenileAliasInfoForm;
import ui.juvenilecase.form.JuvenileBehaviorHistoryForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileDrugForm;
import ui.juvenilecase.form.JuvenileFamilyForm;
import ui.juvenilecase.form.JuvenileGEDProgramForm;
import ui.juvenilecase.form.JuvenileGangsForm;
import ui.juvenilecase.form.JuvenileMainForm;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberAddress;
import ui.juvenilecase.form.JuvenileMemberSearchForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.form.JuvenilePhysicalCharacteristicsForm;
import ui.juvenilecase.form.JuvenileProfileForm;
import ui.juvenilecase.form.TraitsForm;
import ui.juvenilecase.medical.form.MedicalForm;

/**
 * @author asrvastava
 */
public class UIJuvenileHelper
{
    public final static String HEADER_FORM = "juvenileProfileHeader";
    public final static String TRAITS_FORM = "juvenileTraitsForm";
    public final static String MEDICAL_FORM = "medicalForm";
    public final static String JUVENILE_ABUSE_FORM = "juvenileAbuseForm";
    public final static String FAMILY_FORM = "juvenileFamilyForm";
    public final static String JUV_PHOTO_FORM = "juvenilePhotoForm";
    public final static String MEMBER_SEARCH_FORM = "juvenileMemberSearchForm";
    public final static String JUVENILE_CASEFILE_FORM = "juvenileCasefileForm";
    public final static String JUVENILE_CASEFILE_CLOSING_FORM = "casefileClosingForm";
    public final static String JUVENILE_COMMONAPP_FORM = "commonAppForm";
    public final static String JUVENILE_RESIDENTIAL_EXIT_PLAN_FORM = "residentialExitPlanForm";
    public final static String JUVENILE_BEHAVIOR_HISTORY_FORM = "juvenileBehaviorHistoryForm";
    public final static String JUVENILE_DETAIL_FORM = "JuvenileDetailForm";
    public final static String MEMBER_FORM = "juvenileMemberForm";
    public final static String GED_PROGRAM_FORM = "juvenileGEDProgramForm";
    public final static String JUVENILE_GANGS_FORM = "juvenileGangsForm"; //ER_GANG-JIMS200074578 CHANGES
    public final static String BRIEFINGDETAILS_FORM = "JuvenileBriefingDetailsForm";

    /**
     * @param CompositeResponse
     * @return
     */
    public static Collection fetchCollection(CompositeResponse response, String responseEventTopic)
    {
	Map map = MessageUtil.groupByTopic(response);
	Collection objs = (Collection) map.get(responseEventTopic);
	objs = MessageUtil.processEmptyCollection(objs);

	return objs;
    }

    /*
     * @param juvenileNum
     * @return
     */
    public static Collection fetchJuvenileAbuses(String juvenileNum)
    {
	GetJuvenileAbuseListEvent event = (GetJuvenileAbuseListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEABUSELIST);

	event.setJuvenileNum(juvenileNum);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);

	IEvent replyEvent = dispatch.getReply();
	CompositeResponse composite = (CompositeResponse) replyEvent;
	Collection<JuvenileAbuseResponseEvent> abuses = MessageUtil.compositeToCollection(composite, JuvenileAbuseResponseEvent.class);
	Iterator<JuvenileAbuseResponseEvent> abuseItr = abuses.iterator();
	JuvenileAbuseResponseEvent juvAbuseResponse;
	while (abuseItr.hasNext())
	{
	    juvAbuseResponse = abuseItr.next();
	    String test = juvAbuseResponse.getLastName();
	    if (juvAbuseResponse.getFirstName() != null && !(juvAbuseResponse.getFirstName()).isEmpty())
	    {
		test += ", " + juvAbuseResponse.getFirstName() + " " + juvAbuseResponse.getMiddleName();

	    }
	    juvAbuseResponse.setFirstName(test);

	    String abuseId = juvAbuseResponse.getAbuseId();
	    GetJuvenileAbusePerpsListEvent event1 = (GetJuvenileAbusePerpsListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEABUSEPERPSLIST);
	    event1.setAbuseID(abuseId);
	    IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch1.postEvent(event1);

	    Iterator abusePerps = JuvenileAbusePerpatrator.findJuvenileAbusePerps((GetJuvenileAbusePerpsListEvent) event1);
	    Collection<JuvenileAbusePerpatrator> abusePrps = new ArrayList<JuvenileAbusePerpatrator>();
	    String firstNames = new String();
	    String middleNames = new String();
	    String lastNames = new String();
	    String relationshipToJuvenile = new String();
	    String namewithRelationships = new String();
	    //		StringBuffer nameBuffer= new StringBuffer();

	    while (abusePerps.hasNext())
	    {
		JuvenileAbusePerpatrator abusePerp = (JuvenileAbusePerpatrator) abusePerps.next();
		String fName = abusePerp.getPerpetratorLastName();
		String namewithRelationship=null;
		if (abusePerp.getPerpetratorFirstName() != null && !(abusePerp.getPerpetratorFirstName()).isEmpty())
		{
		    /*nameBuffer.append(abusePerp.getPerpetratorLastName());
		    nameBuffer.append(",");
		    nameBuffer.append(abusePerp.getPerpetratorFirstName());
		    nameBuffer.append(abusePerp.getPerpetratorMiddleName());
		    nameBuffer.append("<br />");*/
		    fName += ", " + abusePerp.getPerpetratorFirstName() + " " + abusePerp.getPerpetratorMiddleName() + " ;    ";
		}
		firstNames += fName;
		//namewithRelationships=fName+";";
		//	firstNames = nam.toString();
		String formJuvenileRelationId = abusePerp.getRelationshipToJuvenileId();
		Collection juvenileRealtionships = CodeHelper.getRelationshipsToJuvenileCodes();
		Iterator juvenileRealtionshipsItr = juvenileRealtionships.iterator();
		CodeResponseEvent juvenileRelation;

		String juvRelationDesc = new String();
		while (juvenileRealtionshipsItr.hasNext())
		{
		    juvenileRelation = (CodeResponseEvent) juvenileRealtionshipsItr.next();
		    String realtionshipCode = juvenileRelation.getCode();
		    if (realtionshipCode.equalsIgnoreCase(formJuvenileRelationId))
		    {
			juvRelationDesc = juvenileRelation.getDescription();
			juvRelationDesc += " ; ";
		    }
		}
		if(fName!=null)
		{
        		if(!fName.isEmpty()) 
        		{
        		    fName = fName.replace(fName.substring(fName.length()-5), "");
        		    //fName= fName.substring(0, fName.length() - 2);
        		 }
		}
		namewithRelationship=fName+" - "+juvRelationDesc;
		relationshipToJuvenile += juvRelationDesc;
		namewithRelationships+=namewithRelationship;
		abusePrps.add(abusePerp);

	    }
	    //add code to retrive all abuser relationships
	    GetJuvenileAbuserRelationshipListEvent event2 = (GetJuvenileAbuserRelationshipListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEABUSERRELATIONSHIPLIST);
	    event2.setAbuseID(abuseId);
	    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch2.postEvent(event2);
	    Iterator abuserelnsItr = JuvenileAbuserRelationship.findJuvenileAbuserRelationships((GetJuvenileAbuserRelationshipListEvent) event2);
	    //Collection<JuvenileAbuserRelationship> abuseRelns = new ArrayList<JuvenileAbuserRelationship>();
	    String abuserRelations="";
	    String abuserRelation="";
	    while (abuserelnsItr.hasNext())
		{
		    JuvenileAbuserRelationship abuseReln = (JuvenileAbuserRelationship) abuserelnsItr.next();
		    if (abuseReln!=null)
		    {
			abuserRelation = abuseReln.getAllegedabuserRelationship()+" ; ";
			abuserRelations += abuserRelation;
		    }
		}
	    if (abusePrps != null && !abusePrps.isEmpty())
	    {
		juvAbuseResponse.setAbusePrps(abusePrps);
		juvAbuseResponse.setRelationshipToJuvenileDescription(relationshipToJuvenile);
		juvAbuseResponse.setFirstName(firstNames);
		juvAbuseResponse.setMiddleName(middleNames);
		juvAbuseResponse.setLastName(lastNames);
		juvAbuseResponse.setNamewithRelationship(namewithRelationships);
		juvAbuseResponse.setAllegedAbuserRelationship(abuserRelations);
		//assign all abuser relationship to the response
	    }
	    

	}
	return abuses;
    }
    //epic 109828
    /*
     * @param juvenileNum
     * @return
     */
    public static Collection fetchJuvenileDualstatuses(String juvenileNum)
    {
	//write new function to get dual status
	GetJuvenileDualStatusListEvent event = (GetJuvenileDualStatusListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEDUALSTATUSLIST);
	event.setJuvenileNum(juvenileNum);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);

	IEvent replyEvent = dispatch.getReply();
	CompositeResponse composite = (CompositeResponse) replyEvent;
	Collection<JuvenileDualStatusResponseEvent> duals = MessageUtil.compositeToCollection(composite, JuvenileDualStatusResponseEvent.class);
	Iterator<JuvenileDualStatusResponseEvent> dualItr = duals.iterator();
	JuvenileDualStatusResponseEvent juvDualResponse;
	if(duals.size()!=0)
	{
        	while (dualItr.hasNext())
        	{
        	    juvDualResponse = dualItr.next();	    
        	    String dualId = juvDualResponse.getDualstatusId();
        	    //add code to retrive all cps services
        	    GetJuvenileDSservicesListEvent event2 = (GetJuvenileDSservicesListEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEDSSERVICESLIST);
        	    event2.setDualID(dualId);
        	    IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
        	    dispatch2.postEvent(event2);
        	    Iterator servicesItr = JuvenileDualStatusCPSServices.findJuvenileDSdpsServices((GetJuvenileDSservicesListEvent) event2);
        	    //Collection<JuvenileAbuserRelationship> abuseRelns = new ArrayList<JuvenileAbuserRelationship>();
        	    String dualCPSServices="";
        	    String dualCPSService="";
        	    while (servicesItr.hasNext())
        		{
        		JuvenileDualStatusCPSServices dualservice = (JuvenileDualStatusCPSServices) servicesItr.next();
        		    if (dualservice!=null)
        		    {
        			dualCPSService = dualservice.getCpsService()+" ; ";
        			dualCPSServices += dualCPSService;
        		    }
        		}
        	    juvDualResponse.setCPSServices(dualCPSServices);	    
        	}
	}
	else
	    duals=null;
	    //
	return duals;
    }
    //
    /*
     * @param myForm
     * @return
     */
    public static boolean hasMemberPrimaryInfoChanged(JuvenileMemberForm myForm)
    {
	Name origName = myForm.getOrigName();
	Name newName = myForm.getName();
	SocialSecurity origSSN = myForm.getOrigSSN();
	SocialSecurity newSSN = myForm.getSsn();

	if (newName != null && origName != null)
	{
	    if (!(newName.getFirstName().equalsIgnoreCase(origName.getFirstName())) || !(newName.getMiddleName().equalsIgnoreCase(origName.getMiddleName())) || !(newName.getLastName().equalsIgnoreCase(origName.getLastName())))
	    {
		return true;
	    }
	}

	if (newSSN != null && origSSN != null)
	{
	    //88726
	    if (!newSSN.getSSN1().contains("XXX"))
	    {
		if (!(newSSN.getSSN1().equalsIgnoreCase(origSSN.getSSN1())) || !(newSSN.getSSN2().equalsIgnoreCase(origSSN.getSSN2())) || !(newSSN.getSSN3().equalsIgnoreCase(origSSN.getSSN3())))
		{
		    return true;
		}
	    }
	    //88726
	}

	return (false);
    }
    
    public static JuvenileMemberForm SetPreviousAddressFromCurrent(JuvenileMemberForm juvForm){
	
	String streetName = juvForm.getCurrentAddress().getStreetName();
	String streetNumber = juvForm.getCurrentAddress().getStreetNumber();
	String aptNumber = juvForm.getCurrentAddress().getAptNumber();
	String city = juvForm.getCurrentAddress().getCity();
	String state = juvForm.getCurrentAddress().getState();
	String zipCode = juvForm.getCurrentAddress().getZipCode();
	String addressType = juvForm.getCurrentAddress().getAddressType();
	
	juvForm.getPreviousAddress().setStreetName(streetName);
	juvForm.getPreviousAddress().setStreetNumber(streetNumber);
	juvForm.getPreviousAddress().setAptNumber(aptNumber);
	juvForm.getPreviousAddress().setCity(city);
	juvForm.getPreviousAddress().setState(state);
	juvForm.getPreviousAddress().setZipCode(zipCode);
	juvForm.getPreviousAddress().setAddressType(addressType);
	
	return juvForm;
	
    }
    
    public static boolean memberAddressChanged(JuvenileMemberForm form)
    {
	if(form != null && form.getCurrentAddress() != null){
	    
	    MemberAddress currentAddress = form.getCurrentAddress();
	    MemberAddress previousAddress = form.getPreviousAddress();
		
	    if(currentAddress != null && currentAddress.getStreetName() != null){
		
		if(!currentAddress.getStreetName().trim().equalsIgnoreCase(previousAddress.getStreetName().trim())){
		    return true;
		}		
	    }
	    
	    if(currentAddress != null && currentAddress.getStreetNumber() != null){
		
		if(!currentAddress.getStreetNumber().trim().equalsIgnoreCase(previousAddress.getStreetNumber().trim())){
		    return true;
		}		
	    }
	    
//	    if(currentAddress != null && currentAddress.getStreetNum() != null){
//		
//		if(!currentAddress.getStreetNum().trim().equalsIgnoreCase(previousAddress.getStreetNum().trim())){
//		    return true;
//		}		
//	    }
	    
	    if(currentAddress != null && currentAddress.getAptNumber() != null){
		
		if(!currentAddress.getAptNumber().trim().equalsIgnoreCase(previousAddress.getAptNumber().trim())){
		    return true;
		}		
	    }
	    
	    if(currentAddress != null && currentAddress.getCity() != null){
		
		if(!currentAddress.getCity().trim().equalsIgnoreCase(previousAddress.getCity().trim())){
		    return true;
		}		
	    }
	    
	    if(currentAddress != null && currentAddress.getState() != null){
		
		if(!currentAddress.getState().trim().equalsIgnoreCase(previousAddress.getState().trim())){
		    return true;
		}		
	    }
	    
	    if(currentAddress != null && currentAddress.getZipCode() != null){
		
		if(!currentAddress.getZipCode().trim().equalsIgnoreCase(previousAddress.getZipCode().trim())){
		    return true;
		}		
	    }
	    
	    if(currentAddress != null && currentAddress.getAddressType() != null){
		
		if(!currentAddress.getAddressType().trim().equalsIgnoreCase(previousAddress.getAddressType().trim())){
		    return true;
		}		
	    }
	    
	    
	}
	
	return false;
    }
    
    public static boolean isCurrentAddressEmpty(JuvenileMemberForm form)
    {
	boolean result = false;
	
	if(form != null && form.getCurrentAddress() != null){
	    
	    MemberAddress currentAddress = form.getCurrentAddress();
		
	    if(currentAddress.getStreetName() == null || "".equals(currentAddress.getStreetName()) || currentAddress.getStreetNumber()== null || 
		    "".equals(currentAddress.getStreetNumber()) || currentAddress.getCity() == null || "".equals(currentAddress.getCity()) || 
			    currentAddress.getState() == null || "".equals(currentAddress.getState()) || 
			    currentAddress.getZipCode() == null || "".equals(currentAddress.getZipCode())){
		
		result = true;
	    } 	    
	    
	}
	
	return result;
    }
    
    public static DocketEventResponseEvent getJuvDocketEvent(String juvNum){
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
   	GetJJSCLCourtByJuvNumEvent jjsCLCrtEvent = (GetJJSCLCourtByJuvNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYJUVNUM);
   	jjsCLCrtEvent.setJuvenileNumber(juvNum);
   	dispatch.postEvent(jjsCLCrtEvent);
   	CompositeResponse resp = (CompositeResponse) dispatch.getReply();		
   	List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

   	Date fromDate=DateUtil.stringToDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_2),DateUtil.DATE_FMT_2);
   	
   	Collections.sort((List<DocketEventResponseEvent>) crtDktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
   		@Override
   		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
   		{
   		    return new CompareToBuilder().append(evt1.getChainNumber(), evt2.getChainNumber()).toComparison();
   		 }
   	    }));
   	
   	
   	DocketEventResponseEvent docRespEvent = null;
   	ArrayList<DocketEventResponseEvent> validItems = new ArrayList<DocketEventResponseEvent>();
   	
   	if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
   	{
   	    Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

   	    while (dktRespEvtsItr.hasNext())
   	    {
   		docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
   		
   		Date courtDate=DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1);

   		//get any future court dates
   		if (docRespEvent != null)
   		{
   		    if(courtDate.after(fromDate) && ("F".equalsIgnoreCase(docRespEvent.getPetitionStatus()) || "R".equalsIgnoreCase(docRespEvent.getPetitionStatus()))){
   			validItems.add(docRespEvent);
   		    }
   		    
   		}
   	    }
   	    
   	}
   	
   	//in the case of multiple records, get the one with highest chain number
   	DocketEventResponseEvent validRecord = null;
   	if(validItems.size() > 0){
   	    
   	   Integer index = 0;
   	   Integer maxChainNum = Integer.parseInt(validItems.get(index).getChainNumber());
   	   for(int i = 0; i < validItems.size(); i++){
   	   	    
   	       if(Integer.parseInt(validItems.get(i).getChainNumber()) > maxChainNum){
   		   maxChainNum = Integer.parseInt(validItems.get(i).getChainNumber());
   	   		index = i;
   	       } 	
   	       i = i +1;
   	   }
   	   	
   	   	validRecord = validItems.get(index);  	    	    
   	}
   	
   	return validRecord;
    }
    
    public static void sendAddressChangeEmailNofification(JuvenileMemberForm juvMemberForm)
    {
	
	String familyMemberNumber = juvMemberForm.getMemberNumber();	
	String memberFirstName = null;
        String memberLastName = null;
        String juvRelationship = null;
        
	JuvenileFamilyMemberViewResponseEvent familyMemberEvent = getFamilyMemberInfo(juvMemberForm);
	
	if(familyMemberEvent != null){
	    memberFirstName = familyMemberEvent.getFirstName();
	    memberLastName = familyMemberEvent.getLastName();
	    juvRelationship = familyMemberEvent.getJuvRelation();
	}        
	
	 GetJuvenileProfileMainEvent getJuvenileEvent = (GetJuvenileProfileMainEvent) EventFactory
	                .getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	        getJuvenileEvent.setJuvenileNum(juvMemberForm.getJuvenileNumber());
	        CompositeResponse replyEvent = MessageUtil.postRequest(getJuvenileEvent);
	        JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent,
	                JuvenileProfileDetailResponseEvent.class);
	        
	        //get the logged in user information
	        ISecurityManager manager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		String loginId = null;
		if(manager != null){
			loginId = manager.getIUserInfo().getJIMSLogonId();
		}
		
		if(loginId != null && !"".equals(loginId)){
		    loginId = loginId.toUpperCase();
		}
		
		//get the officer information
		Iterator offIter = OfficerProfile.findAll("logonId", loginId);
		    String officerName = "UNKNOWN";
		    String workPhoneNumber = "";
		    
		    	if( offIter.hasNext() ){	    	    
		    	    OfficerProfile officer = (OfficerProfile) offIter.next(); 
		    	    IName name = new Name(officer.getFirstName(), officer.getMiddleName(), officer.getLastName() );
		    	    if(  name!=null){
		    		officerName = name.getFormattedName(); 
		    		workPhoneNumber = officer.getWorkPhoneNum();
			    }			
		    	}
		    	
	boolean isGuardian = isFamilyMemberAGuardian(detail.getJuvenileNum(), familyMemberNumber);
	
	boolean isAddressEmpty = isCurrentAddressEmpty(juvMemberForm);

	if(isGuardian && detail != null && !isAddressEmpty){	    

	  StringBuilder sbMessage = new StringBuilder();    

	      sbMessage.append("\n");
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append("One of the guardians for youth, ".toUpperCase() + detail.getFirstName().toUpperCase() + " " + detail.getLastName().toUpperCase() + " (" + detail.getJuvenileNum() + "), ");
	      sbMessage.append("has a newly created address in JIMS2. ".toUpperCase());
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append(memberFirstName.toUpperCase() + " " + memberLastName.toUpperCase() + "(" + familyMemberNumber + "), THE " + juvRelationship.toUpperCase() + ", " + " HAS A NEW " + juvMemberForm.getCurrentAddress().getAddressType()+ " ADDRESS: ");
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append(System.getProperty("line.separator"));
	      if(juvMemberForm.getCurrentAddress().getAptNumber() != null && !"".equals(juvMemberForm.getCurrentAddress().getAptNumber())){
		  sbMessage.append(juvMemberForm.getCurrentAddress().getStreetNumber() + " " + juvMemberForm.getCurrentAddress().getStreetName().toUpperCase() + "  Apt " + juvMemberForm.getCurrentAddress().getAptNumber());
	      } else {
		  sbMessage.append(juvMemberForm.getCurrentAddress().getStreetNumber() + " " + juvMemberForm.getCurrentAddress().getStreetName().toUpperCase());
	      }	      
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append(juvMemberForm.getCurrentAddress().getCity().toUpperCase() + ", " + juvMemberForm.getCurrentAddress().getState().toUpperCase() + " " + juvMemberForm.getCurrentAddress().getZipCode());
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append("==================================");
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append("Previous Address: ");
	      sbMessage.append(System.getProperty("line.separator"));
	      if(juvMemberForm.getCurrentAddress().getAptNumber() != null && !"".equals(juvMemberForm.getCurrentAddress().getAptNumber())){
		  sbMessage.append(juvMemberForm.getPreviousAddress().getStreetNumber() + " " + juvMemberForm.getPreviousAddress().getStreetName().toUpperCase() + "  Apt " + juvMemberForm.getPreviousAddress().getAptNumber());
	      } else {
		  sbMessage.append(juvMemberForm.getPreviousAddress().getStreetNumber() + " " + juvMemberForm.getPreviousAddress().getStreetName().toUpperCase());
	      }
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append(juvMemberForm.getPreviousAddress().getCity().toUpperCase() + ", " + juvMemberForm.getPreviousAddress().getState().toUpperCase() + " " + juvMemberForm.getPreviousAddress().getZipCode());
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append("Address Type: " + juvMemberForm.getPreviousAddress().getAddressType().toUpperCase());
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append(System.getProperty("line.separator"));
	      sbMessage.append("For questions regarding this address change, please contact " + officerName + "(" + loginId + "), " + workPhoneNumber);	

	  
	    List<UserEntityBean> userGroup = PDSecurityHelper.getUserGroupByIdOrName("FAMILY MEMBER ADDRESS CHANGE NOTIFICATION GROUP", "");
	    if (userGroup != null)
	    {

		for (UserEntityBean user : userGroup)
		{

		    if (user.getEmail() != null && !user.getEmail().equals(""))
		    {

                	SendEmailEvent sendEmailEvent = new SendEmailEvent();
                	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
                	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, user.getEmail()); 
                	sendEmailEvent.setSubject("Youth, " + detail.getFirstName() + " " + detail.getLastName() + " (" + detail.getJuvenileNum() + "), Address Change Notification");
                	sendEmailEvent.setMessage(sbMessage.toString());
			
                	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
                	MessageUtil.processReturnException( res ) ;
		    }
		}
	    } 
//	    else {
//		
//		SendEmailEvent sendEmailEvent = new SendEmailEvent();
//        	sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
//        	UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, ""); 
//        	sendEmailEvent.setSubject("Address Change for Family Member of " + detail.getFirstName() + " " + detail.getLastName() + " (" + detail.getJuvenileNum() + ")");
//        	sendEmailEvent.setMessage(sbMessage.toString());
//		
//        	CompositeResponse res = MessageUtil.postRequest(sendEmailEvent);
//        	MessageUtil.processReturnException( res ) ;
//	    }
	}

    }
    
    public static JuvenileFamilyMemberViewResponseEvent getFamilyMemberInfo(JuvenileMemberForm juvMemberForm){
	
	JuvenileFamilyMemberViewResponseEvent familyRelation = null;
        juvMemberForm.setMaritalRelWithList(UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(juvMemberForm.getJuvenileNumber()));

        Iterator iter = juvMemberForm.getMaritalRelWithList().iterator();
      	while(iter.hasNext()) {
      		JuvenileFamilyMemberViewResponseEvent famMemberResp = (JuvenileFamilyMemberViewResponseEvent) iter.next();
      		
      		if(famMemberResp.getMemberNum().trim().equalsIgnoreCase(juvMemberForm.getMemberNumber().trim())){
      		    familyRelation = famMemberResp;
      		    break;
      		}
      	}

      	return familyRelation;
    }
    
    public static boolean isFamilyMemberAGuardian(String juvNumber, String familyMemberNumber)
    {
	boolean result = false;
	
	Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(juvNumber);
	if(dataMap!=null) {
		Collection guardians=new ArrayList();
		Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
	    if (families != null && families.size() > 0) {
	       Iterator myIter = families.iterator(); 
	        while (myIter.hasNext()) {
	            FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
	            if (myFamily.isActive()) {
	            	JuvenileFamilyForm.Constellation newFamily=new JuvenileFamilyForm.Constellation();
	            	Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
	            	newFamily.setFamilyNumber(myFamily.getFamilyNum());			            	

	            	UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,familiesMembers);
	            	Iterator iter =  newFamily.getMemberList().iterator();
		  			  while(iter.hasNext()) {
		  			  	JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();
		  			  	
		  			  	if(myMember.getMemberNumber().equals(familyMemberNumber) && myMember.isGuardian()){
		  			  	    result = true;
		  			  	    
		  			  	    return result;
		  			  	}
		  			  }
	            }
	        }
	    }
	}
	
	return result;
    }
    
    public static boolean isGuardianPrimaryContactAndInHome(String juvNumber, String familyMemberNumber)
    {
	boolean result = false;
	
	Map dataMap = UIJuvenileFamilyHelper.getActiveFamilyConstellation(juvNumber);
	if(dataMap!=null) {
		Collection guardians=new ArrayList();
		Collection families = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATIONS_TOPIC);
	    if (families != null && families.size() > 0) {
	       Iterator myIter = families.iterator(); 
	        while (myIter.hasNext()) {
	            FamilyConstellationListResponseEvent myFamily = (FamilyConstellationListResponseEvent) myIter.next();
	            if (myFamily.isActive()) {
	            	JuvenileFamilyForm.Constellation newFamily=new JuvenileFamilyForm.Constellation();
	            	Collection familiesMembers  = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_CONSTELLATION_MEMBER_LIST_TOPIC);
	            	newFamily.setFamilyNumber(myFamily.getFamilyNum());			            	

	            	UIJuvenileHelper.setJuvFamilyFormFROMMemberListRespEvt(newFamily,familiesMembers);
	            	Iterator iter =  newFamily.getMemberList().iterator();
		  			  while(iter.hasNext()) {
		  			  	JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList)iter.next();		  			  	
		  			  	
		  			  	if(myMember.getMemberNumber().equals(familyMemberNumber) && myMember.isGuardian() 
		  			  		&& myMember.isInHomeStatus() && myMember.isPrimaryContact()){
		  			  	    result = true;
		  			  	    
		  			  	    return result;
		  			  	}
		  			  }
	            }
	        }
	    }
	}
	
	return result;
    }

    /**
     * ER Changes JIMS200074578
     * 
     * @param memberId
     * @return boolean true/false.
     */
    public static boolean isMiltary(String memberId)
    {
	// Sending PD Request Event
	GetFamilyMemberEmploymentInfoEvent event = (GetFamilyMemberEmploymentInfoEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBEREMPLOYMENTINFO);
	event.setMemberNum(memberId);
	FamilyMemberEmploymentInfoResponseEvent responseEvt;
	List employments = MessageUtil.postRequestListFilter(event, FamilyMemberEmploymentInfoResponseEvent.class);
	Collections.sort(employments);
	if (employments != null && employments.size() > 0)
	{
	    Iterator iter = employments.iterator();
	    while (iter.hasNext())
	    {
		responseEvt = (FamilyMemberEmploymentInfoResponseEvent) iter.next();
		if (responseEvt != null)
		{
		    if (responseEvt.getStatusId().equalsIgnoreCase(UIConstants.MILITARY))
		    {
			return true;
		    }
		    return false;
		}
	    }
	}
	return false;
    }

    /**
     * ER Changes JIMS200074578
     * 
     * @param juvNum
     * @param traitsCategory
     * @return boolean true/false.
     */
    public static boolean isDyslexia(String juvNum, String traitsCategory)
    {
	String parentTraitId = null;
	GetTraitParentByCategoryEvent traitEvent = (GetTraitParentByCategoryEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETTRAITPARENTBYCATEGORY);
	traitEvent.setTraitCategoryName(traitsCategory);
	TraitTypeResponseEvent respEvent;
	List<TraitTypeResponseEvent> traits = MessageUtil.postRequestListFilter(traitEvent, TraitTypeResponseEvent.class);
	if (traits != null && traits.size() > 0)
	{
	    Iterator iter = traits.iterator();
	    while (iter.hasNext())
	    {
		respEvent = (TraitTypeResponseEvent) iter.next();
		if (respEvent != null)
		{
		    if (!respEvent.getTopic().equals("0"))
		    {
			parentTraitId = respEvent.getTopic();
		    }
		    else
		    {
			parentTraitId = respEvent.getTraitTypeId();
		    }
		}
	    }
	}
	//calling the second service to get traits for parent id and juv num.
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum(juvNum);
	traitByParentEvent.setTraitType(parentTraitId);
	List<JuvenileTraitResponseEvent> traitsList = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);
	if (traitsList != null && traitsList.size() > 0)
	{
	    Collections.sort(traitsList);
	    JuvenileTraitResponseEvent trait = traitsList.get(0); // considering the most recent record in the list after sorting. 
	    if (trait.getTraitTypeName().equalsIgnoreCase(UIConstants.EDUCATIONAL_PERFORMANCE) && trait.getTraitTypeDescription().equalsIgnoreCase(UIConstants.DYSLEXIA) && !trait.getStatus().equalsIgnoreCase(UIConstants.DENIES))
	    {
		return true;
	    }
	    return false;
	}
	return false;
    }

    /**
     * @param fromMemberNumber
     * @param fromMemberName
     * @param toMemberNumber
     * @param toMemberName
     */
    public static void sendOutJPONotificationForMemberMerge(List jposList, String fromMemberNumber, String fromMemberName, String toMemberNumber, String toMemberName, List juvList)
    {
	if (nullOrEmptyString(fromMemberNumber) || nullOrEmptyString(toMemberNumber))
	{
	    return;
	}

	String affectedJuvStr = "";
	int listSize = juvList.size() - 1;
	for (int x = 0; x < juvList.size(); x++)
	{
	    AssociatedJuvenilesResponseEvent ajrEvent = (AssociatedJuvenilesResponseEvent) juvList.get(x);

	    if (ajrEvent.getJuvName() != null)
	    {
		affectedJuvStr = " " + ajrEvent.getJuvName().getFirstName() + " " + ajrEvent.getJuvName().getLastName() + ",";
	    }
	    affectedJuvStr += " Juvenile #: " + ajrEvent.getJuvId();
	    if (x < listSize)
	    {
		affectedJuvStr += ",";
	    }
	}
	// Compose Message				
	String msg = "TSD has merged " + fromMemberName + ", Member#: " + fromMemberNumber + " with " + toMemberName + ", Member#: " + toMemberNumber + ", " + fromMemberName + ", Member#: " + fromMemberNumber + " no longer exists in JIMS2. " + "The following juveniles were affected: + " + affectedJuvStr + ".  Please review the constellation for your assigned juvenile(s).";

	String subject = "Family Member record Merge";

	for (int x = 0; x < jposList.size(); x++)
	{
	    // create a response event to send
	    JPOsForFamilyMemberResponseEvent jpoMember = (JPOsForFamilyMemberResponseEvent) jposList.get(x);
	    jpoMember.setNotificationMessage(msg);
	    // sets the user logged on to the Identity
	    jpoMember.setIdentity(jpoMember.getOfficerUserId());
	    sendOutMemberNotification(jpoMember, UIConstants.FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF, subject);
	}

    }

    /*
     * @param myForm
     * @param aRequest
     */
    public static void sendOutJPONotificationForMemberReplace(List jposList, String fromMemberNumber, String fromMemberName, String toMemberNumber, String toMemberName, List juvList)
    {
	if (nullOrEmptyString(fromMemberNumber) || nullOrEmptyString(toMemberNumber))
	{
	    return;
	}
	String affectedJuvStr = "";
	int listSize = juvList.size() - 1;
	for (int x = 0; x < juvList.size(); x++)
	{
	    AssociatedJuvenilesResponseEvent ajrEvent = (AssociatedJuvenilesResponseEvent) juvList.get(x);
	    affectedJuvStr = " " + ajrEvent.getJuvName().getFirstName() + " " + ajrEvent.getJuvName().getLastName() + ",";
	    affectedJuvStr += " Juvenile #: " + ajrEvent.getJuvId();
	    if (x < listSize)
	    {
		affectedJuvStr += ",";
	    }
	}
	// Compose Message				
	String msg = "TSD has replaced " + fromMemberName + ", Member#: " + fromMemberNumber + " with " + toMemberName + ", Member#: " + toMemberNumber + ", " + fromMemberName + ", Member#: " + fromMemberNumber + " no longer exists in JIMS2. " + "The following juveniles were affected: + " + affectedJuvStr + ".  Please review the constellation for your assigned juvenile(s).";

	String subject = "Family Member record Replacement";
	for (int x = 0; x < jposList.size(); x++)
	{
	    // create a response event to send
	    JPOsForFamilyMemberResponseEvent jpoMember = (JPOsForFamilyMemberResponseEvent) jposList.get(x);
	    jpoMember.setNotificationMessage(msg);
	    // sets the user logged on to the Identity
	    jpoMember.setIdentity(jpoMember.getOfficerUserId());
	    sendOutMemberNotification(jpoMember, UIConstants.FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF, subject);
	    affectedJuvStr = null;
	    msg = null;
	    subject = null;
	}
    }

    /*
     * @param myForm
     * @param aRequest
     */
    public static void sendOutJPONotificationForMemberUpdate(String memberNumber, boolean hasActiveCase)
    {
	if (memberNumber == null || nullOrEmptyString(memberNumber))
	{
	    return;
	}
	// add active casefile check for ER 70083 
	if (hasActiveCase == false)
	{
	    return;
	}

	Collection<JPOsForFamilyMemberResponseEvent> jposList = UIJuvenileHelper.getJPOsForMember(memberNumber);

	if (notNullNotEmptyCollection(jposList))
	{
	    String userId = UIUtil.getCurrentUserID();
	    String userName = UIUtil.getCurrentUserName();
	    IName iUserName = UIUtil.getNameFromString(userName);
	    userName = iUserName.getCompleteFullNameFirst().trim();
	    iUserName = null;
	    String subject = "Family Member Information Has Been Changed";

	    for (JPOsForFamilyMemberResponseEvent jpoMember : jposList)
	    {
		if (jpoMember != null && (jpoMember.getOfficerUserId() != null && userId != null && !(jpoMember.getOfficerUserId().equals(userId))))
		{
		    String memberName = jpoMember.getMemberFirstName() + " ";
		    if (jpoMember.getMemberMiddleName() != null & !"".equalsIgnoreCase(jpoMember.getMemberMiddleName()))
		    {
			memberName += jpoMember.getMemberMiddleName() + " ";
		    }
		    memberName += jpoMember.getMemberLastName().trim();
		    // Compose Message and sent to response event
		    String updateMsg = "Member information for " + memberName + ", " + jpoMember.getRelationship() + ", (" + jpoMember.getJuvenileFirstName() + " " + jpoMember.getJuvenileLastName() + ") has been updated by TSD, " + "(" + userName + "). Please Review.";
		    memberName = null;
		    userName = null;
		    jpoMember.setNotificationMessage(updateMsg);
		    // sets the user logged on to the Identity
		    jpoMember.setIdentity(jpoMember.getOfficerUserId());
		    sendOutMemberNotification(jpoMember, UIConstants.FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF, subject);
		}
	    }
	}
    }

    /*
     * @param myForm
     * @param aRequest
     */
    public static void sendOutJPONotificationForSuspiciousMemberUpdate(String memberNumber, List juvList)
    {
	if (memberNumber == null || nullOrEmptyString(memberNumber))
	{
	    return;
	}
	Collection<JPOsForFamilyMemberResponseEvent> jposList = UIJuvenileHelper.getJPOsForMember(memberNumber);

	if (notNullNotEmptyCollection(jposList))
	{
	    String affectedJuvStr = "";
	    int listSize = juvList.size() - 1;
	    for (int x = 0; x < juvList.size(); x++)
	    {
		AssociatedJuvenilesResponseEvent ajrEvent = (AssociatedJuvenilesResponseEvent) juvList.get(x);
		affectedJuvStr = " " + ajrEvent.getJuvName().getFirstName() + " " + ajrEvent.getJuvName().getLastName() + ",";
		affectedJuvStr += " Juvenile #: " + ajrEvent.getJuvId();
		if (x < listSize)
		{
		    affectedJuvStr += ",";
		}
	    }
	    String subject = "Family Member Information Has Been Changed";
	    String userId = UIUtil.getCurrentUserID();
	    for (JPOsForFamilyMemberResponseEvent jpoMember : jposList)
	    {
		if (jpoMember != null && (jpoMember.getOfficerUserId() != null && userId != null && !(jpoMember.getOfficerUserId().equals(userId))))
		{
		    String memberName = jpoMember.getMemberFirstName() + " ";
		    if (jpoMember.getMemberMiddleName() != null & !"".equalsIgnoreCase(jpoMember.getMemberMiddleName()))
		    {
			memberName += jpoMember.getMemberMiddleName() + " ";
		    }
		    memberName += jpoMember.getMemberLastName().trim();
		    // Compose Message and sent to response event
		    String msg = "The following family member records were identified as having the same Social Security number: " + memberName + ", Member #: " + memberNumber + ". TSD modified the information for Member#:" + memberNumber + " and the suspicious flag was removed from the records. " + "The constellations for the following juveniles were affected: " + affectedJuvStr + ".  Please review the constellations for your assigned juvenile(s).";
		    jpoMember.setNotificationMessage(msg);
		    // sets the user logged on to the Identity
		    jpoMember.setIdentity(jpoMember.getOfficerUserId());
		    sendOutMemberNotification(jpoMember, UIConstants.FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF, subject);
		    memberName = null;
		    msg = null;
		}
	    }
	    affectedJuvStr = null;
	    subject = null;
	}
    }

    /*
     * @param myForm
     * @param aRequest
     */
    public static void sendOutJPONotificationForMemberRemove(String memberNumber, List juvList)
    {
	if (memberNumber == null || nullOrEmptyString(memberNumber))
	{
	    return;
	}
	Collection<JPOsForFamilyMemberResponseEvent> jposList = UIJuvenileHelper.getJPOsForMember(memberNumber);

	if (notNullNotEmptyCollection(jposList))
	{
	    String affectedJuvStr = "";
	    int listSize = juvList.size() - 1;
	    for (int x = 0; x < juvList.size(); x++)
	    {
		AssociatedJuvenilesResponseEvent ajrEvent = (AssociatedJuvenilesResponseEvent) juvList.get(x);
		affectedJuvStr = " " + ajrEvent.getJuvName().getFirstName() + " " + ajrEvent.getJuvName().getLastName() + ",";
		affectedJuvStr += " Juvenile #: " + ajrEvent.getJuvId();
		if (x < listSize)
		{
		    affectedJuvStr += ",";
		}
	    }
	    String subject = "Family Member Information Has Been Changed";
	    String userId = UIUtil.getCurrentUserID();
	    for (JPOsForFamilyMemberResponseEvent jpoMember : jposList)
	    {
		if (jpoMember != null && (jpoMember.getOfficerUserId() != null && userId != null && !(jpoMember.getOfficerUserId().equals(userId))))
		{
		    String memberName = jpoMember.getMemberFirstName() + " ";
		    if (jpoMember.getMemberMiddleName() != null & !"".equalsIgnoreCase(jpoMember.getMemberMiddleName()))
		    {
			memberName += jpoMember.getMemberMiddleName() + " ";
		    }
		    memberName += jpoMember.getMemberLastName().trim();
		    // Compose Message and sent to response event
		    String msg = "The following family member records were identified as having the same Social Security number: " + memberName + ", Member #: " + memberNumber + ". Following additional research by TSD, the suspicious member flag has been removed. " + "The constellations for the following juveniles were affected: " + affectedJuvStr + ".  Please review the constellations for your assigned juvenile(s).";
		    jpoMember.setNotificationMessage(msg);
		    // sets the user logged on to the Identity
		    jpoMember.setIdentity(jpoMember.getOfficerUserId());
		    sendOutMemberNotification(jpoMember, UIConstants.FAMILYMEMBER_PRIMARYINFO_UPDATED_NOTIF, subject);
		    memberName = null;
		    msg = null;
		}
	    }
	    affectedJuvStr = null;
	    subject = null;
	}
    }

    /*
     * @param aMatchName
     * @param aMatchSSN
     * @param aMatchId
     * @param aOrigName
     * @param aOrigSSN
     * @param aOrigId
     */
    public static void sendOutJPONotificationForDuplicateMemberAlert(Name aMatchName, SocialSecurity aMatchSSN, String aMatchId, Name aOrigName, SocialSecurity aOrigSSN, String aOrigId)
    {
	notificationForDuplicateMember(aMatchName, aMatchSSN, aMatchId, aOrigName, aOrigSSN, aOrigId);
	notificationForDuplicateMember(aOrigName, aOrigSSN, aOrigId, aMatchName, aMatchSSN, aMatchId);
    }

    /*
     * @param aMatchName
     * @param aMatchSSN
     * @param aMatchId
     * @param aOrigName
     * @param aOrigSSN
     * @param aOrigId
     */
    private static void notificationForDuplicateMember(Name aMatchName, SocialSecurity aMatchSSN, String aMatchId, Name aOrigName, SocialSecurity aOrigSSN, String aOrigId)
    {
	if (notNullNotEmptyString(aOrigId) && notNullNotEmptyString(aMatchId))
	{
	    Collection<JPOsForFamilyMemberResponseEvent> jposList = UIJuvenileHelper.getJPOsForMember(aOrigId);
	    if (notNullNotEmptyCollection(jposList))
	    {
		String userId = UIUtil.getCurrentUserID();
		String userName = UIUtil.getCurrentUserName();
		String subject = "A Possible Duplicate Family Member Has Been Detected";

		for (JPOsForFamilyMemberResponseEvent jpoMember : jposList)
		{
		    if (jpoMember != null && (jpoMember.getOfficerId() != null && userId != null && !(jpoMember.getOfficerId().equals(userId))))
		    {
			String newIdentity = jpoMember.getOfficerId();
			if ((newIdentity != null) && (!newIdentity.equals("")))
			{
			    jpoMember.setIdentity(newIdentity);
			}
			// Compose Message and sent to response event
			String duplicateMsg = aOrigName.getFirstName() + " " + aOrigName.getLastName() + " (" + aOrigSSN.getFormattedSSN() + "), " + "member number (" + aOrigId + ") on the active constellation for juvenile #" + jpoMember.getJuvenileId() + " ,  has been identified by JPO " + userName + " as a possible duplicate record of " + aMatchName.getFirstName() + " " + aMatchName.getLastName() + " (" + aMatchSSN.getFormattedSSN() + ") , member number (" + aMatchId + ").";

			jpoMember.setNotificationMessage(duplicateMsg);
			sendOutMemberNotification(jpoMember, UIConstants.DUPLICATE_FAMILYMEMBER_FOUND_NOTIF, subject);
		    }
		} // for
	    }
	}
    }

    /*
     * @param jpoMember
     * @param aNotifTopic
     * @param aSubject
     */
    public static void sendOutMemberNotification(JPOsForFamilyMemberResponseEvent jpoMember, String aNotifTopic, String aSubject)
    {
	CreateNotificationEvent notificationEvent = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);

	notificationEvent.setNotificationTopic(aNotifTopic);
	notificationEvent.setSubject(aSubject);
	notificationEvent.addIdentity("juvenileProbationOfficer", (IAddressable) jpoMember);
	notificationEvent.addContentBean(jpoMember);

	EventManager.getSharedInstance(EventManager.REQUEST).postEvent(notificationEvent);
    }

    /*
     * @param aMemberId
     * @param aMemberId
     * @return
     */
    public static List loadJPOs(String fromMemberNumber, String toMemberNumber)
    {
	List jposList = new ArrayList();
	List jposList2 = (List) UIJuvenileHelper.getJPOsForMember(fromMemberNumber);
	if (jposList2 != null && !jposList2.isEmpty())
	{
	    jposList.addAll(jposList2);
	}
	jposList2 = (List) UIJuvenileHelper.getJPOsForMember(toMemberNumber);
	if (jposList2 != null && !jposList2.isEmpty())
	{
	    jposList.addAll(jposList2);
	}
	jposList2 = new ArrayList();

	if (jposList != null && !jposList.isEmpty())
	{
	    // check for duplicate jpos
	    if (jposList.size() > 1)
	    {
		Iterator iter = jposList.iterator();
		SortedMap map = new TreeMap();
		while (iter.hasNext())
		{
		    JPOsForFamilyMemberResponseEvent jpoMember = (JPOsForFamilyMemberResponseEvent) iter.next();
		    map.put(jpoMember.getOfficerId(), jpoMember);
		}
		jposList2.addAll(map.values());
	    }
	    else
	    {
		jposList2.addAll(jposList);
	    }
	}
	return jposList;
    }

    /*
     * @param aMemberId
     * @return
     */
    public static Collection getJPOsForMember(String aMemberId)
    {
	GetJPOsForFamilyMemberEvent event = (GetJPOsForFamilyMemberEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETJPOSFORFAMILYMEMBER);
	event.setFamilyMemberId(aMemberId);

	CompositeResponse composite = MessageUtil.postRequest(event);

	Map dataMap = MessageUtil.groupByTopic(composite);
	Collection jpos = null;
	if (dataMap != null)
	{
	    jpos = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_NOTIFY_JPO_TOPIC);
	    if (nullOrEmptyCollection(jpos))
	    {
		return null;
	    }
	}

	composite = null;
	dataMap = null;
	event = null;

	return jpos;
    }

    /*
     * @return
     */
    static public Collection fetchAliasNameTypes()
    {
	GetAllAliasNameTypeCodesEvent getEvent = (GetAllAliasNameTypeCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLALIASNAMETYPECODES);

	List aliasCodes = MessageUtil.postRequestListFilter(getEvent, CodeResponseEvent.class);

	return aliasCodes;
    }
    static public List<CodeResponseEvent> fetchMAYSIreasoncodes()
    {
	GetAllMAYSIReasonCodesEvent getEvent = (GetAllMAYSIReasonCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETALLMAYSIREASONCODES);

	List<CodeResponseEvent> maysiCodes = MessageUtil.postRequestListFilter(getEvent, CodeResponseEvent.class);

	return maysiCodes;
    }
    
    static public Collection fetchSchoolDistricts()
    {
	GetSchoolDistrictsEvent getEvent = (GetSchoolDistrictsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETSCHOOLDISTRICTS);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getEvent);

	CompositeResponse composite = (CompositeResponse) dispatch.getReply();

	Map eventMap = MessageUtil.groupByTopic(composite);
	Collection schoolDistricts = (Collection) eventMap.get(PDCodeTableConstants.SCHOOL_CODE);

	return schoolDistricts;
    }

    /*
     * @param juvenileNum
     * @return
     */
    static public Collection fetchSchoolHistory(String juvenileNum)
    {
	Collection schools = null;

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJuvenileSchoolEvent event = (GetJuvenileSchoolEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILESCHOOL);

	event.setJuvenileNum(juvenileNum);
	dispatch.postEvent(event);

	CompositeResponse composite = (CompositeResponse) dispatch.getReply();
	schools = MessageUtil.compositeToCollection(composite, JuvenileSchoolHistoryResponseEvent.class);

	return schools;
    }

    static public List buildSchoolHistory(String juvenileNum)
    {

	Collection schoolHistory = fetchSchoolHistory(juvenileNum);
	List returnSchoolHistoryList = new ArrayList();

	Iterator<JuvenileSchoolHistoryResponseEvent> i = schoolHistory.iterator();
	while (i.hasNext())
	{
	    JuvenileSchoolHistoryResponseEvent school = i.next();

	    SchoolHistoryTO to = new SchoolHistoryTO();
	    to.setOID(school.getSchoolHistoryId());
	    to.setEntryDate(school.getCreateDate());
	    to.setGPA(school.getGradeAverage());
	    to.setVerifiedDate(school.getVerifiedDate());
	    to.setGradeLevel(school.getGradeLevelCode() != null ? school.getGradeLevelDescription() : "");
	    to.setAppropriateLevel(school.getAppropriateLevelCode() != null ? school.getAppropriateLevelDescription() : "");
	    to.setEnrollmentStatus(school.getExitTypeCode() != null ? school.getExitTypeDescription() : "");
	    to.setEducationService(school.getEducationService());
	    to.setLastDateAttended(school.getLastAttendedDate());
	    //ER Changes JIMS200077279 starts 
	    to.setEligibilityEnrollmentDate(school.getEligibilityEnrollmentDate());
	    //ER Changes JIMS200077279 ends 
	    to.setStatus(school.getSchoolAttendanceStatusCode() != null ? school.getSchoolAttendanceStatusDescription() : "");
	    //GED information
	    to.setCompletionDate(school.getCompletionDate());
	    to.setGedCompleted(school.isGedCompleted());
	    to.setGedAwarded(school.isGedAwarded());
	    to.setGedAwardedDate(school.getGedAwardedDate());

	    if (school.getProgramAttendingCode() != null)
	    {
		to.setProgram(school.getProgramAttendingDescription());
	    }

	    if (school.getParticipationCode() != null)
	    {
		to.setParticipation(school.getParticipationDescription());
	    }

	    if (school.getRuleInfractionCode() != null)
	    {
		to.setRuleInfraction(school.getRuleInfractionDescription());
	    }

	    if (school.getGradesRepeatedCode() != null)
	    {
		to.setGradesRepeated(school.getGradesRepeatedDescription());
	    }

	    to.setNumberOfGradeRepeated(school.getGradesRepeatNumber());
	    
	    if (school.getSchool() != null) {
		    String instructionType = school.getInstructionType();
		    if (instructionType != null && !instructionType.isEmpty()) {
		        to.setDistrict(school.getSchoolDistrict() + ", " + school.getSchool() + ": " + instructionType);
		    } else {
		        to.setDistrict(school.getSchoolDistrict() + ", " + school.getSchool());
		    }
		}
	    
	    to.setSpecificSchoolName(school.getSpecificSchoolName());
	    returnSchoolHistoryList.add(to);
	}

	return returnSchoolHistoryList;
    }

    /**
     * @param releaseEvent
     * @return CompositeResponse
     */
    public static CompositeResponse getCompositeResponse(RequestEvent requestEvent)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(requestEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();

	return response;
    }

    /**
     * It's expected that only one of the boolean parameters will be true. If
     * both are true both headers will be searched for a valid juvenile number,
     * note this is dangerous and may lead to a contamination of headers and
     * should only be used when absolutely necessary.
     * 
     * @param aRequest
     * @param isSearchJuvHeader
     * @param isSearchCasefileHeader
     * @return
     */
    public static String getJuvenileNumber(HttpServletRequest aRequest, boolean isSearchJuvHeader, boolean isSearchCasefileHeader)
    {
	// Get Juvenile Number from Header Form
	String juvenileNum = UIConstants.EMPTY_STRING;

	if (isSearchJuvHeader)
	{
	    JuvenileProfileForm headerForm = UIJuvenileHelper.getHeaderForm(aRequest);
	    if (headerForm != null)
	    {
		juvenileNum = headerForm.getJuvenileNum();
	    }
	}

	if (nullOrEmptyString(juvenileNum))
	{
	    if (isSearchCasefileHeader)
	    {
		JuvenileCasefileForm myCasefileForm = UIJuvenileHelper.getJuvenileCasefileForm(aRequest);
		if (myCasefileForm != null)
		{
		    juvenileNum = myCasefileForm.getJuvenileNum();
		}
	    }
	}

	if (nullOrEmptyString(juvenileNum))
	{
	    juvenileNum = aRequest.getParameter(PDJuvenileCaseConstants.JUVENILENUM_PARAM);
	}

	return juvenileNum;
    }

    /*
     * @param aRequest
     */
    public static void mapCasefileHeaderToJuvHeaderForm(HttpServletRequest aRequest)
    {
	JuvenileCasefileForm myCasefileForm = getJuvenileCasefileForm(aRequest);
	if (myCasefileForm == null)
	{
	    return;
	}

	JuvenileProfileForm myJuvenileProfileForm = getHeaderForm(aRequest, true);
	myJuvenileProfileForm.setJuvenileNum(myCasefileForm.getJuvenileNum());
	myJuvenileProfileForm.setAge(myCasefileForm.getCurrentAge());
	myJuvenileProfileForm.setJuvenileName(myCasefileForm.getJuvenileFullName());
	myJuvenileProfileForm.setStatus(myCasefileForm.getCaseStatus());
    }

    /**
     * Added to get the header form for juvenile profile
     * 
     * @return HttpServletRequest
     */
    public static JuvenileProfileForm getHeaderForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileProfileForm headerForm = (JuvenileProfileForm) session.getAttribute(HEADER_FORM);

	return headerForm;
    }

    /*
     * @param aRequest
     * @param isCreate
     * @return
     */
    public static JuvenilePhotoForm getJuvPhotoForm(HttpServletRequest aRequest, boolean isCreate)
    {
	HttpSession session = aRequest.getSession();
	JuvenilePhotoForm myForm = null;

	Object myObj = session.getAttribute(JUV_PHOTO_FORM);
	if (myObj == null && isCreate)
	{
	    myForm = new JuvenilePhotoForm();
	    session.setAttribute(JUV_PHOTO_FORM, myForm);
	}
	else
	{
	    myForm = (JuvenilePhotoForm) myObj;
	}

	return myForm;
    }

    /*
     * @param aRequest
     * @param isCreate
     * @return
     */
    public static JuvenileProfileForm getHeaderForm(HttpServletRequest aRequest, boolean isCreate)
    {
	JuvenileProfileForm myForm = getHeaderForm(aRequest);

	if (myForm == null)
	{
	    HttpSession session = aRequest.getSession();
	    myForm = new JuvenileProfileForm();
	    session.setAttribute(HEADER_FORM, myForm);
	}

	return myForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static JuvenileFamilyForm getFamilyForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileFamilyForm familyForm = (JuvenileFamilyForm) session.getAttribute(FAMILY_FORM);

	return familyForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static JuvenileMemberForm getMemberForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileMemberForm memberForm = (JuvenileMemberForm) session.getAttribute(MEMBER_FORM);

	return memberForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static JuvenileBriefingDetailsForm getJuvBriefingDetailsForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) session.getAttribute(BRIEFINGDETAILS_FORM);

	return briefingDetailsForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static JuvenileCasefileForm getJuvenileCasefileForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) session.getAttribute(JUVENILE_CASEFILE_FORM);

	return casefileForm;
    }

    /**
     * Method to fetch Juvenile Details
     * 
     * @param aRequest
     * @return
     */
    public static JuvenileProfileDetailResponseEvent getJuvenileDetailForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileProfileDetailResponseEvent casefileForm = (JuvenileProfileDetailResponseEvent) session.getAttribute(JUVENILE_DETAIL_FORM);

	return casefileForm;
    }

    /*
     * @param aRequest
     * @param isCreate
     * @return
     */
    public static JuvenileCasefileForm getJuvenileCasefileForm(HttpServletRequest aRequest, boolean isCreate)
    {
	JuvenileCasefileForm myForm = getJuvenileCasefileForm(aRequest);
	if (myForm == null)
	{
	    HttpSession session = aRequest.getSession();
	    myForm = new JuvenileCasefileForm();
	    session.setAttribute(JUVENILE_CASEFILE_FORM, myForm);
	}

	return myForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static CasefileClosingForm getJuvenileCasefileClosingForm(HttpServletRequest aRequest, String casefileId)
    {
	HttpSession session = aRequest.getSession();

	CasefileClosingForm form = (CasefileClosingForm) session.getAttribute(JUVENILE_CASEFILE_CLOSING_FORM);
	//#bug fix 35750 starts
	if (form == null || !form.getSupervisionNumber().equals(casefileId))
	{
	    form = new CasefileClosingForm();
	    //session.setAttribute( JUVENILE_CASEFILE_CLOSING_FORM, form );

	    CasefileClosingResponseEvent event = UIJuvenileCasefileClosingHelper.getCasefileClosingDetails(casefileId);
	    if (event != null)
	    {
		UIJuvenileCasefileClosingHelper.setClosingInfoFROMClosingRespEvt(form, event);
	    }

	    session.setAttribute(JUVENILE_CASEFILE_CLOSING_FORM, form);
	    //#bug fix 35750 ends
	}

	return form;
    }

    /*
     * @param aRequest
     * @return
     */
    public static ResidentialExitPlanForm getJuvenileCasefileResidentialExitPlanForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	ResidentialExitPlanForm form = (ResidentialExitPlanForm) session.getAttribute(JUVENILE_RESIDENTIAL_EXIT_PLAN_FORM);

	return form;
    }

    /*
     * @param aRequest
     * @return
     */
    public static CommonAppForm getJuvenileCasefileCommonAppForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	CommonAppForm form = (CommonAppForm) session.getAttribute(JUVENILE_COMMONAPP_FORM);

	return form;
    }

    /*
     * @param aRequest
     * @param createIfNonExistent
     * @return
     */
    public static JuvenileMemberSearchForm getJuvenileMemberSeachForm(HttpServletRequest aRequest, boolean createIfNonExistent)
    {
	HttpSession session = aRequest.getSession();
	JuvenileMemberSearchForm mySearchForm = null;

	Object myObj = session.getAttribute(MEMBER_SEARCH_FORM);
	if (myObj == null)
	{
	    if (createIfNonExistent)
	    {
		mySearchForm = new JuvenileMemberSearchForm();
		session.setAttribute(MEMBER_SEARCH_FORM, mySearchForm);

		return mySearchForm;
	    }
	    else
	    {
		return null;
	    }
	}

	mySearchForm = (JuvenileMemberSearchForm) myObj;

	return mySearchForm;
    }

    /**
     * Added to get the traits form for juvenile profile
     * 
     * @return HttpServletRequest
     */
    public static TraitsForm getTraitsForm(HttpServletRequest aRequest)
    {

	HttpSession session = aRequest.getSession();
	TraitsForm traitsForm = (TraitsForm) session.getAttribute(TRAITS_FORM);

	return traitsForm;
    }

    /*
     * @param aRequest
     * @return
     */
    public static JuvenileAbuseForm getJuvenileAbuseForm(HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	JuvenileAbuseForm form = (JuvenileAbuseForm) session.getAttribute(JUVENILE_ABUSE_FORM);

	return form;
    }

    /*
     * @param aRequest
     * @param isCreate
     * @return
     */
    public static JuvenileAbuseForm getJuvenileAbuseForm(HttpServletRequest aRequest, boolean isCreate)
    {
	JuvenileAbuseForm myForm = getJuvenileAbuseForm(aRequest);
	if (myForm == null)
	{
	    HttpSession session = aRequest.getSession();
	    myForm = new JuvenileAbuseForm();
	    session.setAttribute(JUVENILE_ABUSE_FORM, myForm);
	}

	return myForm;
    }

    /*
     * @param aRequest
     * @param create
     * @return
     */
    public static TraitsForm getTraitsForm(HttpServletRequest aRequest, boolean create)
    {
	HttpSession session = aRequest.getSession();
	TraitsForm traitsForm = (TraitsForm) session.getAttribute(TRAITS_FORM);

	if (create && traitsForm == null)
	{
	    traitsForm = new TraitsForm();
	    putTraitsForm(traitsForm, aRequest);
	}

	return traitsForm;
    }

    /*
     * @param aRequest
     * @param create
     * @return
     */
    public static MedicalForm getMedicalForm(HttpServletRequest aRequest, boolean create)
    {
	HttpSession session = aRequest.getSession();
	MedicalForm medForm = (MedicalForm) session.getAttribute(MEDICAL_FORM);

	if (create && medForm == null)
	{
	    medForm = new MedicalForm();
	    session.setAttribute(MEDICAL_FORM, medForm);
	}

	return medForm;
    }

    //ER_GANG_JIMS200074578 CHANGES STARTS
    /*Adds the gang form in the session.
     * @param aRequest
     * @param create
     * @return
     */
    public static JuvenileGangsForm getGangsForm(HttpServletRequest aRequest, boolean create)
    {
	HttpSession session = aRequest.getSession();
	JuvenileGangsForm gangsForm = (JuvenileGangsForm) session.getAttribute(JUVENILE_GANGS_FORM);

	if (create && gangsForm == null)
	{
	    gangsForm = new JuvenileGangsForm();
	    putGangsForm(gangsForm, aRequest);
	}

	return gangsForm;
    }

    //ER_GANG_JIMS200074578 CHANGES ENDS
    /**
     * @param traitsForm
     */
    private static void putTraitsForm(TraitsForm traitsForm, HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	session.setAttribute(TRAITS_FORM, traitsForm);
    }

    //ER_GANG_JIMS200074578 CHANGES STARTS
    /**
     * adds the gang form in the session.
     * 
     * @param JuvenileGangsForm
     */
    private static void putGangsForm(JuvenileGangsForm gangsForm, HttpServletRequest aRequest)
    {
	HttpSession session = aRequest.getSession();
	session.setAttribute(JUVENILE_GANGS_FORM, gangsForm);
    }

    //ER_GANG_JIMS200074578 CHANGES ENDS

    /*
     * @param aRequest
     * @param create
     * @return
     */
    public static JuvenileGEDProgramForm getGEDProgramForm(HttpServletRequest aRequest, boolean create)
    {
	HttpSession session = aRequest.getSession();
	JuvenileGEDProgramForm gedForm = (JuvenileGEDProgramForm) session.getAttribute(GED_PROGRAM_FORM);

	if (create && gedForm == null)
	{
	    gedForm = new JuvenileGEDProgramForm();
	    session.setAttribute(GED_PROGRAM_FORM, gedForm);
	}

	return gedForm;
    }

    /**
     * Added to get description from scarsmarkstattoo event
     * 
     * @return ScarsTattooDescription
     */
    public static String getScarsTattooDescription(String codeId, Collection<ScarsMarksTattoosCodeResponseEvent> codesList)
    {
	if (codesList != null)
	{
	    for (ScarsMarksTattoosCodeResponseEvent codeEvt : codesList)
	    {
		if (codeEvt.getCode().trim().equalsIgnoreCase(codeId))
		{
		    return codeEvt.getDescription();
		}
	    }
	}

	return (UIConstants.EMPTY_STRING);
    }

    /*
     * Add selectedScars and corresponding category
     */
    public static Collection getSelectedScarsCodes(String[] selectedScars, Collection allAvailableScars)
    {
	Collection scars = new ArrayList();

	if (selectedScars != null && selectedScars.length > 0)
	{
	    Map map = groupByCode(allAvailableScars.iterator());
	    int noOfSelectedScars = selectedScars.length;
	    for (int iCount = 0; iCount < noOfSelectedScars; iCount++)
	    {
		String scarCode = selectedScars[iCount];
		ScarsMarksTattoosCodeResponseEvent scar = (ScarsMarksTattoosCodeResponseEvent) map.get(scarCode);
		if (scar != null)
		{
		    scars.add(scar.getCode());
		}
	    }

	}

	return (scars);
    }

    /**
     * Groups scars by code
     * 
     * @param allAvailableScars
     * @return
     */
    private static Map groupByCode(Iterator<ScarsMarksTattoosCodeResponseEvent> allAvailableScars)
    {
	Map scarsMap = new HashMap();

	while (allAvailableScars.hasNext())
	{
	    ScarsMarksTattoosCodeResponseEvent event = allAvailableScars.next();
	    Object bucketObj = scarsMap.get(event.getCode());
	    if (bucketObj == null)
	    {
		scarsMap.put(event.getCode(), event);
	    }

	}
	return scarsMap;
    }

    /*
     * @param date
     * @return
     */
    private static Date parseDate(String date)
    {
	Date returnDate = null;

	if (date != null)
	{
	    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
	    try
	    {
		returnDate = (Date) formatter.parse(date);
	    }
	    catch (ParseException e)
	    {
	    }
	}

	return returnDate;
    }

    /**
     * @param requestEvent
     * @param juvMainForm
     */
    public static void populateEventFromForm(SaveJuvenileProfileMainEvent requestEvent, JuvenileMainForm juvMainForm)
    {
	requestEvent.setAlienNumber(juvMainForm.getAlienNum());
	requestEvent.setBirthCityId(juvMainForm.getCityId());
	requestEvent.setBirthCountryId(juvMainForm.getCountryId());
	requestEvent.setBirthCountyId(juvMainForm.getBirthCountyId());
	requestEvent.setBirthStateId(juvMainForm.getStateId());
	requestEvent.setComplexionId(juvMainForm.getComplexionId());
	requestEvent.setDateOfBirth(parseDate(juvMainForm.getDateOfBirth()));
	requestEvent.setDateSenttoDPS(parseDate(juvMainForm.getDateSenttoDPS()));
	requestEvent.setDHSNumber(juvMainForm.getDHSNum());
	requestEvent.setDNASampleNumber(juvMainForm.getDNASampleNum());
	requestEvent.setDriverLicenseNumber(juvMainForm.getDriverLicenseNum());
	requestEvent.setDriverLicenseStateId(juvMainForm.getDriverLicenseStateId());
	requestEvent.setEthnicityId(juvMainForm.getEthnicityId());
	requestEvent.setFirstName(juvMainForm.getFirstName());
	requestEvent.setFBINumber(juvMainForm.getFBINum());
	
	//U.S 88526
	if(juvMainForm.getHispanic()!=null && !juvMainForm.getHispanic().isEmpty()){
		requestEvent.setHispanic(PDConstants.YES.equals(juvMainForm.getHispanic())?"Y":"N");
	}
	requestEvent.setLastName(juvMainForm.getLastName());
	requestEvent.setMiddleName(juvMainForm.getMiddleName());
	requestEvent.setNameSuffix(juvMainForm.getNameSuffix());
	requestEvent.setPreferredFirstName(juvMainForm.getPreferredFirstName());
	requestEvent.setMultiracial(PDConstants.YES.equals(juvMainForm.getMultiracial()));
	requestEvent.setNationalityId(juvMainForm.getNationalityId());
	requestEvent.setNatualEyeColorId(juvMainForm.getNaturalEyeColorId());
	requestEvent.setNaturalHairColorId(juvMainForm.getNaturalHairColorId());
	requestEvent.setPEIMSId(juvMainForm.getPEIMSId());
	requestEvent.setTSDSId(juvMainForm.getTSDSId());
	requestEvent.setIsUSCitizenId(juvMainForm.getIsUSCitizenId());
	requestEvent.setPrimaryLanguageId(juvMainForm.getPrimaryLanguageId());
	requestEvent.setRaceId( nvl(juvMainForm.getOriginalRaceId() ,juvMainForm.getRaceId() ) );
	requestEvent.setReligionId(juvMainForm.getReligionId());

	Collection scars = getSelectedScarsCodes(juvMainForm.getSelectedScarsAndMarks(), juvMainForm.getScarsAndMarksTypes());

	requestEvent.setScarsAndMarks(scars);
	requestEvent.setSecondaryLanguageId(juvMainForm.getSecondaryLanguageId());
	requestEvent.setSexId(juvMainForm.getSexId());
	requestEvent.setSID(juvMainForm.getSID());
	requestEvent.setSONumber(juvMainForm.getSONum());
	requestEvent.setEducationId(juvMainForm.getEducationId());
	// Changes for JIMS200077276 Starts
	requestEvent.setStudentId(juvMainForm.getStudentId());
	//Changes for JIMS200077276 ends
	//requestEvent.setSSN(juvMainForm.getSSN().getSSN()); //Bug 87978

	// requestEvent.setTattoos(juvMainForm.getTattoos());

	requestEvent.setVerifiedDOB(PDConstants.YES.equals(juvMainForm.getVerifiedDOB()));
	requestEvent.setDriverLicenseClassId(juvMainForm.getDriverLicenseClassId());

	requestEvent.setDriverLicenseExpireDate(UIUtil.getDateFromString(juvMainForm.getDriverLicenseExpireDate(), UIConstants.DATE_FMT_1));

	requestEvent.setAdopted(juvMainForm.isAdopted());
	if (juvMainForm.isAdopted())
	{
	    requestEvent.setFailedPlacements(juvMainForm.getFailedPlacements());
	    requestEvent.setAdoptionComments(juvMainForm.getAdoptionComments());
	}
	else
	{
	    requestEvent.setFailedPlacements(UIConstants.EMPTY_STRING);
	}
	
	requestEvent.setPassportNum(juvMainForm.getPassportNumber());
	requestEvent.setCountryOfIssuanceId(juvMainForm.getCountryOfIssuance());
	if ( juvMainForm.getPassportExpirationDate() != null ) {
	    requestEvent.setPassportExpirationDate( DateUtil.stringToDate(juvMainForm.getPassportExpirationDate(),DateUtil.DATE_FMT_1) );
	}
    }

    /**
     * @param headerForm
     * @param detail
     */
    public static void populateHeaderFormEvent(JuvenileProfileForm headerForm, JuvenileProfileDetailResponseEvent juvenile)  
    {
	String juvName = juvenile.getLastName() + ", " + juvenile.getFirstName() + "  " + juvenile.getMiddleName() + " " + juvenile.getNameSuffix();
	headerForm.setJuvenileName(juvName.trim());
	headerForm.setPreferredFirstName(juvenile.getPreferredFirstName());

	headerForm.setJuvenileNum(juvenile.getJuvenileNum());
	headerForm.setStatus(juvenile.getStatus());
	headerForm.setDetentionFacility(juvenile.getDetentionFacility());
	headerForm.setDetentionFacilityId(juvenile.getDetentionFacilityId());
	headerForm.setDetentionStatus(juvenile.getDetentionStatus());
	headerForm.setDetentionStatusId(juvenile.getDetentionStatusId());
	headerForm.setRestrictedAccess(checkRestrictedAcces(juvenile.getJuvenileNum()));
	
	//set traits info
	setMergeTrait(juvenile.getJuvenileNum(), headerForm);
	
	if (nullOrEmptyString(juvenile.getStatus()))
	{
	    headerForm.setStatus(PDJuvenileCaseConstants.NO_CASEFILE);
	    headerForm.setHasCasefile(false);
	}
	else
	{
	    headerForm.setStatus(juvenile.getStatus());
	    headerForm.setHasCasefile(true);
	}

	headerForm.setJuvUnder21(true);
	headerForm.setRestrictedAccess(checkRestrictedAcces(juvenile.getJuvenileNum()));
	headerForm.setAge(UIConstants.EMPTY_STRING);
	headerForm.setDateOfBirth(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1));
	if (juvenile.getDateOfBirth() != null)
	{ // Get age based on year
	    int age = UIUtil.getAgeInYears(juvenile.getDateOfBirth());
	    if (age > 0)
	    {
		headerForm.setAge(String.valueOf(age));
		if (age > 20)
		{
		    headerForm.setJuvUnder21(false);
		}
	    }
	}

	//US 40492
	headerForm.setJpoOfRecID(juvenile.getJpoOfRecID());
	headerForm.setJpoOfRecord(juvenile.getJpoOfRecord());
	//task 149506 
	headerForm.setFacilityreleaseDate(juvenile.getReleaseDate());

    }

    /*
     * @param evt
     * @param form
     * @return
     */
    public static JuvenileDrugRequestEvent populateJuvenileDrugRequestEventFromForm(JuvenileDrugRequestEvent evt, JuvenileDrugForm form)
    {
	evt.setDegree(form.getDegreeId());
	evt.setDrugType(form.getDrugTypeId());
	evt.setFrequency(form.getFrequencyId());
	evt.setJuvenileNum(form.getJuvenileNum());
	String onsetAge = form.getOnsetAge();

	if (notNullNotEmptyString(onsetAge))
	{
	    int age = 0;
	    try
	    {
		age = Integer.parseInt(onsetAge);
	    }
	    catch (NumberFormatException nfe)
	    { /*empty*/
	    }
	    evt.setOnsetAge(age);
	}

	evt.setAmountSpent(form.getAmountSpentId());
	evt.setLocationOfUse(form.getLocationOfUseId());

	return evt;
    }

    //ER_GANG-JIMS200074578 CHANGES STARTS
    /* populates the form values to the request.
     * @param evt
     * @param form
     * @return JuvenileGangRequestEvent
     */
    public static JuvenileGangRequestEvent populateJuvenileGangRequestEventFromForm(JuvenileGangRequestEvent evt, JuvenileGangsForm form)
    {

	evt.setJuvenileNum(form.getJuvenileNum());
	evt.setEntryDate(DateUtil.stringToDate(form.getEntryDate(), DateUtil.DATE_FMT_1));
	evt.setGangName(form.getGangNameId());
	evt.setCliqueSet(form.getCliqueSetId());
	evt.setCurrentStatus(form.getCurrentStatusId());
	evt.setAssociationType(form.getAssociationTypeId());
	evt.setAliasMoniker(form.getAliasMoniker().toUpperCase());
	evt.setRank(form.getRank().toUpperCase());
	evt.setOtherGangName(form.getOtherGangName());
	evt.setOtherCliqueSet(form.getOtherCliqueSet());
	evt.setDescHybrid(form.getDescHybrid().toUpperCase());
	return evt;
    }

    //ER_GANG-JIMS200074578 CHANGES ENDS

    /**
     * Added to put the header form in session
     * 
     * @return HttpServletRequest
     */
    public static void putHeaderForm(HttpServletRequest aRequest, JuvenileProfileDetailResponseEvent detail)
    {
	JuvenileProfileForm headerForm = null;
	HttpSession session = aRequest.getSession();
	Object obj = session.getAttribute(HEADER_FORM);

	if (obj != null && obj instanceof JuvenileProfileForm)
	{
	    headerForm = (JuvenileProfileForm) obj;
	}
	else
	{
	    headerForm = new JuvenileProfileForm();
	}

	populateHeaderFormEvent(headerForm, detail);
	session.setAttribute(JUVENILE_DETAIL_FORM, detail);
	session.setAttribute(HEADER_FORM, headerForm);
    }

    /*
     * 
     */
    public static void setJuvenileAliasInfo(HttpServletRequest aRequest, JuvenileProfileDetailResponseEvent detail)
    {
	JuvenilePhysicalCharacteristicsForm physicalForm = null;
	HttpSession session = aRequest.getSession();

	Object obj = session.getAttribute(DisplayJuvenileMasterInformationAction.PHYSICALCHARACTERISTICS_FORM);
	if (obj != null && obj instanceof JuvenilePhysicalCharacteristicsForm)
	{
	    physicalForm = (JuvenilePhysicalCharacteristicsForm) obj;
	}
	else
	{
	    physicalForm = new JuvenilePhysicalCharacteristicsForm();
	}

	List<JuvenileAliasInfoForm> juvenileAliasList = new ArrayList<JuvenileAliasInfoForm>();
	Collection<JuvenileAliasResponseEvent> aliasResponseList = detail.getAliasList();
	if (aliasResponseList != null)
	{
	    Iterator<JuvenileAliasResponseEvent> iter = aliasResponseList.iterator();
	    while (iter.hasNext())
	    {
		JuvenileAliasInfoForm aliasForm = new JuvenileAliasInfoForm();

		JuvenileAliasResponseEvent aliasResponse = iter.next();
		aliasForm.setJuvenileNum(aliasResponse.getJuvenileNum());
		aliasForm.setId(encodeOID(aliasResponse.getId()));
		aliasForm.setAliasName(aliasResponse.getAliasName());
		aliasForm.setEntryDate(aliasResponse.getEntryDate());
		AliasNameTypeCode aliasType = AliasNameTypeCode.find("code", aliasResponse.getJuvenileType());
		aliasForm.setJuvenileType(aliasType.getDescription());
		aliasForm.setNotes(aliasResponse.getNotes());
		juvenileAliasList.add(aliasForm);
	    }
	}

	Collections.sort((List) juvenileAliasList);
	physicalForm.setJuvenileAliasList(juvenileAliasList);

	session.setAttribute(DisplayJuvenileMasterInformationAction.PHYSICALCHARACTERISTICS_FORM, physicalForm);
    }

    /**
     * @param traitsForm
     */
    public static void putTraitsForm(HttpServletRequest aRequest, TraitsForm traitsForm)
    {
	HttpSession session = aRequest.getSession();
	session.setAttribute(TRAITS_FORM, traitsForm);
    }

    /*
     * @param myForm
     * @param myEvent
     */
    public static void getSaveFamilyMemberEvent(JuvenileMemberForm myForm, SaveFamilyMemberEvent myEvent)
    {
	if (myForm == null || myEvent == null)
	{
	    return;
	}

	myEvent.setFirstName(myForm.getName().getFirstName());
	myEvent.setLastName(myForm.getName().getLastName());
	myEvent.setMiddleName(myForm.getName().getMiddleName());
	myEvent.setOver21(myForm.isOver21());
	//		myEvent.setDateOfBirth( UIUtil.getDateFromString( 
	//				myForm.getDateOfBirth(), UIConstants.DATE_FMT_1 ) );
	myEvent.setDateOfBirth(DateUtil.stringToDate(myForm.getDateOfBirth(), UIConstants.DATE_FMT_1));

	myEvent.setSexId(myForm.getSexId());
	myEvent.setAlienRegistrationNum(myForm.getAlienNum());
	myEvent.setIsUSCitizenId(myForm.getIsUSCitizenId());
	myEvent.setEthnicityId(myForm.getEthnicityId());
	myEvent.setNationalityId(myForm.getNationalityId());
	myEvent.setSidNum(myForm.getSidNum());
	myEvent.setSsn(myForm.getCompleteSSN().getSSN());
	//90042
	if(myForm.getAction()!=null && myForm.getAction().equalsIgnoreCase("createMemberSummary") && myForm.getSsn()!=null){
	    myEvent.setSsn(myForm.getSsn().toString().replace("-", ""));
	}
	//90042
	myEvent.setPrimarylanguageId(myForm.getPrimaryLanguageId());
	myEvent.setSecondaryLanguageId(myForm.getSecondaryLanguageId());
	myEvent.setDeceasedInd(myForm.isDeceased());

	myForm.getJuvenileAgeAtDeath();
	if (!myEvent.isDeceasedInd())
	{
	    myForm.setCauseofDeathId(UIConstants.EMPTY_STRING);
	    myForm.setJuvenileAgeAtDeath(UIConstants.EMPTY_STRING);
	}

	myEvent.setIncarcerated(myForm.isIncarcerated());
	//		myEvent.setHasActiveCasefile(myForm.isHasActiveCasefile() );
	myEvent.setJuvenileAgeAtDeath(myForm.getJuvenileAgeAtDeath());
	myEvent.setCauseOfDeathId(myForm.getCauseofDeathId());
	myEvent.setComments(myForm.getFamilyMemberComments());
	myEvent.setDriverLicenceClassId(myForm.getDriverLicenseClassId());

	//		myEvent.setDriverLicenceExpiryDate( UIUtil.getDateFromString( 
	//				myForm.getDriverLicenseExpirationDate(), UIConstants.DATE_FMT_1 ) );
	myEvent.setDriverLicenceExpiryDate(DateUtil.stringToDate(myForm.getDriverLicenseExpirationDate(), UIConstants.DATE_FMT_1));
	myEvent.setDriverLicenceNumber(myForm.getDriverLicenseNum());
	myEvent.setDriverLicenceStateId(myForm.getDriverLicenseStateId());
	myEvent.setPsportNum(myForm.getPassportNum()); //added for passport details
	myEvent.setPsportIssueCountryId(myForm.getCountryOfIssuanceId()); //added for passport details
	myEvent.setPsportExpiryDate(DateUtil.stringToDate(myForm.getPassportExpirationDate(), UIConstants.DATE_FMT_1)); //added for passport details
	myEvent.setIdCardNum(myForm.getStateIssuedIdNum());
	myEvent.setIdCardStateId(myForm.getIssuedByStateId());
	myEvent.setMemberId(myForm.getMemberNumber());

	if (myEvent.getMemberId() != null && (myEvent.getMemberId().trim().length() == 0))
	{
	    myEvent.setMemberId(null);
	}
    }

    /*
     * @param aFamilyForm
     * @param aConstellationListResponseEvents
     */
    public static void setJuvFamilyFormConstListFROMConstListRespEvt(JuvenileFamilyForm aFamilyForm, Collection<FamilyConstellationListResponseEvent> aConstellationListResponseEvents)
    {
	if (aFamilyForm == null || nullOrEmptyCollection(aConstellationListResponseEvents))
	{
	    return;
	}

	List families = new ArrayList();

	for (FamilyConstellationListResponseEvent event : aConstellationListResponseEvents)
	{
	    if (event != null)
	    {
		JuvenileFamilyForm.ConstellationList family = new JuvenileFamilyForm.ConstellationList();

		family.setEntryDate(UIUtil.parseDate(event.getEntryDate()));
		family.setActive(event.isActive());
		family.setGuardianNames(event.getGuardiansNames());
		family.setFamilyNumber(event.getFamilyNum());
		families.add(family);
	    }
	}

	if (families.size() > 1)
	{
	    Collections.sort(families, Collections.reverseOrder());
	}
	aFamilyForm.setConstellationList(families);
    }

    /*
     * @param aConstellation
     * @param aMemberListResponseEvents
     */
    public static void setJuvFamilyFormFROMMemberListRespEvt(JuvenileFamilyForm.Constellation aConstellation, Collection<FamilyConstellationMemberListResponseEvent> aMemberListResponseEvents)
    {
	if (aConstellation == null || aMemberListResponseEvents == null)
	{
	    return;
	}

	ArrayList members = new ArrayList();
	for (FamilyConstellationMemberListResponseEvent event : aMemberListResponseEvents)
	{
	    if (event != null)
	    {
		JuvenileFamilyForm.MemberList member = new JuvenileFamilyForm.MemberList();

		member.setMemberNumber(event.getMemberNum());
		member.getMemberName().setFirstName(event.getFirstName());
		member.getMemberName().setMiddleName(event.getMiddleName());
		member.getMemberName().setLastName(event.getLastName());
		member.setRelationshipToJuvId(event.getRelationToJuvenileId());
		member.setDeceased(event.isDeceased());
		member.setIncarcerated(event.isIncarcerated());
		//User Story 39892
		if (event.getSSN() != null && !event.getSSN().equals(""))
		{
		    if (!event.getSSN().equals("666666666") && !event.getSSN().equals("777777777") && !event.getSSN().equals("888888888") && !event.getSSN().equals("999999999"))//Individual has never had a social security number.
		    {
			member.getSocialSecurity().setSSN("XXXXX" + event.getSSN().substring(5));
		    }
		    else
			member.getSocialSecurity().setSSN(event.getSSN());
		}
		member.getCompleteSSN().setSSN(event.getSSN());
		member.setDateOfBirth(DateUtil.dateToString(event.getDateOfBirth(), UIConstants.DATE_FMT_1));
		member.setDriverLicenseNum(event.getDriverLicenseNum());
		if (event.getDriverLicenseStateId() != null)
		{
		    member.setDriverLicenseStateId(event.getDriverLicenseStateId());
		}
		member.setStateIssuedIdNum(event.getStateIssuedIdNum());
		if (event.getStateIssuedIdStateId() != null)
		{
		    member.setStateIssuedIdStateId(event.getStateIssuedIdStateId());
		}
		member.setGuardian(event.isGuardian());
		member.setParentalRightsTerminated(event.isParentalRightsTerminated());
		member.setPrimaryCareGiver(event.isPrimaryCareGiver()); //11063
		member.setInHomeStatus(event.isInHomeStatus());
		member.setDetentionHearing(event.isDetentionHearing());
		member.setDetentionVisitation(event.isDetentionVisitation());
		member.setInvolvementLevelId(event.getInvolvmentLevelId());
		member.setFamilyConstellationMemberNum(event.getFamConstellationMemberNum());
		member.setSuspiciousMember(event.getSuspiciousMember());
		member.setPrimaryContact(event.isPrimaryContact());
		member.setConfirmedDate(event.getConfirmedDate());
		member.setOver21(event.isOver21());

		members.add(member);
	    }
	}

	aConstellation.setMemberList(members);
    }

    /*
     * @param aConstellation
     * @param aMemberListResponseEvents
     */
    public static int getTotalJuvDetentionVisits(Collection<FamilyConstellationMemberListResponseEvent> aMemberListResponseEvents, String juvNum)
    {
	int detVisitCount = 0;
	for (FamilyConstellationMemberListResponseEvent event : aMemberListResponseEvents)
	{
	    if (event != null)
	    {
		if (event.isDetentionVisitation())
		    detVisitCount++;
	    }
	}

	//Get all the contacts and tally the detention visits
	GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
	contactsEvent.setJuvenileNum(juvNum);
	CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
	Collection<JuvenileContactResponseEvent> contacts = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);

	for (JuvenileContactResponseEvent respEvt : contacts)
	{
	    if (respEvt != null && respEvt.isDetentionVisit())
		detVisitCount++;
	}

	return detVisitCount;
    }
    
    public static boolean isDetDefenseAttorneyVisit( String juvNum  ){
	boolean daVisit = false;
	GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
	contactsEvent.setJuvenileNum(juvNum);
	CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
	Collection<JuvenileContactResponseEvent> contacts = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
	
	for (JuvenileContactResponseEvent respEvt : contacts)
	{
	    if ( respEvt.getRelationshipId().equals("DA") 
		    && respEvt.isDetentionVisit() ) {
		daVisit = true;
		break;
	    }
	}
	
	return daVisit;
    }

    /*
     * @param aConstellation
     * @param aFamilyMemberAddressViewResponseEvents
     */
    public static void setJuvFamilyFormFROMFamilyMemberAddressViewResponseEvent(JuvenileFamilyForm.Constellation aConstellation, Collection<FamilyMemberAddressViewResponseEvent> aFamilyMemberAddressViewResponseEvents)
    {
	if (aConstellation == null || aFamilyMemberAddressViewResponseEvents == null)
	{
	    return;
	}

	ArrayList members = new ArrayList();
	for (FamilyMemberAddressViewResponseEvent event : aFamilyMemberAddressViewResponseEvents)
	{
	    if (event != null)
	    {
		JuvenileFamilyForm.MemberList member = new JuvenileFamilyForm.MemberList();

		member.setMemberNumber(event.getMemberId());
		member.getMemberName().setFirstName(event.getMemberFirstName());
		member.getMemberName().setMiddleName(event.getMemberMiddleName());
		member.getMemberName().setLastName(event.getMemberLastName());
		member.setFamilyConstellationMemberNum(event.getConstellationId());
		member.setCurrentAddress(new MemberAddress());
		member.getCurrentAddress().setAdditionalZipCode(event.getAdditionalZipCode());
		member.getCurrentAddress().setAddressTypeId(event.getAddressTypeId());
		member.getCurrentAddress().setCountyId(event.getCountyId());
		member.getCurrentAddress().setAddress2(event.getAddress2());
		member.getCurrentAddress().setCity(event.getCity());
		member.getCurrentAddress().setStateId(event.getStateId());
		member.getCurrentAddress().setStreetName(event.getStreetName());
		member.getCurrentAddress().setStreetNumber(event.getStreetNum());
		member.getCurrentAddress().setStreetNumSuffixId(event.getStreetNumSuffixId());
		member.getCurrentAddress().setStreetTypeId(event.getStreetTypeId());
		member.getCurrentAddress().setZipCode(event.getZipCode());
		member.getCurrentAddress().setMemberAddressId(event.getAddressId());
		member.getCurrentAddress().setAptNumber(event.getAptNum());
		member.getCurrentAddress().setValidated(event.getValidated());

		members.add(member);
	    }
	}
	aConstellation.setMemberList(members);
    }

    /*
     * @param myForm
     * @param aMemberListResponseEvents
     */
    public static void setJuvMemberSearchFormFROMMemberListRespEvt(JuvenileMemberSearchForm myForm, Collection<FamilyMemberListResponseEvent> aMemberListResponseEvents)
    {
	if (myForm == null || aMemberListResponseEvents == null)
	{
	    return;
	}

	//if( aMemberListResponseEvents.size() > 1 )
	//{
	//	Collections.sort((List)aMemberListResponseEvents);
	//}

	ArrayList members = new ArrayList();
	JuvenileMemberSearchForm.MemberSearchResult member = null;
	List emptyList = new ArrayList();

	for (FamilyMemberListResponseEvent event : aMemberListResponseEvents)
	{
	    if (event != null)
	    {
		member = new JuvenileMemberSearchForm.MemberSearchResult();

		member.setMemberNumber(event.getMemberNum());
		//changed for Bug# 15105
		member.setName(new Name(event.getFirstName(), event.getMiddleName(), event.getLastName()));
		//member.getName().setFirstName( event.getFirstName() );
		//member.getName().setMiddleName( event.getMiddleName() );
		//member.getName().setLastName( event.getLastName() );
		member.setDeceased(event.isDeceased());
		//check if the ssn is a repeat of a single character
		String ssn = event.getSSN();
		if (ssn != null && !ssn.equals(""))
		{
		    String firstChar = ssn.substring(0, 1);
		    String matchingChars[] = ssn.split(firstChar + "+");
		    boolean repeatChars = (matchingChars.length == 0);
		    if (!repeatChars)//Individual has never had a social security number.
		    {
			member.getSsn().setSSN("XXXXX" + ssn.substring(5));
		    }
		    else
			member.getSsn().setSSN(ssn);
		}
		//member.getSsn().setSSN( event.getSSN() );

		member.getCompleteSSN().setSSN(event.getSSN());
		member.setSuspiciousMember(event.getSuspiciousMember());
		member.setSex(event.getSex());
		member.setEthnicityCd(event.getEthnicityCd());
		member.setEthnicity(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_ETHNICITY, event.getEthnicityCd()));
		//	DateUtil.dateToString(event.getDateOfBirth(), DateUtil.DATE_FMT_1);
		member.setDateOfBirth(DateUtil.dateToString(event.getDateOfBirth(), DateUtil.DATE_FMT_1));
		member.setAssociatedJuvenilesList(event.getAssociatedJuveniles());
		if (event.getAssociatedJuveniles() == null)
		{
		    member.setAssociatedJuvenilesList(emptyList);
		}
		member.setOver21(event.isOver21()); //US #43116
		members.add(member);
	    }
	}

	//if( members.size() > 1 )
	//{
	//	Collections.sort( (List)members );
	//}
	myForm.setMemberSearchResults(members);
    }

    public static void setJuvMemberSearchFormUsingMemberListRespEvt(JuvenileMemberSearchForm myForm, Collection<FamilyMemberListResponseEvent> aMemberListResponseEvents)
    {
	if (myForm == null || aMemberListResponseEvents == null)
	{
	    return;
	}

	ArrayList members = new ArrayList();
	JuvenileMemberSearchForm.MemberSearchResult member = null;

	for (FamilyMemberListResponseEvent event : aMemberListResponseEvents)
	{
	    if (event != null)
	    {
		member = new JuvenileMemberSearchForm.MemberSearchResult();

		member.setMemberNumber(event.getMemberNum());
		member.getName().setFirstName(event.getFirstName());
		//member.getName().setMiddleName( event.getMiddleName() );
		member.getName().setLastName(event.getLastName());
		member.setSex(event.getSex());
		member.setEthnicityCd(event.getEthnicityCd());
		member.setEthnicity(event.getEthnicityDesc());
		member.setAssociatedJuvenilesList(event.getAssociatedJuveniles());
		members.add(member);
	    }
	}

	myForm.setMemberSearchResults(members);
    }

    /*
     * @param aForm
     * @param anEvent
     * @param aMemberMaritalResponseEvents
     * @param aAssocJuveniles
     */
    public static void setJuvMemberFormFROMMemberDetailRespEvt(JuvenileMemberForm aForm, FamilyMemberDetailResponseEvent anEvent, List<FamilyMemberMartialStatusResponseEvent> aMemberMaritalResponseEvents, List aAssocJuveniles)
    {
	if (aForm == null || anEvent == null)
	{
	    return;
	}

	aForm.setMemberNumber(anEvent.getMemberId());
	aForm.getName().setFirstName(anEvent.getFirstName());
	aForm.getName().setMiddleName(anEvent.getMiddleName());
	aForm.getName().setLastName(anEvent.getLastName());

	aForm.setIncarcerated(anEvent.isIncarcerated());
	aForm.setDeceased(anEvent.isDeceasedInd());
	aForm.getSsn().setSSN(anEvent.getSsn());

	if (anEvent.getCompleteSSN() != null)
	    aForm.getCompleteSSN().setSSN(anEvent.getCompleteSSN());
	else
	    aForm.setCompleteSSN(new SocialSecurity("", "", ""));
	aForm.setDateOfBirth(UIUtil.parseDate(anEvent.getDateOfBirth()));
	aForm.setSexId(anEvent.getSexId());

	aForm.setIsUSCitizenId(anEvent.getIsUSCitizenId());
	aForm.setEthnicityId(anEvent.getEthnicityId());
	aForm.setNationalityId(anEvent.getNationalityId());
	aForm.setSidNum(anEvent.getSidNum());

	aForm.setPrimaryLanguageId(anEvent.getPrimarylanguageId());
	aForm.setSecondaryLanguageId(anEvent.getSecondaryLanguageId());
	aForm.setCauseofDeathId(anEvent.getCauseOfDeathId());
	aForm.setJuvenileAgeAtDeath(anEvent.getJuvenileAgeAtDeath());
	aForm.setFamilyMemberComments(anEvent.getComments());

	aForm.setDriverLicenseNum(anEvent.getDriverLicenceNumber());
	aForm.setDriverLicenseStateId(anEvent.getDriverLicenceStateId());
	aForm.setDriverLicenseExpirationDate(UIUtil.parseDate(anEvent.getDriverLicenceExpiryDate()));
	aForm.setDriverLicenseClassId(anEvent.getDriverLicenceClassId());
	aForm.setStateIssuedIdNum(anEvent.getIdCardNum());
	aForm.setIssuedByStateId(anEvent.getIdCardStateId());
	aForm.setAlienNum(anEvent.getAlienRegistrationNum());
	aForm.setSuspiciousMember(anEvent.getSuspiciousMember());
	aForm.setPassportNum(anEvent.getPsportNum()); //added for passport details
	aForm.setPassportExpirationDate(UIUtil.parseDate(anEvent.getPsportExpirationDate())); //added for passport details
	aForm.setCountryOfIssuanceId(anEvent.getPsportIssueCountryId()); //added for passport details

	aForm.setOver21(anEvent.isOver21()); //added for User Story 43892
	if (anEvent.isOver21())
	    aForm.setOver21Str("Yes");
	else
	    aForm.setOver21Str("No");

	aForm.setMaritalList(new ArrayList());

	// BEGIN Marital Status Response Events
	if (aMemberMaritalResponseEvents != null)
	{
	    if (aForm.getMaritalRelWithList() == null || aForm.getMaritalRelWithList().isEmpty())
	    {
		aForm.setMaritalRelWithList(UIJuvenileFamilyHelper.getAllJuvenilesFamilyMembers(aForm.getJuvenileNumber()));
	    }

	    ArrayList myMaritalList = new ArrayList();
	    for (FamilyMemberMartialStatusResponseEvent event : aMemberMaritalResponseEvents)
	    {
		if (event != null)
		{
		    JuvenileMemberForm.MaritalList maritalObj = new JuvenileMemberForm.MaritalList();
		    maritalObj.setMaritalId(event.getMartialId());
		    maritalObj.setMaritalStatusId(event.getMartialStatusId());
		    maritalObj.setMarriageDate(UIUtil.parseDate(event.getMarriageDate()));
		    maritalObj.setDivorceDate(UIUtil.parseDate(event.getDivorceDate()));
		    maritalObj.setNumOfChildren(event.getNumberOfChildren());
		    maritalObj.setEntryDate(UIUtil.parseDate(event.getEntryDate()));

		    Name myRelName = new Name();
		    maritalObj.setRelatedFamMemName(myRelName);

		    if (notNullNotEmptyString(event.getRelatedFamMemId()))
		    {
			maritalObj.setRelatedFamMemId(event.getRelatedFamMemId());
			// added 5/21/2013 per ER 74779
			// Append relationship to juvenile to name
			String relStr = UIConstants.EMPTY_STRING;
			for (int x = 0; x < aForm.getMaritalRelWithList().size(); x++)
			{
			    JuvenileFamilyMemberViewResponseEvent respEvent = (JuvenileFamilyMemberViewResponseEvent) aForm.getMaritalRelWithList().get(x);
			    if (respEvent.getMemberNum().equals(event.getRelatedFamMemId()))
			    {
				relStr = event.getRelatedFamMemMiddleName() + " (" + respEvent.getJuvRelation() + ")";
				event.setRelatedFamMemMiddleName(relStr);
				break;
			    }
			}
			relStr = null;
			myRelName.setFirstName(event.getRelatedFamMemFirstName());
			myRelName.setMiddleName(event.getRelatedFamMemMiddleName());
			myRelName.setLastName(event.getRelatedFamMemLastName());
		    }
		    else
		    {
			maritalObj.setRelatedFamMemId(UIConstants.EMPTY_STRING);
		    }
		    myMaritalList.add(maritalObj);
		}
	    } // for
	    aForm.setMaritalList(sortMemberMarriageList(myMaritalList));
	}

	aForm.setAssociatedJuveniles(aAssocJuveniles);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortTraitsList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}

	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberMarriageList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortGuardianList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberBenefitsList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberAddressList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberEmploymentList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberContactList(List aNeedToSortList)
    {

	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	List theList = new ArrayList(aNeedToSortList);
	ArrayList sortFields = new ArrayList();
	sortFields.add(new ReverseComparator(new BeanComparator("entryDateTime")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort(theList, multiSort);
	return aNeedToSortList = new ArrayList(theList);
    }

    /*
     * @param aNeedToSortList
     * @return
     */
    public static List sortMemberInsuranceList(List aNeedToSortList)
    {
	if (aNeedToSortList == null || aNeedToSortList.size() < 2)
	{
	    return (aNeedToSortList);
	}
	Collections.sort(aNeedToSortList);

	return (aNeedToSortList);
    }
    
    public static void populateJuvenileTableWithZipCode(String juvenileId, String familyMemberNumber, String zipCode){
	    
	    if(juvenileId != null && !"".equals(juvenileId) && familyMemberNumber != null 
		    && !"".equals(familyMemberNumber)){
	       
	       boolean conditionsMet = UIJuvenileHelper.isGuardianPrimaryContactAndInHome(juvenileId, familyMemberNumber);
	       JuvenileCore juv = JuvenileCore.findCore(juvenileId);	       
	       
	       if (juv != null)
	       {
		   IHome home = new Home();
		       
		   if(conditionsMet){
		       juv.setZip(zipCode);
		       home.bind(juv); 
        		   
		       System.out.println("Zip Code field in JJS_MS_JUVENILE updated successfully. Juvenile Number: " +  juvenileId + ", FamilyMemberId: " + familyMemberNumber);
		   } 
		  
	       }
	       
	       juv = null;
	    }
	    
	}
    
    public static void nullifyJuvenileZipCode(String juvenileId){
	
	JuvenileCore juv = JuvenileCore.findCore(juvenileId);	       
	       
	       if (juv != null)
	       {
		   IHome home = new Home();
		       
		   juv.setZip(null);
		   home.bind(juv); 
 		   
		   System.out.println("Zip Code field in JJS_MS_JUVENILE nullified. Juvenile Number: " +  juvenileId);		  
	       } else {
		   System.out.println("Could not nullify zip code field in JJS_MS_JUVENILE. Juvenile Number: " +  juvenileId);	
	       }
	       
	       juv = null;
    }
    
    public static MemberAddress getLatestFamilyMemberAddress(String familyMemberId){
	
	 GetFamilyMemberAddressEvent event = (GetFamilyMemberAddressEvent)
			EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
	 
            event.setMemberNum(familyMemberId);
            CompositeResponse response = MessageUtil.postRequest(event);
            Map dataMap = MessageUtil.groupByTopic(response);
            
            ArrayList addressList = new ArrayList<MemberAddress>();
            Date maxDate = StringToDate("1900-01-01 00:00:00");
            MemberAddress latestAddress = new MemberAddress();
            
            if( dataMap != null )
            {
            	Collection addresses = (Collection)
            			dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_ADDRESS_TOPIC);
            	if( addresses != null && addresses.size() > 0 )
            	{
            		AddressResponseEvent responseEvt;
            		Iterator iter = addresses.iterator();
            		while( iter.hasNext() )
            		{
            			responseEvt = (AddressResponseEvent)iter.next();
            			if( responseEvt != null)
            			{
            				MemberAddress myAddress = getJuvMemberFormMemberAddresFROMAddrRespEvt(responseEvt);
            				if( myAddress != null  && maxDate != null)
            				{
            				   if(myAddress.getCreateDate().after(maxDate)){
            				       maxDate = myAddress.getCreateDate();
            				       latestAddress = myAddress;
            				      
            				   }
            				}
            			}
            		} // while
            	}
            	
            }            
           
        return latestAddress;
    }
    
    public static Date StringToDate(String s){

	Date result = null;
	   try
	   {
	           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           result  = dateFormat.parse(s);
	   }
	   catch(ParseException e){
	           e.printStackTrace();
	   }
	   
	return result;
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static MemberAddress getJuvMemberFormMemberAddresFROMAddrRespEvt(AddressResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	MemberAddress myAddress = new MemberAddress();

	myAddress.setAdditionalZipCode(aResponseEvent.getAdditionalZipCode());
	myAddress.setAddress2(aResponseEvent.getAddress2());
	myAddress.setMemberAddressId(aResponseEvent.getAddressId());
	myAddress.setAddressTypeId(aResponseEvent.getAddressTypeId());
	myAddress.setAddressType(aResponseEvent.getAddressType());
	myAddress.setAptNumber(aResponseEvent.getAptNum());
	myAddress.setCity(aResponseEvent.getCity());
	myAddress.setCountyId(aResponseEvent.getCountyId());
	myAddress.setStateId(aResponseEvent.getStateId());
	myAddress.setStreetName(aResponseEvent.getStreetName());
	myAddress.setStreetNumber(aResponseEvent.getStreetNum());
	myAddress.setStreetTypeId(aResponseEvent.getStreetTypeId());
	myAddress.setZipCode(aResponseEvent.getZipCode());
	myAddress.setCreateDate(aResponseEvent.getCreateDate());
	myAddress.setValidated(aResponseEvent.getValidated());
	myAddress.setCurrentAddressInd(aResponseEvent.getCurrentAddressInd());
	myAddress.setStreetNumSuffixId(aResponseEvent.getStreetNumSuffixId());
	myAddress.setAddressId(aResponseEvent.getAddressId());
	
	return (myAddress);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileMemberForm.MemberContact getJuvMemberFormMemberContactFROMEmailRespEvt(FamilyMemberEmailResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileMemberForm.MemberContact myContact = new JuvenileMemberForm.MemberContact();

	myContact.setPhone(false);
	myContact.setEmailAddress(aResponseEvent.getEmailAddress());
	System.out.println("Current Email: " + aResponseEvent.getEmailAddress() );
	myContact.setMemberContactId(aResponseEvent.getFamilyEmailId());
	myContact.setEmailContactTypeId(aResponseEvent.getEmailTypeId());
	myContact.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	myContact.setEntryDateTime(aResponseEvent.getEntryDate());
	myContact.setPrimaryIndEmail(aResponseEvent.getPrimaryInd() ? "Primary" : UIConstants.EMPTY_STRING);

	return (myContact);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileMemberForm.MemberContact getJuvMemberFormMemberContactFROMContactRespEvt(FamilyMemberPhoneResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileMemberForm.MemberContact myContact = new JuvenileMemberForm.MemberContact();

	myContact.setPhone(true);
	myContact.getContactPhoneNumber().setPhoneNumber(aResponseEvent.getPhoneNum());
	myContact.getContactPhoneNumber().setExt(aResponseEvent.getExtentionNum());
	myContact.setMemberContactId(aResponseEvent.getFamilyPhoneId());
	myContact.setContactTypeId(aResponseEvent.getPhoneTypeId());
	myContact.setPrimaryInd(aResponseEvent.isPrimaryInd() ? "Primary" : UIConstants.EMPTY_STRING);
	if (notNullNotEmptyString(aResponseEvent.getPhoneTypeId()))
	{
	    String desc = CodeHelper.getCodeDescription(PDCodeTableConstants.PHONE_TYPE, aResponseEvent.getPhoneTypeId());
	    myContact.setContactType(desc);
	}
	myContact.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	myContact.setEntryDateTime(aResponseEvent.getEntryDate());

	return (myContact);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileMemberForm.MemberInsurance getJuvMemberFormMemberInsuranceFROMInsuranceRespEvt(FamilyMemberInsuranceResponseEvent aResponseEvent)
    {
	JuvenileMemberForm.MemberInsurance myInsurance = null;
	if (aResponseEvent != null)
	{
	    myInsurance = new JuvenileMemberForm.MemberInsurance();
	    myInsurance.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	    myInsurance.setInsuranceCarrier(aResponseEvent.getCarrier());
	    myInsurance.setInsuranceTypeId(aResponseEvent.getTypeId());
	    myInsurance.setMemberInsuranceId(aResponseEvent.getInsuranceId());
	    myInsurance.setPolicyNumber(aResponseEvent.getPolicyNum());
	}

	return (myInsurance);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileMemberForm.MemberBenefit getJuvMemberFormMemberBenefitFROMBenefitRespEvt(FamilyMemberBenefitResponseEvent aResponseEvent)
    {
	JuvenileMemberForm.MemberBenefit myBenefit = null;
	if (aResponseEvent != null)
	{
	    myBenefit = new JuvenileMemberForm.MemberBenefit();
	    myBenefit.setEligibilityTypeId(aResponseEvent.getEligibilityTypeId());
	    myBenefit.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	    myBenefit.setMemberBenefitId(aResponseEvent.getBenefitId());
	    myBenefit.setReceivingBenefits(aResponseEvent.isReceivingBenefits());
	    myBenefit.setEligibleForBenefits(aResponseEvent.isElgibleForBenefits());
	    if (myBenefit.isReceivingBenefits())
	    {
		myBenefit.setReceivedAmt(aResponseEvent.getReceivedAmt());
		myBenefit.setIdNumber(aResponseEvent.getIdNumber());
		Name name = new Name(aResponseEvent.getReceivedBy().getFirstName(), aResponseEvent.getReceivedBy().getMiddleName(), aResponseEvent.getReceivedBy().getLastName());
		myBenefit.setReceivedBy(name.getFormattedName().trim());
		myBenefit.setReceivedByFirstName(aResponseEvent.getReceivedBy().getFirstName());
		myBenefit.setReceivedByMiddleName(aResponseEvent.getReceivedBy().getMiddleName());
		myBenefit.setReceivedByLastName(aResponseEvent.getReceivedBy().getLastName());
	    }
	}

	return myBenefit;
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileMemberForm.MemberEmployment getJuvMemberFormMemberEmploymentInfoFROMEmploymentInfoRespEvt(FamilyMemberEmploymentInfoResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileMemberForm.MemberEmployment myEmployment = new JuvenileMemberForm.MemberEmployment();

	myEmployment.setCurrentEmployer(aResponseEvent.getCurrentEmployer());
	myEmployment.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	myEmployment.setMemberEmploymentId(aResponseEvent.getEmploymentId());
	myEmployment.setJobTitle(aResponseEvent.getJobTitle());
	myEmployment.setLengthOfEmployment(aResponseEvent.getLengthOfEmployment());
	myEmployment.setSalary(Double.toString(aResponseEvent.getSalary()));
	myEmployment.setSalaryRateId(aResponseEvent.getSalaryRateId());
	myEmployment.setEmploymentStatusId(aResponseEvent.getStatusId());
	myEmployment.setWorkHours(Double.toString(aResponseEvent.getWorkHours()));
	myEmployment.setAnnualNetIncome(UIUtil.getStringFromDouble(aResponseEvent.getAnnualNetIncome()));

	return (myEmployment);
    }

    /*
     * @param aResponseEvent
     * @param isMember
     * @return
     */
    public static JuvenileFamilyForm.Trait getFamilyMemberFormTraitFROMTraitInfoRespEvt(FamilyMemberTraitResponseEvent aResponseEvent, boolean isMember)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileFamilyForm.Trait myTrait = new JuvenileFamilyForm.Trait();

	myTrait.setTraitComments(aResponseEvent.getComments());
	myTrait.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	myTrait.setTraitLevelId(aResponseEvent.getLevelId());
	myTrait.setTraitStatusId(aResponseEvent.getStatusId());
	myTrait.setTraitId(aResponseEvent.getTraitid());

	if (isMember)
	{
	    myTrait.setMemberTraitDescId(aResponseEvent.getTraitTypeId());
	}
	else
	{
	    myTrait.setTraitDescId(aResponseEvent.getTraitTypeId());
	}

	return (myTrait);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileFamilyForm.Guardian getFamilyFormGuardianFROMFamilyConstellationFinancialRespEvt(FamilyConstellationMemberFinancialResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileFamilyForm.Guardian myGuardian = new JuvenileFamilyForm.Guardian();
	Name myUIName = new Name(aResponseEvent.getChildSupportPayorFirstName(), aResponseEvent.getChildSupportPayorMiddleName(), aResponseEvent.getChildSupportPayorLastName());

	myGuardian.setChildSupportPayorName(myUIName);
	myGuardian.setConstellationMemberId(Integer.toString(aResponseEvent.getConstellationMemberId()));
	myGuardian.setFinancialId(Integer.toString(aResponseEvent.getConstellationMemberFinancialId()));
	myGuardian.setFoodStamps(UIUtil.formatCurrency(Double.toString(aResponseEvent.getFoodStamps()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));

	myGuardian.setSsi(UIUtil.formatCurrency(Double.toString(aResponseEvent.getSsi()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setGroceryExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getGroceryExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setIntangibleValue(UIUtil.formatCurrency(Double.toString(aResponseEvent.getIntangibleValue()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));

	myGuardian.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), null));
	myGuardian.setLifeInsurancePremium(UIUtil.formatCurrency(Double.toString(aResponseEvent.getLifeInsurancePremium()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setMedicalExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getMedicalExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	//changed for Bug #33954
	//myGuardian.setNumberInFamily( Integer.toString( aResponseEvent.getNumberInFamily() ) );		
	myGuardian.setNumberLivingInHome(Integer.toString(aResponseEvent.getNumberLivingInHome()));
	myGuardian.setNumberOfDependents(Integer.toString(aResponseEvent.getNumberOfDependents()));
	myGuardian.setOtherIncome(UIUtil.formatCurrency(Double.toString(aResponseEvent.getOtherIncome()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));

	myGuardian.setChildSupportPaid(UIUtil.formatCurrency(Double.toString(aResponseEvent.getChildSupportPaid()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setChildSupportReceived(UIUtil.formatCurrency(Double.toString(aResponseEvent.getChildSupportReceived()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setOtherAdultIncome(UIUtil.formatCurrency(Double.toString(aResponseEvent.getOtherAdultIncome()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setNotes(aResponseEvent.getNotes());
	myGuardian.setPropertyValue(UIUtil.formatCurrency(Double.toString(aResponseEvent.getPropertyValue()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setRentExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getRentExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setSavings(UIUtil.formatCurrency(Double.toString(aResponseEvent.getSavings()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setSchoolExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getSchoolExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setTanfAfdc(UIUtil.formatCurrency(Double.toString(aResponseEvent.getTanfAfdc()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));

	myGuardian.setTotalExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getTotalExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));
	myGuardian.setUtilitiesExpenses(UIUtil.formatCurrency(Double.toString(aResponseEvent.getUtilitiesExpenses()), UIConstants.CURRENCY_US_NOCOMMAS_POSITIVE_FORMAT, true, ""));

	return (myGuardian);
    }

    /*
     * @param aResponseEvent
     * @return
     */
    public static JuvenileFamilyForm.Guardian getFamilyFormGuardianFROMFamilyConstellationGuardianRespEvt(FamilyConstellationGuardianResponseEvent aResponseEvent)
    {
	if (aResponseEvent == null)
	{
	    return null;
	}

	JuvenileFamilyForm.Guardian myGuardian = new JuvenileFamilyForm.Guardian();

	myGuardian.setFinancialId(UIUtil.getStringFromInt(aResponseEvent.getFinancialId()));
	myGuardian.setTimeStampEntryDate(UIUtil.getStringFromTimeStamp(aResponseEvent.getEntryDate()));
	myGuardian.setEntryDate(UIUtil.getStringFromDate(aResponseEvent.getEntryDate(), UIConstants.DATE_FMT_1));
	myGuardian.setConstellationMemberId(aResponseEvent.getFamilyNum());

	Name newName = new Name(aResponseEvent.getFirstName(), aResponseEvent.getMiddleName(), aResponseEvent.getLastName());
	myGuardian.setName(newName);
	myGuardian.setRelationshipToJuvId(aResponseEvent.getJuvRelation());

	return (myGuardian);
    }

    /*
     * @param aConstellation
     * @param aTraitListResponseEvents
     */
    public static void setJuvFamilyFormCurConstFROMTraitListRespEvt(JuvenileFamilyForm.Constellation aConstellation, Collection<FamilyConstellationTraitsResponseEvent> aTraitListResponseEvents)
    {
	if (aConstellation == null || aTraitListResponseEvents == null)
	{
	    return;
	}

	ArrayList traits = new ArrayList();
	for (FamilyConstellationTraitsResponseEvent event : aTraitListResponseEvents)
	{
	    if (event != null)
	    {
		JuvenileFamilyForm.Trait trait = new JuvenileFamilyForm.Trait();
		trait.setEntryDate(UIUtil.parseDate(event.getEntryDate()));
		trait.setTraitId(event.getId());
		trait.setTraitStatusId(event.getStatusId());
		trait.setTraitLevelId(event.getLevelId());
		trait.setTraitComments(event.getComments());
		trait.setTraitDescId(event.getDynamicTypeId());
		traits.add(trait);
	    }
	}

	aConstellation.setTraitList(UIJuvenileHelper.sortTraitsList(traits));
    }

    /**
     * @param myForm
     * @param event
     */
    public static SaveFamilyMemberMaritalStatusEvent getSaveFamilyMemberMaritalStatusEvent(JuvenileMemberForm myForm)
    {
	SaveFamilyMemberMaritalStatusEvent event = new SaveFamilyMemberMaritalStatusEvent();
	event.setEntryDate(UIUtil.getDateFromString(myForm.getEntryDate(), UIConstants.DATE_FMT_1));
	event.setMaritalStatusId(myForm.getMaritalStatusId());
	event.setMarriageDate(UIUtil.getDateFromString(myForm.getMarriageDate(), UIConstants.DATE_FMT_1));
	event.setDivorceDate(UIUtil.getDateFromString(myForm.getDivorceDate(), UIConstants.DATE_FMT_1));
	event.setNoOfChildren(myForm.getNumOfChildren());

	if (notNullNotEmptyString(myForm.getRelatedFamMemId()) && !(myForm.getRelatedFamMemId().equals(PDCodeTableConstants.MARITAL_STATUS_SINGLE)))
	{
	    event.setRelatedFamMemId(myForm.getRelatedFamMemId());
	}

	return (event);
    }

    /*
     * @param myAddress
     * @return
     */
    public static SaveFamilyMemberAddressEvent getSaveFamilyMemberAddressEvent(MemberAddress myAddress)
    {
	if (myAddress == null)
	{
	    return null;
	}

	SaveFamilyMemberAddressEvent event = new SaveFamilyMemberAddressEvent();

	event.setAdditionalZip(myAddress.getAdditionalZipCode());
	event.setAddressId(myAddress.getMemberAddressId());
	event.setAddressTypeId(myAddress.getAddressTypeId());
	event.setAptNum(myAddress.getAptNumber());
	event.setCity(myAddress.getCity());
	event.setCountyId(myAddress.getCountyId());
	event.setStateId(myAddress.getStateId());
	event.setStreetName(myAddress.getStreetName());
	event.setStreetNumber(myAddress.getStreetNumber());
	event.setStreetTypeId(myAddress.getStreetTypeId());
	event.setStreetNumSuffixId(myAddress.getStreetNumSuffixId());
	event.setZip(myAddress.getZipCode());
	event.setValidated(myAddress.getValidated());

	if (notNullAndEmptyString(event.getAddressId()))
	{
	    event.setAddressId(null);
	}

	return (event);
    }

    /*
     * @param myBenefit
     * @return
     */
    public static SaveFamilyMemberBenefitsEvent getSaveFamilyBenefitEvent(JuvenileMemberForm.MemberBenefit myBenefit)
    {
	if (myBenefit == null)
	{
	    return null;
	}

	SaveFamilyMemberBenefitsEvent event = new SaveFamilyMemberBenefitsEvent();

	event.setBenefitId(myBenefit.getMemberBenefitId());
	event.setElgibleForBenefits(myBenefit.isEligibleForBenefits());
	event.setEligibilityTypeId(myBenefit.getEligibilityTypeId());
	event.setReceivingBenefits(myBenefit.isReceivingBenefits());
	event.setIdNumber(myBenefit.getIdNumber());
	event.setReceivedAmt(myBenefit.getReceivedAmt());
	IName famMemName = UIUtil.getNameFromString(myBenefit.getReceivedBy());
	event.setReceivedBy(new Name(famMemName.getFirstName(), famMemName.getMiddleName(), famMemName.getLastName()));
	if (notNullAndEmptyString(event.getBenefitId()))
	{
	    event.setBenefitId(null);
	}

	return (event);
    }

    /*
     * @param myInsurance
     * @return
     */
    public static SaveFamilyMemberInsuranceEvent getSaveFamilyInsuranceEvent(JuvenileMemberForm.MemberInsurance myInsurance)
    {
	if (myInsurance == null)
	{
	    return null;
	}

	SaveFamilyMemberInsuranceEvent event = new SaveFamilyMemberInsuranceEvent();

	event.setCarrier(myInsurance.getInsuranceCarrier());
	event.setPolicyNum(myInsurance.getPolicyNumber());
	event.setTypeId(myInsurance.getInsuranceTypeId());

	return (event);
    }

    /*
     * @param myMember
     * @return
     */
    public static SaveFamilyConstellationMemberInfoEvent getSaveFamilyConstellationMemberInfoEvent(JuvenileFamilyForm.MemberList myMember)
    {
	if (myMember == null)
	{
	    return null;
	}

	SaveFamilyConstellationMemberInfoEvent event = new SaveFamilyConstellationMemberInfoEvent();

	event.setConstellationMemberNum(myMember.getFamilyConstellationMemberNum());
	event.setGuardian(myMember.isGuardian());
	event.setInHomeStatus(myMember.isInHomeStatus());
	event.setPrimaryCareGiver(myMember.isPrimaryCareGiver()); //ER changes 11063
	event.setInvolvmentLevelId(myMember.getInvolvementLevelId());
	event.setRelationToJuvenileId(myMember.getRelationshipToJuvId());
	event.setRemoveMemberFromConstellation(myMember.isDelete());
	event.setMemberNum(myMember.getMemberNumber());
	event.setParentalRightsTerminated(myMember.isParentalRightsTerminated());
	event.setDetentionHearing(myMember.isDetentionHearing());
	event.setDetentionVisitation(myMember.isDetentionVisitation());
	event.setPrimaryContact(myMember.isPrimaryContact());
	event.setIsOver21(myMember.isOver21());

	return event;
    }

    /*
     * @param myTrait
     * @return
     */
    public static SaveFamilyTraitEvent getSaveFamilyConstellationTraitEvent(JuvenileFamilyForm.Trait myTrait)
    {
	if (myTrait == null)
	{
	    return null;
	}

	SaveFamilyTraitEvent event = new SaveFamilyTraitEvent();

	event.setComments(myTrait.getTraitComments());
	event.setLevelId(myTrait.getTraitLevelId());
	event.setStatusId(myTrait.getTraitStatusId());
	event.setDynamicTraitId(myTrait.getTraitDescId());
	event.setEntryDate(UIUtil.getDateFromString(myTrait.getEntryDate(), UIConstants.DATE_FMT_1));

	return (event);
    }

    /*
     * @param myContact
     * @return
     */
    public static SaveFamilyMemberContactEvent getSaveFamilyContactEvent(JuvenileMemberForm.MemberContact myContact)
    {
	if (myContact == null)
	{
	    return null;
	}

	SaveFamilyMemberContactEvent event = new SaveFamilyMemberContactEvent();

	if (myContact.isPhone())
	{
	    event.setPhone(true);
	    event.setConstellationMemberPhoneId(myContact.getMemberContactId());
	    event.setExtentionNum(myContact.getContactPhoneNumber().getExt());
	    event.setPhoneId(myContact.getContactPhoneNumber().getPhoneId());
	    event.setPhoneNum(myContact.getContactPhoneNumber().getPhoneNumber());
	    event.setPhoneTypeId(myContact.getContactTypeId());
	    if (notNullAndEmptyString(event.getPhoneId()))
	    {
		event.setPhoneId(null);
	    }
	    event.setPrimaryInd("YES".equalsIgnoreCase(myContact.getPrimaryInd()) ? true : false);
	    if (myContact.getPrimaryInd().equalsIgnoreCase("primary"))
	    {
		event.setPrimaryInd(true);
	    }

	}
	else
	{
	    event.setPhone(false);
	    event.setConstellationMemberEmailId(myContact.getMemberContactId());
	    event.setEmailTypeId(myContact.getEmailContactTypeId());
	    event.setEmailAddress(myContact.getEmailAddress());
	    
	    event.setPrimaryIndEmail("YES".equalsIgnoreCase(myContact.getPrimaryIndEmail()) ? true : false);
	    if (myContact.getPrimaryIndEmail().equalsIgnoreCase("primary"))
	    {
		event.setPrimaryIndEmail(true);
	    }

	}

	return (event);
    }

    /*
     * @param myTrait
     * @return
     */
    public static SaveFamilyMemberTraitEvent getSaveFamilyMemberTraitEvent(JuvenileFamilyForm.Trait myTrait)
    {
	if (myTrait == null)
	{
	    return null;
	}
	SaveFamilyMemberTraitEvent event = new SaveFamilyMemberTraitEvent();

	event.setComments(myTrait.getTraitComments());
	event.setLevelId(myTrait.getTraitLevelId());
	event.setStatusId(myTrait.getTraitStatusId());
	event.setTraitid(myTrait.getTraitId());
	event.setTraitTypeId(myTrait.getTraitDescId());

	if (notNullAndEmptyString(event.getTraitid()))
	{
	    event.setTraitid(null);
	}

	return (event);
    }

    /*
     * @param myEmployment
     * @return
     */
    public static SaveFamilyMemberEmploymentInfoEvent getSaveFamilyEmploymentEvent(JuvenileMemberForm.MemberEmployment myEmployment)
    {
	if (myEmployment == null)
	{
	    return null;
	}

	SaveFamilyMemberEmploymentInfoEvent event = new SaveFamilyMemberEmploymentInfoEvent();

	event.setCurrentEmployer(myEmployment.getCurrentEmployer());
	event.setEmploymentId(myEmployment.getMemberEmploymentId());
	event.setJobTitle(myEmployment.getJobTitle());
	event.setLengthOfEmployment(myEmployment.getLengthOfEmployment());

	if (notNullNotEmptyString(myEmployment.getSalary()))
	{
	    event.setSalary(Double.parseDouble(myEmployment.getSalary()));
	}
	else
	{
	    event.setSalary(0);
	}

	event.setSalaryRateId(myEmployment.getSalaryRateId());
	event.setStatusId(myEmployment.getEmploymentStatusId());

	if (notNullNotEmptyString(myEmployment.getWorkHours()))
	{
	    event.setWorkHours(Double.parseDouble(myEmployment.getWorkHours()));
	}
	else
	{
	    event.setWorkHours(0);
	}

	event.setAnnualNetIncome(UIUtil.getDoubleFromString(myEmployment.getAnnualNetIncome()));
	if (notNullAndEmptyString(event.getEmploymentId()))
	{
	    event.setEmploymentId(null);
	}

	return (event);
    }

    public static SaveFamilyMemberFinancialEvent getSaveFamilyFinancialEvent(JuvenileFamilyForm.Guardian myGuardianInfo, String aFamilyNum, String aMemberNum)
    {
	if (myGuardianInfo == null)
	{
	    return null;
	}

	SaveFamilyMemberFinancialEvent event = new SaveFamilyMemberFinancialEvent();

	event.setChildSupportPaid(UIUtil.getDoubleFromString(myGuardianInfo.getChildSupportPaid()));
	event.setChildSupportReceived(UIUtil.getDoubleFromString(myGuardianInfo.getChildSupportReceived()));
	event.setOtherAdultIncome(UIUtil.getDoubleFromString(myGuardianInfo.getOtherAdultIncome()));
	event.setNotes(myGuardianInfo.getNotes());

	event.setChildSupportPayorFirstName(myGuardianInfo.getChildSupportPayorName().getFirstName());
	event.setChildSupportPayorLastName(myGuardianInfo.getChildSupportPayorName().getLastName());
	event.setChildSupportPayorMiddleName(myGuardianInfo.getChildSupportPayorName().getMiddleName());
	event.setConstellationMemberId(UIUtil.getIntFromString(myGuardianInfo.getConstellationMemberId()));
	event.setFoodStamps(UIUtil.getDoubleFromString(myGuardianInfo.getFoodStamps()));
	event.setGroceryExpenses(UIUtil.getDoubleFromString(myGuardianInfo.getGroceryExpenses()));
	event.setIntangibleValue(UIUtil.getDoubleFromString(myGuardianInfo.getIntangibleValue()));

	event.setLifeInsurancePremium(UIUtil.getDoubleFromString(myGuardianInfo.getLifeInsurancePremium()));
	event.setMedicalExpenses(UIUtil.getDoubleFromString(myGuardianInfo.getMedicalExpenses()));
	event.setNumberLivingInHome(UIUtil.getIntFromString(myGuardianInfo.getNumberLivingInHome()));
	event.setNumberOfDependents(UIUtil.getIntFromString(myGuardianInfo.getNumberOfDependents()));
	event.setNumberInFamily(UIUtil.getIntFromString(myGuardianInfo.getNumberInFamily()));
	event.setOtherIncome(UIUtil.getDoubleFromString(myGuardianInfo.getOtherIncome()));

	event.setPropertyValue(UIUtil.getDoubleFromString(myGuardianInfo.getPropertyValue()));
	event.setRentExpenses(UIUtil.getDoubleFromString(myGuardianInfo.getRentExpenses()));
	event.setSavings(UIUtil.getDoubleFromString(myGuardianInfo.getSavings()));
	event.setSchoolExpenses(UIUtil.getDoubleFromString(myGuardianInfo.getSchoolExpenses()));
	event.setTanfAfdc(UIUtil.getDoubleFromString(myGuardianInfo.getTanfAfdc()));

	event.setUtilitiesExpenses(UIUtil.getDoubleFromString(myGuardianInfo.getUtilitiesExpenses()));
	event.setConstellationNum(aFamilyNum);
	event.setMemberNum(aMemberNum);
	event.setSsi(UIUtil.getDoubleFromString(myGuardianInfo.getSsi()));

	return event;
    }

    /*
     * @param myConstellation
     * @param juvNumber
     * @return
     */
    public static SaveFamilyConstellationEvent getSaveFamilyConstellationEvent(JuvenileFamilyForm.Constellation myConstellation, String juvNumber)
    {
	if (myConstellation == null || nullOrEmptyCollection(myConstellation.getMemberList()))
	{
	    return null;
	}

	// Set Juvenile Number in main event
	SaveFamilyConstellationEvent event = new SaveFamilyConstellationEvent();
	event.setJuvNum(juvNumber);

	// Set Members
	Collection<JuvenileFamilyForm.MemberList> members = myConstellation.getMemberList();
	if (members != null)
	{
	    for (JuvenileFamilyForm.MemberList myConstMember : members)
	    {
		SaveFamilyConstellationMemberInfoEvent myFamMemEvent = getSaveFamilyConstellationMemberInfoEvent(myConstMember);
		if (myConstMember.isGuardian())
		{
		    Collection<JuvenileFamilyForm.Guardian> guardians = myConstellation.getGuardiansList();
		    if (notNullNotEmptyCollection(guardians))
		    {
			for (JuvenileFamilyForm.Guardian myConstGuardian : guardians)
			{
			    if (myConstGuardian.getMemberNumber().equalsIgnoreCase(myConstMember.getMemberNumber()))
			    {
				SaveFamilyMemberFinancialEvent myGuardianEvent = getSaveFamilyFinancialEvent(myConstGuardian, myConstellation.getFamilyNumber(), myConstMember.getMemberNumber());
				myFamMemEvent.addRequest(myGuardianEvent);
			    }
			} // for
		    }
		}
		event.addRequest(myFamMemEvent);
	    } // for
	}

	// Set Traits
	Collection<JuvenileFamilyForm.Trait> traits = myConstellation.getTraitList();
	if (traits != null)
	{
	    for (JuvenileFamilyForm.Trait myConstTrait : traits)
	    {
		SaveFamilyTraitEvent myTraitEvent = getSaveFamilyConstellationTraitEvent(myConstTrait);
		event.addRequest(myTraitEvent);
	    }
	}

	return event;
    }

    /*
     * @param aForm
     * @param aRespEvnt
     */
    public static void setJuvenileMainForm(JuvenileMainForm aForm, JuvenileProfileDetailResponseEvent aRespEvnt)
    {
	aForm.setMatchDetectedSSN(false);
	aForm.setAlienNum(aRespEvnt.getAlienNumber());
	aForm.setCityId(aRespEvnt.getBirthCity());
	aForm.setCountryId(aRespEvnt.getBirthCountry());
	aForm.setBirthCountyId(aRespEvnt.getBirthCounty());
	aForm.setStateId(aRespEvnt.getBirthState());
	aForm.setComplexionId(aRespEvnt.getComplexion());

	if (aRespEvnt.getDateOfBirth() != null)
	{
	    aForm.setDateOfBirth(UIUtil.getStringFromDate(aRespEvnt.getDateOfBirth(), UIConstants.DATE_FMT_1));
	}

	if (aRespEvnt.getDateSenttoDPS() != null)
	{
	    aForm.setDateSenttoDPS(UIUtil.getStringFromDate(aRespEvnt.getDateSenttoDPS(), UIConstants.DATE_FMT_1));
	}

	aForm.setDHSNum(aRespEvnt.getDHSNumber());
	aForm.setDNASampleNum(aRespEvnt.getDNASampleNumber());
	aForm.setDriverLicenseNum(aRespEvnt.getDriverLicenseNumber());
	aForm.setDriverLicenseStateId(aRespEvnt.getDriverLicenseState());
	aForm.setEthnicityId(aRespEvnt.getEthnicity());
	aForm.setFBINum(aRespEvnt.getFBINumber());
	aForm.setPassportNumber(aRespEvnt.getPassportNum());
	aForm.setCountryOfIssuance(aRespEvnt.getCountryOfIssuanceId());
	
	if (aRespEvnt.getPassportExpirationDate() != null)
	{
	    aForm.setPassportExpirationDate(UIUtil.getStringFromDate(aRespEvnt.getPassportExpirationDate(), UIConstants.DATE_FMT_1));
	}

	aForm.setFirstName(aRespEvnt.getFirstName());
	aForm.setLastName(aRespEvnt.getLastName());
	aForm.setMiddleName(aRespEvnt.getMiddleName());
	aForm.setNameSuffix(aRespEvnt.getNameSuffix());
	aForm.setPreferredFirstName(aRespEvnt.getPreferredFirstName());

	aForm.setNationalityId(aRespEvnt.getNationality());
	aForm.setNaturalEyeColorId(aRespEvnt.getNatualEyeColor());
	aForm.setNaturalHairColorId(aRespEvnt.getNaturalHairColor());
	aForm.setPEIMSId(aRespEvnt.getPEIMSId());
	aForm.setTSDSId(aRespEvnt.getPEIMSId());

	aForm.setIsUSCitizenId(aRespEvnt.getIsUSCitizen());
	aForm.setPrimaryLanguageId(aRespEvnt.getPrimaryLanguage());
	aForm.setRaceId(aRespEvnt.getRaceId());
	aForm.setOriginalRaceId(aRespEvnt.getOriginalRaceId());
	aForm.setRace(aRespEvnt.getRace());
	aForm.setReligionId(aRespEvnt.getReligion());

	aForm.setSecondaryLanguageId(aRespEvnt.getSecondaryLanguage());
	aForm.setSexId(aRespEvnt.getSexId());
	aForm.setSID(aRespEvnt.getSID());
	aForm.setSONum(aRespEvnt.getSONumber());
	aForm.setEducationId(aRespEvnt.getEducationId());
	aForm.setStudentId(aRespEvnt.getStudentId());//ER JIMS200076953
	aForm.setSSN(new SocialSecurity(aRespEvnt.getSSN()));
	aForm.setCurrentSSN(new SocialSecurity(aRespEvnt.getSSN()));
	aForm.setCompleteSSN(new SocialSecurity(aRespEvnt.getCompleteSSN()));
	aForm.setScarsAndMarks(aRespEvnt.getScarsAndMarks());

	aForm.setDriverLicenseClassId(aRespEvnt.getDriverLicenseClassId());
	aForm.setDriverLicenseExpireDate(UIUtil.getStringFromDate(aRespEvnt.getDriverLicenseExpireDate(), null));

	aForm.setDetentionFacility(aRespEvnt.getDetentionFacility());
	aForm.setDetentionFacilityId(aRespEvnt.getDetentionFacilityId());
	aForm.setDetentionStatus(aRespEvnt.getDetentionStatus());
	aForm.setDetentionStatusId(aRespEvnt.getDetentionStatusId());
	aForm.setAdopted(aRespEvnt.isAdopted());
	aForm.setFailedPlacements(aRespEvnt.getFailedPlacements());
	aForm.setAdoptionComments(aRespEvnt.getAdoptionComments());
	aForm.setHispanic(aRespEvnt.getHispanic());//U.S 88526

	if (aRespEvnt.isMultiracial())
	{
	    aForm.setMultiracial(PDConstants.YES);
	}
	else
	{
	    aForm.setMultiracial(PDConstants.NO);
	}

	if (aRespEvnt.isVerifiedDOB())
	{
	    aForm.setVerifiedDOB(PDConstants.YES);
	}
	else
	{
	    aForm.setVerifiedDOB(PDConstants.NO);
	}

	aForm.setUpdatable(aRespEvnt.isUpdatable());
	if (notNullNotEmptyString(aRespEvnt.getStatus()))
	{
	    aForm.setHasCasefile(true);
	}
	else
	{
	    aForm.setHasCasefile(false);
	}
	//Added for US 37931 - JIS information
	aForm.setCurrentJISInfo(new JuvenileMainForm.JISInformation());
	Collection<JuvenileJISResponseEvent> jisColl = aRespEvnt.getJisInfo();
	Collections.sort((List) jisColl);
	Iterator jisIter = jisColl.iterator();
	if (jisIter.hasNext())
	{
	    JuvenileMainForm.JISInformation jisInfo = new JuvenileMainForm.JISInformation();
	    JuvenileJISResponseEvent jisResp = (JuvenileJISResponseEvent) jisIter.next();
	    jisInfo.setAgency(jisResp.getAgency());
	    jisInfo.setOtherAgency(jisResp.getOtherAgency());
	    jisInfo.setEntryDate(DateUtil.dateToString(jisResp.getEntryDate(), DateUtil.DATE_FMT_1));
	    jisInfo.setComments(jisResp.getComments());
	    aForm.setCurrentJISInfo(jisInfo);
	    ((List) jisColl).remove(0);

	}
	aForm.setJISInfoList(jisColl);
    }

    /**
     * ADDED for JIMS200077276
     * 
     * @param feature
     * @return
     */
    public static boolean isFeatureAllowed(String feature)
    {
	ISecurityManager mgr = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	if (mgr != null)
	{
	    Set ufeatures = mgr.getFeatures();
	    if (ufeatures.contains(feature))
	    {
		return true;
	    }
	}
	return false;
    }

    /*
     * @param aEvt
     * @param aPhoto
     */
    public static void mapJuvPhotoRespEvtToAPhoto(JuvenilePhotoResponseEvent aEvt, Photo aPhoto)
    {
	if (aEvt == null || aPhoto == null)
	{
	    return;
	}

	aPhoto.setEntryDate(aEvt.getEntryDate());
	aPhoto.setPhoto(aEvt.getPhoto());
	aPhoto.setPhotoFullDesc_Caption(aEvt.getPhotoFullDesc_Caption());
	aPhoto.setPhotoName(aEvt.getPhotoName());
	aPhoto.setPhotoShortDesc_Caption(aEvt.getPhotoShortDesc_Caption());
	aPhoto.setThumbNail(aEvt.getThumbNail());
    }

    /*
     * @param aRequest
     * @param juvenileNum
     */
    public static void setJuvenileBehaviorHistoryForm(HttpServletRequest aRequest, String juvenileNum)
    {
	if (notNullNotEmptyString(juvenileNum))
	{
	    GetBehavioralHistoryEvent reqEvent = (GetBehavioralHistoryEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETBEHAVIORALHISTORY);

	    reqEvent.setJuvenileNum(juvenileNum);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(reqEvent);

	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	    MessageUtil.processReturnException(compositeResponse);

	    JuvenileBehaviorHistoryResponseEvent resp = (JuvenileBehaviorHistoryResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileBehaviorHistoryResponseEvent.class);
	    if (resp != null)
	    {
		JuvenileBehaviorHistoryForm juvenileBehaviorHistoryForm = (JuvenileBehaviorHistoryForm) aRequest.getSession().getAttribute(JUVENILE_BEHAVIOR_HISTORY_FORM);

		if (juvenileBehaviorHistoryForm == null)
		{
		    juvenileBehaviorHistoryForm = new JuvenileBehaviorHistoryForm();
		    aRequest.getSession().setAttribute(JUVENILE_BEHAVIOR_HISTORY_FORM, juvenileBehaviorHistoryForm);
		}
		juvenileBehaviorHistoryForm.clear();

		juvenileBehaviorHistoryForm.setAgeFirstReferred(resp.getAgeFirstReferred());
		juvenileBehaviorHistoryForm.setDeferredAdjudicationEvents(resp.getDeferredAdjudicationEvents());
		juvenileBehaviorHistoryForm.setAdjudicationEvents(resp.getAdjudicationEvents());
		juvenileBehaviorHistoryForm.setTYCCommitments(resp.getTYCCommitments());
		juvenileBehaviorHistoryForm.setSeriousnessIndex(resp.getSeriousnessIndex());
		juvenileBehaviorHistoryForm.setSeverityIndex(resp.getSeverityIndex());
		juvenileBehaviorHistoryForm.setReferralEvents(resp.getReferralEvents());
		juvenileBehaviorHistoryForm.setTotalOffenses(resp.getTotalOffenses());
		juvenileBehaviorHistoryForm.setFelonyCapital(resp.getFelonyCapital());
		juvenileBehaviorHistoryForm.setMisdemeanorA(resp.getMisdemeanorA());
		juvenileBehaviorHistoryForm.setFelony1(resp.getFelony1());
		juvenileBehaviorHistoryForm.setMisdemeanorB(resp.getMisdemeanorB());
		juvenileBehaviorHistoryForm.setFelony2(resp.getFelony2());
		juvenileBehaviorHistoryForm.setMisdemeanorC(resp.getMisdemeanorC());
		juvenileBehaviorHistoryForm.setFelony3(resp.getFelony3());
		juvenileBehaviorHistoryForm.setCityOrdinance(resp.getCityOrdinance());
		juvenileBehaviorHistoryForm.setFelonyStateJail(resp.getFelonyStateJail());
		juvenileBehaviorHistoryForm.setRunaways(resp.getRunaways());
		juvenileBehaviorHistoryForm.setViolationsOfProbation(resp.getViolationsOfProbation());
		juvenileBehaviorHistoryForm.setTotalReferralNonAdmin(resp.getTotalReferralNonAdmin());
		juvenileBehaviorHistoryForm.setDiversionEvents(resp.getDiversionEvents());
		juvenileBehaviorHistoryForm.setReferralByDateNonAdminEvents(resp.getReferralByDateNonAdminEvents());
		
		
	    }
	}
    }

    /*
     * @param aRequest
     * @param juvenileNum
     */
    public static void populateJuvenileProfileHeaderForm(HttpServletRequest aRequest, String juvenileNum)
    {
	GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	requestEvent.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
	JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);

	/*
	 * for the rare instance where there are no instances
	 */
	if (detail == null)
	{
	    detail = new JuvenileProfileDetailResponseEvent();
	}

	putHeaderForm(aRequest, detail);
    }

    /*
     * @param casefileId
     * @param activityId
     * @param comments
     */
    public static void createActivity(String casefileId, String activityId, String comments)
    {
	CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);

	reqEvent.setFromAction(true);
	reqEvent.setSupervisionNumber(casefileId);
	reqEvent.setActivityCodeId(activityId);
	reqEvent.setComments(comments);
	//reqEvent.setActivityTime( new SimpleDateFormat("yyyyMMdd hh:mm").format( new Date().getTime()) );//added @ US 156675
	reqEvent.setActivityTime( new SimpleDateFormat("HH:mm").format( new Date().getTime()) );//changed the time format for bug 158968
	
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(reqEvent);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compositeResponse);
    }

    /*
     * @param casefileId
     * @param activityId
     * @param comments
     */
    public static void createReferralActivity(String casefileId, String comments)
    {
	CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);

	reqEvent.setSupervisionNumber(casefileId);
	reqEvent.setActivityDate(DateUtil.getCurrentDate());
	reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
	reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_REF_REC_CREATED);
	reqEvent.setActivityCodeId(ActivityConstants.REFERRAL_RECORD_CREATED);
	reqEvent.setComments(comments);
	MessageUtil.postRequest(reqEvent);
	
    }
    
    /*
     * @param casefileId
     * @param activityId
     * @param comments
     */
    public static void createReferralOverrideActivity(String casefileId, String comments)
    {
	CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);

	reqEvent.setSupervisionNumber(casefileId);
	reqEvent.setActivityDate(DateUtil.getCurrentDate());
	reqEvent.setActivityCategoryId(ActivityConstants.ACTIVITY_SYSTEM_GENERATED);
	reqEvent.setActivityTypeId(ActivityConstants.ACTIVITY_TYPE_REF_REC_OVERRIDE);//ARO
	reqEvent.setActivityCodeId(ActivityConstants.ASSIGNMENT_RECORD_OVERRIDE);//AOR
	reqEvent.setComments(comments);
	MessageUtil.postRequest(reqEvent);
	
    }

    /*
     * @param re
     * @return
     */
    public static JuvenileMemberForm.MemberBenefit getJuvMemberFormMemberBenefitFROMJuvenileBenefitRespEvt(JuvenileBenefitResponseEvent re)
    {
	JuvenileMemberForm.MemberBenefit uiObj = new JuvenileMemberForm.MemberBenefit();
	if (re != null)
	{
	    uiObj.setMemberBenefitId(re.getBenefitId());
	    uiObj.setEntryDate(UIUtil.getStringFromDate(re.getEntryDate(), UIConstants.DATE_FMT_1));
	    uiObj.setEligibilityId(re.getEligibilityTypeId());
	    uiObj.setEligibilityTypeId(re.getEligibilityTypeId());
	    uiObj.setEligibleForBenefits(re.isEligibleForBenefits());
	    uiObj.setReceivingBenefits(re.isReceivingBenefits());
	}

	return uiObj;
    }

    /*
     * given a string, return true if it's not null AND it's emtpy
     * @param str
     * @return
     */
    private static boolean notNullAndEmptyString(String str)
    {
	return (str != null && (str.trim().length() == 0));
    }

    /*
     * given a string, return true if it's null OR empty
     * @param str
     */
    private static boolean notNullNotEmptyString(String str)
    {
	return (str != null && str.trim().length() > 0);
    }

    /*
     * given a string, return true if it's null OR empty
     * @param str
     */
    private static boolean nullOrEmptyString(String str)
    {
	return (str == null || (str.trim().length() == 0));
    }

    /*
     * given a collection, return true if it's not null AND not empty
     * @param coll
     * @return
     */
    private static boolean notNullNotEmptyCollection(Collection coll)
    {
	return (coll != null && !coll.isEmpty());
    }

    /*
     * given a collection, return true if it's not null AND not empty
     * @param coll
     * @return
     */
    private static boolean nullOrEmptyCollection(Collection coll)
    {
	return (coll == null || coll.isEmpty());
    }

    public static String encodeOID(String oid)
    {
	String returnString = null;
	try
	{
	    returnString = URLEncoder.encode(oid, "UTF-8");
	}
	catch (Exception e)
	{

	}
	return returnString;

    }

    public static String decodeOID(String oid)
    {
	String returnString = null;
	try
	{
	    returnString = URLDecoder.decode(oid, "UTF-8");
	}
	catch (Exception e)
	{

	}
	return returnString;

    }

    public static boolean checkRestrictedAcces(String juvenileNum)
    {
	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	if (juvenileTraits == null)
	{
	    juvenileTraits = new ArrayList();
	}
	else
	{
	    Collections.sort((ArrayList) (juvenileTraits));
	}
	Iterator traitItr = juvenileTraits.iterator();
	while (traitItr.hasNext())
	{
	    JuvenileTraitResponseEvent traitRespEvent = (JuvenileTraitResponseEvent) traitItr.next();
	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_RESTRICTED_ACCESS) && traitRespEvent.getStatusId().equalsIgnoreCase("CU"))
	    {
		return true;
	    }

	}

	return false;
    }

    /**
     * @param memberList
     * @return
     */
    public static JuvenileFamilyForm.MemberList getGuardian(Collection memberList)
    {
	boolean hasPrimaryContact = false;
	Iterator iter = memberList.iterator();
	while (iter.hasNext())
	{
	    JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList) iter.next();
	    if (myMember.isPrimaryContact())
	    {
		if (myMember.isGuardian() && myMember.isInHomeStatus())
		    return myMember;
		hasPrimaryContact = true;
	    }
	}
	iter = memberList.iterator();
	while (iter.hasNext())
	{
	    JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList) iter.next();
	    if (myMember.isPrimaryContact())
	    {
		if (myMember.isGuardian())
		    return myMember;

	    }
	}

	//since there is no primary contact, find the most recent guardian with in-home status
	ArrayList inHomeGuardians = new ArrayList();
	if (!hasPrimaryContact)
	{
	    iter = memberList.iterator();
	    while (iter.hasNext())
	    {
		JuvenileFamilyForm.MemberList myMember = (JuvenileFamilyForm.MemberList) iter.next();
		if (myMember.isGuardian() && myMember.isInHomeStatus())
		    inHomeGuardians.add(myMember);
	    }

	}
	Collections.sort(inHomeGuardians, Collections.reverseOrder(new Comparator<JuvenileFamilyForm.MemberList>() {
	    public int compare(JuvenileFamilyForm.MemberList mem1, JuvenileFamilyForm.MemberList mem2)
	    {
		if (mem1.getConfirmedDate() == null && mem2.getConfirmedDate() == null)
		    return 0;

		if (mem1.getConfirmedDate() == null)
		    return -1;

		if (mem2.getConfirmedDate() == null)
		    return 1;

		return mem1.getConfirmedDate().compareTo(mem2.getConfirmedDate());
	    }

	}));

	return ((JuvenileFamilyForm.MemberList) inHomeGuardians.get(0));
    }

    /**
     * @param memberNum
     * @return
     */
    public static JuvenileMemberForm.MemberContact getFamilyMemberPhone(String memberNum)
    {
	GetFamilyMemberContactEvent getFamilyMemberContact = new GetFamilyMemberContactEvent();
	getFamilyMemberContact.setMemberId(memberNum);

	CompositeResponse replyEvent = MessageUtil.postRequest(getFamilyMemberContact);
	Collection<FamilyMemberPhoneResponseEvent> phoneList = MessageUtil.compositeToCollection(replyEvent, FamilyMemberPhoneResponseEvent.class);

	List theList = new ArrayList(phoneList);
	ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
	sortFields.add(new ReverseComparator(new BeanComparator("entryDate")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort(theList, multiSort);
	Collection<FamilyMemberPhoneResponseEvent> sortedPhoneList = new ArrayList(theList);

	// get primary phone or most recent entered if no primary
	JuvenileMemberForm.MemberContact familyPhone = new JuvenileMemberForm.MemberContact();
	String phoneSet = "N";
	for (FamilyMemberPhoneResponseEvent phone : sortedPhoneList)
	{
	    if (phone.isPrimaryInd())
	    {
		familyPhone = UIJuvenileHelper.getJuvMemberFormMemberContactFROMContactRespEvt(phone);
		break;
	    }
	    if ("N".equals(phoneSet))
	    {
		familyPhone = UIJuvenileHelper.getJuvMemberFormMemberContactFROMContactRespEvt(phone);
		phoneSet = "Y";
	    }
	}
	phoneSet = null;
	theList = null;
	return familyPhone;
    }
    public static JuvenileMemberForm.MemberContact getFamilyMemberEmail(String memberNum)
    {
	GetFamilyMemberContactEvent getFamilyMemberContact = new GetFamilyMemberContactEvent();
	getFamilyMemberContact.setMemberId(memberNum);

	CompositeResponse replyEvent = MessageUtil.postRequest(getFamilyMemberContact);
	Collection<FamilyMemberEmailResponseEvent> emailList = MessageUtil.compositeToCollection(replyEvent, FamilyMemberEmailResponseEvent.class);
	List theList = new ArrayList(emailList);
	ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
	sortFields.add(new ReverseComparator(new BeanComparator("entryDate")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort(theList, multiSort);
	Collection<FamilyMemberEmailResponseEvent> sortedEmailList = new ArrayList(theList);
	JuvenileMemberForm.MemberContact familyEmail = new JuvenileMemberForm.MemberContact();
	for ( FamilyMemberEmailResponseEvent email : sortedEmailList ){
	    familyEmail = UIJuvenileHelper.getJuvMemberFormMemberContactFROMEmailRespEvt(email);
	}
	
	theList = null;
	sortedEmailList = null;
	
	return familyEmail;
    }

    /**
     * getCurrentActiveConstellation
     * 
     * @param juvenileNum
     * @return
     */
    public static Collection<FamilyConstellationListResponseEvent> getCurrentActiveConstellation(String juvenileNum)
    {
	GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();
	getCurrentConstellation.setJuvenileNum(juvenileNum);

	CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
	Collection constellations = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);

	return constellations;
    }
    
    /**
     * getFamilyInfoForMigratedRecordsWithoutCasefile
     * 
     * @param juvenileNum
     * @return
     */
    public static Collection<JuvReferralFamilyResponseEvent> getFamilyInfoForMigratedRecordsWithoutCasefile(String juvenileNum)
    {
	GetJuvReferralFamilyInfoEvent getJuvRefFamilyInfo = new GetJuvReferralFamilyInfoEvent();
	getJuvRefFamilyInfo.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(getJuvRefFamilyInfo);
	if(replyEvent != null)
	{
	    Collection familyInfo = MessageUtil.compositeToCollection(replyEvent, JuvReferralFamilyResponseEvent.class);
	    return familyInfo;
	}
	else {
	    return null;
	}
    }

    /**
     * getFamilyMemberAddress
     * 
     * @param memberNum
     * @return
     */
    public static Address getFamilyMemberAddress(String memberNum)
    {
	GetFamilyMemberAddressEvent memberAddressEvent = (GetFamilyMemberAddressEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERADDRESS);
	memberAddressEvent.setMemberNum(memberNum);

	CompositeResponse response = MessageUtil.postRequest(memberAddressEvent);
	Collection<AddressResponseEvent> addressResponses = MessageUtil.compositeToCollection(response, AddressResponseEvent.class);

	Address addr = new Address();
	if (addressResponses.size() > 0)
	{ // addresses will be sorted by entry
	  // date (latest will show first)
	    Collections.sort((List) addressResponses);
	    
	    for (AddressResponseEvent addre : addressResponses)
	    {
		if ("RES".equalsIgnoreCase(addre.getAddressTypeId()))
		{
		    addr.setStreetNum(addre.getStreetNum());
		    addr.setStreetName(addre.getStreetName());
		    addr.setStreetType(addre.getStreetType());
		    addr.setAptNum(addre.getAptNum());
		    addr.setCity(addre.getCity());
		    addr.setState(addre.getState());
		    addr.setCounty(addre.getCounty());
		    addr.setZipCode(addre.getZipCode());
		    addr.setAdditionalZipCode(addre.getAdditionalZipCode());
		    addr.setValidated(addre.getValidated());
		    addr.setStreetNumSuffix(addre.getStreetNumSuffix());
		    addr.setCreateDate(addre.getCreateDate());
		    //added for US 71172
		    addr.setStreetTypeId(addre.getStreetTypeId());
		    addr.setStateId(addre.getStateId());
		    addr.setAddressType(addre.getAddressType());
		    addr.setAddressTypeId(addre.getAddressTypeId());
		    addr.setStreetNumSuffixId(addre.getStreetNumSuffixId());
		    addr.setCountyId(addre.getCountyId());
		    addr.setAddressId(addre.getAddressId());
		    
		    break; // Stop after finding the first "RES" address
		}
	    }

//	    AddressResponseEvent addre = (AddressResponseEvent) ((List) addressResponses).get(0);

	    
	    /*CodeResponseEvent evt = null;
	    Collection countyCodes = CodeHelper.getCountyCodes();
	    if (addre.getCountyId() != null && !addre.getCountyId().equals(""))
	    {
		evt = UIUtil.findCodeResponseEvent(countyCodes.iterator(), addre.getCountyId());
		addr.setCounty(evt.getDescription());
	    }else if (addre.getCountyId() == null){
		addr.setCounty("");
	    }	  */  
	}

	return addr;
    }
	
	/**
	 * @param addressId
	 * @return
	 */
	public static Address getAddressFromAddressId(String addressId)
	{
		 GetAddressByIdEvent addressEvent = (GetAddressByIdEvent)EventFactory.getInstance("GetAddressById");
		 addressEvent.setAddressId(addressId);
		 CompositeResponse compositeResponse = MessageUtil.postRequest(addressEvent);
		 Collection<AddressResponseEvent> addressResponses = MessageUtil.compositeToCollection(compositeResponse, AddressResponseEvent.class);
		Address address = new Address();
		if (addressResponses.size() > 0) {  
			// addresses will be sorted by entry date (latest will show first)
			Collections.sort((List) addressResponses);
			AddressResponseEvent addressRespEvt = (AddressResponseEvent)((List) addressResponses).get(0);
			Collection countyCodes = CodeHelper.getCountyCodes();
			Collection stateCodes = CodeHelper.getStateCodes();
			address.setStreetNum(addressRespEvt.getStreetNum());
			address.setStreetName(addressRespEvt.getStreetName());
			String streetType = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, addressRespEvt.getStreetTypeId());
			address.setStreetType( streetType );
			address.setStreetTypeId(addressRespEvt.getStreetTypeId());
			address.setAptNum(addressRespEvt.getAptNum());
			address.setCity(addressRespEvt.getCity());
			address.setStateId(addressRespEvt.getStateId());
			address.setAddressId(addressRespEvt.getAddressId());
			address.setCountyId(addressRespEvt.getCountyId());
			address.setCounty(addressRespEvt.getCounty());
			address.setZipCode(addressRespEvt.getZipCode());
			address.setAdditionalZipCode(addressRespEvt.getAdditionalZipCode());
			address.setValidated(addressRespEvt.getValidated());
			if( addressRespEvt.getStreetNumSuffixId() != null || StringUtils.isNotEmpty(addressRespEvt.getStreetNumSuffixId())){
			    
			    address.setStreetNumSuffix( addressRespEvt.getStreetNumSuffixId());
			}else{
			    address.setStreetNumSuffix(addressRespEvt.getStreetNumSuffix());
			}
			
			address.setCreateDate(addressRespEvt.getCreateDate());
			address.setAddressType(addressRespEvt.getAddressType());
			address.setAddressTypeId(addressRespEvt.getAddressTypeId());
			CodeResponseEvent evt = null;
			 if (addressRespEvt.getCountyId() != null && !addressRespEvt.getCountyId().equals(""))
			        {
			            evt = UIUtil.findCodeResponseEvent(countyCodes.iterator(), addressRespEvt.getCountyId());
			            address.setCounty(evt.getDescription());
			        }else if (addressRespEvt.getCountyId() == null){
			            address.setCounty("");
			        }
			 if(addressRespEvt.getStateId() != null && !addressRespEvt.getStateId().equals("")){
			     evt = UIUtil.findCodeResponseEvent(stateCodes.iterator(), addressRespEvt.getStateId());
			     address.setState(evt.getDescription());
			 }
		}

		return address;
	}


    /**
     * getPrimaryGuardianPhone
     * 
     * @param juvenileNum
     * @return MemberContact
     */
    public static JuvenileMemberForm.MemberContact getPrimaryGuardianPhone(String juvenileNum)
    {
	Collection<FamilyConstellationListResponseEvent> constellationList = getCurrentActiveConstellation(juvenileNum);
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
	    if (familyMembers.size() > 0)
	    {
		List<GuardianBean> guardians = new ArrayList<GuardianBean>();

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
			    guardians.add(pbean);
			}
		    }
		}

		if (guardians != null)
		{
		    Address familyAddress = null;
		    JuvenileMemberForm.MemberContact familyPhone = null;
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
				familyPhone = UIJuvenileHelper.getFamilyMemberPhone(gbean.getMemberNum());
				return familyPhone;
			    }
			}
			if (priContactFound == false)
			{
			    GuardianBean gbean0 = guardians.get(0);
			    familyAddress = getFamilyMemberAddress(gbean0.getMemberNum());
			    GuardianBean gbean1 = guardians.get(1);
			    Address familyAddress1 = getFamilyMemberAddress(gbean1.getMemberNum());
			    if ("true".equalsIgnoreCase(gbean0.getInHomeStatus()))
			    {
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    // create date can be null if no address found
				    if (familyAddress1.getCreateDate() == null)
				    {
					memberNum = gbean0.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() == null)
				    {
					memberNum = gbean1.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() != null && familyAddress1.getCreateDate() != null)
				    {
					if (DateUtil.compare(familyAddress.getCreateDate(), familyAddress1.getCreateDate(), DateUtil.DATE_FMT_1) > 0)
					{
					    memberNum = gbean0.getMemberNum();
					}
					else
					{
					    memberNum = gbean1.getMemberNum();
					}
				    }
				}
				else
				{
				    memberNum = gbean0.getMemberNum();
				}
			    }
			    else
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    memberNum = gbean1.getMemberNum();
				}
			    gbean0 = null;
			    gbean1 = null;
			    familyAddress1 = null;
			}
		    }
		    if (guardians.size() == 1)
		    {
			GuardianBean gbean = guardians.get(0);
			if ("true".equalsIgnoreCase(gbean.getInHomeStatus()))
			{
			    memberNum = gbean.getMemberNum();
			}
			gbean = null;
		    }
		    if (memberNum != null && !"".equals(memberNum))
		    {
			if (familyPhone == null)
			{ // true when no primary contact found
			    familyPhone = UIJuvenileHelper.getFamilyMemberPhone(memberNum);
			    return familyPhone;
			}
		    }
		}
	    }
	}
	return null;
    }
    
    private static String nvl(String value1, String value2){
	return ( value1 != null && value1.length() > 0 ) ? value1 : value2;
    }
    
    public static void setMergeTrait(String juvenileNum, JuvenileProfileForm form)
    {
	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	if (juvenileTraits != null)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvenileTraits)
	    {
		//System.out.println("Trait Type Id: " +  juvenileTrait.getTraitTypeId());
		if (juvenileTrait.getTraitTypeId().equals("MER"))
		{
		    form.setTraitTypeId(juvenileTrait.getTraitTypeId());
		    form.setTraitTypeDescription("Merged Youth");
		    break;
		}

	    }
	
	}// end of if
    }
    
    public static void setMergeTrait(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	if (juvenileTraits != null)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvenileTraits)
	    {
		if (juvenileTrait.getTraitTypeId().equals("MER"))
		{
		    form.setTraitTypeId(juvenileTrait.getTraitTypeId());
		    form.setTraitTypeDescription("Merged Youth");
		    break;
		}

	    }
	
	}// end of if
    }
    
    
    public static void updateOver21FamilyMember(String familyMemberId, boolean isOver21)
    {
	if(familyMemberId != null && !"".equals(familyMemberId)){
	    
	    FamilyMember familyMember = FamilyMember.find(familyMemberId);
		
		if(familyMember != null)
		{
		    if(familyMember.isOver21() != isOver21)
		    {
			IHome home = new Home();
			       
			familyMember.setOver21(isOver21);
			home.bind(familyMember);
		    }
		}
		    
		familyMember = null;	    
	}		
    }
    
    
    public static String getAttorneyEmail(String barNumber)
    {
	String attorneyEmail = null;
	
	if(barNumber == null || "".equals(barNumber))
	{
	    return attorneyEmail;
	}
	
	 //bearer token to use for ajax call to get attorney email 
	    Token bearerToken = SecurityManagerWebServiceHelper.getAuthTokenURLConnection(); //PDSecurityHelper.getToken();
	    String accessToken = bearerToken.getAccess_token();
	    
	    URL url;
	    HttpsURLConnection con = null;
	    try
	    {
		String securityManagerServerName = AppProperties.getInstance().getProperty("SecurityManagerServerName");
		//"https://coreservices.qa.ja.itc.harriscountytx.gov/securityapi/GetUserByCredential/6/13589050"
		
		url = new URL("https://" + securityManagerServerName + "/securityapi/GetUserByCredential/6/" + barNumber);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Length", "0");
	        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	        connection.setRequestProperty("Accept", "application/json");
	        connection.setRequestProperty("Authorization", "Bearer " + bearerToken.getAccess_token());
	        
    	     	// Get response code
    	     	int responseCode = connection.getResponseCode();
    	     	
    	     	if(responseCode == 200)
    	     	{
    	     	    InputStream responseStream = new BufferedInputStream(connection.getInputStream());
        	    ObjectMapper mapper = new ObjectMapper();
        	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	    Attorney attorney = mapper.readValue(responseStream, new TypeReference<Attorney>(){});
        	    responseStream.close();	
        		
        	    if(attorney != null && attorney.getEmail() != null && !"".equals(attorney.getEmail()))
        	    {
        		attorneyEmail = attorney.getEmail();
        	    }
    	     	} else {
    	     	    System.out.println("Api (" + url + ")" + " call failed. Response code: " + responseCode);
    	     	}
		
		return attorneyEmail;
		    
	    }
	    catch (IOException e)
	    {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    return attorneyEmail;	
    }
    
    
    public static void PopulateTJPCdisp(UpdateJJSCLCourtSettingEvent court, String hearingDisposition)
    {
	if(hearingDisposition != null && !"".equals(hearingDisposition))
	{
	    JuvenileDispositionCode dispCode = JuvenileDispositionCode.find("codeAlpha",hearingDisposition);
	    if(dispCode != null && !"".equals(dispCode))
	    {
		if(court != null)
		{
		    int tjpcCode = Integer.parseInt(dispCode.getJPCCode());
		    if(tjpcCode > 0)
		    {
			court.setTJPCDisp(dispCode.getJPCCode());
		    }
		    
		}
	    }
	    	    
	}
    }
    
    public static void PopulateReferralTJPCdisp(UpdateJuvenileReferralEvent updateEvt, String intakeDescisionId)
    {
	if(intakeDescisionId != null && !"".equals(intakeDescisionId))
	{
	    JuvenileReferralDispositionCode dispCode = JuvenileReferralDispositionCode.find(intakeDescisionId);
	    if(dispCode != null && !"".equals(dispCode))
	    {
		if(updateEvt != null)
		{
		    int tjpcCode = Integer.parseInt(dispCode.getTjpcCode());
		    if(tjpcCode > 0)
		    {
			updateEvt.setTJPCDisp(dispCode.getTjpcCode());
		    }
		    
		}
	    }
	    	    
	}
    }
    
    public static void PopulateReferralTJPCdispCode(SaveJJSReferralEvent saveEvt, String intakeDescisionId)
    {
	if(intakeDescisionId != null && !"".equals(intakeDescisionId))
	{
	    JuvenileReferralDispositionCode dispCode = JuvenileReferralDispositionCode.find(intakeDescisionId);
	    if(dispCode != null && !"".equals(dispCode))
	    {
		if(saveEvt != null)
		{
		    int tjpcCode = Integer.parseInt(dispCode.getTjpcCode());
		    if(tjpcCode > 0)
		    {
			saveEvt.setTJPCDisp(dispCode.getTjpcCode());
		    }
		    
		}
	    }
	    	    
	}
    }
    
    public static String getOfficerNameFromLogonId(String juvenileNum)
    {
	String updateUser = null;
	String updateUserLogonId = null;
	JuvenileCore juvCore = null;
	juvCore = JuvenileCore.findCore(juvenileNum);
	
	if(juvCore != null && juvCore.getLastActionBy() != null && !"".equals(juvCore.getLastActionBy()))
	{
	    updateUserLogonId = juvCore.getLastActionBy();	    
	    updateUser = getUpdateUser(juvCore, updateUserLogonId);	    
	}
	
	if(updateUser == null || "".equals(updateUser))
	{
	    if(juvCore != null && juvCore.getUpdateUser() != null && !"".equals(juvCore.getUpdateUser()))
	    {
		updateUserLogonId = juvCore.getUpdateUser();
		updateUser = getUpdateUser(juvCore, updateUserLogonId);
	    }	    
	}    
		
	return updateUser;
    }
    
    public static String getUpdateUser(JuvenileCore juvCore, String updateUserLogonId)
    {
	String updateUser = null;
	
	if(juvCore != null)
	{	    
	    if(updateUserLogonId != null && !"".equals(updateUserLogonId))
	    {
		if(updateUserLogonId.equalsIgnoreCase("JIMS2USE"))
		{
		    updateUser = "";
		}
		else
		{
		    OfficerProfile officer = OfficerProfile.findByLogonId(updateUserLogonId);
		    
		    if(officer != null)
		    {
			updateUser = officer.getLastName() + ", " + officer.getFirstName();
		    }
		}		
	    }
	    
	}
	
	return updateUser;
    }
    
    public static boolean juvenileHasConstellation(String juvenileId)
    {
	boolean hasConstellation = false;
	List<FamilyConstellation> constellationList = new ArrayList<FamilyConstellation>();
	
	    if(juvenileId != null && !"".equals(juvenileId))
	    {
		GetFamilyConstellationsEvent reqEvent = new GetFamilyConstellationsEvent();
		reqEvent.setJuvenileNum(juvenileId);

		Iterator<FamilyConstellation> listIter = FamilyConstellation.findAll(reqEvent);               	                   	
            	    
                while(listIter.hasNext())
                {
                    FamilyConstellation constellation = (FamilyConstellation)listIter.next();
                    constellationList.add(constellation);
                }                	 	                	
	    } 
	    
	    if(constellationList.size() > 0){
		hasConstellation = true;
	    }
	    
	 return hasConstellation;
    }
    
    
}
    

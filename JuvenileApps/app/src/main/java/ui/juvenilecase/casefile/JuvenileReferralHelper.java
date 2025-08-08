/**
 * 
 */
package ui.juvenilecase.casefile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.codetable.GetAllJuvenileReferralSourcesEvent;
import messaging.codetable.GetJuvenileDispositionCodeEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileReferralSourceResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileAssignmentsEvent;
import messaging.juvenilecase.GetJuvenileCasefilePetitionsEvent;
import messaging.juvenilecase.GetJuvenileCasefileReferralsEvent;
import messaging.juvenilecase.SaveJuvenileTraitsEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JPOofRecSupervisionTypeCategoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileReferralVOPResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.reply.JJSChargeResponseEvent;
import messaging.productionsupport.GetProductionSupportCasefilesByJuvEvent;
import messaging.productionsupport.GetTransOffenseReferralsQueryEvent;
import messaging.referral.GetVOPDetailsEvent;
import messaging.referral.GetVOPDetailsJuvNumEvent;
import messaging.referral.SaveJJSReferralEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.security.AllUserAccessInfoBean;
import mojo.km.security.ISecurityManager;
import mojo.km.security.IUserInfo;
import mojo.km.security.JIMS2FeaturesEntityBean;
import mojo.km.security.helper.SecurityUtil;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.Features;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileWarrantConstants;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import pd.contact.user.UserProfile;
import pd.juvenile.JuvenileCore;
import pd.juvenile.JuvenileHelper;
import pd.juvenilecase.JuvenileTrait;
import pd.juvenilecase.referral.JJSReferral;
import pd.juvenilecase.referral.JJSSVIntakeHistory;
import pd.security.SecurityService;
import pd.transferobjects.helper.UserProfileHelper;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.SocialSecurity;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.TraitsForm;
import ui.juvenilecase.referral.JuvenileReferralMemberDetailsBean;
import ui.juvenilecase.referral.form.JuvenileReferralForm;

/**
 * @author dwilliamson
 */
public class JuvenileReferralHelper
{

    /*
     * Get the max severity level record for each Referral
     * 
     * @param referralList
     * 
     * @param juvenileNum
     */
    public static String getMaxSeverityOffense(String controllingReferral, String juvenileNum)
    {
	// get petition with max severity level for each referral
	if (controllingReferral != null)
	{
	    String referralNum = UIConstants.EMPTY_STRING;
	    // get petitions
	    GetJuvenileCasefilePetitionsEvent pet = (GetJuvenileCasefilePetitionsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEPETITIONS);

	    pet.setJuvenileNum(juvenileNum);
	    pet.setReferralNum(controllingReferral);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(pet);

	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    Map map = MessageUtil.groupByTopic(response);

	    Collection<JJSChargeResponseEvent> petitions = (Collection) map.get(PDJuvenileWarrantConstants.JJS_CHARGE_EVENT_TOPIC);
	    int count = 0;
	    if (petitions != null)
	    {
		HashMap petitionsMap = new HashMap();
		Iterator<JJSChargeResponseEvent> petIter = petitions.iterator();
		while (petIter.hasNext())
		{
		    JJSChargeResponseEvent chgResp = petIter.next();
		    if (count == 0 && !petIter.hasNext())
		    {
			// if this is the while's first iteration and
			// there is *not* another entry in the HashMap
			referralNum = controllingReferral;
			if (notNullNotEmptyString(chgResp.getLevelDegree()))
			{
			    referralNum = (referralNum + " - " + chgResp.getLevelDegree());
			}
			break;
		    }

		    if (petitionsMap == null || petitionsMap.isEmpty() || !petitionsMap.containsKey(chgResp.getLevelDegree()))
		    {
			petitionsMap.put(chgResp.getLevelDegree(), chgResp);
		    }
		    else
		    {
			JJSChargeResponseEvent tempResp = (JJSChargeResponseEvent) petitionsMap.get(chgResp.getLevelDegree());
			if (chgResp.getPetitionDate().compareTo(tempResp.getPetitionDate()) > 0)
			{
			    petitionsMap.remove(tempResp.getLevelDegree());
			    petitionsMap.put(chgResp.getLevelDegree(), chgResp);
			}
		    }
		    ++count;
		} // while

		if (count != 0)
		{
		    // we're in here because level degree was never
		    // appended to the referral number in the previous loop
		    referralNum = getMaxSeverity(petitionsMap, controllingReferral);
		}
	    }
	    else
	    { // else petitions collection is null - get the offenses
		count = 0;
		Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses(juvenileNum, controllingReferral);
		HashMap offenseMap = new HashMap();
		if (offenses != null)
		{
		    Iterator<JJSOffenseResponseEvent> offIter = offenses.iterator();
		    while (offIter.hasNext())
		    {
			JJSOffenseResponseEvent offResp = offIter.next();
			if (count == 0 && !offIter.hasNext())
			{
			    // if this is the while's first iteration and
			    // there is *not* another entry in the offenses
			    // Collection
			    referralNum = controllingReferral;
			    referralNum = (referralNum + " - " + offResp.getCatagory());
			    break;
			}

			if (offenseMap == null || offenseMap.isEmpty() || !offenseMap.containsKey(offResp.getCatagory()))
			{
			    offenseMap.put(offResp.getCatagory(), offResp);
			}
			else
			{
			    JJSOffenseResponseEvent tempResp = (JJSOffenseResponseEvent) offenseMap.get(offResp.getCatagory());
			    if (offResp.getOffenseDate().compareTo(tempResp.getOffenseDate()) > 0)
			    {
				offenseMap.remove(tempResp.getCatagory());
				offenseMap.put(offResp.getCatagory(), offResp);
			    }
			}
			++count;
		    } // while
		}

		if (count != 0)
		{
		    // we're in here because level degree was never
		    // appended to the referral number in the previous loop
		    referralNum = getMaxSeverity(offenseMap, controllingReferral);
		}
	    }
	    checkReferralNumber(referralNum);
	    return referralNum;

	} // for
	return "";
    }

    /*
     * Added new for User story 14257:Get the most severe offense code(sequence num 1) for each Controlling Referral
     * 
     * @param referralList
     * 
     * @param juvenileNum
     */
    public static String getMaxSeverityOffenseCode(String controllingReferral, String juvenileNum)
    {

	if (controllingReferral != null)
	{
	    String referralNum = UIConstants.EMPTY_STRING;
	    int count = 0;
	    Collection offenses = UIJuvenileCasefileClosingHelper.getOffenses(juvenileNum, controllingReferral);
	    if (offenses != null)
	    {
		Iterator<JJSOffenseResponseEvent> offIter = offenses.iterator();
		while (offIter.hasNext())
		{
		    JJSOffenseResponseEvent offResp = offIter.next();
		    String sequenceNum = offResp.getSequenceNum();
		    String tempJuvNum = offResp.getJuvenileNum();
		    String tempRefNum = offResp.getReferralNum();
		    if (juvenileNum.equals(tempJuvNum) && controllingReferral.equals(tempRefNum))
		    {
			if (count == 0 && !offIter.hasNext())
			{
			    // if this is the while's first iteration and
			    // there is *not* another entry in the offenses
			    // Collection
			    referralNum = controllingReferral;
			    referralNum = (referralNum + " - " + offResp.getOffenseCode());
			    break;
			}
			if (sequenceNum.equalsIgnoreCase("1"))
			{
			    referralNum = controllingReferral;
			    referralNum = (referralNum + " - " + offResp.getOffenseCode());
			    break;
			}
			++count;
		    }
		} // while
	    }
	    checkReferralNumber(referralNum);
	    return referralNum;

	} // for
	return "";
    }

    /*
     * @param offenseMap
     * 
     * @param referralNum
     * 
     * @return
     */
    public static String getMaxSeverity(HashMap offenseMap, String referralNum)
    {
	String severity = UIConstants.EMPTY_STRING;

	if (offenseMap != null && offenseMap.size() != 0)
	{
	    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF) != null)
	    {
		severity = referralNum + " - CF";
	    }
	    else
		if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1) != null)
		{
		    severity = referralNum + " - F1";
		}
		else
		    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2) != null)
		    {
			severity = referralNum + " - F2";
		    }
		    else
			if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3) != null)
			{
			    severity = referralNum + " - F3";
			}
			else
			    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF) != null)
			    {
				severity = referralNum + " - JF";
			    }
			    else
				if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA) != null)
				{
				    severity = referralNum + " - MA";
				}
				else
				    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB) != null)
				    {
					severity = referralNum + " - MB";
				    }
				    else
					if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC) != null)
					{
					    severity = referralNum + " - MC";
					}
					else
					    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO) != null)
					    {
						severity = referralNum + " - CO";
					    }
					    else
						if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO) != null)
						{
						    severity = referralNum + " - FC";
						}
						else
						    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC) != null)
						    {
							severity = referralNum + " - AC";
						    }
	}

	return severity;
    }

    /*
     * @param offenseMap
     * 
     * @param referralNum
     * 
     * @return
     */
    public static String getMaxSeverityCode(HashMap offenseMap, String referralNum)
    {
	String severity = UIConstants.EMPTY_STRING;
	JJSChargeResponseEvent chgResp;

	if (offenseMap != null && offenseMap.size() != 0)
	{
	    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF) != null)
	    {
		//severity = referralNum + " - CF";
		chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CF);
		severity = referralNum + " - " + chgResp.getOffenseCodeId();
	    }
	    else
		if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1) != null)
		{
		    //severity = referralNum + " - F1";
		    chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F1);
		    severity = referralNum + " - " + chgResp.getOffenseCodeId();
		}
		else
		    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2) != null)
		    {
			//severity = referralNum + " - F2";
			chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F2);
			severity = referralNum + " - " + chgResp.getOffenseCodeId();
		    }
		    else
			if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3) != null)
			{
			    //severity = referralNum + " - F3";
			    chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_F3);
			    severity = referralNum + " - " + chgResp.getOffenseCodeId();
			}
			else
			    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF) != null)
			    {
				//severity = referralNum + " - JF";
				chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_JF);
				severity = referralNum + " - " + chgResp.getOffenseCodeId();
			    }
			    else
				if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA) != null)
				{
				    //severity = referralNum + " - MA";
				    chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MA);
				    severity = referralNum + " - " + chgResp.getOffenseCodeId();
				}
				else
				    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB) != null)
				    {
					//severity = referralNum + " - MB";
					chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MB);
					severity = referralNum + " - " + chgResp.getOffenseCodeId();
				    }
				    else
					if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC) != null)
					{
					    //severity = referralNum + " - MC";
					    chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_MC);
					    severity = referralNum + " - " + chgResp.getOffenseCodeId();
					}
					else
					    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO) != null)
					    {
						//severity = referralNum + " - CO";
						chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_CO);
						severity = referralNum + " - " + chgResp.getOffenseCodeId();
					    }
					    else
						if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO) != null)
						{
						    //severity = referralNum + " - FC";
						    chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_SO);
						    severity = referralNum + " - " + chgResp.getOffenseCodeId();
						}
						else
						    if (offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC) != null)
						    {
							//severity = referralNum + " - AC";
							chgResp = (JJSChargeResponseEvent) offenseMap.get(UIConstants.OFFENSE_LEVEL_DEGREE_AC);
							severity = referralNum + " - " + chgResp.getOffenseCodeId();
						    }
	}

	return severity;
    }

    /*
     * given a referral number, ensure it's well-formed
     */
    private static String checkReferralNumber(String referralNum)
    {
	String str = UIConstants.EMPTY_STRING;
	String newNum = UIConstants.EMPTY_STRING;
	str = referralNum;

	int firstIndex = str.indexOf("-"), lastIndex = str.lastIndexOf("-");

	if (firstIndex != (-1))
	{ // found a first occurrence
	    if (lastIndex != (-1) && (firstIndex != lastIndex))
	    {
		// last occurrence found and both char offsets differ,
		// which means the number looks like, "1010 - JF - JF"
		newNum = str.substring(0, (lastIndex - 1));
		referralNum = newNum;
	    }
	}
	return referralNum;
    }
    
    /**
     * valid referral exists for juv. court action changes.
     * @param referralNum
     * @param juvenileNum
     * @return
     */
    public static boolean isReferralAssignedForJuv(String referralNum,String juvenileNum){
	Iterator<JJSReferral> referralIter = JJSReferral.findAll("juvenileNum", juvenileNum );
	while (referralIter.hasNext()){
	    return true;
	}
	return false;
    }
    
    
    
    /**
     * 
     * @param controllingReferral
     * @param juvenileNum
     * @param supervisionNum
     * @return
     */
    public static String getFinalDisposition(String controllingReferral, String juvenileNum, String supervisionNum)
    {

	if (controllingReferral != null)
	{
	    // put GetJuvenileCasefileReferralsEvent and JuvenileCasefileReferralsResponseEvent here
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	    dispatch.getReply();
	    GetJuvenileCasefileReferralsEvent casefileReferrelsEvent = new GetJuvenileCasefileReferralsEvent();
	    casefileReferrelsEvent.setSupervisionNum(supervisionNum);
	    casefileReferrelsEvent.setJuvenileNum(juvenileNum);

	    dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(casefileReferrelsEvent);

	    JuvenileCasefileReferralsResponseEvent refresp = null;
	    CompositeResponse response = (CompositeResponse) dispatch.getReply();
	    Collection referrals = MessageUtil.compositeToCollection(response, JuvenileCasefileReferralsResponseEvent.class);
	    Iterator casefileReferrals = referrals.iterator();
	    while (casefileReferrals.hasNext())
	    {
		refresp = (JuvenileCasefileReferralsResponseEvent) casefileReferrals.next();
		if (refresp.getReferralNumber().equals(controllingReferral))
		{
		    break;
		}
	    }
	    String dispDesc = UIConstants.EMPTY_STRING;
	    if (refresp != null)
	    {
		GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
		if (refresp.getFinalDisposition() != null && !refresp.getFinalDisposition().equals(""))
		{
		    courtDisp.setCode(refresp.getFinalDisposition());
		    CompositeResponse resp = postRequestEvent(courtDisp);
		    int count = 0;
		    Collection juvDisp = MessageUtil.compositeToCollection(resp, JuvenileDispositionCodeResponseEvent.class);
		    if (juvDisp != null)
		    {
			Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvDisp.iterator();
			while (juvCodeIter.hasNext())
			{
			    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
			    dispDesc = dispResp.getShortDesc();
			    ++count;
			}
		    }
		    return dispDesc;
		}
	    }
	}
	return "";
    }

    /*
     * given a String, return true if it's not null and not empty
     * @param str
     * @return
     */
    private static boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.trim().length() > 0));
    }

    /**
     * Utility method to help post method to the PD. Takes in a completed set
     * event, posts it to the PD and runs it through
     * MessageUtil.processReturnException before returning the composite
     * response to the user
     * 
     * @param event
     *            -- the RequestEvent to post to the PD
     * @throws -- throws null pointer exception if incoming event is null
     * @return -- the Composite Response
     */
    private static CompositeResponse postRequestEvent(RequestEvent event)
    {
	return MessageUtil.postRequest(event);
    }
    
    public static void populateReferralForm(JuvenileBriefingDetailsForm briefingDetailsForm, JuvenileReferralForm referralForm){
	referralForm.setJuvenileNum(briefingDetailsForm.getJuvenileNum());
	referralForm.setFirstName(briefingDetailsForm.getProfileDetail().getFirstName());
	referralForm.setMiddleName(briefingDetailsForm.getProfileDetail().getMiddleName());
	referralForm.setLastName(briefingDetailsForm.getProfileDetail().getLastName());
	referralForm.setNameSuffix(briefingDetailsForm.getProfileDetail().getNameSuffix());
	referralForm.setRaceId(briefingDetailsForm.getProfileDetail().getRaceId());
	referralForm.setOriginalRaceId(briefingDetailsForm.getProfileDetail().getOriginalRaceId());
	referralForm.setSexId(briefingDetailsForm.getProfileDetail().getSexId());
	referralForm.setCheckedOutTo(briefingDetailsForm.getProfileDetail().getCheckedOutTo());
	//bug fix: 89372
	if(briefingDetailsForm.getProfileDetail().getCheckedOutDate()!=null){
		referralForm.setDateOut(DateUtil.dateToString(briefingDetailsForm.getProfileDetail().getCheckedOutDate(), DateUtil.DATE_FMT_1));
	}else{
	    referralForm.setDateOut(null); 
	}
	//bug fix: 89372
	if("Y".equals(briefingDetailsForm.getProfileDetail().getFromM204Flag())){
	    referralForm.setJuvFromM204Flag("Y");
	}
	SocialSecurity SSN = new SocialSecurity(briefingDetailsForm.getProfileDetail().getCompleteSSN());
	referralForm.setSSN(SSN);
	if(briefingDetailsForm.getHispanic()!= null)
	{
	    if (briefingDetailsForm.getHispanic().equalsIgnoreCase("Yes")){
		referralForm.setHispanic(true);
	    }
		else  if (briefingDetailsForm.getHispanic().equalsIgnoreCase("No")){
		    referralForm.setHispanic(false);
		}
	    }
	//referralForm.setHispanic(Boolean.parseBoolean(briefingDetailsForm.getHispanic()));
	referralForm.setDateOfBirth(DateUtil.dateToString(briefingDetailsForm.getProfileDetail().getDateOfBirth(), "MM/dd/yyyy"));
	referralForm.setRealDOB(DateUtil.dateToString(briefingDetailsForm.getProfileDetail().getRealDOB(), "MM/dd/yyyy"));
	referralForm.setCompleteSSN(briefingDetailsForm.getProfileDetail().getCompleteSSN());
	referralForm.setComments(briefingDetailsForm.getProfileDetail().getComments());
	referralForm.setJuvAddress(briefingDetailsForm.getMemberAddress());
	referralForm.setSchoolDistrictId(briefingDetailsForm.getSchool().getSchoolDistrictId());
	referralForm.setSchoolDistrictDescription(briefingDetailsForm.getSchool().getSchoolDistrict());
	referralForm.setSchoolName(briefingDetailsForm.getSchool().getSchool());
	referralForm.setSchoolId(briefingDetailsForm.getSchool().getSchoolId());
	referralForm.setGradeLevelDescription(briefingDetailsForm.getSchool().getGradeLevelDescription());
	referralForm.setGradeLevelId(briefingDetailsForm.getSchool().getGradeLevelCode());
	referralForm.setProgramAttendingDescription(briefingDetailsForm.getSchool().getProgramAttendingDescription());
	referralForm.setProgramAttendingId(briefingDetailsForm.getSchool().getProgramAttendingCode());
	referralForm.setAttendanceStatusId(briefingDetailsForm.getSchool().getSchoolAttendanceStatusCode());
	referralForm.setAttendanceStatusDescription(briefingDetailsForm.getSchool().getSchoolAttendanceStatusDescription());
	//for records created in M204 and doesn't have case file, address is a string in Ref briefing Form 'juvAddress' field
	//below converting the it to Address object
	if(briefingDetailsForm.getMemberAddress()== null)
	{
	    Address tempAdd = new Address();
	    tempAdd.setStreetName(briefingDetailsForm.getJuvAddress());
	    Collection countyList = referralForm.getCountyList();
	    if(countyList!= null){
		Iterator itr = countyList.iterator();
		while(itr.hasNext()){
		    CodeResponseEvent codeRespEvent = (CodeResponseEvent) itr.next();
		    if(briefingDetailsForm.getJuvCounty().equals(codeRespEvent.getDescription()))
		    {
			 tempAdd.setCountyId(codeRespEvent.getCodeId());
		    }
		}
	    referralForm.setJuvAddress(tempAdd);
	    }
	}
	//guardian info populate
	//referralForm.setMemberDetailsBeanList((List<JuvenileReferralMemberDetailsBean>) briefingDetailsForm.getGuardians());
	
	//US 170859 -  Add Real DOB and wrap a feature around
	String userLogin = null;
	IUserInfo userInfo = SecurityUtil.getCurrentUser();
	userLogin = userInfo.getJIMSLogonId();
	boolean userHasFeature = false;
	userHasFeature = isRealDOBUpdateAllowed(userLogin, Features.JCW_JCW_REF_REALDOB_UPDATE.toString());
	referralForm.setIsRealDOBUpdateAllowed(userHasFeature);	

    }
    
    public static boolean isRealDOBUpdateAllowed(String jimsLoginId, String featureName){
	boolean hasFeature = false;
	
	ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
 	hasFeature = securityManager.isAllowed(featureName);
	
	return hasFeature;
    }
    
    public static List<JuvenileReferralMemberDetailsBean> populateFromGuardianBeanToMemBean(Collection guardians)
    {
	 List<JuvenileReferralMemberDetailsBean> memBeanList = new ArrayList<JuvenileReferralMemberDetailsBean>();
	if(guardians.size()>0){
	   
	    Iterator guardianItr = guardians.iterator();
	    while (guardianItr.hasNext()){
		GuardianBean gBean = (GuardianBean) guardianItr.next();
		JuvenileReferralMemberDetailsBean memberBean = new JuvenileReferralMemberDetailsBean();
		memberBean.setFirstName(gBean.getNameOfPerson().getFirstName());
		memberBean.setMiddleName(gBean.getNameOfPerson().getMiddleName());
		memberBean.setLastName(gBean.getNameOfPerson().getLastName());
		memberBean.setFormattedName(gBean.getNameOfPerson().toString());
		memberBean.setRelationshipDesc(gBean.getRelationshipType());
		
		if( gBean.getRelationshipTypeId() == null ) {
		    
		    List codes = CodeHelper.getRelationshipsToJuvenileCodes(true);
		    for( int y =0;y<codes.size();y++) {
			
			CodeResponseEvent code = (CodeResponseEvent) codes.get(y);
			if(gBean.getRelationshipType().equalsIgnoreCase(code.getDescription())) {
			    
			    memberBean.setRelationshipId(code.getCode());
			    break;
			}
			
		    }
		}else {
		    memberBean.setRelationshipId(gBean.getRelationshipTypeId());
		}

		memberBean.setCompleteSSN(gBean.getCompleteSSN());
		memberBean.setFormattedSSN(gBean.getSSN());
		memberBean.setIncarcerated(gBean.isIncarcerated());
		memberBean.setDeceased(gBean.isDeceased());
		memberBean.setContactPhoneNumber(gBean.getGuardianPhone().getContactPhoneNumber());
		memberBean.setFormattedPhone(gBean.getGuardianPhone().getContactPhoneNumber().getFormattedPhoneNumber());
		memberBean.setMemberAddress(gBean.getGuardianAddress());
		memberBean.setFormattedAddress(JuvenileHelper.formatAddress(gBean.getGuardianAddress()));
		memberBean.setPhoneType(gBean.getGuardianPhone().getContactTypeId());
		memberBean.setPhoneTypeDesc(gBean.getGuardianPhone().getContactType());
		memberBean.setPrimaryContact(gBean.getPrimaryContact());
		//BUG fixes for 80716
		if(gBean.getGuardianPhone()!=null){
		    if(gBean.getGuardianPhone().getPrimaryInd() != null && !gBean.getGuardianPhone().getPrimaryInd().isEmpty()){
    		    	if(gBean.getGuardianPhone().getPrimaryInd().equalsIgnoreCase("primary")){
    		    	    memberBean.setPhoneIndDesc(gBean.getGuardianPhone().getPrimaryInd());
    		    	    memberBean.setPhoneInd("P");
    			    memberBean.setFormattedPhone(gBean.getGuardianPhone().getContactPhoneNumber().getFormattedPhoneNumber());
    		    	} 
    		    }else if(gBean.getGuardianPhone().getContactPhoneNumber()!= null){
    			if(gBean.getGuardianPhone().getContactPhoneNumber().getFormattedPhoneNumber().equals("000-000-0000")){
    			    memberBean.setPhoneInd("U");
    			    memberBean.setPhoneIndDesc("UNKNOWN");
    			}
    		    }
		}
		//BUG fixes for 80716 ENDS
		//the code below is for the case when they add new Guardian and try to MODIFY the same guardian in the same flow 
		memberBean.setMemberNum(gBean.getMemberNum());
		if(gBean.getMemberNum().equals("1")|| gBean.getMemberNum().equals("2") || gBean.getMemberNum().equals("3")){
		    memberBean.setNewMemflag("Y");
		}
		//ends
		if(gBean.isDeceased()){
		memberBean.setIncarceratedOrDeceasedDesc("Deceased");
		memberBean.setIncarceratedOrDeceased("D");
		}else if(gBean.isIncarcerated()){
		    memberBean.setIncarceratedOrDeceasedDesc("Incarcerated");
		    memberBean.setIncarceratedOrDeceased("I");
		}
		memBeanList.add(memberBean);
	    }
	}
	return memBeanList;
	
    }

    /**
	 * validReferralSource
	 * @param source
	 * @return boolean
	 */
	public static Collection getAllReferralSources(){
		
	    GetAllJuvenileReferralSourcesEvent refEvent = (GetAllJuvenileReferralSourcesEvent)EventFactory.getInstance(CodeTableControllerServiceNames.GETALLJUVENILEREFERRALSOURCES);		
		IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
		disp.postEvent(refEvent);
		CompositeResponse resp = (CompositeResponse)disp.getReply();
		Collection referralSources = MessageUtil.compositeToCollection(resp, JuvenileReferralSourceResponseEvent.class);
		  Collections.sort((List<JuvenileReferralSourceResponseEvent>) referralSources, new Comparator<JuvenileReferralSourceResponseEvent>() {
			@Override
			public int compare(JuvenileReferralSourceResponseEvent evt1, JuvenileReferralSourceResponseEvent evt2)
			{
			    if (evt1.getDescription() != null && evt2.getDescription() != null)
				return evt1.getDescription().compareTo(evt2.getDescription());
			    else
				return -1;
			}
		    });
		return referralSources;
	}
	
	  /**
	     * jpoOfRecord
	     * @param juvenileNum
	     * @param form -court
	     */
	    public static void jpoOfRecord(String juvenileNum, JuvenileReferralForm form)
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
			form.setJpo(codeResp.getJpoId());
			//form.setJpoOfRecord(codeResp.getJpo());
			form.setSupervisionCat(codeResp.getSupervisionCategoryId());
			form.setSupervisionType(codeResp.getSupervisionTypeId());
			//form.setSupervisionTypeId(codeResp.getSupervisionType());
			
			//Supervision Category
			CodeResponseEvent supervisionResp = new CodeResponseEvent();
			supervisionResp.setCode(codeResp.getSupervisionCategoryId());
			supervisionResp.setDescription(codeResp.getSupervisionCategory());
			supervisionResp.setDescriptionWithCode(codeResp.getSupervisionCategoryId() + "-" + codeResp.getSupervisionCategory());
			ArrayList temp = new ArrayList();
			temp.add(supervisionResp);
			form.setSupervisionCategories(temp);
			//Supervision Type
			supervisionResp = new CodeResponseEvent();
			supervisionResp.setCode(codeResp.getSupervisionTypeId());
			supervisionResp.setDescription(codeResp.getSupervisionType());
			supervisionResp.setDescriptionWithCode(codeResp.getSupervisionTypeId() + "-" + codeResp.getSupervisionType());
			temp = new ArrayList();
			temp.add(supervisionResp);
			form.setSupervisionTypes(temp);
			form.setSubsequentCasefileId(codeResp.getSupervisionNum());
		    }
		} else
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
		    //saveErrors(aRequest, errors);
		}
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
		String supervisionCategory = "";
		String supervisionNum = "" ;
		while (iter.hasNext())
		{
		    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) iter.next();

		    if (resp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
		    {
			//US 40492 check if there are any open referrals - if yes, then figure out JPO of record
			//get the referrals for the casefile 
			Collection<JuvenileCasefileReferralsResponseEvent> casefileRefs = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(resp.getSupervisionNum(), resp.getJuvenileNum());
			resp.setSupervisionCatDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, resp.getSupervisionCategory()));
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
			    supervisionCategory = casefileAC.getSupervisionCatDesc();
			    supervisionNum = casefileAC.getSupervisionNum();
			}//end if assessmentDate
			else
			{
			    //jpo will be the one on casefileAR
			    jpoOfRecId = casefileAR.getOfficerLoginId();
			    jpoOfRec = casefileAR.getProbationOfficerFullName();
			    supervisionCategoryId = casefileAR.getSupervisionCategory();
			    supervisionType = casefileAR.getSupervisionType();
			    supervisionTypeId = casefileAR.getSupervisionTypeId();
			    supervisionCategory = casefileAR.getSupervisionCatDesc();
			    supervisionNum = casefileAR.getSupervisionNum();
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
			supervisionCategory = casefileAR.getSupervisionCatDesc();
			 supervisionNum = casefileAR.getSupervisionNum();
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
			    supervisionCategory = casefileAC.getSupervisionCatDesc();
			    supervisionNum = casefileAC.getSupervisionNum();
			}//end if assessmentDate
			else
			{
			    //jpo will be the one on casefileAR
			    jpoOfRecId = casefileAR.getOfficerLoginId();
			    jpoOfRec = casefileAR.getProbationOfficerFullName();
			    supervisionCategoryId = casefileAR.getSupervisionCategory();
			    supervisionType = casefileAR.getSupervisionType();
			    supervisionTypeId = casefileAR.getSupervisionTypeId();
			    supervisionCategory = casefileAR.getSupervisionCatDesc();
			    supervisionNum = casefileAR.getSupervisionNum();
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
			supervisionCategory = casefileAC.getSupervisionCatDesc();
			supervisionNum = casefileAC.getSupervisionNum();
		    }
		}//end else if AC
		else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("ID") != null) //US 87430 
		{
		    JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent) casefileMap.get("ID");
		    jpoOfRecId = casefileAD.getOfficerLoginId();
		    jpoOfRec = casefileAD.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAD.getSupervisionCategory();
		    supervisionType = casefileAD.getSupervisionType();
		    supervisionTypeId = casefileAD.getSupervisionTypeId();
		    supervisionCategory = casefileAD.getSupervisionCatDesc();
		    supervisionNum = casefileAD.getSupervisionNum();

		}//end else if ID //NEW US 
		else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("DA") != null) //BUG 87434 (DA comes after AR,AC and ID)
		{
		    JuvenileCasefileSearchResponseEvent casefileResp = (JuvenileCasefileSearchResponseEvent) casefileMap.get("DA");
		    //get briefing form from session and populate
		    jpoOfRecId = casefileResp.getOfficerLoginId();
		    jpoOfRec = casefileResp.getProbationOfficerFullName();
		    supervisionType = casefileResp.getSupervisionType();
		    supervisionCategoryId = casefileResp.getSupervisionCategory();
		    supervisionTypeId = casefileResp.getSupervisionTypeId();
		    supervisionCategory = casefileResp.getSupervisionCatDesc();
		    supervisionNum = casefileResp.getSupervisionNum();

		}//end else if DA 
		
		else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("AD") != null)
		{
		    JuvenileCasefileSearchResponseEvent casefileAD = (JuvenileCasefileSearchResponseEvent) casefileMap.get("AD");
		    jpoOfRecId = casefileAD.getOfficerLoginId();
		    jpoOfRec = casefileAD.getProbationOfficerFullName();
		    supervisionCategoryId = casefileAD.getSupervisionCategory();
		    supervisionType = casefileAD.getSupervisionType();
		    supervisionTypeId = casefileAD.getSupervisionTypeId();
		    supervisionCategory = casefileAD.getSupervisionCatDesc();
		    supervisionNum = casefileAD.getSupervisionNum();

		}//end else if AD
		else if ((JuvenileCasefileSearchResponseEvent) casefileMap.get("PP") != null)
		{
		    JuvenileCasefileSearchResponseEvent casefilePP = (JuvenileCasefileSearchResponseEvent) casefileMap.get("PP");
		    jpoOfRecId = casefilePP.getOfficerLoginId();
		    jpoOfRec = casefilePP.getProbationOfficerFullName();
		    supervisionCategoryId = casefilePP.getSupervisionCategory();
		    supervisionType = casefilePP.getSupervisionType();
		    supervisionTypeId = casefilePP.getSupervisionTypeId();
		    supervisionCategory = casefilePP.getSupervisionCatDesc();
		    supervisionNum = casefilePP.getSupervisionNum();

		}//end else if PP	
		JPOofRecSupervisionTypeCategoryResponseEvent codeResp = new JPOofRecSupervisionTypeCategoryResponseEvent();
		codeResp.setJpoId(jpoOfRecId);
		codeResp.setJpo(jpoOfRec);
		codeResp.setSupervisionCategory(supervisionCategory);
		codeResp.setSupervisionCategoryId(supervisionCategoryId);
		codeResp.setSupervisionType(supervisionType);
		codeResp.setSupervisionTypeId(supervisionTypeId);
		codeResp.setSupervisionNum(supervisionNum);
		return codeResp;
	    }
	    
	    /**
	     * validateOffenseCd
	     * Conditions for this is different from the validateOffenseCd for the District Court (ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper.validateOffenseCd(String))
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
			if (!"Y".equals(respEvent.getInactiveInd()) 
				&& !"Y".equals( respEvent.getDiscontCode() )
				&& offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()))
			{
			    jpEvent = new JuvenileOffenseCodeResponseEvent();
			    jpEvent.setAgeRestrict( respEvent.getAgeRestrict());
			    jpEvent.setOffenseCode(respEvent.getOffenseCode());   //US 71174
			    jpEvent.setCategory(respEvent.getCategory());
			    jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
			    jpEvent.setNumericCode(respEvent.getNumericCode());
			    jpEvent.setShortDescription(respEvent.getShortDescription());
			    jpEvent.setLongDescription(respEvent.getLongDescription());
			    jpEvent.setSeverity(respEvent.getSeverity());
			    jpEvent.setSeveritySubtype(respEvent.getSeveritySubtype()); //US 71173
			    jpEvent.setCitationCode(respEvent.getCitationCode()); //BUG 156574
			    jpEvent.setCitationSource(respEvent.getCitationSource()); //BUG 156574
			    break;
			}
		    }
		}
		return jpEvent;
	    }
	    
	    
	    public static JuvenileOffenseCodeResponseEvent getOffenseCd(String offenseCd)
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
			if ( offenseCd.equalsIgnoreCase(respEvent.getOffenseCode()))
			{
			    jpEvent = new JuvenileOffenseCodeResponseEvent();
			    jpEvent.setAgeRestrict( respEvent.getAgeRestrict());
			    jpEvent.setOffenseCode(respEvent.getOffenseCode());   //US 71174
			    jpEvent.setCategory(respEvent.getCategory());
			    jpEvent.setDpsOffenseCode(respEvent.getDpsOffenseCode());
			    jpEvent.setNumericCode(respEvent.getNumericCode());
			    jpEvent.setShortDescription(respEvent.getShortDescription());
			    jpEvent.setLongDescription(respEvent.getLongDescription());
			    jpEvent.setSeverity(respEvent.getSeverity());
			    jpEvent.setSeveritySubtype(respEvent.getSeveritySubtype()); //US 71173
			    break;
			}
		    }
		}
		return jpEvent;
	    }
	    
	    /**
	     * @param form
	     * @return
	     */
	    public static OfficerProfileResponseEvent validateJPOEntered(JuvenileReferralForm form)
	    {
		
		if(form.getJpo()!= null && !form.getJpo().equals("") && form.getJpo().length()<= 3)	
		{
		    form.setJpo("UV" + form.getJpo().toUpperCase()); //bug 84455
		}else{
		    form.setJpo(form.getJpo().toUpperCase());//BUG 84455
		}
		if ("manageAssignment".equalsIgnoreCase(form.getAction()))
		{
		    form.setAction("manageAssignment");
		}
		else
		{
		    form.setAction("overrideAssignment");
		}
		//Validate the jpo
		OfficerProfileResponseEvent officerProfile = UIUserFormHelper.getUserOfficerProfile(form.getJpo());
		return officerProfile;
	    }
	   
	    public static List<JJSSVIntakeHistory> getIntakeHisoryForJuvNumRefNum(
		    String juvNum, String referralNumber)
	    {
		GetJuvenileCasefileAssignmentsEvent getAssignmentsEvent = new GetJuvenileCasefileAssignmentsEvent();
		    getAssignmentsEvent.setJuvenileNum(juvNum);
		    getAssignmentsEvent.setReferralNum(referralNumber);

		    Iterator<JJSSVIntakeHistory> intakeIter = JJSSVIntakeHistory.findAll(getAssignmentsEvent);
		    //put the items into a collection (List)
		    List<JJSSVIntakeHistory> intakeHistoryList = new ArrayList<JJSSVIntakeHistory> (); 
		    while (intakeIter.hasNext())
		    {
			JJSSVIntakeHistory intakeHistory = (JJSSVIntakeHistory) intakeIter.next();
			intakeHistoryList.add(intakeHistory);
		    }
		    //sort the List based on the create date desc to get the latest
		    Collections.sort((List<JJSSVIntakeHistory>) intakeHistoryList, Collections.reverseOrder(new Comparator<JJSSVIntakeHistory>() {
			@Override
			public int compare(JJSSVIntakeHistory evt1, JJSSVIntakeHistory evt2)
			{
			    if (evt1.getCreateTimestamp() != null && evt2.getCreateTimestamp() != null)
				return evt1.getCreateTimestamp().compareTo(evt2.getCreateTimestamp());
			    else
				return -1;
			}
		    }));
		    return intakeHistoryList;
	    }
	    
	    public static List<JuvenileCasefileTransferredOffenseResponseEvent> getTransOffenseReferralForJuvNum(String juvNum)
	    {
		ArrayList transOffenseReferrals = null;
		GetTransOffenseReferralsQueryEvent getTransOffenseReferrals = (GetTransOffenseReferralsQueryEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.GETTRANSOFFENSEREFERRALSQUERY);
		getTransOffenseReferrals.setJuvenileNumber(juvNum);
		
		CompositeResponse transOffenseReferralsResp = MessageUtil.postRequest( getTransOffenseReferrals );
		transOffenseReferrals = (ArrayList) MessageUtil.compositeToCollection(transOffenseReferralsResp, JuvenileCasefileTransferredOffenseResponseEvent.class);
		if ( transOffenseReferrals!= null 
			&& transOffenseReferrals.size() > 0) {
		    
		    Collections.sort(transOffenseReferrals, Collections.reverseOrder( new Comparator<JuvenileCasefileTransferredOffenseResponseEvent>(){
			@Override
			public int compare(JuvenileCasefileTransferredOffenseResponseEvent r1, JuvenileCasefileTransferredOffenseResponseEvent r2){
			    return r1.getTransOffenseReferralId().compareTo(r2.getTransOffenseReferralId());
			}
		    }));
		    
		}
		
		return transOffenseReferrals;
	    }
	    
	    /** setSaveDetails
	     * @param saveEvt
	     * @param form
	     * @param respEvent
	     * @return
	     */
	    public static SaveJJSReferralEvent setSaveDetails (SaveJJSReferralEvent saveEvt, JuvenileReferralForm form, JuvenileProfileReferralListResponseEvent respEvent)
	    {
		saveEvt.setReferralNum(respEvent.getReferralNumber());
		saveEvt.setReferralSource(respEvent.getReferralSource());
		saveEvt.setIntakeDate(DateUtil.stringToDate(respEvent.getIntakeDecDate(), DateUtil.DATE_FMT_1));	
		saveEvt.setIntakeDecisionId(respEvent.getIntakeDecisionId());
		saveEvt.setOffenses(respEvent.getOffenses());
		saveEvt.setOffenseTotal(respEvent.getOffenses().size()+"");
		saveEvt.setJuvenileNum(form.getJuvenileNum());
		saveEvt.setCasefileGenerate(form.getCasefileGenerate()); 
		saveEvt.setTJJDReferralDate(form.getTJJDReferralDate());
		saveEvt.setReferralTypeInd(form.getFormalReferralType());
		saveEvt.setAssignmentType(form.getAssignmentType());
		saveEvt.setSupervisionCat(form.getSupervisionCat());
		saveEvt.setSupervisionType(form.getSupervisionType());
		saveEvt.setAssignmentDate(DateUtil.stringToDate(form.getAssignmentDateStr(), DateUtil.DATE_FMT_1));
		saveEvt.setReferralDate( respEvent.getReferralDate() );
		//89887 - 88414
		if (form.getAssignmentType() != null)
		{

		    /**
		     * At point of referral assignment, insert the Officer ID code into
		     * the JJS_MS_REFERRAL table, based on the following conditions: If
		     * the Assignment Type falls into Pre-Petition category insert
		     * assigned JPO code into Intake Officer (INASSIGNJPOID) column.
		     * This applies to the following Assignment Type(s): INTAKE
		     */
		    if (form.getAssignmentType().equalsIgnoreCase("INT"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("PP"))
			    {
				saveEvt.setJpoID(form.getJpo());
			    }
			}
		    }
		    /**
		     * If the Assignment Type falls into Pre-Adjudication category,
		     * insert assigned JPO code into the Court Officer (CTASSIGNJPOID)
		     * column. This applies to the following Assignment Type(s):
		     * PRE-ADJUDICATION
		     */
		   else if (form.getAssignmentType().equalsIgnoreCase("PRE"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("AD"))
			    {
				saveEvt.setCtAssignJPOId(form.getJpo());
			    }else{
			        saveEvt.setJpoID(form.getJpo());
			    }
			}
		    }

		    /**
		     * If the Assignment Type falls into Post-Adjudication category,
		     * insert assigned JPO code into the Probation Officer
		     * (PROBATIONJPOID) column. This applies to the following Assignment
		     * Type(s): POST-ADJUDICATION
		     */

		  else  if (form.getAssignmentType().equalsIgnoreCase("PAD"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("AR") || form.getSupervisionCat().equalsIgnoreCase("AC"))
			    {
				saveEvt.setProbJPOId(form.getJpo());
			    }else{
			     saveEvt.setJpoID(form.getJpo());
			    }
			}
		    }
		    /**
		     * If Assignment Type equals TRANSFERRED OFFENSE (OOC), then refer
		     * to Supervision Category to determine proper insertion. If
		     * Supervision Category = PRE-PETITION, then insert assigned JPO
		     * code into the Intake Officer (INASSIGNJPOID) column. If
		     * Supervision Category = PRE-ADJUDICATION, then insert assigned JPO
		     * code into the Court Officer (CTASSINGJPOID) column. If
		     * Supervision Category = POST-ADJUDICATION COMMUNITY or
		     * POST-ADJUDICATION RESIDENTIAL, then insert JPO code into
		     * Probation Officer (PROBATIONJPOID) column.
		     */

		  else  if (form.getAssignmentType().equalsIgnoreCase("TRN"))
		    {
			if (form.getSupervisionCat() != null)
			{
			    if (form.getSupervisionCat().equalsIgnoreCase("PP"))
			    {
				saveEvt.setJpoID(form.getJpo());
			    }else if(form.getSupervisionCat().equalsIgnoreCase("AD")){
				saveEvt.setCtAssignJPOId(form.getJpo());
			    }else if(form.getSupervisionCat().equalsIgnoreCase("AC") || form.getSupervisionCat().equalsIgnoreCase("AR")){
				saveEvt.setProbJPOId(form.getJpo());
			    }else{
			    	saveEvt.setJpoID(form.getJpo());
			    }
			}
		    }
		    else{
		    saveEvt.setJpoID(form.getJpo());
		    }
		}
		//User-story 8814	
		saveEvt.setSubsequentCasefileId(form.getSubsequentCasefileId());
		saveEvt.setOverrideReason(form.getOverrideReasonStr());
		saveEvt.setOverrideOtherComment(form.getOverrideOTHComments());
		return saveEvt;
	    }
	    
	    /**
	     * check if Juvenile has active Referrals
	     * 
	     * @param juvenileNum   
	     * @return
	     */
	    public static boolean hasActiveReferrals(String juvenileNum)
	    {
		Iterator<JJSReferral> referralIter = JJSReferral.findAll("juvenileNum", juvenileNum );
		while (referralIter.hasNext()){
		    JJSReferral ref = (JJSReferral)referralIter.next();
		    if(ref.getCloseDate()==null)
			return true;
		}
		return false;
	    }
	    
	    /**
	     * check if Juvenile has any Referrals
	     * 
	     * @param juvenileNum   
	     * @return
	     */
	    public static boolean hasAnyReferrals(String juvenileNum)
	    {
		Iterator<JJSReferral> referralIter = JJSReferral.findAll("juvenileNum", juvenileNum );
		if (referralIter.hasNext()){
		    return true;		
		}else{
		    return false;
		}		
	    }
	    /**
	     * getViolationOfProbationForJuvNumRefNum
	     * @param juvNum
	     * @param referralNumber
	     * @return Collection<JuvenileReferralVOPResponseEvent>
	     */
	    public static Collection<JuvenileReferralVOPResponseEvent> getVOPRecordsForJuvNumRefNum(
		    String juvNum, String referralNumber)
	    {
        	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
        	GetVOPDetailsEvent event = (GetVOPDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETVOPDETAILS);
        	event.setJuvenileNumber(juvNum);
        	event.setReferralNumber(referralNumber);
        	dispatch.postEvent(event);
        	CompositeResponse responses = (CompositeResponse) dispatch.getReply();
        	Collection<JuvenileReferralVOPResponseEvent> juvProfRefListEvt = MessageUtil.compositeToCollection(responses, JuvenileReferralVOPResponseEvent.class);
        	return juvProfRefListEvt;
	    }
	    
	    /**
	     * getVOPRecordsForJuvNum
	     * @param juvNum
	     * @return Collection<JuvenileReferralVOPResponseEvent>
	     */
	    public static Collection<JuvenileReferralVOPResponseEvent> getexisitngVOPRecordsForJuvNum(String juvNum)
	    {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetVOPDetailsJuvNumEvent event = (GetVOPDetailsJuvNumEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETVOPDETAILSJUVNUM);
        	event.setJuvenileNum(juvNum);
        	dispatch.postEvent(event);
        	CompositeResponse responses = (CompositeResponse) dispatch.getReply();
        	Collection<JuvenileReferralVOPResponseEvent> juvVOPRefListEvt = MessageUtil.compositeToCollection(responses, JuvenileReferralVOPResponseEvent.class);
        	
        	   Collections.sort((List<JuvenileReferralVOPResponseEvent>) juvVOPRefListEvt, new Comparator<JuvenileReferralVOPResponseEvent>() {
			@Override
			public int compare(JuvenileReferralVOPResponseEvent evt1, JuvenileReferralVOPResponseEvent evt2)
			{
			    if (evt1.getReferralNum() != null && evt2.getReferralNum() != null)
				return evt2.getReferralNum().compareTo(evt1.getReferralNum());
			    else
				return -1;
			}
		    });
        	 
        	return juvVOPRefListEvt;
	    }
	    
	    /**
	     * getViolationOfProbationForJuvNumRefNum
	     * @param juvNum
	     * @param referralNumber
	     * @return Collection<JuvenileReferralVOPResponseEvent>
	     */
	    public static Collection<JuvenileReferralVOPResponseEvent> getJCVOPRecordForJuvNumRefNum(String juvNum, String refNum)
	    {
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetVOPDetailsEvent event = (GetVOPDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETVOPDETAILS);
		event.setJuvenileNumber( juvNum );
		event.setReferralNumber( refNum );
		
		dispatch.postEvent(event);
        	CompositeResponse responses = (CompositeResponse) dispatch.getReply();
        	Collection<JuvenileReferralVOPResponseEvent> juvProfRefListEvt = MessageUtil.compositeToCollection(responses, JuvenileReferralVOPResponseEvent.class);
        	return juvProfRefListEvt;
	    }
	    
	    
	    public static String getJuvCasefileNum(String juvenileId){
		
		ArrayList<JuvenileCasefileResponseEvent>casefiles = new ArrayList<>();
		GetProductionSupportCasefilesByJuvEvent casefilesEvent = (GetProductionSupportCasefilesByJuvEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILESBYJUV);
		casefilesEvent.setJuvenileNumber(juvenileId);
		CompositeResponse casefilesResp = MessageUtil.postRequest( casefilesEvent );
		casefiles = (ArrayList) MessageUtil.compositeToCollection(casefilesResp, JuvenileCasefileResponseEvent.class );
		
		Iterator<JuvenileCasefileResponseEvent> casefileIter = casefiles.iterator();
		
		String supervisionNum = null;
		while(casefileIter.hasNext()){
		    
		    JuvenileCasefileResponseEvent casefileEvent = (JuvenileCasefileResponseEvent)casefileIter.next();
		    String caseStatus = casefileEvent.getCaseStatusId();
		    
		    if(caseStatus != null && !caseStatus.equals("C")){
			
			if(caseStatus.equals("A")){ //active
			    supervisionNum = casefileEvent.getSupervisionNum();
			    break;
			} else if (caseStatus.equals("P")){ //pending
			    supervisionNum = casefileEvent.getSupervisionNum();
			    break;
			}  else if (caseStatus.equals("CS")){ //closing
			    supervisionNum = casefileEvent.getSupervisionNum();
			    break;
			} else {
			    supervisionNum = casefileEvent.getSupervisionNum();
			}
		    }
		    
		}
		
		
		return supervisionNum;
		
	    }
	    
	    public static String getActivatedCasefileNumberWithSeqNum1(String juvenileId){
		
		ArrayList<JuvenileCasefileResponseEvent>casefiles = new ArrayList<JuvenileCasefileResponseEvent>();
		GetProductionSupportCasefilesByJuvEvent casefilesEvent = (GetProductionSupportCasefilesByJuvEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEFILESBYJUV);
		casefilesEvent.setJuvenileNumber(juvenileId);
		CompositeResponse casefilesResp = MessageUtil.postRequest( casefilesEvent );
		casefiles = (ArrayList) MessageUtil.compositeToCollection(casefilesResp, JuvenileCasefileResponseEvent.class );
		
		Iterator<JuvenileCasefileResponseEvent> casefileIter = casefiles.iterator();
		
		String supervisionNum = null;
		while(casefileIter.hasNext()){
		    
		    JuvenileCasefileResponseEvent casefileEvent = (JuvenileCasefileResponseEvent)casefileIter.next();		    
		    String caseStatus = null;
		    String seqNum = null;
		    if(casefileEvent != null)
		    {
			caseStatus = casefileEvent.getCaseStatusId();
			seqNum = casefileEvent.getSequenceNum();
			
			if(caseStatus != null && !"".equals(caseStatus) && seqNum != null && !"".equals(seqNum))
			{
			    if(caseStatus.equalsIgnoreCase("A") && seqNum.equals("1"))
			    {
				supervisionNum = casefileEvent.getSupervisionNum();
				return supervisionNum;
			    }
			}			
		    }		    
		}		
		
		return supervisionNum;
		
	    }
	    
	    public static String getRealDobInMSJuvenile(String juvenileNum)
	    {
		String realDob = null;
		
		if(juvenileNum != null)
		{
		    IHome home = new Home();
		    
		    JuvenileCore juvenile = (JuvenileCore) home.find(juvenileNum, JuvenileCore.class);
		    if (juvenile != null && juvenile.getRealDOB() != null && !"".equals(juvenile.getRealDOB()))
		    {
			realDob = DateUtil.dateToString(juvenile.getRealDOB(), "MM/dd/yyyy");
		    }
		}		
		
		return realDob;
	    }
	    
	    public static boolean realDobTraitTypeExists(String juvenileNum, String supervisionNum)
	    {
		boolean realDobTraitTypeExists = false;
		
		Collection<JuvenileTraitResponseEvent> juvTraits = UIJuvenileCaseworkHelper.fetchJuvTraits(juvenileNum);

		    Iterator<JuvenileTraitResponseEvent>juvTraitsIter = juvTraits.iterator();
		    while( juvTraitsIter.hasNext() ) {
			JuvenileTraitResponseEvent traitResp = juvTraitsIter.next();
			
			if(traitResp != null && traitResp.getSupervisionNum() != null && traitResp.getSupervisionNum().equalsIgnoreCase(supervisionNum)){
			    
			    if(traitResp.getTraitTypeId() != null && traitResp.getTraitTypeId().equalsIgnoreCase("DOB")){
				realDobTraitTypeExists = true;
				return realDobTraitTypeExists;
			    }
			}
		    }
		    
		    return realDobTraitTypeExists;
	    }
	    
	    public static void SaveRealDobTraitInfo(String juvenileNum, String realDob, String supervisionNum){
		
		String comment = "REAL DOB: " + realDob;
		String dispositionNum = null;
		String traitTypeId = "DOB";
		String traitStatus = "CU";
		String informationSrcCd = "O";

		if(supervisionNum != null && !"".equals(supervisionNum))
		{
		    boolean isRealDobTraitType = realDobTraitTypeExists(juvenileNum, supervisionNum);
			    
		    if(realDob != null && !"".equals(realDob) && !isRealDobTraitType)
		    {    
                		SaveJuvenileTraitsEvent saveEvent = (SaveJuvenileTraitsEvent)
                			EventFactory.getInstance( JuvenileCaseControllerServiceNames.SAVEJUVENILETRAITS ) ;
                		
                		saveEvent.setJuvenileNum(juvenileNum);
                		saveEvent.setDispositionNum(dispositionNum);
                		saveEvent.setSupervisionNum(supervisionNum);
                		
                		JuvenileTraitResponseEvent event = new JuvenileTraitResponseEvent();
                		event.setJuvenileNum(juvenileNum);
                		event.setSupervisionNum(supervisionNum);
                		event.setDispositionNum(dispositionNum);
                		event.setTraitTypeId(traitTypeId);
                		event.setStatusId(traitStatus);
                		event.setTraitComments(comment);
                		event.setFacilityAdmitOID(null);
                		event.setFacilityAdmitOID(null);
                		event.setInformationSrcCd(informationSrcCd);
                		
                		JuvenileTrait juvTrait = new JuvenileTrait();
                		juvTrait.hydrate(event);
                		juvTrait.hydrate(saveEvent);
                		
                		IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
                		
                		dispatch.postEvent( saveEvent ) ;
		    }
		    
		}
	    
	    }
	    
	    public static void insertRealDobTraitOnCaseFileActivation(String juvenileNum)
	    {
		if(juvenileNum != null && !"".equals(juvenileNum))
		{
		    String supervisionNum = getActivatedCasefileNumberWithSeqNum1(juvenileNum);
			String realDob = getRealDobInMSJuvenile(juvenileNum);
			//boolean isRealDobTraitType = realDobTraitTypeExists(juvenileNum, supervisionNum);
			
			if(supervisionNum != null && !"".equals(supervisionNum))
			{
			    if(realDob != null && !"".equals(realDob))
			    {
				SaveRealDobTraitInfo(juvenileNum, realDob, supervisionNum);
			    }
			}
		}
		
	    }
	    
}

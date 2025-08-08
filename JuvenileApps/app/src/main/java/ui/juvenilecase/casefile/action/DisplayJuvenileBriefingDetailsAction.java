//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\casefile\\action\\Action.java

package ui.juvenilecase.casefile.action;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerserviceprovider.reply.ProviderProgramResponseEvent;
import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileOffenseCodeEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.contact.to.SocialSecurityBean;
import messaging.detentionCourtHearings.GetJJSCLDetentionByHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumAndHearingDateEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberDetailsEvent;
import messaging.juvenile.GetJuvenileContactsEvent;
import messaging.juvenile.GetJuvenilePactSubjectDetailsEvent;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import messaging.juvenile.SaveJuvJPOOfRecEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileContactResponseEvent;
import messaging.juvenile.reply.JuvenileDetentionVisitResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenilePactDetailResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenile.reply.TattoosScarsPhysicalAttributesResponseEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberDetailResponseEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;
import messaging.juvenilecase.reply.JuvReferralFamilyResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.GetJuvenileWarrantsListEvent;
import messaging.juvenilewarrant.reply.JuvenileWarrantResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.referral.GetBehavioralHistoryEvent;
import messaging.referral.GetJJSReferralEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.reply.JuvenileBehaviorHistoryResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import pd.attorney.Attorney;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JJSJuvenile;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.SupervisionTypeTJJDMap;
import pd.juvenilecase.casefile.CasefileClosingInfo;
import pd.juvenilecase.family.FamilyMemberEmail;
import pd.juvenilecase.family.JuvenileFamilyHelper;
import pd.juvenilecase.referral.JJSReferral;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.PhoneNumber;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.SecurityToken;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.TEAStudentDataReportBean;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileMemberForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;

public class DisplayJuvenileBriefingDetailsAction extends LookupDispatchAction
{
    /**
     * @roseuid 47F52DA40239
     */
    public DisplayJuvenileBriefingDetailsAction()
    {
    }

    /**
     * displayJuvenileBriefingDetails
     * 
     * @param aMapping
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 47F5286E03BF
     */
    public ActionForward displayJuvenileBriefingDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	String juvenileNum = aRequest.getParameter("juvenileNum");
	String leftNavJuvNum = (String) aRequest.getAttribute("leftNavJuvNum");
	aRequest.setAttribute("leftNavJuvNum", null);
	DocketEventResponseEvent dktResp= null;
	String nextCourtDate="";
	String courtID="";

	if (notNullNotEmptyString(leftNavJuvNum))
	{
	    juvenileNum = leftNavJuvNum;
	}
	// no use executing code if juvenile number is empty
	if (notNullNotEmptyString(juvenileNum))
	{
	    String fromProfile = aRequest.getParameter("fromProfile");

	    JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	    form.clearAll();

	    form.setFromProfile((fromProfile == null) ? "" : fromProfile);
	    form.setJuvenileNum(juvenileNum);

	    setProfileDetail(juvenileNum, form);
	    dktResp=getNextCourtDetails(juvenileNum);
	    if(dktResp!=null)
	    {
        	    nextCourtDate = dktResp.getCourtDate();
        		if (nextCourtDate != null && nextCourtDate.length() > 0)
        		{
        		    if (DateUtil.compare(DateUtil.stringToDate(nextCourtDate, DateUtil.DATE_FMT_1), DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1) < 0)
        		    {
        			nextCourtDate = "";
        		    }
        		}
        	    courtID=dktResp.getCourt();
        	    form.setNextCourtDate(nextCourtDate);
        	    form.setCourtID(courtID);
	    }
	    JJSLastAttorney lastAttorney = JuvenileDistrictCourtHelper.getLastAttorneyInfo(juvenileNum); 

	    if(lastAttorney!=null )
	    {
		if(lastAttorney.getAttConnect() != null && !"".equals(lastAttorney.getAttConnect()))
		{
		    form.setLastAttorney(lastAttorney.getAtyName() + " (" + lastAttorney.getAttConnect() + ")");
		}
		else
		{
	           form.setLastAttorney(lastAttorney.getAtyName());
		}
		//add gal name
		if(lastAttorney.getGalName() != null&&!lastAttorney.getGalName().isEmpty())
		    form.setGalName(lastAttorney.getGalName());
	    }
	    session.setAttribute("juvenileBriefingDetailsForm", form);
	    //pact value # task 44099 . U.S #41378
	    String pactServerName = AppProperties.getInstance().getProperty("PactServerName");
	    form.setPactServerName(pactServerName);

	    //bug #46110
	    String pactApplicationName = AppProperties.getInstance().getProperty("PactApplicationName");
	    form.setPactApplicationName(pactApplicationName);

	    // get pact subject details.#43956
	    GetJuvenilePactSubjectDetailsEvent requestEvent = (GetJuvenilePactSubjectDetailsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPACTSUBJECTDETAILS);
	    requestEvent.setJuvenileId(juvenileNum);
	    List<JuvenilePactDetailResponseEvent> pactRespList = MessageUtil.postRequestListFilter(requestEvent, JuvenilePactDetailResponseEvent.class);
	    
	    if (pactRespList != null)
	    {
		Collections.sort((List<JuvenilePactDetailResponseEvent>) pactRespList, Collections.reverseOrder()); // get the most recent risk values
		Iterator<JuvenilePactDetailResponseEvent> juvenilePactSubjectRespItr = pactRespList.iterator();
		while (juvenilePactSubjectRespItr.hasNext())
		{
		    JuvenilePactDetailResponseEvent respEvent = juvenilePactSubjectRespItr.next();
		    if (respEvent != null)
		    {
			// set the value in the form for hovering.
			if (respEvent.getRiskLevel() != null)
			    form.setRiskLvl(respEvent.getRiskLevel());
			if (respEvent.getRiskLevelOverride() != null)
			    form.setNeedsLvl(respEvent.getRiskLevelOverride());
			if (respEvent.getPactLastDate() != null)
			    form.setLastPactDate(DateUtil.dateToString(respEvent.getPactLastDate(), DateUtil.DATE_FMT_1));

			break; // get the most recent risk values
		    }
		}
	    }

	    /*
	     * function to grab the Juv's Casefiles - subsequent code will or
	     * will not be executed based on whether one or more Casefile(s)
	     * exist
	     */
	    boolean hasCasefiles = setCasefiles(juvenileNum, form, aRequest);

	    JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
	    myPhotoForm.setJuvenileNumber(juvenileNum);

	    String casefileId = aRequest.getParameter("supervisionNum");
	    if (stringNullOrNotEqual(fromProfile, "profileBriefingDetails") && notNullNotEmptyString(casefileId))
	    {
		setCurrentCasefile(casefileId, form);
	    }

	    // physical characteristics section - data may exist, even if no
	    // Casefile
	    setPhysicalAttributes(juvenileNum, form);

	    // school history section - data may exist, even if no Casefile
	    setSchoolHistory(juvenileNum, form);

	    // warrant section - Juv could have Warrants info, even if no
	    // Casefile(s)
	    // exist
	    setWarrants(juvenileNum, form);

	    // execute the following data retrieval if Juv has one or more
	    // Casefiles
	    if (hasCasefiles)
	    {
		// family history section
		setFamilyHistoryInfo(juvenileNum, form);

		//set Detention visits from Contacts
		setDetentionVisitsFromContacts(juvenileNum, form);

		// behavior history section
		setBehaviorHistory(juvenileNum, form);

		// traits section
		setTraits(juvenileNum, form);

		/*
		 * TraitsForm tform = new TraitsForm();
		 * tform.setAction(UIConstants.VIEW);
		 * tform.setJuvenileNumber(juvenileNum);
		 * tform.setTraitTypeId("all"); tform.initializeTraitForm(true);
		 */
		setYouthPhoneNoInfo(juvenileNum, form);
		
	    }

	    setTrackerProgram(juvenileNum, form);

	    setSpecialtyCourt(juvenileNum, form);
	    //pact attributes task 41028.
	    String userId = SecurityUIHelper.getJIMSLogonId();
	    form.setUserId(userId);
	    SecurityToken token = new SecurityToken.Builder(userId).SubjectId(form.getJuvenileNum()).ExpirationSeconds(1740).build();
	    try
	    {
		form.setPactAuthKey(token.serialize().toString());
	    }
	    catch (KeyLengthException e)
	    {
		e.printStackTrace();
	    }
	    catch (JOSEException e)
	    {
		e.printStackTrace();
	    }
	    session = aRequest.getSession();
	    session.setAttribute("JuvenileBriefingDetailsForm", form);
	    
	    //get attorney email by using bar number 
	    String barNumber = null;
	    String attorneyEmail = null;
	    String galEmail = null;
	    form.setAttorneyBarNumber(barNumber);
	    form.setAttorneyEmail(attorneyEmail);
	    form.setGalEmail(galEmail);
	    
	    if(lastAttorney != null && lastAttorney.getAtyBarNum() != null && !"".equals(lastAttorney.getAtyBarNum())){
		form.setAttorneyBarNumber(lastAttorney.getAtyBarNum());
		barNumber = lastAttorney.getAtyBarNum();
		
		attorneyEmail = UIJuvenileHelper.getAttorneyEmail(barNumber);
		if(attorneyEmail != null && !"".equals(attorneyEmail))
		{
		    form.setAttorneyEmail(attorneyEmail); 
		}
		
	    }
	    if(lastAttorney != null && lastAttorney.getGalBarNum() != null && !"".equals(lastAttorney.getGalBarNum())){
		//form.setAttorneyBarNumber(lastAttorney.getAtyBarNum());
		barNumber = lastAttorney.getGalBarNum();
		
		galEmail = UIJuvenileHelper.getAttorneyEmail(barNumber);
		if(galEmail != null && !"".equals(galEmail))
		{
		    form.setGalEmail(galEmail);
		}
		
	    }
	   
		
	}// if stringNotEmpty juvenileNum
	
	return (aMapping.findForward(UIConstants.SUCCESS));
    }
    
    /**
     * displayJuvenileReferralBriefingDetails
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward displayJuvenileReferralBriefingDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	//JuvenileProfileDetailResponseEvent profileDetail = (JuvenileProfileDetailResponseEvent) session.getAttribute("JuvenileDetailForm");
	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	//SocialSecurity security = new SocialSecurity(profileDetail.getCompleteSSN());//needed only in update juvenile
	form.clearAll();
	String juvenileNum = aRequest.getParameter("juvenileNum");//isPopup
	String isPopUp = aRequest.getParameter("isPopup");

	// no use executing code if juvenile number is empty
	if (notNullNotEmptyString(juvenileNum))
	{
	    form.setJuvenileNum(juvenileNum);
	    setProfileDetail(juvenileNum, form);//uncommented: yes, need for another call here, we can't retrieve the profile info from the session. if via the button from create juvenile summary page not all info in session

	    //  function to grab the Juv's Casefiles 
	    boolean hasCasefiles = setCasefiles(juvenileNum, form, aRequest);
	    form.setHasCasefiles(hasCasefiles);
	    // school history section - data may exist, even if no Casefile
	    setSchoolHistory(juvenileNum, form);
	    //US 88171 - hide some buttons if no active Referrals
	    form.setHasActiveReferrals(JuvenileReferralHelper.hasActiveReferrals(juvenileNum));
	    form.setHasAnyReferrals(JuvenileReferralHelper.hasAnyReferrals(juvenileNum));
	    String addressId = form.getProfileDetail().getAddressId();
	    //get the juvenile address from JJS_MS_JUVENILE
	    if (addressId != null && !addressId.equalsIgnoreCase("0"))
	    {//True for Juvenile created in JIMS2 with a JUV Address Info + updated Juveniles 
		//get the address ID from JJS_MS_Juvenile and fetch the address from GLAddress (case of juveniles created on SQL n has NO casefile yet)
		Address juvAddress = UIJuvenileHelper.getAddressFromAddressId(form.getProfileDetail().getAddressId().toString());
		juvAddress.setAddressId(form.getProfileDetail().getAddressId());
		form.setMemberAddress(juvAddress);
	    }
	    else
		if ((form.getProfileDetail().getFromM204Flag() != null && form.getProfileDetail().getFromM204Flag().equalsIgnoreCase("Y")) || (addressId != null && addressId.equalsIgnoreCase("0")))
		{
		    //Migrated record
		    //get the address from JJS_MS_Juvenile (case of Migrated records without a casefile)
		    form.setJuvAddress(form.getProfileDetail().getJuvAddress());
		    form.setJuvCounty(form.getProfileDetail().getJuvCounty());
		}
		else
		{
		    //JIMS2 record created with no address/residential info
		    //no address available - No action
		}
	    if (hasCasefiles)
	    {
		setCurrentCasefile(JuvenileFacilityHelper.getMostRecentActiveCasefile(form.getReferrals(), form.getCasefiles(), form.getCurrentReferral()), form);
		// family history section
		//this one sets the juvenile address based on the primary guardian address
		setFamilyHistoryInfo(juvenileNum, form);
		//for incarcerated and deceased family mem, DO NOT display the Member address
		Collection guardianz = form.getGuardians();
		if (guardianz != null && guardianz.size() > 0)
		{
		    Iterator itrGuardian = guardianz.iterator();
		    while (itrGuardian.hasNext())
		    {
			GuardianBean gBean = (GuardianBean) itrGuardian.next();
			if (gBean.isDeceased() || gBean.isIncarcerated())
			{
			    gBean.setGuardianAddress(new Address());
			    gBean.setGuardianPhone(new MemberContact());
			}
		    }

		}
	    }
	    else
	    {
		//only set the guardian info
		setFamHistoryForJuvWithoutSuprvsnAssign(juvenileNum, form);
		//for incarcerated and deceased family mem, DO NOT display the Member address
		Collection guardianz = form.getGuardians();
		if (guardianz != null && guardianz.size() > 0)
		{
		    Iterator itrGuardian = guardianz.iterator();
		    while (itrGuardian.hasNext())
		    {
			GuardianBean gBean = (GuardianBean) itrGuardian.next();
			if (gBean.isDeceased() || gBean.isIncarcerated())
			{
			    gBean.setGuardianAddress(new Address());
			    gBean.setGuardianPhone(new MemberContact());
			}
		    }

		}

		//check for Guardian Address in Juv_Referral_Family table (for legacy records)
		//get the family info from the table JUV_REFERRAL_FAMILY (replication of legacy JJS_FAMILY)Task# 73257
		//if (form.getProfileDetail().getLcuser()!=null){ //True for Juvenile created in M204
		//if (addressId.equalsIgnoreCase("0")&& form.getProfileDetail().getLcuser()!=null){ //Address ID would become != 0 if the legacy kid's address has been updated via Update Referral
		//if family history info is not there (migrated data without case files)
		//get the family info from the new table JUV_REFERRAL_FAMILY (replication of JJS_FAMILY)Task# 73257
		if (form.getProfileDetail().getFromM204Flag() != null && form.getProfileDetail().getFromM204Flag().equalsIgnoreCase("Y"))
		{
		    if (form.getGuardians().isEmpty())
		    { //No casefile condition and Juvenile Created in M204
			Collection<JuvReferralFamilyResponseEvent> juvRefFamily = UIJuvenileHelper.getFamilyInfoForMigratedRecordsWithoutCasefile(form.getJuvenileNum());
			if (juvRefFamily != null)
			{
			    ArrayList guardians = new ArrayList();
			    GuardianBean pbean = new GuardianBean();
			    MemberContact contact = new MemberContact();
			    Address newGuardianAddress = new Address();
			    JuvReferralFamilyResponseEvent juvRefFamilyDetails = juvRefFamily.iterator().next();
			    form.setFathersName(juvRefFamilyDetails.getFathersName());
			    form.setFathersAddress(juvRefFamilyDetails.getFathersAddress());
			    form.setFathersPhone(juvRefFamilyDetails.getFathersPhone());
			    form.setMothersName(juvRefFamilyDetails.getMothersName());
			    form.setMothersAddress(juvRefFamilyDetails.getMothersAddress());
			    form.setMothersPhone(juvRefFamilyDetails.getMothersPhone());
			    form.setOtherRelName(juvRefFamilyDetails.getOtherName());
			    form.setOtherRelAddress(juvRefFamilyDetails.getOtherAddress());
			    form.setOtherRelPhone(juvRefFamilyDetails.getOtherPhone());

			    //Father
			    if (juvRefFamilyDetails.getFathersName() != null && !juvRefFamilyDetails.getFathersName().isEmpty())
			    {
				pbean.setRelationshipTypeId("BF");
				contact.setContactPhoneNumber(new PhoneNumber(form.getFathersPhone()));
				pbean.setGuardianPhone(contact);
				pbean.setNameOfPerson(Name.getNameFromString(form.getFathersName()));
				newGuardianAddress.setStreetName(form.getFathersAddress());
				pbean.setGuardianAddress(newGuardianAddress);
				pbean.setSSN(form.getFathersSSN());
				pbean.setMemberNum("1");
				guardians.add(pbean);
			    }

			    //Mother
			    if (juvRefFamilyDetails.getMothersName() != null && !juvRefFamilyDetails.getMothersName().isEmpty())
			    {
				pbean = new GuardianBean();
				contact = new MemberContact();
				newGuardianAddress = new Address();
				pbean.setRelationshipTypeId("BM");
				contact.setContactPhoneNumber(new PhoneNumber(form.getMothersPhone()));
				pbean.setGuardianPhone(contact);
				pbean.setNameOfPerson(Name.getNameFromString(form.getMothersName()));
				newGuardianAddress.setStreetName(form.getMothersAddress());
				pbean.setGuardianAddress(newGuardianAddress);
				pbean.setSSN(form.getMothersSSN());
				pbean.setMemberNum("2");
				guardians.add(pbean);
			    }
			    //Other.
			    if (juvRefFamilyDetails.getOtherName() != null && !juvRefFamilyDetails.getOtherName().isEmpty())
			    {
				pbean = new GuardianBean();
				contact = new MemberContact();
				newGuardianAddress = new Address();
				pbean.setRelationshipTypeId("OR");
				contact.setContactPhoneNumber(new PhoneNumber(form.getOtherRelPhone()));
				pbean.setGuardianPhone(contact);
				pbean.setNameOfPerson(Name.getNameFromString(form.getOtherRelName()));
				newGuardianAddress.setStreetName(form.getOtherRelAddress());
				pbean.setGuardianAddress(newGuardianAddress);
				pbean.setSSN(form.getMothersSSN());
				pbean.setMemberNum("3");
				guardians.add(pbean);
			    }
			    form.setGuardians(guardians);
			    form.setFamilyMembers(guardians);
			}
		    }
		    if (form.getProfileDetail().getSsnRel1() != null)
		    {
			if (form.getProfileDetail().getSsnRel1().equalsIgnoreCase("BF"))
			{
			    form.setFathersSSN(form.getProfileDetail().getSsn1());
			}
			else
			    if (form.getProfileDetail().getSsnRel1().equalsIgnoreCase("BM") || form.getProfileDetail().getSsnRel1().equalsIgnoreCase("MO"))
			    {
				form.setMothersSSN(form.getProfileDetail().getSsn1());
			    }
			    else
			    {
				form.setOtherRelSSN(form.getProfileDetail().getSsn1());
			    }
		    }
		    if (form.getProfileDetail().getSsnRel2() != null)
		    {
			if (form.getProfileDetail().getSsnRel2().equalsIgnoreCase("BF"))
			{
			    form.setFathersSSN(form.getProfileDetail().getSsn2());
			}
			else
			    if (form.getProfileDetail().getSsnRel2().equalsIgnoreCase("BM") || form.getProfileDetail().getSsnRel1().equalsIgnoreCase("MO"))
			    {
				form.setMothersSSN(form.getProfileDetail().getSsn2());
			    }
			    else
			    {
				form.setOtherRelSSN(form.getProfileDetail().getSsn2());
			    }
		    }
		}
	    }
	    // traits section
	    setTraits(juvenileNum, form);
	    setSpecialtyCourt(juvenileNum, form);

	    JJSJuvenile myJJSInfo = JJSJuvenile.find(juvenileNum);
	    if (myJJSInfo != null && !myJJSInfo.equals(""))
	    {
		form.setPurgeDate(DateUtil.dateToString(myJJSInfo.getPurgeDate(), DateUtil.DATE_FMT_1));
		form.setPurgeBoxNum(myJJSInfo.getPurgeBoxNum());
		form.setPurgeSerNum(myJJSInfo.getPurgeSerNum());
		form.setCheckedOutTo(myJJSInfo.getCheckedOutTo());			
	    }

	}
	if (isPopUp != null)
	{
	    if (isPopUp.equalsIgnoreCase("YesPopUp"))
	    {
		return (aMapping.findForward("referralPopUP"));
	    }
	}
	

	session = aRequest.getSession();
	session.setAttribute("REFERRAL_BRIEFINGDETAILS_FORM", form);
	return (aMapping.findForward(UIConstants.REFERRAL_SUCCESS));

    }

    /**
     * displayJuvenileFacilityBriefingDetails
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward displayJuvenileFacilityBriefingDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	HttpSession session = aRequest.getSession();

	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	form.clearAll();
	form.setHasOpenReferrals(true);
	String juvenileNum = aRequest.getParameter("juvenileNum");
	String leftNavJuvNum = (String) aRequest.getAttribute("leftNavJuvNum");
	aRequest.setAttribute("leftNavJuvNum", null);
	form.setDetVisitBanned(false);
	//added for US 101939
	form.setTempReleaseRequestDecision("i");
	form.setTempDistReleaseRequestDecision("i");
	//

	if (notNullNotEmptyString(leftNavJuvNum))
	{
	    juvenileNum = leftNavJuvNum;
	}

	// no use executing code if juvenile number is empty
	if (notNullNotEmptyString(juvenileNum))
	{
	    String fromProfile = "";
	    form.setFromProfile(fromProfile);
	    form.setJuvenileNum(juvenileNum);

	    setProfileDetail(juvenileNum, form);

	    // traits section
	    setTraits(juvenileNum, form);

	    /*
	     * function to grab the Juv's Casefiles - subsequent code will or
	     * will not be executed based on whether one or more Casefile(s)
	     * exist
	     */
	    boolean hasCasefiles = setCasefiles(juvenileNum, form, aRequest);
	    form.setHasCasefiles(hasCasefiles);

	    JuvenilePhotoForm myPhotoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
	    myPhotoForm.setJuvenileNumber(juvenileNum);

	    String casefileId = aRequest.getParameter("supervisionNum");
	    fromProfile = aRequest.getParameter("fromProfile");
	    form.setFromProfile((fromProfile == null) ? "" : fromProfile);
	    if (stringNullOrNotEqual(fromProfile, "profileBriefingDetails") && notNullNotEmptyString(casefileId))
	    {
		setCurrentCasefile(casefileId, form);
	    }
	    else
	    {
		setCurrentCasefile(JuvenileFacilityHelper.getMostRecentActiveCasefile(form.getReferrals(), form.getCasefiles(), form.getCurrentReferral()), form);
	    }

	    // physical characteristics section - data may exist, even if no
	    // Casefile
	    setPhysicalAttributes(juvenileNum, form);

	    // school history section - data may exist, even if no Casefile
	    setSchoolHistory(juvenileNum, form);

	    // warrant section - Juv could have Warrants info, even if no
	    // Casefile(s)
	    // exist
	    setWarrants(juvenileNum, form);

	    // execute the following data retrieval if Juv has one or more
	    // Casefiles
	    if (hasCasefiles)
	    {
		// family history section
		setFamilyHistoryInfo(juvenileNum, form);

		//set Detention visits from Contacts
		setDetentionVisitsFromContacts(juvenileNum, form);
		setFacilityBriefingFlags(form);

		JuvenileFacilityHeaderResponseEvent jjs_headerInfo = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
		if (jjs_headerInfo != null)
		{
		    form.setHeaderInfo(jjs_headerInfo);
		}

		Collection<JuvenileProfileReferralListResponseEvent> juvProfResEvt = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNum);
		if (jjs_headerInfo != null && juvProfResEvt != null)
		{
		    form.setJuvProfRefListDetails(juvProfResEvt);
		}

		JuvenileDetentionFacilitiesResponseEvent admissionInfo = JuvenileFacilityHelper.getInFacilityDetails(juvenileNum);

		Iterator<JuvenileProfileReferralListResponseEvent> juvProfResItr = juvProfResEvt.iterator();
		while (juvProfResItr.hasNext())
		{
		    JuvenileProfileReferralListResponseEvent profileRespEvent = juvProfResItr.next();
		    if (profileRespEvent != null && profileRespEvent.getReferralNumber().equalsIgnoreCase(admissionInfo.getReferralNumber()))
		    {
			admissionInfo.setReferralOfficer(profileRespEvent.getReferralOfficer());
			admissionInfo.setReferralSource(profileRespEvent.getReferralSource());
			admissionInfo.setReferralSourceDesc(profileRespEvent.getReferralSourceDesc());
			admissionInfo.setPetitionNum(profileRespEvent.getPetitionNumber());
			break;
		    }
		}

		//set admit reason description
		if (admissionInfo.getAdmitReason() != null && !admissionInfo.getAdmitReason().isEmpty())
		    admissionInfo.setAdmitReasonDesc(JuvenileFacilityHelper.getAdmitReasonByCode(admissionInfo.getAdmitReason()).getDescription());

		form.setAdmissionInfo(admissionInfo);
		session.setAttribute("FACILITY_DETENTION_RESP", admissionInfo); // add detention response event to the session.

		//get Facility Detention traits Added for U.S. #42660
		Collection<JuvenileTraitResponseEvent> detTraits = JuvenileFacilityHelper.getDetentionTraits(juvenileNum, form, admissionInfo);
		form.setTraits(detTraits);

		form.setReleaseTo(admissionInfo.getReleaseTo());

		GetJuvenileCasefileOffensesEvent jcoEvent = new GetJuvenileCasefileOffensesEvent();
		jcoEvent.setJuvenileNum(juvenileNum);
		List<JJSOffenseResponseEvent> offenses = MessageUtil.postRequestListFilter(jcoEvent, JJSOffenseResponseEvent.class);

		//U.S #32320
		boolean isTransferredOffense = false;
		List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseResp = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
		//sorts in descending order.
		Collections.sort((List<JJSOffenseResponseEvent>) offenses, Collections.reverseOrder(new Comparator<JJSOffenseResponseEvent>() {
		    @Override
		    public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
		    {
			return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
		    }
		}));

		Iterator<JJSOffenseResponseEvent> offensesItr = offenses.iterator();
		while (offensesItr.hasNext())
		{
		    JJSOffenseResponseEvent offenseResp = offensesItr.next();
		    if (offenseResp != null && JuvenileFacilityHelper.checkIfJJSFacilityHasMoreThanOneRecord(juvenileNum) && admissionInfo != null && admissionInfo.getReferralNumber() != null && !admissionInfo.getReferralNumber().isEmpty())
		    {
			if (offenseResp.getReferralNum().equals(admissionInfo.getReferralNumber()))
			{
			    //if a transferred offense
			    if (offenseResp.getSeveritySubtype() != null && offenseResp.getSeveritySubtype().equals("T"))
			    {
				//if there is a transferred offense added to the cart in SQL.
				if (transferredOffenseResp != null && !transferredOffenseResp.isEmpty())
				{
				    Iterator<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenseIter = transferredOffenseResp.iterator();
				    while (transferredOffenseIter.hasNext())
				    {
					JuvenileCasefileTransferredOffenseResponseEvent transferredOffense = transferredOffenseIter.next();
					if (transferredOffense.getReferralNum().equals(admissionInfo.getReferralNumber()))
					{
					    form.setBookingOffenseCode(transferredOffense.getOffenseCode());
					    form.setBookingOffenseLevel(transferredOffense.getCategory());
					    form.setBookingOffenseCodeDesc(transferredOffense.getOffenseShortDesc());
					    form.setCurrentOffenseCode(transferredOffense.getOffenseCode());
					    form.getAdmissionInfo().setCurrentOffense(transferredOffense.getOffenseCode());
					    form.getAdmissionInfo().setCurrentOffenseDesc(transferredOffense.getOffenseShortDesc());
					    isTransferredOffense = true;
					    break;
					}
				    }

				} //if there is not a transferred offense added to the cart in SQL.Retain the offense from m204 jjsoffense.

				if (!isTransferredOffense)
				{

				    form.setBookingOffenseCode(offenseResp.getOffenseCodeId());
				    form.setBookingOffenseLevel(offenseResp.getCatagory());
				    GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				    jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
				    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				    dispatch.postEvent(jocEvent);
				    CompositeResponse response = (CompositeResponse) dispatch.getReply();

				    JuvenileOffenseCodeResponseEvent juvOffenseCode = (JuvenileOffenseCodeResponseEvent) MessageUtil.filterComposite(response, JuvenileOffenseCodeResponseEvent.class);
				    if (juvOffenseCode != null)
				    {
					form.setBookingOffenseCodeDesc(juvOffenseCode.getLongDescription());
				    }
				    break;
				}
			    }
			    else
			    // not a transferred offense.
			    {
				GetJJSReferralEvent JJSEvent = new GetJJSReferralEvent();
				JJSEvent.setJuvenileNum(offenseResp.getJuvenileNum());
				JJSEvent.setReferralNum(offenseResp.getReferralNum());
				Iterator<JJSReferral> i = JJSReferral.findAll(JJSEvent);
				while (i.hasNext())
				{
				    JJSReferral ref = (JJSReferral) i.next();
				    form.setBookingOffenseCode(offenseResp.getOffenseCodeId());
				}

				//form.setBookingOffenseCode(offenseResp.getOffenseCodeId());
				form.setBookingOffenseLevel(offenseResp.getCatagory());

				GetJuvenileOffenseCodeEvent jocEvent = new GetJuvenileOffenseCodeEvent();
				jocEvent.setAlphaCode(offenseResp.getOffenseCodeId());
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(jocEvent);
				CompositeResponse response = (CompositeResponse) dispatch.getReply();

				JuvenileOffenseCodeResponseEvent juvOffenseCode = (JuvenileOffenseCodeResponseEvent) MessageUtil.filterComposite(response, JuvenileOffenseCodeResponseEvent.class);
				if (juvOffenseCode != null)
				{
				    form.setBookingOffenseCodeDesc(juvOffenseCode.getLongDescription());
				}
				break;
			    }
			} //end of if(2)			
		    }//end of if(1)
		} //end of while(1)
	    }

	    //List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();
	    GetJJSCLDetentionByHearingDateEvent jjsdetnCrtEvent = (GetJJSCLDetentionByHearingDateEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYHEARINGDATE);
	    //JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	    jjsdetnCrtEvent.setJuvNumber(juvenileNum);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    Date currDate = new Date();
	    String today = sdf.format(currDate);
	    jjsdetnCrtEvent.setCourtDate(today);
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch.postEvent(jjsdetnCrtEvent);
	    CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);
	    if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
	    {
		form.setIsCourtSettingToday("yes");
	    }
	    else
	    {
		form.setIsCourtSettingToday("no");
	    }
	    /*JJSHeader header = JuvenileFacilityHelper.getJJSHeader(form.getJuvenileNum());
	    if (header != null)
	    {
		if (header.getReleaseDecisionStatus() != null && header.getReleaseDecisionStatus().matches("A|R|P"))
		{
		    form.setTempRelDecisionEnabled(true);
		}
	    }*/
	    //do for court
	    IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	    List<DocketEventResponseEvent> distDockets = new ArrayList<DocketEventResponseEvent>();
	    GetJJSCLCourtByJuvNumAndHearingDateEvent jjsdistCrtEvent = (GetJJSCLCourtByJuvNumAndHearingDateEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYJUVNUMANDHEARINGDATE);
	    jjsdistCrtEvent.setJuvenileNumber(juvenileNum);	    
	    jjsdistCrtEvent.setCourtDate(today);
	    dispatch1.postEvent(jjsdistCrtEvent);
	    CompositeResponse clDistResp = (CompositeResponse) dispatch1.getReply();
	    List<DocketEventResponseEvent> clDistDktRespEvts = MessageUtil.compositeToList(clDistResp, DocketEventResponseEvent.class);
	    if (clDistDktRespEvts != null && !clDistDktRespEvts.isEmpty())
	    {
		form.setIsDistCourtSettingToday("yes");
	    }
	    else
	    {
		form.setIsDistCourtSettingToday("no");
	    }
	    JJSHeader header = JuvenileFacilityHelper.getJJSHeader(form.getJuvenileNum());
	    if (header != null)
	    {
		if (header.getReleaseDecisionStatus() != null && header.getReleaseDecisionStatus().matches("A|R|P"))
		{
		    form.setTempRelDecisionEnabled(true);
		}
		else
		    form.setTempRelDecisionEnabled(false);
		if (header.getDistReleaseDecisionStatus() != null && header.getDistReleaseDecisionStatus().matches("A|R|P"))
		{
		    form.setTempRelDecisionDistEnabled(true);
		}
		else
		    form.setTempRelDecisionDistEnabled(false);
	    }
	    //
	    //Referral transfer addition
	    if (form.getAdmissionInfo() != null && form.getHeaderInfo().getFacilityStatus() != null && !form.getHeaderInfo().getFacilityStatus().isEmpty())
	    {
		if (form.getHeaderInfo().getFacilityStatus().equals("E"))
		{
		    form.setRefTransferMesg("Juvenile has escaped from custody. Detention Record cannot be transferred to a different referral.");
		}
		else
		{
		    List<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.buildReferralTransferRecord(form);
		    form.setReferrals(referralList);
		}
	    }
	    setTrackerProgram(juvenileNum, form);
	    setSpecialtyCourt(juvenileNum, form);

	}// if stringNotEmpty juvenileNum
	form.setRestrictedAccessTrait(UIJuvenileHelper.checkRestrictedAcces(juvenileNum));
	session = aRequest.getSession();
	session.setAttribute("FACILITY_BRIEFINGDETAILS_FORM", form);

	return (aMapping.findForward(UIConstants.FACILITY_SUCCESS));
    }

    /**
     * DisplayJuvenileFacilityAdmissionInfoDetails
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward displayJuvenileFacilityAdmissionInfoDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	if (form.getAdmissionInfo() != null)
	{
	    form.getAdmissionInfo().setComments(JuvenileFacilityHelper.getFacilityAdmissionComments(form.getJuvenileNum(), form.getAdmissionInfo().getDetentionId())); //added for #51737
	    boolean saReasonOT = false;
	    if (form.getAdmissionInfo() != null && form.getAdmissionInfo().getSaReason() != null && !form.getAdmissionInfo().getSaReason().equals(""))
	    {
		//if the saReason is there, check if other is selected. If it is, then get saReasonComments
		String[] saReasonCodes = StringUtils.split(form.getAdmissionInfo().getSaReason(), ",");
		for (int i = 0; i < saReasonCodes.length; i++)
		{
		    if (saReasonCodes[i] != null && !saReasonCodes[i].isEmpty() && saReasonCodes[i].equalsIgnoreCase("OT"))
		    {
			saReasonOT = true;
			break;
		    }
		}

	    }

	    if (saReasonOT)
		form.getAdmissionInfo().setSaReasonOtherComments(JuvenileFacilityHelper.getMostRecentSplAttnReasonComments(form.getJuvenileNum(), form.getAdmissionInfo().getDetentionId()));
	    else
		form.getAdmissionInfo().setSaReasonOtherComments("");
	}

	//null check added for Bug #42596/#40650
	/*if(form.getAdmissionInfo()!=null && form.getAdmissionInfo().getSaReason()!=null && !form.getAdmissionInfo().getSaReason().contains("OT")){
		form.getAdmissionInfo().setSaReasonOtherComments("");
	}*/
	return (aMapping.findForward("viewDetails"));

    }

    /**
     * displayTraitDetails
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward displayTraitDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String traitID = aRequest.getParameter("traitID");

	// no use executing code if juvenile number is empty
	if (notNullNotEmptyString(traitID))
	{
	    try
	    {
		JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
		int offset = Integer.parseInt(traitID);
		System.out.println("Trait Id: " + offset);
		if ( form.getTraits() != null ) {
		    System.out.println("Trait size" +  form.getTraits().size());
		}
		JuvenileTraitResponseEvent trait = (JuvenileTraitResponseEvent) ((ArrayList) form.getTraits()).get(offset);

		if (trait != null)
		{
		    form.setCurrentTrait(trait);
		}
	    }
	    catch (NumberFormatException nfe)
	    { /* empty */
	    }
	}

	return (aMapping.findForward("showTraitDetails"));
    }
    
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward printDemographics(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	JuvenileBriefingDetailsForm form = (JuvenileBriefingDetailsForm) aForm;
	

	TEAStudentDataReportBean reportBean = new TEAStudentDataReportBean();
	
	reportBean.setJuvenileName(form.getProfileDetail().getFormattedName());
	reportBean.setJuvenileNumber(form.getJuvenileNum());
	reportBean.setDateOfBirth(DateUtil.dateToString( form.getProfileDetail().getDateOfBirth(),DateUtil.DATE_FMT_1));
	reportBean.setCurrentAge(form.getAge());
	reportBean.setHairColor(form.getProfileDetail().getNaturalHairColor());
	reportBean.setEyeColor(form.getProfileDetail().getNatualEyeColor());
	reportBean.setEthnicityDesc(form.getEthnicity());
	reportBean.setGender(form.getProfileDetail().getSex());
	reportBean.setRace(form.getProfileDetail().getRace());
	if("Y".equalsIgnoreCase( form.getProfileDetail().getHispanic() )) {	    
	    reportBean.setHispanicDesc("YES");	    
	}else {
	    reportBean.setHispanicDesc("NO");
	}
	if( form.getProfileDetail().isMultiracial()) {
	    
	    reportBean.setMultiracialDesc("YES");
	}else {
	    
	    reportBean.setMultiracialDesc("NO");
	}
	reportBean.setFamilyInformation(new ArrayList());
	reportBean.setSchoolHistory(new ArrayList());
	// Fixed null pointer rry
	if( form.getMemberAddress() != null ){
	    
	    reportBean.setCounty(form.getMemberAddress().getCounty());
	    reportBean.setFullAddress(form.getMemberAddress().getStreetAddress());
	    reportBean.setCityStateZip(form.getMemberAddress().getCityStateZip());
	}
	// Fixed null pointer rry
	if( form.getMemberContact() != null ){
		reportBean.setPhoneNum(form.getMemberContact().getContactPhoneNumber().getFormattedPhoneNumber());
		reportBean.setPhoneType(form.getMemberContact().getContactType());
	}	
	
	reportBean.setGuardians((List<GuardianBean>) form.getGuardians());
	reportBean.setCurrentSchool(form.getSchool());

	aRequest.getSession().setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "true");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.JUVENILE_DEMOGRAPHICS_REPORT);

	return null;
	
    }

    /**
     * cancel
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.CANCEL));
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.BACK);
    }

    /**
     * setProfileDetail gets the juv profile info
     * 
     * @param juvenileNum
     * @param form
     */
    private void setProfileDetail(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	reqProfileMain.setJuvenileNum(juvenileNum);
	reqProfileMain.setFromProfile(form.getFromProfile());
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfileRE = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	form.setJisInfo(new JuvenileJISResponseEvent());
	if (juvProfileRE != null)
	{
	    //US 186179 - get actual update username instead of update user logonId
	    String updateUser = null;
	    if(juvProfileRE.getJuvenileNum() != null && !"".equals(juvProfileRE.getJuvenileNum()))
	    {
		updateUser = UIJuvenileHelper.getOfficerNameFromLogonId(juvProfileRE.getJuvenileNum());
		juvProfileRE.setUpdateUser(updateUser);
	    }	    
	    
	    form.setProfileDetail(juvProfileRE);
	    form.setProfileDescriptions();

	    if (juvProfileRE.getDateOfBirth() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvProfileRE.getDateOfBirth());
		if (age > 0)
		{
		    form.setAge(String.valueOf(age));
		}
		else
		{
		    form.setAge(UIConstants.EMPTY_STRING);
		}
	    }
	    
	    if(juvProfileRE.getDeathDate() != null){
		form.setDeathDate(DateUtil.dateToString(juvProfileRE.getDeathDate(), DateUtil.DATE_FMT_1));
		form.setDeathAge(juvProfileRE.getDeathAge());
		form.setYouthDeathReason(juvProfileRE.getYouthDeathReason());
		form.setYouthDeathReasonDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.CAUSE_OF_DEATH, form.getYouthDeathReason()));
		form.setYouthDeathVerification(juvProfileRE.getYouthDeathVerification());
		form.setYouthDeathVerificationDesc(CodeHelper.getCodeDescription(PDCodeTableConstants.DEATH_VERIFICATION, form.getYouthDeathVerification()));
	    }

	    Collection jisResps = juvProfileRE.getJisInfo();
	    if (jisResps != null)
	    {
		Collections.sort((List) jisResps);
		Iterator jisIter = jisResps.iterator();
		if (jisIter.hasNext())
		{
		    JuvenileJISResponseEvent jis = (JuvenileJISResponseEvent) jisIter.next();
		    form.setJisInfo(jis);
		}
	    }

	    form.setInMentalHealthServices(juvProfileRE.isMentalHealthServices());
	}
    }

    /**
     * sets the current casefile for the juv
     * 
     * @param casefileId
     * @param form
     */
    private void setCurrentCasefile(String casefileId, JuvenileBriefingDetailsForm form)
    {
	form.setSupervisionNum(casefileId);
	GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);

	getCasefileEvent.setSupervisionNumber(casefileId);

	CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
	JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);

	// Populate casefile found
	if (casefile != null)
	{
	    form.setCurrentCasefile(casefile);
	}
    }

    /**
     * gets the juvenile school history
     * 
     * @param juvenileNum
     * @param form
     */
    private void setSchoolHistory(String juvenileNum, JuvenileBriefingDetailsForm form)
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
     * gets the Program Referral for the Juvenile
     * 
     * @param juvenileNum
     * @param form
     */
    private void setTrackerProgram(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	ProviderProgramResponseEvent program = UIServiceProviderHelper.getProgramByCode("JTP");
	form.setInTrackerProgram(false);
	if (program != null)
	{
	    String trackerProgId = program.getProviderProgramId();
	    List activeReferrals = UIProgramReferralHelper.getActiveJuvenileProgramReferrals(juvenileNum);
	    if (activeReferrals.size() > 0)
	    {
		Iterator iter = activeReferrals.iterator();
		while (iter.hasNext())
		{
		    ProgramReferralResponseEvent prre = (ProgramReferralResponseEvent) iter.next();
		    if (prre.getReferralStatusCd().equals("AC"))
		    {
			if (prre.getProvProgramId().equals(trackerProgId))
			{
			    form.setInTrackerProgram(true);
			    break;
			}
		    }
		}
	    }
	}
    }

    /**
     * @param juvenileNum
     * @param form
     */
    private void setSpecialtyCourt(String juvenileNum, JuvenileBriefingDetailsForm form)
    {

	//'GCP','DCV','GCT','C36'
	//2728,1159,1156,1161
	form.setSpecialtyCourtDescription("");
	form.setInSpecialtyCourt(false);
	List activeReferrals = UIProgramReferralHelper.getOnlyActiveJuvenileProgramReferrals(juvenileNum);
	for (int x = 0; x < activeReferrals.size(); x++)
	{

	    ProgramReferralResponseEvent prre = (ProgramReferralResponseEvent) activeReferrals.get(x);
	    if (prre.getReferralStatusCd().equals("AC"))
	    {
		if (prre.getProvProgramId().equals("2728"))
		{

		    form.setSpecialtyCourtDescription("COURT 360");
		    form.setInSpecialtyCourt(true);//SC   		    
		    break;

		}
		else
		    if (prre.getProvProgramId().equals("1159"))
		    {

			form.setSpecialtyCourtDescription("DRUG COURT SUPERVISION - SPECIALTY COURT");
			form.setInSpecialtyCourt(true);//SC   		    
			break;

		    }
		    else
			if (prre.getProvProgramId().equals("1156"))
			{

			    form.setSpecialtyCourtDescription("GANG COURT SUPERVISION (SPECIALTY COURT)");
			    form.setInSpecialtyCourt(true);//SC   		    
			    break;

			}
			else
			    if (prre.getProvProgramId().equals("1161"))
			    {

				form.setSpecialtyCourtDescription("CARE COURT SUPERVISION - SPECIALTY COURT");
				form.setInSpecialtyCourt(true);//SC   		    
				break;

			    }

	    }
	}
    }

    /**
     * gets the school behavior for the juv
     * 
     * @param juvenileNum
     * @param form
     */
    private void setBehaviorHistory(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetBehavioralHistoryEvent reqBehaviorEvent = (GetBehavioralHistoryEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETBEHAVIORALHISTORY);

	reqBehaviorEvent.setJuvenileNum(juvenileNum);
	CompositeResponse response = MessageUtil.postRequest(reqBehaviorEvent);

	JuvenileBehaviorHistoryResponseEvent respBehaviorHistory = (JuvenileBehaviorHistoryResponseEvent) MessageUtil.filterComposite(response, JuvenileBehaviorHistoryResponseEvent.class);
	if (respBehaviorHistory != null)
	{
	    form.setBehaviorHistory(respBehaviorHistory);
	    if (respBehaviorHistory.getReferralNums() != null && respBehaviorHistory.getReferralNums().size() > 0)
	    {
		form.setReferralNums(respBehaviorHistory.getReferralNums());
	    }
	}
    }

    /**
     * gets the warrants for the juv
     * 
     * @param juvenileNum
     * @param form
     */
    private void setWarrants(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileWarrantsListEvent event = (GetJuvenileWarrantsListEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJUVENILEWARRANTSLIST);

	event.setJuvenileNum(juvenileNum);
	event.setWarrantActivationStatusId(PDJuvenileWarrantConstants.WARRANT_ACTIVATION_ACTIVE);
	CompositeResponse response = MessageUtil.postRequest(event);
	List<JuvenileWarrantResponseEvent> warrants = MessageUtil.compositeToList(response, JuvenileWarrantResponseEvent.class);

	if (warrants.size() > 0)
	{
	    Collections.sort((List) warrants);
	    JuvenileWarrantResponseEvent warrantResp = warrants.get(0);
	    warrantResp.setWarrantType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WARRANT_TYPE, warrantResp.getWarrantTypeId()));
	    form.setWarrant(warrantResp);
	}
    }

    /**
     * gets all the casefiles for the juv and sets the collection
     * 
     * @param juvenileNum
     * @param form
     * @param aRequest
     * @return
     */
    private boolean setCasefiles(String juvenileNum, JuvenileBriefingDetailsForm form, HttpServletRequest aRequest)
    {
	SearchJuvenileCasefilesEvent searchForCasefiles = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);
	OfficerProfileResponseEvent officerProfile = null;
	searchForCasefiles.setSearchType("JNUM");
	searchForCasefiles.setJuvenileNum(juvenileNum);
	CompositeResponse response = MessageUtil.postRequest(searchForCasefiles);

	/*
	 * Handle error thrown as ErrorResponseEvent from the command (if any)
	 * Expected error: Number of juveniles matching criteria > 2000.
	 */
	boolean hasCasefiles = false;
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error == null)
	{
	    List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class);
	    if (casefiles.size() > 0)
	    {
		Collections.sort(casefiles);

		//added for Juvenile Facility
		checkCasefileStatus(casefiles, form);
		hasCasefiles = true;
	    }
	    form.setCasefiles(casefiles);
	    //US 40492
	    //04/04/2018 - taken out for Bug #64721
	    //	if(form.getProfileDetail().getJpoOfRecID()==null)
	    //{
	    CodeResponseEvent codeResp = UIJuvenileCaseworkHelper.setJPOOfRecord(casefiles);
	    SaveJuvJPOOfRecEvent saveJPO = (SaveJuvJPOOfRecEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVJPOOFREC);
	    saveJPO.setJuvenileNum(juvenileNum);
	    saveJPO.setJpoId(codeResp.getCode());
	    IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
	    dispatch1.postEvent(saveJPO);
	    form.getProfileDetail().setJpoOfRecID(codeResp.getCode());
	    form.getProfileDetail().setJpoOfRecord(codeResp.getDescription());
	    if ( codeResp != null ) {
		//officerProfile = UserProfileHelper.getUserProfileFromJUCode( codeResp.getCode() );
		officerProfile = PDOfficerProfileHelper.getOfficerProfileByLogonId( codeResp.getCode() );
	    }
	    
	    if ( officerProfile != null ){
		form.getProfileDetail().setJpoPhoneNumber(officerProfile.getWorkPhone());
		form.getProfileDetail().setLocUnitName(officerProfile.getJuvUnit());
	    }
	    
	    //add program info for residential facilities caseworker
	    form.setJuvProgramCode(null);
	    form.setJuvProgramDescription(null);
	    ProgramReferralResponseEvent juvProgram = UIProgramReferralHelper.getProgramInfoFacilitiesCaseworker(juvenileNum);
	    if(juvProgram != null){		
		form.setJuvProgramCode(juvProgram.getProvProgramCode());
		form.setJuvProgramDescription(juvProgram.getProviderProgramName());
	    }

	    //	}
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    saveErrors(aRequest, errors);
	}

	return (hasCasefiles);
    }

    /**
     * checkCasefilesStatus
     * 
     * @param casefiles
     */
    private void checkCasefileStatus(Collection<JuvenileCasefileSearchResponseEvent> casefiles, JuvenileBriefingDetailsForm form)
    {
	Iterator<JuvenileCasefileSearchResponseEvent> iter = casefiles.iterator();
	while (iter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) iter.next();

	    if (resp.getCaseStatus().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_ACTIVE_DESCRIPTION))
	    {
		form.setHasActiveCasefiles(true);
		form.setHasActiveOrClosingPendingCasefiles(true);
	    }
	    if (resp.getCaseStatus().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_PENDING_DESCRIPTION))
	    {
		form.setHasPendingCasefiles(true);
	    }
	    if (resp.getCaseStatus().equalsIgnoreCase("CLOSING PENDING"))
	    {
		form.setHasActiveOrClosingPendingCasefiles(true);
	    }
	    if (resp.getCaseStatus().equalsIgnoreCase(UIConstants.CASEFILE_CASE_STATUS_CLOSED_DESCRIPTION))
	    {
		if (!form.isHasActiveCasefiles() && !form.isHasPendingCasefiles())
		{
		    form.setHasPendingCasefiles(false);
		    form.setHasActiveCasefiles(false);
		}
	    }
	    //add code for closed date here- task 170950
	    Iterator<CasefileClosingInfo> casefileClosings = CasefileClosingInfo.findAll("supervisionNumber", resp.getSupervisionNum());
		while(casefileClosings.hasNext())
		{
			CasefileClosingInfo casefileClosingInfo = casefileClosings.next();
			resp.setClosedDate(casefileClosingInfo.getSupervisionEndDate());
		}
	    //bug fix for U.S #40769 #48692 #U.S 46428
	    if (resp.getCaseStatus().equalsIgnoreCase("ACTIVE"))
	    {
		String typeId = resp.getSupervisionTypeId();
		if (typeId != null)
		{
		    SupervisionTypeTJJDMap supervisionType = null;
		    Iterator<SupervisionTypeTJJDMap> stpItr = SupervisionTypeTJJDMap.findAll("supervisionTypeId", typeId);
		    while (stpItr.hasNext())
		    {
			supervisionType = stpItr.next();
			if (supervisionType != null)
			{
			    if (supervisionType.getSplCategoryId() != null && supervisionType.getSplCategoryId().equalsIgnoreCase("RE")) //residential
			    {
				form.setHasPostAdjCatCasefile(true);
				break;
			    }
			}
		    }
		}
	    }
	}
    }

    /**
     * gets the juv's physical attributes.
     * 
     * @param juvenileNum
     * @param form
     */
    private void setPhysicalAttributes(String juvenileNum, JuvenileBriefingDetailsForm form)

    {
	GetJuvenilePhysicalAttributesEvent requestEvent = new GetJuvenilePhysicalAttributesEvent();
	requestEvent.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(requestEvent);

	CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
	List<JuvenilePhysicalAttributesResponseEvent> physicalChars = MessageUtil.compositeToList(replyEvent, JuvenilePhysicalAttributesResponseEvent.class);

	if (physicalChars.size() > 0)
	{
	    // sort this list based on entryDate
	    Collections.sort((List) physicalChars);
	    JuvenilePhysicalAttributesResponseEvent phyAttRespEvent = physicalChars.get(0);

	    String description = "";
	    String otherTattooComments = "";
	    GetTattoosScarsPhysicalAttributesEvent request = new GetTattoosScarsPhysicalAttributesEvent();

	    request.setJuvenileNum(juvenileNum);
	    TattoosScarsPhysicalAttributesResponseEvent responseEvent = (TattoosScarsPhysicalAttributesResponseEvent) MessageUtil.postRequest(request, TattoosScarsPhysicalAttributesResponseEvent.class);

	    Collection tattoos = responseEvent.getTattoos();
	    if (tattoos != null)
	    {
		List tattooDescriptions = CodeHelper.getCodeDescriptions(tattoos);
		description = UIUtil.separateByDelim(tattooDescriptions, "; ");
	    }

	    Collection comments = responseEvent.getOtherTattooComments();
	    if (comments != null)
	    {
		Iterator i = comments.iterator();
		if (i.hasNext())
		{
		    otherTattooComments = (String) i.next();
		}
	    }

	    phyAttRespEvent.setTattoosDescription(description);
	    phyAttRespEvent.setOtherTattooCommentsAsString(otherTattooComments);

	    String desc;

	    String item = phyAttRespEvent.getBuild();
	    if (notNullNotEmptyString(item))
	    {
		desc = CodeHelper.getCodeDescription(PDCodeTableConstants.BUILD, item);
		phyAttRespEvent.setBuild(desc);
	    }

	    item = phyAttRespEvent.getHairColor();
	    if (notNullNotEmptyString(item))
	    {
		desc = CodeHelper.getCodeDescription(PDCodeTableConstants.HAIR_COLOR, item);
		phyAttRespEvent.setHairColor(desc);
	    }

	    item = phyAttRespEvent.getEyeColor();
	    if (notNullNotEmptyString(item))
	    {
		desc = CodeHelper.getCodeDescription(PDCodeTableConstants.EYE_COLOR, item);
		phyAttRespEvent.setEyeColor(desc);
	    }

	    form.setPhysicalAttribute(phyAttRespEvent);
	}
    }
    
    private void setYouthPhoneNoInfo(String juvenileNum, JuvenileBriefingDetailsForm form)
    {

	Collection guardians = UIJuvenileFamilyHelper.getGuardians(juvenileNum);
	if (guardians != null && guardians.size() > 0)
	{
	    Iterator guardiansIter = guardians.iterator();
	    Date entryDate = new Date(0L);
	    while (guardiansIter.hasNext())
	    {
		GuardianInfoResponseEvent myRespEvt = (GuardianInfoResponseEvent) guardiansIter.next();
		
		if (myRespEvt.getJuvMobilePhone() != null && !myRespEvt.getJuvMobilePhone().equals(""))
		{
		    if (myRespEvt.getEntryDate() != null && myRespEvt.getEntryDate().compareTo(entryDate)> 0)
		    {
			entryDate = myRespEvt.getEntryDate();
			form.setJuvPhoneNum(myRespEvt.getJuvMobilePhone());
		    }
		}
	    }
	}
    }
    
  
    /**
     * @param juvenileNum
     * @param form
     */
    private void setFamilyHistoryInfo(String juvenileNum, JuvenileBriefingDetailsForm form)
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
		List<GuardianBean> relatives = new ArrayList<GuardianBean>();
		List<JuvenileDetentionVisitResponseEvent> detVisits = new ArrayList<JuvenileDetentionVisitResponseEvent>();

		for (FamilyConstellationMemberListResponseEvent member : familyMembers)
		{
		    GetFamilyMemberDetailsEvent getMemberDetails = (GetFamilyMemberDetailsEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERDETAILS);

		    getMemberDetails.setMemberNum(member.getMemberNum());
		    replyEvent = MessageUtil.postRequest(getMemberDetails);
		    FamilyMemberDetailResponseEvent memberDetail = (FamilyMemberDetailResponseEvent) MessageUtil.filterComposite(replyEvent, FamilyMemberDetailResponseEvent.class);

		    String rel = member.getRelationToJuvenileId();
		    if (notNullNotEmptyString(rel))
		    {
			GuardianBean pbean = new GuardianBean();
			MemberContact memberPhone = null;
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
			pbean.setStateIssuedIdState(member.getStateIssuedIdState());
			//added below two for Referral User Story: 71650
			SocialSecurityBean memSsn = new SocialSecurityBean(member.getSSN());
			pbean.setSSN(memSsn.getFormattedSsn());
			pbean.setGuardianAddress(UIJuvenileHelper.getFamilyMemberAddress(member.getMemberNum()));
			pbean.setGuardianPhone(UIJuvenileHelper.getFamilyMemberPhone(member.getMemberNum()));
			pbean.setIncarcerated(member.isIncarcerated());
			pbean.setDeceased(member.isDeceased());
			// if guardian
			if (member.isGuardian())
			{
			    guardians.add(pbean);
			}

			relatives.add(pbean);

			// <vkoyyada> ER : JIMS200056844 && Task :
			// JIMS200056842
			// Residential address should be the address of first In-Home
			// guardian's recent address
			/**
			 * ER 68391 - activity 74116 negates this code because
			 * of Primary contact superceded In-Home. replacement
			 * code added below if (member.isInHomeStatus()) { if
			 * (familyAddress == null) { familyAddress =
			 * getFamilyMemberAddress(member.getMemberNum());
			 * form.setMemberAddress(familyAddress); } familyPhone =
			 * getFamilyMemberPhone(member.getMemberNum());
			 * form.setMemberContact(familyPhone); }
			 */

		    }

		    //added for US 43116 - check if the family member allowed detention visit
		    if (member.isDetentionVisitation())
		    {
			JuvenileDetentionVisitResponseEvent detVisit = new JuvenileDetentionVisitResponseEvent();
			detVisit.setJuvenileNum(juvenileNum);
			detVisit.setMemOrContactName(member.getFullName());
			String rela = member.getRelationToJuvenileId();
			if (notNullNotEmptyString(rela))
			    detVisit.setRelationship(member.getRelationToJuvenile());
			String dlID = member.getDriverLicenseNum();
			String stateID = member.getStateIssuedIdNum();
			String ppID = member.getPassportNum();
			String idAndType = "";
			if (dlID != null && !dlID.equals(""))
			    idAndType = dlID + "/DL Number";
			else
			    if (stateID != null && !stateID.equals(""))
				idAndType = stateID + "/State ID";
			    else
				if (ppID != null && !ppID.equals(""))
				    idAndType = ppID + "/Passport Number";

			detVisit.setIdType(idAndType);
			detVisits.add(detVisit);
		    }

		    form.setGuardians(guardians);
		    form.setFamilyMembers(relatives);
		} // for - familyMembers

		form.setDetVisits(detVisits);
		
		//get and set the guardian email
		if (guardians != null && guardians.size() > 0)
		{
		    for (int x = 0; x < guardians.size(); x++)
		    {
			GuardianBean gbean = guardians.get(x);
			if ("true".equalsIgnoreCase(gbean.getPrimaryContact()) && "true".equalsIgnoreCase(gbean.getInHomeStatus()))
			{
			    MemberContact memberContact = JuvenileFamilyHelper.getLatestFamilyMemberEmail(form.getMemberContact(), gbean.getMemberNum());
			    
			    if(memberContact != null)
			    {
				form.setMemberContact(memberContact);
				form.setParentEmail(memberContact.getEmailAddress());
				form.setPrimaryIndEmail(memberContact.getPrimaryIndEmail());
			    }

			}
		    }

		}
		    
		
		
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
				form.setMemberNum(gbean.getMemberNum());
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
					form.setMemberNum(gbean0.getMemberNum());
					memberNum = gbean0.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() == null)
				    {
					form.setMemberAddress(familyAddress1);
					form.setMemberNum(gbean1.getMemberNum());
					memberNum = gbean1.getMemberNum();
				    }
				    if (familyAddress.getCreateDate() != null && familyAddress1.getCreateDate() != null)
				    {
					if (DateUtil.compare(familyAddress.getCreateDate(), familyAddress1.getCreateDate(), DateUtil.DATE_FMT_1) > 0)
					{
					    form.setMemberAddress(familyAddress);
					    memberNum = gbean0.getMemberNum();
					    form.setMemberNum(gbean0.getMemberNum());
					}
					else
					{
					    form.setMemberAddress(familyAddress1);
					    memberNum = gbean1.getMemberNum();
					    form.setMemberNum(gbean1.getMemberNum());
					}
				    }
				}
				else
				{
				    form.setMemberAddress(familyAddress);
				    memberNum = gbean0.getMemberNum();
				    form.setMemberNum(gbean0.getMemberNum());
				}
			    }
			    else
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    form.setMemberAddress(familyAddress1);
				    memberNum = gbean1.getMemberNum();
				    form.setMemberNum(gbean1.getMemberNum());
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
			    memberNum = gbean.getMemberNum();
			    form.setMemberNum(gbean.getMemberNum());
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
     * setDetentionVisitsFromContacts
     * 
     * @param juvenileNum
     * @param form
     */
    private void setDetentionVisitsFromContacts(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileContactsEvent contactsEvent = (GetJuvenileContactsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILECONTACTS);
	contactsEvent.setJuvenileNum(juvenileNum);
	CompositeResponse response = UIJuvenileHelper.getCompositeResponse(contactsEvent);
	Collection contacts = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CONTACT_TOPIC);
	Iterator iter = contacts.iterator();
	Collection<JuvenileDetentionVisitResponseEvent> detVisits = form.getDetVisits();
	while (iter.hasNext())
	{
	    JuvenileContactResponseEvent resp = (JuvenileContactResponseEvent) iter.next();
	    if (resp.isDetentionVisit())
	    {
		JuvenileDetentionVisitResponseEvent detVisit = new JuvenileDetentionVisitResponseEvent();
		detVisit.setJuvenileNum(juvenileNum);
		detVisit.setMemOrContactName(resp.getFormattedName());
		String rel = resp.getRelationshipId();
		if (notNullNotEmptyString(rel))
		    detVisit.setRelationship(resp.getRelationship());
		String dlID = resp.getDriverLicenseNum();
		String stateID = resp.getStateIssuedIdNum();
		String ppID = resp.getPassportNum();
		String idAndType = "";
		if (dlID != null && !dlID.equals(""))
		    idAndType = dlID + "/DL Number";
		else
		    if (stateID != null && !stateID.equals(""))
			idAndType = stateID + "/State ID";
		    else
			if (ppID != null && !ppID.equals(""))
			    idAndType = ppID + "/Passport Number";

		detVisit.setIdType(idAndType);
		detVisits.add(detVisit);
	    }
	}
	form.setDetVisits(detVisits);
    }

    /**
     * returns true if string isn't null and contains one or more chars
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && str.trim().length() > 0);
    }

    /**
     * stringNullOrNotEqual
     * 
     * @param theString
     * @param comparedTo
     * @return boolean: string is not null and is not equal to the compared to
     *         string
     */
    private boolean stringNullOrNotEqual(String theString, String comparedTo)
    {
	return (theString == null || !theString.trim().equalsIgnoreCase(comparedTo));
    }

    /**
     * setTraits
     * 
     * @param juvenileNum
     * @param form
     */
    private void setTraits(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	if (juvenileTraits == null)
	{
	    juvenileTraits = new ArrayList<JuvenileTraitResponseEvent>();
	}
	else
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvenileTraits)
	    {
		//System.out.println("Trait Type Id: " +  juvenileTrait.getTraitTypeId());
		//System.out.println("Trait Type: " + juvenileTrait.getTraitTypeName());
		//System.out.println("Trail Description: " + juvenileTrait.getTraitTypeDescription());
		/*		if ( juvenileTrait.getTraitTypeId().equals("DST")
					&& juvenileTrait.getTraitTypeName().equals("ADMINISTRATIVE")
					&& juvenileTrait.getTraitTypeDescription().equals("DUAL STATUS UNIT ONLY-YOUTH IDENTIFIED")
					){
				    form.setDualStatus("DS");
				}else{*/
		if (juvenileTrait.getTraitTypeId().equals("MHO") && juvenileTrait.getTraitTypeName().equals("FACILITY TRAITS") && juvenileTrait.getTraitTypeDescription().equals("MEDICAL HOLD - DO NOT RELEASE") && juvenileTrait.getStatus().equals("CURRENT"))
		{
		    form.setMedicalHold("MHO");
		}

		if (juvenileTrait.getTraitTypeId().equals("JHJ") && juvenileTrait.getStatus().equals("CURRENT"))
		{
		    form.setHoldTrait("HOLD");
		    form.setHoldTraitDescription(juvenileTrait.getTraitTypeDescription());
		}

		if (juvenileTrait.getTraitTypeId().equals("JHD") && juvenileTrait.getStatus().equals("CURRENT"))
		{
		    form.setHoldTrait("HOLD");
		    form.setHoldTraitDescription(juvenileTrait.getTraitTypeDescription());
		}
		
		//set merge trait - US 156449
		if (juvenileTrait.getTraitTypeId().equals("MER"))
		{
		    form.setTraitTypeId(juvenileTrait.getTraitTypeId());
		    form.setTraitTypeDescription("Merged Youth");
		}

	    }
	    Collections.sort((ArrayList<JuvenileTraitResponseEvent>) (juvenileTraits));

	}
	form.setTraits(juvenileTraits);
	form.setRestrictedAccessTrait(false);
	form.setDetVisitBanned(false);
	//iterating the trait details to get trait desc Officer safety /security alert and show the alert in JSP ER JIMS200076601
	Iterator<JuvenileTraitResponseEvent> traitItr = juvenileTraits.iterator();
	while (traitItr.hasNext())
	{
	    JuvenileTraitResponseEvent traitRespEvent = (JuvenileTraitResponseEvent) traitItr.next();
	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_OFFICER_ALERT))
	    {
		form.setOfficerAlert("true");
		//break;
	    }

	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_RESTRICTED_ACCESS) && traitRespEvent.getStatusId().equalsIgnoreCase("CU"))
	    {
		form.setRestrictedAccessTrait(true);
		//break;
	    }

	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_BANNED_VISITATION))
	    {
		form.setDetVisitBanned(true);
		//break;
	    }

	}

	// Filter for former dual trait status
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum( form.getJuvenileNum() );
	traitByParentEvent.setTraitType("25");

	Collection<JuvenileTraitResponseEvent> juvTraits = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);

	Collections.sort((List<JuvenileTraitResponseEvent>) juvTraits, new Comparator<JuvenileTraitResponseEvent>() {
	    @Override
	    public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
	    {
		if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
		    return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
		else
		    return -1;
	    }
	});

	System.out.println(" juvenileTraits size:" + juvTraits.size());
	form.setDualStatus("");
	if (juvTraits != null && juvTraits.size() > 0)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvTraits)
	    {		
		//filter for current vs former
		if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && "CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		{
		    form.setDualStatus("DS");
		    break;
		}
		else
		{
		    if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && !"CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		    {
			form.setDualStatus("FDS");
			break;

		    }
		}
		
		
	    }
	}// end of if
    }

    /**
     * setFacilityBriefingFlags
     * 
     * @param form
     */
    private void setFacilityBriefingFlags(JuvenileBriefingDetailsForm form)
    {
	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(form.getJuvenileNum());
	event.setUIFacility(false);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	//iterating the trait details to get trait desc Officer safety /security alert and show the alert in JSP ER JIMS200076601
	Iterator traitItr = juvenileTraits.iterator();
	while (traitItr.hasNext())
	{

	    JuvenileTraitResponseEvent traitRespEvent = (JuvenileTraitResponseEvent) traitItr.next();
	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_BANNED_VISITATION))
	    {

		form.setDetVisitBanned(true);

	    }

	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_OFFICER_ALERT))
	    {
		form.setOfficerAlert("true");

	    }

	    if (traitRespEvent.getTraitTypeName().equals(UIConstants.TRAIT_TYPE_ADMINISTRATIVE) && traitRespEvent.getTraitTypeDescription().equals(UIConstants.TRAIT_TYPE_DESCRIPTION_RESTRICTED_ACCESS) && traitRespEvent.getStatusId().equalsIgnoreCase("CU"))
	    {
		form.setRestrictedAccessTrait(true);

	    }
	    if (traitRespEvent.getTraitTypeName().equals("PLACEMENT DISCHARGE REASON") && traitRespEvent.getTraitTypeDescription().equals("ESCAPE OR RUNAWAY TENDANCY"))
	    {
		form.setEscapeRisk(true);
	    }
	}
    }

    /**
     * @param juvenileNum
     * @param form
     *            Created by NM to use for referral briefing page, newly created
     *            Juv without a supervision assignment 4.2.3 Section III of
     *            MJCW_Process Referrals.docx
     */
    private void setFamHistoryForJuvWithoutSuprvsnAssign(String juvenileNum, JuvenileBriefingDetailsForm form)
    {
	Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(juvenileNum);

	if (!constellationList.isEmpty())
	{
	    /*
	     * Only 1 active constellation at a time, so get the first in the collection
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
			    MemberContact memberPhone = null;
			    pbean.setNameOfPerson(new Name(memberDetail.getFirstName(), memberDetail.getMiddleName(), memberDetail.getLastName()));

			    pbean.setRelationshipType(member.getRelationToJuvenile());
			    pbean.setRelationshipTypeId(member.getRelationToJuvenileId());
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
			    pbean.setStateIssuedIdState(member.getStateIssuedIdState());
			    /* //added below two for Referral User Story: 71650
			     pbean.setSSN(member.getSSN());
			     pbean.setCompleteSSN(memberDetail.getCompleteSSN());*/
			    //added below two for Referral BUG: 85184 NM
			    //			    SocialSecurityBean ssn = new SocialSecurityBean( member.getFormattedSSN());
			    //bug fix #89097
			    if (member.getSSN() != null)
			    {
				if (member.getSSN().contains("-"))
				{
				    pbean.setSSN(member.getSSN());
				}
				else
				{
				    SocialSecurityBean ssn = new SocialSecurityBean(member.getSSN());
				    pbean.setSSN(ssn.getFormattedSsn());
				}
			    }
			    //bug fix #89097
			    pbean.setGuardianAddress(UIJuvenileHelper.getFamilyMemberAddress(member.getMemberNum()));
			    pbean.setGuardianPhone(UIJuvenileHelper.getFamilyMemberPhone(member.getMemberNum()));
			    pbean.setIncarcerated(member.isIncarcerated());
			    pbean.setDeceased(member.isDeceased());
			    guardians.add(pbean);

			}
		    } // if member is guardian

		    form.setGuardians(guardians);
		    form.setFamilyMembers(guardians);
		} // end of for - familyMembers

	    }
	}
    }

    private DocketEventResponseEvent getNextCourtDetails(String juvenileNum)
    {
	String nextCourtDate = "";
	GetCourtActivityByYouthEvent courtEvt = (GetCourtActivityByYouthEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCOURTACTIVITYBYYOUTH);
	courtEvt.setJuvenileNum(juvenileNum);
	List<DocketEventResponseEvent> docketRespList = MessageUtil.postRequestListFilter(courtEvt, DocketEventResponseEvent.class);

	Iterator iter = docketRespList.iterator();
	while (iter.hasNext())
	{
	    DocketEventResponseEvent docket = (DocketEventResponseEvent) iter.next();
	    if (docket.getDocketEventId() == null || docket.getHearingType() == "DT")
	    {
		iter.remove();
	    }
	}

	if (docketRespList != null && docketRespList.size() > 0)
	{
	    Collections.sort(docketRespList, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent c1, DocketEventResponseEvent c2)
		{
		    return c2.getDocketEventId().compareTo(c1.getDocketEventId());
		}
	    });

	    /*if (docketRespList.get(0) != null)
	    {
		nextCourtDate = docketRespList.get(0).getCourtDate();
		if (nextCourtDate != null && nextCourtDate.length() > 0)
		{
		    if (DateUtil.compare(DateUtil.stringToDate(nextCourtDate, DateUtil.DATE_FMT_1), DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1) < 0)
		    {
			nextCourtDate = "";
		    }
		}
	    }*/ //do this in the call section

	}
	if (docketRespList != null && docketRespList.size() > 0)
	    return docketRespList.get(0);
	else
	    return null;
    }
    

    /**
     * (non-Javadoc)
     * 
     * @see LookupDispatchAction#getKeyMethodMap()
     */
    @Override
    protected Map<String, String> getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.link", "displayJuvenileBriefingDetails");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.back", "back");
	keyMap.put("button.viewProfile", "profile");
	keyMap.put("button.viewCasefile", "casefile");
	keyMap.put("button.details", "displayTraitDetails");
	keyMap.put("button.printDemographics", "printDemographics");
	//facility Changes
	keyMap.put("button.facilityLink", "displayJuvenileFacilityBriefingDetails");
	keyMap.put("button.view", "displayJuvenileFacilityAdmissionInfoDetails");
	//referral Changes
	keyMap.put("button.referralLink", "displayJuvenileReferralBriefingDetails");

	return keyMap;
    }

}

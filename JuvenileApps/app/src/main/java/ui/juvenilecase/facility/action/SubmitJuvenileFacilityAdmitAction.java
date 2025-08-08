/**
 * 
 */
package ui.juvenilecase.facility.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import messaging.address.reply.AddressResponseEvent;
import messaging.casefile.SaveFacilityDocEvent;
import messaging.casefile.reply.CasefileDocumentsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileAdmitReasonsResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.facility.SaveJuvenileFacilityAdmitDetailsEvent;
import messaging.facility.reply.JuvenileFacilityDetailsResponseEvent;
import messaging.family.GetActiveFamilyConstellationEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.family.GetFamilyMemberAddressEvent;
import messaging.family.GetFamilyMemberContactEvent;
import messaging.identityaddress.domintf.IAddressable;
import messaging.juvenile.GetJuvenileJobsEvent;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetTattoosScarsPhysicalAttributesEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import messaging.juvenile.reply.JuvenilePhysicalAttributesResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenile.reply.TattoosScarsPhysicalAttributesResponseEvent;
import messaging.juvenilecase.GetCurrentFacilityByJuvenileIdEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsByTypeEvent;
import messaging.juvenilecase.GetJuvenileTraitsEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.FamilyMemberPhoneResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.notification.CreateNotificationEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.security.UserEntityBean;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import mojo.naming.NotificationControllerSerivceNames;
import naming.ActivityConstants;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileFacilityControllerServiceNames;
import naming.JuvenileFamilyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileFamilyConstants;
import naming.UIConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.common.util.StringUtil;
import pd.juvenilecase.JJSFacility;
import pd.security.PDSecurityHelper;
import ui.action.JIMSBaseAction;
import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.UINotificationHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.SchoolHistoryComparator;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenilePhotoHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.facility.JuvenileFacilityReportBean;
import ui.juvenilecase.facility.form.AdmitReleaseForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenilePhotoForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author ugopinath
 */
public class SubmitJuvenileFacilityAdmitAction extends JIMSBaseAction
{

    /**
     * @param aMapping
     *            The a mapping.
     * @param aForm
     *            The a form.
     * @param aRequest
     *            The a request.
     * @param aResponse
     *            The a response.
     * @return ActionForward
     * @throws GeneralFeedbackMessageException
     * @roseuid 42A46D8E0234
     */

    public static final String FILE_SEPARATOR = File.separator;

    public static final String DIRECTORY_IMAGES_NAME = "images";

    public static final String DIRECTORY_BFO_NAME = "bfo";

    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	AdmitReleaseForm arForm = (AdmitReleaseForm) aForm;
	arForm.setAction("confirm");
	
	 //Check for a previously created admit record (rry)
	   GetCurrentFacilityByJuvenileIdEvent currFacEvt = new GetCurrentFacilityByJuvenileIdEvent();
	    currFacEvt.setJuvenileNum( arForm.getJuvenileNum() );
	    
	    dispatch.postEvent( currFacEvt );
	    CompositeResponse compositeResp  = (CompositeResponse) dispatch.getReply();
	    ReturnException issue = (ReturnException) MessageUtil.filterComposite(compositeResp, mojo.km.messaging.exception.ReturnException.class);

	    JuvenileDetentionFacilitiesResponseEvent facResponse = (JuvenileDetentionFacilitiesResponseEvent) 
		    				MessageUtil.filterComposite(compositeResp, JuvenileDetentionFacilitiesResponseEvent.class);
	    if( issue == null && facResponse != null ) {		
		
		arForm.setAction("failure");
		return aMapping.findForward("failure");
		
	    }
	    
	SaveJuvenileFacilityAdmitDetailsEvent saveAdmit = (SaveJuvenileFacilityAdmitDetailsEvent) EventFactory.getInstance(JuvenileFacilityControllerServiceNames.SAVEJUVENILEFACILITYADMITDETAILS);
	saveAdmit = setAdmitDetails(saveAdmit, arForm);
	dispatch.postEvent( saveAdmit );

	CompositeResponse compositeResponse  = (CompositeResponse) dispatch.getReply();
	ReturnException error = (ReturnException) MessageUtil.filterComposite(compositeResponse, mojo.km.messaging.exception.ReturnException.class);
	
	if (error != null)
	{
	    arForm.setAction("failure");
	    return aMapping.findForward("failure");
	}
	//US 42660 - for traits
	JuvenileDetentionFacilitiesResponseEvent detResponse = (JuvenileDetentionFacilitiesResponseEvent) MessageUtil.filterComposite(compositeResponse, JuvenileDetentionFacilitiesResponseEvent.class);
	if (detResponse != null)
	{
	    JuvenileDetentionFacilitiesResponseEvent det = (JuvenileDetentionFacilitiesResponseEvent) detResponse;
	    arForm.setOriginalAdmitOID(det.getOriginalAdmitOID());
	    if (det.getOriginalAdmitOID() == null)
	    { // Fix added when the migrated record doesn't have original admit oid.
		arForm.setOriginalAdmitOID(det.getDetentionId());
	    }
	    HttpSession session = aRequest.getSession();
	    session.setAttribute("FACILITY_DETENTION_RESP", det); // add detention response event to the session.
	}
	//Record admission activity
	String label1 = "" + arForm.getLocationOneLabel();
	String label2 = "" + arForm.getLocationTwoLabel();
	String label3 = "" + arForm.getLocationThreeLabel();

	String location = "";
	if (arForm.getFloorLocation().equals("") && arForm.getUnitLocation().equals("") && arForm.getRoomLocation().equals(""))
	    location = "Location is Unknown";
	else
	{
	    if (label1.equals("") && label2.equals("") && label3.equals(""))
		location = "LOCATION" + arForm.getFloorLocation() + ", " + arForm.getUnitLocation() + ", " + arForm.getRoomLocation();
	    else
		location = label1 + " " + arForm.getFloorLocation() + ", " + label2 + " " + arForm.getUnitLocation() + " ," + label3 + " " + arForm.getRoomLocation();

	    if (!arForm.getMultipleOccupancyUnit().equals(""))
		location += "-" + arForm.getMultipleOccupancyUnit();
	}

	String comment = "Juvenile's location in facility " + arForm.getDetainedFacility() + ": " + location;
	if (arForm.getCurrentSupervisionNum() != null && !arForm.getCurrentSupervisionNum().equals(""))
	    UIJuvenileHelper.createActivity(arForm.getCurrentSupervisionNum(), ActivityConstants.ADMIT_TO_FACILITY, comment);
	else
	    UIJuvenileHelper.createActivity(arForm.getMostRecentCasefileId(), ActivityConstants.ADMIT_TO_FACILITY, comment);

	//Send notification to JPOs of all active and/or pending casefiles

	Collection coll = JuvenileFacilityHelper.getCasefiles(arForm.getJuvenileNum());
	if (coll.size() > 0)
	{
	    /*Collection tempColl = casefiles.values();
	    Iterator tempIter = tempColl.iterator();*/
	    Iterator casefilesIter = coll.iterator();
	    while (casefilesIter.hasNext())
	    {
		JuvenileCasefileSearchResponseEvent caseResp = (JuvenileCasefileSearchResponseEvent) casefilesIter.next();
		if (caseResp.getCaseStatus() != null && (caseResp.getCaseStatus().equalsIgnoreCase("A") || caseResp.getCaseStatus().equalsIgnoreCase("ACTIVE") || caseResp.getCaseStatus().equalsIgnoreCase("P") || caseResp.getCaseStatus().equalsIgnoreCase("PENDING")))
		{
		    JuvenileFacilityDetailsResponseEvent facDet = new JuvenileFacilityDetailsResponseEvent();
		    facDet.setSubject("Juvenile admitted to facility");
		    String s = caseResp.getJuvenileFirstName() + " " + caseResp.getJuvenileMiddleName() + " " + caseResp.getJuvenileLastName() + " " + caseResp.getJuvenileNum() + " under Supervision# " + caseResp.getSupervisionNum() + " was placed in " + arForm.getDetainedFacilityStr() + " on Referral# " + arForm.getBookingReferral() + " on " + arForm.getAdmitDateStr();
		    facDet.setNotificationMessage(s);
		    facDet.setIdentity(caseResp.getOfficerLoginId());
		    CreateNotificationEvent notification = (CreateNotificationEvent) EventFactory.getInstance(NotificationControllerSerivceNames.CREATENOTIFICATION);
		    notification.setNotificationTopic(UIConstants.JUVENILE_FACILITY_ADMIT_NOTIFICATION);
		    notification.setSubject(facDet.getSubject());
		    notification.addIdentity("respEvent", (IAddressable) facDet);
		    notification.addContentBean(facDet);
		    CompositeResponse response = MessageUtil.postRequest(notification);
		    MessageUtil.processReturnException(response);
		}
	    }

	}

	arForm.setHideFacilityTraitsLink(false);
	JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	briefingDetailsForm.setHideFacilityTraitsLink(false); //bug no #41496

	String juvenileNum = briefingDetailsForm.getJuvenileNum();
	String juvenileName = briefingDetailsForm.getProfileDetail().getLastName() + ", " + briefingDetailsForm.getProfileDetail().getFirstName() + " " + briefingDetailsForm.getProfileDetail().getMiddleName();
	if (juvenileNum != null && juvenileName != null)
	{
	    sendDualStatusEmail(juvenileNum, juvenileName);
	}
	return aMapping.findForward(UIConstants.SUCCESS);

    }

    private SaveJuvenileFacilityAdmitDetailsEvent setAdmitDetails(SaveJuvenileFacilityAdmitDetailsEvent saveAdmit, AdmitReleaseForm arForm)
    {
	saveAdmit.setJuvenileNum(arForm.getJuvenileNum());
	saveAdmit.setSpecialAttentionCd(arForm.getSpecialAttentionCd());
	saveAdmit.setSaReason(JuvenileFacilityHelper.setSAReasonId(arForm.getSelectedSAReasons(), arForm.getSpecialAttentionReasons()));
	saveAdmit.setSaReasonOtherComments(arForm.getSaReasonOtherComments());
	saveAdmit.setBookingReferral(arForm.getBookingReferral());
	saveAdmit.setBookingOffense(arForm.getBookingOffenseCd());
	saveAdmit.setBookingSupervisionNum(arForm.getBookingSupervisionNum());
	saveAdmit.setBookingPetitionNum(arForm.getBookingPetitionNum());
	saveAdmit.setCurrentReferral(arForm.getCurrentReferral());
	saveAdmit.setCurrentOffense(arForm.getCurrentOffenseCd());
	saveAdmit.setCurrentSupervisionNum(arForm.getCurrentSupervisionNum());
	saveAdmit.setCurrentPetitionNum(arForm.getCurrentPetitionNum());
	saveAdmit.setDetainedFacility(arForm.getDetainedFacility());
	saveAdmit.setSecureStatus(arForm.getSecureStatus());
	saveAdmit.setReasonCode(arForm.getReasonCode());
	saveAdmit.setAdmitDate(arForm.getAdmitDate());
	saveAdmit.setAdmitTime(arForm.getAdmitTime());
	saveAdmit.setAdmitBy(arForm.getAdmitBy());
	saveAdmit.setAdmitAuthority(arForm.getAdmitAuthority());
	saveAdmit.setValuablesReceipt(arForm.getValuablesReceipt());
	saveAdmit.setLocker(arForm.getLocker());
	saveAdmit.setReferralSource(arForm.getReferralSource());
	saveAdmit.setReferralOfficers(arForm.getReferralOfficers());
	saveAdmit.setFloorLocation(arForm.getFloorLocation());
	saveAdmit.setUnitLocation(arForm.getUnitLocation());
	saveAdmit.setRoomLocation(arForm.getRoomLocation());
	saveAdmit.setAdmissionComments(arForm.getAdmissionComments());
	saveAdmit.setDetentionType(arForm.getAdmitReasonDetentionType());
	saveAdmit.setDetainedFacility(arForm.getDetainedFacility());
	saveAdmit.setOriginalAdmitDate(arForm.getOriginalAdmitDate());
	saveAdmit.setFacRefereeCourt(JuvenileFacilityHelper.getFacility(arForm.getFacilities(), arForm.getDetainedFacility()).getRefereeCourt());
	JuvenileFacilityResponseEvent facilityResp = JuvenileFacilityHelper.getFacility(arForm.getFacilities(), arForm.getDetainedFacility());
	if (facilityResp != null)
	{
	    saveAdmit.setTjjdFacilityId(facilityResp.getTjjdFacilityId());
	    saveAdmit.setTjjdFundingSrc(facilityResp.getTjjdFundingSrc());
	    saveAdmit.setAvgCostPerDay(facilityResp.getAvgCostPerDay()); //US 161101
	}
	//set the detention hearing flag for admit reason
	JuvenileAdmitReasonsResponseEvent admitReasonResp = JuvenileFacilityHelper.getAdmitReason(arForm.getReasonCode(), arForm.getAdmitReasons());
	if (admitReasonResp != null)
	{
	    saveAdmit.setGenDetHearingChain(admitReasonResp.getGenDetHearingChain());
	    saveAdmit.setDaysToProbableCause(admitReasonResp.getProbCauseHearingDays());
	}
	saveAdmit.setOriginalAdmitTime(arForm.getOriginalAdmitTime());
	saveAdmit.setOriginalAdmitOID(arForm.getOriginalAdmitOID()); // U.S #44549
	saveAdmit.setNewAdmit(arForm.isNewAdmit());
	saveAdmit.setMultipleOccupyUnit(arForm.getMultipleOccupancyUnit());
	//Bug #69605
	saveAdmit.setDetainedDate(DateUtil.stringToDate(arForm.getDetainedDate(), DateUtil.DATE_FMT_1));
	saveAdmit.setDetainedByInd(arForm.getDetainedByInd());
	
	saveAdmit.setVendorLocation(arForm.getVendorLocation());
	//User story 181605 - set originaladmitSEQNUM = 10 as default for the first record
	saveAdmit.setOriginaladmitSEQNUM("10");
	
	//User story 183699 - Add a new POSTADMITOID
	JJSFacility currentFacility = JuvenileFacilityHelper.getCurrentFacilityRecord( arForm.getJuvenileNum() );
	if ( currentFacility!= null
		&& !"NTS".equals( currentFacility.getReleaseTo() )
		&&  arForm.getAdmitDate().equals( currentFacility.getReleaseDate() )  )
	{
	    saveAdmit.setPostAdmitOID( currentFacility.getOID() );
	}
	return saveAdmit;
    }

    public ActionForward generateDraft(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	generateReport(aForm, aRequest, aResponse, "Y");
	return aMapping.findForward("draftSuccess");
    }

    public ActionForward generateFinal(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {
	generateReport(aForm, aRequest, aResponse, "N");
	return aMapping.findForward("draftSuccess");
    }

    private void generateReport(ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse, String isDraft) throws Exception
    {
	AdmitReleaseForm arForm = (AdmitReleaseForm) aForm;
	Collection saReasons = new ArrayList();
	//Added for user-story #51449
	String view = aRequest.getParameter("view");
	if (view != null && view.equals("briefingView"))
	{
	    arForm.setAction("facilityAdmitGenerateReport");
	    //get the form from the briefing details page.
	    JuvenileBriefingDetailsForm briefingDetailsForm = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
	    briefingDetailsForm.setAction("facilityAdmitGenerateReport");
	    //POPULATE THE ADMIT RELEASE FORM NEEDED FOR THE REPORT
	    arForm.setJuvenileNum(briefingDetailsForm.getJuvenileNum());
	    arForm.setBookingSupervisionNum(briefingDetailsForm.getBookingSupervisionNum());
	    arForm.setBookingOffense(briefingDetailsForm.getBookingOffenseCodeDesc());
	    arForm.setReferralSource(briefingDetailsForm.getAdmissionInfo().getReferralSource());
	    ;
	    JuvenileFacilityHelper.populateProfileReferralDetails(arForm);
	    JuvenileFacilityHelper.populateFacilityObservationStatus(arForm, briefingDetailsForm.getAdmissionInfo());
	    JuvenileFacilityHelper.populateAdmissionInformation(arForm, briefingDetailsForm.getAdmissionInfo());
	    //release information population.
	    arForm.setReleasedBy(briefingDetailsForm.getAdmissionInfo().getReleaseBy());
	    arForm.setReleaseDate(DateUtil.dateToString(briefingDetailsForm.getAdmissionInfo().getReleaseDate(), DateUtil.DATE_FMT_1));
	    if (briefingDetailsForm.getAdmissionInfo().getReleaseTime() != null)
	    {
		arForm.setReleaseTime(JuvenileFacilityHelper.stripColonInDate(briefingDetailsForm.getAdmissionInfo().getReleaseTime()));
	    }
	    else
	    {
		arForm.setReleaseTime("");
	    }
	    arForm.setReleaseAuthority(briefingDetailsForm.getAdmissionInfo().getReleaseAuthorizedBy());
	    arForm.setReleasedTo(briefingDetailsForm.getAdmissionInfo().getReleaseTo());
	    // VALUBLES AND RECEIPTS
	    arForm.setValuablesReceipt(briefingDetailsForm.getAdmissionInfo().getReceiptNumber());
	    arForm.setLocker(briefingDetailsForm.getAdmissionInfo().getLockerNumber());
	    arForm.setDetainedFacilityStr(briefingDetailsForm.getAdmissionInfo().getDetainedFacilityDesc());
	    arForm.setReferralOfficers(briefingDetailsForm.getAdmissionInfo().getReferralOfficer());

	    //populate the sareason			
	    if (arForm.getSaReasonStr() != null)
	    {
		String[] selectedSAReasons = StringUtils.split(arForm.getSaReasonStr(), ",");
		for (int i = 0; i < selectedSAReasons.length; i++)
		{
		    String reason = selectedSAReasons[i];
		    Iterator saReasonsIter = arForm.getSpecialAttentionReasons().iterator();
		    while (saReasonsIter.hasNext())
		    {
			CodeResponseEvent codeResp = (CodeResponseEvent) saReasonsIter.next();
			if (reason != null && reason.equalsIgnoreCase(codeResp.getCode()))
			{
			    saReasons.add(codeResp.getDescription());
			}
		    }
		}
	    }
	    arForm.setSelectedSAReasons(null);
	}
	//Added for user-story #51449

	String juvenileNum = arForm.getJuvenileNum();
	JuvenileFacilityReportBean repBean = new JuvenileFacilityReportBean();
	repBean.setJuvenileNum(juvenileNum);
	repBean.setBookingReferral(arForm.getBookingReferral());
	repBean.setBookingSupervisionNum(arForm.getBookingSupervisionNum());
	repBean.setDraft(isDraft);
	repBean.setSpAttention(arForm.getSpecialAttentionCd());

	if (arForm.getSelectedSAReasons() != null)
	{
	    String[] selectedSAReasons = arForm.getSelectedSAReasons();
	    for (int i = 0; i < selectedSAReasons.length; i++)
	    {
		String reason = selectedSAReasons[i];
		Iterator saReasonsIter = arForm.getSpecialAttentionReasons().iterator();
		while (saReasonsIter.hasNext())
		{
		    CodeResponseEvent codeResp = (CodeResponseEvent) saReasonsIter.next();
		    if (reason != null && reason.equalsIgnoreCase(codeResp.getDescription()))
		    {
			saReasons.add(codeResp.getDescription());
		    }
		}
	    }
	}

	repBean.setSaReasons(saReasons);
	repBean.setAdmitAuthority(arForm.getAdmitAuthority());
	repBean.setFloorLocation(arForm.getFloorLocation());
	repBean.setUnitLocation(arForm.getUnitLocation());
	if (arForm.getMultipleOccupancyUnit() != null && !arForm.getMultipleOccupancyUnit().equals(""))
	    repBean.setRoomLocation(arForm.getRoomLocation() + arForm.getMultipleOccupancyUnit());
	else
	    repBean.setRoomLocation(arForm.getRoomLocation());
	//get the juvenile details
	GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);

	requestEvent.setJuvenileNum(juvenileNum);

	CompositeResponse replyEvent = postRequestEvent(requestEvent);

	JuvenileProfileDetailResponseEvent juvenile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);
	//repBean.setJuvenileName(juvenile.getFormattedName());
	String[] splitLastName = juvenile.getLastName().split("");
	String firstThreeChars = "";
	if (splitLastName.length > 3)
	{
	    for (int i = 0; i < 4; i++)
	    {
		firstThreeChars += splitLastName[i];
	    }
	}
	else
	    firstThreeChars = juvenile.getLastName();
	repBean.setThreeLettersOfLastname(firstThreeChars);
	repBean.setHispanic(StringUtil.nvl(juvenile.getHispanic())); //U.S 88526
	repBean.setRace(StringUtil.nvl(juvenile.getRaceId()));
	repBean.setGender(StringUtil.nvl(juvenile.getSexId()));
	repBean.setJuvReligiousPreference(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.RELIGION, juvenile.getReligion()));
	int age = UIUtil.getAgeInYears(juvenile.getDateOfBirth());
	if (age > 0)
	{
	    repBean.setAge(String.valueOf(age));
	}
	else
	    repBean.setAge("");

	repBean.setDob(DateUtil.dateToString(juvenile.getDateOfBirth(), DateUtil.DATE_FMT_1));
	repBean.setVerifiedDOB(juvenile.isVerifiedDOB() ? "Y" : "N");
	//get family details
	Address familyAddress = new Address();

	Collection constellations = getCurrentActiveConstellation(juvenileNum);
	if (constellations != null && !constellations.isEmpty())
	{
	    // Only 1 active constellation at a time, therefore it's safe to get
	    // the first in collection
	    FamilyConstellationListResponseEvent activeConstellation = (FamilyConstellationListResponseEvent) constellations.iterator().next();
	    GetFamilyConstellationDetailsEvent getConstellationDetails = new GetFamilyConstellationDetailsEvent();
	    getConstellationDetails.setConstellationNum(activeConstellation.getFamilyNum());
	    replyEvent = MessageUtil.postRequest(getConstellationDetails);
	    Collection familyMembers = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationMemberListResponseEvent.class);
	    Collection relatives = new ArrayList();
	    Collection livesWith = new ArrayList();
	    // find first in-home guardian and get the address
	    if (familyMembers != null && familyMembers.size() > 0)
	    {

		Iterator iter = familyMembers.iterator();
		while (iter.hasNext())
		{
		    FamilyConstellationMemberListResponseEvent member = (FamilyConstellationMemberListResponseEvent) iter.next();
		    //bug no #41500, Added the check for the inHome status to add the Juv Address
		    if (member.isInHomeStatus() && member.isGuardian())
		    {
			if (member.isPrimaryContact())
			{
			    familyAddress = getFamilyMemberAddress(member.getMemberNum());
			}
		    }
		    member.setMemberAddress(getFamilyMemberAddress(member.getMemberNum()));
		    member.setPhone(getMemberPhoneNumber(member.getMemberNum()));
		    relatives.add(member);
		}
		repBean.setRelatives(relatives);
	    }
	}

	//repBean.setLivesWith(livesWith);

	repBean.setJuvenileName(juvenile.getFormattedName());

	repBean.setMemberAddress(familyAddress);

	//married status
	JuvenileTraitResponseEvent marr = this.isCurrrentlyMarried(juvenileNum, "MRR");
	if (marr != null)
	    repBean.setMaritalStatus(marr.getTraitTypeDescription());
	else
	    repBean.setMaritalStatus("");

	//photo
	JuvenilePhotoForm photoForm = UIJuvenileHelper.getJuvPhotoForm(aRequest, true);
	photoForm.setJuvenileNumber(juvenileNum);
	UIJuvenilePhotoHelper.getMostRecentPhotoInit(photoForm);
	if (photoForm.getMostRecentPhoto() != null)
	{
	    ByteArrayInputStream photo = null;
	    boolean isPhoto = false;
	    if (photoForm.getMostRecentPhoto().getPhoto() != null)
	    {
		photo = new ByteArrayInputStream(photoForm.getMostRecentPhoto().getPhoto());
		isPhoto = true;
	    }
	    else
		if (photoForm.getMostRecentPhoto().getThumbNail() != null)
		{
		    photo = new ByteArrayInputStream(photoForm.getMostRecentPhoto().getThumbNail());
		    isPhoto = true;
		}
	    if (isPhoto)
	    {
		log.error("$$$$$$$$$$$$PROD HOT FIX_ PHOTO TRY 1 $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		BufferedImage image = ImageIO.read(photo);
		String photoFileName = arForm.getJuvenileNum() + ".jpg";
		String bfoDirectoryPath = FILE_SEPARATOR + DIRECTORY_BFO_NAME;
		String relativeFolderPath = DIRECTORY_IMAGES_NAME + bfoDirectoryPath;
		String relativeFilePath = relativeFolderPath + FILE_SEPARATOR + photoFileName;
		String absoluteFolderPath = aRequest.getSession().getServletContext().getRealPath(DIRECTORY_IMAGES_NAME) + bfoDirectoryPath;
		//	String absoluteFilePath = aRequest.getSession().getServletContext().getRealPath(DIRECTORY_IMAGES_NAME) + bfoDirectoryPath + FILE_SEPARATOR + photoFileName;

		String absoluteFilePath = "c:\\BFOImages\\images\\bfo\\" + photoFileName;
		log.error("$$$$$$$$$$$$PROD HOT FIX_ PHOTO TRY 2 $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		File bfoFolder = new File(absoluteFolderPath);
		File file = new File(absoluteFilePath);
		// check if folder exists but file does not... if true then write file and save path to session
		if (!file.exists())
		{
		    ImageIO.write(image, "jpg", file);
		    aRequest.getSession().setAttribute("printJuvPhotoPath", "images\\bfo\\" + photoFileName);
		    log.error("$$$$$$$$$$$$PROD HOT FIX_ PHOTO TRY 3 $$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		    // if folder does not exist - try to create folder and if created write file and save path to session, otherwise, save null
		}
		else
		    if (!bfoFolder.exists())
		    {
			boolean fileCreated = true;
			try
			{
			    fileCreated = bfoFolder.mkdir();
			}
			catch (Exception ex)
			{
			    fileCreated = false;
			    log.error("The folder web-app did not exist and could not be created: " + bfoDirectoryPath);
			}
			if (fileCreated)
			{
			    ImageIO.write(image, "jpg", file); // has to be written for jsp img path to pick up in jsp
			    aRequest.getSession().setAttribute("printJuvPhotoPath", "images\\bfo\\" + photoFileName);
			}
			else
			{
			    aRequest.getSession().setAttribute("printJuvPhotoPath", null);
			}
			// file and folder exist - save the path to the session
		    }
		    else
		    {
			aRequest.getSession().setAttribute("printJuvPhotoPath", "images\\bfo\\" + photoFileName);
		    }
	    }
	    else
	    { // there is no photo - either thumbnail nor higher resolution
		aRequest.getSession().setAttribute("printJuvPhotoPath", null);
	    }
	}
	else
	{
	    // there was no photo object to begin with.
	    aRequest.getSession().setAttribute("printJuvPhotoPath", null);

	}

	//physical characteristics
	repBean.setPhysicalAttribute(getPhysicalAttributes(juvenileNum));
	//get the school history
	JuvenileSchoolHistoryResponseEvent school = getSchoolHistory(juvenileNum);
	repBean.setSchoolHistory(school);
	repBean.setEnrollmentStatus(school.getExitTypeCode());
	//job details
	JuvenileJobResponseEvent job = getJuvenileEmploymentDetails(juvenileNum);
	if (job != null)
	    repBean.setEmploymentDet(job);

	repBean.setDetentionTraits(getTraits(juvenileNum));
	repBean.setDetainedFacility(arForm.getDetainedFacilityStr());

	//set the facilities history	
	List sortedList = new ArrayList(JuvenileFacilityHelper.getJuvFacilityDetails(juvenileNum, null)); //fetch all the facility recs for the juvenile.
	ArrayList sortFields = new ArrayList();
	sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
	sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
	ComparatorChain chain = new ComparatorChain(sortFields);
	Collections.sort(sortedList, chain);
	repBean.setJuvFacList(sortedList);

	// tell BFO to save report if not a draft
	if (isDraft.equalsIgnoreCase("N"))
	{
	    aRequest.setAttribute("isPdfSaveNeeded", "true");
	}
	aRequest.getSession().setAttribute("repBean", repBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.FACILITY_ADMISSION_FORM);

	byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
	if (pdfDocument != null)
	{
	    // persist a copy of the BFO pdf document
	    /*SaveJuvenileProfileDocumentEvent saveEvent = 
	    	(SaveJuvenileProfileDocumentEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SAVEJUVENILEPROFILEDOCUMENT);
	    saveEvent.setDocument(pdfDocument);
	    saveEvent.setDocumentTypeCodeId(UIConstants.FACILITY_DOCTYPE_CODE);
	    saveEvent.setJuvenileNum(arForm.getJuvenileNum());
	    saveEvent.setEntryDate(DateUtil.getCurrentDate());
	    CompositeResponse resp =  MessageUtil.postRequest(saveEvent);
	    ReturnException returnException =
	    	   (ReturnException) MessageUtil.filterComposite(resp, ReturnException.class);
	    
	    if (returnException != null) {
	    
	    }*/

	    SaveFacilityDocEvent saveEvt = new SaveFacilityDocEvent();
	    if (arForm.getCurrentSupervisionNum() != null && !arForm.getCurrentSupervisionNum().equals(""))
		saveEvt.setCasefileId(arForm.getCurrentSupervisionNum());
	    else
		saveEvt.setCasefileId(arForm.getMostRecentCasefileId());
	    saveEvt.setDocument(pdfDocument);
	    saveEvt.setDocTypeCd(UIConstants.FACILITY_DOCTYPE_CODE);

	    CompositeResponse response = postRequestEvent(saveEvt);
	    CasefileDocumentsResponseEvent respEvt = (CasefileDocumentsResponseEvent) MessageUtil.filterComposite(response, CasefileDocumentsResponseEvent.class);
	    if (respEvt == null)
	    {
		sendToErrorPage(aRequest, "error.generic", "Problems generating and saving report"); // Add to error collection for page
	    }

	    aRequest.removeAttribute("pdfSavedReport");
	}

    }

    /* gets the relative's phone number.
    * @param memberNum	 
    */
    private String getMemberPhoneNumber(String memNum)
    {

	GetFamilyMemberContactEvent event = (GetFamilyMemberContactEvent) EventFactory.getInstance(JuvenileFamilyControllerServiceNames.GETFAMILYMEMBERCONTACT);
	event.setMemberId(memNum);
	// Getting PD Response Event
	CompositeResponse response = MessageUtil.postRequest(event);
	Map dataMap = MessageUtil.groupByTopic(response);
	FamilyMemberPhoneResponseEvent responseEvt;
	String phoneNum = "";
	if (dataMap != null)
	{
	    Collection contacts = (Collection) dataMap.get(PDJuvenileFamilyConstants.FAMILY_MEMBER_CONTACT_TOPIC);
	    if (contacts != null && contacts.size() > 0)
	    {
		Iterator iter = contacts.iterator();
		while (iter.hasNext())
		{
		    responseEvt = (FamilyMemberPhoneResponseEvent) iter.next();
		    if (responseEvt != null)
		    {
			if (responseEvt.isPrimaryInd())
			{
			    phoneNum = responseEvt.getPhoneNum();
			    if (phoneNum != null && !phoneNum.equals(""))
				return phoneNum.substring(0, 3) + "-" + phoneNum.substring(3, 6) + "-" + phoneNum.substring(6, 10);
			    else
				return "";
			}
		    }
		}
	    }
	}
	return null;
    }

    /**
     * gets the juv's physical attributes.
     * 
     * @param juvenileNum
     */
    private JuvenilePhysicalAttributesResponseEvent getPhysicalAttributes(String juvenileNum)
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
	    String tattosComments = description + " " + otherTattooComments;
	    phyAttRespEvent.setTattoosDescription(tattosComments);
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
	    return phyAttRespEvent;
	}

	return (new JuvenilePhysicalAttributesResponseEvent());

    }

    /**
     * returns true if string isn't null and contains one or more chars
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && str.trim().length() > 0);
    }

    private JuvenileTraitResponseEvent isCurrrentlyMarried(String juvNum, String traitTypeId)
    {
	GetJuvenileTraitsByTypeEvent event = (GetJuvenileTraitsByTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYTYPE);

	event.setJuvenileNum(juvNum);
	event.setTraitType(traitTypeId);
	CompositeResponse composite = MessageUtil.postRequest(event);
	List traitList = MessageUtil.postRequestListFilter(event, JuvenileTraitResponseEvent.class);
	//go through the list and check married trait current
	Iterator iter = traitList.iterator();
	while (iter.hasNext())
	{
	    JuvenileTraitResponseEvent resp = (JuvenileTraitResponseEvent) iter.next();
	    if (resp.getStatusId() != null && resp.getStatusId().equalsIgnoreCase("CU"))
		return resp;
	}
	return null;
    }

    private JuvenileSchoolHistoryResponseEvent getSchoolHistory(String juvenileNum)
    {
	Collection schoolHistories = UIJuvenileHelper.fetchSchoolHistory(juvenileNum);
	JuvenileSchoolHistoryResponseEvent mySchoolHistoryResp = new JuvenileSchoolHistoryResponseEvent();
	//code edited to get the first item from the school history in the Admission report BUG #41500
	if (schoolHistories.size() > 0)
	{
	    Collections.sort((List) schoolHistories, new SchoolHistoryComparator());
	    mySchoolHistoryResp = (JuvenileSchoolHistoryResponseEvent) ((ArrayList) schoolHistories).get(0);
	}
	return mySchoolHistoryResp;
    }

    private JuvenileJobResponseEvent getJuvenileEmploymentDetails(String juvenileNumber)
    {
	String location = "";
	GetJuvenileJobsEvent jobsEvent = (GetJuvenileJobsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEJOBS);

	jobsEvent.setJuvenileNum(juvenileNumber);
	List jobs = MessageUtil.postRequestListFilter(jobsEvent, JuvenileJobResponseEvent.class);
	if (jobs != null && !jobs.isEmpty())
	{
	    Collections.sort(jobs);
	    // Job Location is a required value on the UI so we can take the
	    // first Object in the list
	    JuvenileJobResponseEvent resp = (JuvenileJobResponseEvent) jobs.get(0);
	    return resp;
	}
	return null;
    }

    private Collection getCurrentActiveConstellation(String juvenileNum)
    {
	GetActiveFamilyConstellationEvent getCurrentConstellation = new GetActiveFamilyConstellationEvent();
	getCurrentConstellation.setJuvenileNum(juvenileNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(getCurrentConstellation);
	Collection constellations = MessageUtil.compositeToCollection(replyEvent, FamilyConstellationListResponseEvent.class);
	return constellations;
    }

    private Address getFamilyMemberAddress(String memberNum)
    {
	Address familyAddress = null;
	GetFamilyMemberAddressEvent getFamilyMemberAddress = new GetFamilyMemberAddressEvent();
	getFamilyMemberAddress.setMemberNum(memberNum);
	CompositeResponse replyEvent = MessageUtil.postRequest(getFamilyMemberAddress);
	Collection familyMemberAddresses = MessageUtil.compositeToCollection(replyEvent, AddressResponseEvent.class);
	if (familyMemberAddresses != null)
	{
	    Collections.sort((List) familyMemberAddresses);
	    Iterator addrIter = familyMemberAddresses.iterator();
	    while (addrIter.hasNext())
	    {
		AddressResponseEvent addr = (AddressResponseEvent) addrIter.next();
		// get only latest home address
		if (addr != null && addr.getAddressTypeId().equalsIgnoreCase("RES"))
		{
		    familyAddress = new Address();
		    familyAddress.setStreetNum(addr.getStreetNum());
		    familyAddress.setStreetNumSuffixId(addr.getStreetNumSuffixId());
		    familyAddress.setStreetName(addr.getStreetName());
		    familyAddress.setAptNum(addr.getAptNum());
		    familyAddress.setCity(addr.getCity());
		    familyAddress.setStateId(addr.getStateId());
		    familyAddress.setState(addr.getState());
		    familyAddress.setCountyId(addr.getCountyId());
		    familyAddress.setZipCode(addr.getZipCode());
		    familyAddress.setAdditionalZipCode(addr.getAdditionalZipCode());
		    familyAddress.setStreetType(addr.getStreetType());
		    familyAddress.setAddressTypeId(addr.getAddressTypeId());
		    familyAddress.setStreetTypeId(addr.getStreetTypeId());
		    familyAddress.setAddressStatus(addr.getValidated());
		    familyAddress.setCounty(addr.getCounty());//added for BUG 41500
		    break;
		}
	    }
	}
	return familyAddress;
    }

    private Collection getTraits(String juvenileNum)
    {

	String detentionTrait = "";
	ArrayList detTraits = new ArrayList();
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum(juvenileNum);
	traitByParentEvent.setTraitType("26");
	List<JuvenileTraitResponseEvent> traitsList = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);
	if (traitsList != null && traitsList.size() > 0)
	{
	    Collections.sort(traitsList);
	}
	Iterator iter = traitsList.iterator();
	while (iter.hasNext())
	{
	    JuvenileTraitResponseEvent resp = (JuvenileTraitResponseEvent) iter.next();
	    if (resp != null)
		detentionTrait = resp.getTraitTypeDescription() + "-" + resp.getStatus() + ";";
	    if (iter.hasNext())
	    {
		resp = (JuvenileTraitResponseEvent) iter.next();
		if (resp != null)
		    detentionTrait += "          " + resp.getTraitTypeDescription() + "-" + resp.getStatus() + ";";
	    }
	    if (iter.hasNext())
	    {
		resp = (JuvenileTraitResponseEvent) iter.next();
		if (resp != null)
		    detentionTrait += "          " + resp.getTraitTypeDescription() + "-" + resp.getStatus() + ";";
	    }
	    resp = new JuvenileTraitResponseEvent();
	    resp.setTraitTypeDescription(detentionTrait);
	    detTraits.add(resp);

	}
	return detTraits;

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
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
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	AdmitReleaseForm admitForm = (AdmitReleaseForm) aForm;
	String[] selectedFacility = null;
	String facility = JuvenileFacilityHelper.getFacilityCodeWithJuvTJPCPlacementType(admitForm.getFacilities(), admitForm.getDetainedFacility());
	if (facility != null)
	{
	    selectedFacility = facility.split("[|]");
	    admitForm.setDetainedFacility(admitForm.getDetainedFacility() + "|" + admitForm.getPlacementType() + "|" + selectedFacility[2]);
	}

	//set the Admit Reason Code
	Iterator arIter = admitForm.getAdmitReasons().iterator();
	while (arIter.hasNext())
	{
	    JuvenileAdmitReasonsResponseEvent resp = (JuvenileAdmitReasonsResponseEvent) arIter.next();
	    if (resp.getCode().equalsIgnoreCase(admitForm.getReasonCode()))
	    {
		admitForm.setReasonCode(resp.getCodeWithDetType());
	    }
	}
	admitForm.setAdmitTime(JuvenileFacilityHelper.stripColonInDate(admitForm.getAdmitTime()));
	admitForm.setAction("create");
	return (aMapping.findForward(UIConstants.BACK));

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward searchFacility(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward("searchSuccess"));

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward casefileDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	AdmitReleaseForm admitForm = (AdmitReleaseForm) aForm;
	HttpSession session = aRequest.getSession();
	JuvenileCasefileForm casefileForm = new JuvenileCasefileForm();
	if (admitForm.getCurrentSupervisionNum() != null && !admitForm.getCurrentSupervisionNum().equals(""))
	    casefileForm.setSupervisionNum(admitForm.getCurrentSupervisionNum());
	else
	    casefileForm.setSupervisionNum(admitForm.getMostRecentCasefileId());
	session.setAttribute("juvenileCasefileForm", casefileForm);
	return (aMapping.findForward("details"));

    }

    private void sendDualStatusEmail(String juvenileNum, String juvenileName)
    {
	boolean isDST = false;
	String headerFacilityStatus = "";

	GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(juvenileNum);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;

	Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	if (juvenileTraits != null && juvenileTraits.size() > 0)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvenileTraits)
	    {
		if (juvenileTrait.getTraitTypeId().equals("DST"))
		{
		    isDST = true;
		}
	    }
	}

	JuvenileFacilityHeaderResponseEvent jjsHeaderInfo = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	if (jjsHeaderInfo != null)
	{
	    headerFacilityStatus = jjsHeaderInfo.getFacilityStatus();
	}

	if (isDST && "D".equals(headerFacilityStatus))
	{
	    //Collection<OfficerProfileResponseEvent>   securityRespEvent =  PDOfficerProfileHelper.getOfficerProfilesInUserGroup("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP");
	    List<UserEntityBean> userGroup = PDSecurityHelper.getUserGroupByIdOrName("DUAL STATUS YOUTH DETAINED NOTIFICATION GROUP", "");
	    if (userGroup != null)
	    {

		for (UserEntityBean user : userGroup)
		{

		    if (user.getEmail() != null && !user.getEmail().equals(""))
		    {

			//send email
			System.out.println("Email:" + user.getEmail());
			SendEmailEvent sendEmailEvent = new SendEmailEvent();
			sendEmailEvent.setFromAddress("jims2notification@itc.hctx.net");
			UINotificationHelper.populateSendEmailAddressEvents(sendEmailEvent, user.getEmail());
			//sendEmailEvent.addToAddress("Dustin.Nguyen@us.hctx.net");
			sendEmailEvent.setSubject("Dual Status - Youth is in Detention, Juvenile: " + juvenileNum + ", " + juvenileName);
			sendEmailEvent.setMessage("This email serves as notice of a Dual Status youth who is actively booked in Detention.");
			dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatch.postEvent(sendEmailEvent);
		    }
		}
	    }
	}
    }

    /*
    * (non-Javadoc)
    * 
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */
    protected void addButtonMapping(Map keyMap)
    {

	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.finish", "finish");
	keyMap.put("button.generateDraft", "generateDraft");
	keyMap.put("button.generateFinal", "generateFinal");
	keyMap.put("button.searchFacilityAdmissions", "searchFacility");
	keyMap.put("button.casefileDetails", "casefileDetails");
    }

}

package ui.juvenilecase.districtCourtHearings.action;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import messaging.districtCourtHearings.GetJJSAllCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByReferreeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLAncillarySettingDisplayEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import messaging.districtCourtHearings.reply.NumberInquiryResponse;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileCasefileListResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetDetailsbyPetitionNumEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.GetJJSJuvenileInfoEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileInfoResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.GetJOTDataEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingChargeResponseEvent;
import messaging.juvenilewarrant.reply.JuvenileOffenderTrackingResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import messaging.referral.GetJJSOffenseByInvestnoEvent;
import messaging.referral.GetJOTDetailsEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.GetJuvenileJOTPetitionDetailsEvent;
import messaging.referral.GetJuvenileProfileReferralListEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileConstants;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;
import pd.codetable.criminal.JuvenileDispositionCode;
import pd.codetable.criminal.JuvenileOffenseCode;
import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSCourt;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSLastAttorney;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.districtCourtHearings.JJSCLAncillary;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilecase.referral.JJSReferral;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.NumberSuffixUtil;

/**
 * @author sthyagarajan
 */
public class ProcessJuvenileDistrictCourtHearingsAction extends JIMSBaseAction
{

    /**
     * Constructor
     */

    /**
     * @roseuid 467FB5C80014
     */
    public ProcessJuvenileDistrictCourtHearingsAction()
    {

    }

    /**
     * courtMainMenu
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward courtMainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.reset();
	courtHearingForm.setCourtDate(null);
	courtHearingForm.setPagerOffset("0");
	//courtHearingForm.setCourtDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1)); // default it to current date.
	List<JSONObject> countyHolidayList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(countyHolidayList);
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * attorneySettingSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward attorneySettingSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.reset();
	courtHearingForm.setAction("attorneySetting");
	courtHearingForm.setErrMessage(UIConstants.EMPTY_STRING);
	return aMapping.findForward(UIConstants.ATTORNEY_SETTING_SUCCESS);
    }
    /**
     * GALattorneySettingSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward GALattorneySettingSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.reset();
	courtHearingForm.setAction("galattorneySetting");
	courtHearingForm.setErrMessage(UIConstants.EMPTY_STRING);
	return aMapping.findForward(UIConstants.GAL_ATTORNEY_SETTING_SUCCESS);
    }

    /**
     * juvenileSettingSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward juvenileSettingSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.reset();
	courtHearingForm.setAction("juvenileSetting");
	courtHearingForm.setErrMessage(UIConstants.EMPTY_STRING);
	return aMapping.findForward(UIConstants.JUVENILE_SETTING_SUCCESS);
    }

    /**
     * courtDocketDisplay
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward courtDocketDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String courtId = courtHearingForm.getCourtId();// both are required field cannot be null
	String date = courtHearingForm.getCourtDate();// both are required field cannot be null 
	//courtHearingForm.setDktSearchResults(null);
	courtHearingForm.setUpdateFlag(""); //this flag is used to check if the M204 data are already ready for the PDF printing
	courtHearingForm.setCourtIdWithSuffix(NumberSuffixUtil.getSuffixForString(courtId));
	List<String> validCourts = Arrays.asList("300", "313", "314", "315");
	Boolean isDistrictCourt = false;
	List<DocketEventResponseEvent> ancillaryDockets = new ArrayList<DocketEventResponseEvent>();
	List<DocketEventResponseEvent> courtDockets = new ArrayList<DocketEventResponseEvent>();
	List<DocketEventResponseEvent> detentionDockets = new ArrayList<DocketEventResponseEvent>();
	List<DocketEventResponseEvent> deliquencyDockets = new ArrayList<DocketEventResponseEvent>();
	ArrayList<DocketEventResponseEvent> finalDocketList = new ArrayList<DocketEventResponseEvent>();

	for (int i = 0; i < validCourts.size(); i++)
	{
	    if (validCourts.get(i).equalsIgnoreCase(courtId))
	    {
		isDistrictCourt = true;
		break;
	    }
	}
	if (!isDistrictCourt)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Please Enter A Valid District Court Id"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	courtHearingForm.setDisplaying("B"); // defaults to B  Search.

	//Getting ancillary dockets from JJSCLANCILLARY
	GetJJSCLAncillaryCourtByReferreeCourtEvent ancillaryCrtEvt = (GetJJSCLAncillaryCourtByReferreeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYREFERREECOURT);
	ancillaryCrtEvt.setCourtDate(date);
	ancillaryCrtEvt.setCourtId(courtId);
	dispatch.postEvent(ancillaryCrtEvt);
	CompositeResponse compResp = (CompositeResponse) dispatch.getReply();
	List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);
	if (ancillaryDktsResp != null && !ancillaryDktsResp.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> ancillaryDktsRespItr = ancillaryDktsResp.iterator();
	    while (ancillaryDktsRespItr.hasNext())
	    {
		DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) ancillaryDktsRespItr.next();
		// If the court is a district court: The application will only retrieve/display settings without a disposition: 
		//{HearingDisposition} or result {CourtResult/HearingDecision} is null.
		if (docRespEvent != null && docRespEvent.getCourtResult() == null && docRespEvent.getDisposition() == null)
		{
		    if (docRespEvent.getCourtTime() != null)
		    {
			docRespEvent.setCourtTime(docRespEvent.getCourtTime().substring(0, 5));
		    }
		    docRespEvent.setPetitionNumUI(docRespEvent.getPetitionNumber());
		    docRespEvent.setJuvenileName(docRespEvent.getRespondantName());
		    docRespEvent.setPetitionAllegation(docRespEvent.getSettingReason());
		    docRespEvent.setHearingType(docRespEvent.getSettingReason());
		    if (docRespEvent.getHearingTypeDesc() != null && !docRespEvent.getHearingTypeDesc().isEmpty())
		    {
			docRespEvent.setPetitionAllegationDesc(docRespEvent.getHearingTypeDesc());
			docRespEvent.setAllegationDesc(docRespEvent.getHearingTypeDesc());
		    }
		    else
		    {
			docRespEvent.setPetitionAllegationDesc(docRespEvent.getSettingReason());
			docRespEvent.setAllegationDesc(docRespEvent.getSettingReason());
		    }

		    if (docRespEvent.getTypeCase() != null && docRespEvent.getTypeCase().equalsIgnoreCase("C"))
		    {
			docRespEvent.setSetNote("DFPS"); //�DFPS� (TX department of family protective services)
		    }
		    else
			if (docRespEvent.getTypeCase() != null && docRespEvent.getTypeCase().equalsIgnoreCase("P"))
			{
			    docRespEvent.setSetNote("PRIVATE (adoptions, etc.)");
			}
			else
			    if (docRespEvent.getTypeCase() != null && docRespEvent.getTypeCase().equalsIgnoreCase("I"))
			    {
				docRespEvent.setSetNote("IMMIGRATION");
			    }
		    
		    ancillaryDockets.add(docRespEvent);
		}
	    }
	    //sorts in ascending order by Petition Num. //requirement for Standard Docket PDF
	    Collections.sort((List<DocketEventResponseEvent>) ancillaryDockets, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    if (evt1.getPetitionNumber() != null && evt2.getPetitionNumber() != null)
			return evt1.getPetitionNumber().compareTo(evt2.getPetitionNumber());
		    else
			return -1;
		}
	    });
	    finalDocketList.addAll(ancillaryDockets);
	}
	
	//get the detention settings from JJSCLDETENTION table
	GetJJSCLDetentionByRefereeCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByRefereeCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYREFEREECOURT);
	jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(date, DateUtil.DATE_FMT_1));
	jjsdetnCrtEvent.setCourtId(courtId);
	dispatch.postEvent(jjsdetnCrtEvent);
	CompositeResponse respDetentionDkts = (CompositeResponse) dispatch.getReply();
	List<DocketEventResponseEvent> detentionDktRespEvts = MessageUtil.compositeToList(respDetentionDkts, DocketEventResponseEvent.class);

	if (detentionDktRespEvts != null && !detentionDktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> detentionDktRespEvtsItr = detentionDktRespEvts.iterator();
	    while (detentionDktRespEvtsItr.hasNext())
	    {
		DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) detentionDktRespEvtsItr.next();
		if (docRespEvent.getCourtTime() != null)
		{
		    docRespEvent.setCourtTime(docRespEvent.getCourtTime().substring(0, 5));
		}
		//The application will only retrieve/display settings without a disposition: {HearingDisposition} or result {CourtResult/HearingDecision} is null.
		if (docRespEvent != null && docRespEvent.getCourtResult().isEmpty() && docRespEvent.getDisposition() == null)
		{
		    if (docRespEvent.getJuvenileNumber() != null)
		    {
			//docRespEvent.setHearingType(""); //71008 Need it for PDF Print
			// courtJPO logic as per Regina's requirement doc
			// get the JPO ID. //83426 //70836 NM
			String ctAssignJpoId = "";
			JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			if (ref != null)
			{
			    ctAssignJpoId = ref.getCtAssignJPOId();
			}
			if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
			{
			    if (ctAssignJpoId.trim().length() < 4)
			    {
				docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
			    }
			    else
			    {
				docRespEvent.setProbationOfficerCd(ctAssignJpoId);
			    }
			    //officer name for hovering.
			    OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
			    if (respEvent != null)
			    {
				IName name = respEvent.getOfficerName();
				if (name != null)
				{
				    docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
				}
			    }
			}
			else
			{
			    docRespEvent.setProbationOfficerCd("");
			}
			//locate the petition record with the highest sequence number (JUVENILE_PETITION. PetitionSequenceNumber) associated to the Juvenile Number and Referral Number from the setting record.
			//If there is an associated PETITION record, display the petition allegation. //of the highest seq Num 
			List<PetitionResponseEvent> petitionResponses = InterviewHelper.getPetitionsRespForReferral(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			if (petitionResponses != null && !petitionResponses.isEmpty())
			{
			    Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
				@Override
				public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
				{
				    if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
					return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
				    else
					return -1;
				}
			    }));
			    Iterator<PetitionResponseEvent> petitionRespItr = petitionResponses.iterator();
			    while (petitionRespItr.hasNext())
			    {
				PetitionResponseEvent petitionResp = petitionRespItr.next();
				if (petitionResp != null)
				{
				    docRespEvent.setPetitionNumber(petitionResp.getPetitionNum());
				    //docRespEvent.setPetitionAllegation(petitionResp.getPetitionNum());
				    docRespEvent.setPetitionAllegationDesc(petitionResp.getOffenseShortDesc());
				    if (petitionResp.getAmend() != null && !petitionResp.getAmend().isEmpty())
				    {
					docRespEvent.setPetitionNumUI(petitionResp.getAmend());
				    }
				    else
				    {
					docRespEvent.setPetitionNumUI(petitionResp.getPetitionNum());
				    }
				    break;
				}
			    }
			}
			else
			// if there isn�t an associated PETITION record, display the short description of the offense associated to the referral.
			{
			    // get the offense code for that juv,ref and highest seq Num
			    GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
			    getOffenses.setJuvenileNum(docRespEvent.getJuvenileNumber());
			    getOffenses.setReferralNum(docRespEvent.getReferralNum());
			    CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);
			    Collection<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToCollection(replyEvent, JJSOffenseResponseEvent.class);

			    // sorts in descending order by seq num.
			    Collections.sort((List<JJSOffenseResponseEvent>) offenses, new Comparator<JJSOffenseResponseEvent>() {
				@Override
				public int compare(JJSOffenseResponseEvent evt1, JJSOffenseResponseEvent evt2)
				{
				    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
				}
			    });
			    if (offenses != null)
			    {
				while (offenses.iterator().hasNext())
				{
				    JJSOffenseResponseEvent offense = offenses.iterator().next();
				    if (offense != null)
				    {
					docRespEvent.setPetitionAllegation(offense.getOffenseCode());
					docRespEvent.setPetitionAllegationDesc(offense.getOffenseDescription());
					docRespEvent.setPetitionNumUI(docRespEvent.getPetitionNumber());
					break;
				    }
				}
			    }
			}
			/* If the record being processed is a Detention setting {JUVENILE_JJS_DETENTION_HEARING} and the juvenile number is less than seven digits in length, display the juvenile number with the leading characters �J0.�  
			 * Otherwise, list the juvenile number with the leading character �J�.
			 * From Carla: My understanding was that if the youth had a petition number, it should show.  If not, then the juvenile number with the J0 should show.  
			 */
			//Bug# 70573: Changed the code as PDF doesn't need to show the petition Number, but the Juv Num 
			/*if (docRespEvent.getPetitionNumber() == null)
			{
			    if (docRespEvent.getJuvenileNumber().length() < 7)
			    {
				docRespEvent.setPetitionNumUI("J0" + docRespEvent.getJuvenileNumber());
			    }
			    else
			    {
				docRespEvent.setPetitionNumUI("J" + docRespEvent.getJuvenileNumber());
			    }
			}
			if (docRespEvent.getJuvenileNumber().length() < 7)
			{
			    docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
			}
			else
			{
			    docRespEvent.setPetitionNumber("J" + docRespEvent.getJuvenileNumber());
			}*/
			//task 130940- to show J with juvnum only if petitionnum is null.
			if (docRespEvent.getPetitionNumber() == null)
			{
			    if (docRespEvent.getJuvenileNumber().length() < 7)
			    {
				docRespEvent.setPetitionNumUI("J0" + docRespEvent.getJuvenileNumber());
				docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
			    }
			    else
			    {
				docRespEvent.setPetitionNumUI("J" + docRespEvent.getJuvenileNumber());
				docRespEvent.setPetitionNumber("J" + docRespEvent.getJuvenileNumber());
			    }
			}
			
			docRespEvent.setRecType("DELIQUENCY"); //used for sorting in HandleJuvenileDistrictCourtDocket
			detentionDockets.add(docRespEvent);
		    } // juvenile no check
		}
	    }
	    //sorts in ascending order by Petition Num. //No sort order for Detention settings - Requirement
	    /*    Collections.sort((List<DocketEventResponseEvent>) detentionDockets, new Comparator<DocketEventResponseEvent>() {
	    	@Override
	    	public int compare(DocketEventResponseEvent evt1,
	    		DocketEventResponseEvent evt2)
	    	{
	    	    if (evt1.getPetitionNumber() != null && evt2.getPetitionNumber() != null)
	    		return evt1.getPetitionNumber().compareTo(evt2.getPetitionNumber());
	    	    else
	    		return -1;
	    	}
	        });*/
	    finalDocketList.addAll(detentionDockets);
	    deliquencyDockets.addAll(detentionDockets);
	}
	

	//Getting dockets from court Calendar Record: JJSCLCOURT
	GetJJSCLCourtByRefereeCourtEvent jjsCLCrtEvent = (GetJJSCLCourtByRefereeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYREFEREECOURT);
	jjsCLCrtEvent.setCourtDate(date);
	jjsCLCrtEvent.setCourtId(courtId);
	dispatch.postEvent(jjsCLCrtEvent);
	CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	List<DocketEventResponseEvent> dktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
	String preceedingseqNuminStr = "";
	if (dktRespEvts != null && !dktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> dktRespEvtsItr = dktRespEvts.iterator();
	    while (dktRespEvtsItr.hasNext())
	    {
		DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		//* If the court is a district court: The application will only retrieve/display settings without a disposition: 
		//{HearingDisposition} or result {CourtResult/HearingDecision} is null.
		if (docRespEvent != null && docRespEvent.getCourtResult() == null && docRespEvent.getDisposition() == null)
		{
		    if (docRespEvent.getCourtTime() != null)
		    {
			docRespEvent.setCourtTime(docRespEvent.getCourtTime().substring(0, 5));
		    }

		    docRespEvent.setPetitionNumUI(docRespEvent.getPetitionNumber());
		    docRespEvent.setSetNote(docRespEvent.getPrevNotes());
		    //Bug 70599
		    if (docRespEvent.getPetitionStatus().equalsIgnoreCase("R"))
		    {
			docRespEvent.setPetitionStatusCd("REOPEN");
		    }
		    if (docRespEvent.getPetitionAmendment() != null && !docRespEvent.getPetitionAmendment().isEmpty())
		    {
			docRespEvent.setAmendment(NumberSuffixUtil.getSuffixForString(docRespEvent.getPetitionAmendment()) + " AMENDMENT");
		    }

		    docRespEvent.setPetitionAllegation(docRespEvent.getAllegation());//as per assignment in JJSCourt value object
		    if (docRespEvent.getAllegation() != null && !docRespEvent.getAllegation().isEmpty())
		    {
			JuvenileOffenseCode juvOffenseCode = JuvenileOffenseCode.find("offenseCode", docRespEvent.getAllegation());
			if (juvOffenseCode != null)
			{
			    docRespEvent.setPetitionAllegationDesc(juvOffenseCode.getShortDescription());//for hovering
			    docRespEvent.setAllegationDesc(juvOffenseCode.getShortDescription());//for the allegation field on the PDF
			    docRespEvent.setOffenseCode(juvOffenseCode.getCategory());//for PDF CLS field
			}
		    }
		    
		    //US 174588
		    String TRNDSP = "TRNDSP";
		    if(docRespEvent.getPetitionAllegation() != null && !"".equals(docRespEvent.getPetitionAllegation())){
			
			if(docRespEvent.getPetitionAllegation().equalsIgnoreCase(TRNDSP)){ //"TRNDSP" = TRANSFER FOR DISPOSITION,
			    
			    docRespEvent = this.getCourtTransferOffenseAndCategory(docRespEvent, TRNDSP);
			}
		    }	
		    

		    //US 174589
		    String TRNSIN = "TRNSIN";
		    if(docRespEvent.getPetitionAllegation() != null && !"".equals(docRespEvent.getPetitionAllegation())){
			
			if(docRespEvent.getPetitionAllegation().equalsIgnoreCase(TRNSIN)){ 
			    
			    docRespEvent = this.getCourtTransferOffenseAndCategory(docRespEvent, TRNSIN);
			}
		    }
		    
		    //US 174590	- VOP	    
		    docRespEvent = this.getOffenseAndCategoryByHearingTypeVOP(docRespEvent, "VP", docRespEvent.getJuvenileNumber());
		    docRespEvent = this.getOffenseAndCategoryByHearingTypeVOP(docRespEvent, "VI", docRespEvent.getJuvenileNumber());		    
		    

		    //If the record being processed is a Court setting {JUVENILE_ DISTRICT_COURT_DOCKET}, retrieve all Court calendar records associated to the same ChainNumber. 
		    //If other Calendar records are located, sort the records in descending order by ChainSequenceNumber.  
		    //Display the HearingDate of the record immediately preceding the current record.
		    if (docRespEvent.getSeqNum() != null)
		    {
			preceedingseqNuminStr = Integer.toString(Integer.valueOf(docRespEvent.getSeqNum()) - 10);
		    }
		    //retrieve all Court calendar records associated to the same ChainNumber.
		    Iterator<JJSCourt> courtSettingItr = JJSCourt.findAll("chainNumber", docRespEvent.getChainNumber());
		    List<DocketEventResponseEvent> docketsOfSameChainNum = new ArrayList<DocketEventResponseEvent>();
		    while (courtSettingItr.hasNext())
		    {
			JJSCourt districtCrt = (JJSCourt) courtSettingItr.next();
			if (districtCrt != null)
			{
			    DocketEventResponseEvent respTemp = districtCrt.valueObject();
			    if (resp != null)
			    {
				docketsOfSameChainNum.add(respTemp);
			    }
			}
		    }
		    //If other Calendar records are located, sort the records in descending order by ChainSequenceNumber.
		    if (docketsOfSameChainNum.size() > 1)
		    {
			// sort by highest sequence number
			Collections.sort((List<DocketEventResponseEvent>) docketsOfSameChainNum, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
			    @Override
			    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
			    {
				if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
				    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
				else
				    return -1;
			    }
			}));
			//Display the HearingDate of the record immediately preceding the current record.
			Iterator<DocketEventResponseEvent> docketRespItr = docketsOfSameChainNum.iterator();
			while (docketRespItr.hasNext())
			{
			    DocketEventResponseEvent respEvt = (DocketEventResponseEvent) docketRespItr.next();
			    if (respEvt != null)
			    {
				if (preceedingseqNuminStr.equals(respEvt.getSeqNum()))
				{
				    docRespEvent.setPrevHearingDate(respEvt.getCourtDate());
				    break;
				}
			    }
			}
		    } //end of if more than one docket of the same chain num
		      // get the COURT JPO ID.
		      // get the JPO ID. //83426 //70836 NM
		    String ctAssignJpoId = "";
		    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
		    if (ref != null)
		    {
			ctAssignJpoId = ref.getCtAssignJPOId();
		    }
		    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
		    {
			if (ctAssignJpoId.trim().length() < 4)
			{
			    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
			}
			else
			{
			    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
			}
			//officer name for hovering.
			OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
			if (respEvent != null)
			{
			    IName name = respEvent.getOfficerName();
			    if (name != null)
			    {
				docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
			    }
			}
		    }
		    else
		    {
			docRespEvent.setProbationOfficerCd("");
		    }
		    docRespEvent.setRecType("deliquency");
		    courtDockets.add(docRespEvent);
		}
	    }
	    //sorts in ascending order by Petition Num. //requirement for Standard Docket PDF
	    Collections.sort((List<DocketEventResponseEvent>) courtDockets, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    if (evt1.getPetitionNumber() != null && evt2.getPetitionNumber() != null)
			return evt1.getPetitionNumber().compareTo(evt2.getPetitionNumber());
		    else
			return -1;
		}
	    });
	    finalDocketList.addAll(courtDockets);
	    deliquencyDockets.addAll(courtDockets);
	}

	courtHearingForm.setDktSearchResults(finalDocketList);
	courtHearingForm.setAncillaryDockets(ancillaryDockets);
	courtHearingForm.setAllDockets(finalDocketList);
	courtHearingForm.setDelinquencyDockets(deliquencyDockets);
	if (ancillaryDktsResp.isEmpty() && detentionDktRespEvts.isEmpty() && dktRespEvts.isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO HEARINGS FOR DATE/COURT ENTERED"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	if (courtHearingForm.getDktSearchResults() == null || (courtHearingForm.getDktSearchResults() != null && courtHearingForm.getDktSearchResults().isEmpty()))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO CALENDER RECORD FOUND, CHECK COURT ID AND DATE"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	return aMapping.findForward(UIConstants.COURT_DOCKET_DISPLAY_PRINT);
    }
    
    public DocketEventResponseEvent getCourtTransferOffenseAndCategory(DocketEventResponseEvent docRespEvent, String petitionAllegation){
	
	if(docRespEvent.getPetitionAllegation() != null && !"".equals(docRespEvent.getPetitionAllegation()) && petitionAllegation != null && !"".equals(petitionAllegation)){
		
		if(docRespEvent.getPetitionAllegation().equalsIgnoreCase(petitionAllegation)){ 
		    
		    List<JuvenileCasefileTransferredOffenseResponseEvent> juvTransferredOffenceList = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(docRespEvent.getJuvenileNumber());
		    for(int i = 0; i < juvTransferredOffenceList.size(); i++){
			JuvenileCasefileTransferredOffenseResponseEvent tOffense = juvTransferredOffenceList.get(i);
			
			if(docRespEvent.getReferralNum() != null && docRespEvent.getReferralNum().equalsIgnoreCase(tOffense.getReferralNum())
				&& docRespEvent.getPetitionStatusCd() != null &&  docRespEvent.getPetitionStatusCd().equalsIgnoreCase("F")){
			    
			    docRespEvent.setOffenseCode(tOffense.getOffenseCode());	
			    docRespEvent.setOffenseCategory(tOffense.getCategory());
			    break;
			}
		    }
		}
	    }
	
	return docRespEvent;
	
    }
    
    public DocketEventResponseEvent getOffenseAndCategoryByHearingTypeVOP(DocketEventResponseEvent docRespEvent, String hearingType, String JuvenileNumber){
	
	if(docRespEvent.getHearingType() != null && !"".equals(docRespEvent.getHearingType()) && hearingType != null && !"".equals(hearingType)){
		
		if(docRespEvent.getHearingType().equalsIgnoreCase(hearingType)){ 
		    
            	    List<PetitionResponseEvent> petitionResponseEvents = InterviewHelper.getPetitionsForJuv(docRespEvent.getJuvenileNumber());
            	    
            	    Iterator<PetitionResponseEvent> petitionEventsIter = petitionResponseEvents.iterator();
            	    
            	    while(petitionEventsIter.hasNext())
            	    {          		
            		PetitionResponseEvent respEvent = (PetitionResponseEvent)petitionEventsIter.next();
            		
            		if(respEvent.getPetitionNum() != null && docRespEvent.getPetitionNumber() != null){
            		    
            		    if(respEvent.getPetitionNum().equalsIgnoreCase(docRespEvent.getPetitionNumber()) && 
            			    respEvent.getPetitionStatus() != null && respEvent.getPetitionStatus().equalsIgnoreCase("F")){
            			
            			if(respEvent.getOffenseCodeId() != null && !"".equals(respEvent.getOffenseCodeId())){
            			    
                    			JuvenileOffenseCode juvOffenseCode = JuvenileOffenseCode.find("offenseCode", respEvent.getOffenseCodeId());
                			if (juvOffenseCode != null)
                			{
                			    docRespEvent.setOffenseCode(respEvent.getOffenseCodeId());
                			    docRespEvent.setOffenseCategory(juvOffenseCode.getCategory());
                			}
            			}
            			
        			
        			break;
            		    }
            		}
            	    }
		    		
		}
		
	}
	
	return docRespEvent;
	
    }

    /**
     * addAncillarySetting
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAncillarySetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String courtId = courtHearingForm.getCourtId();
	String crtDate = courtHearingForm.getCourtDate();
	List<JSONObject> hearingTypesList = JuvenileDistrictCourtHelper.activeHearingTypesWithCatIndAB();
	courtHearingForm.setHearingTypesList(hearingTypesList);
	courtHearingForm.reset(); //clear all other fields.
	courtHearingForm.setCourtId(courtId);
	courtHearingForm.setCourtDate(crtDate);
	courtHearingForm.setAction("ancillarySetting");
	courtHearingForm.setTypeCase(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.TYPE_CASE, true));
	courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
	//courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadActiveHearingTypesWithCatIndAB()); //commented for US 157620
	courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesWithCatIndABonlyActive()); 
	return aMapping.findForward(UIConstants.ANCILLARY_SETTING_ADD);
    }

    /**
     * courtAction
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward courtAction(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setAction("submitCourtAction");
	courtHearingForm.setDktSearchResults(Collections.EMPTY_LIST); //empty the list

	String courtId = courtHearingForm.getCourtId();
	String date = courtHearingForm.getCourtDate();

	List<DocketEventResponseEvent> dockets = null;
	Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	Map<String, DocketEventResponseEvent> originalDocketsMap = new HashMap<String, DocketEventResponseEvent>();

	// check if it is a valid court Id's
	if (courtId != null && !courtId.isEmpty())
	{
	    //Settings scheduled in Juvenile Referee courts cannot be retrieved using this option.
	    if (courtId.equals("001"))
	    {
		courtId = "1";
	    }
	    else
	    {
		if (courtId.equals("002"))
		{
		    courtId = "2";
		}
	    }
	    //CODETABLE
	    GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
	    courtEvent.setCourt(courtId);
	    CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
	    JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);

	    /*
	     * If the court is a detention court: the application will default to the search detailed in the Process Juvenile Detention Hearings.doc
	     */
	    if (crtRespEvt != null)
	    {
		if (crtRespEvt.getRefereesCourtInd() != null && crtRespEvt.getRefereesCourtInd().equals("Y"))
		{
		    courtHearingForm.setAction("error");
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Referee Court Not Valid For This Option, Please Correct And Retry"));
		    saveErrors(aRequest, errors);
		    courtHearingForm.setCursorPosition("courtId");
		    return aMapping.findForward(UIConstants.SUCCESS);
		}
	    }
	    //task 174215 for performance improvement with single fetch for all tables	    
	    List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
	    courtHearingForm.setCourtDecisionsResponses(juvenileDispositions);
	    List<JSONObject> districtcourtDecisionsList = JuvenileDistrictCourtHelper.districtcourtDecisionResponses(juvenileDispositions);
	    courtHearingForm.setDistrictcourtDecisionsList(districtcourtDecisionsList);
	    courtHearingForm.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
	    
	    Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();
	    List<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = JuvenileCaseHelper.getCourtDecisionsNew(); 

	    GetJJSAllCourtByRefereeCourtEvent jjsCLCrtEvent = (GetJJSAllCourtByRefereeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSALLCOURTBYREFEREECOURT);
	    jjsCLCrtEvent.setCourtDate(date);
	    jjsCLCrtEvent.setCourtId(courtId);
	    CompositeResponse resp = MessageUtil.postRequest(jjsCLCrtEvent);
	    List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

	    if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			if (docRespEvent.getRecType().equalsIgnoreCase("COURT")||docRespEvent.getRecType().equalsIgnoreCase("I.COURT")||docRespEvent.getRecType().equalsIgnoreCase("S.COURT"))
			{
				
        			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
        			{
        			    if (juvenileDispositions != null)
        			    {
        				Iterator<JuvenileDispositionCodeResponseEvent> juvDispItr = juvenileDispositions.iterator();
        				while (juvDispItr.hasNext())
        				{
        				    JuvenileDispositionCodeResponseEvent juvDisp = juvDispItr.next();
        				    if (juvDisp != null && juvDisp.getCodeAlpha() != null && juvDisp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && juvDisp.getOptionCode() != null && juvDisp.getOptionCode().equalsIgnoreCase("R"))
        				    {
        					GetProductionSupportCourtRecordsEvent courtEvt = (GetProductionSupportCourtRecordsEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);//reusing the existing mapping by paasing juvnum and chainnum to jjclcourt
        
        					courtEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
        					courtEvt.setChainNumber(docRespEvent.getChainNumber());
        
        					dispatch.postEvent(courtEvt);
        
        					CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
        					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
        					if (docketResponses != null && !docketResponses.isEmpty())
        					{
        					    // sort Seq Number asc
        					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
        						@Override
        						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
        						{
        						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
        							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
        						    else
        							return -1;
        						}
        					    });
        					}
        
        					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
        					int currentSeqnum = Integer.valueOf(docRespEvent.getSeqNum());
        					while (docketResponsesItr.hasNext())
        					{
        					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
        					    int nextSeqnum = Integer.valueOf(docResp.getSeqNum());
        					    if (docResp != null && nextSeqnum > currentSeqnum)
        					    {
        						docRespEvent.setActionDate(docResp.getCourtDate());
        						docRespEvent.setOldactionDate(docResp.getCourtDate());
        						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
        						docRespEvent.setOldactionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
        						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
        						break;
        					    }
        					}
        				    }
        				}//while end 
        			    }
        			}
        			else
        			{
        			    docRespEvent.setActionDate("");
        			    docRespEvent.setActionTime("");
        			}
        			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
        			{
        			    // Get petition
        			    List<PetitionResponseEvent> petitionResponses = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
        			    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
        			    if (petitionIter != null && petitionIter.hasNext())
        			    {
        				PetitionResponseEvent petition = petitionIter.next();
        				if (petition != null)
        				{
        				    if (petition.getPetitionNum() != null)
        				    {
        					docRespEvent.setPetitionNumber(petition.getPetitionNum());
        					docRespEvent.setPetitionStatusCd(petition.getPetitionStatus());
        					if (petition.getPetitionStatus() != null)
        					{
        					    if ("F".equalsIgnoreCase(petition.getPetitionStatus()))
        					    {
        						docRespEvent.setPetitionStatus("FILED");
        					    }
        					    else
        						if ("R".equalsIgnoreCase(petition.getPetitionStatus()))
        						{
        						    docRespEvent.setPetitionStatus("REOPENED");
        						}
        					}
        					docRespEvent.setPetitionAmendment(petition.getAmend());
        					docRespEvent.setPetitionType(petition.getPetitionType());
        					docRespEvent.setPetitionTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PETITION_TYPE, petition.getPetitionType()));
        					docRespEvent.setPetitionAllegation(petition.getOffenseCodeId());
        				    }
        				}
        			    }
        			    // get the JPO ID. //83426
        			    String ctAssignJpoId = "";
        			    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
        			    if (ref != null)
        			    {
        				ctAssignJpoId = ref.getCtAssignJPOId();
        			    }
        			    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
        			    {
        				if (ctAssignJpoId.trim().length() < 4)
        				{
        				    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
        				}
        				else
        				{
        				    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
        				}
        				//officer name for hovering.
        				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
        				if (respEvent != null)
        				{
        				    IName name = respEvent.getOfficerName();
        				    if (name != null)
        				    {
        					docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
        				    }
        				}
        			    }
        			    else
        			    {
        				docRespEvent.setProbationOfficerCd("");
        			    }
        			    //added for CourtAction JuvenileNumber without 0 prefix #100626
        			    if (docRespEvent.getJuvenileNumber() != null)
        			    {
        				docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
        			    }

        			    //pad zero in juv num
        			    if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
        			    {
        				docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
        			    }

        			    //reset Hearing Type
        			    if (docRespEvent.getResetHearingType() == null || (docRespEvent.getResetHearingType() != null && docRespEvent.getResetHearingType().isEmpty()))
        			    {
        				docRespEvent.setResetHearingType(docRespEvent.getHearingType());
        				docRespEvent.setOldresetHearingType(docRespEvent.getHearingType());
        			    }
        			}//end of if (resp.getJuvenileNumber() != null && resp.getReferralNum() != null)
        		
			}//END OF COURT
			if (docRespEvent.getRecType().equalsIgnoreCase("DETENTION")||docRespEvent.getRecType().equalsIgnoreCase("I.DETENTION")||docRespEvent.getRecType().equalsIgnoreCase("S.DETENTION"))
			{
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtDecision != null)
				    {
					//filter
					if (docRespEvent.getHearingType() != null)
					{
					    if (docRespEvent.getHearingType().equalsIgnoreCase("DT"))
					    {
						if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("DT") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
						{
						    courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
						}
					    }
					    else
					    {
						if (docRespEvent.getHearingType().equalsIgnoreCase("PC"))
						{
						    if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("PC") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
						    {
							courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
						    }
						}
					    }
					}
				    }
				}
			    }
			    if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			    {
				//83426
				if (crtDecisions != null)
				{
				    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				    while (crtDecisionsItr.hasNext())
				    {
					JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
					if (crtDecision != null)
					{
					    if (crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
					    {
						docRespEvent.setDisableResetDate("false");
						break;
					    }
					    else
					    {
						docRespEvent.setDisableResetDate("true");
						break;
					    }
					}
				    }
				}
				if (juvenileDispositions != null)
				{
				    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				    while (juvCodeIter.hasNext())
				    {
					JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
					{
					    GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
					    detentionEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					    detentionEvt.setChainNumber(docRespEvent.getChainNumber());

					    dispatch.postEvent(detentionEvt);

					    CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
					    if (docketResponses != null && !docketResponses.isEmpty())
					    {
						// sort Seq Number asc
						Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						    @Override
						    public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						    {
							if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							    return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
							else
							    return -1;
						    }
						});
					    }
					    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					    int currentSeqnum = Integer.valueOf(docRespEvent.getSeqNum());
					    while (docketResponsesItr.hasNext())
					    {
						DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						int nextSeqnum = Integer.valueOf(docResp.getSeqNum());
						if (docResp != null && nextSeqnum > currentSeqnum)
						{
						    docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						    docRespEvent.setActionDate(docResp.getCourtDate());
						    docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						    break;
						}
					    }
					}
				    }
				}
			    }
			    else
			    { //do not set as in m204
				docRespEvent.setActionDate("");
				docRespEvent.setActionTime("");
			    }

			    if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			    {
				//task 130940- to show J with juvnum only if petitionnum is null.
				if (docRespEvent.getPetitionNumber() == null)
				    docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
				// get the JPO ID. //83426
				String ctAssignJpoId = "";
				JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
				if (ref != null)
				{
				    ctAssignJpoId = ref.getCtAssignJPOId();
				}
				if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
				{
				    if (ctAssignJpoId.trim().length() < 4)
				    {
					docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
				    }
				    else
				    {
					docRespEvent.setProbationOfficerCd(ctAssignJpoId);
				    }
				    //officer name for hovering.
				    OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
				    if (respEvent != null)
				    {
					IName name = respEvent.getOfficerName();
					if (name != null)
					{
					    docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
					}
				    }
				}
				else
				{
				    docRespEvent.setProbationOfficerCd("");
				}
				//added for CourtAction JuvenileNumber without 0 prefix #100626
				if (docRespEvent.getJuvenileNumber() != null)
				{
				    docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
				}
				//pad zero in juv num
				if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
				{
				    docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
				}
				//Detention calendar records: Will stay the same as the PetitionType value unless user resets the setting; in which case it will be set to �DT.� 
				//filter
				if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("DT"))
				{
				    docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				    docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
				}
				else
				    if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("PC"))
				    {
					docRespEvent.setResetHearingType(docRespEvent.getHearingType());
					docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
				    }
			    }

			}//DETENTION if end
			 //
			if (docRespEvent.getRecType().equalsIgnoreCase("ANCILLARY")||docRespEvent.getRecType().equalsIgnoreCase("I.ANCILLARY")||docRespEvent.getRecType().equalsIgnoreCase("S.ANCILLARY"))
			{
			    if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			    {

				if (juvenileDispositions != null)
				{
				    Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				    while (juvCodeIter.hasNext())
				    {
					JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
					if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
					{
					    GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
					    ancillaryEvt.setChainNumber(docRespEvent.getChainNumber());
					    ancillaryEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					    dispatch.postEvent(ancillaryEvt);

					    CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();
					    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
					    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					    while (docketResponsesItr.hasNext())
					    {

						DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						if (docResp != null)
						{
						    docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						    docRespEvent.setActionDate(docResp.getCourtDate());
						    docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						    break;
						}
					    }
					}
				    }
				}
			    }
			    else
			    {
				docRespEvent.setActionDate("");
				docRespEvent.setActionTime("");
			    }
			}

			//
			//only this if above can be moved to command
			/*if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{*/
			docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);

			// add it to the new object to maintain originality
			DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			try
			{
			    //ConvertUtils.register(new DateConverter(null), Date.class);
			    Date defaultValue = null;
			    Converter converter = new DateConverter(defaultValue);
			    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			    beanUtilsBean.getConvertUtils().register(converter, Date.class);
			    BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			    BeanUtils.copyProperties(origDocket, docRespEvent);
			}
			catch (IllegalAccessException e)
			{
			    e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
			    e.printStackTrace();
			}
			originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map

			//} ///end of if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
		    }
		}
	    }
	    

	    courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
	    List<JuvenileCourtDecisionCodeResponseEvent> courtDecisionResponseEvts = new ArrayList(courtDecisionsMap.values());
	    Collections.sort(courtDecisionResponseEvts, new Comparator<JuvenileCourtDecisionCodeResponseEvent>() {
		@Override
		public int compare(JuvenileCourtDecisionCodeResponseEvent evt1, JuvenileCourtDecisionCodeResponseEvent evt2)
		{
		    if (evt1.getCode() != null && evt2.getCode() != null)
			return evt1.getCode().compareTo(evt2.getCode());
		    else
			return -1;
		}
	    });
	    List<JSONObject> courtDecisionsList = JuvenileDistrictCourtHelper.courtDecisionResponses(courtDecisionResponseEvts);
	    courtHearingForm.setCourtDecisionsList(courtDecisionsList);//add for district dropdown too
	    courtHearingForm.setDetentionHearingResults(courtDecisionResponseEvts);	    
	    courtHearingForm.setAncillaryHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("ANCILLARY"));
	    
	    // commented below as part of task 174215 performance improvement and replaced with single fetch for all tables above
	    //83426
	    /*List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
	    //List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getCourtDispositionCodeTypeDorB();
	    courtHearingForm.setCourtDecisionsResponses(juvenileDispositions);
	    List<JSONObject> districtcourtDecisionsList = JuvenileDistrictCourtHelper.districtcourtDecisionResponses(juvenileDispositions);
	    courtHearingForm.setDistrictcourtDecisionsList(districtcourtDecisionsList);
	    //court calendar record. JJSCLCOURT
	    GetJJSCLCourtByRefereeCourtEvent jjsCLCrtEvent = (GetJJSCLCourtByRefereeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYREFEREECOURT);
	    jjsCLCrtEvent.setCourtDate(date);
	    jjsCLCrtEvent.setCourtId(courtId);
	    dispatch.postEvent(jjsCLCrtEvent);
	    CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> crtDktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);

	    if (crtDktRespEvts != null && !crtDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvts.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			
			//set Court Date and time
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    // court Decision Code table.83426
			    // GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //  courtDisp.setCode(docRespEvent.getCourtResult());
			    //    CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //   Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvDispItr = juvenileDispositions.iterator();
				while (juvDispItr.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent juvDisp = juvDispItr.next();

				    if (juvDisp != null && juvDisp.getCodeAlpha() != null && juvDisp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && juvDisp.getOptionCode() != null && juvDisp.getOptionCode().equalsIgnoreCase("R"))
				    {
					GetJJSCLCourtByChainAndSeqNumEvent courtEvt = (GetJJSCLCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYCHAINANDSEQNUM);
					courtEvt.setChainNumber(docRespEvent.getChainNumber());
					courtEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					 GetProductionSupportCourtRecordsEvent courtEvt = (GetProductionSupportCourtRecordsEvent)
    							EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCOURTRECORDS);//reusing the existing mapping by paasing juvnum and chainnum to jjclcourt

					 courtEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					 courtEvt.setChainNumber(docRespEvent.getChainNumber());
					
					dispatch.postEvent(courtEvt);

					CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
					if (docketResponses != null && !docketResponses.isEmpty())
					{
					 // sort Seq Number asc
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						{
						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
						    else
							return -1;
						}
					    });
					}					
					
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					int currentSeqnum=Integer.valueOf(docRespEvent.getSeqNum());
					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    int nextSeqnum=Integer.valueOf(docResp.getSeqNum());
					    if (docResp != null && nextSeqnum > currentSeqnum)
					    {
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setOldactionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOldactionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						break;
					    }
					}
				    }
				}// while
			    }
			}
			else
			{
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			}

			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{
			    // Get petition
			    List<PetitionResponseEvent> petitionResponses = JuvenileDistrictCourtHelper.getPetitionsByJuvAndRefNum(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
			    if (petitionIter != null && petitionIter.hasNext())
			    {
				PetitionResponseEvent petition = petitionIter.next();
				if (petition != null)
				{
				    if (petition.getPetitionNum() != null)
				    {
					docRespEvent.setPetitionNumber(petition.getPetitionNum());
					docRespEvent.setPetitionStatusCd(petition.getPetitionStatus());
					if (petition.getPetitionStatus() != null)
					{
					    if ("F".equalsIgnoreCase(petition.getPetitionStatus()))
					    {
						docRespEvent.setPetitionStatus("FILED");
					    }
					    else
						if ("R".equalsIgnoreCase(petition.getPetitionStatus()))
						{
						    docRespEvent.setPetitionStatus("REOPENED");
						}
					}
					docRespEvent.setPetitionAmendment(petition.getAmend());
					docRespEvent.setPetitionType(petition.getPetitionType());
					docRespEvent.setPetitionTypeDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.PETITION_TYPE, petition.getPetitionType()));
					docRespEvent.setPetitionAllegation(petition.getOffenseCodeId());
				    }
				}
			    }
			    // get the JPO ID. //83426
			    String ctAssignJpoId = "";
			    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if (ref != null)
			    {
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
			    {
				if (ctAssignJpoId.trim().length() < 4)
				{
				    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
				}
				else
				{
				    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
				}
				//officer name for hovering.
				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
				if (respEvent != null)
				{
				    IName name = respEvent.getOfficerName();
				    if (name != null)
				    {
					docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
				    }
				}
			    }
			    else
			    {
				docRespEvent.setProbationOfficerCd("");
			    }
			    //added for CourtAction JuvenileNumber without 0 prefix #100626
			    if (docRespEvent.getJuvenileNumber() != null)
			    {	
				docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			    }
			    
			    //pad zero in juv num
			    if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			    {	
				docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			    }

			    //reset Hearing Type
			    if (docRespEvent.getResetHearingType() == null || (docRespEvent.getResetHearingType() != null && docRespEvent.getResetHearingType().isEmpty()))
			    {
				docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				docRespEvent.setOldresetHearingType(docRespEvent.getHearingType());
			    }
			    docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);

			    // add it to the new object to maintain originality
			    DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			    try
			    {
				//ConvertUtils.register(new DateConverter(null), Date.class);
				java.util.Date defaultValue = null;
				Converter converter = new DateConverter(defaultValue);
				BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
				beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
				BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
				BeanUtils.copyProperties(origDocket, docRespEvent);
			    }
			    catch (IllegalAccessException e)
			    {
				e.printStackTrace();
			    }
			    catch (InvocationTargetException e)
			    {
				e.printStackTrace();
			    }
			    originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			    // add it to the new object to maintain originality
			} // juvenile no check
		    }
		}
		courtHearingForm.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
		//courtHearingForm.setCourtDecisionsList(courtDecisionsList)
	    }

	    //GET JJSCLDETENTION RECORDS
	    Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();
	    GetJJSCLDetentionByRefereeCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByRefereeCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYREFEREECOURT);
	    jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1));
	    jjsdetnCrtEvent.setCourtId(courtId);
	    dispatch.postEvent(jjsdetnCrtEvent);
	    CompositeResponse clDetnResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> clDetnDktRespEvts = MessageUtil.compositeToList(clDetnResp, DocketEventResponseEvent.class);

	    //pre-load new court decision code table //83426
	    List<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = JuvenileCaseHelper.getCourtDecisionsNew();

	    if (clDetnDktRespEvts != null && !clDetnDktRespEvts.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> clDetnDktRespItr = clDetnDktRespEvts.iterator();
		while (clDetnDktRespItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
		    if (docRespEvent != null)
		    {
			//83426
			//disable reset Date when the required Reset is NO
			//GetJuvenileCourtDecisionCodesEvent courtDecision = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			//Collection<JuvenileCourtDecisionCodeResponseEvent> crtDecisions = MessageUtil.postRequestListFilter(courtDecision, JuvenileCourtDecisionCodeResponseEvent.class);
			if (crtDecisions != null)
			{
			    Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
			    while (crtDecisionsItr.hasNext())
			    {
				JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				if (crtDecision != null)
				{
				    //filter
				    if (docRespEvent.getHearingType() != null)
				    {
					if (docRespEvent.getHearingType().equalsIgnoreCase("DT"))
					{
					    if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("DT") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
					    {
						courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
					    }
					}
					else
					{
					    if (docRespEvent.getHearingType().equalsIgnoreCase("PC"))
					    {
						if (crtDecision.getDecision() != null && crtDecision.getDecision().equalsIgnoreCase("PC") || crtDecision.getDecision().equalsIgnoreCase("BOTH"))
						{
						    courtDecisionsMap.put(crtDecision.getCode(), crtDecision);
						}
					    }
					}
				    }
				}
			    }
			}

			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			//set Court Date and time
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    //83426
			    // GetJuvenileCourtDecisionCodesEvent courtDecsn = (GetJuvenileCourtDecisionCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTDECISIONCODES);
			    //  courtDecsn.setCode(docRespEvent.getCourtResult());
			    //  Collection<JuvenileCourtDecisionCodeResponseEvent> crtDescisions = MessageUtil.postRequestListFilter(courtDecsn, JuvenileCourtDecisionCodeResponseEvent.class);
			    if (crtDecisions != null)
			    {
				Iterator<JuvenileCourtDecisionCodeResponseEvent> crtDecisionsItr = crtDecisions.iterator();
				while (crtDecisionsItr.hasNext())
				{
				    JuvenileCourtDecisionCodeResponseEvent crtDecision = (JuvenileCourtDecisionCodeResponseEvent) crtDecisionsItr.next();
				    if (crtDecision != null)
				    {
					if (crtDecision.getResetAllowed().equalsIgnoreCase("Y") || crtDecision.getResetAllowed().equalsIgnoreCase("R"))
					{
					    docRespEvent.setDisableResetDate("false");
					    break;
					}
					else
					{
					    docRespEvent.setDisableResetDate("true");
					    break;
					}
				    }
				}
			    }
			    // court Decision Code table.
			    //83426
			    //   GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //    courtDisp.setCode(docRespEvent.getCourtResult());
			    //    CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //    Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
				    {
					GetJJSCLDetentionByChainAndSeqNumEvent detentionEvt = (GetJJSCLDetentionByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAINANDSEQNUM);
					GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent detentionEvt = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
					detentionEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
					detentionEvt.setChainNumber(docRespEvent.getChainNumber());					
					
					dispatch.postEvent(detentionEvt);

					CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
					if (docketResponses != null && !docketResponses.isEmpty())
					{
					 // sort Seq Number asc
					    Collections.sort((List<DocketEventResponseEvent>) docketResponses, new Comparator<DocketEventResponseEvent>() {
						@Override
						public int compare(DocketEventResponseEvent crt1, DocketEventResponseEvent crt2)
						{
						    if (crt1.getSeqNum() != null && crt2.getSeqNum() != null)
							return Integer.valueOf(crt1.getSeqNum()).compareTo(Integer.valueOf(crt2.getSeqNum()));
						    else
							return -1;
						}
					    });
					}	
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();
					int currentSeqnum=Integer.valueOf(docRespEvent.getSeqNum());
					while (docketResponsesItr.hasNext())
					{
					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    int nextSeqnum=Integer.valueOf(docResp.getSeqNum());
					    if (docResp != null && nextSeqnum > currentSeqnum)
					    {
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						break;
					    }
					}
				    }
				}
			    }
			}
			else
			{ //do not set as in m204
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			}

			if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			{
			  //task 130940- to show J with juvnum only if petitionnum is null.
			    if (docRespEvent.getPetitionNumber() == null)
			        docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
			    // get the JPO ID. //83426
			    String ctAssignJpoId = "";
			    JJSReferral ref = JuvenileDistrictCourtHelper.getRefInfo(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
			    if (ref != null)
			    {
				ctAssignJpoId = ref.getCtAssignJPOId();
			    }
			    if (ctAssignJpoId != null && !ctAssignJpoId.isEmpty())
			    {
				if (ctAssignJpoId.trim().length() < 4)
				{
				    docRespEvent.setProbationOfficerCd("UV" + ctAssignJpoId);
				}
				else
				{
				    docRespEvent.setProbationOfficerCd(ctAssignJpoId);
				}
				//officer name for hovering.
				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(docRespEvent.getProbationOfficerCd());
				if (respEvent != null)
				{
				    IName name = respEvent.getOfficerName();
				    if (name != null)
				    {
					docRespEvent.setProbationOfficer(respEvent.getOfficerName().getFormattedName());
				    }
				}
			    }
			    else
			    {
				docRespEvent.setProbationOfficerCd("");
			    }
			    //added for CourtAction JuvenileNumber without 0 prefix #100626
			    if (docRespEvent.getJuvenileNumber() != null)
			    {	
				docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			    }
			    //pad zero in juv num
			    if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			    {
				docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			    }
			    //Detention calendar records: Will stay the same as the PetitionType value unless user resets the setting; in which case it will be set to �DT.� 
			    //filter
			    if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("DT"))
			    {
				docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
			    }
			    else
				if (docRespEvent.getHearingType() != null && docRespEvent.getHearingType().equalsIgnoreCase("PC"))
				{
				    docRespEvent.setResetHearingType(docRespEvent.getHearingType());
				    docRespEvent.setResetHearingTypeDesc(docRespEvent.getHearingTypeDesc());
				}

			    docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);
			    // add it to the new object to maintain originality
			    DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			    try
			    {
				    java.util.Date defaultValue = null;
				    Converter converter = new DateConverter(defaultValue);
				    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
				    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
				    BeanUtils.copyProperties(origDocket, docRespEvent);
			    }
			    catch (IllegalAccessException e)
			    {
				e.printStackTrace();
			    }
			    catch (InvocationTargetException e)
			    {
				e.printStackTrace();
			    }
			    originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			    // add it to the new object to maintain originality
			}
		    }
		}// while

		courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
		List<JuvenileCourtDecisionCodeResponseEvent> courtDecisionResponseEvts = new ArrayList(courtDecisionsMap.values());
		Collections.sort(courtDecisionResponseEvts, new Comparator<JuvenileCourtDecisionCodeResponseEvent>() {
		    @Override
		    public int compare(JuvenileCourtDecisionCodeResponseEvent evt1, JuvenileCourtDecisionCodeResponseEvent evt2)
		    {
			if (evt1.getCode() != null && evt2.getCode() != null)
			    return evt1.getCode().compareTo(evt2.getCode());
			else
			    return -1;
		    }
		});
		List<JSONObject> courtDecisionsList = JuvenileDistrictCourtHelper.courtDecisionResponses(courtDecisionResponseEvts);
		courtHearingForm.setCourtDecisionsList(courtDecisionsList);//add for district dropdown too
		courtHearingForm.setDetentionHearingResults(courtDecisionResponseEvts);
	    } //if
	      //bug:76303

	    // The last Court Date in a regular District Court for which there is no Result or Disposition for the setting. JJSCLANCILLARY
	    GetJJSCLAncillaryCourtByReferreeCourtEvent ancillaryCrtEvt = (GetJJSCLAncillaryCourtByReferreeCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYREFERREECOURT);
	    ancillaryCrtEvt.setCourtDate(date);
	    ancillaryCrtEvt.setCourtId(courtId);
	    dispatch.postEvent(ancillaryCrtEvt);
	    CompositeResponse compResp = (CompositeResponse) dispatch.getReply();
	    List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);
	    if (ancillaryDktsResp != null && !ancillaryDktsResp.isEmpty())
	    {
		Iterator<DocketEventResponseEvent> dktRespEvtsItr = ancillaryDktsResp.iterator();

		while (dktRespEvtsItr.hasNext())
		{
		    DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
		    if (docRespEvent != null)
		    {
			docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
			if (docRespEvent.getCourtResult() != null && !docRespEvent.getCourtResult().isEmpty())
			{
			    //83426
			    // court Decision Code table.
			    //  GetJuvenileDispositionCodeEvent courtDisp = (GetJuvenileDispositionCodeEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODE);
			    //  courtDisp.setCode(docRespEvent.getCourtResult());
			    //  CompositeResponse dispCompResp = postRequestEvent(courtDisp);
			    //  Collection<JuvenileDispositionCodeResponseEvent> juvDisp = MessageUtil.compositeToCollection(dispCompResp, JuvenileDispositionCodeResponseEvent.class);
			    if (juvenileDispositions != null)
			    {
				Iterator<JuvenileDispositionCodeResponseEvent> juvCodeIter = juvenileDispositions.iterator();
				while (juvCodeIter.hasNext())
				{
				    JuvenileDispositionCodeResponseEvent dispResp = juvCodeIter.next();
				    if (dispResp != null && dispResp.getCodeAlpha() != null && dispResp.getCodeAlpha().equalsIgnoreCase(docRespEvent.getCourtResult()) && dispResp.getOptionCode() != null && dispResp.getOptionCode().equals("R"))
				    {
					GetJJSCLAncillaryCourtByChainAndSeqNumEvent ancillaryEvt = (GetJJSCLAncillaryCourtByChainAndSeqNumEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYCOURTBYCHAINANDSEQNUM);
					ancillaryEvt.setChainNumber(docRespEvent.getChainNumber());
					ancillaryEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					dispatch.postEvent(ancillaryEvt);

					CompositeResponse ancillaryResp = (CompositeResponse) dispatch.getReply();
					List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(ancillaryResp, DocketEventResponseEvent.class);
					Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					while (docketResponsesItr.hasNext())
					{

					    DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
					    if (docResp != null)
					    {
						docRespEvent.setOriginalActionDate(docResp.getCourtDate());
						docRespEvent.setActionDate(docResp.getCourtDate());
						docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
						break;
					    }
					}
				    }
				}
			    }
			}
			else
			{
			    docRespEvent.setActionDate("");
			    docRespEvent.setActionTime("");
			} 
			//added for CourtAction JuvenileNumber without 0 prefix #100626
			if (docRespEvent.getJuvenileNumber() != null)
			 {	
			    docRespEvent.setJuvNum(docRespEvent.getJuvenileNumber());
			 }
			//pad zero in juv num
			if (docRespEvent.getJuvenileNumber() != null && !docRespEvent.getJuvenileNumber().substring(0, 1).matches("0"))
			{
			    docRespEvent.setJuvenileNumber("0" + docRespEvent.getJuvenileNumber());
			}
			if (docRespEvent.getSettingReason() != null && !docRespEvent.getSettingReason().isEmpty())
			{
			    docRespEvent.setResetHearingType(docRespEvent.getSettingReason());
			}
			else
			{
			    docRespEvent.setResetHearingType(docRespEvent.getHearingType());
			}

			docketsMap.put(docRespEvent.getDocketEventIdKey(), docRespEvent);
			// add it to the new object to maintain originality
			DocketEventResponseEvent origDocket = new DocketEventResponseEvent();
			try
			{
			    java.util.Date defaultValue = null;
			    Converter converter = new DateConverter(defaultValue);
			    BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
			    beanUtilsBean.getConvertUtils().register(converter, java.util.Date.class);
			    BeanUtils.copyProperties(origDocket, docRespEvent);
			}
			catch (IllegalAccessException e)
			{
			    e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
			    e.printStackTrace();
			}
			originalDocketsMap.put(docRespEvent.getDocketEventIdKey(), origDocket); //original docket map
			// add it to the new object to maintain originality
		    }
		}
		courtHearingForm.setAncillaryHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("ANCILLARY"));
	    }
*/
	    //Using bean comparator. 
	    /* ArrayList<ReverseComparator> sortFields = new ArrayList<ReverseComparator>();
	     sortFields.add(new ReverseComparator(new BeanComparator("issueFlag")));
	     sortFields.add(new ReverseComparator(new BeanComparator("courtTime")));
	     sortFields.add(new ReverseComparator(new BeanComparator("docketType")));
	     sortFields.add(new ReverseComparator(new BeanComparator("petitionNumber")));
	     sortFields.add(new ReverseComparator(new BeanComparator("juvenileNumber")));
	     ComparatorChain multiSort = new ComparatorChain(sortFields);
	     Collections.sort(dockets, multiSort);*/

	    if (!docketsMap.isEmpty())
	    {
		dockets = new ArrayList<DocketEventResponseEvent>(docketsMap.values());
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

		//sort by docket type.
		Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		    public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
		    {
			if (det2.getDocketType() != null && det1.getDocketType() != null)
			    return det2.getDocketType().compareTo(det1.getDocketType());
			else
			    return -1;
		    }
		});

		Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		    @Override
		    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		    {

			int cmp = 0;
			//hearing Type
			if (isEmpty(evt1.getHearingType()))
			{
			    if (isNotEmpty(evt2.getHearingType()))
			    {
				cmp = -1;
				return cmp;
			    }
			    else
			    {
				cmp = 0;
				return cmp;
			    }

			}
			else
			    if (isEmpty(evt2.getHearingType()))
			    {
				cmp = 1;
				return cmp;
			    }
			    else
			    {
				cmp = evt1.getHearingType().compareTo(evt2.getHearingType());
				if (cmp == 0)
				{
				    //court Time
				    if (isEmpty(evt1.getCourtTime()))
				    {
					if (isNotEmpty(evt2.getCourtTime()))
					{
					    cmp = -1;
					    return cmp;
					}
					else
					{
					    cmp = 0;
					    return cmp;
					}
				    }
				    else
					if (isEmpty(evt2.getCourtTime()))
					{
					    cmp = 1;
					    return cmp;
					}
					else
					{
					    cmp = evt1.getCourtTime().compareTo(evt2.getCourtTime());
					    //petition Num
					    if (cmp == 0)
					    {
						if (isEmpty(evt1.getPetitionNumber()))
						{
						    if (isNotEmpty(evt2.getPetitionNumber()))
						    {
							cmp = -1;//(one of them empty)
							return cmp;
						    }
						    else
						    {
							cmp = 0; //(equals)
							return cmp;
						    }
						}
						else
						    if (isEmpty(evt2.getPetitionNumber()))
						    {
							cmp = 1; //second one empty
							return cmp;
						    }
						    else
						    {
							/* String petitionNum1;
							 String petitionNum2;
							 
							 if(evt1.getPetitionNumber().startsWith("J")){
							petitionNum1 = evt1.getPetitionNumber().split("J")[1];
							 }else if(evt1.getPetitionNumber().endsWith("J")){
							petitionNum1 = evt1.getPetitionNumber().split("J")[0];
							 }else if(evt1.getPetitionNumber().contains("-")){
							petitionNum1=evt1.getPetitionNumber().split("-")[0]+evt1.getPetitionNumber().split("-")[1];
							 }else{
							petitionNum1=evt1.getPetitionNumber();
							 }

							 if(evt2.getPetitionNumber().startsWith("J")){
							petitionNum2 = evt2.getPetitionNumber().split("J")[1];
							 }else if(evt2.getPetitionNumber().endsWith("J")){
							petitionNum2 = evt2.getPetitionNumber().split("J")[0];
							 }else if(evt2.getPetitionNumber().contains("-")){
							petitionNum2=evt2.getPetitionNumber().split("-")[0]+evt2.getPetitionNumber().split("-")[1];
							 }else{
							petitionNum2=evt2.getPetitionNumber();
							 }*/
							cmp = evt1.getPetitionNumber().compareTo(evt2.getPetitionNumber());
							if (cmp == 0)
							{
							    if (isEmpty(evt1.getJuvenileNumber()))
							    {
								if (isNotEmpty(evt2.getJuvenileNumber()))
								{
								    cmp = -1;
								    return cmp;
								}
								else
								{
								    cmp = 0;
								    return cmp;
								}
							    }
							    else
								if (isEmpty(evt2.getJuvenileNumber()))
								{
								    cmp = 1;
								    return cmp;
								}
								else
								{
								    cmp = evt1.getJuvenileNumber().compareTo(evt2.getJuvenileNumber());
								}
							}
						    }
					    }
					}
				}
			    } //main else
			return cmp;
		    }
		});

		//courtHearingForm.setOriginalDktSearchResultsMap(originalDocketsMap);
		aRequest.getSession().setAttribute("originalSetting", originalDocketsMap);
		courtHearingForm.setDktSearchResultsMap(docketsMap);
		courtHearingForm.setAllDktSearchResults(dockets);

		List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
		courtHearingForm.setHolidaysList(juvenileDatesFormattedList);

		List<JuvenileDispositionCodeResponseEvent> courtDecisions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
		courtHearingForm.setCourtDecisionsResponses(courtDecisions);
		List<JuvenileDispositionCodeResponseEvent> courtDispDecisions = JuvenileDistrictCourtHelper.getCourtDispositionCodeTypeDorB();//this is the right one
		courtHearingForm.setCourtDispositionResponses(courtDispDecisions);
		List<JuvenileDispositionCodeResponseEvent> courtResultDecisions = JuvenileDistrictCourtHelper.getCourtResultCodeTypeRorB();//this is the right one
		courtHearingForm.setCourtResultResponses(courtResultDecisions);
		//courtHearingForm.setHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes(null));
	    }
	    if (courtHearingForm.getAllDktSearchResults() == null || (courtHearingForm.getAllDktSearchResults() != null && courtHearingForm.getAllDktSearchResults().isEmpty()))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Records Found Please Correct and Retry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		courtHearingForm.setSearchResultSize(dockets.size());
	    }
	}//court Id and date check
	return aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
    }
      

    /**
     * displayAncillarySetting
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward displayAncillarySetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String courtId = courtHearingForm.getCourtId();
	String crtDate = courtHearingForm.getCourtDate();
	courtHearingForm.reset(); //clear all other fields.
	courtHearingForm.setCourtId(courtId);
	courtHearingForm.setCourtDate(crtDate);
	courtHearingForm.setAction("ancillarySettingDisplay");

	GetJJSCLAncillarySettingDisplayEvent ancillarySettingEvt = (GetJJSCLAncillarySettingDisplayEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLANCILLARYSETTINGDISPLAY);
	String courtDate = courtHearingForm.getCourtDate();
	Date cortDate = DateUtil.stringToDate(courtDate, DateUtil.DATE_FMT_1);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String formattedCourtDate = sdf.format(cortDate);
	ancillarySettingEvt.setCourtDate(formattedCourtDate);
	ancillarySettingEvt.setCourtId(courtHearingForm.getCourtId());
	CompositeResponse compResp = MessageUtil.postRequest(ancillarySettingEvt);

	List<DocketEventResponseEvent> ancillaryDktsResp = MessageUtil.compositeToList(compResp, DocketEventResponseEvent.class);

	if (ancillaryDktsResp.size() == 0)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Record Not Found In Calendar File - Check Court And Date"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    courtHearingForm.setDktSearchResults(ancillaryDktsResp);
	    return aMapping.findForward(UIConstants.ANCILLARY_SETTING_DISPLAY);
	}
    }

    /**
     * initialSetting
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward initialSetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String juvenileNum = courtHearingForm.getJuvenileNumber();
	String referralNumber = courtHearingForm.getReferralNumber();
	courtHearingForm.reset(); //clear all other fields.

	//reset the juv and ref num
	courtHearingForm.setAction("initialSetting");
	courtHearingForm.setJuvenileNumber(juvenileNum);
	courtHearingForm.setReferralNumber(referralNumber);
	//bug fix for 99947
	courtHearingForm.setActionMessage("");
	//

	List<PetitionResponseEvent> petitionResponses = null;

	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	reqProfileMain.setJuvenileNum(juvenileNum);
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	if (juvProfile == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  record not found select another option"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
	    courtHearingForm.setJuvenileName(name.getFormattedName());
	}

	// Get Juvenile Profile Information
	courtHearingForm.setProfileDetail(juvProfile);
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	if (facilityHeaderResp != null)
	{

	    //pull detention facility from jjs detention.Bug fix #57130 
	    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvenileNum);
	    courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
	    courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));

	}

	// get referral details: //83426
	JJSReferral profileRefEvent = JuvenileDistrictCourtHelper.getRefInfo(juvenileNum, referralNumber);//JuvenileFacilityHelper.getJuvReferralDetails(juvenileNum, referralNumber);
	if (profileRefEvent != null)
	{
	    courtHearingForm.setReferralDate(DateUtil.dateToString(profileRefEvent.getReferralDate(), DateUtil.DATE_FMT_1));// set referral date for the filing date validation.
	    courtHearingForm.setDaLogNum(profileRefEvent.getDaLogNum());
	    if (profileRefEvent.getCloseDate() == null)
	    {
		petitionResponses = InterviewHelper.getPetitionsRespForReferral(juvenileNum, referralNumber);
	    }
	    else
	    {
		/*
		 * If the CloseDate variable on the Referral record is
		 * populated, display �INITIAL SETTING NOT LOGICAL FOR THIS
		 * REFERRAL- REFERRAL CLOSED ON {CloseDate}.� User cannot modify
		 * or populate any field. Set PROT.IND (temp) = �X.�
		 */
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Initial Setting not logical for this referral - referral closed on " + DateUtil.dateToString(profileRefEvent.getCloseDate(), DateUtil.DATE_FMT_1)));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    
	    if (profileRefEvent.getDaLogNum() != null){
		//task 149494
		GetJuvenileJOTPetitionDetailsEvent jotpetEvent =
			(GetJuvenileJOTPetitionDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJUVENILEJOTPETITIONDETAILS);
	    jotpetEvent.setDalogNum(profileRefEvent.getDaLogNum());
	    jotpetEvent.setReferralNum(profileRefEvent.getReferralNum());
	    CompositeResponse jotpetResponse = MessageUtil.postRequest(jotpetEvent);
	    JuvenileOffenderTrackingChargeResponseEvent resp = (JuvenileOffenderTrackingChargeResponseEvent) MessageUtil.filterComposite(jotpetResponse,
		    JuvenileOffenderTrackingChargeResponseEvent.class);
	    //PetitionResponseEvent juvenileOffenderTrackingChargeResponse = (PetitionResponseEvent) MessageUtil.filterComposite(jotResponse, PetitionResponseEvent.class);
	    if ( resp  != null ){
		try 
		{
		    courtHearingForm.setJotPetitionnum(resp.getPetitionNum());	
		    //add cjis and rejected indicator as status
		} 
		catch (Exception e){}
	    }
	    else 
		courtHearingForm.setJotPetitionnum(null);
	    //
	    /*GetJOTDataEvent jotEvent =
			(GetJOTDataEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJOTDATA);*/
	  //jotEvent.setDaLogNum(resp.getDaLogNum());
	    GetJOTDetailsEvent jotEvent =
    		(GetJOTDetailsEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJOTDETAILS);	    
	    jotEvent.setDaLogNum(profileRefEvent.getDaLogNum());
	    CompositeResponse jotResponse = MessageUtil.postRequest(jotEvent);
	    JuvenileOffenderTrackingResponseEvent juvenileOffenderTrackingResponse = (JuvenileOffenderTrackingResponseEvent) MessageUtil.filterComposite(jotResponse, JuvenileOffenderTrackingResponseEvent.class);
	    if ( juvenileOffenderTrackingResponse  != null ){
		try {
		    String daDateOut = String.valueOf(juvenileOffenderTrackingResponse.getDaDateOut() );
		    if ( daDateOut != null || daDateOut.length() > 0 ) {
		    	courtHearingForm.setDaDateOut(new SimpleDateFormat("yyyyMMdd").parse(daDateOut));
		    } else {
			courtHearingForm.setDaDateOut(null);
		    }
		 } catch (Exception e){}
		courtHearingForm.setLogStatus(juvenileOffenderTrackingResponse.getLogStatus());
	    }
	    } else {
		courtHearingForm.setDaDateOut(null);
		courtHearingForm.setLogStatus(null);
	    }

	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No referral found for the referral number entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	
	// get offense details: // 105193
	JJSOffense offenseDetail = InterviewHelper.getOffenseForReferral(juvenileNum, referralNumber);
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);

	//List<JJSOffense>offenseDetails = offenseDetails(juvenileNum, referralNumber);
	List<JJSOffenseResponseEvent>offenseDetails = InterviewHelper.getOffenses(juvenileNum, referralNumber);
	if ( offenseDetail != null ) {
	    if (offenseDetails.size() > 0 ) {
		courtHearingForm.setOffenseDetails(offenseDetails);
		StringBuilder offenseCodes = new StringBuilder();
		StringBuilder offenseCategories = new StringBuilder();
		StringBuilder offenseDescriptions = new StringBuilder();
		for (int i = 0; i < offenseDetails.size(); i++ ) {
		    if (transferredOffenses !=null
				   && transferredOffenses.size() > 0 ){
			   if ( "TRNDSP".equals(offenseDetails.get(i).getOffenseCodeId()) 
				   || "TRNSIN".equals(offenseDetails.get(i).getOffenseCodeId())
				   || "REGION".equals(offenseDetails.get(i).getOffenseCodeId())
			           || "ISCOIN".equals(offenseDetails.get(i).getOffenseCodeId())
			       ){
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( referralNumber.equals(transferredOffense.getReferralNum()) ) {
				       offenseDetails.get(i).setOffenseDescription(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
				      
				   }
			       }
			   }
		    }
		    offenseCodes.append(offenseDetails.get(i).getOffenseCodeId());
		    offenseCategories.append(offenseDetails.get(i).getCatagory());
		    offenseDescriptions.append(offenseDetails.get(i).getOffenseDescription());
		    if ( i < (offenseDetails.size()-1)){
			offenseCodes.append(", ");
			offenseCategories.append(", ");
			offenseDescriptions.append(", ");
		    }
		}
		
		courtHearingForm.setOffenseCodeIds(offenseCodes.toString());
		courtHearingForm.setOffenseCategories(offenseCategories.toString());
		courtHearingForm.setOffenseDescriptions(offenseDescriptions.toString());
	    }
	    courtHearingForm.setOffenseDetail(offenseDetail);
	}

	// get petition Information from ms petition sql.
	int petitionsCount = 0;

	if (petitionResponses != null && !petitionResponses.isEmpty())
	{
	    Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		    @Override
		    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		    {
			if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
			    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			else
			    return -1;
		    }
		}));
	    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
	    while (petitionIter.hasNext())
	    {
		petitionsCount++;
		PetitionResponseEvent respEvent = petitionIter.next();
		/*if (respEvent != null && respEvent.getSequenceNum().equals("1"))
		{*/
		if(petitionsCount==1)
		{
		    courtHearingForm.setPetitionStatus(respEvent.getPetitionStatus());
		    courtHearingForm.setFilingDate(DateUtil.dateToString(respEvent.getPetitionDate(), DateUtil.DATE_FMT_1));
		    courtHearingForm.setPetitionType(respEvent.getPetitionType());
		    courtHearingForm.setPetitionNumber(respEvent.getPetitionNum());
		    courtHearingForm.setPetitionAmendment(respEvent.getAmend());
		    courtHearingForm.setPetitionAllegation(respEvent.getOffenseCodeId());
		    break;
	    	}
		//}
	    }
	}
	else
	{
	    /*If the referral is active (no close date) and no petition is associated to the referral, screen �FILING DATE� defaults to current system date.  User can modify.*/
	    courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
	}
	if (petitionsCount > 1)
	{
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.petitionAlreadyExists"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	// get Court Information
	
	List<DocketEventResponseEvent> crtdktRespEvts = JuvenileDistrictCourtHelper.getCourtDocket(juvenileNum, referralNumber);
	if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
	    while (crtdktRespEvtsItr.hasNext())
	    {
		DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
		if (crtDocResp != null)
		{
		    courtHearingForm.setBarNumber(crtDocResp.getBarNum());
		    courtHearingForm.setAttorneyConnection(crtDocResp.getAttorneyConnection());
		    courtHearingForm.setAttorneyName(crtDocResp.getAttorneyName());
		    courtHearingForm.setCourtId(crtDocResp.getCourt());
		    courtHearingForm.setHearingType(crtDocResp.getHearingType().trim());
		    courtHearingForm.setCourtDate(crtDocResp.getLastCourtDate());
		    courtHearingForm.setCourtTime(JuvenileFacilityHelper.stripColonInDate(crtDocResp.getCourtTime()));
		    courtHearingForm.setAmendmentDate(crtDocResp.getPetitionAmendmentDate());
		    courtHearingForm.setAction("subpoenas"); //court and petition Record already exists.// bug.
		    courtHearingForm.setJuvAge(crtDocResp.getAge());
		    break;
		}
	    }
	}
	else
	{
	  //add code to get attorney details from last attorney table task 115245
	    JJSLastAttorney jjsRecord = new JJSLastAttorney();
	    Iterator it = JJSLastAttorney.findAll("juvenileNum",juvenileNum);
	  		
	    while(it.hasNext())
	    {  		    
	  	JJSLastAttorney rec = (JJSLastAttorney) it.next();
	  	courtHearingForm.setBarNumber(rec.getAtyBarNum());
	  	courtHearingForm.setAttorneyConnection(rec.getAttConnect());
	  	courtHearingForm.setAttorneyName(rec.getAtyName());
	    }
	    if (petitionsCount > 0)
	    {
		
		ActionMessages messageHolder = new ActionMessages();
		messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.readyForHearingInformation"));
		aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
		saveMessages(aRequest, messageHolder);
		courtHearingForm.setAction("courtHearing");
		courtHearingForm.setCursorPosition("courtId");
		
	    }
	    courtHearingForm.setCourtTime("0900");
	}
	//Determine if the status = �Filed or Re-open� on the Petition Record. Already has a court date
	//If true, display �SUBPOENAS CAN NOT BE PRINTED � SELECT PETITION UPDATE OPTION.�  User cannot modify or populate any field.
	/*	if(courtHearingForm.getPetitionStatus()!=null && (courtHearingForm.getPetitionStatus().equals("F")|| courtHearingForm.getPetitionStatus().equals("R")) && courtHearingForm.getCourtId()!=null && !courtHearingForm.getCourtId().isEmpty() && courtHearingForm.getCourtDate()!=null && !courtHearingForm.getCourtDate().isEmpty()){
		    	courtHearingForm.setAction("courtHearing");
		    	ActionMessages messageHolder = new ActionMessages();
			messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.subpoenasnotprinted"));
			aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
			saveMessages(aRequest, messageHolder);
		}*/// NOT A VALID SCENARIO. DISCUSSED WITH REGINA

	// set family details
	Collection<FamilyConstellationListResponseEvent> constellation = JuvenileDistrictCourtHelper.setJuvenileFamilyDetails(juvenileNum, courtHearingForm);
	if (constellation == null || (constellation != null && constellation.isEmpty()))
	{
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.noFamilyFound"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}

	courtHearingForm.setPreparationDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));

	// codetable constants
	courtHearingForm.setPetitionStatuses(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.PETITION_STATUS, true));
	courtHearingForm.setPetitionTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.PETITION_TYPE, true));
	courtHearingForm.setSubpoenasToBePrinted(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUBPOENAS_TO_BE_PRINTED, true));
	courtHearingForm.setPetitionAmendments(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.AMENDMENT_NUMBER, true));
	courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesonlyActive()); //edited for US 157620
	
	if(isReadyForCourtAction(courtHearingForm)){
	    courtHearingForm.setIsCourtActionReady(true);
	}

	Collections.swap((List<CodeResponseEvent>) courtHearingForm.getSubpoenasToBePrinted(), 0, 1);//Juv,Father,Mother,Other [to maintain order]

	List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(juvenileDatesFormattedList);
	courtHearingForm.setPrevAction(courtHearingForm.getAction());
	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }
    
    public boolean isReadyForCourtAction(CourtHearingForm courtHearingForm){
	
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
     * updatePetition
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward petitionUpdate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String juvenileNumber = courtHearingForm.getJuvenileNumber();
	String referralNumber = courtHearingForm.getReferralNumber();
	String prevAction = courtHearingForm.getPrevAction();
	courtHearingForm.reset(); //clear all other fields.
	//check if user has feature to see disposed ones
	String feature = "JCW-PETITIONCORRECTION";
	boolean grantedFeature = false;
	try
	{
		ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
		if (securityManager != null)
		{
	                grantedFeature = securityManager.isAllowed(feature);	                
		}
	}
	catch (Throwable e)
	{
			// ignore any exception as this is not visible to the user        
	}

	//reset the juv and ref num
	courtHearingForm.setAction("petitionUpdate");
	if (prevAction.equalsIgnoreCase("FromReferralInquiry"))
	{
	    courtHearingForm.setPrevAction("FromReferralInquiry");
	}
	else
	{
	    courtHearingForm.setPrevAction("FromMainMenu");
	}
	courtHearingForm.setJuvenileNumber(juvenileNumber);
	courtHearingForm.setReferralNumber(referralNumber);

	List<PetitionResponseEvent> petitionResponses = null;

	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	reqProfileMain.setJuvenileNum(juvenileNumber);
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);

	if (juvProfile == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  record not found select another option"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
	    courtHearingForm.setJuvenileName(name.getFormattedName());
	}

	// Get Juvenile Profile Information
	courtHearingForm.setProfileDetail(juvProfile);

	// added for the reports.
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNumber);
	if (facilityHeaderResp != null)
	{

	    //pull detention facility from jjs detention.Bug fix #57130 
	    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvenileNumber);
	    courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
	    courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));

	}

	//populate CourtInformation to set AmendemntDate in the form.
	//need to check if attorney details to come from last attorney task 115545
	List<DocketEventResponseEvent> crtdktRespEvts = JuvenileDistrictCourtHelper.getCourtDocket(juvenileNumber, referralNumber);
	if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
	{
	    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
	    if (crtdktRespEvtsItr.hasNext())
	    {
		DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
		if (crtDocResp != null)
		{
		    courtHearingForm.setBarNumber(crtDocResp.getBarNum());
		    courtHearingForm.setAttorneyConnection(crtDocResp.getAttorneyConnection());
		    courtHearingForm.setAttorneyName(crtDocResp.getAttorneyName());
		    courtHearingForm.setCourtId(crtDocResp.getCourt());
		    courtHearingForm.setHearingType(crtDocResp.getHearingType().trim());
		    courtHearingForm.setCourtDate(crtDocResp.getLastCourtDate());
		    courtHearingForm.setCourtTime(JuvenileFacilityHelper.stripColonInDate(crtDocResp.getCourtTime()));
		    courtHearingForm.setAmendmentDate(crtDocResp.getPetitionAmendmentDate());
		    courtHearingForm.setChainNumber(crtDocResp.getChainNumber());
		    courtHearingForm.setJuvAge(crtDocResp.getAge());
		}
	    }
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition cannot be updated. Initial Setting has not been docketed"));
	    saveErrors(aRequest, errors);
	    if (courtHearingForm.getPrevAction().equalsIgnoreCase("FromReferralInquiry"))
	    { //stay in the referral Summary Page
		return aMapping.findForward(UIConstants.COURT_REFERRAL_SUMMARY);
	    }
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	// get referral details:
	JuvenileProfileReferralListResponseEvent profileRefEvent = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNumber, referralNumber);
	if (profileRefEvent != null)
	{
	    courtHearingForm.setReferralDate(DateUtil.dateToString(profileRefEvent.getReferralDate(), DateUtil.DATE_FMT_1));// set referral date for the filing date validation.

	    /**
	     * {Bug 64304): If CourtResult and/or Court Disposition is populated
	     * for the associated referral/petition, display
	     * "CASE DISPOSED.  INFORMATION CANNOT BE MODIFIED." Proposed
	     * requirement: An additional parameter needs to be added: If
	     * CourtResult and/or Court Disposition is populated AND there is no
	     * future date (Reset Date) associated to the setting....
	     */
	    // bug fix #64231 //bug 64800 // task no 67756
	    if (!grantedFeature&&((profileRefEvent.getCourtResult() != null && !profileRefEvent.getCourtResult().isEmpty()) || (profileRefEvent.getCourtDisposition() != null && !profileRefEvent.getCourtDisposition().isEmpty())) && (!DateUtil.stringToDate(courtHearingForm.getCourtDate(), "MM/dd/yyyy").after(JuvenileDistrictCourtHelper.getDateWithoutTime(DateUtil.getCurrentDate())) && !DateUtil.stringToDate(courtHearingForm.getCourtDate(), "MM/dd/yyyy").equals(JuvenileDistrictCourtHelper.getDateWithoutTime(DateUtil.getCurrentDate()))))
	    {		
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Case Disposed. Information cannot be modified"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		petitionResponses = InterviewHelper.getPetitionsRespForReferral(juvenileNumber, referralNumber);
		Collections.sort((List<PetitionResponseEvent>) petitionResponses, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		    @Override
		    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		    {
			if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
			    return Integer.valueOf(evt1.getSequenceNum()).compareTo(Integer.valueOf(evt2.getSequenceNum()));
			else
			    return -1;
		    }
		}));

		//get highest seq number
		if (petitionResponses != null && !petitionResponses.isEmpty())
		{
		    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
		    if (petitionIter.hasNext())
		    {
			PetitionResponseEvent respEvent = petitionIter.next();
			if (respEvent != null)
			{
			    courtHearingForm.setPetitionStatus(respEvent.getPetitionStatus());
			    courtHearingForm.setFilingDate(DateUtil.dateToString(respEvent.getPetitionDate(), DateUtil.DATE_FMT_1));
			    courtHearingForm.setPetitionType(respEvent.getPetitionType());
			    courtHearingForm.setPetitionNumber(respEvent.getPetitionNum());
			    courtHearingForm.setPetitionAmendment(respEvent.getAmend());
			    courtHearingForm.setPetitionAllegation(respEvent.getOffenseCodeId());
			    courtHearingForm.setPetitionSeqNum(respEvent.getSequenceNum());
			    if (courtHearingForm.getPetitionAllegationSev() == null || (courtHearingForm.getPetitionAllegationSev() != null && courtHearingForm.getPetitionAllegationSev().isEmpty()))
			    {
				if (respEvent != null)
				{
				    courtHearingForm.setPetitionAllegationSev(respEvent.getSeverity());
				}
			    }
			}
		    }
		}
		else
		{
		    /*If the referral is active (no close date) and no petition is associated to the referral, screen �FILING DATE� defaults to current system date.  User can modify.*/
		    courtHearingForm.setFilingDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		    // update from regina.
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO PETITION FOUND FOR THIS JUVENILE, ACCESS INITIAL SETTING OPTION TO CREATE PETITION"));
		    saveErrors(aRequest, errors);
		    if (courtHearingForm.getPrevAction().equalsIgnoreCase("FromReferralInquiry"))
		    { //stay in the referral Summary Page
			return aMapping.findForward(UIConstants.COURT_REFERRAL_SUMMARY);
		    }
		    return aMapping.findForward(UIConstants.FAILURE);
		}

	    }
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No referral found for the referral number entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	JuvenileDistrictCourtHelper.setJuvenileFamilyDetails(juvenileNumber, courtHearingForm);
	courtHearingForm.setPreparationDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));

	// codetable constants
	courtHearingForm.setPetitionStatuses(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.PETITION_STATUS, true));
	courtHearingForm.setPetitionTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.PETITION_TYPE, true));
	courtHearingForm.setSubpoenasToBePrinted(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.SUBPOENAS_TO_BE_PRINTED, true));
	courtHearingForm.setPetitionAmendments(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.AMENDMENT_NUMBER, true));

	Collections.swap((List<CodeResponseEvent>) courtHearingForm.getSubpoenasToBePrinted(), 0, 1);//Juv,Father,Mother,Other [to maintain order]

	List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(juvenileDatesFormattedList);
	return aMapping.findForward(UIConstants.PETITION_UPDATE_DISPLAY);
    }

    /**
     * masterReferralDisplay
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward masterBriefingInquiry(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setFinalReleaseDate(""); //88723
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	if (courtHearingForm.getJuvenileNumber() == null || courtHearingForm.getJuvenileNumber().equals(""))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Juvenile Number entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);
	searchEvent.setJuvenileNum(courtHearingForm.getJuvenileNumber());
	courtHearingForm.reset();
	courtHearingForm.setAction("JuvNumSearch");
	dispatch.postEvent(searchEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	//ActionForward forward = null;
	JuvenileProfileDetailResponseEvent respEvent = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
	Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (errorResp != null)
	{
	    ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResp;
	    try
	    {
		handleFatalUnexpectedException(myEvt.getMessage());
	    }
	    catch (GeneralFeedbackMessageException e)
	    {
		e.printStackTrace();
	    }
	}
	if (respEvent == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No records found. Please retry search"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	JuvenileDistrictCourtHelper.setProfileDetail(respEvent, courtHearingForm);
	//88723
	Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList = JuvenileFacilityHelper.getJuvFacilityDetails(courtHearingForm.getJuvenileNumber(), null);
	if(facilityHistoryList!=null){
		// re-sort the child list based on facility seq num.
	    	Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>)facilityHistoryList, Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
		//set offense information.
        	    Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityHistIterator = facilityHistoryList.iterator();
        	    if (facilityHistIterator.hasNext())
        	    { //iterate detention recs.
        		JuvenileDetentionFacilitiesResponseEvent detRec = facilityHistIterator.next();
        		if (detRec != null)
        		{
        		    if (detRec.getReleaseDate() != null && detRec.getReleaseDateStr()!=null && !detRec.getReleaseDateStr().isEmpty())
        		    {
        			courtHearingForm.setFinalReleaseDate(detRec.getReleaseDateStr());
        		    }
        		}
        	    }//
		
	} //end of if //88723
	
        Collection<JuvenileProfileReferralListResponseEvent> referrals = JuvenileFacilityHelper.getJuvReferralDetailsfiltered(courtHearingForm.getJuvenileNumber());

        if(referrals!=null)
        {
	 //sorts in descending order by prob end date. 
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referrals, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getProbationEndDate()!= null && evt2.getProbationEndDate() != null)
			return evt1.getProbationEndDate().compareTo(evt2.getProbationEndDate());
		    else
			return -1;
		}
	    }));
	
	    Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referrals.iterator();
	    if (referralIterator.hasNext())
	    { 
		JuvenileProfileReferralListResponseEvent refRec = referralIterator.next();
		if (refRec != null)
		{
		    if (refRec.getProbationEndDate() != null)
		    {
			courtHearingForm.setLastprobendDate(DateUtil.dateToString(refRec.getProbationEndDate(), DateUtil.DATE_FMT_1));
		    }
		}
	    }//
        }
	//97817
	//Collection<JuvenileTraitResponseEvent> detTraits = JuvenileFacilityHelper.getDetentionTraits(courtHearingForm.getJuvenileNumber());
	/*GetJuvenileTraitsEvent event = (GetJuvenileTraitsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITS);
	event.setJuvenileNum(courtHearingForm.getJuvenileNumber());

	dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(event);
	IEvent replyEvent = dispatch.getReply();
	CompositeResponse responses = (CompositeResponse) replyEvent;*/
	//Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.compositeToCollection(responses, JuvenileTraitResponseEvent.class);
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) 
				EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum( courtHearingForm.getJuvenileNumber() );
	traitByParentEvent.setTraitType("25");
	
	Collection<JuvenileTraitResponseEvent> juvenileTraits = MessageUtil.postRequestListFilter(traitByParentEvent, JuvenileTraitResponseEvent.class);
	
	 Collections.sort((List<JuvenileTraitResponseEvent>) juvenileTraits, new Comparator<JuvenileTraitResponseEvent>() {
		@Override
		public int compare(JuvenileTraitResponseEvent evt1, JuvenileTraitResponseEvent evt2)
		{
		    if (evt1.getJuvenileTraitId() != null && evt2.getJuvenileTraitId() != null)
			return evt2.getJuvenileTraitId().compareTo(evt1.getJuvenileTraitId());
		    else
			return -1;
		}
	    });
	
	
	courtHearingForm.setDualStatus("");
	if ( juvenileTraits != null && juvenileTraits.size() > 0) {
	    for ( JuvenileTraitResponseEvent juvenileTrait : juvenileTraits ){
		//filter for current vs former
		if ( "DST".equalsIgnoreCase( juvenileTrait.getTraitTypeId()) && "CU".equalsIgnoreCase( juvenileTrait.getStatusId()) ){
		    courtHearingForm.setDualStatus("DS");
		    break;
		}else{
		    if ( "DST".equalsIgnoreCase( juvenileTrait.getTraitTypeId()) && !"CU".equalsIgnoreCase( juvenileTrait.getStatusId()) ){
			courtHearingForm.setDualStatus("FDS");
			break;
			 
		}
	    }
	    }
	}// end of if
	//97817
	
	//User Story 156680
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails( courtHearingForm.getJuvenileNumber() );
	
	if (facilityHeaderResp != null ) {
	    courtHearingForm.setFacilityHeader(facilityHeaderResp);
	    courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	
	}
	
	return aMapping.findForward(UIConstants.BRIEFING_DETAILS);
    }
   
    /**
     * numberInquiry
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward numberInquiry(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String msg = "";
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	Collection<NumberInquiryResponse> resultsList = courtHearingForm.getJuvCourtHearingList();
	Collection<NumberInquiryResponse> resultsHist = courtHearingForm.getJuvCourtHearingHist();
	resultsList = new ArrayList();
	resultsHist = new ArrayList();
	courtHearingForm.setSearchResultSize(0);

	NumberInquiryResponse numberResponse = null;
	NumberInquiryResponse historyResponse = null;

	if ("INV".equalsIgnoreCase(courtHearingForm.getReferralNumber()))
	{

	    GetJJSOffenseByInvestnoEvent offenseEvent = (GetJJSOffenseByInvestnoEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSOFFENSEBYINVESTNO);
	    offenseEvent.setInquiryNum(courtHearingForm.getJuvenileNumber());

	    CompositeResponse compositeResp = MessageUtil.postRequest(offenseEvent);
	    List<JJSOffenseResponseEvent> offenseResp = MessageUtil.compositeToList(compositeResp, JJSOffenseResponseEvent.class);

	    if (offenseResp.size() > 0)
	    {

		// For loop here
		for (int y = 0; y < offenseResp.size(); y++)
		{
		    String caseStatus = "";
		    numberResponse = new NumberInquiryResponse();
		    Name nameJuv = null;

		    JJSOffenseResponseEvent offenseRec = offenseResp.get(y);

		    GetJJSJuvenileInfoEvent request = (GetJJSJuvenileInfoEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILEINFO);
		    request.setJuvenileNum(offenseRec.getJuvenileNum());

		    JJSJuvenileInfoResponseEvent juvenileResp = (JJSJuvenileInfoResponseEvent) MessageUtil.postRequest(request, JJSJuvenileInfoResponseEvent.class);

		    caseStatus = juvenileResp.getStatusId();
		    String statusDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, juvenileResp.getStatusId());

		    nameJuv = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName(), "");

		    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
		    req.setJuvenileNum(offenseRec.getJuvenileNum());
		    req.setReferralNum(offenseRec.getReferralNum());

		    List casefileList = MessageUtil.postRequestListFilter(req, JuvenileCasefileReferralDetailResponseEvent.class);

		    Collections.sort((List) casefileList, JuvenileCasefileReferralDetailResponseEvent.DateRefComparator);

		    if (casefileList.size() > 0)
		    {

			String parentRef = "";
			List<NumberInquiryResponse> historyList = new ArrayList();
			for (int i = 0; i < casefileList.size(); i++)
			{

			    JuvenileCasefileReferralDetailResponseEvent response = (JuvenileCasefileReferralDetailResponseEvent) casefileList.get(i);

			    if (!parentRef.equals(response.getReferralNumber()))
			    {
				// place on parent list
				numberResponse = new NumberInquiryResponse();

				numberResponse.setJuvenileName(nameJuv.getFormattedName());
				numberResponse.setStatusCd(caseStatus);
				numberResponse.setStatusDesc(statusDesc);
				numberResponse.setSupervisionNumber(response.getCaseFileId());
				numberResponse.setSupervisionCategory(response.getSupervisionTypeDesc());
				Name offName = new Name(response.getOfficerFName(), response.getOfficerMName(), response.getOfficerLName());
				numberResponse.setOfficerName(offName.getFormattedName());
				numberResponse.setJpoOfficer(response.getAssignOfficerId());
				numberResponse.setJuvenileNumber(response.getJuvenileId());
				numberResponse.setReferralNumber(response.getReferralNumber());
				resultsList.add(numberResponse);

				parentRef = response.getReferralNumber();
			    }
			    else
			    {

				// Add to child
				historyResponse = new NumberInquiryResponse();
				JuvenileCasefileReferralDetailResponseEvent refResponse = (JuvenileCasefileReferralDetailResponseEvent) casefileList.get(i);

				historyResponse.setJuvenileName(nameJuv.getFormattedName());
				historyResponse.setStatusCd(caseStatus);
				historyResponse.setStatusDesc(statusDesc);
				historyResponse.setSupervisionNumber(refResponse.getCaseFileId());
				historyResponse.setSupervisionCategory(refResponse.getSupervisionTypeDesc());
				Name offName = new Name(refResponse.getOfficerFName(), refResponse.getOfficerMName(), refResponse.getOfficerLName());
				historyResponse.setOfficerName(offName.getFormattedName());
				historyResponse.setJpoOfficer(refResponse.getAssignOfficerId());
				historyResponse.setJuvenileNumber(refResponse.getJuvenileId());
				historyResponse.setReferralNumber(refResponse.getReferralNumber());
				historyList.add(historyResponse);

				// Add response to list
				resultsHist.add(historyResponse);

			    }
			}

			numberResponse.setHistoryList(historyList);
		    }

		}
	    }
	    else
	    {

		courtHearingForm.setJuvCourtHearingList(new ArrayList<NumberInquiryResponse>());
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO RECORDS FOUND FOR THIS 'INVESTIGATION NUMBER'"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }

	}
	else
	{

	    Map petitionMap = new TreeMap();
	    GetJJSPetitionsEvent petRequest = (GetJJSPetitionsEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJJSPETITIONS);
	    petRequest.setPetitionNum(courtHearingForm.getJuvenileNumber());

	    List petitionList = MessageUtil.postRequestListFilter(petRequest, PetitionResponseEvent.class);

	    if (petitionList.size() > 0)
	    {

		//sorts in ascending order by Petition Num. //requirement for Standard Docket PDF
		Collections.sort((List<PetitionResponseEvent>) petitionList, new Comparator<PetitionResponseEvent>() {
		    @Override
		    public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		    {
			if (evt1.getSequenceNum() != null && evt2.getSequenceNum() != null)
			    return evt2.getSequenceNum().compareTo(evt1.getSequenceNum());
			else
			    return -1;
		    }
		});
		    
		Iterator petIter = petitionList.iterator();
		while (petIter.hasNext())
		{

		    PetitionResponseEvent resp = (PetitionResponseEvent) petIter.next();

		    petitionMap.put(resp.getReferralNum(), resp.getJuvenileNum());

		}

		 List<Entry<String,String>> sortedEntries = new ArrayList<Entry<String,String>>(petitionMap.entrySet());

		    Collections.sort(sortedEntries, 
		            new Comparator<Entry<String,String>>() {
		                @Override
		                public int compare(Entry<String,String> e1, Entry<String,String> e2) {
		                    return e2.getKey().compareTo(e1.getKey());
		                }
		            }
		    );
		    
		Iterator iter = sortedEntries.iterator();
		while (iter.hasNext())
		{
		    
		    
		    Entry pair = (Entry) iter.next();

		    String caseStatusCd = "";
		    String statusDesc = "";
		    numberResponse = new NumberInquiryResponse();
		    Name nameJuvenile = null;

		    GetJJSJuvenileInfoEvent request = (GetJJSJuvenileInfoEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILEINFO);
		    request.setJuvenileNum( pair.getValue().toString());

		    JJSJuvenileInfoResponseEvent juvenileResp = (JJSJuvenileInfoResponseEvent) MessageUtil.postRequest(request, JJSJuvenileInfoResponseEvent.class);

		    if( juvenileResp != null ){
			    caseStatusCd = juvenileResp.getStatusId();
			    statusDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, juvenileResp.getStatusId());

			    nameJuvenile = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName(), "");
		    }

		    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
		    req.setJuvenileNum( pair.getValue().toString() );
		    req.setReferralNum( pair.getKey().toString() );

		    List casefileList = MessageUtil.postRequestListFilter(req, JuvenileCasefileReferralDetailResponseEvent.class);

		    Collections.sort((List) casefileList, JuvenileCasefileReferralDetailResponseEvent.DateRefComparator);

		    if (casefileList.size() > 0)
		    {

			String parentRef = "";
			List<NumberInquiryResponse> historyList = new ArrayList();
			for (int i = 0; i < casefileList.size(); i++)
			{

			    JuvenileCasefileReferralDetailResponseEvent response = (JuvenileCasefileReferralDetailResponseEvent) casefileList.get(i);

			    if (!parentRef.equals(response.getReferralNumber()))
			    {
				// place on parent list
				numberResponse = new NumberInquiryResponse();

				numberResponse.setJuvenileName(nameJuvenile.getFormattedName());
				numberResponse.setStatusCd(caseStatusCd);
				numberResponse.setStatusDesc(statusDesc);
				numberResponse.setSupervisionNumber(response.getCaseFileId());
				numberResponse.setSupervisionCategory(response.getSupervisionTypeDesc());
				Name offName = new Name(response.getOfficerFName(), response.getOfficerMName(), response.getOfficerLName());
				numberResponse.setOfficerName(offName.getFormattedName());
				numberResponse.setJpoOfficer(response.getAssignOfficerId());
				numberResponse.setJuvenileNumber(response.getJuvenileId());
				numberResponse.setReferralNumber(response.getReferralNumber());
				resultsList.add(numberResponse);

				parentRef = response.getReferralNumber();
			    }
			    else
			    {

				// Add to child
				historyResponse = new NumberInquiryResponse();
				JuvenileCasefileReferralDetailResponseEvent refResponse = (JuvenileCasefileReferralDetailResponseEvent) casefileList.get(i);

				historyResponse.setJuvenileName(nameJuvenile.getFormattedName());
				historyResponse.setStatusCd(caseStatusCd);
				historyResponse.setStatusDesc(statusDesc);
				historyResponse.setSupervisionNumber(refResponse.getCaseFileId());
				historyResponse.setSupervisionCategory(refResponse.getSupervisionTypeDesc());
				Name offName = new Name(refResponse.getOfficerFName(), refResponse.getOfficerMName(), refResponse.getOfficerLName());
				historyResponse.setOfficerName(offName.getFormattedName());
				historyResponse.setJpoOfficer(refResponse.getAssignOfficerId());
				historyResponse.setJuvenileNumber(refResponse.getJuvenileId());
				historyResponse.setReferralNumber(refResponse.getReferralNumber());
				historyList.add(historyResponse);

				// Add response to list
				resultsHist.add(historyResponse);

			    }
			}

			numberResponse.setHistoryList(historyList);
		    }

		}

	    }
	    else
	    {
		courtHearingForm.setJuvCourtHearingList(new ArrayList<NumberInquiryResponse>());
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO RECORDS FOUND FOR THIS 'PETITION NUMBER'"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }

	}
	// add children to parents
	// for loop here
	List<NumberInquiryResponse> childList = null;
	for (int p = 0; p < resultsList.size(); p++)
	{

	    childList = new ArrayList();
	    NumberInquiryResponse parentResp = ((List<NumberInquiryResponse>) resultsList).get(p);

	    for (int c = 0; c < resultsHist.size(); c++)
	    {

		NumberInquiryResponse childResp = ((List<NumberInquiryResponse>) resultsHist).get(c);

		if (childResp.getReferralNumber().equalsIgnoreCase(parentResp.getReferralNumber()) && (childResp.getJuvenileNumber().equalsIgnoreCase(parentResp.getJuvenileNumber())))
		{

		    childList.add(childResp);
		    parentResp.setHistoryList(childList);
		}
	    }
	}

	courtHearingForm.setSearchResultSize((resultsList.size() + resultsHist.size()));
	courtHearingForm.setJuvCourtHearingList(resultsList);
	msg = "courtNumberInquiryDisplay";

	return aMapping.findForward(msg);
    }

    /**
     * nameSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward nameSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.reset();
	courtHearingForm.setSearchType("JNAM");
	//populate the profileForm
	return aMapping.findForward(UIConstants.COURT_NAME_SEARCH);
    }

    /**
     * referralInquiry
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward referralInquiry(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String juvNum = courtHearingForm.getJuvenileNumber();
	courtHearingForm.reset();

	// 	Get Juvenile Profile Information
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	reqProfileMain.setJuvenileNum(juvNum);
	CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
	if (juvProfile == null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please Enter A New Juv No"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	else
	{
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
	    courtHearingForm.setJuvenileName(name.getFormattedName());
	    
	    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
	    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName() );
	    courtHearingForm.setProfileDetail(respEvent);
	}
	courtHearingForm.setProfileDetail(juvProfile);

	// Get Juvenile Facility/Detention Information
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvNum);
	if (facilityHeaderResp != null)
	{
	    //pull status from jjs header.Bug fix #57130
	    courtHearingForm.setFacilityHeader(facilityHeaderResp);
	    courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	    courtHearingForm.setFacilityStatusDesc(JuvenileFacilityHelper.getCodeTableDescription(PDCodeTableConstants.FACILITY_STATUS, facilityHeaderResp.getFacilityStatus()));

	    //pull detention facility from jjs detention.Bug fix #57130 
	    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvNum);
	    courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
	    courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));
	}
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	while (referralIterator.hasNext())
	{	    
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    
	    Collection<JJSOffense> offenses = referral.getOffenses();
	    if (offenses != null)
	    {
		Iterator<JJSOffense> offenseItr = offenses.iterator();
		while (offenseItr.hasNext())
		{
		    JJSOffense offense = offenseItr.next();
		    
		    referral.setOffenseCategory(offense.getOffenseCategory());
		    
		    if (offense.getSequenceNum().equalsIgnoreCase("1"))
		    {
			referral.setOffense(offense.getOffenseCodeId());
			referral.setOffenseDesc(offense.getOffenseDescription());
			if ( "TRNDSP".equals(offense.getOffenseCodeId()) 
				   || "TRNSIN".equals(offense.getOffenseCodeId())
				   || "REGION".equals(offense.getOffenseCodeId())
			           || "ISCOIN".equals(offense.getOffenseCodeId())
			       ){
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( referral.getReferralNumber().equals(transferredOffense.getReferralNum()) ) {
				       referral.setOffenseDesc( transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				       referral.setPetitionAllegationDescr(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				   }
			       }
			  
			} 
			break;
		    }
		}
	    }
	    // Get and set all jpo's from referrals first so they are filled in assigned casefiles.
	     if(StringUtils.isNotEmpty(referral.getJpoId()) || (referral.getJpoId() != null )){
		 
		 GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) 
				 EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		 event.setAttributeName("logonId");
		 if( !referral.getJpoId().startsWith("UV")){
		     
		     event.setAttributeValue("UV" + referral.getJpoId());
		 }else{
		     event.setAttributeValue(referral.getJpoId());//inassignjpoid		     
		 }
		 
		 List<OfficerProfileResponseEvent> officerProfileList = MessageUtil.postRequestListFilter(event, OfficerProfileResponseEvent.class);
		 if ( officerProfileList.size() > 0 )
		 {
		     OfficerProfileResponseEvent offResp = officerProfileList.get(0);
		     referral.setJpoId( offResp.getUserId());
		     String officerFullName = offResp.getLastName() + ", " + offResp.getFirstName();
		     referral.setJpo(officerFullName);
		 }
	     }
	     
	    /*  if(!referral.getJpoId().isEmpty() ){
	      referral.setJpoId("UV" + referral.getJpoId());
	    IName name = SecurityUIHelper.getUserName(referral.getJpoId());
	    if (name != null)
	    {
	    	if (! name.getFormattedName().equalsIgnoreCase("NOT AVAILABLE")){
	    	    referral.setJpo(name.getFormattedName());
	    	}
	    	else{
	    		OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(referral.getJpoId());
	    		if (respEvent != null) {
	    			IName nameAlt = respEvent.getOfficerName();
	    			if (nameAlt != null) {
	    			    referral.setJpo(respEvent.getOfficerName().getFormattedName());
	    			}
	    		}
	    	}
	    }
	      }*/
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
			return evt1.getOID().compareTo(evt2.getOID());//sort based on OID bug fix for 152448
		    else
			return -1;
		}
	    }));
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    while (caseFileRefItr.hasNext())
	    {
			JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
        		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        		if (officerProfileResponse != null)
        		{
        		    referral.setJpoId(officerProfileResponse.getUserId());
        		    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        		    referral.setJpo(officerFullName);
        		}
        		if(caseFileReferral.getCaseStatusCd().equalsIgnoreCase("A"))//check active only for supervision check bug fix for 152448
                	{
                		referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
                		referral.setSupervisionType(caseFileReferral.getSupervisionType());
                		referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
                		referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
        		}
        		break;
		
	    }
	    //commented the below piece of code for BUG# 71197
	    /*	    //for each referral get the casefiles, then find the supervision Category, Supervision Type and JPO   	
	    	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getCasefilesFromReferral(juvNum, referral.getReferralNumber());
	    	    JPOofRecSupervisionTypeCategoryResponseEvent codeResp = JuvenileDistrictCourtHelper.setJPOOfRecordForReferralList(refCasefileList.getCasefileReferrals(), juvNum);
	    	    if (!codeResp.getJpoId().isEmpty())
	    	    {
	    		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(codeResp.getJpoId());
	    		referral.setJpoId(officerProfileResponse.getUserId());
	    		String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
	    		referral.setJpo(officerFullName);
	    		referral.setSupervisionTypeId(codeResp.getSupervisionTypeId());
	    		referral.setSupervisionType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE, codeResp.getSupervisionTypeId()));
	    		referral.setSupervisionCategoryId(codeResp.getSupervisionCategoryId());
	    		referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, codeResp.getSupervisionCategoryId()));
	    	    }*/
	}
	
	 Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());//bug 160275
		    else
			return -1;
		}
	    }));
	 
	JuvenileFacilityHelper.populateTransferTo(juvNum, referralList); 
	 
	courtHearingForm.setReferralList(referralList);
	courtHearingForm.setSearchResultSize(referralList.size());
	String supCatDescr = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, courtHearingForm.getSupervisionCategoryId());
	courtHearingForm.setSupervisionCategory(supCatDescr);
	String formJPO = courtHearingForm.getJpoOfRecID();
	if (formJPO == "" || formJPO == null)
	{
	    JuvenileDistrictCourtHelper.jpoOfRecord(juvNum, courtHearingForm);//if we don't have the info in the profileDetail
	}
	courtHearingForm.setJuvenileNumber(juvNum);
	return aMapping.findForward(UIConstants.COURT_REFERRAL_SUMMARY);
    }
    
    
    
    /**
     * DALogNumSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    
    public ActionForward DALogNumSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String dalogNum = courtHearingForm.getJuvenileNumber();
	courtHearingForm.reset();
	courtHearingForm.setAction("DALognumSearch");
	courtHearingForm.setDaLogNum(dalogNum);
	String juvNum = "";

	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getReferralDetails(dalogNum);
	Iterator<JuvenileProfileReferralListResponseEvent> referralIterator = referralList.iterator();
	List<JuvenileProfileReferralListResponseEvent> referrals = new ArrayList<JuvenileProfileReferralListResponseEvent>();
	while (referralIterator.hasNext())
	{
	    JuvenileProfileReferralListResponseEvent referral = referralIterator.next();
	    referrals.add(referral);
	}
	if(referrals!=null&&referrals.size()>0)
	    juvNum = referrals.get(0).getJuvNum();
	if (juvNum != null && !juvNum.isEmpty())
	{
	    // 	Get Juvenile Profile Information
	    List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	    GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
	    reqProfileMain.setJuvenileNum(juvNum);
	    CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
	    JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
	    if (juvProfile == null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please Enter A New Juv No"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
		courtHearingForm.setJuvenileName(name.getFormattedName());

		JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
		respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
		courtHearingForm.setProfileDetail(respEvent);
	    }
	    courtHearingForm.setProfileDetail(juvProfile);

	    // Get Juvenile Facility/Detention Information
	    JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvNum);
	    if (facilityHeaderResp != null)
	    {
		//pull status from jjs header.Bug fix #57130
		courtHearingForm.setFacilityHeader(facilityHeaderResp);
		courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
		courtHearingForm.setFacilityStatusDesc(JuvenileFacilityHelper.getCodeTableDescription(PDCodeTableConstants.FACILITY_STATUS, facilityHeaderResp.getFacilityStatus()));

		//pull detention facility from jjs detention.Bug fix #57130 
		JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvNum);
		courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
		courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));
	    }

	    while (referralIterator.hasNext())
	    {
		JuvenileProfileReferralListResponseEvent referral = referralIterator.next();

		Collection<JJSOffense> offenses = referral.getOffenses();
		//if(referral.getJuvNum()!=null&&!referral.getJuvNum().isEmpty())	    
		if (offenses != null)
		{
		    Iterator<JJSOffense> offenseItr = offenses.iterator();
		    while (offenseItr.hasNext())
		    {
			JJSOffense offense = offenseItr.next();

			referral.setOffenseCategory(offense.getOffenseCategory());

			if (offense.getSequenceNum().equalsIgnoreCase("1"))
			{
			    referral.setOffense(offense.getOffenseCodeId());
			    referral.setOffenseDesc(offense.getOffenseDescription());
			    if ("TRNDSP".equals(offense.getOffenseCodeId()) || "TRNSIN".equals(offense.getOffenseCodeId()) || "REGION".equals(offense.getOffenseCodeId()) || "ISCOIN".equals(offense.getOffenseCodeId()))
			    {
				for (JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses)
				{
				    if (referral.getReferralNumber().equals(transferredOffense.getReferralNum()))
				    {
					referral.setOffenseDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
					referral.setPetitionAllegationDescr(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory());
				    }
				}

			    }
			    break;
			}
		    }
		}
		// Get and set all jpo's from referrals first so they are filled in assigned casefiles.
		if (StringUtils.isNotEmpty(referral.getJpoId()) || (referral.getJpoId() != null))
		{

		    GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
		    event.setAttributeName("logonId");
		    if (!referral.getJpoId().startsWith("UV"))
		    {

			event.setAttributeValue("UV" + referral.getJpoId());
		    }
		    else
		    {
			event.setAttributeValue(referral.getJpoId());//inassignjpoid		     
		    }

		    List<OfficerProfileResponseEvent> officerProfileList = MessageUtil.postRequestListFilter(event, OfficerProfileResponseEvent.class);
		    if (officerProfileList.size() > 0)
		    {
			OfficerProfileResponseEvent offResp = officerProfileList.get(0);
			referral.setJpoId(offResp.getUserId());
			String officerFullName = offResp.getLastName() + ", " + offResp.getFirstName();
			referral.setJpo(officerFullName);
		    }
		}
		//for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
		JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(juvNum, referral.getReferralNumber());
		Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

		Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		    @Override
		    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		    {
			if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
			    return evt1.getOID().compareTo(evt2.getOID());//sort based on OID bug fix for 152448
			else
			    return -1;
		    }
		}));
		Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
		while (caseFileRefItr.hasNext())
		{
		    JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
		    OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
		    if (officerProfileResponse != null)
		    {
			referral.setJpoId(officerProfileResponse.getUserId());
			String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
			referral.setJpo(officerFullName);
		    }
		    if (caseFileReferral.getCaseStatusCd().equalsIgnoreCase("A"))//check active only for supervision check bug fix for 152448
		    {
			referral.setSupervisionCategoryId(caseFileReferral.getSupervisionCat());
			referral.setSupervisionType(caseFileReferral.getSupervisionType());
			referral.setSupervisionTypeId(caseFileReferral.getSupervisionTypeCd());
			referral.setSupervisionCategory(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, referral.getSupervisionCategoryId()));
		    }
		    break;

		}
	    }

	    Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());//bug 160275
		    else
			return -1;
		}
	    }));

	    JuvenileFacilityHelper.populateTransferTo(juvNum, referralList);

	    courtHearingForm.setReferralList(referralList);
	    courtHearingForm.setSearchResultSize(referralList.size());
	    String supCatDescr = SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY, courtHearingForm.getSupervisionCategoryId());
	    courtHearingForm.setSupervisionCategory(supCatDescr);
	    String formJPO = courtHearingForm.getJpoOfRecID();
	    if (formJPO == "" || formJPO == null)
	    {
		JuvenileDistrictCourtHelper.jpoOfRecord(juvNum, courtHearingForm);//if we don't have the info in the profileDetail
	    }
	    courtHearingForm.setJuvenileNumber(juvNum);
	}
	else
	{
	    	ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Record Not Found. Please Enter A New DA Log No"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	}
	return aMapping.findForward(UIConstants.COURT_REFERRAL_SUMMARY);
    }

    /**
     * PetCJISSearch
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward PetCJISSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	//String juvNum = courtHearingForm.getJuvenileNumber();
	courtHearingForm.reset();
	String juvNum = aRequest.getParameter("juvnum");
	String typeCode = aRequest.getParameter("typeCode");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	// 	Get Juvenile Profile Information
	if (juvNum != null && !juvNum.isEmpty())
	{
	    if (typeCode.isEmpty())
	    {
		GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		reqProfileMain.setJuvenileNum(juvNum);
		CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
		JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		if (juvProfile == null)
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.FAILURE);
		}
		else
		{
		    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
		    courtHearingForm.setJuvenileName(name.getFormattedName());

		    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
		    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
		    courtHearingForm.setProfileDetail(respEvent);
		}
		courtHearingForm.setProfileDetail(juvProfile);
		courtHearingForm.setOrigjuvNum(juvNum);
	    }
	    else
		if (typeCode.equalsIgnoreCase("pet"))
		{
		    GetDetailsbyPetitionNumEvent petitionEvt = (GetDetailsbyPetitionNumEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETDETAILSBYPETITIONNUM);
		    petitionEvt.setPetitionNum(juvNum);
		    dispatch.postEvent(petitionEvt);
		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		    List<PetitionResponseEvent> petresEvts = MessageUtil.compositeToList(compositeResponse, PetitionResponseEvent.class);
		    PetitionResponseEvent petition = new PetitionResponseEvent();
		    if (petresEvts.size() > 0)
		    {
			petition = petresEvts.get(0);
			String juvNo = petition.getJuvenileNum();
			if (juvNo != null && !juvNo.isEmpty())
			{
			    //courtHearingForm.setJuvenileNumber(juvNum);
			    GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
			    reqProfileMain.setJuvenileNum(juvNo);
			    CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
			    JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
			    if (juvProfile == null)
			    {
				    ActionErrors errors = new ActionErrors();
				    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
				    saveErrors(aRequest, errors);
				    return aMapping.findForward(UIConstants.FAILURE);
			    }
			    else
			    {
				Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
				courtHearingForm.setJuvenileName(name.getFormattedName());

				JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
				respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
				courtHearingForm.setProfileDetail(respEvent);
			    }
			    courtHearingForm.setProfileDetail(juvProfile);
			    courtHearingForm.setOrigjuvNum(juvNo);
			}
		    }
		    else
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Number entered is not valid"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }

		}
		else
		    if (typeCode.equalsIgnoreCase("cjis")) //create like search with cjis
		    {
			List<PetitionResponseEvent> petresEvts = InterviewHelper.getPetitionsForCJIS(juvNum);//get ALL petitions for cjis num like
			PetitionResponseEvent petition = new PetitionResponseEvent();
			if (petresEvts.size() > 0)
			{
			    petition = petresEvts.get(0);
			    String juvNo = petition.getJuvenileNum();
			    if (juvNo != null && !juvNo.isEmpty())
			    {
				GetJuvenileProfileMainEvent reqProfileMain = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
				reqProfileMain.setJuvenileNum(juvNo);
				CompositeResponse response = MessageUtil.postRequest(reqProfileMain);
				JuvenileProfileDetailResponseEvent juvProfile = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
				if (juvProfile == null)
				{
					    ActionErrors errors = new ActionErrors();
					    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Juvenile  Record Not Found. Please enter a new number"));
					    saveErrors(aRequest, errors);
					    return aMapping.findForward(UIConstants.FAILURE);
				}
				else
				{
				    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
				    courtHearingForm.setJuvenileName(name.getFormattedName());

				    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
				    respEvent.setPreferredFirstName(juvProfile.getPreferredFirstName());
				    courtHearingForm.setProfileDetail(respEvent);
				}
				courtHearingForm.setProfileDetail(juvProfile);
				courtHearingForm.setOrigjuvNum(juvNo);
			    }
			}
			else
			{
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "CJIS Number entered is not valid"));
			    saveErrors(aRequest, errors);
			    return aMapping.findForward(UIConstants.FAILURE);
			}

		    }
		    else
		    {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Type Code"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		    }
	    Collection<PetitionResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetailsforPetCJISSearch(juvNum, typeCode);

	    Collections.sort((List<PetitionResponseEvent>) referralList, Collections.reverseOrder(new Comparator<PetitionResponseEvent>() {
		@Override
		public int compare(PetitionResponseEvent evt1, PetitionResponseEvent evt2)
		{
		    if (evt1.getReferralNum() != null && evt2.getReferralNum() != null)
			return evt1.getReferralNum().compareTo(evt2.getReferralNum());//bug 160275
		    else
			return -1;
		}
	    }));

	    courtHearingForm.setPetitionList(referralList);
	    courtHearingForm.setSearchResultSize(referralList.size());
	    if (referralList.size() > 0)
	    {
		courtHearingForm.setJuvenileNumber(juvNum);
		courtHearingForm.setTypeCode(typeCode);
		return aMapping.findForward(UIConstants.COURT_PETCJIS_SUMMARY);
	    }
	    else
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No records found"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	else
	    return aMapping.findForward(UIConstants.FAILURE);

    }
    /**
     * ancillaryCourtActivity
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward ancillaryCourtActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String petitionNum = courtHearingForm.getJuvenileNumber();
	courtHearingForm.setPetitionNumber(petitionNum);
	courtHearingForm.setAction("ancillaryCourtActivity");
	Iterator<JJSCLAncillary> ancillaryDocketsItr = JJSCLAncillary.findAll("petitionNumber", petitionNum);
	List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();
	while (ancillaryDocketsItr.hasNext())
	{
	    JJSCLAncillary ancillaryCrt = (JJSCLAncillary) ancillaryDocketsItr.next();
	    if (ancillaryCrt != null)
	    {
		DocketEventResponseEvent resp = ancillaryCrt.valueObject();
		resp.setCourtDate(DateUtil.dateToString(resp.getEventDate(), "MM/dd/yyyy"));
		//time conversion is done here and not the Submit Action to avoid error if the user 
		//clicks back button from the Update page and then the clicks the Ancillary Update button 
		String crtTime = resp.getCourtTime();
		String editedCrtTime = crtTime.substring(0, 5);
		resp.setCourtTime(JuvenileFacilityHelper.stripColonInDate(editedCrtTime));
		if (resp.getDisposition() != null)
		{
		    JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha",resp.getDisposition());
		    if (juvenileDispositionCode != null)
		    {
			resp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
		    }
		}
		else
		    if (resp.getCourtResult() != null)
		    {
			JuvenileDispositionCode juvenileDispositionCode = JuvenileDispositionCode.find("codeAlpha",resp.getCourtResult());
			resp.setDispositionDesc(juvenileDispositionCode.getShortDesc());
		    }
		if (resp != null)
		{
		    dockets.add(resp);
		}
	    }
	}
	courtHearingForm.setSearchResultSize(dockets.size());

	if (dockets.size() == 0)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Setting Records Found For This Petition Number"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.ANCILLARY_COURT_ACTIVITY);

	}
	//Sort the located records in descending ChainNumber and ChainSequenceNumber order.
	// sort by highest sequence number
	Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
	    {
		if (evt1.getSeqNum() != null && evt2.getSeqNum() != null)
		    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
		else
		    return -1;
	    }
	}));
	// sort by highest chain number
	Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
	    {
		if (evt1.getChainNumber() != null && evt2.getChainNumber() != null)
		    return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
		else
		    return -1;
	    }
	}));
	courtHearingForm.setDktSearchResults(dockets);
	// sort by most recent court date //to make the Ancillary Update
	Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
	    {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date1 = new Date();
		Date date2 = new Date();
		try
		{
		    date1 = sdf.parse(evt1.getCourtDate());
		}
		catch (ParseException e)
		{
		    e.printStackTrace();
		}
		try
		{
		    date2 = sdf.parse(evt2.getCourtDate());
		}
		catch (ParseException e)
		{
		    e.printStackTrace();
		}
		return new CompareToBuilder().append(date2, date1).toComparison();
	    }
	});
	//get the docket with the most recent court date //to make the Ancillary Update
	if (!dockets.isEmpty())
	{
	    courtHearingForm.setSelectedDocket(dockets.get(0));
	}
	return aMapping.findForward(UIConstants.ANCILLARY_COURT_ACTIVITY);
    }

    /**
     * Court Activity By Youth
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward youthCourtActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	if(courtHearingForm.getReferralNumber()!=null)
	{
        	if(!courtHearingForm.getReferralNumber().equalsIgnoreCase("pet"))
        	{
                	String juvenileNum = aRequest.getParameter("juvnum");
                	String fromPage = aRequest.getParameter("fromPage");
                	courtHearingForm.setFromPage(fromPage);
                	
                	if (juvenileNum != null && !"".equals(juvenileNum))
                	{
                	    courtHearingForm.setJuvenileNumber(juvenileNum.trim());
                	    aRequest.removeAttribute("juvnum");
                	}
                	 try
                	    {
                		int tempNo = Integer.parseInt( courtHearingForm.getJuvenileNumber() );
                	    }
                	    catch (NumberFormatException e)
                	    {
                		    ActionErrors errors = new ActionErrors();
                		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Number entered is not a valid Juvenile Number"));
                		    saveErrors(aRequest, errors);
                		    return aMapping.findForward(UIConstants.FAILURE);
                	    }
        	}
        	else
        	{
        	    //retrieve juvnum from JJSMSPETITION 
        	    String petitionNum=courtHearingForm.getJuvenileNumber();
        	    GetDetailsbyPetitionNumEvent petitionEvt = (GetDetailsbyPetitionNumEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETDETAILSBYPETITIONNUM);
            	    petitionEvt.setPetitionNum(petitionNum);
            	    dispatch.postEvent(petitionEvt);
            	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
            	    List<PetitionResponseEvent> petresEvts = MessageUtil.compositeToList(compositeResponse, PetitionResponseEvent.class);
            	    PetitionResponseEvent petition = new PetitionResponseEvent();
            	    if (petresEvts.size()>0)
            	    {
            		petition = petresEvts.get(0);
            		String juvNum=petition.getJuvenileNum();
            		courtHearingForm.setJuvenileNumber(juvNum);
            	    }
            	    else
            	    {
            		ActionErrors errors = new ActionErrors();
        		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Petition Number entered is not valid."));
        		saveErrors(aRequest, errors);
        		return aMapping.findForward(UIConstants.FAILURE);
            	    }
        	}
	}
	else
	{
	    String juvenileNum = aRequest.getParameter("juvnum");	
	    courtHearingForm.setReferralNumber("");
        	if (juvenileNum != null && !"".equals(juvenileNum))
        	{
        	    courtHearingForm.setJuvenileNumber(juvenileNum.trim());
        	    aRequest.removeAttribute("juvnum");
        	}
        	 try
        	    {
        		int tempNo = Integer.parseInt( courtHearingForm.getJuvenileNumber() );
        	    }
        	    catch (NumberFormatException e)
        	    {
        		    ActionErrors errors = new ActionErrors();
        		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Number entered is not a valid Juvenile Number"));
        		    saveErrors(aRequest, errors);
        		    return aMapping.findForward(UIConstants.FAILURE);
        	    }
	}
	    
	 
	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(courtHearingForm.getJuvenileNumber());
	 
	request.setJuvenileNum(courtHearingForm.getJuvenileNumber());

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	if (juvenileResp != null && juvenileResp.getJuvenileNum() != null)
	{

	    Name name = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName());

	    courtHearingForm.setJuvenileName(name.getFormattedName());
	    courtHearingForm.setRaceId(  nvl(juvenileResp.getOriginalRaceId(), juvenileResp.getRaceId() ) );
	    courtHearingForm.setSexId(juvenileResp.getSexId());
	    courtHearingForm.setRectype(juvenileResp.getRectype());

	    if (juvenileResp.getBirthDate() != null)
	    { // Get age based on
	      // year
		int age = UIUtil.getAgeInYears(juvenileResp.getBirthDate());
		if (age > 0)
		{
		    courtHearingForm.setJuvAge(String.valueOf(age));
		}
		else
		{
		    courtHearingForm.setJuvAge(UIConstants.EMPTY_STRING);
		}
	    }

	    GetCourtActivityByYouthEvent courtEvt = (GetCourtActivityByYouthEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCOURTACTIVITYBYYOUTH);
	    courtEvt.setJuvenileNum(courtHearingForm.getJuvenileNumber());

	    List<DocketEventResponseEvent> docketRespList = MessageUtil.postRequestListFilter(courtEvt, DocketEventResponseEvent.class);

	    Collections.sort((List<DocketEventResponseEvent>) docketRespList, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return new CompareToBuilder().append(Integer.valueOf(evt1.getChainNumber()), Integer.valueOf(evt2.getChainNumber())).append(Integer.valueOf(evt1.getSeqNum()), Integer.valueOf(evt2.getSeqNum())).toComparison();
		}
	    }));
	    
	   
	    if ( docketRespList != null ) {
		for ( DocketEventResponseEvent resp : docketRespList ) {
		   
		    if ( "TRNDSP".equals(resp.getPetitionAllegation()) 
				   || "TRNSIN".equals(resp.getPetitionAllegation())
				   || "REGION".equals(resp.getPetitionAllegation())
			           || "ISCOIN".equals(resp.getPetitionAllegation())
			       ){
			
			       for ( JuvenileCasefileTransferredOffenseResponseEvent transferredOffense : transferredOffenses ) {
				   if ( resp.getReferralNum().equals(transferredOffense.getReferralNum()) ) {
				       resp.setPetitionAllegationDesc(transferredOffense.getOffenseShortDesc() + "-" + transferredOffense.getCategory() );
				   }
			       }
			  
		    }
		}
	    }

	    JuvenileProfileDetailResponseEvent respEvent = new JuvenileProfileDetailResponseEvent();
	    respEvent.setPreferredFirstName(juvenileResp.getPreferredFirstName() );
	    courtHearingForm.setProfileDetail(respEvent);
	    courtHearingForm.setDktSearchResults(docketRespList);
	    courtHearingForm.setSearchResultSize(docketRespList.size());
	    
	    //User story 156683
	    JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(courtHearingForm.getJuvenileNumber());
	    if (facilityHeaderResp != null ) {
		courtHearingForm.setFacilityHeader(facilityHeaderResp);
		courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	    }
	}
	else
	{

	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Records Found For Juvenile Number"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	courtHearingForm.setAction("ancillaryCourtActivity");
	courtHearingForm.setReferralNumber("");
	return aMapping.findForward("youthCourtActivity");
    }

    /**
     * CANCEL
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "courtMainMenu");
	keyMap.put("button.attorneySettingInquiry", "attorneySettingSearch");
	keyMap.put("button.GALattorneySettingInquiry", "GALattorneySettingSearch");
	keyMap.put("button.juvenileSettingInquiry", "juvenileSettingSearch");
	keyMap.put("button.courtDocketDisplay", "courtDocketDisplay");
	keyMap.put("button.courtAction", "courtAction");
	keyMap.put("button.addAncillarySetting", "addAncillarySetting");
	keyMap.put("button.displayAncillarySetting", "displayAncillarySetting");
	keyMap.put("button.initialSetting", "initialSetting");
	keyMap.put("button.petitionUpdate", "petitionUpdate");
	keyMap.put("button.masterReferralDisplay", "masterBriefingInquiry"); //master Briefing Inquiry
	keyMap.put("button.numberInquiry", "numberInquiry");
	keyMap.put("button.nameSearch", "nameSearch");
	keyMap.put("button.ancillaryCourtActivity", "ancillaryCourtActivity");
	keyMap.put("button.referralInquiry", "referralInquiry");
	keyMap.put("button.courtActivityByYouth", "youthCourtActivity");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.PetCJISSearch", "PetCJISSearch");
	keyMap.put("button.DALognumSearch", "DALogNumSearch");

    }

    /**
     * isEmpty
     * 
     * @param str
     * @return
     */
    private boolean isEmpty(String str)
    {
	return str == null || str.isEmpty();
    }

    /**
     * isNotEmpty
     * 
     * @param str
     * @return
     */
    private boolean isNotEmpty(String str)
    {
	return !isEmpty(str);
    }
    
    private String  nvl( String value1, String value2 ) {
	return (value1 != null && value1.length() >0 ) ? value1: value2;
    }
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefileFeeSelectionAction.java

package ui.juvenilecase.districtCourtHearings.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByReferreeCourtEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByChainAndSeqNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByRefereeCourtEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCourtActivityByYouthEvent;
import messaging.juvenilecase.GetDistrictCourtActivityByYouthEvent;
import messaging.juvenilecase.GetJJSJuvenileEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
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
import naming.PDCodeTableConstants;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSReferral;
import pd.km.util.Name;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class HandleJuvenileDistrictCourtActivityByYouthAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */

    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.courtMainMenu", "courtMainMenu");
	keyMap.put("button.go", "youthCourtActivity");
	keyMap.put("button.masterDisplay", "briefingDetails");
	keyMap.put("button.initialSettingBtn", "initialSetting");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.courtAction2", "courtAction");

    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileDistrictCourtActivityByYouthAction()
    {

    }

    /**
     * courtMainMenu
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward courtMainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.reset();
	form.setCourtDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
	form.setAction("initialSetting");
	return aMapping.findForward(UIConstants.COURT_MAIN_MENU);
    }

    @SuppressWarnings("rawtypes")
    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAlphaCodeId(UIConstants.EMPTY_STRING);
	form.setShortDesc(UIConstants.EMPTY_STRING);
	form.setDpsCodeId(UIConstants.EMPTY_STRING);
	form.setCategoryId(UIConstants.EMPTY_STRING);
	form.setOffenseResultList(new ArrayList());
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setPetitionAllegationSev(UIConstants.EMPTY_STRING);
	return aMapping.findForward("success");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("validateOffenseCd");
	form.setValidateMsg("");
	List wrkList = new ArrayList(form.getOffenseResultList());
	String selVal = form.getSelectedValue();
	for (int x = 0; x < wrkList.size(); x++)
	{
	    JuvenileCasefileOffenseCodeResponseEvent jcoEvent = (JuvenileCasefileOffenseCodeResponseEvent) wrkList.get(x);
	    if (selVal.equals(jcoEvent.getAlphaCode()))
	    {
		form.setPetitionAllegation(jcoEvent.getAlphaCode());
		form.setPetitionAllegationDesc(jcoEvent.getShortDescription());
		form.setPetitionAllegationSev(jcoEvent.getSeverity());
		break;
	    }
	}
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	selVal = null;
	wrkList = null;
	form.setOffenseResultList(null);
	String forwardStr = "successInitialSetting";
	return aMapping.findForward(forwardStr);
    }

    /**
    * 
    */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.BACK;
	return aMapping.findForward(forwardStr);
    }

    /**
    * 
    */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.CANCEL;
	return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward initialSetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	String data = aRequest.getParameter("selectedVal");
	//335884|1030
	String juvenileNum = data.substring(0, 6);
	String referralNumber = data.substring(7);
	courtHearingForm.reset(); //clear all other fields.

	//reset the juv and ref num
	courtHearingForm.setAction("initialSetting");
	courtHearingForm.setJuvenileNumber(juvenileNum);
	courtHearingForm.setReferralNumber(referralNumber);

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
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName());
	    courtHearingForm.setJuvenileName(name.getFormattedName());
	}

	// Get Juvenile Profile Information
	courtHearingForm.setProfileDetail(juvProfile);
	JuvenileFacilityHeaderResponseEvent facilityHeaderResp = JuvenileFacilityHelper.getJuvFacilityHeaderDetails(juvenileNum);
	if (facilityHeaderResp != null)
	{
	    //pull status from jjs header.Bug fix #57130
	    courtHearingForm.setFacilityHeader(facilityHeaderResp);
	    courtHearingForm.setFacilityStatus(facilityHeaderResp.getFacilityStatus());
	    courtHearingForm.setFacilityStatusDesc(JuvenileFacilityHelper.getCodeTableDescription(PDCodeTableConstants.FACILITY_STATUS, facilityHeaderResp.getFacilityStatus()));

	    //pull detention facility from jjs detention.Bug fix #57130 
	    JJSFacility facility = JuvenileFacilityHelper.getCurrentFacilityRecord(juvenileNum);
	    courtHearingForm.setDetainedFacility(facility.getDetainedFacility());
	    courtHearingForm.setDetainedFacilityDesc(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_DETENTION_FACILITY, facility.getDetainedFacility()));

	}

	// get referral details:
	JuvenileProfileReferralListResponseEvent profileRefEvent = JuvenileFacilityHelper.getJuvReferralDetails(juvenileNum, referralNumber);
	if (profileRefEvent != null)
	{
	    courtHearingForm.setReferralDate(DateUtil.dateToString(profileRefEvent.getReferralDate(), DateUtil.DATE_FMT_1));// set referral date for the filing date validation.
	    if (profileRefEvent.getCloseDate() == null)
	    {
		petitionResponses = InterviewHelper.getPetitionsRespForReferral(juvenileNum, referralNumber);
	    }
	    else
	    {
		/*If the CloseDate variable on the Referral record is populated, display �INITIAL SETTING NOT LOGICAL FOR THIS REFERRAL- REFERRAL CLOSED ON {CloseDate}.�   User cannot modify or populate any field.   Set PROT.IND (temp) = �X.�*/
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Initial Setting not logical for this referral - referral closed on " + DateUtil.dateToString(profileRefEvent.getCloseDate(), DateUtil.DATE_FMT_1)));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No referral found for the referral number entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	// get petition Information from ms petition sql.
	int petitionsCount = 0;

	if (petitionResponses != null && !petitionResponses.isEmpty())
	{
	    Iterator<PetitionResponseEvent> petitionIter = petitionResponses.iterator();
	    while (petitionIter.hasNext())
	    {
		petitionsCount++;
		PetitionResponseEvent respEvent = petitionIter.next();
		if (respEvent != null && respEvent.getSequenceNum().equals("1"))
		{
		    courtHearingForm.setPetitionStatus(respEvent.getPetitionStatus());
		    courtHearingForm.setFilingDate(DateUtil.dateToString(respEvent.getPetitionDate(), DateUtil.DATE_FMT_1));
		    courtHearingForm.setPetitionType(respEvent.getPetitionType());
		    courtHearingForm.setPetitionNumber(respEvent.getPetitionNum());
		    courtHearingForm.setPetitionAmendment(respEvent.getAmend());
		    courtHearingForm.setPetitionAllegation(respEvent.getOffenseCodeId());
		}
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
		    /*  if(crtDocResp.getPetitionAmendmentDate()==null){
		    courtHearingForm.setAmendmentDate(DateUtil.dateToString(DateUtil.getCurrentDate(), DateUtil.DATE_FMT_1));
		      }*/
		    courtHearingForm.setAction("subpoenas"); //court and petition Record already exists.// bug.
		    courtHearingForm.setJuvAge(crtDocResp.getAge());
		    break;
		}
	    }
	}
	else
	{
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
	//courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadActiveHearingTypes());//commented for US 157620
	courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesonlyActive());

	Collections.swap((List<CodeResponseEvent>) courtHearingForm.getSubpoenasToBePrinted(), 0, 1);//Juv,Father,Mother,Other [to maintain order]

	List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(juvenileDatesFormattedList);

	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    public ActionForward briefingDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setFinalReleaseDate(""); //Bug 97389 Sticky Release Date
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

	Collection<JuvenileDetentionFacilitiesResponseEvent> facilityHistoryList = JuvenileFacilityHelper.getJuvFacilityDetails(courtHearingForm.getJuvenileNumber(), null);
	if (facilityHistoryList != null)
	{
	    // re-sort the child list based on facility seq num.
	    Collections.sort((List<JuvenileDetentionFacilitiesResponseEvent>) facilityHistoryList, Collections.reverseOrder(JuvenileDetentionFacilitiesResponseEvent.DetentionRespEventComparator));
	    //set offense information.
	    Iterator<JuvenileDetentionFacilitiesResponseEvent> facilityHistIterator = facilityHistoryList.iterator();
	    if (facilityHistIterator.hasNext())
	    { //iterate detention recs.
		JuvenileDetentionFacilitiesResponseEvent detRec = facilityHistIterator.next();
		if (detRec != null)
		{
		    if (detRec.getReleaseDate() != null && detRec.getReleaseDateStr() != null && !detRec.getReleaseDateStr().isEmpty())
		    {
			courtHearingForm.setFinalReleaseDate(detRec.getReleaseDateStr());
		    }
		}
	    }//

	} //end of if //88723
	//bug fix for 151520
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
        //
	this.setDualStatus(courtHearingForm);

	return aMapping.findForward(UIConstants.BRIEFING_DETAILS);
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
	courtHearingForm.clearForm();
	courtHearingForm.clear();

	String juvenileNum = aRequest.getParameter("juvnum");

	if (juvenileNum != null && !"".equals(juvenileNum))
	{

	    courtHearingForm.setJuvenileNumber(juvenileNum);
	    aRequest.removeAttribute("juvnum");
	}

	GetJJSJuvenileEvent request = (GetJJSJuvenileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILE);
	request.setJuvenileNum(courtHearingForm.getJuvenileNumber());

	JJSJuvenileResponseEvent juvenileResp = (JJSJuvenileResponseEvent) MessageUtil.postRequest(request, JJSJuvenileResponseEvent.class);

	if (juvenileResp != null && juvenileResp.getJuvenileNum() != null)
	{

	    Name name = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName());

	    courtHearingForm.setJuvenileName(name.getFormattedName());
	    courtHearingForm.setRaceId(nvl(juvenileResp.getOriginalRaceId(), juvenileResp.getRaceId()));
	    courtHearingForm.setSexId(juvenileResp.getSexId());

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

	    List docketRespList = MessageUtil.postRequestListFilter(courtEvt, DocketEventResponseEvent.class);

	    Collections.sort((List<DocketEventResponseEvent>) docketRespList, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return new CompareToBuilder().append(Integer.valueOf(evt1.getChainNumber()), Integer.valueOf(evt2.getChainNumber())).append(Integer.valueOf(evt1.getSeqNum()), Integer.valueOf(evt2.getSeqNum())).toComparison();
		}
	    }));

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
	return aMapping.findForward("success");
    }

    public ActionForward courtAction(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	GetDistrictCourtActivityByYouthEvent courtEv = (GetDistrictCourtActivityByYouthEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETDISTRICTCOURTACTIVITYBYYOUTH);
	courtEv.setJuvenileNum(courtHearingForm.getJuvenileNumber());
	courtHearingForm.clearForm();
	courtHearingForm.clear();
	courtHearingForm.setAction("submitCourtAction");
	courtHearingForm.setDktSearchResults(Collections.EMPTY_LIST); //empty the list

	List docketRespList = MessageUtil.postRequestListFilter(courtEv, DocketEventResponseEvent.class);

	Collections.sort((List<DocketEventResponseEvent>) docketRespList, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    @Override
	    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
	    {
		return new CompareToBuilder().append(Integer.valueOf(evt1.getChainNumber()), Integer.valueOf(evt2.getChainNumber())).append(Integer.valueOf(evt1.getSeqNum()), Integer.valueOf(evt2.getSeqNum())).toComparison();
	    }
	}));
	if (docketRespList != null && docketRespList.size() > 0)
	{
	    DocketEventResponseEvent response = (DocketEventResponseEvent) docketRespList.get(0);
	    courtHearingForm.setCourtId(response.getCourt());
	    courtHearingForm.setCourtDate(response.getCourtDate());

	    String courtId = response.getCourt();
	    String date = response.getCourtDate();

	    List<DocketEventResponseEvent> dockets = null;
	    Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	    Map<String, DocketEventResponseEvent> originalDocketsMap = new HashMap<String, DocketEventResponseEvent>();

	    // check if it is a valid court Id's
	    if (courtId != null && !courtId.isEmpty())
	    {
		//Settings scheduled in Juvenile Referee courts cannot be retrieved using this option.
		/*if (courtId.equals("001"))
		{
		    courtId = "1";
		}
		else
		{
		    if (courtId.equals("002"))
		    {
			courtId = "2";
		    }
		}*/
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
		//83426
		List<JuvenileDispositionCodeResponseEvent> juvenileDispositions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
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
					    dispatch.postEvent(courtEvt);

					    CompositeResponse courtResp = (CompositeResponse) dispatch.getReply();
					    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(courtResp, DocketEventResponseEvent.class);
					    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResponses.iterator();

					    while (docketResponsesItr.hasNext())
					    {
						DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
						if (docResp != null)
						{
						    docRespEvent.setActionDate(docResp.getCourtDate());
						    docRespEvent.setActionTime(JuvenileFacilityHelper.stripColonInDate(docResp.getCourtTime()));
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
			    } // juvenile no check
			}
		    }
		    courtHearingForm.setCourtHearingTypes(JuvenileDistrictCourtHelper.loadActiveHearingTypes("COURT"));
		}

		//GET JJSCLDETENTION RECORDS
		/*Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDecisionsMap = new HashMap<String, JuvenileCourtDecisionCodeResponseEvent>();
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
					    detentionEvt.setChainNumber(docRespEvent.getChainNumber());
					    detentionEvt.setSeqNumber(String.valueOf(Integer.valueOf(docRespEvent.getSeqNum()) + 10));
					    dispatch.postEvent(detentionEvt);

					    CompositeResponse detnResp = (CompositeResponse) dispatch.getReply();
					    List<DocketEventResponseEvent> docketResponses = MessageUtil.compositeToList(detnResp, DocketEventResponseEvent.class);
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
			    { //do not set as in m204
				docRespEvent.setActionDate("");
				docRespEvent.setActionTime("");
			    }

			    if (docRespEvent.getJuvenileNumber() != null && docRespEvent.getReferralNum() != null)
			    {
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
		    courtHearingForm.setCourtDecisionsList(courtDecisionsList);
		    courtHearingForm.setDetentionHearingResults(courtDecisionResponseEvts);
		} *///if
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
		    courtHearingForm.setPagerOffset("0");

		    List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
		    courtHearingForm.setHolidaysList(juvenileDatesFormattedList);

		    List<JuvenileDispositionCodeResponseEvent> courtDecisions = JuvenileDistrictCourtHelper.getSortedCourtDecisions();
		    courtHearingForm.setCourtDecisionsResponses(courtDecisions);
		    List<JuvenileDispositionCodeResponseEvent> courtDispDecisions = JuvenileDistrictCourtHelper.getCourtDispositionCodeTypeDorB();// BUG 169937 changes starts
		    courtHearingForm.setCourtDispositionResponses(courtDispDecisions);
		    List<JuvenileDispositionCodeResponseEvent> courtResultDecisions = JuvenileDistrictCourtHelper.getCourtResultCodeTypeRorB();
		    courtHearingForm.setCourtResultResponses(courtResultDecisions); // BUG 169937 changes ENDS

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
	else
	{
	    courtHearingForm.setAction("error");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No district court dockets found"));
	    saveErrors(aRequest, errors);
	    courtHearingForm.setCursorPosition("courtId");
	    return aMapping.findForward(UIConstants.FAILURE);
	}
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

    private String nvl(String value1, String value2)
    {
	return (value1 != null && value1.length() > 0) ? value1 : value2;
    }

    /*
     * Calculate Dual Status
     * Set dual status to the form
     */
    private void setDualStatus( CourtHearingForm courtHearingForm ){
	
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
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
	if (juvenileTraits != null && juvenileTraits.size() > 0)
	{
	    for (JuvenileTraitResponseEvent juvenileTrait : juvenileTraits)
	    {
		//filter for current vs former
		if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && "CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		{
		    courtHearingForm.setDualStatus("DS");
		    break;
		}
		else
		{
		    if ("DST".equalsIgnoreCase(juvenileTrait.getTraitTypeId()) && !"CU".equalsIgnoreCase(juvenileTrait.getStatusId()))
		    {
			courtHearingForm.setDualStatus("FDS");
			break;

		    }
		}
	    }
	}// end of if
    }
}

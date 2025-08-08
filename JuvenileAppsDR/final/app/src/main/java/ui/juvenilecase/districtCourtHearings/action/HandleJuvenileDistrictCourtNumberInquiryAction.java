//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\referral\\action\\HandleJuvenileCasefileFeeSelectionAction.java

package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.districtCourtHearings.reply.NumberInquiryResponse;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetCasefileWithReferralsEvent;
import messaging.juvenilecase.GetJJSJuvenileInfoEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.JJSJuvenileInfoResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileOffenseCodeResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralDetailResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.juvenilewarrant.reply.PetitionResponseEvent;
import messaging.referral.GetJJSOffenseByInvestnoEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import pd.codetable.criminal.PDCriminalCodeTableHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.interviewinfo.InterviewHelper;
import pd.juvenilecase.referral.JJSOffense;
import pd.km.util.Name;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.SimpleCodeTableHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;

public class HandleJuvenileDistrictCourtNumberInquiryAction extends JIMSBaseAction
{
    /* (non-Javadoc)
    * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
    */

    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.courtMainMenu", "courtMainMenu");
	keyMap.put("button.go", "numberInquiry");
	keyMap.put("button.masterDisplay", "briefingDetails");
	keyMap.put("button.initialSettingBtn", "initialSetting");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.cancel", "cancel");

    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileDistrictCourtNumberInquiryAction()
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
	    Name name = new Name(juvProfile.getFirstName(), juvProfile.getMiddleName(), juvProfile.getLastName(), juvProfile.getNameSuffix());
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
	//offense detail
	JJSOffense offenseDetail = InterviewHelper.getOffenseForReferral(juvenileNum, referralNumber);
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum);
	//List<JJSOffense>offenseDetails = InterviewHelper.getOffensesForReferral(juvenileNum, referralNumber);
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
	//
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
	courtHearingForm.setHearingTypes(PDCriminalCodeTableHelper.loadHearingTypesonlyActive());//changed for US 157620

	Collections.swap((List<CodeResponseEvent>) courtHearingForm.getSubpoenasToBePrinted(), 0, 1);//Juv,Father,Mother,Other [to maintain order]

	List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(juvenileDatesFormattedList);

	return aMapping.findForward(UIConstants.INITIAL_SETTING_DISPLAY);
    }

    public ActionForward briefingDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	if (courtHearingForm.getJuvenileNumber() == null || courtHearingForm.getJuvenileNumber().equals(""))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Juvenile Number entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);
	String data = aRequest.getParameter("selectedVal");
	//335884
	String juvenileNum = data.substring(0, 6);
	searchEvent.setJuvenileNum(juvenileNum);
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

		List<Entry<String, String>> sortedEntries = new ArrayList<Entry<String, String>>(petitionMap.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<String, String>>() {
		    @Override
		    public int compare(Entry<String, String> e1, Entry<String, String> e2)
		    {
			return e2.getKey().compareTo(e1.getKey());
		    }
		});

		Iterator iter = sortedEntries.iterator();
		while (iter.hasNext())
		{

		    Entry pair = (Entry) iter.next();

		    String caseStatusCd = "";
		    String statusDesc = "";
		    numberResponse = new NumberInquiryResponse();
		    Name nameJuvenile = null;

		    GetJJSJuvenileInfoEvent request = (GetJJSJuvenileInfoEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSJUVENILEINFO);
		    request.setJuvenileNum(pair.getValue().toString());

		    JJSJuvenileInfoResponseEvent juvenileResp = (JJSJuvenileInfoResponseEvent) MessageUtil.postRequest(request, JJSJuvenileInfoResponseEvent.class);

		    if (juvenileResp != null)
		    {
			caseStatusCd = juvenileResp.getStatusId();
			statusDesc = CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, juvenileResp.getStatusId());

			nameJuvenile = new Name(juvenileResp.getFirstName(), juvenileResp.getMiddleName(), juvenileResp.getLastName(), "");
		    }

		    GetCasefileWithReferralsEvent req = (GetCasefileWithReferralsEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEWITHREFERRALS);
		    req.setJuvenileNum(pair.getValue().toString());
		    req.setReferralNum(pair.getKey().toString());

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
	msg = "success";

	return aMapping.findForward(msg);
    }

}

package ui.juvenilecase.detentionCourtHearings.action;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.codetable.GetJuvenileCourtsEvent;
import messaging.codetable.criminal.reply.JuvenileCourtDecisionCodeResponseEvent;
import messaging.codetable.criminal.reply.JuvenileCourtResponseEvent;
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.detentionCourtHearings.DeleteFutureJJSCLDetentionSettingEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainAndSeqNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByChainEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDetentionCourtEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import messaging.detentionCourtHearings.UpdateJJSCLDetentionEvent;
import messaging.districtCourtHearings.UpdateJJSHeaderFromDetentionCourtEvent;
import messaging.districtCourtHearings.UpdateJJSHeaderFromDistrictCourtEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.GetJJSDetentionbyOIDEvent;
import messaging.juvenilecase.SaveJJSLastAttorneyEvent;
import messaging.juvenilecase.UpdateJJSLastAttorneyGALEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileTransferredOffenseResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.context.multidatasource.Home;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.persistence.IHome;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import mojo.messaging.mailrequestsevents.SendEmailEvent;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import pd.contact.officer.OfficerProfile;
import pd.contact.officer.PDOfficerProfileHelper;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;
import pd.juvenilecase.referral.JJSOffense;
import pd.juvenilewarrant.JJSPetition;
import ui.action.JIMSBaseAction;
import ui.common.Name;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.detentionCourtHearings.DetentionHearingDocketBean;
import ui.juvenilecase.detentionCourtHearings.form.DetentionHearingForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author sthyagarajan
 */
public class HandleJuvenileSearchDetentionHearingsAction extends JIMSBaseAction
{

    /**
	 * 
	 */
    public HandleJuvenileSearchDetentionHearingsAction()
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
     * @throws GeneralFeedbackMessageException
     */
    public ActionForward courtMainMenu(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
	CourtHearingForm courtHearingForm = (CourtHearingForm) getSessionForm(aMapping, aRequest, "courtHearingForm", true);
	courtHearingForm.reset();
	courtHearingForm.setCourtDate(null);
	List<JSONObject> countyHolidayList = JuvenileDistrictCourtHelper.juvenileHolidays();
	courtHearingForm.setHolidaysList(countyHolidayList);
	return aMapping.findForward("courtMainMenu");
    }

    /**
     * searchDetentionHearings
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward searchDetentionHearings(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setFacilities(JuvenileFacilityHelper.loadActiveFacilities());
	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * detentionHearingActionSearchResults
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward detentionHearingActionSearchResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setCourtId(form.getCourtId());
	form.setSearchResultSize(0);
	form.setDetentionSearchResults(new ArrayList<DocketEventResponseEvent>());
	form.setUpdatedDocketList(new ArrayList<DocketEventResponseEvent>());
	form.setUpdateDocketMap(null);
	form.setInsertDocketMap(null);
	aRequest.setAttribute("pager.offset", null);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);

	form.setCourtDecisions(JuvenileCaseHelper.getCourtDecisionsNew()); //fill in the court decisions. for the result field
	/**
	 * Call jjs detention using court and courtDate. The application
	 * retrieved the docket (JUVENILE_ DETENTION_HEARING), if any, set for
	 * the facility’s referee court on the user-identified court date.
	 */
	IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJJSCLDetentionByRefereeCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByRefereeCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYREFEREECOURT);
	jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(form.getDate(), DateUtil.DATE_FMT_1));
	jjsdetnCrtEvent.setCourtId("1");
	jjsdetnCrtEvent.setFacilityId(form.getFacility());
	disp.postEvent(jjsdetnCrtEvent);
	CompositeResponse resp = (CompositeResponse) disp.getReply();

	Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	List<DocketEventResponseEvent> dktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);
	if (dktRespEvts != null && !dktRespEvts.isEmpty())
	{
	    //sorts in descending order by seq num.
	    Collections.sort((List<DocketEventResponseEvent>) dktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return Integer.valueOf(evt1.getSeqNum()).compareTo(Integer.valueOf(evt2.getSeqNum()));
		}
	    }));

	    //Bug #69911 - DktSearchResultsMap was not getting set, so getting null pointer exception
	    Iterator<DocketEventResponseEvent> docketIter = dktRespEvts.iterator();

	    while (docketIter.hasNext())
	    {
		DocketEventResponseEvent docRespEvent = docketIter.next();
		//find the next rest to date
		if(docRespEvent.getResetTo()==null||docRespEvent.getResetTo().isEmpty())
		{
		   //check for all in detention higher and put next courtdate.
		  //check if any record with high seqnum exists for this kid and chain if there give the court date of that else below. in the search
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
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
		    boolean found=false;
		    while (docketResponsesItr.hasNext())
		    {			
			DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();
		    	int nextSeqnum=Integer.valueOf(docResp.getSeqNum());
		    			    	if (docResp != null && nextSeqnum > currentSeqnum)
		    	{
		    	    docRespEvent.setResetTo(docResp.getCourtDate());	
		    	    found=true;
		    	    break;
		    	}
		    }
		    if(found==false) 
		    {
			docRespEvent.setTransferTo(null);
			//docRespEvent.setCourtResult(null); //commented for task 173011 for bug 173010
		    }
		}
		//
		docketsMap.put(docRespEvent.getDocketEventId(), docRespEvent);
	    }
	    form.setSearchResultSize(dktRespEvts.size());
	    form.setDetentionSearchResults(dktRespEvts);
	    form.setDktSearchResultsMap(docketsMap);
	    /**
	     * TODO If a docket is located, retrieve the JUVENILE_MASTER,
	     * JUVENILE_JJS_DETENTION_HEARING, the
	     * JUVENILE_FACILITY_ADMISSION_HEADER, the JUVENILE_JJS_REFERRAL
	     * and, if available, the JUVENILE_JJS_PETITION record associated to
	     * the juvenile numbers and/or the referral number of the docket
	     * record.
	     */
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "NO DETENTION HEARINGS FOUND - CORRECT AND CONTINUE"));
	    saveErrors(aRequest, errors);

	    form.setDetentionSearchResults(new ArrayList());
	    form.setDktSearchResultsMap(new HashMap());
	    return aMapping.findForward(UIConstants.FAILURE);
	}

	return aMapping.findForward(UIConstants.COURT_ACTION);
    }

    /**
     * detentionHearingDisplaySearchResults
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward detentionHearingDisplaySearchResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;

	String selectedFacility = form.getFacility();
	String refereeCourt = "";
	String paddedCourt = "";
	form.clearHearingInfo();
	//reset the list
	form.reset();
	List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();

	/**
	 * The application will locate the referee court
	 * (JUVENILE_FACILITY.RefereeCourt) assigned to the user-designated
	 * facility. The application will retrieve the docket (JUVENILE_
	 * DETENTION_HEARING), SORTING: Default sorting of located records is a
	 * follows… Primary sort – descending alphabetical Hearing Type
	 * Secondary sort – ascending juvenile number within Hearing Type
	 * grouping
	 */
	// Get refereeCourt for facility
	if (StringUtils.isNotEmpty(form.getFacility()))
	{
	    JuvenileFacilityResponseEvent facilityRespEvt = JuvenileFacilityHelper.getFacilityByCode(form.getFacility());
	    if (facilityRespEvt != null)
	    {
		refereeCourt = facilityRespEvt.getRefereeCourt();
		form.setRefereeCourt(refereeCourt);
		form.setFacilityDesc(facilityRespEvt.getDescription());
		paddedCourt = String.format("%03d", Integer.parseInt(refereeCourt));
		form.setCourtId(paddedCourt);
	    }
	}
	else
	{
	    refereeCourt = "1";
	    form.setCourtId("001");
	}
	// On the report : In the 1st line of the document header, display the 
	// JUVENILE_ DETENTION_HEARING.AssignedCourt (a.k.a. Referee court for the facility used in the original search)
	form.setCourtId(String.format("%03d", Integer.parseInt(refereeCourt)));

	GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
	courtEvent.setCourt(refereeCourt);
	CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
	JuvenileCourtResponseEvent respEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);
	form.setAssignedJudge(respEvt.getJudgeName());

	/**
	 * Call jjs detention using court and courtDate. The application
	 * retrieved the docket (JUVENILE_ DETENTION_HEARING), if any, set for
	 * the facility’s referee court on the user-identified court date.
	 */
	IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);

	GetJJSCLDetentionByDetentionCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByDetentionCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYDETENTIONCOURT);
	jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(form.getDate(), DateUtil.DATE_FMT_1));
	jjsdetnCrtEvent.setCourtId(refereeCourt);

	List<DocketEventResponseEvent> dktRespEvts = MessageUtil.postRequestListFilter(jjsdetnCrtEvent, DocketEventResponseEvent.class);

	if (dktRespEvts != null && !dktRespEvts.isEmpty())
	{
	    /*Collections.sort((List<DocketEventResponseEvent>)dktRespEvts,Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
	    	@Override
	    	public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2) {
	    		return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
	    	}
	    }));*/

	    for (int i = 0; i < dktRespEvts.size(); i++)
	    {

		DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvts.get(i);

		//if(docRespEvent.getJuvenileNumber()!=null){
		//admit date
		JJSHeader header = JuvenileFacilityHelper.getJJSHeader(docRespEvent.getJuvenileNumber());
		if (header != null)
		{
		    /*If a hearing result {JUVENILE_JJS_DETENTION_HEARING.HearingDecision} or a hearing disposition
		    {JUVENILE_ JJS_DETENTION_HEARING.HearingDisposition} has been entered for a 
		    case on a docket, the case will not be listed in the search results.*/
		    String lastSeqNum = header.getLastSequenceNumber();
		    if (lastSeqNum != null)
		    {
			JJSFacility facility = JuvenileFacilityHelper.getJJSDetention(docRespEvent.getJuvenileNumber(), lastSeqNum);
			if (facility != null)
			{
			    if (facility.getOriginalAdmitDate() != null)
			    {
				docRespEvent.setAdmitDate(DateUtil.dateToString(facility.getOriginalAdmitDate(), DateUtil.DATE_FMT_1)); //admit date
			    }
			    else
			    {
				docRespEvent.setAdmitDate(DateUtil.dateToString(facility.getAdmitDate(), DateUtil.DATE_FMT_1)); //admit date
			    }
			    docRespEvent.setProbableCauseDate(DateUtil.dateToString(header.getProbableCauseDate(), DateUtil.DATE_FMT_1)); //probable cause Date
			}
		    }
		}

		docRespEvent.setLastCourtDate(StringUtils.EMPTY);//clear out the date and court so we can get it from JJSCLCOURT
		docRespEvent.setCourt(StringUtils.EMPTY);
		//The last Court Date in a regular District Court for which there is no Result or Disposition for the setting 
		GetJJSCourtEvent courtEvt = (GetJJSCourtEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJJSCOURT);
		courtEvt.setJuvenileNumber(docRespEvent.getJuvenileNumber());
		courtEvt.setReferralNumber(docRespEvent.getReferralNum());
		disp.postEvent(courtEvt);
		CompositeResponse response = (CompositeResponse) disp.getReply();
		//Court Response.
		List<DocketEventResponseEvent> crtdktRespEvts = MessageUtil.compositeToList(response, DocketEventResponseEvent.class);
		//sort by highest chain number
		Collections.sort((List<DocketEventResponseEvent>) crtdktRespEvts, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		    @Override
		    public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		    {
			if (evt1.getChainNumber() != null && evt2.getChainNumber() != null)
			    return Integer.valueOf(evt1.getChainNumber()).compareTo(Integer.valueOf(evt2.getChainNumber()));
			else
			    return -1;
		    }
		}));
		if (crtdktRespEvts != null && !crtdktRespEvts.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> crtdktRespEvtsItr = crtdktRespEvts.iterator();
		    while (crtdktRespEvtsItr.hasNext())
		    {
			DocketEventResponseEvent crtDocResp = (DocketEventResponseEvent) crtdktRespEvtsItr.next();
			if (crtDocResp != null && crtDocResp.getCourtResult() == null && crtDocResp.getDisposition() == null)
			{
			    docRespEvent.setLastCourtDate(crtDocResp.getLastCourtDate());
			    if (docRespEvent.getCourt() != null)
			    {

				docRespEvent.setCourt(String.format("%03d", Integer.parseInt(crtDocResp.getCourt())));

			    }
			    docRespEvent.setIssueFlag(crtDocResp.getIssueFlag());
			    docRespEvent.setHearingType(crtDocResp.getHearingType());
			    break;
			}
		    }
		}

		// find offense code
		//docRespEvent.setPetitionAllegationDesc(docRespEvent.getJuvenileNumber() +"/"+docRespEvent.getReferralNum() );
		GetJuvenileCasefileOffensesEvent jcoEvent = new GetJuvenileCasefileOffensesEvent();
		jcoEvent.setJuvenileNum(docRespEvent.getJuvenileNumber());
		jcoEvent.setReferralNum(docRespEvent.getReferralNum());

		List<JJSOffenseResponseEvent> jjsOffenses = MessageUtil.postRequestListFilter(jcoEvent, JJSOffenseResponseEvent.class);
		if (jjsOffenses.size() > 0)
		{
		    JJSOffenseResponseEvent offenseCode = (JJSOffenseResponseEvent) jjsOffenses.get(0);
		    docRespEvent.setPetitionAllegationDesc(offenseCode.getOffenseDescription());
		}
		//Get petition
		GetJJSPetitionsEvent petitionEvent = new GetJJSPetitionsEvent();
		petitionEvent.setJuvenileNum(docRespEvent.getJuvenileNumber());
		petitionEvent.setReferralNum(docRespEvent.getReferralNum());
		Iterator<JJSPetition> petitionIter = JJSPetition.findAll(petitionEvent);

		if (petitionIter != null && petitionIter.hasNext())
		{
		    JJSPetition petition = petitionIter.next();
		    if (petition != null)
		    {
			if (petition.getPetitionNum() != null)
			{
			    String fmtPetitionNum = petition.getType() + StringUtils.trimToEmpty(petition.getPetitionNum());
			    if (petition.getStatus() != null && "R".equalsIgnoreCase(petition.getStatus()))
			    {
				fmtPetitionNum += "R";
			    }
			    docRespEvent.setPetitionNumber(fmtPetitionNum); //petitionNum
			    docRespEvent.setPetitionAllegation(petition.getOffenseCodeId());
			    if (petition.getOffenseCode() != null)
				docRespEvent.setPetitionAllegationDesc(petition.getOffenseCode().getShortDescription());
			}
		    }
		}

		//get the JPO ID.
		JuvenileProfileReferralListResponseEvent profileResp = JuvenileFacilityHelper.getAllCasefilesForJuvNumRefNum(docRespEvent.getJuvenileNumber(), docRespEvent.getReferralNum());
		Collection<JuvenileCasefileReferral> casefilesResp = profileResp.getCasefileReferrals();

		Collections.sort((List<JuvenileCasefileReferral>) casefilesResp, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		    @Override
		    public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		    {
			if (evt1.getAssignmentDate() != null && evt2.getAssignmentDate() != null)
			    return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());
			else
			    return -1;
		    }
		}));
		Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
		if (casefileItr.hasNext())
		{
		    JuvenileCasefileReferral responseEvt = casefileItr.next();
		    if (responseEvt != null)
		    {
			OfficerProfile profile = responseEvt.getCaseFile().getProbationOfficer();
			docRespEvent.setProbationOfficerCd(profile.getLogonId());
			Name offName = new Name(profile.getFirstName(), profile.getMiddleName(), profile.getLastName());
			docRespEvent.setProbationOfficer(offName.toString());
		    }
		}

		dockets.add(docRespEvent);
		//} //juvenile no check
		//}
	    }//end of for loop
	     //sorts in descending order by issue flag.
	    Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    if (evt1.getHearingType() != null && evt2.getHearingType() != null)
			return evt2.getHearingType().compareTo(evt1.getHearingType());
		    else
			return -1;
		}
	    });

	    //sorts in ascending order by juvNum.
	    Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return Integer.valueOf(evt2.getJuvenileNumber()).compareTo(Integer.valueOf(evt1.getJuvenileNumber()));
		}
	    }));

	    form.setSearchResultSize(dockets.size());
	    form.setDetentionSearchResults(dockets);
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Detention Hearings for the Date/Facility Entered"));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	return aMapping.findForward(UIConstants.COURT_DOCKET);
    }

    /**
     * generateCourtDocket
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward generateCourtDocket(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	DetentionHearingDocketBean docketBean = new DetentionHearingDocketBean();
	docketBean.setDetentionSearchResults(form.getDetentionSearchResults());
	docketBean.setRefereeCourt(form.getRefereeCourt());
	docketBean.setHearingDate(form.getDate());
	docketBean.setAssignedDAName(form.getAssistantDA());
	docketBean.setHearingTime(form.getDocketTime());

	aRequest.getSession().setAttribute("reportInfo", docketBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.DETENTION_HEARING_DOCKET);
	// remove the pdf report attributes from session when finished saving to database
	aRequest.removeAttribute("isPdfSaveNeeded");
	aRequest.removeAttribute("pdfSavedReport");
	return null;
    }
    /**
     * SearchAttorney
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;

	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList());
	form.setAction("backDetentionHearing");
	/*form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);*/

	return aMapping.findForward("refresh");
    }

    /**
     * SearchAttorney
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;

	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList());
	String mainOffset = (String) aRequest.getParameter("mainOffset");
	//take from session
	if(mainOffset==null)
	    mainOffset = (String) aRequest.getSession().getAttribute("main_Offset");
	//form.setAction("search2ndAttorney");
	/*form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);*/
	HttpSession session = aRequest.getSession();
	session.setAttribute("main_Offset", mainOffset);

	return aMapping.findForward("attorneySearchSuccess");
    }

    /**
     * returnSelect
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	String forwardStr = "";
	String attyName = "";
	String barNum = "";
	String attyNameHist = "";
	form.setValidateMsg("");
	String pagerOffset = (String) aRequest.getSession().getAttribute("Pager_Offset"); //get pager offset from session.
	String mainOffset = (String) aRequest.getSession().getAttribute("main_Offset");
	List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
	String selVal = form.getSelectedValue();
	for (int x = 0; x < wrkList.size(); x++)
	{
	    AttorneyNameAndAddressResponseEvent jcoEvent = (AttorneyNameAndAddressResponseEvent) wrkList.get(x);
	    if (selVal.equals(jcoEvent.getBarNum()))
	    {
		barNum = jcoEvent.getBarNum();
		attyName = jcoEvent.getAttyName();
		attyNameHist = jcoEvent.getAttyNameHistory();
		break;
	    }
	}

	if ("searchGALCourtAction".equalsIgnoreCase(form.getAction()))
	{
	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	    if (respEvt != null)
	    {
		respEvt.setGalName(attyName);
		if (StringUtils.isNotEmpty(barNum))
		{

		    respEvt.setGalBarNum(padBarNumber(barNum));
		}
		form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (updtEvt != null)
		{

		    updtEvt.setGalName(attyName);
		    updtEvt.setGalBarNum(respEvt.getGalBarNum());
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		}
		else
		{
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		}
	    }

	}
	//r 2nd attorney details
	else if ("search2ndAttorney".equalsIgnoreCase(form.getAction()))
	{
	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	    if (respEvt != null)
	    {
		respEvt.setAttorney2Name(attyName);
		if (StringUtils.isNotEmpty(barNum))
		{

		    respEvt.setAttorney2BarNum(padBarNumber(barNum));
		}
		form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (updtEvt != null)
		{

		    updtEvt.setAttorney2Name(attyName);
		    updtEvt.setAttorney2BarNum(respEvt.getAttorney2BarNum());
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		}
		else
		{
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		}
	    }

	}
	else
	{

	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	    if (respEvt != null)
	    {
		respEvt.setAttorneyName(attyName);
		if (StringUtils.isNotEmpty(barNum))
		{

		    respEvt.setBarNum(padBarNumber(barNum));
		}
		form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (updtEvt != null)
		{

		    updtEvt.setAttorneyName(attyName);
		    updtEvt.setBarNum(respEvt.getBarNum());
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		}
		else
		{
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		}
	    }
	}

	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAction("");
	selVal = null;
	wrkList = null;
	form.setAttorneyDataList(null);
	//forwardStr = "backDetentionHearing";
	ActionForward af = aMapping.findForward("backDetentionHearing");
	String path = af.getPath();
	path = path + "?pager.offset=" + mainOffset;
	return new ActionForward(path);

    }

    /**
     * CANCEL
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	System.out.println("im here");
	return aMapping.findForward(UIConstants.CANCEL);
    }

    /**
     * findAttorney
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward findAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	String mainOffset = (String) aRequest.getParameter("mainOffset");
	//take from session
	if(mainOffset==null)
	    mainOffset = (String) aRequest.getSession().getAttribute("main_Offset");
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	//form.setAttorneyName(null);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	if (form.getAttorneyName() != null && !"".equals(form.getAttorneyName()))
	{

	    String attyName = form.getAttorneyName();
	    attyName = attyName.replaceAll("'", "''");
	    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	    request.setAttorneyName(trimAttorneyName(attyName));
	    dispatch.postEvent(request);

	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (error != null)
	    {
		sendToErrorPage(aRequest, error.getMessage());
		return aMapping.findForward("failure");
	    }

	    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);

	    if (attorneyDataList == null || attorneyDataList.isEmpty())
	    {
		attorneyDataList = new ArrayList<AttorneyNameAndAddressResponseEvent>();
	    }
	    Collections.sort((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList);
	    form.setAttorneyDataList(attorneyDataList);
	    if (attorneyDataList.size() == 0)
	    {
		form.setErrMessage("No records found for supplied search criteria");
	    }

	}
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	session.setAttribute("main_Offset", mainOffset);
	return aMapping.findForward("attorneySearchSuccess");
    }
    
    public ActionForward AttorneyConfirmation(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	String docketEventId = (String) aRequest.getParameter("docketEventId");
	DocketEventResponseEvent resp = null;
	Collection<DocketEventResponseEvent> dockets = form.getUpdatedDocketList();
	for (DocketEventResponseEvent currentEvent : dockets)
	{
	    if (currentEvent.getDocketEventId().equals(docketEventId))
	    {
		resp = currentEvent;
		break;
	    }
	}
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("AttorneyConfirmation");

	SaveJJSLastAttorneyEvent updatelstattorneyEvent = (SaveJJSLastAttorneyEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SAVEJJSLASTATTORNEY);
	
	if (resp != null)
	{
	    //bug fix for 148213 - task 148845 check if coc is null
	    if (StringUtils.isNotEmpty(resp.getAttorneyName()) && (resp.getAttorneyConnection() == null||resp.getAttorneyConnection().isEmpty()))
	    {

		    form.setErrMessage("ATTORNEY CONNECTION CODE MUST BE ENTERED.");
		    return aMapping.findForward(UIConstants.COURT_ACTION);
	    }
	    else
	    {
        	    updatelstattorneyEvent.setJuvenileNumber(resp.getJuvenileNumber());
        	    if (resp.getBarNum() == null)//resp.getBarNum().isEmpty()
        		updatelstattorneyEvent.setBarNumber(null);
        	    else
        		updatelstattorneyEvent.setBarNumber(resp.getBarNum());
        	    updatelstattorneyEvent.setAttorneyName(resp.getAttorneyName());
        	    if (resp.getAttorneyConnection() == null)
        	    {
        		//error message
        		updatelstattorneyEvent.setAttorneyConnection(null);
        	    }
        	    else
        		updatelstattorneyEvent.setAttorneyConnection(resp.getAttorneyConnection());
        	    updatelstattorneyEvent.setJjclcourtId(resp.getDocketEventId());
        	    updatelstattorneyEvent.setRecType("DETENTION");
        	    //task 165536
        	    if (resp.getGalBarNum() != null && !resp.getGalBarNum().isEmpty())
		    {
        		updatelstattorneyEvent.setGalName(resp.getGalName());
        		updatelstattorneyEvent.setGalbarNumber(resp.getGalBarNum());
		    }
        	    //
        	    dispatch.postEvent(updatelstattorneyEvent);
        	    CompositeResponse composite = (CompositeResponse) dispatch.getReply();
        
        	    MessageUtil.processReturnException(composite);
        	    return updateafterConfirm(aMapping, aForm, aRequest, aResponse, null);
	    }
	}
	return aMapping.findForward("backDetentionHearing");
    }

    public ActionForward AttorneyBypass(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	String docketEventId = (String) aRequest.getParameter("docketEventId");
	//String coc = (String) aRequest.getParameter("coc");
	DocketEventResponseEvent resp = null;
	Collection<DocketEventResponseEvent> dockets = form.getUpdatedDocketList();
	for (DocketEventResponseEvent currentEvent : dockets)
	{
	    if (currentEvent.getDocketEventId().equals(docketEventId))
	    {
		resp = currentEvent;
		break;
	    }
	}
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAction("AttorneyBypass");

	if (resp != null)
	{
	    return updateafterConfirm(aMapping, aForm, aRequest, aResponse, "YES");
	}
	return aMapping.findForward("backDetentionHearing");
    }

    public ActionForward searchGuardianAdlitem(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	//form.setAction("searchGALCourtAction");
	//Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	if (form.getDktSearchResultsMap().size() == 1 && form.getGalName() != null && form.getGalName().equalsIgnoreCase(""))
	{
	    Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
	    DocketEventResponseEvent onlyRespEvt = entry.getValue();
	    form.setGalName(onlyRespEvt.getAttorneyName());
	}
	form.setMessage(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }

    /**
     * validateBarNumber
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);

	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	if (form.getBarNum() != null && !form.getBarNum().isEmpty())
	{
	    CompositeResponse compositeResponse = validateBarNum(form);

	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
	    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (error != null || attorneyDataList.isEmpty())
	    {

		form.setErrMessage("Invalid Bar Number. Please Correct and Retry");
		form.setAction("validateBarNumber");
		form.setAttorneyName("");
		form.setCursorPosition("barNum");
		return aMapping.findForward("validationFailure");
	    }

	    if (attorneyDataList != null & !attorneyDataList.isEmpty())
	    {
		for (int x = 0; x < attorneyDataList.size();)
		{
		    AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		    attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		    attorneyRespEvt.setAttyName(respEvent.getAttyName());
		    attorneyRespEvt.setBarNum(respEvent.getBarNum());
		    attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		    break;
		}
	    }
	    if (attorneyRespEvt != null)
	    {
		form.setAction("validateBarNumber");
		//reset back on the object 
		if (form.getDcktEvtId() != null)
		{
		    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		    respEvt.setAttorneyName(attorneyRespEvt.getAttyName());
		    respEvt.setBarNum(padBarNumber(attorneyRespEvt.getBarNum()));
		    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		    if (updtEvt != null)
		    {

			updtEvt.setCourtResult(respEvt.getCourtResult());
			form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		    }
		    else
		    {
			form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		    }
		}

		//reset back on the object 
		form.setAttorneyName(attorneyRespEvt.getAttyName());
		form.setBarNum(attorneyRespEvt.getBarNum());
		form.setMessage("Bar Number Entered Valid");
	    }
	    form.setCursorPosition("barNum");
	}
	else
	{
	    if (form.getDcktEvtId() != null)
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		respEvt.setAttorneyName(null);
		respEvt.setBarNum(null);
		respEvt.setAttorneyConnection(null);
		respEvt.setAttorneyConnectionDesc(null);
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
	    }

	}

	return aMapping.findForward("backDetentionHearing");

    }
    /**
     * validateaty2BarNumber
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward validateAty2BarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);

	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	if (form.getBarNum() != null && !form.getBarNum().isEmpty())
	{
	    CompositeResponse compositeResponse = validateBarNum(form);

	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
	    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (error != null || attorneyDataList.isEmpty())
	    {

		form.setErrMessage("Invalid Aty2 Bar Number. Please Correct and Retry");
		form.setAction("validateAty2BarNumber");
		form.setAttorneyName("");
		form.setCursorPosition("#2ndAttorneyBarnum-" + form.getDcktEvtId());
		if (form.getDcktEvtId() != null)
		{
		    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		    respEvt.setAttorney2Name(null);
		    respEvt.setAttorney2BarNum(null);
		    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);
		}
		return aMapping.findForward("validationFailure");
	    }

	    if (attorneyDataList != null & !attorneyDataList.isEmpty())
	    {
		for (int x = 0; x < attorneyDataList.size();)
		{
		    AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		    attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		    attorneyRespEvt.setAttyName(respEvent.getAttyName());
		    attorneyRespEvt.setBarNum(respEvent.getBarNum());
		    attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		    break;
		}
	    }
	    if (attorneyRespEvt != null)
	    {
		form.setAction("validateAty2BarNumber");
		//reset back on the object 
		if (form.getDcktEvtId() != null)
		{
		    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		    respEvt.setAttorney2Name(attorneyRespEvt.getAttyName());
		    respEvt.setAttorney2BarNum(padBarNumber(attorneyRespEvt.getBarNum()));
		    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		    if (updtEvt != null)
		    {

			//updtEvt.setCourtResult(respEvt.getCourtResult());
			form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		    }
		    else
		    {
			form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		    }
		}

		//reset back on the object 
		form.setAttorneyName(attorneyRespEvt.getAttyName());
		form.setBarNum(attorneyRespEvt.getBarNum());
		form.setMessage("Bar Number Entered Valid");
	    }
	    form.setCursorPosition("#2ndAttorneyBarnum-" + form.getDcktEvtId());
	}
	else
	{
	    if (form.getDcktEvtId() != null)
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		respEvt.setAttorney2Name(null);
		respEvt.setAttorney2BarNum(null);
		respEvt.setAttorney2Connection(null);
		//respEvt.setAttorneyConnectionDesc(null);
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
	    }

	}

	return aMapping.findForward("backDetentionHearing");

    }
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward validateGalBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);

	form.setAction("courtHearing");
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	CompositeResponse compositeResponse = validateBarNum(form);

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{

	    form.setErrMessage("Invalid Bar Number. Please Correct and Retry");
	    form.setAction("validateBarNumber");
	    form.setGalName("");
	    form.setCursorPosition("galBarNum");
	    return aMapping.findForward("validationFailure");
	}

	if (attorneyDataList != null & !attorneyDataList.isEmpty())
	{
	    for (int x = 0; x < attorneyDataList.size();)
	    {
		AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		attorneyRespEvt = new AttorneyNameAndAddressResponseEvent();
		attorneyRespEvt.setAttyName(respEvent.getAttyName());
		attorneyRespEvt.setBarNum(respEvent.getBarNum());
		attorneyRespEvt.setAttyNameHistory(respEvent.getAttyNameHistory());
		break;
	    }
	}
	if (attorneyRespEvt != null)
	{
	    form.setAction("validateBarNumber");
	    //reset back on the object 
	    if (form.getDcktEvtId() != null)
	    {
		DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
		respEvt.setGalName(attorneyRespEvt.getAttyName());
		respEvt.setGalBarNum(padBarNumber(attorneyRespEvt.getBarNum()));
		form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (updtEvt != null)
		{

		    updtEvt.setCourtResult(respEvt.getCourtResult());
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		}
		else
		{
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		}
	    }

	    //reset back on the object 
	    form.setGalName(attorneyRespEvt.getAttyName());
	    form.setGalBarNumber(attorneyRespEvt.getBarNum());
	    form.setMessage("Bar Number Entered Valid");
	    ;
	}
	form.setCursorPosition("galBarNum");
	return aMapping.findForward("backDetentionHearing");

    }
  //add 2nd attorney bar validate
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateResult(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");
	String regex = "^[0-9]";
	DocketEventResponseEvent respEvt = null;

	respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if (respEvt != null)
	{
	    if (isNullOrEmpty(form.getSelectedValue()) || form.getSelectedValue().matches(regex))
	    {
		//Skip adding record to map
		respEvt.setCourtResult(form.getSelectedValue());
		respEvt.setTransferFlag("0");
		respEvt.setTransferTo(null);
		respEvt.setOldtransferTo(null);
		respEvt.setResetTo(null);		
	    }
	    else
	    {
		respEvt.setCourtResult(form.getSelectedValue());
		if ("REL".equalsIgnoreCase(form.getSelectedValue()))
		{
		    respEvt.setResetTo(null);
		}
		form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (updtEvt != null)
		{
		    updtEvt.setCourtResult(form.getSelectedValue());
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		}
		else
		{
		    form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
		}
	    }

	}

	form.setCourtResult(form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateComments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	String forwardStr = "";
	form.setValidateMsg("");

	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if (respEvt != null)
	{

	    respEvt.setResetReason(form.getSelectedValue());
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setResetReason(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
	    }
	}

	form.setResetReason(form.getSelectedValue());
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateTransfer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");
	String trnValue = (String) aRequest.getParameter("trn");	    
	// A Reset date change always creates a new Calendar record
	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if(trnValue.equals("1"))
	    respEvt.setTransferFlag("1");
	if (respEvt != null)
	{

	    respEvt.setTransferTo(form.getSelectedValue());
	    respEvt.setOldtransferTo(form.getSelectedValue());
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setTransferTo(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);
	    }

	    DocketEventResponseEvent insertEvt = form.getInsertDocketMap().get(form.getDcktEvtId());
	    if (insertEvt != null)
	    {

		insertEvt.setCourt(form.getSelectedValue());
		form.getInsertDocketMap().put(form.getDcktEvtId(), insertEvt);
	    }
	    else
	    {
		respEvt.setCourt(form.getSelectedValue());
		form.getInsertDocketMap().put(form.getDcktEvtId(), respEvt);
	    }
	}

	form.setTransferTo(form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateResetDate(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");
	String trnValue = (String) aRequest.getParameter("trn");
	// A Reset date change always creates a new Calendar record
	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if(trnValue.equals("1"))
	    respEvt.setTransferFlag("1");
	if (respEvt != null)
	{
	    GetJJSCLDetentionByChainEvent recs = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
	    recs.setChainNumber(respEvt.getChainNumber());
	    List<DocketEventResponseEvent> docketEvts = MessageUtil.postRequestListFilter(recs, DocketEventResponseEvent.class);

	    // sort lowest to highest
	    // sort the list based on seq num.
	    Collections.sort((List<DocketEventResponseEvent>) docketEvts, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
		{
		    if (det1.getSeqNum() != null && det2.getSeqNum() != null)
			return Integer.valueOf(det1.getSeqNum()).compareTo(Integer.valueOf(det2.getSeqNum()));
		    else
			return -1;
		}
	    });
	    if (docketEvts.size() > 0 && !isNullOrEmpty(form.getSelectedValue()))
	    {

		DocketEventResponseEvent resp = docketEvts.get(0);
		respEvt.setDetentionId(resp.getDetentionId());
		Date resetDate = DateUtil.stringToDate(form.getSelectedValue(), DateUtil.DEFAULT_DATE_FMT);
		Date pcHearingDate = DateUtil.stringToDate(resp.getCourtDate(), DateUtil.DEFAULT_DATE_FMT);
		if (resetDate != null && resetDate.before(pcHearingDate))
		{
		    // Throw Less Than Error
		    form.setCursorPosition("#resetToDate-" + form.getDcktEvtId());
		    //resp.setResetTo(null);
		    form.setErrMessage("RESET DATE CANNOT BE LESS THAN PROBABLE CAUSE DATE.");
		    return aMapping.findForward("backUpdateResult");
		}

		for (int x = 0; x < docketEvts.size(); x++)
		{

		    DocketEventResponseEvent response = docketEvts.get(x);

		    if (response.getCourtDate() != null)
		    {
			Date courtDate = DateUtil.stringToDate(response.getCourtDate(), DateUtil.DEFAULT_DATE_FMT);
			//chnage as trn is checkbox   162560
			if (courtDate.equals(resetDate) && !"1".equalsIgnoreCase(trnValue))//!"TRN".equalsIgnoreCase(respEvt.getCourtResult())
			{

			    // Throw Duplicate Error
			    form.setCursorPosition("#resetToDate-" + form.getDcktEvtId());
			    //resp.setResetTo(resp.getPrevResetTo());
			    //resp.setResetTo(null);
			    form.setErrMessage(" RESET DATE CANNOT BE SAME AS ANOTHER RECORD.");
			    return aMapping.findForward("backUpdateResult");
			}
		    }

		}
	    }
	    respEvt.setResetTo(form.getSelectedValue());
	    /*
	     * NOTE:  Reset Date on Detention (DT) hearings can be a date prior to the current court date, 
	     * but the Reset Date cannot precede the court date for the associated Probable Cause hearing.
	     */
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setResetTo(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);
		if (!isNullOrEmpty(form.getSelectedValue()))
		{//check if chnage required as checkbox   161648
		    if ("1".equalsIgnoreCase(trnValue) && !updtEvt.getCourtResult().equalsIgnoreCase(updtEvt.getPrevCourtResult()))
		    {

			String docketId = getSubsequentOid(updtEvt.getSeqNum(), updtEvt.getChainNumber());
			if (docketId == null)
			{

			    form.getInsertDocketMap().put(form.getDcktEvtId(), updtEvt);
			}

		    }
		    else
		    {

			form.getInsertDocketMap().put(form.getDcktEvtId(), updtEvt);
		    }
		}
	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);

		DocketEventResponseEvent dktEvent = form.getUpdateDocketMap().get(form.getDcktEvtId());
		if (dktEvent != null)
		{
		    if (!isNullOrEmpty(form.getSelectedValue()))
		    {//check if change needed as checkbox for trn   161648
			// do not add if has subsequent
			if ("1".equalsIgnoreCase(trnValue) && !dktEvent.getCourtResult().equalsIgnoreCase(dktEvent.getPrevCourtResult()))
			{

			    String docketId = getSubsequentOid(dktEvent.getSeqNum(), dktEvent.getChainNumber());
			    if (docketId == null)
			    {

				form.getInsertDocketMap().put(form.getDcktEvtId(), dktEvent);
			    }

			}
			else
			{

			    form.getInsertDocketMap().put(form.getDcktEvtId(), dktEvent);
			}

		    }
		}
	    }
	}

	form.setResetTo(form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateAttorneyConnection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");

	// A Reset date change always creates a new Calendar record
	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if (respEvt != null)
	{

	    respEvt.setAttorneyConnection(form.getSelectedValue());
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setAttorneyConnection(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);

	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);

	    }

	    DocketEventResponseEvent insertEvt = form.getInsertDocketMap().get(form.getDcktEvtId());
	    if (insertEvt != null)
	    {
		insertEvt.setAttorneyConnection(form.getSelectedValue());
		form.getInsertDocketMap().put(form.getDcktEvtId(), insertEvt);
	    }
	}

	//form.setConnectionCode( form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);
    }
//attorney2 connection change
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward updateAttorney2Connection(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");

	// A Reset date change always creates a new Calendar record
	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if (respEvt != null)
	{

	    respEvt.setAttorney2Connection(form.getSelectedValue());
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setAttorney2Connection(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);

	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);

	    }

	    DocketEventResponseEvent insertEvt = form.getInsertDocketMap().get(form.getDcktEvtId());
	    if (insertEvt != null)
	    {
		insertEvt.setAttorney2Connection(form.getSelectedValue());
		form.getInsertDocketMap().put(form.getDcktEvtId(), insertEvt);
	    }
	}

	//form.setConnectionCode( form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);
    }
    // task 168666
    public ActionForward updateInterpreter(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	String forwardStr = "";
	form.setValidateMsg("");

	// 
	DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtId());
	if (respEvt != null)
	{

	    respEvt.setInterpreter(form.getSelectedValue());
	    form.getDktSearchResultsMap().put(form.getDcktEvtId(), respEvt);

	    DocketEventResponseEvent updtEvt = form.getUpdateDocketMap().get(form.getDcktEvtId());
	    if (updtEvt != null)
	    {

		updtEvt.setInterpreter(form.getSelectedValue());
		form.getUpdateDocketMap().put(form.getDcktEvtId(), updtEvt);

	    }
	    else
	    {
		form.getUpdateDocketMap().put(form.getDcktEvtId(), respEvt);

	    }

	    DocketEventResponseEvent insertEvt = form.getInsertDocketMap().get(form.getDcktEvtId());
	    if (insertEvt != null)
	    {
		insertEvt.setInterpreter(form.getSelectedValue());
		form.getInsertDocketMap().put(form.getDcktEvtId(), insertEvt);
	    }
	}

	//form.setConnectionCode( form.getSelectedValue());
	forwardStr = "backUpdateResult";
	return aMapping.findForward(forwardStr);
    }
    //
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String detentionId = "";
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	boolean recordUpd = false;
	boolean isEmailsend = false;	
	int newSeqNum = 0;

	Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDescMap = form.getCourtDecisionsMap();

	String docketIds[] = form.getSelectedDocketsToUpdate();
	List<String> docketIdList = Arrays.asList(docketIds[0].split(","));

	for (int i = 0; i < docketIdList.size(); i++)
	{

	    String docketEventId = docketIdList.get(i);
	    DocketEventResponseEvent uDocket = form.getUpdateDocketMap().get(docketEventId);

	    if (uDocket != null)
	    {
		detentionId = uDocket.getDetentionId();
		UpdateJJSCLDetentionEvent updObj = new UpdateJJSCLDetentionEvent();

		updObj.setUpdateFlag("Y");
		updObj.setResetDate(DateUtil.stringToDate(uDocket.getResetTo(), DateUtil.DATE_FMT_1));

		String result = uDocket.getCourtResult();
		if ("REL".equalsIgnoreCase(result))
		{

		    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(updObj.getDocketEventId());
		    if (respEvt != null)
		    {
			respEvt.setResetTo(null);
			form.getDktSearchResultsMap().put(updObj.getDocketEventId(), respEvt);
			updObj.setResetDate(null);
		    }
		}

		updObj.setHearingResult(result);
		updObj.setDocketEventId(docketEventId);
		//code chnage if its checkbox   161648
		JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(uDocket.getCourtResult());

		updObj.setResetReason(uDocket.getResetReason());
		updObj.setTransferTo(uDocket.getTransferTo());

		String attorneyName = uDocket.getAttorneyName();
		if (StringUtils.isNotEmpty(attorneyName) && uDocket.getAttorneyConnection() == null)
		{

		    form.setErrMessage("ATTORNEY CONNECTION CODE MUST BE ENTERED.");
		    return aMapping.findForward(UIConstants.COURT_ACTION);
		}
		
		if (uDocket.getGalBarNum() != null && !uDocket.getGalBarNum().isEmpty() && !uDocket.getGalBarNum().equalsIgnoreCase(uDocket.getPreviousGalBarNum()))
		{
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    //validate bar Number
		    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
		    request.setBarNum(uDocket.getGalBarNum());
		    dispatch.postEvent(request);
		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

		    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
		    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		    Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
		    if (error != null || attorneyDataList.isEmpty() || exceptions != null)
		    {
			form.setDcktEvtId(uDocket.getDocketEventId());
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid GAL Bar Number. Please Correct"));
			saveErrors(aRequest, errors);
			form.setAction("error");
			form.setCursorPosition("#galBarNum-" + uDocket.getDocketEventIdKey());
			return aMapping.findForward(UIConstants.FAILURE);
		    }
		    else
		    {

			updObj.setUpdateSubAdLitem(true);
		    }
		}
		updObj.setAttorneyName(attorneyName);
		updObj.setAttorneyConnection(uDocket.getAttorneyConnection());
		updObj.setBarNumber(uDocket.getBarNum());
		//attorney 2 details
		updObj.setAttorney2Name(uDocket.getAttorney2Name());
		updObj.setAttorney2Connection(uDocket.getAttorney2Connection());
		updObj.setAttorney2BarNum(uDocket.getAttorney2BarNum());
		//
		// gal changes for task 165536
		    if (uDocket.getGalBarNum() != null && !uDocket.getGalBarNum().isEmpty())
		    {
			UpdateJJSLastAttorneyGALEvent updatelstattorneyEvent =(UpdateJJSLastAttorneyGALEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJJSLASTATTORNEYGAL );
			updatelstattorneyEvent.setJuvenileNumber(uDocket.getJuvenileNumber());
			updatelstattorneyEvent.setGalName(uDocket.getGalName());
			updatelstattorneyEvent.setGalbarNumber(uDocket.getGalBarNum());
			/*updatelstattorneyEvent.setRecType(uDocket.getRecType());
			updatelstattorneyEvent.setJjclcourtId(uDocket.getDocketEventId());*/
			
			MessageUtil.postRequest(updatelstattorneyEvent);
		    }
		    //
		updObj.setGalBarNumber(uDocket.getGalBarNum());
		updObj.setGalName(uDocket.getGalName());		
		updObj.setAtyConfirmation(uDocket.getAtyConfirmation());
		updObj.setJuvenileNumber(uDocket.getJuvenileNumber());// needed for update subsequent
		updObj.setCourtDate(DateUtil.stringToDate(uDocket.getCourtDate(), DateUtil.DATE_FMT_1)); // needed for update subsequent
		updObj.setDetentionId(detentionId);
		if(uDocket.getPetitionNumber()==null || uDocket.getPetitionNumber().isEmpty())
		{
		    if (uDocket.getJuvenileNumber().length() < 7)
		    {
			updObj.setPetitionNumber("J0" + uDocket.getJuvenileNumber());
		    }
		    else
		    {
			updObj.setPetitionNumber("J" + uDocket.getJuvenileNumber());
		    }		    
		}
		else
		    updObj.setPetitionNumber(uDocket.getPetitionNumber());
		// 168666
		updObj.setInterpreter(uDocket.getInterpreter());

		MessageUtil.postRequest(updObj);
		recordUpd = true;

		//Update Subsequent record
		//code change for TRN checkbox   161648
		if (codeResp != null && ("Y".equalsIgnoreCase(codeResp.getResetAllowed()) || "R".equalsIgnoreCase(codeResp.getResetAllowed())))
		{
		    if (updObj.getTransferTo() != null && !updObj.getTransferTo().isEmpty())
		    {
			GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
			courtEvent.setCourt(updObj.getTransferTo());
			CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
			JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);

			if (crtRespEvt != null)
			{
			    if (crtRespEvt.getRefereesCourtInd() != null && !crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
			    {
				// call function to update subsequent 
				updateSubsequent(uDocket);
			    }

			}
		    }

		}
		UpdateJJSHeaderFromDetentionCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDetentionCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDETENTIONCOURT);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date currDate = new Date();
		String today = sdf.format(currDate).trim();
		if (!("".equalsIgnoreCase(result)) && result != null && uDocket.getCourtDate().trim().equals(today))
		{
		 // as part of task 102922
		    updateHeaderEvt.setReleasedecisionStatus(true);
		    updateHeaderEvt.setJuvenileNumber(uDocket.getJuvenileNumber());
		    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
		    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
		}		
		//
	    }

	    //Check insert record for the same docket event Id
	    DocketEventResponseEvent iDocket = form.getInsertDocketMap().get(docketEventId);
	   
	    if (iDocket != null)
	    {

		UpdateJJSCLDetentionEvent insObj = new UpdateJJSCLDetentionEvent();
		insObj.setUpdateHeader("");
		insObj.setJuvenileNumber(iDocket.getJuvenileNumber());
		insObj.setReferralNumber(iDocket.getReferralNum());
		insObj.setChainNumber(iDocket.getChainNumber());
		if (StringUtils.isEmpty(iDocket.getCourt()))
		{
		    insObj.setCourtId("1");
		}
		else
		{
		    insObj.setCourtId(iDocket.getCourt());
		}

		Date newCourtDate = DateUtil.stringToDate(iDocket.getResetTo(), DateUtil.DATE_FMT_1);
		//check if any change needed in TRN   161648
		JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(iDocket.getCourtResult());
		//checking action
		//if ("detained".equalsIgnoreCase(codeResp.getAction()))
		if (codeResp.getResetAllowed() != null&&!StringUtils.isEmpty(codeResp.getResetAllowed()))
		{
        		if ("R".equalsIgnoreCase(codeResp.getResetAllowed())||"Y".equalsIgnoreCase(codeResp.getResetAllowed()))
        		{
        
        		    insObj.setUpdateHeader("Y");
        		}
		}

		insObj.setCourtDate(newCourtDate);

		GetJJSCLDetentionByChainEvent recs = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
		recs.setChainNumber(iDocket.getChainNumber());
		List<DocketEventResponseEvent> docketEvts = MessageUtil.postRequestListFilter(recs, DocketEventResponseEvent.class);

		// sort highest to lowest Seq Number
		Collections.sort((List<DocketEventResponseEvent>) docketEvts, new Comparator<DocketEventResponseEvent>() {
		    @Override
		    public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
		    {
			if (det1.getSeqNum() != null && det2.getSeqNum() != null)
			    return Integer.valueOf(det2.getSeqNum()).compareTo(Integer.valueOf(det1.getSeqNum()));
			else
			    return -1;
		    }
		});

		if (docketEvts.size() > 0)
		{
		    DocketEventResponseEvent resp = docketEvts.get(0);
		    newSeqNum = Integer.parseInt(resp.getSeqNum());
		    newSeqNum = newSeqNum + 10;
		}
		insObj.setSeqNumber(String.valueOf(newSeqNum));
		insObj.setIssueFlag("E");
		insObj.setAttorneyName(iDocket.getAttorneyName());
		insObj.setBarNumber(iDocket.getBarNum());
		insObj.setGalBarNumber(iDocket.getGalBarNum());
		insObj.setGalName(iDocket.getGalName());
		insObj.setAttorneyConnection(iDocket.getAttorneyConnection());
		//attorney2 details
		insObj.setAttorney2Name(iDocket.getAttorney2Name());
		insObj.setAttorney2BarNum(iDocket.getAttorney2BarNum());		
		insObj.setAttorney2Connection(iDocket.getAttorney2Connection());
		//
		insObj.setDetentionId(detentionId);
		if(iDocket.getPetitionNumber()==null || iDocket.getPetitionNumber().isEmpty())
		{
		    if (iDocket.getJuvenileNumber().length() < 7)
		    {
			insObj.setPetitionNumber("J0" + iDocket.getJuvenileNumber());
		    }
		    else
		    {
			insObj.setPetitionNumber("J" + iDocket.getJuvenileNumber());
		    }		    
		}
		else
		    insObj.setPetitionNumber(iDocket.getPetitionNumber());
		
		//task 168662
		insObj.setInterpreter(iDocket.getInterpreter());
		MessageUtil.postRequest(insObj);
		//send email to JPO task 152219
		isEmailsend = sendJPOemail(iDocket);
		recordUpd = true;
	    }
	}

	if (!recordUpd)
	{
	    form.getUpdateDocketMap().clear();
	    form.getInsertDocketMap().clear();
	    form.setErrMessage(UIConstants.EMPTY_STRING);
	    form.setMessage("NO CHANGE DETECTED FOR UPDATE");
	}
	else
	{
	    form.getUpdateDocketMap().clear();
	    form.getInsertDocketMap().clear();
	    form.setMessage("RECORD(S) SUCCESSFULLY UPDATED");
	}

	return aMapping.findForward(UIConstants.COURT_ACTION);
    }

    //new update after confirmation
    public ActionForward updateafterConfirm(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse, String byPass)
    {
	String detentionId = "";
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	boolean recordUpd = false;
	boolean isEmailsend = false;
	int newSeqNum = 0;
	String juvNum="";
	String confirmdocketEventId ="";
	Map<String, JuvenileCourtDecisionCodeResponseEvent> courtDescMap = form.getCourtDecisionsMap();

	String docketIds[] = form.getSelectedDocketsToUpdate();
	List<String> docketIdList = Arrays.asList(docketIds[0].split(","));
	if (byPass == null)
	{
	    for (int i = 0; i < docketIdList.size(); i++)
	    {

		String docketEventId = docketIdList.get(i);
		confirmdocketEventId=docketEventId;
		DocketEventResponseEvent uDocket = form.getUpdateDocketMap().get(docketEventId);
		//commented out below as it goes to update even if no docket changed.- bug 150953 but then uncommented as it wont allow just attorney confirmation with bug 151521
		if (uDocket == null)
		{
		    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(docketEventId);
		    respEvt.setAtyConfirmation("YES");
		    form.getUpdateDocketMap().put(docketEventId, respEvt);

		}
		uDocket = form.getUpdateDocketMap().get(docketEventId);
		if (uDocket != null)
		{		    
		    uDocket.setAtyConfirmation("YES");
		    uDocket.setOldattorneyName(uDocket.getAttorneyName());
		    uDocket.setOldattorneyConnection(uDocket.getAttorneyConnection());
		    uDocket.setOldcourtResult(uDocket.getCourtResult());
		    detentionId = uDocket.getDetentionId();
		    UpdateJJSCLDetentionEvent updObj = new UpdateJJSCLDetentionEvent();

		    updObj.setUpdateFlag("Y");
		    updObj.setResetDate(DateUtil.stringToDate(uDocket.getResetTo(), DateUtil.DATE_FMT_1));

		    String result = uDocket.getCourtResult();
		    if ("REL".equalsIgnoreCase(result))
		    {

			DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(updObj.getDocketEventId());
			if (respEvt != null)
			{
			    respEvt.setResetTo(null);
			    form.getDktSearchResultsMap().put(updObj.getDocketEventId(), respEvt);
			    updObj.setResetDate(null);
			}
		    }
		    if(result!=null)//bug fix 162153
		    {
        		    if(result.isEmpty())
        			updObj.setHearingResult(null);
        		    else
        			updObj.setHearingResult(result);
		    }
		    updObj.setDocketEventId(docketEventId);

		    JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(uDocket.getCourtResult());

		    updObj.setResetReason(uDocket.getResetReason());
		    updObj.setTransferTo(uDocket.getTransferTo());

		    String attorneyName = uDocket.getAttorneyName();
		    if (StringUtils.isNotEmpty(attorneyName) && uDocket.getAttorneyConnection() == null)
		    {

			form.setErrMessage("ATTORNEY CONNECTION CODE MUST BE ENTERED.");
			return aMapping.findForward(UIConstants.COURT_ACTION);
		    }

		    if (uDocket.getGalBarNum() != null && !uDocket.getGalBarNum().isEmpty() && !uDocket.getGalBarNum().equalsIgnoreCase(uDocket.getPreviousGalBarNum()))
		    {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			//validate bar Number
			GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			request.setBarNum(uDocket.getGalBarNum());
			dispatch.postEvent(request);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			{
			    form.setDcktEvtId(uDocket.getDocketEventId());
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid GAL Bar Number. Please Correct"));
			    saveErrors(aRequest, errors);
			    form.setAction("error");
			    form.setCursorPosition("#galBarNum-" + uDocket.getDocketEventIdKey());
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			else
			{

			    updObj.setUpdateSubAdLitem(true);
			}
		    }
		    updObj.setAttorneyName(attorneyName);
		    updObj.setAtyConfirmation("YES");
		    updObj.setAttorneyConnection(uDocket.getAttorneyConnection());
		    updObj.setBarNumber(uDocket.getBarNum());
		    updObj.setGalBarNumber(uDocket.getGalBarNum());
		    updObj.setGalName(uDocket.getGalName());
		    //attorney 2 details
		    updObj.setAttorney2Name(uDocket.getAttorney2Name());
		    updObj.setAttorney2Connection(uDocket.getAttorney2Connection());
		    updObj.setAttorney2BarNum(uDocket.getAttorney2BarNum());
		    //
		    //updObj.setAttorneyConnection(uDocket.getAttorneyConnection());
		    updObj.setJuvenileNumber(uDocket.getJuvenileNumber());// needed for update subsequent
		    updObj.setCourtDate(DateUtil.stringToDate(uDocket.getCourtDate(), DateUtil.DATE_FMT_1)); // needed for update subsequent
		    updObj.setDetentionId(detentionId);
		    if(uDocket.getPetitionNumber()==null || uDocket.getPetitionNumber().isEmpty())
			{
			    if (uDocket.getJuvenileNumber().length() < 7)
			    {
				updObj.setPetitionNumber("J0" + uDocket.getJuvenileNumber());
			    }
			    else
			    {
				updObj.setPetitionNumber("J" + uDocket.getJuvenileNumber());
			    }		    
			}
			else
			    updObj.setPetitionNumber(uDocket.getPetitionNumber());///// add J+ if confirmed by Carla
		    
		 // 168666
		    updObj.setInterpreter(uDocket.getInterpreter());
		    MessageUtil.postRequest(updObj);
		    juvNum=uDocket.getJuvenileNumber();
		    recordUpd = true;

		    //Update Subsequent record
		    if (codeResp != null && ("Y".equalsIgnoreCase(codeResp.getResetAllowed()) || "R".equalsIgnoreCase(codeResp.getResetAllowed())))
		    {
			if (updObj.getTransferTo() != null && !updObj.getTransferTo().isEmpty())
			{
			    GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
			    courtEvent.setCourt(updObj.getTransferTo());
			    CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
			    JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);

			    if (crtRespEvt != null)
			    {
				if (crtRespEvt.getRefereesCourtInd() != null && !crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
				{
				    // call function to update subsequent 
				    updateSubsequent(uDocket);
				}

			    }
			}

		    }
		    //add probable cause date addition code
		    UpdateJJSHeaderFromDetentionCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDetentionCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDETENTIONCOURT);
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date currDate = new Date();
			String today = sdf.format(currDate).trim();
			if (!("".equalsIgnoreCase(result)) && result != null && uDocket.getCourtDate().trim().equals(today))
			{
			 // as part of task 102922
			    updateHeaderEvt.setReleasedecisionStatus(true);
			    updateHeaderEvt.setJuvenileNumber(uDocket.getJuvenileNumber());
			    CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
			    JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
			} 
		    //
		}

		//Check insert record for the same docket event Id
		DocketEventResponseEvent iDocket = form.getInsertDocketMap().get(docketEventId);

		if (iDocket != null)
		{

		    UpdateJJSCLDetentionEvent insObj = new UpdateJJSCLDetentionEvent();
		    insObj.setUpdateHeader("");
		    insObj.setJuvenileNumber(iDocket.getJuvenileNumber());
		    insObj.setReferralNumber(iDocket.getReferralNum());
		    insObj.setChainNumber(iDocket.getChainNumber());
		    if (StringUtils.isEmpty(iDocket.getCourt()))
		    {
			insObj.setCourtId("1");
		    }
		    else
		    {
			insObj.setCourtId(iDocket.getCourt());
		    }

		    Date newCourtDate = DateUtil.stringToDate(iDocket.getResetTo(), DateUtil.DATE_FMT_1);

		    JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(iDocket.getCourtResult());
		    //not the action but the rest code
		    //if ("detained".equalsIgnoreCase(codeResp.getAction()))
		    if (codeResp.getResetAllowed() != null&&!StringUtils.isEmpty(codeResp.getResetAllowed()))
		    {
        		    if ("R".equalsIgnoreCase(codeResp.getResetAllowed())||"Y".equalsIgnoreCase(codeResp.getResetAllowed()))
        		    {
        			insObj.setUpdateHeader("Y");
        		    }
		    }

		    insObj.setCourtDate(newCourtDate);

		    GetJJSCLDetentionByChainEvent recs = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
		    recs.setChainNumber(iDocket.getChainNumber());
		    List<DocketEventResponseEvent> docketEvts = MessageUtil.postRequestListFilter(recs, DocketEventResponseEvent.class);

		    // sort highest to lowest Seq Number
		    Collections.sort((List<DocketEventResponseEvent>) docketEvts, new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
			{
			    if (det1.getSeqNum() != null && det2.getSeqNum() != null)
				return Integer.valueOf(det2.getSeqNum()).compareTo(Integer.valueOf(det1.getSeqNum()));
			    else
				return -1;
			}
		    });

		    if (docketEvts.size() > 0)
		    {
			DocketEventResponseEvent resp = docketEvts.get(0);
			newSeqNum = Integer.parseInt(resp.getSeqNum());
			newSeqNum = newSeqNum + 10;
		    }
		    insObj.setSeqNumber(String.valueOf(newSeqNum));
		    insObj.setIssueFlag("E");
		    insObj.setAttorneyName(iDocket.getAttorneyName());
		    insObj.setBarNumber(iDocket.getBarNum());
		    insObj.setGalBarNumber(iDocket.getGalBarNum());
		    insObj.setGalName(iDocket.getGalName());
		    insObj.setAttorneyConnection(iDocket.getAttorneyConnection());
		    //attorney 2 details
		    insObj.setAttorney2Name(iDocket.getAttorney2Name());
		    insObj.setAttorney2Connection(iDocket.getAttorney2Connection());
		    insObj.setAttorney2BarNum(iDocket.getAttorney2BarNum());
		    //
		    insObj.setDetentionId(detentionId);
		    if(iDocket.getPetitionNumber()==null || iDocket.getPetitionNumber().isEmpty())
			{
			    if (iDocket.getJuvenileNumber().length() < 7)
			    {
				insObj.setPetitionNumber("J0" + iDocket.getJuvenileNumber());
			    }
			    else
			    {
				insObj.setPetitionNumber("J" + iDocket.getJuvenileNumber());
			    }		    
			}
			else
			    insObj.setPetitionNumber(iDocket.getPetitionNumber());///// add J+ if confirmed by Carla
		    
		    //task 168662
		    insObj.setInterpreter(iDocket.getInterpreter());
		    MessageUtil.postRequest(insObj);
		    recordUpd = true;
		    //send email to JPO task 152219
		    isEmailsend = sendJPOemail(iDocket);
		}
	    }
	}
	else
	{
	    
	    for (int i = 0; i < docketIdList.size(); i++)
	    {

		String docketEventId = docketIdList.get(i);
		confirmdocketEventId=docketEventId;
		DocketEventResponseEvent uDocket = form.getUpdateDocketMap().get(docketEventId);
		if (uDocket != null)
		{
		    uDocket.setAtyBypass("YES");
		    uDocket.setOldattorneyName(uDocket.getAttorneyName());
		    uDocket.setOldattorneyConnection(uDocket.getAttorneyConnection());
		    uDocket.setOldcourtResult(uDocket.getCourtResult());
		    detentionId = uDocket.getDetentionId();
		    UpdateJJSCLDetentionEvent updObj = new UpdateJJSCLDetentionEvent();
		    updObj.setUpdateFlag("Y");
		    updObj.setResetDate(DateUtil.stringToDate(uDocket.getResetTo(), DateUtil.DATE_FMT_1));

		    String result = uDocket.getCourtResult();
		    if ("REL".equalsIgnoreCase(result))
		    {

			DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(updObj.getDocketEventId());
			if (respEvt != null)
			{
			    respEvt.setResetTo(null);
			    form.getDktSearchResultsMap().put(updObj.getDocketEventId(), respEvt);
			    updObj.setResetDate(null);
			}
		    }
		    if(result!=null)//bug fix 162153
		    {
        		    if(result.isEmpty())
        			updObj.setHearingResult(null);
        		    else
        			updObj.setHearingResult(result);
		    }
		    updObj.setDocketEventId(docketEventId);

		    JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(uDocket.getCourtResult());

		    updObj.setResetReason(uDocket.getResetReason());
		    updObj.setTransferTo(uDocket.getTransferTo());
		    String attorneyName = uDocket.getAttorneyName();
		    if (StringUtils.isNotEmpty(attorneyName) && uDocket.getAttorneyConnection() == null)
		    {

			form.setErrMessage("ATTORNEY CONNECTION CODE MUST BE ENTERED.");
			return aMapping.findForward(UIConstants.COURT_ACTION);
		    }

		    if (uDocket.getGalBarNum() != null && !uDocket.getGalBarNum().isEmpty() && !uDocket.getGalBarNum().equalsIgnoreCase(uDocket.getPreviousGalBarNum()))
		    {
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			//validate bar Number
			GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
			request.setBarNum(uDocket.getGalBarNum());
			dispatch.postEvent(request);
			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
			ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
			Collection exceptions = (Collection) dataMap.get(ReturnException.RETURN_EXCEPTION_TOPIC);
			if (error != null || attorneyDataList.isEmpty() || exceptions != null)
			{
			    form.setDcktEvtId(uDocket.getDocketEventId());
			    ActionErrors errors = new ActionErrors();
			    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid GAL Bar Number. Please Correct"));
			    saveErrors(aRequest, errors);
			    form.setAction("error");
			    form.setCursorPosition("#galBarNum-" + uDocket.getDocketEventIdKey());
			    return aMapping.findForward(UIConstants.FAILURE);
			}
			else
			{

			    updObj.setUpdateSubAdLitem(true);
			}
		    }
		    updObj.setAttorneyName(attorneyName);
		    updObj.setAttorneyConnection(uDocket.getAttorneyConnection());
		    updObj.setBarNumber(uDocket.getBarNum());
		    updObj.setGalBarNumber(uDocket.getGalBarNum());
		    updObj.setGalName(uDocket.getGalName());
		    //updObj.setAttorneyConnection(uDocket.getAttorneyConnection());
		    //attorney 2 details
		    updObj.setAttorney2Name(uDocket.getAttorney2Name());
		    updObj.setAttorney2Connection(uDocket.getAttorney2Connection());
		    updObj.setAttorney2BarNum(uDocket.getAttorney2BarNum());
		    //
		    updObj.setJuvenileNumber(uDocket.getJuvenileNumber());// needed for update subsequent
		    updObj.setCourtDate(DateUtil.stringToDate(uDocket.getCourtDate(), DateUtil.DATE_FMT_1)); // needed for update subsequent
		    updObj.setDetentionId(detentionId);
		    if(uDocket.getPetitionNumber()==null || uDocket.getPetitionNumber().isEmpty())
			{
			    if (uDocket.getJuvenileNumber().length() < 7)
			    {
				updObj.setPetitionNumber("J0" + uDocket.getJuvenileNumber());
			    }
			    else
			    {
				updObj.setPetitionNumber("J" + uDocket.getJuvenileNumber());
			    }		    
			}
			else
			    updObj.setPetitionNumber(uDocket.getPetitionNumber());///// add J+ if confirmed by Carla
		    
		  //task 168662
		    updObj.setInterpreter(uDocket.getInterpreter());
		    // gal changes for task 165536
		    if (uDocket.getGalBarNum() != null && !uDocket.getGalBarNum().isEmpty())
		    {
			UpdateJJSLastAttorneyGALEvent updatelstattorneyEvent =(UpdateJJSLastAttorneyGALEvent)
				EventFactory.getInstance( JuvenileCaseControllerServiceNames.UPDATEJJSLASTATTORNEYGAL );
			updatelstattorneyEvent.setJuvenileNumber(uDocket.getJuvenileNumber());
			updatelstattorneyEvent.setGalName(uDocket.getGalName());
			updatelstattorneyEvent.setGalbarNumber(uDocket.getGalBarNum());	
			/*updatelstattorneyEvent.setRecType(uDocket.getRecType());
			updatelstattorneyEvent.setJjclcourtId(uDocket.getDocketEventId());*/
			
			MessageUtil.postRequest(updatelstattorneyEvent);
		    }
		    //
		    if (uDocket.getAtyConfirmation() == null || uDocket.getAtyConfirmation().isEmpty())
		    {			
			GetJJSDetentionbyOIDEvent courtEvent = (GetJJSDetentionbyOIDEvent) EventFactory
	                        .getInstance(JuvenileCaseControllerServiceNames.GETJJSDETENTIONBYOID);
		    	courtEvent.setId(docketEventId);
	                CompositeResponse officerComposite = MessageUtil.postRequest(courtEvent);

	                DocketEventResponseEvent resp= (DocketEventResponseEvent) MessageUtil.filterComposite(
	                        officerComposite, DocketEventResponseEvent.class);
			
	                if(resp!=null)
	                {
	                    updObj.setAttorneyName(resp.getAttorneyName());
	                    updObj.setAttorneyConnection(resp.getAttorneyConnection());
	                    updObj.setBarNumber(resp.getBarNum());
	                }

		    }
		    MessageUtil.postRequest(updObj);
		    juvNum=uDocket.getJuvenileNumber();
		    recordUpd = true;

		    //Update Subsequent record
		    if (codeResp != null && ("Y".equalsIgnoreCase(codeResp.getResetAllowed()) || "R".equalsIgnoreCase(codeResp.getResetAllowed())))
		    {
			if (updObj.getTransferTo() != null && !updObj.getTransferTo().isEmpty())
			{
			    GetJuvenileCourtsEvent courtEvent = (GetJuvenileCourtsEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILECOURTS);
			    courtEvent.setCourt(updObj.getTransferTo());
			    CompositeResponse courtRoomResp = MessageUtil.postRequest(courtEvent);
			    JuvenileCourtResponseEvent crtRespEvt = (JuvenileCourtResponseEvent) MessageUtil.filterComposite(courtRoomResp, JuvenileCourtResponseEvent.class);

			    if (crtRespEvt != null)
			    {
				if (crtRespEvt.getRefereesCourtInd() != null && !crtRespEvt.getRefereesCourtInd().equalsIgnoreCase("Y"))
				{
				    // call function to update subsequent 
				    updateSubsequent(uDocket);
				}

			    }
			}

		    }
		    UpdateJJSHeaderFromDetentionCourtEvent updateHeaderEvt = (UpdateJJSHeaderFromDetentionCourtEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.UPDATEJJSHEADERFROMDETENTIONCOURT);
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date currDate = new Date();
			String today = sdf.format(currDate).trim();
			if (!("".equalsIgnoreCase(result)) && result != null && uDocket.getCourtDate().trim().equals(today))
			{
			 // as part of task 102922
			    updateHeaderEvt.setReleasedecisionStatus(true);			
        			updateHeaderEvt.setJuvenileNumber(uDocket.getJuvenileNumber());
        			CompositeResponse headerResponse = MessageUtil.postRequest(updateHeaderEvt);
        			JuvenileFacilityHeaderResponseEvent headerRespEvt = (JuvenileFacilityHeaderResponseEvent) MessageUtil.filterComposite(headerResponse, JuvenileFacilityHeaderResponseEvent.class);
        		}			
		    //
		}

		//Check insert record for the same docket event Id
		DocketEventResponseEvent iDocket = form.getInsertDocketMap().get(docketEventId);

		if (iDocket != null)
		{

		    UpdateJJSCLDetentionEvent insObj = new UpdateJJSCLDetentionEvent();
		    insObj.setUpdateHeader("");
		    insObj.setJuvenileNumber(iDocket.getJuvenileNumber());
		    insObj.setReferralNumber(iDocket.getReferralNum());
		    insObj.setChainNumber(iDocket.getChainNumber());
		    if (StringUtils.isEmpty(iDocket.getCourt()))
		    {
			insObj.setCourtId("1");
		    }
		    else
		    {
			insObj.setCourtId(iDocket.getCourt());
		    }

		    Date newCourtDate = DateUtil.stringToDate(iDocket.getResetTo(), DateUtil.DATE_FMT_1);

		    JuvenileCourtDecisionCodeResponseEvent codeResp = (JuvenileCourtDecisionCodeResponseEvent) courtDescMap.get(iDocket.getCourtResult());
		    //not the action but the rest code
		    //if ("detained".equalsIgnoreCase(codeResp.getAction()))
		    if (codeResp.getResetAllowed() != null&&!StringUtils.isEmpty(codeResp.getResetAllowed()))
		    {
        		    if ("R".equalsIgnoreCase(codeResp.getResetAllowed())||"Y".equalsIgnoreCase(codeResp.getResetAllowed()))
        		    {
        
        			insObj.setUpdateHeader("Y");
        		    }
		    }

		    insObj.setCourtDate(newCourtDate);

		    GetJJSCLDetentionByChainEvent recs = (GetJJSCLDetentionByChainEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYCHAIN);
		    recs.setChainNumber(iDocket.getChainNumber());
		    List<DocketEventResponseEvent> docketEvts = MessageUtil.postRequestListFilter(recs, DocketEventResponseEvent.class);

		    // sort highest to lowest Seq Number
		    Collections.sort((List<DocketEventResponseEvent>) docketEvts, new Comparator<DocketEventResponseEvent>() {
			@Override
			public int compare(DocketEventResponseEvent det1, DocketEventResponseEvent det2)
			{
			    if (det1.getSeqNum() != null && det2.getSeqNum() != null)
				return Integer.valueOf(det2.getSeqNum()).compareTo(Integer.valueOf(det1.getSeqNum()));
			    else
				return -1;
			}
		    });

		    if (docketEvts.size() > 0)
		    {
			DocketEventResponseEvent resp = docketEvts.get(0);
			newSeqNum = Integer.parseInt(resp.getSeqNum());
			newSeqNum = newSeqNum + 10;
		    }
		    insObj.setSeqNumber(String.valueOf(newSeqNum));
		    insObj.setIssueFlag("E");
		    insObj.setAttorneyName(iDocket.getAttorneyName());
		    insObj.setAttorneyConnection(iDocket.getAttorneyConnection());
		    insObj.setBarNumber(iDocket.getBarNum());
		    //attorney 2 details
		    insObj.setAttorney2Name(iDocket.getAttorney2Name());
		    insObj.setAttorney2Connection(iDocket.getAttorney2Connection());
		    insObj.setAttorney2BarNum(iDocket.getAttorney2BarNum());
		    //
		    //}
		    insObj.setGalBarNumber(iDocket.getGalBarNum());
		    insObj.setGalName(iDocket.getGalName());
		    if (uDocket.getAtyConfirmation() == null || uDocket.getAtyConfirmation().isEmpty())
		    {
			String atyName = null, atyBarnum = null, atyCon = null;
			Iterator<JJSCLDetention> objIter = new ArrayList<JJSCLDetention>().iterator(); // empty   iterator
			objIter = JJSCLDetention.findAll("OID", docketEventId);
			while (objIter.hasNext())
			{
			    JJSCLDetention crt = (JJSCLDetention) objIter.next();
			    if (crt != null && crt.getRecType().equalsIgnoreCase("DETENTION"))
			    {
				atyName = crt.getAttorneyName();
				atyCon = crt.getAttorneyConnection();
				atyBarnum = crt.getBarNumber();
			    }
			}
			insObj.setAttorneyName(atyName);
			insObj.setAttorneyConnection(atyCon);
			insObj.setBarNumber(atyBarnum);

		    }
		    insObj.setDetentionId(detentionId);
		    if(iDocket.getPetitionNumber()==null || iDocket.getPetitionNumber().isEmpty())
			{
			    if (iDocket.getJuvenileNumber().length() < 7)
			    {
				insObj.setPetitionNumber("J0" + iDocket.getJuvenileNumber());
			    }
			    else
			    {
				insObj.setPetitionNumber("J" + iDocket.getJuvenileNumber());
			    }		    
			}
			else
			    insObj.setPetitionNumber(iDocket.getPetitionNumber());/// add J+ if confirmed by Carla
		    
		    // task 168662
		    insObj.setInterpreter(iDocket.getInterpreter());
		    MessageUtil.postRequest(insObj);
		    recordUpd = true;
		    //send email to JPO task 152219
		    isEmailsend = sendJPOemail(iDocket);
		}
	    }
	}
	if (!recordUpd)
	{
	    form.getUpdateDocketMap().clear();
	    form.getInsertDocketMap().clear();
	    form.setErrMessage(UIConstants.EMPTY_STRING);
	    form.setMessage("NO CHANGE DETECTED FOR UPDATE");
	}
	else
	{
	    //commented as updatedocket and InsertDocketMap clears after an attorney confirmation happens in the list of records so submit says no change detected. bug fix for 150812
	    //form.getUpdateDocketMap().clear();
	    //form.getInsertDocketMap().clear();
	    form.setMessage(juvNum +" SUCCESSFULLY UPDATED. PLEASE USE SUBMIT IN CASE OF CHANGES TO OTHER RECORDS");//changed the message to alert user that only the selected record updated.
	    //below code removes the updates key from map once confirmation/bypass is done else creates/updates it again if submit is used.
	    if(confirmdocketEventId!=null&&!confirmdocketEventId.isEmpty())
	    {
        	    if(form.getUpdateDocketMap().containsKey(confirmdocketEventId))
        		form.getUpdateDocketMap().remove(confirmdocketEventId);
        	    if(form.getInsertDocketMap().containsKey(confirmdocketEventId))
        		form.getInsertDocketMap().remove(confirmdocketEventId);        	    
	    }
	}

	return aMapping.findForward(UIConstants.COURT_ACTION);
    }

    //
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward deleteSetting(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	String forwardStr = "backUpdateResult";

	String docketIds[] = form.getSelectedDocketsToUpdate();

	if (docketIds != null && docketIds.length > 0)
	{
	    boolean isError = false;
	    boolean isSuccess = false;
	    List<String> docketIdList = Arrays.asList(docketIds[0].split(","));
	    String docketId = "";
	    Map dktEventsMap = form.getDktSearchResultsMap();
	    ErrorResponseEvent error = null;
	    for (int ct = 0; ct < docketIdList.size(); ct++)
	    {
		docketId = docketIdList.get(ct);
		DocketEventResponseEvent docketResp = (DocketEventResponseEvent) dktEventsMap.get(docketId);

		if (docketResp != null)
		{
		    DeleteFutureJJSCLDetentionSettingEvent deleteEvt = new DeleteFutureJJSCLDetentionSettingEvent();
		    deleteEvt.setChainNum(docketResp.getChainNumber());
		    deleteEvt.setResetToDate(docketResp.getResetTo());
		    deleteEvt.setCourtDate(docketResp.getCourtDate());
		    deleteEvt.setSeqNumber(docketResp.getSeqNum());
		    deleteEvt.setDocketEventId(docketResp.getDocketEventId());

		    dispatch.postEvent(deleteEvt);
		    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		    error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
		    if (error != null)
		    {
			isError = true;
			break;

		    }
		    else
		    {
			docketResp.setCourtResult("");
			docketResp.setDisposition("");
			docketResp.setResetTo("");
			form.getDktSearchResultsMap().put(docketId, docketResp);
			isSuccess = true;

		    }
		}
	    }//for

	    if (isError)
	    {
		form.setErrMessage(error.getMessage());
	    }
	    if (isSuccess)
	    {
		form.setMessage("Record Successfully Deleted");
	    }
	}
	return aMapping.findForward(forwardStr);
    }

    /**
     * @param form
     * @return
     */
    private CompositeResponse validateBarNum(DetentionHearingForm form)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(form.getBarNum());

	dispatch.postEvent(request);
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	return compositeResponse;
    }

    @Override
    protected Map<String, String> getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.link", "searchDetentionHearings");
	keyMap.put("button.go", "detentionHearingActionSearchResults");
	keyMap.put("button.courtMainMenu", "courtMainMenu");
	keyMap.put("button.courtAction2", "detentionHearingActionSearchResults");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.select", "returnSelect");
	keyMap.put("button.findAttorney", "findAttorney");
	keyMap.put("button.searchGal", "searchGuardianAdlitem");
	keyMap.put("button.validateGAL", "validateGalBarNumber");
	keyMap.put("button.courtDocket", "detentionHearingDisplaySearchResults");
	keyMap.put("button.print", "generateCourtDocket");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.validate", "validateBarNumber");
	keyMap.put("button.validateaty2Barnum", "validateAty2BarNumber");
	keyMap.put("button.updateResults", "updateResult");
	keyMap.put("button.attorneySetting", "updateAttorneyConnection");
	keyMap.put("button.attorney2Setting", "updateAttorney2Connection");
	keyMap.put("button.updateLink", "updateResetDate");
	keyMap.put("button.updateAttr", "updateComments");
	keyMap.put("button.transferOut", "updateTransfer");
	keyMap.put("button.submit", "update");
	keyMap.put("button.deleteSetting", "deleteSetting");
	keyMap.put("button.attorneyConfirmation", "AttorneyConfirmation");
	keyMap.put("button.attorneyBypass", "AttorneyBypass");	
	keyMap.put("button.updateInterpreter", "updateInterpreter");
	/*keyMap.put("button.searchAty2","searchAttorney2");*/
	return keyMap;
    }

    @Override
    protected void addButtonMapping(Map keyMap)
    {
	// TODO Auto-generated method stub

    }

    public String trimAttorneyName(String atyName)
    {

	if (atyName != null && atyName.length() > 0 && atyName.charAt(atyName.trim().length() - 1) == '.')
	{
	    atyName = atyName.substring(0, atyName.length() - 1);
	}
	return atyName;
    }

    /**
     * @param barNumber
     * @return
     */
    private String padBarNumber(String barNumber)
    {

	String barNum = "";
	if (barNumber != null)
	{

	    barNum = barNumber;
	    if (barNum.length() < 8)
	    {
		StringBuffer sb = new StringBuffer(barNumber);
		for (int i = 0; i < 8 - barNumber.length(); i++)
		{
		    sb.insert(0, "0");
		}
		barNum = sb.toString();
	    }
	}
	return barNum;
    }

    private boolean isNullOrEmpty(String str)
    {
	return (str == null || str.length() == 0);
    }

    /**
     * @param updateEvent
     */
    private void updateSubsequent(DocketEventResponseEvent updateEvent)
    {

	// Find subsequent record to update

	GetJJSCLDetentionByChainAndSeqNumEvent subEvent = new GetJJSCLDetentionByChainAndSeqNumEvent();
	subEvent.setChainNumber(updateEvent.getChainNumber());
	subEvent.setSeqNumber(String.valueOf(Integer.valueOf(updateEvent.getSeqNum()) + 10));

	List<DocketEventResponseEvent> docketResp = MessageUtil.postRequestListFilter(subEvent, DocketEventResponseEvent.class);

	if (docketResp != null)
	{
	    Iterator<DocketEventResponseEvent> docketResponsesItr = docketResp.iterator();
	    if (docketResponsesItr.hasNext())
	    {

		DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketResponsesItr.next();

		//update the subsequent record with current setting details.
		UpdateJJSCLDetentionEvent updateEvt = (UpdateJJSCLDetentionEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.UPDATEJJSCLDETENTION);
		updateEvt.setSubDocketEventId(docResp.getDocketEventId());
		updateEvt.setSubChainNumber(docResp.getChainNumber());
		updateEvt.setSubSeqNumber(docResp.getSeqNum());
		updateEvt.setSubCourtId(updateEvent.getTransferTo());
		updateEvt.setSubCourtDate(DateUtil.stringToDate(updateEvent.getResetTo(), DateUtil.DATE_FMT_1));
		updateEvt.setUpdateFlag("Y");
		updateEvt.setUpdateSub("Y");
		CompositeResponse compResp = MessageUtil.postRequest(updateEvt);

		Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
		if (errResp != null)
		{
		    ErrorResponseEvent myEvt = (ErrorResponseEvent) errResp;
		    try
		    {
			handleFatalUnexpectedException(myEvt.getMessage());
		    }
		    catch (GeneralFeedbackMessageException e)
		    {
			e.printStackTrace();
		    }
		}
	    }

	}

    }
  

    /*
     * 
     */
    private String getSubsequentOid(String seqNum, String chainnum)
    {

	String oid = null;
	GetJJSCLDetentionByChainAndSeqNumEvent subEvent = new GetJJSCLDetentionByChainAndSeqNumEvent();
	subEvent.setChainNumber(chainnum);
	subEvent.setSeqNumber(String.valueOf(Integer.valueOf(seqNum) + 10));

	List<DocketEventResponseEvent> docketResp = MessageUtil.postRequestListFilter(subEvent, DocketEventResponseEvent.class);

	if (docketResp != null)
	{
	    Iterator<DocketEventResponseEvent> docketRespItr = docketResp.iterator();
	    if (docketRespItr.hasNext())
	    {

		DocketEventResponseEvent docResp = (DocketEventResponseEvent) docketRespItr.next();
		oid = docResp.getDocketEventId();
	    }
	}
	return oid;
    }
    //to email on reset task 152219
    private boolean sendJPOemail(DocketEventResponseEvent currentSettingResp)
    {
	String fromEmail = "jims2notification@itc.hctx.net";
	String juvNum =currentSettingResp.getJuvenileNumber();
	String juvName =currentSettingResp.getJuvenileName();
	
	List<JuvenileCasefileTransferredOffenseResponseEvent> transferredOffenses = UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvNum);
	Collection<JuvenileProfileReferralListResponseEvent> referralList = JuvenileFacilityHelper.getJuvReferralDetails(juvNum);	
	Collections.sort((List<JuvenileProfileReferralListResponseEvent>) referralList, Collections.reverseOrder(new Comparator<JuvenileProfileReferralListResponseEvent>() {
		@Override
		public int compare(JuvenileProfileReferralListResponseEvent evt1, JuvenileProfileReferralListResponseEvent evt2)
		{
		    if (evt1.getReferralNumber() != null && evt2.getReferralNumber() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getReferralNumber().compareTo(evt2.getReferralNumber());
		    else
			return -1;
		}
	    }));
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
	  
	    
	}
		
	    //for each referral get the casefiles, then find the supervision Category, Supervision Type of the most recent casefile assign /highest seq Num - confirmed with Carla 7/17/2018 [email]
	    JuvenileProfileReferralListResponseEvent refCasefileList = JuvenileFacilityHelper.getAllCasefilesForJuvNum(juvNum);
	    Collection<JuvenileCasefileReferral> casefileReferrals = refCasefileList.getCasefileReferrals();

	    Collections.sort((List<JuvenileCasefileReferral>) casefileReferrals, Collections.reverseOrder(new Comparator<JuvenileCasefileReferral>() {
		@Override
		public int compare(JuvenileCasefileReferral evt1, JuvenileCasefileReferral evt2)
		{
		    if (evt1.getOfficerID() != null && evt2.getOfficerID() != null)
			//return evt1.getAssignmentDate().compareTo(evt2.getAssignmentDate());	
			return evt1.getOfficerID().compareTo(evt2.getOfficerID());
		    else
			return -1;
		}
	    }));
	    Iterator<JuvenileCasefileReferral> caseFileRefItr = casefileReferrals.iterator();
	    String prevOfficer="";
	    while (caseFileRefItr.hasNext())
	    {
		//add if to check active
		JuvenileCasefileReferral caseFileReferral = caseFileRefItr.next();
		if(!caseFileReferral.getCaseStatusCd().equalsIgnoreCase("C")&&!caseFileReferral.getCaseStatusCd().equalsIgnoreCase("CS"))//not sure if to check this 
        	{
        		OfficerProfileResponseEvent officerProfileResponse = JuvenileDistrictCourtHelper.getOfficerUserId(caseFileReferral.getOfficerID());
        		if (officerProfileResponse != null)
        		{        		   
        		    String officerFullName = officerProfileResponse.getLastName() + ", " + officerProfileResponse.getFirstName();
        		    
        		    if (officerProfileResponse.getEmail() != null && !"".equals(officerProfileResponse.getEmail()))
        		    {
        			if(!prevOfficer.equalsIgnoreCase(caseFileReferral.getOfficerID()))
        			{
                			//send email notification
                			StringBuffer message = new StringBuffer(100);
                			SendEmailEvent sendEmailEvent = new SendEmailEvent();
                			sendEmailEvent.setSubject("Detention Court Reset for : " + juvName + " " + juvNum);
                			sendEmailEvent.setMessage(message.toString());
                			sendEmailEvent.setFromAddress(fromEmail);
                			sendEmailEvent.addToAddress(officerProfileResponse.getEmail());
                			setCommonMessage(message,sendEmailEvent,referralList,currentSettingResp);
                			sendEmailEvent.setContentType("text/html");
                			IDispatch dispatch1 = EventManager.getSharedInstance(EventManager.REQUEST);
                			dispatch1.postEvent(sendEmailEvent);
                			//CLM email                			
                			    String mngrUserId = officerProfileResponse.getManagerId();
                			    if (mngrUserId != null && !"".equals(mngrUserId))
                			    {
                				OfficerProfileResponseEvent respEvent = PDOfficerProfileHelper.getOfficerProfileByLogonId(mngrUserId);
                				//send CLM email notification					
                				StringBuffer mess = new StringBuffer(100);
                				SendEmailEvent sendCLMEmailEvent = new SendEmailEvent();
                				sendCLMEmailEvent.setSubject("Detention Court Reset for : " + juvName + " " + juvNum);
                				sendCLMEmailEvent.setMessage(mess.toString());
                				sendCLMEmailEvent.setFromAddress(fromEmail);
                				sendCLMEmailEvent.addToAddress(respEvent.getEmail());
                				setCommonMessage(mess, sendCLMEmailEvent, referralList, currentSettingResp);
                				sendCLMEmailEvent.setContentType("text/html");
                				IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
                				dispatch2.postEvent(sendCLMEmailEvent);    
                			    }
                			    //
                			prevOfficer=caseFileReferral.getOfficerID();
        			}
        		    }
        		    
        		}        		
		}
	    }
	    
	return true;
    }
    private void setCommonMessage(StringBuffer message,SendEmailEvent sendEmailEvent,Collection<JuvenileProfileReferralListResponseEvent> referralCollection,DocketEventResponseEvent currentSettingResp )
    {
	//referral History Details.
	String juvNum =currentSettingResp.getJuvenileNumber();
	String juvName =currentSettingResp.getJuvenileName();
	String actnDate =currentSettingResp.getResetTo();
	String reffNum =currentSettingResp.getReferralNum();
	    if(referralCollection!=null && !referralCollection.isEmpty())
	    {
            	    message.append(juvName + " , " + juvNum + ", referral number " + reffNum + " has a new court date " + actnDate);        			
            	    message.append("<BR>");
            	    message.append("<BR>");
            	    message.append("<html><body>");
            	    message.append("<table border=\"1\" colspan=\"10\">");
            	    message.append("<tr><th colspan=\"10\">Referral Summary</th></tr>");
            	    message.append("<tr>");
            	    message.append("<td>Ref No </td>");
            	    message.append("<td>Referral Date</td>");
            	    message.append("<td>Offense/Petition Allegation</td>");
            	    message.append("<td>Intake Decision</td>");
            	    message.append("<td>Court Id</td>");
            	    message.append("<td>Previous Court Date</td>");
            	    message.append("<td>Decision</td>");
            	    message.append("<td>Petition</td>");
            	    message.append("<td>Date Closed</td>");
            	    message.append("</tr>");
	    
			   Iterator<JuvenileProfileReferralListResponseEvent> juvProfCaseFileListIter = referralCollection.iterator();
			   while(juvProfCaseFileListIter.hasNext())
			   {
			       JuvenileProfileReferralListResponseEvent juvProfCaseFileListResp =juvProfCaseFileListIter.next();
				    message.append("<tr>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getReferralNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getReferralDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getReferralDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getOffense()!=null)
				    	message.append(juvProfCaseFileListResp.getOffense());
				    else
				    	message.append("");
				    message.append("/");
				    if(juvProfCaseFileListResp.getPetitionAllegation()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionAllegation());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getIntakeDecisionId()!=null)
				    	message.append(juvProfCaseFileListResp.getIntakeDecisionId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCourtId()!=null)
				    	message.append(juvProfCaseFileListResp.getCourtId());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCourtDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getCourtDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getFinalDisposition()!=null)				
				    	message.append(juvProfCaseFileListResp.getFinalDisposition());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");				    
				    if(juvProfCaseFileListResp.getPetitionNumber()!=null)
				    	message.append(juvProfCaseFileListResp.getPetitionNumber());
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("<td>");
				    if(juvProfCaseFileListResp.getCloseDate()!=null)
				    	message.append(DateUtil.dateToString(juvProfCaseFileListResp.getCloseDate(), DateUtil.DATE_FMT_1));
				    else
				    	message.append("");
				    message.append("</td>");
				    message.append("</tr>");
			   }
	    }
	    else
		message.append("Please refer to Data Corrections for details.");
	    message.append("</table></body></html>");
	    sendEmailEvent.setMessage(message.toString());
    }

}
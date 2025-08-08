package ui.juvenilecase.detentionCourtHearings.action;

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
import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByDetentionCourtEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByRefereeCourtEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenilecase.GetJJSCourtEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilewarrant.GetJJSPetitionsEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.contact.officer.OfficerProfile;
import pd.juvenilecase.JJSFacility;
import pd.juvenilecase.JJSHeader;
import pd.juvenilecase.JuvenileCaseHelper;
import pd.juvenilecase.JuvenileCasefileReferral;
import pd.juvenilewarrant.JJSPetition;
import ui.common.Name;
import ui.juvenilecase.detentionCourtHearings.DetentionHearingDocketBean;
import ui.juvenilecase.detentionCourtHearings.form.DetentionHearingForm;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author sthyagarajan
 */
public class DisplayJuvenileSearchDetentionHearingsAction extends LookupDispatchAction
{

    /**
	 * 
	 */
    public DisplayJuvenileSearchDetentionHearingsAction()
    {

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
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	form.setDate("");
	form.setFacility("");
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
	form.setErrMessage(UIConstants.EMPTY_STRING);
	form.setMessage(UIConstants.EMPTY_STRING);
	form.setUpdateDocketMap(null);
	form.setInsertDocketMap(null);

	Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	List<JSONObject> countyHolidayList = JuvenileDistrictCourtHelper.juvenileHolidays();
	form.setHolidaysList(countyHolidayList);

	List<JSONObject> courtDecisionList = JuvenileDistrictCourtHelper.detentionCourtDecisions();
	form.setDetentionCourtDecisions(courtDecisionList);

	String refereeCourt = "";
	String paddedCourt = "";
	form.setPagerOffset("0");
	form.setPageNum("0");

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

	form.setCourtDecisions(JuvenileCaseHelper.getCourtDecisionsNew()); //fill in the court decisions. for the result field
	/**
	 * Call jjs detention using court and courtDate. The application
	 * retrieved the docket (JUVENILE_ DETENTION_HEARING), if any, set for
	 * the facility�s referee court on the user-identified court date.
	 */
	IDispatch disp = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJJSCLDetentionByRefereeCourtEvent jjsdetnCrtEvent = (GetJJSCLDetentionByRefereeCourtEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYREFEREECOURT);
	jjsdetnCrtEvent.setCourtDate(DateUtil.stringToDate(form.getDate(), DateUtil.DATE_FMT_1));
	jjsdetnCrtEvent.setCourtId(refereeCourt);
	jjsdetnCrtEvent.setFacilityId(form.getFacility());
	disp.postEvent(jjsdetnCrtEvent);
	CompositeResponse resp = (CompositeResponse) disp.getReply();

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

	    Iterator<DocketEventResponseEvent> docketIter = dktRespEvts.iterator();

	    while (docketIter.hasNext())
	    {
		DocketEventResponseEvent docRespEvent = docketIter.next();
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
		docketsMap.put(docRespEvent.getDocketEventId(), docRespEvent);
	 }
	 form.setSearchResultSize(dktRespEvts.size());
	 form.setDktSearchResultsMap(docketsMap);
	 form.setDetentionSearchResults(dktRespEvts);
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
	 * follows� Primary sort � descending alphabetical Hearing Type
	 * Secondary sort � ascending juvenile number within Hearing Type
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
	 * the facility�s referee court on the user-identified court date.
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
			    // docRespEvent.setHearingType(crtDocResp.getHearingType());
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
//		Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
//		if (casefileItr.hasNext())
//		{
//		    JuvenileCasefileReferral responseEvt = casefileItr.next();
//		    if (responseEvt != null)
//		    {
//			OfficerProfile profile = responseEvt.getCaseFile().getProbationOfficer();
//			docRespEvent.setProbationOfficerCd(profile.getLogonId());
//			Name offName = new Name(profile.getFirstName(), profile.getMiddleName(), profile.getLastName());
//			docRespEvent.setProbationOfficer(offName.toString());
//		    }
//		}
		
		JuvenileCasefileReferral highestRefSeqNumRecord = this.getHighestRefSeqNum(casefilesResp);
		if(highestRefSeqNumRecord != null){
		    	OfficerProfile profile = highestRefSeqNumRecord.getCaseFile().getProbationOfficer();
			docRespEvent.setProbationOfficerCd(profile.getLogonId());
			Name offName = new Name(profile.getFirstName(), profile.getMiddleName(), profile.getLastName());
			docRespEvent.setProbationOfficer(offName.toString());
		}

		dockets.add(docRespEvent);
		//} //juvenile no check
		//}
	    }//end of for loop

	    //sorts in ascending order by juvNum.
	    Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    return Integer.valueOf(evt2.getJuvenileNumber()).compareTo(Integer.valueOf(evt1.getJuvenileNumber()));
		}
	    }));

	    //sorts in descending order by hearing type.
	    Collections.sort((List<DocketEventResponseEvent>) dockets, new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    if (evt2.getHearingType() != null && evt1.getHearingType() != null)
			return evt1.getHearingType().compareTo(evt2.getHearingType());
		    else
			return -1;
		}
	    });

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
    
    public JuvenileCasefileReferral getHighestRefSeqNum(Collection<JuvenileCasefileReferral> casefileReferral)
    {
	JuvenileCasefileReferral juvCasefileReferral = null;
	
	int maxRefSeqNum = 0;
	Iterator<JuvenileCasefileReferral> casefileItr = casefileReferral.iterator();
	while(casefileItr.hasNext()){
	    
	    JuvenileCasefileReferral casefileEvt = casefileItr.next();
	    if(casefileEvt != null){
		int casefileRefSeqNum = casefileEvt.getRefSeqNum() != null ? Integer.parseInt(casefileEvt.getRefSeqNum()) : 0;
		if(casefileRefSeqNum > maxRefSeqNum){
			maxRefSeqNum = casefileRefSeqNum;
			juvCasefileReferral = casefileEvt;
		  }
	    }
	    
	}
	
   	return juvCasefileReferral;
    }

    /**
     * detentionHearingDisplaySearchResults2
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward detentionHearingDisplaySearchResults2(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
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
	 * follows� Primary sort � descending alphabetical Hearing Type
	 * Secondary sort � ascending juvenile number within Hearing Type
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
	 * the facility�s referee court on the user-identified court date.
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
//		Iterator<JuvenileCasefileReferral> casefileItr = casefilesResp.iterator();
//		if (casefileItr.hasNext())
//		{
//		    JuvenileCasefileReferral responseEvt = casefileItr.next();
//		    if (responseEvt != null)
//		    {
//			OfficerProfile profile = responseEvt.getCaseFile().getProbationOfficer();
//			docRespEvent.setProbationOfficerCd(profile.getLogonId());
//			Name offName = new Name(profile.getFirstName(), profile.getMiddleName(), profile.getLastName());
//			docRespEvent.setProbationOfficer(offName.toString());
//		    }
//		}
		
		JuvenileCasefileReferral highestRefSeqNumRecord = this.getHighestRefSeqNum(casefilesResp);
		if(highestRefSeqNumRecord != null){
		    	OfficerProfile profile = highestRefSeqNumRecord.getCaseFile().getProbationOfficer();
			docRespEvent.setProbationOfficerCd(profile.getLogonId());
			Name offName = new Name(profile.getFirstName(), profile.getMiddleName(), profile.getLastName());
			docRespEvent.setProbationOfficer(offName.toString());
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
	    return aMapping.findForward("failure2");
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
	DetentionHearingForm form = (DetentionHearingForm) aForm;
	form.setDate("");
	form.setFacility("");
	return aMapping.findForward(UIConstants.CANCEL);
    }

    @Override
    protected Map<String, String> getKeyMethodMap()
    {
	Map<String, String> keyMap = new HashMap<String, String>();
	keyMap.put("button.link", "searchDetentionHearings");
	keyMap.put("button.go", "detentionHearingDisplaySearchResults2");
	keyMap.put("button.courtAction2", "detentionHearingActionSearchResults");
	keyMap.put("button.courtDocket", "detentionHearingDisplaySearchResults");
	keyMap.put("button.print", "generateCourtDocket");
	keyMap.put("button.cancel", "cancel");
	return keyMap;
    }

}

package ui.juvenilecase.action;

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

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import messaging.codetable.criminal.reply.JuvenileFacilityResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.juvenilecase.GetJuvenileCaseLoadByOfficerEvent;
import messaging.juvenilecase.SearchJuvenileCasefileByZipCodeAttributeEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.JuvenileCaseLoadRepsonseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;
import pd.codetable.SimpleCodeTableHelper;
import pd.juvenilecase.JuvenileCasefile;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.CasefileSearchForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * @author dgibler
 */
public class DisplayJuvenileCasefileSearchResultsAction extends Action
{
    /**
     * @roseuid 4278CA1D0093
     */
    public DisplayJuvenileCasefileSearchResultsAction()
    {
    }

    private ActionForward printCaseloadSearchResults(HttpServletRequest aRequest, HttpServletResponse aResponse, CasefileSearchForm form)
    {
	HttpSession aSession = aRequest.getSession();
	CasefileSearchForm searchForm = form;
	//(CasefileSearchForm) aSession.getAttribute("casefileSearchForm");
	CaseloadSearchResultsReportBean reportBean = new CaseloadSearchResultsReportBean();

	List<JuvenileRecord> myList = new ArrayList<>();

	Iterator resultsIter = searchForm.getCaseloads().iterator();

	while (resultsIter.hasNext())
	{
	    JuvenileCaseLoadRepsonseEvent res = (JuvenileCaseLoadRepsonseEvent) resultsIter.next();

	    if (res.getCasefileAssignments().size() > 0)
	    {
		Iterator assFilter = res.getCasefileAssignments().iterator();
		int i = 0;
		while (assFilter.hasNext())
		{
		    messaging.juvenilecase.reply.JuvenileCasefileEvent res1 = (messaging.juvenilecase.reply.JuvenileCasefileEvent) assFilter.next();
		    JuvenileRecord newR = new JuvenileRecord();
		    
		    if (i < 1)
		    {
			newR.setJuvenileName(res.getJuvenileName());
			newR.setJuvenileNumber(res.getJuvenileNum());

			if (res.getDetentionFacility() != null && !res.getDetentionFacility().isEmpty())
			{
			    JuvenileFacilityResponseEvent activeFacilityResponseEvent = JuvenileFacilityHelper.getFacilityByCode(res.getDetentionFacility());
			    if (activeFacilityResponseEvent != null)
			    {
				newR.setFacilityLocation(activeFacilityResponseEvent.getDescription());
			    }
			    else
			    {
				newR.setFacilityLocation("");
			    }
			}

			if (res.getDetentionStatus() != null && !res.getDetentionStatus().isEmpty())
			{
			    newR.setFacilityStatus(SimpleCodeTableHelper.getDescrByCode("FACILITY_STATUS", res.getDetentionStatus())); //set facility status desc.
			}
		    }

		    newR.setSupervisionNumber(res1.getSupervisionNum());
		    newR.setSupervisionType(res1.getSupervisionType());
		    newR.setExpectedEndDate(res1.getSupervisionEndDate());

		    Iterator refFilter = res1.getReferralNumCourtDates().iterator();
		    String refNum = "";
		    String courtDate = "";
		    String courtID = "";
		    int seqnum = 0;
		    while (refFilter.hasNext())
		    {
			messaging.juvenilecase.reply.JuvenileReferralCourtDateResponseEvent c1 = (messaging.juvenilecase.reply.JuvenileReferralCourtDateResponseEvent) refFilter.next();

			if (c1.getSequenceNum() != null && !c1.getSequenceNum().isEmpty())
			{
			    if (seqnum < Integer.parseInt(c1.getSequenceNum()))
			    {
				refNum = refNum + " " + c1.getReferralNumber();
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				//added for Hot fix - Bug #37952
				if (c1.getCourtDate() != null)
				    courtDate = sdf.format(c1.getCourtDate());

				if (c1.getCourtId() != null)
				    courtID = c1.getCourtId();
			    }
			    seqnum = Integer.parseInt(c1.getSequenceNum());
			}
		    }

		    newR.setReferral(refNum);
		    newR.setCourtDate(courtDate);
		    newR.setCourtID(courtID);

		    myList.add(newR);
		    i++;
		}
	    }
	    else
	    {
		JuvenileRecord newR = new JuvenileRecord();

		newR.setJuvenileName(res.getJuvenileName());
		newR.setJuvenileNumber(res.getJuvenileNum());
		newR.setFacilityLocation(res.getDetentionFacility());
		newR.setFacilityStatus(res.getDetentionStatus());
		myList.add(newR);
	    }
	}

	reportBean.setNumberOfCasefiles(searchForm.getCasefilesCount());
	//go through the casefiles and set the date format for supervisionEndDate
	/*	Iterator resultsIter = searchForm.getCaseloads().iterator();
		Collection tempResults = new ArrayList();
		while (resultsIter.hasNext())
		{
		    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) resultsIter.next();
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    //added for Hot fix - Bug #37952
		    if (resp.getSupervisionEndDate() != null)
			resp.setSupervisionEndDateStr(sdf.format(resp.getSupervisionEndDate()));
		    tempResults.add(resp);
		}*/
	reportBean.setCaseloadSearchResults(myList);
	//reportBean.setStatusSearchType(CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, searchForm.getCaseStatusTypeId2()));

	//Bug #171580
	if (searchForm.getOfficerName() != null)
	    reportBean.setStatusSearchType(searchForm.getOfficerName());

	aSession.setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CASELOAD_SEARCH_RESULTS_REPORT);
	//form.setAction(""); // added for bug #37247
	return null;

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4278C7B90057
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CasefileSearchForm form = (CasefileSearchForm) aForm;

	String printChk = aRequest.getParameter("print");
	if (printChk != null && printChk.equalsIgnoreCase("Y"))
	{
	    return printCaseloadSearchResults(aRequest, aResponse, form); // added for bug #37247
	}

	//Added for US 32107 - check if the User has feature to view Resticted Access kids
	boolean grantedFeature = false;
	try
	{

	    ISecurityManager securityManager = (ISecurityManager) ContextManager.getSession().get("ISecurityManager");
	    if (securityManager != null)
	    {

		String feature = "JCW-RAJ-VIEW";
		grantedFeature = securityManager.isAllowed(feature);
		if (grantedFeature)
		    form.setRestrictedAccessFeature("Y");
		else
		    form.setRestrictedAccessFeature("N");
	    }
	}
	catch (Throwable e)
	{
	    // ignore any exception as this is not visible to the user        
	}
	//<KISHORE>JIMS200059601 : Emulate CSCD Caseload Search-Search CF (UI) - KK
	if (PDJuvenileCaseConstants.SEARCH_CASE_LOAD.equalsIgnoreCase(form.getSearchTypeId()))
	{
	    GetJuvenileCaseLoadByOfficerEvent caseLoadEvent = (GetJuvenileCaseLoadByOfficerEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASELOADBYOFFICER);

	    caseLoadEvent.setOfficerId(form.getOfficer());
	    caseLoadEvent.setCaseStatusCd(form.getCaseStatusCd());
	    //<KISHORE>JIMS200060469 : MJCW CF: Add Caseload Search date range(UI)-KK
	    caseLoadEvent.setCasefileActivationStDate(form.getCasefileActivationStDate());
	    caseLoadEvent.setCasefileActivationEndDate(form.getCasefileActivationEndDate());
	    caseLoadEvent.setCasefileExpectedStartDate(form.getCaseloadExpectedEndDateFrom());
	    caseLoadEvent.setCasefileExpectedEndDate(form.getCaseloadExpectedEndDateTo());

	    CompositeResponse response = MessageUtil.postRequest(caseLoadEvent);

	    CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(response, CountInfoMessage.class);
	    if (iMessage != null)
	    {
		// More than 2000 results: forward 'failure'
		ActionErrors errors = new ActionErrors();
		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    else
	    {
		List caseLoads = MessageUtil.compositeToList(response, JuvenileCaseLoadRepsonseEvent.class);
		form.setCaseloads(caseLoads);
		// remove records with no juvenile name -- this indicates a sealed record
		// added 4/22/2013 - defect 75442
		if (caseLoads.size() > 0)
		{
		    List wrkList = new ArrayList();
		    String juvName = "";
		    for (int x = 0; x < caseLoads.size(); x++)
		    {
			JuvenileCaseLoadRepsonseEvent jcrEvent = (JuvenileCaseLoadRepsonseEvent) caseLoads.get(x);

			//added for US 32107 - check for restricted access						
			boolean restrictedAccess = UIJuvenileHelper.checkRestrictedAcces(jcrEvent.getJuvenileNum());
			if (restrictedAccess)
			    jcrEvent.setRestrictedAccess("Y");
			else
			    jcrEvent.setRestrictedAccess("N");

			juvName = jcrEvent.getJuvenileFirstName() + jcrEvent.getJuvenileLastName();
			if (!"nullnull".equals(juvName))
			{
			    wrkList.add(jcrEvent);
			}
		    }
		    caseLoads = wrkList;
		    wrkList = null;
		    juvName = null;
		    form.setCaseloads(caseLoads);
		}

		if (caseLoads.size() > 0)
		{ // removed check for null since compositeToList returns at least an empty collection
		    JuvenileCaseLoadRepsonseEvent event = (JuvenileCaseLoadRepsonseEvent) caseLoads.get(0);
		    form.setJuvenilesCount(event.getActiveJuvenilesCount());
		    form.setCasefilesCount(event.getActiveCasefilesCount());
		    Iterator<OfficerProfileResponseEvent> iter = form.getOfficers().iterator();
		    while (iter.hasNext())
		    {
			OfficerProfileResponseEvent resp = iter.next();
			if (form.getOfficer().equalsIgnoreCase(resp.getOfficerId()))
			{
			    form.setOfficerLastName(resp.getLastName());
			    form.setOfficerFirstName(resp.getFirstName());
			    form.setOfficerMiddleName(resp.getMiddleName());
			}
		    }
		}
		if (caseLoads.size() == 0)
		{
		    // zero results: forward 'searchFailure'
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
		    saveErrors(aRequest, errors);
		    return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
	    }
	    return aMapping.findForward("caseloadSearchResults");
	}

	SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.SEARCHJUVENILECASEFILES);

	// Set default searchType to PDJuvenileCaseConstants.SEARCH_CASE_STATUS
	String searchType = (form.getSearchTypeId() != null && form.getSearchTypeId().length() > 0) ? form.getSearchTypeId() : PDJuvenileCaseConstants.SEARCH_CASE_STATUS;

	/* Populate everything from the form. The Command will use the correct
	 * values based off the search type selected by the user.
	 */
	search.setFirstName(form.getFirstName());
	search.setMiddleName(form.getMiddleName());
	search.setLastName(form.getLastName());
	search.setOfficerFirstName(form.getOfficerFirstName());
	search.setOfficerMiddleName(form.getOfficerMiddleName());
	search.setOfficerLastName(form.getOfficerLastName());
	search.setSearchType(searchType);
	search.setJuvenileNum(form.getJuvenileNum());
	search.setSupervisionNum(form.getSupervisionNum());
	search.setLocation(form.getLocationId());
	search.setZipCode(form.getZipCode()); //#32659 changes

	/* Pick the correct supervision type and case status off the form based
	 * off of which search type is done. '2' properties are for Supervision Type.
	 */
	if (searchType.equals(PDJuvenileCaseConstants.SEARCH_CASE_STATUS))
	{
	    String supervisionType = form.getSupervisionTypeId2();

	    search.setSupervisionType(supervisionType);
	    //search.setCaseStatus( form.getCaseStatusTypeId2() );
	    if (form.getCaseStatusTypeId2().equalsIgnoreCase("ACTIVE"))
	    {
		search.setCaseStatus("A");
	    }
	    else
		if (form.getCaseStatusTypeId2().equalsIgnoreCase("CLOSED"))
		{
		    search.setCaseStatus("C");
		}
		else
		    if (form.getCaseStatusTypeId2().equalsIgnoreCase("PENDING"))
		    {
			search.setCaseStatus("P");
		    }
		    else
			if (form.getCaseStatusTypeId2().equalsIgnoreCase("CLOSING IN PROCESS"))
			{
			    search.setCaseStatus("CIP");
			}
	    search.setLocation(form.getLocationId());
	    search.setOfficerFirstName(form.getOfficerFirstName());
	    search.setOfficerMiddleName(form.getOfficerMiddleName());
	    search.setOfficerLastName(form.getOfficerLastName());
	    search.setCasefileExpectedEndDateFrom(form.getCaseStatusExpectedEndDateFrom());
	    search.setCasefileExpectedEndDateTo(form.getCaseStatusExpectedEndDateTo());
	    search.setCasefileDispositionDateFrom(form.getCaseStatusDispDateFrom());
	    search.setCasefileDispositionDateTo(form.getCaseStatusDispDateTo());
	    search.setZipCode(form.getZipCode()); //#32659 changes
	}
	else
	    if (searchType.equals(PDJuvenileCaseConstants.SEARCH_SUPERVISION_TYPE))
	    {
		search.setSupervisionType(form.getSupervisionTypeId2());
		search.setCaseStatus(form.getCaseStatusTypeId2());
	    }

	CompositeResponse response = MessageUtil.postRequest(search);

	ActionForward forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);

	/* Handle error thrown as ErrorResponseEvent from the command, 
	 * if there is any Expected error: Number of juveniles matching 
	 * this criteria is greater than 2000.
	 */
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    saveErrors(aRequest, errors);
	    forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	}
	else
	{
	    //#32659 changes starts
	    List<JuvenileCasefileSearchResponseEvent> casefiles = MessageUtil.compositeToList(response, JuvenileCasefileSearchResponseEvent.class); // result set from officer casefile.

	    if (searchType.equals(PDJuvenileCaseConstants.SEARCH_CASE_STATUS))
	    {
		Map<String, String> uniqueRecords = new HashMap<String, String>();
		boolean hasAnyPrimaryContact = false;
		//Get the most recent record and filter by casefile_Id.move the null values to the end.
		Collections.sort(casefiles, Collections.reverseOrder(new Comparator<JuvenileCasefileSearchResponseEvent>() {
		    @Override
		    public int compare(JuvenileCasefileSearchResponseEvent evt1, JuvenileCasefileSearchResponseEvent evt2)
		    {
			if (evt1.getFmMemCreateDate() == null && evt2.getFmMemCreateDate() == null)
			{
			    return 0;
			}
			if (evt1.getFmMemCreateDate() == null)
			{
			    return -1;
			}
			if (evt2.getFmMemCreateDate() == null)
			{
			    return 1;
			}
			return evt1.getFmMemCreateDate().compareTo(evt2.getFmMemCreateDate());
		    }
		}));

		List<JuvenileCasefileSearchResponseEvent> new_casefiles = new ArrayList<JuvenileCasefileSearchResponseEvent>();
		List<JuvenileCasefileSearchResponseEvent> casefiles_np = new ArrayList<JuvenileCasefileSearchResponseEvent>();
		Iterator<JuvenileCasefileSearchResponseEvent> casefileSearchRespEvt = null;
		//US 50044 changes to add disposition date range in Case Status search
		Date dispDateFrom = DateUtil.stringToDate(form.getCaseStatusDispDateFrom(), DateUtil.DATE_FMT_1);
		Date dispDateTo = DateUtil.stringToDate(form.getCaseStatusDispDateTo(), DateUtil.DATE_FMT_1);
		if (dispDateFrom != null && dispDateTo != null)
		{
		    if (casefiles.size() != 0)
		    {
			Iterator<JuvenileCasefileSearchResponseEvent> casefileSearchRespEvt1 = casefiles.iterator();
			casefiles = new ArrayList<JuvenileCasefileSearchResponseEvent>();
			while (casefileSearchRespEvt1 != null && casefileSearchRespEvt1.hasNext())
			{

			    JuvenileCasefileSearchResponseEvent checkResult = casefileSearchRespEvt1.next();
			    String dispDateStr = checkResult.getDispositionDate();
			    Date dispDate = DateUtil.stringToDate(dispDateStr, DateUtil.DATE_FMT_1);
			    if (dispDate != null)
			    {
				if (DateUtil.compare(dispDate, dispDateFrom, DateUtil.DATE_FMT_1) >= 0 && DateUtil.compare(dispDate, dispDateTo, DateUtil.DATE_FMT_1) <= 0)
				{
				    casefiles.add(checkResult);
				}
			    }
			    casefileSearchRespEvt = casefiles.iterator();
			}
		    }
		    else
		    {
			casefileSearchRespEvt = casefiles.iterator();
		    }
		}
		else
		{
		    casefileSearchRespEvt = casefiles.iterator();

		}
		while (casefileSearchRespEvt.hasNext()) //seperate primary and non-primary contact.
		{
		    JuvenileCasefileSearchResponseEvent respEvent = (JuvenileCasefileSearchResponseEvent) casefileSearchRespEvt.next();

		    if (!uniqueRecords.containsKey(respEvent.getSupervisionNum()))
		    {
			if (respEvent.isPrimaryContact())
			{
			    uniqueRecords.put(respEvent.getSupervisionNum(), respEvent.getJuvenileNum());
			    new_casefiles.add(respEvent);
			}
			else
			{
			    casefiles_np.add(respEvent); //casefiles with no primary contact.
			}
		    }
		}

		// if no primary contact found.use the most recent record with inhome status true.				

		casefileSearchRespEvt = casefiles_np.iterator();
		while (casefileSearchRespEvt.hasNext())
		{
		    JuvenileCasefileSearchResponseEvent respEvent = (JuvenileCasefileSearchResponseEvent) casefileSearchRespEvt.next();

		    //Even if it is a in home status and if zip code is on
		    if (form.getZipCode() != null && !form.getZipCode().equals(""))
		    {
			if (respEvent.isInHomeStatus() && !uniqueRecords.containsKey(respEvent.getSupervisionNum()))
			{
			    //search by juvenile id and check if it has any records of primary contact.
			    SearchJuvenileCasefileByZipCodeAttributeEvent attrEvent = new SearchJuvenileCasefileByZipCodeAttributeEvent();
			    attrEvent.setJuvenileId(respEvent.getJuvenileNum());
			    JuvenileCasefile casefile = new JuvenileCasefile();
			    Iterator<JuvenileCasefile> casefiles_attr = casefile.findAll(attrEvent);
			    while (casefiles_attr.hasNext())
			    {
				casefile = casefiles_attr.next();
				if (casefile.getIsPrimaryContact())
				{
				    uniqueRecords.put(respEvent.getSupervisionNum(), respEvent.getJuvenileNum());
				    hasAnyPrimaryContact = true;
				    break;
				}
			    }

			    if (!hasAnyPrimaryContact)
			    {
				uniqueRecords.put(respEvent.getSupervisionNum(), respEvent.getJuvenileNum());
				new_casefiles.add(respEvent);
			    }
			}
		    }
		    else
		    {
			if (!uniqueRecords.containsKey(respEvent.getSupervisionNum()))
			{
			    uniqueRecords.put(respEvent.getSupervisionNum(), respEvent.getJuvenileNum());
			    new_casefiles.add(respEvent);
			}
		    }
		}

		//count implementation starts
		Map<String, JuvenileCasefileSearchResponseEvent> zipCodes_juvCount = new HashMap<String, JuvenileCasefileSearchResponseEvent>();
		Map<String, JuvenileCasefileSearchResponseEvent> officers_juvCount = new HashMap<String, JuvenileCasefileSearchResponseEvent>();
		Map<String, JuvenileCasefileSearchResponseEvent> suprvsns_juvCount = new HashMap<String, JuvenileCasefileSearchResponseEvent>();

		int zipCode_juvCount = 1;
		int officer_juvCount = 1;
		int supervisionType_juvCount = 1;

		int totZip_juvCount = 0;
		int totOfficer_juvCount = 0;
		int totSupervisionType_juvCount = 0;

		Iterator<JuvenileCasefileSearchResponseEvent> profileRespItr = new_casefiles.iterator();
		while (profileRespItr.hasNext())
		{
		    JuvenileCasefileSearchResponseEvent file = (JuvenileCasefileSearchResponseEvent) profileRespItr.next();

		    //ZipCode Implementation
		    if (file.getZipCode() != null && !file.getZipCode().isEmpty())
		    {
			JuvenileCasefileSearchResponseEvent respEvent = new JuvenileCasefileSearchResponseEvent();
			if (zipCodes_juvCount.containsKey(file.getZipCode()))
			{
			    respEvent.setZipCode(file.getZipCode());
			    respEvent.setZipCode_juvenileCount(zipCodes_juvCount.get(file.getZipCode()).getZipCode_juvenileCount() + 1);
			    zipCodes_juvCount.put(file.getZipCode(), respEvent);
			    totZip_juvCount++;
			}
			else
			{
			    respEvent.setZipCode(file.getZipCode());
			    respEvent.setZipCode_juvenileCount(zipCode_juvCount);
			    zipCodes_juvCount.put(file.getZipCode(), respEvent);
			    totZip_juvCount++;
			}
		    }

		    //ProbationOfficerFullName
		    if (file.getProbationOfficerFullName() != null && !file.getProbationOfficerFullName().isEmpty())
		    {
			JuvenileCasefileSearchResponseEvent respEvent = new JuvenileCasefileSearchResponseEvent();
			if (officers_juvCount.containsKey(file.getProbationOfficerFullName()))
			{
			    respEvent.setProbationOfficerFullName(file.getProbationOfficerFullName());
			    respEvent.setOfficer_juvenileCount(officers_juvCount.get(file.getProbationOfficerFullName()).getOfficer_juvenileCount() + 1);
			    officers_juvCount.put(file.getProbationOfficerFullName(), respEvent);
			    totOfficer_juvCount++;
			}
			else
			{
			    respEvent.setProbationOfficerFullName(file.getProbationOfficerFullName());
			    respEvent.setOfficer_juvenileCount(officer_juvCount);
			    officers_juvCount.put(file.getProbationOfficerFullName(), respEvent);
			    totOfficer_juvCount++;
			}
		    }

		    //Supervision Type
		    if (file.getSupervisionType() != null && !file.getSupervisionType().isEmpty())
		    {
			JuvenileCasefileSearchResponseEvent respEvent = new JuvenileCasefileSearchResponseEvent();
			if (suprvsns_juvCount.containsKey(file.getSupervisionType()))
			{
			    respEvent.setSupervisionType(file.getSupervisionType());
			    respEvent.setSupType_juvenileCount(suprvsns_juvCount.get(file.getSupervisionType()).getSupType_juvenileCount() + 1);
			    suprvsns_juvCount.put(file.getSupervisionType(), respEvent);
			    totSupervisionType_juvCount++;
			}
			else
			{
			    respEvent.setSupervisionType(file.getSupervisionType());
			    respEvent.setSupType_juvenileCount(supervisionType_juvCount);
			    suprvsns_juvCount.put(file.getSupervisionType(), respEvent);
			    totSupervisionType_juvCount++;
			}
		    }
		}
		// convert it to collections
		form.setZipCodes_count(zipCodes_juvCount.values());
		form.setOfficers_count(officers_juvCount.values());
		form.setSupervisions_count(suprvsns_juvCount.values());

		//set the total
		form.setTotZipJuvCount(String.valueOf(totZip_juvCount));
		form.setTotOffJuvCount(String.valueOf(totOfficer_juvCount));
		form.setTotSupvJuvCount(String.valueOf(totSupervisionType_juvCount));
		//#32659 changes ends

		// modified casefile : response  associated from JCOFFICERCASEFILE
		forward = redirectCaseStatusResponse(aMapping, aRequest, forward, response, form, searchType, new_casefiles);

	    }
	    else
	    {
		//added for US 32107
		if (searchType.equalsIgnoreCase("SNUM") && casefiles.size() != 0)
		{
		    JuvenileCasefileSearchResponseEvent result = casefiles.get(0);
		    form.setJuvenileNum(result.getJuvenileNum());
		}

		if ((casefiles.size() == 1 || searchType.equalsIgnoreCase("JNUM")) && UIJuvenileHelper.checkRestrictedAcces(form.getJuvenileNum()) && form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		{
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
		    saveErrors(aRequest, errors);
		    forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
		    return forward;
		}

		//response not associated from JCOFFICERCASEFILE
		forward = redirectResponse(aMapping, aRequest, forward, response, form, searchType, casefiles);

	    }
	}
	return forward;
    }

    /**
     * For casefile status search
     * 
     * @param casefiles
     */
    @SuppressWarnings("null")
    private ActionForward redirectCaseStatusResponse(ActionMapping aMapping, HttpServletRequest aRequest, ActionForward forward, CompositeResponse response, CasefileSearchForm form, String searchType, List<JuvenileCasefileSearchResponseEvent> casefilesOriginal)
    {
	// #32659 changes ends
	form.setSearchResultSize(casefilesOriginal.size());
	//US 50044 changes to add disposition date range in Case Status search
	List<JuvenileCasefileSearchResponseEvent> casefiles = casefilesOriginal;
	Date dispDateFrom = DateUtil.stringToDate(form.getCaseStatusDispDateFrom(), DateUtil.DATE_FMT_1);
	Date dispDateTo = DateUtil.stringToDate(form.getCaseStatusDispDateTo(), DateUtil.DATE_FMT_1);
	if (dispDateFrom != null && dispDateTo != null)
	{
	    Iterator<JuvenileCasefileSearchResponseEvent> casefileSearchRespEvt1 = casefilesOriginal.iterator();
	    casefiles = new ArrayList<JuvenileCasefileSearchResponseEvent>();
	    while (casefileSearchRespEvt1 != null && casefileSearchRespEvt1.hasNext())
	    {

		JuvenileCasefileSearchResponseEvent checkResult = casefileSearchRespEvt1.next();
		String dispDateStr = checkResult.getDispositionDate();
		Date dispDate = DateUtil.stringToDate(dispDateStr, DateUtil.DATE_FMT_1);
		if (dispDate != null)
		{
		    if (DateUtil.compare(dispDate, dispDateFrom, DateUtil.DATE_FMT_1) >= 0 && DateUtil.compare(dispDate, dispDateTo, DateUtil.DATE_FMT_1) <= 0)
		    {
			casefiles.add(checkResult);
		    }
		}
		form.setSearchResultSize(casefiles.size());
	    }
	}
	Iterator<JuvenileCasefileSearchResponseEvent> casefileSearchRespEvt = casefiles.iterator();

	switch (casefiles.size())
	{
	case 0:
	    // zero results: forward 'searchFailure'
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
	    saveErrors(aRequest, errors);
	    forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	    break;

	case 1:
	    // single result: forward to casefile details

	    JuvenileCasefileSearchResponseEvent result = casefileSearchRespEvt.next();

	    //added for US 32107
	    if (UIJuvenileHelper.checkRestrictedAcces(result.getJuvenileNum()) && form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
	    {
		errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
		saveErrors(aRequest, errors);
		forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
		return forward;
	    }

	    ActionForward oldForward = aMapping.findForward(UIConstants.SUCCESS);
	    /*forward = new ActionForward( oldForward.getPath() + "?supervisionNum=" 
	     *		+ result.getSupervisionNum() + "&juvenileNum=" 
	     *		+ result.getJuvenileNum() + "&submitAction=Link" );
	     */

	    /* in case you need to get around the display of Briefing and go 
	     * straight to Casefile, uncomment the next line and comment out 
	     * the above "forward" assignment
	     */
	    if (searchType.equals(PDJuvenileCaseConstants.SEARCH_SUPERVISION_NUMBER))
	    {
		forward = new ActionForward("displayJuvenileCasefileDetails.do?supervisionNum=" + result.getSupervisionNum());
	    }
	    else
	    {
		forward = new ActionForward(oldForward.getPath() + "?supervisionNum=" + result.getSupervisionNum() + "&juvenileNum=" + result.getJuvenileNum() + "&submitAction=Link");
	    }

	    forward.setRedirect(true);

	    break;

	default:
	    // default to many results: set count and forward to 'listSuccess'
	    Collections.sort(casefiles);
	    if (form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		form.setCasefileSearchResults(setRestrictedAccesMarkers(casefiles));
	    else
		form.setCasefileSearchResults(casefiles);
	    //form.setCasefileSearchResults( casefiles );
	    forward = aMapping.findForward(UIConstants.LISTSUCCESS);
	    break;
	}
	return forward;
    }

    /**
     * For other search
     * 
     * @param casefiles
     */
    private ActionForward redirectResponse(ActionMapping aMapping, HttpServletRequest aRequest, ActionForward forward, CompositeResponse response, CasefileSearchForm form, String searchType, List<JuvenileCasefileSearchResponseEvent> casefiles)
    {
	//#32659 changes ends
	form.setSearchResultSize(casefiles.size());

	switch (casefiles.size())
	{
	case 0:
	    // zero results: forward 'searchFailure'
	    ActionErrors errors = new ActionErrors();
	    /*errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));*/
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.nocasefileRecords"));
	    saveErrors(aRequest, errors);
	    forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	    break;

	case 1:
	    // single result: forward to casefile details
	    JuvenileCasefileSearchResponseEvent result = (JuvenileCasefileSearchResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileSearchResponseEvent.class);

	    //added for US 32107
	    if (UIJuvenileHelper.checkRestrictedAcces(result.getJuvenileNum()) && form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
	    {
		errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
		saveErrors(aRequest, errors);
		forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
		return forward;
	    }
	    ActionForward oldForward = aMapping.findForward(UIConstants.SUCCESS);
	    /*forward = new ActionForward( oldForward.getPath() + "?supervisionNum=" 
	     *		+ result.getSupervisionNum() + "&juvenileNum=" 
	     *		+ result.getJuvenileNum() + "&submitAction=Link" );
	     */

	    /* in case you need to get around the display of Briefing and go 
	     * straight to Casefile, uncomment the next line and comment out 
	     * the above "forward" assignment
	     */
	    if (searchType.equals(PDJuvenileCaseConstants.SEARCH_SUPERVISION_NUMBER))
	    {
		forward = new ActionForward("displayJuvenileCasefileDetails.do?supervisionNum=" + result.getSupervisionNum());
	    }
	    else
	    {
		forward = new ActionForward(oldForward.getPath() + "?supervisionNum=" + result.getSupervisionNum() + "&juvenileNum=" + result.getJuvenileNum() + "&submitAction=Link");
	    }

	    forward.setRedirect(true);
	    break;

	default:
	    // default to many results: set count and forward to 'listSuccess'
	    Collections.sort(casefiles);
	    if (form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		form.setCasefileSearchResults(setRestrictedAccesMarkers(casefiles));
	    else
		form.setCasefileSearchResults(casefiles);
	    forward = aMapping.findForward(UIConstants.LISTSUCCESS);
	    break;
	}
	return forward;
    }

    private Collection setRestrictedAccesMarkers(Collection casefiles)
    {

	Iterator iter = casefiles.iterator();
	while (iter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) iter.next();
	    if (UIJuvenileHelper.checkRestrictedAcces(resp.getJuvenileNum()))
		resp.setRestrictedAccess("Y");
	    else
		resp.setRestrictedAccess("N");

	}
	return casefiles;
    }
}

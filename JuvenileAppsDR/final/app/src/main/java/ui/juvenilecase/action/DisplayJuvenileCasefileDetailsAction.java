//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileDetailsAction.java

package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.family.GetFamilyConstellationDetailsEvent;
import messaging.juvenile.GetJuvenilePactSubjectDetailsEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.GetSubstanceAbuseInfoEvent;
import messaging.juvenile.reply.JJSOffenseResponseEvent;
import messaging.juvenile.reply.JuvenileJISResponseEvent;
import messaging.juvenile.reply.JuvenilePactDetailResponseEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import messaging.juvenilecase.GetCasefileAssignmentHistoryEvent;
import messaging.juvenilecase.GetJuvenileCasefileRiskNeedsLevelEvent;
import messaging.juvenilecase.GetJuvenileTraitsByParentTypeEvent;
import messaging.juvenilecase.reply.FamilyConstellationListResponseEvent;
import messaging.juvenilecase.reply.FamilyConstellationMemberListResponseEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryViewResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileSearchResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.PactRiskLevelResponseEvent;
import messaging.officer.ValidateOfficerProfileEvent;
import messaging.referral.GetBehavioralHistoryEvent;
import messaging.referral.GetJuvenileCasefileOffensesEvent;
import messaging.referral.reply.JuvenileBehaviorHistoryResponseEvent;
import mojo.km.config.AppProperties;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.OfficerProfileControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDJuvenileCaseConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.ujac.util.BeanComparator;
import org.ujac.util.CollectionUtils;

import pd.supervision.programreferral.JuvenileProgramReferralAssignmentHistory;

import ui.common.Address;
import ui.common.CodeHelper;
import ui.common.UIUtil;
import ui.contact.user.helper.UIUserFormHelper;
import ui.juvenilecase.CasefileSearchResultsReportBean;
import ui.juvenilecase.SecurityToken;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.casefile.JuvenileReferralHelper;
import ui.juvenilecase.casefile.form.GuardianBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.CasefileSearchForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.security.SecurityUIHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import pd.juvenilecase.JuvenileCaseHelper;

/**
 * @author dgibler
 */
public class DisplayJuvenileCasefileDetailsAction extends Action
{
    /**
     * @roseuid 4278CA1A0374
     */
    public DisplayJuvenileCasefileDetailsAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4278C7B802FF
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	if (form == null) {
	    form = UIJuvenileCaseworkHelper.getHeaderForm(aRequest);
	}


	String casefileId = form.getSupervisionNum();
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("print"))
	{

	    return printCasefileSearchResults(aRequest, aResponse, form); // added for bug #37247
	}
	if (form.getAction() != null && form.getAction().equalsIgnoreCase("printYouthAddress"))
	{

	    return printCasefileSearchResultsWithYouthAddress(aRequest, aResponse, form); // added for US 157343
	}

	if (StringUtils.isEmpty(casefileId))
	{
	    casefileId = UIJuvenileCaseworkHelper.getCasefileNumber(aRequest, false, true);
	}

	form.populateJuvenileCasefileForm(casefileId);
	
	JuvenileBriefingDetailsForm juvenileBriefingForm = new JuvenileBriefingDetailsForm();;
	setProfileDetail(form.getJuvenileNum(), juvenileBriefingForm);
	

	/*if ( juvenileBriefingForm != null
		&& juvenileBriefingForm.getProfileDetail().getHispanic() != null
		&& juvenileBriefingForm.getProfileDetail().getHispanic().length() > 0
		&& juvenileBriefingForm.getProfileDetail().getIsUSCitizen() != null
		&& juvenileBriefingForm.getProfileDetail().getIsUSCitizen().length() > 0 
		) {
	    form.setHispanicIndicatorNeedsUpdate( false );
	} else {
	    form.setHispanicIndicatorNeedsUpdate( true );
	}*/
	
	
	setDualStatus(form.getJuvenileNum(), juvenileBriefingForm);
	HttpSession session = aRequest.getSession();
	session.setAttribute("juvenileBriefingDetailsForm", juvenileBriefingForm);
	
	if (form.getExpectedSupervisionEndDate() != null)
	{
	    form.setSupervisionEndDateStr(DateUtil.dateToString(form.getExpectedSupervisionEndDate(), UIConstants.DATE_FMT_1));
	}
	else
	{
	    form.setSupervisionEndDateStr(UIConstants.EMPTY_STRING);
	}
	if (form.getClosingDate() != null)
	{
	    form.setClosingDateStr(DateUtil.dateToString(form.getClosingDate(), UIConstants.DATE_FMT_1));
	}
	else
	{
	    form.setClosingDateStr(UIConstants.EMPTY_STRING);
	}

	if (form.getSupervisionNum() == null || form.getSupervisionNum().equals(""))
	{
	    ActionMessage myError = new ActionMessage("error.juvenileUnavailable", form.getJuvenileNum());
	    ArrayList coll = new ArrayList();
	    coll.add(myError);
	    sendToErrorPage(aRequest, coll);
	    return aMapping.findForward(UIConstants.CANCEL);
	}

	List activityResults = UIJuvenileCaseworkHelper.fetchCleActivities(form.getSupervisionNum());
	Iterator<ActivityResponseEvent> iterator = activityResults.iterator();
	if (iterator.hasNext())
	{
	    ActivityResponseEvent activityResponseEvent = iterator.next();
	    aRequest.setAttribute("exceptionOverrideComments", activityResponseEvent.getComments());
	}

	Collection assignments = new ArrayList();
	GetCasefileAssignmentHistoryEvent jpoAssignmentHistoriesEvent = (GetCasefileAssignmentHistoryEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETCASEFILEASSIGNMENTHISTORY);
	jpoAssignmentHistoriesEvent.setCasefileId(form.getSupervisionNum());
	//jpoAssignmentHistoriesEvent.setJuvNum(form.getJuvenileNum());

	CompositeResponse response = UIJuvenileHelper.getCompositeResponse(jpoAssignmentHistoriesEvent);
	assignments = (Collection) UIJuvenileHelper.fetchCollection(response, PDJuvenileCaseConstants.JUVENILE_CASEFILE_TOPIC);

	boolean historyExists = false;
	Iterator<JPOAssignmentHistoryViewResponseEvent> iter = assignments.iterator();
	while (iter.hasNext())
	{    
	    JPOAssignmentHistoryViewResponseEvent respEvent = iter.next();
	    if (respEvent.getJpoAssignmentDate() != null)
	    {
		historyExists = true;
		break;
	    }
	}
	Iterator<JPOAssignmentHistoryViewResponseEvent> iter2 = assignments.iterator();
	while (iter2.hasNext())
	{
	    JPOAssignmentHistoryViewResponseEvent respEvent = iter2.next();	
	    if(!form.getJuvenileNum().isEmpty()&&form.getJuvenileNum()!=null&&respEvent.getReferralNumber()!=null&&!respEvent.getReferralNumber().isEmpty())
	    {
		//respEvent.setMAYSINeededforReferrral(JuvenileCaseHelper.maysiNeededforReferral(form.getJuvenileNum(), respEvent.getReferralNumber()));
		if(JuvenileCaseHelper.maysiNeededforReferral(form.getJuvenileNum(), respEvent.getReferralNumber()))
		    respEvent.setIsMAYSINeededforReferrral("1");
		else 
		    respEvent.setIsMAYSINeededforReferrral("0");
	    }
	}
	List theList = new ArrayList(assignments);
	ArrayList sortFields = new ArrayList();
	if (historyExists)
	{
	    sortFields.add(new ReverseComparator(new BeanComparator("jpoAssignmentDate")));
	}
	sortFields.add(new ReverseComparator(new BeanComparator("assignmentAddDate")));
	sortFields.add(new ReverseComparator(new BeanComparator("referralNumber")));
	ComparatorChain multiSort = new ComparatorChain(sortFields);
	Collections.sort(theList, multiSort);

	form.setJpoAssignmentHistories(theList);
	form.setHasReferrals(checkForReferral(form.getJuvenileNum()));

	form.setLoggedInUserACLM(checkIfUserIsCLMType(aRequest));

	List activeReferrals = UIProgramReferralHelper.getActiveCasefileProgramReferrals(casefileId);
	form.setActiveProgramReferralsCnt(activeReferrals.size());

	// get VOP Needed
	//form.setIsVOPEntryNeeded(vopEntryNeeded(form.getJuvenileNum(), theList));

	//added for US 31029 - need RESTRICTED displayed next to name of Juvenile
	form.setRestrictedAccess(UIJuvenileHelper.checkRestrictedAcces(form.getJuvenileNum()));

	//pact value # task 44099 . U.S #41378
	String pactServerName = AppProperties.getInstance().getProperty("PactServerName");
	form.setPactServerName(pactServerName);

	//bug #46110
	String pactApplicationName = AppProperties.getInstance().getProperty("PactApplicationName");
	form.setPactApplicationName(pactApplicationName);

	    // get pact subject details.#43956
	    GetJuvenilePactSubjectDetailsEvent requestEvent = (GetJuvenilePactSubjectDetailsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPACTSUBJECTDETAILS);
	    requestEvent.setJuvenileId(form.getJuvenileNum());
	    List<JuvenilePactDetailResponseEvent> pactRespList = MessageUtil.postRequestListFilter(requestEvent, JuvenilePactDetailResponseEvent.class);

	    // No need to check for nulls or empty --- we are always sending a new response
	    Collections.sort((List<JuvenilePactDetailResponseEvent>) pactRespList, Collections.reverseOrder()); // get the most recent risk values
	    Iterator<JuvenilePactDetailResponseEvent> juvenilePactSubjectRespItr = pactRespList.iterator();
	    while (juvenilePactSubjectRespItr.hasNext())
	    {
		JuvenilePactDetailResponseEvent respEvent = juvenilePactSubjectRespItr.next();
		if (respEvent != null && respEvent.getRiskLevel() != null)
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
	    
	boolean result = UIJuvenileCaseworkHelper.isSupTypeInCat(form.getSupervisionTypeId(), UIConstants.CASEFILE_SUPERVISION_CAT_PRE_ADJ);

	form.setIsNoblePactNeeded("No");
	if (result)
	{
	    //Check to see if has user-entered pact for the casefile
	    GetJuvenileCasefileRiskNeedsLevelEvent reqEvent = (GetJuvenileCasefileRiskNeedsLevelEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILERISKNEEDSLEVEL);
	    reqEvent.setCaseFileId(casefileId);

	    List<PactRiskLevelResponseEvent> pactRNList = MessageUtil.postRequestListFilter(reqEvent, PactRiskLevelResponseEvent.class);
	    if (pactRNList.size() == 0)
	    {
		form.setIsNoblePactNeeded("Yes");
	    }
	}// Result check

	//pact attributes task 41028.
	String userId = SecurityUIHelper.getJIMSLogonId();
	form.setUserId(userId);
	//pact attributes task 41028.//29 mins is the session timeout in jims2
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
	//pact attributes task 41028.

	/*GetSubstanceAbuseInfoEvent getSubstanceAbuseInfoEvent = (GetSubstanceAbuseInfoEvent)
		  EventFactory.getInstance(JuvenileControllerServiceNames.GETSUBSTANCEABUSEINFO);
	  getSubstanceAbuseInfoEvent.setCasefileId( casefileId );
	  CompositeResponse substanceAbuseInfoResp = MessageUtil.postRequest(getSubstanceAbuseInfoEvent);
	  Collection<SubstanceAbuseResponseEvent>substanceAbuseInfos = MessageUtil.compositeToCollection(substanceAbuseInfoResp, SubstanceAbuseResponseEvent.class);
	  if (substanceAbuseInfos != null
		  && substanceAbuseInfos.size() > 0 ){
	      form.setSubstanceAbuseTjjdRequired(false);
	  } else {
	      form.setSubstanceAbuseTjjdRequired(true);
	  }*/
	  
	  List<JuvenileSchoolHistoryResponseEvent> schoolHistoryList = new ArrayList<>();
	  schoolHistoryList.addAll(UIJuvenileHelper.fetchSchoolHistory(form.getJuvenileNum()));
  
	  if ( schoolHistoryList.size() == 0  ){
	      form.setSchoolHistoryNeedsUpdate( true );
	  } else if ( schoolHistoryList.size() > 0   ){
	      Collections.sort(schoolHistoryList, new Comparator<JuvenileSchoolHistoryResponseEvent>(){
		    @Override
		    public int compare(JuvenileSchoolHistoryResponseEvent h1, JuvenileSchoolHistoryResponseEvent h2 ){
			return h2.getSchoolHistoryId().compareTo(h1.getSchoolHistoryId());
		    }
		});
	     
	      int month = schoolHistoryList.get( 0 ).getCreateDate().getMonth() + 1;
	      int year = schoolHistoryList.get( 0 ).getCreateDate().getYear() + 1900;
	   
	      int currentMonth = DateUtil.getCurrentDate().getMonth() + 1;
	      int currentYear = DateUtil.getCurrentDate().getYear() + 1900;
	      
	      if ( 8 == currentMonth
		      || 9 == currentMonth ){
		  if ( year == currentYear
			  && month >=8 ){
		      form.setSchoolHistoryNeedsUpdate( false );
		  } else {
		      form.setSchoolHistoryNeedsUpdate( true );
		  }
		  
	      } else {
		  form.setSchoolHistoryNeedsUpdate( false );
	      }
	   
	  } 
	  
	return (aMapping.findForward(UIConstants.SUCCESS));
    }

    /**
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
	    //U.S 88526
	    if (juvProfileRE.getHispanic() != null)
	    {
		if (juvProfileRE.getHispanic().equalsIgnoreCase("Y"))
		{
		    form.setHispanic(UIConstants.YES_FULL_TEXT);
		}
		else
		{
		    form.setHispanic(UIConstants.NO_FULL_TEXT);
		}
	    }
	    else
	    {
		form.setHispanic(UIConstants.EMPTY_STRING);
	    }
	    
	    if (juvProfileRE.getIsUSCitizen() != null)
	    {
		if (juvProfileRE.getIsUSCitizen().equalsIgnoreCase("Y"))
		{
		    form.setIsUsCitizen(UIConstants.YES_FULL_TEXT);
		}
		else
		{
		    form.setIsUsCitizen(UIConstants.NO_FULL_TEXT);
		}
	    }
	    else
	    {
		form.setIsUsCitizen(UIConstants.EMPTY_STRING);
	    }
	}

    }

    private void setDualStatus(String juvenileNum, JuvenileBriefingDetailsForm form)
    {

	// Filter for former dual trait status
	GetJuvenileTraitsByParentTypeEvent traitByParentEvent = (GetJuvenileTraitsByParentTypeEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILETRAITSBYPARENTTYPE);
	traitByParentEvent.setJuvenileNum(juvenileNum);
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

    private ActionForward printCasefileSearchResults(HttpServletRequest aRequest, HttpServletResponse aResponse, JuvenileCasefileForm form)
    {
	HttpSession aSession = aRequest.getSession();
	CasefileSearchForm searchForm = (CasefileSearchForm) aSession.getAttribute("casefileSearchForm");
	CasefileSearchResultsReportBean reportBean = new CasefileSearchResultsReportBean();
	//added for Hot fix - Bug #37952
	if (searchForm.getCasefileSearchResults() != null)
	    reportBean.setNumberOfCasefiles(searchForm.getCasefileSearchResults().size());	
	//go through the casefiles and set the date format for supervisionEndDate
	Iterator resultsIter = searchForm.getCasefileSearchResults().iterator();
	Collection tempResults = new ArrayList();
	while (resultsIter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) resultsIter.next();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    //added for Hot fix - Bug #37952
	    if (resp.getSupervisionEndDate() != null)
		resp.setSupervisionEndDateStr(sdf.format(resp.getSupervisionEndDate()));
	    tempResults.add(resp);
	}
	reportBean.setCasefileSearchResults(tempResults);
	//reportBean.setStatusSearchType(CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, searchForm.getCaseStatusTypeId2()));
	
	//Bug #171580
	if(searchForm.getCaseStatusTypeId2() != null)
	    reportBean.setStatusSearchType(searchForm.getCaseStatusTypeId2());
		
	aSession.setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CASEFILE_SEARCH_RESULTS_REPORT);
	form.setAction(""); // added for bug #37247
	return null;

    }

    private ActionForward printCasefileSearchResultsWithYouthAddress(HttpServletRequest aRequest, HttpServletResponse aResponse, JuvenileCasefileForm form)
    {
	HttpSession aSession = aRequest.getSession();
	CasefileSearchForm searchForm = (CasefileSearchForm) aSession.getAttribute("casefileSearchForm");
	CasefileSearchResultsReportBean reportBean = new CasefileSearchResultsReportBean();
	if (searchForm.getCasefileSearchResults() != null)
	    reportBean.setNumberOfCasefiles(searchForm.getCasefileSearchResults().size());
	//go through the casefiles and set the date format for supervisionEndDate
	Iterator resultsIter = searchForm.getCasefileSearchResults().iterator();
	Collection tempResults = new ArrayList();
	while (resultsIter.hasNext())
	{
	    JuvenileCasefileSearchResponseEvent resp = (JuvenileCasefileSearchResponseEvent) resultsIter.next();
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    if (resp.getSupervisionEndDate() != null)
		resp.setSupervisionEndDateStr(sdf.format(resp.getSupervisionEndDate()));
	    resp.setMemberAddress(setResidentialAddress(resp.getJuvenileNum()));
	    tempResults.add(resp);
	}
	reportBean.setCasefileSearchResults(tempResults);
	reportBean.setStatusSearchType(CodeHelper.getCodeDescription(PDCodeTableConstants.JUVENILE_CASEFILE_CASE_STATUS, searchForm.getCaseStatusTypeId2()));
	reportBean.setOfficerLastName(searchForm.getOfficerLastName());
	reportBean.setOfficerFirstName(searchForm.getOfficerFirstName());
	reportBean.setOfficerMiddleName(searchForm.getOfficerMiddleName());
	aSession.setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.CASEFILE_SEARCH_RESULTS_WITH_ADDRESS_REPORT);
	form.setAction("");
	return null;

    }

    /*
     * given a request, query to see if the logged in user is a CLM (Caseload
     * Manager) - return true if so. this is NOT the same as if this user is a
     * CLM and the CLM for the current casefile
     */
    private boolean checkIfUserIsCLMType(HttpServletRequest aRequest)
    {
	boolean isCLM = false;
	HttpSession httpSession = aRequest.getSession(false);

	if (httpSession != null)
	{
	    ValidateOfficerProfileEvent event = (ValidateOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.VALIDATEOFFICERPROFILE);

	    OfficerProfileResponseEvent officer = UIUserFormHelper.getUserOfficerProfile(SecurityUIHelper.getLogonId());

	    if (officer != null)
	    {
		event.setLogonId(officer.getUserId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Object obj = MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		if (obj != null)
		{
		    OfficerProfileResponseEvent resp = (OfficerProfileResponseEvent) obj;
		    if (StringUtils.equalsIgnoreCase(resp.getOfficerSubTypeId(), "CLM") || StringUtils.equalsIgnoreCase(resp.getOfficerSubType(), "CASELOAD MANAGER"))
		    {
			isCLM = true;
		    }
		}
	    }
	}

	return (isCLM);
    }

    /*
     * given a juvenile number, search JJS for Referral records and return true
     * if one or more Referrals exist
     */
    private boolean checkForReferral(String juvenileNum)
    {
	boolean hasReferralEvents = false;

	if (juvenileNum != null && (juvenileNum.length() > 0))
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
		String str = resp.getReferralEvents();
		if (str != null && !str.trim().equals("0"))
		{
		    hasReferralEvents = true;
		}
	    }
	}

	return (hasReferralEvents);
    }

    /**
     * @param aRequest
     */
    private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
    {
	ActionErrors errors = new ActionErrors();
	if (aActionErrors != null && aActionErrors.size() > 0)
	{
	    Iterator i = aActionErrors.iterator();
	    while (i.hasNext())
	    {
		ActionMessage error = (ActionMessage) i.next();
		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	    }
	    saveErrors(aRequest, errors);
	}
    }

    private Address setResidentialAddress(String juvenileNum)
    {
	Collection<FamilyConstellationListResponseEvent> constellationList = UIJuvenileHelper.getCurrentActiveConstellation(juvenileNum);

	Address youthAddress = null;
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

	    // create a list of guardian(s) for residential information - address
	    if (familyMembers.size() > 0)
	    {
		List<GuardianBean> guardians = new ArrayList<GuardianBean>();
		List<GuardianBean> relatives = new ArrayList<GuardianBean>();

		for (FamilyConstellationMemberListResponseEvent member : familyMembers)
		{
		    String rel = member.getRelationToJuvenileId();
		    if (notNullNotEmptyString(rel))
		    {
			GuardianBean pbean = new GuardianBean();
			pbean.setMemberNum(member.getMemberNum());
			pbean.setPrimaryContact(String.valueOf(member.isPrimaryContact()));
			pbean.setInHomeStatus(String.valueOf(member.isInHomeStatus()));
			// if guardian
			if (member.isGuardian())
			{
			    guardians.add(pbean);
			}
		    }
		}
		// check for guardian (in any) flagged as Primary contact and load
		// most recent address and phone data 
		if (guardians != null)
		{
		    Address famAddress0 = null;
		    boolean priContactFound = false;
		    if (guardians.size() == 1)
		    {
			GuardianBean gbean = guardians.get(0);
			if ("true".equalsIgnoreCase(gbean.getInHomeStatus()))
			{
			    famAddress0 = UIJuvenileHelper.getFamilyMemberAddress(gbean.getMemberNum());

			    return famAddress0;
			}
			gbean = null;
		    }
		    if (guardians.size() > 1)
		    {
			for (int x = 0; x < guardians.size(); x++)
			{
			    GuardianBean gbean = guardians.get(x);
			    if ("true".equalsIgnoreCase(gbean.getPrimaryContact()))
			    {
				priContactFound = true;
				famAddress0 = UIJuvenileHelper.getFamilyMemberAddress(gbean.getMemberNum()); // use member# to find address
				return famAddress0;
			    }
			}
			// if no primary contact found, use most current data based on create date 
			// and in-home status -- possible as older data may not have primary contact
			if (priContactFound == false)
			{
			    GuardianBean gbean0 = guardians.get(0);
			    famAddress0 = UIJuvenileHelper.getFamilyMemberAddress(gbean0.getMemberNum());
			    GuardianBean gbean1 = guardians.get(1);
			    Address familyAddress1 = UIJuvenileHelper.getFamilyMemberAddress(gbean1.getMemberNum());
			    if ("true".equalsIgnoreCase(gbean0.getInHomeStatus()))
			    {
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    // create date can be null if no address found
				    if (familyAddress1.getCreateDate() == null)
				    {
					youthAddress = famAddress0;
				    }
				    if (famAddress0.getCreateDate() == null)
				    {
					youthAddress = familyAddress1;
				    }
				    //when create dates not null for both address
				    if (famAddress0.getCreateDate() != null && familyAddress1.getCreateDate() != null)
				    {
					if (DateUtil.compare(famAddress0.getCreateDate(), familyAddress1.getCreateDate(), DateUtil.DATE_FMT_1) > 0)
					{
					    youthAddress = famAddress0;
					}
					else
					{
					    youthAddress = familyAddress1;
					}
				    }
				}
				else
				{
				    youthAddress = famAddress0;
				}
			    }
			    else
				if ("true".equalsIgnoreCase(gbean1.getInHomeStatus()))
				{
				    youthAddress = familyAddress1;
				}
			    gbean0 = null;
			    gbean1 = null;
			    familyAddress1 = null;
			} //end of if (priContactFound == false)

		    } // end of more than 1 guardian 

		} // end guardian check if (guardians != null)
	    } //end  if (familyMembers.size() > 0)
	}//end if (!constellationList.isEmpty())
	return youthAddress;
    }

    /**
     * returns true if string isn't null and contains one or more chars
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && str.trim().length() > 0);
    }

    /**
     * @param juvNum
     * @param refNum
     * @return
     */
   /* private String vopEntryNeeded(String juvNum, List assignments)
    {

	String retValue = "No";

	Iterator iter = assignments.iterator();

	while (iter.hasNext())
	{

	    JPOAssignmentHistoryViewResponseEvent assignment = (JPOAssignmentHistoryViewResponseEvent) iter.next();
	    List vopResponse = (List) JuvenileReferralHelper.getJCVOPRecordForJuvNumRefNum(juvNum, assignment.getReferralNumber());

	    if (vopResponse.size() == 0)
	    {

		// check offenses
		GetJuvenileCasefileOffensesEvent getOffenses = new GetJuvenileCasefileOffensesEvent();
		getOffenses.setJuvenileNum(juvNum);
		getOffenses.setReferralNum(assignment.getReferralNumber());

		CompositeResponse replyEvent = MessageUtil.postRequest(getOffenses);
		Collection<JJSOffenseResponseEvent> offenses = MessageUtil.compositeToCollection(replyEvent, JJSOffenseResponseEvent.class);
		Iterator offenseIter = offenses.iterator();
		while (offenseIter.hasNext())
		{

		    JJSOffenseResponseEvent offense = (JJSOffenseResponseEvent) offenseIter.next();

		    JuvenileOffenseCodeResponseEvent offenseCode = JuvenileReferralHelper.getOffenseCd(offense.getOffenseCodeId());
		    String numericCode = offenseCode.getNumericCode();
		    if (numericCode != null && numericCode.equalsIgnoreCase("23"))
		    {
			retValue = "Yes";
			break;
		    }
		}
	    }

	}

	return retValue;
    }*/
    
    
}

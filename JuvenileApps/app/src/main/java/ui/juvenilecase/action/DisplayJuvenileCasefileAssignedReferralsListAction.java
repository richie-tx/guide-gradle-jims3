//Source file: C:\\views\\dev\\app\\src\\ui\\juvenilecase\\action\\DisplayJuvenileCasefileAssignedReferralsDetailAction.java

package ui.juvenilecase.action;

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
import javax.servlet.http.HttpSession;

import messaging.casefile.GetJournalEntriesEvent;
import messaging.codetable.GetJuvenileDispositionCodeByValueEvent;
import messaging.codetable.criminal.reply.JuvenileDispositionCodeResponseEvent;
import messaging.juvenile.GetJuvenileProfileMainEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCurrentPactScoresEvent;
import messaging.juvenilecase.GetJuvenileDetentionFacilitiesEvent;
import messaging.juvenilecase.GetSupervisionTypeTJJDMapEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileReferralsResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenilePactScoresResponseEvent;
import messaging.juvenilecase.reply.JuvenileProfileReferralListResponseEvent;
import messaging.juvenilecase.reply.SupervisionTypeMapResponseEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileReferralControllerServiceNames;
import naming.PDJuvenileCaseConstants;
import naming.PDJuvenileConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;
import org.ujac.util.BeanComparator;

import pd.codetable.criminal.JuvenileActivityTypeCode;
import pd.juvenilecase.casefile.Activity;
import pd.juvenilecase.casefile.ActivityHelper;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.UIJuvenileTransferredOffenseReferralHelper;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.juvenilecase.form.JuvenileBehaviorHistoryForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.JuvenileMemberForm.MemberContact;
import ui.juvenilecase.form.TransferredOffenseForm;

/**
 * @author glyons
 */
public class DisplayJuvenileCasefileAssignedReferralsListAction extends LookupDispatchAction
{
    public final static String JUVENILE_BEHAVIOR_HISTORY_FORM = "juvenileBehaviorHistoryForm";

    /**
     * @roseuid 4278CA190105
     */
    public DisplayJuvenileCasefileAssignedReferralsListAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4278C9BB0044
     */
    public ActionForward referral(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	String juvenileNum = form.getJuvenileNum();

	UIJuvenileHelper.setJuvenileBehaviorHistoryForm(aRequest, juvenileNum);

	JuvenileBehaviorHistoryForm juvenileBehaviorHistoryForm = (JuvenileBehaviorHistoryForm) aRequest.getSession().getAttribute(JUVENILE_BEHAVIOR_HISTORY_FORM);

	String refEvents = "";
	if (juvenileBehaviorHistoryForm != null)
	{
	    refEvents = juvenileBehaviorHistoryForm.getReferralEvents().trim();
	}

	if (notNullNotEmptyString(refEvents) && !refEvents.equals("0"))
	{
	    //Bug #80122
	    //reverse sort the referrals
	    Collection tempArray = UIJuvenileCaseworkHelper.fetchJuvenileCasefileReferralsList(form.getSupervisionNum(), juvenileNum);
	    Collections.sort((List<JuvenileCasefileReferralsResponseEvent>) tempArray, Collections.reverseOrder(new Comparator<JuvenileCasefileReferralsResponseEvent>() {
		@Override
		public int compare(JuvenileCasefileReferralsResponseEvent evt1, JuvenileCasefileReferralsResponseEvent evt2)
		{
		    if (evt1.getRefSeqNum() != null && evt2.getRefSeqNum() != null)
		    {
			int seq1 = Integer.parseInt(evt1.getRefSeqNum()); //Bug #91992
			int seq2 = Integer.parseInt(evt2.getRefSeqNum());
			Integer seq1Int = new Integer(seq1);
			Integer seq2Int = new Integer(seq2);
			return seq1Int.compareTo(seq2Int);
		    }
		    else
			return -1;
		}
	    }));
	    form.setJuvenileCasefileReferralsList(tempArray);
	}
	else
	{
	    form.setJuvenileCasefileReferralsList(new ArrayList());
	}
	//add specailcategoryCode to form bug 105403
	if( form.getSupervisionTypeId() != null)
	{
	    
	    GetSupervisionTypeTJJDMapEvent request = new GetSupervisionTypeTJJDMapEvent();
	    request.setSupervisionTypeId(form.getSupervisionTypeId());
	    
	    CompositeResponse replyEvent = MessageUtil.postRequest(request);
	    SupervisionTypeMapResponseEvent tjjdMap = (SupervisionTypeMapResponseEvent) MessageUtil.filterComposite(replyEvent,SupervisionTypeMapResponseEvent.class);
	    
	    if( tjjdMap != null ){
		
		form.setSpecialCategoryCd( tjjdMap.getSplCategoryId() );
	    }
	    
	}
	//
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE, PDJuvenileCaseConstants.JUVENILE_CASEFILE);
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.JUVENILE_REFERRAL);

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * facility
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward facility(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	String casefileId = (String) aRequest.getParameter("casefileId");
	String juvenileNum = form.getJuvenileNum();

	GetAssignmentsByCasefileIdEvent getAssignments = new GetAssignmentsByCasefileIdEvent();
	getAssignments.setCasefileId(casefileId);
	CompositeResponse getAssignmentsResp = MessageUtil.postRequest(getAssignments);
	Collection<AssignmentResponseEvent> assignments = MessageUtil.compositeToCollection(getAssignmentsResp, AssignmentResponseEvent.class);

	List facilityHistoryList = new ArrayList();
	List<String> referrals   = new ArrayList<String>();

	if (notNullNotEmptyCollection(assignments))
	{
	    Collections.sort((List) assignments);
	}
	
	if (assignments != null
		&& assignments.size() > 0)
	{
	    for (AssignmentResponseEvent assignmentEvent : assignments)
	    {
		// We remove the duplicate referral numbers
		if ( !referrals.contains(assignmentEvent.getReferralNum()) ){
		    referrals.add( assignmentEvent.getReferralNum() );
		}
	    }
	    
	    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	    GetJuvenileDetentionFacilitiesEvent event = (GetJuvenileDetentionFacilitiesEvent) //converted to SQL
	    EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILEDETENTIONFACILITIES);
	    
	    for (String referralNum : referrals)
	    {
		GetJJSReferralEvent referralEvent = (GetJJSReferralEvent) EventFactory.getInstance(JuvenileReferralControllerServiceNames.GETJJSREFERRAL);
		referralEvent.setJuvenileNum(juvenileNum);
		referralEvent.setReferralNum(referralNum);
		List referralList = MessageUtil.postRequestListFilter(referralEvent, JuvenileProfileReferralListResponseEvent.class);
		if (referralList.size() > 0)
		{

		    event.setReferralNum(referralNum);
		    event.setJuvenileNum(juvenileNum);
		    dispatch.postEvent(event);

		    // Get PD Response Event
		    CompositeResponse response = (CompositeResponse) dispatch.getReply();
		    // Perform Error handling
		    MessageUtil.processReturnException(response);
		    Map dataMap = MessageUtil.groupByTopic(response);
		    if (dataMap != null)
		    {
			Collection facilityHistory = (Collection) dataMap.get(PDJuvenileConstants.JUVENILE_PROFILE_FACILITY_HISTORY_TOPIC);
			if (facilityHistory != null)
			{

			    List theList = new ArrayList(facilityHistory);
			    for (int f = 0; f < theList.size(); f++)
			    {
				JuvenileDetentionFacilitiesResponseEvent jdfEvent = (JuvenileDetentionFacilitiesResponseEvent) theList.get(f);
				facilityHistoryList.add(jdfEvent);
			    }
			}
		    }

		    //end loop
		}
	    } // foreach
	}
	if (facilityHistoryList != null && facilityHistoryList.size() > 1)
	{
	    ArrayList sortFields = new ArrayList();
	    sortFields.add(new ReverseComparator(new BeanComparator("admitDate")));
	    sortFields.add(new ReverseComparator(new BeanComparator("admitTime")));
	    ComparatorChain multiSort = new ComparatorChain(sortFields);
	    Collections.sort(facilityHistoryList, multiSort);
	}
	form.setJuvenileFacilityList(facilityHistoryList);

	//added for us #14780
	form.setResidentialCasefile(false);// reset it back;
	GetJournalEntriesEvent entries = new GetJournalEntriesEvent();
	entries.setCasefileId(casefileId);
	Iterator<Activity> actIter = Activity.findAll(entries);
	while (actIter.hasNext())
	{
	    Activity act = actIter.next();
	    if (act != null)
	    {
		JuvenileActivityTypeCode activityTypeCode = ActivityHelper.getActivityTypeCode(act.getActivityCodeId());
		if (act != null && activityTypeCode != null && activityTypeCode.getCategoryId() != null && activityTypeCode.getCategoryId().equals("RES") && activityTypeCode.getTypeId().equalsIgnoreCase("CSM"))
		{
		    form.setResidentialCasefile(true);
		    break;
		}
	    }
	}
	//added for us #14780
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY_PAGE, PDJuvenileCaseConstants.JUVENILE_CASEFILE);
	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.JUVENILE_FACILITY);

	return aMapping.findForward(UIConstants.FACILITY);
    }

    /**
     * transferred
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward transferred(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm jform = (JuvenileCasefileForm) aForm;

	String juvenileNum = jform.getJuvenileNum();
	HttpSession session = aRequest.getSession();
	TransferredOffenseForm tForm = (TransferredOffenseForm) session.getAttribute("transferredOffenseForm");
	if (tForm == null)
	{
	    tForm = new TransferredOffenseForm();
	    session.setAttribute("transferredOffenseForm", tForm);
	}
	tForm.setFromPage(UIConstants.CASEFILE);
	tForm.setJuvenileNumber(juvenileNum);
	tForm.setConfirmMsg(UIConstants.EMPTY_STRING);
	tForm.setExistingTransferredOffensesList(UIJuvenileTransferredOffenseReferralHelper.loadExistingTransferredOffenses(juvenileNum));

	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.JUVENILE_TRANFERRED);
	return aMapping.findForward(UIConstants.TRANSFER_SUCCESS);
    }

    /**
     * addMoreTransOffenses
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addMoreTransOffenses(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	String juvenileNum = form.getJuvenileNum();
	HttpSession session = aRequest.getSession();
	TransferredOffenseForm tForm = (TransferredOffenseForm) session.getAttribute("transferredOffenseForm");
	if (tForm == null)
	{
	    tForm = new TransferredOffenseForm();
	    session.setAttribute("transferredOffenseForm", tForm);
	}
	tForm.clear();
	if (tForm.getCountiesList() == null || tForm.getCountiesList().isEmpty())
	{
	    tForm.setCountiesList(UIJuvenileTransferredOffenseReferralHelper.getCounties());
	}

	tForm.setAvailableTransferredOffenseReferralList(UIJuvenileTransferredOffenseReferralHelper.loadAvailableTransferredOffenseReferrals(tForm.getExistingTransferredOffensesList(), juvenileNum));
	tForm.setFromPage(UIConstants.CASEFILE);

	aRequest.getSession().setAttribute(PDJuvenileCaseConstants.JUVENILE_HISTORY, PDJuvenileCaseConstants.JUVENILE_TRANFERRED);

	return aMapping.findForward(UIConstants.ADD_SUCCESS);
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward addRiskNeedLevels(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	form.setOnlyNosRecords(false);
	
	//check if any of the referrals is active
	Iterator<JuvenileCasefileReferralsResponseEvent> referralsIter = form.getJuvenileCasefileReferralsList().iterator();
	boolean hasActiveRef = false;
	while (referralsIter.hasNext())
	{
	    JuvenileCasefileReferralsResponseEvent resp = (JuvenileCasefileReferralsResponseEvent) referralsIter.next();
	    if (resp.getRefCloseDate() == null || resp.getRefCloseDate().equals(""))
	    {
		hasActiveRef = true;
		break;
	    }
	}
	
	
	// find all Subtype G codes
	ArrayList<String> codeValues = new ArrayList<String>();
	
	GetJuvenileDispositionCodeByValueEvent codeReq = (GetJuvenileDispositionCodeByValueEvent) 
							EventFactory.getInstance(CodeTableControllerServiceNames.GETJUVENILEDISPOSITIONCODEBYVALUE);
	codeReq.setAttributeName("subGroupInd");
	codeReq.setAttributeValue("G");
	List<JuvenileDispositionCodeResponseEvent> codes = MessageUtil.postRequestListFilter(codeReq, JuvenileDispositionCodeResponseEvent.class);
	
	Iterator codeIter = codes.iterator();
	while( codeIter.hasNext()) {
	    
	    JuvenileDispositionCodeResponseEvent code = (JuvenileDispositionCodeResponseEvent) codeIter.next();
	    codeValues.add(code.getCodeAlpha());
	}
	
	Iterator<JuvenileCasefileReferralsResponseEvent> iter = form.getJuvenileCasefileReferralsList().iterator();
	int count =0;
	while( iter.hasNext() ) {
	    
	    JuvenileCasefileReferralsResponseEvent sortedResp = (JuvenileCasefileReferralsResponseEvent) iter.next();
	    if ( codeValues.contains( sortedResp.getFinalDisposition() ))
	    {
		count ++;
		
	    }
	}
	if( count == form.getJuvenileCasefileReferralsList().size() ) {
	    
	   form.setOnlyNosRecords(true);
	}
  
	GetJuvenileCurrentPactScoresEvent request = (GetJuvenileCurrentPactScoresEvent) 
						EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECURRENTPACTSCORES);
	request.setSubjectEID(form.getJuvenileNum());
	request.setFormName("PACT");

	List replies = MessageUtil.postRequestListFilter(request, JuvenilePactScoresResponseEvent.class);
	
	if(replies.size() > 0){
	   	    
	    for(int x =0; x< replies.size();x++){
		
		JuvenilePactScoresResponseEvent obj = (JuvenilePactScoresResponseEvent) replies.get(x);
		if("Risk Level".equalsIgnoreCase( obj.getScoreType())){
		    
		    form.setUserRiskLvl(obj.getScoreClassification().substring(0,1));
		    form.setUserPACTDateHover(DateUtil.dateToString(obj.getDateCompleted(), DateUtil.DATE_FMT_1));
		    form.setUserPactId(obj.getFormInstanceId());
		    
		}else{
		    form.setUserNeedsLvl(obj.getScoreClassification().substring(0,1));
		    
		}

	    }
	}

	
	if (!hasActiveRef)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "All the referrals are closed. Atleast one referral needs to be active to add PACT data."));
	    saveErrors(aRequest, errors);
	    return aMapping.findForward(UIConstants.FAILURE);
	}
	form.setSelectedValue("");
	form.setSecondaryAction("");
	//form.setUserPACTDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
	return aMapping.findForward("addRiskNeedLevelsSuccess");
    }

    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm form = (JuvenileCasefileForm) aForm;
	String pda = aRequest.getParameter("pda");
	form.setUserPACTDate(pda);
	form.setRiskLvlDesc(SimpleCodeTableHelper.getDescrByCode("RISKNEEDSLVL", form.getUserRiskLvl()));
	form.setNeedLvlDesc(SimpleCodeTableHelper.getDescrByCode("RISKNEEDSLVL", form.getUserNeedsLvl()));
	return aMapping.findForward(UIConstants.NEXT_SUCCESS);
    }

    /**
     * Added for U.S #39094
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward displayFacilityAdmitRecord(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileCasefileForm casefileForm = (JuvenileCasefileForm) aForm;
	String detentionId = aRequest.getParameter("detentionId");

	Collection<JuvenileDetentionFacilitiesResponseEvent> detentionResponseList = JuvenileFacilityHelper.getJuvFacilityDetailsByDetentionID(detentionId);
	if (detentionResponseList != null)
	{
	    Iterator<JuvenileDetentionFacilitiesResponseEvent> detentionResponseListItr = detentionResponseList.iterator();
	    if (detentionResponseListItr.hasNext())
	    { //iterate detention recs.
		JuvenileDetentionFacilitiesResponseEvent detRec = detentionResponseListItr.next();
		if (detRec != null)
		{
		    JuvenileFacilityHelper.setBookingOffense(detRec); //Bug #58186
		    
		    //get some missing referral info such as referralOfficer, ReferralSource etc
		    detRec = JuvenileFacilityHelper.getReferralDetail(detRec, casefileForm.getJuvenileNum(), detRec.getReferralNumber());
			
		    casefileForm.setAdmissionInfo(detRec);
		}
	    }

	    casefileForm.setTraits(JuvenileFacilityHelper.getDetentionTraits(casefileForm.getJuvenileNum()));

	    JuvenileProfileDetailResponseEvent detailForm = UIJuvenileHelper.getJuvenileDetailForm(aRequest);
	    if (detailForm != null)
		casefileForm.setProfileDetail(detailForm);
	    else
	    {
		GetJuvenileProfileMainEvent requestEvent = (GetJuvenileProfileMainEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPROFILEMAIN);
		requestEvent.setJuvenileNum(casefileForm.getJuvenileNum());
		CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);
		JuvenileProfileDetailResponseEvent detail = (JuvenileProfileDetailResponseEvent) MessageUtil.filterComposite(replyEvent, JuvenileProfileDetailResponseEvent.class);
		casefileForm.setProfileDetail(detail);
	    }

	    JuvenileBriefingDetailsForm juvBriefing = UIJuvenileHelper.getJuvBriefingDetailsForm(aRequest);
	    if (juvBriefing != null)
	    {
		casefileForm.setMemberContact(juvBriefing.getMemberContact());
		casefileForm.setBookingOffenseCd(juvBriefing.getBookingOffenseCode());
		casefileForm.setBookingOffenseCdDesc(juvBriefing.getBookingOffenseCodeDesc());
	    }
	    else
	    {
		JuvenileBriefingDetailsForm facilityBriefing = (JuvenileBriefingDetailsForm) aRequest.getSession().getAttribute("FACILITY_BRIEFINGDETAILS_FORM");
		if (facilityBriefing != null)
		{
		    casefileForm.setMemberContact(facilityBriefing.getMemberContact());
		    casefileForm.setBookingOffenseCd(facilityBriefing.getBookingOffenseCode());
		    casefileForm.setBookingOffenseCdDesc(facilityBriefing.getBookingOffenseCodeDesc());
		}
	    }
	    if (casefileForm.getMemberContact() == null)
	    {
		MemberContact memberContact = UIJuvenileHelper.getPrimaryGuardianPhone(casefileForm.getJuvenileNum());
		if (memberContact != null)
		{
		    casefileForm.setMemberContact(memberContact);
		}
	    }
	}
	return aMapping.findForward(UIConstants.VIEW);
    }

    /*
     * given a String, return true if it's not null and not empty
     */
    private boolean notNullNotEmptyString(String str)
    {
	return (str != null && (str.length() > 0));
    }

    /*
     * given a Collection, return true if it's not null and not empty
     */
    private boolean notNullNotEmptyCollection(Collection col)
    {
	return (col != null && !col.isEmpty());
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
	Map keyMap = new HashMap();
	keyMap.put("button.referrals", "referral");
	keyMap.put("button.facility", "facility");
	keyMap.put("button.transfer", "transferred");
	keyMap.put("button.addMoreTransferredOffenses", "addMoreTransOffenses");
	keyMap.put("button.addRiskNeedLevels", "addRiskNeedLevels");
	keyMap.put("button.next", "next");
	keyMap.put("button.view", "displayFacilityAdmitRecord");
	return keyMap;
    }

}

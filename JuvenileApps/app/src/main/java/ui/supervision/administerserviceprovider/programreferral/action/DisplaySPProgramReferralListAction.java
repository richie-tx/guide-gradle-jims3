// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\supervision\\administerserviceprovider\\programreferral\\action\\DisplaySPProgramReferralListAction.java

package ui.supervision.administerserviceprovider.programreferral.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.administerlocation.GetJuvLocationUnitsEvent;
import messaging.administerlocation.reply.LocationResponseEvent;


import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.GuardianInfoResponseEvent;

import messaging.programreferral.GetProgramReferralDetailsReportEvent;
import messaging.programreferral.ProgramReferralRetrieverAttribute;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.LocationControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ProgramReferralConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileFamilyHelper;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.programreferral.ProgramReferralAttributeFactory;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralSearchForm;
import ui.security.SecurityUIHelper;
import ui.supervision.administerserviceprovider.UIServiceProviderHelper;

public class DisplaySPProgramReferralListAction extends JIMSBaseAction
{

    /**
     * @roseuid 463BA54902FA
     */
    public DisplaySPProgramReferralListAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49960111
     */
    public ActionForward displayReferralList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProgramReferralSearchForm form = (ProgramReferralSearchForm) aForm;

	String forward = null;
	form.clearAll();

	String serviceProviderId = SecurityUIHelper.getServiceProviderId();
	if (serviceProviderId == null)
	{
	    this.saveErrors(aRequest, "error.serviceProvider.invalidUser");
	    forward = UIConstants.FAILURE;
	}
	else
	{
	    //Added for US 32107 - check if the User has feature to view Restricted Access kids
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

	    CompositeResponse compositeResponse = UIProgramReferralHelper.getComprehensiveSPProgramReferrals(serviceProviderId, form);

	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);

	    if (error != null)
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
		saveErrors(aRequest, errors);
		ActionForward actionForward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
		return (actionForward);

	    }

	    //Set the response list
	    List programReferrals = (List) MessageUtil.compositeToCollection(compositeResponse, ProgramReferralResponseEvent.class);

	    List closeReferrals = new ArrayList();
	    if (!programReferrals.isEmpty() && programReferrals.size() > 0 && programReferrals != null)
	    {
		Iterator programReferralsIter = programReferrals.iterator();
		while (programReferralsIter.hasNext())
		{
		    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) programReferralsIter.next();

		    if ((resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED)) && (resp.getReferralSubStatusCd() != null && !resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.REJECTED)))
		    {

			closeReferrals.add(resp);
		    }
		    //					if ( (resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED) && resp.getReferralSubStatusCd() == null)
		    //						|| (resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED) && resp.getReferralSubStatusCd() != null && resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.ACCEPTED))
		    //						|| (resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED) && resp.getReferralSubStatusCd() != null && resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.CANCELLED))
		    //						|| (resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED) && resp.getReferralSubStatusCd() != null && resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.COMPLETED))						
		    //						|| (resp.getReferralStatusCd() != null && resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.CLOSED) && resp.getReferralSubStatusCd() != null && resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.WITHDRAWN))
		    //						) {
		    //
		    //						closeReferrals.add(resp);
		    //					}
		}
	    }

	    //update referral list with contact name and phone - US 170574
	    ArrayList<ProgramReferralResponseEvent> updatedClosedReferrals = this.addReferralContactInfo(closeReferrals);
	    Collections.sort(updatedClosedReferrals);
	    if (form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		form.setClosedReferralList((List) setRestrictedAccesMarkers(updatedClosedReferrals));
	    else
		form.setClosedReferralList(updatedClosedReferrals);

	    List rejectedReferrals = new ArrayList();
	    if (!programReferrals.isEmpty() && programReferrals.size() > 0 && programReferrals != null)
	    {
		Iterator programReferralsIter = programReferrals.iterator();
		while (programReferralsIter.hasNext())
		{
		    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) programReferralsIter.next();
		    if (resp.getReferralSubStatusCd() != null && resp.getReferralSubStatusCd().equalsIgnoreCase(ProgramReferralConstants.REJECTED))
		    {
			rejectedReferrals.add(resp);
		    }
		}
	    }

	    //update referral list with contact name and phone - US 170574
	    ArrayList<ProgramReferralResponseEvent> updatedRejectedReferrals = this.addReferralContactInfo(rejectedReferrals);
	    Collections.sort(updatedRejectedReferrals);
	    if (form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		form.setRejectedReferralList((List) setRestrictedAccesMarkers(updatedRejectedReferrals));
	    else
		form.setRejectedReferralList(updatedRejectedReferrals);

	    List activeReferrals = new ArrayList();
	    if (!programReferrals.isEmpty() && programReferrals.size() > 0 && programReferrals != null)
	    {
		Iterator programReferralsIter = programReferrals.iterator();
		while (programReferralsIter.hasNext())
		{
		    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) programReferralsIter.next();
		    if (resp.getReferralStatusCd() != null && (resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.ACCEPTED) || resp.getReferralStatusCd().equalsIgnoreCase(ProgramReferralConstants.TENTATIVE))

		    )
		    {

			Boolean isActiveProgram = UIServiceProviderHelper.IsActiveProgram(serviceProviderId);

			if (isActiveProgram)
			{

			    activeReferrals.add(resp);

			}
		    }
		}
	    }

	    //update referral list with contact name and phone - US 170574
	    ArrayList<ProgramReferralResponseEvent> updatedActiveReferrals = this.addReferralContactInfo(activeReferrals);
	    Collections.sort(updatedActiveReferrals);

	    if (form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
		form.setActiveReferralList((List) setRestrictedAccesMarkers(updatedActiveReferrals));
	    else
		form.setActiveReferralList(updatedActiveReferrals);

	    forward = UIConstants.SUCCESS;
	}

	HttpSession session = aRequest.getSession();
	session.setAttribute("programReferralSearchForm", form);

	return aMapping.findForward(forward);
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward submitSearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProgramReferralSearchForm form = (ProgramReferralSearchForm) aForm;

	String forward = null;
	form.clearAll();

	String serviceProviderId = SecurityUIHelper.getServiceProviderId();
	if (serviceProviderId == null)
	{
	    this.saveErrors(aRequest, "error.serviceProvider.invalidUser");
	    forward = UIConstants.FAILURE;
	}
	else
	{
	    //Added for US 32107 - check if the User has feature to view Restricted Access kids
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

	    /*CompositeResponse compositeResponse = UIProgramReferralHelper.getComprehensiveSPProgramReferrals(serviceProviderId, form);

	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);

	    if (error != null)
	    {
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    saveErrors(aRequest, errors);
	    ActionForward actionForward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	    return (actionForward);

	    }*/
	}

	List attributeList = new ArrayList();
	List activeReferrals = new ArrayList();
	List closeReferrals = new ArrayList();
	List rejectedReferrals = new ArrayList();

	//Set Service Provider Attribute(s)
	ProgramReferralRetrieverAttribute serviceProviderAttr = ProgramReferralAttributeFactory.getServiceProviderAttribute();
	serviceProviderAttr.setAttributeValue(serviceProviderId);
	attributeList.add(serviceProviderAttr);

	//Set Service Provider Attribute(s)
	if (form.getProviderProgramName() != null && form.getProviderProgramName().length() > 0)
	{
	    ProgramReferralRetrieverAttribute programAttr = ProgramReferralAttributeFactory.getProgramAttribute();
	    programAttr.setAttributeValue(form.getProviderProgramName());
	    attributeList.add(programAttr);
	}

	//Set From Begin Date Attribute(s)
	if (form.getFromBeginDate() != null && form.getFromBeginDate().length() > 0)
	{
	    ProgramReferralRetrieverAttribute fromBeginDateAttr = ProgramReferralAttributeFactory.getBeginDateProviderAttribute();
	    fromBeginDateAttr.setAttributeValue(form.getFromBeginDate());
	    attributeList.add(fromBeginDateAttr);
	}

	//Set To End Date Attribute(s)
	if (form.getToBeginDate() != null && form.getToBeginDate().length() > 0)
	{
	    ProgramReferralRetrieverAttribute toEndDateAttr = ProgramReferralAttributeFactory.getEndDateProviderAttribute();
	    toEndDateAttr.setAttributeValue(form.getToBeginDate());
	    attributeList.add(toEndDateAttr);
	}

	//Set From End Closed Date Attribute(s)
	if (form.getFromCloseDate() != null && form.getFromCloseDate().length() > 0)
	{
	    ProgramReferralRetrieverAttribute fromCloseDateAttr = ProgramReferralAttributeFactory.getClosedBeginDateProviderAttribute();
	    fromCloseDateAttr.setAttributeValue(form.getFromCloseDate());
	    attributeList.add(fromCloseDateAttr);
	}

	//Set To End Closed Date Attribute(s)
	if (form.getToCloseDate() != null && form.getToCloseDate().length() > 0)
	{
	    ProgramReferralRetrieverAttribute toCloseDateAttr = ProgramReferralAttributeFactory.getClosedEndDateProviderAttribute();
	    toCloseDateAttr.setAttributeValue(form.getToCloseDate());
	    attributeList.add(toCloseDateAttr);
	}

	//Set Juvenile Attributes
	if (form.getJuvenileNum() != null && form.getJuvenileNum().length() > 0)
	{

	    ProgramReferralRetrieverAttribute juvenileAttr = ProgramReferralAttributeFactory.getJuvenileAttribute();
	    juvenileAttr.setAttributeValue(form.getJuvenileNum());
	    attributeList.add(juvenileAttr);

	}
	else
	{
	    //Code Checking for Juvenile Name
	    if ( PDCalendarConstants.JUVENILE_NAME_SEARCH.equalsIgnoreCase( form.getJpoJuvenileNameInd( )))
	    {

		if( StringUtils.isNotEmpty(form.getLastName())){
		    
		    ProgramReferralRetrieverAttribute juvLastNameAttr = ProgramReferralAttributeFactory.getJuvenileLastNameProviderAttribute();
		    juvLastNameAttr.setAttributeValue( form.getLastName() );
		    attributeList.add(juvLastNameAttr);
		}
		
		if( StringUtils.isNotEmpty( form.getFirstName() )){
		    
		    ProgramReferralRetrieverAttribute juvFirstNameAttr = ProgramReferralAttributeFactory.getJuvenileFirstNameProviderAttribute();
		    juvFirstNameAttr.setAttributeValue( form.getFirstName() );
		    attributeList.add(juvFirstNameAttr);
		}

		if( StringUtils.isNotEmpty(form.getMiddleName())){
    
		    ProgramReferralRetrieverAttribute juvMiddleNameAttr = ProgramReferralAttributeFactory.getJuvenileMiddleNameProviderAttribute();
		    juvMiddleNameAttr.setAttributeValue( form.getMiddleName() );
		    attributeList.add(juvMiddleNameAttr);
		}
	    }
	}
	//Include JPO name for search if...
	if (form.getJpoJuvenileNameInd() != null && form.getJpoJuvenileNameInd().equalsIgnoreCase(PDCalendarConstants.JPO_NAME_SEARCH))
	{

	    //Set Last name Attribute -If the code gets to this point, last name should never be null
	    ProgramReferralRetrieverAttribute officerLastNameAttr = ProgramReferralAttributeFactory.getOfficerLastNameProviderAttribute();
	    officerLastNameAttr.setAttributeValue(form.getLastName().toUpperCase());
	    attributeList.add(officerLastNameAttr);

	    //Set First name Attribute
	    if (form.getFirstName() != null && form.getFirstName().length() > 0)
	    {
		ProgramReferralRetrieverAttribute officerFirstNameAttr = ProgramReferralAttributeFactory.getOfficerFirstNameProviderAttribute();
		officerFirstNameAttr.setAttributeValue(form.getFirstName().toUpperCase());
		attributeList.add(officerFirstNameAttr);
	    }

	    //Set Middle name Attribute
	    if (form.getMiddleName() != null && form.getMiddleName().length() > 0)
	    {
		ProgramReferralRetrieverAttribute officerMiddleNameAttr = ProgramReferralAttributeFactory.getOfficerMiddleNameProviderAttribute();
		officerMiddleNameAttr.setAttributeValue(form.getMiddleName().toUpperCase());
		attributeList.add(officerMiddleNameAttr);
	    }
	}

	if (form.getReferralStatusDescription() != null && form.getReferralStatusDescription().length() > 0)
	{
	    //Specific Status or Sub Status combination has been chosen
	    ProgramReferralRetrieverAttribute statusAttribute = ProgramReferralAttributeFactory.getStateAttribute();
	    statusAttribute.setAttributeValue(form.getReferralStatusDescription());
	    attributeList.add(statusAttribute);

	}
	else
	{
	    // Default Return ALL THREE Major Types
	    ProgramReferralRetrieverAttribute statusAcceptedAttribute = ProgramReferralAttributeFactory.getStateAttribute();
	    statusAcceptedAttribute.setAttributeValue(ProgramReferralConstants.ACCEPTED);
	    attributeList.add(statusAcceptedAttribute);

	    ProgramReferralRetrieverAttribute statusTentativeAttribute = ProgramReferralAttributeFactory.getStateAttribute();
	    statusTentativeAttribute.setAttributeValue(ProgramReferralConstants.TENTATIVE);
	    attributeList.add(statusTentativeAttribute);

	    ProgramReferralRetrieverAttribute statusClosedAttribute = ProgramReferralAttributeFactory.getStateAttribute();
	    statusClosedAttribute.setAttributeValue(ProgramReferralConstants.CLOSED);
	    attributeList.add(statusClosedAttribute);

	}

	if (form.getSupervisionTypeId() != null && StringUtils.isNotEmpty(form.getSupervisionTypeId()))
	{

	    ProgramReferralRetrieverAttribute sprvisionTypeAttribute = ProgramReferralAttributeFactory.getJuvenileSupervisionTypeAttribute();
	    sprvisionTypeAttribute.setAttributeValue(form.getSupervisionTypeId());
	    attributeList.add(sprvisionTypeAttribute);

	}
	if (form.getLocationId() != null && StringUtils.isNotEmpty(form.getLocationId()))
	{

	    ProgramReferralRetrieverAttribute locationAttribute = ProgramReferralAttributeFactory.getJuvenileLocationProviderAttribute();
	    locationAttribute.setAttributeValue(form.getLocationId());
	    attributeList.add(locationAttribute);
	}

	GetProgramReferralDetailsReportEvent gpre = (GetProgramReferralDetailsReportEvent) 
							EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILSREPORT);

	gpre.setReferralAttributes(attributeList);
	gpre.setDetailsNeeded(true);

	List<ProgramReferralResponseEvent> responses = MessageUtil.postRequestListFilter(gpre, ProgramReferralResponseEvent.class);

	Collections.sort((List<ProgramReferralResponseEvent>) responses, Collections.reverseOrder(new Comparator<ProgramReferralResponseEvent>() {
	    @Override
	    public int compare(ProgramReferralResponseEvent evt1, ProgramReferralResponseEvent evt2)
	    {
		return Integer.valueOf(evt2.getProvProgramId()).compareTo(Integer.valueOf(evt1.getProvProgramId()));
	    }
	}));

	/*Map<String, String> unique = new HashMap<String, String>();
	Map<String, Integer> groupings = new HashMap<String, Integer>();*/

	Iterator<ProgramReferralResponseEvent> iter = responses.iterator();
	
	GetJuvLocationUnitsEvent ev = (GetJuvLocationUnitsEvent) EventFactory.getInstance(LocationControllerServiceNames.GETJUVLOCATIONUNITS);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(ev);

	CompositeResponse response1 = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(response1);
	MessageUtil.processReturnException(dataMap);
    
	while (iter.hasNext())
	{

	    ProgramReferralResponseEvent evt = iter.next();

	    if (ProgramReferralConstants.ACCEPTED.equalsIgnoreCase(evt.getReferralStatusCd()) || ProgramReferralConstants.TENTATIVE.equalsIgnoreCase(evt.getReferralStatusCd()))
	    {
                
		String locationId = evt.getJuvLocUnitId();

		if (locationId != null && !locationId.isEmpty())
		{

		    Iterator juvUnits = MessageUtil.compositeToCollection(response1, LocationResponseEvent.class).iterator();
		    while (juvUnits.hasNext())
		    {
			LocationResponseEvent resp = (LocationResponseEvent) juvUnits.next();
			if (resp.getJuvLocationUnitId().equals(locationId))
			{
			    evt.setOfficerLocationUnit(locationId);
			    evt.setOfficerLocationUnitName(resp.getLocationUnitName());
			    break;
			}

		    }

		}
		activeReferrals.add(evt);
	    }
	    else
		if (ProgramReferralConstants.CLOSED.equalsIgnoreCase(evt.getReferralStatusCd()))
		{

		    closeReferrals.add(evt);
		}
		else
		    if (ProgramReferralConstants.REJECTED.equalsIgnoreCase(evt.getReferralStatusCd()))
		    {
			rejectedReferrals.add(evt);
		    }
	    // unique.put(evt.getProvProgramId(), evt.getProviderProgramName());
	}

	/*int cntr = 0;
	for (String key : unique.keySet())
	{
	    //System.out.println("Key = " + key);
	    for (ProgramReferralResponseEvent s : responses)
	    {
		if (key.equals(s.getProvProgramId()))
		{
		    // increment counter
		    cntr++;
		}

	    }
	    groupings.put(unique.get(key), cntr);
	    cntr = 0;
	    // add list of objects to form
	}

	//System.out.println(groupings);

	form.setGroupMap(groupings);*/
	form.setActiveReferralList(activeReferrals);
	form.setActiveReferralListSize(activeReferrals.size());
	form.setClosedReferralList(closeReferrals);
	form.setRejectedReferralList(rejectedReferrals);

	HttpSession session = aRequest.getSession();
	session.setAttribute("programReferralSearchForm", form);

	return aMapping.findForward(UIConstants.SUCCESS);
    }

    /**
     * @param referralList
     * @return
     */
    private ArrayList<ProgramReferralResponseEvent> addReferralContactInfo(List<ProgramReferralResponseEvent> referralList)
    {

	Iterator iter = referralList.iterator();
	ArrayList<ProgramReferralResponseEvent> updatedReferralList = new ArrayList<ProgramReferralResponseEvent>();
	ProgramReferralResponseEvent programReferral = null;

	while (iter.hasNext())
	{

	    programReferral = (ProgramReferralResponseEvent) iter.next();

	    String juvenileNum = programReferral.getJuvenileId();
	    
	    GuardianInfoResponseEvent guardian = this.getGuardian(juvenileNum);

	    if (guardian != null)
	    {

		String name = guardian.getGuardianName() != null ? guardian.getGuardianName() : "";
		programReferral.setContactName(name);

		String latestPhone = null;
		if (guardian.getMobilePhone() != null && !"".equals(guardian.getMobilePhone()))
		{
		    latestPhone = guardian.getMobilePhone();
		}
		else
		    if (guardian.getHomePhone() != null && !"".equals(guardian.getHomePhone()))
		    {
			latestPhone = guardian.getHomePhone();
		    }
		    else
			if (guardian.getPhone() != null && !"".equals(guardian.getPhone()))
			{
			    latestPhone = guardian.getPhone();
			}
			else
			    if (guardian.getJuvMobilePhone() != null && !"".equals(guardian.getJuvMobilePhone()))
			    {
				latestPhone = guardian.getJuvMobilePhone();
			    }
			    else
			    {
				latestPhone = "";
			    }

		programReferral.setContactPhone(latestPhone);

		updatedReferralList.add(programReferral);

	    }
	}

	return updatedReferralList;

    }

    /**
     * @param juvenileNum
     * @return
     */
    private GuardianInfoResponseEvent getGuardian(String juvenileNum)
    {

	List<GuardianInfoResponseEvent> guardians = UIJuvenileFamilyHelper.getGuardians(juvenileNum);
	Iterator<GuardianInfoResponseEvent> iter = guardians.iterator();

	String baseDateString = "1900-01-01 00:00:00";
	Date latestDate = DateUtil.stringToDate(baseDateString, "yyyy-MM-dd HH:mm:ss");
	GuardianInfoResponseEvent guardianRecord = null;

	while (iter.hasNext())
	{

	    GuardianInfoResponseEvent juvGuardian = iter.next();
	    String familyMemberNumber = juvGuardian.getFamMemberId();
	    boolean isGuardian = UIJuvenileHelper.isFamilyMemberAGuardian(juvenileNum, familyMemberNumber);

	    Date phoneEntryDate = juvGuardian.getEntryDate() != null ? juvGuardian.getEntryDate() : latestDate;

	    boolean isPrimaryContact = "true".equalsIgnoreCase(juvGuardian.getPrimaryContact()) ? true : false;

	    //we want the latest phone number listed
	    if (isGuardian && isPrimaryContact && phoneEntryDate.after(latestDate))
	    {
		latestDate = phoneEntryDate;
		guardianRecord = juvGuardian;
	    }

	}

	return guardianRecord;
    }

    private Collection setRestrictedAccesMarkers(Collection progRefs)
    {

	Iterator iter = progRefs.iterator();
	while (iter.hasNext())
	{
	    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) iter.next();
	    if (UIJuvenileHelper.checkRestrictedAcces(resp.getJuvenileId()))
		resp.setRestrictedAccess("Y");
	    else
		resp.setRestrictedAccess("N");

	}
	return progRefs;
    }

    /**
     * @param aRequest
     * @param errorkey
     */
    private void saveErrors(HttpServletRequest aRequest, String errorKey)
    {
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(errorKey, SecurityUIHelper.getLogonId()));
	saveErrors(aRequest, errors);
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProgramReferralSearchForm form = (ProgramReferralSearchForm) aForm;
	form.clear();

	ActionForward forward = aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	return forward;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "displayReferralList");
	keyMap.put("button.submit", "submitSearch");
	keyMap.put("button.refresh", "refresh");
    }
}

/*
 
 String lname = searchEvent.getSearchLastName() ;
			String mname = searchEvent.getSearchMiddleName() ;
			String fname = searchEvent.getSearchFirstName() ;

			// now we have to see "what type of name" we are searching for, one of:
			// JPO, CLM, or Juvenile name
			 //
			String nameSearchType = searchEvent.getNameSearchType() ;

			if( PDCalendarConstants.JPO_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileOfficerEvent juvOfficerEvent = (SearchJuvenileOfficerEvent)
						EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

				juvOfficerEvent.setJpo( true ) ;
				juvOfficerEvent.setFirstName( fname ) ;
				juvOfficerEvent.setLastName( lname ) ;
				juvOfficerEvent.setMiddleName( mname ) ;

				List results = super.postRequestListFilter( juvOfficerEvent, OfficerProfileResponseEvent.class ) ;
				form.setNameSearchResults( results ) ;

				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
					form.setListCount( results.size() ) ;
				}
			}
			else if( PDCalendarConstants.CLM_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileOfficerEvent juvOfficerEvent = (SearchJuvenileOfficerEvent)
						EventFactory.getInstance( OfficerProfileControllerServiceNames.SEARCHJUVENILEOFFICER ) ;

				juvOfficerEvent.setCaseloadManager( true ) ;
				juvOfficerEvent.setFirstName( fname ) ;
				juvOfficerEvent.setLastName( lname ) ;
				juvOfficerEvent.setMiddleName( mname ) ;

				List results = super.postRequestListFilter( juvOfficerEvent, OfficerProfileResponseEvent.class ) ;
				form.setNameSearchResults( results ) ;

				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
					form.setListCount( results.size() ) ;
				}
			}
			else if( PDCalendarConstants.JUVENILE_NAME_SEARCH.equals( nameSearchType ) )
			{
				SearchJuvenileProfilesEvent juvProfileEvent = (SearchJuvenileProfilesEvent)
						EventFactory.getInstance( JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES ) ;

				juvProfileEvent.setFirstName( fname ) ;
				juvProfileEvent.setLastName( lname ) ;
				juvProfileEvent.setMiddleName( mname ) ;
				// empty string: dont include alias records  
				juvProfileEvent.setSearchType( UIConstants.EMPTY_STRING ) ; 

				List results = super.postRequestListFilter( juvProfileEvent, JuvenileProfileDetailResponseEvent.class ) ;
				if( results.size() > 0 )
				{
					searchEvent.setShowSPSection( true ) ;
					searchEvent.setShowJPOSection( true ) ;
					searchEvent.setShowStatusSection( true ) ;
					searchEvent.setShowDateSection( true ) ;
					searchEvent.setShowButtonSection( true ) ;
				}
				form.setListCount( results.size() ) ;
				form.setNameSearchResults( results ) ;
			}
			searchEvent.setDoNameSearch( true ) ;
		}// else if PDCalendarConstants.CALENDAR_SEARCH
 
 */


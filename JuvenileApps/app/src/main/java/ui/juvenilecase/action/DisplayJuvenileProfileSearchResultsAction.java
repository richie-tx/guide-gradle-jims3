package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.GetJuvenilePhysicalAttributesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.context.ContextManager;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.security.ISecurityManager;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import ui.action.AbstractResultsTemplateAction;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.form.JuvenileProfileSearchForm;
import ui.juvenilecase.helper.JuvenileMainFormDirector;
import ui.juvenilecase.helper.JuvenilePhysicalCharacteristicsFormDirector;

/**
 * Class DisplayJuvenileProfileSearchResultsAction.
 */
public class DisplayJuvenileProfileSearchResultsAction extends AbstractResultsTemplateAction
{
    /**
     * @roseuid 42A898D701A5
     */
    public DisplayJuvenileProfileSearchResultsAction()
    {
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm,HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	HttpSession session = aRequest.getSession();
	session.setAttribute("richBean", new JuvenileProfileDetailResponseEvent());
	
	JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;

	if (form == null)
	    form = new JuvenileProfileSearchForm();
	String isFacility = aRequest.getParameter("isFacility");
	String isReferral = aRequest.getParameter("isReferral");
	String searchType = aRequest.getParameter("searchType");
	if (isFacility != null || isReferral != null)
	{
	    if (isFacility != null)
	    {
		form.setFacilityAction("facility");
	    }
	    if (isReferral != null)
	    {
		form.setReferralAction("referral");
	    }
	    else
		form.setReferralAction("");
	    form.setJuvenileNum(aRequest.getParameter("juvenileNum"));
	    if (searchType != null)
		form.setSearchType(searchType);
	}
	

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

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
	if ((form.getJuvenileDOB() == null || form.getJuvenileDOB().equals("")))
	{
	    if ((form.getSsn().getSSN() == null || form.getSsn().getSSN().isEmpty()))
	    {
		//task 171828
		if ((form.getDalogNum() == null || form.getDalogNum().equals("")))
		    {
        		if ((form.getLastName() == null || form.getLastName().equals("")) && (form.getJuvenileNum() == null || form.getJuvenileNum().equals("")))
        		{
        		    ActionMessage myError = new ActionMessage("error.common");
        		    ArrayList coll = new ArrayList();
        		    coll.add(myError);
        		    sendToErrorPage(aRequest, coll);
        		    if (form.getFacilityAction() != null && !form.getFacilityAction().equals(""))
        		    { // check for facility
        			return aMapping.findForward(UIConstants.FACILITY_SEARCH_FAILURE);
        		    }
        		    if (form.getReferralAction() != null && !form.getReferralAction().equals(""))
        		    { // check for referrals
        			form.setReferralAction("failure");
        			return aMapping.findForward(UIConstants.REFERRAL_SEARCH_FAILURE);
        		    }
        		    return aMapping.findForward(UIConstants.SEARCH_FAILURE);
        		}
		    }
	    }
	}
	IEvent searchEvent = form.createSearchJuvenileProfileEvent();

	dispatch.postEvent(searchEvent);
	CompositeResponse response = (CompositeResponse) dispatch.getReply();
	
	JuvenileProfileDetailResponseEvent detailResponse =(JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(response,JuvenileProfileDetailResponseEvent.class);

	// default forward 
	session.setAttribute("richBean", detailResponse);
	ActionForward forward = this.handleResponse(aMapping, aForm, aRequest, aResponse, JuvenileProfileDetailResponseEvent.class, response);

	/* Handle error thrown as ErrorResponseEvent from the command, 
	 * if there is any. Expected error: Number of juveniles matching 
	 * this criteria is greater than 2000.
	 */
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);
	if (error != null)
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	    saveErrors(aRequest, errors);
	    if (form.getFacilityAction() != null && !form.getFacilityAction().equals(""))
	    { // check for facility
		return aMapping.findForward(UIConstants.FACILITY_SEARCH_FAILURE);
	    }
	    if (form.getReferralAction() != null && !form.getReferralAction().equals(""))
	    { // check for facility
		form.setReferralAction("failure");
		return aMapping.findForward(UIConstants.REFERRAL_SEARCH_FAILURE);
	    }
	    forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	}

	return (forward);
    }

    /*
     * (non-Javadoc)
     * @see
     * mojo.pattern.AbstractResultsAction#handleZeroResults(org.apache.struts.
     * action.ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * mojo.km.messaging.Composite.CompositeResponse)
     */
    public ActionForward handleZeroResults(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse, CompositeResponse event)
    {
	JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.NO_RECORDS_ERROR));
	saveErrors(aRequest, errors);
	if (form.getFacilityAction() != null && !form.getFacilityAction().equals(""))
	{
	    return aMapping.findForward(UIConstants.FACILITY_SEARCH_FAILURE);
	}
	if (form.getReferralAction() != null && !form.getReferralAction().equals(""))
	{ // check for referral
	    form.setReferralAction("failure");
	    return aMapping.findForward(UIConstants.REFERRAL_SEARCH_FAILURE);
	}
	return (aMapping.findForward(UIConstants.SEARCH_FAILURE));
    }

    /*
     * (non-Javadoc)
     * @see
     * mojo.pattern.AbstractResultsAction#handleSingleResult(org.apache.struts
     * .action.ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * mojo.km.messaging.Composite.CompositeResponse, mojo.km.messaging.IEvent)
     */
    public ActionForward handleSingleResult(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse, CompositeResponse event, IEvent data)
    {
	JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;
	form.setSearchResultSize(1);
	JuvenileProfileDetailResponseEvent responseEvent = (JuvenileProfileDetailResponseEvent) data;

	//added for US 32107
	if (UIJuvenileHelper.checkRestrictedAcces(responseEvent.getJuvenileNum()) && form.getRestrictedAccessFeature().equalsIgnoreCase("N"))
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.noRecords"));
	    saveErrors(aRequest, errors);
	    ActionForward forward = aMapping.findForward(UIConstants.SEARCH_FAILURE);
	    return forward;
	}

	// populate the form with the response
	JuvenileMainFormDirector director = new JuvenileMainFormDirector(aRequest);
	director.setJuvenileProfileProperties(responseEvent);

	// populate the header form from the event and put in session
	UIJuvenileHelper.putHeaderForm(aRequest, responseEvent);

	handlePhysicalCharacteristics(aRequest, responseEvent.getJuvenileNum());

	ActionForward oldForward = aMapping.findForward(UIConstants.SUCCESS);

	ActionForward forward = new ActionForward(oldForward.getPath() + "?juvenileNum=" + responseEvent.getJuvenileNum() + "&fromProfile=" + "profileBriefingDetails" + "&submitAction=Link");
	forward.setRedirect(true);

	if (form.getFacilityAction() != null && !form.getFacilityAction().equals(""))
	{
	    forward = new ActionForward(oldForward.getPath() + "?juvenileNum=" + responseEvent.getJuvenileNum() + "&fromProfile=" + "profileBriefingDetails" + "&submitAction=facilityLink");
	    forward.setRedirect(true);
	}
	//referral briefing forward
	if (form.getReferralAction() != null && !form.getReferralAction().equals(""))
	{
	    if (form.getSearchType().equals("JNUM")||form.getSearchType().equals("JDALG") )// added ||form.getSearchType().equals("JDALG") for task 172859
	    {
		forward = new ActionForward(oldForward.getPath() + "?juvenileNum=" + responseEvent.getJuvenileNum() + "&fromProfile=" + "profileBriefingDetails" + "&submitAction=referralLink");
		forward.setRedirect(true);
	    }
	    else
	    {
		ArrayList<JuvenileProfileDetailResponseEvent> dataList = new ArrayList<JuvenileProfileDetailResponseEvent>();
		dataList.add(responseEvent);
		form.setJuvenileProfiles(dataList);
		form.setReferralAction("referralSearchResults");
		//UIJuvenileHelper.putHeaderForm(aRequest, responseEvent);
		return (aMapping.findForward(UIConstants.REFERRAL_LIST_SUCCESS));
	    }
	}
	return forward;
    }

    /*
     * (non-Javadoc)
     * @see
     * mojo.pattern.AbstractResultsAction#handleMultipleResults(org.apache.struts
     * .action.ActionMapping, org.apache.struts.action.ActionForm,
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse,
     * mojo.km.messaging.Composite.CompositeResponse, java.util.Collection)
     */
    public ActionForward handleMultipleResults(ActionMapping aMapping,  ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse, CompositeResponse event,Collection data)
    {
	JuvenileProfileSearchForm form = (JuvenileProfileSearchForm) aForm;
	form.setSearchResultSize(data.size());
	if (form.getRestrictedAccessFeature().equalsIgnoreCase("N")){
	    form.setJuvenileProfiles(setRestrictedAccesMarkers(data));
	}else{
	    form.setJuvenileProfiles(data);
	}
	if (form.getFacilityAction() != null && !form.getFacilityAction().equals(""))
	{
	    form.setFacilityAction("facilitySearchResults");
	    return (aMapping.findForward(UIConstants.FACILITY_LISTSUCCESSS));
	}

	if (form.getReferralAction() != null && !form.getReferralAction().equals(""))
	{
	    form.setReferralAction("referralSearchResults");
	    return (aMapping.findForward(UIConstants.REFERRAL_LIST_SUCCESS));
	}
	return (aMapping.findForward(UIConstants.LISTSUCCESS));
    }

    
    /*
     * (non-Javadoc)
     * @see
     * ui.action.IErrorResultsActionHandler#processBusinessExceptions(javax.servlet
     * .http.HttpServletRequest, mojo.km.messaging.Composite.CompositeResponse,
     * org.apache.struts.action.ActionErrors)
     */
    public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors)
    {
    }
    
    /**
     * handlePhysicalCharacteristics
     * @param aRequest
     * @param juvenileNum
     */
    private void handlePhysicalCharacteristics(HttpServletRequest aRequest,String juvenileNum)
    {
	// fetch responses
	GetJuvenilePhysicalAttributesEvent requestEvent = (GetJuvenilePhysicalAttributesEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPHYSICALATTRIBUTES);
	requestEvent.setJuvenileNum(juvenileNum);

	CompositeResponse replyEvent = MessageUtil.postRequest(requestEvent);

	JuvenilePhysicalCharacteristicsFormDirector formDirector = new JuvenilePhysicalCharacteristicsFormDirector(aRequest);
	formDirector.setJuvenileNum(juvenileNum);
	formDirector.setPhysicalCharacteristics(replyEvent);
    }

   /**
    * sendToErrorPage
    * @param aRequest
    * @param aActionErrors
    */
    private void sendToErrorPage(HttpServletRequest aRequest,Collection aActionErrors)
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
    
    /**
     * setRestrictedAccesMarkers
     * @param casefiles
     * @return
     */
    private Collection setRestrictedAccesMarkers(Collection casefiles)
    {

	Iterator iter = casefiles.iterator();
	while (iter.hasNext())
	{
	    JuvenileProfileDetailResponseEvent resp = (JuvenileProfileDetailResponseEvent) iter.next();
	    if (UIJuvenileHelper.checkRestrictedAcces(resp.getJuvenileNum()))
		resp.setRestrictedAccess("Y");
	    else
		resp.setRestrictedAccess("N");

	}
	return casefiles;
    }
}

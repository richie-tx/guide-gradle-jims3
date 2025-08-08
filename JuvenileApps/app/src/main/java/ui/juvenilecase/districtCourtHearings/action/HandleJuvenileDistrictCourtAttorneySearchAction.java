package ui.juvenilecase.districtCourtHearings.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;

/**
 * @author sthyagarajan
 */
public class HandleJuvenileDistrictCourtAttorneySearchAction extends JIMSBaseAction
{
    /*
     * (non-Javadoc)
     * 
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "addToList");
	keyMap.put("button.findAttorney", "findAttorney");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.select", "returnSelect");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
    }

    /**
     * @roseuid 467FB5C80014
     */
    public HandleJuvenileDistrictCourtAttorneySearchAction()
    {

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
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setPrevAction("findAttorney");
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	String attyName = form.getAttorneyName();
	attyName = attyName.replaceAll("'", "''");

	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	//request.setAttorneyName(form.getAttorneyName());
	//task 162556
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

	return aMapping.findForward("attorneySearchSuccess");
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
     * refresh
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setBarNumber(UIConstants.EMPTY_STRING);
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyNameHistory(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	form.setErrMessage(UIConstants.EMPTY_STRING);
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
	CourtHearingForm form = (CourtHearingForm) aForm;
	//added to refresh the docketlist once attorney is been selected from results--bug 142990
	form.setPrevAction("selectAttorney");
	String forwardStr = "";
	String pagerOffset =(String) aRequest.getSession().getAttribute("Pager_Offset"); //get pager offset from session.

	if (form.getAction().equalsIgnoreCase("ancillarySetting") || form.getAction().equalsIgnoreCase("ancillarySettingUpdate") || form.getAction().equalsIgnoreCase("ancillaryUpdateFromCourtActivity"))
	{
	    forwardStr = form.getAction();
	}
	if (form.getAction().equalsIgnoreCase("attorneySetting"))
	{
	    forwardStr = form.getAction();
	}
	if (form.getAction().equalsIgnoreCase("searchAttorneyCourtAction"))
	{
	    forwardStr = form.getAction();
	}
	if (form.getAction().equalsIgnoreCase("searchGALCourtAction"))
	{
	    forwardStr = form.getAction();
	}
	if (form.getAction().equalsIgnoreCase("searchAttorney2CourtAction"))
	{
	    forwardStr = form.getAction();
	}
	if (form.getAction().equalsIgnoreCase("GALattorneySetting"))
	{
	    forwardStr = form.getAction();
	}
	form.setValidateMsg("");

	//null check for form attorney data list needed.
	if (form.getAttorneyDataList() != null && !forwardStr.equalsIgnoreCase("GALattorneySetting"))
	{
	    List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
	    String selVal = form.getSelectedValue();
	    for (int x = 0; x < wrkList.size(); x++)
	    {
		AttorneyNameAndAddressResponseEvent jcoEvent = (AttorneyNameAndAddressResponseEvent) wrkList.get(x);
		if (selVal.equals(jcoEvent.getBarNum()))
		{
		    form.setBarNumber(jcoEvent.getBarNum());
		    form.setAttorneyName(jcoEvent.getAttyName());
		    form.setAttorneyNameHistory(jcoEvent.getAttyNameHistory());
		    break;
		}
	    }

	    form.setSelectedValue(UIConstants.EMPTY_STRING);
	    form.setErrMessage(UIConstants.EMPTY_STRING);
	    selVal = null;
	    wrkList = null;
	    form.setAttorneyDataList(null);
	}
	if (forwardStr.equalsIgnoreCase("ancillarySetting") || forwardStr.equalsIgnoreCase("ancillarySettingUpdate") || forwardStr.equalsIgnoreCase("ancillaryUpdateFromCourtActivity"))
	{
	    form.setAction(forwardStr);
	    ActionForward af =  aMapping.findForward("successAncillarySetting");
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	  //  return aMapping.findForward("successAncillarySetting");
	}
	if (forwardStr.equalsIgnoreCase("attorneySetting"))
	{
	    form.setAction(forwardStr);
	    ActionForward af =  aMapping.findForward("successAttorneySettingSearch");
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	if (forwardStr.equalsIgnoreCase("GALattorneySetting"))
	{
	    // check for gal
	    if (form.getAttorneyDataList() != null)
		{
		    List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
		    String selVal = form.getSelectedValue();
		    for (int x = 0; x < wrkList.size(); x++)
		    {
			AttorneyNameAndAddressResponseEvent jcoEvent = (AttorneyNameAndAddressResponseEvent) wrkList.get(x);
			if (selVal.equals(jcoEvent.getBarNum()))
			{
			    form.setGalBarNumber(jcoEvent.getBarNum());
			    form.setGalName(jcoEvent.getAttyName());			    
			    break;
			}
		    }

		    form.setSelectedValue(UIConstants.EMPTY_STRING);
		    form.setErrMessage(UIConstants.EMPTY_STRING);
		    selVal = null;
		    wrkList = null;
		    form.setAttorneyDataList(null);
		}
		    //
	    form.setAction(forwardStr);
	    ActionForward af =  aMapping.findForward("successGALAttorneySettingSearch");
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	if (forwardStr.equalsIgnoreCase("searchAttorneyCourtAction"))
	{
	    //form.setAction(forwardStr);
	    form.setAction("search");
	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
	    if (respEvt != null)
	    {
		respEvt.setAttorneyName(form.getAttorneyName());
		respEvt.setBarNum(form.getBarNumber());
		form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
	    }
	    //Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	    if (form.getDktSearchResultsMap().size() == 1)
	    {
		Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
		DocketEventResponseEvent onlyRespEvt = entry.getValue();
		if (onlyRespEvt != null)
		{
		    onlyRespEvt.setAttorneyName(form.getAttorneyName());
		    onlyRespEvt.setBarNum(form.getBarNumber());
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), onlyRespEvt);
		}
	    }

	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	if (forwardStr.equalsIgnoreCase("searchAttorney2CourtAction"))
	{
	    //form.setAction(forwardStr);
	    form.setAction("search");
	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
	    if (respEvt != null)
	    {
		respEvt.setAttorney2Name(form.getAttorneyName());
		respEvt.setAttorney2BarNum(form.getBarNumber());
		form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
	    }
	    //Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	    if (form.getDktSearchResultsMap().size() == 1)
	    {
		Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
		DocketEventResponseEvent onlyRespEvt = entry.getValue();
		if (onlyRespEvt != null)
		{
		    onlyRespEvt.setAttorney2Name(form.getAttorneyName());
		    onlyRespEvt.setAttorney2BarNum(form.getBarNumber());
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), onlyRespEvt);
		}
	    }

	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	
	if (forwardStr.equalsIgnoreCase("searchGALCourtAction"))
	{
	    //form.setAction(forwardStr);
	    form.setAction("search");
	    DocketEventResponseEvent respEvt = form.getDktSearchResultsMap().get(form.getDcktEvtIdKey());
	    if (respEvt != null)
	    {
		respEvt.setGalName(form.getAttorneyName());
		respEvt.setGalBarNum(form.getBarNumber());
		form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), respEvt);
	    }
	    //Bug #70017 - pagination issue - attorney name not getting set when single result - fixed
	    if (form.getDktSearchResultsMap().size() == 1)
	    {
		Map.Entry<String, DocketEventResponseEvent> entry = form.getDktSearchResultsMap().entrySet().iterator().next();
		DocketEventResponseEvent onlyRespEvt = entry.getValue();
		if (onlyRespEvt != null)
		{
		    onlyRespEvt.setGalName( form.getAttorneyName() );
		    onlyRespEvt.setGalBarNum(form.getBarNumber());
		    form.getDktSearchResultsMap().put(form.getDcktEvtIdKey(), onlyRespEvt);
		    form.setAdlitemUpdateFlag(true);
		}
	    }

	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	forwardStr = "successInitialSetting";
	return aMapping.findForward(forwardStr);
    }

    /**
     * back
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	String pagerOffset =(String) aRequest.getSession().getAttribute("Pager_Offset"); //get pager offset from session.
	
	form.setErrMessage(UIConstants.EMPTY_STRING);
	if (form.getAction().equalsIgnoreCase("ancillarySetting") || form.getAction().equalsIgnoreCase("ancillarySettingUpdate") || form.getAction().equalsIgnoreCase("ancillaryUpdateFromCourtActivity"))
	{
	    form.setCursorPosition("attorneyName");
	    return aMapping.findForward("backAncillarySetting");
	}
	if (form.getAction().equalsIgnoreCase("attorneySetting"))
	{
	    //Bug #69378
	    if (form.getAttorneyName() != null && !form.getAttorneyName().equals("") && form.getSelectedValue().equals(""))
	    {
		form.setErrMessage("Multiple attorneys found with this name, press Search Attorney to display list.");

	    }
	    return aMapping.findForward("backAttorneySettingSearch");
	}
	if (form.getAction().equalsIgnoreCase("GALattorneySetting"))
	{	    
	    return aMapping.findForward("backAttorneySettingSearch");
	}
	if (form.getAction().equalsIgnoreCase("courtActionUpdate"))
	{
	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	if (form.getAction().equalsIgnoreCase("searchAttorneyCourtAction")
		|| form.getAction().equalsIgnoreCase("searchGALCourtAction") ||form.getAction().equalsIgnoreCase("searchAttorney2CourtAction"))
	{
	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}	
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	String forwardStr = UIConstants.BACK;
	form.setCursorPosition("courtId");
	form.setAction("search");
	return aMapping.findForward(forwardStr);
    }

    /**
     * cancel
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String pagerOffset =(String) aRequest.getSession().getAttribute("Pager_Offset"); //get pager offset from session.
	if (form.getAction().equalsIgnoreCase("ancillarySetting") || form.getAction().equalsIgnoreCase("ancillarySettingUpdate") || form.getAction().equalsIgnoreCase("ancillaryUpdateFromCourtActivity"))
	{
	    form.setAttorneyName(UIConstants.EMPTY_STRING);
	    form.setCursorPosition("attorneyName");
	    return aMapping.findForward("backAncillarySetting");
	}
	if (form.getAction().equalsIgnoreCase("attorneySetting"))
	{
	    form.clearForm();
	    //form.setCursorPosition("barNumber");
	    return aMapping.findForward("cancelAttorneySettingSearch");
	}
	if (form.getAction().equalsIgnoreCase("GALattorneySetting"))
	{
	    form.clearForm();
	    return aMapping.findForward("cancelGALAttorneySettingSearch");
	}
	if (form.getAction().equalsIgnoreCase("courtActionUpdate"))
	{
	    //form.setCursorPosition("barNumber");
	    form.setAttorneyName(UIConstants.EMPTY_STRING);
	    form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	    return aMapping.findForward("cancelCourtActionDisplay");
	}
	
	if (form.getAction().equalsIgnoreCase("searchAttorneyCourtAction")
		|| form.getAction().equalsIgnoreCase("searchGALCourtAction")||form.getAction().equalsIgnoreCase("searchAttorney2CourtAction") )
	{
	    ActionForward af = aMapping.findForward(UIConstants.COURT_ACTION_DISPLAY);
	    String path = af.getPath();
	    path = path + "?pager.offset=" + pagerOffset;
	    return new ActionForward(path);
	}
	String forwardStr = UIConstants.CANCEL;
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	form.setCursorPosition("courtId");
	return aMapping.findForward(forwardStr);
    }
}

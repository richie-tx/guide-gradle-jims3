package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.codetable.criminal.reply.JuvenileOffenseCodeResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import messaging.productionsupport.UpdateJJSCLCourtEvent;
import messaging.productionsupport.UpdateProductionSupportDistrictCourtReferralEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileReferralEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.lang.StringUtils;

import pd.juvenilecase.referral.JJSReferral;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;


/**
 * @author apillai
 */

public class PerformUpdateDistrictCourtCalendarRecordAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.updateRecord", "submitCourtRecordUpdate");
	keyMap.put("button.validateOffense", "validateOffenseCode");
	keyMap.put("button.validateBarNumber", "validateBarNumber");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.findAttorney", "findAttorney");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.select", "returnSelect");
    }

    private Logger log = Logger.getLogger("PerformUpdateDistrictCourtCalendarRecordAction");

    public ActionForward submitCourtRecordUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String logonid = SecurityUIHelper.getLogonId();
	String actionFwd = "";

	DocketEventResponseEvent record = retrieveRecord(regform);

	if (record == null)
	{
	    regform.setMsg("PerformUpdateDistrictCourtCalendarRecordAction.java (73) - Could not retrieve record.");
	    return (mapping.findForward("error"));
	}
	//validate offense
	if (record.getAllegation() != null && !record.getAllegation().equals(""))
	{
	    //JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(record.getAllegation()); //commented for US 171712
	    //JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateWithAllOffenseCd(record.getAllegation()); //added for US 171712 No need to check against invalid or discontinued or DPSCode here
	    JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateWithAllOffenses(record.getAllegation()); //added for US 171712 No need to check against invalid or discontinued or DPSCode here
	    if (jocEvent != null)
	    {
	    }
	    else
	    {
		regform.setCursorPosition("Allegation");
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Incorrect Allegation Code. Please Correct and Retry"));
		saveErrors(request, errors);
		return (mapping.findForward("failure"));
	    }
	}
	// end validate offense	
	//validate barnum
	if (record.getBarNum() != null && !record.getBarNum().equals(""))
	{
	    if (record.getAttorneyName()== null || record.getAttorneyName().equals(""))
	    {
		    ActionErrors errors = new ActionErrors();
		    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Attorney name is required if bar number is provided.Give the bar number and validate or use Search Attorney to get the attorney"));
		    saveErrors(request, errors);
		    record.setAttorneyName("");
		    return (mapping.findForward("failure"));
	    
	    }
		
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent arequest = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	arequest.setBarNum(record.getBarNum());
	dispatch.postEvent(arequest);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(request, errors);
	    record.setAttorneyName("");
	    return (mapping.findForward("failure"));
	}
	}
	//end of validate barnum

	//if (record.getJuvenileNumber().equals(regform.getJuvenileId()))
	if (record.getJuvenileNumber() != null && !record.getJuvenileNumber().isEmpty())
	{
	    UpdateJJSCLCourtEvent updateCourt = (UpdateJJSCLCourtEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEJJSCLCOURT);
	    // set values to update
	    updateCourt.setDocketEventId(record.getDocketEventId());
	    updateCourt.setCourtId(record.getCourt());
	    updateCourt.setCourtDate(record.getCourtDate());
	    updateCourt.setCourtTime(record.getFormattedCourtTime());
	    updateCourt.setReferralNumber(record.getReferralNum());
	    updateCourt.setSeqNumber(record.getSeqNum());
	    updateCourt.setJuvenileNumber(record.getJuvenileNumber());
	    if (StringUtil.isEmpty(record.getTransferTo()))
		    updateCourt.setTrasferTo(null); 
	    else
		updateCourt.setTrasferTo(record.getTransferTo());
	    if (StringUtil.isEmpty(record.getCourtResult()))
		    updateCourt.setHearingResult(null); 
	    else
		updateCourt.setHearingResult(record.getCourtResult());
	    if (StringUtil.isEmpty(record.getDisposition()))
		    updateCourt.setHearingDisposition(null); 
	    else
		updateCourt.setHearingDisposition(record.getDisposition());	 
	    if (StringUtil.isEmpty(record.getHearingType()))
		    updateCourt.setHearingType(null); 
	    else
		    updateCourt.setHearingType(record.getHearingType());
	    if (StringUtil.isEmpty(record.getResetHearingType()))
		    updateCourt.setResetHearingType(null); 
	    else
		updateCourt.setResetHearingType(record.getResetHearingType());
	    updateCourt.setPetitionNumber(record.getPetitionNumber());
	    updateCourt.setPetitionStatus(record.getPetitionStatus());
	    updateCourt.setPetitionAllegation(record.getAllegation());
	    updateCourt.setFilingDate(record.getFilingDate());
	    if (StringUtil.isEmpty(record.getPetitionAmendment()))
		    updateCourt.setPetitionAmendment(null); 
	    else
		updateCourt.setPetitionAmendment(record.getPetitionAmendment());
	    updateCourt.setAmendmentDate(record.getPetitionAmendmentDate());
	    //updateCourt.setBarNumber(record.getBarNum());
	    if (StringUtil.isEmpty(record.getBarNum()))
		    updateCourt.setBarNumber(null); 
	    else
		updateCourt.setBarNumber(record.getBarNum());
	    if (StringUtil.isEmpty(record.getAttorneyConnection()))
		    updateCourt.setAttorneyConnection(null); 
	    else
		updateCourt.setAttorneyConnection(record.getAttorneyConnection());
	    if (StringUtil.isEmpty(record.getAttorneyName()))
		    updateCourt.setAttorneyName(null); 
	    else
		updateCourt.setAttorneyName(record.getAttorneyName());
	    if (StringUtil.isEmpty(record.getPrevNotes()))
		updateCourt.setPrevNotes(null); 
	    else
		updateCourt.setPrevNotes(record.getPrevNotes());

	    CompositeResponse compResp = MessageUtil.postRequest(updateCourt);
	    Object errResp = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
	    ////////

	    if (errResp == null)
	    {
		// referral save do it once jjsclcourt is success

		GetJJSReferralEvent refEvent = new GetJJSReferralEvent();
		//get the referral id from referral table using juvenileid and refnum
		refEvent.setJuvenileNum(record.getJuvenileNumber());
		refEvent.setReferralNum(record.getReferralNum());
		JJSReferral referralResp = getJuvenileReferralDetails(refEvent);
		if (referralResp != null)
		{
		    UpdateProductionSupportDistrictCourtReferralEvent updateReferral = (UpdateProductionSupportDistrictCourtReferralEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTDISTRICTCOURTREFERRAL);

		    updateReferral.setOID(referralResp.getOID());
		    // set values to update	
		    updateReferral.setJuvenileNum(record.getJuvenileNumber());
		    updateReferral.setCourtId(record.getCourt());
		    updateReferral.setDispositionDate(record.getCourtDate());
		    updateReferral.setCourtResult(record.getCourtResult());
		    updateReferral.setCourtDisposition(record.getDisposition());

		    CompositeResponse compRes = MessageUtil.postRequest(updateReferral);
		    Object errRes = MessageUtil.filterComposite(compResp, ErrorResponseEvent.class);
		    if (errRes == null)
		    {

			log.info("UPDATE JUVENILE DISTRICT COURT DOCKET: " + SecurityUIHelper.getLogonId());

			regform.setMsg("");
			actionFwd = "success";
		    }
		    else
		    {

			regform.setMsg("");
			actionFwd = "error";
		    }
		}
		else
		{
		    log.info("UPDATE JUVENILE DISTRICT COURT DOCKET: " + SecurityUIHelper.getLogonId());

		    regform.setMsg("");
		    actionFwd = "success";
		}
	    }

	    else
	    {

		regform.setMsg("");
		actionFwd = "error";
	    }
	}

	return mapping.findForward(actionFwd);

    }

    private static JJSReferral getJuvenileReferralDetails(GetJJSReferralEvent refEvent)
    {
	JJSReferral referralResp = null;
	//List<JJSReferralResponseEvent> referralResps = MessageUtil.postRequestListFilter(refEvent, JJSReferralResponseEvent.class);
	Iterator<JJSReferral> referralRespItr = JJSReferral.findAll(refEvent);
	if (referralRespItr.hasNext())
	{
	    referralResp = referralRespItr.next();
	}
	return referralResp;
    }

    public ActionForward validateOffenseCode(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	DocketEventResponseEvent record = retrieveRecord(form);

	JuvenileOffenseCodeResponseEvent jocEvent = JuvenileDistrictCourtHelper.validateOffenseCd(record.getAllegation());
	if (jocEvent != null)
	{
	    String lit = jocEvent.getShortDescription() + " (" + jocEvent.getCategory() + ")";
	    record.setAllegationDesc(lit);
	    ActionMessages messageHolder = new ActionMessages();
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.allegationEnteredValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	else
	{
	    form.setCursorPosition("Allegation");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Allegation Code. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	}
	return aMapping.findForward(UIConstants.DISTRICTCOURT_UPDATE_DISPLAY);
    }

    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	String from = nvl( aRequest.getParameter("from") );
	DocketEventResponseEvent record  = null;
	String forward ="";

	
	
	if ( from.equals("updateAncillaryCalendar")) {
	    record = form.getAncillaryCalendarRecord();
	    forward = UIConstants.ANCILLARYCALENDAR_UPDATE_DISPLAY;
	} else {
	    record = retrieveRecord(form);
	    forward = UIConstants.DISTRICTCOURT_UPDATE_DISPLAY;
	}
	
	
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(record.getBarNum());
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    record.setAttorneyName("");
	    return aMapping.findForward(forward);
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
	    if (record.getDocketEventId() != null && !record.getDocketEventId().isEmpty())
	    {
		DocketEventResponseEvent respEvt = record;
		if (respEvt != null)
		{
		    respEvt.setAttorneyName(attorneyRespEvt.getAttyName());
		    respEvt.setBarNum(attorneyRespEvt.getBarNum());
		}
	    }
	    //reset back on the object 
	    ActionMessages messageHolder = new ActionMessages();
	    record.setAttorneyName(attorneyRespEvt.getAttyName());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	return aMapping.findForward(forward);
    }

    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	String from = nvl(aRequest.getParameter("from"));
	DocketEventResponseEvent record = null;
	if ( from.equals("updateAncillaryCalendar") ){
	    record = form.getAncillaryCalendarRecord();
	} else {
	    record = retrieveRecord(form);
	  
	}
	
	
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setAttorneyName(record.getAttorneyName());
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);
    }

    public ActionForward findAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	String attorneyName = aRequest.getParameter("attorneyName");
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setAttorneyName(attorneyName);
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

    public ActionForward returnSelect(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {

	ProdSupportForm form = (ProdSupportForm) aForm;
	DocketEventResponseEvent record = null;
	String attyName = "";
	String barNum = "";
	String attyNameHist = "";
	String forward = "";

	form.setValidateMsg("");
	List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
	String selVal = nvl(aRequest.getParameter("barNum"));
	String from   = nvl(aRequest.getParameter("from"));
	
	if( from.equals("updateAncillaryCalendar")) {
	    record = form.getAncillaryCalendarRecord();
	    forward = UIConstants.ANCILLARYCALENDAR_UPDATE_DISPLAY;
	} else {
	    record = retrieveRecord(form);
	    forward = UIConstants.DISTRICTCOURT_UPDATE_DISPLAY;
	    
	}
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

	record.setAttorneyName(attyName);
	record.setBarNum(barNum);
	form.setSelectedValue(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	selVal = null;
	wrkList = null;
	form.setAttorneyDataList(null);
	
	 return aMapping.findForward(forward);
    }

    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	form.setAttorneyName(UIConstants.EMPTY_STRING);
	form.setAttorneyDataList(new ArrayList<AttorneyNameAndAddressResponseEvent>());
	return aMapping.findForward("attorneySearchSuccess");
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	String from = nvl( aRequest.getParameter("from") );
	String forward ="";

	
	
	if ( from.equals("updateAncillaryCalendar")) {
	    forward = UIConstants.ANCILLARYCALENDAR_UPDATE_DISPLAY;
	} else {
	    forward = UIConstants.DISTRICTCOURT_UPDATE_DISPLAY;
	}
	return aMapping.findForward(forward);
    }

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

    private DocketEventResponseEvent retrieveRecord(ProdSupportForm regform)
    {

	ArrayList juvDistCourtRecords = regform.getJuvDistCourtRecords();
	DocketEventResponseEvent record = null;

	Iterator iter = juvDistCourtRecords.iterator();
	if (iter.hasNext())
	{
	    record = (DocketEventResponseEvent) iter.next();
	}

	return record;
    }
   

    private String nvl(String value){
	return (value != null && value.length() > 0) ? value : "";
    }

    
}

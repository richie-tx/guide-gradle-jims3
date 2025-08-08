
package ui.juvenilecase.prodsupport.action.delete;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.districtCourtHearings.DeleteJJSCLAncillarySettingEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import messaging.productionsupport.UpdateJJSCLDetentionCourtEvent;
import messaging.referral.GetJJSReferralEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
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

import pd.juvenilecase.referral.JJSReferral;
import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;



public class DeleteAncillaryCourtCalendarRecordAction extends JIMSBaseAction
{
    @Override
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.deleteRecord", "deleteAncillaryCourtCalendarRecord");	
	keyMap.put("button.validateBarNumber", "validateBarNumber");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.findAttorney", "findAttorney");
	keyMap.put("button.refresh", "refresh");
	keyMap.put("button.back", "back");
	keyMap.put("button.cancel", "cancel");
	keyMap.put("button.select", "returnSelect");
    }

    private Logger log = Logger.getLogger("DeleteAncillaryCourtCalendarRecordAction");

    public ActionForward deleteAncillaryCourtCalendarRecord(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	ProdSupportForm regform = (ProdSupportForm) form;
	String logonid = SecurityUIHelper.getLogonId();
	String actionFwd = "";
	ArrayList<DocketEventResponseEvent> ancillaryRecords = regform.getAncillaryCalendarRecords();

	DocketEventResponseEvent ancillaryRecord = retrieveRecord(regform);

	if (ancillaryRecord == null)
	{
	    regform.setMsg("DeleteAncillaryCourtCalendarRecordAction.java - Could not delete record. ancillary record is null");
	    return (mapping.findForward("error"));
	}
	
	if (ancillaryRecord.getDocketEventId() == null || ancillaryRecord.getDocketEventId().equals(""))
	{
	    regform.setMsg("DeleteAncillaryCourtCalendarRecordAction.java - Could not delete record. docketEventId is null");
	    return (mapping.findForward("error"));
	}
	
	DeleteJJSCLAncillarySettingEvent deleteAncillarySettingEvt = (DeleteJJSCLAncillarySettingEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.DELETEJJSCLANCILLARYSETTING);
	    deleteAncillarySettingEvt.setDocketEventId(ancillaryRecord.getDocketEventId());

	    CompositeResponse compositeResponse = MessageUtil.postRequest(deleteAncillarySettingEvt);
	    DocketEventResponseEvent resp = (DocketEventResponseEvent) MessageUtil.filterComposite(compositeResponse, DocketEventResponseEvent.class);
	    Object errorResponse = MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (errorResponse != null)
	    {
		ErrorResponseEvent myEvt = (ErrorResponseEvent) errorResponse;
		try
		{
		    handleFatalUnexpectedException(myEvt.getMessage());
		}
		catch (GeneralFeedbackMessageException e)
		{
		    e.printStackTrace();
		}
	    }	    
	    
	    regform.setPetitionNum(ancillaryRecord.getPetitionNumber());
	    regform.setRespondentName(ancillaryRecord.getRespondantName());
	    regform.setDcketEventId(ancillaryRecord.getDocketEventId());
	    regform.setAncillaryCalendarRecord(ancillaryRecord);
		    
	    
	    actionFwd = "success";

	return mapping.findForward(actionFwd);

    }
    
    
    private ArrayList retrieveDetentionCourtDetails( DocketEventResponseEvent record ){

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent jjsCLCrtEvent = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.GETJJSCLDETENTIONBYJUVNUMREFNUMCHAINNUMCOURTDATE);
	jjsCLCrtEvent.setJuvenileNumber(record.getJuvenileNumber());
	jjsCLCrtEvent.setReferralNumber(record.getReferralNum());
	jjsCLCrtEvent.setChainNumber(record.getChainNumber());
	Date cortDate = DateUtil.stringToDate(record.getCourtDate(), DateUtil.DATE_FMT_1);
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String formattedCourtDate = sdf.format(cortDate);
	    jjsCLCrtEvent.setCourtDate(formattedCourtDate);	

	dispatch.postEvent(jjsCLCrtEvent);
	CompositeResponse resp = (CompositeResponse) dispatch.getReply();
	Map<String, DocketEventResponseEvent> docketsMap = new HashMap<String, DocketEventResponseEvent>();
	//List<DocketEventResponseEvent> dktRespEvts = MessageUtil.compositeToList(resp, DocketEventResponseEvent.class);	
	ArrayList juvDetCourtRecords = (ArrayList) MessageUtil.compositeToCollection(resp, DocketEventResponseEvent.class);
	//filter by court number
	ArrayList filteredjuvDetCourtRecords = new ArrayList();
	if (juvDetCourtRecords != null) 
	{
		Iterator iter = juvDetCourtRecords.iterator();
		while (iter.hasNext()) 
		{
		    DocketEventResponseEvent dkt = (DocketEventResponseEvent) iter.next();
		    if(dkt.getCourt().equalsIgnoreCase(record.getCourt()))
		    {
			filteredjuvDetCourtRecords.add(dkt);
		    }			
		
		}
	}
	return filteredjuvDetCourtRecords;
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

    
    public ActionForward validateBarNumber(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	DocketEventResponseEvent record = retrieveRecord(form);
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
	    return aMapping.findForward(UIConstants.FAILURE);
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
	return aMapping.findForward(UIConstants.DISTRICTCOURT_UPDATE_DISPLAY);
    }

    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	ProdSupportForm form = (ProdSupportForm) aForm;
	DocketEventResponseEvent record = retrieveRecord(form);

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
	DocketEventResponseEvent record = retrieveRecord(form);
	String attyName = "";
	String barNum = "";
	String attyNameHist = "";

	form.setValidateMsg("");
	List<AttorneyNameAndAddressResponseEvent> wrkList = new ArrayList<AttorneyNameAndAddressResponseEvent>(form.getAttorneyDataList());
	String selVal = form.getSelectedValue();
	for ( int x = 0; x < wrkList.size(); x++)
	{
	    AttorneyNameAndAddressResponseEvent jcoEvent = (AttorneyNameAndAddressResponseEvent) wrkList.get(x);
	    if ( jcoEvent.getBarNum().equals(selVal))
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
	//selVal = null;
	//wrkList = null;
	//form.setAttorneyDataList(null);
	return aMapping.findForward(UIConstants.DISTRICTCOURT_UPDATE_DISPLAY);
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
	return aMapping.findForward(UIConstants.DISTRICTCOURT_UPDATE_DISPLAY);
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

	ArrayList ancillaryCourtRecords = regform.getAncillaryCalendarRecords();
	DocketEventResponseEvent record = null;

	Iterator iter = ancillaryCourtRecords.iterator();
	if (iter.hasNext())
	{
	    record = (DocketEventResponseEvent) iter.next();
	}

	return record;
    }

}


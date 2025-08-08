package ui.juvenilecase.districtCourtHearings.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetAttorneyNameAndBarNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByAtySettingSrchAttrEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByBarNumAndHearingDateEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByBarNumAndJuvNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByBarNumEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByGALSearchEvent;
import messaging.detentionCourtHearings.GetJJSCLDetentionByJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLAncillaryCourtByBarNumAndHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByAtySettingSrchAttrEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByBarNumAndHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByBarNumAndJuvNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByBarNumEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByGALSearchEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenilecase.reply.AttorneyNameAndAddressResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileCourtHearingControllerServiceNames;
import naming.JuvenileDetentionHearingControllerServiceNames;
import naming.UIConstants;
import net.minidev.json.JSONObject;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.AttorneysettingsSearchResultsReportBean;
import ui.juvenilecase.CasefileSearchResultsReportBean;
import ui.juvenilecase.GALAttorneysettingsSearchResultsReportBean;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.StandardDocketReportBean;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;
import ui.juvenilecase.facility.JuvenileFacilityHelper;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

/**
 * 
 * @author sthyagarajan
 *
 */
public class SubmitJuvenileDistrictCourtGALAttorneySettingSearch extends JIMSBaseAction
{
   
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.submit", "submit");
	keyMap.put("button.searchAttorney", "searchAttorney");
	keyMap.put("button.validateBarNumber", "validateBarNumber");
	keyMap.put("button.print", "print");
    }


    /**
     * Submit
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
	courtHearingForm.setDktSearchResults(Collections.EMPTY_LIST); //empty the list
	
	String barNum = courtHearingForm.getGalBarNumber();
	String juvenileNum = courtHearingForm.getJuvenileNumber();
	String attorneyName = courtHearingForm.getGalName();
	courtHearingForm.setErrMessage(UIConstants.EMPTY_STRING);
	// bar no not entered but attorney name entered.
	if (attorneyName != null && !attorneyName.isEmpty() && barNum != null && barNum.isEmpty() )
	{
	    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	    request.setAttorneyName(attorneyName);
	    dispatch.postEvent(request);

	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
	    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (error != null || attorneyDataList.isEmpty())
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Attorney Name. Please Correct and Retry"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    if (attorneyDataList != null & !attorneyDataList.isEmpty()) //set Bar Number of the attorney
	    {
	    	//Bug #69378 - if multiple attorneys found then display error message
	    	if(attorneyDataList.size()>1)
	    	{
	    		ActionErrors errors = new ActionErrors();
	    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Multiple attorneys found with this name, press Search Attorney to display list."));
	    		saveErrors(aRequest, errors);
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	}
	    	else if(attorneyDataList.size()==1) //an attorney match found
	    	{
	    		 AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(0);
	 		    courtHearingForm.setBarNumber(respEvent.getBarNum());
	    	}
	    	else
	    	{
	    		ActionErrors errors = new ActionErrors();
	    		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Attorney Name. Please Correct and Retry"));
	    		saveErrors(aRequest, errors);
	    		return aMapping.findForward(UIConstants.FAILURE);
	    	}
		/*for (int x = 0; x < attorneyDataList.size();)
		{
		    AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		    courtHearingForm.setBarNumber(respEvent.getBarNum());
		    break;
		}*/
	    }
	}
	
	// if validate bar num is not clicked.
	if (barNum != null && !barNum.isEmpty() && attorneyName != null && attorneyName.isEmpty())
	{
	    // bar no entered but attorney name not entered.
	    GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	    request.setBarNum(barNum);
	    dispatch.postEvent(request);

	    CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	    Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	    MessageUtil.processReturnException(dataMap);
	    Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	    ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	    if (error != null || attorneyDataList.isEmpty())
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Records Found For Supplied Inquiry Fields."));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	    if (attorneyDataList != null & !attorneyDataList.isEmpty()) //set Bar Number of the attorney
	    {
		for (int x = 0; x < attorneyDataList.size();)
		{
		    AttorneyNameAndAddressResponseEvent respEvent = ((List<AttorneyNameAndAddressResponseEvent>) attorneyDataList).get(x);
		    courtHearingForm.setAttorneyName(respEvent.getAttyName());
		    break;
		}
	    }
	}

	List<DocketEventResponseEvent> dockets = new ArrayList<DocketEventResponseEvent>();

	/*if (courtHearingForm.getBarNumber()!=null && !courtHearingForm.getBarNumber().isEmpty() || juvenileNum!=null && !juvenileNum.isEmpty()
												|| courtHearingForm.getAttorneyName()!=null && courtHearingForm.getAttorneyName().isEmpty() )
	{*/
	    
	    //*****************************************COURT RECORDS *********************************************//	
	     
	    
	    if (courtHearingForm.getCourtDate() != null || courtHearingForm.getJuvenileNumber() != null || courtHearingForm.getGalBarNumber()!= null)
	    {
		GetJJSCLCourtByGALSearchEvent jjsCrtJuvEvt = (GetJJSCLCourtByGALSearchEvent) EventFactory.getInstance(JuvenileCourtHearingControllerServiceNames.GETJJSCLCOURTBYGALSEARCH);
		jjsCrtJuvEvt.setJuvenileNumber(courtHearingForm.getJuvenileNumber());
		jjsCrtJuvEvt.setBarNumber(courtHearingForm.getGalBarNumber());
		if(courtHearingForm.getCourtDate()!=null&&!courtHearingForm.getCourtDate().isEmpty())
		{
        		Date cortDate = DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1);
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        		String formattedCourtDate = sdf.format(cortDate);
        		jjsCrtJuvEvt.setCourtDate(formattedCourtDate);
		}	
		Date todaysDate = DateUtil.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(todaysDate);
		jjsCrtJuvEvt.setTodaysDate(formattedDate);
		dispatch.postEvent(jjsCrtJuvEvt);
		CompositeResponse juvCompResp = (CompositeResponse) dispatch.getReply();
		List<DocketEventResponseEvent> crtDktRespEvents = MessageUtil.compositeToList(juvCompResp, DocketEventResponseEvent.class);
		//add the conditions for 146714 except the date //add validation to give only todays or future date while entering
		//sort order by oid and chainnumber
				//Date fromDate=DateUtil.stringToDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_2),DateUtil.DATE_FMT_2);
				Collections.sort((List<DocketEventResponseEvent>) crtDktRespEvents, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
					@Override
					public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
					{
					    return new CompareToBuilder().append(evt1.getChainNumber(), evt2.getChainNumber())
							.append(evt1.getDocketEventId(), evt2.getDocketEventId()).toComparison();
					 }
				    }));
				
				String prevChain="";
				//
		if (crtDktRespEvents != null && !crtDktRespEvents.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> dktRespEvtsItr = crtDktRespEvents.iterator();

		    while (dktRespEvtsItr.hasNext())
		    {
			DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) dktRespEvtsItr.next();
			if (docRespEvent != null)
			{
			    if(docRespEvent.getChainNumber().equalsIgnoreCase(prevChain))
				continue;
			    else //if(fromDate.compareTo(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1))<=0)
			    {
				docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
				dockets.add(docRespEvent);
			    }
			}
			//to set the previous chain
			prevChain=docRespEvent.getChainNumber();
		    }
		}
	    }    
	    
	    if (courtHearingForm.getCourtDate() != null || courtHearingForm.getJuvenileNumber() != null || courtHearingForm.getGalBarNumber()!= null)
	    {
		// Get detention records based on bar no or juvenile no.
		GetJJSCLDetentionByGALSearchEvent jjsJuvDetnEvt = (GetJJSCLDetentionByGALSearchEvent) EventFactory.getInstance(JuvenileDetentionHearingControllerServiceNames.GETJJSCLDETENTIONBYGALSEARCH);
		jjsJuvDetnEvt.setJuvenileNumber(courtHearingForm.getJuvenileNumber());
		jjsJuvDetnEvt.setGalBarNumber(courtHearingForm.getGalBarNumber());
		if(courtHearingForm.getCourtDate()!=null&&!courtHearingForm.getCourtDate().isEmpty())
		{
        		Date cortDate = DateUtil.stringToDate(courtHearingForm.getCourtDate(), DateUtil.DATE_FMT_1);
        		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        		String formattedCourtDate = sdf.format(cortDate);
        		jjsJuvDetnEvt.setCourtDate(formattedCourtDate);
		}
		Date todaysDate = DateUtil.getCurrentDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(todaysDate);
		jjsJuvDetnEvt.setTodaysDate(formattedDate);
		dispatch.postEvent(jjsJuvDetnEvt);
		CompositeResponse juvRespnse = (CompositeResponse) dispatch.getReply();
		List<DocketEventResponseEvent> dktResponses = MessageUtil.compositeToList(juvRespnse, DocketEventResponseEvent.class);
		////task 146725
				//sort order by oid and chainnumber
				//Date fromDate=DateUtil.stringToDate(DateUtil.dateToString( DateUtil.getCurrentDate(), DateUtil.DATE_FMT_2),DateUtil.DATE_FMT_2);
				Collections.sort((List<DocketEventResponseEvent>) dktResponses, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
					@Override
					public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
					{
					    return new CompareToBuilder().append(evt1.getChainNumber(), evt2.getChainNumber())
							.append(evt1.getDocketEventId(), evt2.getDocketEventId()).toComparison();
					 }
				    }));
				
				String prevChain="";
				//
		if (dktResponses != null && !dktResponses.isEmpty())
		{
		    Iterator<DocketEventResponseEvent> clDetnDktRespItr = dktResponses.iterator();
		    while (clDetnDktRespItr.hasNext())
		    {
			DocketEventResponseEvent docRespEvent = (DocketEventResponseEvent) clDetnDktRespItr.next();
			if (docRespEvent != null)
			{
			////task 146725
			    if(docRespEvent.getChainNumber().equalsIgnoreCase(prevChain))
				continue;
			    else //if(fromDate.compareTo(DateUtil.stringToDate(docRespEvent.getCourtDate(), DateUtil.DATE_FMT_1))<=0)
			    {
        			    //set Court Date and time
        			    docRespEvent.setCourtTime(JuvenileFacilityHelper.stripColonInDate(docRespEvent.getCourtTime()));
        			    if (docRespEvent.getJuvenileNumber() != null)
        			    {
        				docRespEvent.setPetitionNumber("J0" + docRespEvent.getJuvenileNumber());
        			    }
        			    dockets.add(docRespEvent);
			    }
			}
			//to set the previous chain
			prevChain=docRespEvent.getChainNumber();
		    }
		}
	    }
	    
	    Collections.sort((List<DocketEventResponseEvent>) dockets, Collections.reverseOrder(new Comparator<DocketEventResponseEvent>() {
		@Override
		public int compare(DocketEventResponseEvent evt1, DocketEventResponseEvent evt2)
		{
		    //Bug #69311 - string comparison of date was not working
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    Date date1 = new Date();
		    Date date2 = new Date();
		    if(evt1.getCourtDate()!=null &&evt2.getCourtDate()!=null)
		    {
        		    try
        		    {
        			date1 = sdf.parse(evt1.getCourtDate());
        		    }
        		    catch (ParseException e)
        		    {
        			e.printStackTrace();
        		    }
        		    try
        		    {
        			date2 = sdf.parse(evt2.getCourtDate());
        		    }
        		    catch (ParseException e)
        		    {
        			e.printStackTrace();
        		    }
		    }
		    return new CompareToBuilder().append(date1, date2).append(evt1.getCourtTime(), evt2.getCourtTime()).append(evt1.getPetitionNumber(), evt2.getPetitionNumber()).toComparison();

		}
	    }));
	  
	    courtHearingForm.setDktSearchResults(dockets);
	    courtHearingForm.setSearchResultSize(dockets.size());

	    if (courtHearingForm.getDktSearchResults() == null || (courtHearingForm.getDktSearchResults() != null && courtHearingForm.getDktSearchResults().isEmpty()))
	    {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No Record Found For Supplied Inquiry Fields"));
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	    }
	   // List<JSONObject> juvenileDatesFormattedList = JuvenileDistrictCourtHelper.juvenileHolidays();
	//    courtHearingForm.setHolidaysList(juvenileDatesFormattedList);
	//}
	return aMapping.findForward(UIConstants.GAL_ATTORNEY_SETTING_SUCCESS);
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
    public ActionForward validateBarNumber(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	AttorneyNameAndAddressResponseEvent attorneyRespEvt = null;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	
	GetAttorneyNameAndBarNumEvent request = (GetAttorneyNameAndBarNumEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETATTORNEYNAMEANDBARNUM);
	request.setBarNum(form.getGalBarNumber());
	dispatch.postEvent(request);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();

	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	Collection<AttorneyNameAndAddressResponseEvent> attorneyDataList = MessageUtil.compositeToCollection(compositeResponse, AttorneyNameAndAddressResponseEvent.class);
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, ErrorResponseEvent.class);
	if (error != null || attorneyDataList.isEmpty())
	{
	    form.setCursorPosition("barNumber");
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Invalid Bar Number. Please Correct and Retry"));
	    saveErrors(aRequest, errors);
	    form.setAttorneyName("");
	    form.setPrevAction(form.getAction());
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
	    form.setValidateMsg("Bar Number Entered Valid");
	    ActionMessages messageHolder = new ActionMessages();
	    form.setAttorneyName(attorneyRespEvt.getAttyName());
	    messageHolder.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("prompt.barNumberValid"));
	    aRequest.setAttribute(Globals.MESSAGE_KEY, messageHolder);
	    saveMessages(aRequest, messageHolder);
	}
	form.setPrevAction(form.getAction());
	return aMapping.findForward(UIConstants.GAL_ATTORNEY_SETTING_SUCCESS);
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
    public ActionForward searchAttorney(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	form.setAction("GALattorneySetting");
	String pagerOffset = (String) aRequest.getParameter("pager.offset");
	HttpSession session = aRequest.getSession();
	session.setAttribute("Pager_Offset", pagerOffset);
	form.setConfirmMsg(UIConstants.EMPTY_STRING);
	form.setErrMessage(UIConstants.EMPTY_STRING);
	
	return aMapping.findForward(UIConstants.ATTORNEY_SEARCH_SUCCESS);

	
    }
    public ActionForward print(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse)
    {
	CourtHearingForm form = (CourtHearingForm) aForm;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	GALAttorneysettingsSearchResultsReportBean reportBean = new GALAttorneysettingsSearchResultsReportBean();
	if (form.getDktSearchResults() != null)
	    reportBean.setNumberOfResults(form.getDktSearchResults().size());
	reportBean.setTodaysDate(DateUtil.dateToString(new Date(), UIConstants.DATE_FMT_1));
	reportBean.setAttorneySearchResults(form.getDktSearchResults());
	aRequest.getSession().setAttribute("reportInfo", reportBean);
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	//let the pdfManager know that the report should be saved in the request during report creation
	aRequest.setAttribute("isPdfSaveNeeded", "false");
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.GALATTORNEYSETTINGS_SEARCH_RESULTS_REPORT);
	return null;
	
    }

}

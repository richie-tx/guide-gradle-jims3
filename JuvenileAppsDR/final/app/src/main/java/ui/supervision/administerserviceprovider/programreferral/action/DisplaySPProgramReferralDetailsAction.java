// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\supervision\\administerserviceprovider\\programreferral\\action\\DisplaySPProgramReferralDetailsAction.java

package ui.supervision.administerserviceprovider.programreferral.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import messaging.report.GenericPrintRequestEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.common.Name;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;
import ui.juvenilecase.programreferral.form.ProgramReferralSearchForm;
import ui.supervision.administerserviceprovider.JuvenileProgramReferralListBean;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class DisplaySPProgramReferralDetailsAction extends JIMSBaseAction
{
    /**
     * @roseuid 463BA549028D
     */
    public DisplaySPProgramReferralDetailsAction()
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
    public ActionForward displayReferralDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	String referralId = aRequest.getParameter("referralId");

	ProgramReferralForm form = (ProgramReferralForm) aForm;

	UIProgramReferralBean programReferral = null;
	if (referralId != null && !"".equals(referralId))
	{
	    GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
	    gpdt.setProgramReferralId(referralId);
	    CompositeResponse compositeResponse = MessageUtil.postRequest(gpdt);

	    ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent) MessageUtil.filterComposite(compositeResponse, ProgramReferralResponseEvent.class);
	    if (respDetail != null)
	    {
		programReferral = new UIProgramReferralBean(respDetail);
		programReferral.setCurrentUserType(ProgramReferralConstants.SP_USER);
		programReferral.setNextPossibleActions(programReferral.getReferralState().getPossibleNextActions(ProgramReferralConstants.SP_USER, respDetail.isInHouse()));
	    }
	}

	if (programReferral != null)
	{
	    UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true), programReferral.getCasefileId());

	    GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);
	    gprs.setJuvenileNum(programReferral.getJuvenileId());
	    gprs.setProgramId(programReferral.getProviderProgramId());
	    gprs.setProgramReferralId(programReferral.getReferralId());

	    CompositeResponse compositeResponse = MessageUtil.postRequest(gprs);

	    List calendarEvents = (List) MessageUtil.compositeToCollection(compositeResponse, CalendarServiceEventResponseEvent.class);
	    if (calendarEvents != null)
	    {
		if (calendarEvents.size() > 1)
		{
		    Collections.sort(calendarEvents);
		}
		//programReferral.setJuvenileEvents(calendarEvents);

		//<KISHORE>JIMS200059455 : Prog. Referral: Do not display canc. events(UI)-KK
		List activeCalendarEvents = new ArrayList();
		Iterator<CalendarServiceEventResponseEvent> iter = calendarEvents.iterator();
		while (iter.hasNext())
		{
		    CalendarServiceEventResponseEvent event = iter.next();
		    if (!UIConstants.STATUS_CODE_CANCELLED.equalsIgnoreCase(event.getEventStatusCode()))
		    {
			event.setEventStatus(CodeHelper.getCodeDescription(PDCodeTableConstants.SERVEVENT_STATUS, event.getEventStatusCode()));
			activeCalendarEvents.add(event);
		    }
		}

		programReferral.setJuvenileEvents(activeCalendarEvents);
	    }

	    GetProgramAttendanceEvent gpae = (GetProgramAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE);
	    gpae.setProgramId(programReferral.getProviderProgramId());
	    gpae.setJuvenileNum(programReferral.getJuvenileId());

	    compositeResponse = MessageUtil.postRequest(gpae);

	    List attendanceEvents = (List) MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);
	    if (attendanceEvents != null)
	    {
		if (attendanceEvents.size() > 1)
		{
		    Collections.sort(attendanceEvents);
		}
		programReferral.setExistingReferrals(attendanceEvents);
	    }

	    List<ProgramReferralResponseEvent> juvRefHistory = UIProgramReferralHelper.getJuvenileReferralHistory(programReferral.getJuvenileId());
	    if (juvRefHistory != null)
	    {
		List history = new ArrayList();
		for (ProgramReferralResponseEvent progReferral : juvRefHistory)
		{
		    if (!progReferral.getReferralId().equals(referralId))
		    {
			history.add(progReferral);
		    }
		}

		if (history.size() > 1)
		{
		    Collections.sort(history);
		}
		programReferral.setJuvenileReferralHistory(history);
	    }
	}

	form.setProgramReferral(programReferral);
	form.setAction(UIConstants.VIEW);

	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);

	return forward;
    }

    public ActionForward printProgramReferralList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws Exception
    {

	//ProgramReferralForm form = (ProgramReferralForm) aForm;
	HttpSession session = aRequest.getSession();
	ProgramReferralSearchForm programReferralSearchForm = (ProgramReferralSearchForm) session.getAttribute("programReferralSearchForm");

	GenericPrintRequestEvent printEvent = new GenericPrintRequestEvent();
	printEvent.addDataObject(prepareBean(programReferralSearchForm));
	printEvent.setReportName("REPORTING::PROGRAM_REFERRAL_LIST_REPORT");

	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(printEvent);

	CompositeResponse compResponse = (CompositeResponse) dispatch.getReply();

	MessageUtil.processReturnException(compResponse);

	ReportResponseEvent aRespEvent = (ReportResponseEvent) MessageUtil.filterComposite(compResponse, ReportResponseEvent.class);
	if (aRespEvent == null)
	{
	    ReturnException returnException = (ReturnException) MessageUtil.filterComposite(compResponse, ReturnException.class);
	    throw returnException;
	}
	else
	{
	    aResponse.setContentType("application/x-file-download");
	    aResponse.setHeader("Content-disposition", "attachment; filename=" + aRespEvent.getFileName().substring(aRespEvent.getFileName().lastIndexOf("/") + 1) + ".pdf");
	    aResponse.setHeader("Cache-Control", "max-age=" + 120);
	    aResponse.setContentLength(aRespEvent.getContent().length);
	    aResponse.resetBuffer();
	    OutputStream os;
	    os = aResponse.getOutputStream();
	    os.write(aRespEvent.getContent(), 0, aRespEvent.getContent().length);
	    os.flush();
	    os.close();
	}
	return null;
    }

    private JuvenileProgramReferralListBean prepareBean(ProgramReferralSearchForm prForm)
    {

	JuvenileProgramReferralListBean printDataBean = new JuvenileProgramReferralListBean();
	printDataBean.setJuvServiceProviderName(StringEscapeUtils.escapeXml(prForm.getProgramReferral().getJuvServiceProviderName()));

	Iterator iterActiveReferrals = prForm.getActiveReferralList().iterator();
	Iterator iterClosedReferrals = prForm.getClosedReferralList().iterator();
	Iterator iterRejectedReferrals = prForm.getRejectedReferralList().iterator();
	ArrayList referralList = new ArrayList();

	while (iterActiveReferrals.hasNext())
	{

	    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) iterActiveReferrals.next();
	    
	    resp.setProviderProgramName(StringEscapeUtils.escapeXml(resp.getProviderProgramName()));

	    if (printDataBean.getJuvServiceProviderName() == null || printDataBean.getJuvServiceProviderName().equals(""))
	    {
		printDataBean.setJuvServiceProviderName(StringEscapeUtils.escapeXml(resp.getJuvServiceProviderName()));
	    }
	    Name juvenileFullName = new Name();
	    if (StringUtils.isNotEmpty(resp.getJuvenileFirstName()))
	    {

		juvenileFullName.setFirstName(resp.getJuvenileFirstName());
		juvenileFullName.setMiddleName(resp.getJuvenileMiddleName());
		juvenileFullName.setLastName(resp.getJuvenileLastName());
		resp.setJuvenileFullName(juvenileFullName.getFormattedName());
	    }

	    Name jpoFullName = new Name();
	    jpoFullName.setFirstName(resp.getOfficerFirstName());
	    //jpoFullName.setMiddleName(resp.getOfficerMiddleName());
	    jpoFullName.setLastName(resp.getOfficerLastName());
	    resp.setOfficerFullName(jpoFullName.getFormattedName());
	    referralList.add(resp);
	}

	while (iterRejectedReferrals.hasNext())
	{

	    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) iterRejectedReferrals.next();
	    
	    resp.setProviderProgramName(StringEscapeUtils.escapeXml(resp.getProviderProgramName()));

	    if (printDataBean.getJuvServiceProviderName() == null || printDataBean.getJuvServiceProviderName().equals(""))
	    {
		printDataBean.setJuvServiceProviderName(StringEscapeUtils.escapeXml(resp.getJuvServiceProviderName()));
	    }
	    Name juvenileFullName = new Name();
	    if (StringUtils.isNotEmpty(resp.getJuvenileFirstName()))
	    {

		juvenileFullName.setFirstName(resp.getJuvenileFirstName());
		juvenileFullName.setMiddleName(resp.getJuvenileMiddleName());
		juvenileFullName.setLastName(resp.getJuvenileLastName());
		resp.setJuvenileFullName(juvenileFullName.getFormattedName());
	    }
	    Name jpoFullName = new Name();
	    jpoFullName.setFirstName(resp.getOfficerFirstName());
	    //jpoFullName.setMiddleName(resp.getOfficerMiddleName());
	    jpoFullName.setLastName(resp.getOfficerLastName());
	    resp.setOfficerFullName(jpoFullName.getFormattedName());

	    referralList.add(resp);
	}

	while (iterClosedReferrals.hasNext())
	{

	    ProgramReferralResponseEvent resp = (ProgramReferralResponseEvent) iterClosedReferrals.next();
	    
	    resp.setProviderProgramName(StringEscapeUtils.escapeXml(resp.getProviderProgramName()));
	    
	    if (printDataBean.getJuvServiceProviderName() == null || printDataBean.getJuvServiceProviderName().equals(""))
	    {
		printDataBean.setJuvServiceProviderName(StringEscapeUtils.escapeXml(resp.getJuvServiceProviderName()));
	    }
	    Name juvenileFullName = new Name();
	    if (StringUtils.isNotEmpty(resp.getJuvenileFirstName()))
	    {

		juvenileFullName.setFirstName(resp.getJuvenileFirstName());
		juvenileFullName.setMiddleName(resp.getJuvenileMiddleName());
		juvenileFullName.setLastName(resp.getJuvenileLastName());
		resp.setJuvenileFullName(juvenileFullName.getFormattedName());
	    }    
	   
	    
	    Name jpoFullName = new Name();
	    jpoFullName.setFirstName(resp.getOfficerFirstName());
	    //jpoFullName.setMiddleName(resp.getOfficerMiddleName());
	    jpoFullName.setLastName(resp.getOfficerLastName());
	    resp.setOfficerFullName(jpoFullName.getFormattedName());

	    referralList.add(resp);
	}

	/*if (referralList != null && referralList.size() > 1)
	{
	    List sortedList = new ArrayList(referralList);

	    ArrayList sortFields = new ArrayList();
	    sortFields.add(new ComparatorChain(new BeanComparator("juvenileFullName")));
	    sortFields.add(new ReverseComparator(new BeanComparator("beginDate")));
	    sortFields.add(new ComparatorChain(new BeanComparator("providerProgramName")));
	    ComparatorChain multiSort = new ComparatorChain(sortFields);
	    Collections.sort(sortedList, multiSort);

	    referralList = new ArrayList(sortedList);

	}*/

	printDataBean.setReferralList(referralList);
	return printDataBean;
    }

    /**
     * 
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward printProgramReferralListBFO(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	JuvenileProgramReferralListBean printEventTO = new JuvenileProgramReferralListBean();

	HttpSession session = aRequest.getSession();
	ProgramReferralSearchForm programReferralSearchForm = (ProgramReferralSearchForm) session.getAttribute("programReferralSearchForm");

	printEventTO = prepareBean(programReferralSearchForm);

	aRequest.getSession().setAttribute("form", printEventTO);
	// generate report
	BFOPdfManager pdfManager = BFOPdfManager.getInstance();
	pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PROGRAM_REFERRAL_LIST_REPORT);

	return null;
    }

     /*
     * (non-Javadoc)
     * 
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "displayReferralDetails");
	keyMap.put("button.details", "showEventDetails");
	keyMap.put("button.printProgramReferralList", "printProgramReferralList");
	keyMap.put("button.printProgramReferralListBFO", "printProgramReferralListBFO");
    }
}

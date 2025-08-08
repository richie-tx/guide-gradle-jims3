// Source file:
// C:\\views\\MJCW\\app\\src\\ui\\juvenilecase\\programreferral\\action\\DisplayProgramReferralDetailsAction.java

package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.GetProgramAttendanceEvent;
import messaging.calendar.GetProgramReferralServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.calendar.reply.ServiceEventAttendanceResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.programreferral.GetJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.programreferral.GetProgramReferralDetailsEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.programreferral.reply.ProgramReferralResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileProgramReferralControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.ProgramReferralConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.programreferral.form.ProgramReferralForm;

public class DisplayJuvenileProfileProgramReferralDetailsAction extends JIMSBaseAction
{

    /**
     * @roseuid 463BA573033A
     */
    public DisplayJuvenileProfileProgramReferralDetailsAction()
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
	if (referralId != null || !"".equals(referralId))
	{
	    GetProgramReferralDetailsEvent gpdt = (GetProgramReferralDetailsEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETPROGRAMREFERRALDETAILS);
	    gpdt.setProgramReferralId(referralId);
	    CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gpdt);
	    ProgramReferralResponseEvent respDetail = (ProgramReferralResponseEvent) MessageUtil.filterComposite(compositeResponse, ProgramReferralResponseEvent.class);
	    if (respDetail != null)
	    {
		programReferral = new UIProgramReferralBean(respDetail);
		programReferral.setCurrentUserType(ProgramReferralConstants.JPO_USER);
		programReferral.setNextPossibleActions(programReferral.getReferralState().getPossibleNextActions(ProgramReferralConstants.JPO_USER));
	    }
	}

	if (programReferral != null)
	{

	    UIJuvenileCaseworkHelper.populateJuvenileCasefileForm(UIJuvenileCaseworkHelper.getHeaderForm(aRequest, true), programReferral.getCasefileId());
	    
	    //Attendance Events
	    GetProgramAttendanceEvent gpae = (GetProgramAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE);

	    gpae.setProgramId(programReferral.getProviderProgramId());
	    gpae.setJuvenileNum(programReferral.getJuvenileId());
	    
	    CompositeResponse compResponse = (CompositeResponse) MessageUtil.postRequest(gpae);
	    List<ServiceEventAttendanceResponseEvent> attendanceEvents = (List<ServiceEventAttendanceResponseEvent>) MessageUtil.compositeToCollection(compResponse, ServiceEventAttendanceResponseEvent.class);

	    if (attendanceEvents != null)
	    {
		Collections.sort((List<ServiceEventAttendanceResponseEvent>) attendanceEvents);
		programReferral.setExistingReferrals(attendanceEvents);
	    }

	    GetProgramReferralServiceEventsEvent gprs = (GetProgramReferralServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMREFERRALSERVICEEVENTS);
	    gprs.setJuvenileNum(programReferral.getJuvenileId());
	    gprs.setProgramId(programReferral.getProviderProgramId());
	    gprs.setProgramReferralId(programReferral.getReferralId());

	    CompositeResponse compositeResponse = (CompositeResponse) MessageUtil.postRequest(gprs);

	    List calendarEvents = (List) MessageUtil.compositeToCollection(compositeResponse, CalendarServiceEventResponseEvent.class);

	    float creditHours = 0;
	    float totalCreditHours = 0;
	    List<ServiceEventAttendanceResponseEvent> eventsAttended = new ArrayList<ServiceEventAttendanceResponseEvent>();
	    if (calendarEvents != null)
	    {
		Collections.sort((List) calendarEvents);
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
		    
		    if(programReferral.getExistingReferrals() != null && programReferral.getExistingReferrals().size() > 0){			
			
			Iterator<ServiceEventAttendanceResponseEvent> iterExistingRef = programReferral.getExistingReferrals().iterator();
				
			while(iterExistingRef.hasNext()){
				
				ServiceEventAttendanceResponseEvent attendanceEvent = (ServiceEventAttendanceResponseEvent)iterExistingRef.next();
				if(attendanceEvent != null){
				    
				    if((attendanceEvent.getAttendanceStatusCd() != null && attendanceEvent.getAttendanceStatusCd().equals("AT")) && 
					(attendanceEvent.getProgramReferralId() != null && attendanceEvent.getProgramReferralId().equals(event.getProgramReferralId()) && 
						(attendanceEvent.getServiceEventId() != null && attendanceEvent.getServiceEventId().equals(event.getEventId())))){
					
					creditHours = (float) UIProgramReferralHelper.calculateTimeDiff(attendanceEvent.getStartDateTime(), attendanceEvent.getEndDateTime());
				    	attendanceEvent.setCreditHours(creditHours);
        				totalCreditHours += creditHours; 
        				
        				eventsAttended.add(attendanceEvent);
        				
        				break;
				    } 
				    
				    
				    if((attendanceEvent.getAttendanceStatusCd() != null && !attendanceEvent.getAttendanceStatusCd().equals("AT")) && 
						(attendanceEvent.getProgramReferralId() != null && attendanceEvent.getProgramReferralId().equals(event.getProgramReferralId()) && 
							(attendanceEvent.getServiceEventId() != null && attendanceEvent.getServiceEventId().equals(event.getEventId())))){
	            				
	        			eventsAttended.add(attendanceEvent);
	            				
	            			break;
				    } 
				    
				}
													
			    }		
					
		    }
		}
		
		programReferral.setTotalCreditHours(totalCreditHours);
		programReferral.setExistingReferrals(eventsAttended);
		programReferral.setJuvenileEvents(activeCalendarEvents);
	    }

	    GetJuvenileProgramReferralAssignmentHistoryEvent gjprahe = (GetJuvenileProgramReferralAssignmentHistoryEvent) EventFactory.getInstance(JuvenileProgramReferralControllerServiceNames.GETJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY);

	    gjprahe.setProgramReferralId(programReferral.getReferralId());

	    compositeResponse = (CompositeResponse) MessageUtil.postRequest(gjprahe);
	    List prAssignmentHistory = (List) MessageUtil.compositeToCollection(compositeResponse, ProgramReferralAssignmentHistoryResponseEvent.class);
	    //Task 39399 

	    if (prAssignmentHistory != null)
	    {
		int size = prAssignmentHistory.size();
		Iterator<ProgramReferralAssignmentHistoryResponseEvent> prAssignHistoryItr = prAssignmentHistory.iterator();
		List<ProgramReferralAssignmentHistoryResponseEvent> prAssignmentHistoryNew = new ArrayList<ProgramReferralAssignmentHistoryResponseEvent>();
		while (prAssignHistoryItr.hasNext())
		{

		    ProgramReferralAssignmentHistoryResponseEvent prAssgnHistory = prAssignHistoryItr.next();
		    String superVisionID = prAssgnHistory.getCasefileId();

		    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
		    getCasefileEvent.setSupervisionNumber(superVisionID);
		    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
		    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);

		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		    prAssgnHistory.setCasefileAssignDate(sdf.format(casefile.getAssignmentDate()));
		    if (casefile.getClosingDate() != null)
		    {
			prAssgnHistory.setCasefileEndDate(sdf.format(casefile.getClosingDate()));
		    }
		    if (casefile.getControllingReferral() != null && !casefile.getControllingReferral().isEmpty())
		    {
			prAssgnHistory.setCntrlRefNum(casefile.getControllingReferral());
		    }
		    else
			if (casefile.getControllingReferralId() != null && !casefile.getControllingReferralId().isEmpty())
			{
			    prAssgnHistory.setCntrlRefNum(casefile.getControllingReferralId());
			}
			else
			{
			    prAssgnHistory.setCntrlRefNum(UIProgramReferralHelper.getLowestCtrlngRefNbr((String) casefile.getSupervisionNum()));
			}

		    prAssignmentHistoryNew.add(prAssgnHistory);
		}
		Collections.sort((List) prAssignmentHistoryNew);
		programReferral.setPrAssignmentHistoryList(prAssignmentHistoryNew);
	    }

//	    GetProgramAttendanceEvent gpae = (GetProgramAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETPROGRAMATTENDANCE);
//
//	    gpae.setProgramId(programReferral.getProviderProgramId());
//	    gpae.setJuvenileNum(programReferral.getJuvenileId());
//
//	    compositeResponse = (CompositeResponse) MessageUtil.postRequest(gpae);
//
//	    List attendanceEvents = (List) MessageUtil.compositeToCollection(compositeResponse, ServiceEventAttendanceResponseEvent.class);
//
//	    if (attendanceEvents != null)
//	    {
//		Collections.sort((List) attendanceEvents);
//		programReferral.setExistingReferrals(attendanceEvents);
//	    }

	}
	form.setProgramReferral(programReferral);
	form.setAction(UIConstants.VIEW);
	ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
	return forward;
    }

    /* (non-Javadoc)
     * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
     */
    protected void addButtonMapping(Map keyMap)
    {
	keyMap.put("button.link", "displayReferralDetails");
	keyMap.put("button.details", "showEventDetails");
    }
}

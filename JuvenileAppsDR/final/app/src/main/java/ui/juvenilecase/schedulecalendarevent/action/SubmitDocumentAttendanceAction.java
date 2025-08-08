//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\schedulecalendarevent\\action\\DisplayCalendarEventListAction.java

package ui.juvenilecase.schedulecalendarevent.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.LookupDispatchAction;

import messaging.calendar.SaveJuvenileAttendanceEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;
import pd.supervision.calendar.ServiceEvent;
import ui.juvenilecase.workshopcalendar.form.CalendarEventListForm;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;

public class SubmitDocumentAttendanceAction extends LookupDispatchAction
{
    /**
     * @roseuid 4576E78400F1
     */
    public SubmitDocumentAttendanceAction()
    {
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;

	if (form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED) || form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT))
	{ // Excused or Absent, so clear out these values
	    clearExtraValues(form);
	}
	form.setAction("attendanceSummary");

	return (aMapping.findForward(UIConstants.NEXT));
    }

    /* 
     * if the attend status is excused or absent, clear out
     * the values that don't make sense.
     */
    private void clearExtraValues(CalendarEventListForm form)
    {
	if (form.getSelectedAttendeeNames() != null)
	{
	    String[] mystr = new String[0];
	    form.setSelectedAttendeeNames(mystr);
	}

	if (form.getSelectedNamesList() != null)
	{
	    form.getSelectedNamesList().clear();
	}

	if (form.getAddlAttendees() != null)
	{
	    form.setAddlAttendees(UIConstants.EMPTY_STRING);
	}
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 447F49A50033
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	CalendarEventListForm form = (CalendarEventListForm) aForm;

	SaveJuvenileAttendanceEvent saveAttendanceEvent = (SaveJuvenileAttendanceEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.SAVEJUVENILEATTENDANCE);

	saveAttendanceEvent.setJuvenileId(form.getJuvenileNum());
	saveAttendanceEvent.setAttendanceStatusCd(form.getAttendanceStatusCd());
	saveAttendanceEvent.setProgressNotes(form.getProgressNotes());

	if (form.getCurrentEvent() != null)
	{
	    saveAttendanceEvent.setServiceEventId(form.getCurrentEvent().getEventId());
	    saveAttendanceEvent.setEventStartDate(form.getCurrentEvent().getStartDatetime());
	    saveAttendanceEvent.setEventCategory(form.getCurrentEvent().getEventTypeCategory());
	    saveAttendanceEvent.setCurrentEvent(form.getCurrentEvent());
	    // add CalendarEventListForm.currentevent.serviceprovidername,serviceProvideInhouse and servicelocation or pass currentevent and then access inside save
	}
	else
	{
	    ActionErrors errors = new ActionErrors();
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Current Event is invalid; please re-select the Event from the Calendar."));
	    saveErrors(aRequest, errors);

	    return aMapping.findForward(UIConstants.CANCEL);
	}

	if (form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED) || form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT))
	{ // Excused or Absent, so clear out these values
	    clearExtraValues(form);
	}
	else
	{
	    String str = form.getAddlAttendees().trim();
	    if (str != null && (str.length() > 0))
	    {
		try
		{
		    saveAttendanceEvent.setAddlAttendees(Integer.parseInt(str));
		}
		catch (NumberFormatException nfe)
		{
		    saveAttendanceEvent.setAddlAttendees(0);
		}
	    }
	}

	List attendeeNames = saveAttendanceEvent.getAddlAttendeeNames();
	attendeeNames.clear();
	if (form.getSelectedNamesList() != null)
	{
	    attendeeNames.addAll(form.getSelectedNamesList());
	}
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(saveAttendanceEvent);

	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	Map dataMap = MessageUtil.groupByTopic(compositeResponse);
	MessageUtil.processReturnException(dataMap);
	form.setAction("attendanceConfirm");
	String forward = "confirm";

	if (form.getSecondaryAction().equalsIgnoreCase("workshopCalendar"))
	{
	    forward = "addjuveniletoEvent";	    
	    ScheduleNewEventForm frm = new ScheduleNewEventForm();
	    frm.setMaxyouthLimit("false");
	    frm.setAction("attendanceConfirm");
	    ServiceEvent serv = (ServiceEvent)ServiceEvent.find( form.getCurrentEvent().getEventId() );
	    //aRequest.setAttribute("pageType", "attendancedocumented");
	    if (form.getCurrentEvent().getMaxAttendance() != null && form.getCurrentEvent().getCurrentEnrollment() != null)
	    {
		int maxAttendance = serv.getEventMaximum(); //Integer.parseInt(form.getCurrentEvent().getMaxAttendance());
		int currentEnrollment = serv.getCurrentEnrollment();
			//Integer.parseInt(form.getCurrentEvent().getCurrentEnrollment());
		/*if (maxAttendance == currentEnrollment)
		    frm.setMaxyouthLimit("true");*/
		if (maxAttendance > currentEnrollment)
		    aRequest.setAttribute("pageType", "maxYouthLimit");
		else
		    aRequest.setAttribute("pageType", "");
	    }
	}

	return (aMapping.findForward(forward));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.BACK));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward returnToCalendar(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.RETURN_SUCCESS));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward documentAttendance(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.VIEW_DOCUMENT_ATTENDANCE));
    }

    /*
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return
     */
    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
    {
	return (aMapping.findForward(UIConstants.CANCEL));
    }

    /*
     * (non-Javadoc)
     * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
     */
    protected Map getKeyMethodMap()
    {
	Map buttonMap = new HashMap();
	buttonMap.put("button.back", "back");
	buttonMap.put("button.cancel", "cancel");
	buttonMap.put("button.returnToCalendar", "returnToCalendar");
	buttonMap.put("button.documentAttendance", "documentAttendance");
	buttonMap.put("button.next", "next");
	buttonMap.put("button.finish", "finish");

	return buttonMap;
    }
}

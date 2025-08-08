package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.CreateCalendarServiceEventEvent;
import messaging.calendar.CreateScheduleCalendarEventsEvent;
import messaging.calendar.SaveJuvenileAttendanceEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.casefile.CreateActivityEvent;
import messaging.codetable.GetServiceTypeCdByGroupEvent;
import messaging.codetable.criminal.reply.ServiceTypeCdResponseEvent;
import messaging.interviewinfo.AddInterviewSummaryNotesEvent;
import messaging.interviewinfo.CompleteInterviewEvent;
import messaging.interviewinfo.CreateInterviewEvent;
import messaging.interviewinfo.SaveInterviewEvent;
import messaging.interviewinfo.reply.InterviewPersonResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InterviewConstants;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.PDCalendarConstants;
import naming.PDJuvenileCaseConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIJuvenileCaseworkHelper;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm.PersonResponsible;

/**
 * @author awidjaja
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitJuvInterviewCreateAction extends JIMSBaseAction {
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.continueToInterviewWizard",
				"continueToInterviewWizard");
		keyMap.put("button.continueToClassicInterview",
				"continueToClassicInterview");
		keyMap.put("button.updateSummaryNotes", "updateSummaryNotes");
		keyMap.put("button.back", "back");
	}

	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward updateSummaryNotes(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;

		form.setAction(UIConstants.UPDATE_SUMMARY);

		return (aMapping.findForward(UIConstants.UPDATE_SUMMARY));
	}

	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward continueToInterviewWizard(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;

		form.setWizardInterviewType(true);

		return (aMapping.findForward("interviewWizard"));
	}

	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward continueToClassicInterview(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		JuvenileInterview juvInterview = form.getCurrentInterview();

		form.setWizardInterviewType(false);

		CompleteInterviewEvent event = new CompleteInterviewEvent();

		if (juvInterview != null) {
			event.setInterviewId(juvInterview.getInterviewId());
		}

		IDispatch dispatch = EventManager
				.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(event);

		// Get PD Response Event
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		MessageUtil.processReturnException(response); // Perform Error handling

		ActionForward newFwd = getNewForward(aMapping
				.findForward("classicInterview"),
				((JuvenileInterviewForm) aForm).getJuvenileNum());

		return (newFwd);
	}

	/**
	 * @param forward
	 * @param form
	 * @return
	 */
	private ActionForward getNewForward(ActionForward forward, String juvNum) {
		StringBuffer sb = new StringBuffer(forward.getPath()).append("?")
				.append(PDJuvenileCaseConstants.JUVENILENUM_PARAM).append("=")
				.append(juvNum);

		return (new ActionForward(sb.toString()));
	}

	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		JuvenileInterview interview = form.getCurrentInterview();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (form.getAction().equals(UIConstants.CREATE) || 
				form.getAction().equals("recordSumm")) {
			CreateInterviewEvent event = new CreateInterviewEvent();
			event.setCasefileId(form.getCasefileId());
			event.setInterviewDate(interview.getInterviewDate());
			
			List persons = event.getInterviewPersons();
			persons.clear();
			if (form.getCurrentInterview().getSelectedPersonsInterviewed() != null) {				
					persons.addAll(form.getCurrentInterview().getSelectedPersonsInterviewed());			
			}

			{
				List records = event.getInventoryRecordsIds();
				records.clear();
				records.addAll(Arrays.asList(interview.getRecordsInventoryIds()));
				records.remove("other"); // this is UI-related only, not to be saved
			}

			event.setOtherInventoryRecords(interview.getOtherInventoryRecords());
			event.setInterviewTypeId(interview.getInterviewTypeId());
			event.setSummaryNotes(interview.getSummaryNote());
			event.setUserComments(interview.getUserComments());

			if (interview.getJuvLocUnitId().equals("newaddress")) {
				interview.getNewAddress().setAddressStatus(form.getAddressStatus());
				event.setAddress(interview.getNewAddress());
			} else {
				event.setLocationDescription(interview.getJuvLocUnitDescription());
				event.setJuvLocUnitId(interview.getJuvLocUnitId());

				/*
				 * set address to null so that a new entry won't be created for
				 * custom address
				 */
				event.setAddress(null);
			}

			/*
			 * This should be changed to set it to incomplete for scheduled
			 * interview and complete for unscheduled interviews.
			 */
			event.setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_COMPLETE);
			interview.setInterviewStatusCd(InterviewConstants.INTERVIEW_STATUS_COMPLETE);

			if (!createCalendarEvent(aRequest, event, interview, form)) {
				sendToErrorPage(aRequest, "error.interviewCreationFailed");
				return (aMapping.findForward(UIConstants.FAILURE));
			}
		} else {
			if (form.getAction().equals(UIConstants.UPDATE_SUMMARY)) {
				String interviewID = interview.getInterviewId();
				if (interviewID == null || interviewID.trim().length() == 0) {
					sendToErrorPage(aRequest, "error.interviewCreationFailed","Interview ID is missing.");
					return (aMapping.findForward(UIConstants.FAILURE));
				}
				AddInterviewSummaryNotesEvent saveSummaryNotesEvent = (AddInterviewSummaryNotesEvent) EventFactory
						.getInstance(JuvenileInterviewInfoControllerServiceNames.ADDINTERVIEWSUMMARYNOTES);
				saveSummaryNotesEvent.setInterviewId(interviewID);
				saveSummaryNotesEvent.setSummaryNotes(interview.getSummaryNote());
				
				dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(saveSummaryNotesEvent);

				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);
			} else {
				String intID = interview.getInterviewId();
				if (intID == null || intID.trim().length() == 0) {
					sendToErrorPage(aRequest, "error.interviewCreationFailed","Interview ID is missing.");
					return (aMapping.findForward(UIConstants.FAILURE));
				}

				SaveInterviewEvent event = new SaveInterviewEvent();
				event.setInterviewId(intID);
				event.setCasefileId(form.getCasefileId());
				event.setInterviewDate(interview.getInterviewDate());
				event.setCalEventId(interview.getCalEventId());
				event.setJuvenileNum(form.getJuvenileNum());

				{
					List records = event.getInventoryRecordsIds();
					records.clear();
					String[] strArr = interview.getRecordsInventoryIds();
					if (strArr != null) {
						records.addAll(Arrays.asList(strArr));
					}
				}

				event.setOtherInventoryRecords(interview.getOtherInventoryRecords());
				event.setSummaryNotes(interview.getSummaryNote());
				event.setUserComments(interview.getUserComments());
				event.setProgressReport(interview.getProgressReport());

				{
					String locUnit = interview.getJuvLocUnitId().trim();
					if (locUnit.equals("newaddress")) {
						event.setAddress(interview.getNewAddress());
					} else {
						event.setJuvLocUnitId(locUnit);
						event.setLocationDescription(interview.getJuvLocUnitDescription());

						// set address to null so that a new entry wouldnt be
						// created for custom address
						event.setAddress(null);
					}
				}

				/*
				 * This should be changed to set it to incomplete for scheduled
				 * interview and complete for unscheduled interviews.
				 */
				event.setInterviewStatusCd(interview.getInterviewStatusCd());
				dispatch.postEvent(event);

				// Get PD Response Event
				CompositeResponse response = (CompositeResponse) dispatch.getReply();
				MessageUtil.processReturnException(response); // Perform Error handling

				// <KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
				if (form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_EXCUSED)
						|| form.getAttendanceStatusCd().equalsIgnoreCase(PDCalendarConstants.JUV_ATTEND_STATUS_ABSENT)) { 
					// Excused or Absent, so clear out these values
					clearExtraValues(form);
				}

				if (form.getServiceEventId() == null || form.getServiceEventId().equals("")) {
					ActionMessage myError=new ActionMessage("error.casefileUnavailable",form.getCasefileId());
					ArrayList coll=new ArrayList();
					coll.add(myError);
					sendToErrorPage(aRequest,coll);
					return aMapping.findForward(UIConstants.CANCEL);
				} 
				
				SaveJuvenileAttendanceEvent saveAttendanceEvent = (SaveJuvenileAttendanceEvent) EventFactory
						.getInstance(ServiceEventControllerServiceNames.SAVEJUVENILEATTENDANCE);
				saveAttendanceEvent.setJuvenileId(form.getJuvenileNum());
				saveAttendanceEvent.setServiceEventId(form.getServiceEventId());
				saveAttendanceEvent.setAttendanceStatusCd(form.getAttendanceStatusCd());
				saveAttendanceEvent.setEventCategory(UIConstants.INTERVIEW_SERVICE_TYPE);
				saveAttendanceEvent.setEventStartDate(interview.getInterviewDate());
				saveAttendanceEvent.setProgressNotes(interview.getProgressReport());
				saveAttendanceEvent.setAppendProgressNotes(false);
				if (StringUtils.isNotEmpty(form.getAddlAttendees())) {
					try {
						saveAttendanceEvent.setAddlAttendees(Integer.parseInt(form.getAddlAttendees()));
					} catch (NumberFormatException nfe) {
						saveAttendanceEvent.setAddlAttendees(0);
					}
				}

				List attendeeNames = saveAttendanceEvent.getAddlAttendeeNames();
				attendeeNames.clear();
				if (form.getSelectedAttendeeNames() != null) {
					attendeeNames.addAll(form.getSelectedNamesList());
				}
				dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(saveAttendanceEvent);

				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);
			}
		}

		// <KISHORE>JIMS200059459 : Display Interview Record inventory (PD) - KK
		if (!form.getAction().equals(UIConstants.UPDATE_SUMMARY)
				&& interview.getRecordsInventoryDisplay() != null
				&& !interview.getRecordsInventoryDisplay().isEmpty()) {
			CreateActivityEvent reqEvent = (CreateActivityEvent) EventFactory.getInstance(JuvenileCasefileControllerServiceNames.CREATEACTIVITY);
			if (reqEvent != null) {
				JuvenileCasefileForm juvenileCasefileForm = (JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
				if (juvenileCasefileForm != null) {
					if (interview.getCasefileId() != null && interview.getCasefileId().length() > 0) {
						reqEvent.setSupervisionNumber(interview.getCasefileId());
					} else {
						reqEvent.setSupervisionNumber(juvenileCasefileForm.getSupervisionNum());
					}
				}
				reqEvent.setActivityDate(new Date());
				reqEvent.setActivityCategoryId(UIConstants.ACTIVITY_CATEGORY_REPORTING);
				reqEvent.setActivityTypeId(UIConstants.ACTIVITY_TYPE_CASE_MANAGEMENT);
				reqEvent.setActivityCodeId(UIConstants.ACTIVITY_CODE_DOR);
				StringBuffer comments = new StringBuffer(UIConstants.EMPTY_STRING);

				Iterator<String> records = interview.getRecordsInventoryDisplay().iterator();
				while (records.hasNext()) {
					comments.append(records.next()).append("; ");
				}
				reqEvent.setComments(comments.toString());
				MessageUtil.postRequest(reqEvent);
			}
		}

		form.setStatus(UIConstants.CONFIRM);
		form.setAction("updateSummaryNotes");
		aRequest.setAttribute(UIConstants.ACTION, "updateSummaryNotes");
		aRequest.setAttribute("status", UIConstants.CONFIRM);

		return (aMapping.findForward(UIConstants.SUCCESS));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#cancel(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ui.action.JIMSBaseAction#back(org.apache.struts.action.ActionMapping,
	 * org.apache.struts.action.ActionForm,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		
		JuvenileInterviewForm form = (JuvenileInterviewForm)aForm ;
		//Bug #67710
		if(form.getAction().equals("recordSumm"))
		{
			form.setAction("record");
			System.out.println("back create");
			return aMapping.findForward(UIConstants.BACK_CREATE);
		}
		else
			return aMapping.findForward(UIConstants.BACK);
	}

	/*
	 * @param aRequest
	 * 
	 * @param createInterviewEvent
	 * 
	 * @param juvInterview
	 * 
	 * @return
	 */
	private boolean createCalendarEvent(HttpServletRequest aRequest,
			CreateInterviewEvent createInterviewEvent,
			JuvenileInterview juvInterview, JuvenileInterviewForm interviewForm) {
		JuvenileCasefileForm form = UIJuvenileCaseworkHelper.getHeaderForm(aRequest);
		GetServiceTypeCdByGroupEvent getSvcTypeEvent = new GetServiceTypeCdByGroupEvent();
		getSvcTypeEvent.setCodeTableName(UIConstants.SERVICE_TYPE);
		getSvcTypeEvent.setGroup(UIConstants.INTERVIEW_SERVICE_TYPE);
		CompositeResponse response = postRequestEvent(getSvcTypeEvent);

		String serviceTypeId = null;
		List<ServiceTypeCdResponseEvent> serviceTypes = MessageUtil.compositeToList(response, ServiceTypeCdResponseEvent.class);
		for (ServiceTypeCdResponseEvent stre : serviceTypes) {
			if (stre.getServiceTypeCode().equals(UIConstants.INTERVIEW_UNSCHEDULED_CODE)) {
				serviceTypeId = stre.getServiceTypeId();
				break;
			}
		}

		if (serviceTypeId == null) { // error
			return false;
		}

		CreateCalendarServiceEventEvent calendarServiceEvent = new CreateCalendarServiceEventEvent();
		calendarServiceEvent.setEventTypeId(serviceTypeId);
		calendarServiceEvent.setEventStatusId(PDCalendarConstants.SERVICE_EVENT_STATUS_AVAILABLE);
		calendarServiceEvent.setEventTypeCategory("I");
		calendarServiceEvent.setEventComments(createInterviewEvent.getUserComments());
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setProbationOfficerId(form.getProbationOfficerId());
		calendarContextEvent.setJuvenileId(form.getJuvenileNum());
		calendarContextEvent.setCaseFileId(form.getSupervisionNum());
		calendarServiceEvent.setCalendarContextEvent(calendarContextEvent);

		{
			ArrayList scheduledEvents = new ArrayList();
			CalendarServiceEventResponseEvent cre = new CalendarServiceEventResponseEvent();
			cre.setStartDatetime(createInterviewEvent.getInterviewDate());
			cre.setEndDatetime(createInterviewEvent.getInterviewDate());
			scheduledEvents.add(cre);
			calendarServiceEvent.setEvents(scheduledEvents);
		}

		calendarServiceEvent.setLocationId(createInterviewEvent.getJuvLocUnitId());
		calendarServiceEvent.setProgressNotes(juvInterview.getProgressReport());

		// <KISHORE>JIMS200059211 : MJCW: Document attendance for all int.types(UI)-KK
		if (StringUtils.isNotEmpty(interviewForm.getAddlAttendees())) {
			calendarServiceEvent.setAddlAttendees(Integer.parseInt(interviewForm.getAddlAttendees()));
		}
		calendarServiceEvent.setAdditionalAttendeeNames(interviewForm.getSelectedNamesList());

		CreateScheduleCalendarEventsEvent createEvent = (CreateScheduleCalendarEventsEvent) EventFactory
				.getInstance(ServiceEventControllerServiceNames.CREATESCHEDULECALENDAREVENTS);
		createEvent.setCreateInterviewEvent(createInterviewEvent);
		createEvent.setCreateCalendarEvent(calendarServiceEvent);

		response = postRequestEvent(createEvent);

		CalendarServiceEventResponseEvent cseResponseEvent = (CalendarServiceEventResponseEvent) MessageUtil
				.filterComposite(response,	CalendarServiceEventResponseEvent.class);

		if (cseResponseEvent == null || cseResponseEvent.getCalendarEventId() == null) {
			sendToErrorPage(aRequest, "error.interviewCreationFailed");
			return false;
		}

		juvInterview.setInterviewId(cseResponseEvent.getInterviewId());
		return (true);
	}

	/*
	 * if the attend status is excused or absent, clear out the values that
	 * don't make sense.
	 */
	private void clearExtraValues(JuvenileInterviewForm form) {
		if (form.getSelectedAttendeeNames() != null) {
			String[] mystr = new String[0];
			form.setSelectedAttendeeNames(mystr);
		}

		if (form.getSelectedNamesList() != null) {
			form.getSelectedNamesList().clear();
		}

		if (StringUtils.isNotEmpty(form.getAddlAttendees())) {
			form.setAddlAttendees(UIConstants.EMPTY_STRING);
		}

		if (StringUtils.isNotEmpty(form.getSelectedAttendeeNamesAsString())) {
			form.setSelectedAttendeeNamesAsString(UIConstants.EMPTY_STRING);
		}
	}
	/**
	* @param aRequest
	*/
   private void sendToErrorPage(HttpServletRequest aRequest, Collection aActionErrors)
   {
		ActionErrors errors = new ActionErrors();
		if(aActionErrors!=null && aActionErrors.size()>0){
			Iterator i=aActionErrors.iterator();
			while(i.hasNext()){
				ActionMessage error=(ActionMessage)i.next();
				errors.add(ActionErrors.GLOBAL_MESSAGE,error);
			}
		   saveErrors(aRequest, errors);
		}
   }	
}
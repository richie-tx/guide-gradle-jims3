//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\form\\CSCalendarOtherForm.java

package ui.supervision.cscdcalendar.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import messaging.codetable.reply.CodeResponseEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.StringUtil;
import ui.security.SecurityUIHelper;

public class CSCalendarOtherForm extends ActionForm {
	public static final String[] FV_OUTCOME_USER_SELECTABLE = { "CO", "IC" };

	// Default Elements in all forms
	private static List emptyColl = new ArrayList();

	private String eventId;

	private String eventName;

	private Date eventDate;

	private String endTime;

	private String startTime;

	private String purpose;

	private String narrative;
	
	private List filteredEvents;
	
	private List selectedEvents;	

	private Collection events;

	private String eventTypeCd;

	private String eventTypeDescription;

	private String otherEventTypeName;

	private String outcomeCd;

	private String statusCd;
	
	private String AMPMId1;

	private String AMPMId2; 
	

	// UI Related controls
	private String activityFlow;

	private String context; // context, whether it is Position (Officer) or
	// Supervisee (P/S)

	private String selectedEventId; // Id of the selected event by the user

	private String positionId; // The position of the current logged-on user

	private String superviseeId; // Supervisee Id exist only when creating
	// from supervisee calendar

	private String agencyId;

	/**
	 * @roseuid 47A2384A0377
	 */
	public CSCalendarOtherForm() {
		events = emptyColl;
		agencyId = SecurityUIHelper.getUserAgencyId();
	}

	// This method will clear out the form for new data entries
	public void clear() {

		eventName = null;
		startTime = null;
		endTime = null;
		purpose = null;
		// Dont clear eventDate, eventTypeCd & positionId since
		// it is being passed from the previous page

		events = emptyColl;
		activityFlow = null;
		selectedEventId = null;
		outcomeCd = null;

		narrative = null;
		statusCd = null;
		filteredEvents = null;
		selectedEvents = null;
		
		AMPMId1 ="";
		AMPMId2 ="";	
	}

	/**
	 * @return Returns the endTime.
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            The endTime to set.
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return Returns the events.
	 */
	public Collection getEvents() {
		return events;
	}

	/**
	 * @param events
	 *            The events to set.
	 */
	public void setEvents(Collection events) {
		this.events = events;
	}

	/**
	 * @return Returns the context.
	 */
	public String getContext() {
		return context;
	}

	
	public String getAMPMId1() {
		return AMPMId1;
	}

	public void setAMPMId1(String id1) {
		AMPMId1 = id1;
	}
	/**
	 * @param context
	 *            The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}

	/**
	 * @return Returns the eventName.
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            The eventName to set.
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return Returns the outcomeCd.
	 */
	public String getOutcomeCd() {
		return outcomeCd;
	}

	/**
	 * @param outcomeCd
	 *            The outcomeCd to set.
	 */
	public void setOutcomeCd(String outcomeCd) {
		this.outcomeCd = outcomeCd;
	}

	/**
	 * @return Returns the narrative.
	 */
	public String getNarrative() {
		return narrative;
	}

	/**
	 * @param narrative
	 *            The narrative to set.
	 */
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}

	/**
	 * @return Returns the purpose.
	 */
	public String getPurpose() {
		return purpose;
	}

	/**
	 * @param purpose
	 *            The purpose to set.
	 */
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	/**
	 * @return Returns the startTime.
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            The startTime to set.
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEventDateStr(String dateStr) {
		eventDate = DateUtil.stringToDate(dateStr, DateUtil.DATE_FMT_1);
	}

	public String getEventDateStr() {
		return DateUtil.dateToString(eventDate, DateUtil.DATE_FMT_1);
	}

	/**
	 * @return Returns the activityFlow.
	 */
	public String getActivityFlow() {
		return activityFlow;
	}

	/**
	 * @param activityFlow
	 *            The activityFlow to set.
	 */
	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}

	/**
	 * @return Returns the eventTypeCd.
	 */
	public String getEventTypeCd() {
		return eventTypeCd;
	}

	/**
	 * @param eventTypeCd
	 *            The eventTypeCd to set.
	 */
	public void setEventTypeCd(String eventTypeCd) {
		this.eventTypeCd = eventTypeCd;
	}

	public String getEventTypeDesc() {
		List eventTypeList = ComplexCodeTableHelper.getEventTypesByContext("P",
				true);
		for (Iterator iter = eventTypeList.iterator(); iter.hasNext();) {
			CSEventTypeResponseEvent eventType = (CSEventTypeResponseEvent) iter
					.next();
			if (eventType.getEventTypeId().equals(eventTypeCd)) {
				return eventType.getDescription();
			}
		}
		return null;
	}

	/**
	 * @return Returns the selectedEventId.
	 */
	public String getSelectedEventId() {
		return selectedEventId;
	}

	/**
	 * @param selectedEventId
	 *            The selectedEventId to set.
	 */
	public void setSelectedEventId(String selectedEventId) {
		this.selectedEventId = selectedEventId;
	}

	/**
	 * @return Returns the eventId.
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            The eventId to set.
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setCalendardate(String date) {
		eventDate = new Date(Long.parseLong(date));
	}
	
	public List getFilteredEvents() {
		return filteredEvents;
	}

	public void setFilteredEvents(List filteredEvents) {
		this.filteredEvents = filteredEvents;
	}
	
	public List getSelectedEvents() {
		return selectedEvents;
	}

	public void setSelectedEvents(List selectedEvents) {
		this.selectedEvents = selectedEvents;
	}

	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId
	 *            The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public void populate(CSOtherResponseEvent responseEvent) {
		this.eventTypeCd = responseEvent.getEventType();
		this.eventDate = responseEvent.getEventDate();
		this.eventId = responseEvent.getEventId();
		this.eventName = responseEvent.getEventName();
		this.purpose = responseEvent.getPurpose();
		this.superviseeId = responseEvent.getSuperviseeId(); 
		if(!StringUtil.isEmpty(responseEvent.getStartTime())){
			if(responseEvent.getStartTime().length()>5){
				this.startTime = responseEvent.getStartTime().substring(0, 5);
				this.AMPMId1 = responseEvent.getStartTime().substring(5).trim();
			}
		}
		else{
		this.startTime = responseEvent.getStartTime();
		this.AMPMId1 ="";
		}
		if(!StringUtil.isEmpty(responseEvent.getEndTime())){
			if(responseEvent.getEndTime().length()>5){
			this.endTime = responseEvent.getEndTime().substring(0, 5);
			this.AMPMId2 = responseEvent.getEndTime().substring(5).trim();
			}
		}
		else{
			this.endTime = responseEvent.getEndTime();
			this.AMPMId2 ="";
		}
		this.statusCd = responseEvent.getStatus();
		if (responseEvent.getNarrative() == null) {
			this.narrative = responseEvent.getPurpose() != null ? responseEvent.getPurpose() : "";
		} else {
			this.narrative = responseEvent.getNarrative();
		}
		this.outcomeCd = responseEvent.getOutcome();

	}

	public String getAMPMId2() {
		return AMPMId2;
	}

	public void setAMPMId2(String id2) {
		AMPMId2 = id2;
	}

	/**
	 * @return Returns the statusCd.
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd
	 *            The statusCd to set.
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	private List getFVOutcomeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId,
				PDCodeTableConstants.FV_OUTCOME);
	}

	public String getOutcomeDesc() {
		List ovOutcomeList = getFVOutcomeList();
		return ComplexCodeTableHelper.getDescrBySupervisionCode(ovOutcomeList,
				outcomeCd);
	}

	public List getFilteredFVOutcomeList() {
		List ovOutcomeList = getFVOutcomeList();
		List tobeRemoved = new ArrayList();

		List selectableList = Arrays.asList(FV_OUTCOME_USER_SELECTABLE);

		for (Iterator iter = ovOutcomeList.iterator(); iter.hasNext();) {
			CodeResponseEvent code = (CodeResponseEvent) iter.next();

			if (!selectableList.contains(code.getSupervisionCode())) {
				tobeRemoved.add(code);
			}
		}
		ovOutcomeList.removeAll(tobeRemoved);

		return ovOutcomeList;
	}

	/**
	 * @return Returns the otherEventTypeName.
	 */
	public String getOtherEventTypeName() {
		return otherEventTypeName;
	}

	/**
	 * @param otherEventTypeName
	 *            The otherEventTypeName to set.
	 */
	public void setOtherEventTypeName(String otherEventTypeName) {
		this.otherEventTypeName = otherEventTypeName;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	public String getAgencyId() {
		return agencyId;
	}

	public String getEventTypeDescription() {
		return eventTypeDescription;
	}

	public void setEventTypeDescription(String eventTypeDescription) {
		this.eventTypeDescription = eventTypeDescription;
	}
}

//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\form\\CSCalendarOVForm.java

package ui.supervision.cscdcalendar.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import messaging.cscdcalendar.reply.CSOfficeVisitResponseEvent;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import ui.common.ComplexCodeTableHelper;
import ui.common.UIUtil;
import ui.supervision.cscdcalendar.CSOVBean;
import ui.supervision.cscdcalendar.cscdCalendarUIUtil;


public class CSCalendarOVForm extends ActionForm {
	
	//Office Visit
	private CSOVBean currentOfficeVisit;
	private String superviseeId;
   
	//Group Office Visit
	private List superviseeList;
	private List newSuperviseeList;
	
	//View List 
	private List officeVisits;
	private List groupOfficeVisits;
	
	//UI Related controls
	private String activityFlow;
	private String altFlow;
	private String context;
	
	private String positionId; //The position of the current logged-on user
	private String agencyId;
	private String userAgency;
	
	private String confirmMsg;

	private List eventTypeList;

	private Date eventDate;
	private String selectedEventId;
	
	private String eventTypeCd;
	
	private String[] selectedEventIds;
	private List selectedEventIdList;
	
	private String selectedGroupOVName;
	
	
	/**
	 * This method is invoked everytime the form is submitted. 
	 * Currently, it is being used to clear the list of selected 
	 * Group Office Visits in officeVisitEventsList.jsp
	 */
	public void reset(ActionMapping aMapping, HttpServletRequest aRequest) {
		if(aRequest.getParameter("reset") != null) {
			selectedEventIds = null;
			selectedEventId = null;
		}
	}
	
	
   	/**
   	 * @roseuid 47A2384B0079
   	 */
   	public CSCalendarOVForm() {
   		currentOfficeVisit = new CSOVBean();
   		superviseeList = new ArrayList();
   		newSuperviseeList = new ArrayList();
   		confirmMsg = "";
   		
   	}
   	
   	public void clear() {
		currentOfficeVisit.clear();

	}
   	
   	public String getUserAgency() {
		return UIUtil.getCurrentUserAgencyID();
	}
   	
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}

	/**
	 * @return Returns the activityFlow.
	 */
	public String getActivityFlow() {
		return activityFlow;
	}
	/**
	 * @param activityFlow The activityFlow to set.
	 */
	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}
	
	/**
	 * @return Returns the context.
	 */
	public String getContext() {
		return context;
	}
	/**
	 * @param context The context to set.
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * @return Returns the currentOfficeVisit.
	 */
	public CSOVBean getCurrentOfficeVisit() {
		return currentOfficeVisit;
	}
	/**
	 * @param currentOfficeVisit The currentOfficeVisit to set.
	 */
	public void setCurrentOfficeVisit(CSOVBean currentOfficeVisit) {
		this.currentOfficeVisit = currentOfficeVisit;
	}
	/**
	 * @return Returns the groupOfficeVisits.
	 */
	public List getGroupOfficeVisits() {
		return groupOfficeVisits;
	}
	/**
	 * @param groupOfficeVisits The groupOfficeVisits to set.
	 */
	public void setGroupOfficeVisits(List groupOfficeVisits) {
		this.groupOfficeVisits = groupOfficeVisits;
	}
	/**
	 * @return Returns the officeVisits.
	 */
	public List getOfficeVisits() {
		return officeVisits;
	}
	/**
	 * @param officeVisits The officeVisits to set.
	 */
	public void setOfficeVisits(List officeVisits) {
		this.officeVisits = officeVisits;
	}
	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}
	/**
	 * @param superviseeId The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the superviseeList.
	 */
	public List getSuperviseeList() {
		return superviseeList;
	}
	/**
	 * @param superviseeList The superviseeList to set.
	 */
	public void setSuperviseeList(List superviseeList) {
		this.superviseeList = superviseeList;
	}
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the positionId.
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId The positionId to set.
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	/**
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	/**
	 * @return Returns the selectedEventId.
	 */
	public String getSelectedEventId() {
		return selectedEventId;
	}
	/**
	 * @param selectedEventId The selectedEventId to set.
	 */
	public void setSelectedEventId(String selectedEventId) {
		this.selectedEventId = selectedEventId;
	}
	
	public void loadSelectedEvent(String selectedEventId) {
		if(officeVisits != null) {
			for(Iterator eventsIter = officeVisits.iterator();eventsIter.hasNext();) {
				CSOfficeVisitResponseEvent eventRE = 
					(CSOfficeVisitResponseEvent)eventsIter.next();
				if(eventRE.getEventId().equals(selectedEventId)) {
					this.currentOfficeVisit = new CSOVBean(eventRE);
					break;
				}
			}
		}
	}
	
	public List getFilteredOVOutcomeList() {
		return cscdCalendarUIUtil.getFilteredOVOutcomeList("CSC");
	}
	/**
	 * @return Returns the eventTypeCd.
	 */
	public String getEventTypeCd() {
		return eventTypeCd;
	}
	/**
	 * @param eventTypeCd The eventTypeCd to set.
	 */
	public void setEventTypeCd(String eventTypeCd) {
		this.eventTypeCd = eventTypeCd;
	}
	/**
	 * @return Returns the selectedEventIds.
	 */
	public String[] getSelectedEventIds() {
		return selectedEventIds;
	}
	/**
	 * @param selectedEventIds The selectedEventIds to set.
	 */
	public void setSelectedEventIds(String[] selectedEventIds) {
		this.selectedEventIds = selectedEventIds;
	}
	/**
	 * @return Returns the selectedEventIdList.
	 */
	public List getSelectedEventIdList() {
		return selectedEventIdList;
	}
	/**
	 * @param selectedEventIdList The selectedEventIdList to set.
	 */
	public void setSelectedEventIdList(List selectedEventIdList) {
		this.selectedEventIdList = selectedEventIdList;
	}
	/**
	 * @return Returns the selectedGroupOVName.
	 */
	public String getSelectedGroupOVName() {
		return selectedGroupOVName;
	}
	/**
	 * @param selectedGroupOVName The selectedGroupOVName to set.
	 */
	public void setSelectedGroupOVName(String selectedGroupOVName) {
		this.selectedGroupOVName = selectedGroupOVName;
	}

	public List getNewSuperviseeList() {
		return newSuperviseeList;
	}


	public void setNewSuperviseeList(List newSuperviseeList) {
		this.newSuperviseeList = newSuperviseeList;
	}

	/**
	 * @return Returns the eventTypeList.
	 * eventTypeList is a drop-down menu that is populated
	 * Using this list to match the code value and set the description
	 */
	public List getEventTypeList() {
		
		if ( eventTypeList == null  ){
			
			eventTypeList = ComplexCodeTableHelper.getEventTypesByContext( context, true );
		}
		return eventTypeList;
	}

	/**
	 * 
	 * @param eventTypeList
	 */
	public void setEventTypeList(List eventTypeList) {
		this.eventTypeList = eventTypeList;
	}


	/**
	 * @return the confirmMsg
	 */
	public String getConfirmMsg() {
		return confirmMsg;
	}


	/**
	 * @param confirmMsg the confirmMsg to set
	 */
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}


	public String getAltFlow() {
		return altFlow;
	}


	public void setAltFlow(String altFlow) {
		this.altFlow = altFlow;
	}

}

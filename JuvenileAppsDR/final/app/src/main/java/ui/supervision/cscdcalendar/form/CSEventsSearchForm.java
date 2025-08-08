package ui.supervision.cscdcalendar.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

public class CSEventsSearchForm extends ActionForm
{
	private String selectedOfficerNameNPosition = "";
	
	private String positionId = "";
	
	private String calendarCategory = "";
	
	private String startDateStr = "";
	
	private String endDateStr = "";
	
    private String inOutStartDateStr = "";    
    private String inOutEndDateStr = "";
	
	private String csEventsCount = "";
	
	private List csEventsList = new ArrayList();
	private List inOutActivity = new ArrayList();
	private int numInOutActivity;
	
	private String selectedCSEventId = "";
	
	private String selectedCSEventTypeId = "";
	private String officerName;
	
	
	/**
	 * 
	 */
	public void clear()
	{
		selectedOfficerNameNPosition = "";
		positionId = "";
		calendarCategory = "";
		startDateStr = "";
		endDateStr = "";
		csEventsCount = "";
		csEventsList = new ArrayList();
		inOutActivity = new ArrayList();
		selectedCSEventId = "";
		selectedCSEventTypeId = "";
	}
	
	
	/**
	 * @return the selectedOfficerNameNPosition
	 */
	public String getSelectedOfficerNameNPosition() {
		return selectedOfficerNameNPosition;
	}


	/**
	 * @param selectedOfficerNameNPosition the selectedOfficerNameNPosition to set
	 */
	public void setSelectedOfficerNameNPosition(String selectedOfficerNameNPosition) {
		this.selectedOfficerNameNPosition = selectedOfficerNameNPosition;
	}


	/**
	 * @return the positionId
	 */
	public String getPositionId() {
		return positionId;
	}


	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}


	/**
	 * @return the calendarCategory
	 */
	public String getCalendarCategory() {
		return calendarCategory;
	}

	/**
	 * @param calendarCategory the calendarCategory to set
	 */
	public void setCalendarCategory(String calendarCategory) {
		this.calendarCategory = calendarCategory;
	}

	/**
	 * @return the endDateStr
	 */
	public String getEndDateStr() {
		return endDateStr;
	}

	/**
	 * @param endDateStr the endDateStr to set
	 */
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	/**
	 * @return the startDateStr
	 */
	public String getStartDateStr() {
		return startDateStr;
	}

	/**
	 * @param startDateStr the startDateStr to set
	 */
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	/**
	 * @return the csEventsList
	 */
	public List getCsEventsList() {
		return csEventsList;
	}

	/**
	 * @param csEventsList the csEventsList to set
	 */
	public void setCsEventsList(List csEventsList) {
		this.csEventsList = csEventsList;
	}

	/**
	 * @return the csEventsCount
	 */
	public String getCsEventsCount() {
		return csEventsCount;
	}

	/**
	 * @param csEventsCount the csEventsCount to set
	 */
	public void setCsEventsCount(String csEventsCount) {
		this.csEventsCount = csEventsCount;
	}


	/**
	 * @return the selectedCSEventId
	 */
	public String getSelectedCSEventId() {
		return selectedCSEventId;
	}


	/**
	 * @param selectedCSEventId the selectedCSEventId to set
	 */
	public void setSelectedCSEventId(String selectedCSEventId) {
		this.selectedCSEventId = selectedCSEventId;
	}


	/**
	 * @return the selectedCSEventTypeId
	 */
	public String getSelectedCSEventTypeId() {
		return selectedCSEventTypeId;
	}


	/**
	 * @param selectedCSEventTypeId the selectedCSEventTypeId to set
	 */
	public void setSelectedCSEventTypeId(String selectedCSEventTypeId) {
		this.selectedCSEventTypeId = selectedCSEventTypeId;
	}


	public String getInOutStartDateStr() {
		return inOutStartDateStr;
	}


	public void setInOutStartDateStr(String inOutStartDateStr) {
		this.inOutStartDateStr = inOutStartDateStr;
	}


	public String getInOutEndDateStr() {
		return inOutEndDateStr;
	}


	public void setInOutEndDateStr(String inOutEndDateStr) {
		this.inOutEndDateStr = inOutEndDateStr;
	}


	public List getInOutActivity() {
		return inOutActivity;
	}


	public void setInOutActivity(List inOutActivity) {
		this.inOutActivity = inOutActivity;
	}


	public int getNumInOutActivity() {
		return numInOutActivity;
	}


	public void setNumInOutActivity(int numInOutActivity) {
		this.numInOutActivity = numInOutActivity;
	}


	public String getOfficerName() {
		return officerName;
	}


	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
}

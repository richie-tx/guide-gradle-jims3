//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\form\\CSCalendarFVForm.java

package ui.supervision.cscdcalendar.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.PDCodeTableConstants;
import ui.common.ComplexCodeTableHelper;
import ui.common.form.AddressValidationForm;
import ui.security.SecurityUIHelper;
import ui.supervision.cscdcalendar.CSFVBean;
import ui.supervision.cscdcalendar.CSFVItineraryBean;
import ui.supervision.managetasks.helper.AssignSuperviseeService;


public class CSCalendarFVForm extends AddressValidationForm {
	
	
	//Default Elements in all forms
	private static List emptyColl = new ArrayList();
    
	private String selectedFVEventId;
	private String currentItineraryId;
	
	private CSFVItineraryBean currentItinerary;
	private CSFVBean currentFieldVisit;
   
	//UI Related controls
	private String context;
	private String activityFlow;
	private String secondaryActivityFlow;
	
	
	private List currentEventsList;
	private List eventsList;
	private List eventsForSupervisee;
	private List filteredList;
	

	private Date currentEventDate;
	private Date eventDate;
	private String eventTypeCd;
	private String itineraryCreate;
	
	
	private String agencyId;
	private String positionId;
	private String superviseeId; //this variable is only used when context is Supervisee
	
	private String[] origSequenceNum;
	private String jobTitleCd;
	
  

/**
    * @roseuid 47A2384A0079	
    */
   	public CSCalendarFVForm() {
   		
   		agencyId = SecurityUIHelper.getUserAgencyId();
   		currentItinerary = new CSFVItineraryBean(agencyId);
   		currentFieldVisit = new CSFVBean(agencyId);
   	
   		
   	}
   	
   	public void clear() {
   		if (currentItinerary != null ) {
   			currentItinerary.clear();
   		}
   		if (currentFieldVisit != null ) {
   			currentFieldVisit.clear();
   		}
   		
   		eventsList = null;
   		eventsForSupervisee = null;
   	}
   	
   	public void clearFieldVisit() {
   		currentFieldVisit = new CSFVBean(agencyId);
   	}
   	
   	public boolean isCCO() {
   		AssignSuperviseeService helper = AssignSuperviseeService.getInstance();
   		CSCDSupervisionStaffResponseEvent staff = helper.getCSCDStaff();
   		
   		staff.getPositionTypeId();
   		
   		return false;
   	}
   	
   	
   	public String getItineraryCreate() {
		return itineraryCreate;
	}

	public void setItineraryCreate(String itineraryCreate) {
		this.itineraryCreate = itineraryCreate;
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
	 * @return Returns the secondaryActivityFlow.
	 */
	public String getSecondaryActivityFlow() {
		return secondaryActivityFlow;
	}
	/**
	 * @param secondaryActivityFlow The secondaryActivityFlow to set.
	 */
	public void setSecondaryActivityFlow(String secondaryActivityFlow) {
		this.secondaryActivityFlow = secondaryActivityFlow;
	}
	/**
	 * @return Returns the currentItinerary.
	 */
	public CSFVItineraryBean getCurrentItinerary() {
		return currentItinerary;
	}
	/**
	 * @param currentItinerary The currentItinerary to set.
	 */
	public void setCurrentItinerary(CSFVItineraryBean currentItinerary) {
		this.currentItinerary = currentItinerary;
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
	 * @return Returns the currentFieldVisit.
	 */
	public CSFVBean getCurrentFieldVisit() {
		return currentFieldVisit;
	}
	/**
	 * @param currentFieldVisit The currentFieldVisit to set.
	 */
	public void setCurrentFieldVisit(CSFVBean currentFieldVisit) {
		this.currentFieldVisit = currentFieldVisit;
	}
	
	/**
	 * over-ride the implementation of super class, so that
	 * it also sets the addressStatus at our address specific variable
	 */
	public void setAddressStatus(String addressStatus) {
		super.setAddressStatus(addressStatus);
		this.getCurrentFieldVisit().getAlternateAddress().setAddressStatus(addressStatus);
		
	}
	/**
	 * @return the currentEventsList
	 */
	public List getCurrentEventsList() {
		return currentEventsList;
	}
	/**
	 * @param currentEventsList the currentEventsList to set
	 */
	public void setCurrentEventsList(List currentEventsList) {
		this.currentEventsList = currentEventsList;
	}
	/**
	 * @return Returns the eventsList.
	 */
	public List getEventsList() {
		return eventsList;
	}
	/**
	 * @param eventsList The eventsList to set.
	 */
	public void setEventsList(List eventsList) {
		this.eventsList = eventsList;
	}
	
	public List getFilteredList() {
		return filteredList;
	}

	public void setFilteredList(List filteredList) {
		this.filteredList = filteredList;
	}
	/**
	 * @return the currentEventDate
	 */
	public Date getCurrentEventDate() {
		return currentEventDate;
	}
	/**
	 * @param currentEventDate the currentEventDate to set
	 */
	public void setCurrentEventDate(Date currentEventDate) {
		this.currentEventDate = currentEventDate;
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
		this.currentFieldVisit.setFieldVisitTypeCd(eventTypeCd);
	}
	
	public List getPurposeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.FV_PURPOSE);
	}
	
	public List getFvTypeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.FV_TYPES);
	}
	
	public List getCarTypeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.CAR_TYPE);
	}
	
	public List getSexOffenderCategoryList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.SEX_OFFENDER_TYPES);
	}
	
	public List getMeasurementTypeList() {
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.FV_MEASUREMENT_TYPES);
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
	 * @return Returns the selectedFVEventId.
	 */
	public String getSelectedFVEventId() {
		return selectedFVEventId;
	}
	/**
	 * @param selectedFVEventId The selectedFVEventId to set.
	 */
	public void setSelectedFVEventId(String selectedFVEventId) {
		this.selectedFVEventId = selectedFVEventId;
	}
	/**
	 * @return the currentItineraryId
	 */
	public String getCurrentItineraryId() {
		return currentItineraryId;
	}
	/**
	 * @param currentItineraryId the currentItineraryId to set
	 */
	public void setCurrentItineraryId(String currentItineraryId) {
		this.currentItineraryId = currentItineraryId;
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
	 * @return Returns the origSequenceNum.
	 */
	public String[] getOrigSequenceNum() {
		return origSequenceNum;
	}
	/**
	 * @param origSequenceNum The origSequenceNum to set.
	 */
	public void setOrigSequenceNum(String[] origSequenceNum) {
		this.origSequenceNum = origSequenceNum;
	}
	
	public void setEventDateStr(String dateString) {
		eventDate = DateUtil.stringToDate(dateString, DateUtil.DATE_FMT_1);
		this.getCurrentFieldVisit().setFieldVisitDate(eventDate);
	}
	
	public String getEventDateStr() {
		return DateUtil.dateToString(eventDate, DateUtil.DATE_FMT_1);
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
	 * @return Returns the eventsForSupervisee.
	 */
	public List getEventsForSupervisee() {
		return eventsForSupervisee;
	}
	/**
	 * @param eventsForSupervisee The eventsForSupervisee to set.
	 */
	public void setEventsForSupervisee(List eventsForSupervisee) {
		this.eventsForSupervisee = eventsForSupervisee;
	}
	/**
	 * @return Returns the jobTitleCd.
	 */
	public String getJobTitleCd() {
		return jobTitleCd;
	}
	/**
	 * @param jobTitleCd The jobTitleCd to set.
	 */
	public void setJobTitleCd(String jobTitleCd) {
		this.jobTitleCd = jobTitleCd;
	}
	
	public List getQuadrantList(){
		return ComplexCodeTableHelper.getSupervisionCodes(agencyId, PDCodeTableConstants.QUADRANT);
	}
	
}

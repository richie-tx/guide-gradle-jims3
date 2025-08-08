//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\form\\CSCalendarDisplayForm.java

package ui.supervision.cscdcalendar.form;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import messaging.cscdcalendar.reply.CSSpnViewConditionsResponseEvent;
import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.supervision.administercaseload.form.OfficerCaseload;


public class CSCalendarDisplayForm extends ActionForm {
	//Default Elements in all forms
    private static List emptyColl = new ArrayList();
    
	//Drop down menus
    private List divisionList;
	private List supervisorList;
	private List officerList;
	private List eventTypeList;
	
	private String selectedDivisionId;
	private String selectedSupervisorId;
	private String selectedSupervisorName;
	
	private String selectedOfficerId;
	
	private String selectedOfficerName;
	private String positionId;
	private String agencyId;
	private String jobTitleCd;
   	
	private String selectedEventTypeCd;
	private String otherEventTypeName;
	private Date eventDate;
	
	private String activityFlow; 
	private String selectedQuadrantId;
    private String quadrantDescription;
    private String quadrantSearch;
    private String soZipCode;
    private String zipCode;
	
	//UI Related controls
	private String context;
	private String superviseeId;
	private String caseSuperviseeId;
	private String[] caseSuperviseeIds;
	
	private CaseAssignmentResponseEvent searchBySPNResult;
	private CSSpnViewConditionsResponseEvent conditions;
	
	public String getSelectedSupervisorName() {
		return selectedSupervisorName;
	}

	public void setSelectedSupervisorName(String selectedSupervisorName) {
		this.selectedSupervisorName = selectedSupervisorName;
	}
	

	public String[] getCaseSuperviseeIds() {
		return caseSuperviseeIds;
	}

	public void setCaseSuperviseeIds(String[] caseSuperviseeIds) {
		this.caseSuperviseeIds = caseSuperviseeIds;
	}

	public String getCaseSuperviseeId() {
		return caseSuperviseeId;
	}

	public void setCaseSuperviseeId(String caseSuperviseeId) {
		this.caseSuperviseeId = caseSuperviseeId;
	}

	public String getTotalSuperviseesInCaseload() {
		return totalSuperviseesInCaseload;
	}

	public void setTotalSuperviseesInCaseload(String totalSuperviseesInCaseload) {
		this.totalSuperviseesInCaseload = totalSuperviseesInCaseload;
	}

	public String getTotalCasesInCaseload() {
		return totalCasesInCaseload;
	}

	public void setTotalCasesInCaseload(String totalCasesInCaseload) {
		this.totalCasesInCaseload = totalCasesInCaseload;
	}

	public List getDefendantsSupervised() {
		return defendantsSupervised;
	}

	public void setDefendantsSupervised(List defendantsSupervised) {
		this.defendantsSupervised = defendantsSupervised;
	}

	private String totalSuperviseesInCaseload;
	private String totalCasesInCaseload;
	private List defendantsSupervised;
	
    private OfficerCaseload officerCaseload;

   
	public OfficerCaseload getOfficerCaseload() {
		return officerCaseload;
	}

	public void setOfficerCaseload(OfficerCaseload officerCaseload) {
		this.officerCaseload = officerCaseload;
	}

	private String courtIdFilter;
	/**
	 * @roseuid 47A23849030A
	 */
	public CSCalendarDisplayForm() {
		eventTypeList = emptyColl;
		supervisorList = emptyColl;
		officerList = emptyColl;
		divisionList = emptyColl;
		
		eventDate = new Date();
	}
	
	public void clear() {
//		selectedEventTypeCd = "";
		eventDate = new Date();
		otherEventTypeName=null;
		caseSuperviseeId = "";
		//selectedDivisionId = "";
		selectedSupervisorId = "";
		selectedOfficerId = "";
		//positionId = "";
		officerList = null;
		defendantsSupervised = null;
		courtIdFilter = "";
		soZipCode = "";
		zipCode = "";
	}
	
	public void reset() {
		selectedEventTypeCd = "";
		eventDate = new Date();
		otherEventTypeName="";
		
	}	
	
	/**
	 * @return Returns the eventDate.
	 */
	public Date getEventDate() {
		return eventDate;
	}
	
	/**
	 * @return Returns the eventDateStr.
	 */
	public String getEventDateStr() {
		return DateUtil.dateToString(eventDate, DateUtil.DATE_FMT_1);
	}
	/**
	 * @param eventDateStr The eventDateStr to set.
	 */
	public void setEventDateStr(String eventDateStr) {
		this.eventDate = DateUtil.stringToDate(eventDateStr, DateUtil.DATE_FMT_1);
	}
	
	public void setEventDateAsLong(String eventDateAsLong) {
		eventDate = new Date(Long.parseLong(eventDateAsLong));
	}
	
	/**
	 * @return Returns the eventTypeList.
	 * eventTypeList is a drop-down menu that is populated
	 * based on the context of the use-case (officer or supervisee). 
	 * Since the codetable will be cached, there is no reason to cache 
	 * the list in the form.  
	 */
	public List getEventTypeList() {
		eventTypeList = ComplexCodeTableHelper.getEventTypesByContext(context, true);
		//sort EventType by description order
        Collections.sort((List)eventTypeList,CSEventTypeResponseEvent.DescriptionComparator);
		return eventTypeList;
	}
	/**
	 * @param eventTypeList The eventTypeList to set.
	 */
	public void setEventTypeList(List eventTypeList) {
		this.eventTypeList = eventTypeList;
	}
	/**
	 * @return Returns the officerList.
	 */
	public List getOfficerList() {
		return officerList;
	}
	/**
	 * @param officerList The officerList to set.
	 */
	public void setOfficerList(List officerList) {
		this.officerList = officerList;
	}
	/**
	 * @return Returns the otherEventTypeName.
	 */
	public String getOtherEventTypeName() {
		return otherEventTypeName;
	}
	/**
	 * @param otherEventTypeName The otherEventTypeName to set.
	 */
	public void setOtherEventTypeName(String otherEventTypeName) {
		this.otherEventTypeName = otherEventTypeName;
	}
	/**
	 * @return Returns the selectedEventTypeCd.
	 */
	public String getSelectedEventTypeCd() {
		return selectedEventTypeCd;
	}
	/**
	 * @param selectedEventTypeCd The selectedEventTypeCd to set.
	 */
	public void setSelectedEventTypeCd(String selectedEventTypeCd) {
		this.selectedEventTypeCd = selectedEventTypeCd;
	}
	
	/**
	 * @return Returns the selectedOfficerName.
	 */
	public String getSelectedOfficerName() {
		return selectedOfficerName;
	}
	/**
	 * @param selectedOfficerName The selectedOfficerName to set.
	 */
	public void setSelectedOfficerName(String selectedOfficerName) {
		this.selectedOfficerName = selectedOfficerName;
	}
	
	/**
	 * @return Returns the selectedSupervisorId.
	 */
	public String getSelectedSupervisorId() {
		return selectedSupervisorId;
	}
	/**
	 * @param selectedSupervisorId The selectedSupervisorId to set.
	 */
	public void setSelectedSupervisorId(String selectedSupervisorId) {
		this.selectedSupervisorId = selectedSupervisorId;
	}
	/**
	 * @return Returns the supervisorList.
	 */
	public List getSupervisorList() {
		return supervisorList;
	}
	/**
	 * @param supervisorList The supervisorList to set.
	 */
	public void setSupervisorList(List supervisorList) {
		this.supervisorList = supervisorList;
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
	 * @return Returns the divisionList.
	 */
	public List getDivisionList() {
		return divisionList;
	}
	/**
	 * @param divisionList The divisionList to set.
	 */
	public void setDivisionList(List divisionList) {
		this.divisionList = divisionList;
	}
	/**
	 * @return Returns the selectedDivisionId.
	 */
	public String getSelectedDivisionId() {
		return selectedDivisionId;
	}
	/**
	 * @param selectedDivisionId The selectedDivisionId to set.
	 */
	public void setSelectedDivisionId(String selectedDivisionId) {
		this.selectedDivisionId = selectedDivisionId;
	}
	/**
	 * @return Returns the selectedOfficerId.
	 */
	public String getSelectedOfficerId() {
		return selectedOfficerId;
	}
	/**
	 * @param selectedOfficerId The selectedOfficerId to set.
	 */
	public void setSelectedOfficerId(String selectedOfficerId) {
		this.selectedOfficerId = selectedOfficerId;
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
	 * @param eventDate The eventDate to set.
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
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
	 * @return Returns the searchBySPNResult.
	 */
	public CaseAssignmentResponseEvent getSearchBySPNResult() {
		return searchBySPNResult;
	}
	/**
	 * @param searchBySPNResult The searchBySPNResult to set.
	 */
	public void setSearchBySPNResult(CaseAssignmentResponseEvent searchBySPNResult) {
		this.searchBySPNResult = searchBySPNResult;
	}
	/**
	 * @return Returns the conditions.
	 */
	public CSSpnViewConditionsResponseEvent getConditions() {
		return conditions;
	}
	/**
	 * @param conditions The conditions to set.
	 */
	public void setConditions(CSSpnViewConditionsResponseEvent conditions) {
		this.conditions = conditions;
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

	public String getCourtIdFilter() {
		return courtIdFilter;
	}

	public void setCourtIdFilter(String courtIdFilter) {
		this.courtIdFilter = courtIdFilter;
	}

	public String getSelectedQuadrantId() {
		return selectedQuadrantId;
	}

	public void setSelectedQuadrantId(String selectedQuadrantId) {
		this.selectedQuadrantId = selectedQuadrantId;
	}

	public String getQuadrantDescription() {
		return quadrantDescription;
	}

	public void setQuadrantDescription(String quadrantDescription) {
		this.quadrantDescription = quadrantDescription;
	}

	public String getQuadrantSearch() {
		return quadrantSearch;
	}

	public void setQuadrantSearch(String quadrantSearch) {
		this.quadrantSearch = quadrantSearch;
	}

	/**
	 * @return the soZipCode
	 */
	public String getSoZipCode() {
		return soZipCode;
	}

	/**
	 * @param soZipCode the soZipCode to set
	 */
	public void setSoZipCode(String soZipCode) {
		this.soZipCode = soZipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}

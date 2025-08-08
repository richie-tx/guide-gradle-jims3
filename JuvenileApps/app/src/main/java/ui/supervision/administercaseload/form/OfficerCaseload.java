/*
 * Created on Apr 10, 2008
 */
package ui.supervision.administercaseload.form;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cc_rbhat
 */
public class OfficerCaseload {
	/**
	 * Court id used to filter officers caseload
	 */
	private String courtIdFilter;
	
	private String zipCodeFilter;
	
	/**
	 * Divisions in an agency
	 */
	private List divisions;
	/**
	 * Set of supervisees under officer's supervision
	 */
	private List defendantsSupervised;
	/**
	 * Caseload carrying officers under a supervisor
	 */
	private List officersUnderSupervisor;
	/**
	 * Id of the selected division
	 */
	private String selectedDivisionId;
	/**
	 * Id of the selected officer
	 */
	private String selectedOfficerId;
	/**
	 * Id of the selected supervisor
	 */
	private String selectedSupervisorId;
	/**
	 * Supervisors in a division
	 */
	private List supervisorsInDivision;
	/**
	 * Total number of cases in officer's caseload
	 */
	private String toalCasesInCaseload;
	/**
	 * Total number of supervisees in officer's caseload
	 */
	private String totalSuperviseesInCaseload;
	/**
	 * Defendant selected for reassignment
	 */
	private String selectedDefendantId;
	/**
	 * 
	 */
	private String[] selectedCasesForReassignment;
	
	private String selectedSupervisorName;
	
	private String selectedOfficerName;
	
	public void clear() {
		courtIdFilter = "";
		zipCodeFilter = "";
		defendantsSupervised = new ArrayList();
		officersUnderSupervisor = new ArrayList();
		selectedDefendantId = "";
		selectedOfficerId = "";
		selectedSupervisorId = "";
		selectedDivisionId = "";
		toalCasesInCaseload = "";
		totalSuperviseesInCaseload = "";
		selectedSupervisorName = "";
		selectedOfficerName = "";
		selectedCasesForReassignment = new String[0];
		supervisorsInDivision = new ArrayList();
	}
	/**
	 * @return Returns the courtIdFilter.
	 */
	public String getCourtIdFilter() {
		return courtIdFilter;
	}
	/**
	 * @return Returns the divisions.
	 */
	public List getDivisions() {
		return divisions;
	}
	/**
	 * @return Returns the defendantsSupervised.
	 */
	public List getDefendantsSupervised() {
		return defendantsSupervised;
	}
	/**
	 * @return Returns the officersUnderSupervisor.
	 */
	public List getOfficersUnderSupervisor() {
		return officersUnderSupervisor;
	}
	/**
	 * @return Returns the selectedDivisionId.
	 */
	public String getSelectedDivisionId() {
		return selectedDivisionId;
	}
	/**
	 * @return Returns the selectedOfficerId.
	 */
	public String getSelectedOfficerId() {
		return selectedOfficerId;
	}
	/**
	 * @return Returns the selectedSuperivisorId.
	 */
	public String getSelectedSupervisorId() {
		return selectedSupervisorId;
	}
	/**
	 * @return Returns the supervisorsInDivision.
	 */
	public List getSupervisorsInDivision() {
		return supervisorsInDivision;
	}
	/**
	 * @return Returns the toalCasesInCaseload.
	 */
	public String getToalCasesInCaseload() {
		return toalCasesInCaseload;
	}
	/**
	 * @return Returns the totalSuperviseesInCaseload.
	 */
	public String getTotalSuperviseesInCaseload() {
		return totalSuperviseesInCaseload;
	}
	/**
	 * @return Returns the selectedDefendantId.
	 */
	public String getSelectedDefendantId() {
		return selectedDefendantId;
	}
	/**
	 * @return Returns the selectedCasesForReassignment.
	 */
	public String[] getSelectedCasesForReassignment() {
		return selectedCasesForReassignment;
	}
	/**
	 * @return the selectedOfficerName
	 */
	public String getSelectedOfficerName() {
		return selectedOfficerName;
	}
	/**
	 * @return the selectedSupervisorName
	 */
	public String getSelectedSupervisorName() {
		return selectedSupervisorName;
	}
	/**
	 * @param courtIdFilter The courtIdFilter to set.
	 */
	public void setCourtIdFilter(String courtIdFilter) {
		this.courtIdFilter = courtIdFilter;
	}
	/**
	 * @param divisions The divisions to set.
	 */
	public void setDivisions(List divisions) {
		this.divisions = divisions;
	}
	/**
	 * @param defendantsSupervised The defendantsSupervised to set.
	 */
	public void setDefendantsSupervised(List defendantsSupervised) {
		this.defendantsSupervised = defendantsSupervised;
	}
	/**
	 * @param officersUnderSupervisor The officersUnderSupervisor to set.
	 */
	public void setOfficersUnderSupervisor(List officersUnderSupervisor) {
		this.officersUnderSupervisor = officersUnderSupervisor;
	}
	/**
	 * @param selectedDivisionId The selectedDivisionId to set.
	 */
	public void setSelectedDivisionId(String selectedDivisionId) {
		this.selectedDivisionId = selectedDivisionId;
	}
	/**
	 * @param selectedOfficerId The selectedOfficerId to set.
	 */
	public void setSelectedOfficerId(String selectedOfficerId) {
		this.selectedOfficerId = selectedOfficerId;
	}
	/**
	 * @param selectedSuperivisorId The selectedSuperivisorId to set.
	 */
	public void setSelectedSupervisorId(String selectedSupervisorId) {
		this.selectedSupervisorId = selectedSupervisorId;
	}
	/**
	 * @param supervisorsInDivision The supervisorsInDivision to set.
	 */
	public void setSupervisorsInDivision(List supervisorsInDivision) {
		this.supervisorsInDivision = supervisorsInDivision;
	}
	/**
	 * @param toalCasesInCaseload The toalCasesInCaseload to set.
	 */
	public void setToalCasesInCaseload(String toalCasesInCaseload) {
		this.toalCasesInCaseload = toalCasesInCaseload;
	}
	/**
	 * @param totalSuperviseesInCaseload The totalSuperviseesInCaseload to set.
	 */
	public void setTotalSuperviseesInCaseload(String totalSuperviseesInCaseload) {
		this.totalSuperviseesInCaseload = totalSuperviseesInCaseload;
	}
	/**
	 * @param selectedDefendantId The selectedDefendantId to set.
	 */
	public void setSelectedDefendantId(String selectedDefendantId) {
		this.selectedDefendantId = selectedDefendantId;
	}
	/**
	 * @param selectedCasesForReassignment The selectedCasesForReassignment to set.
	 */
	public void setSelectedCasesForReassignment(String[] selectedCasesForReassignment) {
		this.selectedCasesForReassignment = selectedCasesForReassignment;
	}
	/**
	 * @param selectedOfficerName the selectedOfficerName to set
	 */
	public void setSelectedOfficerName(String selectedOfficerName) {
		this.selectedOfficerName = selectedOfficerName;
	}
	/**
	 * @param selectedSupervisorName the selectedSupervisorName to set
	 */
	public void setSelectedSupervisorName(String selectedSupervisorName) {
		this.selectedSupervisorName = selectedSupervisorName;
	}
	
	public String getZipCodeFilter() {
		return zipCodeFilter;
	}
	
	public void setZipCodeFilter(String zipCodeFilter) {
		this.zipCodeFilter = zipCodeFilter;
	}
}

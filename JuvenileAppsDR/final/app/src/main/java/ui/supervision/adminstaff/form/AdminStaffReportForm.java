/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.adminstaff.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;

import ui.common.ComplexCodeTableHelper;
import ui.common.Name;
import ui.common.SimpleCodeTableHelper;
import ui.security.SecurityUIHelper;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AdminStaffReportForm extends ActionForm {
	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	
	// FORM specific values
	private String reportType="";
	private String positionId="";
	private String staffId="";  // this is generally the same thing as User ID
	private String programUnitId="";
	private String positionStatusId="";
	private String positionStatusDesc="";
	private Date startDate=null;
	private Date endDate=null;
	private ArrayList positionList=null;  // Array List of all positions with no filter criteria 
	private ArrayList filteredList=null;   // ArrayList of positioins with filtered criteria
	
	public void clearDefaultFormValues(){
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}

	public void clear(){
		positionStatusId="";
		positionStatusDesc="";
		resetDatesForSearch();
		positionList=new ArrayList();
		filteredList=new ArrayList();
		reportType="";
		programUnitId="";
		positionStatusId="";
		staffId="";
		
	}
	
	public void resetFilterSearch(){
		resetDatesForSearch();
		positionStatusId="";
		positionStatusDesc="";
	}
	
	public void resetDatesForSearch(){
		Calendar myCal=new GregorianCalendar();
		endDate=myCal.getTime();
		myCal.add(Calendar.YEAR,-1);
		startDate=myCal.getTime();
	}
	
	public void clearAll(){
		clearDefaultFormValues();
		clear();
	}
	
	
	
	
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}
	/**
	 * @param delete The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	/**
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the filteredList.
	 */
	public ArrayList getFilteredList() {
		return filteredList;
	}
	/**
	 * @param filteredList The filteredList to set.
	 */
	public void setFilteredList(ArrayList filteredList) {
		this.filteredList = filteredList;
	}
	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}
	/**
	 * @param listsSet The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}
	/**
	 * @return Returns the positionList.
	 */
	public ArrayList getPositionList() {
		return positionList;
	}
	/**
	 * @param positionList The positionList to set.
	 */
	public void setPositionList(ArrayList positionList) {
		this.positionList = positionList;
	}
	
	public Collection getPositionStatuses(){
		return ComplexCodeTableHelper.getSupervisionStaffStatus(SecurityUIHelper.getUserAgencyId());
	}
	/**
	 * @return Returns the positionStatusDesc.
	 */
	public String getPositionStatusDesc() {
		return positionStatusDesc;
	}
	/**
	 * @param positionStatusDesc The positionStatusDesc to set.
	 */
	public void setPositionStatusDesc(String positionStatusDesc) {
		this.positionStatusDesc = positionStatusDesc;
	}
	/**
	 * @return Returns the positionStatusId.
	 */
	public String getPositionStatusId() {
		return positionStatusId;
	}
	/**
	 * @param positionStatusId The positionStatusId to set.
	 */
	public void setPositionStatusId(String positionStatusId) {
		this.positionStatusId = positionStatusId;
		positionStatusDesc="";
		Collection myPosStatus=getPositionStatuses();
		if(myPosStatus!=null && myPosStatus.size()>0){
			positionStatusDesc=SimpleCodeTableHelper.getDescrByCode((List)myPosStatus,positionStatusId);
		}
	}
	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}
	/**
	 * @param secondaryAction The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}
	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}
	/**
	 * @param selectedValue The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	public String getStartDateAsStr(){
		if(startDate==null){
			return "";
		}
		else{
			return DateUtil.dateToString(startDate,UIConstants.DATE_FMT_1);
		}
	}
	
	public void setStartDateAsStr(String aDate){
		if(aDate==null || aDate.equals("")){
			startDate=null;
		}
		else{
			startDate=DateUtil.stringToDate(aDate,UIConstants.DATE_FMT_1);
		}
	}
	
	public String getEndDateAsStr(){
		if(endDate==null){
			return "";
		}
		else{
			return DateUtil.dateToString(endDate,UIConstants.DATE_FMT_1);
		}
	}
	
	public void setEndDateAsStr(String aDate){
		if(aDate==null || aDate.equals("")){
			endDate=null;
		}
		else{
			endDate=DateUtil.stringToDate(aDate,UIConstants.DATE_FMT_1);
		}
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}
	/**
	 * @param update The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl() {
		return emptyColl;
	}
	/**
	 * @return Returns the reportType.
	 */
	public String getReportType() {
		return reportType;
	}
	/**
	 * @param reportType The reportType to set.
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
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
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	/**
	 * @return Returns the staffId.
	 */
	public String getStaffId() {
		return staffId;
	}
	/**
	 * @param staffId The staffId to set.
	 */
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	
}

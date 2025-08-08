/*
 * Created on Feb 21, 2007
 */
package ui.supervision.manageworkgroup.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;
import naming.PDCodeTableConstants;

import ui.security.SecurityUIHelper;

import ui.common.SimpleCodeTableHelper;

//import ui.supervision.workgroup.UIWorkgroupLoadCodeTables;

/**
 * @author hrodriguez
 */
public class WorkgroupSearchForm extends ActionForm 
{
	//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";

	//	Fields
	private String agencyId;
	private boolean openedTaskInd;
	private String workgroupDescription;
	private String workgroupId;
	private String workgroupName;
	private Collection workgroupList; // Search result list
	private String workgroupListSize;
	private String workgroupTypeDesc;
	private String workgroupTypeId;  //Codetable Drop Down Lists
	
	public void clearDefaultFormValues() {
		action = "";
		secondaryAction = "";
		update = false;
		delete = false;
		selectedValue = "";
	}

	public void clear() {
		agencyId = "";
		openedTaskInd = false;
		workgroupId = "";
		workgroupName = "";
		workgroupListSize = "";
		workgroupTypeId = "";

		// Collections and Drop Down Lists
		workgroupList = new ArrayList();
	}

	public void clearAll() {
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
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the workgroupDescription.
	 */
	public String getWorkgroupDescription() {
		return workgroupDescription;
	}

	/**
	 * @param workgroupDescription
	 *            The workgroupDescription to set.
	 */
	public void setWorkgroupDescription(String workgroupDescription) {
		this.workgroupDescription = workgroupDescription;
	}

	/**
	 * @return Returns the workgroupId.
	 */
	public String getWorkgroupId() {
		return workgroupId;
	}

	/**
	 * @param workgroupId
	 *            The workgroupId to set.
	 */
	public void setWorkgroupId(String workgroupId) {
		this.workgroupId = workgroupId;
	}

	/**
	 * @return Returns the workgroupList.
	 */
	public Collection getWorkgroupList() {
		return workgroupList;
	}

	/**
	 * @param workgroupList
	 *            The workgroupList to set.
	 */
	public void setWorkgroupList(Collection workgroupList) {
		this.workgroupList = workgroupList;
	}

	/**
	 * @param workgroupList
	 *            The workgroupList to set.
	 */
	public void clearWorkgroupList() {
		workgroupList.clear();
	}

	/**
	 * @return Returns the workgroupListSize.
	 */
	public String getWorkgroupListSize() {
		return workgroupListSize;
	}

	/**
	 * @param workgroupListSize
	 *            The workgroupListSize to set.
	 */
	public void setWorkgroupListSize(String workgroupListSize) {
		this.workgroupListSize = workgroupListSize;
	}

	/**
	 * @return Returns the workgroupName.
	 */
	public String getWorkgroupName() {
		return workgroupName;
	}

	/**
	 * @param workgroupName
	 *            The workgroupName to set.
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
	}

	/**
	 * @return Returns the workgroupType.
	 */
	public String getWorkgroupTypeDesc() {
		return workgroupTypeDesc;
	}

	/**
	 * @param workgroupType
	 *            The workgroupType to set.
	 */
	public void setWorkgroupTypeDesc(String workgroupType) {
		this.workgroupTypeDesc = workgroupType;
	}

	/**
	 * @return Returns the workgroupTypeId.
	 */
	public String getWorkgroupTypeId() {
		return workgroupTypeId;
	}

	/**
	 * @param workgroupTypeId
	 *            The workgroupTypeId to set.
	 */
	public void setWorkgroupTypeId(String workgroupTypeId) {
		this.workgroupTypeId = workgroupTypeId;
		workgroupTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE,workgroupTypeId); 
	}

	/**
	 * @return Returns the openedTaskInd.
	 */
	public boolean isOpenedTaskInd() {
		return openedTaskInd;
	}

	/**
	 * @param openedTaskInd
	 *            The openedTaskInd to set.
	 */
	public void setOpenedTaskInd(boolean openedTaskInd) {
		this.openedTaskInd = openedTaskInd;
	}

	/**
	 * @return Returns the emptyColl.
	 */
	public static Collection getEmptyColl() {
		return emptyColl;
	}

	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		if (agencyId == null || agencyId.equals("")) {
			agencyId = SecurityUIHelper.getUserAgencyId();
		}
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete
	 *            The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction
	 *            The secondaryAction to set.
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
	 * @param selectedValue
	 *            The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}
	
}

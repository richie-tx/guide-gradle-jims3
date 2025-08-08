//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasehistory\\form\\CaseHistoryForm.java

package ui.supervision.administercompliance.administercasehistory.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 *  
 */
public class CaseHistoryForm extends ActionForm 
{
//	 begin common form variables
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
// end common form variables	
	public String superviseeId;
	public String selectedOrderId;
	public List caseHistoryList;
	public boolean allowUpdates = false;
	
    public void clear()
    {
    	caseHistoryList = new ArrayList();
    	this.selectedOrderId = "";
    	this.superviseeId = "";
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
	 * @return Returns the selectedOrderId.
	 */
	public String getSelectedOrderId() {
		return selectedOrderId;
	}
	/**
	 * @param selectedOrderId The selectedOrderId to set.
	 */
	public void setSelectedOrderId(String selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}
	/**
	 * @return Returns the caseHistoryList.
	 */
	public List getCaseHistoryList() {
		return caseHistoryList;
	}
	/**
	 * @param caseHistoryList The caseHistoryList to set.
	 */
	public void setCaseHistoryList(List caseHistoryList) {
		this.caseHistoryList = caseHistoryList;
	}

	/**
	 * @return the allowUpdates
	 */
	public boolean isAllowUpdates() {
		return allowUpdates;
	}

	/**
	 * @param allowUpdates the allowUpdates to set
	 */
	public void setAllowUpdates(boolean allowUpdates) {
		this.allowUpdates = allowUpdates;
	}
	
}

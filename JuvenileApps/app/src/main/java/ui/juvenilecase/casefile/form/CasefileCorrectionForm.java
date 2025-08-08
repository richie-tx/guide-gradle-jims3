/*
 * Created on Nov 16, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Collection;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;

import ui.common.SimpleCodeTableHelper;
import ui.common.UIUtil;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CasefileCorrectionForm extends ActionForm {
	//	 Default field
		private static Collection emptyColl = new ArrayList();
		private boolean listsSet=false;
		private String action="";
		private String secondaryAction="";
		private boolean update=false;
		private boolean delete=false;
		private String selectedValue="";
		
	private String casefileId;
    private String currentSupTypeId;
    private String currentSupTypeDesc;
    private String changeToSupTypeId;
    private String changeToSupTypeDesc;
    private String currentSupCatId;
    private String currentSupCatDesc;
    private String changeStatusToClosingPendingStr;
    private boolean changeStatusToClosingPending;
    private String currentCasefileStatusId;
    private String clmId;
    private String currentUserId;
    private boolean isCLM;
    
    private Collection supervisionTypes; //SupervisionTypeMapResponseEvent objects
    private String juvenileNum;
	
    
    public void clear(){
    	setChangeToSupTypeId("");
        setChangeToSupTypeId("");
        setCurrentSupCatId("");
        setChangeStatusToClosingPending(false);
        setCurrentCasefileStatusId("");
        setClmId("");    
        setCurrentUserId("");
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
	 * @return Returns the changeStatusToClosingPending.
	 */
	public boolean isChangeStatusToClosingPending() {
		return changeStatusToClosingPending;
	}
	/**
	 * @param changeStatusToClosingPending The changeStatusToClosingPending to set.
	 */
	public void setChangeStatusToClosingPending(boolean changeStatusToClosingPending) {
		this.changeStatusToClosingPending = changeStatusToClosingPending;
	}
	/**
	 * @return Returns the changeStatusToClosingPendingStr.
	 */
	public String getChangeStatusToClosingPendingStr() {
		return UIUtil.getYesNo(changeStatusToClosingPending,true);
	}
	/**
	 * @param changeStatusToClosingPendingStr The changeStatusToClosingPendingStr to set.
	 */
	public void setChangeStatusToClosingPendingStr(String changeStatusToClosingPendingStr) {
		this.changeStatusToClosingPendingStr = changeStatusToClosingPendingStr;
	}
	/**
	 * @return Returns the changeToSupTypeDesc.
	 */
	public String getChangeToSupTypeDesc() {
		return changeToSupTypeDesc;
	}
	/**
	 * @param changeToSupTypeDesc The changeToSupTypeDesc to set.
	 */
	public void setChangeToSupTypeDesc(String changeToSupTypeDesc) {
		this.changeToSupTypeDesc = changeToSupTypeDesc;
	}
	/**
	 * @return Returns the changeToSupTypeId.
	 */
	public String getChangeToSupTypeId() {
		return changeToSupTypeId;
	}
	/**
	 * @param changeToSupTypeId The changeToSupTypeId to set.
	 */
	public void setChangeToSupTypeId(String aChangeToSupTypeId) {
		this.changeToSupTypeId = aChangeToSupTypeId;
		changeToSupTypeDesc="";
		changeToSupTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE,this.changeToSupTypeId);
		if(changeToSupTypeDesc==null){
			changeToSupTypeDesc="";
		}
	}
	/**
	 * @return Returns the clmId.
	 */
	public String getClmId() {
		return clmId;
	}
	/**
	 * @param clmId The clmId to set.
	 */
	public void setClmId(String clmId) {
		this.clmId = clmId;
	}
	/**
	 * @return Returns the currentCasefileStatusId.
	 */
	public String getCurrentCasefileStatusId() {
		return currentCasefileStatusId;
	}
	/**
	 * @param currentCasefileStatusId The currentCasefileStatusId to set.
	 */
	public void setCurrentCasefileStatusId(String currentCasefileStatusId) {
		this.currentCasefileStatusId = currentCasefileStatusId;
	}
	/**
	 * @return Returns the currentSupCatDesc.
	 */
	public String getCurrentSupCatDesc() {
		return currentSupCatDesc;
	}
	/**
	 * @param currentSupCatDesc The currentSupCatDesc to set.
	 */
	public void setCurrentSupCatDesc(String currentSupCatDesc) {
		this.currentSupCatDesc = currentSupCatDesc;
	}
	/**
	 * @return Returns the currentSupCatId.
	 */
	public String getCurrentSupCatId() {
		return currentSupCatId;
	}
	/**
	 * @param currentSupCatId The currentSupCatId to set.
	 */
	public void setCurrentSupCatId(String aCurrentSupCatId) {
		this.currentSupCatId = aCurrentSupCatId;
		currentSupCatDesc="";
		currentSupCatDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_CATEGORY,this.currentSupCatId);
		if(currentSupCatDesc==null){
			currentSupCatDesc="";
		}
		
	}
	/**
	 * @return Returns the currentSupTypeDesc.
	 */
	public String getCurrentSupTypeDesc() {
		return currentSupTypeDesc;
	}
	/**
	 * @param currentSupTypeDesc The currentSupTypeDesc to set.
	 */
	public void setCurrentSupTypeDesc(String currentSupTypeDesc) {
		this.currentSupTypeDesc = currentSupTypeDesc;
	}
	/**
	 * @return Returns the currentSupTypeId.
	 */
	public String getCurrentSupTypeId() {
		return currentSupTypeId;
	}
	/**
	 * @param currentSupTypeId The currentSupTypeId to set.
	 */
	public void setCurrentSupTypeId(String aCurrentSupTypeId) {
		this.currentSupTypeId = aCurrentSupTypeId;
		currentSupTypeDesc="";
		currentSupTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.JUVENILE_CASEFILE_SUPERVISION_TYPE,this.currentSupTypeId);
		if(currentSupTypeDesc==null){
			currentSupTypeDesc="";
		}
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
	 * @return Returns the isCLM.
	 */
	public boolean isCLM() {
		if(this.getClmId()==null || this.getClmId().equals("") || this.getCurrentUserId()==null || this.getCurrentUserId().equals("")){
			return false;
		}
		else{
			return this.getClmId().equalsIgnoreCase(this.getCurrentUserId());
		}
	}
	/**
	 * @param isCLM The isCLM to set.
	 */
	public void setCLM(boolean isCLM) {
		this.isCLM = isCLM;
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
	 * @return Returns the supervisionTypes.
	 */
	public Collection getSupervisionTypes() {
		return supervisionTypes;
	}
	/**
	 * @param supervisionTypes The supervisionTypes to set.
	 */
	public void setSupervisionTypes(Collection supervisionTypes) {
		this.supervisionTypes = supervisionTypes;
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
		 * @return Returns the listsSet.
		 */
		public boolean isListsSet() {
			return listsSet;
		}
		
		
	/**
	 * @return Returns the currentUserId.
	 */
	public String getCurrentUserId() {
		return currentUserId;
	}
	/**
	 * @param currentUserId The currentUserId to set.
	 */
	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}
	/**
	 * @return Returns the casefileId.
	 */
	public String getCasefileId() {
		return casefileId;
	}
	/**
	 * @param casefileId The casefileId to set.
	 */
	public void setCasefileId(String casefileId) {
		this.casefileId = casefileId;
	}



	public String getJuvenileNum()
	{
	    return juvenileNum;
	}



	public void setJuvenileNum(String juvenileNum)
	{
	    this.juvenileNum = juvenileNum;
	}
}// END CLASS

/*
 * Created on Aug 2, 2006
 *
 */
package ui.supervision.administercasenotes.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

import ui.common.UIUtil;
import ui.supervision.manageassociate.UIManageAssociateHelper;



/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CasenoteJournalForm extends ActionForm {
	
//	 Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	
	// form related
	private String superviseeId="";
	private String supervisionPeriod="";
	private String supervisionPeriodId="";
	private CasenoteForm currentCasenote=new CasenoteForm();
	private CasenoteSearchForm searchCasenote=new CasenoteSearchForm();
	private String userAgency;
	private String selectedCasenoteId;
	
	private String associateId;
	private Collection associatesList;
	
	public CasenoteJournalForm(){
		setAllLists();
	}
	
	private void setAllLists(){
		// set all 
	}
	
	public void clearAll(){
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
		
		supervisionPeriod="";
		superviseeId="";
		currentCasenote=new CasenoteForm();
		searchCasenote=new CasenoteSearchForm();
		userAgency="";
		supervisionPeriodId="";
		selectedCasenoteId = "";
		
		associateId = "";
		associatesList = new ArrayList();
		
	}
	
	public void clearCurrentCasenoteAction(){
	    if(this.getCurrentCasenote()!=null)
	        this.getCurrentCasenote().setAction("");
	    else{
	        
	    }
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
	 * @return Returns the currentCasenote.
	 */
	public CasenoteForm getCurrentCasenote() {
		return currentCasenote;
	}
	/**
	 * @param currentCasenote The currentCasenote to set.
	 */
	public void setCurrentCasenote(CasenoteForm currentCasenote) {
		this.currentCasenote = currentCasenote;
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
	 * @return Returns the searchCasenote.
	 */
	public CasenoteSearchForm getSearchCasenote() {
		return searchCasenote;
	}
	/**
	 * @param searchCasenote The searchCasenote to set.
	 */
	public void setSearchCasenote(CasenoteSearchForm searchCasenote) {
		this.searchCasenote = searchCasenote;
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
	public String getSupervisionPeriod() {
		return supervisionPeriod;
	}
	public void setSupervisionPeriod(String supervisionPeriod) {
		this.supervisionPeriod = supervisionPeriod;
	}
	public String getUserAgency() {
		return UIUtil.getCurrentUserAgencyID();
	}
	public void setUserAgency(String userAgency) {
		this.userAgency = userAgency;
	}
	/**
	 * @return Returns the supervisionPeriodId.
	 */
	public String getSupervisionPeriodId() {
		return supervisionPeriodId;
	}
	/**
	 * @param supervisionPeriodId The supervisionPeriodId to set.
	 */
	public void setSupervisionPeriodId(String supervisionPeriodId) {
		this.supervisionPeriodId = supervisionPeriodId;
	}
	
	/**
	 * @return Returns the associateId.
	 */
	public String getAssociateId() {
		return associateId;
	}
	/**
	 * @param associateId The associateId to set.
	 */
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	
	/**
	 * @return Returns the associatesList.
	 */
	public Collection getAssociatesList() {
	    if (associatesList == null || associatesList.size() == 0) {
            // get groups
	    	associatesList = UIManageAssociateHelper.fetchAssociatesListSortedOnDisplayName(this.getSuperviseeId());
        }
        return associatesList;
	}
	/**
	 * @param associatesList The associatesList to set.
	 */
	public void setAssociatesList(Collection associatesList) {
		this.associatesList = associatesList;
	}

	/**
	 * @return the selectedCasenoteId
	 */
	public String getSelectedCasenoteId() {
		return selectedCasenoteId;
	}

	/**
	 * @param selectedCasenoteId the selectedCasenoteId to set
	 */
	public void setSelectedCasenoteId(String selectedCasenoteId) {
		this.selectedCasenoteId = selectedCasenoteId;
	}
	
}// END CLASS

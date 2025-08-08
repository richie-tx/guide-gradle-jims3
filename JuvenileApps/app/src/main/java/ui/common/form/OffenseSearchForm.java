/*
 * Created on Jan 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common.form;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.struts.action.ActionForm;

/**
 * @author jjose
 *
 * The purpose of this form is to be able to search offenses. It was originally created for use in Common 
 * Supervision but then shown that it might be useful in MJCW as well so was placed here.
 */
public class OffenseSearchForm extends ActionForm {
	
	// Default Elements in all forms
	private static Collection emptyColl = new ArrayList();
	private boolean listsSet=false;
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
	
	
	// Search Field
	private String levelId;
	private String offenseCode;
	private String offenseLiteral;
	private String degreeId;
	private String penalCode;
	private String stateOffenseCode;
	private Collection offenseResultList = new ArrayList();
	
	
	public void clearSearchFields(){
		offenseCode="";
		offenseLiteral="";
		levelId="";
		degreeId="";
		penalCode="";
		stateOffenseCode="";
		offenseResultList = new ArrayList();
	}
	
	public void clearAll(){
		action="";
		secondaryAction="";
		update=false;
		delete=false;
		selectedValue="";
		offenseCode="";
		offenseLiteral="";
		levelId="";
		degreeId="";
		penalCode="";
		stateOffenseCode="";
		offenseResultList = new ArrayList();
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
	 * @return Returns the degreeId.
	 */
	public String getDegreeId() {
		return degreeId;
	}
	/**
	 * @param degreeId The degreeId to set.
	 */
	public void setDegreeId(String degreeId) {
		this.degreeId = degreeId;
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
	 * @return Returns the levelId.
	 */
	public String getLevelId() {
		return levelId;
	}
	/**
	 * @param levelId The levelId to set.
	 */
	public void setLevelId(String levelId) {
		this.levelId = levelId;
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
	 * @return Returns the offenseCode.
	 */
	public String getOffenseCode() {
		return offenseCode;
	}
	/**
	 * @param offenseCode The offenseCode to set.
	 */
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}
	/**
	 * @return Returns the offenseLiteral.
	 */
	public String getOffenseLiteral() {
		return offenseLiteral;
	}
	/**
	 * @param offenseLiteral The offenseLiteral to set.
	 */
	public void setOffenseLiteral(String offenseLiteral) {
		this.offenseLiteral = offenseLiteral;
	}
	/**
	 * @return Returns the offenseResultList.
	 */
	public Collection getOffenseResultList() {
		return offenseResultList;
	}
	/**
	 * @param offenseResultList The offenseResultList to set.
	 */
	public void setOffenseResultList(Collection offenseResultList) {
		this.offenseResultList = offenseResultList;
	}
	/**
	 * @return Returns the penalCode.
	 */
	public String getPenalCode() {
		return penalCode;
	}
	/**
	 * @param penalCode The penalCode to set.
	 */
	public void setPenalCode(String penalCode) {
		this.penalCode = penalCode;
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
	 * @return Returns the stateOffenseCode.
	 */
	public String getStateOffenseCode() {
		return stateOffenseCode;
	}
	/**
	 * @param stateOffenseCode The stateOffenseCode to set.
	 */
	public void setStateOffenseCode(String stateOffenseCode) {
		this.stateOffenseCode = stateOffenseCode;
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
}// END CLASS

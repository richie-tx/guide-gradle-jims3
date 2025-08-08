/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.supervisee.form;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.struts.action.ActionForm;

import ui.common.Name;

/**
 * @author jjose
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SuperviseeHeaderForm extends ActionForm {

	private static Collection emptyColl = new ArrayList();
	private boolean listsSet = false;
	private String action = "";
	private String secondaryAction = "";
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";

	// form related
	private String superviseeId = "";
	private Name superviseeName = new Name();
	private String superviseeSpn = "";
	private boolean compliant = true;
	private Name officerName = new Name();
	private String superviseeNameDesc = "";
	private String officerNameDesc;
	private String LOSId = "";
	private String LOSDesc = "";
	private String programUnitId = "";
	private String programUnitDesc = "";
	private String officerPositionId = "";
	private boolean superviseeFound;
	private boolean dnaFlagInd;
	private String probationStartDate = "";
	private String disposition = "";
	private String offenseLevel = "";
	private boolean overNinetyDays;
	
	private transient byte[] superviseePhoto;
	private boolean hasPhoto;
	private String superviseePhotoCreateDate;
	
	public boolean isSuperviseeFound() {
		return superviseeFound;
	}

	public void setSuperviseeFound(boolean superviseeFound) {
		this.superviseeFound = superviseeFound;
	}

	public void clear() {
		superviseeId = "";
		superviseeName = new Name();
		superviseeSpn = "";
		compliant = true;
		officerName = new Name();
		LOSId = "";
		LOSDesc = "";
		programUnitId = "";
		programUnitDesc = "";
		superviseeNameDesc = "";
		officerNameDesc = "";
		superviseeFound = false;
		officerPositionId = "";
		dnaFlagInd = false;
		probationStartDate = "";
		disposition = "";
		offenseLevel = "";
		overNinetyDays = false;
		
	}

	public String getOfficerPositionId() {
		return officerPositionId;
	}

	public void setOfficerPositionId(String officerPositionId) {
		this.officerPositionId = officerPositionId;
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
	 * @return Returns the compliant.
	 */
	public boolean isCompliant() {
		return compliant;
	}

	/**
	 * @param compliant
	 *            The compliant to set.
	 */
	public void setCompliant(boolean compliant) {
		this.compliant = compliant;
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
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}

	/**
	 * @param listsSet
	 *            The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}

	/**
	 * @return Returns the lOSDesc.
	 */
	public String getLOSDesc() {
		return LOSDesc;
	}

	/**
	 * @param desc
	 *            The lOSDesc to set.
	 */
	public void setLOSDesc(String desc) {
		LOSDesc = desc;
	}

	/**
	 * @return Returns the lOSId.
	 */
	public String getLOSId() {
		return LOSId;
	}

	/**
	 * @param id
	 *            The lOSId to set.
	 */
	public void setLOSId(String id) {
		LOSId = id;
	}

	/**
	 * @return Returns the officerName.
	 */
	public Name getOfficerName() {
		return officerName;
	}

	/**
	 * @param officerName
	 *            The officerName to set.
	 */
	public void setOfficerName(Name officerName) {
		this.officerName = officerName;
	}

	/**
	 * @return Returns the programUnitDesc.
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}

	/**
	 * @param programUnitDesc
	 *            The programUnitDesc to set.
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
	}

	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}

	/**
	 * @param programUnitId
	 *            The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
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
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the superviseeName.
	 */
	public Name getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName
	 *            The superviseeName to set.
	 */
	public void setSuperviseeName(Name superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return Returns the superviseeSpn.
	 */
	public String getSuperviseeSpn() {
		return superviseeSpn;
	}

	/**
	 * @param superviseeSpn
	 *            The superviseeSpn to set.
	 */
	public void setSuperviseeSpn(String superviseeSpn) {
		this.superviseeSpn = superviseeSpn;
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

	/**
	 * @return Returns the officerNameDesc.
	 */
	public String getOfficerNameDesc() {
		return officerNameDesc;
	}

	/**
	 * @param officerNameDesc
	 *            The officerNameDesc to set.
	 */
	public void setOfficerNameDesc(String officerNameDesc) {
		this.officerNameDesc = officerNameDesc;
	}

	/**
	 * @return Returns the superviseeNameDesc.
	 */
	public String getSuperviseeNameDesc() {
		return superviseeNameDesc;
	}

	/**
	 * @param superviseeNameDesc
	 *            The superviseeNameDesc to set.
	 */
	public void setSuperviseeNameDesc(String superviseeNameDesc) {
		this.superviseeNameDesc = superviseeNameDesc;
	}
	
	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}

	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
		
	/**
	 * @return the probationStartDate
	 */
	public String getProbationStartDate() {
		return probationStartDate;
	}

	/**
	 * @param probationStartDate the probationStartDate to set
	 */
	public void setProbationStartDate(String probationStartDate) {
		this.probationStartDate = probationStartDate;
	}

	/**
	 * @return the dispositionTypeId
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * @param dispositionTypeId the dispositionTypeId to set
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	
	/**
	 * @return the offenseLevel
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}

	/**
	 * @param offenseLevel the offenseLevel to set
	 */
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}

	public byte[] getSuperviseePhoto() {
		return superviseePhoto;
	}

	public void setSuperviseePhoto(byte[] superviseePhoto) {
		this.superviseePhoto = superviseePhoto;
	}

	public boolean isHasPhoto() {
		return hasPhoto;
	}

	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}

	public String getSuperviseePhotoCreateDate() {
		return superviseePhotoCreateDate;
	}

	public void setSuperviseePhotoCreateDate(String superviseePhotoCreateDate) {
		this.superviseePhotoCreateDate = superviseePhotoCreateDate;
	}

	/**
	 * @return the overNinetyDays
	 */
	public boolean isOverNinetyDays() {
		return overNinetyDays;
	}

	/**
	 * @param overNinetyDays the overNinetyDays to set
	 */
	public void setOverNinetyDays(boolean overNinetyDays) {
		this.overNinetyDays = overNinetyDays;
	}
	
}// END FORM

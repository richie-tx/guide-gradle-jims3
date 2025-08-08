//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\supplementalDocuments\\form\\SupplementalDocumentsForm.java
package ui.supervision.supplementalDocuments.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * @author rcapestani
 *  
 */

public class SupplementalDocumentsForm extends ActionForm
{
//	begin common form variables
	private String action="";
	private String secondaryAction="";
	private boolean update=false;
	private boolean delete=false;
	private String selectedValue="";
// 	end common form variables
	
//  begin parameter variables
	private String spn = "";
	private String cdi = "";
	private String caseNumber = "";
	private String court = "";
	private String poi = "";
	private String officer = "";
//  end parameter variables
	
	private List correspondenceList;
	private List ctiList;
	private List defendantFormsList;
	private List elmFormsList;
	private List financialList;
	private List miscellaneousList;
	private List newCaseList;
	private List probationerMonthlyReportList;
	private List referralFormsList;
	private List sexOffenderList;
	private List specialProgramsList;
	private List transferUnitList;
	private List travelPermitsList;
	private List waiversList;
	
	public void clear()
    {
		spn = "";
		cdi = "";
		caseNumber = "";
		court = "";
		poi = "";
		officer = "";
		correspondenceList = new ArrayList();
		ctiList = new ArrayList();
		defendantFormsList = new ArrayList();
		elmFormsList = new ArrayList();
		financialList = new ArrayList();
		miscellaneousList = new ArrayList();
		newCaseList = new ArrayList();
		probationerMonthlyReportList = new ArrayList();
		referralFormsList = new ArrayList();
		sexOffenderList = new ArrayList();
		specialProgramsList = new ArrayList();
		transferUnitList = new ArrayList();
		travelPermitsList = new ArrayList();
		waiversList = new ArrayList();
    }

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return the secondaryAction
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction the secondaryAction to set
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return the update
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return the delete
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the selectedValue
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue the selectedValue to set
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}

	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}

	/**
	 * @return the cdi
	 */
	public String getCdi() {
		return cdi;
	}

	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}

	/**
	 * @return the caseNumber
	 */
	public String getCaseNumber() {
		return caseNumber;
	}

	/**
	 * @param caseNumber the caseNumber to set
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	/**
	 * @return the court
	 */
	public String getCourt() {
		return court;
	}

	/**
	 * @param court the court to set
	 */
	public void setCourt(String court) {
		this.court = court;
	}

	/**
	 * @return the poi
	 */
	public String getPoi() {
		return poi;
	}

	/**
	 * @param poi the poi to set
	 */
	public void setPoi(String poi) {
		this.poi = poi;
	}

	/**
	 * @return the officer
	 */
	public String getOfficer() {
		return officer;
	}

	/**
	 * @param officer the officer to set
	 */
	public void setOfficer(String officer) {
		this.officer = officer;
	}

	/**
	 * @return the correspondenceList
	 */
	public List getCorrespondenceList() {
		return correspondenceList;
	}

	/**
	 * @param correspondenceList the correspondenceList to set
	 */
	public void setCorrespondenceList(List correspondenceList) {
		this.correspondenceList = correspondenceList;
	}

	/**
	 * @return the ctiList
	 */
	public List getCtiList() {
		return ctiList;
	}

	/**
	 * @param ctiList the ctiList to set
	 */
	public void setCtiList(List ctiList) {
		this.ctiList = ctiList;
	}

	/**
	 * @return the defendantFormsList
	 */
	public List getDefendantFormsList() {
		return defendantFormsList;
	}

	/**
	 * @param defendantFormsList the defendantFormsList to set
	 */
	public void setDefendantFormsList(List defendantFormsList) {
		this.defendantFormsList = defendantFormsList;
	}

	/**
	 * @return the elmFormsList
	 */
	public List getElmFormsList() {
		return elmFormsList;
	}

	/**
	 * @param elmFormsList the elmFormsList to set
	 */
	public void setElmFormsList(List elmFormsList) {
		this.elmFormsList = elmFormsList;
	}

	/**
	 * @return the financialList
	 */
	public List getFinancialList() {
		return financialList;
	}

	/**
	 * @param financialList the financialList to set
	 */
	public void setFinancialList(List financialList) {
		this.financialList = financialList;
	}

	/**
	 * @return the miscellaneousList
	 */
	public List getMiscellaneousList() {
		return miscellaneousList;
	}

	/**
	 * @param miscellaneousList the miscellaneousList to set
	 */
	public void setMiscellaneousList(List miscellaneousList) {
		this.miscellaneousList = miscellaneousList;
	}

	/**
	 * @return the newCaseList
	 */
	public List getNewCaseList() {
		return newCaseList;
	}

	/**
	 * @param newCaseList the newCaseList to set
	 */
	public void setNewCaseList(List newCaseList) {
		this.newCaseList = newCaseList;
	}

	/**
	 * @return the probationerMonthlyReportList
	 */
	public List getProbationerMonthlyReportList() {
		return probationerMonthlyReportList;
	}

	/**
	 * @param probationerMonthlyReportList the probationerMonthlyReportList to set
	 */
	public void setProbationerMonthlyReportList(List probationerMonthlyReportList) {
		this.probationerMonthlyReportList = probationerMonthlyReportList;
	}

	/**
	 * @return the referralFormsList
	 */
	public List getReferralFormsList() {
		return referralFormsList;
	}

	/**
	 * @param referralFormsList the referralFormsList to set
	 */
	public void setReferralFormsList(List referralFormsList) {
		this.referralFormsList = referralFormsList;
	}

	/**
	 * @return the sexOffenderList
	 */
	public List getSexOffenderList() {
		return sexOffenderList;
	}

	/**
	 * @param sexOffenderList the sexOffenderList to set
	 */
	public void setSexOffenderList(List sexOffenderList) {
		this.sexOffenderList = sexOffenderList;
	}

	/**
	 * @return the specialProgramsList
	 */
	public List getSpecialProgramsList() {
		return specialProgramsList;
	}

	/**
	 * @param specialProgramsList the specialProgramsList to set
	 */
	public void setSpecialProgramsList(List specialProgramsList) {
		this.specialProgramsList = specialProgramsList;
	}

	/**
	 * @return the transferUnitList
	 */
	public List getTransferUnitList() {
		return transferUnitList;
	}

	/**
	 * @param transferUnitList the transferUnitList to set
	 */
	public void setTransferUnitList(List transferUnitList) {
		this.transferUnitList = transferUnitList;
	}

	/**
	 * @return the travelPermitsList
	 */
	public List getTravelPermitsList() {
		return travelPermitsList;
	}

	/**
	 * @param travelPermitsList the travelPermitsList to set
	 */
	public void setTravelPermitsList(List travelPermitsList) {
		this.travelPermitsList = travelPermitsList;
	}

	/**
	 * @return the waiversList
	 */
	public List getWaiversList() {
		return waiversList;
	}

	/**
	 * @param waiversList the waiversList to set
	 */
	public void setWaiversList(List waiversList) {
		this.waiversList = waiversList;
	}
}

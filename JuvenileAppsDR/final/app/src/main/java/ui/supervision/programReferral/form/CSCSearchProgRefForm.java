/*
 * Created on Mar 3, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.programReferral.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CSCSearchProgRefForm extends ActionForm
{
//	 Default Elements in all forms
	private String action = "";
	private String secondaryAction = "";
	
	private boolean update = false;
	private boolean delete = false;
	private String selectedValue = "";
	
	
	// FORM SPECIFIC VALUES
	private String superviseeId;
	
	private boolean initated;
	private boolean open;
	private boolean exited;
	
//	holds the case Number selected to be filtered
	private String caseNum;
	private String selectedCaseId;
	private List casesList=new ArrayList(); // CaseClassObj
	
	private String progRefSearchCount = "";
	private List availableProgReferralsList=new ArrayList(); // CSCProgRefSearchBean
	private List filteredProgReferralsList=new ArrayList(); // CSCProgRefSearchBean
	
	 
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.ServletRequest)
	 */
	public void reset(ActionMapping mapping, javax.servlet.http.HttpServletRequest request)
    {
        Object obj = request.getAttribute("clearStatusBoxes");
        if (obj != null)
        {
        	clearCheckBoxes();
        }
        obj = null;
        obj = request.getParameter("clearStatusBoxes");
        if (obj != null)
        {
        	clearCheckBoxes();
        }
    }
	 
	 public void clearCheckBoxes()
	 {
	 	initated=false;
	 	open=false;
	 	exited=false;
	 }
	
	
	public void clearAll()
	{
		action = "";
		secondaryAction = "";
		
		update=false;
		delete=false;
		
		selectedValue = "";
		
		initated=true;
		open=true;
		exited=false;
		
		casesList=new ArrayList(); // CaseClassObj
		caseNum="";
		
		progRefSearchCount = "";
		availableProgReferralsList=new ArrayList(); // CSC Program Referral Search Bean
		filteredProgReferralsList=new ArrayList(); // CSC Program Referral Search Bean
	}
	
	public void clearSuperviseeId()
	{
		superviseeId = "";
	}
	
	public void clearSearch(){
		initated=true;
		open=true;
		exited=false;
	}
	
	public class CaseClassObj{
		
		private String caseNums;
		
		public CaseClassObj(String aCaseNum){
			caseNums=aCaseNum;
		}
		
		/**
		 * @return Returns the caseNum.
		 */
		public String getCaseNums() {
			return caseNums;
		}
		/**
		 * @param caseNum The caseNum to set.
		 */
		public void setCaseNums(String caseNums) {
			this.caseNums = caseNums;
		}
	}
	
	public void addCaseClassObj(String caseNum){
		casesList.add(new CaseClassObj(caseNum));
	}
	
	public CaseClassObj getCaseClassObj(String caseNum){
		return new CaseClassObj(caseNum);
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
	 * @return Returns the caseNum.
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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
	 * @return Returns the exited.
	 */
	public boolean isExited() {
		return exited;
	}
	/**
	 * @param exited The exited to set.
	 */
	public void setExited(boolean exited) {
		this.exited = exited;
	}
	/**
	 * @return Returns the initiated.
	 */
	public boolean isInitated() {
		return initated;
	}
	/**
	 * @param initated The initiated to set.
	 */
	public void setInitated(boolean initated) {
		this.initated = initated;
	}
	/**
	 * @return Returns the open.
	 */
	public boolean isOpen() {
		return open;
	}
	/**
	 * @param open The open to set.
	 */
	public void setOpen(boolean open) {
		this.open = open;
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
	 * @return Returns the casesList.
	 */
	public List getCasesList() {
		return casesList;
	}
	/**
	 * @param casesList The casesList to set.
	 */
	public void setCasesList(List casesList) {
		this.casesList = casesList;
	}

	/**
	 * @return the availableProgReferralsList
	 */
	public List getAvailableProgReferralsList() {
		return availableProgReferralsList;
	}

	/**
	 * @param availableProgReferralsList the availableProgReferralsList to set
	 */
	public void setAvailableProgReferralsList(List availableProgReferralsList) {
		this.availableProgReferralsList = availableProgReferralsList;
	}

	/**
	 * @return the filteredProgReferralsList
	 */
	public List getFilteredProgReferralsList() {
		return filteredProgReferralsList;
	}

	/**
	 * @param filteredProgReferralsList the filteredProgReferralsList to set
	 */
	public void setFilteredProgReferralsList(List filteredProgReferralsList) {
		this.filteredProgReferralsList = filteredProgReferralsList;
	}

	/**
	 * @return the superviseeId
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId the superviseeId to set
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return the progRefSearchCount
	 */
	public String getProgRefSearchCount() {
		return progRefSearchCount;
	}

	/**
	 * @param progRefSearchCount the progRefSearchCount to set
	 */
	public void setProgRefSearchCount(String progRefSearchCount) {
		this.progRefSearchCount = progRefSearchCount;
	}

	/**
	 * @return the selectedCaseId
	 */
	public String getSelectedCaseId() {
		return selectedCaseId;
	}

	/**
	 * @param selectedCaseId the selectedCaseId to set
	 */
	public void setSelectedCaseId(String selectedCaseId) {
		this.selectedCaseId = selectedCaseId;
	}
	
}//END CLASS

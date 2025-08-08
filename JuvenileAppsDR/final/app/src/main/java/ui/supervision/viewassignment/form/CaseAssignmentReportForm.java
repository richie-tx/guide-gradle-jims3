/*
 * Created on Jan 18, 2008
 *
 */
package ui.supervision.viewassignment.form;

import java.util.List;
import org.apache.struts.action.ActionForm;


/**
 * @author cc_rbhat
 *  
 */
public class CaseAssignmentReportForm extends ActionForm {
	private String searchBy;

	private ProgramUnitReport programUnitReport = new ProgramUnitReport();

	private SuperviseeReport superviseeReport = new SuperviseeReport();

	private SingleCaseReport singleCaseReport = new SingleCaseReport();

	private UserReport userReport = new UserReport();

	private WorkgroupReport workgroupReport = new WorkgroupReport();
	
	private String errorMsg;
	
	private String logonUserName;
	
	

	public void initialize() {
		programUnitReport = new ProgramUnitReport();
		superviseeReport = new SuperviseeReport();
		singleCaseReport = new SingleCaseReport();
		userReport = new UserReport();
		workgroupReport = new WorkgroupReport();
		this.errorMsg = "";
		this.searchBy = "programUnit";
	}
	
	public List getSearchResults() {
		List results = null;
		if (searchBy.equalsIgnoreCase("individualCase")) {
			results = singleCaseReport.getSearchResults();
		} else if (searchBy.equalsIgnoreCase("programunit")) {
			results = programUnitReport.getSearchResults();
		} else if (searchBy.equalsIgnoreCase("supervisee")) {
			results = superviseeReport.getSearchResults();
		} else if (searchBy.equalsIgnoreCase("user")) {
			results = userReport.getSearchResults();
		} else if (searchBy.equalsIgnoreCase("workgroup")) {
			results = workgroupReport.getSearchResults();
		}
		return results;
	}

	/**
	 * @return Returns the programUnitReport.
	 */
	public ProgramUnitReport getProgramUnitReport() {
		return programUnitReport;
	}

	/**
	 * @return Returns the searchBy.
	 */
	public String getSearchBy() {
		return searchBy;
	}

	/**
	 * @return Returns the singleCaseReport.
	 */
	public SingleCaseReport getSingleCaseReport() {
		return singleCaseReport;
	}

	/**
	 * @return Returns the superviseeReport.
	 */
	public SuperviseeReport getSuperviseeReport() {
		return superviseeReport;
	}
	
	public String getLogonUserName() {
		return logonUserName;
	}

	public void setLogonUserName(String logonUserName) {
		this.logonUserName = logonUserName;
	}
	

	/**
	 * @return Returns the userReport.
	 */
	public UserReport getUserReport() {
		return userReport;
	}

	/**
	 * @return Returns the workgroupReport.
	 */
	public WorkgroupReport getWorkgroupReport() {
		return workgroupReport;
	}

	/**
	 * @param programUnitReport
	 *            The programUnitReport to set.
	 */
	public void setProgramUnitReport(ProgramUnitReport programUnitReport) {
		this.programUnitReport = programUnitReport;
	}

	/**
	 * @param searchBy
	 *            The searchBy to set.
	 */
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}

	/**
	 * @param singleCaseReport
	 *            The singleCaseReport to set.
	 */
	public void setSingleCaseReport(SingleCaseReport singleCaseReport) {
		this.singleCaseReport = singleCaseReport;
	}

	/**
	 * @param superviseeReport
	 *            The superviseeReport to set.
	 */
	public void setSuperviseeReport(SuperviseeReport superviseeReport) {
		this.superviseeReport = superviseeReport;
	}

	/**
	 * @param userReport
	 *            The userReport to set.
	 */
	public void setUserReport(UserReport userReport) {
		this.userReport = userReport;
	}

	/**
	 * @param workgroupReport
	 *            The workgroupReport to set.
	 */
	public void setWorkgroupReport(WorkgroupReport workgroupReport) {
		this.workgroupReport = workgroupReport;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
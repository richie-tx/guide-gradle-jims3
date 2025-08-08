/*
 * Created on February 25, 2009
 */
package ui.supervision.posttrial.form;

import org.apache.struts.action.ActionForm;

/**
 * @author cshimek
 *  
 */
public class CaseAssignmentDataControlHeaderForm extends ActionForm 
{
	private String superviseeName;
	private String spn;
	private String ssn;	
	private String sex;
	private String dob;
	private String caseNum;
	private String cdi;
	private String courtNum;
	private boolean caseActivelySupervised;
	private boolean superviseeActivelySupervised;
	
    public void clear()
    {
    	this.superviseeName = "";
    	this.spn = "";
    	this.ssn = "";
    	this.sex = "";
    	this.caseNum = "";
    	this.dob = "";
    	this.cdi = "";
    	this.courtNum = "";
    	this.caseActivelySupervised = false;
    	this.superviseeActivelySupervised = false;

    }

	/**
	 * @return the superviseeName
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName the superviseeName to set
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
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
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the caseNum
	 */
	public String getCaseNum() {
		return caseNum;
	}

	/**
	 * @param caseNum the caseNum to set
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
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
	 * @return the courtNum
	 */
	public String getCourtNum() {
		return courtNum;
	}

	/**
	 * @param courtNum the courtNum to set
	 */
	public void setCourtNum(String courtNum) {
		this.courtNum = courtNum;
	}

	/**
	 * @return the caseActivelySupervised
	 */
	public boolean isCaseActivelySupervised() {
		return caseActivelySupervised;
	}

	/**
	 * @param caseActivelySupervised the caseActivelySupervised to set
	 */
	public void setCaseActivelySupervised(boolean caseActivelySupervised) {
		this.caseActivelySupervised = caseActivelySupervised;
	}

	/**
	 * @return the superviseeActivelySupervised
	 */
	public boolean isSuperviseeActivelySupervised() {
		return superviseeActivelySupervised;
	}

	/**
	 * @param superviseeActivelySupervised the superviseeActivelySupervised to set
	 */
	public void setSuperviseeActivelySupervised(boolean superviseeActivelySupervised) {
		this.superviseeActivelySupervised = superviseeActivelySupervised;
	}
}
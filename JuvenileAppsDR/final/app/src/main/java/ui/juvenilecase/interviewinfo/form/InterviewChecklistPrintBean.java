/*
 * Created on Aug 6, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.interviewinfo.form;

import java.util.ArrayList;
import java.util.Collection;

import ui.common.Name;

/**
 * @author awidjaja
 * This bean is to be used to transfer information
 * to the Printing Utility, for printing Interview Checklist
 */
public class InterviewChecklistPrintBean {

	private Collection checklist;
	private String urlPrefix;
	private String juvenileNumber;
	private Name juvenileName;
	private String casefileNumber;
	
	public InterviewChecklistPrintBean() {
		checklist = new ArrayList();
		urlPrefix = "";
	}
	/**
	 * @return Returns the checklist.
	 */
	public Collection getChecklist() {
		return checklist;
	}
	/**
	 * @param checklist The checklist to set.
	 */
	public void setChecklist(Collection checklist) {
		this.checklist = checklist;
	}
	
	public void clear() {
		checklist = new ArrayList();
	}
	
	/**
	 * @return Returns the urlPrefix.
	 */
	public String getUrlPrefix() {
		return urlPrefix;
	}
	/**
	 * @param urlPrefix The urlPrefix to set.
	 */
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
	/**
	 * @return Returns the casefileNumber.
	 */
	public String getCasefileNumber() {
		return casefileNumber;
	}
	/**
	 * @param casefileNumber The casefileNumber to set.
	 */
	public void setCasefileNumber(String casefileNumber) {
		this.casefileNumber = casefileNumber;
	}
	/**
	 * @return Returns the juvenileName.
	 */
	public Name getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName The juvenileName to set.
	 */
	public void setJuvenileName(Name juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
}

/*
 * Created on Feb 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.administercasenotes;

import java.util.Collection;

import ui.supervision.administercasenotes.form.CasenoteJournalForm;
import ui.supervision.administercasenotes.form.SuperviseeInfoHeaderForm;

/**
 * @author cc_rsojitrawala
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CasenotesJournalBean {
	private Collection events = null;
	private SuperviseeInfoHeaderForm supInfoHeaderForm = null;
	private CasenoteJournalForm casenoteJournalForm = null;
	private String superviseeName = null;
	private String officerName = null;
	
	/**
	 * @return Returns the supInfoHeaderForm.
	 */
	public SuperviseeInfoHeaderForm getSupInfoHeaderForm() {
		return supInfoHeaderForm;
	}
	/**
	 * @param supInfoHeaderForm The supInfoHeaderForm to set.
	 */
	public void setSupInfoHeaderForm(SuperviseeInfoHeaderForm supInfoHeaderForm) {
		this.supInfoHeaderForm = supInfoHeaderForm;
	}
	
	/**
	 * @return Returns the casenoteJournalForm.
	 */
	public CasenoteJournalForm getCasenoteJournalForm() {
		return casenoteJournalForm;
	}
	/**
	 * @param casenoteJournalForm The casenoteJournalForm to set.
	 */
	public void setCasenoteJournalForm(CasenoteJournalForm casenoteJournalForm) {
		this.casenoteJournalForm = casenoteJournalForm;
	}
	/**
	 * @param officerName The officerName to set.
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @param superviseeName The superviseeName to set.
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}
	/**
	 * @return Returns the superviseeName.
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}
	/**
	 * @return Returns the officerName.
	 */
	public String getOfficerName() {
		return officerName;
	}
	public Collection getEvents() {
		return events;
	}
	public void setEvents(Collection events) {
		this.events = events;
	}
}

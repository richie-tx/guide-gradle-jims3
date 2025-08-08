/*
 * Created on Sep 21, 2007
 *
 */
package ui.juvenilecase.casefile.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import messaging.juvenile.reply.JJSOffenseResponseEvent;

/**
 * @author awidjaja
 * This bean is just for containing data to be passed to CommonAppReportPrintBean
 */
public class JJSReferralBean {
	private String referralId;
	private Date referralDate;
	
	//List of JJSOffenseResponseEvent: offenseDescription, 
	//citationSource, citationCode, catagory 
	private List offenses; 
	
	//List of JJSChargeResponseEvent: offense/allegation, 
	//penalCategory (currently missing citationSource), levelDegree
	private List petitions; 
	//user story 11029/task changes
	private List<JJSOffenseResponseEvent> dispositions;
	
	private String dispositionCode; //courtDispositionId from JJSReferral
	private Date dispositionDate; //Either courtDate or intakeDate
	
	public JJSReferralBean(){
		referralId = "";
		referralDate = new Date();
		offenses = new ArrayList();
		petitions = new ArrayList();
		dispositionCode = "";
		dispositionDate = new Date();
	}

	/**
	 * @return Returns the dispositionCode.
	 */
	public String getDispositionCode() {
		return dispositionCode;
	}
	/**
	 * @param dispositionCode The dispositionCode to set.
	 */
	public void setDispositionCode(String dispositionCode) {
		if(dispositionCode != null) {
			this.dispositionCode = dispositionCode;
		}
	}
	/**
	 * @return Returns the dispositionDate.
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}
	/**
	 * @param dispositionDate The dispositionDate to set.
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}
	/**
	 * @return Returns the offenses.
	 */
	public List getOffenses() {
		return offenses;
	}
	/**
	 * @param offenses The offenses to set.
	 */
	public void setOffenses(List offenses) {
		if(offenses != null) {
			this.offenses = offenses;
		}
	}
	/**
	 * @return Returns the petitions.
	 */
	public List getPetitions() {
		return petitions;
	}
	/**
	 * @param petitions The petitions to set.
	 */
	public void setPetitions(List petitions) {
		if(petitions != null) {
			this.petitions = petitions;
		}
	}
	/**
	 * @return Returns the referralDate.
	 */
	public Date getReferralDate() {
		return referralDate;
	}
	/**
	 * @param referralDate The referralDate to set.
	 */
	public void setReferralDate(Date referralDate) {
		this.referralDate = referralDate;
	}
	public String getReferralId() {
		return referralId;
	}
	public void setReferralId(String referralId) {
		this.referralId = referralId;
	}

	/**
	 * @param dispositions the dispositions to set
	 */
	public void setDispositions(List dispositions) {
		this.dispositions = dispositions;
	}

	/**
	 * @return the dispositions
	 */
	public List getDispositions() {
		return dispositions;
	}
}

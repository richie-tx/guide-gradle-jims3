/*
 * Created on Dec 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.legacyupdates;

import java.util.Date;

/**
 * @author mchowdhury
 *
 */
public class UpdateLevelOfSupervisionDataEvent extends LegacyUpdatesRequestEvent {
    private String action;

	private String buildT20Ind;
	private String comments;
	private String criminalCaseId;
	private String los;
	private String olos;
	private Date transactionDate;
	
	public String getAction() {
		return action;
	}
	public String getBuildT20Ind() {
		return buildT20Ind;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	
    /**
	 * @return the los
	 */
	public String getLos() {
		return los;
	}
    
    public String getOlos() {
		return olos;
	}
    /**
	 * @return the transactionDate
	 */
	public Date getTransactionDate() {
		return transactionDate;
	}
    
	public void setAction(String action) {
		this.action = action;
	}
	public void setBuildT20Ind(String buildT20Ind) {
		this.buildT20Ind = buildT20Ind;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @param los the los to set
	 */
	public void setLos(String los) {
		this.los = los;
	}
	public void setOlos(String olos) {
		this.olos = olos;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
}

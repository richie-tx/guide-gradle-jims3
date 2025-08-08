//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCLawViolationEvent extends RequestEvent 
{
    private String caseId;
    private String crt;
    private Date offenseDate;
    private String offenseLiteral;
    private String offenseDegree;
    private String lawViolationId;    
    private String offenseLevel;
    private boolean manualAdded;
	
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	/**
	 * @return Returns the offenseLevel.
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}
	/**
	 * @param offenseLevel The offenseLevel to set.
	 */
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}
    /**
	 * @return Returns the caseId.
	 */
	public String getCaseId() {
		return caseId;
	}
	/**
	 * @param caseId The caseId to set.
	 */
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	/**
	 * @return Returns the crt.
	 */
	public String getCrt() {
		return crt;
	}
	/**
	 * @param crt The crt to set.
	 */
	public void setCrt(String crt) {
		this.crt = crt;
	}
	/**
	 * @return Returns the lawViolationId.
	 */
	public String getLawViolationId() {
		return lawViolationId;
	}
	/**
	 * @param lawViolationId The lawViolationId to set.
	 */
	public void setLawViolationId(String lawViolationId) {
		this.lawViolationId = lawViolationId;
	}

	/**
	 * @return Returns the offenseDate.
	 */
	public Date getOffenseDate() {
		return offenseDate;
	}
	/**
	 * @param offenseDate The offenseDate to set.
	 */
	public void setOffenseDate(Date offenseDate) {
		this.offenseDate = offenseDate;
	}
	/**
	 * @return Returns the offenseDegree.
	 */
	public String getOffenseDegree() {
		return offenseDegree;
	}
	/**
	 * @param offenseDegree The offenseDegree to set.
	 */
	public void setOffenseDegree(String offenseDegree) {
		this.offenseDegree = offenseDegree;
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
}

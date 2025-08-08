/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class NCLawViolationResponseEvent extends ResponseEvent 
{
    private String caseId;
    private String courtId;
    private Date offenseDate;
    private String offenseLitrel;
    private String offenseLevel;
    private String defendantId;
    private String offenseDegree;
    private String ncResponseId;
    private String lawViolationId;
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
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
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
	 * @return Returns the offenseLitrel.
	 */
	public String getOffenseLitrel() {
		return offenseLitrel;
	}
	/**
	 * @param offenseLitrel The offenseLitrel to set.
	 */
	public void setOffenseLitrel(String offenseLitrel) {
		this.offenseLitrel = offenseLitrel;
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
 }

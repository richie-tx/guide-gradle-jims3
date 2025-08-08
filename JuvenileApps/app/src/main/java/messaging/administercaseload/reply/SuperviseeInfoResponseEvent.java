package messaging.administercaseload.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class SuperviseeInfoResponseEvent extends ResponseEvent
{
    private String defendantId;
    private String defendantName;
    private String programUnit;
    private String supervisionLevel;
    private String officerName;
    private boolean superviseeFound;
    private String positionId;
    private String probationOfficerInd;
    private boolean dnaFlagInd;
    private String disposition;
    private String dispositionTypeId;
    private String offenseLevel;
    private String probationStartDate;
    
    public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public boolean isSuperviseeFound() {
		return superviseeFound;
	}
	public void setSuperviseeFound(boolean superviseeFound) {
		this.superviseeFound = superviseeFound;
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
	 * @return Returns the defendantName.
	 */
	public String getDefendantName() {
		return defendantName;
	}
	/**
	 * @param defendantName The defendantName to set.
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	/**
	 * @return Returns the officerName.
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName The officerName to set.
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return Returns the programUnit.
	 */
	public String getProgramUnit() {
		return programUnit;
	}
	/**
	 * @param programUnit The programUnit to set.
	 */
	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}
	/**
	 * @return Returns the supervisionLevel.
	 */
	public String getSupervisionLevel() {
		return supervisionLevel;
	}
	/**
	 * @param supervisionLevel The supervisionLevel to set.
	 */
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}
	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}
	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
	/**
	 * @return the disposition
	 */
	public String getDisposition() {
		return disposition;
	}
	/**
	 * @param disposition the disposition to set
	 */
	public void setDisposition(String disposition) {
		this.disposition = disposition;
	}
	
	/**
	 * @return the dispositionTypeId
	 */
	public String getDispositionTypeId() {
		return dispositionTypeId;
	}
	/**
	 * @param dispositionTypeId the dispositionTypeId to set
	 */
	public void setDispositionTypeId(String dispositionTypeId) {
		this.dispositionTypeId = dispositionTypeId;
	}
	/**
	 * @return the offenseLevel
	 */
	public String getOffenseLevel() {
		return offenseLevel;
	}
	/**
	 * @param offenseLevel the offenseLevel to set
	 */
	public void setOffenseLevel(String offenseLevel) {
		this.offenseLevel = offenseLevel;
	}
	/**
	 * @return the probationStartDate
	 */
	public String getProbationStartDate() {
		return probationStartDate;
	}
	/**
	 * @param probationStartDate the probationStartDate to set
	 */
	public void setProbationStartDate(String probationStartDate) {
		this.probationStartDate = probationStartDate;
	}
	
}

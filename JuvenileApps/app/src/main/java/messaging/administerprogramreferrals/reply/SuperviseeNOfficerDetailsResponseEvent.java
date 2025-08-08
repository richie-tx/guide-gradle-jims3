package messaging.administerprogramreferrals.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

public class SuperviseeNOfficerDetailsResponseEvent extends ResponseEvent
{
	private String defendantId;
	private String superviseeName;
	private String superviseePhoneNum;
	private Date superviseeDOB;
	private String offenseId;
	private String offenseDesc;
	private String crt;
	
	private String officerName;
	private String positionId;
	private String positionPOI;
	private String positionName;
	private String programUnitDesc;
	private String officerPhoneNum;
	
	
	
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
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
	 * @return the superviseePhoneNum
	 */
	public String getSuperviseePhoneNum() {
		return superviseePhoneNum;
	}
	/**
	 * @param superviseePhoneNum the superviseePhoneNum to set
	 */
	public void setSuperviseePhoneNum(String superviseePhoneNum) {
		this.superviseePhoneNum = superviseePhoneNum;
	}
	/**
	 * @return the superviseeDOB
	 */
	public Date getSuperviseeDOB() {
		return superviseeDOB;
	}
	/**
	 * @param superviseeDOB the superviseeDOB to set
	 */
	public void setSuperviseeDOB(Date superviseeDOB) {
		this.superviseeDOB = superviseeDOB;
	}
	/**
	 * @return the offenseId
	 */
	public String getOffenseId() {
		return offenseId;
	}
	/**
	 * @param offenseId the offenseId to set
	 */
	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}
	/**
	 * @return the offenseDesc
	 */
	public String getOffenseDesc() {
		return offenseDesc;
	}
	/**
	 * @param offenseDesc the offenseDesc to set
	 */
	public void setOffenseDesc(String offenseDesc) {
		this.offenseDesc = offenseDesc;
	}
	/**
	 * @return the crt
	 */
	public String getCrt() {
		return crt;
	}
	/**
	 * @param crt the crt to set
	 */
	public void setCrt(String crt) {
		this.crt = crt;
	}
	/**
	 * @return the officerName
	 */
	public String getOfficerName() {
		return officerName;
	}
	/**
	 * @param officerName the officerName to set
	 */
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	/**
	 * @return the positionId
	 */
	public String getPositionId() {
		return positionId;
	}
	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	/**
	 * @return the positionPOI
	 */
	public String getPositionPOI() {
		return positionPOI;
	}
	/**
	 * @param positionPOI the positionPOI to set
	 */
	public void setPositionPOI(String positionPOI) {
		this.positionPOI = positionPOI;
	}
	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @return the programUnitDesc
	 */
	public String getProgramUnitDesc() {
		return programUnitDesc;
	}
	/**
	 * @param programUnitDesc the programUnitDesc to set
	 */
	public void setProgramUnitDesc(String programUnitDesc) {
		this.programUnitDesc = programUnitDesc;
	}
	/**
	 * @return the officerPhoneNum
	 */
	public String getOfficerPhoneNum() {
		return officerPhoneNum;
	}
	/**
	 * @param officerPhoneNum the officerPhoneNum to set
	 */
	public void setOfficerPhoneNum(String officerPhoneNum) {
		this.officerPhoneNum = officerPhoneNum;
	}
	
}

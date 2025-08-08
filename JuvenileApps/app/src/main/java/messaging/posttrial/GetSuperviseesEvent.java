/*
 * Created on May 29, 2008
 *
 */
package messaging.posttrial;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSuperviseesEvent extends RequestEvent
{
	private String lastName;
	private String middleName;
	private String firstName;
	private Date dateOfBirth;
	private String raceId;
	private String sexId;
	private String ssn;
	private String dlStateId;
	private String dlNumber;
	private String sidNumber;
	private String fbiNumber;
	private String cjisNumber;
	private String spn;
	private String cdi;
	private String caseNum;
	
	
	public String getCdi() {
		return cdi;
	}
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	public String getCaseNum() {
		return caseNum;
	}
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	/**
	 * @return the cjisNumber
	 */
	public String getCjisNumber() {
		return cjisNumber;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @return the dlNumber
	 */
	public String getDlNumber() {
		return dlNumber;
	}
	/**
	 * @return the dlStateId
	 */
	public String getDlStateId() {
		return dlStateId;
	}
	/**
	 * @return the fbiNumber
	 */
	public String getFbiNumber() {
		return fbiNumber;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @return the raceId
	 */
	public String getRaceId() {
		return raceId;
	}
	/**
	 * @return the sexed
	 */
	public String getSexId() {
		return sexId;
	}
	/**
	 * @return the sidNumber
	 */
	public String getSidNumber() {
		return sidNumber;
	}
	/**
	 * @return the spn
	 */
	public String getSpn() {
		return spn;
	}
	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param cjisNumber the cjisNumber to set
	 */
	public void setCjisNumber(String cjisNumber) {
		this.cjisNumber = cjisNumber;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @param dlNumber the dlNumber to set
	 */
	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}
	/**
	 * @param dlStateId the dlStateId to set
	 */
	public void setDlStateId(String dlStateId) {
		this.dlStateId = dlStateId;
	}
	/**
	 * @param fbiNumber the fbiNumber to set
	 */
	public void setFbiNumber(String fbiNumber) {
		this.fbiNumber = fbiNumber;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @param raceId the raceId to set
	 */
	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}
	/**
	 * @param sexed the sexed to set
	 */
	public void setSexId(String sexId) {
		this.sexId = sexId;
	}
	/**
	 * @param sidNumber the sidNumber to set
	 */
	public void setSidNumber(String sidNumber) {
		this.sidNumber = sidNumber;
	}
	/**
	 * @param spn the spn to set
	 */
	public void setSpn(String spn) {
		this.spn = spn;
	}
	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
}

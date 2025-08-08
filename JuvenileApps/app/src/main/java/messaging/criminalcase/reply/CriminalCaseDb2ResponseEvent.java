package messaging.criminalcase.reply;

import mojo.km.messaging.ResponseEvent;

public class CriminalCaseDb2ResponseEvent extends ResponseEvent{
	
	private String caseStatusId;
	private String caseNum;
	private String courtId;
	private String cdi;
	private String criminalCaseId;
	private String defendantId;
	private String defendantName;
	private String defendantStatusId;
	private String caseFilingDate;
	private String offenseCodeId;
	private String probationOfficerId;
	private String sequenceNum;
	private String city;
	private String streetName;
	private String adSequenceNum;
	private String zipCode;
	
	
	
	/**
	 * 
	 * @return
	 */
	public String getCaseStatusId() {
		return caseStatusId;
	}
	/**
	 * 
	 * @param caseStatusId
	 */
	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCourtId() {
		return courtId;
	}
	
	/**
	 * 
	 * @param courtId
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDefendantId() {
		return defendantId;
	}
	
	/**
	 * 
	 * @param defendantId
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDefendantName() {
		return defendantName;
	}
	
	/**
	 * 
	 * @param defendantName
	 */
	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDefendantStatusId() {
		return defendantStatusId;
	}
	
	/**
	 * 
	 * @param defendantStatusId
	 */
	public void setDefendantStatusId(String defendantStatusId) {
		this.defendantStatusId = defendantStatusId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOffenseCodeId() {
		return offenseCodeId;
	}
	
	/**
	 * 
	 * @param offenseCodeId
	 */
	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProbationOfficerId() {
		return probationOfficerId;
	}
	
	/**
	 * 
	 * @param probationOfficerId
	 */
	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/**
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCaseNum() {
		return caseNum;
	}
	
	/**
	 * 
	 * @param caseNum
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCdi() {
		return cdi;
	}
	
	/**
	 * 
	 * @param cdi
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	
	/**
	 * 
	 * @param criminalCaseId
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCaseFilingDate() {
		return caseFilingDate;
	}
	
	/**
	 * 
	 * @param caseFilingDate
	 */
	public void setCaseFilingDate(String caseFilingDate) {
		this.caseFilingDate = caseFilingDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}
	
	/**
	 * 
	 * @param sequenceNum
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	/**
	 * 
	 * @return
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 
	 * @return
	 */
	public String getStreetName() {
		return streetName;
	}
	
	/**
	 * 
	 * @param streetName
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getAdSequenceNum() {
		return adSequenceNum;
	}
	
	/**
	 * 
	 * @param adSequenceNum
	 */
	public void setAdSequenceNum(String adSequenceNum) {
		this.adSequenceNum = adSequenceNum;
	}
	

}

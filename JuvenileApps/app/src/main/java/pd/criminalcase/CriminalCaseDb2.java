package pd.criminalcase;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;


/**
 * @stereotype entity
 */
public class CriminalCaseDb2 extends PersistentObject{
	
	private String defendantId;
	private String defendantName;
	private String defendantStatusId;
	private String caseNum;
	private String caseStatusId;
	private String courtDivisionId;
	private String courtId;
	private String criminalCaseId;
	private String probationOfficerId;
	private String filingDate;
	private String offenseCodeId;
	private String sequenceNum;
	private String adSequenceNum;
	private String city;
	private String streetName;
	private String zipCode;
	
	
	static public CriminalCaseDb2 find(String aCaseId) {
		IHome home = new Home();
		return ( CriminalCaseDb2 ) home.find(aCaseId, CriminalCaseDb2.class);
	}
	
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, CriminalCaseDb2.class);
	}
	
	
	public String getDefendantId() {
		return defendantId;
	}

	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}

	public String getDefendantName() {
		return defendantName;
	}

	public void setDefendantName(String defendantName) {
		this.defendantName = defendantName;
	}

	public String getDefendantStatusId() {
		return defendantStatusId;
	}

	public void setDefendantStatusId(String defendantStatusId) {
		this.defendantStatusId = defendantStatusId;
	}

	public String getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}

	public String getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(String caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public String getCourtDivisionId() {
		return courtDivisionId;
	}

	public void setCourtDivisionId(String courtDivisionId) {
		this.courtDivisionId = courtDivisionId;
	}

	public String getCourtId() {
		return courtId;
	}

	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	public String getCriminalCaseId() {
		return criminalCaseId;
	}

	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}

	public String getProbationOfficerId() {
		return probationOfficerId;
	}

	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}

	public String getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(String filingDate) {
		this.filingDate = filingDate;
	}

	public String getOffenseCodeId() {
		return offenseCodeId;
	}

	public void setOffenseCodeId(String offenseCodeId) {
		this.offenseCodeId = offenseCodeId;
	}

	public String getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	public String getAdSequenceNum() {
		return adSequenceNum;
	}
	public void setAdSequenceNum(String adSequenceNum) {
		this.adSequenceNum = adSequenceNum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}

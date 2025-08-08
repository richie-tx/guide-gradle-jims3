//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administercasenotes\\CasenoteEvent.java

package messaging.administercasenotes.reply;

import java.util.Date;
import java.util.List;
import mojo.km.messaging.ResponseEvent;

public class CasenotePrintResponseEvent extends ResponseEvent
{
	
	private String probationOfficerId;
	private String officerPosition;
	private String officerName;
	private String superviseeName;
	private String defendantId;
	private String connectionCd;
	private Date dateOfBirth;
	private String race;
	private String sex;
	private String programUnit;
	private Date nextContactDate;
	private Date nextContactTime;
	private String contactMethod;
	private String criminalCaseId;
	private List cases;
	private List casenoteResults;
	/**
    * @roseuid 44F4616F0118
    */
   public CasenotePrintResponseEvent() 
   {
	  
   }




	public String getProbationOfficerId() {
		return probationOfficerId;
	}




	public void setProbationOfficerId(String probationOfficerId) {
		this.probationOfficerId = probationOfficerId;
	}




	public String getOfficerPosition() {
		return officerPosition;
	}




	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}




	public String getOfficerName() {
		return officerName;
	}




	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}




	public String getSuperviseeName() {
		return superviseeName;
	}




	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}




	public String getDefendantId() {
		return defendantId;
	}




	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}




	public String getConnectionCd() {
		return connectionCd;
	}




	public void setConnectionCd(String connectionCd) {
		this.connectionCd = connectionCd;
	}




	public Date getDateOfBirth() {
		return dateOfBirth;
	}




	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}




	public String getRace() {
		return race;
	}




	public void setRace(String race) {
		this.race = race;
	}




	public String getSex() {
		return sex;
	}




	public void setSex(String sex) {
		this.sex = sex;
	}




	public String getProgramUnit() {
		return programUnit;
	}




	public void setProgramUnit(String programUnit) {
		this.programUnit = programUnit;
	}




	public Date getNextContactDate() {
		return nextContactDate;
	}




	public void setNextContactDate(Date nextContactDate) {
		this.nextContactDate = nextContactDate;
	}




	public Date getNextContactTime() {
		return nextContactTime;
	}




	public void setNextContactTime(Date nextContactTime) {
		this.nextContactTime = nextContactTime;
	}




	public String getContactMethod() {
		return contactMethod;
	}




	public void setContactMethod(String nextContactMethod) {
		this.contactMethod = nextContactMethod;
	}




	public String getCriminalCaseId() {
		return criminalCaseId;
	}




	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}




	public List getCasenoteResults() {
		return casenoteResults;
	}




	public void setCasenoteResults(List caseNoteResults) {
		this.casenoteResults = caseNoteResults;
	}




	public List getCases() {
		return cases;
	}




	public void setCases(List cases) {
		this.cases = cases;
	}
   
		
}

package messaging.administercaseload.reply;

import java.util.ArrayList;
import java.util.List;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

public class LightCSCDStaffResponseEvent extends ResponseEvent {
   
	private String divisionId;
	private String divisionName;
	private String jobTitleCD;
	private String staffPositionId;
	private String officerLogonId;
	private String officerNameQualifiedByPosition;
	private String officerName;
	private String staffPositionName;
	private String staffPositionType;
	private String sPPhoneNumber;
	private Name supervisorName;
	private Name supervisorSupervisorName;
	private String supervisorSupervisorPositionId;
	private String probationOfficerInd;
	private String parentPositionId;
	private String programUnitId;
	
	public String getProgramUnitId() {
		return programUnitId;
	}
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	public String getParentPositionId() {
		return parentPositionId;
	}
	public void setParentPositionId(String parentPositionId) {
		this.parentPositionId = parentPositionId;
	}
	private List courts;
	
	public List getCourts() {
		return courts;
	}
	public Name getSupervisorSupervisorName() {
		return supervisorSupervisorName;
	}
	public void setSupervisorSupervisorName(Name supervisorSupervisorName) {
		this.supervisorSupervisorName = supervisorSupervisorName;
	}
	public String getSupervisorSupervisorPositionId() {
		return supervisorSupervisorPositionId;
	}
	public void setSupervisorSupervisorPositionId(
			String supervisorSupervisorPositionId) {
		this.supervisorSupervisorPositionId = supervisorSupervisorPositionId;
	}
	public Name getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(Name supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getStaffPositionName() {
		return staffPositionName;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	private String supervisorPositionId;
	
	public String getSupervisorPositionId() {
		return supervisorPositionId;
	}
	public void setSupervisorPositionId(String supervisorPositionId) {
		this.supervisorPositionId = supervisorPositionId;
	}
	private String supervisorNameQualifiedByPosition;
	
	public String getSupervisorNameQualifiedByPosition() {
		return supervisorNameQualifiedByPosition;
	}
	public void setSupervisorNameQualifiedByPosition(
			String supervisorNameQualifiedByPosition) {
		this.supervisorNameQualifiedByPosition = supervisorNameQualifiedByPosition;
	}
	public String getOfficerNameQualifiedByPosition() {
		return officerNameQualifiedByPosition;
	}
	public void setOfficerNameQualifiedByPosition(
			String officerNameQualifiedByPosition) {
		this.officerNameQualifiedByPosition = officerNameQualifiedByPosition;
	}
	public String getOfficerLogonId() {
		return officerLogonId;
	}
	public void setOfficerLogonId(String officerLogonId) {
		this.officerLogonId = officerLogonId;
	}
	public String getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public String getJobTitleCD() {
		return jobTitleCD;
	}
	public void setJobTitleCD(String jobTitleCD) {
		this.jobTitleCD = jobTitleCD;
	}
	public String getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	public String getDivisionName() {
		return divisionName;
	}
	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}
	public String getProbationOfficerInd() {
		return probationOfficerInd;
	}
	public void setProbationOfficerInd(String probationOfficerInd) {
		this.probationOfficerInd = probationOfficerInd;
	}
	public void setStaffPositionName(String positionName) {
		this.staffPositionName = positionName;		
	}
	public String getStaffPositionType() {
		return staffPositionType;
	}
	public void setStaffPositionType(String staffPositionType) {
		this.staffPositionType = staffPositionType;
	}
	/**
	 * @return the sPPhoneNumber
	 */
	public String getSPPhoneNumber() {
		return sPPhoneNumber;
	}
	/**
	 * @param sPPhoneNumber the sPPhoneNumber to set
	 */
	public void setSPPhoneNumber(String sPPhoneNumber) {
		this.sPPhoneNumber = sPPhoneNumber;
	}
	public void addCourt(String courtId){
        if (courts == null){
            courts = new ArrayList();
        }
        courts.add(courtId);
    }
}

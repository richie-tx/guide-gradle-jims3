package messaging.administersupervisee.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import messaging.codetable.person.reply.ScarsMarksTattoosCodeResponseEvent;
import messaging.contact.reply.EmployerResponseEvent;
import messaging.contact.to.Address;
import messaging.transfers.reply.TransferResponseEvent;
import mojo.km.messaging.ResponseEvent;

public class SuperviseeDetailResponseEvent extends ResponseEvent {

	private String advancedDegree;

	private String assessedLevel;

	private Date assessmentDate;

	private String assessmentMethod;

	private String assignedProgramUnitId;

	private String assignedProgramUnitName;

	private String assignedStaffPositionId;

	private String birthPlaceId;

	private String birthStateCountryId;

	private String buildId;

	private String caseloadCreditStaffPositionId;

	private String caseloadCreditStaffPositionName;

	private String comments;

	private String complexionId;

	private Date createDate;

	private Address currentAddress;

	private EmployerResponseEvent currentEmployer;

	private SupervisionLevelResponseEvent currentLevelOfSupervision;

	private boolean currentlySupervised;

	private Date dateOfBirth;
	
	private Date dnaCollectedDate;
	
	private boolean dnaFlagInd;

	private String descriptionSourceId;

	private String driversLicenseNum;

	private String driversLicenseStateId;

	private String educationLevel; // highest Grade completed

	private String ethnicityId;

	private String eyeColorId;

	 
	private String fbiNum;
	private Boolean fingerprinted;

	private String firstName;

	private Date gedDate;

	private boolean gedInd;

	// EducationAssessment attributes

	private boolean gedVerifiedInd;
	
	private String hairColorId;
	
	private boolean hasAssementData;

	private boolean hasReportedEducationData;

	private String height;

	private Date highSchoolDiplomaDate;

	private boolean highSchoolDiplomaInd;

	private boolean highSchoolDiplomaVerifiedInd;

	private Collection history;

	private String homePhoneNum;

	// ReportedEducation Attributes

	private Date intakeDate;

	private String lastName;

	private List levelOfSupervisionHistories; // List of
												// SupervisionLevelResponseEvents
	private List programTrackerHistories;
	
	private List dnaHistories;
	
	private Date losEffectiveDate;

	private String maritalStatusId;

	private String middleName;
	private Date programTrackerEffectiveDate;
	private Date programTrackerEndDate;
	private String programTrackerId;
	private Date programUnitAssignmentDate;
	private String raceId;
	// private Code reportedLevel;
	private String reportedLevelId;
	private List scars; // list of ScarsMarksTattoosCodeResponseEvents
	private String sexId;
	private String sidNum;

	private String ssn;
	
	private List superviseeHistory;
	private List tattoos; // list of ScarsMarksTattoosCodeResponseEvents
	
	private List transfers; // List of TransferResponseEvents

	private String usCitizenId;

	private String weight;

	/**
	 * @param re
	 */
	public void addLevelOfSupervisionHistories(SupervisionLevelResponseEvent re) {
		if (levelOfSupervisionHistories == null) {
			levelOfSupervisionHistories = new ArrayList();
		}
		levelOfSupervisionHistories.add(re);
	}

	/**
	 * @param re
	 */
	public void addScars(ScarsMarksTattoosCodeResponseEvent re) {
		if (scars == null) {
			scars = new ArrayList();
		}
		scars.add(re);
	}

	/**
	 * @param re
	 */
	public void addTattoos(ScarsMarksTattoosCodeResponseEvent re) {
		if (tattoos == null) {
			tattoos = new ArrayList();
		}
		tattoos.add(re);
	}

	/**
	 * @param re
	 */
	public void addTransfers(TransferResponseEvent re) {
		if (transfers == null) {
			transfers = new ArrayList();
		}
		transfers.add(re);
	}
	public String getAdvancedDegree() {
		return advancedDegree;
	}
	public String getAssessedLevel() {
		return assessedLevel;
	}
	
	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public String getAssessmentMethod() {
		return assessmentMethod;
	}

	public String getAssignedProgramUnitId() {
		return assignedProgramUnitId;
	}

	public String getAssignedProgramUnitName() {
		return assignedProgramUnitName;
	}

	public String getAssignedStaffPositionId() {
		return assignedStaffPositionId;
	}

	public String getBirthPlaceId() {
		return birthPlaceId;
	}

	public String getBirthStateCountryId() {
		return birthStateCountryId;
	}

	public String getBuildId() {
		return buildId;
	}

	public String getCaseloadCreditStaffPositionId() {
		return caseloadCreditStaffPositionId;
	}

	public String getCaseloadCreditStaffPositionName() {
		return caseloadCreditStaffPositionName;
	}

	public String getComments() {
		return comments;
	}

	public String getComplexionId() {
		return complexionId;
	}
	
	/*
	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}
	*/
	
	public Date getCreateDate() {
		return createDate;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public EmployerResponseEvent getCurrentEmployer() {
		return currentEmployer;
	}

	public SupervisionLevelResponseEvent getCurrentLevelOfSupervision() {
		return currentLevelOfSupervision;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getDescriptionSourceId() {
		return descriptionSourceId;
	}
	
	/**
	 * @return the dnaCollectedDate
	 */
	public Date getDnaCollectedDate() {
		return dnaCollectedDate;
	}

	public String getDriversLicenseNum() {
		return driversLicenseNum;
	}

	public String getDriversLicenseStateId() {
		return driversLicenseStateId;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public String getEthnicityId() {
		return ethnicityId;
	}

	public String getEyeColorId() {
		return eyeColorId;
	}

	public String getFbiNum() {
		return fbiNum;
	}

	public Boolean getFingerprinted() {
		return fingerprinted;
	}

	public String getFirstName() {
		return firstName;
	}

	public Date getGedDate() {
		return gedDate;
	}

	public String getHairColorId() {
		return hairColorId;
	}

	public boolean getHasAssementData() {
		return hasAssementData;
	}

	public boolean getHasReportedEducationData() {
		return hasReportedEducationData;
	}

	public String getHeight() {
		return height;
	}

	public Date getHighSchoolDiplomaDate() {
		return highSchoolDiplomaDate;
	}

	public Collection getHistory() {
		return history;
	}

	public String getHomePhoneNum() {
		return homePhoneNum;
	}

	public Date getIntakeDate() {
		return intakeDate;
	}

	public String getLastName() {
		return lastName;
	}

	public List getLevelOfSupervisionHistories() {
		return levelOfSupervisionHistories;
	}

	public Date getLosEffectiveDate() {
		return losEffectiveDate;
	}

	public String getMaritalStatusId() {
		return maritalStatusId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public Date getProgramTrackerEffectiveDate() {
		return programTrackerEffectiveDate;
	}

	public Date getProgramTrackerEndDate() {
		return programTrackerEndDate;
	}

	public String getProgramTrackerId() {
		return programTrackerId;
	}

	public Date getProgramUnitAssignmentDate() {
		return programUnitAssignmentDate;
	}

	public String getRaceId() {
		return raceId;
	}

	public String getReportedLevelId() {
		return reportedLevelId;
	}

	public List getScars() {
		return scars;
	}

	public String getSexId() {
		return sexId;
	}

	public String getSidNum() {
		return sidNum;
	}

	public String getSsn() {
		return ssn;
	}

	public List getSuperviseeHistory() {
		return superviseeHistory;
	}

	public List getTattoos() {
		return tattoos;
	}

	public List getTransfers() {
		return transfers;
	}

	public String getUsCitizenId() {
		return usCitizenId;
	}

	public String getWeight() {
		return weight;
	}

	public boolean isCurrentlySupervised() {
		return currentlySupervised;
	}
	
	/**
	 * @return the dnaFlagInd
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}

	public boolean isGedInd() {
		return gedInd;
	}

	public boolean isGedVerifiedInd() {
		return gedVerifiedInd;
	}

	public boolean isHighSchoolDiplomaInd() {
		return highSchoolDiplomaInd;
	}

	public boolean isHighSchoolDiplomaVerifiedInd() {
		return highSchoolDiplomaVerifiedInd;
	}

	public void setAdvancedDegree(String advancedDegree) {
		this.advancedDegree = advancedDegree;
	}

	public void setAssessedLevel(String assessedLevel) {
		this.assessedLevel = assessedLevel;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public void setAssessmentMethod(String assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	public void setAssignedProgramUnitId(String assignedProgramUnitId) {
		this.assignedProgramUnitId = assignedProgramUnitId;
	}

	public void setAssignedProgramUnitName(String assignedProgramUnitName) {
		this.assignedProgramUnitName = assignedProgramUnitName;
	}

	public void setAssignedStaffPositionId(String assignedStaffPositionId) {
		this.assignedStaffPositionId = assignedStaffPositionId;
	}

	public void setBirthPlaceId(String birthPlaceId) {
		this.birthPlaceId = birthPlaceId;
	}

	public void setBirthStateCountryId(String birthStateCountryId) {
		this.birthStateCountryId = birthStateCountryId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}

	public void setCaseloadCreditStaffPositionId(
			String caseloadCreditStaffPositionId) {
		this.caseloadCreditStaffPositionId = caseloadCreditStaffPositionId;
	}

	public void setCaseloadCreditStaffPositionName(
			String caseloadCreditStaffPositionName) {
		this.caseloadCreditStaffPositionName = caseloadCreditStaffPositionName;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setComplexionId(String complexionId) {
		this.complexionId = complexionId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	/*
	public Code getReportedLevel() {
		return reportedLevel;
	}

	public void setReportedLevel(Code reportedLevel) {
		this.reportedLevel = reportedLevel;
	}
	*/
	
	public void setCurrentEmployer(EmployerResponseEvent currentEmployer) {
		this.currentEmployer = currentEmployer;
	}

	public void setCurrentLevelOfSupervision(SupervisionLevelResponseEvent currentLOS) {
		this.currentLevelOfSupervision = currentLOS;
	}

	public void setCurrentlySupervised(boolean currentlySupervised) {
		this.currentlySupervised = currentlySupervised;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDescriptionSourceId(String descriptionSourceId) {
		this.descriptionSourceId = descriptionSourceId;
	}

	public void setDriversLicenseNum(String driversLicenseNum) {
		this.driversLicenseNum = driversLicenseNum;
	}

	public void setDriversLicenseStateId(String driversLicenseStateId) {
		this.driversLicenseStateId = driversLicenseStateId;
	}
		
	

	/**
	 * @param dnaCollectedDate the dnaCollectedDate to set
	 */
	public void setDnaCollectedDate(Date dnaCollectedDate) {
		this.dnaCollectedDate = dnaCollectedDate;
	}

	/**
	 * @param dnaFlagInd the dnaFlagInd to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public void setEyeColorId(String eyeColorId) {
		this.eyeColorId = eyeColorId;
	}

	public void setFbiNum(String fbiNum) {
		this.fbiNum = fbiNum;
	}

	public void setFingerprinted(Boolean fingerprinted) {
		this.fingerprinted = fingerprinted;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGedDate(Date gedDate) {
		this.gedDate = gedDate;
	}

	public void setGedInd(boolean gedInd) {
		this.gedInd = gedInd;
	}

	public void setGedVerifiedInd(boolean gedVerifiedInd) {
		this.gedVerifiedInd = gedVerifiedInd;
	}

	public void setHairColorId(String hairColorId) {
		this.hairColorId = hairColorId;
	}

	public void setHasAssementData(boolean hasAssementData) {
		this.hasAssementData = hasAssementData;
	}

	public void setHasReportedEducationData(boolean hasReportedEducationData) {
		this.hasReportedEducationData = hasReportedEducationData;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setHighSchoolDiplomaDate(Date highSchoolDiplomaDate) {
		this.highSchoolDiplomaDate = highSchoolDiplomaDate;
	}

	public void setHighSchoolDiplomaInd(boolean highSchoolDiplomaInd) {
		this.highSchoolDiplomaInd = highSchoolDiplomaInd;
	}

	public void setHighSchoolDiplomaVerifiedInd(
			boolean highSchoolDiplomaVerifiedInd) {
		this.highSchoolDiplomaVerifiedInd = highSchoolDiplomaVerifiedInd;
	}

	public void setHistory(Collection history) {
		this.history = history;
	}

	public void setHomePhoneNum(String homePhoneNum) {
		this.homePhoneNum = homePhoneNum;
	}

	public void setIntakeDate(Date intakeDate) {
		this.intakeDate = intakeDate;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLevelOfSupervisionHistories(List levelOfSupervisionHistories) {
		this.levelOfSupervisionHistories = levelOfSupervisionHistories;
	}

	public void setLosEffectiveDate(Date losEffectiveDate) {
		this.losEffectiveDate = losEffectiveDate;
	}

	public void setMaritalStatusId(String maritalStatusId) {
		this.maritalStatusId = maritalStatusId;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setProgramTrackerEffectiveDate(Date programTrackerEffectiveDate) {
		this.programTrackerEffectiveDate = programTrackerEffectiveDate;
	}

	public void setProgramTrackerEndDate(Date programTrackerEndDate) {
		this.programTrackerEndDate = programTrackerEndDate;
	}

	public void setProgramTrackerId(String programTrackerId) {
		this.programTrackerId = programTrackerId;
	}

	public void setProgramUnitAssignmentDate(Date programUnitAssignmentDate) {
		this.programUnitAssignmentDate = programUnitAssignmentDate;
	}

	public void setRaceId(String raceId) {
		this.raceId = raceId;
	}

	public void setReportedLevelId(String reportedLevelId) {
		this.reportedLevelId = reportedLevelId;
	}

	public void setScars(List scars) {
		this.scars = scars;
	}

	public void setSexId(String sexId) {
		this.sexId = sexId;
	}

	public void setSidNum(String sidNum) {
		this.sidNum = sidNum;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public void setSuperviseeHistory(List superviseeHistory) {
		this.superviseeHistory = superviseeHistory;
	}

	public void setTattoos(List tattoos) {
		this.tattoos = tattoos;
	}

	public void setTransfers(List transfers) {
		this.transfers = transfers;
	}

	public void setUsCitizenId(String usCitizenId) {
		this.usCitizenId = usCitizenId;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setProgramTrackerHistories(List programTrackerHistories) {
		this.programTrackerHistories = programTrackerHistories;
	}

	public List getProgramTrackerHistories() {
		return programTrackerHistories;
	}
	
	/**
	 * @return the dnaHistories
	 */
	public List getDnaHistories() {
		return dnaHistories;
	}

	/**
	 * @param dnaHistories the dnaHistories to set
	 */
	public void setDnaHistories(List dnaHistories) {
		this.dnaHistories = dnaHistories;
	}
	
	

}

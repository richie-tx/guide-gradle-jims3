/*
 * Created on Feb 12, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.supervisee.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import messaging.administersupervisee.reply.ProgramTrackerResponseEvent;
import messaging.administersupervisee.reply.SupervisionLevelResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import mojo.km.utilities.DateUtil;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.struts.action.ActionForm;
import org.ujac.util.BeanComparator;

import ui.common.ComplexCodeTableHelper;
import ui.common.SocialSecurity;
import ui.supervision.administercaseload.form.SuperviseeTransferCasesInfo;

/**
 * @author jjose
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
/**
 * @author PAlcocer
 *
 */
/**
 * @author PAlcocer
 *
 */
public class SuperviseeForm extends ActionForm {
	private String confirmMessage = "";
	public String getConfirmMessage() {
		return confirmMessage;
	}

	public void setConfirmMessage(String confirmMessage) {
		this.confirmMessage = confirmMessage;
	}

	private static Collection emptyColl = new ArrayList();

	private boolean listsSet = false;

	private String action = "";

	private String secondaryAction = "";

	private boolean update = false;

	private boolean delete = false;

	private String selectedValue = "";

	private String fromPage = "";
	
	private String tabLink;
	
	private boolean allowLOSUpdates = false;
	private boolean allowProgramTrackerUpdates = false;
	private boolean allowDNAUpdates = false;
	/* SUPERVISEE INFORMATION */
	private String superviseeId = "";

	private String superviseeName;

	private String superviseeNameSource;

	private String superviseeListIndex; // For SSN attribute

	private SocialSecurity ssn = new SocialSecurity("");

	/* DESCRIPTIIVE INFORMATION */
	private String dateOfBirth;

	private String build;

	private String race;

	private String sex;
	
	private String rawHeight;
	
	private String height;
	
	private String heightFeet;

	private String heightInch;

	private String weight;

	private String complexion;

	private String eyeColor;

	private String hairColor;

	private List scarsMarksDescription;

	private List tattoosDescription;

	private String usCitizenshipInd;

	private String fingerprintedInd;

	private String ethnicity;

	private String maritalStatus;

	private String placeOfBirth;

	private String stateCountryOfBirth;

	private String descriptionSource;

	/* ADDRESS INFORMATION */
	private String superviseeStreetNumber;

	private String superviseeStreetName;

	private String superviseeStreetType;

	private String superviseeApartmentNumber;

	private String superviseeCity;

	private String superviseeState;

	private String superviseeZipCode;

	private String superviseeAddressType;

	private String superviseePhoneNumber;

	/* ID NUMBER INFORMATION */
	private String driverLicenseNumber;

	private String driverLicenseState;

	private String stateIdNumber;

	private String fbiNumber;

	/* EMPLOYER INFORMATION */
	private String employerName;

	private String employerOccupation;

	private String employePhoneNum;

	private String employerEmploymentStatus;

	private String employerStreetNum;

	private String employerStreetName;

	private String employerStreetType;

	private String employerAptNum;

	private String employerCity;

	private String employerState;

	private String employerZipCode;

	private String employerAdditionalZipCode;

	/* EDUCATION INFORMATION */
	private String intakeDate;

	private String highestGradeCompleted;

	private String highSchoolDiploma;

	private String GED;

	private String advancedDegreeEarned;

	private String GEDVerified;

	private String GEDAttainedDate;

	private String hsDiplomaVerified;

	private String hsDiplomaAttainedDate;

	private String assessmentDate;

	private String assessmentMethod;

	private String assessmentLevel;

	private String reportedLevel;

	/* LOS INFORMATION */
	private String supervisionLevelHistoryId;

	private String effectiveDate;
	
	private String effectiveLosDateCurrentRecord;

	private String supervisionLevel;
	
	private String supervisionLevelCurrentRecord;

	private String supervisionLevelDesc;

	private String losComments;
	
	private String losCommentsCurrentRecord;

	private List losHistories;

	private String priorEffectiveDate;

	private String subsequentEffectiveDate;

	private String mostRecentEffectiveDate;

	private List supervisionLevelList;
	
	private boolean hasPhoto;
	
	/* DNA Information */
	private String dnaCollectedDate;
	private String dnaCurrentRecordDate;
	private String dnaEntryDate; // when created
	private boolean dnaCurrentRecordFlagInd;
	private boolean dnaFlagInd;
	private String superviseeHistoryId;
	private List dnaHistories;
	private String dnaEntryUser;
	
	/* Program Tracker Information */
	private String programTrackerHistoryId;
	private String programTrackerEffectiveDate;
	private String programTrackerEndDate;
	private String programTrackerDesc;
	private String programTrackerId;
	private List programTrackerHistories;
	private String programTrackerEnded;
	private String mostRecentProgramTrackerEffectiveDate;
	private String mostRecentProgramTrackerEndDate;
	private String programTrackerSubsequentEffectiveDate;
	private String programTrackerSubsequentEndDate;
	private String programTrackerPriorEffectiveDate;
	private String programTrackerPriorEndDate;

	/* TRANSFER INFORMATION */
	private Collection harrisCountyCases;

	private Collection courtesyCases;
	
	private SuperviseeTransferCasesInfo transferCasesInfo = new SuperviseeTransferCasesInfo();
	private SuperviseeTransferCasesInfo prevTransferCasesInfo = new SuperviseeTransferCasesInfo();
	
	private transient byte[] superviseePhoto;
	private String superviseePhotoCreateDate;	
	

	public String getSuperviseePhotoCreateDate() {
		return superviseePhotoCreateDate;
	}

	public void setSuperviseePhotoCreateDate(String superviseePhotoCreateDate) {
		this.superviseePhotoCreateDate = superviseePhotoCreateDate;
	}

	public byte[] getSuperviseePhoto() {
		return superviseePhoto;
	}

	public void setSuperviseePhoto(byte[] superviseePhoto) {
		this.superviseePhoto = superviseePhoto;
	}

	public void clear() {
		superviseeId = "";
		confirmMessage = "";
		tabLink = "";
		// superviseeName=new Name();
		transferCasesInfo.clear();
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action
	 *            The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the delete.
	 */
	public boolean isDelete() {
		return delete;
	}

	/**
	 * @param delete
	 *            The delete to set.
	 */
	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return Returns the listsSet.
	 */
	public boolean isListsSet() {
		return listsSet;
	}

	/**
	 * @param listsSet
	 *            The listsSet to set.
	 */
	public void setListsSet(boolean listsSet) {
		this.listsSet = listsSet;
	}

	/**
	 * @return Returns the secondaryAction.
	 */
	public String getSecondaryAction() {
		return secondaryAction;
	}

	/**
	 * @param secondaryAction
	 *            The secondaryAction to set.
	 */
	public void setSecondaryAction(String secondaryAction) {
		this.secondaryAction = secondaryAction;
	}

	/**
	 * @return Returns the selectedValue.
	 */
	public String getSelectedValue() {
		return selectedValue;
	}

	/**
	 * @param selectedValue
	 *            The selectedValue to set.
	 */
	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	/**
	 * @return Returns the superviseeId.
	 */
	public String getSuperviseeId() {
		return superviseeId;
	}

	/**
	 * @param superviseeId
	 *            The superviseeId to set.
	 */
	public void setSuperviseeId(String superviseeId) {
		this.superviseeId = superviseeId;
	}

	/**
	 * @return Returns the superviseeName.
	 */
	public String getSuperviseeName() {
		return superviseeName;
	}

	/**
	 * @param superviseeName
	 *            The superviseeName to set.
	 */
	public void setSuperviseeName(String superviseeName) {
		this.superviseeName = superviseeName;
	}

	/**
	 * @return Returns the update.
	 */
	public boolean isUpdate() {
		return update;
	}

	/**
	 * @param update
	 *            The update to set.
	 */
	public void setUpdate(boolean update) {
		this.update = update;
	}

	/**
	 * @return
	 */
	public String getAdvancedDegreeEarned() {
		return advancedDegreeEarned;
	}

	/**
	 * @param advancedDegreeEarned
	 */
	public void setAdvancedDegreeEarned(String advancedDegreeEarned) {
		this.advancedDegreeEarned = advancedDegreeEarned;
	}

	/**
	 * @return AssessmentDate
	 */
	public String getAssessmentDate() {
		return assessmentDate;
	}

	/**
	 * @param assessmentDate
	 */
	public void setAssessmentDate(String assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	/**
	 * @return AssessmentLevel
	 */
	public String getAssessmentLevel() {
		return assessmentLevel;
	}

	/**
	 * @param assessmentLevel
	 */
	public void setAssessmentLevel(String assessmentLevel) {
		this.assessmentLevel = assessmentLevel;
	}

	/**
	 * @return AssessmentMethod
	 */
	public String getAssessmentMethod() {
		return assessmentMethod;
	}

	/**
	 * @param assessmentMethod
	 */
	public void setAssessmentMethod(String assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	/**
	 * @return Build
	 */
	public String getBuild() {
		return build;
	}

	/**
	 * @param build
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/**
	 * @return Complexion
	 */
	public String getComplexion() {
		return complexion;
	}

	/**
	 * @param complexion
	 */
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	/**
	 * @return CourtesyCases
	 */
	public Collection getCourtesyCases() {
		return courtesyCases;
	}

	/**
	 * @param courtesyCases
	 */
	public void setCourtesyCases(Collection courtesyCases) {
		this.courtesyCases = courtesyCases;
	}

	/**
	 * @return DateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return DescriptionSource
	 */
	public String getDescriptionSource() {
		return descriptionSource;
	}

	/**
	 * @param descriptionSource
	 */
	public void setDescriptionSource(String descriptionSource) {
		this.descriptionSource = descriptionSource;
	}

	/**
	 * @return DriverLicenseNumber
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	/**
	 * @param driverLicenseNumber
	 */
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	/**
	 * @return DriverLicenseState
	 */
	public String getDriverLicenseState() {
		return driverLicenseState;
	}

	/**
	 * @param driverLicenseState
	 */
	public void setDriverLicenseState(String driverLicenseState) {
		this.driverLicenseState = driverLicenseState;
	}

	/**
	 * @return los comments
	 */
	public String getLosComments() {
		return losComments;
	}

	/**
	 * @param losComments
	 */
	public void setLosComments(String losComments) {
		this.losComments = losComments;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLosCommentsCurrentRecord() {
		return losCommentsCurrentRecord;
	}

	/**
	 * 
	 * @param losCommentsCurrentRecord
	 */
	public void setLosCommentsCurrentRecord(String losCommentsCurrentRecord) {
		this.losCommentsCurrentRecord = losCommentsCurrentRecord;
	}

	/**
	 * @return EffectiveDate
	 */
	public String getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate
	 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEffectiveLosDateCurrentRecord() {
		return effectiveLosDateCurrentRecord;
	}

	/**
	 * 
	 * @param effectiveLosDateCurrentRecord
	 */
	public void setEffectiveLosDateCurrentRecord(
			String effectiveLosDateCurrentRecord) {
		this.effectiveLosDateCurrentRecord = effectiveLosDateCurrentRecord;
	}

	/**
	 * @return EmployePhoneNum
	 */
	public String getEmployePhoneNum() {
		return employePhoneNum;
	}

	/**
	 * @param employePhoneNum
	 */
	public void setEmployePhoneNum(String employePhoneNum) {
		this.employePhoneNum = employePhoneNum;
	}

	/**
	 * @return EmployerAdditionalZipCode
	 */
	public String getEmployerAdditionalZipCode() {
		return employerAdditionalZipCode;
	}

	/**
	 * @param employerAdditionalZipCode
	 */
	public void setEmployerAdditionalZipCode(String employerAdditionalZipCode) {
		this.employerAdditionalZipCode = employerAdditionalZipCode;
	}

	/**
	 * @return EmployerAptNum
	 */
	public String getEmployerAptNum() {
		return employerAptNum;
	}

	/**
	 * @param employerAptNum
	 */
	public void setEmployerAptNum(String employerAptNum) {
		this.employerAptNum = employerAptNum;
	}

	/**
	 * @return EmployerCity
	 */
	public String getEmployerCity() {
		return employerCity;
	}

	/**
	 * @param employerCity
	 */
	public void setEmployerCity(String employerCity) {
		this.employerCity = employerCity;
	}

	/**
	 * @return EmployerEmploymentStatus
	 */
	public String getEmployerEmploymentStatus() {
		return employerEmploymentStatus;
	}

	/**
	 * @param employerEmploymentStatus
	 */
	public void setEmployerEmploymentStatus(String employerEmploymentStatus) {
		this.employerEmploymentStatus = employerEmploymentStatus;
	}

	/**
	 * @return EmployerName
	 */
	public String getEmployerName() {
		return employerName;
	}

	/**
	 * @param employerName
	 */
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	/**
	 * @return EmployerOccupation
	 */
	public String getEmployerOccupation() {
		return employerOccupation;
	}

	/**
	 * @param employerOccupation
	 */
	public void setEmployerOccupation(String employerOccupation) {
		this.employerOccupation = employerOccupation;
	}

	/**
	 * @return EmployerState
	 */
	public String getEmployerState() {
		return employerState;
	}

	/**
	 * @param employerState
	 */
	public void setEmployerState(String employerState) {
		this.employerState = employerState;
	}

	/**
	 * @return EmployerStreetName
	 */
	public String getEmployerStreetName() {
		return employerStreetName;
	}

	/**
	 * @param employerStreetName
	 */
	public void setEmployerStreetName(String employerStreetName) {
		this.employerStreetName = employerStreetName;
	}

	/**
	 * @return EmployerStreetNum
	 */
	public String getEmployerStreetNum() {
		return employerStreetNum;
	}

	/**
	 * @param employerStreetNum
	 */
	public void setEmployerStreetNum(String employerStreetNum) {
		this.employerStreetNum = employerStreetNum;
	}

	/**
	 * @return EmployerStreetType
	 */
	public String getEmployerStreetType() {
		return employerStreetType;
	}

	/**
	 * @param employerStreetType
	 */
	public void setEmployerStreetType(String employerStreetType) {
		this.employerStreetType = employerStreetType;
	}

	/**
	 * @return EmployerZipCode
	 */
	public String getEmployerZipCode() {
		return employerZipCode;
	}

	/**
	 * @param employerZipCode
	 */
	public void setEmployerZipCode(String employerZipCode) {
		this.employerZipCode = employerZipCode;
	}

	/**
	 * @return Ethnicity
	 */
	public String getEthnicity() {
		return ethnicity;
	}

	/**
	 * @param ethnicity
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 * @return EyeColor
	 */
	public String getEyeColor() {
		return eyeColor;
	}

	/**
	 * @param eyeColor
	 */
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}

	/**
	 * @return FbiNumber
	 */
	public String getFbiNumber() {
		return fbiNumber;
	}

	/**
	 * @param fbiNumber
	 */
	public void setFbiNumber(String fbiNumber) {
		this.fbiNumber = fbiNumber;
	}

	/**
	 * @return FingerprintedInd
	 */
	public String getFingerprintedInd() {
		return fingerprintedInd;
	}

	/**
	 * @param fingerprintedInd
	 */
	public void setFingerprintedInd(String fingerprintedInd) {
		this.fingerprintedInd = fingerprintedInd;
	}

	/**
	 * @return GED
	 */
	public String getGED() {
		return GED;
	}

	/**
	 * @param ged
	 */
	public void setGED(String ged) {
		GED = ged;
	}

	/**
	 * @return GEDAttainedDate
	 */
	public String getGEDAttainedDate() {
		return GEDAttainedDate;
	}

	/**
	 * @param attainedDate
	 */
	public void setGEDAttainedDate(String attainedDate) {
		GEDAttainedDate = attainedDate;
	}

	/**
	 * @return GEDVerified
	 */
	public String getGEDVerified() {
		return GEDVerified;
	}

	/**
	 * @param verified
	 */
	public void setGEDVerified(String verified) {
		GEDVerified = verified;
	}

	/**
	 * @return HairColor
	 */
	public String getHairColor() {
		return hairColor;
	}

	/**
	 * @param hairColor
	 */
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * @return HarrisCountyCases
	 */
	public Collection getHarrisCountyCases() {
		return harrisCountyCases;
	}

	/**
	 * @param harrisCountyCases
	 */
	public void setHarrisCountyCases(Collection harrisCountyCases) {
		this.harrisCountyCases = harrisCountyCases;
	}
	
	public String getHeight() {
		String h = getRawHeight();
		
		if (h != null && h.length()> 0)
		{
			setHeight(h.substring(0,1) + "ft " + h.substring(1) + "in");
		}
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getRawHeight() {
		return rawHeight;
	}

	public void setRawHeight(String rawHeight) {
		this.rawHeight = rawHeight;
	}

	/**
	 * @return HeightFeet
	 */
	public String getHeightFeet() {
		return heightFeet;
	}

	/**
	 * @param heightFeet
	 */
	public void setHeightFeet(String heightFeet) {
		this.heightFeet = heightFeet;
	}

	/**
	 * @return HeightInch
	 */
	public String getHeightInch() {
		return heightInch;
	}

	/**
	 * @param heightInch
	 */
	public void setHeightInch(String heightInch) {
		this.heightInch = heightInch;
	}

	/**
	 * @return HighestGradeCompleted
	 */
	public String getHighestGradeCompleted() {
		return highestGradeCompleted;
	}

	/**
	 * @param highestGradeCompleted
	 */
	public void setHighestGradeCompleted(String highestGradeCompleted) {
		this.highestGradeCompleted = highestGradeCompleted;
	}

	/**
	 * @return HighSchoolDiploma
	 */
	public String getHighSchoolDiploma() {
		return highSchoolDiploma;
	}

	/**
	 * @param highSchoolDiploma
	 */
	public void setHighSchoolDiploma(String highSchoolDiploma) {
		this.highSchoolDiploma = highSchoolDiploma;
	}

	/**
	 * @return HsDiplomaAttainedDate
	 */
	public String getHsDiplomaAttainedDate() {
		return hsDiplomaAttainedDate;
	}

	/**
	 * @param hsDiplomaAttainedDate
	 */
	public void setHsDiplomaAttainedDate(String hsDiplomaAttainedDate) {
		this.hsDiplomaAttainedDate = hsDiplomaAttainedDate;
	}

	/**
	 * @return HsDiplomaVerified
	 */
	public String getHsDiplomaVerified() {
		return hsDiplomaVerified;
	}

	/**
	 * @param hsDiplomaVerified
	 */
	public void setHsDiplomaVerified(String hsDiplomaVerified) {
		this.hsDiplomaVerified = hsDiplomaVerified;
	}

	/**
	 * @return IntakeDate
	 */
	public String getIntakeDate() {
		return intakeDate;
	}

	/**
	 * @param intakeDate
	 */
	public void setIntakeDate(String intakeDate) {
		this.intakeDate = intakeDate;
	}

	/**
	 * @return MaritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @return PlaceOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}

	/**
	 * @param placeOfBirth
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}

	/**
	 * @return Race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return ReportedLevel
	 */
	public String getReportedLevel() {
		return reportedLevel;
	}

	/**
	 * @param reportedLevel
	 */
	public void setReportedLevel(String reportedLevel) {
		this.reportedLevel = reportedLevel;
	}

	/**
	 * @return ScarsMarksDescription
	 */
	public List getScarsMarksDescription() {
		return scarsMarksDescription;
	}

	/**
	 * @param scarsMarksDescription
	 */
	public void setScarsMarksDescription(List scarsMarksDescription) {
		this.scarsMarksDescription = scarsMarksDescription;
	}

	/**
	 * @return Sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return StateCountryOfBirth
	 */
	public String getStateCountryOfBirth() {
		return stateCountryOfBirth;
	}

	/**
	 * @param stateCountryOfBirth
	 */
	public void setStateCountryOfBirth(String stateCountryOfBirth) {
		this.stateCountryOfBirth = stateCountryOfBirth;
	}

	/**
	 * @return StateIdNumber
	 */
	public String getStateIdNumber() {
		return stateIdNumber;
	}

	/**
	 * @param stateIdNumber
	 */
	public void setStateIdNumber(String stateIdNumber) {
		this.stateIdNumber = stateIdNumber;
	}

	/**
	 * @return SuperviseeAddressType
	 */
	public String getSuperviseeAddressType() {
		return superviseeAddressType;
	}

	/**
	 * @param superviseeAddressType
	 */
	public void setSuperviseeAddressType(String superviseeAddressType) {
		this.superviseeAddressType = superviseeAddressType;
	}

	/**
	 * @return SuperviseeApartmentNumber
	 */
	public String getSuperviseeApartmentNumber() {
		return superviseeApartmentNumber;
	}

	/**
	 * @param superviseeApartmentNumber
	 */
	public void setSuperviseeApartmentNumber(String superviseeApartmentNumber) {
		this.superviseeApartmentNumber = superviseeApartmentNumber;
	}

	/**
	 * @return SuperviseeCity
	 */
	public String getSuperviseeCity() {
		return superviseeCity;
	}

	/**
	 * @param superviseeCity
	 */
	public void setSuperviseeCity(String superviseeCity) {
		this.superviseeCity = superviseeCity;
	}

	/**
	 * @return SuperviseeListIndex
	 */
	public String getSuperviseeListIndex() {
		return superviseeListIndex;
	}

	/**
	 * @param superviseeListIndex
	 */
	public void setSuperviseeListIndex(String superviseeListIndex) {
		this.superviseeListIndex = superviseeListIndex;
	}

	/**
	 * @return SuperviseeNameSource
	 */
	public String getSuperviseeNameSource() {
		return superviseeNameSource;
	}

	/**
	 * @param superviseeNameSource
	 */
	public void setSuperviseeNameSource(String superviseeNameSource) {
		this.superviseeNameSource = superviseeNameSource;
	}

	/**
	 * @return SuperviseePhoneNumber
	 */
	public String getSuperviseePhoneNumber() {
		return superviseePhoneNumber;
	}

	/**
	 * @param superviseePhoneNumber
	 */
	public void setSuperviseePhoneNumber(String superviseePhoneNumber) {
		this.superviseePhoneNumber = superviseePhoneNumber;
	}

	/**
	 * @return SuperviseeState
	 */
	public String getSuperviseeState() {
		return superviseeState;
	}

	/**
	 * @param superviseeState
	 */
	public void setSuperviseeState(String superviseeState) {
		this.superviseeState = superviseeState;
	}

	/**
	 * @return SuperviseeStreetName
	 */
	public String getSuperviseeStreetName() {
		return superviseeStreetName;
	}

	/**
	 * @param superviseeStreetName
	 */
	public void setSuperviseeStreetName(String superviseeStreetName) {
		this.superviseeStreetName = superviseeStreetName;
	}

	/**
	 * @return SuperviseeStreetNumber
	 */
	public String getSuperviseeStreetNumber() {
		return superviseeStreetNumber;
	}

	/**
	 * @param superviseeStreetNumber
	 */
	public void setSuperviseeStreetNumber(String superviseeStreetNumber) {
		this.superviseeStreetNumber = superviseeStreetNumber;
	}

	/**
	 * @return SuperviseeStreetType
	 */
	public String getSuperviseeStreetType() {
		return superviseeStreetType;
	}

	/**
	 * @param superviseeStreetType
	 */
	public void setSuperviseeStreetType(String superviseeStreetType) {
		this.superviseeStreetType = superviseeStreetType;
	}

	/**
	 * @return SuperviseeZipCode
	 */
	public String getSuperviseeZipCode() {
		return superviseeZipCode;
	}

	/**
	 * @param superviseeZipCode
	 */
	public void setSuperviseeZipCode(String superviseeZipCode) {
		this.superviseeZipCode = superviseeZipCode;
	}

	/**
	 * @return SupervisionLevel
	 */
	public String getSupervisionLevel() {
		return supervisionLevel;
	}

	/**
	 * @param supervisionLevel
	 */
	public void setSupervisionLevel(String supervisionLevel) {
		this.supervisionLevel = supervisionLevel;
	}

	/**
	 * 
	 * @return
	 */
	public String getSupervisionLevelCurrentRecord() {
		return supervisionLevelCurrentRecord;
	}

	/**
	 * 
	 * @param supervisionLevelCurrentRecord
	 */
	public void setSupervisionLevelCurrentRecord(
			String supervisionLevelCurrentRecord) {
		this.supervisionLevelCurrentRecord = supervisionLevelCurrentRecord;
	}

	/**
	 * @return TattoosDescription
	 */
	public List getTattoosDescription() {
		return tattoosDescription;
	}

	/**
	 * @param tattoosDescription
	 */
	public void setTattoosDescription(List tattoosDescription) {
		this.tattoosDescription = tattoosDescription;
	}

	/**
	 * @return UsCitizenshipInd
	 */
	public String getUsCitizenshipInd() {
		return usCitizenshipInd;
	}

	/**
	 * @param usCitizenshipInd
	 */
	public void setUsCitizenshipInd(String usCitizenshipInd) {
		this.usCitizenshipInd = usCitizenshipInd;
	}

	/**
	 * @return Weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return ssn
	 */
	public SocialSecurity getSsn() {
		setSsn(new SocialSecurity(superviseeListIndex));
		return ssn;
	}

	/**
	 * @param ssn
	 */
	public void setSsn(SocialSecurity ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return losHistories
	 */
	public List getLosHistories() {

		if (losHistories != null) {
			Iterator losHistoriesIter = losHistories.iterator();
			List supervisionList = getSupervisionLevelList();
			Collection losHistoriesNew = new ArrayList();
			while (losHistoriesIter.hasNext()) {
				SupervisionLevelResponseEvent losResp = (SupervisionLevelResponseEvent) losHistoriesIter
						.next();
				Iterator supervisionListIter = supervisionList.iterator();
				while (supervisionListIter.hasNext()) {
					CodeResponseEvent cre = (CodeResponseEvent) supervisionListIter
							.next();
					if ((losResp.getSupervisionLevelId() != null) && (losResp.getSupervisionLevelId().equals(cre.getCode()))) {
						losResp.setSupervisionLevelDesc(cre.getDescription());
					}
				}
				losHistoriesNew.add(losResp);
			}
			return (List) losHistoriesNew;
		} else {
			return losHistories;
		}

	}
	/**
	 * @return programTrackerHistories
	 */
	public List getProgramTrackerHistories() {

		if (programTrackerHistories != null) {
			Iterator programTrackerHistoriesIter = programTrackerHistories.iterator();
			List trackerList = getProgramTrackerList();
			Collection programTrackerHistoriesNew = new ArrayList();
			while (programTrackerHistoriesIter.hasNext()) {
				ProgramTrackerResponseEvent tResp = (ProgramTrackerResponseEvent) programTrackerHistoriesIter
						.next();
				Iterator trackerListIter = trackerList.iterator();
				while (trackerListIter.hasNext()) {
					CodeResponseEvent cre = (CodeResponseEvent) trackerListIter
							.next();
					if ((tResp.getProgramTrackerId() != null) && (tResp.getProgramTrackerId().equals(cre.getCode()))) {
						tResp.setProgramTrackerDesc(cre.getDescription());
					}
				}
				programTrackerHistoriesNew.add(tResp);
			}
			return (List) programTrackerHistoriesNew;
		} else {
			return programTrackerHistories;
		}

	}

	/**
	 * @param losHistories
	 */
	public void setLosHistories(List losHistories) {
		this.losHistories = losHistories;
	}

	/**
	 * @return SupervisionLevelList
	 */
	public List getSupervisionLevelList() {
		return ComplexCodeTableHelper.getSupervisionLevelOfServiceCodes();
	}

	/**
	 * @param supervisionLevelList
	 */
	public void setSupervisionLevelList(List supervisionLevelList) {
		this.supervisionLevelList = supervisionLevelList;
	}

	/**
	 * @return supervisionLevelHistoryId
	 */
	public String getSupervisionLevelHistoryId() {
		return supervisionLevelHistoryId;
	}

	/**
	 * @param supervisionLevelHistoryId
	 */
	public void setSupervisionLevelHistoryId(String supervisionLevelHistoryId) {
		this.supervisionLevelHistoryId = supervisionLevelHistoryId;
	}

	/**
	 * @return supervisionLevelDes
	 */
	public String getSupervisionLevelDesc() {

		if ((getSupervisionLevel() != null)
				&& (getSupervisionLevel().length() > 0)) {

			List supervisionList = getSupervisionLevelList();
			Iterator supervisionListIter = supervisionList.iterator();
			while (supervisionListIter.hasNext()) {
				CodeResponseEvent cre = (CodeResponseEvent) supervisionListIter
						.next();
				if (getSupervisionLevel().equals(cre.getCode())) {
					setSupervisionLevelDesc(cre.getDescription());
					break;
				}
			}
		}

		return supervisionLevelDesc;
	}

	/**
	 * @param supervisionLevelDesc
	 */
	public void setSupervisionLevelDesc(String supervisionLevelDesc) {
		this.supervisionLevelDesc = supervisionLevelDesc;
	}

	/**
	 * @return priorEffectiveDate
	 */
	public String getPriorEffectiveDate() {

		if (losHistories != null) {

			List lh = losHistories;
			Collections.sort(lh);
			Iterator losHistoriesIter = lh.iterator();

			boolean breakOnNext = false;
			while (losHistoriesIter.hasNext()) {
				SupervisionLevelResponseEvent losResp = (SupervisionLevelResponseEvent) losHistoriesIter
						.next();

				if (breakOnNext) {
					setPriorEffectiveDate(DateUtil.dateToString(losResp
							.getLosEffectiveDate(), UIConstants.DATE_FMT_1));
					break;
				}

				if (losResp.getSupervisionLevelHistoryId().equals(
						this.getSupervisionLevelHistoryId())) {
					breakOnNext = true;
				}
			}
		}
		return priorEffectiveDate;

	}

	/**
	 * @param priorEffectiveDate
	 */
	public void setPriorEffectiveDate(String priorEffectiveDate) {
		this.priorEffectiveDate = priorEffectiveDate;
	}

	/**
	 * @return subsequentEffectiveDate
	 */
	public String getSubsequentEffectiveDate() {

		if (losHistories != null) {

			List lh = losHistories;
			Collections.sort(lh);
			Iterator losHistoriesIter = lh.iterator();

			while (losHistoriesIter.hasNext()) {
				SupervisionLevelResponseEvent losResp = (SupervisionLevelResponseEvent) losHistoriesIter
						.next();

				if (losResp.getSupervisionLevelHistoryId().equals(
						this.getSupervisionLevelHistoryId())) {
					break;
				} else {
					setSubsequentEffectiveDate(DateUtil.dateToString(losResp
							.getLosEffectiveDate(), UIConstants.DATE_FMT_1));
				}

			}

		}

		return subsequentEffectiveDate;
	}

	/**
	 * @param subsequentEffectiveDate
	 */
	public void setSubsequentEffectiveDate(String subsequentEffectiveDate) {
		this.subsequentEffectiveDate = subsequentEffectiveDate;
	}

	/**
	 * @return mostRecentEffectiveDate
	 */
	public String getMostRecentEffectiveDate() {

		if (losHistories != null) {

			List lh = losHistories;
			Collections.sort(lh);
			Iterator losHistoriesIter = lh.iterator();

			while (losHistoriesIter.hasNext()) {
				SupervisionLevelResponseEvent losResp = (SupervisionLevelResponseEvent) losHistoriesIter
						.next();
				setMostRecentEffectiveDate(DateUtil.dateToString(losResp
						.getLosEffectiveDate(), UIConstants.DATE_FMT_1));
				break;
			}
		}
		return mostRecentEffectiveDate;
	}
	public String getMostRecentProgramTrackerEffectiveDate() {

		if (programTrackerHistories != null && programTrackerHistories.size() > 0) {

			List sortedList = new ArrayList(programTrackerHistories);
			List sortFields = new ArrayList();
			//Sort by OID desc 
			sortFields.add(new ReverseComparator(new BeanComparator("superviseeHistoryId")));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, multiSort);
			ProgramTrackerResponseEvent ptre = (ProgramTrackerResponseEvent) sortedList.get(0);
			setMostRecentProgramTrackerEffectiveDate(DateUtil.dateToString(ptre.getProgramTrackerEffectiveDate(), UIConstants.DATE_FMT_1));
			if (ptre.getProgramTrackerEndDate() != null){
				setMostRecentProgramTrackerEndDate(DateUtil.dateToString(ptre.getProgramTrackerEndDate(), UIConstants.DATE_FMT_1));
			}
		}
		return mostRecentProgramTrackerEffectiveDate;
	}

	/**
	 * @param mostRecentEffectiveDate
	 */
	public void setMostRecentEffectiveDate(String mostRecentEffectiveDate) {
		this.mostRecentEffectiveDate = mostRecentEffectiveDate;
	}

	/**
	 * @return the transferCasesInfo
	 */
	public SuperviseeTransferCasesInfo getTransferCasesInfo() {
		return transferCasesInfo;
	}

	/**
	 * @param transferCasesInfo the transferCasesInfo to set
	 */
	public void setTransferCasesInfo(SuperviseeTransferCasesInfo transferCasesInfo) {
		this.transferCasesInfo = transferCasesInfo;
	}

	public String getFromPage() {
		return fromPage;
	}

	public void setFromPage(String fromPage) {
		this.fromPage = fromPage;
	}

	public boolean isHasPhoto() {
		return hasPhoto;
	}

	public void setHasPhoto(boolean hasPhoto) {
		this.hasPhoto = hasPhoto;
	}
	/**
	 * @return the allowLOSUpdates
	 */
	public boolean isAllowLOSUpdates() {
		return allowLOSUpdates;
	}
	public boolean isAllowProgramTrackerUpdates() {
		return allowProgramTrackerUpdates;
	}
	public void setAllowProgramTrackerUpdates(boolean allowProgramTrackerUpdates) {
		this.allowProgramTrackerUpdates = allowProgramTrackerUpdates;
	}
	/**
	 * @param allowLOSUpdates the allowLOSUpdates to set
	 */
	
	
	public void setAllowLOSUpdates(boolean allowLOSUpdates) {
		this.allowLOSUpdates = allowLOSUpdates;
	}
	/**
	 * @return the allowDNAUpdates
	 */
	public boolean isAllowDNAUpdates() {
		return allowDNAUpdates;
	}

	/**
	 * @param allowDNAUpdates the allowDNAUpdates to set
	 */
	public void setAllowDNAUpdates(boolean allowDNAUpdates) {
		this.allowDNAUpdates = allowDNAUpdates;
	}
		
	/**
	 * @return the superviseeHistoryId
	 */
	public String getSuperviseeHistoryId() {
		return superviseeHistoryId;
	}

	/**
	 * @param superviseeHistoryId the superviseeHistoryId to set
	 */
	public void setSuperviseeHistoryId(String superviseeHistoryId) {
		this.superviseeHistoryId = superviseeHistoryId;
	}

	public String getProgramTrackerEffectiveDate() {
		return programTrackerEffectiveDate;
	}
	public void setProgramTrackerEffectiveDate(String programTrackerEffectiveDate) {
		this.programTrackerEffectiveDate = programTrackerEffectiveDate;
	}

	public String getProgramTrackerEndDate() {
		return programTrackerEndDate;
	}
	public String getProgramTrackerId() {
		return programTrackerId;
	}

	public void setProgramTrackerEndDate(String programTrackerEndDate) {
		this.programTrackerEndDate = programTrackerEndDate;
	}
	
	public String getProgramTrackerDesc() {

		if ((getProgramTrackerId() != null)
				&& (getProgramTrackerId().length() > 0)) {

			List codeList = getProgramTrackerList();
			Iterator codeListIter = codeList.iterator();
			while (codeListIter.hasNext()) {
				CodeResponseEvent cre = (CodeResponseEvent) codeListIter
						.next();
				if (getProgramTrackerId().equals(cre.getCode())) {
					setProgramTrackerDesc(cre.getDescription());
					break;
				}
			}
		}

		return programTrackerDesc;
	}

	public void setProgramTrackerDesc(String programTrackerDesc) {
		this.programTrackerDesc = programTrackerDesc;
	}
	public List getProgramTrackerList() {
		return ComplexCodeTableHelper.getProgramTrackerCodes();
	}
	public void setProgramTrackerId(String programTrackerId) {
		this.programTrackerId = programTrackerId;
	}
	public void setProgramTrackerHistories(List programTrackerHistories) {
		this.programTrackerHistories = programTrackerHistories;
	}

	public void setProgramTrackerHistoryId(String programTrackerHistoryId) {
		this.programTrackerHistoryId = programTrackerHistoryId;
	}

	public String getProgramTrackerHistoryId() {
		return programTrackerHistoryId;
	}

	public void setProgramTrackerEnded(String programTrackerEnded) {
		this.programTrackerEnded = programTrackerEnded;
	}

	public String getProgramTrackerEnded() {
		return programTrackerEnded;
	}
	public void setMostRecentProgramTrackerEffectiveDate(
			String mostRecentProgramTrackerEffectiveDate) {
		this.mostRecentProgramTrackerEffectiveDate = mostRecentProgramTrackerEffectiveDate;
	}
	public String getMostRecentProgramTrackerEndDate() {
		return mostRecentProgramTrackerEndDate;
	}
	public void setMostRecentProgramTrackerEndDate(
			String mostRecentProgramTrackerEndDate) {
		this.mostRecentProgramTrackerEndDate = mostRecentProgramTrackerEndDate;
	}
	public String getProgramTrackerSubsequentEffectiveDate() {
		return programTrackerSubsequentEffectiveDate;
	}

	public void setProgramTrackerSubsequentEffectiveDate(
			String programTrackerSubsequentEffectiveDate) {
		this.programTrackerSubsequentEffectiveDate = programTrackerSubsequentEffectiveDate;
	}

	public String getProgramTrackerSubsequentEndDate() {
		return programTrackerSubsequentEndDate;
	}

	public void setProgramTrackerSubsequentEndDate(
			String programTrackerSubsequentEndDate) {
		this.programTrackerSubsequentEndDate = programTrackerSubsequentEndDate;
	}

	public String getProgramTrackerPriorEffectiveDate() {
		
		this.setProgramTrackerPriorEffectiveDate("");
		this.setProgramTrackerPriorEndDate("");
		this.setProgramTrackerSubsequentEffectiveDate("");
		this.setProgramTrackerSubsequentEndDate("");
		
		if (programTrackerHistories != null) {

			List sortedList = new ArrayList(programTrackerHistories);
			List sortFields = new ArrayList();
			//Sort by OID desc 
			sortFields.add(new BeanComparator("superviseeHistoryId"));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, multiSort);
			
			int selectedRowIndex = -1;
			
			for (int i = 0; i < sortedList.size(); i++) {
				ProgramTrackerResponseEvent ptre = (ProgramTrackerResponseEvent) sortedList.get(i);
				if (ptre.getSuperviseeHistoryId().equals(this.getProgramTrackerHistoryId())){
					selectedRowIndex = i;
					break;
				}
			}
	
			//Records are sorted by OID, i.e. a recent record would have a higher oid than an older record.
			if (selectedRowIndex > -1){
				int priorRowIndex = selectedRowIndex - 1;
				if (priorRowIndex > -1){
					ProgramTrackerResponseEvent prevRecord = (ProgramTrackerResponseEvent) sortedList.get(priorRowIndex);
					this.setProgramTrackerPriorEffectiveDate(DateUtil.dateToString(prevRecord.getProgramTrackerEffectiveDate(), UIConstants.DATE_FMT_1));
					this.setProgramTrackerPriorEndDate(DateUtil.dateToString(prevRecord.getProgramTrackerEndDate(), UIConstants.DATE_FMT_1));
				}
				int nextRowIndex = selectedRowIndex + 1;
				if (nextRowIndex < sortedList.size()){
					ProgramTrackerResponseEvent nextRecord = (ProgramTrackerResponseEvent) sortedList.get(nextRowIndex);
					this.setProgramTrackerSubsequentEffectiveDate(DateUtil.dateToString(nextRecord.getProgramTrackerEffectiveDate(), UIConstants.DATE_FMT_1));
					this.setProgramTrackerSubsequentEndDate(DateUtil.dateToString(nextRecord.getProgramTrackerEndDate(), UIConstants.DATE_FMT_1));
				}
			}

		} 

		return programTrackerPriorEffectiveDate;
	}

	public void setProgramTrackerPriorEffectiveDate(
			String programTrackerPriorEffectiveDate) {
		this.programTrackerPriorEffectiveDate = programTrackerPriorEffectiveDate;
	}

	public String getProgramTrackerPriorEndDate() {
		return programTrackerPriorEndDate;
	}

	public void setProgramTrackerPriorEndDate(String programTrackerPriorEndDate) {
		this.programTrackerPriorEndDate = programTrackerPriorEndDate;
	}

	/**
	 * @return the tabLink
	 */
	public String getTabLink() {
		return tabLink;
	}

	/**
	 * @param tabLink the tabLink to set
	 */
	public void setTabLink(String tabLink) {
		this.tabLink = tabLink;
	}

	/**
	 * @return the dnaCurrentDate
	 */
	public String getDnaCurrentRecordDate() {
		return dnaCurrentRecordDate;
	}

	
	public void setDnaCurrentRecordDate(String dnaCurrentRecordDate) {
		this.dnaCurrentRecordDate = dnaCurrentRecordDate;
	}

	/**
	 * @return the dnaCollectedDate
	 */
	public String getDnaCollectedDate() {
		return dnaCollectedDate;
	}

	/**
	 * @param dnaCollectedDate the dnaCollectedDate to set
	 */
	public void setDnaCollectedDate(String dnaCollectedDate) {
		this.dnaCollectedDate = dnaCollectedDate;
	}

	/**
	 * 
	 * @return the dnaCurrentRecordFlagInd
	 */
	public boolean isDnaCurrentRecordFlagInd() {
		return dnaCurrentRecordFlagInd;
	}

	public void setDnaCurrentRecordFlagInd(boolean dnaCurrentRecordFlagInd) {
		this.dnaCurrentRecordFlagInd = dnaCurrentRecordFlagInd;
	}

	/**
	 * @return the dnaFlag
	 */
	public boolean isDnaFlagInd() {
		return dnaFlagInd;
	}

	/**
	 * @param dnaFlag the dnaFlag to set
	 */
	public void setDnaFlagInd(boolean dnaFlagInd) {
		this.dnaFlagInd = dnaFlagInd;
	}
		
	/**
	 * @return the dnaEntryDate
	 */
	public String getDnaEntryDate() {
		return dnaEntryDate;
	}

	/**
	 * @param dnaEntryDate the dnaEntryDate to set
	 */
	public void setDnaEntryDate(String dnaEntryDate) {
		this.dnaEntryDate = dnaEntryDate;
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
	
	/**
	 * 
	 * @return
	 */
	public String getDnaEntryUser() {
		return dnaEntryUser;
	}

	/**
	 * 
	 * @param dnaEntryUser
	 */
	public void setDnaEntryUser(String dnaEntryUser) {
		this.dnaEntryUser = dnaEntryUser;
	}

	/**
	 * 
	 * @param prevTransferCasesInfo
	 */
	public void setPrevTransferCasesInfo(SuperviseeTransferCasesInfo prevTransferCasesInfo) {
		this.prevTransferCasesInfo = prevTransferCasesInfo;
	}

	public SuperviseeTransferCasesInfo getPrevTransferCasesInfo() {
		return prevTransferCasesInfo;
	}
	
	
	
	
}// END Class

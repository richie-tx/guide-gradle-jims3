/*
 * Created on Jun 17, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.DateUtil;

/**
 * @author jfisher
 *
 */
public class JuvenileSchoolHistoryResponseEvent extends ResponseEvent implements Comparable
{
	private final String DATE_FORMAT_1 = "M/d/yyyy";

	private String schoolHistoryId;
	private String juvenileNum;
	private Date verifiedDate;
	private Date eligibilityEnrollmentDate; //Changes for ER:JIMS200077279
	private String TEASchoolNumber;
	private Date lastAttendedDate;
	private String lastAttendedDateString;
	private String exitTypeCode;
	private String exitTypeDescription;
	private String gradeLevelCode;
	private String gradeLevelDescription;
	private String instructionType;
	private String appropriateLevelCode;
	private String appropriateLevelDescription;
	private String schoolDistrictId;
	private String schoolDistrict;
	private String schoolId;
	private String school;
	
	private String homeSchoolDistrictId;
	private String homeSchoolDistrict;
	private String homeSchoolId;
	private String homeSchool;

	private String schoolStreet;
	private String schoolCity;
	private String schoolState;
	private String schoolZip;
	private String schoolPhone;
	private String schoolAddress;   // for display only
	private String schoolCityState; // for display only
	private String gradeAverage;
	private String gradesRepeatNumber;
	private String gradesRepeatedCode;
	private String gradesRepeatedDescription;
	private int gradeRepeatTotal;
	private String gradeChangeReason;
	private String participationCode;
	private String participationDescription;
	private String splEduCategoryCode;  //added for spl edu 
	private String splEduCategoryDescription; //added for spl edu 
	private String programAttendingCode;
	private String programAttendingDescription;		
	private String schoolAttendanceStatusCode;
	private String schoolAttendanceStatusDescription;
	private String ruleInfractionCode;
	private String ruleInfractionDescription;
	private String truancyHistory;
	private String educationService;

	
	private String specificSchoolName;
	private String schoolStreetNum;
	private String schoolStreetName;
	private String schoolZipCodeExt;
	private String addressId;
	
	private String academicPerformance;
	private String schoolInfoVerifiedBy;
	private boolean truancy;
	private String verifiedByOther;
	private boolean gedAwarded;	
	private Date   gedAwardedDate;
	private Date   completionDate;
	private boolean gedCompleted;

	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJims2User;
	private String updateJims2User;
	

	/**
	 * @return
	 */
	public String getAppropriateLevelCode()
	{
		return appropriateLevelCode;
	}

	/**
	 * @return
	 */
	public String getAppropriateLevelDescription()
	{
		return appropriateLevelDescription;
	}


	/**
	 * @return
	 */
	public String getExitTypeCode()
	{
		return exitTypeCode;
	}

	/**
	 * @return
	 */
	public String getExitTypeDescription()
	{
		return exitTypeDescription;
	}

	/**
	 * @return
	 */
	public String getGradeLevelCode()
	{
		return gradeLevelCode;
	}

	/**
	 * @return
	 */
	public String getGradeLevelDescription()
	{		
		return gradeLevelDescription;
	}

	/**
	 * @return
	 */
	public String getJuvenileNum()
	{
		return juvenileNum;
	}

	/**
	 * @return
	 */
	public Date getLastAttendedDate()
	{
		return lastAttendedDate;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrictId()
	{
		return schoolDistrictId;
	}

	/**
	 * @return
	 */
	public String getSchoolHistoryId()
	{
		return schoolHistoryId;
	}

	/**
	 * @return
	 */
	public String getSchoolId()
	{
		return schoolId;
	}

	/**
	 * @return
	 */
	public String getTEASchoolNumber()
	{
		return TEASchoolNumber;
	}

	/**
	 * @return
	 */
	public Date getVerifiedDate()
	{
		return verifiedDate;
	}

	/**
	 * @param string
	 */
	public void setAppropriateLevelCode(String string)
	{
		appropriateLevelCode = string;
	}

	/**
	 * @param string
	 */
	public void setAppropriateLevelDescription(String string)
	{
		appropriateLevelDescription = string;
	}

	/**
	 * @param string
	 */
	public void setExitTypeCode(String string)
	{
		exitTypeCode = string;
	}

	/**
	 * @param string
	 */
	public void setExitTypeDescription(String string)
	{
		exitTypeDescription = string;
	}

	/**
	 * @param string
	 */
	public void setGradeLevelCode(String string)
	{
		gradeLevelCode = string;
	}

	/**
	 * @param string
	 */
	public void setGradeLevelDescription(String string)
	{
		gradeLevelDescription = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param date
	 */
	public void setLastAttendedDate(Date date)
	{
		lastAttendedDate = date;
	}

	/**
	 * @param string
	 */
	public void setSchoolDistrictId(String string)
	{
		schoolDistrictId = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolHistoryId(String string)
	{
		schoolHistoryId = string;
	}

	/**
	 * @param string
	 */
	public void setSchoolId(String string)
	{
		schoolId = string;
	}

	/**
	 * @param string
	 */
	public void setTEASchoolNumber(String string)
	{
		TEASchoolNumber = string;
	}

	/**
	 * @param date
	 */
	public void setVerifiedDate(Date date)
	{
		verifiedDate = date;
	}

	/**
	 * @return
	 */
	public String getSchool()
	{
		return school;
	}

	/**
	 * @param string
	 */
	public void setSchool(String string)
	{
		school = string;
	}

	/**
	 * @return the schoolStreet
	 */
	public String getSchoolStreet() {
		return schoolStreet;
	}

	/**
	 * @param schoolStreet the schoolStreet to set
	 */
	public void setSchoolStreet(String schoolStreet) {
		this.schoolStreet = schoolStreet;
	}

	/**
	 * @return the schoolCity
	 */
	public String getSchoolCity() {
		return schoolCity;
	}

	/**
	 * @param schoolCity the schoolCity to set
	 */
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	/**
	 * @return the schoolState
	 */
	public String getSchoolState() {
		return schoolState;
	}

	/**
	 * @param schoolState the schoolState to set
	 */
	public void setSchoolState(String schoolState) {
		this.schoolState = schoolState;
	}

	/**
	 * @return the schoolZip
	 */
	public String getSchoolZip() {
		return schoolZip;
	}

	/**
	 * @param schoolZip the schoolZip to set
	 */
	public void setSchoolZip(String schoolZip) {
		this.schoolZip = schoolZip;
	}

	/**
	 * @return the schoolPhone
	 */
	public String getSchoolPhone() {
		return schoolPhone;
	}

	/**
	 * @param schoolPhone the schoolPhone to set
	 */
	public void setSchoolPhone(String schoolPhone) {
		this.schoolPhone = schoolPhone;
	}

	/**
	 * @return the dATE_FORMAT_1
	 */
	public String getDATE_FORMAT_1() {
		return DATE_FORMAT_1;
	}

	/**
	 * @return
	 */
	public String getSchoolDistrict()
	{
		return schoolDistrict;
	}

	/**
	 * @param string
	 */
	public void setSchoolDistrict(String string)
	{
		schoolDistrict = string;
	}

	/**
	 * @return
	 */
	public String getLastAttendedDateString()
	{
		String dateString = null;
		if (lastAttendedDate != null)
		{
			dateString = DateUtil.dateToString(lastAttendedDate, DATE_FORMAT_1);
		}
		return dateString;
	}

	/**
	 * @param string
	 */
	public void setLastAttendedDateString(String string)
	{
		lastAttendedDateString = string;
	}

	/**
	 * @return
	 */
	public String getGradesRepeatNumber()
	{
		return gradesRepeatNumber;
	}

	/**
	 * @return
	 */
	public String getParticipationCode()
	{
		return participationCode;
	}

	/**
	 * @return
	 */
	public String getProgramAttendingCode()
	{
		return programAttendingCode;
	}

	/**
	 * @return
	 */
	public String getSchoolAttendanceStatusCode()
	{
		return schoolAttendanceStatusCode;
	}

	/**
	 * @return
	 */
	public String getTruancyHistory()
	{
		return truancyHistory;
	}

	/**
	 * @param gradesRepeatNumber
	 */
	public void setGradesRepeatNumber(String gradesRepeatNumber)
	{
		this.gradesRepeatNumber = gradesRepeatNumber;
	}

	/**
	 * @param participationCode
	 */
	public void setParticipationCode(String participationCode)
	{
		this.participationCode = participationCode;
	}

	/**
	 * @param programAttendingCode
	 */
	public void setProgramAttendingCode(String programAttendingCode)
	{
		this.programAttendingCode = programAttendingCode;
	}

	/**
	 * @param schoolAttendanceStatusCode
	 */
	public void setSchoolAttendanceStatusCode(String schoolAttendanceStatusCode)
	{
		this.schoolAttendanceStatusCode = schoolAttendanceStatusCode;
	}

	/**
	 * @param truancyHistory
	 */
	public void setTruancyHistory(String truancyHistory)
	{
		this.truancyHistory = truancyHistory;
	}

	/**
	 * @return
	 */
	public String getGradesRepeatedCode()
	{
		return gradesRepeatedCode;
	}
	
	public int getGradeRepeatTotal()
	{
	    return gradeRepeatTotal;
	}

	public void setGradeRepeatTotal(int gradeRepeatTotal)
	{
	    this.gradeRepeatTotal = gradeRepeatTotal;
	}

	/**
	 * @return
	 */
	public String getRuleInfractionCode()
	{
		return ruleInfractionCode;
	}

	/**
	 * @return
	 */
	public String getGradeAverage()
	{
		return gradeAverage;
	}

	/**
	 * @param gradeAverage
	 */
	public void setGradeAverage(String gradeAverage)
	{
		this.gradeAverage = gradeAverage;
	}

	/**
	 * @return
	 */
	public String getParticipationDescription()
	{
		return participationDescription;
	}

	/**
	 * @return
	 */
	public String getProgramAttendingDescription()
	{
		return programAttendingDescription;
	}

	/**
	 * @return
	 */
	public String getRuleInfractionDescription()
	{
		return ruleInfractionDescription;
	}

	/**
	 * @return
	 */
	public String getSchoolAttendanceStatusDescription()
	{
		return schoolAttendanceStatusDescription;
	}

	/**
	 * @param participationDescription
	 */
	public void setParticipationDescription(String participationDescription)
	{
		this.participationDescription = participationDescription;
	}

	/**
	 * @param programAttendingDescription
	 */
	public void setProgramAttendingDescription(String programAttendingDescription)
	{
		this.programAttendingDescription = programAttendingDescription;
	}

	/**
	 * @param ruleInfractionDescription
	 */
	public void setRuleInfractionDescription(String ruleInfractionDescription)
	{
		this.ruleInfractionDescription = ruleInfractionDescription;
	}

	/**
	 * @param schoolAttendanceStatusDescription
	 */
	public void setSchoolAttendanceStatusDescription(String schoolAttendanceStatusDescription)
	{
		this.schoolAttendanceStatusDescription = schoolAttendanceStatusDescription;
	}

	/**
	 * @return
	 */
	public String getGradesRepeatedDescription()
	{
		return gradesRepeatedDescription;
	}

	/**
	 * @param gradesRepeatedCode
	 */
	public void setGradesRepeatedCode(String gradesRepeatedCode)
	{
		this.gradesRepeatedCode = gradesRepeatedCode;
	}

	/**
	 * @param gradesRepeatedDescription
	 */
	public void setGradesRepeatedDescription(String gradesRepeatedDescription)
	{
		this.gradesRepeatedDescription = gradesRepeatedDescription;
	}

	/**
	 * @param ruleInfractionCode
	 */
	public void setRuleInfractionCode(String ruleInfractionCode)
	{
		this.ruleInfractionCode = ruleInfractionCode;
	}
	/** Sorts by school name
	 * @see Comparable#compareTo(Object)
	 */
	public int compareTo(Object schoolObj)
	{
		JuvenileSchoolHistoryResponseEvent school = (JuvenileSchoolHistoryResponseEvent) schoolObj;
		return this.getSchool().compareTo(school.getSchool());
	}

	public String getInstructionType() {
		return instructionType;
	}

	public void setInstructionType(String instructionType) {
		this.instructionType = instructionType;
	}
	
	public String getHomeSchoolDistrictId() {
		return homeSchoolDistrictId;
	}

	public void setHomeSchoolDistrictId(String homeSchoolDistrictId) {
		this.homeSchoolDistrictId = homeSchoolDistrictId;
	}

	public String getHomeSchoolDistrict() {
		return homeSchoolDistrict;
	}

	public void setHomeSchoolDistrict(String homeSchoolDistrict) {
		this.homeSchoolDistrict = homeSchoolDistrict;
	}

	public String getHomeSchoolId() {
		return homeSchoolId;
	}

	public void setHomeSchoolId(String homeSchoolId) {
		this.homeSchoolId = homeSchoolId;
	}

	public void setHomeSchool(String homeSchool) {
		this.homeSchool = homeSchool;
	}

	public String getHomeSchool() {
		return homeSchool;
	}

	/**
	 * @return the specificSchoolName
	 */
	public String getSpecificSchoolName() {
		return specificSchoolName;
	}

	/**
	 * @param specificSchoolName the specificSchoolName to set
	 */
	public void setSpecificSchoolName(String specificSchoolName) {
		this.specificSchoolName = specificSchoolName;
	}

	/**
	 * @return the schoolStreetNum
	 */
	public String getSchoolStreetNum() {
		return schoolStreetNum;
	}

	/**
	 * @param schoolStreetNum the schoolStreetNum to set
	 */
	public void setSchoolStreetNum(String schoolStreetNum) {
		this.schoolStreetNum = schoolStreetNum;
	}

	/**
	 * @return the schoolStreetName
	 */
	public String getSchoolStreetName() {
		return schoolStreetName;
	}

	/**
	 * @param schoolStreetName the schoolStreetName to set
	 */
	public void setSchoolStreetName(String schoolStreetName) {
		this.schoolStreetName = schoolStreetName;
	}

	/**
	 * @return the schoolZipCodeExt
	 */
	public String getSchoolZipCodeExt() {
		return schoolZipCodeExt;
	}

	/**
	 * @param schoolZipCodeExt the schoolZipCodeExt to set
	 */
	public void setSchoolZipCodeExt(String schoolZipCodeExt) {
		this.schoolZipCodeExt = schoolZipCodeExt;
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the schoolAddress
	 */
	public String getSchoolAddress() {
		return schoolAddress;
	}

	/**
	 * @param schoolAddress the schoolAddress to set
	 */
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}

	/**
	 * @return the schoolCityState
	 */
	public String getSchoolCityState() {
		return schoolCityState;
	}

	/**
	 * @param schoolCityState the schoolCityState to set
	 */
	public void setSchoolCityState(String schoolCityState) {
		this.schoolCityState = schoolCityState;
	}

	/**
	 * @return the splEduCategoryCode
	 */
	public String getSplEduCategoryCode() {
		return splEduCategoryCode;
	}

	/**
	 * @param splEduCategoryCode the splEduCategoryCode to set
	 */
	public void setSplEduCategoryCode(String splEduCategoryCode) {
		this.splEduCategoryCode = splEduCategoryCode;
	}

	/**
	 * @return the splEduCategoryDescription
	 */
	public String getSplEduCategoryDescription() {
		return splEduCategoryDescription;
	}

	/**
	 * @param splEduCategoryDescription the splEduCategoryDescription to set
	 */
	public void setSplEduCategoryDescription(String splEduCategoryDescription) {
		this.splEduCategoryDescription = splEduCategoryDescription;
	}

	/**
	 * @param elibilityEnrollmentDate the elibilityEnrollmentDate to set
	 */
	public void setEligibilityEnrollmentDate(Date eligibilityEnrollmentDate) {
		this.eligibilityEnrollmentDate = eligibilityEnrollmentDate;
	}

	/**
	 * @return the elibilityEnrollmentDate
	 */
	public Date getEligibilityEnrollmentDate() {
		return eligibilityEnrollmentDate;
	}

	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		return createDate;
	}
	
	/**
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		createDate = date;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * 
	 * @param createUser
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * 
	 * @param updateUser
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * 
	 * @return
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * 
	 * @param updateDate
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 
	 * @return
	 */
	public String getCreateJims2User() {
		return createJims2User;
	}

	/**
	 * 
	 * @param createJims2User
	 */
	public void setCreateJims2User(String createJims2User) {
		this.createJims2User = createJims2User;
	}

	/**
	 * 
	 * @return
	 */
	public String getUpdateJims2User() {
		return updateJims2User;
	}

	/**
	 * 
	 * @param updateJims2User
	 */
	public void setUpdateJims2User(String updateJims2User) {
		this.updateJims2User = updateJims2User;
	}

	public String getEducationService()
	{
	    return educationService;
	}

	public void setEducationService(String educationService)
	{
	    this.educationService = educationService;
	}

	public String getGradeChangeReason()
	{
	    return gradeChangeReason;
	}

	public void setGradeChangeReason(String gradeChangeReason)
	{
	    this.gradeChangeReason = gradeChangeReason;
	}

	public String getAcademicPerformance()
	{
	    return academicPerformance;
	}

	public void setAcademicPerformance(String academicPerformance)
	{
	    this.academicPerformance = academicPerformance;
	}

	public String getSchoolInfoVerifiedBy()
	{
	    return schoolInfoVerifiedBy;
	}

	public void setSchoolInfoVerifiedBy(String schoolInfoVerifiedBy)
	{
	    this.schoolInfoVerifiedBy = schoolInfoVerifiedBy;
	}

	public boolean getTruancy()
	{
	    return truancy;
	}

	public void setTruancy(boolean truancy)
	{
	    this.truancy = truancy;
	}

	public String getVerifiedByOther()
	{
	    return verifiedByOther;
	}

	public void setVerifiedByOther(String verifiedByOther)
	{
	    this.verifiedByOther = verifiedByOther;
	}

	public boolean isGedAwarded()
	{
	    return gedAwarded;
	}

	public void setGedAwarded(boolean gedAwarded)
	{
	    this.gedAwarded = gedAwarded;
	}

	public Date getGedAwardedDate()
	{
	    return gedAwardedDate;
	}

	public void setGedAwardedDate(Date gedAwardedDate)
	{
	    this.gedAwardedDate = gedAwardedDate;
	}

	public Date getCompletionDate()
	{
	    return completionDate;
	}

	public void setCompletionDate(Date completionDate)
	{
	    this.completionDate = completionDate;
	}

	public boolean isGedCompleted()
	{
	    return gedCompleted;
	}

	public void setGedCompleted(boolean gedCompleted)
	{
	    this.gedCompleted = gedCompleted;
	}	
	
}
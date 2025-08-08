package messaging.interviewinfo.to;

import java.util.Date;

/**
 *
 */
public class SchoolHistoryTO extends EntryDateTO
{
	private String oid = "";
	
	private String district = "";
	
	private String gradeLevel = "";
	private String appropriateLevel = "";
	private String GPA = "";
	private Date lastDateAttended = null;
	private String enrollmentStatus = "";
	private Date eligibilityEnrollmentDate = null; //Changes for JIMS200077279
	private Date verifiedDate = null;
	
	private String program = "";
	private String participation = "";
	private String ruleInfraction = "";
	private String gradesRepeated = "";
	private String numberOfGradeRepeated = "";
	private String status = "";
	private String specificSchoolName="";
	private String educationService="";
	
	//GED Information
	 private boolean gedAwarded;	
	 private Date   gedAwardedDate;
	 private Date   completionDate;
	 private boolean gedCompleted;
	
	
	 public String getEducationService()
	    {
		return educationService;
	    }

	    public void setEducationService(String educationService)
	    {
		this.educationService = educationService;
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
	 * @return Returns the appropriateLevel.
	 */
	public String getAppropriateLevel() {
		return appropriateLevel;
	}
	/**
	 * @param appropriateLevel The appropriateLevel to set.
	 */
	public void setAppropriateLevel(String appropriateLevel) {
		this.appropriateLevel = appropriateLevel;
	}
	/**
	 * @return Returns the district.
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param district The district to set.
	 */
	public void setDistrict(String district) {
		this.district = district;
	}
	/**
	 * @return Returns the enrollmentStatus.
	 */
	public String getEnrollmentStatus() {
		return enrollmentStatus;
	}
	/**
	 * @param enrollmentStatus The enrollmentStatus to set.
	 */
	public void setEnrollmentStatus(String enrollmentStatus) {
		this.enrollmentStatus = enrollmentStatus;
	}
	/**
	 * @return Returns the gradeLevel.
	 */
	public String getGradeLevel() {
		return gradeLevel;
	}
	/**
	 * @param gradeLevel The gradeLevel to set.
	 */
	public void setGradeLevel(String gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	/**
	 * @return Returns the lastDateAttended.
	 */
	public Date getLastDateAttended() {
		return lastDateAttended;
	}
	/**
	 * @param lastDateAttended The lastDateAttended to set.
	 */
	public void setLastDateAttended(Date lastDateAttended) {
		this.lastDateAttended = lastDateAttended;
	}
	/**
	 * @return Returns the gradesRepeated.
	 */
	public String getGradesRepeated() {
		return gradesRepeated;
	}
	/**
	 * @param gradesRepeated The gradesRepeated to set.
	 */
	public void setGradesRepeated(String gradesRepeated) {
		this.gradesRepeated = gradesRepeated;
	}
	/**
	 * @return Returns the participation.
	 */
	public String getParticipation() {
		return participation;
	}
	/**
	 * @param participation The participation to set.
	 */
	public void setParticipation(String participation) {
		this.participation = participation;
	}
	/**
	 * @return Returns the program.
	 */
	public String getProgram() {
		return program;
	}
	/**
	 * @param program The program to set.
	 */
	public void setProgram(String program) {
		this.program = program;
	}
	/**
	 * @return Returns the ruleInfraction.
	 */
	public String getRuleInfraction() {
		return ruleInfraction;
	}
	/**
	 * @param ruleInfraction The ruleInfraction to set.
	 */
	public void setRuleInfraction(String ruleInfraction) {
		this.ruleInfraction = ruleInfraction;
	}
	/**
	 * @return Returns the oid.
	 */
	public String getOID() {
		return oid;
	}
	/**
	 * @param oid The oid to set.
	 */
	public void setOID(String oid) {
		this.oid = oid;
	}
	
	/**
	 * @return Returns the gPA.
	 */
	public String getGPA() {
		return GPA;
	}
	/**
	 * @param gpa The gPA to set.
	 */
	public void setGPA(String gpa) {
		GPA = gpa;
	}
	/**
	 * @return Returns the oid.
	 */
	public String getOid() {
		return oid;
	}
	/**
	 * @param oid The oid to set.
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return Returns the verifiedDate.
	 */
	public Date getVerifiedDate() {
		return verifiedDate;
	}
	/**
	 * @param verifiedDate The verifiedDate to set.
	 */
	public void setVerifiedDate(Date verifiedDate) {
		this.verifiedDate = verifiedDate;
	}
	public String getNumberOfGradeRepeated() {
		return numberOfGradeRepeated;
	}
	public void setNumberOfGradeRepeated(String numberOfGradeRepeated) {
		this.numberOfGradeRepeated = numberOfGradeRepeated;
	}
	/**
	 * @param eligibilityEnrollmentDate the eligibilityEnrollmentDate to set
	 */
	public void setEligibilityEnrollmentDate(Date eligibilityEnrollmentDate) {
		this.eligibilityEnrollmentDate = eligibilityEnrollmentDate;
	}
	/**
	 * @return the eligibilityEnrollmentDate
	 */
	public Date getEligibilityEnrollmentDate() {
		return eligibilityEnrollmentDate;
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

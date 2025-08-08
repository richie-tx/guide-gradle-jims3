package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

public class GangRiskPrefillResponseEvent extends ResponseEvent{

	private String juvenileName;
	private String gender;
	private String juvNum;
	private String assessingJPO;
	private String dob;
	private String ethnicity;
	private String court;
	private String school;
	private String grade;
	private String dateOfAssessment;

	
	/**
	 * @return the juvenileName
	 */
	public String getJuvenileName() {
		return juvenileName;
	}
	/**
	 * @param juvenileName the juvenileName to set
	 */
	public void setJuvenileName(String juvenileName) {
		this.juvenileName = juvenileName;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the juvNum
	 */
	public String getJuvNum() {
		return juvNum;
	}
	/**
	 * @param juvNum the juvNum to set
	 */
	public void setJuvNum(String juvNum) {
		this.juvNum = juvNum;
	}
	/**
	 * @return the assessingJPO
	 */
	public String getAssessingJPO() {
		return assessingJPO;
	}
	/**
	 * @param assessingJPO the assessingJPO to set
	 */
	public void setAssessingJPO(String assessingJPO) {
		this.assessingJPO = assessingJPO;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	/**
	 * @return the court
	 */
	public String getCourt() {
		return court;
	}
	/**
	 * @param court the court to set
	 */
	public void setCourt(String court) {
		this.court = court;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the ethnicity
	 */
	public String getEthnicity() {
		return ethnicity;
	}
	/**
	 * @param ethnicity the ethnicity to set
	 */
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	/**
	 * @param dateOfAssessment the dateOfAssessment to set
	 */
	public void setDateOfAssessment(String dateOfAssessment) {
		this.dateOfAssessment = dateOfAssessment;
	}
	/**
	 * @return the dateOfAssessment
	 */
	public String getDateOfAssessment() {
		return dateOfAssessment;
	}
}

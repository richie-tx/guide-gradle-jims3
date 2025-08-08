package messaging.interviewinfo.to;



/**
 *
 */
public class EmploymentHistoryTO extends EntryDateTO
{
	private String oid = "";
	private String placeEmployed = "";
	private String workHours = "";
	private String employmentStatus = "";
	private String supervisorName = "";
	private String jobDescription = "";
	private double annualGrossIncome = 0;
	
	
	/**
	 * @return Returns the employmentStatus.
	 */
	public String getEmploymentStatus() {
		return employmentStatus;
	}
	/**
	 * @param employmentStatus The employmentStatus to set.
	 */
	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
	}

	/**
	 * @return Returns the placeEmployed.
	 */
	public String getPlaceEmployed() {
		return placeEmployed;
	}
	/**
	 * @param placeEmployed The placeEmployed to set.
	 */
	public void setPlaceEmployed(String placeEmployed) {
		this.placeEmployed = placeEmployed;
	}
	/**
	 * @return Returns the workHours.
	 */
	public String getWorkHours() {
		return workHours;
	}
	/**
	 * @param workHours The workHours to set.
	 */
	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}
	/**
	 * @return Returns the jobDescription.
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription The jobDescription to set.
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return Returns the supervisorName.
	 */
	public String getSupervisorName() {
		return supervisorName;
	}
	/**
	 * @param supervisorName The supervisorName to set.
	 */
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
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
	 * @return Returns the annualGrossIncome.
	 */
	public double getAnnualGrossIncome() {
		return annualGrossIncome;
	}
	/**
	 * @param annualGrossIncome The annualGrossIncome to set.
	 */
	public void setAnnualGrossIncome(double annualGrossIncome) {
		this.annualGrossIncome = annualGrossIncome;
	}
}

/*
 * Created on Jun 14, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenile.reply;

import java.text.SimpleDateFormat;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileJobResponseEvent extends ResponseEvent  implements Comparable
{
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private double workHours;
	private String juvenileNum;
	private String jobDescription;
	private String employmentStatusId;
	private String employmentStatus;
	private Date entryDate;
	private String employmentPlace;
	private String supervisorLastName;
	private String supervisorFamilyNum;
	private String supervisorMiddleName;
	private String supervisorFirstName;
	private String jobNum;
	private double salary;
	private String salaryRateId;
	private String salaryRate;
	private double annualSalary;
	
	
	/**
	 * @return
	 */
	public String getEmploymentPlace()
	{
		return employmentPlace;
	}

	/**
	 * @return
	 */
	public String getEmploymentStatus()
	{
		return employmentStatus;
	}

	/**
	 * @return
	 */
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @return
	 */
	public String getEntryDateAsString()
	{
		String date = "";
		if (entryDate != null)
			date = dateFormat.format(entryDate);
		return date;
	}

	public int compareTo(Object obj) throws ClassCastException {
		JuvenileJobResponseEvent evt = (JuvenileJobResponseEvent)obj;
		return evt.getEntryDate().compareTo(entryDate);
	}

	/**
	 * @return
	 */
	public String getJobDescription()
	{
		return jobDescription;
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
	public double getWorkHours()
	{
		return workHours;
	}

	/**
	 * @param string
	 */
	public void setEmploymentPlace(String string)
	{
		employmentPlace = string;
	}

	/**
	 * @param string
	 */
	public void setEmploymentStatus(String string)
	{
		employmentStatus = string;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
	}

	/**
	 * @param string
	 */
	public void setJobDescription(String string)
	{
		jobDescription = string;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNum(String string)
	{
		juvenileNum = string;
	}

	/**
	 * @param string
	 */
	public void setWorkHours(double hours)
	{
		workHours = hours;
	}

	/**
	 * @return
	 */
	public String getSupervisorFamilyNum()
	{
		return supervisorFamilyNum;
	}

	/**
	 * @return
	 */
	public String getSupervisorFirstName()
	{
		return supervisorFirstName;
	}

	/**
	 * @return
	 */
	public String getSupervisorLastName()
	{
		return supervisorLastName;
	}

	/**
	 * @return
	 */
	public String getSupervisorMiddleName()
	{
		return supervisorMiddleName;
	}

	/**
	 * @param string
	 */
	public void setSupervisorFamilyNum(String string)
	{
		supervisorFamilyNum = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisorFirstName(String string)
	{
		supervisorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisorLastName(String string)
	{
		supervisorLastName = string;
	}

	/**
	 * @param string
	 */
	public void setSupervisorMiddleName(String string)
	{
		supervisorMiddleName = string;
	}

	/**
	 * @return
	 */
	public String getJobNum()
	{
		return jobNum;
	}

	/**
	 * @param string
	 */
	public void setJobNum(String string)
	{
		jobNum = string;
	}

	/**
	 * @return
	 */
	public String getEmploymentStatusId()
	{
		return employmentStatusId;
	}

	/**
	 * @param string
	 */
	public void setEmploymentStatusId(String string)
	{
		employmentStatusId = string;
	}
	/**
	 * @return Returns the salary.
	 */
	public double getSalary() {
		return salary;
	}
	/**
	 * @return Returns the salaryRate.
	 */
	public String getSalaryRate() {
		return salaryRate;
	}
	/**
	 * @return Returns the salaryRateId.
	 */
	public String getSalaryRateId() {
		return salaryRateId;
	}
	/**
	 * @param salary The salary to set.
	 */
	public void setSalary(double amount) {
		this.salary = amount;
	}
	/**
	 * @param salaryRate The salaryRate to set.
	 */
	public void setSalaryRate(String salaryRate) {
		this.salaryRate = salaryRate;
	}
	/**
	 * @param salaryRateId The salaryRateId to set.
	 */
	public void setSalaryRateId(String salaryRateId) {
		this.salaryRateId = salaryRateId;
	}
	
	/**
	 * @return Returns the annualSalary.
	 */
	public double getAnnualSalary() {
		return annualSalary;
	}
	/**
	 * @param annualSalary The annualSalary to set.
	 */
	public void setAnnualSalary(double annualSalary) {
		this.annualSalary = annualSalary;
	}
}

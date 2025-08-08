/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberEmploymentInfoEvent  extends RequestEvent
{
	private String 	employmentId;

	private String 	statusId;
	private String 	currentEmployer;
	private double 	salary;
	private String 	salaryRateId;
	private double 	workHours;
	private String 	lengthOfEmployment;
	private String 	jobTitle;
	private double  annualNetIncome;

	/**
	 * @return
	 */
	public String getCurrentEmployer()
	{
		return currentEmployer;
	}

	/**
	 * @return
	 */
	public String getEmploymentId()
	{
		return employmentId;
	}



	/**
	 * @return
	 */
	public String getJobTitle()
	{
		return jobTitle;
	}

	/**
	 * @return
	 */
	public String getLengthOfEmployment()
	{
		return lengthOfEmployment;
	}

	/**
	 * @return
	 */
	public double getSalary()
	{
		return salary;
	}

	/**
	 * @return
	 */
	public String getSalaryRateId()
	{
		return salaryRateId;
	}

	/**
	 * @return
	 */
	public String getStatusId()
	{
		return statusId;
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
	public void setCurrentEmployer(String string)
	{
		currentEmployer = string;
	}

	/**
	 * @param string
	 */
	public void setEmploymentId(String string)
	{
		employmentId = string;
	}



	/**
	 * @param string
	 */
	public void setJobTitle(String string)
	{
		jobTitle = string;
	}

	/**
	 * @param string
	 */
	public void setLengthOfEmployment(String string)
	{
		lengthOfEmployment = string;
	}

	/**
	 * @param string
	 */
	public void setSalary(double amount)
	{
		salary = amount;
	}

	/**
	 * @param string
	 */
	public void setSalaryRateId(String string)
	{
		salaryRateId = string;
	}

	/**
	 * @param string
	 */
	public void setStatusId(String string)
	{
		statusId = string;
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
	public double getAnnualNetIncome()
	{
		return annualNetIncome;
	}

	/**
	 * @param d
	 */
	public void setAnnualNetIncome(double d)
	{
		annualNetIncome = d;
	}

}

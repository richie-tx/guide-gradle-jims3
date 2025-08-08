/*
 * Created on Oct 13, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilecase.reply;

import java.util.Date;

import messaging.juvenile.reply.JuvenileJobResponseEvent;
import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class FamilyMemberEmploymentInfoResponseEvent extends ResponseEvent implements Comparable
{
	private String 	employmentId;
	private String 	statusId;
	private String 	currentEmployer;
	private double 	salary;
	private String 	salaryRateId;
	private double 	workHours;
	private String 	lengthOfEmployment;
	private String 	jobTitle;
	private Date 	entryDate;
	private double  annualNetIncome;

	public int compareTo(Object obj) throws ClassCastException {
		FamilyMemberEmploymentInfoResponseEvent evt = (FamilyMemberEmploymentInfoResponseEvent)obj;
		return evt.getEntryDate().compareTo(entryDate);
	}
	
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
	public Date getEntryDate()
	{
		return entryDate;
	}

	/**
	 * @param date
	 */
	public void setEntryDate(Date date)
	{
		entryDate = date;
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

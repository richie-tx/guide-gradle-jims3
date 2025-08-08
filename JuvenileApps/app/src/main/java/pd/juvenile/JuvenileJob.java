package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
 * @referencedType pd.codetable.Code
 * @contextKey EMPLOYMENT_STATUS
 * @detailerDoNotGenerate false
 */
public class JuvenileJob extends PersistentObject
{
	/**
	 * @referencedType pd.codetable.Code
	 * @contextKey EMPLOYMENT_STATUS
	 * @detailerDoNotGenerate false
	 */
	private Code employmentStatus = null;

	private String juvenileId;

	private String jobDescription;

	private String employmentStatusId;

	private String supervisorLastName;

	private double workHours;

	private String supervisorFamilyNum;

	private Date entryDate;

	private String employmentPlace;

	private String supervisorMiddleName;

	private String supervisorFirstName;

	private double salary;

	private String salaryRateId;

	private Code salaryRate = null;

	/**
	 * @roseuid 42B18E4D0251
	 */
	public JuvenileJob()
	{
	}

	/**
	 * Access method for the entryDate property.
	 * 
	 * @return the current value of the entryDate property
	 */
	public Date getEntryDate()
	{
		fetch();
		return entryDate;
	}

	/**
	 * Sets the value of the entryDate property.
	 * 
	 * @param aEntryDate
	 *            the new value of the entryDate property
	 */
	public void setEntryDate(Date aEntryDate)
	{
		if (this.entryDate == null || !this.entryDate.equals(aEntryDate))
		{
			markModified();
		}
		entryDate = aEntryDate;
	}

	/**
	 * Access method for the employmentPlace property.
	 * 
	 * @return the current value of the employmentPlace property
	 */
	public String getEmploymentPlace()
	{
		fetch();
		return employmentPlace;
	}

	/**
	 * Sets the value of the employmentPlace property.
	 * 
	 * @param aEmploymentPlace
	 *            the new value of the employmentPlace property
	 */
	public void setEmploymentPlace(String aEmploymentPlace)
	{
		if (this.employmentPlace == null || !this.employmentPlace.equals(aEmploymentPlace))
		{
			markModified();
		}
		employmentPlace = aEmploymentPlace;
	}

	/**
	 * Access method for the workHours property.
	 * 
	 * @return the current value of the workHours property
	 */
	public double getWorkHours()
	{
		fetch();
		return workHours;
	}

	/**
	 * Sets the value of the workHours property.
	 * 
	 * @param aWorkHours
	 *            the new value of the workHours property
	 */
	public void setWorkHours(double hours)
	{
		if (this.workHours != hours)
		{
			markModified();
		}
		workHours = hours;
	}

	/**
	 * Access method for the jobDescription property.
	 * 
	 * @return the current value of the jobDescription property
	 */
	public String getJobDescription()
	{
		fetch();
		return jobDescription;
	}

	/**
	 * Sets the value of the jobDescription property.
	 * 
	 * @param aJobDescription
	 *            the new value of the jobDescription property
	 */
	public void setJobDescription(String aJobDescription)
	{
		if (this.jobDescription == null || !this.jobDescription.equals(aJobDescription))
		{
			markModified();
		}
		jobDescription = aJobDescription;
	}

	/**
	 * Access method for the supervisorFirstName property.
	 * 
	 * @return the current value of the supervisorFirstName property
	 */
	public String getSupervisorFirstName()
	{
		fetch();
		return supervisorFirstName;
	}

	/**
	 * Sets the value of the supervisorFirstName property.
	 * 
	 * @param aSupervisorFirstName
	 *            the new value of the supervisorFirstName property
	 */
	public void setSupervisorFirstName(String aSupervisorFirstName)
	{
		if (this.supervisorFirstName == null || !this.supervisorFirstName.equals(aSupervisorFirstName))
		{
			markModified();
		}
		supervisorFirstName = aSupervisorFirstName;
	}

	/**
	 * Access method for the supervisorMiddleName property.
	 * 
	 * @return the current value of the supervisorMiddleName property
	 */
	public String getSupervisorMiddleName()
	{
		fetch();
		return supervisorMiddleName;
	}

	/**
	 * Sets the value of the supervisorMiddleName property.
	 * 
	 * @param aSupervisorMiddleName
	 *            the new value of the supervisorMiddleName property
	 */
	public void setSupervisorMiddleName(String aSupervisorMiddleName)
	{
		if (this.supervisorMiddleName == null || !this.supervisorMiddleName.equals(aSupervisorMiddleName))
		{
			markModified();
		}
		supervisorMiddleName = aSupervisorMiddleName;
	}

	/**
	 * Access method for the supervisorLastName property.
	 * 
	 * @return the current value of the supervisorLastName property
	 */
	public String getSupervisorLastName()
	{
		fetch();
		return supervisorLastName;
	}

	/**
	 * Sets the value of the supervisorLastName property.
	 * 
	 * @param aSupervisorLastName
	 *            the new value of the supervisorLastName property
	 */
	public void setSupervisorLastName(String aSupervisorLastName)
	{
		if (this.supervisorLastName == null || !this.supervisorLastName.equals(aSupervisorLastName))
		{
			markModified();
		}
		supervisorLastName = aSupervisorLastName;
	}

	/**
	 * Access method for the supervisorFamilyNum property.
	 * 
	 * @return the current value of the supervisorFamilyNum property
	 */
	public String getSupervisorFamilyNum()
	{
		fetch();
		return supervisorFamilyNum;
	}

	/**
	 * Sets the value of the supervisorFamilyNum property.
	 * 
	 * @param aSupervisorFamilyNum
	 *            the new value of the supervisorFamilyNum property
	 */
	public void setSupervisorFamilyNum(String aSupervisorFamilyNum)
	{
		if (this.supervisorFamilyNum == null || !this.supervisorFamilyNum.equals(aSupervisorFamilyNum))
		{
			markModified();
		}
		supervisorFamilyNum = aSupervisorFamilyNum;
	}

	/**
	 * @roseuid 42B183080030
	 */
	public static JuvenileJob find(String jobId)
	{
		IHome home = new Home();
		Object obj = home.find(jobId, JuvenileJob.class);
		return (JuvenileJob) obj;
	}

	/**
	 * @roseuid 42B183080031
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * Finds juvenile drug list by an event
	 * 
	 * @return Iterator of drugs list
	 * @param event
	 */

	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator jobs = home.findAll(attrName, attrValue, JuvenileJob.class);
		return jobs;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setEmploymentStatusId(String employmentStatusId)
	{
		if (this.employmentStatusId == null || !this.employmentStatusId.equals(employmentStatusId))
		{
			markModified();
		}
		employmentStatus = null;
		this.employmentStatusId = employmentStatusId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getEmploymentStatusId()
	{
		fetch();
		return employmentStatusId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initEmploymentStatus()
	{
		if (employmentStatus == null)
		{
			try
			{
				employmentStatus = (Code) new mojo.km.persistence.Reference(employmentStatusId,
						Code.class, PDCodeTableConstants.EMPLOYMENT_STATUS).getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getEmploymentStatus()
	{
		initEmploymentStatus();
		return employmentStatus;
	}

	/**
	 * set the type reference for class member employmentStatus
	 */
	public void setEmploymentStatus(Code employmentStatus)
	{
		if (this.employmentStatus == null || !this.employmentStatus.equals(employmentStatus))
		{
			markModified();
		}
		if (employmentStatus.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(employmentStatus);
		}
		setEmploymentStatusId("" + employmentStatus.getOID());
		employmentStatus.setContext(PDCodeTableConstants.EMPLOYMENT_STATUS);
		this.employmentStatus = (Code) new mojo.km.persistence.Reference(employmentStatus).getObject();
	}

	/**
	 * Set the reference value to class :: pd.juvenile.Juvenile
	 */
	public void setJuvenileId(String juvenileId)
	{
		if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
		{
			markModified();
		}
		this.juvenileId = juvenileId;
	}

	public String getJuvenileId()
	{
		fetch();
		return juvenileId;
	}


	/**
	 * @return Returns the salary.
	 */
	public double getSalary()
	{
		fetch();
		return salary;
	}

	/**
	 * @param salary
	 *            The salary to set.
	 */
	public void setSalary(double amount)
	{
		if (this.salary != amount)
		{
			markModified();
		}
		this.salary = amount;
	}

	public void setSalaryRateId(String salaryRateId)
	{
		if (this.salaryRateId == null || !this.salaryRateId.equals(salaryRateId))
		{
			markModified();
		}
		salaryRate = null;
		this.salaryRateId = salaryRateId;
	}

	/**
	 * Get the reference value to class :: pd.codetable.Code
	 */
	public String getSalaryRateId()
	{
		fetch();
		return salaryRateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 */
	private void initSalaryRate()
	{
		if (salaryRate == null)
		{
			try
			{
				salaryRate = (Code) new mojo.km.persistence.Reference(salaryRateId,
						Code.class, "SALARY_RATE").getObject();
			}
			catch (Throwable t)
			{
			}
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 */
	public Code getSalaryRate()
	{
		initSalaryRate();
		return salaryRate;
	}

	/**
	 * set the type reference for class member salaryRate
	 */
	public void setSalaryRate(Code salaryRate)
	{
		if (this.salaryRate == null || !this.salaryRate.equals(salaryRate))
		{
			markModified();
		}
		if (salaryRate.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(salaryRate);
		}
		setSalaryRateId("" + salaryRate.getOID());
		salaryRate.setContext("SALARY_RATE");
		this.salaryRate = (Code) new mojo.km.persistence.Reference(salaryRate).getObject();
	}

}

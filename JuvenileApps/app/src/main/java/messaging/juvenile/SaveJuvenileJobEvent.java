//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenile\\SaveJuvenileJobEvent.java

package messaging.juvenile;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class SaveJuvenileJobEvent extends RequestEvent 
{
   private String juvenileNum;
   private String employmentPlace;
   private Date entryDate;
   private String jobDescription;
   private String juvenileJobId;
   private String workHrsFrom;
   private double workHours;
   private String employmentStatus;
   private String supervisorLastName;
   private String supervisorFamilyNum;
   private String supervisorMiddleName;
   private String supervisorFirstName;
   private double salary;
   private String salaryRate;
   
   /**
    * @roseuid 42B1968B009C
    */
   public SaveJuvenileJobEvent() 
   {
    
   }
   
   /**
    * @param juvenileNum
    * @roseuid 42B18309009C
    */
   public void setJuvenileNum(String juvenileNum) 
   {
     this.juvenileNum = juvenileNum;
   }
   
   /**
    * @return String
    * @roseuid 42B18309009E
    */
   public String getJuvenileNum() 
   {
    return juvenileNum;
   }
   
   /**
    * @param employmentPlace
    * @roseuid 42B1830900A0
    */
   public void setEmploymentPlace(String employmentPlace) 
   {
     this.employmentPlace = employmentPlace;
   }
   
   /**
    * @roseuid 42B1830900AC
    */
   public String getEmploymentPlace() 
   {
     return employmentPlace;
   }
   
   /**
    * @param entryDate
    * @roseuid 42B1830900AE
    */
   public void setEntryDate(Date entryDate) 
   {
    	this.entryDate = entryDate;
   }
   
   /**
    * @roseuid 42B1830900B0
    */
   public Date getEntryDate() 
   {
   		return entryDate;
   }
   
   /**
    * @param jobDescription
    * @roseuid 42B1830900BB
    */
   public void setJobDescription(String jobDescription) 
   {
    	this.jobDescription = jobDescription;
   }
   
   /**
    * @roseuid 42B1830900BD
    */
   public String getJobDescription() 
   {
    	return jobDescription;
   }
   
   /**
    * @param juvenileJobId
    * @roseuid 42B1830900BF
    */
   public void setJuvenileJobId(String juvenileJobId) 
   {
    	this.juvenileJobId = juvenileJobId;
   }
   
   /**
    * @roseuid 42B1830900CC
    */
   public String getJuvenileJobId() 
   {
    	return juvenileJobId;
   }
   
   /**
    * @param workHrsFrom
    * @roseuid 42B1830900CE
    */
   public void setWorkHrsFrom(String workHrsFrom) 
   {
    	this.workHrsFrom = workHrsFrom;
   }
   
   /**
    * @roseuid 42B1830900D0
    */
   public String getWorkHrsFrom() 
   {
    	return workHrsFrom;
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
 * @return
 */
public double getWorkHours()
{
	return workHours;
}

/**
 * @param string
 */
public void setEmploymentStatus(String employmentStatus)
{
	this.employmentStatus = employmentStatus;
}

/**
 * @param string
 */
public void setSupervisorFamilyNum(String supervisorFamilyNum)
{
	this.supervisorFamilyNum = supervisorFamilyNum;
}

/**
 * @param string
 */
public void setSupervisorFirstName(String supervisorFirstName)
{
	this.supervisorFirstName = supervisorFirstName;
}

/**
 * @param string
 */
public void setSupervisorLastName(String supervisorLastName)
{
	this.supervisorLastName = supervisorLastName;
}

/**
 * @param string
 */
public void setSupervisorMiddleName(String supervisorMiddleName)
{
	this.supervisorMiddleName = supervisorMiddleName;
}

/**
 * @param string
 */
public void setWorkHours(double workHours)
{
	this.workHours = workHours;
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
 * @param salary The salary to set.
 */
public void setSalary(double salary) {
	this.salary = salary;
}
/**
 * @param salaryRate The salaryRate to set.
 */
public void setSalaryRate(String salaryRate) {
	this.salaryRate = salaryRate;
}
}

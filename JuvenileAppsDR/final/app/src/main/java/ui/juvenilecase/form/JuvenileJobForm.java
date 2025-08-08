/*
 * Created on Jun 24, 2005
 *
 */
package ui.juvenilecase.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.juvenile.reply.JuvenileJobResponseEvent;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ui.common.CodeHelper;
import ui.common.UIUtil;

/**
 * @author sprakash
 *  
 */
public class JuvenileJobForm extends ActionForm
{
    //private Code employmentStatus = null;
    private String juvenileId;

    private String jobDescription;

    private String employmentStatusId;

    private String supervisorLastName;

    private double workHours;

    private String supervisorFamilyNum;

    private String entryDate;

    private String employmentPlace;

    private String supervisorMiddleName;

    private String supervisorFirstName;
    
    private String errorMessage;

    private Map jobMap;

    private Collection jobs;

    private Collection jobsIndex;

    private String JobNum;

    private String employmentStatusDesc;

    private String tab;

    private Collection juvenileJobs;

    private Collection empolymentStatus;

    private double salary;
    
    private double annualSalary;

    private String salaryRateId;

    private String salaryRateDesc;

    private Collection salaryRate;
    
    private Collection activeFamMembers;

    private JuvenileJobResponseEvent currentJobDetails;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private String action = "";

    public JuvenileJobForm()
    {

    }

    public void setJobProperties(JuvenileJobResponseEvent jobRespEvent)
    {
        this.juvenileId = jobRespEvent.getJuvenileNum();
        this.jobDescription = jobRespEvent.getJobDescription();
        this.employmentStatusDesc = jobRespEvent.getEmploymentStatus();
        this.salaryRateDesc = jobRespEvent.getSalaryRate();
        this.supervisorLastName = jobRespEvent.getSupervisorLastName();
        this.supervisorMiddleName = jobRespEvent.getSupervisorMiddleName();
        this.supervisorFirstName = jobRespEvent.getSupervisorFirstName();
        this.workHours = jobRespEvent.getWorkHours();
        this.supervisorFamilyNum = jobRespEvent.getSupervisorFamilyNum();
        this.entryDate = jobRespEvent.getEntryDateAsString();
        this.employmentPlace = jobRespEvent.getEmploymentPlace();
        this.JobNum = jobRespEvent.getJobNum();
        this.salary = jobRespEvent.getSalary();
        this.setCurrentJobDetails(jobRespEvent);
        this.setAnnualSalary(jobRespEvent.getAnnualSalary());
    }

    /**
     * @return Returns the currentJobDetails.
     */
    public JuvenileJobResponseEvent getCurrentJobDetails()
    {
        return currentJobDetails;
    }

    /**
     * @param currentJobDetails
     *            The currentJobDetails to set.
     */
    public void setCurrentJobDetails(JuvenileJobResponseEvent currentJobDetails)
    {
        this.currentJobDetails = currentJobDetails;
    }

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
    //		public Code getEmploymentStatus()
    //		{
    //			return employmentStatus;
    //		}
    /**
     * @return
     */
    public String getEmploymentStatusId()
    {
        return employmentStatusId;
    }

    public String getSalaryRateId()
    {
        return salaryRateId;
    }

    /**
     * @return
     */
    public String getEntryDate()
    {

        return entryDate;
    }

    public String getEntryDateDisplay()
    {
        String date = "";
        if (entryDate != null)
            date = dateFormat.format(entryDate);
        return date;
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
    public String getJuvenileId()
    {
        return juvenileId;
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
    public void setEmploymentPlace(String string)
    {
        employmentPlace = string;
    }

    /**
     * @param code
     */
    //		public void setEmploymentStatus(Code code)
    //		{
    //			employmentStatus = code;
    //		}
    /**
     * @param string
     */
    public void setEmploymentStatusId(String string)
    {
        employmentStatusId = string;
    }

    public void setSalaryRateId(String string)
    {
        salaryRateId = string;
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
    public void setJuvenileId(String string)
    {
        juvenileId = string;
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
     * @param string
     */
    public void setWorkHours(double amount)
    {
        workHours = amount;
    }

    /**
     * @return
     */
    public Map getJobMap()
    {
        return jobMap;
    }

    /**
     * @param map
     */
    public void setJobMap(Map map)
    {
        jobMap = map;
    }

    /**
     * @return
     */
    public Collection getJuvenileJobs()
    {
        return juvenileJobs;
    }

    /**
     * @param collection
     */
    public void setJuvenileJobs(Collection collection)
    {
        juvenileJobs = collection;
    }

    /**
     * @return
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }

    /**
     * @param string
     */
    public void setErrorMessage(String string)
    {
        errorMessage = string;
    }

    /**
     * @return
     */
    public Collection getJobsIndex()
    {
        return jobsIndex;
    }

    /**
     * @param collection
     */
    public void setJobsIndex(Collection collection)
    {
        jobsIndex = collection;
    }

    public void reset(ActionMapping aMapping, HttpServletRequest aRequest)
    {

        String actionRequest = aRequest.getParameter("action");
        /*
         * if (actionRequest != null && "create".equals(actionRequest)) {
         * this.clear(); this.action = actionRequest; } else
         */
        if (actionRequest != null && "modify".equals(actionRequest))
        {
            JuvenileJobResponseEvent juvenileEvent = (JuvenileJobResponseEvent) aRequest.getAttribute("juvenileEvent");
            this.setAnnualSalary(juvenileEvent.getAnnualSalary());
            this.setWorkHours(juvenileEvent.getWorkHours());
            this.setJuvenileId(juvenileEvent.getJuvenileNum());  
            this.setJobDescription(juvenileEvent.getJobDescription());      
            this.setEmploymentStatusId(juvenileEvent.getEmploymentStatusId());
            this.setEmploymentPlace(juvenileEvent.getEmploymentPlace());
            this.setEmploymentStatusDesc(juvenileEvent.getEmploymentStatus());
            //this.setEntryDate(juvenileEvent.getEntryDate());
        	this.setSupervisorLastName(juvenileEvent.getSupervisorLastName());
        	this.setSupervisorFamilyNum(juvenileEvent.getSupervisorFamilyNum());
        	this.setSupervisorFirstName(juvenileEvent.getSupervisorFirstName());
            this.setSupervisorMiddleName(juvenileEvent.getSupervisorMiddleName());
        	this.setJobNum(juvenileEvent.getJobNum());
        	this.setSalary(juvenileEvent.getSalary());
        	this.setSalaryRateId(juvenileEvent.getSalaryRateId());
        	this.setSalaryRateDesc(juvenileEvent.getSalaryRate());
        }
        //selectedJobs = null;
    }

    public void clear()
    {
        this.employmentPlace = "";
        this.employmentStatusDesc = "";
        this.salaryRateDesc = "";
        this.employmentStatusId = "";
        this.salaryRateId = "";
        this.entryDate = null;
        this.jobDescription = "";
        //		this.jobsIndex = "";
        //		this.juvenileJobs = null;
        this.supervisorFamilyNum = "";
        this.supervisorFirstName = "";
        this.supervisorLastName = "";
        this.supervisorMiddleName = "";
        this.workHours = 0;
        //		this.jobs = null;
        this.salary = 0;
        activeFamMembers=new ArrayList();
    }

    /**
     * @return
     */
    public Collection getJobs()
    {
        return jobs;
    }

    /**
     * @param collection
     */
    public void setJobs(Collection collection)
    {
        jobs = collection;
    }

    /**
     * @param date
     */
    public void setEntryDate(String date)
    {
        entryDate = date;
    }

    /**
     * @return
     */
    public Collection getEmpolymentStatus()
    {
        return empolymentStatus;
    }

    /**
     * @param collection
     */
    public void setEmpolymentStatus(Collection collection)
    {
        empolymentStatus = collection;
    }

    public Collection getEmploymentStatus()
    {
        return CodeHelper.getEmploymentStatusCodes();
    }

    /**
     * Sets the descriptions of the dropdown codes to be displayed on the
     * summary page.
     */
    public void processCodeDescriptions()
    {
        CodeResponseEvent evt;
        if (employmentStatusId != null)
        {
            evt = UIUtil.findCodeResponseEvent(getEmploymentStatus().iterator(), employmentStatusId);
            setEmploymentStatusId(evt.getDescription());
        }
        if (salaryRateId != null)
        {
            evt = UIUtil.findCodeResponseEvent(getSalaryRate().iterator(), salaryRateId);
            setSalaryRateId(evt.getDescription());
        }
    }

    /**
     * @return
     */
    public String getAction()
    {
        return action;
    }

    /**
     * @param string
     */
    public void setAction(String string)
    {
        action = string;
    }

    public String getEmploymentStatusDescription()
    {
        return getCodeDescription(empolymentStatus, employmentStatusId);
    }

    public String getSalaryRateDescription()
    {
        return getCodeDescription(salaryRate, salaryRateId);
    }

    public String getSupervisorName()
    {
        String superName = "";
        if (supervisorLastName == null)
        {
        	superName = this.getSupervisorLastName() + " " + this.getSupervisorFirstName() + " "
			   + this.getSupervisorMiddleName();
        }
        if ((supervisorLastName != null) && (supervisorFirstName != null))
        {
        	superName = this.getSupervisorLastName() + ", " + this.getSupervisorFirstName() + " "
			   + this.getSupervisorMiddleName();
        }
        return superName;
     	
    }

    /**
     * Returns the code description.
     * 
     * @param codeCollection
     *            The code collection.
     * @param codeId
     *            The code id.
     * @return The code description.
     */
    public String getCodeDescription(Collection codeCollection, String codeId)
    {
        String description = "";
        if (codeCollection != null && codeId != null)
        {
            CodeResponseEvent code = UIUtil.findCodeResponseEvent(codeCollection.iterator(), codeId);
            if (code != null)
                description = code.getDescription();
        }
        return description;
    } //end of ui.juvenilecase.form.JuvenileJobForm.getCodeDescription

    /**
     * @return
     */
    public String getJobNum()
    {
        return JobNum;
    }

    /**
     * @param string
     */
    public void setJobNum(String string)
    {
        JobNum = string;
    }

    /**
     * @return
     */
    public String getEmploymentStatusDesc()
    {
        return employmentStatusDesc;
    }

    /**
     * @param string
     */
    public void setEmploymentStatusDesc(String string)
    {
        employmentStatusDesc = string;
    }

    /**
     * @return
     */
    public String getTab()
    {
        return tab;
    }

    /**
     * @param string
     */
    public void setTab(String string)
    {
        tab = string;
    }

    /**
     * @return Returns the salaryRate.
     */
    public Collection getSalaryRate()
    {
        return salaryRate;
    }

    /**
     * @param salaryRate
     *            The salaryRate to set.
     */
    public void setSalaryRate(Collection salaryRate)
    {
        this.salaryRate = salaryRate;
    }

    /**
     * @return Returns the salaryRateDesc.
     */
    public String getSalaryRateDesc()
    {
        return salaryRateDesc;
    }

    /**
     * @param salaryRateDesc
     *            The salaryRateDesc to set.
     */
    public void setSalaryRateDesc(String salaryRateDesc)
    {
        this.salaryRateDesc = salaryRateDesc;
    }

    /**
     * @return Returns the salary.
     */
    public double getSalary()
    {
        return salary;
    }

    /**
     * @param salary
     *            The salary to set.
     */
    public void setSalary(double amount)
    {
        this.salary = amount;
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
	/**
	 * @return Returns the activeFamMembers.
	 */
	public Collection getActiveFamMembers() {
		return activeFamMembers;
	}
	/**
	 * @param activeFamMembers The activeFamMembers to set.
	 */
	public void setActiveFamMembers(Collection activeFamMembers) {
		this.activeFamMembers = activeFamMembers;
	}
}

/*
 * Created on Apr 4, 2007
 *
 */
package messaging.cscdstaffposition.reply;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;

import mojo.km.messaging.ResponseEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *  
 */
public class CSCDSupervisionStaffResponseEvent extends ResponseEvent implements Serializable
{
    private String assignedLogonId;
    private Name assignedName;
    private Collection children;
    private String cjadNum;
    private Date effectiveDate;
    private Date retirementDate;
    private Collection courts;
    private String cstsOfficerTypeId;
    private String divisionId;
    private String divisionName;
    private boolean hasCaseload;
    private String jobTitleId;
    private String locationDetails;
    private String locationId;
    private String organizationId;
    private String parentPositionId;
    private String phoneNum;
    private String positionName;
    private String positionStatusId;
    private String positionTypeId;
    private String probationOfficerInd;
    private String programUnitId;
    private String programUnitName;
    private String sectionId;
    private String sectionName;
    private String staffPositionId;
    private String staffPositionType;
    private String supervisorId;
    private String supervisorLogonId;
    private String supervisorPoi;
    private Name supervisorName;
    private String assignedNameQualifiedByPositionName;
    
    private String jobTitleCode;
    private String jobTitleDesc;
    private String parentPositionDesc;
    private String positionTypeDesc;
    private String positionStatusDesc;
    private String officerTypeDesc;
    
    public static Comparator staffnameComparator = new Comparator() 
    {
		public int compare (Object o1, Object o2) 
		{
			Name assignedName1 = 
				((CSCDSupervisionStaffResponseEvent) o1).getAssignedName();
			Name assignedName2 = 
				((CSCDSupervisionStaffResponseEvent) o2).getAssignedName();
			
			String formattedName1 = "";
			String formattedName2 = "";
			if (assignedName1 != null) 
			{
				formattedName1 = assignedName1.getFormattedName();
			} 
			if (assignedName2 != null) 
			{
				formattedName2 = assignedName2.getFormattedName();
			}
			return formattedName1.compareTo(formattedName2);					
		}
    };
		
    public String getJobTitleCode() {
		return jobTitleCode;
	}
	public void setJobTitleCode(String jobTitleCode) {
		this.jobTitleCode = jobTitleCode;
	}
	public String getJobTitleDesc() {
		return jobTitleDesc;
	}
	public void setJobTitleDesc(String jobTitleDesc) {
		this.jobTitleDesc = jobTitleDesc;
	}
	public String getOfficerTypeDesc() {
		return officerTypeDesc;
	}
	public void setOfficerTypeDesc(String officerTypeDesc) {
		this.officerTypeDesc = officerTypeDesc;
	}
	public String getParentPositionDesc() {
		return parentPositionDesc;
	}
	public void setParentPositionDesc(String parentPositionDesc) {
		this.parentPositionDesc = parentPositionDesc;
	}
	public String getPositionStatusDesc() {
		return positionStatusDesc;
	}
	public void setPositionStatusDesc(String positionStatusDesc) {
		this.positionStatusDesc = positionStatusDesc;
	}
	public String getPositionTypeDesc() {
		return positionTypeDesc;
	}
	public void setPositionTypeDesc(String positionTypeDesc) {
		this.positionTypeDesc = positionTypeDesc;
	}
	/**
     * @return Returns the divisionName.
     */
    public String getDivisionName() {
        return divisionName;
    }
    /**
     * @param divisionName The divisionName to set.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }
    /**
     * @return Returns the programUnitName.
     */
    public String getProgramUnitName() {
        return programUnitName;
    }
    /**
     * @param programUnitName The programUnitName to set.
     */
    public void setProgramUnitName(String programUnitName) {
        this.programUnitName = programUnitName;
    }
    /**
     * @return Returns the sectionName.
     */
    public String getSectionName() {
        return sectionName;
    }
    /**
     * @param sectionName The sectionName to set.
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
    private Collection workGroups;

    public void addChild(CSCDSupervisionStaffResponseEvent sre)
    {
        if (children == null)
        {
            children = new ArrayList();
        }
        children.add(sre);
    }
    public void addCourt(String courtId){
        if (courts == null){
            courts = new ArrayList();
        }
        courts.add(courtId);
    }
    
    public void addWorkGroup(String workGroupName){
        if (workGroups == null){
            workGroups = new ArrayList();
        }
        workGroups.add(workGroupName);
    }

    /**
     * @return Returns the userProfileId.
     */
    public String getAssignedLogonId()
    {
        return assignedLogonId;
    }

    /**
     * @return Returns the assignedName.
     */
    public Name getAssignedName()
    {
        return assignedName;
    }

    public Collection getChildren()
    {
        return children;
    }

    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum()
    {
        return cjadNum;
    }
    /**
     * @return Returns the courts.
     */
    public Collection getCourts() {
        return courts;
    }

    /**
     * @return Returns the cstsOfficerTypeId.
     */
    public String getCstsOfficerTypeId()
    {
        return cstsOfficerTypeId;
    }

	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
    /**
     * @return Returns the jobTitleId.
     */
    public String getJobTitleId()
    {
        return jobTitleId;
    }

    /**
     * @return Returns the locationDetails.
     */
    public String getLocationDetails()
    {
        return locationDetails;
    }

    /**
     * @return Returns the locationId.
     */
    public String getLocationId()
    {
        return locationId;
    }

    /**
     * @return Returns the organizationId.
     */
    public String getOrganizationId()
    {
        return organizationId;
    }

    /**
     * @return Returns the parentPositionId.
     */
    public String getParentPositionId()
    {
        return parentPositionId;
    }

    /**
     * @return Returns the phoneNum.
     */
    public String getPhoneNum()
    {
        return phoneNum;
    }

    /**
     * @return Returns the positionName.
     */
    public String getPositionName()
    {
        return positionName;
    }

    /**
     * @return Returns the statusId.
     */
    public String getPositionStatusId()
    {
        return positionStatusId;
    }

    /**
     * @return Returns the positionTypeId.
     */
    public String getPositionTypeId()
    {
        return positionTypeId;
    }

    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd()
    {
        return probationOfficerInd;
    }
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
    /**
     * @return Returns the sectionId.
     */
    public String getSectionId() {
        return sectionId;
    }
    /**
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId()
    {
        return staffPositionId;
    }

    /**
     * @return Returns the staffPositionType.
     */
    public String getStaffPositionType()
    {
        return staffPositionType;
    }

    /**
     * @return Returns the parentPositionId.
     */
    public String getSupervisorId()
    {
        return supervisorId;
    }

    /**
     * @return Returns the supervisorName.
     */
    public Name getSupervisorName()
    {
        return supervisorName;
    }
    /**
     * @return Returns the workGroups.
     */
    public Collection getWorkGroups() {
        return workGroups;
    }

    /**
     * @return Returns the hasCaseload.
     */
    public boolean isHasCaseload()
    {
        return hasCaseload;
    }

    public String getAssignedNameQualifiedByPositionName() {
    	String staff_name = "";
    	Name assigned_name = this.getAssignedName();
    	if (assigned_name != null)
    	{
    		staff_name = assigned_name.getFormattedName();
    		if(staff_name.equals("")){
    			staff_name = "No Officer Assigned";
    		}
    	}
    	
    	assignedNameQualifiedByPositionName = staff_name + " | " + this.getPositionName();
		return assignedNameQualifiedByPositionName;
	}
	
    /**
     * @param userProfileId
     *            The userProfileId to set.
     */
    public void setAssignedLogonId(String userProfileId)
    {
        this.assignedLogonId = userProfileId;
    }

    /**
     * @param assignedName
     *            The assignedName to set.
     */
    public void setAssignedName(Name assignedName)
    {
        this.assignedName = assignedName;
    }
    /**
     * @param children The children to set.
     */
    public void setChildren(Collection children) {
        this.children = children;
    }

    /**
     * @param cjadNum
     *            The cjadNum to set.
     */
    public void setCjadNum(String cjadNum)
    {
        this.cjadNum = cjadNum;
    }
    /**
     * @param courts The courts to set.
     */
    public void setCourts(Collection courts) {
        this.courts = courts;
    }

    /**
     * @param cstsOfficerTypeId
     *            The cstsOfficerTypeId to set.
     */
    public void setCstsOfficerTypeId(String cstsOfficerTypeId)
    {
        this.cstsOfficerTypeId = cstsOfficerTypeId;
    }
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
    /**
     * @param hasCaseload
     *            The hasCaseload to set.
     */
    public void setHasCaseload(boolean hasCaseload)
    {
        this.hasCaseload = hasCaseload;
    }

    /**
     * @param jobTitleId
     *            The jobTitleId to set.
     */
    public void setJobTitleId(String jobTitleId)
    {
        this.jobTitleId = jobTitleId;
    }

    /**
     * @param locationDetails
     *            The locationDetails to set.
     */
    public void setLocationDetails(String locationDetails)
    {
        this.locationDetails = locationDetails;
    }

    /**
     * @param locationId
     *            The locationId to set.
     */
    public void setLocationId(String locationId)
    {
        this.locationId = locationId;
    }

    /**
     * @param organizationId
     *            The organizationId to set.
     */
    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

    /**
     * @param parentPositionId
     *            The parentPositionId to set.
     */
    public void setParentPositionId(String parentPositionId)
    {
        this.parentPositionId = parentPositionId;
    }

    /**
     * @param phoneNum
     *            The phoneNum to set.
     */
    public void setPhoneNum(String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    /**
     * @param positionName
     *            The positionName to set.
     */
    public void setPositionName(String positionName)
    {
        this.positionName = positionName;
    }

    /**
     * @param statusId
     *            The statusId to set.
     */
    public void setPositionStatusId(String statusId)
    {
        this.positionStatusId = statusId;
    }

    /**
     * @param positionTypeId
     *            The positionTypeId to set.
     */
    public void setPositionTypeId(String positionTypeId)
    {
        this.positionTypeId = positionTypeId;
//        this.setStaffPositionType(positionTypeId);
    }

    /**
     * @param probationOfficerInd
     *            The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd)
    {
        this.probationOfficerInd = probationOfficerInd;
    }
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
    /**
     * @param sectionId The sectionId to set.
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    /**
     * @param staffPositionId
     *            The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId)
    {
        this.staffPositionId = staffPositionId;
    }
    /**
     * @param staffPositionType The staffPositionType to set.
     */
    public void setStaffPositionType(String staffPositionType) {
        this.staffPositionType = staffPositionType;
    }

    /**
     * @param staffPositionType
     *            The staffPositionType to set.
     */
//    public void setStaffPositionType(String staffPositionTypeId)
//    {
//       
//        this.staffPositionType = PDSupervisionCodeHelper.getSupervisionCodeByCodeId(staffPositionTypeId).getDescription();
//
//    }

    /**
     * @param parentPositionId
     *            The parentPositionId to set.
     */
    public void setSupervisorId(String parentPositionId)
    {
        this.supervisorId = parentPositionId;
    }

    /**
     * @param supervisorName
     *            The supervisorName to set.
     */
    public void setSupervisorName(Name supervisorName)
    {
        this.supervisorName = supervisorName;
    }
    /**
     * @param workGroups The workGroups to set.
     */
    public void setWorkGroups(Collection workGroups) {
        this.workGroups = workGroups;
    }
    /**
     * @return Returns the supervisorLogonId.
     */
    public String getSupervisorLogonId() {
        return supervisorLogonId;
    }
    /**
     * @param supervisorLogonId The supervisorLogonId to set.
     */
    public void setSupervisorLogonId(String supervisorLogonId) {
        this.supervisorLogonId = supervisorLogonId;
    }
    /**
     * @return Returns the supervisorPoi.
     */
    public String getSupervisorPoi() {
        return supervisorPoi;
    }
    /**
     * @param supervisorPoi The supervisorPoi to set.
     */
    public void setSupervisorPoi(String supervisorPoi) {
        this.supervisorPoi = supervisorPoi;
    }
	
	public void setAssignedNameQualifiedByPositionName(String name) {
		this.assignedNameQualifiedByPositionName = name;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getRetirementDate() {
		return retirementDate;
	}
	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}
}

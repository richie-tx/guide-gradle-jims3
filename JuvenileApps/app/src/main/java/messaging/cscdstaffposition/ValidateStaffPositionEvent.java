//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\ValidatePositionEvent.java

package messaging.cscdstaffposition;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

public class ValidateStaffPositionEvent extends RequestEvent 
{
	private String agencyId;
	private String cjadNum;
	private Collection courts;
    private	String divisionId;
    private String jobTitleId;
	private	String positionTypeId;
	private	String probationOfficerInd;
    private String programUnitId;
    private String sectionId;
	private	String staffPositionId;
	private String statusId;
	private String positionName;
	private boolean hasCaseLoad;
  
   /**
    * @roseuid 460BDCF70173
    */
   public ValidateStaffPositionEvent() 
   {
    
   }
    /**
     * @param courtId
     */
    public void addCourt(String courtId){
        if (courts == null){
            courts = new ArrayList();
        }
        courts.add(courtId);
    }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        return cjadNum;
    }
    /**
     * @return Returns the courts.
     */
    public Collection getCourts() {
        return courts;
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
    public String getJobTitleId() {
        return jobTitleId;
    }
	/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}
	/**
	 * @return Returns the probationOfficerInd.
	 */
	public String getProbationOfficerInd() {
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
	public String getStaffPositionId() {
		return staffPositionId;
	}
    /**
     * @return Returns the statusId.
     */
    public String getStatusId() {
        return statusId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
        this.cjadNum = cjadNum;
    }
    /**
     * @param courts The courts to set.
     */
    public void setCourts(Collection courts) {
        this.courts = courts;
    }
    /**
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }
    /**
     * @param jobTitleId The jobTitleId to set.
     */
    public void setJobTitleId(String jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
	/**
	 * @param positionTypeId The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}
	/**
	 * @param probationOfficerInd The probationOfficerInd to set.
	 */
	public void setProbationOfficerInd(String probationOfficerInd) {
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
	 * @param staffPositionId The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
	public String getPositionName()
	{
		return positionName;
	}
	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}
	public boolean isHasCaseLoad() {
		return hasCaseLoad;
	}
	public void setHasCaseLoad(boolean hasCaseLoad) {
		this.hasCaseLoad = hasCaseLoad;
	}
}

//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\GetCSCDSupervisionStaffEvent.java

package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

public class GetCSCDSupervisionStaffEvent extends RequestEvent 
{
	private String agencyId;
	private	String cjadNum;
	private	String divisionId;
	private	String positionName;
	private	String programSectionId;
	private	String programUnitId;
	private	String staffFirstName;
	private	String staffLastName;
   	private	String staffLogonId;
	private	String staffMiddleName;
	private	String staffPositionId;
	private String statusId;
	private	String workGroupName;
	private String cstsOfficerTypeId;
	private boolean useStaffLogonId=false;

   /**
    * @roseuid 460BDCBF025D
    */
   public GetCSCDSupervisionStaffEvent() 
   {
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
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @return Returns the positionName.
	 */
	public String getPositionName() {
		return positionName;
	}
	/**
	 * @return Returns the programSectionId.
	 */
	public String getProgramSectionId() {
		return programSectionId;
	}
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @return Returns the staffFirstName.
	 */
	public String getStaffFirstName() {
		return staffFirstName;
	}
	/**
	 * @return Returns the staffLastName.
	 */
	public String getStaffLastName() {
		return staffLastName;
	}
	/**
	 * @return Returns the staffLogonId.
	 */
	public String getStaffLogonId() {
		return staffLogonId;
	}
	/**
	 * @return Returns the staffMiddleName.
	 */
	public String getStaffMiddleName() {
		return staffMiddleName;
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
	 * @return Returns the workGroupName.
	 */
	public String getWorkGroupName() {
		return workGroupName;
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
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @param positionName The positionName to set.
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	/**
	 * @param programSectionId The programSectionId to set.
	 */
	public void setProgramSectionId(String programSectionId) {
		this.programSectionId = programSectionId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	/**
	 * @param staffFirstName The staffFirstName to set.
	 */
	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}
	/**
	 * @param staffLastName The staffLastName to set.
	 */
	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}
	/**
	 * @param staffLogonId The staffLogonId to set.
	 */
	public void setStaffLogonId(String staffLogonId) {
		this.staffLogonId = staffLogonId;
	}
	/**
	 * @param staffMiddleName The staffMiddleName to set.
	 */
	public void setStaffMiddleName(String staffMiddleName) {
		this.staffMiddleName = staffMiddleName;
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
	/**
	 * @param workGroupName The workGroupName to set.
	 */
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}
	/**
	 * @return Returns the cstsOfficerTypeId.
	 */
	public String getCstsOfficerTypeId() {
		return cstsOfficerTypeId;
	}
	/**
	 * @param cstsOfficerTypeId The cstsOfficerTypeId to set.
	 */
	public void setCstsOfficerTypeId(String cstsOfficerTypeId) {
		this.cstsOfficerTypeId = cstsOfficerTypeId;
	}
	/**
	 * @return Returns the useStaffLogonId.
	 */
	public boolean isUseStaffLogonId() {
		return useStaffLogonId;
	}
	/**
	 * @param useStaffLogonId The useStaffLogonId to set.
	 */
	public void setUseStaffLogonId(boolean useStaffLogonId) {
		this.useStaffLogonId = useStaffLogonId;
	}
}

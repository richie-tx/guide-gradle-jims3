//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\cscdstaffposition\\GetUsersForCSCDSupervisionStaffEvent.java

package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

public class GetUsersForCSCDSupervisionStaffEvent extends RequestEvent 
{
	private String agencyId;
	private String cjadNum;
	private String positionName;
	private String staffFirstName;
	private String staffLastName;
	private String staffLogonId;
	private String staffMiddleName;
	private String workgroupName;
	private String cstsOfficerTypeId;
	private boolean useStaffLogonId=false;
   /**
    * @roseuid 460BDCD601B1
    */
   public GetUsersForCSCDSupervisionStaffEvent() 
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
     * @return Returns the positionName.
     */
    public String getPositionName() {
        return positionName;
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
	 * @return Returns the workgroupName.
	 */
	public String getWorkgroupName() {
		return workgroupName;
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
     * @param positionName The positionName to set.
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
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
	 * @param workgroupName The workgroupName to set.
	 */
	public void setWorkgroupName(String workgroupName) {
		this.workgroupName = workgroupName;
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

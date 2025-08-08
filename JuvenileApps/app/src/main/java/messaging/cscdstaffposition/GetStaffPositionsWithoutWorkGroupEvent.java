/*
 * Created on Apr 26, 2007
 *
 */
package messaging.cscdstaffposition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetStaffPositionsWithoutWorkGroupEvent extends RequestEvent implements Serializable {
    private String agencyId;
    private String cjadNum;
    private String cstsOfficerTypeId;
    private String divisionId;
    private Collection logonIds;
    private String positionName;
    private String programSectionId;
    private String programUnitId;
    private String staffPositionId;
    private String statusId;

    public void addLogonId(String logonId){
        if (logonIds == null){
            logonIds = new ArrayList();
        }
        logonIds.add(logonId);
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
     * @return Returns the cstsOfficerTypeId.
     */
    public String getCstsOfficerTypeId() {
        return cstsOfficerTypeId;
    }
    /**
     * @return Returns the divisionId.
     */
    public String getDivisionId() {
        return divisionId;
    }
    /**
     * @return Returns the logonIds.
     */
    public Collection getLogonIds() {
        return logonIds;
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
     * @return Returns the staffPositionId.
     */
    public String getStaffPositionId() {
        return staffPositionId;
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
     * @param cstsOfficerTypeId The cstsOfficerTypeId to set.
     */
    public void setCstsOfficerTypeId(String cstsOfficerTypeId) {
        this.cstsOfficerTypeId = cstsOfficerTypeId;
    }
    /**
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(String organizationId) {
        this.divisionId = organizationId;
    }
    /**
     * @param logonIds The logonIds to set.
     */
    public void setLogonIds(Collection logonIds) {
        this.logonIds = logonIds;
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
    public void setProgramSectionId(String programSectionIdId) {
        this.programSectionId = programSectionIdId;
    }
    /**
     * @param programUnitId The programUnitId to set.
     */
    public void setProgramUnitId(String programUnitId) {
        this.programUnitId = programUnitId;
    }
    /**
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId) {
        this.staffPositionId = staffPositionId;
    }
	public String getStatusId()
	{
		return statusId;
	}
	public void setStatusId(String statusId)
	{
		this.statusId = statusId;
	}
}

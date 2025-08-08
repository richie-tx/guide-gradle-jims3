/*
 * Created on Apr 13, 2007
 *
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSupervisorsEvent extends RequestEvent {
    private String agencyId;
    private String divisionId; 
    private String programSectionId;
    private String programUnitId;
    private String staffPositionId;
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the organizationId.
     */
    public String getDivisionId() {
        return divisionId;
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
     * @param organizationId The organizationId to set.
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
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
     * @param staffPositionId The staffPositionId to set.
     */
    public void setStaffPositionId(String staffPositionId) {
        this.staffPositionId = staffPositionId;
    }
}

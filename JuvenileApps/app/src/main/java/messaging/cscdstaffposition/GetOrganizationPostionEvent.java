/*
 * Created on May 10, 2007
 */
package messaging.cscdstaffposition;

import java.util.ArrayList;
import java.util.Collection;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetOrganizationPostionEvent extends RequestEvent {
    private String agencyId;
    private String divisionId;
    private Collection jobTitles;
    private Collection positionTypes;
    private String programSectionId;
    private String programUnitId;
    private String staffPositionId;
    private String statusId;
    /**
     * @param jobTitleId
     */
    public void addJobTitle(String jobTitleId){
        if (jobTitles == null){
            jobTitles = new ArrayList();
        }
        jobTitles.add(jobTitleId);
    }

    public void addPositionTypeId(String positionTypeId){
        if (this.positionTypes == null){
            positionTypes = new ArrayList();
        }
        positionTypes.add(positionTypeId);
    }
    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @return Returns the divisionId.
     */
    public String getDivisionId() {
        return divisionId;
    }
    /**
     * @return Returns the jobTitles.
     */
    public Collection getJobTitles() {
        return jobTitles;
    }
    /**
     * @return Returns the positionTypes.
     */
    public Collection getPositionTypes() {
        return positionTypes;
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
     * @param divisionId The divisionId to set.
     */
    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }
    /**
     * @param jobTitles The jobTitles to set.
     */
    public void setJobTitles(Collection jobTitles) {
        this.jobTitles = jobTitles;
    }
    /**
     * @param positionTypes The positionTypes to set.
     */
    public void setPositionTypes(Collection positionTypes) {
        this.positionTypes = positionTypes;
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
    /**
     * @param statusId The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
}

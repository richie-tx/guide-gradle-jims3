/*
 * Created on Dec 11, 2007
 *
 */
package messaging.legacycscdstaff;

import java.util.Date;

import mojo.km.messaging.RequestEvent;
import mojo.km.utilities.Name;

/**
 * @author dgibler
 *
 */
public class UpdateLegacyStaffAssignmentEvent extends RequestEvent {
    private String cjadNum;
    private String courtNum;
    private Date effectiveDate;
    private String legacyProgramUnit;
    private String location;
    private Name officerName;
    private String phoneNum;
    private String poi;
    
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        return cjadNum;
    }
    /**
     * @return Returns the courtNum.
     */
    public String getCourtNum() {
        return courtNum;
    }
    /**
     * @return Returns the effectiveDate.
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }
    /**
     * @return Returns the legacyProgramUnit.
     */
    public String getLegacyProgramUnit() {
        return legacyProgramUnit;
    }
    /**
     * @return Returns the location.
     */
    public String getLocation() {
        return location;
    }
    /**
     * @return Returns the officerName.
     */
    public Name getOfficerName() {
        return officerName;
    }
    /**
     * @return Returns the phoneNum.
     */
    public String getPhoneNum() {
        return phoneNum;
    }
    /**
     * @return Returns the poi.
     */
    public String getPoi() {
        return poi;
    }
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
        this.cjadNum = cjadNum;
    }
    /**
     * @param courtNum The courtNum to set.
     */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
    }
    /**
     * @param effectiveDate The efectiveDate to set.
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    /**
     * @param legacyProgramUnit The legacyProgramUnit to set.
     */
    public void setLegacyProgramUnit(String legacyProgramUnit) {
        this.legacyProgramUnit = legacyProgramUnit;
    }
    /**
     * @param location The location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * @param officerName The officerName to set.
     */
    public void setOfficerName(Name officerName) {
        this.officerName = officerName;
    }
    /**
     * @param phoneNum The phoneNum to set.
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    /**
     * @param poi The poi to set.
     */
    public void setPoi(String poi) {
        this.poi = poi;
    }
}

/*
 * Created on Oct 16, 2007
 *
 */
package messaging.cscdstaffposition;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class StaffPositionReassignmentEvent extends RequestEvent {
	private Date assignmentDate;
	private String cjadNum;
	private String criminalCaseId;
	private String operatorId;
	private String probationOfficerInd;
	private String spn;

    /**
     * @return Returns the assignmentDate.
     */
    public Date getAssignmentDate() {
        return assignmentDate;
    }
    /**
     * @return Returns the cjadNum.
     */
    public String getCjadNum() {
        return cjadNum;
    }
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @return Returns the operatorId.
     */
    public String getOperatorId() {
        return operatorId;
    }
    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd() {
        return probationOfficerInd;
    }
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        return spn;
    }
    /**
     * @param assignmentDate The assignmentDate to set.
     */
    public void setAssignmentDate(Date assignmentDate) {
        this.assignmentDate = assignmentDate;
    }
    /**
     * @param cjadNum The cjadNum to set.
     */
    public void setCjadNum(String cjadNum) {
        this.cjadNum = cjadNum;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @param operatorId The operatorId to set.
     */
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    /**
     * @param probationOfficerInd The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd) {
        this.probationOfficerInd = probationOfficerInd;
    }
    /**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
        this.spn = spn;
    }
}

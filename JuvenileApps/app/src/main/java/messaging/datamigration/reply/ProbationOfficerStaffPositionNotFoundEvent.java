/*
 * Created on Nov 1, 2007
 *
 */
package messaging.datamigration.reply;

import java.util.Date;

import messaging.error.reply.ErrorResponseEvent;

/**
 * @author dgibler
 *
 */
public class ProbationOfficerStaffPositionNotFoundEvent extends
        ErrorResponseEvent {
    private String criminalCaseId;
    private String probationOfficerInd;
    private String seqNum;
    private String spn;
    private Date transDate;
    private static final String PIPE = "|";
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @return Returns the probationOfficerInd.
     */
    public String getProbationOfficerInd() {
        return probationOfficerInd;
    }
    /**
     * @return Returns the seqNum.
     */
    public String getSeqNum() {
        return seqNum;
    }
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        return spn;
    }
    /**
     * @return Returns the transDate.
     */
    public Date getTransDate() {
        return transDate;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @param probationOfficerInd The probationOfficerInd to set.
     */
    public void setProbationOfficerInd(String probationOfficerInd) {
        this.probationOfficerInd = probationOfficerInd;
    }
    /**
     * @param seqNum The seqNum to set.
     */
    public void setSeqNum(String seqNum) {
        this.seqNum = seqNum;
    }
    /**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
        this.spn = spn;
    }
    /**
     * @param transDate The transDate to set.
     */
    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.spn);
        sb.append(PIPE);
        sb.append(this.criminalCaseId.substring(0,3));
        sb.append(PIPE);
        sb.append(this.criminalCaseId.substring(3));
        sb.append(PIPE);
        sb.append(this.getProbationOfficerInd());
        return sb.toString();
    }
}

/*
 * Created on Nov 6, 2007
 *
 */
package messaging.datamigration.reply;

import messaging.error.reply.ErrorResponseEvent;

/**
 * @author dgibler
 *
 */
public class NoProbationOfficerAssignmentEvent extends ErrorResponseEvent {
    private String criminalCaseId;
    private String spn;
    private static final String PIPE = "|";
    /**
     * @return Returns the criminalCaseId.
     */
    public String getCriminalCaseId() {
        return criminalCaseId;
    }
    /**
     * @param criminalCaseId The criminalCaseId to set.
     */
    public void setCriminalCaseId(String criminalCaseId) {
        this.criminalCaseId = criminalCaseId;
    }
    /**
     * @return Returns the spn.
     */
    public String getSpn() {
        return spn;
    }
    /**
     * @param spn The spn to set.
     */
    public void setSpn(String spn) {
        this.spn = spn;
    }
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.spn);
        sb.append(PIPE);
        sb.append(this.criminalCaseId.substring(0,3));
        sb.append(PIPE);
        sb.append(this.criminalCaseId.substring(3));
        return sb.toString();
    }
}

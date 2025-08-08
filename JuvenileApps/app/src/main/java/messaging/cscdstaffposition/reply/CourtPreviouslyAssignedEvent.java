/*
 * Created on Aug 7, 2007
 *
 */
package messaging.cscdstaffposition.reply;

import messaging.contact.to.NameBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 */
public class CourtPreviouslyAssignedEvent extends ResponseEvent implements Comparable {

    NameBean assignedName;
    String courtId;
    String courtNum;

    /**
     * @return Returns the assignedName.
     */
    public NameBean getAssignedName() {
        return assignedName;
    }
    /**
     * @return Returns the courtId.
     */
    public String getCourtId() {
        return courtId;
    }
    /**
     * @return Returns the courtNum.
     */
    public String getCourtNum() {
        return courtNum;
    }
    /**
     * @param assignedName The assignedName to set.
     */
    public void setAssignedName(NameBean assignedName) {
        this.assignedName = assignedName;
    }
    /**
     * @param courtId The courtId to set.
     */
    public void setCourtId(String courtId) {
        this.courtId = courtId;
    }
    /**
     * @param courtNum The courtNum to set.
     */
    public void setCourtNum(String courtNum) {
        this.courtNum = courtNum;
    }
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        CourtPreviouslyAssignedEvent e1 = (CourtPreviouslyAssignedEvent) o;
        return this.getCourtId().compareTo(e1.getCourtId());
    }
}

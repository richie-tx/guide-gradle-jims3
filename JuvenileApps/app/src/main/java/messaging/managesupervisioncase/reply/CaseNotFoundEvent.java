package messaging.managesupervisioncase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author jmcnabb
 *
 */
public class CaseNotFoundEvent extends ResponseEvent
{
    private String criminalCaseId;
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
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append(this.criminalCaseId.substring(0,3));
        sb.append(PIPE);
        sb.append(this.criminalCaseId.substring(3));
        return sb.toString();
    }
}

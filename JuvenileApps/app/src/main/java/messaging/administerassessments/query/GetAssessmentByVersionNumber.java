/*
 * Created on Mar 20, 2008
 *
 */
package messaging.administerassessments.query;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *  
 */
public class GetAssessmentByVersionNumber extends RequestEvent {
    private int versionNum;

    private String masterAssessmentId;

    /**
     * @return Returns the masterAssessmentId.
     */
    public String getMasterAssessmentId() {
        return masterAssessmentId;
    }

    /**
     * @param masterAssessmentId
     *            The masterAssessmentId to set.
     */
    public void setMasterAssessmentId(String masterAssessmentId) {
        this.masterAssessmentId = masterAssessmentId;
    }

    /**
     * @return Returns the versionNum.
     */
    public int getVersionNum() {
        return versionNum;
    }

    /**
     * @param versionNum
     *            The versionNum to set.
     */
    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }
}

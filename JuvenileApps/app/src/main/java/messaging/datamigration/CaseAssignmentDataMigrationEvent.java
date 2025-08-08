/*
 * Created on Oct 31, 2007
 *
  */
package messaging.datamigration;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class CaseAssignmentDataMigrationEvent extends RequestEvent {
    private int restartKey;
    private String defendantId;
    private boolean isUpdate;
    /**
     * @return Returns the restartKey.
     */
    public int getRestartKey() {
        return restartKey;
    }
    /**
     * @param restartKey The restartKey to set.
     */
    public void setRestartKey(int restartKey) {
        this.restartKey = restartKey;
    }
    /**
     * @return Returns the defendantId.
     */
    public String getDefendantId() {
        return defendantId;
    }
    /**
     * @param defendantId The defendantId to set.
     */
    public void setDefendantId(String defendantId) {
        this.defendantId = defendantId;
    }
    /**
     * @return Returns the isUpdate.
     */
    public boolean isUpdate() {
        return isUpdate;
    }
    /**
     * @param isUpdate The isUpdate to set.
     */
    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }
}

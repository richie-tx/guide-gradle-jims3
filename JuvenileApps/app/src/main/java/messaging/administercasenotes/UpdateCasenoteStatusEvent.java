/*
 * Created on Nov 15, 2006
 *
 */
package messaging.administercasenotes;

import mojo.km.messaging.PersistentEvent;

/**
 * @author dgibler
 *  
 */
public class UpdateCasenoteStatusEvent extends PersistentEvent {
    private String casenoteId;
    private String statusId;
    private boolean autoSaveAsDraft;
    /**
     * @return Returns the casenoteId.
     */
    public String getCasenoteId() {
        return casenoteId;
    }

    /**
     * @return Returns the statusId.
     */
    public String getStatusId() {
        return statusId;
    }

    /**
     * @param casenoteId
     *            The casenoteId to set.
     */
    public void setCasenoteId(String casenoteId) {
        this.casenoteId = casenoteId;
    }

    /**
     * @param statusId
     *            The statusId to set.
     */
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }
	/**
	 * @return Returns the autoSaveAsDraft.
	 */
	public boolean getAutoSaveAsDraft() {
		return autoSaveAsDraft;
	}
	/**
	 * @param autoSaveAsDraft The autoSaveAsDraft to set.
	 */
	public void setAutoSaveAsDraft(boolean autoSaveAsDraft) {
		this.autoSaveAsDraft = autoSaveAsDraft;
	}
}

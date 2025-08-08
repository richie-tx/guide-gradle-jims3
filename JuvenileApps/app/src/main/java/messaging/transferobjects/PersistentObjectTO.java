/*
 * Created on Aug 22, 2007
 *
 */
package messaging.transferobjects;

import java.sql.Timestamp;
import java.util.HashMap;

/**
 * @author cc_mdsouza
 *
 */
public class PersistentObjectTO
{

    private String OID;
    private String SID;
    private Timestamp STP;
    private String userID;
    private String createUserID;
    private Timestamp createTimestamp;
    private String createJIMS2UserID;
    private String updateUserID;
    private Timestamp updateTimestamp;
    private String updateJIMS2UserID;
    private Timestamp EXP;
    private String workflowID;
    private transient boolean isDirty = false;
    private transient boolean isNewFlag = true;
    private transient boolean isDeletedFlag = false;
    private transient boolean isComposedFlag = false;
    private transient HashMap currentParents = new HashMap();

	

	
	/**
	 * @return Returns the createJIMS2UserID.
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}
	/**
	 * @param createJIMS2UserID The createJIMS2UserID to set.
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}
	/**
	 * @return Returns the createTimestamp.
	 */
	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}
	/**
	 * @param createTimestamp The createTimestamp to set.
	 */
	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}
	/**
	 * @return Returns the createUserID.
	 */
	public String getCreateUserID() {
		return createUserID;
	}
	/**
	 * @param createUserID The createUserID to set.
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	/**
	 * @return Returns the currentParents.
	 */
	public HashMap getCurrentParents() {
		return currentParents;
	}
	/**
	 * @param currentParents The currentParents to set.
	 */
	public void setCurrentParents(HashMap currentParents) {
		this.currentParents = currentParents;
	}
	/**
	 * @return Returns the eXP.
	 */
	public Timestamp getEXP() {
		return EXP;
	}
	/**
	 * @param exp The eXP to set.
	 */
	public void setEXP(Timestamp exp) {
		EXP = exp;
	}
	/**
	 * @return Returns the isComposedFlag.
	 */
	public boolean isComposedFlag() {
		return isComposedFlag;
	}
	/**
	 * @param isComposedFlag The isComposedFlag to set.
	 */
	public void setComposedFlag(boolean isComposedFlag) {
		this.isComposedFlag = isComposedFlag;
	}
	/**
	 * @return Returns the isDeletedFlag.
	 */
	public boolean isDeletedFlag() {
		return isDeletedFlag;
	}
	/**
	 * @param isDeletedFlag The isDeletedFlag to set.
	 */
	public void setDeletedFlag(boolean isDeletedFlag) {
		this.isDeletedFlag = isDeletedFlag;
	}
	/**
	 * @return Returns the isDirty.
	 */
	public boolean isDirty() {
		return isDirty;
	}
	/**
	 * @param isDirty The isDirty to set.
	 */
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
	}
	/**
	 * @return Returns the isNewFlag.
	 */
	public boolean isNewFlag() {
		return isNewFlag;
	}
	/**
	 * @param isNewFlag The isNewFlag to set.
	 */
	public void setNewFlag(boolean isNewFlag) {
		this.isNewFlag = isNewFlag;
	}
	/**
	 * @return Returns the oID.
	 */
	public String getOID() {
		return OID;
	}
	/**
	 * @param oid The oID to set.
	 */
	public void setOID(String oid) {
		OID = oid;
	}
	/**
	 * @return Returns the sID.
	 */
	public String getSID() {
		return SID;
	}
	/**
	 * @param sid The sID to set.
	 */
	public void setSID(String sid) {
		SID = sid;
	}
	/**
	 * @return Returns the sTP.
	 */
	public Timestamp getSTP() {
		return STP;
	}
	/**
	 * @param stp The sTP to set.
	 */
	public void setSTP(Timestamp stp) {
		STP = stp;
	}
	/**
	 * @return Returns the updateJIMS2UserID.
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}
	/**
	 * @param updateJIMS2UserID The updateJIMS2UserID to set.
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	/**
	 * @return Returns the updateTimestamp.
	 */
	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}
	/**
	 * @param updateTimestamp The updateTimestamp to set.
	 */
	public void setUpdateTimestamp(Timestamp updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
	/**
	 * @return Returns the updateUserID.
	 */
	public String getUpdateUserID() {
		return updateUserID;
	}
	/**
	 * @param updateUserID The updateUserID to set.
	 */
	public void setUpdateUserID(String updateUserID) {
		this.updateUserID = updateUserID;
	}
	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return Returns the workflowID.
	 */
	public String getWorkflowID() {
		return workflowID;
	}
	/**
	 * @param workflowID The workflowID to set.
	 */
	public void setWorkflowID(String workflowID) {
		this.workflowID = workflowID;
	}
}

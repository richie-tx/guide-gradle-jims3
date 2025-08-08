/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.manageworkgroup.reply;

import java.util.ArrayList;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import mojo.km.messaging.ResponseEvent;


/**
 * @author asrvastava
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WorkGroupResponseEvent extends ResponseEvent{
	private boolean hasOpenTasks;
    private String workgroupId;
	private String workgroupName;
	private String workgroupDescription;
	private String agencyId;
	private java.util.Collection users = new ArrayList();
	private java.util.Collection associatedTasks = new ArrayList();
	private String workgroupTypeId;
	private String workgroupTypeDesc;

    /**
     * @return Returns the agencyId.
     */
    public String getAgencyId() {
        return agencyId;
    }
    /**
     * @param agencyId The agencyId to set.
     */
    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
    /**
     * @return Returns the associatedTasks.
     */
    public java.util.Collection getAssociatedTasks() {
        return associatedTasks;
    }
    /**
     * @param associatedTasks The associatedTasks to set.
     */
    public void setAssociatedTasks(java.util.Collection associatedTasks) {
        this.associatedTasks = associatedTasks;
    }
    /**
     * @return Returns the description.
     */
    public String getWorkgroupDescription() {
        return workgroupDescription;
    }
    /**
     * @param description The description to set.
     */
    public void setWorkgroupDescription(String workgroupDescription) {
        this.workgroupDescription = workgroupDescription;
    }
    /**
     * @return Returns the name.
     */
    public String getWorkgroupName() {
        return workgroupName;
    }
    /**
     * @param name The name to set.
     */
    public void setWorkgroupName(String workgroupName) {
        this.workgroupName = workgroupName;
    }
    /**
     * @return Returns the typeId.
     */
    public String getWorkgroupTypeId() {
        return workgroupTypeId;
    }
    /**
     * @param typeId The typeId to set.
     */
    public void setWorkgroupTypeId(String workgroupTypeId) {
        this.workgroupTypeId = workgroupTypeId;
    }
    /**
     * @return Returns the users.
     */
    public java.util.Collection getUsers() {
        return users;
    }
//    /**
//     * @param users The users to set.
//     */
//    public void setUsers(java.util.Collection users) {
//        this.users = users;
//    }
    /**
     * @param users The users to set.
     */
    public void addUser(SecurityUserResponseEvent userRespEvent) {
        this.users.add(userRespEvent);
    }
    /**
     * @return Returns the workGroupId.
     */
    public String getWorkgroupId() {
        return workgroupId;
    }
    /**
     * @param workGroupId The workGroupId to set.
     */
    public void setWorkgroupId(String workgroupId) {
        this.workgroupId = workgroupId;
    }
	/**
	 * @return Returns the workgroupTypeDesc.
	 */
	public String getWorkgroupTypeDesc() {
		return workgroupTypeDesc;
		
	}
	/**
	 * @param workgroupTypeDesc The workgroupTypeDesc to set.
	 */
	public void setWorkgroupTypeDesc(String workgroupTypeDesc) {
		this.workgroupTypeDesc = workgroupTypeDesc;
	//	workgroupTypeDesc=SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.WORKGROUP_TYPE, workgroupTypeId);
	}
	public boolean isHasOpenTasks() {
		return hasOpenTasks;
	}
	public void setHasOpenTasks(boolean hasOpenTasks) {
		this.hasOpenTasks = hasOpenTasks;
	}
}

/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

import java.util.Iterator;
import java.util.List;

import messaging.manageworkgroup.reply.WorkGroupResponseEvent;

/**
 * @author cc_rbhat
 */
public class WorkgroupReport extends ProgramUnitReport {
	private List workgroups;

	private String selectedWorkgroupId;

	private String selectedWorkGroupName;


	/**
	 * @return Returns the selectedWorkgroupId.
	 */
	public String getSelectedWorkgroupId() {
		return selectedWorkgroupId;
	}
	/**
	 * @return Returns the selectedWorkGroupName.
	 */
	public String getSelectedWorkGroupName() {
		if (workgroups != null) {
			for (Iterator iterator = workgroups.iterator(); iterator.hasNext();) {
				WorkGroupResponseEvent workgroup = (WorkGroupResponseEvent) iterator.next();
				if (workgroup.getWorkgroupId().equals(this.selectedWorkgroupId)) {
					selectedWorkGroupName = workgroup.getWorkgroupName();
				}
			}
		}
		return selectedWorkGroupName;
	}
	/**
	 * @return Returns the workgroups.
	 */
	public List getWorkgroups() {
		return workgroups;
	}
	/**
	 * @param selectedWorkgroupId The selectedWorkgroupId to set.
	 */
	public void setSelectedWorkgroupId(String selectedWorkgroupId) {
		this.selectedWorkgroupId = selectedWorkgroupId;
	}
	/**
	 * @param selectedWorkGroupName The selectedWorkGroupName to set.
	 */
	public void setSelectedWorkGroupName(String selectedWorkGroupName) {
		this.selectedWorkGroupName = selectedWorkGroupName;
	}
	/**
	 * @param workgroups The workgroups to set.
	 */
	public void setWorkgroups(List workgroups) {
		this.workgroups = workgroups;
	}
}

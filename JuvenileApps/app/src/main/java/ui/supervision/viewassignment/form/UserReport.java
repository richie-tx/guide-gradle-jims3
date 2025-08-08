/*
 * Created on Feb 20, 2008
 */
package ui.supervision.viewassignment.form;

import java.util.List;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.utilities.MessageUtil;

/**
 * @author cc_rbhat
 */
public class UserReport extends ProgramUnitReport {
	/**
	 * The user id entered in the search criteria 
	 */
	private String userId;
	/**
	 * The staff name associated with the user id
	 */
	private String userName;
	/**
	 * Last name of officer entered in the search criteria
	 */
	private String officerLastName;
	/**
	 * First name of officer entered in the search criteria
	 */
	private String officerFirstName;
	/**
	 * List of officers matching the name search
	 */
	private List officersWithMatchingName;

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
		ev.setLogonId(this.getUserId());	
		ev.setOfficerNameNeeded(true);
		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
        if(staffResponseEvent != null) {
        	userName = staffResponseEvent.getOfficerName();
        	if (userName == null) {
        		userName = "No Name Available";
        	}
        } else {
        	userName = "No Name Available";
        }
		return userName;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @param userName
	 *            The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOfficerLastName() {
		return officerLastName;
	}

	public void setOfficerLastName(String officerLastName) {
		this.officerLastName = officerLastName;
	}

	public String getOfficerFirstName() {
		return officerFirstName;
	}

	public void setOfficerFirstName(String officerFirstName) {
		this.officerFirstName = officerFirstName;
	}

	public List getOfficersWithMatchingName() {
		return officersWithMatchingName;
	}

	public void setOfficersWithMatchingName(List officersWithMatchingName) {
		this.officersWithMatchingName = officersWithMatchingName;
	}
}

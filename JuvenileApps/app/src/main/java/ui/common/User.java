/*
 * Created on Mar 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.common;


import java.util.Date;

import messaging.contact.domintf.IName;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class User {
	private IName userName=new Name();
	private String agencyId;
	private String agencyName;
	private String email;
	private String userId="";
	private String cjad="";
	private String workgroupDesc="";
	
	
	/**
	 * @return Returns the userName.
	 */
	public IName getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(IName userName) {
		this.userName = userName;
	}
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
	 * @return Returns the agencyName.
	 */
	public String getAgencyName() {
		return agencyName;
	}
	/**
	 * @param agencyName The agencyName to set.
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return Returns the cjad.
	 */
	public String getCjad() {
		return cjad;
	}
	/**
	 * @param cjad The cjad to set.
	 */
	public void setCjad(String cjad) {
		this.cjad = cjad;
	}
	/**
	 * @return Returns the workgroupDesc.
	 */
	public String getWorkgroupDesc() {
		return workgroupDesc;
	}
	/**
	 * @param workgroupDesc The workgroupDesc to set.
	 */
	public void setWorkgroupDesc(String workgroupDesc) {
		this.workgroupDesc = workgroupDesc;
	}

}

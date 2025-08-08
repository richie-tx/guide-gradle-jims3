/*
 * Created on Sep 29, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SaveFamilyMemberContactEvent extends RequestEvent
{
	private String constellationMemberPhoneId;
	private String phoneId;
	private String phoneTypeId;
	private String phoneNum;
	private String extentionNum;
	private boolean isPhone=true;
	private String constellationMemberEmailId;
	private String familyEmailId;
	private String emailTypeId;
	private String emailAddress;
	private boolean primaryInd = false;
	private boolean primaryIndEmail = false;
	
	/**
	 * @return
	 */
	public String getConstellationMemberPhoneId()
	{
		return constellationMemberPhoneId;
	}

	/**
	 * @return
	 */
	public String getExtentionNum()
	{
		return extentionNum;
	}

	/**
	 * @return
	 */
	public String getPhoneId()
	{
		return phoneId;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @return
	 */
	public String getPhoneTypeId()
	{
		return phoneTypeId;
	}

	/**
	 * @param string
	 */
	public void setConstellationMemberPhoneId(String string)
	{
		constellationMemberPhoneId = string;
	}

	/**
	 * @param string
	 */
	public void setExtentionNum(String string)
	{
		extentionNum = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneId(String string)
	{
		phoneId = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneNum(String string)
	{
		phoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneTypeId(String string)
	{
		phoneTypeId = string;
	}

	/**
	 * @return Returns the constellationMemberEmailId.
	 */
	public String getConstellationMemberEmailId() {
		return constellationMemberEmailId;
	}
	/**
	 * @param constellationMemberEmailId The constellationMemberEmailId to set.
	 */
	public void setConstellationMemberEmailId(String constellationMemberEmailId) {
		this.constellationMemberEmailId = constellationMemberEmailId;
	}
	/**
	 * @return Returns the emailAddress.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress The emailAddress to set.
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return Returns the emailTypeId.
	 */
	public String getEmailTypeId() {
		return emailTypeId;
	}
	/**
	 * @param emailTypeId The emailTypeId to set.
	 */
	public void setEmailTypeId(String emailTypeId) {
		this.emailTypeId = emailTypeId;
	}
	/**
	 * @return Returns the familyEmailId.
	 */
	public String getFamilyEmailId() {
		return familyEmailId;
	}
	/**
	 * @param familyEmailId The familyEmailId to set.
	 */
	public void setFamilyEmailId(String familyEmailId) {
		this.familyEmailId = familyEmailId;
	}
	/**
	 * @return Returns the isPhone.
	 */
	public boolean isPhone() {
		return isPhone;
	}
	/**
	 * @param isPhone The isPhone to set.
	 */
	public void setPhone(boolean isPhone) {
		this.isPhone = isPhone;
	}

	/**
	 * @return the primaryInd
	 */
	public boolean isPrimaryInd() {
		return primaryInd;
	}

	/**
	 * @param primaryInd the primaryInd to set
	 */
	public void setPrimaryInd(boolean primaryInd) {
		this.primaryInd = primaryInd;
	}
	
	public boolean getPrimaryIndEmail() {
		return this.primaryIndEmail;
	}

	public void setPrimaryIndEmail(boolean primaryInd) {
		this.primaryIndEmail = primaryInd;
	}

}

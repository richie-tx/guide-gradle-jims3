/*
 * Created on Jun 8, 2004
 */
package messaging.contact.agency.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 */
public class AgencyContactResponseEvent extends ResponseEvent {
	private String agencyId;
	private String contactId;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String phone1;
	private String phone2;
	private String phone3;
	private String phoneExt;
	private String title;
	public String transaction;
	private String logonId;

	/**
	 * @return
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return
	 */
	public String getPhoneExt() {
		return phoneExt;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return
	 */
	public String getTransaction() {
		return transaction;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * @param contactId
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @param phoneExt
	 */
	public void setPhoneExt(String phoneExt) {
		this.phoneExt = phoneExt;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param transaction
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}
	/**
	 * @return
	 */
	public String getPhone1()
	{
		return phone1;
	}

	/**
	 * @return
	 */
	public String getPhone2()
	{
		return phone2;
	}

	/**
	 * @return
	 */
	public String getPhone3()
	{
		return phone3;
	}

	/**
	 * @param string
	 */
	public void setPhone1(String phone1)
	{
		this.phone1 = phone1;
	}

	/**
	 * @param string
	 */
	public void setPhone2(String phone2)
	{
		this.phone2 = phone2;
	}

	/**
	 * @param string
	 */
	public void setPhone3(String phone3)
	{
		this.phone3 = phone3;
	}

	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

}
/*
 * Created on May 24, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.schedulecalendarevent;

import messaging.contact.to.Address;
import messaging.contact.to.PhoneNumberBean;
import messaging.contact.to.SocialSecurityBean;

import ui.common.Name;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GuardianPrintBean {

	private Name name = new Name();
	private Address address = new Address();
	
	private String dateOfBirth = "";
	private SocialSecurityBean ssn = new SocialSecurityBean("");
	private PhoneNumberBean phoneNumber = new PhoneNumberBean();
		
	
	/**
	 * @return Returns the address.
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address The address to set.
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	/**
	 * @return Returns the dateOfBirth.
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth The dateOfBirth to set.
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return Returns the name.
	 */
	public Name getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(Name name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the phoneNumber.
	 */
	public PhoneNumberBean getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber The phoneNumber to set.
	 */
	public void setPhoneNumber(PhoneNumberBean phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return Returns the ssn.
	 */
	public SocialSecurityBean getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(SocialSecurityBean ssn) {
		this.ssn = ssn;
	}
}

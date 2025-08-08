/*
 * Created on Jun 7, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.caseplan.form;

import messaging.juvenile.reply.JuvenileContactResponseEvent;
import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;



/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UIJuvenileContact {
	private Name name;
	private Address address;
	private PhoneNumber workPhoneNumber;
	private PhoneNumber mobilePhoneNumber;
	private PhoneNumber faxPhoneNumber;
	
	public UIJuvenileContact() {
		name = new Name();
		address = new Address();
		workPhoneNumber = new PhoneNumber("");
		mobilePhoneNumber = new PhoneNumber("");
		faxPhoneNumber = new PhoneNumber("");
	}
	
	public UIJuvenileContact(JuvenileContactResponseEvent jcre)
	{
		name = new Name();
		address = new Address();
		
		name = new Name(jcre.getFirstName(), jcre.getMiddleName(), jcre.getLastName());
		address.setStreetNum(jcre.getStreetNum());
		address.setStreetName(jcre.getStreetName());
		address.setStreetType(jcre.getStreetType());
		address.setStreetTypeCode(jcre.getStreetTypeId());
		address.setStreetTypeId(jcre.getStreetTypeId());
		address.setAptNum(jcre.getApartmentNum());
		address.setAptNumber(jcre.getApartmentNum());
		address.setCity(jcre.getCity());
		address.setState(jcre.getState());
		address.setStateCode(jcre.getStateId());
		address.setStateId(jcre.getStateId());
		address.setZipCode(jcre.getZipCode());
		address.setAddressType(jcre.getAddressType());
		address.setAddressTypeCode(jcre.getAddressTypeId());
		address.setAddressTypeId(jcre.getAddressTypeId());
		address.setAdditionalZipCode(jcre.getAdditionalZipCode());
		address.setCounty(jcre.getCounty());
		address.setCountyCode(jcre.getCountyId());
		address.setCountryCode(jcre.getCounty());
		address.setCountyId(jcre.getCountyId());
		address.setStreetNumSuffixId(jcre.getStreetNumSuffixId());
		address.setStreetNumSuffixCode(jcre.getStreetNumSuffixId());
		address.setStreetNumSuffix(jcre.getStreetNumSuffix());
		
		setWorkPhoneNumber(jcre.getWorkPhone() != null 
				? new PhoneNumber(jcre.getWorkPhone()): new PhoneNumber(""));
		setMobilePhoneNumber(jcre.getCellPhone() != null 
				? new PhoneNumber(jcre.getCellPhone()): new PhoneNumber(""));
		setFaxPhoneNumber(jcre.getFax() != null 
				? new PhoneNumber(jcre.getFax()): new PhoneNumber(""));
	}
		
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
	 * @return Returns the faxPhoneNumber.
	 */
	public PhoneNumber getFaxPhoneNumber() {
		return faxPhoneNumber;
	}
	/**
	 * @param faxPhoneNumber The faxPhoneNumber to set.
	 */
	public void setFaxPhoneNumber(PhoneNumber faxPhoneNumber) {
		this.faxPhoneNumber = faxPhoneNumber;
	}
	/**
	 * @return Returns the mobilePhoneNumber.
	 */
	public PhoneNumber getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}
	/**
	 * @param mobilePhoneNumber The mobilePhoneNumber to set.
	 */
	public void setMobilePhoneNumber(PhoneNumber mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
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
	 * @return Returns the workPhoneNumber.
	 */
	public PhoneNumber getWorkPhoneNumber() {
		return workPhoneNumber;
	}
	/**
	 * @param workPhoneNumber The workPhoneNumber to set.
	 */
	public void setWorkPhoneNumber(PhoneNumber workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
}

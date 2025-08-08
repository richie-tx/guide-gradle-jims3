/*
 * Created on Sep 26, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.juvenilecase.casefile.form;

import java.util.Date;
import ui.common.Address;
import ui.common.Name;
import ui.common.PhoneNumber;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PersonInAndOutHomeBean {
	
	private Name nameOfPerson;
	private Date dateofBirth;
	private String relationshipType;
	private String relationshipTypeId;
	private String SSN;
	private String involvementLevel;
	private Address personAddress;
	private PhoneNumber personPhone;
	
	public PersonInAndOutHomeBean() {
		nameOfPerson = new Name();
		dateofBirth = new Date();
		relationshipType = "";
		relationshipTypeId = "";
		SSN = "";
		involvementLevel = "";	
		personAddress = new Address();
		personPhone = new PhoneNumber("");
	}	

	/**
	 * @return Returns the dateofBirth.
	 */
	public Date getDateofBirth() {
		return dateofBirth;
	}
	/**
	 * @param dateofBirth The dateofBirth to set.
	 */
	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	/**
	 * @return Returns the involvementLevel.
	 */
	public String getInvolvementLevel() {
		return involvementLevel;
	}
	/**
	 * @param involvementLevel The involvementLevel to set.
	 */
	public void setInvolvementLevel(String involvementLevel) {
		this.involvementLevel = involvementLevel;
	}
	/**
	 * @return Returns the nameOfPerson.
	 */
	public Name getNameOfPerson() {
		return nameOfPerson;
	}
	/**
	 * @param nameOfPerson The nameOfPerson to set.
	 */
	public void setNameOfPerson(Name nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}
	/**
	 * @return Returns the relationshipType.
	 */
	public String getRelationshipType() {
		return relationshipType;
	}
	/**
	 * @param relationshipType The relationshipType to set.
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	/**
	 * @return Returns the sSN.
	 */
	public String getSSN() {
		return SSN;
	}
	/**
	 * @param ssn The sSN to set.
	 */
	public void setSSN(String ssn) {
		SSN = ssn;
	}
	/**
	 * @return Returns the personAddress.
	 */
	public Address getPersonAddress() {
		return personAddress;
	}
	/**
	 * @param personAddress The personAddress to set.
	 */
	public void setPersonAddress(Address personAddress) {
		this.personAddress = personAddress;
	}

	/**
	 * @return Returns the personPhone.
	 */
	public PhoneNumber getPersonPhone() {
		return personPhone;
	}
	/**
	 * @param personPhone The personPhone to set.
	 */
	public void setPersonPhone(PhoneNumber personPhone) {
		this.personPhone = personPhone;
	}

	/**
	 * @return the relationshipTypeId
	 */
	public String getRelationshipTypeId() {
		return relationshipTypeId;
	}

	/**
	 * @param relationshipTypeId the relationshipTypeId to set
	 */
	public void setRelationshipTypeId(String relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}
}

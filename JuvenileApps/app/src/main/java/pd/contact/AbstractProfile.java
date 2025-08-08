
// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.

package pd.contact;

import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.user.UserProfile;

/**
* 
*/
public abstract class AbstractProfile extends PersistentObject {
	/*private String personId;
	private String contactId;
	*//**
	 * Properties for person
	 * @referencedType pd.contact.Person
	 * @detailerDoNotGenerate true
	 *//*
	private Person person;
	*//**
	 * Properties for contact
	 * @referencedType Contact
	 * @detailerDoNotGenerate true
	 *//*
	private Contact contact;

	*//**
	 *
	 *//*
	public void bind() {
		markModified();
	}

	*//**
	 * @return String
	 *//*
	public String getPersonId() {
		fetch();
		return personId;
	}

	*//**
	 * @return String
	 *//*
	public String getContactId() {
		fetch();
		return contactId;
	}

	*//**
	 * @return Person
	 *//*
	private Person getPerson() {
		fetch();
		initPerson();
		return person;
	}

	*//**
	 * @return Contact
	 *//*
	private Contact getContact() {
		fetch();
		initContact();
		return contact;
	}

	*//**
	 * @param personId 
	 *//*
	public void setPersonId(String personId) {
		if (this.personId == null || !this.personId.equals(personId))
		{
			markModified();
		}
		this.personId = personId;
	}

	*//**
	 * @param contactId 
	 *//*
	public void setContactId(String contactId) {
		if (this.contactId == null || !this.contactId.equals(personId))
		{
			markModified();
		}
		this.contactId = contactId;
	}

	*//**
	 * set the type reference for class member person
	 * @param aPerson
	 *//*
	public void setPerson(Person aPerson) {
		if (this.person == null || !this.person.equals(aPerson)) {
			markModified();
		}
		setPersonId("" + aPerson.getOID());
		this.person = (pd.contact.Person) new mojo.km.persistence.Reference((PersistentObject) aPerson).getObject();
	}
	
	*//**
	 * set the type reference for class member contact
	 * @param aContact
	 *//*
	public void setContact(Contact aContact) {
		if (this.person == null || !this.person.equals(aContact)) {
			markModified();
		}
		setPersonId("" + aContact.getOID());
		this.contact = (pd.contact.Contact) new mojo.km.persistence.Reference((PersistentObject) aContact).getObject();
	}
	
	*//**
	 * Initialize class relationship to class pd.contact.Person
	 *//*
	private void initPerson() {
		if (person == null) {
			try {
				person = (pd.contact.Person) new mojo.km.persistence.Reference(personId, pd.contact.Person.class).getObject();
			} catch (Throwable t) {
				person = null;
			}
		}
	}
	
	*//**
	 * Initialize class relationship to class pd.contact.Contact
	 *//*
	private void initContact() {
		if (person == null) {
			try {
				contact = (pd.contact.Contact) new mojo.km.persistence.Reference(contactId, pd.contact.Contact.class).getObject();
			} catch (Throwable t) {
				contact = null;
			}
		}
	}
	
	*//**
	 * Finds all User profiles by an attribute value
	 * @param attributeName
	 * @param attributeValue
	 * @return 
	 *//*
	static public Iterator findAllUserProfiles(String attributeName, String attributeValue) {
		IHome home = new Home();
		Iterator profiles = home.findAll(attributeName, attributeValue, UserProfile.class);
		return profiles;
	}
	
	public boolean isUserProfile()
	{
//		if (this instanceof UserProfile)
//		{
//			return true;
//		}
		return false;
	}

	// Delegate methods for the Person and Contact entities
	*//**
	 * @return the current value of the cellPhone
	 *//*
	public java.lang.String getCellPhone()
	{
		return getContact().getCellPhone();
	}
	*//**
	 * @param aCellPhone the new value of the cellPhone
	 *//*
	public void setCellPhone(java.lang.String aCellPhone)
	{
		getContact().setCellPhone(aCellPhone);
	}
	*//**
	 * Delegate method for the Contact email property.
	 * @return the current value of the email 
	 *//*
	public java.lang.String getEmail()
	{
		return getContact().getEmail();
	}
	*//**
	 * @param aEmail the new value of the email
	 *//*
	public void setEmail(java.lang.String aEmail)
	{
		getContact().setEmail(aEmail);
	}
	*//**
	 * @return the current value of the faxLocation
	 *//*
	public java.lang.String getFaxLocation()
	{
		return getContact().getFaxLocation();
	}
	*//**
	 * Delegate method for the Contact faxLocation property.
	 * @param aFaxLocation the new value of the faxLocation property
	 *//*
	public void setFaxLocation(java.lang.String aFaxLocation)
	{
		getContact().setFaxLocation(aFaxLocation);
	}
	*//**
	 * Delegate method for the Contact faxNum property.
	 * @return the current value of the faxNum property
	 *//*
	public java.lang.String getFaxNum()
	{
		return getContact().getFaxNum();
	}
	*//**
	 * Delegate method for setting the Contact faxNum property.
	 * @param aFaxNum the new value of the faxNum property
	 *//*
	public void setFaxNum(java.lang.String aFaxNum)
	{
		getContact().setFaxNum(aFaxNum);
	}
	*//**
	 * Delegate method for the Person firstName property.
	 * @return the current value of the firstName property
	 *//*
	public java.lang.String getFirstName()
	{
		return getPerson().getFirstName();
	}
	*//**
	 * Delegate method for setting the Person firstName property.
	 * @param aFirstName the new value of the firstName property
	 *//*
	public void setFirstName(java.lang.String aFirstName)
	{
		getPerson().setFirstName(aFirstName);
	}

	*//**
	 * @return Contact homePhoneNum property
	 *//*
	public java.lang.String getHomePhoneNum()
	{
		return getContact().getHomePhoneNum();
	}
	
	*//**
	 * Delegate method for setting the Contact homePhoneNum property.
	 * @param aHomePhoneNum the new value of the homePhoneNum property
	 *//*
	public void setHomePhoneNum(java.lang.String aHomePhoneNum)
	{
		getContact().setHomePhoneNum(aHomePhoneNum);
	}
	*//**
	 * Delegate method for the Person lastName property.
	 * @return the current value of the lastName property
	 *//*
	public java.lang.String getLastName()
	{
		return getPerson().getLastName();
	}
	*//**
	 * Delegate method for setting the Person lastName property.
	 * @param aLastName the new value of the lastName property
	 *//*
	public void setLastName(java.lang.String aLastName)
	{
		getPerson().setLastName(aLastName);
	}
	*//**
	 * Delegate method for the Person middleName property.
	 * @return the current value of the middleName property
	 *//*
	public java.lang.String getMiddleName()
	{
		return getPerson().getMiddleName();
	}
	*//**
	 * Delegate method for setting the Contact middleName property.
	 * @param aMiddleName the new value of the middleName property
	 *//*
	public void setMiddleName(java.lang.String aMiddleName)
	{
		getPerson().setMiddleName(aMiddleName);
	}
	*//**
	 * Delegate method for the Contact pager property.
	 * @return the current value of the pager property
	 *//*
	public java.lang.String getPager()
	{
		return getContact().getPager();
	}
	*//**
	 * Delegate method for setting the Contact pager property.
	 * @param aPager the new value of the pager property
	 *//*
	public void setPager(java.lang.String aPager)
	{
		getContact().setPager(aPager);
	}
	*//**
	 * Delegate method for setting the Contact phoneExt property.
	 * @return the current value of the phoneExt property
	 *//*
	public java.lang.String getPhoneExt()
	{
		return getContact().getPhoneExt();
	}
	*//**
	 * Delegate method for setting the Contact phoneExt property.
	 * @param aPhoneExt the new value of the phoneExt property
	 *//*
	public void setPhoneExt(java.lang.String aPhoneExt)
	{
		getContact().setPhoneExt(aPhoneExt);
	}
	*//**
	 * Delegate method for the Contact phoneNum property.
	 * @return the current value of the phoneNum property
	 *//*
	public java.lang.String getPhoneNum()
	{
		return getContact().getPhoneNum();
	}
	*//**
	 * Delegate method for setting the Contact phoneNum property.
	 * @param aPhoneNum the new value of the phoneNum property
	 *//*
	public void setPhoneNum(java.lang.String aPhoneNum)
	{
		getContact().setPhoneNum(aPhoneNum);
	}
	*//**
	 * Delegate method for the Person title property.
	 * @return the current value of the title property
	 *//*
	public java.lang.String getTitle()
	{
		return getPerson().getTitle();
	}
	*//**
	 * Delegate method for the Contact title property.
	 * @param aTitle the new value of the title property
	 *//*
	public void setTitle(java.lang.String aTitle)
	{
		getPerson().setTitle(aTitle);
	}
	*//**
	 * @return the current value of the workPhoneNum property
	 *//*
	public java.lang.String getWorkPhoneNum()
	{
		return getContact().getWorkPhoneNum();
	}
	*//**
	 * @param aWorkPhoneNum the new value of the workPhoneNum property
	 *//*
	public void setWorkPhoneNum(java.lang.String aWorkPhoneNum)
	{
		getContact().setWorkPhoneNum(aWorkPhoneNum);
	}
*/
	
}

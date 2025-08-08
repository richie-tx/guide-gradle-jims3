package pd.juvenile;

import java.util.Date;
import java.util.Iterator;

import messaging.juvenile.SaveJuvenileContactEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
 * @author asrvastava This entity stores Contact information for Juveniles. It has
 *         many-to-many relationship with the Juvenile.
 * DPA: Added sorting by createdate to find out the latest contacts.
 */
public class JuvenileContact extends PersistentObject implements Comparable
{
    private String juvenileId;

    private String phonePriorityInd;
    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey CONTACT_RELATIONSHIP
     * @detailerDoNotGenerate false
     */
    private Code relationship = null;

    private String firstName;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey ADDRESS_TYPE
     * @detailerDoNotGenerate false
     */
    public Code addressType = null;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STATE_ABBR
     * @detailerDoNotGenerate false
     */
    private Code state = null;

    private String apartmentNum;

    private String previousAgencyInvolvmentId;
    private Code previousAgencyInvolvment = null;

    private Date entryDate;

    private String cellPhone;

    private String zipCode;

    private String streetName;

    private String workPhone;
    private String workPhoneExtn;

    private String currentAgencyInvolvmentId;
    private Code currentAgencyInvolvment = null;

    private String stateId;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey JUVENILE_AGENCY
     * @detailerDoNotGenerate false
     */
    private Code agencyName = null;

    private String addressTypeId;

    private String lastName;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STREET_TYPE
     * @detailerDoNotGenerate false
     */
    private Code streetType = null;

    /**
     * Properties for streetNumSuffix
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STREET_NUM_SUFFIX
     * @detailerDoNotGenerate false
     */
    private Code streetNumSuffix = null;

    private String titleId;

    private String streetTypeId;
    
    private String streetNumSuffixId;

    private String city;

    /**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey COUNTY
     * @detailerDoNotGenerate false
     */
    private Code county = null;

    private String relationshipId;

    private String middleName;

    private String eMail;

    private String fax;

    private String countyId;

    private String agencyNameId;

    private String additionalZipCode;
    
    private String contactMemberComments;

    /**
     * Properties for prefix
     * 
     * @referencedType pd.codetable.Code
     * @contextKey PREFIX
     * @detailerDoNotGenerate false
     */
    private Code title = null;

    private String streetNum;
    private String validated;
    
    //the following are added for Detention Visit US 43115, Task 45932
    private boolean detentionVisit;
    private boolean ageOver21;
    private String driverLicenseNum;
    private String driverLicenseStateId;
    private String driverLicenseClassId;
    private String driverLicenseExpirationDate;
    private String issuedByStateId;
    private String stateIssuedIdNum;
    private String passportNum; 
    private String countryOfIssuanceId; 
    private String passportExpirationDate; 
    private Code driverLicenseState = null;
    private Code driverLicenseClass = null;
    private Code issuedByState = null;
    private Code countryOfIssuance = null;

    /**
     * @roseuid 42A5E0E70203
     */
    public JuvenileContact()
    {
    }

    /**
     * @return JuvenileContact
     * @param contact
     */
    static public JuvenileContact find(String contactId)
    {
        IHome home = new Home();
        JuvenileContact contact = (JuvenileContact) home.find(contactId, JuvenileContact.class);
        return contact;
    }

    /**
     * Finds juvenile contacts by an event
     * 
     * @return Iterator of contacts
     * @param event
     */
    static public Iterator findAll(IEvent event)
    {
        IHome home = new Home();
        Iterator contacts = home.findAll(event, JuvenileContact.class);
        return contacts;
    }

    /**
     * @return Iterator contacts
     * @param attrName
     *            name fo the attribute for where clause
     * @param attrValue
     *            value to be checked in the where clause
     */
    static public Iterator findAll(String attrName, String attrValue)
    {
        IHome home = new Home();
        Iterator contacts = home.findAll(attrName, attrValue, JuvenileContact.class);
        return contacts;
    }

    static public JuvenileContact create(SaveJuvenileContactEvent saveEvent)
    {
        JuvenileContact contact = new JuvenileContact();
        contact.setPhonePriorityInd(saveEvent.getPhonePriorityInd());
        contact.setJuvenileId(saveEvent.getJuvenileNum());
        contact.setAddressTypeId(saveEvent.getAddressType());
        contact.setAgencyNameId(saveEvent.getAgencyName());
        contact.setApartmentNum(saveEvent.getApartmentNum());
        contact.setCellPhone(saveEvent.getCellPhone());
        contact.setCity(saveEvent.getCity());
        contact.setCountyId(saveEvent.getCounty());
        contact.setCurrentAgencyInvolvmentId(saveEvent.getCurrentAgencyInvolvementId());
        contact.setEMail(saveEvent.getEMail());
        contact.setEntryDate(saveEvent.getEntryDate());
        contact.setFax(saveEvent.getFax());
        contact.setFirstName(saveEvent.getFirstName());
        contact.setLastName(saveEvent.getLastName());
        contact.setMiddleName(saveEvent.getMiddleName());
        contact.setPreviousAgencyInvolvmentId(saveEvent.getPreviousAgencyInvolvementId());
        contact.setRelationshipId(saveEvent.getRelationshipId());
        contact.setStateId(saveEvent.getState());
        contact.setStreetName(saveEvent.getStreetName());
        contact.setStreetNum(saveEvent.getStreetNum());
        contact.setStreetTypeId(saveEvent.getStreetType());
        contact.setTitleId(saveEvent.getPrefixId());
        contact.setWorkPhone(saveEvent.getWorkPhone());
        contact.setWorkPhoneExtn(saveEvent.getWorkPhoneExtn());
        contact.setZipCode(saveEvent.getZipCode());
        contact.setAdditionalZipCode(saveEvent.getAdditionalZipCode());
        contact.setStreetNumSuffixId(saveEvent.getStreetNumSuffix());
        contact.setValidated(saveEvent.getValidated());
        contact.setDetentionVisit(saveEvent.isDetentionVisit());
        contact.setAgeOver21(saveEvent.isAgeOver21());
        contact.setDriverLicenseNum(saveEvent.getDriverLicenseNum());
        contact.setDriverLicenseStateId(saveEvent.getDriverLicenseStateId());
        contact.setDriverLicenseClassId(saveEvent.getDriverLicenseClassId());
        if(saveEvent.getDriverLicenseExpirationDate().equals("")){
            contact.setDriverLicenseExpirationDate(null);
        }else {
            String expiryDate = saveEvent.getDriverLicenseExpirationDate();
            expiryDate = expiryDate.substring(0, 10); 
            contact.setDriverLicenseExpirationDate(expiryDate);
        }
        contact.setIssuedByStateId(saveEvent.getIssuedByStateId());
        contact.setStateIssuedIdNum(saveEvent.getStateIssuedIdNum());
        contact.setPassportNum(saveEvent.getPassportNum());
        contact.setCountryOfIssuanceId(saveEvent.getCountryOfIssuanceId());
        if (saveEvent.getPassportExpirationDate().equals("")){
        contact.setPassportExpirationDate(null);
        }
        else {
            String expiryDate = saveEvent.getPassportExpirationDate();
            expiryDate = expiryDate.substring(0, 10); 
            contact.setPassportExpirationDate(saveEvent.getPassportExpirationDate());
        }
        if (saveEvent.getValidated().equals("")) {
            contact.setValidated("U");
	}

        contact.setContactMemberComments(saveEvent.getContactMemberComments());
        return contact;
    }
    
    static public JuvenileContact update(SaveJuvenileContactEvent saveEvent, JuvenileContact contact)
    {
    	contact.setPhonePriorityInd(saveEvent.getPhonePriorityInd());
        contact.setJuvenileId(saveEvent.getJuvenileNum());
        contact.setAddressTypeId(saveEvent.getAddressType());
        contact.setAgencyNameId(saveEvent.getAgencyName());
        contact.setApartmentNum(saveEvent.getApartmentNum());
        contact.setCellPhone(saveEvent.getCellPhone());
        contact.setCity(saveEvent.getCity());
        contact.setCountyId(saveEvent.getCounty());
        contact.setCurrentAgencyInvolvmentId(saveEvent.getCurrentAgencyInvolvementId());
        contact.setEMail(saveEvent.getEMail());
        contact.setEntryDate(saveEvent.getEntryDate());
        contact.setFax(saveEvent.getFax());
        contact.setPreviousAgencyInvolvmentId(saveEvent.getPreviousAgencyInvolvementId());
        contact.setRelationshipId(saveEvent.getRelationshipId());
        contact.setStateId(saveEvent.getState());
        contact.setStreetName(saveEvent.getStreetName());
        contact.setStreetNum(saveEvent.getStreetNum());
        contact.setStreetTypeId(saveEvent.getStreetType());
        contact.setTitleId(saveEvent.getPrefixId());
        contact.setWorkPhone(saveEvent.getWorkPhone());
        contact.setWorkPhoneExtn(saveEvent.getWorkPhoneExtn());
        contact.setZipCode(saveEvent.getZipCode());
        contact.setAdditionalZipCode(saveEvent.getAdditionalZipCode());
        contact.setStreetNumSuffixId(saveEvent.getStreetNumSuffix());
        contact.setValidated(saveEvent.getValidated());
        contact.setDetentionVisit(saveEvent.isDetentionVisit());
        contact.setAgeOver21(saveEvent.isAgeOver21());
        contact.setDriverLicenseNum(saveEvent.getDriverLicenseNum());
        contact.setDriverLicenseStateId(saveEvent.getDriverLicenseStateId());
        contact.setDriverLicenseClassId(saveEvent.getDriverLicenseClassId());
        if(saveEvent.getDriverLicenseExpirationDate().equals("")){
            contact.setDriverLicenseExpirationDate(null);
        }else
        {
            String expiryDate = saveEvent.getDriverLicenseExpirationDate();
            expiryDate = expiryDate.substring(0, 10); 
            contact.setDriverLicenseExpirationDate(expiryDate);
        }
        contact.setIssuedByStateId(saveEvent.getIssuedByStateId());
        contact.setStateIssuedIdNum(saveEvent.getStateIssuedIdNum());
        contact.setPassportNum(saveEvent.getPassportNum());
        contact.setCountryOfIssuanceId(saveEvent.getCountryOfIssuanceId());
        if (saveEvent.getPassportExpirationDate().equals("")){
            contact.setPassportExpirationDate(null);
            }
        else {
            String expiryDate = saveEvent.getPassportExpirationDate();
            expiryDate = expiryDate.substring(0, 10); 
            contact.setPassportExpirationDate(saveEvent.getPassportExpirationDate());
        }
        if (saveEvent.getValidated().equals("")) {
            contact.setValidated("U");
	}
        contact.setContactMemberComments(saveEvent.getContactMemberComments());
        return contact;
    }

    /**
     * Access method for the firstName property.
     * 
     * @return the current value of the firstName property
     */
    public String getFirstName()
    {
        fetch();
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param aFirstName
     *            the new value of the firstName property
     */
    public void setFirstName(String aFirstName)
    {
        if (this.firstName == null || !this.firstName.equals(aFirstName))
        {
            markModified();
        }
        firstName = aFirstName;
    }

    /**
     * Access method for the cellPhone property.
     * 
     * @return the current value of the cellPhone property
     */
    public String getCellPhone()
    {
        fetch();
        return cellPhone;
    }

    /**
     * Sets the value of the cellPhone property.
     * 
     * @param aCellPhone
     *            the new value of the cellPhone property
     */
    public void setCellPhone(String aCellPhone)
    {
        if (this.cellPhone == null || !this.cellPhone.equals(aCellPhone))
        {
            markModified();
        }
        cellPhone = aCellPhone;
    }

    /**
     * Access method for the entryDate property.
     * 
     * @return the current value of the entryDate property
     */
    public Date getEntryDate()
    {
        fetch();
        return entryDate;
    }

    /**
     * Sets the value of the entryDate property.
     * 
     * @param aEntryDate
     *            the new value of the entryDate property
     */
    public void setEntryDate(Date aEntryDate)
    {
        if (this.entryDate == null || !this.entryDate.equals(aEntryDate))
        {
            markModified();
        }
        entryDate = aEntryDate;
    }

    /**
     * Access method for the lastName property.
     * 
     * @return the current value of the lastName property
     */
    public String getLastName()
    {
        fetch();
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param aLastName
     *            the new value of the lastName property
     */
    public void setLastName(String aLastName)
    {
        if (this.lastName == null || !this.lastName.equals(aLastName))
        {
            markModified();
        }
        lastName = aLastName;
    }

    /**
     * Determines if the currentAgencyInvolvment property is true.
     * 
     * @return <code>true<code> if the currentAgencyInvolvment property is true
     */
    public String getCurrentAgencyInvolvmentId()
    {
        fetch();
        return currentAgencyInvolvmentId;
    }

    /**
     * Sets the value of the currentAgencyInvolvment property.
     * 
     * @param aCurrentAgencyInvolvment
     *            the new value of the currentAgencyInvolvment property
     */
    public void setCurrentAgencyInvolvmentId(String aCurrentAgencyInvolvmentId)
    {
    if (this.currentAgencyInvolvmentId==null || !this.currentAgencyInvolvmentId.equals(aCurrentAgencyInvolvmentId))
        {
            markModified();
        }
        currentAgencyInvolvment=null;
        currentAgencyInvolvmentId = aCurrentAgencyInvolvmentId;
    }

    /**
     * Determines if the previousAgencyInvolvment property is true.
     * 
     * @return <code>true<code> if the previousAgencyInvolvment property is true
     */
    public String getPreviousAgencyInvolvmentId()
    {
        fetch();
        return previousAgencyInvolvmentId;
    }

    /**
     * Sets the value of the previousAgencyInvolvment property.
     * 
     * @param aPreviousAgencyInvolvment
     *            the new value of the previousAgencyInvolvment property
     */
    public void setPreviousAgencyInvolvmentId(String aPreviousAgencyInvolvmentId)
    {
        if (this.previousAgencyInvolvmentId==null || !this.previousAgencyInvolvmentId.equals(aPreviousAgencyInvolvmentId))
        {
            markModified();
        }
        previousAgencyInvolvment=null;
        previousAgencyInvolvmentId = aPreviousAgencyInvolvmentId;
    }

    /**
     * Access method for the middleName property.
     * 
     * @return the current value of the middleName property
     */
    public String getMiddleName()
    {
        fetch();
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param aMiddleName
     *            the new value of the middleName property
     */
    public void setMiddleName(String aMiddleName)
    {
        if (this.middleName == null || !this.middleName.equals(aMiddleName))
        {
            markModified();
        }
        middleName = aMiddleName;
    }

    /**
     * Access method for the streetNum property.
     * 
     * @return the current value of the streetNum property
     */
    public String getStreetNum()
    {
        fetch();
        return streetNum;
    }

    /**
     * Sets the value of the streetNum property.
     * 
     * @param aStreetNum
     *            the new value of the streetNum property
     */
    public void setStreetNum(String aStreetNum)
    {
        if (this.streetNum == null || !this.streetNum.equals(aStreetNum))
        {
            markModified();
        }
        streetNum = aStreetNum;
    }

    /**
     * Access method for the streetName property.
     * 
     * @return the current value of the streetName property
     */
    public String getStreetName()
    {
        fetch();
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param aStreetName
     *            the new value of the streetName property
     */
    public void setStreetName(String aStreetName)
    {
        if (this.streetName == null || !this.streetName.equals(aStreetName))
        {
            markModified();
        }
        streetName = aStreetName;
    }

    /**
     * Access method for the zipCode property.
     * 
     * @return the current value of the zipCode property
     */
    public String getZipCode()
    {
        fetch();
        return zipCode;
    }

    /**
     * Sets the value of the zipCode property.
     * 
     * @param aZipCode
     *            the new value of the zipCode property
     */
    public void setZipCode(String aZipCode)
    {
        if (this.zipCode == null || !this.zipCode.equals(aZipCode))
        {
            markModified();
        }
        zipCode = aZipCode;
    }

    /**
     * Access method for the eMail property.
     * 
     * @return the current value of the eMail property
     */
    public String getEMail()
    {
        fetch();
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param aEMail
     *            the new value of the eMail property
     */
    public void setEMail(String aEMail)
    {
        if (this.eMail == null || !this.eMail.equals(aEMail))
        {
            markModified();
        }
        eMail = aEMail;
    }

    /**
     * Access method for the apartmentNum property.
     * 
     * @return the current value of the apartmentNum property
     */
    public String getApartmentNum()
    {
        fetch();
        return apartmentNum;
    }

    /**
     * Sets the value of the apartmentNum property.
     * 
     * @param aApartmentNum
     *            the new value of the apartmentNum property
     */
    public void setApartmentNum(String aApartmentNum)
    {
        if (this.apartmentNum == null || !this.apartmentNum.equals(aApartmentNum))
        {
            markModified();
        }
        apartmentNum = aApartmentNum;
    }

    /**
     * Access method for the workPhone property.
     * 
     * @return the current value of the workPhone property
     */
    public String getWorkPhone()
    {
        fetch();
        return workPhone;
    }

    /**
     * Sets the value of the workPhone property.
     * 
     * @param aWorkPhone
     *            the new value of the workPhone property
     */
    public void setWorkPhone(String aWorkPhone)
    {
        if (this.workPhone == null || !this.workPhone.equals(aWorkPhone))
        {
            markModified();
        }
        workPhone = aWorkPhone;
    }

    /**
     * Access method for the fax property.
     * 
     * @return the current value of the fax property
     */
    public String getFax()
    {
        fetch();
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param aFax
     *            the new value of the fax property
     */
    public void setFax(String aFax)
    {
        if (this.fax == null || !this.fax.equals(aFax))
        {
            markModified();
        }
        fax = aFax;
    }

    /**
     * @roseuid 42A5DD8F0290
     */
    public void find()
    {
        fetch();
    }

    /**
     * @roseuid 42A5DD8F0291
     */
    public void bind()
    {
        markModified();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setRelationshipId(String relationshipId)
    {
        if (this.relationshipId == null || !this.relationshipId.equals(relationshipId))
        {
            markModified();
        }
        relationship = null;
        this.relationshipId = relationshipId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getRelationshipId()
    {
        fetch();
        return relationshipId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initRelationship()
    {
        if (relationship == null)
        {
            try
            {
                relationship = (Code) new mojo.km.persistence.Reference(relationshipId, Code.class,
                        "CONTACT_RELATIONSHIP").getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getRelationship()
    {
        fetch();
        initRelationship();
        return relationship;
    }

    /**
     * set the type reference for class member relationship
     */
    public void setRelationship(Code relationship)
    {
        if (this.relationship == null || !this.relationship.equals(relationship))
        {
            markModified();
        }
        if (relationship.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(relationship);
        }
        setRelationshipId("" + relationship.getOID());
        relationship.setContext("CONTACT_RELATIONSHIP");
        this.relationship = (Code) new mojo.km.persistence.Reference(relationship).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setAddressTypeId(String addressTypeId)
    {
        if (this.addressTypeId == null || !this.addressTypeId.equals(addressTypeId))
        {
            markModified();
        }
        addressType = null;
        this.addressTypeId = addressTypeId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getAddressTypeId()
    {
        fetch();
        return addressTypeId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAddressType()
    {
        if (addressType == null)
        {
            try
            {
                addressType = (Code) new mojo.km.persistence.Reference(addressTypeId, Code.class,
                	PDCodeTableConstants.ADDRESS_TYPE).getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getAddressType()
    {
        fetch();
        initAddressType();
        return addressType;
    }

    /**
     * set the type reference for class member addressType
     */
    public void setAddressType(Code addressType)
    {
        if (this.addressType == null || !this.addressType.equals(addressType))
        {
            markModified();
        }
        if (addressType.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(addressType);
        }
        setAddressTypeId("" + addressType.getOID());
        addressType.setContext(PDCodeTableConstants.ADDRESS_TYPE);
        this.addressType = (Code) new mojo.km.persistence.Reference(addressType).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStateId(String stateId)
    {
        if (this.stateId == null || !this.stateId.equals(stateId))
        {
            markModified();
        }
        state = null;
        this.stateId = stateId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getStateId()
    {
        fetch();
        return stateId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initState()
    {
        if (state == null)
        {
            try
            {
                state = (Code) new mojo.km.persistence.Reference(stateId, Code.class, PDCodeTableConstants.STATE_ABBR)
                        .getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getState()
    {
        fetch();
        initState();
        return state;
    }

    /**
     * set the type reference for class member state
     */
    public void setState(Code state)
    {
        if (this.state == null || !this.state.equals(state))
        {
            markModified();
        }
        if (state.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(state);
        }
        setStateId("" + state.getOID());
        state.setContext(PDCodeTableConstants.STATE_ABBR);
        this.state = (Code) new mojo.km.persistence.Reference(state).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setAgencyNameId(String agencyNameId)
    {
        if (this.agencyNameId == null || !this.agencyNameId.equals(agencyNameId))
        {
            markModified();
        }
        agencyName = null;
        this.agencyNameId = agencyNameId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getAgencyNameId()
    {
        fetch();
        return agencyNameId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAgencyName()
    {
        if (agencyName == null)
        {
            try
            {
                agencyName = (Code) new mojo.km.persistence.Reference(agencyNameId, Code.class,
                        "JUVENILE_AGENCY").getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }
    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getPreviousAgencyInvolvment()
    {
        fetch();
        initPreviousAgencyInvolvment();
        return previousAgencyInvolvment;
    }

    /**
     * set the type reference for class member agencyName
     */
    public void setPreviousAgencyInvolvment(Code previousAgencyInvolvment)
    {
        if (this.previousAgencyInvolvment == null || !this.previousAgencyInvolvment.equals(previousAgencyInvolvment))
        {
            markModified();
        }
        if (previousAgencyInvolvment.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(previousAgencyInvolvment);
        }
        setPreviousAgencyInvolvmentId("" + previousAgencyInvolvment.getOID());
        previousAgencyInvolvment.setContext("JUVENILE_AGENCY");
        this.previousAgencyInvolvment = (Code) new mojo.km.persistence.Reference(previousAgencyInvolvment).getObject();
    }
    
    private void initPreviousAgencyInvolvment()
    {
        if (previousAgencyInvolvment == null)
        {
            try
            {
            	previousAgencyInvolvment = (Code) new mojo.km.persistence.Reference(previousAgencyInvolvmentId, Code.class,
                        "JUVENILE_AGENCY").getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }
    
    public Code getCurrentAgencyInvolvment()
    {
        fetch();
        initCurrentAgencyInvolvment();
        return currentAgencyInvolvment;
    }

    /**
     * set the type reference for class member agencyName
     */
    public void setCurrentAgencyInvolvment(Code currentAgencyInvolvment)
    {
        if (this.currentAgencyInvolvment == null || !this.currentAgencyInvolvment.equals(currentAgencyInvolvment))
        {
            markModified();
        }
        if (currentAgencyInvolvment.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(currentAgencyInvolvment);
        }
        setCurrentAgencyInvolvmentId("" + currentAgencyInvolvment.getOID());
        currentAgencyInvolvment.setContext("JUVENILE_AGENCY");
        this.currentAgencyInvolvment = (Code) new mojo.km.persistence.Reference(currentAgencyInvolvment).getObject();
    }
    
    private void initCurrentAgencyInvolvment()
    {
        if (currentAgencyInvolvment == null)
        {
            try
            {
            	currentAgencyInvolvment = (Code) new mojo.km.persistence.Reference(currentAgencyInvolvmentId, Code.class,
                        "JUVENILE_AGENCY").getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getAgencyName()
    {
        fetch();
        initAgencyName();
        return agencyName;
    }

    /**
     * set the type reference for class member agencyName
     */
    public void setAgencyName(Code agencyName)
    {
        if (this.agencyName == null || !this.agencyName.equals(agencyName))
        {
            markModified();
        }
        if (agencyName.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(agencyName);
        }
        setAgencyNameId("" + agencyName.getOID());
        agencyName.setContext("JUVENILE_AGENCY");
        this.agencyName = (Code) new mojo.km.persistence.Reference(agencyName).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStreetTypeId(String streetTypeId)
    {
        if (this.streetTypeId == null || !this.streetTypeId.equals(streetTypeId))
        {
            markModified();
        }
        streetType = null;
        this.streetTypeId = streetTypeId;
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStreetNumSuffixId(String streetNumSuffixId)
    {
        if (this.streetNumSuffixId == null || !this.streetNumSuffixId.equals(streetNumSuffixId))
        {
            markModified();
        }
        streetNumSuffix = null;
        this.streetNumSuffixId = streetNumSuffixId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getStreetTypeId()
    {
        fetch();
        return streetTypeId;
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getStreetNumSuffixId()
    {
        fetch();
        return streetNumSuffixId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStreetType()
    {
        if (streetType == null)
        {
            try
            {
                streetType = (Code) new mojo.km.persistence.Reference(streetTypeId, Code.class,
                	PDCodeTableConstants.STREET_TYPE).getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStreetNumSuffix()
    {
        if (streetNumSuffix == null)
        {
            try
            {
            	streetNumSuffix = (Code) new mojo.km.persistence.Reference(streetNumSuffixId, Code.class,
                        "STREET_NUM_SUFFIX").getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getStreetType()
    {
        fetch();
        initStreetType();
        return streetType;
    }
    
    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getStreetNumSuffix()
    {
        fetch();
        initStreetNumSuffix();
        return streetNumSuffix;
    }

    /**
     * set the type reference for class member streetType
     */
    public void setStreetType(Code streetType)
    {
        if (this.streetType == null || !this.streetType.equals(streetType))
        {
            markModified();
        }
        if (streetType.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(streetType);
        }
        setStreetTypeId("" + streetType.getOID());
        streetType.setContext(PDCodeTableConstants.STREET_TYPE);
        this.streetType = (Code) new mojo.km.persistence.Reference(streetType).getObject();
    }
    
    /**
     * set the type reference for class member streetType
     */
    public void setStreetNumSuffix(Code streetNumSuffix)
    {
        if (this.streetNumSuffix == null || !this.streetNumSuffix.equals(streetNumSuffix))
        {
            markModified();
        }
        if (streetNumSuffix.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(streetNumSuffix);
        }
        setStreetNumSuffixId("" + streetNumSuffix.getOID());
        streetNumSuffix.setContext("STREET_NUM_SUFFIX");
        this.streetNumSuffix = (Code) new mojo.km.persistence.Reference(streetNumSuffix).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setCountyId(String countyId)
    {
        if (this.countyId == null || !this.countyId.equals(countyId))
        {
            markModified();
        }
        county = null;
        this.countyId = countyId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getCountyId()
    {
        fetch();
        return countyId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initCounty()
    {
        if (county == null)
        {
            try
            {
                county = (Code) new mojo.km.persistence.Reference(countyId, Code.class, PDCodeTableConstants.JUVENILE_COUNTY)
                        .getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getCounty()
    {
        fetch();
        initCounty();
        return county;
    }

    /**
     * set the type reference for class member county
     */
    public void setCounty(Code county)
    {
        if (this.county == null || !this.county.equals(county))
        {
            markModified();
        }
        if (county.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(county);
        }
        setCountyId("" + county.getOID());
        county.setContext(PDCodeTableConstants.COUNTY);
        this.county = (Code) new mojo.km.persistence.Reference(county).getObject();
    }

    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setTitleId(String titleId)
    {
        if (this.titleId == null || !this.titleId.equals(titleId))
        {
            markModified();
        }
        title = null;
        this.titleId = titleId;
    }

    /**
     * Get the reference value to class :: pd.codetable.Code
     */
    public String getTitleId()
    {
        fetch();
        return titleId;
    }

    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initTitle()
    {
        if (title == null)
        {
            try
            {
                title = (Code) new mojo.km.persistence.Reference(titleId, Code.class, "PREFIX")
                        .getObject();
            }
            catch (Throwable t)
            {
            }
        }
    }

    /**
     * Gets referenced type pd.codetable.Code
     */
    public Code getTitle()
    {
        fetch();
        initTitle();
        return title;
    }

    /**
     * set the type reference for class member title
     */
    public void setTitle(Code title)
    {
        if (this.title == null || !this.title.equals(title))
        {
            markModified();
        }
        if (title.getOID() == null)
        {
            new mojo.km.persistence.Home().bind(title);
        }
        setTitleId("" + title.getOID());
        title.setContext("PREFIX");
        this.title = (Code) new mojo.km.persistence.Reference(title).getObject();
    }

    /**
     * Set the reference value to class :: pd.juvenilecase.Juvenile
     */
    public void setJuvenileId(String juvenileId)
    {
        if (this.juvenileId == null || !this.juvenileId.equals(juvenileId))
        {
            markModified();
        }
        this.juvenileId = juvenileId;
    }

    /**
     * Get the reference value to class :: pd.juvenilecase.Juvenile
     */
    public String getJuvenileId()
    {
        fetch();
        return juvenileId;
    }
    
    public void setPhonePriorityInd(String phonePriorityInd)
    {
        if (this.phonePriorityInd == null || !this.phonePriorityInd.equals(phonePriorityInd))
        {
            markModified();
        }
        this.phonePriorityInd = phonePriorityInd;
    }

    /**
     * Get the reference value to class :: pd.juvenilecase.Juvenile
     */
    public String getPhonePriorityInd()
    {
        fetch();
        return phonePriorityInd;
    }

    /**
     * @return
     */
    public String getAdditionalZipCode()
    {
        return additionalZipCode;
    }

    /**
     * @return
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param string
     */
    public void setAdditionalZipCode(String additionalZipCode)
    {
    	if (this.additionalZipCode == null || !this.additionalZipCode.equals(additionalZipCode))
        {
            markModified();
        }
        this.additionalZipCode = additionalZipCode;
    }

    /**
     * @param string
     */
    public void setCity(String city)
    {
    	if (this.city == null || !this.city.equals(city))
        {
            markModified();
        }
        this.city = city;
    }
    
    public int compareTo(Object obj) throws ClassCastException {
		JuvenileContact jc = (JuvenileContact)obj;
		return entryDate.compareTo(jc.getEntryDate());
	}

	/**
	 * @return Returns the workPhoneExtn.
	 */
	public String getWorkPhoneExtn() {
		return workPhoneExtn;
	}
	/**
	 * @param workPhoneExtn The workPhoneExtn to set.
	 */
	public void setWorkPhoneExtn(String workPhoneExtn) {
	   	if (this.workPhoneExtn == null || !this.workPhoneExtn.equals(workPhoneExtn))
        {
            markModified();
        }
		this.workPhoneExtn = workPhoneExtn;
	}

	public String getValidated() {
		return validated;
	}

	public void setValidated(String validated) {
	   	if (this.validated == null || !this.validated.equals(validated))
        {
            markModified();
        }
		this.validated = validated;
	}

	public boolean isDetentionVisit() {
	    return detentionVisit;
	}

	public void setDetentionVisit(boolean detentionVisit) {
	    this.detentionVisit = detentionVisit;
	}

	public boolean isAgeOver21() {
	    return ageOver21;
	}

	public void setAgeOver21(boolean ageOver21) {
	    this.ageOver21 = ageOver21;
	}

	public String getDriverLicenseNum() {
	    return driverLicenseNum;
	}

	public void setDriverLicenseNum(String driverLicenseNum) {
	    this.driverLicenseNum = driverLicenseNum;
	}

	public String getDriverLicenseStateId() {
	    fetch();
		return driverLicenseStateId;
	}

	public void setDriverLicenseStateId(String driverLicenseStateId) {
	    if (this.driverLicenseStateId == null || !this.driverLicenseStateId.equals(driverLicenseStateId)) {
		markModified();
	    	}
	    driverLicenseState = null;
	    this.driverLicenseStateId = driverLicenseStateId;
	}

	public String getDriverLicenseClassId() {
	    return driverLicenseClassId;
	}

	public void setDriverLicenseClassId(String driverLicenseClassId) {
	    this.driverLicenseClassId = driverLicenseClassId;
	}

	public String getDriverLicenseExpirationDate() {
	    return driverLicenseExpirationDate;
	}

	public void setDriverLicenseExpirationDate(String driverLicenseExpirationDate) {
	    this.driverLicenseExpirationDate = driverLicenseExpirationDate;
	}

	public String getIssuedByStateId() {
	    fetch();
	    return issuedByStateId;
	}

	public void setIssuedByStateId(String issuedByStateId) {
    	    if (this.issuedByStateId == null || !this.issuedByStateId.equals(issuedByStateId)) {
    		markModified();
    	    }
    	    issuedByState = null;
    	    this.issuedByStateId = issuedByStateId;
	}

	public String getStateIssuedIdNum() {
	    return stateIssuedIdNum;
	}

	public void setStateIssuedIdNum(String stateIssuedIdNum) {
	    this.stateIssuedIdNum = stateIssuedIdNum;
	}

	public String getPassportNum() {
	    return passportNum;
	}

	public void setPassportNum(String passportNum) {
	    this.passportNum = passportNum;
	}

	public String getCountryOfIssuanceId() {
	    fetch();
	    return countryOfIssuanceId;
	}

	public void setCountryOfIssuanceId(String countryOfIssuanceId) {
	    if (this.countryOfIssuanceId == null || !this.countryOfIssuanceId.equals(countryOfIssuanceId)) {
		markModified();
	    }
	    countryOfIssuance = null;
	    this.countryOfIssuanceId = countryOfIssuanceId;
	}
	public Code getCountryOfIssuance() {
	    fetch();
	    initPsportIssueCountry();
	    return countryOfIssuance;
	}
	public void setCountryOfIssuance(Code countryOfIssuance) {
	    if (this.countryOfIssuance == null || !this.countryOfIssuance.equals(countryOfIssuance)) {
        	markModified();
	    }
	    if (countryOfIssuance.getOID() == null) {
        	new mojo.km.persistence.Home().bind(countryOfIssuance);
	    }
	    setCountryOfIssuanceId("" + countryOfIssuance.getOID());
	    countryOfIssuance.setContext(PDCodeTableConstants.PLACE_OF_BIRTH);
	    this.countryOfIssuance = (Code) new mojo.km.persistence.Reference(countryOfIssuance).getObject();
	}
	private void initPsportIssueCountry() {
		if (countryOfIssuance == null) {
			countryOfIssuance = (Code) new mojo.km.persistence.Reference(countryOfIssuanceId, Code.class, PDCodeTableConstants.PLACE_OF_BIRTH).getObject();
		}
	}
	public String getPassportExpirationDate() {
	    return passportExpirationDate;
	}

	public void setPassportExpirationDate(String passportExpirationDate) {
	    this.passportExpirationDate = passportExpirationDate;
	}

	public Code getDriverLicenseState() {
	    fetch();
	    initDriverLicenseState();
	    return driverLicenseState;
	}
	private void initDriverLicenseState() {
		if (driverLicenseState == null) {
		driverLicenseState = (Code) new mojo.km.persistence.Reference(driverLicenseStateId, Code.class, PDCodeTableConstants.STATE_ABBR).getObject();
		}
	}

	public void setDriverLicenseState(Code driverLicenseState) {
		if (this.driverLicenseState == null || !this.driverLicenseState.equals(driverLicenseState)) {
			markModified();
		}
		if (driverLicenseState.getOID() == null) {
			new mojo.km.persistence.Home().bind(driverLicenseState);
		}
		setDriverLicenseStateId("" + driverLicenseState.getOID());
		driverLicenseState.setContext(PDCodeTableConstants.STATE_ABBR);
		this.driverLicenseState = (Code) new mojo.km.persistence.Reference(driverLicenseState).getObject();
	}

	public Code getDriverLicenseClass() {
	    fetch();
	    initDriverLicenseClass();
	    return driverLicenseClass;
	}
	
	private void initDriverLicenseClass (){
	    if (driverLicenseClass == null) {
		driverLicenseClass = (Code) new mojo.km.persistence.Reference(driverLicenseClassId, Code.class, "DRIVERS_LICENSE_CLASS").getObject();
	    }
	}

	public void setDriverLicenseClass(Code driverLicenseClass) {
	    this.driverLicenseClass = driverLicenseClass;
	}

	public Code getIssuedByState() {
	    fetch();
	    initIdNumState();
	    return issuedByState;
	}
	private void initIdNumState() {
	    if (issuedByState == null) {
		issuedByState = (Code) new mojo.km.persistence.Reference(issuedByStateId, Code.class, PDCodeTableConstants.STATE_ABBR).getObject();
	    }
	}
	public void setIssuedByState(Code issuedByState) {
	    if (this.issuedByState == null || !this.issuedByState.equals(issuedByState)) {
		markModified();
	    }
	    if (issuedByState.getOID() == null) {
    		new mojo.km.persistence.Home().bind(issuedByState);
	    }
	    setIssuedByStateId("" + issuedByState.getOID());
	    issuedByState.setContext(PDCodeTableConstants.STATE_ABBR);
	    this.issuedByState = (Code) new mojo.km.persistence.Reference(issuedByState).getObject();
	}

	public String getContactMemberComments()
	{
	    fetch();
	    return contactMemberComments;
	}

	public void setContactMemberComments(String contactMemberComments)
	{
	    this.contactMemberComments = contactMemberComments;
	}
	
	


}

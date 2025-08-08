/*
 * Created on Jul 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.juvenilecase.family;

import java.util.Date;
import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class GuardianInfo extends PersistentObject {

	private String juvenileId;
	private String lastName;
	private String middleName;
	private String firstName;
	private String primaryContact;
	/**
	 * @detailerDoNotGenerate true
	 * @referencedType pd.codetable.Code
	 * @contextKey PHONE_TYPE
	 */
	private Code phoneType = null;
	private String phoneTypeId;
	private String phone;
	private String extension;
	private Date createDate;
	private String famMemberId;
	
	private String addressId;
	/**
     * Properties for addressType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey ADDRESS_TYPE
     * @detailerDoNotGenerate true
     */
    private Code addressType = null;
    private String addressTypeId;
    private String streetNumber;
    /**
     * Properties for streetNumSuffix
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STREET_SUFFIX
     * @detailerDoNotGenerate true
     */
    private Code streetNumSuffix = null;
    private String streetNumSuffixId;
    
    private String streetName;
    /**
     * Properties for streetType
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STREET_TYPE
     * @detailerDoNotGenerate true
     */
    private Code streetType = null;
    private String streetTypeId;
    /**
     * Properties for state
     * 
     * @referencedType pd.codetable.Code
     * @contextKey STATE_ABBR
     * @detailerDoNotGenerate true
     */
    private Code state = null;
    private String stateId;
    private String city;
    private String zipCode;
    private String additionalZipCode;
    
    /**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey COUNTY
	*/
	private Code county = null;
	private String countyId;
	private String aptNumber;
	private String validated;
    

	/**
	 * Finds GuardianInfo by a certain event
	 * 
	 * @roseuid 45AF7A0A0192
	 * @param event
	 *            The event.
	 * @return Iterator of GuardianInfo
	 */
	public static Iterator findAll(IEvent event) {
		IHome home = new Home();
		return home.findAll(event, GuardianInfo.class);
	}

	/**
	 * Finds GuardianInfo by a certain event
	 * 
	 * @roseuid 45AF7A0A0192
	 * @param event
	 *            The event.
	 * @return Iterator of GuardianInfo
	 */
	public static Iterator findAll(String juvenileId) {
		IHome home = new Home();
		return home.findAll("juvenileId", juvenileId, GuardianInfo.class);
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param juvenileId
	 */
	/*
	 * public static GuardianInfo find(String juvenileId) { IHome home = new
	 * Home(); GuardianInfo guardianInfo = (GuardianInfo) home.find(juvenileId,
	 * GuardianInfo.class); return guardianInfo; }
	 */


	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		fetch();
		return firstName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param firstName
	 *            The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the juvenileId.
	 */
	public String getJuvenileId() {
		fetch();
		return juvenileId;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param juvenileId
	 *            The juvenileId to set.
	 */
	public void setJuvenileId(String juvenileId) {
		this.juvenileId = juvenileId;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		fetch();
		return lastName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param lastName
	 *            The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the middleName.
	 */
	public String getMiddleName() {
		fetch();
		return middleName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param middleName
	 *            The middleName to set.
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the phone.
	 */
	public String getPhone() {
		fetch();
		return phone;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param phone
	 *            The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @roseuid 45AF7A0A0192 Set the reference value to class ::
	 *          pd.codetable.Code
	 * 
	 * @param phoneTypeId
	 *            The phone type id.
	 */
	public void setPhoneTypeId(String phoneTypeId) {
		this.phoneTypeId = phoneTypeId;
	}

	/**
	 * @roseuid 45AF7A0A0192 Get the reference value to class ::
	 *          pd.codetable.Code
	 * 
	 * @return The phone type id.
	 */
	public String getPhoneTypeId() {
		fetch();
		return phoneTypeId;
	}

	/**
	 * @roseuid 45AF7A0A0192 Gets referenced type pd.codetable.Code
	 * 
	 * @return The phone type.
	 */
	public Code getPhoneType() {
		initPhoneType();
		return phoneType;
	}

	/**
	 * @roseuid 45AF7A0A0192 set the type reference for class member phoneType
	 * 
	 * @param phoneType
	 *            The phone type.
	 */
	public void setPhoneType(Code phoneType) {
		setPhoneTypeId("" + phoneType.getOID());
		phoneType.setContext("PHONE_TYPE");
		this.phoneType = (Code) new mojo.km.persistence.Reference(phoneType).getObject();
	}

	/**
	 * @roseuid 45AF7A0A0192 Initialize class relationship to class
	 *          pd.codetable.Code
	 */
	private void initPhoneType() {
		if (phoneType == null) {
			phoneType = (Code) new mojo.km.persistence.Reference(phoneTypeId, Code.class,
					"PHONE_TYPE").getObject();
		}
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the extension.
	 */
	public String getExtension() {
		fetch();
		return extension;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param extension
	 *            The extension to set.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		fetch();
		return createDate;
	}

	/**
	 * @param createDate
	 *            The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the famMemberId.
	 */
	public String getFamMemberId() {
		fetch();
		return famMemberId;
	}

	/**
	 * @param famMemberId
	 *            The famMemberId to set.
	 */
	public void setFamMemberId(String famMemberId) {
		this.famMemberId = famMemberId;
	}
	
	/**
	 * @roseuid 45AF7A0A0192
	 * @return Returns the addressId.
	 */
	public String getAddressId() {
		fetch();
		return addressId;
	}

	/**
	 * @roseuid 45AF7A0A0192
	 * @param addressId
	 *            The juvenileId to set.
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	/**
     * @return Code addressType
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
    public void setAddressType(Code laddressType)
    {
        setAddressTypeId("" + laddressType.getOID());
        this.addressType = (Code) new mojo.km.persistence.Reference(laddressType).getObject();
    }
    
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initAddressType()
    {
        if (addressType == null)
        {
            addressType = (Code) new mojo.km.persistence.Reference(addressTypeId, Code.class,
        	    PDCodeTableConstants.ADDRESS_TYPE).getObject();
        }
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String addressTypeId
     */
    public String getAddressTypeId()
    {
        fetch();
        return addressTypeId;
    }
    
    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setAddressTypeId(String laddressTypeId)
    {
        this.addressTypeId = laddressTypeId;
    }
    
	/**
     * Access method for the streetNum property.
     * 
     * @return the current value of the streetNumber property
     */
    public String getStreetNumber()
    {
        fetch();
        return streetNumber;
    }
	
	/**
     * Sets the value of the streetNumber property.
     * 
     * @param aStreetNum
     *            the new value of the streetNumber property
     */
    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
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
    public void setStreetName(String streetName)
    {
         this.streetName = streetName;
    }
    
    /**
     * @return Code streetType
     */
    public Code getStreetType()
    {
        fetch();
        initStreetType();
        return streetType;
    }
    
    /**
     * set the type reference for class member streetType
     */
    public void setStreetType(Code lstreetType)
    {
        setStreetTypeId("" + lstreetType.getOID());
        this.streetType = (Code) new mojo.km.persistence.Reference(lstreetType).getObject();
    }
    
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStreetType()
    {
        if (streetType == null)
        {
            streetType = (Code) new mojo.km.persistence.Reference(streetTypeId, Code.class,
        	    PDCodeTableConstants.STREET_TYPE).getObject();
        }
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String streetTypeId
     */
    public String getStreetTypeId()
    {
        fetch();
        return streetTypeId;
    }
    
    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStreetTypeId(String streetTypeId)
    {
        this.streetTypeId = streetTypeId;
    }
    
    /**
     * Access method for the state property.
     * 
     * @return the current value of the state property
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
    public void setState(Code lstate)
    {
        setStateId("" + lstate.getOID());
        this.state = (Code) new mojo.km.persistence.Reference(lstate).getObject();
    }
    
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initState()
    {
        if (state == null)
        {
            state = (Code) new mojo.km.persistence.Reference(stateId, Code.class,
        	    PDCodeTableConstants.STATE_ABBR).getObject();
        }
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String stateId
     */
    public String getStateId()
    {
        fetch();
        return stateId;
    }
    
    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStateId(String stateId)
    {
         this.stateId = stateId;
    }
    
    /**
     * Access method for the city property.
     * 
     * @return the current value of the city property
     */
    public String getCity()
    {
        fetch();
        return city;
    }
    
    /**
     * Sets the value of the city property.
     * 
     * @param aCity
     *            the new value of the city property
     */
    public void setCity(String city)
    {
        this.city = city;
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
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }
    
    /**
	* @return String
	*/
	public String getAdditionalZipCode()
	{
		fetch();
		return additionalZipCode;
	}
	
	/**
	* @param string
	*/
	public void setAdditionalZipCode(String additionalZipCode)
	{
		this.additionalZipCode = additionalZipCode;
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
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setCountyId(String countyId)
	{
		this.countyId = countyId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCounty()
	{
		if (county == null)
		{
			county =
				(Code) new mojo
					.km
					.persistence
					.Reference(countyId, Code.class, PDCodeTableConstants.JUVENILE_COUNTY)
					.getObject();
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
		setCountyId("" + county.getOID());
		county.setContext(PDCodeTableConstants.COUNTY);
		this.county = (Code) new mojo.km.persistence.Reference(county).getObject();
	}
	
	/**
	 * @return Returns the aptNumber.
	 */
	public String getAptNumber()
	{
		fetch();
		return aptNumber;
	}
	
	/**
	 * @param aptNumber The aptNumber to set.
	 */
	public void setAptNumber(String aptNumber)
	{
		this.aptNumber = aptNumber;
	}

	public String getValidated() {
		fetch();
		return validated;
	}

	public void setValidated(String validated) {
		this.validated = validated;
	}
	
    /**
     * @return Code streetType
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
    public void setStreetNumSuffix(Code streetNumSuffix)
    {
        setStreetNumSuffixId("" + streetNumSuffix.getOID());
        this.streetNumSuffix = (Code) new mojo.km.persistence.Reference(streetNumSuffix).getObject();
    }
    
    /**
     * Initialize class relationship to class pd.codetable.Code
     */
    private void initStreetNumSuffix()
    {
        if (streetNumSuffix == null)
        {
            streetNumSuffix = (Code) new mojo.km.persistence.Reference(streetNumSuffixId, Code.class,
                    "STREET_SUFFIX").getObject();
        }
    }
    
    /**
     * Get the reference value to class :: pd.codetable.Code
     * 
     * @return String streetTypeId
     */
    public String getStreetNumSuffixId()
    {
        fetch();
        return streetNumSuffixId;
    }
    
    /**
     * Set the reference value to class :: pd.codetable.Code
     */
    public void setStreetNumSuffixId(String streetNumSuffixId)
    {
        this.streetNumSuffixId = streetNumSuffixId;
    }

	/**
	 * @return the primaryContact
	 */
	public String getPrimaryContact() {
		return primaryContact;
	}

	/**
	 * @param primaryContact the primaryContact to set
	 */
	public void setPrimaryContact(String primaryContact) {
		this.primaryContact = primaryContact;
	}

}

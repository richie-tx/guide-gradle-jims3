package pd.address;

import java.util.Date;
import java.util.Iterator;

import messaging.contact.domintf.IAddress;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

public class Address extends PersistentObject
{
	private String addressId;
	private String addressStatus;

	private String currentAddressInd;
	private String additionalZipCode;
	private String streetNum;
	/**
	* Properties for streetNumSuffix
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey STREET_NUM_SUFFIX
	*/
	private Code streetNumSuffix;
	private String streetNumSuffixId;
	/**
	* Properties for addressType
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey ADDRESS_TYPE
	*/
	private Code addressType = null;
	private String countryId;
	/**
	* @detailerDoNotGenerate false
	* @referencedType pd.codetable.Code
	* @contextKey COUNTY
	*/
	private Code county = null;
	private String aptNum;
	private double longitude;
	private String addressTypeId;
	private String address2;
	private String keymap;
	private String streetName;
	/**
	* Properties for country
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey PLACE_OF_BIRTH_COUNTRY
	*/
	private Code country = null;
	private String zipCode;
	/**
	* Properties for streetType
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey STREET_TYPE
	*/
	private Code streetType = null;
	private String streetTypeId;
	/**
	* Properties for state
	* @detailerDoNotGenerate true
	* @referencedType pd.codetable.Code
	* @contextKey STATE_ABBR
	*/
	private Code state = null;
	private double latitude;
	private String countyId;
	private String city;
	private String stateId;
	
	private Date createDate;
	
	private String criteria;
	private String validated;
	/**
	* @roseuid 4180ED3B0006
	*/
	public Address()
	{
	}
	/**
	* @roseuid 417FAB25037A
	*/
	public void bind()
	{
		markModified();
	}

	public void fillAddress(IAddress address)
	{
		if (address != null)
		{
			address.setAdditionalZipCode(this.getAdditionalZipCode());
			address.setAddress2(this.getAddress2());
			address.setAddressId(this.getAddressId());
			address.setAddressStatus(this.getAddressStatus());
			address.setAddressTypeCode(this.getAddressTypeId());
			address.setAptNum(this.getAptNum());
			address.setCity(this.getCity());
			address.setCountryCode(this.getCountryId());
			address.setCountyCode(this.getCountyId());
			address.setCurrentAddressInd(this.getCurrentAddressInd());
			address.setKeymap(this.getKeymap());
			address.setLatitude(String.valueOf(this.getLatitude()));
			address.setLongitude(String.valueOf(this.getLongitude()));
			address.setStateCode(this.getStateId());
			address.setStreetName(this.getStreetName());
			address.setStreetNum(this.getStreetNum());
			address.setStreetTypeCode(this.getStreetTypeId());
			address.setZipCode(this.getZipCode());
			address.setCreateDate(this.getCreateDate());
			address.setValidated(this.getValidated());
			address.setStreetNumSuffixCode(this.getStreetNumSuffixId());
		}
	}

	/**
	 *
	 */
	public void updateAddress( IAddress address )
	{
		if ( address == null )
		{
			return;
		}

		setAdditionalZipCode( address.getAdditionalZipCode() );
		setAddress2( address.getAddress2() );
		setAddressStatus( address.getAddressStatus() );
		setAddressTypeId( address.getAddressTypeCode() );
		setAptNum( address.getAptNum() );
		setCity( address.getCity() );
		setCountryId( address.getCountryCode() );
		setCountyId( address.getCountyCode() );
		setCurrentAddressInd( address.getCurrentAddressInd() );
		setKeymap( address.getKeymap() );
		
		// Long and Lat are set to 0 if the string is null or empty. 
		// All other number format errors result in a runtime exception. 
		if ( address.getLatitude() == null || address.getLatitude().trim().length() == 0 )
		{
			setLatitude( 0 );
		}
		else
		{
			setLatitude( Double.parseDouble(address.getLatitude()) );
		}
				
		
		if ( address.getLongitude() == null || address.getLongitude().trim().length() == 0 )
		{
			setLongitude( 0 );
		}
		else
		{
			setLongitude( Double.parseDouble(address.getLongitude()) );
		}
				
		
		setStateId( address.getStateCode() );
		setStreetName( address.getStreetName() );
		setStreetNum( address.getStreetNum() );
		setStreetTypeId( address.getStreetTypeCode() );
		setValidated(address.getValidated());
		setZipCode( address.getZipCode() );
		setCreateDate( address.getCreateDate() );
		setStreetNumSuffixId(address.getStreetNumSuffixCode());
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
	* Access method for the address2 property.
	* @return the current value of the address2 property
	*/
	public String getAddress2()
	{
		fetch();
		return address2;
	}
	/**
	* Access method for the addressId property.
	* @return the current value of the addressId property
	*/
	public String getAddressId()
	{
		return "" + getOID();
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return addressTypeCode
	*/
	public Code getAddressType()
	{
		fetch();
		initAddressType();
		return addressType;
	}
	
	/**
		* Gets referenced type pd.codetable.Code
		* @return addressTypeCode
		*/
		public Code getDepartmentAddressType()
		{
			fetch();
			initDepartmentAddressType();
			return addressType;
		}
	/**
	 * 
	 */
	private void initDepartmentAddressType()
	{
		if (addressType == null)
		{
			try
			{
				addressType =
					(Code) new mojo
						.km
						.persistence
						.Reference(addressTypeId, Code.class, "CONTACT.ADDRESS.TYPE")
						.getObject();
			}
			catch (Throwable t)
			{
				addressType = null;
			}
		}
		
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String
	*/
	public String getAddressTypeId()
	{
		fetch();
		return addressTypeId;
	}
	/**
	* Access method for the city property.
	* @return the current value of the city property
	*/
	public String getCity()
	{
		fetch();
		return city;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return String
	*/
	public Code getCountry()
	{
		fetch();
		initCountry();
		return country;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String
	*/
	public String getCountryId()
	{
		fetch();
		return countryId;
	}
	/**
	* @return String
	*/
	public String getCurrentAddressInd()
	{
		fetch();
		return currentAddressInd;
	}
	/**
	* Access method for the keymap property.
	* @return the current value of the keymap property
	*/
	public String getKeymap()
	{
		fetch();
		return keymap;
	}
	/**
	* Access method for the latitude property.
	* @return the current value of the latitude property
	*/
	public double getLatitude()
	{
		fetch();
		return latitude;
	}
	/**
	* Access method for the longitude property.
	* @return the current value of the longitude property
	*/
	public double getLongitude()
	{
		fetch();
		return longitude;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return Code
	*/
	public Code getState()
	{
		fetch();
		initState();
		return state;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String
	*/
	public String getStateId()
	{
		fetch();
		return stateId;
	}
	/**
	* Access method for the streetName property.
	* @return the current value of the streetName property
	*/
	public String getStreetName()
	{
		fetch();
		return streetName;
	}
	/**
	* Access method for the streetNum property.
	* @return the current value of the streetNum property
	*/
	public String getStreetNum()
	{
		fetch();
		return streetNum;
	}
	/**
	* Gets referenced type pd.codetable.Code
	* @return StreetTypeCode
	*/
	public Code getStreetType()
	{
		fetch();
		initStreetType();
		return streetType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String
	*/
	public String getStreetTypeId()
	{
		fetch();
		return streetTypeId;
	}
	/**
	* Access method for the zipCode property.
	* @return the current value of the zipCode property
	*/
	public String getZipCode()
	{
		fetch();
		return zipCode;
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
				addressType =
					(Code) new mojo
						.km
						.persistence
						.Reference(addressTypeId, Code.class, PDCodeTableConstants.ADDRESS_TYPE)
						.getObject();
			}
			catch (Throwable t)
			{
				addressType = null;
			}
		}
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initCountry()
	{
		if (country == null)
		{
			try
			{
				country =
					(Code) new mojo
						.km
						.persistence
						.Reference(countryId, Code.class, PDCodeTableConstants.PLACE_OF_BIRTH)
						.getObject();
			}
			catch (Throwable t)
			{
				country = null;
			}
		}
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
				state =
					(Code) new mojo
						.km
						.persistence
						.Reference(stateId, Code.class, PDCodeTableConstants.STATE_ABBR)
						.getObject();
			}
			catch (Throwable t)
			{
				state = null;
			}
		}
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
				streetType =
					(Code) new mojo
						.km
						.persistence
						.Reference(streetTypeId, Code.class, PDCodeTableConstants.STREET_TYPE)
						.getObject();
			}
			catch (Throwable t)
			{
				streetType = null;
			}
		}
	}
	/**
	* @param string
	*/
	public void setAdditionalZipCode(String aAdditionalZipCode)
	{
		if (this.additionalZipCode == null || !this.additionalZipCode.equals(aAdditionalZipCode))
		{
			markModified();
		}
		additionalZipCode = aAdditionalZipCode;
	}
	
	/**
	* Sets the value of the address2 property.
	* @param aAddress2 the new value of the address2 property
	*/
	public void setAddress2(String aAddress2)
	{
		if (this.address2 == null || !this.address2.equals(aAddress2))
		{
			markModified();
		}
		address2 = aAddress2;
	}
	
	/**
	* Sets the value of the addressId property.
	* @param aAddressId the new value of the addressId property
	*/
	public void setAddressId(String aAddressId)
	{
		if (this.addressId == null || !this.addressId.equals(aAddressId))
		{
			markModified();
		}
		addressId = aAddressId;
	}
	
	/**
	* set the type reference for class member addressType
	* @param addressType
	*/
	public void setAddressType(Code aAddressType)
	{
		if (this.addressType == null || !this.addressType.equals(aAddressType))
		{
			markModified();
		}
		if (aAddressType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aAddressType);
		}
		setAddressTypeId("" + aAddressType.getOID());
		this.addressType = (Code) new mojo.km.persistence.Reference(aAddressType).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param addressTypeId
	*/
	public void setAddressTypeId(String aAddressTypeId)
	{
		if (this.addressTypeId == null || !this.addressTypeId.equals(aAddressTypeId))
		{
			markModified();
		}
		addressType = null;
		this.addressTypeId = aAddressTypeId;
	}
	
	/**
	* Sets the value of the city property.
	* @param aCity the new value of the city property
	*/
	public void setCity(String aCity)
	{
		if (this.city == null || !this.city.equals(aCity))
		{
			markModified();
		}
		city = aCity;
	}
	
	/**
	* set the type reference for class member country
	* @param countryCode
	*/
	public void setCountry(Code aCountry)
	{
		if (this.country == null || !this.country.equals(aCountry))
		{
			markModified();
		}
		if (aCountry.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aCountry);
		}
		setCountryId("" + aCountry.getOID());
		this.country = (Code) new mojo.km.persistence.Reference(aCountry).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param countryId
	*/
	public void setCountryId(String aCountryId)
	{
		if (this.countryId == null || !this.countryId.equals(aCountryId))
		{
			markModified();
		}
		country = null;
		this.countryId = aCountryId;
	}
	
	/**
	* @param string
	*/
	public void setCurrentAddressInd(String aCurrentAddressInd)
	{
		if (this.currentAddressInd == null || !this.currentAddressInd.equals(aCurrentAddressInd))
		{
			markModified();
		}
		currentAddressInd = aCurrentAddressInd;
	}
	
	/**
	* Sets the value of the keymap property.
	* @param aKeymap the new value of the keymap property
	*/
	public void setKeymap(String aKeymap)
	{
		if (this.keymap == null || !this.keymap.equals(aKeymap))
		{
			markModified();
		}
		keymap = aKeymap;
	}
	
	/**
	* Sets the value of the latitude property.
	* @param aLatitude the new value of the latitude property
	*/
	public void setLatitude(double aLatitude)
	{
		if (this.latitude != aLatitude)
		{
			markModified();
		}
		latitude = aLatitude;
	}
	
	/**
	* Sets the value of the longitude property.
	* @param aLongitude the new value of the longitude property
	*/
	public void setLongitude(double aLongitude)
	{
		if (this.longitude != aLongitude)
		{
			markModified();
		}
		longitude = aLongitude;
	}
	
	/**
	* set the type reference for class member state
	* @param stateCode
	*/
	public void setState(Code aState)
	{
		if (this.state == null || !this.state.equals(aState))
		{
			markModified();
		}
		if (aState.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aState);
		}
		setStateId("" + aState.getOID());
		this.state = (Code) new mojo.km.persistence.Reference(aState).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aStateId
	*/
	public void setStateId(String aStateId)
	{
		if (this.stateId == null || !this.stateId.equals(aStateId))
		{
			markModified();
		}
		state = null;
		this.stateId = aStateId;
	}
	

	/**
	* Sets the value of the streetName property.
	* @param aStreetName the new value of the streetName property
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
	* Sets the value of the streetNum property.
	* @param aStreetNum the new value of the streetNum property
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
	* set the type reference for class member streetType
	* @param streetTypeCode
	*/
	public void setStreetType(Code aStreetType)
	{
		if (this.streetType == null || !this.streetType.equals(aStreetType))
		{
			markModified();
		}
		if (aStreetType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aStreetType);
		}
		setStreetTypeId("" + aStreetType.getOID());
		this.streetType = (Code) new mojo.km.persistence.Reference(aStreetType).getObject();
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param streetTypeId
	*/
	public void setStreetTypeId(String aStreetTypeId)
	{
		if (this.streetTypeId == null || !this.streetTypeId.equals(aStreetTypeId))
		{
			markModified();
		}
		streetType = null;
		this.streetTypeId = aStreetTypeId;
	}
	
	/**
	* Sets the value of the zipCode property.
	* @param aZipCode the new value of the zipCode property
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
	* Returns the value of the aptNum property
	* @return java.lang.String
	*/
	public String getAptNum()
	{
		fetch();
		return aptNum;
	}
	
	/**
	* Sets the value of the aptNum property
	* @param string
	*/
	public void setAptNum(String anAptNum)
	{
		if (this.aptNum == null || !this.aptNum.equals(anAptNum))
		{
			markModified();
		}
		this.aptNum = anAptNum;
	}

	/**
	 * @return
	 */
	public String getAddressStatus()
	{
		fetch();
		return addressStatus;
	}

	/**
	 * @param string
	 */
	public void setAddressStatus(String addressStatus)
	{
		if (this.addressStatus == null || !this.addressStatus.equals(addressStatus))
		{
			markModified();
		}
		this.addressStatus = addressStatus;
	}
	/**
	* @param attrName
	* @param attrValue
	* @return java.util.Iterator
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, Address.class);
	}
    	
    	/**
    	* @return java.util.Iterator
    	* @param event
    	* @roseuid 4107B06D01BB
    	*/
    	static public Iterator findAll(IEvent event)
    	{
    		IHome home = new Home();
    		Iterator iter = home.findAll(event, Address.class);
    		return iter;
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
			county =
				(Code) new mojo
					.km
					.persistence
					.Reference(countyId, Code.class, PDCodeTableConstants.COUNTY)
					.getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getCounty()
	{
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
	 * 
	 * @return
	 */
	public String getStreetNumSuffixId() {
		fetch();
		return streetNumSuffixId;
	}
	/**
	 * 
	 * @param streetSuffixId
	 */
	public void setStreetNumSuffixId(String streetNumSuffixId) {
		if (this.streetNumSuffixId == null || !this.streetNumSuffixId.equals(streetNumSuffixId))
		{
			markModified();
		}
		streetNumSuffix = null;
		this.streetNumSuffixId = streetNumSuffixId;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStreetNumSuffix()
	{
		if (streetNumSuffix == null)
		{
			streetNumSuffix =
				(Code) new mojo
					.km
					.persistence
					.Reference(streetNumSuffixId, Code.class, "STREET_NUM_SUFFIX")
					.getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStreetNumSuffix()
	{
		initStreetNumSuffix();
		return streetNumSuffix;
	}
	
	/**
	* set the type reference for class member streetSuffix
	*/
	public void setStreetSuffix(Code streetSuffix)
	{
		if (this.streetNumSuffix == null || !this.streetNumSuffix.equals(streetSuffix))
		{
			markModified();
		}
		if (streetNumSuffix.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(streetNumSuffix);
		}
		setStreetNumSuffixId("" + streetNumSuffix.getOID());
		streetSuffix.setContext("STREET_NUM_SUFFIX");
		this.streetNumSuffix = (Code) new mojo.km.persistence.Reference(streetNumSuffix).getObject();
	}	
	/**
	 * @return
	 */
	public Date getCreateDate()
	{
		fetch();
		return createDate;
	}

	/**
	 * 
	 * @param date
	 */
	public void setCreateDate(Date date)
	{
		if (this.createDate == null || !this.createDate.equals(date))
		{
			markModified();
		}
        this.createDate = date;
	}
	
	/**
	 * 
	 * @param oid
	 * @return
	 */
	static public Address find(int oid) {
		Integer i = new Integer(oid);
		String addressId = i.toString();
		Address address = null;
		IHome home = new Home();
		address = (Address) home.find(addressId, Address.class);
		return address;
	}
	
    /**
     * @return Returns the criteria.
     */
    public String getCriteria() {
        fetch();
        return criteria;
    }
    
    /**
     * @param criteria The criteria to set.
     */
    
    public void setCriteria(String aCriteria) {
        if (this.criteria == null || !this.criteria.equals(aCriteria))
		{
			markModified();
		}
        this.criteria = aCriteria;
    }
	public String getValidated() {
		return validated;
	}
	public void setValidated(String aValidated) {
		if (this.validated != aValidated)
		{
			markModified();
		}
		
		this.validated = aValidated;
	}
    
}

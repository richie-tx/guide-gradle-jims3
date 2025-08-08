package pd.juvenilecase.family;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import naming.PDJuvenileFamilyConstants;
import pd.codetable.Code;

/**
* @author athorat
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class FamilyMemberAddressView extends PersistentObject
{
	
	private String constellationId;
	private String memberLastName;
	private double longitude;
	private String addressTypeId;
	private String address2;
	private String keymap;
	private String streetName;
	private String addressStatus;
	private String streetTypeId;
	private String stateId;
	private String city;
	private String currentAddressInd;
	private String memberMiddleName;
	private String memberId;
	private String memberAddressId;
	private String validated;
	
	private String juvenileNum;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey JUVENILE_RELATIONSHIP
	*/
	public Code relationshipToJuvenile = null;

	private String relationshipToJuvenileId;
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
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey ADDRESS_TYPE
	*/
	private Code addressType = null;
	private String countryId;
	/**
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate false
	* @contextKey COUNTY
	*/
	private Code county = null;
	private String aptNum;
	/**
	* Properties for country
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey PLACE_OF_BIRTH_COUNTRY
	*/
	private Code country = null;
	private String zipCode;
	/**
	* Properties for streetType
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey STREET_TYPE
	*/
	private Code streetType = null;
	/**
	* Properties for state
	* @referencedType pd.codetable.Code
	* @detailerDoNotGenerate true
	* @contextKey STATE_ABBR
	*/
	private Code state = null;
	private double latitude;
	private String countyId;
	private String memberFirstName;

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
		fetch();
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
	public void setAdditionalZipCode(String additionalZipCode)
	{
		this.additionalZipCode = additionalZipCode;
	}
	/**
	* Sets the value of the address2 property.
	* @param aAddress2 the new value of the address2 property
	*/
	public void setAddress2(String address2)
	{
		this.address2 = address2;
	}

	/**
	* set the type reference for class member addressType
	* @param addressType
	*/
	public void setAddressType(Code aAddressType)
	{
		setAddressTypeId("" + aAddressType.getOID());
		this.addressType = (Code) new mojo.km.persistence.Reference(aAddressType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param addressTypeId
	*/
	public void setAddressTypeId(String aAddressTypeId)
	{
		this.addressTypeId = aAddressTypeId;
	}
	/**
	* Sets the value of the city property.
	* @param aCity the new value of the city property
	*/
	public void setCity(String city)
	{
		this.city = city;
	}
	/**
	* set the type reference for class member country
	* @param countryCode
	*/
	public void setCountry(Code aCountry)
	{
		setCountryId("" + aCountry.getOID());
		this.country = (Code) new mojo.km.persistence.Reference(aCountry).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param countryId
	*/
	public void setCountryId(String aCountryId)
	{
		this.countryId = aCountryId;
	}
	/**
	* @param string
	*/
	public void setCurrentAddressInd(String currentAddressInd)
	{
		this.currentAddressInd = currentAddressInd;
	}
	/**
	* Sets the value of the keymap property.
	* @param aKeymap the new value of the keymap property
	*/
	public void setKeymap(String keymap)
	{
		this.keymap = keymap;
	}
	/**
	* Sets the value of the latitude property.
	* @param aLatitude the new value of the latitude property
	*/
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	/**
	* Sets the value of the longitude property.
	* @param aLongitude the new value of the longitude property
	*/
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	/**
	* set the type reference for class member state
	* @param stateCode
	*/
	public void setState(Code aState)
	{
		setStateId("" + aState.getOID());
		this.state = (Code) new mojo.km.persistence.Reference(aState).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param aStateId
	*/
	public void setStateId(String aStateId)
	{
		this.stateId = aStateId;
	}
	/**
	* Sets the value of the streetName property.
	* @param aStreetName the new value of the streetName property
	*/
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}
	/**
	* Sets the value of the streetNum property.
	* @param aStreetNum the new value of the streetNum property
	*/
	public void setStreetNum(String streetNum)
	{
		this.streetNum = streetNum;
	}
	/**
	* set the type reference for class member streetType
	* @param streetTypeCode
	*/
	public void setStreetType(Code aStreetType)
	{
		setStreetTypeId("" + aStreetType.getOID());
		this.streetType = (Code) new mojo.km.persistence.Reference(aStreetType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param streetTypeId
	*/
	public void setStreetTypeId(String aStreetTypeId)
	{
		this.streetTypeId = aStreetTypeId;
	}
	/**
	* Sets the value of the zipCode property.
	* @param aZipCode the new value of the zipCode property
	*/
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
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
		this.addressStatus = addressStatus;
	}
	/**
	* @return java.util.Iterator
	* @param attrName
	* @param attrValue
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, FamilyMemberAddressView.class);
	}
	
    /**
     * Finds FamilyMemberAddressView by a certain event
     *  
     * @param event The event.
     * @return  Iterator of FamilyMemberAddressView
     */
    public static Iterator findAll(IEvent event) {
        IHome home = new Home();
        return (Iterator) home.findAll(event, FamilyMemberAddressView.class);
    }
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setCountyId(String countyId)
	{
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
	* @return 
	*/
	public String getConstellationId()
	{
		fetch();
		return constellationId;
	}
	/**
	* @return 
	*/
	public String getMemberFirstName()
	{
		fetch();
		return memberFirstName;
	}
	/**
	* @return 
	*/
	public String getMemberId()
	{
		fetch();
		return memberId;
	}
	/**
	* @return 
	*/
	public String getMemberLastName()
	{
		fetch();
		return memberLastName;
	}
	/**
	* @return 
	*/
	public String getMemberMiddleName()
	{
		fetch();
		return memberMiddleName;
	}
	/**
	* @param string
	*/
	public void setConstellationId(String constellationId)
	{
		this.constellationId = constellationId;
	}
	/**
	* @param string
	*/
	public void setMemberFirstName(String memberFirstName)
	{
		this.memberFirstName = memberFirstName;
	}
	/**
	* @param string
	*/
	public void setMemberId(String memberId)
	{
		this.memberId = memberId;
	}
	/**
	* @param string
	*/
	public void setMemberLastName(String memberLastName)
	{
		this.memberLastName = memberLastName;
	}
	/**
	* @param string
	*/
	public void setMemberMiddleName(String memberMiddleName)
	{
		this.memberMiddleName = memberMiddleName;
	}
	
	   /**
	   * Set the reference value to class :: pd.juvenilecase.family.FamilyMember
	   */
	   public void setJuvenileNum(String aJuvenileNum) {
		   this.juvenileNum = aJuvenileNum;
	   }
	   /**
	   * Get the reference value to class :: pd.juvenilecase.family.FamilyMember
	   */
	   public String getJuvenileNum() {
		   fetch();
		   return juvenileNum;
	   }
	
	/**
	 * @return Returns the memberAddressId.
	 */
	public String getMemberAddressId() {
		fetch();
		return memberAddressId;
	}
	/**
	 * @param memberAddressId The memberAddressId to set.
	 */
	public void setMemberAddressId(String memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
	
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param relationshipToJuvenileId The relationship to juvenile id.
	*/
	public void setRelationshipToJuvenileId(String relationshipToJuvenileId) {
		this.relationshipToJuvenileId = relationshipToJuvenileId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return The relationship to juvenile id.
	*/
	public String getRelationshipToJuvenileId() {
		fetch();
		return relationshipToJuvenileId;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initRelationshipToJuvenile() {
		if (relationshipToJuvenile == null) {
			relationshipToJuvenile =
				(Code) new mojo.km.persistence.Reference(relationshipToJuvenileId, Code.class, "JUVENILE_RELATIONSHIP").getObject();
		}
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	* @return The relationship to juvenile.
	*/
	public Code getRelationshipToJuvenile() {
		fetch();
		initRelationshipToJuvenile();
		return relationshipToJuvenile;
	}
	/**
	* set the type reference for class member relationshipToJuvenile
	* @param relationshipToJuvenile The relationship to juvenile.
	*/
	public void setRelationshipToJuvenile(Code relationshipToJuvenile) {
		setRelationshipToJuvenileId("" + relationshipToJuvenile.getOID());
		relationshipToJuvenile.setContext("JUVENILE_RELATIONSHIP");
		this.relationshipToJuvenile = (Code) new mojo.km.persistence.Reference(relationshipToJuvenile).getObject();
	}
	public String getValidated() {
		fetch();
		return validated;
	}
	public void setValidated(String validated) {
		this.validated = validated;
	}
	
	public String getValidatedDetail() {
		
		String value = "";
		
		if (validated != null) {
			if (validated.equalsIgnoreCase(PDJuvenileFamilyConstants.VALIDLOCATION)) {
				value = PDJuvenileFamilyConstants.VALIDLOCATIONDETAIL;
			} else if (validated.equalsIgnoreCase(PDJuvenileFamilyConstants.INVALIDLOCATION)) {
				value = PDJuvenileFamilyConstants.INVALIDLOCATIONDETAIL;
			} else if (validated.equalsIgnoreCase(PDJuvenileFamilyConstants.UNPROCESSEDLOCATION)) {
				value = PDJuvenileFamilyConstants.UNPROCESSEDLOCATIONDETAIL;
			} else {
				value = "";
			}
			
		}
		
		return value;
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
}

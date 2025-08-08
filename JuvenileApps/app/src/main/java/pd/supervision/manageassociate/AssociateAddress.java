package pd.supervision.manageassociate;

import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @roseuid 45E5EB650222
 */
public class AssociateAddress extends PersistentObject
{
	private String associateAddressId;
	private String streetNumber;
	private String streetName;
	private String streetTypeId;
	private String aptOrSuite;
	private String city;
	private String stateId;
	private String zipCode;
	private String extendedZipCode;
	private String complexName;
	private String countyId;
	private String addressTypeId;
	private boolean isPrimaryResidenceAddress;
	/**
	 * Properties for streetType
	 */
	private Code streetType = null;
	/**
	 * Properties for county
	 */
	private Code county = null;
	/**
	 * Properties for state
	 */
	private Code state = null;
	/**
	 * Properties for addressType
	 */
	private Code addressType = null;

	/**
	 * 
	 * @roseuid 45E5EB650222
	 */
	public AssociateAddress()
	{
	}

	/**
	 * 
	 * @roseuid 45DCB3A302C1
	 */
	public void bind()
	{
		markModified();
	}

	/**
	 * @roseuid 45DCB3A302C2
	 */
	public void find()
	{
		fetch();
	}
	
	/**
	 * @param oid
	 * @return pd.supervision.manageassociate.AssociateAddress
	 * @roseuid 45DCB3A302C2
	 */
	static public AssociateAddress find(String oid)
	{
		return (AssociateAddress) new Home().find(oid, AssociateAddress.class);
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @roseuid 45E73E960290
	 * @param streetTypeId
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
	 * Get the reference value to class :: pd.codetable.Code
	 * @roseuid 45E73E9602AF
	 * @return java.lang.String
	 */
	public String getStreetTypeId()
	{
		fetch();
		return streetTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 * @roseuid 45E73E9602BF
	 */
	private void initStreetType()
	{
		if (streetType == null)
		{
			streetType = (Code) new mojo.km.persistence.Reference(streetTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @roseuid 45E73E96031C
	 * @param countyId
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
	 * @roseuid 45E73E96032D
	 * @return java.lang.String
	 */
	public String getCountyId()
	{
		fetch();
		return countyId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 * @roseuid 45E73E96033C
	 */
	private void initCounty()
	{
		if (county == null)
		{
			county = (Code) new mojo.km.persistence.Reference(countyId, Code.class)
					.getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @roseuid 45E73E96039A
	 * @param stateId
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
	 * @roseuid 45E73E9603B9
	 * @return java.lang.String
	 */
	public String getStateId()
	{
		fetch();
		return stateId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 * @roseuid 45E73E9603C8
	 */
	private void initState()
	{
		if (state == null)
		{
			state = (Code) new mojo.km.persistence.Reference(stateId, Code.class).getObject();
		}
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 * @roseuid 45E73E97003F
	 * @param addressTypeId
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
	 * @roseuid 45E73E97005D
	 * @return java.lang.String
	 */
	public String getAddressTypeId()
	{
		fetch();
		return addressTypeId;
	}

	/**
	 * Initialize class relationship to class pd.codetable.Code
	 * @roseuid 45E73E97006D
	 */
	private void initAddressType()
	{
		if (addressType == null)
		{
			addressType = (Code) new mojo.km.persistence.Reference(addressTypeId, Code.class)
					.getObject();
		}
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @roseuid 45E7413B00FA
	 * @return pd.codetable.Code
	 */
	public Code getStreetType()
	{
		fetch();
		initStreetType();
		return streetType;
	}

	/**
	 * set the type reference for class member streetType
	 * @roseuid 45E7413B0128
	 * @param streetType
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
		this.streetType = (Code) new mojo.km.persistence.Reference(streetType).getObject();
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @roseuid 45E7413B0186
	 * @return pd.codetable.Code
	 */
	public Code getCounty()
	{
		fetch();
		initCounty();
		return county;
	}

	/**
	 * set the type reference for class member county
	 * @roseuid 45E7413B01B5
	 * @param county
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
		this.county = (Code) new mojo.km.persistence.Reference(county).getObject();
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @roseuid 45E7413B0213
	 * @return pd.codetable.Code
	 */
	public Code getState()
	{
		fetch();
		initState();
		return state;
	}

	/**
	 * set the type reference for class member state
	 * @roseuid 45E7413B0232
	 * @param state
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
		this.state = (Code) new mojo.km.persistence.Reference(state).getObject();
	}

	/**
	 * Gets referenced type pd.codetable.Code
	 * @roseuid 45E7413B02AF
	 * @return pd.codetable.Code
	 */
	public Code getAddressType()
	{
		fetch();
		initAddressType();
		return addressType;
	}

	/**
	 * set the type reference for class member addressType
	 * @roseuid 45E7413B02CE
	 * @param addressType
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
		this.addressType = (Code) new mojo.km.persistence.Reference(addressType).getObject();
	}

	/**
	 * @return Returns the aptOrSuite.
	 */
	public String getAptOrSuite()
	{
		return aptOrSuite;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * @return Returns the complexName.
	 */
	public String getComplexName()
	{
		return complexName;
	}

	/**
	 * @return Returns the streetName.
	 */
	public String getStreetName()
	{
		return streetName;
	}

	/**
	 * @return Returns the streetNumber.
	 */
	public String getStreetNumber()
	{
		return streetNumber;
	}

	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @return Returns the extendedZipCode.
	 */
	public String getExtendedZipCode()
	{
		return extendedZipCode;
	}

	/**
	 * @param aptOrSuite The aptOrSuite to set.
	 */
	public void setAptOrSuite(String aptOrSuite)
	{
		this.aptOrSuite = aptOrSuite;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @param complexName The complexName to set.
	 */
	public void setComplexName(String complexName)
	{
		this.complexName = complexName;
	}

	/**
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * @param streetNumber The streetNumber to set.
	 */
	public void setStreetNumber(String streetNumber)
	{
		this.streetNumber = streetNumber;
	}

	/**
	 * @param zipCode The zipCode to set.
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * @param extendedZipCode The extendedZipCode to set.
	 */
	public void setExtendedZipCode(String extendedZipCode)
	{
		this.extendedZipCode = extendedZipCode;
	}

	/**
	 * @return Returns the isPrimaryResidenceAddress.
	 */
	public boolean getIsPrimaryResidenceAddress()
	{
		return isPrimaryResidenceAddress;
	}

	/**
	 * @param isPrimaryResidenceAddress The isPrimaryResidenceAddress to set.
	 */
	public void setIsPrimaryResidenceAddress(boolean isPrimaryResidenceAddress)
	{
		this.isPrimaryResidenceAddress = isPrimaryResidenceAddress;
	}

	/**
	 * @return Returns the associateAddressId.
	 */
	public String getAssociateAddressId()
	{
		return associateAddressId;
	}

	/**
	 * @param associateAddressId The associateAddressId to set.
	 */
	public void setAssociateAddressId(String associateAddressId)
	{
		this.associateAddressId = associateAddressId;
	}
}

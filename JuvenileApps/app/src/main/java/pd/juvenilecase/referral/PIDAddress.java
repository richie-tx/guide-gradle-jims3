package pd.juvenilecase.referral;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;

/**
 * 
 * @roseuid 467FBB0F001E
 */
public class PIDAddress extends PersistentObject
{
	private String phoneNum;
	private String address;
	private String aptNum;
	private String city;
	private String name;
	private String zipCode;
	private String barNum;
	/**
	 * Properties for state
	 */
	private Code state = null;
	private String stateId;

	/**
	 * 
	 * @roseuid 467FBB0F001E
	 */
	public PIDAddress()
	{
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress()
	{
		fetch();
		return address;
	}

	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * @return Returns the aptNum.
	 */
	public String getAptNum()
	{
		fetch();
		return aptNum;
	}

	/**
	 * @param aptNum The aptNum to set.
	 */
	public void setAptNum(String aptNum)
	{
		this.aptNum = aptNum;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity()
	{
		fetch();
		return city;
	}

	/**
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName()
	{
		fetch();
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}

	/**
	 * @param phoneNum The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * @return Returns the state.
	 */
	public Code getState()
	{
		fetch();
		initState();
		return state;
	}

	/**
	 * @return Returns the zipCode.
	 */
	public String getZipCode()
	{
		fetch();
		return zipCode;
	}

	/**
	 * @param zipCode The zipCode to set.
	 */
	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * Set the reference value to class :: pd.codetable.Code
	 */
	public void setStateId(String stateId)
	{
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
			state = (Code) new mojo.km.persistence.Reference(stateId, Code.class).getObject();
		}
	}

	/**
	 * set the type reference for class member state
	 */
	public void setState(Code state)
	{
		setStateId("" + state.getOID());
		this.state = (Code) new mojo.km.persistence.Reference(state).getObject();
	}

	/**
	 * 
	 * @return Returns the barNum.
	 */
	public String getBarNum()
	{
		fetch();
		return barNum;
	}

	/**
	 * 
	 * @param barNum The barNum to set.
	 */
	public void setBarNum(String barNum)
	{
		this.barNum = barNum;
	}

	/**
	 * @return PIDAddress
	 * @param barNum
	 * @roseuid 4177C29D03A9
	 */
	static public PIDAddress find(String oid)
	{
		IHome home = new Home();
		return (PIDAddress) home.find(oid, PIDAddress.class);
	}
}

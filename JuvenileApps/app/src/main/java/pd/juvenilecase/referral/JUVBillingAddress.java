package pd.juvenilecase.referral;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import naming.PDCodeTableConstants;
import pd.codetable.Code;

/**
 * 
 * @roseuid 467FBB08003D
 */
public class JUVBillingAddress extends PersistentObject
{
	private String aptNum;
	private String city;
	private String payorName;
	private String phoneNum;
	private String streetNum;
	private String streetName;
	private String zipCode;
	/**
	 * Properties for state
	 */
	private Code state = null;
	private String stateId;

	/**
	 * 
	 * @roseuid 467FBB08003D
	 */
	public JUVBillingAddress()
	{
	}

	/**
	 * 
	 * @return Returns the aptNum.
	 */
	public String getAptNum()
	{
		fetch();
		return aptNum;
	}

	/**
	 * 
	 * @param aptNum The aptNum to set.
	 */
	public void setAptNum(String aptNum)
	{
		this.aptNum = aptNum;
	}

	/**
	 * 
	 * @return Returns the city.
	 */
	public String getCity()
	{
		fetch();
		return city;
	}

	/**
	 * 
	 * @param city The city to set.
	 */
	public void setCity(String city)
	{
		this.city = city;
	}

	/**
	 * 
	 * @return Returns the payorName.
	 */
	public String getPayorName()
	{
		fetch();
		return payorName;
	}

	/**
	 * 
	 * @param payorName The payorName to set.
	 */
	public void setPayorName(String payorName)
	{
		this.payorName = payorName;
	}

	/**
	 * 
	 * @return Returns the phoneNum.
	 */
	public String getPhoneNum()
	{
		fetch();
		return phoneNum;
	}

	/**
	 * 
	 * @param phoneNum The phoneNum to set.
	 */
	public void setPhoneNum(String phoneNum)
	{
		this.phoneNum = phoneNum;
	}

	/**
	 * 
	 * @return Returns the state.
	 */
	public Code getState()
	{
		initState();
		return state;
	}

	/**
	 * 
	 * @return Returns the streetName.
	 */
	public String getStreetName()
	{
		fetch();
		return streetName;
	}

	/**
	 * 
	 * @param streetName The streetName to set.
	 */
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}

	/**
	 * 
	 * @return Returns the streetNum.
	 */
	public String getStreetNum()
	{
		fetch();
		return streetNum;
	}

	/**
	 * 
	 * @param streetNum The streetNum to set.
	 */
	public void setStreetNum(String streetNum)
	{
		this.streetNum = streetNum;
	}

	/**
	 * 
	 * @return Returns the zipCode.
	 */
	public String getZipCode()
	{
		fetch();
		return zipCode;
	}

	/**
	 * 
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
			state = (Code) new mojo.km.persistence.Reference(stateId, Code.class, PDCodeTableConstants.STATE_ABBR)
            .getObject();
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
	 * @return PIDAddress
	 * @param barNum
	 * @roseuid 4177C29D03A9
	 */
	static public JUVBillingAddress find(String oid)
	{
		IHome home = new Home();
		return (JUVBillingAddress) home.find(oid, JUVBillingAddress.class);
	}
}

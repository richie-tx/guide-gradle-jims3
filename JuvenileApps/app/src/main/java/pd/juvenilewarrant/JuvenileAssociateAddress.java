package pd.juvenilewarrant;

import java.util.Iterator;

import naming.PDCodeTableConstants;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.address.Address;
import pd.codetable.Code;

/**
* @author ryoung
To change the template for this generated type comment go to
Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
*/
public class JuvenileAssociateAddress extends Address
{
	private String countyId;
	private String associateNum;
	private String juvenileAssociateId;
	private boolean badAddress;
	/**
	* Properties for county
	* @referencedType pd.codetable.Code
	* @contextKey COUNTY
	* @detailerDoNotGenerate true
	*/
	private Code county = null;
	/**
	* @roseuid 421A3642000F
	*/
	public JuvenileAssociateAddress()
	{
	}
	/**
	* Access method for the badAddress property.
	* @return the current value of the badAddress property
	*/
	public boolean getBadAddress()
	{
		fetch();
		return badAddress;
	}
	/**
	* Sets the value of the badAddress property.
	* @param aBadAddress the new value of the badAddress property
	*/
	public void setBadAddress(boolean aBadAddress)
	{
		if (this.badAddress != aBadAddress)
		{
			markModified();
		}
		badAddress = aBadAddress;
	}
	/**
	* Access method for the county property.
	* @return the current value of the county property
	*/
	public Code getCounty()
	{
		fetch();
		initCounty();
		return county;
	}
	/**
	* Sets the value of the county property.
	* @param aCounty the new value of the county property
	*/
	public void setCounty(Code aCounty)
	{
		if (this.county == null || !this.county.equals(aCounty))
		{
			markModified();
		}
		county = aCounty;
	}
	/**
	* @roseuid 4208F0D20294
	*/
	public void bind()
	{
		markModified();
	}
	/**
	* @return juvAddress
	* @param juvenileAssociateAddressId
	* @roseuid 4208F24503D8
	*/
	static public JuvenileAssociateAddress find(String addressId)
	{
		JuvenileAssociateAddress juvAddress = null;
		IHome home = new Home();
		juvAddress = (JuvenileAssociateAddress) home.find(addressId, JuvenileAssociateAddress.class);
		return juvAddress;
	}

	/**
	* @return Iterator juvAddresses
	* @param attrName
	* @param attrValue
	*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		Iterator juvAddresses = null;
		juvAddresses = home.findAll(attrName, attrValue, JuvenileAssociateAddress.class);
		return juvAddresses;
	}

	/**
	 * 
	 * @param attrName
	 * @param attrValue
	 * @return Iterator juvAddresses
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, JuvenileAssociateAddress.class);
	}

	/**
	* @roseuid 4208F2460243
	*/
	public void update()
	{
		markModified();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	* @param countyId
	*/
	public void setCountyId(String acountyId)
	{
		if (this.countyId == null || !this.countyId.equals(acountyId))
		{
			markModified();
		}
		county = null;
		this.countyId = acountyId;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	* @return String countyId
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
	* @return String associateNum
	*/
	public String getAssociateNum()
	{
		fetch();
		return associateNum;
	}
	/**
	* @param string
	*/
	public void setAssociateNum(String string)
	{
		if (this.associateNum == null || !this.associateNum.equals(string))
		{
			markModified();
		}
		associateNum = string;
	}
	/**
	* Set the reference value to class :: pd.juvenilewarrant.JuvenileAssociate
	*/
	public void setJuvenileAssociateId(String aJuvenileAssociateId)
	{
		if (this.juvenileAssociateId == null || !this.juvenileAssociateId.equals(aJuvenileAssociateId))
		{
			markModified();
		}
		this.juvenileAssociateId = aJuvenileAssociateId;
	}
	/**
	* Get the reference value to class :: pd.juvenilewarrant.JuvenileAssociate
	*/
	public String getJuvenileAssociateId()
	{
		fetch();
		return juvenileAssociateId;
	}

}
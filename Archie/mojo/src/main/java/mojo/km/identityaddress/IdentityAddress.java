/*
 * Created on Mar 9, 2006
 *
 */
package mojo.km.identityaddress;

import java.util.Collection;
import java.util.Iterator;

import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;

/**
 * @author Jim Fisher
 *
 */
public class IdentityAddress extends PersistentObject
{
	// Static behavior

	public static IdentityAddress find(String anIdentityId)
	{
		IHome home = new Home();
		return (IdentityAddress) home.find(anIdentityId, IdentityAddress.class);
	}

	/**
	 * @param value
	 * @return
	 */
	public static IdentityAddress findByValue(String value)
	{
		IdentityAddress address = null;
		if (value != null)
		{
			IHome home = new Home();
			Iterator i = home.findAll("value", value.toUpperCase(), IdentityAddress.class);
			if (i.hasNext())
			{
				address = (IdentityAddress) i.next();
			}
		}
		return address;
	}

	static public IdentityAddress identityFactory(String value, String type)
	{
		IdentityAddress address = null;

		// null OR blank identities may not be created
		if (value != null && value.trim().equals("") == false)
		{
			value = value.toUpperCase();
			address = IdentityAddress.findByValue(value);
			if (address == null)
			{
				// Create the identity address
				address = new IdentityAddress();
				address.setValue(value);
				address.setType(type);
				IHome home = new Home();
				home.bind(address);
			}
		}
		return address;
	}

	/**
	 * Properties for members
	 * @referencedType mojo.km.identityaddress.IdentityAddress
	 * @detailerDoNotGenerate true
	 */
	private Collection members;

	// Members
	private String type;
	private String value;

	public IdentityAddress()
	{
	}

	/**
	* Clears all mojo.km.identityaddress.IdentityAddress from class relationship collection.
	*/
	public void clearMembers()
	{
		this.initMembers();
		this.members.clear();
	}

	public boolean equals(Object identityObj)
	{
		IdentityAddress comparedIdentity = (IdentityAddress) identityObj;
		boolean equals = this.getValue().equals(comparedIdentity.getValue());
		return equals;
	}

	public Collection getMembers()
	{
		this.initMembers();
		Collection retVal = new java.util.ArrayList();
		Iterator i = members.iterator();
		while (i.hasNext())
		{
			IdentityGroup actual = (IdentityGroup) i.next();
			retVal.add(actual.getChild());
		}
		return retVal;
	}

	/**
	 * @return
	 */
	public String getType()
	{
		fetch();
		return type;
	}

	/**
	 * @return
	 */
	public String getValue()
	{
		fetch();
		return value;
	}

	/**
		* Initialize class relationship implementation for mojo.km.identityaddress.IdentityAddress
		*/
	private void initMembers()
	{
		if (this.members == null)
		{
			if (this.getOID() == null)
			{
				new Home().bind(this);
			}

			this.members = new mojo.km.persistence.ArrayList(IdentityGroup.class, "parentId", "" + getOID());
		}
	}

	public void insertMember(IdentityAddress anObject)
	{
		this.initMembers();
		IdentityGroup actual = new IdentityGroup();
		if (this.getOID() == null)
		{
			new Home().bind(this);
		}
		if (anObject.getOID() == null)
		{
			new Home().bind(anObject);
		}
		actual.setChild(anObject);
		actual.setParent(this);
		this.members.add(anObject);
	}

	/**
	 * @param string
	 */
	public void setType(String string)
	{
		if (this.type == null || !this.type.equals(string))
		{
			markModified();
		}
		this.type = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string)
	{
		if (this.value == null || !this.value.equals(string))
		{
			markModified();
		}
		this.value = string;
	}

	public String toString()
	{
		return this.getValue();
	}

}

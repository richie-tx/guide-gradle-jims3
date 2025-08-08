package pd.contact;
//// no longer in use. Migrated to SM. Refer US #87188. No references in the mapping file.
/*package pd.contact;

import java.util.Collection;
import java.util.Iterator;

import mojo.km.persistence.PersistentObject;
import pd.contact.user.UserProfile;

*//**
 * @author jmcnabb
 *
 * Represents a person with identity data.
 *//*
public class Person extends PersistentObject
{
	private String personId;
	private String ssn;
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	*//**
	* Properties for profiles
	* @referencedType pd.contact.AbstractProfile
	* @detailerDoNotGenerate false
	*//*
	private Collection profiles;

	*//**
	 * @return String
	 *//*
	public String getPersonId()
	{
		fetch();
		return personId;
	}

	*//**
	 * @param personId 
	 *//*
	public void setPersonId(String personId)
	{
		if (this.personId == null || !this.personId.equals(personId))
		{
			markModified();
		}
		this.personId = personId;
	}

	*//**
	 * Access method for the firstName property.
	 * @return the current value of the firstName property
	 *//*
	public java.lang.String getFirstName()
	{
		fetch();
		return firstName;
	}
	*//**
	 * Sets the value of the firstName property.
	 * @param aFirstName the new value of the firstName property
	 *//*
	public void setFirstName(java.lang.String aFirstName)
	{
		if (this.firstName == null || !this.firstName.equals(aFirstName))
		{
			markModified();
		}
		firstName = aFirstName;
	}

	*//**
	 * Access method for the lastName property.
	 * @return the current value of the lastName property
	 *//*
	public java.lang.String getLastName()
	{
		fetch();
		return lastName;
	}
	*//**
	 * Sets the value of the lastName property.
	 * @param aLastName the new value of the lastName property
	 *//*
	public void setLastName(java.lang.String aLastName)
	{
		if (this.lastName == null || !this.lastName.equals(aLastName))
		{
			markModified();
		}
		lastName = aLastName;
	}

	*//**
	 * Access method for the middleName property.
	 * @return the current value of the middleName property
	 *//*
	public java.lang.String getMiddleName()
	{
		fetch();
		return middleName;
	}

	*//**
	 * Sets the value of the middleName property.
	 * @param aMiddleName the new value of the middleName property
	 *//*
	public void setMiddleName(java.lang.String aMiddleName)
	{
		if (this.middleName == null || !this.middleName.equals(aMiddleName))
		{
			markModified();
		}
		middleName = aMiddleName;
	}

	*//**
	 * Access method for the title property.
	 * @return the current value of the title property
	 *//*
	public java.lang.String getTitle()
	{
		fetch();
		return title;
	}

	*//**
	 * Sets the value of the title property.
	 * @param aTitle the new value of the title property
	 *//*
	public void setTitle(java.lang.String aTitle)
	{
		if (this.title == null || !this.title.equals(aTitle))
		{
			markModified();
		}
		title = aTitle;
	}

	*//**
	 * Access method for the ssn property.
	 * @return the current value of the ssn property
	 *//*
	public java.lang.String getSsn()
	{
		fetch();
		return ssn;
	}

	*//**
	 * Sets the value of the ssn property.
	 * @param aTitle the new value of the ssn property
	 *//*
	public void setSsn(java.lang.String anSsn)
	{
		if (this.ssn == null || !this.ssn.equals(anSsn))
		{
			markModified();
		}
		ssn = anSsn;
	}

	*//**
	 * returns a collection of pd.contact.AbstractProfile
	 *//*
	public java.util.Collection getProfiles()
	{
		initProfiles();
		return profiles;
	}

	*//**
	 * Initialize class relationship implementation for pd.contact.AbstractProfile
	 *//*
	private void initProfiles()
	{
		if (profiles == null)
		{
			if (this.getOID() == null)
			{
				new mojo.km.persistence.Home().bind(this);
			}
			profiles =
				new mojo.km.persistence.ArrayList(pd.contact.AbstractProfile.class, "officerProfileId", "" + getOID());
		}
	}

	*//**
	 * insert a pd.contact.AbstractProfile into class relationship collection.
	 *//*
	public void insertProfile(pd.contact.AbstractProfile anObject)
	{
		initProfiles();
		profiles.add(anObject);
	}
	
	public UserProfile getUserProfile()
	{
//		for (Iterator i = profiles.iterator(); i.hasNext();)
//		{
//			AbstractProfile aProfile = (UserProfile)i.next();
//			if (aProfile.isUserProfile())
//			{
//				return (UserProfile)aProfile;
//			}
//		}
		return null;
	}
}
*/
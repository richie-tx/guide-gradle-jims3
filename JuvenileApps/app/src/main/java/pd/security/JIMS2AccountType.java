package pd.security;

import java.util.Iterator;

import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.persistence.Home;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

/**
* @roseuid 4399CD6D0090
*/
public class JIMS2AccountType extends PersistentObject
{
	private String JIMS2AccountId;
	private String logonId;
	private String userAccountOID;
	/**
	* Properties for userAccountType
	* @referencedType pd.codetable.Code
	* @contextKey JIMS2_ACCOUNT_TYPE
	* @detailerDoNotGenerate true
	*/
	private Code userAccountType = null;
	private String userAccountTypeId;
	/**
	* Properties for user
	*/
	private UserProfile userProfile = null;
	/**
	* @roseuid 4399CD6D0090
	*/
	public JIMS2AccountType()
	{
	}

	/**
	* Get the reference value to class :: pd.security.JIMS2Account
	*/
	public String getJIMS2AccountId()
	{
		fetch();
		return JIMS2AccountId;
	}
	/**
	* Get the reference value to class :: pd.security.JIMSGenericAccount
	*/
	public String getLogonId()
	{
		fetch();
		return logonId;
	}
	/**
	 * @return
	 */
	public String getUserAccountOID()
	{
		fetch();
		return userAccountOID;
	}
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getUserAccountType()
	{
		initUserAccountType();
		return userAccountType;
	}
	/**
	* Get the reference value to class :: pd.codetable.Code
	*/
	public String getUserAccountTypeId()
	{
		fetch();
		return userAccountTypeId;
	}
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getUserProfile()
	{
		initUserProfile();
		return userProfile;
	}
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initUserAccountType()
	{
		if (userAccountType == null)
		{
			userAccountType =
				(Code) new mojo
					.km
					.persistence
					.Reference(userAccountTypeId, Code.class, "JIMS2_ACCOUNT_TYPE")
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initUserProfile()
	{
		if (userProfile == null)
		{
		//87191
			userProfile = UserProfileHelper.getUserProfileFromJUCode(logonId);//(UserProfile) new mojo.km.persistence.Reference(logonId, UserProfile.class).getObject();
		}
	}
	/**
	* Set the reference value to class :: pd.security.JIMS2Account
	*/
	public void setJIMS2AccountId(String JIMS2AccountId)
	{
		if (this.JIMS2AccountId == null || !this.JIMS2AccountId.equals(JIMS2AccountId))
		{
			markModified();
		}
		this.JIMS2AccountId = JIMS2AccountId;
	}
	/**
	* Set the reference value to class :: pd.security.JIMSGenericAccount
	*/
	public void setLogonId(String userId)
	{
		if (this.logonId == null || !this.logonId.equals(userId))
		{
			markModified();
		}
		userProfile = null;
		this.logonId = userId;
	}

	/**
	 * @param string
	 */
	public void setUserAccountOID(String userAccountOID)
	{
		if (this.userAccountOID == null || !this.userAccountOID.equals(userAccountOID))
		{
			markModified();
		}
		this.userAccountOID = userAccountOID;
	}
	/**
	* set the type reference for class member userAccountType
	*/
	public void setUserAccountType(Code userAccountType)
	{
		if (this.userAccountType == null || !this.userAccountType.equals(userAccountType))
		{
			markModified();
		}
		if (userAccountType.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(userAccountType);
		}
		setUserAccountTypeId("" + userAccountType.getOID());
		this.userAccountType = (Code) new mojo.km.persistence.Reference(userAccountType).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.Code
	*/
	public void setUserAccountTypeId(String userAccountTypeId)
	{
		if (this.userAccountTypeId == null || !this.userAccountTypeId.equals(userAccountTypeId))
		{
			markModified();
		}
		userAccountType = null;
		this.userAccountTypeId = userAccountTypeId;
	}
	/**
	* set the type reference for class member userProfile
	*/
	//87191
/*	public void setUserProfile(UserProfile userProfile)
	{
		if (this.userProfile == null || !this.userProfile.equals(userProfile))
		{
			markModified();
		}
		if (userProfile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(userProfile);
		}
		setLogonId("" + userProfile.getOID());
		this.userProfile = (UserProfile) new mojo.km.persistence.Reference(userProfile).getObject();
	}*/
	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return (Iterator) home.findAll(attrName, attrValue, JIMS2AccountType.class);
	}

	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		Iterator iter = home.findAll(event, JIMS2AccountType.class);
		return iter;
	}
	static public JIMS2AccountType find(String JIMS2AccountTypeId)
	{
		JIMS2AccountType jims2Account = null;
		IHome home = new Home();
		jims2Account = (JIMS2AccountType) home.find(JIMS2AccountTypeId, JIMS2Account.class);
		return jims2Account;
	}
	/**
	* @roseuid 42E65EA6010F
	*/
	static public Iterator findAll()
	{
		IHome home = new Home();
		Iterator iter = home.findAll(JIMS2AccountType.class);
		return iter;
	}
}
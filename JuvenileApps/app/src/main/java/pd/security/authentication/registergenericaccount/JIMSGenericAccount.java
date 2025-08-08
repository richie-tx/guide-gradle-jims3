package pd.security.authentication.registergenericaccount;

import java.util.Iterator;

import messaging.registergenericaccount.UpdateJIMSGenericAccountEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.Code;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

/**
* @roseuid 4399E4160242
*/
public class JIMSGenericAccount extends PersistentObject
{
	private String logonId;
	private String password;
	private String statusId;
	private String jimsGenericId;
	/**
	* Properties for userProfile
	*/
	private UserProfile userProfile = null;
	
	/**
	* Properties for eventStatus
	* @referencedType pd.codetable.Code
	* @contextKey SERVICEEVENT_STATUS
	* @detailerDoNotGenerate true
	*/
	private Code status = null;
	
	/**
	* @roseuid 4399E4160242
	*/
	public JIMSGenericAccount()
	{
	}

	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getLogonId()
	{
		fetch();
		return logonId;
	}
	/**
	 * @return
	 */
	public String getPassword()
	{
		fetch();
		return password;
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
	* Initialize class relationship to class pd.contact.user.UserProfile
	*/
	private void initUserProfile()
	{
		if (userProfile == null)
		{
		//87191
			userProfile = UserProfileHelper.getUserProfileFromJUCode(logonId);
				/*(pd.contact.user.UserProfile) new mojo
					.km
					.persistence
					.Reference(logonId, pd.contact.user.UserProfile.class)
					.getObject();*/
		}
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setLogonId(String userProfileId)
	{
		if (this.logonId == null || !this.logonId.equals(userProfileId))
		{
			markModified();
		}
		userProfile = null;
		this.logonId = userProfileId;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password)
	{
		if (this.password == null || !this.password.equals(password))
		{
			markModified();
		}
		this.password = password;
	}
	/**
	* set the type reference for class member userProfile
	*/
	//87191
	/*public void setUserProfile(pd.contact.user.UserProfile userProfile)
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
		this.userProfile = (pd.contact.user.UserProfile) new mojo.km.persistence.Reference(userProfile).getObject();
	}*/
	/**
	 * @param string
	 * @return
	 */

	static public JIMSGenericAccount find(String logonId)
	{
		JIMSGenericAccount jimsGenericAccount = null;
		IHome home = new Home();
		Iterator iter = (Iterator) home.findAll("logonId", logonId, JIMSGenericAccount.class);
		while (iter.hasNext())
		{
			jimsGenericAccount = (JIMSGenericAccount) iter.next();
		}
		return jimsGenericAccount;
	}
	
	/**
	 * @param string
	 * @return
	 */

	static public JIMSGenericAccount findByOid(String genericAccountId)
	{
		JIMSGenericAccount jimsGenericAccount = null;
		IHome home = new Home();
		return (JIMSGenericAccount) home.find(genericAccountId, JIMSGenericAccount.class);
	}
	
	/**
	 * @param attrName
	 * @param attrValue
	 * @return Iterator of JIMSGenericAccount
	 */

	static public Iterator findAll(String attrName, String attrValue)
	{
		IHome home = new Home();
		return home.findAll(attrName, attrValue, JIMSGenericAccount.class);
	}
	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		fetch();
		return statusId;
	}
	/**
	 * @param statusId The statusId to set.
	 */
	public void setStatusId(String statusId) {
		if (this.statusId == null || !this.statusId.equals(statusId))
		{
			markModified();
		}
		this.statusId = statusId;
	}
	
	/**
	* Gets referenced type pd.codetable.Code
	*/
	public Code getStatus() {
		initStatus();
		return status;
	}
	
	/**
	* Initialize class relationship to class pd.codetable.Code
	*/
	private void initStatus() {
		if (status == null) {
			status = (Code) new mojo.km.persistence.Reference(statusId, Code.class, "OFFICER_STATUS").getObject();
		}
	}
	/**
	 * @return Returns the jimsGenericId.
	 */
	public String getJimsGenericId() {
		fetch();
		return jimsGenericId;
	}
	/**
	 * @param jimsGenericId The jimsGenericId to set.
	 */
	public void setJimsGenericId(String jimsGenericId) {
		if(this.jimsGenericId == null || !this.jimsGenericId.equals(jimsGenericId))
		{
			markModified();
		}
		this.setOID(jimsGenericId);
		this.jimsGenericId = jimsGenericId;
	}

	/**
	 * @param event
	 */
	public void setJimsGenericAccount(UpdateJIMSGenericAccountEvent event) {
		this.setLogonId(event.getLogonId());
		this.setPassword(event.getPassword());	
		this.setStatusId(event.getStatusId());
	}
}

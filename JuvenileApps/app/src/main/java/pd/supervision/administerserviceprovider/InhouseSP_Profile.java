package pd.supervision.administerserviceprovider;
import java.util.Iterator;

import messaging.administerserviceprovider.CreateServiceProviderContactEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.contact.user.UserProfile;
import pd.transferobjects.helper.UserProfileHelper;

public class InhouseSP_Profile extends SP_Profile
{
	/**
	* Properties for genericUsers
	* @referencedType pd.contact.user.UserProfile
	* @detailerDoNotGenerate true
	*/
	private UserProfile userProfile = null;
	private String logonId;
	/**
	* @roseuid 447EFA4C0221
	*/
	public InhouseSP_Profile()
	{
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setLogonId(String logonId)
	{
		if (this.logonId == null || !this.logonId.equals(logonId))
		{
			markModified();
		}
		userProfile = null;
		this.logonId = logonId;
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
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getUserProfile()
	{
		initUserProfile();
		return userProfile;
	}
	/**
	* set the type reference for class member userProfile
	*/
	//87191
/*	public void setUserProfile(pd.contact.user.UserProfile userProfile)
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
	* @return java.util.Iterator
	* @param logonId
	* @roseuid 4177C29D03A9
	*/
	static public Iterator findAll(String attrName, String attrValue) {
		IHome home = new Home();
		Iterator serviceProviderProfiles = null;
		serviceProviderProfiles = home.findAll(attrName, attrValue, InhouseSP_Profile.class);
		return serviceProviderProfiles;
	}
	
	static public Iterator findAllByNumericAttribute(String attrName, String attrValue) {
		IHome home = new Home();
		return home.findAll(attrName, new Integer(attrValue), InhouseSP_Profile.class);
	}
			
	/**
	* @param CreateServiceProviderContactEvent
	*/
	public void setInhouseSP_Profile(CreateServiceProviderContactEvent spProfileEvent, boolean update) {
		
		this.setInHouse(spProfileEvent.isInHouse());		
		this.setFirstName(spProfileEvent.getFullName().getFirstName());
		this.setMiddleName(spProfileEvent.getFullName().getMiddleName());
		this.setLastName(spProfileEvent.getFullName().getLastName());
		//this.setNotes(spProfileEvent.getNotes()); //88615
		this.setIsAdminContact(spProfileEvent.getIsAdminContact());
		this.setLogonId(spProfileEvent.getLogonId());
		this.setServiceProviderId(spProfileEvent.getServiceProviderId());
		if(spProfileEvent.getWorkPhone().getAreaCode()!=null&&spProfileEvent.getWorkPhone().getPrefix()!=null&&spProfileEvent.getWorkPhone().getLast4Digit()!=null)
		{
        		this.setPhoneNum(spProfileEvent.getWorkPhone().getAreaCode()+spProfileEvent.getWorkPhone().getPrefix()+spProfileEvent.getWorkPhone().getLast4Digit());
        		this.setWorkPhoneNum(spProfileEvent.getWorkPhone().getAreaCode()+spProfileEvent.getWorkPhone().getPrefix()+spProfileEvent.getWorkPhone().getLast4Digit());
		}
		else
		{
		    this.setPhoneNum(null);
    		    this.setWorkPhoneNum(null);
		}
		//this.setExtnNum(spProfileEvent.getWorkPhone().getExt());
		this.setEmail(spProfileEvent.getEmail());
		//this.setCellPhone(spProfileEvent.getCellPhone().getAreaCode()+spProfileEvent.getCellPhone().getPrefix()+spProfileEvent.getCellPhone().getLast4Digit());
		//this.setPager(spProfileEvent.getPager().getAreaCode()+spProfileEvent.getPager().getPrefix()+spProfileEvent.getPager().getLast4Digit());
		//this.setFaxNum(spProfileEvent.getFax().getAreaCode()+spProfileEvent.getFax().getPrefix()+spProfileEvent.getFax().getLast4Digit());
		//this.setPrefix(spProfileEvent.getFullName().getPrefix());
		//this.setSuffix(spProfileEvent.getFullName().getSuffix());
		//this.setJobTitle(spProfileEvent.getJobTitle());
		this.setStatusId(spProfileEvent.getStatusId());
		if(update) {
			this.setEmployeeId(spProfileEvent.getJuvServProvProfId());
		}
//		this.setCreateUserID(spProfileEvent.getUserID());
	}
	
	public void createOID() {
		markModified();
		IHome home = new Home();
		home.bind(this);
	}
	
	static public SP_Profile find(String serviceProviderProfileId) {
		IHome home = new Home();
		return (SP_Profile) home.find(serviceProviderProfileId, InhouseSP_Profile.class);
	}
	public void updateInHouseProfileStatus(CreateServiceProviderContactEvent createEvent) {
		this.setStatusId(createEvent.getStatusId());
	}
	static public Iterator findAll(IEvent event) {
		IHome home = new Home();
		return (Iterator) home.findAll(event, InhouseSP_Profile.class);
	}
	
}

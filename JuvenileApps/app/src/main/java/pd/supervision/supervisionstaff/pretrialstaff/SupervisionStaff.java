package pd.supervision.supervisionstaff.pretrialstaff;

import java.util.Iterator;

import messaging.supervision.reply.SupervisionStaffResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.codetable.supervision.SupervisionCode;
import pd.contact.user.UserProfile;
import pd.supervision.Court;

/**
* @roseuid 43BD3E3200E3
*/
public class SupervisionStaff extends PersistentObject
{
	private boolean isSupervisor;
	/**
	* Properties for primaryCourtAssignment
	*/
	private Court primaryCourtAssignment = null;
	/**
	* Properties for supervisionStaffDivisionUnitRels
	* @referencedType pd.codetable.supervision.SupervisionStaffDivisionUnitRel
	*/
	private String primaryCourtAssignmentId;
	private String userProfileId;
	/**
	* Properties for backupCourtAssignment
	*/
	private Court backupCourtAssignment = null;
	private String jobTitleId;
	private SupervisionStaff supervisionStaff;
	/**
	* Properties for orderPresentor
	*/
	private SupervisionStaff orderPresentor;
	/**
	* Properties for userProfile
	*/
	private UserProfile userProfile = null;
	private String backupCourtAssignmentId;
	/**
	* Properties for jobTitle
	* @referencedType pd.codetable.supervision.SupervisionCode
	* @contextKey NEW_CONTEXT_KEY_NEEDED
	* @detailerDoNotGenerate true
	*/
	private SupervisionCode jobTitle = null;
	/**
	* @roseuid 43BD3E3200E3
	*/
	public SupervisionStaff()
	{
	}
	/**
	* Gets referenced type pd.supervision.Court
	*/
	public Court getBackupCourtAssignment()
	{
		fetch();
		initBackupCourtAssignment();
		return backupCourtAssignment;
	}
	/**
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getBackupCourtAssignmentId()
	{
		fetch();
		return backupCourtAssignmentId;
	}
	/**
	* @param b
	*/
	public void getIsSupervisior(boolean b)
	{
		fetch();
		isSupervisor = b;
	}
	/**
	* @return 
	*/
	public boolean getIsSupervisor()
	{
		fetch();
		return isSupervisor;
	}
	/**
	* Gets referenced type pd.codetable.supervision.SupervisionCode
	*/
	public SupervisionCode getJobTitle()
	{
		fetch();
		initJobTitle();
		return jobTitle;
	}
	/**
	* Get the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public String getJobTitleId()
	{
		fetch();
		return jobTitleId;
	}
	/**
	* Gets referenced type pd.supervision.Court
	*/
	public Court getPrimaryCourtAssignment()
	{
		fetch();
		initPrimaryCourtAssignment();
		return primaryCourtAssignment;
	}
	/**
	* Get the reference value to class :: pd.supervision.Court
	*/
	public String getPrimaryCourtAssignmentId()
	{
		fetch();
		return primaryCourtAssignmentId;
	}
	/**
	* returns a collection of pd.codetable.supervision.SupervisionStaffDivisionUnitRel
	*/
	/**
	* Gets referenced type pd.contact.user.UserProfile
	*/
	public UserProfile getUserProfile()
	{
		fetch();
		initUserProfile();
		return userProfile;
	}
	/**
	* Get the reference value to class :: pd.contact.user.UserProfile
	*/
	public String getUserProfileId()
	{
		fetch();
		return userProfileId;
	}
	/**
	* Initialize class relationship to class pd.supervision.Court
	*/
	private void initBackupCourtAssignment()
	{
		if (backupCourtAssignment == null)
		{
			backupCourtAssignment =
				(Court) new mojo
					.km
					.persistence
					.Reference(backupCourtAssignmentId, Court.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.codetable.supervision.SupervisionCode
	*/
	private void initJobTitle()
	{
		if (jobTitle == null)
		{
			jobTitle =
				(SupervisionCode) new mojo
					.km
					.persistence
					.Reference(jobTitleId, SupervisionCode.class)
					.getObject();
		}
	}
	/**
	* Initialize class relationship to class pd.supervision.Court
	*/
	private void initPrimaryCourtAssignment()
	{
		if (primaryCourtAssignment == null)
		{
			primaryCourtAssignment =
				(Court) new mojo
					.km
					.persistence
					.Reference(primaryCourtAssignmentId, Court.class)
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
			userProfile =
				(UserProfile) new mojo
					.km
					.persistence
					.Reference(userProfileId, UserProfile.class)
					.getObject();
		}
	}
	/**
	* set the type reference for class member backupCourtAssignment
	*/
	public void setBackupCourtAssignment(Court aBackupCourtAssignment)
	{
		if (this.backupCourtAssignment == null || !this.backupCourtAssignment.equals(aBackupCourtAssignment))
		{
			markModified();
		}
		if (aBackupCourtAssignment.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aBackupCourtAssignment);
		}
		setBackupCourtAssignmentId("" + aBackupCourtAssignment.getOID());
		this.backupCourtAssignment =
			(Court) new mojo.km.persistence.Reference(aBackupCourtAssignment).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.Court
	*/
	public void setBackupCourtAssignmentId(String aBackupCourtAssignmentId)
	{
		if (this.backupCourtAssignmentId == null || !this.backupCourtAssignmentId.equals(aBackupCourtAssignmentId))
		{
			markModified();
		}
		backupCourtAssignment = null;
		this.backupCourtAssignmentId = aBackupCourtAssignmentId;
	}
	/**
	* @param b
	*/
	public void setIsSupervisor(boolean b)
	{
		if (this.isSupervisor != b)
		{
			markModified();
		}
		isSupervisor = b;
	}
	/**
	* set the type reference for class member jobTitle
	*/
	public void setJobTitle(SupervisionCode aJobTitle)
	{
		if (this.jobTitle == null || !this.jobTitle.equals(aJobTitle))
		{
			markModified();
		}
		if (aJobTitle.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aJobTitle);
		}
		setJobTitleId("" + aJobTitle.getOID());
		this.jobTitle =
			(SupervisionCode) new mojo.km.persistence.Reference(aJobTitle).getObject();
	}
	/**
	* Set the reference value to class :: pd.codetable.supervision.SupervisionCode
	*/
	public void setJobTitleId(String aJobTitleId)
	{
		if (this.jobTitleId == null || !this.jobTitleId.equals(aJobTitleId))
		{
			markModified();
		}
		jobTitle = null;
		this.jobTitleId = aJobTitleId;
	}
	/**
	* set the type reference for class member primaryCourtAssignment
	*/
	public void setPrimaryCourtAssignment(Court aPrimaryCourtAssignment)
	{
		if (this.primaryCourtAssignment == null || !this.primaryCourtAssignment.equals(aPrimaryCourtAssignment))
		{
			markModified();
		}
		if (aPrimaryCourtAssignment.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aPrimaryCourtAssignment);
		}
		setPrimaryCourtAssignmentId("" + aPrimaryCourtAssignment.getOID());
		this.primaryCourtAssignment =
			(Court) new mojo.km.persistence.Reference(aPrimaryCourtAssignment).getObject();
	}
	/**
	* Set the reference value to class :: pd.supervision.Court
	*/
	public void setPrimaryCourtAssignmentId(String aPrimaryCourtAssignmentId)
	{
		if (this.primaryCourtAssignmentId == null || !this.primaryCourtAssignmentId.equals(aPrimaryCourtAssignmentId))
		{
			markModified();
		}
		primaryCourtAssignment = null;
		this.primaryCourtAssignmentId = aPrimaryCourtAssignmentId;
	}
	/**
	* set the type reference for class member userProfile
	*/
	public void setUserProfile(UserProfile aUserProfile)
	{
		if (this.userProfile == null || !this.userProfile.equals(aUserProfile))
		{
			markModified();
		}
		if (aUserProfile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(aUserProfile);
		}
		setUserProfileId("" + aUserProfile.getOID());
		this.userProfile = (UserProfile) new mojo.km.persistence.Reference(aUserProfile).getObject();
	}
	/**
	* Set the reference value to class :: pd.contact.user.UserProfile
	*/
	public void setUserProfileId(String aUserProfileId)
	{
		if (this.userProfileId == null || !this.userProfileId.equals(aUserProfileId))
		{
			markModified();
		}
		userProfile = null;
		this.userProfileId = aUserProfileId;
	}
	/**
	 * @param event
	 * @return
	 */
	public static Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, SupervisionStaff.class);
	}
	/**
	* @roseuid 43BD3231024E
	* @param oid
	*/
	public static SupervisionStaff find(String oid)
	{
		IHome home = new Home();
		return (SupervisionStaff) home.find(oid, SupervisionStaff.class);
	}
	/**
	 * @return
	 */
	public SupervisionStaffResponseEvent getResponseEvent()
	{
		SupervisionStaffResponseEvent re = new SupervisionStaffResponseEvent();
		UserProfile userProfile = this.getUserProfile();
		if (userProfile != null)
		{
			re.setFirstName(userProfile.getFirstName());
			re.setLastName(userProfile.getLastName());
			re.setMiddleName(userProfile.getMiddleName());
			re.setLogonId(userProfile.getLogonId());
		}
		re.setSupervisionStaffId(this.getOID().toString());
		return re;
	}
}

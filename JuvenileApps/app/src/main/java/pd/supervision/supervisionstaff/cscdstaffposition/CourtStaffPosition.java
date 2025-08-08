package pd.supervision.supervisionstaff.cscdstaffposition;

import pd.supervision.Court;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import mojo.km.persistence.PersistentObject;
import pd.contact.user.UserProfile;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import java.util.Iterator;
import mojo.km.messaging.IEvent;

/**
 * @author cc_rbhat
 */
public class CourtStaffPosition extends PersistentObject
{

	/**
	 * @param userProfileId
	 The userProfileId to set.
	 */
	static public Iterator findAll(IEvent event)
	{
		IHome home = new Home();
		return home.findAll(event, CourtStaffPosition.class);
	}
	/**
	 * Properties for court
	 */
	private Court court = null;
	private String courtId;
	private String jobTitleId;
	private String staffPositionId;
	private String probationOfficerInd;
	/**
	 * Properties for userProfile
	 */
	private UserProfile userProfile = null;
	private String userProfileId;

	/**
	 * Gets referenced type pd.supervision.Court
	 */
	public Court getCourt()
	{
		initCourt();
		return court;
	}

	/**
	 * Get the reference value to class :: pd.supervision.Court
	 */
	public String getCourtId()
	{
		fetch();
		return courtId;
	}

	public String getProbationOfficerInd() {
		
		fetch();
		return probationOfficerInd;
	}

	public void setProbationOfficerInd(String probationOfficerInd) {
		
		this.probationOfficerInd = probationOfficerInd;
	}

	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId()
	{
		fetch();
		return jobTitleId;
	}

	/**
	 * @return Returns the staffPositionId.
	 */
	public String getStaffPositionId()
	{
		fetch();
		return staffPositionId;
	}

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
	private void initCourt()
	{
		if (court == null)
		{
			court = (Court) new mojo.km.persistence.Reference(courtId, Court.class)
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
			userProfile = (UserProfile) new mojo.km.persistence.Reference(userProfileId,
					UserProfile.class).getObject();
		}
	}

	/**
	 * set the type reference for class member court
	 */
	public void setCourt(Court court)
	{
		if (this.court == null || !this.court.equals(court))
		{
			markModified();
		}
		if (court.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(court);
		}
		setCourtId("" + court.getOID());
		this.court = (Court) new mojo.km.persistence.Reference(court).getObject();
	}

	/**
	 * Set the reference value to class :: pd.supervision.Court
	 */
	public void setCourtId(String courtId)
	{
		if (this.courtId == null || !this.courtId.equals(courtId))
		{
			markModified();
		}
		court = null;
		this.courtId = courtId;
	}

	/**
	 * @param jobTitleId
	 The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId)
	{
		if (this.jobTitleId == null || !this.jobTitleId.equals(jobTitleId))
		{
			markModified();
		}
		this.jobTitleId = jobTitleId;
	}

	/**
	 * @param staffPositionId
	 The staffPositionId to set.
	 */
	public void setStaffPositionId(String staffPositionId)
	{
		if (this.staffPositionId == null || !this.staffPositionId.equals(staffPositionId))
		{
			markModified();
		}
		this.staffPositionId = staffPositionId;
	}

	/**
	 * set the type reference for class member userProfile
	 */
	public void setUserProfile(UserProfile userProfile)
	{
		if (this.userProfile == null || !this.userProfile.equals(userProfile))
		{
			markModified();
		}
		if (userProfile.getOID() == null)
		{
			new mojo.km.persistence.Home().bind(userProfile);
		}
		setUserProfileId("" + userProfile.getOID());
		this.userProfile = (UserProfile) new mojo.km.persistence.Reference(userProfile).getObject();
	}

	/**
	 * Set the reference value to class :: pd.contact.user.UserProfile
	 */
	public void setUserProfileId(String userProfileId)
	{
		if (this.userProfileId == null || !this.userProfileId.equals(userProfileId))
		{
			markModified();
		}
		userProfile = null;
		this.userProfileId = userProfileId;
	}

	public SupervisionStaffResponseEvent valueObject()
	{
		SupervisionStaffResponseEvent event = new SupervisionStaffResponseEvent();
		event.setCourtNum(this.courtId);
		event.setLogonId(this.userProfileId);
		UserProfile up = this.getUserProfile();
		if (up != null)
		{
			event.setFirstName(up.getFirstName());
			event.setMiddleName(up.getMiddleName());
			event.setLastName(up.getLastName());
		}
		event.setSupervisionStaffId(this.staffPositionId);
		event.setProbationOfficerInd(this.probationOfficerInd);
		return event;
	}
}

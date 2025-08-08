package mojo.km.naming;

import java.io.Serializable;

/**
 * @author jmcnabb
 *
 * Enumeration of the values for Status of a Security User.
 */
public class UserStatus implements Serializable
{
	private String status;
	private int profileStatus;

	private static final int PROFILE_ERROR = 0;
	private static final int PROFILE_ACTIVE = 1;
	private static final int PROFILE_EXPIRED = 2;
	private static final int PROFILE_SUSPENDED = 4;
	// Status Constants
	public static final UserStatus INUSE = new UserStatus("IN USE");
	public static final UserStatus INACTIVE = new UserStatus("INACTIVE");
	public static final UserStatus UNUSED = new UserStatus("UNUSED");

	private UserStatus(String aStatus)
	{
		this.status = aStatus;
	}

	public String getStatus()
	{
		return this.status;
	}

	public void setProfileActive()
	{
		this.profileStatus = PROFILE_ACTIVE;
	}

	public void setProfileSuspended()
	{
		this.profileStatus = PROFILE_SUSPENDED;
	}

	public void setProfileExpired()
	{
		this.profileStatus = PROFILE_EXPIRED;
	}

	public void setGeneralProfileError()
	{
		this.profileStatus = PROFILE_ERROR;
	}

	public boolean isProfileSuspended()
	{
		return (this.profileStatus == PROFILE_SUSPENDED);
	}

	public boolean isProfileExpired()
	{
		return (this.profileStatus == PROFILE_EXPIRED);
	}

	public boolean isProfileActive()
	{
		return (this.profileStatus == PROFILE_ACTIVE);
	}

	public boolean hasGeneralProfileError()
	{
		return (this.profileStatus == PROFILE_ERROR);
	}

}

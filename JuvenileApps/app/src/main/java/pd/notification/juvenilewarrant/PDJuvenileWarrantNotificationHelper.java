/*
 * Created on Dec 28, 2004
 */
package pd.notification.juvenilewarrant;

import pd.contact.officer.OfficerProfile;
import pd.contact.user.UserProfile;
import pd.juvenilewarrant.JuvenileWarrant;

/**
 * @author glyons
 */
public final class PDJuvenileWarrantNotificationHelper
{

	private PDJuvenileWarrantNotificationHelper()
	{
	}
	/**
	 * 
	 * @param className
	 * @param warrantNum
	 * @param notificationType
	 * @return taskName
	 */
	public static String getTaskName(String className, String warrantNum, int notificationType)
	{
		return className + "-" + warrantNum + "-" + notificationType;
	}
	/**
	 * 
	 * @param warrantNum
	 * @return warrantOriginatorEmail
	 */
	public static String getWarrantOriginatorEmail(String warrantNum)
	{
		String emailAddress = null;
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
		if (warrant != null)
		{
			UserProfile warrantOriginator = warrant.getWarrantOriginatorUser();
			if (warrantOriginator != null)
			{
				emailAddress = warrantOriginator.getEmail();
			}
		}
		return emailAddress;
	}
	/**
	 * 
	 * @param warrantNum
	 * @return emailAddress
	 */
	public static String getOfficerEmail(String warrantNum)
	{
		String emailAddress = null;
		JuvenileWarrant warrant = JuvenileWarrant.find(warrantNum);
		if (warrant != null)
		{
			OfficerProfile officer = warrant.getOfficer();
			if (officer != null)
			{
				emailAddress = officer.getEmail();
			}
		}
		return emailAddress;
	}
}
/*
 * Created on Feb 3, 2005
 */
package pd.notification;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mojo.km.config.PropertyBundleProperties;
import mojo.messaging.mailrequestsevents.SendEmailEvent;

/**
 * @author dnikolis
 */
public final class PDNotificationHelper
{

	private PDNotificationHelper()
	{
	}
	/**
	 * This method uses the PropertyBundle to retrieve a value from the passed
	 * parameter key.  In this case, the key is usually a value that represents
	 * the type of notification email to retrieve
	 * @param key
	 * @return email
	 */
	public static String getNotificationEmail(String key)
	{
		PropertyBundleProperties propBundle = PropertyBundleProperties.getInstance();
		String email = propBundle.getProperty(key);
		return email;
	}

	/**
	 * Creates a collection of emails from a concatenated list of emails seperated 
	 * by a specified parameter that is a deliminator.  
	 * @param listOfEmails
	 * @param delim
	 * @return Collection
	 */
	public static List createEmailAddressEvents(String listOfEmails, String delim)
	{
		List list = new ArrayList();
		StringTokenizer st = new StringTokenizer(listOfEmails, delim);

		if (!st.hasMoreElements())
		{		
			list.add(listOfEmails);
		}
		else
		{
			while (st.hasMoreElements())
			{
				list.add(st.nextToken());
			}
		}
		return list;
	}

	/**
	 * Creates a collection of emails from a concatenated list of emails seperated 
	 * by a using the specific deliminator of ';' (semicolon)  
	 * @param listOfEmails
	 * @return Collection
	 */
	public static List createEmailAddressEvents(String listOfEmails)
	{
		return PDNotificationHelper.createEmailAddressEvents(listOfEmails, ";");
	}

	/**
	 * Inserts a collection of EmailAddressEvents from a string list of emails that are 
	 * seperated by semicolons onto the SendEmailEvent
	 * @param sendEvent
	 * @param listOfEmails
	 */
	public static void populateSendEmailAddressEvents(SendEmailEvent sendEvent, String listOfEmails)
	{
		List emails = PDNotificationHelper.createEmailAddressEvents(listOfEmails);
		PDNotificationHelper.populateSendEmailAddressEvents(sendEvent, emails);
	}

	/**
	 * Inserts a collection of EmailAddressEvents from a string list of emails that are 
	 * seperated by a passed parameter deliminator onto the SendEmailEvent
	 * @param sendEvent
	 * @param listOfEmails
	 * @param delim
	 */
	public static void populateSendEmailAddressEvents(SendEmailEvent sendEvent, String listOfEmails, String delim)
	{
		List emails = PDNotificationHelper.createEmailAddressEvents(listOfEmails, delim);
		PDNotificationHelper.populateSendEmailAddressEvents(sendEvent, emails);
	}

	/**
	 * Inserts a collection of EmailAddressEvents onto a SendEmailEvent
	 * @param sendEvent
	 * @param emailAddresses
	 */
	public static void populateSendEmailAddressEvents(SendEmailEvent sendEvent, List emailAddresses)
	{
		if (emailAddresses != null && sendEvent != null)
		{
			int len = emailAddresses.size();
			for(int i=0;i<len;i++)
			{
				sendEvent.addToAddress((String) emailAddresses.get(i));
			}
		}
	}

}

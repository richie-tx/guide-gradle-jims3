package ui.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import mojo.messaging.mailrequestsevents.SendEmailEvent;

public final class UINotificationHelper {
	
	private UINotificationHelper()
	{
	}
	
	/**
	 * Inserts a collection of EmailAddressEvents from a string list of emails that are 
	 * seperated by semicolons onto the SendEmailEvent
	 * @param sendEvent
	 * @param listOfEmails
	 */
	public static void populateSendEmailAddressEvents(SendEmailEvent sendEvent, String listOfEmails)
	{
		List emails = UINotificationHelper.createEmailAddressEvents(listOfEmails);
		UINotificationHelper.populateSendEmailAddressEvents(sendEvent, emails);
	}
	

	/**
	 * Creates a collection of emails from a concatenated list of emails seperated 
	 * by a using the specific deliminator of ';' (semicolon)  
	 * @param listOfEmails
	 * @return Collection
	 */
	public static List createEmailAddressEvents(String listOfEmails)
	{
		return UINotificationHelper.createEmailAddressEvents(listOfEmails, ";");
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

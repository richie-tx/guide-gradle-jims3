/*
 * Created on Sep 13, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.contact.user;

import java.util.Comparator;


import messaging.contact.user.reply.UserResponseEvent;


/**
 * @author cshimek
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserResponseEventComparator implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1) {
		int result = 0;
		UserResponseEvent eventA = (UserResponseEvent) arg0;
		UserResponseEvent eventB = (UserResponseEvent) arg1;

		String eventNameA = eventA.getFormattedName();
		String eventNameB = eventB.getFormattedName();

		if (eventNameA == null && eventNameB == null)
		{
			result = 0;
		}
		else if (eventNameA == null)
		{
			result = -1;
		}
		else if (eventNameB == null)
		{
			result = 1;
		}
		else
		{
			result = eventNameA.compareToIgnoreCase(eventNameB);
			if(result>0)
				result=1;
			else if (result <0)
				result =-1;
		}
		
		//result = -result;
		
		return result;
	}
}

/*
 * Created on Jul 6, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.Comparator;
import java.util.Date;

import messaging.juvenile.reply.JuvenileAbuseResponseEvent;

/**
 * @author asrvastava
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AbuseEntryComparator implements Comparator
{

	public int compare(Object arg0, Object arg1)
	{
		int result = 0;
		JuvenileAbuseResponseEvent eventA = (JuvenileAbuseResponseEvent) arg0;
		JuvenileAbuseResponseEvent eventB = (JuvenileAbuseResponseEvent) arg1;

		Date eventDateA = eventA.getEntryDate();
		Date eventDateB = eventB.getEntryDate();

		if (eventDateA == null && eventDateB == null)
		{
			result = 0;
		}
		else if (eventDateA == null)
		{
			result = -1;
		}
		else if (eventDateB == null)
		{
			result = 1;
		}
		else
		{
			result = eventDateA.compareTo(eventDateB);
		}
		
		result = -result;
		
		return result;
	}

}

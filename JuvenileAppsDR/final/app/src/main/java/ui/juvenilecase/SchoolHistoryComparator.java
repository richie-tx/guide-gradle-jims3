/*
 * Created on Jul 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.juvenilecase;

import java.util.Comparator;

import messaging.juvenile.reply.JuvenileSchoolHistoryResponseEvent;

/**
 * @author jfisher
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SchoolHistoryComparator implements Comparator
{

	public int compare(Object arg0, Object arg1)
	{
		int result = 0;
		JuvenileSchoolHistoryResponseEvent eventA = (JuvenileSchoolHistoryResponseEvent) arg0;
		JuvenileSchoolHistoryResponseEvent eventB = (JuvenileSchoolHistoryResponseEvent) arg1;
		
		if (eventA.getCreateDate().before(eventB.getCreateDate())){
			return 1;
		}else if (eventA.getCreateDate().after(eventB.getCreateDate())){
			return -1;
		}
		return 0;
	}
}

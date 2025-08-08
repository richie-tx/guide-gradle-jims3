/*
 * Created on Oct 10, 2007
 * 
 */
package ui.common;

import java.util.Comparator;

import messaging.casefile.reply.ActivityResponseEvent;

/**
 * @author awidjaja
 *
 */
public class ActivityOldestFirstComparator implements Comparator
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object arg0, Object arg1)
	{
		int result = 0;

		ActivityResponseEvent obj1 = (ActivityResponseEvent)arg0;
		ActivityResponseEvent obj2 = (ActivityResponseEvent)arg1;
		
		if(obj2 == null || obj2.getActivityDate() == null) {
			return -1; // this makes any null objects go to the bottom change this to -1 if you want the top of the list
		}
		if(obj1 == null || obj1.getActivityDate() == null) {
			return 1;// this makes any null objects go to the bottom change this to 1 if you want the top of the list
		}
		
		return obj1.getActivityDate().compareTo(obj2.getActivityDate());
	}
}

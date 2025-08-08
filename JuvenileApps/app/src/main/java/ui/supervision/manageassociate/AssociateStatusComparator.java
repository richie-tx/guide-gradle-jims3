/*
 * Created on Mar 30, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.manageassociate;

import java.util.Comparator;

import messaging.manageassociate.reply.AssociateResponseEvent;

/**
 * @author cc_rsojitrawala
 *
 * 
 */
public class AssociateStatusComparator implements Comparator{

	public int compare(Object arg0, Object arg1)
	{
		int result = 0;
		AssociateResponseEvent eventA = (AssociateResponseEvent) arg0;
		AssociateResponseEvent eventB = (AssociateResponseEvent) arg1;

		boolean statusA = eventA.getStatus();
		boolean statusB = eventB.getStatus();

		if (statusA == true && statusB == true) 
		{
			result = 0;
		}
		else if (statusA == true)	//	Associate A has a GOOD status
		{
			result = -1;
		}
		else if (statusB == true)	// Associate A has a BAD status but Associate B has GOOD status 
		{
			result = 1;
		}

		return result;
	}
}

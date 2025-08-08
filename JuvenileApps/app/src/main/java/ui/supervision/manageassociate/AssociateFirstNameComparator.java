/*
 * Created on Apr 2, 2007
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
 */
public class AssociateFirstNameComparator implements Comparator {
	public int compare(Object arg0, Object arg1)
	{
		int result = 0;
		AssociateResponseEvent eventA = (AssociateResponseEvent) arg0;
		AssociateResponseEvent eventB = (AssociateResponseEvent) arg1;

		boolean statusA = eventA.getStatus();
		boolean statusB = eventB.getStatus();
		
		String lastNameA = eventA.getAssocLastName();
		String lastNameB = eventB.getAssocLastName();
		
		String firstNameA = eventA.getAssocFirstName();
		String firstNameB = eventB.getAssocFirstName();

		if (statusA != statusB) // STATUSes are different - Do not compare Last Names
		{
			result = 0;
		}
		else 
		{
			if ((lastNameA == null || lastNameA.equals(""))
					&& (lastNameB == null || lastNameB.equals(""))){ // No Last Name present, so compare on First Name
				result = firstNameA.compareTo(firstNameB);
			}		
		}
		
		return result;
	}

}

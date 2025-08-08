/*
 * Created on Apr 2, 2007
 *
 */
package ui.supervision.manageassociate;

import java.util.Comparator;

import messaging.manageassociate.reply.AssociateResponseEvent;

/**
 * @author cc_rsojitrawala
 *
 */
public class AssociateLastNameComparator implements Comparator{

	public int compare(Object arg0, Object arg1)
	{
		int result = 0;
		AssociateResponseEvent eventA = (AssociateResponseEvent) arg0;
		AssociateResponseEvent eventB = (AssociateResponseEvent) arg1;

		boolean statusA = eventA.getStatus();
		boolean statusB = eventB.getStatus();
		
		String lastNameA = eventA.getAssocLastName();
		String lastNameB = eventB.getAssocLastName();
		
/*		String nameA;
		String nameB;

		if (lastNameA == null || lastNameA.equals("")
		{
			nameA = eventA.getAssocLastName();
		}
		else
			nameA = eventA.getAssocFirstName();
		
		
		if (lastNameB == null || lastNameB.equals("")
		{
			nameB = eventB.getAssocLastName();
		}
		else
			nameB = eventB.getAssocFirstName();
		
		if (statusA != statusB) // STATUSes are different - Do not compare Last Names
		{
			result = 0;
		}
		else
			result = nameA.compareTo(nameB);
		*/
		
		if (statusA != statusB) // STATUSes are different - Do not compare Last Names
		{
			result = 0;
		}
		else if (lastNameA == null || lastNameA.equals("") )
		{
			result = -1;
		}
		else if (lastNameB == null || lastNameB.equals("")) 
		{
			result = 1;
		}
		else  
		{
			result = lastNameA.compareTo(lastNameB);	
		}
		
		return result;
	}


}

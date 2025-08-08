/*
 * Created on May 8, 2006
 */
package messaging.managesupervisioncase.reply;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import messaging.domintf.managesupervisioncase.IParty;
import mojo.km.messaging.ResponseEvent;

/**
 * @author jmcnabb
 *
 * For displaying the list of Out of County Cases for a Defendant (SPN).
 */
public class CaseListResponseEvent extends ResponseEvent
{
	private IParty party;
	private Collection cases = new ArrayList();

	public CaseListResponseEvent()
	{

	}

	/**
	 * Access method for the party property.
	 * 
	 * @return   the current value of the party property
	 */
	public IParty getParty()
	{
		return party;
	}

	/**
	 * Sets the value of the party property.
	 * 
	 * @param aParty the new value of the party property
	 */
	public void setParty(IParty aParty)
	{
		party = aParty;
	}

	/**
	 * Access method for the cases property.
	 * 
	 * @return   the collection of cases
	 */
	public Collection getCases()
	{
		return cases;
	}

	/**
	 * Adds an entry to the internal collection of cases.
	 * 
	 * @param aCase the new value of the offense property
	 */
	public void addCase(OutOfCountyCaseTO aCase)
	{
		cases.add(aCase);
	}

	/**
	 * Sorts the internal collection of cases in ascending order by caseNum.
	 * 
	 * @param aCase the new value of the offense property
	 */
	public void sortCases()
	{
		Collections.sort((ArrayList)cases);
	}
	
}

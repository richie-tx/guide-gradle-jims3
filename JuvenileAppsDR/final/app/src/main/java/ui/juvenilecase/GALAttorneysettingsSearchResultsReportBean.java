/**
 * 
 */
package ui.juvenilecase;

import java.util.Collection;

/**
 * @author apillai
 *
 
 */
public class GALAttorneysettingsSearchResultsReportBean {

	private int numberOfResults;
	private Collection attorneySearchResults;
	private String todaysDate;
	
	public int getNumberOfResults()
	{
	    return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults)
	{
	    this.numberOfResults = numberOfResults;
	}	

	public Collection getAttorneySearchResults()
	{
	    return attorneySearchResults;
	}

	public void setAttorneySearchResults(Collection attorneySearchResults)
	{
	    this.attorneySearchResults = attorneySearchResults;
	}
	public String getTodaysDate()
	{
	    return todaysDate;
	}

	public void setTodaysDate(String todaysDate)
	{
	    this.todaysDate = todaysDate;
	}
	
	
}

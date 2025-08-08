/*
 * Created on Apr 25, 2005
 */
package messaging.juvenilecase.reply;

import naming.PDJuvenileCaseConstants;
import mojo.km.messaging.ResponseEvent;

/**
 * @author glyons
 */
public class SearchResultsCountResponseEvent extends ResponseEvent
{
	private int numberOfResults;
	
	/**
	 * Set the default topic in the constructor
	 *
	 */
	public SearchResultsCountResponseEvent()
	{
		this.setTopic(PDJuvenileCaseConstants.SEARCH_RESULTS_COUNT_TOPIC);
	}
	
	/**
	 * 
	 * @return numberOfResults
	 */
	public int getNumberOfResults() 
	{
	        return this.numberOfResults;
	}
	
	/**
	 * 
	 * @param NumberOfResults
	 */
	public void setNumberOfResults(int aNumberOfResults) 
	{
	        this.numberOfResults = aNumberOfResults;
	}
}
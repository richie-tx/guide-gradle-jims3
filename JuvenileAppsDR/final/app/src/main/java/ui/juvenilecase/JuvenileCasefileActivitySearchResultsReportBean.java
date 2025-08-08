/**
 * 
 */
package ui.juvenilecase;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import messaging.juvenilecase.reply.JuvenileCasefileActivityStatisticsResponseEvent;

/**
 * @author apillai
 *
 
 */
public class JuvenileCasefileActivitySearchResultsReportBean {

	private int numberOfResults;
	private Collection searchResults;	
	private List<JuvenileCasefileActivityStatisticsResponseEvent> statistics;

	private int total;
	private int actCounter;
	
	private String todaysDate;
	
	private String title;
	
	public int getNumberOfResults()
	{
	    return numberOfResults;
	}

	public void setNumberOfResults(int numberOfResults)
	{
	    this.numberOfResults = numberOfResults;
	}	

	
	public String getTodaysDate()
	{
	    return todaysDate;
	}

	public void setTodaysDate(String todaysDate)
	{
	    this.todaysDate = todaysDate;
	}
	public Collection getSearchResults()
	{
	    return searchResults;
	}

	public void setSearchResults(Collection searchResults)
	{
	    this.searchResults = searchResults;
	}
	
	public int getTotal()
	{
	    return total;
	}

	public void setTotal(int total)
	{
	    this.total = total;
	}
	
	public int getActCounter()
	{
	    return actCounter;
	}

	public void setActCounter(int actCounter)
	{
	    this.actCounter = actCounter;
	}
	public List<JuvenileCasefileActivityStatisticsResponseEvent> getStatistics()
	{
	    return statistics;
	}

	public void setStatistics(List<JuvenileCasefileActivityStatisticsResponseEvent> statistics)
	{
	    this.statistics = statistics;
	}

	public String getTitle()
	{
	    return title;
	}

	public void setTitle(String title)
	{
	    this.title = title;
	}
	
	
	
	
}

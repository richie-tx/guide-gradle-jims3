package pd.juvenilecase.casefilesearch;

import java.util.Iterator;
import java.util.Map;

import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.exception.ReturnException;

import org.apache.commons.collections.FastHashMap;

import pd.juvenilecase.JuvenileCasefile;

/**
 * 
 * @author mchowdhury
 *
 */
public class SearchById extends SearchJuvenileCaseFile implements ISearch
{

	/**
	 * @roseuid 4278CAAC00FD
	 */
	public SearchById()
	{
	}

	/**
	 * @param event
	 * @roseuid 4278C7B900B1
	 */
	public void search(IEvent event)
	{
		SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) event;
		int resultsCount = searchByAttribute(search);
		// Send back the number of results for the search
		this.postResultsCount(resultsCount);
	}

	/**
	 * This method searches Casefile by a specific attribute value.  Current attribute
	 * values searching by Juvenile Number and Supervision Number
	 * @param event
	 * @return boolean if the search returned any hits
	 */
	private int searchByAttribute(SearchJuvenileCasefilesEvent search)
	{
		int count = 0;
		Iterator files = null;
		try{
		    files = JuvenileCasefile.findAll(search.getAttributeName(), search.getAttributeValue().toString());
			Map juveniles = new FastHashMap();
			while (files.hasNext())
			{
				JuvenileCasefile file = (JuvenileCasefile) files.next();
				postSearchResponseEvents(file, juveniles, true,"");
				count++;
			}		    
		}catch(Exception e){
			e.printStackTrace();
			String t = e.getCause().getMessage();
			ReturnException re = null;
			if(t.indexOf("SQLSTATE: 22001") > 0){
				re = new ReturnException("Not a valid juvenile number");
			}else{
				re = new ReturnException(t);
			}
		}
		return count;
	}
}

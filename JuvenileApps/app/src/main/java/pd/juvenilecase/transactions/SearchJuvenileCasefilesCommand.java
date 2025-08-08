package pd.juvenilecase.transactions;

import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import messaging.juvenilecase.reply.NoJuvenileCasefilesResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDJuvenileCaseConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseContextFactory;
import pd.exception.ReflectionException;
import pd.juvenilecase.casefilesearch.ISearch;

/**
 * 
 * @author glyons
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SearchJuvenileCasefilesCommand implements ICommand
{

	/**
	 * @roseuid 4278CAAC00FD
	 */
	public SearchJuvenileCasefilesCommand()
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B900B1
	 */
	public void execute(IEvent event)
	{
		SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) event;

		// Determine which Search Type Code is being used
		String searchType = search.getSearchType();
		
 	    ResponseContextFactory respFac = new ResponseContextFactory();
	    ISearch aSearch =  null;
	    
	    try {
	        if(PDJuvenileCaseConstants.SEARCH_JUVENILE_NAME.equalsIgnoreCase(searchType)){
	        	aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_JUVENILE_NAME_LOCATOR);
	        }else if(PDJuvenileCaseConstants.SEARCH_JUVENILE_NUMBER.equalsIgnoreCase(searchType)){
	       	    search.setAttributeName("juvenileId");
	       	    search.setAttributeValue(search.getJuvenileNum());
	       	    aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_ATTRIBUTE_LOCATOR);
	        }else if(PDJuvenileCaseConstants.SEARCH_PROBATION_OFFICER.equalsIgnoreCase(searchType)){
	        	aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_PROBATION_OFFICER_LOCATOR);
	        }else if(PDJuvenileCaseConstants.SEARCH_CASE_STATUS.equalsIgnoreCase(searchType)){
	        	aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_CASE_STATUS_LOCATOR);
	        }else if(PDJuvenileCaseConstants.SEARCH_SUPERVISION_NUMBER.equalsIgnoreCase(searchType)){
	        	search.setAttributeName("OID");
	       	    search.setAttributeValue(search.getSupervisionNum());
	       	    aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_ATTRIBUTE_LOCATOR);
	        }else if(PDJuvenileCaseConstants.SEARCH_CASE_LOAD.equalsIgnoreCase(searchType)){
	       	    aSearch = (ISearch) respFac.lookup(ResponseLocatorConstants.CASEFILE_SEARCH_BY_ATTRIBUTE_LOCATOR);
	        }else{
	        	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
				// Return a Error Event if invalid search criteria is sent.
				NoJuvenileCasefilesResponseEvent error = new NoJuvenileCasefilesResponseEvent();
				error.setMessage("Invalid search criteria for searching juvenile case file.");
				dispatch.postEvent(error);
				return;
	        }
	    }catch (ReflectionException e) {
		    e.printStackTrace();
	    }
	    aSearch.search(search);
	}


	/**
	 * @param event
	 * @roseuid 4278C7B900B3
	 */
	public void onRegister(IEvent event)
	{

	}

	/**
	 * @param event
	 * @roseuid 4278C7B900BC
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 4278C7B900C6
	 */
	public void update(Object anObject)
	{

	}
}

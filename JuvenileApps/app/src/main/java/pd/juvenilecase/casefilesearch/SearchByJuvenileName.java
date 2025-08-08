package pd.juvenilecase.casefilesearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenilecase.GetJuvenileAliasEvent;
import messaging.juvenilecase.SearchCasefileByJuvenileAndOfficerEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDConstants;

import org.apache.commons.collections.FastHashMap;

import pd.juvenile.Juvenile;
import pd.juvenile.JuvenileAlias;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author mchowdhury
 */
public class SearchByJuvenileName extends SearchJuvenileCaseFile implements ISearch
{
	/**
	 * @roseuid 4278CAAC00FD
	 */
	public SearchByJuvenileName()
	{
	}

	/**
	 * @param event
	 * @roseuid 4278C7B900B1
	 */
	public void search( IEvent event )
	{
		SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent)event;
		int resultsCount = searchByJuvenileName( search );
		// Send back the number of results for the search
		this.postResultsCount( resultsCount );
	}

	/**
	 * Searches JuvenileCasefile by name and also includes alias records, if any.
	 * 
	 * @param event
	 * @return boolean if the search returned any hits
	 * @author parumbakkam
	 */
	private int searchByJuvenileName( SearchJuvenileCasefilesEvent search )
	{
		// Return value
		int returnValue = 0;

		// Populate Juvenile information
		SearchJuvenileProfilesEvent juvProfSearch = new SearchJuvenileProfilesEvent();
		juvProfSearch.setLastName( search.getLastName() );
		juvProfSearch.setFirstName( search.getFirstName() );
		juvProfSearch.setMiddleName( search.getMiddleName() );

		// Populate juvenile info for Alias search
		GetJuvenileAliasEvent aliasSearch = new GetJuvenileAliasEvent();
		aliasSearch.setLastName( search.getLastName() );
		aliasSearch.setFirstName( search.getFirstName() );
		aliasSearch.setMiddleName( search.getMiddleName() );
		aliasSearch.setJuvenileType( "A" );

		// Perform row count for Alias/Juvenile names
		IHome home = new Home();
		MetaDataResponseEvent juvenileRecordCount = home.findMeta( juvProfSearch, Juvenile.class );
		MetaDataResponseEvent aliasRecordCount = home.findMeta( aliasSearch, JuvenileAlias.class );
		home = null;

		// If search results exceed 2000, then return...
		if( (juvenileRecordCount != null && juvenileRecordCount.getCount() > PDConstants.SEARCH_LIMIT) && 
				(aliasRecordCount != null && aliasRecordCount.getCount() > PDConstants.SEARCH_LIMIT) )
		{
			super.sendMaxCountExceeded();
		}
		else
		{
			Map<String, JuvenileAlias> juvenileAliasMap = null;
			Map juvenilesMap = null;
			Juvenile juv = null ;
			SearchCasefileByJuvenileAndOfficerEvent caseEvent = null;

			// We are good... Let's make the call to get the name records.
			// To be used with the outer block.
			// For nested blocks, use a local declaration
			Iterator blockIterator = Juvenile.findAll( juvProfSearch );

			// Let's actually check to see if we got back any records before we
			// instantiate. This is a lazy instantiation technique to save memory
			while(blockIterator != null && blockIterator.hasNext())
			{
				if( juvenilesMap == null )
				{
					juvenilesMap = new FastHashMap();
				}

				// get the record
				juv = (Juvenile)blockIterator.next();

				// *** The above commented operation of adding juvenile data to a map is
				// a pretty expensive operation. The below code is implemented with the
				// assumption that, juvenile data obtained from M204 is always unique
				// ***

				if( juv != null )
				{
					juvenilesMap.put( juv.getJuvenileNum(), juv );
				}
			} // end while block

			// Obtain alias records for the search terms.

			if( ( blockIterator = JuvenileAlias.findAll( aliasSearch ) ) != null )
			{
				JuvenileAlias juvAlias = null;
				
				while( blockIterator.hasNext())
				{
					if( juvenileAliasMap == null )
					{
						juvenileAliasMap = new FastHashMap();
					}

					// get the record
					if( (juvAlias = (JuvenileAlias)blockIterator.next()) != null )
					{
						juvenileAliasMap.put( juvAlias.getJuvenileNum(), juvAlias );
					}
				} // end while block
			}

			// Get Case file/Officer information
			// Per DN the relationship between a juvenile and an officer is 1:1 while
			// reporting casework.

			if( juvenilesMap != null && juvenilesMap.size() > 0 )
			{
				// Set returnValue to the size of the juvenilesMap
				returnValue = juvenilesMap.size();
				List juvNumbersList = Arrays.asList( juvenilesMap.keySet().toArray( new String[ juvenilesMap.size() ] ) );

				// Process Juvenile Records
				caseEvent = new SearchCasefileByJuvenileAndOfficerEvent();
				caseEvent.setJuvNumList( juvNumbersList );

				if( (blockIterator = JuvenileCasefile.findAll( caseEvent )) != null )
				{
					while(blockIterator.hasNext())
					{
						JuvenileCasefile caseFile = (JuvenileCasefile)blockIterator.next();
						postSearchResponseEvents( caseFile, juvenilesMap, true,"" );
					}
				}
			}

			// Process Juvenile Alias Records
			if( juvenileAliasMap != null && juvenileAliasMap.size() > 0 )
			{
				returnValue =+ juvenileAliasMap.size();

				// Process Juvenile Records
				caseEvent = new SearchCasefileByJuvenileAndOfficerEvent();

				List juvAliasNumbersList = Arrays.asList( juvenileAliasMap.keySet()
						.toArray( new String[ juvenileAliasMap.size() ] ) );
				caseEvent.setJuvNumList( juvAliasNumbersList );

				if( (blockIterator = JuvenileCasefile.findAll( caseEvent )) != null)
				{
					while( blockIterator.hasNext())
					{
						JuvenileCasefile caseFile = (JuvenileCasefile)blockIterator.next();
						postSearchResponseEvents( caseFile, juvenileAliasMap, true,"" );
					}
				}
			}
		} // else record count is less than 2000

		// Don't trust GC and explicitly de-link to guarantee clean up
		juvProfSearch = null;
		juvenileRecordCount = null;
		aliasRecordCount = null;

		// Return
		return returnValue;
	}
}

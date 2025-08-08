package pd.juvenilecase.casefilesearch;

import java.util.Iterator;
import java.util.Map;

import messaging.juvenilecase.SearchJuvenileCasefileByLocationsEvent;
import messaging.juvenilecase.SearchJuvenileCasefileByZipCodeEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import naming.PDJuvenileCaseConstants;

import org.apache.commons.collections.FastHashMap;

import pd.juvenilecase.JuvenileCasefile;

/**
 * @author mchowdhury
 */
public class SearchByCaseStatus extends SearchJuvenileCaseFile implements ISearch
{

    /**
     * @roseuid 4278CAAC00FD
     */
    public SearchByCaseStatus()
    {
    }

    /**
     * @param event
     * @roseuid 4278C7B900B1
     */
    public void search(IEvent event)
    {
        SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) event;
        int resultsCount = searchByCaseStatus(search);
        // Send back the number of results for the search
        this.postResultsCount(resultsCount);
    }

    /**
     * Searches JuvenileCasefile by supervisionType and caseStatus if supplied.
     * 
     * @param event
     * @return boolean if the search returned any hits
     */
    private int searchByCaseStatus(SearchJuvenileCasefilesEvent event)
    {
    	// if zip code search call zipcode event.
        if(event.getZipCode()!=null & !event.getZipCode().equals("")){
        	SearchJuvenileCasefileByZipCodeEvent zipCodeSearch = new SearchJuvenileCasefileByZipCodeEvent();
        	zipCodeSearch.setSearchType(PDJuvenileCaseConstants.SEARCH_CASE_STATUS);
        	zipCodeSearch.setSupervisionTypeId(event.getSupervisionType());
        	zipCodeSearch.setCaseStatusId(event.getCaseStatus());
        	zipCodeSearch.setOfficerLastName(event.getOfficerLastName().toUpperCase());
             String officerFirstName = event.getOfficerFirstName();
             if (officerFirstName != null && !officerFirstName.equals(""))
             {
            	 zipCodeSearch.setOfficerFirstName(officerFirstName.toUpperCase());
             }
             String officerMiddleName = event.getOfficerMiddleName();
             if (officerMiddleName != null && !officerMiddleName.equals(""))
             {
            	 zipCodeSearch.setOfficerMiddleName(officerMiddleName.toUpperCase());
             }
             zipCodeSearch.setLocation(event.getLocation());
             zipCodeSearch.setExpectedEndDateFrom(event.getCasefileExpectedEndDateFrom());
             zipCodeSearch.setExpectedEndDateTo(event.getCasefileExpectedEndDateTo());
             zipCodeSearch.setDispDateFrom(event.getCasefileDispositionDateFrom()); // Task 50044 changes
             zipCodeSearch.setDispDateTo(event.getCasefileDispositionDateTo()); // Task 50044 changes
             zipCodeSearch.setZipCode(event.getZipCode()); //#32659 changes
             
             zipCodeSearch.setCountOfJuv("true"); // work around to add the order by clause as rob could not sort it . //#32659 changes
             
             
             int count = 0;
             IHome home = new Home();
             MetaDataResponseEvent meta = home.findMeta(zipCodeSearch, JuvenileCasefile.class);
             if (meta != null)
             {	
             	if (meta.getCount() > 0)
             	{	
             		if (meta.getCount() > 5000)
             		{
             			super.sendMaxCountExceeded();
             		} else {	
             			zipCodeSearch.setCountOfJuv("false"); // work around to add the order by clause as rob could not sort it . //#32659 changes
             			Iterator files = JuvenileCasefile.findAll(zipCodeSearch);
     		
             			Map juveniles = new FastHashMap();
             			while (files.hasNext())
             			{
     			            JuvenileCasefile file = (JuvenileCasefile) files.next();
     			            postSearchResponseEvents(file, juveniles, false,zipCodeSearch.getSearchType()); // added searchType as the OID for case status search are different from the other search.
     			            count++;
             			}
             		}	
             	}   
             }    
             return count;
        }else{ // non - zipCode search 
             SearchJuvenileCasefileByLocationsEvent search = new SearchJuvenileCasefileByLocationsEvent();
             search.setSearchType(PDJuvenileCaseConstants.SEARCH_CASE_STATUS);
             search.setSupervisionTypeId(event.getSupervisionType());
             search.setCaseStatusId(event.getCaseStatus());
             search.setOfficerLastName(event.getOfficerLastName().toUpperCase());
             String officerFirstName = event.getOfficerFirstName();
             if (officerFirstName != null && !officerFirstName.equals(""))
             {
                 search.setOfficerFirstName(officerFirstName.toUpperCase());
             }
             String officerMiddleName = event.getOfficerMiddleName();
             if (officerMiddleName != null && !officerMiddleName.equals(""))
             {
                 search.setOfficerMiddleName(officerMiddleName.toUpperCase());
             }
             search.setLocation(event.getLocation());
             search.setExpectedEndDateFrom(event.getCasefileExpectedEndDateFrom());
             search.setExpectedEndDateTo(event.getCasefileExpectedEndDateTo());
             search.setDispDateFrom(event.getCasefileDispositionDateFrom()); // Task 50044 changes
             search.setDispDateTo(event.getCasefileDispositionDateTo()); // Task 50044 changes
             
             
             search.setCountOfJuv("true"); // work around to add the order by clause as rob could not sort it . //#32659 changes
             
             
             int count = 0;
             IHome home = new Home();
             MetaDataResponseEvent meta = home.findMeta(search, JuvenileCasefile.class);
             if (meta != null)
             {	
             	if (meta.getCount() > 0)
             	{	
             		if (meta.getCount() > 5000)
             		{
             			super.sendMaxCountExceeded();
             		} else {	
             			search.setCountOfJuv("false"); // work around to add the order by clause as rob could not sort it . //#32659 changes
             			Iterator files = JuvenileCasefile.findAll(search);
     		
             			Map juveniles = new FastHashMap();
             			while (files.hasNext())
             			{
     			            JuvenileCasefile file = (JuvenileCasefile) files.next();
     			            postSearchResponseEvents(file, juveniles, false,search.getSearchType()); // added searchType as the OID for case status search are different from the other search.
     			            count++;
             			}
             		}	
             	}   
             }    
             return count;
        }
      
    }
}

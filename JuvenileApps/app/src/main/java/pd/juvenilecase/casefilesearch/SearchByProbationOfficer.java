package pd.juvenilecase.casefilesearch;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.FastHashMap;

import messaging.juvenilecase.SearchCasefileByJuvenileAndOfficerEvent;
import messaging.juvenilecase.SearchJuvenileCasefilesEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.MetaDataResponseEvent;
import mojo.km.persistence.Home;
import mojo.km.persistence.IHome;
import pd.juvenilecase.JuvenileCasefile;

/**
 * @author mchowdhury
 */
public class SearchByProbationOfficer extends SearchJuvenileCaseFile implements ISearch
{

    /**
     * @roseuid 4278CAAC00FD
     */
    public SearchByProbationOfficer()
    {
    }

    /**
     * @param event
     * @roseuid 4278C7B900B1
     */
    public void search(IEvent event)
    {
        SearchJuvenileCasefilesEvent search = (SearchJuvenileCasefilesEvent) event;
        int resultsCount = searchByProbationOfficer(search);
        // Send back the number of results for the search
        this.postResultsCount(resultsCount);
    }

    /**
     * This method searches UserProfile by First/Middle/Last name and with a logonId prefix of 'UV'
     * (representing Prob Officers). The method posts JuvenileCasefileSearchResponseEvents for each
     * result.
     * 
     * @param event
     * @return boolean if the search returned any hits
     */
    private int searchByProbationOfficer(SearchJuvenileCasefilesEvent event)
    {
        int count = 0;

        SearchCasefileByJuvenileAndOfficerEvent sEvent = new SearchCasefileByJuvenileAndOfficerEvent();
        sEvent.setOfficerFirstName(event.getOfficerFirstName());
        sEvent.setOfficerLastName(event.getOfficerLastName());
        sEvent.setOfficerMiddleName(event.getOfficerMiddleName());
        IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);

        IHome home = new Home();
        MetaDataResponseEvent meta = home.findMeta(sEvent, JuvenileCasefile.class);
        if (meta != null && meta.getCount() > 2000)
        {
            super.sendMaxCountExceeded();
            return 0;
        }
        Iterator files = JuvenileCasefile.findAll(sEvent);
        Map juveniles = new FastHashMap();
        while (files.hasNext())
        {
            JuvenileCasefile file = (JuvenileCasefile) files.next();
            postSearchResponseEvents(file, juveniles, true,"");
            count++;
        }
        return count;
    }
}

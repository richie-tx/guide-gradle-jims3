package pd.juvenilecase.casefile.transactions;

import java.util.Iterator;

import pd.juvenilecase.JJSFacilityReport;
import messaging.juvenilecase.GetFacilityPopTotalRptEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;

public class GetFacilityPopTotalRptCommand implements ICommand
{
    public GetFacilityPopTotalRptCommand(){}
    
    public void execute(IEvent event)
    {
	GetFacilityPopTotalRptEvent evt = (GetFacilityPopTotalRptEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
	Iterator facIter = JJSFacilityReport.findAll(evt);		
	JJSFacilityReport facility = null;
	
	int facilityCount=0;
	while (facIter.hasNext()) 
	{
	    facility = (JJSFacilityReport) facIter.next();
	    JuvenileFacilitiesCurrPopResponseEvent resp = facility.valueObject();
	    facilityCount++;
	    dispatch.postEvent(resp);
	}
	
	SearchResultsCountResponseEvent countEvent = new SearchResultsCountResponseEvent();
	countEvent.setNumberOfResults(facilityCount);	     
	dispatch.postEvent(countEvent);
	
    }
}

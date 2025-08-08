package pd.juvenilecase.transactions;

import java.util.Iterator;

import pd.juvenilecase.JJSFacilityReport;
import messaging.juvenilecase.GetFacilityPopTotalRptEvent;
import messaging.juvenilecase.reply.FacilityPopTotalRptResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.util.MessageUtil;

public class GetFacilityPopTotalRptCommand implements ICommand
{
    public GetFacilityPopTotalRptCommand(){}
    
    public void execute(IEvent event) throws Exception
    {
	System.out.println("GetFacilityPopTotalRptCommand");
	GetFacilityPopTotalRptEvent evt = (GetFacilityPopTotalRptEvent)event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
	Iterator facIter = JJSFacilityReport.findAll(evt);		
	JJSFacilityReport facility = null;
	while (facIter.hasNext()) 
	{
	    facility = (JJSFacilityReport) facIter.next();
	    FacilityPopTotalRptResponseEvent resp = new FacilityPopTotalRptResponseEvent();
	    resp.setNeed(facility.getNeeds());
	    resp.setRisk(facility.getRisk());
	    dispatch.postEvent(resp);
	}
    }
}

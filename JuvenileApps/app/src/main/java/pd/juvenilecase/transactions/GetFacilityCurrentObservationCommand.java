package pd.juvenilecase.transactions;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import messaging.juvenilecase.GetFacilityCurrentObservationEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.codetable.Code;
import pd.juvenilecase.JJSFacilityReport;

public class GetFacilityCurrentObservationCommand implements ICommand{
	
	/**
	 * @roseuid 42A9A3040091
	 */
	public GetFacilityCurrentObservationCommand()
	{

	}
	
	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
		GetFacilityCurrentObservationEvent evt = (GetFacilityCurrentObservationEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		Iterator facIter = JJSFacilityReport.findAll(evt);
		int facilityCount =0;

		while (facIter.hasNext()) 
		{
			//iterate and get values for each last sequence number value
		    	JJSFacilityReport facility = (JJSFacilityReport) facIter.next();
			JuvenileFacilitiesCurrPopResponseEvent resp = facility.valueObject();			
				
			facilityCount++;
			dispatch.postEvent(resp);
				
		}		
		
	     SearchResultsCountResponseEvent countEvent = new SearchResultsCountResponseEvent();
	     countEvent.setNumberOfResults(facilityCount);	     
	     dispatch.postEvent(countEvent);
	}
	
	/**
	 * @param event
	 * @roseuid 42A99B990131
	 */
	public void onRegister(IEvent event)
	{

	}
	
	private static HashMap buildObservationCode(String code_table_name){
	    
	    Collection codes = Code.findAll( code_table_name );
	    HashMap map = new HashMap();
            Iterator i = codes.iterator();

            while (i.hasNext())
            {
                Code code = (Code) i.next();
                map.put(code.getCode(), code.getDescription());                

            }
            return map;
            
	}

	/**
	 * @param event
	 * @roseuid 42A99B990133
	 */
	public void onUnregister(IEvent event)
	{

	}

	/**
	 * @param anObject
	 * @roseuid 42A99B99013A
	 */
	public void update(Object anObject)
	{

	}

}

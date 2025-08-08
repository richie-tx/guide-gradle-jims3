package pd.juvenilecase.transactions;

import java.util.Iterator;

import messaging.juvenilecase.GetFacilityCurrentPopRptEvent;
import messaging.juvenilecase.reply.JuvenileFacilitiesCurrPopResponseEvent;
import messaging.juvenilecase.reply.SearchResultsCountResponseEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.JJSFacilityReport;

public class GetFacilityCurrentPopRptCommand implements ICommand{
	
	/**
	 * @roseuid 42A9A3040091
	 */
	public GetFacilityCurrentPopRptCommand()
	{

	}
	
	/**
	 * @param event
	 * @roseuid 42A99B990128
	 */
	public void execute(IEvent event)
	{
	    GetFacilityCurrentPopRptEvent evt = (GetFacilityCurrentPopRptEvent) event;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);		
		Iterator facIter = JJSFacilityReport.findAll(evt);		
		JJSFacilityReport facility = null;

		int facilityCount=0;
		while (facIter.hasNext()) 
		{
		    //iterate and get values for each last sequence number value
		    facility = (JJSFacilityReport) facIter.next();
		    JuvenileFacilitiesCurrPopResponseEvent resp = facility.valueObject();
		 // get pact  details 153576
		   /* GetJuvenilePactSubjectDetailsEvent requestEvent = (GetJuvenilePactSubjectDetailsEvent) EventFactory.getInstance(JuvenileControllerServiceNames.GETJUVENILEPACTSUBJECTDETAILS);
		    requestEvent.setJuvenileId(resp.getJuvenileNum());
		    List<JuvenilePactDetailResponseEvent> pactRespList = MessageUtil.postRequestListFilter(requestEvent, JuvenilePactDetailResponseEvent.class);

    		    if (pactRespList != null)
    		    {
    			//Collections.sort((List<JuvenilePactDetailResponseEvent>) pactRespList, Collections.reverseOrder()); // get the most recent risk values
    			Collections.sort((List<JuvenilePactDetailResponseEvent>)pactRespList,Collections.reverseOrder(new Comparator<JuvenilePactDetailResponseEvent>() {
				@Override
				public int compare(JuvenilePactDetailResponseEvent evt1, JuvenilePactDetailResponseEvent evt2)
				{
				    if (evt1.getrPactLastDate() != null && evt2.getrPactLastDate() != null)
					return evt1.getrPactLastDate().compareTo(evt2.getrPactLastDate());
				    else
					return -1;
				}
			}));
    			Iterator<JuvenilePactDetailResponseEvent> juvenilePactSubjectRespItr = pactRespList.iterator();
    			while (juvenilePactSubjectRespItr.hasNext())
    			{
    			    JuvenilePactDetailResponseEvent respEvent = juvenilePactSubjectRespItr.next();
    			    if (respEvent != null)
    			    {
    				if (respEvent.getRiskLevel() != null)
    				{
    				    resp.setRiskLvlDesc(respEvent.getRiskLevel());
    				    resp.setRiskLvl(respEvent.getRiskLevel().substring(0, 1));
    				}
    				if (respEvent.getRiskLevelOverride() != null)
    				{
    				    resp.setNeedsLvlDesc(respEvent.getRiskLevelOverride());
    				    resp.setNeedsLvl(respEvent.getRiskLevelOverride().substring(0,1));
    				}
    				if(resp.getRiskLvl()!= null&&!resp.getRiskLvl().isEmpty() && resp.getNeedsLvl()!=null&&!resp.getNeedsLvl().isEmpty())
    				{
    				    resp.setRiskneedLvl(resp.getRiskLvl()+'/'+resp.getNeedsLvl());
    				}
    				break; 
    				// get the most recent risk values
    			    }
    			}*/
    		   // }
			
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

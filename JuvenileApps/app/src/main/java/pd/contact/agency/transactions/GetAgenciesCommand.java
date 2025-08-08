//Source file: C:\\views\\dev\\app\\src\\pd\\contact\\transactions\\GetAgenciesCommand.java

package pd.contact.agency.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import naming.PDContactConstants;
import naming.ResponseLocatorConstants;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.transferobjects.helper.AgencyHelper;

/**
 * @author dgibler
 */
public class GetAgenciesCommand extends ResponseCommonUtil implements ICommand
{

    /**
     * @roseuid 4107F7FF02BA
     */
    public GetAgenciesCommand()
    {

    }

    /**
     * @param event
     * @roseuid 4107F6F00208
     */
    public void execute(IEvent event)
    {
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	GetAgenciesEvent gaEvent = (GetAgenciesEvent) event;
	ResponseContextFactory respFac = new ResponseContextFactory();
	ResponseCreator aCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.AGENCY_RESPONSE_LOCATOR, respFac);

	Object obj = AgencyHelper.getAgencies(gaEvent.getAgencyId(), gaEvent.getAgencyName());
	if (obj != null)
	{
	    if (obj instanceof List<?>)
	    {
		List<Agency> agencies = (List<Agency>) obj;
		if (agencies != null)
		{
		    Iterator<Agency> agencyIterator = agencies.iterator();
		    while (agencyIterator.hasNext())
		    {
			Agency agency = agencyIterator.next();
			if (agency != null)
			{
			    AgencyResponseEvent agencyEvent = (AgencyResponseEvent) aCreator.create(agency);
			    agencyEvent.setTopic(PDContactConstants.AGENCY_EVENT_TOPIC);
			    dispatch.postEvent(agencyEvent);
			}
		    }
		}
	    }
	    else
	    {
		if (obj instanceof Agency)
		{
		    Agency agency = (Agency) obj;
		    if (agency != null)
		    {
			CountInfoMessage infoEvent = new CountInfoMessage();
			infoEvent.setCount(agency.getMetaDataRespEvent().getCount());
			dispatch.postEvent(infoEvent);
		    }
		}
	    }
	}



	/*
	ResponseContextFactory respFac = new ResponseContextFactory();
	ResponseCreator aCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.AGENCY_RESPONSE_LOCATOR,respFac);
	GetAgenciesEvent gaEvent = (GetAgenciesEvent) event;

	MetaDataResponseEvent metaData = (MetaDataResponseEvent) Agency.findMeta(gaEvent);
	// Get the IDispatch to post to
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if (metaData.getCount() > 2000){
	CountInfoMessage infoEvent = new CountInfoMessage();
	//      	infoEvent.setMessage("Record count exceeded - total records found = " + metaData.getCount());
	infoEvent.setCount(metaData.getCount());  
	dispatch.postEvent(infoEvent);
	}else{
	// Send the Event to find all of the agencies
	Iterator agencies = Agency.findAll(gaEvent);

	// Iterate through the agencies and post the AgencyResponseEvent for each
	while (agencies.hasNext()){
		Agency agency = (Agency) agencies.next();
		if (agency != null){
			AgencyResponseEvent agencyEvent = (AgencyResponseEvent) aCreator.create(agency);
			agencyEvent.setTopic(PDContactConstants.AGENCY_EVENT_TOPIC);
			dispatch.postEvent(agencyEvent);
		}
	}
	}	*///87191
    }

    /**
     * @param event
     * @roseuid 4107F6F0020A
     */
    public void onRegister(IEvent event)
    {

    }

    /**
     * @param event
     * @roseuid 4107F6F0020C
     */
    public void onUnregister(IEvent event)
    {

    }

    /**
     * @param updateObject
     * @roseuid 4107F7FF02CE
     */
    public void update(Object updateObject)
    {

    }
}
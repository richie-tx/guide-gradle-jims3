package pd.productionsupport.transactions;

import java.util.Iterator;
import java.util.List;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.productionsupport.GetProductionSupportCourtRecordsEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;

import org.apache.commons.collections.IteratorUtils;

import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;

public class GetProductionSupportCourtRecordsCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetProductionSupportCourtRecordsEvent evt = (GetProductionSupportCourtRecordsEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//search by juvenile 
	if (evt.getJuvenileNumber() != null && !evt.getJuvenileNumber().isEmpty())
	{
	    Iterator<JJSCourt> courtItr = JJSCourt.findAll(evt);
	    while (courtItr.hasNext())
	    {
		JJSCourt court = (JJSCourt) courtItr.next();
		DocketEventResponseEvent resp = court.valueObject();
		if (resp != null)
		{		   
		    dispatch.postEvent(resp);
		}
	    }
	}
    }
}

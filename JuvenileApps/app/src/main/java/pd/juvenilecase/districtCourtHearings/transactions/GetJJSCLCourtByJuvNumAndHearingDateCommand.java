package pd.juvenilecase.districtCourtHearings.transactions;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.codetable.GetJuvenileHearingTypesEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByBarNumAndHearingDateEvent;
import messaging.districtCourtHearings.GetJJSCLCourtByJuvNumAndHearingDateEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.Home;
import pd.codetable.criminal.JuvenileHearingTypeCode;
import pd.juvenilecase.JJSCourt;

public class GetJJSCLCourtByJuvNumAndHearingDateCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLCourtByJuvNumAndHearingDateEvent evt = (GetJJSCLCourtByJuvNumAndHearingDateEvent) event;

	GetJuvenileHearingTypesEvent hearingTypeEvt = new GetJuvenileHearingTypesEvent();
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	if ((evt.getJuvenileNumber() != null && !evt.getJuvenileNumber().isEmpty()) && (evt.getCourtDate() != null && !evt.getCourtDate().isEmpty()))
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

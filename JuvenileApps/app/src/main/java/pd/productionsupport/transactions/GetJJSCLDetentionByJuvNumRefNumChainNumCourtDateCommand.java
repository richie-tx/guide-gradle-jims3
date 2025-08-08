package pd.productionsupport.transactions;

import java.util.Iterator;

import messaging.calendar.reply.DocketEventResponseEvent;
import messaging.productionsupport.GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent;
import mojo.km.context.ICommand;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.IEvent;
import pd.juvenilecase.detentionCourtHearings.JJSCLDetention;

public class GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateCommand implements ICommand
{

    @Override
    public void execute(IEvent event) throws Exception
    {
	GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent evt = (GetJJSCLDetentionByJuvNumRefNumChainNumCourtDateEvent) event;
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REPLY);
	//search by juvenile 
	if (evt.getJuvenileNumber() != null && !evt.getJuvenileNumber().isEmpty())
	{
	    Iterator<JJSCLDetention> courtItr = JJSCLDetention.findAll(evt);
	    while (courtItr.hasNext())
	    {
		JJSCLDetention court = (JJSCLDetention) courtItr.next();
		DocketEventResponseEvent resp = court.valueObject();
		if (resp != null)
		{		   
		    dispatch.postEvent(resp);
		}
	    }
	}
    }
}
